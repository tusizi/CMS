<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/backend/common/taglib.jsp"%>
  <script type="text/javascript">
    function selectPagesize(field){
      alert(document.getElementById("firstPage").href+"&pagesize="+field.value);
      window.location = document.getElementById("firstPage").href+"&pagesize="+field.value;
    }
  </script>
<pg:pager url="${param.url}" items="${total}" maxPageItems="${pagesize}" export="currentPageNumber=pageNumber" maxIndexPages="15" >
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td width="33%"><div align="left"><span class="STYLE22">&nbsp;&nbsp;&nbsp;&nbsp;
          共有<strong> ${total}</strong> 条记录，当前第<strong>${currentPageNumber}</strong> 页，
         <pg:last>
           共 <strong>${pageNumber}</strong> 页
         </pg:last></span></div></td>
      <td width="67%" align=right vAlign="center" noWrap>
        <c:forEach items="${param.params}" var="p">
          <pg:param name="${p}"/>
        </c:forEach>
        <pg:first >
          <a id="firstPage" href="${pageUrl}">首页</a>
        </pg:first>
        <pg:prev>
          <a href="${pageUrl}">前页</a>
        </pg:prev>
        <pg:pages>
          <c:choose>
            <c:when test="${currentPageNumber eq pageNumber}">
              <font color="red">${currentPageNumber}</font>
            </c:when>
            <c:otherwise>
              <a href="${pageUrl}">${pageNumber}</a>
            </c:otherwise>
          </c:choose>
        </pg:pages>
        <pg:next>
          <a href="${pageUrl}">下页</a>
        </pg:next>
        <pg:last>
          <a href="${pageUrl}">尾页</a>
        </pg:last>

        <select name="pagesize" onchange="selectPagesize(this)" >
          <c:forEach begin="5" end="50" step="5" var="i">
            <option value="${i}"<c:if test="${i eq pagesize}">selected</c:if> >${i}</option>
          </c:forEach>
        </select>

      </td>
    </tr>
  </table>
</pg:pager>

