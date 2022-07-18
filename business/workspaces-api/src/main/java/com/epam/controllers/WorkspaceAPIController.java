package com.epam.controllers;

import com.epam.api.WorkspaceAPI;
import com.epam.entity.Workspace;
import com.epam.services.WorkspaceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@Repository
public class WorkspaceAPIController implements WorkspaceAPI {

    private final WorkspaceService workspaceService;

    @Override
    public List<Workspace> getWorkspaces() {
        log.info("Instance {} received workspace request", this);
        return workspaceService.getWorkspaces();
    }

    @Override
    public Workspace getWorkspaceById(@PathVariable String id) {
        log.info("Instance {} received workspace request", this);
        return workspaceService.getWorkspaceById(id);
    }
}
