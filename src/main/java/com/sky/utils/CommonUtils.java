package com.sky.utils;

import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.function.EntityResponse;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public class CommonUtils {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(CommonUtils.class);
    private static final String ENUM_CLASS_NOT_FOUND = "enu.class.not.found";

    private CommonUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static EntityResponse getEnumByKey(String className, String actionName) {
        try {
            if (!StringUtils.hasText(actionName)) {
                actionName = "map";
            }
            Class<?> classz = Class.forName(className);
            Method method = classz.getMethod(actionName);
            Object object = method.invoke(new HashMap<>());
//return MapperU
        } catch (ClassNotFoundException var) {
            log.error("Enum class not found");
            //throw new EntityNotFoundException("class: " + className + " not found");
        } catch (NoSuchMethodError var) {
            log.error("Enum class not found");
            //throw new EntityNotFoundException("class: " + className + " not found");
        } catch (IllegalAccessException var) {
            log.error("Enum class not found");
            //throw new EntityNotFoundException("class: " + className + " not found");
        } catch (InvocationTargetException var) {
            log.error("Enum class not found");
            //throw new EntityNotFoundException("class: " + className + " not found");
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
