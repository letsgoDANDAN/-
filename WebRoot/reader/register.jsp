<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">    
    <title>注册</title>        
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
  <h2>图书借阅系统</h2>
  </div>
  <div class="m-division"></div>  
 <div id="m-view">
  <form action="${pageContext.request.contextPath}/addReader.do" method="post">
  <input type="hidden" name="role" value="reader">
  <table align="center" style="font-size:22px;border-collapse:separate;border-spacing:10px;">
      <tr><th>账号：</th>
          <td><input type="text" name="userid"></td>
      </tr>
      <tr><th>密码：</th>
          <td><input type="text" name="password">
          </td>
       <tr>
       <tr><th>姓名：</th>
          <td><input type="text" name="name"></td>
      </tr>
      <tr><th>邮箱：</th>
          <td><input type="text" name="Email"></td>
      </tr>
      <tr><th>电话：</th>
          <td><input type="text" name="phone"></td>
      </tr>
       <tr><th>身份：</th>
          <td><select name="readertypeID" >
          <option value="">-->请选择<--</option>
          <%
          request.setCharacterEncoding("utf-8");
          @SuppressWarnings("unchecked")
          List<String> readertypename=(List<String>)request.getAttribute("getreadertype");
          @SuppressWarnings("unchecked")
            List<String> readertypeid=(List<String>)request.getAttribute("readertypeID");
          for(int i=0;i<readertypename.size();i++)
          {
          %>
          <option value="<%=readertypeid.get(i)%>"><%=readertypename.get(i) %></option><%} %>
          </select></td>
      </tr>
        <tr><th>单位：</th>
          <td><select name="unitID">
          <option value="">-->请选择<--</option>
          <%        
          @SuppressWarnings("unchecked")
          List<String> unitname=(List<String>)request.getAttribute("getunit");
          @SuppressWarnings("unchecked")
            List<String> unitid=(List<String>)request.getAttribute("unitID");
          for(int i=0;i<unitname.size();i++)
          {
          %>
          <option value="<%=unitid.get(i)%>"><%=unitname.get(i) %></option><%} %>
          </select></td>
      </tr>
      <tr>
       <th></th>
          <td><input type="submit" value="注册">&nbsp;&nbsp;
          <input type="reset" value="重置 "/>&nbsp;&nbsp;
          <input type="button" value="返回 " onclick="window.location.href='login.jsp'"/></td>
      </tr>
     
   </table>
    </form>
 </div>
 
</body>
</html>