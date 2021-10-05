<!doctype html>
<html lang="en">
<head>
<div class="container-fluid">
<ul class="navbar-nav ml-auto ms-0 me-0">
	<center><h2 id="header">Return Order Management</h2></center>
</ul>
</div>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Return Order Portal</title>
<link rel="stylesheet" href="style.css">


<link href="https://fonts.googleapis.com/css?family=Oswald:400" rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
    integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@200&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" 
    integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
</head>

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
                <a class="nav-link" href="/cancelPage">Cancel Request</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/login">Logout</a>
            </li>

        </ul>
    </div>
</nav>


<body>
</hr>
</hr>

	
	<div class="container"  >
	<div class="col-md-4"></div>
		<div class="row" id="main" style="border:green; border-width:8px;">
			<div class="col-sm">
				<h1 id="heading">Track Request</h1>
			</div>

		</div>
		<div class="row">
			
			<div class="col-md-4" id="login-form" >
				<form action="track" method="GET" class="needs-validation"
					novalidate>
					<div class="form-group">
						<label for="id" class="sr-only">Request Id</label> <input
							type="text" name="id" id="id" class="form-control" pattern="[0-9]+"
							placeholder="Enter Request ID"required>
						<div class="invalid-feedback">Please enter Request ID.</div>
					</div>
						<button type="submit" style="margin-bottom:25px;" class="btn btn-success btn-block"	>Track a Request</button>
						<p class="text-danger" id="error">${invalidlogin}
						<p>
				</form>
			</div>
		</div>
	</div>


	
</body>

</html>