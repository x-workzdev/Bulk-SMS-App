<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- <html> -->
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="shortcut icon" href="./img/Logo.png">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<!-- Latest compiled JavaScript -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"
	integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"
	integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k"
	crossorigin="anonymous"></script>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="./css/index.css">
</head>

<body>
	<div class="backgroundImage">
		<div class="header">
			<a href="" class="logo"><img src="./img/Logo.png" height="90px"></a>
			<div class="header-right">
				<h1>Om's Development Center</h1>
			</div>
			<div style="align-items: flex-end; width: 6%;">
				<button onclick="location.href='Login.jsp'" type="button"
					class="btn btn-danger btn-block"><i class="fa fa-sign-out" aria-hidden="true"></i> Log Out</button>
			</div>
		</div>
		<div class="container">
			<div>
				<nav class="navbar navbar-expand-md navbar-dark bg-dark">	
				<h3 style="color: white; font-weight: 900; margin-left: 30%; align-items: center;">
						<b>X-Workz Bulk SMS App</b>
				</h3>
				<div class="collapse navbar-collapse" id="navbarsExample04">
				</div>
				</nav>
			</div>

			<div class="container" align="center">
				<div class="row"
					style="color: white; text-align: center; margin-top: 3%">
					<div class="col-md-3"></div>
					<div class="col-md-6">
						<h3 style="color: red;">
							<b>${msg}</b> <b>${loginsuccess}</b> <b>${loginfaildbypasswod}</b>
						</h3>
					</div>
					<div class="col-md-3"></div>
				</div>
			</div>							
			<div class="container container_border ">	
			    <div class="sendSMS">
					<h2 align="center"
						style="margin-top: 5%; color: white; padding-top: 3%">
						Send SMS</h2>
					<div class="panel panel-default">
						<div class="panel-body" align="center" style="margin-top: 2%">
							<div class="row mt-3 mb-3">
								<div class="col-sm-4"></div>
								<div class="col-sm-4" align="center">
									<select class="custom-select custom-select-lg sm-3"
										onchange="getSMSType()" id="smsOerations"
										style="font-size: 20px; text-align: center;">
										<option value="0">Select SMS Operation</option>
										<option value="1">Bulk SMS</option>
										<option value="2">Single SMS</option>
										<option value="3">Delivery Report</option>
										<option value="4">Check Balance</option>
									</select>
								</div>
							</div>
						</div>
					</div>
				</div>
			
				
				<div class="sendBulkSMS">
					<div class="panel panel-default">
						<div class="panel-body" align="center" style="margin-top: 2%">
							<form action="sendSMS.do" class="smsSender" method="post"
								enctype="multipart/form-data">
								<table class="col-md-6 table table-bordered table-dark"
									border="1" border-color="white" align="center"
									style="color: white">
									<tr>
										<td colspan="2" align="center"><h3>
												<b>Send Bulk SMS</b>
											</h3></td>
									</tr>
									<tr>
										<td ><h5>Template ID<sup>*</sup>:</h5></td>
										<td>
										    <select class="custom-select custom-select-lg sm-3" name="templateID" class="templateID" onchange="getTemaplate(this);">
												<option value="">Select Template</option>
												<option value="1607100000000058712">FREE COURSE</option>
												<option value="1607100000000058734">FEE REMINDER</option>
										    </select>
										</td>
									</tr>
									<tr>
										<td><h5>
												Enter <sup>*</sup>:
											</h5></td>
										<td><textarea rows="4" cols="30" class="form-control"
												id="textMessages" name="msg"></textarea>
											<h6 id="count_message"></h6></td>
									</tr>
									<tr>
										<td>
											<h5>Upload Excel File:</h5>
										</td>
										<td><input type="file" class="form-control"
											name="uploadFile"></td>
									</tr>
									<tr>
										<td colspan="2" align="center"><input
											class="col-sm-5 btn btn-secondary" type="submit" value="Send SMS"></td>
									</tr>

								</table>
							</form>
						</div>
					</div>
				</div>
				
				<div class="sendSingleSMS">
					<div class="panel panel-default">
						<div class="panel-body" align="center" style="margin-top: 2%">
							<form action="sendSingleSMS.do" class="smsSender" method="post"
								enctype="multipart/form-data">
								<table class="col-md-6 table table-bordered table-dark"
									border="1" border-color="white" align="center"
									style="color: white">
									<tr>
										<td colspan="2" align="center"><h3>
												<b>Send Single SMS</b>
											</h3></td>
									</tr>
									<tr>
										<td>
											<h5>Enter Mobile Number<sup>*</sup>:</h5>
										</td>
										<td><input type="text" class="form-control"
											name="mobile"></td>
									</tr>
									 <tr>
										<td ><h5>Template ID<sup>*</sup>:</h5></td>
										<td>
										    <select class="custom-select custom-select-lg sm-3" name="templateID" class="templateID" onchange="getTemaplate(this);">
												<option value="">Select Template</option>
												<option value="1607100000000058712">FREE COURSE</option>
												<option value="1607100000000058734">FEE REMINDER</option>
										    </select>
										</td>
									</tr>
									<tr>
										<td><h5>
												Enter Message<sup>*</sup>:
											</h5></td>
										<td><textarea rows="4" cols="30" class="form-control"
												id="textMessage" name="message"></textarea>
											<h6 id="count_message"></h6></td>
									</tr>
									
									<tr>
										<td colspan="2" align="center"><input
											class="col-sm-5 btn btn-secondary" type="submit" value="Send SMS"></td>
									</tr>
								</table>
							</form>
						</div>
					</div>
				</div>
				
				
				<div class="reports">
					<div class="panel panel-default">
						<div class="panel-body" align="center" style="margin-top: 2%">
							<form action="reports.do" class="smsSender" method="post"
								enctype="multipart/form-data">
								<table class="col-md-6 table table-bordered table-dark"
									border="1" border-color="white" align="center"
									style="color: white">
									<tr>
										<td colspan="2" align="center"><h3>
												<b>Check SMS Report</b>
											</h3></td>
									</tr>
									<tr>
										<td>
											<h5>Enter Message Id<sup>*</sup>:</h5>
										</td>
										<td><input type="text" class="form-control"
											name="messageId"></td>
									</tr>
									
									<tr>
										<td colspan="2" align="center"><input
											class="col-sm-5 btn btn-secondary" type="submit" value="Get Report"></td>
									</tr>
								</table>
							</form>
						</div>
					</div>
				</div>
				
				<div class="checkSMSBalance">
					<div class="panel panel-default">
						<div class="panel-body" align="center" style="margin-top: 2%">
							<form action="checkSMSBalance.do" class="smsSender" method="post"
								enctype="multipart/form-data">
								<table class="col-md-6 table table-bordered table-dark"
									border="1" border-color="white" align="center"
									style="color: white">
									<tr>
										<td colspan="2" align="center">
										   <h3>
											<b>Check SMS Balance</b>
										  </h3>
										</td>
									 </tr>
									 <tr>
										<td colspan="2" align="center"><input
										class="col-sm-5 btn btn-secondary" type="submit" value="Check Balance"></td>
									</tr>
								</table>
							</form>
						</div>
					</div>
				</div>
			</div>			
				
			</div>
			
			<div>
			<nav class="navbar navbar-expand-md navbar-dark bg-dark"> </nav>
		    </div>
		    
			<script
				src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
			<script type="text/javascript"
				src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.0/jquery.validate.min.js"></script>
			<script type="text/javascript" src="./js/index.js"></script>
		</div>
</body>
</html>