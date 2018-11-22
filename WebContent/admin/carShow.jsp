<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>汽车信息</title>
<link href="css/main.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript">
var tempClassName="";
function tr_mouseover(obj) 
{ 
	tempClassName=obj.className;
	obj.className="list_mouseover";
}
function tr_mouseout(obj)      
{ 
	obj.className=tempClassName;
}      
function CheckAll(obj) 
{
	var checks=document.getElementsByName("chkid");
    for (var i=0;i<checks.length;i++)
	{
	    var e = checks[i];
	    e.checked = obj.checked;
	}
    
}


function serch()
{
   document.info.action="Admin_listCars.action";
   document.info.submit();
}
function del()
{
	var checks=document.getElementsByName("chkid");
    var ids="";
	for (var i=0;i<checks.length;i++)
    {
        var e = checks[i];
		if(e.checked==true)
		{
		  if(ids=="")
		  {
		    ids=ids+e.value;
		  }
		  else
		  {
		    ids=ids+","+e.value;
		  }
		}
    }
    if(ids=="")
    {
       alert("请至少选择一个要删除的汽车！");
       return false;
    }
    if(confirm('确认删除吗!?'))
    {
       document.info.action="Admin_delCars.action?paramsCar.ids="+ids;
       document.info.submit();
    }
    
}
function GoPage()
{
  var pagenum=document.getElementById("goPage").value;
  var patten=/^\d+$/;
  if(!patten.exec(pagenum))
  {
    alert("页码必须为大于0的数字");
    return false;
  }
  document.getElementById("pageNo").value=pagenum;
  document.info.action="Admin_listCars.action";
  document.info.submit();
}
function ChangePage(pagenum)
{
  document.getElementById("pageNo").value=pagenum;
  document.info.action="Admin_listCars.action";
  document.info.submit();
}
</script>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">汽车管理&gt;&gt;汽车查询</span>
</div>
<form name="info" id="info" action="Admin_listCars.action" method="post">
<input type="hidden" name="pageNo" id="pageNo" value="${pageNo}"/>
<table width="95%" align="center" cellpadding="0" cellspacing="0">
  <tr><td colspan="2" height="10px">&nbsp;</td></tr>
  <tr>
    <td width="">汽车列表</td>
    <td width="" align="right">
            汽车品牌：
      <input type="text" id="paramsCar.car_name" name="paramsCar.car_name" value="${paramsCar.car_name}" class="inputstyle"/>&nbsp;
            汽车型号：
      <input type="text" id="paramsCar.car_model" name="paramsCar.car_model" value="${paramsCar.car_model}" class="inputstyle"/>&nbsp;&nbsp;
      <input type="button" value="搜索" onclick="serch();" class="btnstyle"/>&nbsp;
      <input type="button" value="删除" onclick="del();" class="btnstyle"/>
    </td>
  </tr>
  <tr><td colspan="2" height="2px"></td></tr>  
</table>
<table width="95%" align="center" class="table_list" cellpadding="0" cellspacing="0">
   <tr class="listtitle">
     <td width="40" align="center"><input type="checkbox" onclick="CheckAll(this);" style="vertical-align:text-bottom;" title="全选/取消全选"/></td>
     <td width="40" align="center">序号</td>
     <td width="150" align="center">汽车品牌</td>
     <td width="150" align="center">汽车型号</td>
     <td width="" align="center">汽车描述</td>
     <td width="" align="center">操作</td>
   </tr>
   <s:if test="#attr.cars!=null && #attr.cars.size()>0">
   <s:iterator value="#attr.cars" id="car" status="status">
   <tr class="<s:if test='(#status.index + 1)%2==0'>list1</s:if><s:else>list0</s:else>" onmouseover="tr_mouseover(this)" onmouseout="tr_mouseout(this)"> 
     <td width="" align="center"><s:checkbox name="chkid" fieldValue="%{#car.car_id}" cssStyle="vertical-align:text-bottom;"/></td>
     <td width="" align="center"><s:property value="#status.index+#attr.paramsCar.start+1"/></td>
     <td width="" align="center"><s:property value="#car.car_name"/></td>
     <td width="" align="center"><s:property value="#car.car_model"/></td>
     <td width="" align="center"><s:property value="#car.car_desc"/>&nbsp;</td>
     <td width="" align="center">
       <img src="images/edit.png"/>&nbsp;<s:a href="Admin_editCar.action?paramsCar.car_id=%{#car.car_id}">编辑</s:a>
     </td>
   </tr> 
   </s:iterator>
   </s:if>
   <s:else>
   <tr>
     <td height="60" colspan="10" align="center"><b>&lt;不存在汽车信息&gt;</b></td>
   </tr>
   </s:else>
   
</table>
<jsp:include page="page.jsp"></jsp:include>
</form> 
</body>
</html>