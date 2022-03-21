package com.fpoly.huongque.duantotnghiep.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class ConvertUtils {

    private static final Logger log = LoggerFactory.getLogger(ConvertUtils.class);

    private static final String PREFIX_SET = "set";
    private static final String PREFIX_GET = "get";

    public  static <ENTITY, MODEL> ENTITY toEntity(MODEL MODEL, Class<ENTITY> entityClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        ENTITY entity = converter(MODEL, entityClass);
        return entity;
    }

    public static <ENTITY, MODEL> MODEL toModel(ENTITY entity, Class<MODEL> MODELClass) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        MODEL MODEL = converter(entity, MODELClass);
        return MODEL;
    }

    private static <T1,T2> T2 converter(T1 t1, Class<T2> t2Class) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> MODELClass = t1.getClass();
        Constructor<T2> t2Constructor = t2Class.getConstructor();
        T2 t2 = t2Constructor.newInstance();
        Field[] fields = t2Class.getDeclaredFields();

        Arrays.stream(fields).forEach( field -> {
            String originalName = field.getName();
            String name = resolverMethodName(originalName);
            String getMethod = PREFIX_GET + name;
            String setMethod = PREFIX_SET + name;

            try {
                Method MODELMethod = MODELClass.getMethod(getMethod);
                Method entityMethod = t2Class.getMethod(setMethod, field.getType());
                entityMethod.invoke(t2, MODELMethod.invoke(t1));
            } catch (NoSuchMethodException e) {
                log.error("not found method: " + getMethod);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

        });
        return t2;
    }

    private static String resolverMethodName(String originalName) {
        String first = originalName.substring(0, 1);
        String rest = originalName.substring(1);
        return String.format("%s%s", first.toUpperCase(), rest);
    }

}
