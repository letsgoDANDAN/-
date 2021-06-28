<%@page import="cn.jmu.po.Book"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">    
    <title>   实体书</title>        
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
  <h2> 实体书列表</h2>
  </div>
  <div class="m-division"></div>  
 <div id="m-view">
<table align="center" border=1px  width=820px rules=none bordercolor="blue" table-layout:fixed;>

<tr>
	<th>实体书编号</th><th>ISBN</th><th>所处分馆</th><th>借阅状态</th><th>详情</th><th>修改</th><th>删除</th>
</tr>
<%
request.setCharacterEncoding("utf-8");
List<Book> books = (List<Book>)request.getAttribute("books");
if(books.size()!=0)
{
	for(int i=0;i<books.size();i++)
	{
		Book book = books.get(i);
%>

		<tr>
		<td align="center"><%=book.getBookID() %></td>
		<td align="center"><%=book.getISBN() %></td>
		<td align="center"><%=book.getBranchID() %></td>
		<td align="center"><%=book.getReaderstatus() %></td>
		<td align="center"><%=book.getDetails() %></td>
		<td align="center"><a href="${pageContext.request.contextPath}/admin/readyModifyBook.do?bookID=<%=book.getBookID()%>">修改</a></td>
		<td align="center"><a href="${pageContext.request.contextPath}/admin/DeleteBook.do?bookID=<%=book.getBookID()%>">删除</a></td>
	<%}
	}%>
	</tr>
</table>



 </div>

</body>
</html>