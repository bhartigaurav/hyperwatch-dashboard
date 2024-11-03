<%@ page import="java.io.BufferedReader, java.io.InputStreamReader, java.net.HttpURLConnection, java.net.URL, org.json.JSONObject" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.Connection" %>
<%@ page import=" java.sql.DriverManager" %>
<%@ page import=" java.sql.ResultSet" %>
<%@ page import=" java.sql.Statement" %>
<%@ page import="com.aemcentral.hyperwatch.dashboard.dbinteraction.*" %>
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
  <%
    String endpoint = "http://hyperwatch.corp.adobe.com/dashboard/servlets/dataops/counts";
    StringBuilder responseContent = new StringBuilder();
    JSONObject jsonResponse = null;

    try {
        URL url = new URL(endpoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            responseContent.append(inputLine);
        }
        in.close();

        jsonResponse = new JSONObject(responseContent.toString());

    } catch (Exception e) {
        e.printStackTrace();
    }
    int total = jsonResponse.getInt("total");
    int dev = jsonResponse.getInt("dev");
    int stage = jsonResponse.getInt("stage");
    int prod = jsonResponse.getInt("prod");
    int active = jsonResponse.getInt("active");
%>
    <div class="container-scroller">
      <!-- partial:partials/_sidebar.html -->
      <nav class="sidebar sidebar-offcanvas" id="sidebar">
        <div class="sidebar-brand-wrapper d-none d-lg-flex align-items-center justify-content-center fixed-top">
          
           <a class="sidebar-brand brand-logo" href="homepage.jsp"><H1 style="color: antiquewhite;"> HyperWatch </H1></a>
          <a class="sidebar-brand brand-logo-mini" href="homepage.jsp"><H1 style="color: antiquewhite;"> HyperWatch </H1></a>
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
        <div class="main-panel">
          <div class="content-wrapper">

            <div class="row">
              <div class="col-xl-3 col-sm-6 grid-margin stretch-card">
                <div class="card">
                  <div class="card-body">
                    <div class="row">
                      <div class="col-9">
                        <div class="d-flex align-items-center align-self-start">
                          <h4 class="mb-0 text-center">All Instance Details</h4>
                        </div>
                      </div>
                      <div class="col-3">
                        <div class="icon icon-box-success ">
                          <span id ="all" class="mdi icon-item" onclick="changeInstance(this)"><%=total %></span>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div class="col-xl-3 col-sm-6 grid-margin stretch-card">
                <div class="card">
                  <div class="card-body">
                    <div class="row">
                      <div class="col-9">
                        <div class="d-flex align-items-center align-self-start">
                          <h4 class="mb-0 text-center">Prod Instance Details</h4>
                        </div>
                      </div>
                      <div class="col-3">
                        <div class="icon icon-box-success">
                          <span id ="prod" class="mdi  icon-item" onclick="changeInstance(this)"><%=prod %></span>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div class="col-xl-3 col-sm-6 grid-margin stretch-card">
                <div class="card">
                  <div class="card-body">
                    <div class="row">
                      <div class="col-9">
                        <div class="d-flex align-items-center align-self-start">
                          <h4 class="mb-0 text-center">Stage Instance Details</h4>
                        </div>
                      </div>
                      <div class="col-3">
                        <div class="icon icon-box-success">
                          <span id ="stage" class="mdi icon-item" onclick="changeInstance(this)" ><%=stage %></span>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div class="col-xl-3 col-sm-6 grid-margin stretch-card">
                <div class="card">
                  <div class="card-body">
                    <div class="row">
                      <div class="col-9">
                        <div class="d-flex align-items-center align-self-start">
                          <h4 class="mb-0 text-center">Dev Instance Details</h4>
                        </div>
                      </div>
                      <div class="col-3">
                        <div class="icon icon-box-success ">
                          <span id ="dev" class="mdi  icon-item" onclick="changeInstance(this)" ><%=dev %></span>
                        </div>
                      </div>
                    </div>
                   </div>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-md-4 grid-margin stretch-card">
                <div class="card">
                  <div class="card-body">
                    <h4 class="card-title">Instance Availability</h4>
                    <canvas id="pieChart" ></canvas>
                    
                    <div class="bg-gray-dark d-flex d-md-block d-xl-flex flex-row py-3 px-4 px-md-3 px-xl-4 rounded mt-3">
                      <div class="text-md-center text-xl-left">
                        <h6 class="mb-1">Total Available Instance</h6>
                        <p class="text-muted mb-0"></p>
                      </div>
                      <div class="align-self-center flex-grow text-right text-md-center text-xl-right py-md-2 py-xl-0">
                        <h6 id="availableinstance" class="font-weight-bold mb-0"><%=total %></h6>
                      </div>
                    </div>
                    <div class="bg-gray-dark d-flex d-md-block d-xl-flex flex-row py-3 px-4 px-md-3 px-xl-4 rounded mt-3">
                      <div class="text-md-center text-xl-left">
                        <h6 class="mb-1">Active Instances</h6>
                      </div>
                      <div class="align-self-center flex-grow text-right text-md-center text-xl-right py-md-2 py-xl-0">
                        <h6 id="activeinstance" class="font-weight-bold mb-0"><%=active %></h6>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div class="col-md-8 grid-margin stretch-card">
                                <div class="card">
                  <div class="card-body">
                    <h4 class="card-title">Recent Alerts</h4>
                    <div class="table-responsive">
                      <table class="table">
                        <thead>
                          <tr>
                            <th>Incident Id</th>
                            <th>Timestamp</th>
                            <th>webapp</th>
                            <th>alert</th>
                           <!--   <th>Impact</th>  -->

                          </tr>
                        </thead>
                        <tbody id = "alerttable">
                         <%

						String sql = "SELECT * FROM `alerts` ORDER BY c1 DESC LIMIT 5;";
					
						try {
							Class.forName("com.mysql.cj.jdbc.Driver");
							Connection con = DriverManager.getConnection(Credentials.getDburl(), Credentials.getDbusr(),
									Credentials.getDbpass());
							Statement stmt = con.createStatement();
					
							// Retrieving values from table
							ResultSet rs = stmt.executeQuery(sql);
							int i=1000911;
							while (rs.next()) {
								%>
								 <tr>
								 <td><%=i++ %>
							 <td><%=rs.getString(1) %></td>
							  <td><%= FetchDB.getname(rs.getString(2)) %></td>
							   <td><%=rs.getString(3) %></td>
							 </tr>
								<% 
								 
							}
					
							
							con.close();
					
						} catch (Exception e) {
							e.printStackTrace();
							// Logg.writetofile("at com.aemcentral.instance.portal.dbinteraction.serialno()
							// JDBC Connection ERROR", 2);
						}
				      %>
                        </tbody>
                      </table>
                    </div>
                  </div>
                </div>
              </div>
            </div>

            
            <div class="row">
              <div class="col-lg-12 grid-margin stretch-card">
              <div class="card">
                  <div class="card-body">
                    <div class="d-flex flex-row justify-content-between">
                      <h4 class="card-title mb-1">Active Instance Details</h4>
                      <p class="text-muted mb-1">Instance status</p>
                    </div>
                    <div class="row">
                      <div class="col-12">
                        <div id = "activedetails" class="preview-list">             
                        <!-- Loop for jsp will start from here once the button is clicked above to show all tha data present in the browser and will change with each click -->                                    
                          <%
							try{
							//{"VMMCode":"vmm-1","CpuLoadPercentage":1,"TotalMemoryMB":46079.0,"MemoryinuseMB":14112.0,"PercentageMemoryUse":30,"TotalDiskSpaceGB":149,
								//"DiskSpaceinUseGB":28,"PercentageDiskUse":18,"Status":"metrics under control"}
							ArrayList<String> VMMcodes = FetchDB.getvmmcodes();
							
							for(String x: VMMcodes ){
								
								 ArrayList<String> temp= Fetchlast.getlastvalues(x);
								 {
									 %>
									 <div class="preview-item">
		                            <div class="preview-thumbnail">
		                              <div class="preview-icon bg-warning">
		                                <i class="mdi mdi-chart-pie"></i>
		                              </div>
		                            </div>
		                            <div class="preview-item-content d-sm-flex flex-grow"  onclick="window.location.href='/dashboard/vw/machineinfo.jsp?webapp=<%out.println(temp.get(0));%>';">
		                              <div class="flex-grow">
		                               <a href="machineinfo.jsp?webapp=<%=temp.get(0)%>"> <h6 class="preview-subject"><%out.println(temp.get(0)); %></h6></a>
		                                <p class="text-muted mb-0"><%out.println(temp.get(1)); %></p>
		                              </div>
		                              <div class="mr-auto text-sm-right pt pt-sm-0">
		                                <p class="text-muted">CPU : <%out.println(temp.get(2)); %>%</p>
		                                <p class="text-muted mb-0">Memory : <%out.println(temp.get(5)); %>% </p>
		                                 </div>
		                                 
		                              <div class="mr-auto text-sm-right pt-2 pt-sm-0">
		                                 <button type="button" class="btn  btn-rounded btn-icon" onclick="redirecttopage('<%=temp.get(0)%>')">
				                            <i class="mdi mdi-trending-up text-success"></i>
				                          </button>
		                              </div>
		                            </div>
		                          	</div>
									 <% 
									 
									// out.println(temp.get(1));
										
								 }
							}
							}
							catch (Exception e){
								
							}
							
							%>
                          
                          
                          <!--  endfor -->                          
                          
                        </div>
                      </div>
                    </div>
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
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"></script>
    <script>
    
    function redirecttopage(path)
    {
     path ="/dashboard/vw/machineinfo.jsp?webapp="+path;
     window.location.href=path;
    }
    const totalAvailable = <%=total%>;
    const active = <%=active%>;
    const inactive = totalAvailable-active;
	var xValues = ["Inactive", "Active"];
	var yValues = [inactive,active];
	var barColors = [
	  "#b91d47",
	  "#1e7145"
	];
	
	new Chart("pieChart", {
	  type: "pie",
	  data: {
	    labels: xValues,
	    datasets: [{
	      backgroundColor: barColors,
	      data: yValues
	    }]
	  }
	});
    </script>
    
    
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
    <script src="../assets/js/todolist.js"></script>
     
    <!-- endinject -->
    <!-- Custom js for this page -->
    <!-- End custom js for this page -->
  </body>
</html>