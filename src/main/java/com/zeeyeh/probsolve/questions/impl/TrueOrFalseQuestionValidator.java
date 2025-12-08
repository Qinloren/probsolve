package com.zeeyeh.probsolve.questions.impl;

import com.zeeyeh.probsolve.entity.data.Questions;
import com.zeeyeh.probsolve.questions.QuestionValidator;
import org.springframework.stereotype.Service;

@Service
public class TrueOrFalseQuestionValidator implements QuestionValidator {
    @Override
    public boolean validate(Questions questions, Object answer) {
        return false;
    }

    @Override
    public Integer getType() {
        return 3;
    }
}
