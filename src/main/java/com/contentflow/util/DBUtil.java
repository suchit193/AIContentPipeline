package com.contentflow.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {

    public static Connection getConnection() {

        Connection conn = null;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/AI_SOCIAL_MEDIA",
                    "root",
                    "12345"
            );

            System.out.println("Database Connected!");

        } catch (Exception e) {

            e.printStackTrace();
        }

        return conn;
    }
}