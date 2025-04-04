package com.cpms.cpms.test;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnectionTest {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/cpms";
        String username = "root";
        String password = "Harman@1410";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Database connection successful!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}