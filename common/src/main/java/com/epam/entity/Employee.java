package com.epam.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee {

    @Id
    private String id;

    private String firstName;
    private String lastName;
    private String email;
    private String workspaceId;
    private String equipmentId;

}
