<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ page import="java.util.*" %>
   <%@ page import="cn.jmu.po.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">    
    <title>个人界面</title>        
    <style type="text/css">    
      html{margin: 0; padding: 0;}
      body{margin: 0 auto; padding: 0;max-width: 1200px; background: #f5f6f8;font-size: 14px;max-height: 800px;-webkit-font-smoothing: antialiased;-moz-osx-font-smoothing: grayscale;}
      .m-header{position: relative; height: 0.8rem;line-height: 1rem; font-size:0.18rem;border-bottom:1px solid #EBEBEB; text-align: center;color: #2C2C2C;letter-spacing: 0;background: #fff;background-color: #7FFFD4;}
       .m-return {position: absolute;display: inline-block;top:0.13rem;left: 0.12rem;width: 0.14rem; height: 0.3rem; background-image: url(http://c2.cgyouxi.com/website/mobile/img/arrow-l.png?v=20180122);background-size: 100% 100%;}
      .m-view{background: #fff;height: 800px;overflow:auto;background-color: #FFFAF0;}     
      .m-view ul{

        width:1100px;/*设置元素宽度为980px*/ 

        margin:0px auto 0px auto;/*也可以简写为margin:0 auto*/
        
        justify-content: center;

     }
     .m-view ul li{

        float:left;/*让li元素左浮动*/
        
         list-style:none;
         

       }
       
       .m-view ul li a{

       width:80px;/*设置元素宽为80px*/

       height:28px;/*设置高度为28px*/

       line-height:28px;/*设置行距为28px，让文字在每行的中间位置*/

       background:red;/*设置元素的背景为红色*/

       color:#FFF;/*文字颜色是白色*/

       margin:5px 10px;

       font-size:12px;/*用12号字*/

       display:block;/*这个比较关键，因为a本身就是联级元素，本身不具有宽高，用这个把它变成块级元素，这样前面设置的宽和高就能起作用了*/

       text-align:center;/*让文本居中*/

       text-decoration:none; /*去掉下划线*/

       }
      .m-division{height: 1px; background:#EBEBEB;} 
       
          div.type{
         width: 60px; 
         white-space: nowrap;
         text-overflow: ellipsis; 
         overflow: hidden;
        }
          div.information{
         width: 120px; 
         white-space: nowrap;
         text-overflow: ellipsis; 
         overflow: hidden;
        }
       
      @media screen and (min-width: 360px) {  
        html {
          font-size: 100px!important;
        }
      }
   </style>
</head>
<body>
<%   request.setCharacterEncoding("utf-8");
       if(session.getAttribute("name")==null)
       {
    	   response.setHeader("refresh", "1;URL=login.jsp");
    	   %>
    	   <h2>盗链可耻，请重新登录！</h2>
   <%}else{%>

<div class="m-header" id="m-header">
  <div class="m-return" id="m-return"></div>
  <h2>图书借阅系统</h2>
  </div>
  <div class="m-division"></div>  
 <div class="m-view">
   <img src="img/logo.jpg" width="100px" height="100px" align="right" style="left: 30px">
 <br>
 <h3 align="center">欢迎<font color="blue"><%=session.getAttribute("name") %> </font>登录！</h3>
    <ul>
<li><a href="${pageContext.request.contextPath}/selectBookDescribe.do">首页</a></li>
<li><a href="${pageContext.request.contextPath}/tipGo.do">消息</a></li>
<li><a href="${pageContext.request.contextPath}/showReader.do">信息</a></li>
<li><a href="${pageContext.request.contextPath}/borrowShowGo.do?key=borrow">借阅</a></li>
<li><a href="${pageContext.request.contextPath}/ticketShowGo.do?key=no">罚单</a></li>
<li><a href="${pageContext.request.contextPath}/out.do">退出</a></li>
</ul>
<div style="right: 30px">
 <form action="${pageContext.request.contextPath}/selectBookDescribe.do" method="post">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   <input type="text" name="key" width="100px" placeholder="搜索内容"/>
   &nbsp;&nbsp;&nbsp;&nbsp;
   <input type="submit" value="搜索"></form></div>
<br> <br> <br>
<hr>
   <br><br>
   <div style="position: relative;text-align: center;width:960px;border: 1px;border-color: red;">
    <%
       List<BookType> bd=(List<BookType>)request.getAttribute("bd");
       List<BookType> bn=(List<BookType>)request.getAttribute("bn");
   %>
   <h3 align="center"><a href="${pageContext.request.contextPath}/selectBookDescribe.do">首页</a>/
   <%if(bn!=null){ 
     for(int i=bn.size()-1;i>-1;i--)
     { %><a href="${pageContext.request.contextPath}/selectByTree.do?tkey=<%=bn.get(i).getSonID()%>"><%=bn.get(i).getSonID()%></a>/ 
   <% }}%></h4><hr width="80%" align="center" color="pink">
   <% for(int i=0;i<bd.size();i++)
    {
   %><a href="${pageContext.request.contextPath}/selectByTree.do?tkey=<%=bd.get(i).getSonID()%>">
   <%=bd.get(i).getSonID()%></a>&nbsp;&nbsp;
   <%} %>
   </div>

<br><br><br>
<table align="center" border="1"  width=1080px  bordercolor="blue">

<tr>
    <th>ISBN</th><th>封面</th><th>书名</th><th>分类名</th><th>出版社</th><th>主编</th><th>价格</th><th>简介</th><th>操作</th>
  </tr>
 <%  
 @SuppressWarnings("unchecked")
 List<BookDescribe> books=(List<BookDescribe>)request.getAttribute("books");
 @SuppressWarnings("unchecked")
 List<String> authornames=(List<String>)request.getAttribute("authornames");
 @SuppressWarnings("unchecked")
 List<String> publicnames=(List<String>)request.getAttribute("publicnames");
 @SuppressWarnings("unchecked")
 List<String> booktypenames=(List<String>)request.getAttribute("booktypenames");
 
 if(books.size()!=0)
  {
	  for(int i=0;i<books.size();i++)
	  {
		  BookDescribe bookDescribe=books.get(i);
		  String authorname=authornames.get(i);
		  %>
		  <tr height="110px">
		  <td align="center" width="50px"><%=bookDescribe.getISBN()%></td>
		 <td align="center" width="90px"><img src="img/<%=bookDescribe.getPicture()%>.jpg" width="80px" height="100px"></td>
		  <td align="center" width="100px">《<%=bookDescribe.getBookname()%>》</div></td>
		  <td align="center"><div class="type"><%=booktypenames.get(i)%></div></td>
		  <td align="center"><%=publicnames.get(i) %></td>
		  <td align="center" width="40px"><%=authorname  %></td>
		  <td align="center" width="40px"><%=bookDescribe.getPrice() %></td>  
		  <td align="center"><div class="information"><%=bookDescribe.getIntroduce()%></div></td>  
		  <td align="center" width="40px"><a href="${pageContext.request.contextPath}/booksShowGo.do?ISBN=<%=bookDescribe.getISBN()%>">查看</a></td>
	  <%
		     }
	  }
  
  

%>
		 </tr>


</table>
 </div>
<%} %>
</body>
</html>