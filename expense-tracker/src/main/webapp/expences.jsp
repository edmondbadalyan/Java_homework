<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Expense Tracker</title>
</head>
<body>
    <h1>Expense List</h1>
    <ul>
        <c:forEach items="${expenses}" var="expense">
            <li>${expense}</li>
        </c:forEach>
    </ul>

    <h2>Add New Expense</h2>
    <form action="expenses" method="post">
        <label for="amount">Amount:</label>
        <input type="text" id="amount" name="amount" required>
        <br>
        <label for="purpose">Purpose:</label>
        <input type="text" id="purpose" name="purpose" required>
        <br>
        <button type="submit">Save</button>
    </form>
</body>
</html>
