<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/backend/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<!-- 首页左边 -->
	<div id="left" style="text-align:left">
		首页 -&gt; ${channel.name}
		<hr>

<!-- 文章标题及简介 -->
<c:forEach items="${pageVO.datas}" var="a">
	<div class="h2title">${a.title}</div>
	<div CLASS="atime">
	作者：${a.author}
	| 来源：
	| <fmt:formatDate value="${a.createtime}" pattern="yyyy-MM-dd HH:mm"/>
	</div>
	${a.intro}
	<br/>
	<a href="article.jsp?articleId=${a.id}">【阅读全文】</a>
</c:forEach>
		<jsp:include page="/backend/common/pager.jsp">
			<jsp:param name="url" value="channel.jsp"/>
			<jsp:param name="params" value="channelI"/>
		</jsp:include>
</div>