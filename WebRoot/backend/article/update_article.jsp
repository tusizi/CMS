<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/backend/common/taglib.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/backend/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>添加文章</title>
<style type="text/css">
<!--
body {
	font-family: Arial, Helvetica, sans-serif;
	font-size:12px;
	color:#666666;
	background:#fff;
	text-align:center;

}

* {
	margin:0;
	padding:0;
}

a {
	color:#1E7ACE;
	text-decoration:none;	
}

a:hover {
	color:#000;
	text-decoration:underline;
}
h3 {
	font-size:14px;
	font-weight:bold;
}

pre,p {
	color:#1E7ACE;
	margin:4px;
}
input, select,textarea {
	padding:1px;
	margin:2px;
	font-size:12px;
}
.buttom{
	padding:1px 10px;
	font-size:12px;
	border:1px #1E7ACE solid;
	background:#D0F0FF;
}
#formwrapper {
	width:95%;
	margin:15px auto;
	padding:20px;
	text-align:left;
}

fieldset {
	padding:10px;
	margin-top:5px;
	border:1px solid #1E7ACE;
	background:#fff;
}

fieldset legend {
	color:#1E7ACE;
	font-weight:bold;
	background:#fff;
}

fieldset label {
	float:left;
	width:120px;
	text-align:right;
	padding:4px;
	margin:1px;
}

fieldset div {
	clear:left;
	margin-bottom:2px;
}

.enter{ text-align:center;}
.clear {
	clear:both;
}

-->
</style>
	<script type="text/javascript" src="../ckeditor/ckeditor.js"></script>
	<script type="text/javascript">
		function initCkeditor(){
			CKEDITOR.replace('content',
					{
						skin:'office2003'
					}
			);
		}
		function insertFile(value) {
			// Get the editor instance that we want to interact with.
			var editor = CKEDITOR.instances.content;
			// Check the active editing mode.
			if ( editor.mode == 'wysiwyg' )
			{
				// Insert HTML code.
				// http://docs.ckeditor.com/#!/api/CKEDITOR.editor-method-insertHtml
				editor.insertHtml( "<a href='upload_file/"+value+"'>"+value+"</a>" );
			}
			else
				alert( 'You must be in WYSIWYG mode!' );
		}
		function insertImage(value) {
			// Get the editor instance that we want to interact with.
			var editor = CKEDITOR.instances.content;
			// Check the active editing mode.
			if ( editor.mode == 'wysiwyg' )
			{
				// Insert HTML code.
				// http://docs.ckeditor.com/#!/api/CKEDITOR.editor-method-insertHtml
				editor.insertHtml( "<img src = 'upload_image/"+value+"' title='"+value+"'>");
			}
			else
				alert( 'You must be in WYSIWYG mode!' );
		}
	</script>
</head>
<body onload="initCkeditor()">
<div id="formwrapper">
	<h3>更新网站文章</h3>
	<form action="ArticleServlet" method="post" enctype="multipart/form-data">
		<input type="hidden" name="method" value="openUpdate">
		<%--隐藏域，不需要给用户看见--%>
		<input type="hidden" name ="id" value="${article.id}">
	<fieldset>
		<legend>文章基本信息</legend>
		<div>
			<label for="title">文章标题</label>
			<input type="text" name="title" id="title" value="${article.title}" size="60" maxlength="200" />
			*(最多200个字符)<br />
		</div>
		<div>
			<label for="source">文章来源</label>
			<input type="text" name="source" id="source" value="${article.source}" size="30" maxlength="100" />
			*(最多100个字符)<br />
		</div>
		<div>
			<label for="content">文章内容</label>
			<textarea rows="20" cols="100" name="content" id="content">${article.content}</textarea>
			<br />
		</div>
		<div>
			<label for="attachs">选择附件</label>
			<c:forEach items="${article.attachments}" var="a">
				<c:choose>
					<c:when test="${a.image}">
						<a href="javascript:insertImage('${a.name}')">${a.name}</a>
					</c:when>
					<c:otherwise>
						<a href="javascript:insertFile('${a.name}')">${a.name}</a>
					</c:otherwise>

				</c:choose>
				<a href="ArticleServlet?method=delAttachment&attachmentId=${a.id}">删除</a>
				&nbsp;&nbsp;&nbsp;&nbsp;
			</c:forEach>
			<input type="file" id="attachs" name="attachs" >
			<br />
		</div>
		<div class="enter">
		    <input name="submit" type="submit" class="buttom" value="提交" />
		    <input name="reset" type="reset" class="buttom" value="重置" />
		    <input name="return" type="button" class="buttom" value="返回列表页面" onclick="window.location = 'ArticleServlet'"/>
		</div>
	</fieldset>
	</form>
</div>

</body>
</html>

