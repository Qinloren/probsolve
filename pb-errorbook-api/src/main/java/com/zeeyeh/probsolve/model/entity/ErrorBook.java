package com.zeeyeh.probsolve.model.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.mybatisflex.core.keygen.KeyGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("pb_error_book")
public class ErrorBook implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 错题 id
     */
    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    private Long id;

    /**
     * 用户 id
     */
    @Column("user_id")
    private Long userId;

    /**
     * 题目 id
     */
    @Column("question_id")
    private Long questionId;

    /**
     * 错误原因
     */
    private String reason;

    /**
     * 加入时间
     */
    private LocalDateTime createTime;

    /**
     * 是否删除
     */
    @Column(value = "is_deleted", isLogicDelete = true)
    private Boolean isDeleted;
}
