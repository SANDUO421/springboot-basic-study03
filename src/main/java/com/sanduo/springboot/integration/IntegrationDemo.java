package com.sanduo.springboot.integration;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.feed.inbound.FeedEntryMessageSource;
import org.springframework.integration.scheduling.PollerMetadata;

import com.rometools.rome.feed.synd.SyndEntry;

/**
 * @author sanduo
 * @date 2018/08/07
 */
// @Component
// @Configuration
// FIXME 暂时没用。没有实现
public class IntegrationDemo {

    @Value("https://spring.io/blog.atom") // 获取资源
    Resource resource;

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {// 轮询元数据
        return Pollers.fixedRate(500).get();
    }

    @Bean
    public FeedEntryMessageSource feedEntryMessageSource() throws IOException {// 入站通道适配器，数据的输入
        FeedEntryMessageSource source = new FeedEntryMessageSource(resource.getURL(), "news");
        return source;
    }

    // 读取流程
    @Bean
    public IntegrationFlow flow() throws IOException {
        return IntegrationFlows.from(feedEntryMessageSource())// 流的开始
            .<SyndEntry, String>route(payload -> payload.getCategories().get(0).getName(), // route选择路由
                mapping -> mapping.channelMapping("releases", "releasesChannel")
                    .channelMapping("engineering", "engineeringChannel").channelMapping("news", "newsChannel"))// 分类
            .get();// 获取实体配置bean
    }

}
