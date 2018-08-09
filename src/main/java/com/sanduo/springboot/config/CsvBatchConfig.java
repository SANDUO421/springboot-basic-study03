
package com.sanduo.springboot.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.validator.Validator;
import org.springframework.batch.support.DatabaseType;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import com.sanduo.springboot.batch.CsvBeanValidator;
import com.sanduo.springboot.batch.CsvItemProcess;
import com.sanduo.springboot.batch.CsvJobListener;
import com.sanduo.springboot.entity.People;

/**
 * 批处理配置
 * 
 * @author sanduo
 * @date 2018/08/03
 */
/*@Configuration
@EnableBatchProcessing // 开启批处理支持
*/public class CsvBatchConfig {

    // 注册job容器---作业仓库
    @Bean
    public JobRepository jobRepository(DataSource dataSource, PlatformTransactionManager transactionManager)
        throws Exception {
        JobRepositoryFactoryBean bean = new JobRepositoryFactoryBean();
        bean.setDataSource(dataSource);
        bean.setTransactionManager(transactionManager);
        bean.setDatabaseType(DatabaseType.ORACLE.name());

        return bean.getObject();
    }

    // 启动job容器---作业调度器
    @Bean
    public SimpleJobLauncher jobLauncher(DataSource dataSource, PlatformTransactionManager transactionManager)
        throws Exception {
        SimpleJobLauncher launcher = new SimpleJobLauncher();
        launcher.setJobRepository(this.jobRepository(dataSource, transactionManager));
        return launcher;
    }

    // 加入任务
    @Bean
    public Job importJob(JobBuilderFactory jobs, Step step) {

        return jobs.get("importJob").incrementer(new RunIdIncrementer()).flow(step)// 指定步骤
            .end().listener(csvJobListener())// 绑定监听器
            .build();
    }

    // 创建任务
    @Bean
    public Step step1(StepBuilderFactory factory, ItemReader<People> reader, ItemWriter<People> writer,
        ItemProcessor<People, People> processor) {
        return factory.get("step1").<People, People>chunk(65000)// 每次处理65000条数据
            .reader(reader).processor(processor).writer(writer)// getstep绑定reader、processor、writer
            .build();
    }

    // 读取数据
    @Bean
    public ItemReader<People> reader() {
        FlatFileItemReader<People> reader = new FlatFileItemReader<People>();// 读取文件
        reader.setResource(new ClassPathResource("people.csv"));// csv的文件路径
        reader.setLineMapper(new DefaultLineMapper<People>() {// 对Csv和领域模型进行映射
            {
                setLineTokenizer(new DelimitedLineTokenizer() {
                    {
                        setNames(new String[] {"name", "age", "nation", "address"});
                    }
                });
                setFieldSetMapper(new BeanWrapperFieldSetMapper<People>() {
                    {
                        setTargetType(People.class);
                    }
                });
            }
        });
        return reader;
    }

    // 处理数据
    @Bean
    public ItemProcessor<People, People> processor() {
        CsvItemProcess itemProcess = new CsvItemProcess();
        itemProcess.setValidator(csvBeanValidator());// 指定校验器
        return itemProcess;
    }

    // 写出数据
    @Bean
    public ItemWriter<People> writer(DataSource dataSource) {// springboot 已经注入DataSource这里直接用
        JdbcBatchItemWriter<People> itemWriter = new JdbcBatchItemWriter<People>();// jdbc批处理写入到数据库
        itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<People>());
        String sql = "insert into people" + "(id,name,age,nation,address)"
            + " values(hibernate_sequence.nextval,:name,:age,:nation,:address)";
        itemWriter.setSql(sql);// 设置SQL语句
        itemWriter.setDataSource(dataSource);
        return itemWriter;
    }

    // 注入监听器
    @Bean
    public CsvJobListener csvJobListener() {
        return new CsvJobListener();
    }

    // 注入校验器
    @Bean
    public Validator<People> csvBeanValidator() {
        return new CsvBeanValidator<People>();
    }
}
