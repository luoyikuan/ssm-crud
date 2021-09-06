<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% pageContext.setAttribute("APP_PATH", request.getContextPath()); %>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<script src="${APP_PATH}/static/js/jquery-3.6.0.min.js"></script>
	<link rel="stylesheet" href="${APP_PATH}/static/bootstrap/css/bootstrap.min.css" />
	<script src="${APP_PATH}/static/bootstrap/js/bootstrap.min.js"></script>

	<title>员工列表</title>
</head>

<body>
	<div class="container">
		<!-- 标题 -->
		<div class="row">
			<div class="col-md-12">
				<h1>SSM-CRUD</h1>
			</div>
		</div>
		<!-- 按钮 -->
		<div class="row">
			<div class="col-md-4 col-md-offset-8">
				<button class="btn btn-primary">新增</button>
				<button class="btn btn-danger">删除</button>
			</div>
		</div>
		<!-- 表格 -->
		<div class="row">
			<div class="col-md-12">
				<table class="table table-hover">
					<tr>
						<th>#</th>
						<th>姓名</th>
						<th>性别</th>
						<th>邮箱</th>
						<th>部门</th>
						<th>操作</th>
					</tr>
					<c:forEach items="${pageInfo.list}" var="emp">
					<tr>
						<td>${emp.empId}</td>
						<td>${emp.empName}</td>
						<td>${emp.gander=="M"?"男":"女"}</td>
						<td>${emp.email}</td>
						<td>${emp.department.deptName}</td>
						<td>
							<button class="btn btn-sm btn-primary">
								<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
								编辑
							</button>
							<button class="btn btn-sm btn-danger">
								<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
								删除
							</button>
						</td>
					</tr>
					</c:forEach>
				</table>
			</div>
		</div>
		<!-- 分页 -->
		<div class="row">
			<div class="col-md-6">
				当前${pageInfo.pageNum}页,总${pageInfo.pages}页,总${pageInfo.total}条记录
			</div>
			<div class="col-md-6">
			<!-- 分页条结束 -->
				<nav aria-label="Page navigation">
					<ul class="pagination">
						<li><a href="${APP_PATH}/emps?pn=1">首页</a></li>
						<c:if test="${pageInfo.hasPreviousPage }">
							<li>
								<a href="${APP_PATH}/emps?pn=${pageInfo.pageNum - 1}" aria-label="Previous">
									<span aria-hidden="true">&laquo;</span>
								</a>
							</li>
						</c:if>
						<c:forEach items="${pageInfo.navigatepageNums}" var="pageNum">
							<c:if test="${pageInfo.pageNum == pageNum}">
								<li class="active"><a href="#">${pageNum}</a></li>
							</c:if>
							<c:if test="${pageInfo.pageNum != pageNum}">
								<li><a href="${APP_PATH}/emps?pn=${pageNum}">${pageNum}</a></li>
							</c:if>
						</c:forEach>
						<c:if test="${pageInfo.hasNextPage }">
							<li>
								<a href="${APP_PATH}/emps?pn=${pageInfo.pageNum + 1}" aria-label="Next">
									<span aria-hidden="true">&raquo;</span>
								</a>
							</li>
						</c:if>
						<li><a href="${APP_PATH}/emps?pn=${pageInfo.pages}">末页</a></li>
					</ul>
				</nav>
				<!-- 分页条开始 -->
			</div>
		</div>
	</div>
</body>

</html>