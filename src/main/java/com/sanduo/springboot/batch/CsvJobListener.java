package com.sanduo.springboot.batch;

import java.text.SimpleDateFormat;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

/**
 * 批处理监视器
 * 
 * @author sanduo
 * @date 2018/08/03
 */
public class CsvJobListener implements JobExecutionListener {

    Long startTime;
    Long endTime;

    /* 任务前执行
     * @see org.springframework.batch.core.JobExecutionListener#beforeJob(org.springframework.batch.core.JobExecution)
     */
    @Override
    public void beforeJob(JobExecution jobExecution) {

        startTime = System.currentTimeMillis();
        System.out.println("任务处理开始" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startTime));
    }

    /* 任务后执行
     * @see org.springframework.batch.core.JobExecutionListener#afterJob(org.springframework.batch.core.JobExecution)
     */
    @Override
    public void afterJob(JobExecution jobExecution) {
        endTime = System.currentTimeMillis();
        System.out.println("任务结束" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(endTime));
        System.out.println("耗时：" + (endTime - startTime) + "ms");
    }

}
