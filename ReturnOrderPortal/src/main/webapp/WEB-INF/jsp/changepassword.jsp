<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Return Order Login Page</title>
        <link rel="stylesheet" href="/portal/css/bootstrap.min.css">
        <link href="https://fonts.googleapis.com/css?family=Oswald:400" rel="stylesheet">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
            integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    
            <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@200&display=swap" rel="stylesheet">
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" 
            integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <script src="/portal/js/bootstrap.min.js"></script>
    </head>
   <body>
   <div class="banner">
<nav class="navbar navbar-expand-lg navbar-light">
    <!-- <a class="navbar-brand" href="#"><img src="logo8.ico"></a> -->
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
        aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav" style="font-size: 24px !important;">
        <ul class="navbar-nav">

            <li class="nav-item">
                <a class="nav-link" href="/home">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/order">About</a>
          
            
            
        </ul>
        <ul class="navbar-nav ml-auto">
        <li class="nav-item">
                    <a class="nav-link" href="/trackPage">Track Order</a>
                </li>
            <li class="nav-item">
                <a class="nav-link" href="/cancelPage">Cancel Request</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/login">Logout</a>
            </li>

        </ul>
    </div>
</nav>

   
 
    <div >
            
            <div class="row">
                <spring:url value="/changepassword" var="ChangePassword"></spring:url>
                <div class="col-md-6 ">
                    <h2 class="text-info">Change the Password</h2><br>
                    <spring:url value="/changepassword" var="changepassword"></spring:url>
                    <form:form method="Post" modelAttribute="model" action="${changepassword}" >
                    <spring:bind path="username">
                        <div class="form-group ">
                            <label for="username" >Username</label>
                            <form:input path="username" type="text" 
                                class="form-control ${status.error ? 'is-invalid' : ''}" id="username" placeholder=" Enter your username" />
                            <form:errors path="username" class="invalid-feedback" />
                        </div>
                    </spring:bind>
                    <spring:bind path="oldPassword">
                        <div class="form-group ">
                            <label for="oldPassword">Password</label>
                            <form:input path="oldPassword" type="password" 
                                class="form-control ${status.error ? 'is-invalid' : ''}" id="oldPassword" placeholder="Enter your oldPassword" />
                            <form:errors path="oldPassword" class="invalid-feedback" />
                        </div>
                    </spring:bind>
                    <spring:bind path="newPassword">
                        <div class="form-group ">
                            <label for=" newPassword">Password</label>
                            <form:input path="newPassword" type="password" 
                                class="form-control ${status.error ? 'is-invalid' : ''}" id="newPassword" placeholder="Enter your newPassword" />
                            <form:errors path="newPassword" class="invalid-feedback" />
                        </div>
                    </spring:bind>
                        <button type="submit" class="btn btn-primary">Change the Password</button>
                    </form:form>
                    <span style="color:red;font-weight:bold">${error }</span>
                </div>
            </div>
        </div>
    </body>

   
    </html>