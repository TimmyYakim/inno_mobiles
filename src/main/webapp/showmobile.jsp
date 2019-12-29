<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<h1>Mobile</h1>
<ul class="list-group">
   <li class="list-group-item">${mobile.id}</li>
   <li class="list-group-item">${mobile.model}</li>
   <li class="list-group-item">${mobile.price}</li>
   <li class="list-group-item">${mobile.manufacturer}</li>
</ul>
<br>
<a href="${pageContext.request.contextPath}/main.jsp">Main page</a>
