<%@page import="cn.jmu.po.Ticket"%>
<%@page import="cn.jmu.po.Borrow"%>
<%@page import="cn.jmu.po.Reader"%>
<%@page import="cn.jmu.po.Book"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">    
    <title>   读者罚单信息</title>        
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
  <h2> 待处理请求</h2>
  </div>
  <div class="m-division"></div>  
 <div id="m-view">
<% List<Borrow> borrow=(List<Borrow>)request.getAttribute("rborrow");
   List<String> over=(List<String>)request.getAttribute("over");
   List<String> names=(List<String>)request.getAttribute("rname");
%>
<table align="center" border="0">
<tr>
    <th>读者姓名</th><th>实体书编号</th><th>逾期状态</th><th>操作</th></tr>
    <%for(int i=0;i<borrow.size();i++){ %>
<tr>
    <td align="center"><%=names.get(i)%></td><td align="center"><%=borrow.get(i).getBookID()%></td><td align="center"><%=over.get(i)%></td>
    <td align="center"><a href="${pageContext.request.contextPath}/doReturn.do?bookID=<%=borrow.get(i).getBookID()%>&userid=<%=borrow.get(i).getUserid()%>&over=<%=over.get(i)%>">确定归还</a></td></tr><%} %>
</table>

 </div>

</body>
</html>