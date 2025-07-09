<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
    <h1>Login</h1>

        <%
        List<String> errors = (List<String>) session.getAttribute("errors");
            if (errors != null) {
            for (String error : errors) {
        %>
            <div class="error"><%= error %></div>
        <% }} %>

    <form method="post" action="/WebCrudJDBC/login">
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