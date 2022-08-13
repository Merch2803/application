package com.epam.kafka;

import com.epam.entity.dto.EquipmentRequest;
import com.epam.entity.dto.EquipmentResponse;
import com.epam.services.EquipmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetEquipmentListener {

    private final EquipmentService equipmentService;

    @KafkaListener(topics = {"${kafka.topics.equipment-topics.name}"})
    @SendTo
    public EquipmentResponse getEquipmentListener(EquipmentRequest equipmentRequest) {
        log.info("Trying to find an equipment with id " + equipmentRequest.getEquipmentId());
        return new EquipmentResponse(equipmentService.findById(equipmentRequest.getEquipmentId()));
    }
}
