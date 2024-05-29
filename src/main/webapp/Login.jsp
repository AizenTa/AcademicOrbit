<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link href="Style/styles.css" rel="stylesheet"> <!-- Link to custom CSS -->
</head>
<body>
    <div class="container d-flex align-items-center justify-content-center min-vh-100">
        <div class="row login-container shadow p-4 bg-white rounded">
            <div class="col-md-6 d-none d-md-block">
                <img src="img/Logo.png" alt="Login Image" class="img-fluid rounded login-image">
            </div>
            <div class="col-md-6 login-form">
                <h2 class="text-center mb-4">Academic Orbit Login</h2>
                <form action="LoginController" method="post">
                    <div class="form-group">
                        <label for="userType">Login as</label>
                        <select class="form-control" id="userType" name="userType" required>
                            <option value="admin">Admin</option>
                            <option value="professor">Professor</option>
                            <option value="student">Student</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="username">Username</label>
                        <input type="text" class="form-control" id="username" name="username" required>
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <input type="password" class="form-control" id="password" name="password" required>
                    </div>
                    <button type="submit" class="btn btn-primary btn-block btn-theme">Login</button>
                </form>
                <div class="mt-3">
                    <c:if test="${not empty errorMessage}">
                        <div class="alert alert-danger" role="alert">
                            ${errorMessage}
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>

    <div class="account-block">
        <div class="overlay"></div>
        <div class="account-testimonial">
            <h4 class="text-theme">Welcome to Academic Orbit</h4>
            <p>"Your gateway to a world of knowledge"</p>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>