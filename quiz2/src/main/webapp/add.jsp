<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>New Note</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background-color: #fffdf5; }
        input, textarea { background: transparent; border: none; outline: none; width: 100%; }
        textarea { height: 70vh; resize: none; }
    </style>
</head>
<body>
<div class="container py-3">
    <form action="insert" method="post">
        <div class="d-flex justify-content-between mb-3">
            <a href="./notes" class="text-warning text-decoration-none fw-bold">&lt; Notes</a>
            <button type="submit" class="btn btn-link text-warning text-decoration-none fw-bold">Done</button>
        </div>
        <input type="text" name="title" class="fs-3 fw-bold mb-3" placeholder="Title" required autocomplete="off">
        <textarea name="content" class="fs-5 text-muted" placeholder="Type something..."></textarea>
    </form>
</div>
</body>
</html>