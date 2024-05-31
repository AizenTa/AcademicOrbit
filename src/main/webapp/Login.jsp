<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body style="background-color: #f8f9fa; font-family: 'Poppins', sans-serif; display: flex; justify-content: center; align-items: center; height: 85vh; margin: 3.5%;">

    <div class="container" style="">

            <div class="row login-container shadow p-4 bg-light rounded" style="display: flex;">

                <div class="col-md-6 d-none d-md-block" style="display: flex; justify-content: center; align-items: center;">
                    <img src="img/Logo.png" alt="Login Image" class="img-fluid rounded login-image" style="width: 250px; height: 320px; margin-top: 100px;">
                </div>

                <div class="col-md-6 login-form" style="padding: 30px;">

                    <h2 class="text-center mb-4" style="font-size: 2.5rem; color: #007bff; font-weight: 600;">Academic Orbit Login</h2>

                    <form action="LoginController" method="post">

                        <div class="form-group" style="margin-bottom: 25px;">
                            <label for="userType" style="font-size: 1.2rem; font-weight: bold; color: #333333;">Mode</label>
                            <select class="form-control" id="userType" name="userType" required style="transition: border-color 0.3s;">
                                <option value="admin">Admin</option>
                                <option value="professor">Professor</option>
                                <option value="student">Student</option>
                            </select>
                        </div>

                        <div class="form-group" style="margin-bottom: 25px;">
                            <label for="username" style="font-size: 1.2rem; font-weight: bold; color: #333333;">Username</label>
                            <input type="text" class="form-control" id="username" name="username" required style="transition: border-color 0.3s;">
                        </div>

                        <div class="form-group" style="margin-bottom: 25px;">
                            <label for="password" style="font-size: 1.2rem; font-weight: bold; color: #333333;">Password</label>
                            <input type="password" class="form-control" id="password" name="password" required style="transition: border-color 0.3s;">
                        </div>

                        <button type="submit" class="btn btn-primary btn-block btn-theme" style="background-color: #007bff; border: none; border-radius: 8px; padding: 15px; color: #ffffff; font-weight: bold; cursor: pointer; transition: background-color 0.3s, box-shadow 0.3s;">Login</button>

                    </form>

                </div>
            </div>
        </div>

       <style>
           /* Global Styles */
           body {
               background: linear-gradient(to right, #f0f0f0, #ffffff);
               font-family: 'Poppins', sans-serif;
               display: flex;
               justify-content: center;
               align-items: center;
               height: 100vh;
               margin: 0;
           }

           .container {
               max-width: 800px;
               width: 100%;
               padding: 30px;
           }

           .login-container {
               display: flex;
           }

           .login-image {
               width: 250px;
               height: 320px;
               margin-top: 100px;
           }

           .login-form {
               padding: 30px;
               background-color: #f9f9f9;
               border-radius: 15px;
               box-shadow: 0px 8px 15px rgba(0, 0, 0, 0.1);
           }

           /* Form Styles */
           .login-form h2 {
               font-size: 2.5rem;
               color: #007bff;
               font-weight: 600;
               margin-bottom: 20px;
               text-align: center;
               text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.1);
           }

           .form-group {
               margin-bottom: 25px;
           }

           label {
               font-size: 1.2rem;
               font-weight: bold;
               color: #333333;
           }

           input[type="text"],
           input[type="password"],
           select {
               width: 100%;
               padding: 15px;
               border: 1px solid #cccccc;
               border-radius: 8px;
               transition: border-color 0.3s;
               background-color: #f5f5f5;
           }

           input[type="text"]:focus,
           input[type="password"]:focus,
           select:focus {
               outline: none;
               border-color: #007bff;
               box-shadow: 0px 0px 5px 0px #007bff;
           }

           /* Button Styles */
           .btn-primary {
               background-color: #007bff;
               border: none;
               border-radius: 8px;
               padding: 15px;
               color: #ffffff;
               font-weight: bold;
               cursor: pointer;
               transition: background-color 0.3s, box-shadow 0.3s;
               width: 100%;
           }

           .btn-primary:hover {
               background-color: #0056b3;
               box-shadow: 0px 3px 5px rgba(0, 0, 0, 0.1);
           }

           .btn-primary:active {
               transform: translateY(2px);
           }
       </style>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>

</html>