<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8" />
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <!-- 引入 Bootstrap -->
  <script src="https://cdn.bootcss.com/jquery/2.2.4/jquery.min.js"></script>
  <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
  <!-- 引入 font-awesome -->
  <link href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
  <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script type="application/javascript">
    function change(url,index){
      $(".list-group-item").removeClass("active");
      $(".list-group-item").eq(index).addClass("active");
      $("iframe").attr("src",url);
    }
  </script>
</head>
<body>
<nav class="navbar navbar-inverse" role="navigation">
  <div class="container-fluid">
    <ul class="nav navbar-nav navbar-left">
      <li>
        <a id="hidd" style="font-size: 26px;">宿舍管理系统-系统管理员</a>
      </li>
    </ul>
    <span style="color: #CCCCCC;font-size: 26px;position: relative;top: 5px;"></span>
    <ul class="nav navbar-nav navbar-right">
      <li>
<%--          <c:if test="${dormitoryAdmin.password==123123}">--%>
<%--          <a>我的工资为: <c:out value="${200}"/><p>--%>
<%--          </c:if>--%>

        <a id="welcomeSys">欢迎您,${systemAdmin.name}</a>
        <a id="welcomeDor">欢迎您,${dormitoryAdmin.name}</a>
        <a id="sysType" hidden>${type}</a>
      </li>
      <li>
        <a href="account?method=logout&type=systemAdmin">安全退出</a>
      </li>
    </ul>
  </div>
</nav>
<div class="container-fluid">
  <div class="row">
    <div class="col-sm-2">

      <a id="suGuanGuanLi" href="javascript:void(0)" class="list-group-item active" onclick="change('dormitoryAdminServlet?method=list',0)">
						<span class="" aria-hidden="true">
							<i class="fa fa-user-circle-o fa-fw"></i>
						</span>宿管管理
      </a>
      <a href="javascript:void(0)" class="list-group-item" onclick="change('studentManagerServlet?method=list',1)">
						<span class="" aria-hidden="true">
							<i class="fa fa-user-circle fa-fw"></i>
						</span>学生管理
      </a>
      <a href="javascript:void(0)" class="list-group-item" onclick="change('buildingServlet?method=list',2)">
						<span class="" aria-hidden="true">
							<i class="fa fa-home fa-fw"></i>
						</span>楼宇管理
      </a>
      <a href="javascript:void(0)" class="list-group-item" onclick="change('dormitoryServlet?method=list',3)">
						<span class="" aria-hidden="true">
							<i class="fa fa-bed fa-fw"></i>
						</span>宿舍管理
      </a>
<%--      <a href="javascript:void(0)" class="list-group-item" onclick="change('/moveout?method=list',4)">--%>
<%--						<span class="" aria-hidden="true">--%>
<%--							<i class="fa fa-address-card-o fa-fw"></i>--%>
<%--						</span>学生迁出登记--%>
<%--      </a>--%>
<%--      <a href="javascript:void(0)" class="list-group-item" onclick="change('/moveout?method=record',5)">--%>
<%--						<span class="" aria-hidden="true">--%>
<%--							<i class="fa fa-bookmark fa-fw"></i>--%>
<%--						</span>学生迁出记录--%>
<%--      </a>--%>
<%--      <a href="javascript:void(0)" class="list-group-item" onclick="change('/absent?method=list',6)">--%>
<%--						<span class="" aria-hidden="true">--%>
<%--							<i class="fa fa-bookmark-o fa-fw"></i>--%>
<%--						</span>学生缺寝记录--%>
<%--      </a>--%>

    </div>
    <!--右边内容-->
<%--    <iframe style="width: 81%; height: 600px; border: 0px;" src="/dormitoryAdmin?method=list"></iframe>--%>
    <iframe style="width: 81%; height: 600px; border: 0px;" src="dormitoryAdminServlet?method=list">drgdrgdrgdrg</iframe>
  </div>
</div>
<div class="footer">
  <p class="text-center">
    2022 © DORMS
  </p>
</div>
<script>
   const type =document.getElementById("sysType").innerHTML;
   if (type === "systemAdmin"){  // 1 代表系统管理员  2 代表楼管  控制显示不同页面
       document.getElementById("sysType").style.display="none";
       document.getElementById("welcomeDor").style.display="none";
   }else {
       document.getElementById("suGuanGuanLi").style.display='none';
       document.getElementById("sysType").style.display="none";
       document.getElementById("welcomeSys").style.display="none";
       document.getElementById("hidd").innerHTML="宿舍管理系统-楼寓管理员"
   }

</script>
</body>
</html>
