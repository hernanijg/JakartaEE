<%@ page session="true" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
    <h1>Login</h1>

<%@ page import="java.util.Map" %>

<%
    Object obj = session.getAttribute("errors");
    if (obj != null && obj instanceof Map) {
        Map<String, String> errors = (Map<String, String>) obj;
        for (String key : errors.keySet()) {
%>
    <div style="color:red;"><%= errors.get(key) %></div>
<%
        }
        session.removeAttribute("errors");
    }
%>

    <form method="post"  action="<%= request.getContextPath() %>/login">
        <div>
            <label for="email">Email</label>
            <div><input type="email" name="email" id="email"></div>

        </div>
        <div>
            <label for="password">Password</label>
            <div><input type="password" name="password" id="password"></div>
        </div>

        <div>
            <input type="submit" value="Send">
        </div>
    </form>
</body>
</html>