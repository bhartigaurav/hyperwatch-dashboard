<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.BufferedReader, java.io.InputStreamReader, java.net.HttpURLConnection, java.net.URL, org.json.JSONObject" %>
    <%@ page import="com.aemcentral.hyperwatch.dashboard.dbinteraction.*"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.Connection" %>
<%@ page import=" java.sql.DriverManager" %>
<%@ page import=" java.sql.ResultSet" %>
<%@ page import=" java.sql.Statement" %>
<%@ page import=" java.io.BufferedReader" %>
  <%
  String name = (String) session.getAttribute("username");
  String ldap = (String) session.getAttribute("useremail");
  String webapp = request.getParameter("webapp");
  try {
		if (name.equals(null)) {
			
			response.sendRedirect("../index.jsp");
		}
	} catch (Exception e) {
		
		response.sendRedirect("../index.jsp");
	}
   // name="test";
  %>
<!DOCTYPE html>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title><%=webapp %></title>
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
<body>

    <div class="container-scroller">
      <!-- partial:partials/_sidebar.html -->
      <nav class="sidebar sidebar-offcanvas" id="sidebar">
        <div class="sidebar-brand-wrapper d-none d-lg-flex align-items-center justify-content-center fixed-top">
          
          <a class="sidebar-brand brand-logo" href="/homepage.jsp"><H1 style="color: antiquewhite;"> HyperWatch </H1></a>
          <a class="sidebar-brand brand-logo-mini" href="/homepage.jsp"><H1 style="color: antiquewhite;"> HyperWatch </H1></a>
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
            <a class="nav-link" href="homepage.jsp">
              <span class="menu-icon">
                <i class="mdi mdi-speedometer"></i>
              </span>
              <span class="menu-title">Dashboard</span>
            </a>
          </li>
           <li class="nav-item menu-items">
            <a class="nav-link" href="initialise.jsp" >
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
            
            <div class="alert alert-success d-flex align-items-center mt-3 mx-4" role="alert">
  <div class="mx-2">
  Webapp: <%=webapp %>
  </div>
</div>

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
                  <a class="dropdown-item preview-item">
                    <div class="preview-thumbnail">
                      <div class="preview-icon bg-dark rounded-circle">
                        <i class="mdi mdi-settings text-success"></i>
                      </div>
                    </div>
                    <div class="preview-item-content">
                      <p class="preview-subject mb-1">Profile Settings</p>
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
        <div class="main-panel">
          <div class="content-wrapper">

                
                <%



String currentinfourl = EnvDetails.getcurrentstats(webapp);
String infourl = EnvDetails.getmachinfo(webapp);

//out.println(currentinfourl);
//out.println(infourl);

ArrayList<String> val = EnvDetails.printmachinfo(infourl);
String VMMCode = val.get(0);
%>

    <div class="row">
              <div class="col-md-5 grid-margin stretch-card">
                <div class="card">
                  <div class="card-body">
                    <h4 class="card-title">Remote Machine Information</h4>
                    
                    <div class="bg-gray-dark d-flex d-md-block d-xl-flex flex-row py-3 px-4 px-md-3 px-xl-4 rounded mt-3">
                      <div class="text-md-center text-xl-left">
                        <h6 class="mb-1">VMM Code:</h6>
                        <p class="text-muted mb-0"></p>
                      </div>
                      <div class="align-self-center flex-grow text-right text-md-center text-xl-right py-md-2 py-xl-0">
                        <h6 id="availableinstance" class="font-weight-bold mb-0"><%=val.get(0)%></h6>
                      </div>
                    </div>
                    <div class="bg-gray-dark d-flex d-md-block d-xl-flex flex-row py-3 px-4 px-md-3 px-xl-4 rounded mt-3">
                      <div class="text-md-center text-xl-left">
                        <h6 class="mb-1">VM Level:</h6>
                      </div>
                      <div class="align-self-center flex-grow text-right text-md-center text-xl-right py-md-2 py-xl-0">
                        <h6 id="activeinstance" class="font-weight-bold mb-0"><%=val.get(1)%></h6>
                      </div>
                    </div>
                    <div class="bg-gray-dark d-flex d-md-block d-xl-flex flex-row py-3 px-4 px-md-3 px-xl-4 rounded mt-3">
                      <div class="text-md-center text-xl-left">
                        <h6 class="mb-1">Machine Environment:</h6>
                        <p class="text-muted mb-0"></p>
                      </div>
                      <div class="align-self-center flex-grow text-right text-md-center text-xl-right py-md-2 py-xl-0">
                        <h6 id="availableinstance" class="font-weight-bold mb-0"><%=val.get(2)%></h6>
                      </div>
                      </div>
                      <div class="bg-gray-dark d-flex d-md-block d-xl-flex flex-row py-3 px-4 px-md-3 px-xl-4 rounded mt-3">
                      <div class="text-md-center text-xl-left">
                        <h6 class="mb-1">OS Name:</h6>
                        <p class="text-muted mb-0"></p>
                      </div>
                      <div class="align-self-center flex-grow text-right text-md-center text-xl-right py-md-2 py-xl-0">
                        <h6 id="availableinstance" class="font-weight-bold mb-0"><%=val.get(3)%></h6>
                      </div></div>
                      <div class="bg-gray-dark d-flex d-md-block d-xl-flex flex-row py-3 px-4 px-md-3 px-xl-4 rounded mt-3">
                      <div class="text-md-center text-xl-left">
                        <h6 class="mb-1">CPU Cores:</h6>
                        <p class="text-muted mb-0"></p>
                      </div>
                      <div class="align-self-center flex-grow text-right text-md-center text-xl-right py-md-2 py-xl-0">
                        <h6 id="availableinstance" class="font-weight-bold mb-0"><%=val.get(4)%></h6>
                      </div></div>
                      <div class="bg-gray-dark d-flex d-md-block d-xl-flex flex-row py-3 px-4 px-md-3 px-xl-4 rounded mt-3">
                      <div class="text-md-center text-xl-left">
                        <h6 class="mb-1">Total Memory:</h6>
                        <p class="text-muted mb-0"></p>
                      </div>
                      <div class="align-self-center flex-grow text-right text-md-center text-xl-right py-md-2 py-xl-0">
                        <h6 id="availableinstance" class="font-weight-bold mb-0"><%=val.get(5)%>MB</h6>
                      </div></div>
                      <div class="bg-gray-dark d-flex d-md-block d-xl-flex flex-row py-3 px-4 px-md-3 px-xl-4 rounded mt-3">
                      <div class="text-md-center text-xl-left">
                        <h6 class="mb-1">Total DiskSpace:</h6>
                        <p class="text-muted mb-0"></p>
                      </div>
                      <div class="align-self-center flex-grow text-right text-md-center text-xl-right py-md-2 py-xl-0">
                        <h6 id="availableinstance" class="font-weight-bold mb-0"><%=val.get(6)%>GB</h6>
                      </div></div>

                      
                  </div>
                </div>
              </div>
              
              <%
ArrayList<String> currinfo = EnvDetails.printcurrentinfo(currentinfourl);
%>



              <div class="col-md-7 grid-margin stretch-card">
                                <div class="card">
                  <div class="card-body">
                    <h4 class="card-title">Current Status</h4>
                    <div class="bg-gray-dark d-flex d-md-block d-xl-flex flex-row py-3 px-4 px-md-3 px-xl-4 rounded mt-3">
                      <div class="text-md-center text-xl-left">
                        <h6 class="mb-1">CPU Load :</h6></div>
                      <div class="align-self-center flex-grow text-right text-md-center text-xl-right py-md-2 py-xl-0">
                        <h6 id="availableinstance" class="font-weight-bold mb-0"><%=currinfo.get(0)%>%</h6></div>
                        </div>
                         <div class="bg-gray-dark d-flex d-md-block d-xl-flex flex-row py-3 px-4 px-md-3 px-xl-4 rounded mt-3">
                      <div class="text-md-center text-xl-left">
                        <h6 class="mb-1">Total Memory:</h6></div>
                      <div class="align-self-center flex-grow text-right text-md-center text-xl-right py-md-2 py-xl-0">
                        <h6 id="availableinstance" class="font-weight-bold mb-0"><%=currinfo.get(1)%>MB</h6></div>
                        </div> 
                        <div class="bg-gray-dark d-flex d-md-block d-xl-flex flex-row py-3 px-4 px-md-3 px-xl-4 rounded mt-3">
                      <div class="text-md-center text-xl-left">
                        <h6 class="mb-1">Memory in-use:</h6></div>
                      <div class="align-self-center flex-grow text-right text-md-center text-xl-right py-md-2 py-xl-0">
                        <h6 id="availableinstance" class="font-weight-bold mb-0"><%=currinfo.get(2)%>MB</h6></div>
                        </div> 
                        <div class="bg-gray-dark d-flex d-md-block d-xl-flex flex-row py-3 px-4 px-md-3 px-xl-4 rounded mt-3">
                      <div class="text-md-center text-xl-left">
                        <h6 class="mb-1">Memory Usage:</h6></div>
                      <div class="align-self-center flex-grow text-right text-md-center text-xl-right py-md-2 py-xl-0">
                        <h6 id="availableinstance" class="font-weight-bold mb-0"><%=currinfo.get(3)%>%</h6></div>
                        </div> 
                        <div class="bg-gray-dark d-flex d-md-block d-xl-flex flex-row py-3 px-4 px-md-3 px-xl-4 rounded mt-3">
                      <div class="text-md-center text-xl-left">
                        <h6 class="mb-1">Total DiskSpace:</h6></div>
                      <div class="align-self-center flex-grow text-right text-md-center text-xl-right py-md-2 py-xl-0">
                        <h6 id="availableinstance" class="font-weight-bold mb-0"><%=currinfo.get(4)%>GB</h6></div>
                        </div> 
                        <div class="bg-gray-dark d-flex d-md-block d-xl-flex flex-row py-3 px-4 px-md-3 px-xl-4 rounded mt-3">
                      <div class="text-md-center text-xl-left">
                        <h6 class="mb-1">DiskSpace in-use:</h6></div>
                      <div class="align-self-center flex-grow text-right text-md-center text-xl-right py-md-2 py-xl-0">
                        <h6 id="availableinstance" class="font-weight-bold mb-0"><%=currinfo.get(5)%>GB</h6></div>
                        </div>
                         <div class="bg-gray-dark d-flex d-md-block d-xl-flex flex-row py-3 px-4 px-md-3 px-xl-4 rounded mt-3">
                      <div class="text-md-center text-xl-left">
                        <h6 class="mb-1">Disk Usage:</h6></div>
                      <div class="align-self-center flex-grow text-right text-md-center text-xl-right py-md-2 py-xl-0">
                        <h6 id="availableinstance" class="font-weight-bold mb-0"><%=currinfo.get(6)%>%</h6></div>
                        </div> 
                        <div class="bg-gray-dark d-flex d-md-block d-xl-flex flex-row py-3 px-4 px-md-3 px-xl-4 rounded mt-3">
                      <div class="text-md-center text-xl-left">
                        <h6 class="mb-1">Status: </h6></div>
                      <div class="align-self-center flex-grow text-right text-md-center text-xl-right py-md-2 py-xl-0">
                        <h6 id="availableinstance" class="font-weight-bold mb-0"><%=currinfo.get(7)%></h6></div>
                        </div>
                  </div>
                </div>
              </div>
            </div>
<div class="row">
			<div class="col-lg-7 grid-margin stretch-card">
                <div class="card">
                  <div class="card-body">
                    <h4 class="card-title">CPU Utilization</h4>
                    <canvas id="Cpuchart" style="height:250px"></canvas>
                  </div>
                </div>
              </div>
              <div class="col-md-5 grid-margin stretch-card">
                                <div class="card">
                  <div class="card-body">
                  <h4 class="card-title">Historical Data</h4>
              <div class="bg-gray-dark d-flex d-md-block d-xl-flex flex-row py-3 px-4 px-md-3 px-xl-4 rounded mt-3">
                      <div class="text-md-center text-xl-left">
                        <h6 class="mb-1">Percentage Uptime:</h6>
                        <p class="text-muted mb-0"></p>
                      </div>
                      <div class="align-self-center flex-grow text-right text-md-center text-xl-right py-md-2 py-xl-0">
                        <h6 id="availableinstance" class="font-weight-bold mb-0"><%=Intelligence.percentageuptime(VMMCode)%>%</h6>
                      </div></div>
                        <div class="bg-gray-dark d-flex d-md-block d-xl-flex flex-row py-3 px-4 px-md-3 px-xl-4 rounded mt-3">
                      <div class="text-md-center text-xl-left">
                        <h6 class="mb-1">Alert Count:</h6>
                        <p class="text-muted mb-0"></p>
                      </div>
                      <div class="align-self-center flex-grow text-right text-md-center text-xl-right py-md-2 py-xl-0">
                        <h6 id="availableinstance" class="font-weight-bold mb-0"><%=Intelligence.countalerts(VMMCode)%></h6>
                      </div></div>
                      </div></div>
              
              
              </div>
</div>
           <div class="row">
			<div class="col-lg-6 grid-margin stretch-card">
                <div class="card">
                  <div class="card-body">
                    <h4 class="card-title">Memory Utilization</h4>
                    <canvas id="Memchart" style="height:250px"></canvas>
                  </div>
                </div>
              </div>
             <div class="col-lg-6 grid-margin stretch-card">
                <div class="card">
                  <div class="card-body">
                    <h4 class="card-title">Disk Utilization</h4>
                    <canvas id="Diskchart" style="height:250px"></canvas>
                  </div>
                </div>
              </div>
              
              </div>
            
          <!-- partial:partials/_footer.html -->
          <footer class="footer">
            <div class="d-sm-flex justify-content-center justify-content-sm-between">
              <span class="text-muted d-block text-center text-sm-left d-sm-inline-block">Copyright © hyperwatch 2024 </span>
              <span class="float-none float-sm-right d-block mt-1 mt-sm-0 text-center"> <a href="#" target="_blank">Keep Track on machine Vitals </a> </span>
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
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
      <script>
    // Chart data and configuration
    const ctx = document.getElementById('Cpuchart').getContext('2d');
    const Cpuchart = new Chart(ctx, {
      type: 'line',  // Type of chart
      data: {
        labels: [<%=Fetchlast.gettimeinfo(val.get(0))%>], // X-axis labels
        datasets: [{
          label: 'Cpu',
          data: [<%=Fetchlast.getcpu(val.get(0))%>],  // Y-axis data
          borderColor: 'rgba(75, 192, 192, 1)',
          backgroundColor: 'rgba(75, 192, 192, 0.2)',
          borderWidth: 2
        }]
      },
      options: {
        responsive: true,
        scales: {
          y: {
            beginAtZero: true  // Starts Y-axis from zero
          }
        }
      }
    });
    const ctx1 = document.getElementById('Memchart').getContext('2d');
    const Memchart = new Chart(ctx1, {
      type: 'line',  // Type of chart
      data: {
        labels: [<%=Fetchlast.gettimeinfo(val.get(0))%>], // X-axis labels
        datasets: [{
          label: 'Memory',
          data: [<%=Fetchlast.getmem(val.get(0))%>],  // Y-axis data
          borderColor: 'rgba(75, 192, 192, 1)',
          backgroundColor: 'rgba(75, 192, 192, 0.2)',
          borderWidth: 2
        }]
      },
      options: {
        responsive: true,
        scales: {
          y: {
            beginAtZero: true  // Starts Y-axis from zero
          }
        }
      }
    });
    const ctx2 = document.getElementById('Diskchart').getContext('2d');
    const Diskchart = new Chart(ctx2, {
      type: 'line',  // Type of chart
      data: {
        labels: [<%=Fetchlast.gettimeinfo(val.get(0))%>], // X-axis labels
        datasets: [{
          label: 'Disk',
          data: [<%=Fetchlast.getdisk(val.get(0))%>],  // Y-axis data
          borderColor: 'rgba(75, 192, 192, 1)',
          backgroundColor: 'rgba(75, 192, 192, 0.2)',
          borderWidth: 2
        }]
      },
      options: {
        responsive: true,
        scales: {
          y: {
            beginAtZero: true  // Starts Y-axis from zero
          }
        }
      }
    });
  </script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"></script>
   
    
    <script src="../assets/vendors/js/vendor.bundle.base.js"></script>
    <!-- endinject -->
    <!-- Plugin js for this page -->
    
    <script src="../assets/vendors/progressbar.js/progressbar.min.js"></script>
    <script src="../assets/vendors/jvectormap/jquery-jvectormap.min.js"></script>
    <script src="../assets/vendors/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
    <script src="../assets/vendors/owl-carousel-2/owl.carousel.min.js"></script>
    <!-- End plugin js for this page -->
    <!-- inject:js -->
    <script src="../assets/js/off-canvas.js"></script>
    <script src="../assets/js/hoverable-collapse.js"></script>
    <script src="../assets/js/misc.js"></script>
    <script src="../assets/js/todolist.js"></script>
     
    <!-- endinject -->
    <!-- Custom js for this page -->
    <!-- End custom js for this page -->
  </body>
</html>