<!DOCTYPE html>
<html lang="en">
<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

  <form action="LoginServlet" method="post" class = "m-3">
    <div class="mb-3">
          <label for="email" class="form-label">Email :</label>
          <input type="email" class="form-control" id="email" name="email" required>
    </div>

    <div class="mb-3">
         <label for="password" class="form-label">Mot de passe :</label>
         <input type="password" class="form-control" id="mdps" name="mdps" pattern="^\d{8}$" required>
    </div>

    <button type="submit" class="btn btn-success">Se connecter</button>

    </div>
  </form>
</head>
<body>

</body>
</html>