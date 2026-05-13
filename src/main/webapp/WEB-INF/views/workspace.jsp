<%@ page language="java"
contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>

<title>Workspace</title>

<link href=
"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
rel="stylesheet">

</head>

<body class="bg-dark text-white">

<div class="container mt-5">

<h2>Create Workspace</h2>

<form action="workspace/create"
method="post">

<input type="text"
name="name"
placeholder="Workspace Name"
class="form-control mb-3">

<textarea name="description"
placeholder="Workspace Description"
class="form-control mb-3"></textarea>

<button class="btn btn-primary">

Create Workspace

</button>

</form>

<hr>

<h3>Your Workspaces</h3>

<c:forEach var="workspace"
items="${workspaceList}">

<div class="card bg-secondary p-3 mt-3">

    <h4>${workspace.name}</h4>

    <p>${workspace.description}</p>

    <a href="content/${workspace.id}"
       class="btn btn-warning">

        Open Workspace

    </a>

</div>

</c:forEach>

</div>

</body>

</html>