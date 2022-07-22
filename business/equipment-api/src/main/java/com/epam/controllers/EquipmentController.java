package com.epam.controllers;

import com.epam.entity.Equipment;
import com.epam.services.EquipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/equipment")
public class EquipmentController {
    private final EquipmentService equipmentService;

    @GetMapping()
    public List<Equipment> getAll() {
        return equipmentService.findAll();
    }

    @GetMapping("/{id}")
    public Equipment getAll(@PathVariable String id) {
        return equipmentService.findById(id);
    }


}
