<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error Page</title>
    <link rel="stylesheet" href="/portal/css/bootstrap.min.css">
    <link href="https://fonts.googleapis.com/css?family=Oswald:400" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
        integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@200&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" 
        integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
</head>
<body>
<div class="container">
<div class="row" id="main">
	<div class="col-sm">
		<h1 id="heading">Return  Order Management !</h1>
	</div>
</div>
<div class="row">
	
	<div class="col-md-6">
		<div class="tab-content" id="myTabContent">
					<table id="order" style="margin-top:50px;">
						<tr>
							<td colspan="5"><a style="margin-bottom:25px;" class="btn btn-success btn-block"
								href="/order" >Confirm Your Order Return</a></td>
						</tr>
<tr>
							<td colspan="5"><a style="margin-bottom:25px;" class="btn btn-success btn-block"
								href="/showchangepasswordpage" >Change the Password</a></td>
						</tr>
						<tr>
							<td colspan="5"><a style="margin-bottom:25px;" class="btn btn-success btn-block"
								href="/trackPage" >Track Request</a></td>
						</tr>
						<tr>
							<td colspan="5"><a style="margin-bottom:25px;" class="btn btn-success btn-block"
								href="/cancelPage" >Cancel a Request</a></td>
						</tr>
						<tr>
							<td colspan="5"><a class="btn btn-success btn-block"
								href="/login" >Logout</a></td>
						</tr>
		</div>
	</div>
</div>
</div>
    

    
</body>
</html>