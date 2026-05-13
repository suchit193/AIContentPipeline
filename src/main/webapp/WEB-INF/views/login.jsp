<!DOCTYPE html>
<html>

<head>

 <title>Login</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

</head>
<body class="bg-dark text-white">
<div class="container mt-5">

 <div class="col-md-4 mx-auto card p-4 bg-secondary">

<h2 class="text-center mb-4">Login</h2>

<p style="color:red;">${error}</p>

<form action="login" method="post"> 
<input type="email"
 name="email"
 placeholder="Enter Email"
 class="form-control mb-3">
 <input type="password"
name="password"
placeholder="Enter Password"
 class="form-control mb-3">
<button class="btn btn-success w-100">
 Login </button>
 <p class="text-center mt-3">

    Don't have account?

    <a href="signup"
       class="text-warning">

        Signup

    </a>

</p>
</form>
</div>

</div>

</body>

</html>