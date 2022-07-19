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
public class Workspace {

    @Id
    private String id;
    private int unit;
    private int seat;
    private String serialNumber;
    private OSFamily osFamily;
}
