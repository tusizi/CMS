<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/backend/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- 本站搜索 -->
<div class="right">
  <div class="right_topic_1">
    本站搜索
  </div>
  <div class="right_topic_2">
  </div>
  <div class="right_topic_3">
    <form>
      &nbsp;&nbsp;<input type="text">
      <input type="submit" value="搜索"> <br/>
      <input type="radio" name="type" value="baidu">百度
      <input type="radio" name="type" value="google">谷歌
      <input type="radio" name="type" value="local">本站
    </form>
  </div>
</div>