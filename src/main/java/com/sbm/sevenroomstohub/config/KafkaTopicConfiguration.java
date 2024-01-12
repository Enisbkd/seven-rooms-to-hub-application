package com.sbm.sevenroomstohub.config;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaTopicConfiguration {

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic clientTopic() {
        return new NewTopic("data-7rooms-client", 3, (short) 1);
    }

    @Bean
    public NewTopic reservationTopic() {
        return new NewTopic("data-7rooms-reservation", 3, (short) 1);
    }

    @Bean
    public NewTopic deadLetterTopic() {
        return new NewTopic("data-7rooms-deadletters", 3, (short) 1);
    }
}
