package com.epam.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Equipment {

    @Id
    private String id;
    private String name;
    private EquipmentType equipmentType;
    private String cpu;
    private long ram;
    private long rom;
    private OSFamily osFamily;
}
