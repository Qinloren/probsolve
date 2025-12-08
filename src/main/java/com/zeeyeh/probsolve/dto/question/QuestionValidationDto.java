package com.zeeyeh.probsolve.dto.question;

public class QuestionValidationDto {
    private Long questionId;
    private Object answer;

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Object getAnswer() {
        return answer;
    }

    public void setAnswer(Object answer) {
        this.answer = answer;
    }
}
