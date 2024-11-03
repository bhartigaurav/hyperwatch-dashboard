<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<div style="margin-left:100px;margin-top:100px;">
<h4>Initialise VM</h4>
<form action="../servlets/ops/initialise" method="POST">
  <label for="domain">Domain:</label><br>
  <input type="text" id="domain" name="domain"><br>
  <p>Please include protocal: http://app-url and don't add / or :port at the end</p>
 
   <label for="machenv">Machine Environment:</label><br>
   <select id="machenv" name="machenv">
    <option value="prod">Prod</option>
    <option value="stage">Stage</option>
    <option value="dev">Dev</option>
  </select>
    <br><br>
   <label for="type">Type:</label><br>
  <select id="type" name="type">
    <option value="vm">Virtual Machine</option>
    <option value="pod">Pod</option>
  </select>
  <br><br>
  <label for="machlev">Machine Level:</label><br>
   <select id="machlev" name="machlev">
    <option value="critical">Critical</option>
    <option value="sandbox">Sandbox</option>
  </select>
  <br><br>
  <label for="webapp">WebApp:</label><br>
  <input type="text" id="webapp" name="webapp" placeholder="App-1">
  <br> <br>
  <input type="submit" value="Submit">
</form>
</div>

</body>
</html>