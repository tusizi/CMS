<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/backend/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- 首页的导航条 -->
<div id="nav">
	<div id="menu">
		<a href="index.jsp">首页</a>
		<c:forEach items="${channels}" var="c">
			<a href="channel.jsp&channelId=${c.id}">${c.name}</a>
		</c:forEach>
	</div>
</div>
