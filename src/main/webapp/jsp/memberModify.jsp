<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="x-ua-compatible" content="ie=edge">

<title>회원 정보 수정</title>

<%@ include file="/common/common_lib.jsp" %>

<!-- Font Awesome Icons -->
<link rel="stylesheet" href="${cp }/resources/bootstrap/plugins/fontawesome-free/css/all.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="${cp }/resources/bootstrap/dist/css/adminlte.min.css">
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script>
		$(document).ready(function(){
			
			$('#cancelBtn').on('click', function() {
				
				location.href="/pagingMember.do";
			})
			
			$(document).submit(function() {
				
				if( $('#pass').val().length < 1 && $('#usernm').val().length < 1 ) {
					alert("비밀번호, 이름을 꼭 입력해 주십시오.");
					 return false;
				 }
				
			})
			
			// 주소검색 버튼이 클릭 되었을 때 다음주소 api 팝업을 연다
			$("#addrBtn").on("click", function() {
				
			    new daum.Postcode({
			        oncomplete: function(data) {
			            
			            $("#addr1").val(data.roadAddress);		//도로주소
			            $("#zipcode").val(data.zonecode);		//우편번호
			        }
			    }).open();
				
			    
			    // 사용자 편의성을 위해 상세주소 입력 input 태그로 focus 설정
			    $("#addr2").focus();
				
			});
			
			// picture input의 파일 변경시 이벤트 
			$("#picture").change(function(){
			   readURL(this);
			});
			
			
		});
		
		function readURL(input) {
			if (input.files && input.files[0]) {
				var reader = new FileReader();
			  
				reader.onload = function (e) {
					$('#pictureViewImg').attr('src', e.target.result);  
				}
				reader.readAsDataURL(input.files[0]);
			}
		}
			 
		
	</script>

</head>
<body class="hold-transition sidebar-mini">
	
	<div class="wrapper">

		<!-- Navbar -->
		<nav class="main-header navbar navbar-expand navbar-white navbar-light">
			<%@ include file="/common/common_header.jsp" %>
		</nav>
		<!-- /.navbar -->


		<!-- Main Sidebar Container -->
		<aside class="main-sidebar sidebar-dark-primary elevation-4">
			<%@ include file="/common/common_left.jsp" %>
		</aside>

		<!-- ***************************************회원상세정보보는부분*************************************** -->
		
		<div id="if_list_div" style="position: relative; padding: 0; overflow: hidden;">
			<!-- Content Wrapper. Contains page content -->
			<div class="content-wrapper">

				<!-- Main content -->
				<section class="content register-page" style="height:100%;">
					<div class="container-fluid">
						<div class="login-logo">
							<b>회원 정보수정</b>
						</div>
						<!-- form start -->
						<div class="card">
							<div class="register-card-body">
								<form role="form" class="form-horizontal" id="frm" action="${cp}/memberModify.do?userid=${member.userid }" method="post" enctype="multipart/form-data">
									<div class="input-group mb-3">
										<div class="mailbox-attachments clearfix" style="text-align: center; width:100%;">
											<div class="mailbox-attachment-icon has-img" id="pictureView" style="border: 1px solid green; height: 200px; width: 140px; margin: 0 auto;">
												<img id="pictureViewImg" src="${cp}/profile.do?userid=${member.userid }" style="width:100%; height:100%;"/>
											</div>
											<div class="mailbox-attachment-info">
												<div class="input-group input-group-sm">
													<input id="picture" class="form-control"
														   type="file" name="picture" accept=".gif, .jpg, .png" style="height:37px;"/>
												</div>
											</div>
										</div>
										<br />
									</div>
									
									<div class="form-group row">
										<label for="id" class="col-sm-3" style="font-size: 0.9em;">
											<span style="color: red; font-weight: bold;">*</span>아이디
										</label>
										<div class="col-sm-9 input-group-sm">
											<input name="id" type="text" class="form-control" id="userid" value="${member.userid }" readonly="readonly" />
										</div>
									</div>
									
									<div class="form-group row">
										<label class="col-sm-3" style="font-size: 0.9em;">
											아이디
										</label>
										<div class="col-sm-9 input-group input-group-sm">
											<span class="input-group-append-sm">${member.userid }</span>
										</div>
									</div>
									
									<div class="form-group row">
										<label for="pwd" class="col-sm-3" style="font-size: 0.9em;">
											<span style="color: red; font-weight: bold;">*</span>패스워드</label>
										<div class="col-sm-9 input-group-sm">
											<input class="form-control" name="pass" type="password" class="form-control" id="pass" value="${member.pass }" />
										</div>
									</div>
									
									<div class="form-group row">
										<label for="name" class="col-sm-3" style="font-size: 0.9em;">
											<span style="color: red; font-weight: bold;">*</span>이 름
										</label>
										<div class="col-sm-9 input-group-sm">
											<input class="form-control" name="usernm" type="text" id="usernm" value="${member.usernm }" />
										</div>

									</div>
									<div class="form-group row">
										<label for="alias" class="col-sm-3" style="font-size: 0.9em;">별명</label>
										<div class="col-sm-9 input-group-sm">
											<input class="form-control" name="alias" type="text" id="alias" value="${member.alias }" />
										</div>
									</div>
									<div class="form-group row">
										<label for="addr1" class="col-sm-3 control-label">주소</label>
										<div class="col-sm-3 input-group-sm">
											<input name="addr1" type="text" class="form-control" id="addr1" placeholder="주소" value="${member.addr1 }" readonly>
										</div>
										<div class="col-sm-3 input-group-sm">
											<input name="addr2" type="text" class="form-control" id="addr2" value="${member.addr2 }">	
										</div>
										
										<div class="col-sm-2 input-group-sm">
											<input name="zipcode" type="text" class="form-control" id="zipcode" value="${member.zipcode }" readonly>
										</div>
										<div class="col-sm-1 input-group-sm">
											<span class="input-group-append-sm">
												<button id="addrBtn" type="button" class="btn btn-info btn-sm btn-append" style="font-size:0.7em;">주소검색</button>
											</span>
										</div>
									</div>
									
									<div class="card-footer">
										<div class="row">
											<div class="col-sm-6">
												<button type="submit" id="modifyBtn" class="btn btn-info">수정완료</button>
											</div>

											<div class="col-sm-6">
												<button type="button" id="cancelBtn" onclick="CloseWindow();" class="btn btn-default float-right">&nbsp;&nbsp;&nbsp;취 &nbsp;&nbsp;소&nbsp;&nbsp;&nbsp;</button>
											</div>

										</div>
									</div>
								</form>
							</div>
							<!-- register-card-body -->
						</div>
					</div>
				</section>
				<!-- /.content -->
			</div>
			<!-- /.content-wrapper -->
		</div>
	</div>

	<!-- Main Footer -->
	<footer class="main-footer">
		<!-- To the right -->
		<div class="float-right d-none d-sm-inline">Anything you want</div>
		<!-- Default to the left -->
		<strong>Copyright &copy; 2014-2019 <a href="https://adminlte.io">AdminLTE.io</a>.
		</strong> All rights reserved.
	</footer>
	</div>
	<!-- ./wrapper -->


</body>
</html>







