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

<title>회원 리스트</title>

<%@ include file="/common/common_lib.jsp" %>

<!-- Font Awesome Icons -->
<link rel="stylesheet" href="${cp }/resources/bootstrap/plugins/fontawesome-free/css/all.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="${cp }/resources/bootstrap/dist/css/adminlte.min.css">

<script>
	
// 	$(function() {
	$(document).ready(function(){
		
		$('#memberRegistBtn').on('click', function() {
				
				location.href="/jsp/memberRegist.jsp";
		});
		
		$('#perPageNum').on("change", function() {
			
			perPageVar = $('#perPageNum').val()
			
// 			if(perPageVar !=3 && perPageVar !=5 && perPageVar !=7 ) {
// 				perPageVar = null;
// 			}
			
			if(perPageVar == "") perPageVar = 5;
			
// 			alert("페이지1.4 처리전 : "+perPageVar);
			$('#pageSizeNumber').val(perPageVar);
			$('#frm3').submit();
		});	
		
		$('#selectTextBtn').on('click', function() {
			
			selecgKeyWordVar = $('#selecgKeyWordVar').val()
			
			keywordVar = $('#keywordInputTextVar').val().trim();
			
// 			alert("검색값 11 전 : " + $('#keywordInputTextVar').val() + " " + "선택한 키워드 종류 값 : " + selecgKeyWordVar);
			
			if(selecgKeyWordVar == 'i') {
				
				$('#frmuserid').val(keywordVar);
			}
			else if(selecgKeyWordVar == 'n') {
				
				$('#frmusernm').val(keywordVar);
			}
			else if(selecgKeyWordVar == 'a') {
				
				$('#frmalias').val(keywordVar);
			}
			else {
				$('#frmuserid').val(keywordVar);
				$('#frmusernm').val(keywordVar);
				$('#frmalias').val(keywordVar);
			}
			
// 			alert("검색값11 후 : " + $('#keywordInputTextVar').val() + " " + "선택한 키워드 종류 값 : " + selecgKeyWordVar);
			
			$('#frm4').submit();
		})
		
// 		$(document).on('click', '#common_header_searchBtn', function() {
			
// 			keywordVar = $('#common_header_searchInput').val().trim();
			
			
// 			if(keywordVar != null) {
			
// 				$('#frmuserid').val(keywordVar);
// 				$('#frmusernm').val(keywordVar);
// 				$('#frmalias').val(keywordVar);
// 			}
// 			else {
// 				$('#frmuserid').val("");
// 				$('#frmusernm').val("");
// 				$('#frmalias').val("");
// 			}
			
// 			alert("test5 : " + keywordVar);
// 			$('#frm4').submit();
// 		})
		
		
	});
	
</script>


</head>

<body class="hold-transition sidebar-mini">

	<form id="frm3" action="${cp }/pagingMember.do" >
	   <input type="hidden" id="pageSizeNumber" name = "pageSize" value=""/>
	   <input type="hidden" id="page" name = "page" value="${pageVo.page}"/>
	</form>
	
	<form id="frm4" action="${cp }/pagingMember.do">
<%-- 	<form id="frm4" action="${cp }/pagingSelectMember.do"> --%>
	   <input type="hidden" id="frmuserid" name = "userid" value=""/>
	   <input type="hidden" id="frmusernm" name = "usernm" value=""/>
	   <input type="hidden" id="frmalias" name = "alias" value=""/>
	</form>

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


		<div id="if_list_div" style="position: relative; padding: 0; overflow: hidden; height: 750px;">
			<div class="content-wrapper" style="min-height: 584px;">
				<!-- Content Header (Page header) -->
				<section class="content-header">
					<div class="container-fluid">
						<div class="row md-2">
							<div class="col-sm-6">
								<h1>메인화면</h1>
							</div>
							<div class="col-sm-6">
								<ol class="breadcrumb float-sm-right">
<!-- 									<li class="breadcrumb-item">회원리스트</li> -->
<!-- 									<li class="breadcrumb-item">목록</li> -->
								</ol>
							</div>
						</div>
					</div>
				</section>
				<!-- Main content -->
				<section class="content">
					<div class="card">
						<div class="card-header with-border">
							<div id="keyword" class="card-tools" style="width: 550px;">
							</div>
						</div>
						
						<div class="card-body" style="text-align: center;">
							<div class="row">
								<div class="col-sm-12">
									Main화면
								</div>
								
								<!-- col-sm-12 -->
							</div>
							<!-- row -->
						</div>
					</div>
				</section>

	

</body>
</html>







