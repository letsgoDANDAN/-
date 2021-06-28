<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">          
<html>          
<head>              
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">    
    <title>登录</title>        
    <style type="text/css">    
      html{margin: 0; padding: 0;}
      body{margin: 0 auto; padding: 0;max-width: 1200px; background: #f5f6f8;font-size: 14px;max-height: 800px;-webkit-font-smoothing: antialiased;-moz-osx-font-smoothing: grayscale;}
      .m-header{position: relative; height: 0.8rem;line-height: 1rem; font-size:0.18rem;border-bottom:1px solid #EBEBEB; text-align: center;color: #2C2C2C;letter-spacing: 0;background: #fff;background-color: #7FFFD4;}
      .m-return {position: absolute;display: inline-block;top:0.13rem;left: 0.12rem;width: 0.14rem; height: 0.3rem; background-image: url(http://c2.cgyouxi.com/website/mobile/img/arrow-l.png?v=20180122);background-size: 100% 100%;}
      .m-tab{position: relative;padding: 0.40rem 0 0; width: 100%;background: #fff;background-color:#FFF0F5;}
      .m-tab a{position: absolute; display: inline-block;width: 50%; font-size: 0.15rem;color: #2C2C2C;letter-spacing: 0;line-height: 0.45rem;text-align: center;text-decoration: none;}
      .m-tab a:first-child{top: 0;left: 0; }
      .m-tab a.m-coupons{top:0; right:0;}
      .m-tab a.active{border-bottom: 2px solid #FFAC28;}
      .m-division{height: 1px; background:#EBEBEB;} 
      .m-view1{background: #fff;height: 400px;}    
      .m-view2{background: #fff;height: 400px;}       
      #login_click{ margin-top:32px; height:40px;}
      
      @media screen and (min-width: 360px) {  
        html {
          font-size: 100px!important;
        }
      }
 
    </style>   
    <script type="text/javascript">  
      document.documentElement.style.fontSize = document.documentElement.clientWidth / 3.6 + 'px';
      (function (doc, win) {
          var docEl = doc.documentElement;
          var resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize';
 
          var recalc = function () {
              var clientWidth = docEl.clientWidth;
              if (!clientWidth) {
                  return;
              }
              docEl.style.fontSize = 100 * (clientWidth / 360) + 'px';
          };
 
          if (!doc.addEventListener) {
              return;
          }
          win.addEventListener(resizeEvt, recalc, false);
          doc.addEventListener('DOMContentLoaded', recalc, false);
      })(document, window);     
    </script>        
</head>          
<body>  
  <div class="m-header" id="m-header">
  <div class="m-return" id="m-return"></div>
  <h2>图书借阅系统</h2>
  </div>
  <div class="m-tab">
    <a href="javascript:void(0);" id="m-my-invite" class="active">读者登录</a>
    <a href="javascript:void(0);" id="m-my-coupons" class="m-coupons">管理员登录</a>
    <div class="m-division"></div>  
  </div>  
  
  <!-- 登录传值 -->
  <div id="m-view1" class="m-view1">
   <br> <br><br> <br><br> <br>
    <form action="${pageContext.request.contextPath}/readerlogin.do" method="post">
  <table align="center" style="font-size:22px;border-collapse:separate;border-spacing:10px;">
      <tr><th>账号：</th>
          <td><input type="text" name="userid"></td>
      </tr>
      <tr><th>密码：</th>
          <td><input type="password" name="password">
          </td>
       <tr><th></th>
          <td><input type="submit" value="登录" class="login_click">&nbsp;&nbsp;&nbsp;
          <input type="reset" value="重置 " class="login_click"/></td>
      </tr>
   </table>
   <p align="center" style="font-size:16px;">
   还没有账户？快来<a href="${pageContext.request.contextPath}/registerGo.do">注册</a>吧！
   </p>
     <%if(request.getAttribute("loginfail")!=null)
    	{%>
    	<p align="center"><font color="red">账户或密码错误！请重新登录</font></p><%} %>
  </form>
  </div>
  <div id="m-view2" class="m-view2" style="display: none;"> 
  
  <br> <br><br> <br><br> <br>
    <form action="${pageContext.request.contextPath}/adminlogin.do" method="post">
  <table align="center" style="font-size:22px;border-collapse:separate;border-spacing:10px;">
      <tr><th>账号：</th>
          <td><input type="text" name="userid"></td>
      </tr>
      <tr><th>密码：</th>
          <td><input type="password" name="password">
          </td>
       <tr><th></th>
          <td><input type="submit" value="登录">&nbsp;&nbsp;&nbsp;
          <input type="reset" value="重置 "/></td>
      </tr>
   </table>
     <%if(request.getAttribute("loginfail")!=null)
    	{%>
    	<p align="center"><font color="red">账户或密码错误！请重新登录</font></p><%} %>
  </form>
  </div>  
  <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.js"></script>    
  <script type="text/javascript">    
    $('#m-return').on('click', function() {
      window.history.back()
    });  
    var myCoupons = $('#m-my-coupons');
    var myInvite = $('#m-my-invite');
    var view1 = $('#m-view1');
    var view2 = $('#m-view2');  
    $('#m-return').on('click', function() {
      window.history.back()
    });
    myInvite.on('click', function() {
      myCoupons.removeClass('active');
      myInvite.addClass('active');
      view2.hide();
      view1.show(); 
    });
    myCoupons.on('click', function() {
      myInvite.removeClass('active');
      myCoupons.addClass('active');     
      view1.hide();
      view2.show();   
    });   
  </script>    
</body>          
</html>