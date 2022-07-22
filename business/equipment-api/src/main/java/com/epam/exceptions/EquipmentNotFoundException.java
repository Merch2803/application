package com.epam.exceptions;

public class EquipmentNotFoundException extends RuntimeException {
    public EquipmentNotFoundException(String s) {
        super(s);
    }

    public EquipmentNotFoundException() {
        super();
    }
}
