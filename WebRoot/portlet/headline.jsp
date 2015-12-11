<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/backend/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- 首页图片及首页文章标题、简介 -->
<div style="width:100%;border:0px;text-align: left;">
	<img style="float:left;width:200px;height:200px;" src="images/earth.jpg">
	<c:forEach items="${headline}" var="h">
		<h4>${h.title}</h4>
		<p>${h.intro}</p>
	</c:forEach>
</div>
