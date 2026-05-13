package com.contentflow.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.contentflow.model.ContentItem;
import com.contentflow.util.DBUtil;

public class ContentDao {

    // SAVE GENERATED CONTENT

    public boolean saveContent(
            ContentItem item) {

        boolean status = false;

        try {

            Connection conn =
                    DBUtil.getConnection();

            String sql =
            "INSERT INTO content_items(workspace_id, platform, brand_name, brand_details, target_audience, tone, content_goal, generated_content) VALUES(?,?,?,?,?,?,?,?)";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setInt(1,
                    item.getWorkspaceId());

            ps.setString(2,
                    item.getPlatform());

            ps.setString(3,
                    item.getBrandName());

            ps.setString(4,
                    item.getBrandDetails());

            ps.setString(5,
                    item.getTargetAudience());

            ps.setString(6,
                    item.getTone());

            ps.setString(7,
                    item.getContentGoal());

            ps.setString(8,
                    item.getGeneratedContent());

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
}