package com.epam.configs.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Component
@Data
@ConfigurationProperties("kafka")
public class KafkaProperties {

    public static final String EQUIPMENTS_TOPIC = "equipments-topic";

    private Map<String, Topic> topics;

    public Topic getTopic(String name) {
        return topics.get(name);
    }

    @Data
    @NoArgsConstructor
    public static class Topic {
        private static final String REPLY_POSTFIX = ".REPLY";
        private static final String DLT_SUFFIX = ".DLT";

        private String name;
        private int partitions;
        private int replicationFactor;
        private String replyName;

        public Topic(String name) {
            this.name = name;
        }

        public String getReplyName() {
            return Optional.ofNullable(replyName).orElse(name + REPLY_POSTFIX);
        }

        public String getDltName() {
            return name + DLT_SUFFIX;
        }

        public NewTopic toNewTopic(String name) {
            return TopicBuilder.name(name).replicas(replicationFactor).partitions(partitions).build();
        }
    }
}
