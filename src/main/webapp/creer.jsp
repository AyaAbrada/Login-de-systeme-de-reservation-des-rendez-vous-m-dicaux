<!DOCTYPE html>
<html lang="en">
<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<head>
    <meta charset="UTF-8">
    <title>Registration</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>

    <form action="RegisterServlet" method="post" class="formulaire m-3" id="form-contact">
        <div class="mb-3">
            <label for="nom" class="form-label">Nom complet :</label>
            <input type="text" class="form-control" id="nom" name="nom" required>
        </div>

        <div class="mb-3">
            <label for="email" class="form-label">Email :</label>
            <input type="email" class="form-control" id="email" name="email" required>
        </div>

        <div class="mb-3">
             <label for="mdps" class="form-label">Mot de passe :</label>
             <input type="password" class="form-control" id="mdps" name="mdps" required>
        </div>

        <div class="mb-3">
            <label for="telephone" class="form-label">Numéro de téléphone :</label>
            <input type="tel" class="form-control" id="telephone" name="telephone" pattern="^\d{10}$" required>
        </div>

        <div class="mb-3">
             <label for="specialite" class="form-label">Spécialité (si médecin) :</label>
             <input type="text" class="form-control" id="specialite" name="specialite">
             <small>Remplissez ceci uniquement si vous êtes un médecin.</small>
        </div>
        <button type="submit" class="btn btn-success">Créer un compte</button>
    </form>

</body>
</html>
