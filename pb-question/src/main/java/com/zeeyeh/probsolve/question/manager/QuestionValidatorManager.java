package com.zeeyeh.probsolve.question.manager;

import com.zeeyeh.probsolve.common.exceptions.ResponseCode;
import com.zeeyeh.probsolve.common.exceptions.ServiceException;
import com.zeeyeh.probsolve.question.QuestionValidator;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 题目验证器管理器
 *
 * @author Qinloren
 */
@Slf4j
@Component
@Getter
public class QuestionValidatorManager {

    private final Map<Integer, QuestionValidator> validators;
    private final Map<Integer, String> questionTypes = Map.of(
            1,
            "单选题",
            2,
            "多选题",
            3,
            "判断题",
            4,
            "填空题",
            5,
            "简答题"
    );


    public QuestionValidatorManager(Set<QuestionValidator> validators) {
        this.validators = new ConcurrentHashMap<>();
        for (QuestionValidator validator : validators) {
            log.info("已注册题目校验器: {}", getQuestionTypeName(validator.getType()));
            this.validators.put(validator.getType(), validator);
        }
        log.info("题目校验器已注册完成，共计 {} 个", validators.size());
    }

    /**
     * 获取题目验证器
     * @param type 题目类型
     * @return 验证器
     */
    public QuestionValidator getValidator(Integer type) {
        QuestionValidator questionValidator = validators.get(type);
        if (questionValidator == null) {
            throw new ServiceException(ResponseCode.PARAM_ERROR, "题目校验器不存在");
        }
        return questionValidator;
    }

    /**
     * 判断是否支持该题目类型
     * @param type 题目类型
     * @return 是否支持
     */
    public boolean supports(Integer type) {
        return validators.containsKey(type);
    }

    /**
     * 获取所有支持的题目类型
     * @return 题目类型
     */
    public Set<Integer> getSupportedTypes() {
        return validators.keySet();
    }

    /**
     * 获取题目类型名称
     * @param type 题目类型
     * @return 题目类型名称
     */
    public String getQuestionTypeName(Integer type) {
        return questionTypes.get(type);
    }
}
