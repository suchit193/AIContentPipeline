package com.contentflow.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.contentflow.model.User;
import com.contentflow.util.DBUtil;

public class UserDao {

    public boolean registerUser(User user) {

        boolean status = false;

        try {

            Connection conn =DBUtil.getConnection();

            String sql = "INSERT INTO users(name,email,password) VALUES(?,?,?)";

            PreparedStatement ps =conn.prepareStatement(sql);

            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());

            int rows = ps.executeUpdate();

            if(rows > 0) {
                status = true;
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return status;
    }
    
    public boolean checkUserExists(String email) {

        boolean exists = false;

        try {

            Connection conn =DBUtil.getConnection();

            String sql = "SELECT * FROM users WHERE email=?";

            PreparedStatement ps =conn.prepareStatement(sql);

            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {

                exists = true;
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return exists;
    }
    
    public User loginUser(String email,
            String password) {

User user = null;

try {

Connection conn =
      DBUtil.getConnection();

String sql =
      "SELECT * FROM users WHERE email=? AND password=?";

PreparedStatement ps =
      conn.prepareStatement(sql);

ps.setString(1, email);

ps.setString(2, password);

ResultSet rs =
      ps.executeQuery();

if(rs.next()) {

  user = new User();

  user.setId(
          rs.getInt("id"));

  user.setName(
          rs.getString("name"));

  user.setEmail(
          rs.getString("email"));
}

} catch (Exception e) {

e.printStackTrace();
}

return user;
}

    
}