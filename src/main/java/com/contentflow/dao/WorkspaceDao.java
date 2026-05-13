package com.contentflow.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.contentflow.model.Workspace;
import com.contentflow.util.DBUtil;

public class WorkspaceDao {

    // CREATE WORKSPACE

    public boolean createWorkspace(
            Workspace workspace) {

        boolean status = false;

        try {

            Connection conn =
                    DBUtil.getConnection();

            String sql =
                    "INSERT INTO workspaces(name, description, user_id) VALUES(?,?,?)";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setString(1,
                    workspace.getName());

            ps.setString(2,
                    workspace.getDescription());

            ps.setInt(3,
                    workspace.getUserId());

            int rows =
                    ps.executeUpdate();

            if(rows > 0) {

                status = true;
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return status;
    }

    // GET ALL WORKSPACES

    public List<Workspace> getAllWorkspaces(
            int userId) {

        List<Workspace> list =
                new ArrayList<>();

        try {

            Connection conn =
                    DBUtil.getConnection();

            String sql =
                    "SELECT * FROM workspaces WHERE user_id=?";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setInt(1, userId);

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()) {

                Workspace workspace =
                        new Workspace();

                workspace.setId(
                        rs.getInt("id"));

                workspace.setName(
                        rs.getString("name"));

                workspace.setDescription(
                        rs.getString("description"));

                workspace.setUserId(
                        rs.getInt("user_id"));

                list.add(workspace);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }
}