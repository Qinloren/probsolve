package com.zeeyeh.probsolve.ai.provider;

import com.alibaba.fastjson2.JSONArray;

/**
 * 原因提供者接口
 */
public interface ReasonProvider {
    /**
     * 获取原因
     * @param type 题型
     * @param isChoice 是否选择题
     * @param isMultiChoice 是否多选题
     * @param stem 题干
     * @param options 可选选项。每个元素必须是一个字符串
     * @param userAnswer 用户答案
     * @param correctAnswer 正确答案
     * @return 原因
     */
    String getReason(String type, boolean isChoice, boolean isMultiChoice, String stem, JSONArray options, Object userAnswer, Object correctAnswer);
}
