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
      
      .btn {
      /* 文字颜色 */
      color: #0099CC; 
      /* 清除背景色 */
      background: transparent; 
      /* 边框样式、颜色、宽度 */
      border: 2px solid #0099CC;
      /* 给边框添加圆角 */
      border-radius: 6px; 
      /* 字母转大写 */
      border: none;
      color: white;
      padding: 16px 32px;
      text-align: center;
      display: inline-block;
      font-size: 16px;
      margin: 4px 2px;
      -webkit-transition-duration: 0.4s; /* Safari */
      transition-duration: 0.4s;
      cursor: pointer;
      text-decoration: none;
      text-transform: uppercase;
}
.btn1 {
      background-color: white; 
      color: black; 
      border: 2px solid #008CBA;
}
/* 悬停样式 */
.btn1:hover {
      background-color: #008CBA;
      color: white;
}
       
      @media screen and (min-width: 360px) {  
        html {
          font-size: 100px!important;
        }
      }
   </style>
</head>
<body>
<%
      request.setCharacterEncoding("utf-8");
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
   <input type="text"name="key" width="100px" placeholder="搜索内容"/>
   &nbsp;&nbsp;&nbsp;&nbsp;
   <input type="submit" value="搜索"></form></div>
<br> <br> <br>
<hr>
   <br><br>
<%Reader reader=(Reader)request.getAttribute("reader"); 
%>
  <table align="center" border="0" width=950px bgcolor="#F0FFFF" >
<tr>
<th bgcolor="#00FFFF">用户名</th>
<td align="center"><%=reader.getUserid() %></td></tr>
<tr><th bgcolor="#00FFFF">单位</th>
<td align="center">${unitname}</td></tr>
<tr><th bgcolor="#00FFFF">读者类型</th>
<td align="center">${readertypename}</td></tr>
<tr>
<th bgcolor="#00FFFF">姓名</th>
<td align="center">
<%=reader.getName() %></td></tr>
<tr><th bgcolor="#00FFFF">E-mail</th>
<td align="center">
<%=reader.getEmail()%></td></tr>
<tr><th bgcolor="#00FFFF">联系方式</th>
<td align="center">
<%=reader.getPhone()%>
</td></tr>
<tr>
<th bgcolor="#00FFFF">活跃状态</th>
<%if(reader.isBorrowstatus()==false) {%>
<td align="center" bgcolor="red"><font color="#F0F8FF" face="黑体" >不活跃</font></td>
<%}else{%>
<td align="center" bgcolor="green"><font color="#F0F8FF" face="黑体">活跃</font></td>
<%} %>
</tr>
<tr><th bgcolor="#00FFFF">中文书籍借阅数量</th>
<td align="center">
<%=reader.getCbooknumber()%>
</td></tr>
<tr><th bgcolor="#00FFFF">外文书籍借阅数量</th>
<td align="center">
<%=reader.getFbooknumber()%>
</td></tr>
<tr><th bgcolor="#00FFFF">总借阅数量</th>
<td align="center">
<%=reader.getBooknumber()%>
</tr>
<tr><th>
</th>
<td align="center"><button onclick="window.location.href='updateReaderGo.do'" class="btn"><font class="btn1">修改信息</font></button></td>
</table>
<%if(request.getAttribute("flag")!=null){ %>
 <h4 align="center"><font color="red">${flag}</font></h4><%}} %>
 </div>

</body>
</html>