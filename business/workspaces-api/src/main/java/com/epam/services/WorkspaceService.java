package com.epam.services;

import com.epam.entity.Workspace;
import com.epam.exceptions.NoWorkspaceFoundException;
import com.epam.repositories.WorkspaceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class WorkspaceService {

    private final WorkspaceRepository workspaceRepository;

//    private final List<Workspace> workspaces = List.of(
//            new Workspace("0000001", 1, 1, randomUUID().toString(), WINDOWS),
//            new Workspace("0000002", 1, 2, randomUUID().toString(), WINDOWS),
//            new Workspace("0000003", 1, 3, randomUUID().toString(), WINDOWS),
//            new Workspace("0000004", 1, 4, randomUUID().toString(), OSX),
//            new Workspace("0000005", 1, 5, randomUUID().toString(), OSX),
//            new Workspace("0000006", 1, 6, randomUUID().toString(), OSX),
//            new Workspace("0000007", 1, 7, randomUUID().toString(), WINDOWS),
//            new Workspace("0000008", 2, 1, randomUUID().toString(), WINDOWS),
//            new Workspace("0000009", 2, 2, randomUUID().toString(), WINDOWS),
//            new Workspace("0000010", 2, 3, randomUUID().toString(), OSX),
//            new Workspace("0000011", 2, 4, randomUUID().toString(), OSX),
//            new Workspace("0000012", 2, 5, randomUUID().toString(), WINDOWS),
//            new Workspace("0000013", 2, 6, randomUUID().toString(), WINDOWS),
//            new Workspace("0000014", 2, 7, randomUUID().toString(), LINUX),
//            new Workspace("0000015", 2, 9, randomUUID().toString(), LINUX)
//    );

    public List<Workspace> getWorkspaces() {
        log.info("Workspaces are being returned with find all");
        return workspaceRepository.findAll();
    }

    public Workspace getWorkspaceById(String id) {
        return workspaceRepository.findById(id)
                .map(workspace -> {
                    log.info("Instance {} send workspace response {}", this, workspace);
                    return workspace;
                })
                .orElseThrow(() ->
                        new NoWorkspaceFoundException(
                                String.format("Could not find Workspace with id = %s", id)));
    }
}
