package com.epam.api;

import com.epam.entity.Workspace;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "workspaces-api")
public interface WorkspaceAPI {

    @GetMapping("/workspaces")
    List<Workspace> getWorkspaces();

    @GetMapping("/workspaces/{id}")
    Workspace getWorkspaceById(@PathVariable String id);

}
