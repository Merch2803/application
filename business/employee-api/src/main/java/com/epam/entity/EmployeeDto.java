package com.epam.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDto {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private Workspace workspace;
    private Equipment equipment;
}
