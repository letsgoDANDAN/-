<%@page import="cn.jmu.po.Reader"%>
<%@page import="cn.jmu.po.Book"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">    
    <title>   读者信息</title>        
    <style type="text/css">    
      html{margin: 0; padding: 0;}
      body{margin: 0 auto; padding: 0;max-width: 1200px; background: #f5f6f8;font-size: 14px;max-height: 800px;}
      .m-header{position: relative; height: 0.8rem;line-height: 1rem; font-size:0.18rem;border-bottom:1px solid #EBEBEB; text-align: center;color: #2C2C2C;letter-spacing: 0;background: #fff;}
       .m-return {position: absolute;display: inline-block;top:0.13rem;left: 0.12rem;width: 0.14rem; height: 0.3rem; background-image: url(http://c2.cgyouxi.com/website/mobile/img/arrow-l.png?v=20180122);background-size: 100% 100%;}
      .m-view{background: #fff;height: 400px;}     
      .m-division{height: 1px; background:#EBEBEB;} 
      @media screen and (min-width: 360px) {  
        html {
          font-size: 100px!important;
        }
      }
   </style>
</head>
<body>

<div class="m-header" id="m-header">
<div class="m-return" id="m-return"></div>
  <h2> 读者信息</h2>
  </div>
  <div class="m-division"></div>  
 <div id="m-view">
<table align="center" border=1px  width=820px rules=none bordercolor="blue" table-layout:fixed;>

<tr>
	<th>读者ID</th><th>单位</th><th>身份</th><th>名字</th><th>邮箱</th><th>电话</th><th>修改</th><th>查看借阅记录</th><th>查看罚单</th>
</tr>
<%
request.setCharacterEncoding("utf-8");
List<Reader> readers = (List<Reader>)request.getAttribute("readers");
if(readers.size()!=0)
{
	for(int i=0;i<readers.size();i++)
	{
		Reader reader = readers.get(i);
%>

		<tr>
		<td align="center"><%=reader.getUserid() %></td>
		<td align="center"><%=reader.getUnitID() %></td>
		<td align="center"><%=reader.getRole() %></td>
		<td align="center"><%=reader.getName() %></td>
		<td align="center"><%=reader.getEmail() %></td>
		<td align="center"><%=reader.getPhone() %></td>
		<td align="center"><a href="${pageContext.request.contextPath}/admin/readyUpdateUser.do?userID=<%=reader.getUserid()%>">修改</a></td>
		<td align="center"><a href="${pageContext.request.contextPath}/admin/readyBorrow_showgo.do?userID=<%=reader.getUserid()%>">查看借阅记录</a></td>
		<td align="center"><a href="${pageContext.request.contextPath}/admin/readyTicket_showgo.do?userID=<%=reader.getUserid()%>">查看罚单</a></td>
	<%}
	}%>
	</tr>
</table>



 </div>

</body>
</html>