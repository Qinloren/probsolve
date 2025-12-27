package com.zeeyeh.probsolve.entity;

import com.zeeyeh.probsolve.entity.data.QuestionAnswers;
import com.zeeyeh.probsolve.entity.data.Questions;

public class ImportRow {
    private Questions questions;
    QuestionAnswers questionAnswers;

    public ImportRow() {
    }

    public ImportRow(Questions questions, QuestionAnswers questionAnswers) {
        this.questions = questions;
        this.questionAnswers = questionAnswers;
    }

    public Questions getQuestions() {
        return questions;
    }

    public void setQuestions(Questions questions) {
        this.questions = questions;
    }

    public QuestionAnswers getQuestionAnswers() {
        return questionAnswers;
    }

    public void setQuestionAnswers(QuestionAnswers questionAnswers) {
        this.questionAnswers = questionAnswers;
    }
}
