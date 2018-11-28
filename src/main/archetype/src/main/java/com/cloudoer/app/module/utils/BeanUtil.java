package com.cloudoer.bigtour.core.utils;

import org.springframework.beans.BeanUtils;

/**
 * @author code gen
 * @version 0.0.1
 * @since 2018/11/19
 */
public class BeanUtil {

    public static <T> T copyProperties(Object source, T target) {

        BeanUtils.copyProperties(source, target);
        return target;
    }
}