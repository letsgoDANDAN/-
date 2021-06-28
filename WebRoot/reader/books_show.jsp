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
      body{margin: 0 auto; padding: 0;max-width: 1200px; background: #f5f6f8;font-size: 14px;max-height: 800px;}
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
       .login-button { /* 按钮美化 */
	 width: 270px; /* 宽度 */
	height: 40px; /* 高度 */
	border-width: 0px; /* 边框宽度 */
	border-radius: 3px; /* 边框半径 */
	background: #1E90FF; /* 背景颜色 */
	cursor: pointer; /* 鼠标移入按钮范围时出现手势 */
	outline: none; /* 不显示轮廓线 */
	font-family: Microsoft YaHei; /* 设置字体 */
	color: white; /* 字体颜色 */
	font-size: 17px; /* 字体大小 */
  }
   .login-button:hover { /* 鼠标移入按钮范围时改变颜色 */
	background: #5599FF;
  }
       
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
   <%BookDescribe books=(BookDescribe)request.getAttribute("books"); 
     BorrowConditions borrowConditions=(BorrowConditions)request.getAttribute("borrowConditions");
   %>
 <table align="center" border="0" width=1000px bgcolor="#F0FFFF" >
<tr>
<th bgcolor="#00FFFF">ISBN</th>
<td align="center"><%=books.getISBN() %></td></tr>
<tr><th bgcolor="#00FFFF">书名</th>
<td align="center"><%=books.getBookname() %></td></tr>
<tr><th bgcolor="#00FFFF">封面</th>
<td align="center"><img src="img/<%=books.getPicture()%>.jpg" width="200px" height="260px"></td></tr>
<tr><th bgcolor="#00FFFF">分类号</th>
<td align="center">${booktypename}</td></tr>
<tr>
<th bgcolor="#00FFFF">出版社</th>
<td align="center">
${publicname }</td></tr>
<tr><th bgcolor="#00FFFF">借阅类型</th>
<td align="center">
${borrowtypename }</td></tr>
<tr><th bgcolor="#00FFFF">单价</th>
<td align="center">
<%=books.getPrice()%>
</td></tr>
<tr>
<th bgcolor="#00FFFF">简介</th>
<td align="center" height="100px"><%=books.getIntroduce() %></td>
</tr>
</table>
 <h4 align="center"><font color="#CD5C5C">根据您的情况，提供如下借阅条件：</font></h4>
 <table align="center" border="0" width=1000px bgcolor="#FFF8DC">
 <tr><th align="center" bgcolor="#FFD700">册数</th><th align="center" bgcolor="#FFD700">借期</th><th align="center" bgcolor="#FFD700">续借</th></tr>
 <tr><th><%=borrowConditions.getBorrownum() %></th> <th><%=borrowConditions.getBorrowtime()%></th> <th><%=borrowConditions.getRenewtime() %></th>
 </tr>
 <tr>
 </tr>
 </table>
 <%Book b=(Book)session.getAttribute("borrowbook");
 if(b!=null&&b.getISBN().equals(books.getISBN())){ %>
  <h1 align="center"><button class="login-button" onclick="window.location.href='borrowBook.do?ISBN=<%=books.getISBN()%>&borrownum=<%=borrowConditions.getBorrownum()%>&borrowtypeID=<%=borrowConditions.getBorrowtypeID()%>&borrowtime=<%=borrowConditions.getBorrowtime()%>'">借阅</button></h1>
 <%} %>
 </div>
</body>
</html>