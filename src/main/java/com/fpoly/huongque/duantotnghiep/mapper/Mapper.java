package com.fpoly.huongque.duantotnghiep.mapper;

import com.fpoly.huongque.duantotnghiep.utils.ConvertUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;

@Component
public interface Mapper<ENTITY, MODEL> {

    default MODEL toModel(ENTITY e, Class<MODEL> modelClass) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        return ConvertUtils.toModel(e, modelClass);
    }
    default ENTITY toEntity(MODEL m, Class<ENTITY> entityClass) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return ConvertUtils.toEntity(m, entityClass);
    }
}
