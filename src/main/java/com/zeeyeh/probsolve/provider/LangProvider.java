package com.zeeyeh.probsolve.provider;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

@Component
public class LangProvider {

    @Resource
    private HttpServletRequest request;
    private static final String ACCEPT_LANGUAGE = HttpHeaders.ACCEPT_LANGUAGE;
    public static final String FILTER_PREFIX = "#";

    public String translate(String key, Object ...args) {
        if (key.startsWith(FILTER_PREFIX)) {
            return key.substring(1);
        }
        String header = request.getHeader(ACCEPT_LANGUAGE);
        try {
            ResourceBundle resourceBundle = getResourceBundle(header);
            return MessageFormat.format(resourceBundle.getString(key), args);
        } catch (Exception e) {
            return key;
        }
    }

    private ResourceBundle getResourceBundle(String lang) {
        if (StringUtils.hasText(lang)) {
            return ResourceBundle.getBundle("message");
        }
        try {
            Locale locale = parseLocale(lang);
            if (locale != null) {
                return ResourceBundle.getBundle("message", locale);
            }
        } catch (Exception ignored) {}
        return ResourceBundle.getBundle("message");
    }

    private Locale parseLocale(String lang) {
        String[] parts = lang.replace("-", "_").split("_");
        if (parts.length == 1) {
            return new Locale(parts[0]);
        } else if (parts.length == 2) {
            return new Locale(parts[0], parts[1]);
        }
        return null;
    }
}
