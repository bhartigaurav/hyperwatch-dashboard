<!--<%@page import="com.aemcentral.logsmithanalyser.checks.*" %> -->
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.84.0">
    <title>AEM Instance Portal</title>

    <!-- Bootstrap core CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
    </style>

    
    <!-- Custom styles for this template -->
    <link href="../css/signin.css" rel="stylesheet">
  </head>
  <body class="text-center">
    
<main class="form-signin">
  <form action="../servlet/userops/requestaccess" method="POST">
   <!-- Add Logo here -->
   
   <img class="mb-4" src="../images/logo-crop.png" alt="" width="202" height="202">
   
    <div class="form-floating" style="margin-top:10px;">
      <input type="text" class="form-control" id="floatingInput" name="username" placeholder="name@example.com" required>
      <label for="floatingInput">Ldap</label>
    </div>
    <div class="form-floating" style="margin-top:10px;">
      <input type="text" class="form-control" id="floatingName" name="name" placeholder="Password" required>
      <label for="floatingName">Name</label>
    </div>

    <div class="checkbox mb-3">
      <label>
        
      </label>
    </div>
    <button class="w-100 btn btn-lg btn-success" type="submit">Request Access</button>
    
    <div class="s text-center p-5"><a href="signin.jsp" style="text-decoration: none;color:#6990F2;">Back to Sign In</a></div>
    
    <%
    try{
    if (request.getParameter("msg").equals("success"))
    {
    	%>
    	
    	<br>
    	<div class="text-success text-center p-2">Request Sent Successfully!</div>
    				
    	<%
    
    }
    
    else if(request.getParameter("msg")!=null){
	%>
	
	<br>
	<div class="text-danger text-center p-2"><%=request.getParameter("msg") %></div>
				
	<%
	}
    }
    catch(Exception e ){
    	
    }
	%>
	
	
    <p class="mt-5 mb-3 text-muted"> < A Tool by AEM Central ></p>
  </form>
</main>


    
  </body>
</html>