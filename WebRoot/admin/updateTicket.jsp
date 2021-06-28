<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ page import="java.util.*,cn.jmu.po.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">    
    <title>   修改罚单</title>        
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
  <h2> 修改罚单</h2>
  </div>
  <div class="m-division"></div>  
 <div id="m-view">
 <%
 request.setCharacterEncoding("utf-8");
Ticket ticket = (Ticket) request.getAttribute("ticket");
 %>
<form action="${pageContext.request.contextPath}/admin/updateTicket.do" method="post">
<table align="center" style="font-size:22px;border-collapse:separate;border-spacing:10px;">
	<tr><th>罚单编号：</th>
		<td><input type="hidden" name="fineID" value="<%=ticket.getFineid()%>"><%=ticket.getFineid()%></td> 
	</tr>
	<tr><th>罚单类型：</th>
		<td><input type="hidden" name="finetypeID" value="<%=ticket.getFinetypeid()%>"><%=ticket.getFinetypeid()%></td>
	</tr>
	<tr><th>用户ID：</th>
		<td><input type="hidden" name="userID" value="<%=ticket.getUserid()%>"><%=ticket.getUserid()%></td>
	</tr>
	<tr><th>罚单金额：</th>
		<td><input type="text" name="totalfineprice" value="<%=ticket.getTotalfineprice()%>"></td>
	</tr>
	<tr><th>详情：</th>
		<td><input type="text" name="finedetails" value="<%=ticket.getFinedetail()%>"></td>
	</tr>  
	<tr><th>是否罚款：</th>
		<td><input type="text" name="settlestatus" value="<%=ticket.isSettlestatus()%>"></td>
	</tr>
	<tr><th></th>
          <td><input type="submit" value="修改"></td>
      </tr>
</table>
</form>



 </div>

</body>
</html>