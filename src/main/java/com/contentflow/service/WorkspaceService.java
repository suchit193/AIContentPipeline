package com.contentflow.service;

import java.util.List;

import com.contentflow.dao.WorkspaceDao;
import com.contentflow.model.Workspace;

public class WorkspaceService {

    WorkspaceDao workspaceDao =
            new WorkspaceDao();

    // CREATE WORKSPACE

    public boolean createWorkspace(
            Workspace workspace) {

        return workspaceDao
                .createWorkspace(workspace);
    }

    // GET ALL WORKSPACES

    public List<Workspace> getAllWorkspaces(
            int userId) {

        return workspaceDao
                .getAllWorkspaces(userId);
    }
}