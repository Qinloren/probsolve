package com.zeeyeh.probsolve.ai.provider.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.zeeyeh.probsolve.ai.provider.ReasonProvider;
import dev.langchain4j.data.message.Content;
import dev.langchain4j.data.message.TextContent;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReasonProviderImpl implements ReasonProvider {

    @Resource
    private ChatModel chatModel;

    @Override
    public String getReason(String type, boolean isChoice, boolean isMultiChoice,
                            String stem, JSONArray options,
                            Object userAnswer, Object correctAnswer) {
        List<Content> contentList = new ArrayList<>();
        contentList.add(TextContent.from("请帮我分析一下我这道题的错误原因："));
        contentList.add(TextContent.from("我的题目是：" + stem));
        if (isChoice) {
            if (isMultiChoice) {
                contentList.add(TextContent.from("这是一道多选题"));
            } else {
                contentList.add(TextContent.from("这是一道单选题"));
            }
            contentList.add(TextContent.from("我的选项有：" + getOptionsToString(options)));
        } else {
            contentList.add(TextContent.from("这是一道：" + type));
        }
        contentList.add(TextContent.from("我的答案是：" + JSON.toJSONString(userAnswer)));
        contentList.add(TextContent.from("正确答案是：" + JSON.toJSONString(correctAnswer)));
        contentList.add(TextContent.from("内容尽可能的简洁明了，不要长篇大论，只返回错误原因和描述关键点原因"));
        UserMessage message = UserMessage.from(contentList);
        ChatResponse response = chatModel.chat(message);
        return response.aiMessage().text();
    }

    /**
     * 将选项数组转换为字符串
     * @param optionsArray 选项数组
     * @return 选项字符串
     */
    public String getOptionsToString(JSONArray optionsArray) {
        JSONArray jsonArray = new JSONArray();
        char letter = 'a';
        for (Object o : optionsArray) {
            String option = String.valueOf(o);
            jsonArray.add(letter + "." + option);
            letter++;
        }
        return jsonArray.toJSONString();
    }
}
