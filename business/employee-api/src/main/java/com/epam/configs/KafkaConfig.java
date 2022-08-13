package com.epam.configs;

import com.epam.configs.properties.KafkaProperties;
import com.epam.configs.properties.KafkaProperties.Topic;
import com.epam.entity.dto.EquipmentRequest;
import com.epam.entity.dto.EquipmentResponse;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.web.context.support.GenericWebApplicationContext;

import javax.annotation.PostConstruct;
import java.util.Collection;

@Configuration
@EnableKafka
@RequiredArgsConstructor
public class KafkaConfig {

    private final KafkaProperties kafkaProperties;

    private final GenericWebApplicationContext webApplicationContext;

    @Bean
    public RecordMessageConverter converter() {
        return new StringJsonMessageConverter();
    }

    @Bean
    public ReplyingKafkaTemplate<String, EquipmentRequest, EquipmentResponse> getEquipmentTemplate(
            ProducerFactory<String, EquipmentRequest> producerFactory,
            ConcurrentKafkaListenerContainerFactory<String, EquipmentResponse> containerFactory
    ) {
        Topic topic = kafkaProperties.getTopic(KafkaProperties.EQUIPMENTS_TOPIC);

        ReplyingKafkaTemplate<String, EquipmentRequest, EquipmentResponse> template =
                new ReplyingKafkaTemplate<>(
                        producerFactory, replyContainer(containerFactory, topic.getReplyName())
                );
        template.setMessageConverter(converter());

        return template;
    }

    private <K, V> ConcurrentMessageListenerContainer<K, V> replyContainer(
            ConcurrentKafkaListenerContainerFactory<K, V> containerFactory,
            String name
    ) {
        ConcurrentMessageListenerContainer<K, V> replyContainer = containerFactory.createContainer(name);
        replyContainer.setAutoStartup(true);
        return replyContainer;
    }

    @PostConstruct
    public void createTopics() {
        Collection<Topic> topics = kafkaProperties.getTopics().values();
        createTopics(topics);
        createReplyTopics(topics);
        createDLTTopics(topics);
    }

    private void createTopics(Collection<Topic> topics) {
        topics.forEach(topic -> registerNewTopic(topic, topic.getName()));
    }

    private void createReplyTopics(Collection<Topic> topics) {
        topics.forEach(topic -> registerNewTopic(topic, topic.getReplyName()));
    }

    private void createDLTTopics(Collection<Topic> topics) {
        topics.forEach(topic -> registerNewTopic(topic, topic.getDltName()));
    }

    private void registerNewTopic(Topic topic, String name) {
        webApplicationContext.registerBean(name, NewTopic.class, () -> topic.toNewTopic(name));
    }
}
