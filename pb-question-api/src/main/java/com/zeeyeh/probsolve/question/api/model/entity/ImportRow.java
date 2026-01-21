package com.zeeyeh.probsolve.question.api.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 导入行
 *
 * @author Qinloren
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImportRow {
    private Question questions;
    QuestionAnswer questionAnswers;
}
