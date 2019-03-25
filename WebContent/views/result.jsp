<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setAttribute("msg","미안해요 전반적으로");    
%>
<script>
	alert("${msg}");
	location.href="${url}"; 
</script>