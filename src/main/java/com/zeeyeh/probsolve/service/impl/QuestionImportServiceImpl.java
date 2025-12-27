package com.zeeyeh.probsolve.service.impl;

import com.zeeyeh.probsolve.entity.ImportRow;
import com.zeeyeh.probsolve.entity.data.QuestionAnswers;
import com.zeeyeh.probsolve.entity.data.QuestionCategoryRelation;
import com.zeeyeh.probsolve.entity.data.Questions;
import com.zeeyeh.probsolve.service.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionImportServiceImpl implements QuestionImportService {
    private final QuestionsService questionsService;
    private final QuestionAnswersService questionAnswersService;
    private final QuestionCategoryRelationService questionCategoryRelationService;

    public QuestionImportServiceImpl(QuestionsService questionsService, QuestionAnswersService questionAnswersService, QuestionCategoryRelationService questionCategoryRelationService) {
        this.questionsService = questionsService;
        this.questionAnswersService = questionAnswersService;
        this.questionCategoryRelationService = questionCategoryRelationService;
    }

    @Override
    public void saveBatch(List<ImportRow> importRows, Long categoryId) {
        List<Questions> questions = new ArrayList<>(importRows.size());
        for (ImportRow importRow : importRows) {
            questions.add(importRow.getQuestions());
        }
        questionsService.saveBatch(questions);
        List<QuestionAnswers> answers = new ArrayList<>(importRows.size());
        List<QuestionCategoryRelation> relations = new ArrayList<>(importRows.size());

        for (ImportRow importRow : importRows) {
            Questions question = importRow.getQuestions();
            importRow.getQuestionAnswers().setQuestionId(question.getId());
            answers.add(importRow.getQuestionAnswers());
            QuestionCategoryRelation relation = new QuestionCategoryRelation();
            relation.setQuestionsId(question.getId());
            relation.setCategoryId(categoryId);
            relations.add(relation);
        }

        questionAnswersService.saveBatch(answers);
        questionCategoryRelationService.saveBatch(relations);
    }
}
