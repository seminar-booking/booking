package com.example.member.util;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.FeatureDescriptor;
import java.beans.PropertyDescriptor;
import java.util.Arrays;

public class EntityBeanWrapper {

    private static String[] getIgnoreFieldNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);

        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        return Arrays.stream(pds)
                .filter(descriptor ->
                    !src.isReadableProperty(descriptor.getName())
                            || src.getPropertyValue(descriptor.getName()) == null
                )
                .map(FeatureDescriptor::getName)
                .toArray(String[]::new);
    }

    public static <T> T copyProperties(T source, T target, String... ignoreFields) {

        BeanUtils.copyProperties(source, target,
                ArrayUtils.addAll(EntityBeanWrapper.getIgnoreFieldNames(source), ignoreFields));
        return target;
    }
}
