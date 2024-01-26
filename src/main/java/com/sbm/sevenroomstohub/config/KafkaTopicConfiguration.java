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

    @Value(value = "${spring.kafka.topics.client-topic}")
    private String clientsTopic;

    @Value(value = "${spring.kafka.topics.reservation-topic}")
    private String reservationsTopic;

    @Value(value = "${spring.kafka.topics.client-dead-letters-topic}")
    private String clientsDeadLettersTopic;

    @Value(value = "${spring.kafka.topics.reservation-dead-letters-topic}")
    private String reservationsDeadLettersTopic;

    @Value(value = "${spring.kafka.topics.logs-topic}")
    private String logsTopic;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic clientTopic() {
        return new NewTopic(clientsTopic, 3, (short) 1);
    }

    @Bean
    public NewTopic reservationTopic() {
        return new NewTopic(reservationsTopic, 3, (short) 1);
    }

    @Bean
    public NewTopic clientDeadLetterTopic() {
        return new NewTopic(clientsDeadLettersTopic, 3, (short) 1);
    }

    public NewTopic reservationDeadLetterTopic() {
        return new NewTopic(reservationsDeadLettersTopic, 3, (short) 1);
    }

    @Bean
    public NewTopic logsTopic() {
        return new NewTopic(logsTopic, 3, (short) 1);
    }
}
