<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jstl/core"%>
  <script type="text/javascript">
    function reloadcheckcode(img){
      img.src = "/backend/LoginServlet?method=checkcode&"+Math.random();
    }
    //让页面平铺在整个窗口
    if(window.parent != window){
      window.parent.location = window.location;
    }
  </script>

<div class="right">
  <div class="right_topic_1">
    会员信息
    会员登录
  </div>
  <div class="right_topic_2">
  </div>
  <div class="right_topic_3">
    <form action="MemberLoginServlet" method="post">
      &nbsp;
      账号：<input type="text" style="width:100px" name="nickname"> <br/>
      &nbsp;
      密码：<input type="password" style="width:100px" name="password"> <br/>
      <img src="MemberLoginServlet?method=checkcode" id="safecode" onclick="reloadcheckcode(this)" title="如果看不清，请点击本图片换一张"/>
      <br/>
      &nbsp;<input type="submit" value="登录"> <br/>
    </form>
    <a href="MemberServlet">个人控制台</a>
    <a href="MemberLoginServlet?method=quit">退出登录</a>
  </div>
</div>