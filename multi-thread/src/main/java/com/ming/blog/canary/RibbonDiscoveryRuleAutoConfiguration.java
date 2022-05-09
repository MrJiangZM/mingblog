//package com.ming.blog.canary;
//
//import com.netflix.loadbalancer.IRule;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.cloud.netflix.ribbon.RibbonClients;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//
//@RibbonClients(defaultConfiguration = DefaultRibbonConfig.class)
//public class RibbonDiscoveryRuleAutoConfiguration {
//
//}
//
//@Configuration
//@ConditionalOnProperty(value = "ribbon.filter.metadata.enabled", matchIfMissing = true)
//class DefaultRibbonConfig {
//
//    @Bean
//    public IRule metadataAwareRule() {
//        return new MetadataCanaryRule();
//    }
//}