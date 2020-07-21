<!DOCTYPE html>
<html lang="en">
<head>
<title>Test JSP page</title>
</head>
<body>
<%! int count = 0; %>
<% count++; %>
<%= Math.random() %>
<br/>
<%= count %>
</body>
</html>