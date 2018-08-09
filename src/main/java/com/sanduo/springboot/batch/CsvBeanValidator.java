package com.sanduo.springboot.batch;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.item.validator.Validator;
import org.springframework.beans.factory.InitializingBean;

/**
 * 校验数据
 * 
 * @author sanduo
 * @date 2018/08/03
 */
public class CsvBeanValidator<T> implements Validator<T>, InitializingBean {

    private javax.validation.Validator validator;

    /* 初始化校验数据
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validatorFactory.usingContext().getValidator();

    }

    /* 校验
     * @see org.springframework.batch.item.validator.Validator#validate(java.lang.Object)
     */
    @Override
    public void validate(T value) throws ValidationException {
        Set<ConstraintViolation<T>> violations = validator.validate(value);
        if (violations.size() > 0) {
            StringBuilder message = new StringBuilder();
            for (ConstraintViolation<T> constraintViolation : violations) {
                message.append(constraintViolation.getMessage() + "\n");
            }
            throw new ValidationException(message.toString());
        }
    }

}
