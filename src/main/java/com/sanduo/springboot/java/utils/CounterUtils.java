package com.sanduo.springboot.java.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 计数
 * 
 * @author sanduo
 * @date 2018/07/13
 */
public class CounterUtils extends HashMap<Class<?>, Integer> {

    /**
     * 类型计数
     */
    private static final long serialVersionUID = 8141798737908842177L;

    private Class<?> baseType;// 基本类型

    /**
     * @param baseType
     */
    public CounterUtils(Class<?> baseType) {
        super();
        this.baseType = baseType;
    }

    public void count(Object obj) {
        Class<?> type = obj.getClass();
        if (!baseType.isAssignableFrom(type))// baseType 不是type的父类,运行时检查
            throw new RuntimeException(obj + "incorrect type:" + type + ". should be type or subtype of baseType");
        // baseType 是type的父类
        countClass(type);
    }

    /**
     * 计数是否是某个类的子类的
     * 
     * @param type
     */
    private void countClass(Class<?> type) {
        // 获取当前类计数
        Integer quantity = get(type);
        put(type, quantity == null ? 1 : quantity + 1);
        // 获取父类
        Class<?> superClass = type.getSuperclass();
        // 父类不等于null，同时父类的父类还是baseType
        if (superClass != null && baseType.isAssignableFrom(superClass)) {
            // 计数
            countClass(superClass);
        }
    }

    /* 重写,json 格式化
     * @see java.util.AbstractMap#toString()
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("{");
        for (Map.Entry<Class<?>, Integer> pair : entrySet()) {
            result.append(pair.getKey().getSimpleName());
            result.append("=");
            result.append(pair.getValue());
            result.append(",");
        }
        result.delete(result.length() - 2, result.length());
        result.append("}");

        return result.toString();
    }
}
