package com.sanduo.springboot.batch;

import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidationException;

import com.sanduo.springboot.entity.People;

/**
 * @author sanduo
 * @date 2018/08/03
 */
public class CsvItemProcess extends ValidatingItemProcessor<People> {

    /* 处理数据
     * @see org.springframework.batch.item.validator.ValidatingItemProcessor#process(java.lang.Object)
     */
    @Override
    public People process(People item) throws ValidationException {
        super.process(item);// 必须调用这条语句才会执行自定义执行器
        if (item.getNation().equals("汉族")) {// 简单的处理数据
            item.setNation("01");
        } else {
            item.setNation("02");
        }
        return item;
    }
}
