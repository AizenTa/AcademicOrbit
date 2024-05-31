<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <div id="errorMessage" class="alert alert-danger" role="alert" style="padding: 20px; border: 2px solid #dc3545; border-radius: 10px; color: #721c24; background-color: #f8d7da; box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);">
            <div style="display: flex; align-items: center;">
                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-exclamation-triangle-fill" viewBox="0 0 16 16" style="margin-right: 10px;">
                    <path d="M.172 13.172a.5.5 0 0 0 .172.672l7 4.5a.5.5 0 0 0 .656 0l7-4.5a.5.5 0 0 0 .172-.672l-7-12.5a.5.5 0 0 0-.344-.172l-7 0a.5.5 0 0 0-.172.672l7 12.5zm7-2a1 1 0 1 1 2 0v1a1 1 0 1 1-2 0v-1zm1-8a1 1 0 0 1 1 1v5a1 1 0 0 1-2 0V4a1 1 0 0 1 1-1z"/>
                </svg>
                <span><strong>Error:</strong> Invalid username or password. Please try again.</span>
            </div>
        </div>
    <jsp:include page="Login.jsp" />
</div>
<script>
    setTimeout(function() {
        var errorMessage = document.getElementById("errorMessage");
        errorMessage.style.display = "none";
    }, 5000); // 5000 milliseconds = 5 seconds
</script>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>