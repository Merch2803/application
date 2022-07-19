package com.epam.services;

import com.epam.entity.Employee;
import com.epam.entity.EmployeeDto;
import com.epam.entity.Workspace;
import com.epam.exceptions.EmployeeNotFoundException;
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

//    private List<Employee> employees = new ArrayList<>(List.of(
//            new Employee("0000001", "Ivan", "Ivanov", "Ivan_Ivanov@corpmail.com", "0000001"),
//            new Employee("0000002", "Ivan", "Ivanov", "Ivan_Ivanov@corpmail.com", "0000002"),
//            new Employee("0000003", "Ivan", "Ivanov", "Ivan_Ivanov@corpmail.com", "0000003"),
//            new Employee("0000004", "Ivan", "Ivanov", "Ivan_Ivanov@corpmail.com", "0000004"),
//            new Employee("0000005", "Ivan", "Ivanov", "Ivan_Ivanov@corpmail.com", "0000005"),
//            new Employee("0000006", "Ivan", "Ivanov", "Ivan_Ivanov@corpmail.com", "0000006"),
//            new Employee("0000007", "Ivan", "Ivanov", "Ivan_Ivanov@corpmail.com", "0000007"),
//            new Employee("0000008", "Ivan", "Ivanov", "Ivan_Ivanov@corpmail.com", "0000008"),
//            new Employee("0000009", "Ivan", "Ivanov", "Ivan_Ivanov@corpmail.com", "0000009"),
//            new Employee("0000010", "Ivan", "Ivanov", "Ivan_Ivanov@corpmail.com", "0000010"),
//            new Employee("0000011", "Ivan", "Ivanov", "Ivan_Ivanov@corpmail.com", "0000011"),
//            new Employee("0000012", "Ivan", "Ivanov", "Ivan_Ivanov@corpmail.com", "0000012"),
//            new Employee("0000013", "Ivan", "Ivanov", "Ivan_Ivanov@corpmail.com", "0000013"),
//            new Employee("0000014", "Ivan", "Ivanov", "Ivan_Ivanov@corpmail.com", "0000014"),
//            new Employee("0000015", "Ivan", "Ivanov", "Ivan_Ivanov@corpmail.com", "0000015")
//    ));

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
}
