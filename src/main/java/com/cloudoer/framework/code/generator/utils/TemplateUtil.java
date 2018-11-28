package com.cloudoer.framework.code.generator.utils;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author liuxiaokun
 * @version 0.0.1
 * @since 2018/11/27
 */
public class TemplateUtil {

    public static String getTemplateContent(String templatePath) {
        String content = null;
        InputStream in = Object.class.getResourceAsStream(templatePath);
        try {
            content = FileUtils.readAsText(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
}
