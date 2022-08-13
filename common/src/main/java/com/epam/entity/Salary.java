package com.epam.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Salary {

    @Id
    private String id;

    private String employeeId;

    private long monthly;
    private long paid;
    private int separator;
}
