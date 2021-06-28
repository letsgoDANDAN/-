<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*,cn.jmu.po.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">    
    <title>   增加图书</title>        
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
  <h2> 增加图书</h2>
  </div>
  <div class="m-division"></div>  
 <div id="m-view">
<form action="${pageContext.request.contextPath}/admin/addBookDescribe.do" method="post">
<table align="center" style="font-size:22px;border-collapse:separate;border-spacing:10px;">
	<tr><th>ISBN：</th>
		<td><input type="text" name="ISBN"></td>
	</tr>
	<tr><th>分类：</th>
		<td><input type="text" name="sonID"></td>
	</tr>
	<tr><th>出版社：</th>
		<td><select name="publicID">
		<option value="">-->请选择<--</option>
		<%
		request.setCharacterEncoding("utf-8");
		List<Press> presses = (List<Press>)request.getAttribute("presses");
		for(int i=0;i<presses.size();i++){
		%>
		<option value="<%=presses.get(i).getPublicID() %>"><%=presses.get(i).getPublicname() %></option>
		<%} %>
		</select> </td>
	</tr>
	<tr><th>借阅类型：</th>
		<td><select name="borrowtypeID">
		<option value="">-->请选择<--</option>
		<%
		@SuppressWarnings("unchecked")
		List<BorrowType> borrowTypes = (List<BorrowType>)request.getAttribute("borrowTypes");
		for(int i=0;i<borrowTypes.size();i++){
		%>
		<option value="<%=borrowTypes.get(i).getBorrowtypeID() %>"><%=borrowTypes.get(i).getBorrowtypeName() %></option>
		<%} %>
		</select></td>
	</tr>
	<tr><th>书名：</th>
		<td><input type="text" name="bookname"></td>
	</tr>
	<tr><th>作者：</th>
		<td><select name="authorID">
		<option value="">-->请选择<--</option>
		<%
		List<Author> authors = (List<Author>)request.getAttribute("authors");
		for(int i=0;i<authors.size();i++){
		%>
		<option value="<%=authors.get(i).getAuthorID() %>"><%=authors.get(i).getAuthorname() %></option>
		<%} %>
		</select></td>
	</tr>
	<tr><th>价格：</th>
		<td><input type="text" name="price"></td>
	</tr>
	<tr><th>简介：</th>
		<td><input type="text" name="introduce"></td>
	</tr>
	<tr><th>封面：</th>
		<td><input type="text" name="picture"></td>
	</tr>  
	<tr><th></th>
          <td><input type="submit" value="添加">&nbsp;&nbsp;&nbsp;
          <input type="reset" value="重置 "/>
          <input type="button" value="返回 " onclick="window.location.href='${pageContext.request.contextPath}/admin/return.do'"/>
          </td>
      </tr>
</table>


</form>


 </div>

</body>
</html>