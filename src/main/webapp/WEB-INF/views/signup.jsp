<!DOCTYPE html>
<html>

<head>

<title>Signup</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

</head>

<body class="bg-dark text-white">

<div class="container mt-5">
<div class="col-md-4 mx-auto card p-4 bg-secondary">

 <h2 class="text-center mb-4">Signup</h2>
 
<p style="color:lightgreen;">${success}</p>

<p style="color:red;">${error} </p>

<form action="register" method="post">
<input type="text" name="name" placeholder="Enter Name" class="form-control mb-3">
<br>
<input type="email" name="email" placeholder="Enter Email" class="form-control mb-3">
<br>
<input type="password" name="password" placeholder="Enter Password" class="form-control mb-3">
<br>
<button class="btn btn-primary w-100">Register</button>

<p class="text-center mt-3">

    Already have account?

    <a href="login"
       class="text-warning">

        Login

    </a>

</p>
 
 </form>

</div>

</div>

</body>

</html>