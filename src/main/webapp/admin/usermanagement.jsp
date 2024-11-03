<%@page import="java.util.ArrayList"%>

<%@page import="org.apache.http.*"%>

<%@page import="org.apache.http.HttpEntity"%>
<%@page import="org.apache.http.HttpResponse"%>
<%@page import="org.apache.http.client.HttpClient"%>
<%@page import="org.apache.http.client.methods.HttpPost"%>
<%@page import="org.apache.http.entity.ContentType"%>
<%@page import="org.apache.http.entity.StringEntity"%>
<%@page import="org.apache.http.impl.client.HttpClientBuilder"%>
<%@page import="org.apache.http.util.EntityUtils"%>
<%@page import="org.json.JSONObject"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Hyperwatch Dashboard</title>
    <!-- plugins:css -->
    <link rel="stylesheet" href="../assets/vendors/mdi/css/materialdesignicons.min.css">
    <link rel="stylesheet" href="../assets/vendors/css/vendor.bundle.base.css">
    <!-- endinject -->
    <!-- Plugin css for this page -->
    <link rel="stylesheet" href="../assets/vendors/jvectormap/jquery-jvectormap.css">
    <link rel="stylesheet" href="../assets/vendors/flag-icon-css/css/flag-icon.min.css">
    <link rel="stylesheet" href="../assets/vendors/owl-carousel-2/owl.carousel.min.css">
    <link rel="stylesheet" href="../assets/vendors/owl-carousel-2/owl.theme.default.min.css">
    <!-- End plugin css for this page -->
    <!-- inject:css -->
    <!-- endinject -->
    <!-- Layout styles -->
    <link rel="stylesheet" href="../assets/css/style.css">
    <!-- End layout styles -->
    <link rel="shortcut icon" href="../assets/images/favicon.png" />
  </head> 
  
  <%
  String name = (String) session.getAttribute("username");
  String ldap = (String) session.getAttribute("useremail");
  try {
		if (name.equals(null)) {
			
			response.sendRedirect("../index.jsp");
		}
	} catch (Exception e) {
		
		response.sendRedirect("../index.jsp");
	}
  // name="test";
  %>
<body>
 
    <div class="container-scroller">
      <!-- partial:partials/_sidebar.html -->
      <nav class="sidebar sidebar-offcanvas" id="sidebar">
        <div class="sidebar-brand-wrapper d-none d-lg-flex align-items-center justify-content-center fixed-top">
          
          <a class="sidebar-brand brand-logo" href="../vw/homepage.jsp"><H1 style="color: antiquewhite;"> HyperWatch </H1></a>
          <a class="sidebar-brand brand-logo-mini" href="../vw/homepage.jsp"><H1 style="color: antiquewhite;"> HyperWatch </H1></a>
        </div>
        <ul class="nav">
          <li class="nav-item profile">
            <div class="profile-desc">
              <div class="profile-pic">
                <div class="count-indicator">
                  <img class="img-fluid rounded-circle " src="../images/logo-crop.png" alt="">
                  <span class="count bg-success"></span>
                </div>
              </div>
          <li class="nav-item nav-category">
            <span class="nav-link">Navigation Panel</span>
          </li>
          <li class="nav-item menu-items">
            <a class="nav-link" href="../vw/homepage.jsp">
              <span class="menu-icon">
                <i class="mdi mdi-speedometer"></i>
              </span>
              <span class="menu-title">Dashboard</span>
            </a>
          </li>
           <li class="nav-item menu-items">
            <a class="nav-link" href="../vw/initialise.jsp" >
              <span class="menu-icon">
                <i class="mdi mdi-laptop"></i>
              </span>
              <span class="menu-title">Manage VM/POD</span>
            </a>            
          </li>
          <li class="nav-item menu-items">
            <a class="nav-link" href="../admin/usermanagement.jsp">
              <span class="menu-icon">
                <i class="mdi mdi-playlist-play"></i>
              </span>
              <span class="menu-title">User Management</span>
            </a>
          </li>
        </ul>
      </nav>
      <!-- partial -->
      <div class="container-fluid page-body-wrapper">
        <!-- partial:partials/_navbar.html -->
        <nav class="navbar p-0 fixed-top d-flex flex-row">
          <div class="navbar-brand-wrapper d-flex d-lg-none align-items-center justify-content-center">
            <a class="navbar-brand brand-logo-mini" href="index.html"><H3 style="color: antiquewhite;"> HyperWatch </H3></a>
          </div>
          <div class="navbar-menu-wrapper flex-grow d-flex align-items-stretch">
            <button class="navbar-toggler navbar-toggler align-self-center" type="button" data-toggle="minimize">
              <span class="mdi mdi-menu"></span>
            </button>
            <ul class="navbar-nav navbar-nav-right">
            
            
            
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
<%
 			
 			try{
 				
 				String msg= request.getParameter("msg");
 				
 				if(msg.equals("User Deleted Successfully!")){
 					%>
 					<div class="alert alert-success d-flex align-items-center mt-3 mx-4" role="alert">
  <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Success:"><use xlink:href="#check-circle-fill"/></svg>
  <div class="mx-2">
User Deleted Successfully!
  </div>
</div>
 					<% 
 				}
 				
 				
 				
 				else if(msg.contains("User Created Successfully!")){
 					%>
 					<div class="alert alert-success d-flex align-items-center mt-3 mx-4" role="alert">
  <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Success:"><use xlink:href="#check-circle-fill"/></svg>
  <div class="mx-2">
User Created Successfully!
  </div>
</div>
 					<% 
 				}
 				
 				
 				else if(msg.equals("Invalid User Credential!")){
 					%>
 						<div class="alert alert-danger d-flex align-items-center mt-3 mx-" role="alert">
  <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Danger:"><use xlink:href="#exclamation-triangle-fill"/></svg>
  <div class="mx-2">
  <%=msg %> !
  </div>
</div>
 					<%
 				}
 				
 				
 				
 				}
catch (Exception e){
	}
 					%>
            

              <li class="nav-item dropdown border-left">
                <a class="nav-link count-indicator dropdown-toggle" id="notificationDropdown" href="#" data-toggle="dropdown">
                  <i class="mdi mdi-bell"></i>
                  <span class="count bg-danger"></span>
                </a>
                <div class="dropdown-menu dropdown-menu-right navbar-dropdown preview-list" aria-labelledby="notificationDropdown">
                  <h6 class="p-3 mb-0">Alerts</h6>
                  <div class="dropdown-divider"></div>
                  <a class="dropdown-item preview-item">
                    <div class="preview-thumbnail">
                      <div class="preview-icon bg-dark rounded-circle">
                        <i class="mdi mdi-calendar text-success"></i>
                      </div>
                    </div>
                    <div class="preview-item-content">
                      <p class="preview-subject mb-1">Event today</p>
                      <p class="text-muted ellipsis mb-0"> Just a reminder that you have an event today </p>
                    </div>
                  </a>
                  <div class="dropdown-divider"></div>
                  <a class="dropdown-item preview-item">
                    <div class="preview-thumbnail">
                      <div class="preview-icon bg-dark rounded-circle">
                        <i class="mdi mdi-settings text-danger"></i>
                      </div>
                    </div>
                    <div class="preview-item-content">
                      <p class="preview-subject mb-1">Settings</p>
                      <p class="text-muted ellipsis mb-0"> Update dashboard </p>
                    </div>
                  </a>
                  <div class="dropdown-divider"></div>
                  <a class="dropdown-item preview-item">
                    <div class="preview-thumbnail">
                      <div class="preview-icon bg-dark rounded-circle">
                        <i class="mdi mdi-link-variant text-warning"></i>
                      </div>
                    </div>
                    <div class="preview-item-content">
                      <p class="preview-subject mb-1">Launch Admin</p>
                      <p class="text-muted ellipsis mb-0"> New admin wow! </p>
                    </div>
                  </a>
                  <div class="dropdown-divider"></div>
                  <p class="p-3 mb-0 text-center">See all notifications</p>
                </div>
              </li>
              <li class="nav-item dropdown">
                <a class="nav-link" id="profileDropdown" href="#" data-toggle="dropdown">
                  <div class="navbar-profile">
                    <img class="img-xs rounded-circle" src="../assets/images/faces/face15.jpg" alt="">
                    <p class="mb-0 d-none d-sm-block navbar-profile-name"><%=name %></p>
                    <i class="mdi mdi-menu-down d-none d-sm-block"></i>
                  </div>
                </a>
                <div class="dropdown-menu dropdown-menu-right navbar-dropdown preview-list" aria-labelledby="profileDropdown">
                  <h6 class="p-3 mb-0">Profile</h6>
                  <div class="dropdown-divider"></div>
                    <a class="dropdown-item preview-item" href="../admin/dashboard-settings.jsp">
                    <div class="preview-thumbnail">
                      <div class="preview-icon bg-dark rounded-circle">
                        <i class="mdi mdi-settings text-success"></i>
                      </div>
                    </div>
                    <div class="preview-item-content">
                      <p class="preview-subject mb-1">Dashboard Settings</p>
                    </div>
                  </a>
                  <div class="dropdown-divider"></div>
                  <a class="dropdown-item preview-item" href="../servlets/usermanagement/Logout">
                    <div class="preview-thumbnail">
                      <div class="preview-icon bg-dark rounded-circle">
                        <i class="mdi mdi-logout text-danger"></i>
                      </div>
                    </div>
                    <div class="preview-item-content">
                      <p class="preview-subject mb-1">Log out</p>
                    </div>
                  </a>
              </li>
            </ul>
            <button class="navbar-toggler navbar-toggler-right d-lg-none align-self-center" type="button" data-toggle="offcanvas">
              <span class="mdi mdi-format-line-spacing"></span>
            </button>
          </div>
        </nav>
      <!-- partial -->
      
        <!-- partial -->
        <div class="main-panel">
          <div class="content-wrapper">


            <div class="row">
              <div class="col-md-5 grid-margin stretch-card">
                <div class="card">
                  <div class="card-body">
                    <h4 class="card-title">Add User</h4>
                    
                    <form class="py-2 mt-4" form action="../servlet/userops/adduser" method="POST">
					  <div class="mb-3">
					    <label for="ldap" class="form-label">Ldap</label>
					    <input type="text" class="form-control" name="username" id="username" aria-describedby="emailHelp">
					    <div id="emailHelp" class="form-text">Ldap will be the default username and password </div>
					  </div>
					  <div class="mb-3">
					    <label for="name" class="form-label">Name</label>
					    <input type="text" class="form-control" name="name" id="name">
					  </div>
					  <div class="mb-3">
					  <label for="select" class="form-label">Role</label>
					  <br>
					  <select class="form-select bg-dark" name="type" id="type" style="width:150px;height:25px;color:white;" aria-label="Default select example">
					  <option value="ADMIN">Admin</option>
					  <option value="USER">User</option>
					</select>
					</div>
					  <button type="submit" class="btn btn-primary" style="margin-top:10px;width:100px;height:35px;">Add</button>
					</form>
                   
                   
                  </div>
                </div>
              </div>
              <div class="col-md-7 grid-margin stretch-card">
                                <div class="card">
                  <div class="card-body">
                    <h4 class="card-title">Existing Users</h4>
                    
     
      <table class="table mt-4">
  <thead>
    <tr>
      <th scope="col">Name</th>
      <th scope="col">Role</th>
      <th scope="col">Is Enabled</th>
      <th scope="col">Action</th>
      
    </tr>
  </thead>
   <tbody>
  <%
  try {
		
	  String payload ="{\n"
				+ "    \"username\" : \"\", \n"
				+ "    \"securitycode\" : \"s9jg3na\",\n"
				+ "    \"appid\": \"apx3\"\n"
				+ "}";
		
		        StringEntity entity = new StringEntity(payload,
		                ContentType.APPLICATION_JSON);
		       
		        HttpClient httpClient = HttpClientBuilder.create().build();
		        HttpPost logreq = new HttpPost("http://auth.aemcentral.corp.adobe.com:8090/auth/list/allusers");
		        logreq.setEntity(entity);
		        HttpResponse res = httpClient.execute(logreq);
		       
		        HttpEntity entity1 = res.getEntity();
		        if (entity1 != null) {
		            String content = EntityUtils.toString(entity1);
		            String[] Str = content.split("},");
		            
		            for(String i: Str){
		            	i=i.replace("[","");
		            	i=i+"}";
		               
		        JSONObject json = new JSONObject(i);
		        %>
		        
		         <tr>
      <td><%=json.get("name") %></td>
      <td ><%=json.get("role") %></td>
      <td class="text-center"> <%=json.get("isenabled") %></td>
      <td>
      <form action="../servlet/userops/deleteuser" method="POST">
      <input type="hidden" id="user" name="user" value="<%=json.get("name") %>">
      <input type="submit" class="btn-danger" style="width:60px;height:25px;" value = "Delete">
      </form>
      </td>
    </tr>
   
		        <%
		      
  }
		       
		        } 
		         
	}
	catch(Exception e) {
		e.printStackTrace();
		//response.sendRedirect("../../admin/add-user.jsp?msg=Invalid User Credential!");
	}
  %>
  
  </tbody>
</table>
                    
                  </div>
                </div>
              </div>
            </div>

           <!-- partial:partials/_footer.html -->
          <footer class="footer">
            <div class="d-sm-flex justify-content-center justify-content-sm-between">
              <span class="text-muted d-block text-center text-sm-left d-sm-inline-block">Copyright © hyperwatch 2024 </span>
              <span class="float-none float-sm-right d-block mt-1 mt-sm-0 text-center"> <a href="#" target="_blank">&nbsp; Keep Track on machine Vitals </a> </span>
            </div>  
          </footer>
          <!-- partial -->
        </div>
        <!-- main-panel ends -->
      </div>
      <!-- page-body-wrapper ends -->
    </div>
    <!-- container-scroller -->
    <!-- plugins:js -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"></script>
    
    <script src="../assets/vendors/js/vendor.bundle.base.js"></script>
    <!-- endinject -->
    <!-- Plugin js for this page -->
    <script src="../assets/vendors/chart.js/Chart.min.js"></script>
    <script src="../assets/vendors/progressbar.js/progressbar.min.js"></script>
    <script src="../assets/vendors/jvectormap/jquery-jvectormap.min.js"></script>
    <script src="../assets/vendors/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
    <script src="../assets/vendors/owl-carousel-2/owl.carousel.min.js"></script>
    <!-- End plugin js for this page -->
    <!-- inject:js -->
    <script src="../assets/js/off-canvas.js"></script>
    <script src="../assets/js/hoverable-collapse.js"></script>
    <script src="../assets/js/misc.js"></script>
    <script src="../assets/js/settings.js"></script>
    <script src="../assets/js/todolist.js"></script>
    <script src="../assets/js/changeDetails.js"></script>
    <!-- endinject -->
    <!-- Custom js for this page -->
    <script src="../assets/js/dashboard.js"></script>
    <!-- End custom js for this page -->
  </body>
</html>