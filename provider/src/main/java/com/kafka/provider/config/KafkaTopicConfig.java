package com.kafka.provider.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic generateTopic(){
        Map<String,String> configurations= new HashMap<>();
        configurations.put(TopicConfig.CLEANUP_POLICY_CONFIG,TopicConfig.CLEANUP_POLICY_DELETE); //Borra el mensaje luego del tiempo de retencion
        configurations.put(TopicConfig.RETENTION_MS_CONFIG,"86400000"); //Tiempo de retencion de mensajes --Defecto -1
        configurations.put(TopicConfig.SEGMENT_BYTES_CONFIG,"1073741824");// Tamano maximo del segmento --Defecto 1GB
        configurations.put(TopicConfig.MAX_MESSAGE_BYTES_CONFIG,"2048"); //Cantidad de bytes dentro de los mensajes --Defecto 1MB
        return TopicBuilder.name("ms-provider")
                .partitions(2)
                .replicas(1)
                .configs(configurations)
                .build();
    }
}
