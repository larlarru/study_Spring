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
								<h1>회원리스트</h1>
							</div>
							<div class="col-sm-6">
								<ol class="breadcrumb float-sm-right">
									<li class="breadcrumb-item">회원리스트</li>
									<li class="breadcrumb-item">목록</li>
								</ol>
							</div>
						</div>
					</div>
				</section>
				<!-- Main content -->
				<section class="content">
					<div class="card">
						<div class="card-header with-border">
							<button type="button" id="memberRegistBtn" class="btn btn-primary" onclick="OpenWindow(&#39;registForm.do&#39;,&#39;회원등록&#39;,800,700);">회원등록</button>
							<div id="keyword" class="card-tools" style="width: 550px;">
							
							<form id="#frm2" action="${cp }/pagingMember.do" method="get">
								<div class="input-group row">
									<!-- sort num -->
									<select class="form-control col-md-3" id="perPageNum">
										<option value="">정렬개수</option>
										<option value="3">3개씩</option>
										<option value="5">5개씩</option>
										<option value="7">7개씩</option>
									</select>
									<!-- search bar -->
									<select id="selecgKeyWordVar" class="form-control col-md-3" name="searchType" id="searchType">
										<option value="">검색구분</option>
										<option value="i">아이디</option>
										<option value="n">이름</option>
										<option value="a">별명</option>
									</select> 
									<input id="keywordInputTextVar" class="form-control" type="text" name="keyword" placeholder="검색어를 입력하세요." value=""/> 
									<span class="input-group-append">
<!-- 										<button id="selectTextBtn" class="btn btn-primary" type="button" id="searchBtn" data-card-widget="search" onclick="searchList_go(1);"> -->
										<button id="selectTextBtn" class="btn btn-primary" type="button" id="searchBtn" data-card-widget="search">
											<i class="fa fa-fw fa-search"></i>
										</button>
									</span>
									<!-- end : search bar -->
								</div>
							</form>
							</div>
						</div>
						
						<div class="card-body" style="text-align: center;">
							<div class="row">
								<div class="col-sm-12">
									<table class="table table-bordered">
										<tbody>
											<c:forEach items="${memberList }" var="member">
												<tr>
													<th><a href="${cp }/member?userid=${member.userid }">${member.userid }</a></th>
													<th>${member.pass }</th>
													<th>${member.alias }</th>
													<th>${member.addr1 }</th>
													<th><fmt:formatDate value="${member.reg_dt }" pattern="yyyy-MM-dd" /></th>
													<!-- yyyy-MM-dd  -->
												</tr>
											</c:forEach>
											
											
										</tbody>
									</table>
								</div>
								
								<!-- col-sm-12 -->
							</div>
							<!-- row -->
						</div>
						
						<!-- card-body -->
						<div class="card-footer">
							<nav aria-label="member list Navigation">
								<ul class="pagination justify-content-center m-0">
									<li class="page-item"><a class="page-link" href="${cp }/pagingMember.do?page=1&pageSize=${pageVo.pageSize}"><i class="fas fa-angle-double-left"></i></a></li>
									<c:choose>
										<c:when test="${pageVo.page > 1 }">
											<li class="page-item"><a class="page-link" href="${cp }/pagingMember.do?page=${pageVo.page-1 }&pageSize=${pageVo.pageSize}"><i class="fas fa-angle-left"></i></a></li>
									</c:when>
										<c:otherwise>
											<li class="page-item"><a class="page-link" href="#"><i class="fas fa-angle-left"></i></a></li>
										</c:otherwise>
									</c:choose>
										<c:forEach begin="1" end="${pagination }" var="i"> 
											<c:choose>
												<c:when test="${pageVo.page >= 0 }">
													<c:choose>
														
														<c:when test="${pageVo.page == i }">
															<li class="page-item active"><a class="page-link" href="#">${i }</a></li>
	<%-- 														<li class="page-item active"><a class="page-link" href="#">${i }</a></li> --%>
														</c:when>
														<c:otherwise>
															<li><a class="page-link" href="${cp }/pagingMember.do?page=${i }&pageSize=${pageVo.pageSize}">${i }</a></li>
<%-- 															<li><a class="page-link" href="#">${i }</a></li> --%>
														</c:otherwise>
													</c:choose>
												</c:when>
												<c:otherwise>
													<li><a class="page-link" href="${cp }/pagingMember.do?page=${i }&pageSize=${pageVo.pageSize}">${i }</a></li>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									
									<c:choose>
										<c:when test="${pageVo.page == pagination}">
											<li class="page-item"><a class="page-link" href="${cp }/pagingMember.do?page=${pagination }&pageSize=${pageVo.pageSize}"><i class="fas fa-angle-right"></i></a></li>
									</c:when>
										<c:otherwise>
											<li class="page-item"><a class="page-link" href="${cp }/pagingMember.do?page=${pageVo.page+1 }&pageSize=${pageVo.pageSize}"><i class="fas fa-angle-right"></i></a></li>
										</c:otherwise>
									</c:choose>
									<li class="page-item"><a class="page-link" href="${cp }/pagingMember.do?page=${pagination }&pageSize=${pageVo.pageSize}"><i class="fas fa-angle-double-right"></i></a></li>
								</ul>
							</nav>

						</div>
						<!-- card-footer -->
					</div>
					<!-- card  -->
				</section>
			</div>
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
	<!-- ${cp }wrapper -->

	<!-- REQUIRED SCRIPTS -->

	

</body>
</html>







