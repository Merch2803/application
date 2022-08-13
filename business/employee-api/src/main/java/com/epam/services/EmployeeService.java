package com.epam.services;

import com.epam.entity.Employee;
import com.epam.entity.EmployeeDto;
import com.epam.entity.Workspace;
import com.epam.exceptions.EmployeeNotFoundException;
import com.epam.kafka.GetEquipmentProducer;
import com.epam.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final WorkspaceService workspaceService;
    private final EmployeeRepository employeeRepository;

    private final GetEquipmentProducer equipmentProducer;

    public EmployeeDto getEmployeeById(String id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Could not find employee with id: " + id));

        return toEmployeeDto(employee);
    }

    public List<EmployeeDto> getEmployees() {
        List<Workspace> workspaces = workspaceService.getWorkspaces();
        return employeeRepository.findAll()
                .stream()
                .map(employee ->
                        toEmployeeDtoWithWorkspaces(
                                employee, getWorkspaceFromList(workspaces, employee.getWorkspaceId())
                        )
                ).collect(Collectors.toList());
    }

    private Workspace getWorkspaceFromList(List<Workspace> workspaces, String workspaceId) {
        return workspaces.stream()
                .filter(workspace -> workspace.getId().equals(workspaceId))
                .findFirst()
                .orElse(new Workspace());
    }

    private EmployeeDto toEmployeeDto(Employee employee) {
        return EmployeeDto.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .workspace(workspaceService.getWorkspaceById(employee.getWorkspaceId()))
                .equipment(equipmentProducer.getEquipmentById(employee.getEquipmentId()))
                .build();
    }

    private EmployeeDto toEmployeeDtoWithWorkspaces(Employee employee, Workspace workspace) {
        return EmployeeDto.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .workspace(workspace)
                .build();
    }

    public void deleteEmployeeById(String id) {
        employeeRepository.deleteById(id);
    }
}
