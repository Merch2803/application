package com.epam.services;

import com.epam.api.WorkspaceAPI;
import com.epam.entity.Workspace;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class WorkspaceService {

    private final WorkspaceAPI workspaceAPI;

    public Workspace getWorkspaceById(String id) {
        return workspaceAPI.getWorkspaceById(id);
    }

    public List<Workspace> getWorkspaces() {
        return workspaceAPI.getWorkspaces();
    }
}
