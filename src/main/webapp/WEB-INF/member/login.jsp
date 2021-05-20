<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
   
    <title>Signin Template for Bootstrap</title>
    

    <!-- Bootstrap core CSS -->
    <%-- <link href="<%=request.getContextPath() %>/css/bootstrap.min.css" rel="stylesheet"> --%>
	<%@ include file="/common/common_lib.jsp" %>
	<!-- common_lib.jsp의 내용을 지금 기술되는 부분에 코드를 복사해서 붙여넣기 -->
	
	<script src="https://cdn.jsdelivr.net/npm/js-cookie@rc/dist/js.cookie.min.js"></script>
	
    <!-- Custom styles for this template -->
    <link href="${cp }/css/signin.css" rel="stylesheet">
	
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="${cp }/resources/bootstrap/plugins/fontawesome-free/css/all.min.css">
<!-- Ionicons -->
<link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
<!-- icheck bootstrap -->
<link rel="stylesheet"
	href="${cp }/resources/bootstrap/plugins/icheck-bootstrap/icheck-bootstrap.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="${cp }/resources/bootstrap/dist/css/adminlte.min.css">
<!-- Google Font: Source Sans Pro -->
<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">

<script src="https://cdn.jsdelivr.net/npm/js-cookie@rc/dist/js.cookie.min.js"></script>	
	
<style>
	body.login-page {
/* 		background-image: url('/resources/images/intro.jpg'); */
		background-position: center;
		background-size: cover;
		background-repeat: no-repeat;
	}
</style>

<script>

	// html 문서 로딩이 완료 되고 나서 실행 되는 코드
	$(function() {
		
		
		
		// userid, rememberme 쿠키를 확인하여 존재할 경우 값설정, 체크
		if (Cookies.get('userid') != null && Cookies.get('rememberme') != null) {

			$('#userid').val(Cookies.get('userid'))
			$('#rememberme').prop("checked", true)
		}

		$("#loginBtn").on('click', function() {

			// rememberme 체크박스가 체크 되어있는지 확인
			// 체크되어있을 경우

			if ($("#rememberme").is(":checked") == true) {

				// userid input에 있는 값을 userid쿠키로 저장
				Cookies.set("userid", $("#userid").val());

				// rememberme 쿠키로 Y값을 저장
				Cookies.set("rememberme", "Y");
			}

			// 체크 해제되어 있는 경우 : 더이상 사용하지 않는다는 의미 이므로 userid, rememberme 쿠키 삭제
			else {
				Cookies.remove("userid");
				Cookies.remove("rememberme");
			}
			
			
			
			// form태그를 이용하여 signin 요청
			$('#frm').submit();

		})

	})
</script>

</head>
<body class="hold-transition login-page">
	<div class="login-box">
		<div class="login-logo">
			<a href="#"><b>관리자 로그인</b></a>
		</div>
		<!-- /.login-logo -->
		<div class="card">
			<div class="card-body login-card-body">
				<p class="login-box-msg">Sign in to start your session</p>

				<form id="frm" action="${cp }/member/process" method="post">
					<div class="form-group has-feedback">
						<input id="userid" type="text" class="form-control" name="userid"
							placeholder="아이디를 입력하세요." value=""> <span
							class="glyphicon glyphicon-envelope form-control-feedback"></span>
					</div>
					<div class="form-group has-feedback">
						<input type="password" class="form-control" name="pass"
							placeholder="패스워드를 입력하세요." value=""> <span
							class="glyphicon glyphicon-lock form-control-feedback"></span>
					</div>
					<div class="row">
						<div class="col-sm-8">
							<div class="checkbox icheck">
								<label> <input id="rememberme" type="checkbox" name="rememberme" value=""> Remember Me
								</label>
							</div>
						</div>
						<!-- /.col -->
						<div class="col-sm-4">
							<button id="loginBtn" type="button" class="btn btn-primary btn-block btn-flat">로그인</button>
						</div>
						<!-- /.col -->
					</div>
				</form>

			</div>
			<!-- /.login-box-body -->
		</div>
	</div>
	<!-- /.login-box -->



</body>
</html>
