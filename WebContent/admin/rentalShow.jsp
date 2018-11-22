<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>汽车租赁信息</title>
<link href="css/main.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
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
   var num = /^\d+(\.\d+)?$/;
   document.info.action="Admin_listRentals.action";
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
       alert("请至少选择一个要删除的汽车租赁！");
       return false;
    }
    if(confirm('确认删除吗!?'))
    {
       document.info.action="Admin_delRentals.action?paramsRental.ids="+ids;
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
  document.info.action="Admin_listRentals.action";
  document.info.submit();
}
function ChangePage(pagenum)
{
  document.getElementById("pageNo").value=pagenum;
  document.info.action="Admin_listRentals.action";
  document.info.submit();
}
</script>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">汽车租赁管理&gt;&gt;汽车租赁查询</span>
</div>
<form name="info" id="info" action="Admin_listRentals.action" method="post">
<input type="hidden" name="pageNo" id="pageNo" value="${pageNo}"/>
<table width="95%" align="center" cellpadding="0" cellspacing="0">
  <tr><td colspan="2" height="10px">&nbsp;</td></tr>
  <tr>
    <td width="">汽车租赁列表</td>
    <td width="" align="right">
            租赁日期：
      <input type="text" style="width:80px;" name="paramsRental.rental_dateMin" value="${paramsRental.rental_dateMin}" 
      		class="inputstyle" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"/>
      &nbsp;-&nbsp;
      <input type="text" style="width:80px;" name="paramsRental.rental_dateMax" value="${paramsRental.rental_dateMax}" 
      		class="inputstyle" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"/>&nbsp;
      <s:if test="#attr.admin.user_type==2">      
            客户姓名：
      <input type="text" style="width:100px;"  name="paramsRental.custom_name" value="${paramsRental.custom_name}" class="inputstyle"/>&nbsp;
      </s:if>      
            当前状态：
      <s:select id="paramsRental.rental_flag" name="paramsRental.rental_flag" list="#{'1':'租赁中', '2':'续租中', '3':'已归还' }" 
   			listKey="key" listValue="value" value="%{#attr.paramsRental.rental_flag}"
   			headerKey="0" headerValue="请选择" cssStyle="width:100px">
      </s:select>&nbsp;&nbsp;
      <input type="button" value="搜索" onclick="serch();" class="btnstyle"/>&nbsp;
      <s:if test="#attr.admin.user_type==2"> 
      <input type="button" value="删除" onclick="del();" class="btnstyle"/>
      </s:if>
    </td>
  </tr>
  <tr><td colspan="2" height="2px"></td></tr>  
</table>
<table width="95%" align="center" class="table_list" cellpadding="0" cellspacing="0">
   <tr class="listtitle">
   	 <s:if test="#attr.admin.user_type==2"> 
     <td width="40" align="center"><input type="checkbox" onclick="CheckAll(this);" style="vertical-align:text-bottom;" title="全选/取消全选"/></td>
     </s:if>
     <td width="40" align="center">序号</td>
     <td width="" align="center">客户姓名</td>
     <td width="" align="center">联系电话</td>
     <td width="" align="center">汽车品牌</td>
     <td width="" align="center">汽车型号</td>
     <td width="" align="center">租赁数量</td>
     <td width="" align="center">租赁日期起至</td>
     <td width="" align="center">租赁费用</td>
     <td width="" align="center">添加日期</td>
     <td width="" align="center">当前状态</td>
     <s:if test="#attr.admin.user_type==2"> 
     <td width="" align="center">操作</td>
     </s:if>
   </tr>
   <s:if test="#attr.rentals!=null && #attr.rentals.size()>0">
   <s:iterator value="#attr.rentals" id="rental" status="status">
   <tr class="<s:if test='(#status.index + 1)%2==0'>list1</s:if><s:else>list0</s:else>" onmouseover="tr_mouseover(this)" onmouseout="tr_mouseout(this)"> 
     <s:if test="#attr.admin.user_type==2"> 
     <td width="" align="center"><s:checkbox name="chkid" fieldValue="%{#rental.rental_id}" cssStyle="vertical-align:text-bottom;"/></td>
     </s:if>
     <td width="" align="center"><s:property value="#status.index+#attr.paramsRental.start+1"/></td>
     <td width="" align="center"><s:property value="#rental.custom_name"/></td>
     <td width="" align="center"><s:property value="#rental.custom_phone"/></td>
     <td width="" align="center"><s:property value="#rental.car_name"/></td>
     <td width="" align="center"><s:property value="#rental.car_model"/></td>
     <td width="" align="center"><s:property value="#rental.rental_count"/></td>
     <td width="" align="center"><s:property value="#rental.rental_date1"/> ~ <s:property value="#rental.rental_date2"/></td>
     <td width="" align="center">￥<s:property value="#rental.rental_money"/></td>
     <td width="" align="center"><s:property value="#rental.apply_date.substring(0,10)"/></td>  
     <td width="" align="center"><s:property value="#rental.rental_flagDesc"/></td>  
     <s:if test="#attr.admin.user_type==2"> 
     <td width="" align="center">&nbsp;
       <s:if test="#rental.rental_flag!=3">
       	<s:a href="Admin_editRental.action?paramsRental.rental_id=%{#rental.rental_id}">编辑</s:a>&nbsp;
       	<s:a href="Admin_returnRental.action?paramsRental.rental_id=%{#rental.rental_id}">归还</s:a>&nbsp;
       </s:if>
       <s:if test="#rental.rental_flag==1">
       	<s:a href="Admin_extendRental.action?paramsRental.rental_id=%{#rental.rental_id}">续租</s:a>
       </s:if>
     </td>
     </s:if>
   </tr> 
   </s:iterator>
   </s:if>
   <s:else>
   <tr>
     <td height="60" colspan="12" align="center"><b>&lt;不存在汽车租赁信息&gt;</b></td>
   </tr>
   </s:else>
   
</table>
<jsp:include page="page.jsp"></jsp:include>
</form> 
</body>
</html>