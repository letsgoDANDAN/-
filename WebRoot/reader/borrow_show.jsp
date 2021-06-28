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
   /* 下拉按钮样式 */
.dropbtn {
    position:relative;
    left:25px;
    background-color: #4CAF50;
    color: white;
    padding: 16px;
    font-size: 16px;
    border: none;
    cursor: pointer;
}

/* 容器 <div> - 需要定位下拉内容 */
.dropdown {
    position: relative;
    display: inline-block;
    left:150px;
}

/* 下拉内容 (默认隐藏) */
.dropdown-content {
    display: none;
    position: absolute;
    background-color: #f9f9f9;
    min-width: 160px;
    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
}

/* 下拉菜单的链接 */
.dropdown-content a {
    color: black;
    padding: 12px 16px;
    text-decoration: none;
    display: block;
}

/* 鼠标移上去后修改下拉菜单链接颜色 */
.dropdown-content a:hover {background-color: #f1f1f1}

/* 在鼠标移上去后显示下拉菜单 */
.dropdown:hover .dropdown-content {
    display: block;
}

/* 当下拉内容显示后修改下拉按钮的背景颜色 */
.dropdown:hover .dropbtn {
    background-color: #3e8e41;
}

.dropdown:hover .dropdown-content {
    display: block;
}
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
       
       div.title{
         width: 60px; 
         white-space: nowrap;
         text-overflow: ellipsis; 
         overflow: hidden;
        }
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
   
   <div class="dropdown">
  <button class="dropbtn" style="l">查找借阅</button>
  <div class="dropdown-content">
    <a href="${pageContext.request.contextPath}/borrowShowGo.do?key=borrow">借阅中</a>
    <a href="${pageContext.request.contextPath}/borrowShowGo.do?key=over">逾期中</a>
    <a href="${pageContext.request.contextPath}/borrowShowGo.do?key=finish">已归还</a>
  </div>
</div>
<%  @SuppressWarnings("unchecked")
    List<Borrow> borrow=(List<Borrow>)request.getAttribute("borrow"); 
@SuppressWarnings("unchecked")
    List<Book> books=(List<Book>)request.getAttribute("books");
@SuppressWarnings("unchecked")
    List<BookDescribe> bookDescribes=(List<BookDescribe>)request.getAttribute("bookDescribes");
@SuppressWarnings("unchecked")
    List<String> libraryname=(List<String>)request.getAttribute("libraryname");
%>
<br><br>
<table align="center" border="0" bgcolor="#F0F8FF" width="1000px">
<tr><th>ISBN</th><th>书名</th><th>借阅日期</th><th>应还日期</th><th>实还日期 </th><th>图书馆</th><th colspan="2">操作</th><tr>
<%for(int i=0;i<borrow.size();i++){%>
<tr><td align="center"><%=books.get(i).getISBN() %></td><td align="center"><%=bookDescribes.get(i).getBookname() %></td><td align="center"><%=borrow.get(i).getBorrowingtime() %></td>
<td align="center">
<%Date now=new Date();
  if(now.after(borrow.get(i).getReturntime())){ %>
<font color="red"><%=borrow.get(i).getReturntime()%></font><%}else{ %>
<font color="green"><%=borrow.get(i).getReturntime()%></font><%} %>
</td>
<td align="center">
<% if(borrow.get(i).getTime()==null){ %>
尚未归还</td><%}else{ %>
<%=borrow.get(i).getTime()%><%} %>
 <td align="center"><%=libraryname.get(i) %></td>
<td>
<% if(borrow.get(i).isRenewstatus()==false||now.after(borrow.get(i).getReturntime())){ %>
<button disabled="disabled">续借</button><%}else{ %>
<button onclick="window.location.href='${pageContext.request.contextPath}/renewBook.do?bookID=<%=borrow.get(i).getBookID()%>&userid=<%=borrow.get(i).getUserid()%>'">续借</button><%} %></td>
<td><%if(books.get(i).getReaderstatus()<0||borrow.get(i).isReturnstatus()==true){%><button disabled="disabled">归还</button><%}else{ %>
<button onclick="window.location.href='${pageContext.request.contextPath}/returnBook.do?bookID=<%=borrow.get(i).getBookID()%>'">归还</button><%} %></td>
</tr>
<%} %>
</table>
</div>
</body>
</html>