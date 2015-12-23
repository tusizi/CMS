<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/backend/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<!-- 首页左边 -->
	<div id="left" style="text-align:left">
		首页 -&gt; [
		<c:forEach items="${article.channels}" var="c">
			<a href="channel.jsp?channelId=${c.id }">${c.name }</a>
		</c:forEach>
		] -&gt; ${article.title}
		<hr>
<!-- 文章标题及简介 -->
<div class="h2title">${article.title}</div>
<div CLASS="atime">
作者：${article.author}
| 来源：${article.source}
	| 阅读：<script type="text/javascript" src="NavServlet?method=click&articleId=${param.articleId}"></script>
| <fmt:formatDate value="${article.createtime}" pattern="yyyy-MM-dd HH:mm"/>
</div>
${article.content}
		<jsp:include page="/CommonServlet?mehtod=comments"></jsp:include>
	</div>