package com.zeeyeh.probsolve.questions.impl;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.mybatisflex.core.query.QueryWrapper;
import com.zeeyeh.probsolve.entity.ImportRow;
import com.zeeyeh.probsolve.entity.data.QuestionAnswers;
import com.zeeyeh.probsolve.entity.data.QuestionCategories;
import com.zeeyeh.probsolve.entity.data.Questions;
import com.zeeyeh.probsolve.exceptions.GlobalError;
import com.zeeyeh.probsolve.exceptions.ServiceException;
import com.zeeyeh.probsolve.questions.QuestionLibFileHandler;
import com.zeeyeh.probsolve.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TxtQuestionLibFileHandler implements QuestionLibFileHandler {

    private static final Logger log = LoggerFactory.getLogger(TxtQuestionLibFileHandler.class);
    private static final int BATCH_SIZE = 200;
    private final QuestionCategoriesService questionCategoriesService;
    private final QuestionImportTaskService questionImportTaskService;
    private final QuestionImportService questionImportService;

    public TxtQuestionLibFileHandler(QuestionCategoriesService questionCategoriesService, QuestionImportTaskService questionImportTaskService, QuestionImportService questionImportService) {
        this.questionCategoriesService = questionCategoriesService;
        this.questionImportTaskService = questionImportTaskService;
        this.questionImportService = questionImportService;
    }

    protected List<JSONObject> singleList = new ArrayList<>();
    protected List<JSONObject> multiList = new ArrayList<>();
    protected List<JSONObject> trueOrFalseList = new ArrayList<>();
    protected List<JSONObject> fillList = new ArrayList<>();
    protected List<JSONObject> shortAnswerList = new ArrayList<>();

    private boolean inHeader = false;
    private String libName;
    private String libSignature;

    @Override
    public boolean handler(InputStream inputStream, String taskId, Long uid) {
        try {
            // 清空之前的数据
            singleList.clear();
            multiList.clear();
            trueOrFalseList.clear();
            fillList.clear();
            shortAnswerList.clear();
            libName = null;
            libSignature = null;

            // 读取输入流并转换为字符串列表
            List<String> lines = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
            }

            parseLines(lines);

            String finalLibName = StringUtils.hasText(libName)
                    ? libName
                    : "txt_import_" + taskId;

            String finalLibSignature = StringUtils.hasText(libSignature)
                    ? libSignature
                    : "";

            // 创建分类
            Long categoryId = createCategoryOnce(taskId, finalLibName, finalLibSignature, uid);

            // 将解析出的题目数据保存到数据库
            processAndSaveQuestions(singleList, 1, categoryId, uid, taskId); // 单选题
            processAndSaveQuestions(multiList, 2, categoryId, uid, taskId); // 多选题
            processAndSaveQuestions(trueOrFalseList, 3, categoryId, uid, taskId); // 判断题
            processAndSaveQuestions(fillList, 4, categoryId, uid, taskId); // 填空题
            processAndSaveQuestions(shortAnswerList, 5, categoryId, uid, taskId); // 简答题

            log.info("TXT题库导入完成 taskId: {}", taskId);
            return true;
        } catch (Exception e) {
            log.error("解析TXT题库文件失败: {}", e.getMessage());
            e.fillInStackTrace();
            return false;
        }
    }

    /**
     * 解析所有题目
     *
     * @param lines 文件所有行内容
     */
    public void parseLines(List<String> lines) {
        Integer type = null;
        JSONObject currentQuestion = new JSONObject();
        JSONArray options = new JSONArray();
        for (String rawLine : lines) {
            String line = rawLine.trim();
            if (!StringUtils.hasText(line)) {
                continue;
            }
            if (line.contains("header")) {
                inHeader = true;
                continue;
            }
            if (inHeader && line.startsWith("//====")) {
                inHeader = false;
            }
            if (inHeader) {
                if (line.startsWith("题库名")) {
                    libName = line.substring(4).trim();
                }
                if (line.startsWith("简介")) {
                    libSignature = line.substring(3).trim();
                }
                continue;
            }
            if (line.contains("单选题")) {
                // 保存前一个题目（如果存在）
                if (currentQuestion.containsKey("title") && type != null) {
                    addQuestion(type, currentQuestion);
                }
                type = 0;
                currentQuestion = new JSONObject(); // 重置当前题目
                options = new JSONArray(); // 重置选项数组
                continue;
            }
            if (line.contains("多选题")) {
                // 保存前一个题目（如果存在）
                if (currentQuestion.containsKey("title") && type != null) {
                    addQuestion(type, currentQuestion);
                }
                type = 1;
                currentQuestion = new JSONObject(); // 重置当前题目
                options = new JSONArray(); // 重置选项数组
                continue;
            }
            if (line.contains("判断题")) {
                // 保存前一个题目（如果存在）
                if (currentQuestion.containsKey("title") && type != null) {
                    addQuestion(type, currentQuestion);
                }
                type = 2;
                currentQuestion = new JSONObject(); // 重置当前题目
                options = new JSONArray(); // 重置选项数组
                continue;
            }
            if (line.contains("填空题")) {
                // 保存前一个题目（如果存在）
                if (currentQuestion.containsKey("title") && type != null) {
                    addQuestion(type, currentQuestion);
                }
                type = 3;
                currentQuestion = new JSONObject(); // 重置当前题目
                options = new JSONArray(); // 重置选项数组
                continue;
            }
            if (line.contains("简答题")) {
                // 保存前一个题目（如果存在）
                if (currentQuestion.containsKey("title") && type != null) {
                    addQuestion(type, currentQuestion);
                }
                type = 4;
                currentQuestion = new JSONObject(); // 重置当前题目
                options = new JSONArray(); // 重置选项数组
                continue;
            }

            if (line.startsWith("题目：")) {
                // 保存前一个题目（如果存在）
                if (currentQuestion.containsKey("title") && type != null && currentQuestion.getString("title") != null) {
                    addQuestion(type, currentQuestion);
                }
                
                // 开始新题目
                currentQuestion = new JSONObject();
                currentQuestion.put("title", line.substring(3).trim());
                options = new JSONArray(); // 重置选项数组
                continue;
            }
            if (type == null) {
                continue;
            }
            if ((type == 0 || type == 1) && line.matches("^[A-Za-z]\\..*")) {
                // 选择题选项
                String key = line.substring(0, 1).toLowerCase();
                String value = line.substring(2).trim();
                JSONObject optionObject = new JSONObject();
                String keyString = "abcd";
                if (!keyString.contains(key)) {
                    continue;
                }
                int keyIndexChar = key.toCharArray()[0];
                int keyIndex = keyIndexChar - 'a';
                optionObject.put("index", keyIndex);
                optionObject.put("value", value);
                options.add(optionObject);
                currentQuestion.put("options", options);
                continue;
            }

            if (line.startsWith("正确答案：")) {
                currentQuestion.put("answer", line.substring(5).trim());
                continue;
            }

            if (line.startsWith("解析：")) {
                currentQuestion.put("analysis", line.substring(3).trim());
            }
        }
        
        // 添加最后一个题目（如果存在）
        if (currentQuestion.containsKey("title") && currentQuestion.getString("title") != null && type != null) {
            addQuestion(type, currentQuestion);
        }
    }

    private void addQuestion(Integer type, JSONObject question) {
        if (type == null) {
            return;
        }
        switch (type) {
            case 0 -> singleList.add(new JSONObject(question));
            case 1 -> multiList.add(new JSONObject(question));
            case 2 -> trueOrFalseList.add(new JSONObject(question));
            case 3 -> fillList.add(new JSONObject(question));
            case 4 -> shortAnswerList.add(new JSONObject(question));
            default -> throw new IllegalArgumentException("不支持的题型");
        }
    }

    /**
     * 处理并保存题目到数据库
     */
    private void processAndSaveQuestions(List<JSONObject> questionList, int questionType, Long categoryId, Long uid, String taskId) {
        if (questionList.isEmpty()) {
            return;
        }

        List<ImportRow> list = new ArrayList<>(BATCH_SIZE);
        int total = 0;
        int success = 0;
        int error = 0;

        for (JSONObject questionJson : questionList) {
            total++;
            try {
                // 转换题目格式
                QuestionDescription desc = handlerQuestion(questionJson, questionType, uid);
                ImportRow importRow = new ImportRow();
                importRow.setQuestions(desc.getQuestions());
                importRow.setQuestionAnswers(desc.getQuestionAnswers());
                list.add(importRow);

                if (list.size() >= BATCH_SIZE) {
                    questionImportService.saveBatch(list, categoryId);
                    success += list.size();
                    questionImportTaskService.incrementSuccess(taskId, list.size());
                    list.clear();
                }
            } catch (Exception e) {
                error++;
                log.error("题目导入失败， taskId={}", taskId, e);
            }
        }

        // 处理剩余的题目
        if (!list.isEmpty()) {
            try {
                questionImportService.saveBatch(list, categoryId);
                success += list.size();
                questionImportTaskService.incrementSuccess(taskId, list.size());
            } catch (Exception e) {
                error += list.size();
                log.error("题目导入失败， taskId={}", taskId, e);
            }
        }

        if (error > 0) {
            questionImportTaskService.incrementError(taskId, error);
        }
        if (success > 0) {
            questionImportTaskService.incrementSuccess(taskId, success);
        }
        questionImportTaskService.updateTotal(taskId, total);
        questionCategoriesService.updateSize(categoryId, total);
    }

    private Long createCategoryOnce(String taskId, String libName, String libSignature, Long uid) {
        QueryWrapper queryWrapper = QueryWrapper.create()
                .eq(QuestionCategories::getName, libName)
                .eq(QuestionCategories::getUserId, uid);

        if (questionCategoriesService.exists(queryWrapper)) {
            throw new ServiceException(GlobalError.QUESTION_CATEGORY_ALREADY_FOUND);
        }

        QuestionCategories questionCategories = new QuestionCategories();
        questionCategories.setName(libName);
        questionCategories.setSignature(libSignature);
        questionCategories.setUserId(uid);
        questionCategories.setStatus(1);
        questionCategories.setTaskId(taskId);
        questionCategories.setCreateTime(LocalDateTime.now());
        questionCategories.setUpdateTime(LocalDateTime.now());

        if (!questionCategoriesService.save(questionCategories)) {
            throw new ServiceException(GlobalError.QUESTION_CATEGORY_CREATE_FAILED);
        }
        return questionCategories.getId();
    }

    private QuestionDescription handlerQuestion(JSONObject jsonObject, int questionType, Long uid) {
        QuestionDescription questionDescription = new QuestionDescription();
        Questions questions = new Questions();
        String title = jsonObject.getString("title");
        Integer difficulty = jsonObject.getInteger("difficulty");
        if (difficulty == null) {
            difficulty = 1; // 默认难度
        }
        Integer score = jsonObject.getInteger("score");
        if (score == null) {
            score = 1; // 默认分数
        }
        String analysis = jsonObject.getString("analysis");
        String tips = jsonObject.getString("tips");
        questions.setCreateTime(LocalDateTime.now());
        questions.setUpdateTime(LocalDateTime.now());
        questions.setContent(title);
        questions.setScore(score);
        questions.setAnalysis(analysis);
        questions.setStatus(1);
        questions.setUserId(uid);
        questions.setDifficulty(difficulty);
        questions.setType(questionType); // 使用传入的类型参数
        QuestionAnswers questionAnswers = new QuestionAnswers();
        questionAnswers.setTips(tips);
        switch (questionType) {
            case 1:
                // 单选题
                JSONArray singleOptions = jsonObject.getJSONArray("options");
                questionAnswers.setContent(singleOptions != null ? singleOptions.toJSONString() : "[]");
                String singleAnswer = jsonObject.getString("answer");
                questionAnswers.setAnswers(singleAnswer != null ? singleAnswer : "");
                break;
            case 2:
                // 多选题
                JSONArray multiOptions = jsonObject.getJSONArray("options");
                questionAnswers.setContent(multiOptions != null ? multiOptions.toJSONString() : "[]");
                String multiAnswer = jsonObject.getString("answer");
                questionAnswers.setAnswers(multiAnswer != null ? multiAnswer : "[]");
                break;
            case 3:
                // 判断题
                String judgeAnswer = jsonObject.getString("answer");
                questionAnswers.setAnswers(judgeAnswer != null ? judgeAnswer : "");
                break;
            case 4:
                // 填空题
                JSONArray gapFillingAnswers = jsonObject.getJSONArray("answer");
                questionAnswers.setAnswers(gapFillingAnswers != null ? gapFillingAnswers.toJSONString() : "[]");
                break;
            case 5:
                // 简答题
                String shortAnswer = jsonObject.getString("answer");
                questionAnswers.setAnswers(shortAnswer != null ? shortAnswer : "");
                break;
        }
        questionDescription.setQuestions(questions);
        questionDescription.setQuestionAnswers(questionAnswers);
        return questionDescription;
    }

    public static class QuestionDescription {
        private Questions questions;
        private QuestionAnswers questionAnswers;

        public QuestionDescription() {
        }

        public QuestionDescription(Questions questions, QuestionAnswers questionAnswers) {
            this.questions = questions;
            this.questionAnswers = questionAnswers;
        }

        public Questions getQuestions() {
            return questions;
        }

        public QuestionDescription setQuestions(Questions questions) {
            this.questions = questions;
            return this;
        }

        public QuestionAnswers getQuestionAnswers() {
            return questionAnswers;
        }

        public void setQuestionAnswers(QuestionAnswers questionAnswers) {
            this.questionAnswers = questionAnswers;
        }
    }

    @Override
    public String getType() {
        return "txt";
    }
}
