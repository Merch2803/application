package com.epam.kafka;

import com.epam.configs.properties.KafkaProperties;
import com.epam.entity.Equipment;
import com.epam.entity.dto.EquipmentRequest;
import com.epam.entity.dto.EquipmentResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyTypedMessageFuture;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetEquipmentProducer {
    private final ReplyingKafkaTemplate<String, EquipmentRequest, EquipmentResponse> getEquipmentTemplate;
    private final KafkaProperties kafkaProperties;

    public Equipment getEquipmentById(String id) {
        log.info("Sending request...");
        RequestReplyTypedMessageFuture<String, EquipmentRequest, EquipmentResponse> future =
                sendRequest(new EquipmentRequest(id));

        try {
            Message<EquipmentResponse> message = future.get();
            log.info("Response received");
            return message.getPayload().getEquipment();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private RequestReplyTypedMessageFuture<String, EquipmentRequest, EquipmentResponse> sendRequest(
            EquipmentRequest equipmentRequest
    ) {
        Message<EquipmentRequest> message = MessageBuilder
                .withPayload(equipmentRequest)
                .setHeader(KafkaHeaders.TOPIC, getTopic().getName())
                .setHeader(KafkaHeaders.REPLY_TOPIC, getTopic().getReplyName())
                .build();

        return getEquipmentTemplate.sendAndReceive(message,
                ParameterizedTypeReference.forType(EquipmentResponse.class));
    }

    private KafkaProperties.Topic getTopic() {
        return kafkaProperties.getTopic(KafkaProperties.EQUIPMENTS_TOPIC);
    }
}
