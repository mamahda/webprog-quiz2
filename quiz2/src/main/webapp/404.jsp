<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Page Not Found</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <style>
        body {
            background-color: #f2f2f7;
            height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, sans-serif;
        }
        .error-container {
            text-align: center;
            color: #8e8e93;
        }
        .icon-404 {
            font-size: 6rem;
            margin-bottom: 1rem;
            color: #d1d1d6;
        }
        .error-title {
            font-size: 2rem;
            font-weight: bold;
            color: #000;
            margin-bottom: 0.5rem;
        }
        .btn-back {
            background-color: #ffc107;
            color: #fff;
            border-radius: 50px;
            padding: 10px 25px;
            font-weight: bold;
            text-decoration: none;
            transition: 0.2s;
        }
        .btn-back:hover {
            background-color: #ffca2c;
            color: #fff;
            transform: scale(1.05);
        }
    </style>
</head>
<body>

<div class="container error-container">
    <div class="icon-404">
        <i class="far fa-file-excel"></i>
    </div>
    <h1 class="error-title">404</h1>
    <p class="mb-4">Oops! Catatan halaman ini hilang entah kemana.</p>
    
    <a href="<%=request.getContextPath()%>/notes" class="btn-back shadow-sm">
        <i class="fas fa-arrow-left me-2"></i> Back to Notes
    </a>
</div>

</body>
</html>