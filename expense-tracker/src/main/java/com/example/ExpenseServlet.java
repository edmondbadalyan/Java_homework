package com.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ExpenseServlet", urlPatterns = {"/expenses"})
public class ExpenseServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/Finance";
    private static final String DB_USER = "Edmond";
    private static final String DB_PASSWORD = "Edmond5432";

    public ExpenseServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String amount = request.getParameter("amount");
        String purpose = request.getParameter("purpose");

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "INSERT INTO expenses (amount, purpose) VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setBigDecimal(1, new BigDecimal(amount));
                statement.setString(2, purpose);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("expenses");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<String> expenses = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT amount, purpose FROM expenses";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    String expense = resultSet.getBigDecimal("amount") + " - " + resultSet.getString("purpose");
                    expenses.add(expense);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("expenses", expenses);
        request.getRequestDispatcher("/expences.jsp").forward(request, response);
    }
}

