package com.zeeyeh.probsolve.question.api.model.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.mybatisflex.core.keygen.KeyGenerators;
import com.zeeyeh.probsolve.question.api.model.enums.QuestionStatus;
import com.zeeyeh.probsolve.question.api.model.enums.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 题目表
 *
 * @author Qinloren
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("pb_questions")
public class Question implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 题目 Id
     */
    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    private Long id;

    /**
     * 题目内容
     */
    private String content;

    /**
     * 题目类型
     */
    private QuestionType type;

    /**
     * 题目难度
     */
    private Integer difficulty;

    /**
     * 题目分数
     */
    private Integer score;

    /**
     * 题目解析
     */
    private String analysis;

    /**
     * 题目来源
     */
    private String source;

    /**
     * 题目状态
     */
    private QuestionStatus status;

    /**
     * 创建人 Id
     */
    @Column("user_id")
    private Long userId;

    /**
     * 创建时间
     */
    @Column("create_time")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @Column("update_time")
    private LocalDateTime updateTime;
}
