<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/backend/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- 底下是专题或频道的文章标题列表 -->
<div class="index_topic">
	<img src="images/jiantou.gif" style="float:left">
	<span style="margin-top:8px;float:left;FONT-WEIGHT: bold; FONT-SIZE: 12px; COLOR: #852b2b; FONT-FAMILY: 宋体;">JavaSE</span>
	<a href="#"><img src="images/more_gray.gif" style="float:right;border:0px"></a>
	<c:forEach items="${articles}" var="c">
		<div class="index_topic_list">
			<img src="images/h_article.gif" >
			<a href="#">${c.title}</a>
			<span>[<fmt:formatDate value="${c.createtime}" pattern="yyyy-mm-dd"/>]</span>
		</div>
	</c:forEach>
</div>
