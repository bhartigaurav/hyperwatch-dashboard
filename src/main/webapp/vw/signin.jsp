<%
try{
String name = (String) session.getAttribute("username");
if(name == null){
	
}
else{
	response.sendRedirect("homepage.jsp");
}
}
catch(Exception e){
	
}

String url = "";
String path= "";
String fname= "";
if((String) request.getParameter("url")!=null)
 url = "?url=" +(String) request.getParameter("url");
if((String) request.getParameter("path")!=null)
	path = "&path=" +(String) request.getParameter("path");
if((String) request.getParameter("fname")!=null)
	fname = "&fname=" +(String) request.getParameter("fname");
%>

<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
     <!-- endinject -->
  <link rel="shortcut icon" href="../images/baba.png" />
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.84.0">
    <title>Signin - Instances Portal</title>


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
      
      .form-control:valid {
  
  color:white!important;
}
    </style>


    <!-- Custom styles for this template -->
    <link href="../css/signin.css" rel="stylesheet">
  </head>
  <body class="text-center">
    
    <svg xmlns="http://www.w3.org/2000/svg" style="display: none;">
  <symbol id="check-circle-fill" fill="currentColor" viewBox="0 0 16 16">
    <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zm-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z"/>
  </symbol>
  <symbol id="info-fill" fill="currentColor" viewBox="0 0 16 16">
    <path d="M8 16A8 8 0 1 0 8 0a8 8 0 0 0 0 16zm.93-9.412-1 4.705c-.07.34.029.533.304.533.194 0 .487-.07.686-.246l-.088.416c-.287.346-.92.598-1.465.598-.703 0-1.002-.422-.808-1.319l.738-3.468c.064-.293.006-.399-.287-.47l-.451-.081.082-.381 2.29-.287zM8 5.5a1 1 0 1 1 0-2 1 1 0 0 1 0 2z"/>
  </symbol>
  <symbol id="exclamation-triangle-fill" fill="currentColor" viewBox="0 0 16 16">
    <path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
  </symbol>
</svg>



<main class="form-signin">



  <form action="../servlets/usermanagement/Login<%=url %><%=path %><%=fname %>" method="POST">
   <!-- Add Logo here -->
   
   <img class="mb-4" src="../images/logo-crop.png" alt="" width="202" height="202">

	
    <div class="form-floating" style="margin-top:10px;">
      <input type="text" class="form-control  bg-dark" id="floatingUsername" name="username" placeholder="name@example.com" required>
      <label for="floatingInput" style="color:white">Username</label>
    </div>
    <div class="form-floating" style="margin-top:10px;">
      <input type="password" class="form-control bg-dark" id="floatingPassword" name="password" placeholder="Password" required>
      <label for="floatingPassword" style="color:white">Password</label>
      
    </div>

    <div class="checkbox mb-3">
      <label>
        
      </label>
    </div>
    <button class="w-100 btn btn-lg btn-success" type="submit">Sign in</button>
    
    <div class="s text-center p-5"><a href="request-access.jsp" style="text-decoration: none;color:#6990F2;"></a></div>
    
    <%
    try{
    if (request.getParameter("msg").equals("success"))
    {
    	%>
    	
    			<div class="alert alert-success d-flex align-items-center" role="alert">
  <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Success:"><use xlink:href="#check-circle-fill"/></svg>
  <div>
    Logout Successful!
  </div>
  </div>	
    	<%
    
    }
    
    else if(request.getParameter("msg")!=null){
	%>
	

<div class="alert alert-danger d-flex align-items-center" role="alert">
  <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Danger:"><use xlink:href="#exclamation-triangle-fill"/></svg>
  <div>
    <%=request.getParameter("msg") %>
  </div>
</div>

	<%
	}
    }
    catch(Exception e ){
    	
    }
   %>

    <p class="mt-5 mb-2 text-muted"> < A Tool by AEM Central > </p>
  </form>
</main>
    
  </body>
</html>