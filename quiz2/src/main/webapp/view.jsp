<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>My Notes</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <style>
        body { background-color: #f2f2f7; font-family: -apple-system, BlinkMacSystemFont, sans-serif; }
        .note-card { border: none; border-radius: 10px; transition: 0.2s; background: #fff; height: 100%; }
        .note-card:hover { transform: translateY(-2px); box-shadow: 0 4px 10px rgba(0,0,0,0.05); }
        .pinned { border-left: 5px solid #ffc107; background: #fffdf0; }
        .btn-fab { position: fixed; bottom: 30px; right: 30px; width: 60px; height: 60px; border-radius: 50%; display: flex; align-items: center; justify-content: center; font-size: 24px; box-shadow: 0 4px 10px rgba(0,0,0,0.2); }
    </style>
</head>
<body>
<div class="container py-4">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2 class="fw-bold">Notes</h2>
        <a href="deleteAll" class="text-danger text-decoration-none small" onclick="return confirm('Delete All Notes?')">Clear All</a>
    </div>

    <form action="search" method="get" class="mb-4">
        <div class="input-group shadow-sm rounded-pill overflow-hidden">
            <span class="input-group-text bg-white border-0 ps-3"><i class="fas fa-search text-muted"></i></span>
            <input type="text" name="keyword" class="form-control border-0 shadow-none" placeholder="Search">
        </div>
    </form>

    <div class="row g-3">
        <c:forEach var="note" items="${listNotes}">
            <div class="col-6 col-md-4 col-lg-3">
                <div class="card note-card p-3 ${note.pinned ? 'pinned' : ''}">
                    <div class="d-flex justify-content-between mb-2">
                        <a href="pin?id=${note.id}" class="text-warning"><i class="${note.pinned ? 'fas' : 'far'} fa-star"></i></a>
                        <a href="delete?id=${note.id}" class="text-danger" onclick="return confirm('Delete?')"><i class="far fa-trash-alt"></i></a>
                    </div>
                    <a href="edit?id=${note.id}" class="text-decoration-none text-dark">
                        <h6 class="fw-bold text-truncate mb-1">${note.title}</h6>
                        <p class="small text-muted mb-0 text-truncate">${note.content}</p>
                        <small class="text-muted" style="font-size: 10px;">${note.createdAt}</small>
                    </a>
                </div>
            </div>
        </c:forEach>
    </div>

    <a href="new" class="btn btn-warning text-white btn-fab"><i class="fas fa-plus"></i></a>
</div>
</body>
</html>