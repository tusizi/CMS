<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/backend/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	
<div class="right" style="height:200px">
	<div class="right_topic_1">
		推荐阅读
	</div>
	<div class="right_topic_2">
		<a href="#"><img src="images/more_red.gif" style="float:right;margin-top:10px;border:0px"></a>
	</div>
	<div class="right_topic_3">
		<c:forEach items="${recommend}" var="r">
			·<a href="#">${r.title}</a><br/>
		</c:forEach>
	</div>
</div>
