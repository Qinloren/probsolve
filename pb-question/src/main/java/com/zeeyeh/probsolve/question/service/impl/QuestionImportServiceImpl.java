package com.zeeyeh.probsolve.question.service.impl;

import com.zeeyeh.probsolve.question.api.model.entity.ImportRow;
import com.zeeyeh.probsolve.question.api.model.entity.Question;
import com.zeeyeh.probsolve.question.api.model.entity.QuestionAnswer;
import com.zeeyeh.probsolve.question.api.model.entity.QuestionCategoryRelation;
import com.zeeyeh.probsolve.question.imports.task.api.QuestionImportTaskApi;
import com.zeeyeh.probsolve.question.service.QuestionAnswerService;
import com.zeeyeh.probsolve.question.service.QuestionCategoryRelationService;
import com.zeeyeh.probsolve.question.service.QuestionImportService;
import com.zeeyeh.probsolve.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * QuestionImportService 实现类
 *
 * @author Qinloren
 */
@Service
@RequiredArgsConstructor
public class QuestionImportServiceImpl implements QuestionImportService {

    private final QuestionService questionService;
    private final QuestionAnswerService questionAnswerService;
    private final QuestionCategoryRelationService questionCategoryRelationService;
    private final QuestionImportTaskApi questionImportTaskApi;

    @Override
    public void saveBatch(List<ImportRow> importRows, Long categoryId) {
        List<Question> questions = new ArrayList<>(importRows.size());
        for (ImportRow importRow : importRows) {
            questions.add(importRow.getQuestions());
        }
        questionService.saveBatch(questions);
        List<QuestionAnswer> answers = new ArrayList<>(importRows.size());
        List<QuestionCategoryRelation> relations = new ArrayList<>(importRows.size());

        for (ImportRow importRow : importRows) {
            Question question = importRow.getQuestions();
            importRow.getQuestionAnswers().setQuestionId(question.getId());
            answers.add(importRow.getQuestionAnswers());
            QuestionCategoryRelation relation = new QuestionCategoryRelation();
            relation.setQuestionId(question.getId());
            relation.setCategoryId(categoryId);
            relations.add(relation);
        }

        questionAnswerService.saveBatch(answers);
        questionCategoryRelationService.saveBatch(relations);
    }

    @Override
    public void incrementSuccess(String taskId, int count) {
        questionImportTaskApi.incrementSuccess(taskId, count);
    }

    @Override
    public void incrementError(String taskId, int count) {
        questionImportTaskApi.incrementError(taskId, count);
    }

    @Override
    public void updateTotal(String taskId, int count) {
        questionImportTaskApi.updateTotal(taskId, count);
    }
}
