package com.epam.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Workspace {

    private String id;
    private int unit;
    private int seat;
    private String serialNumber;
    private OSFamily osFamily;
}
