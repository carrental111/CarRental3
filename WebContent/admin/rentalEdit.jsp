<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:if test="#attr.rental!=null && #attr.rental.rental_id!=0">编辑</s:if><s:else>添加</s:else>汽车租赁信息</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<script language="javascript" type="text/javascript">
$(document).ready(function(){
	
	 var num = /^\d+?$/;
	 var num2 = /^\d+(\.\d+)?$/;
	 $("#addBtn").bind('click',function(){
		if($("#paramsRental\\.custom_id").val()=='0'){
			alert('客户姓名不能为空');
			return;
		}
		if($("#paramsRental\\.car_id").val()=='0'){
			alert('租赁汽车不能为空');
			return;
		}
		if($("#paramsRental\\.rental_date1").val()=='' || $("#paramsRental\\.rental_date2").val()==''){
			alert('租赁日期起止不能为空');
			return;
		}
		if(!num.exec($("#paramsRental\\.rental_count").val())){
			alert('租赁数量必须为数字');
			return;
		}
		if(!num2.exec($("#paramsRental\\.rental_money").val())){
			alert('租赁费用必须为数字');
			return;
		}
		$("#paramsRental\\.rental_id").val(0);
		$("#info").attr('action','Admin_addRental.action').submit();
		 
	 });
	 
	 $("#editBtn").bind('click',function(){
		 if($("#paramsRental\\.custom_id").val()=='0'){
				alert('客户姓名不能为空');
				return;
			}
			if($("#paramsRental\\.car_id").val()=='0'){
				alert('租赁汽车不能为空');
				return;
			}
			if($("#paramsRental\\.rental_date1").val()=='' || $("#paramsRental\\.rental_date2").val()==''){
				alert('租赁日期起止不能为空');
				return;
			}
			if(!num.exec($("#paramsRental\\.rental_count").val())){
				alert('租赁数量必须为数字');
				return;
			}
			if(!num2.exec($("#paramsRental\\.rental_money").val())){
				alert('租赁费用必须为数字');
				return;
			}
			$("#info").attr('action','Admin_saveRental.action').submit();
			 
	});
	
});
</script>
<style type="text/css">
</style>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">汽车租赁管理&gt;&gt;<s:if test="#attr.rental!=null && #attr.rental.rental_id!=0">编辑</s:if><s:else>添加</s:else>汽车租赁</span>
</div>
<form id="info" name="info" action="Admin_addRental.action" method="post">   
<s:hidden id="paramsRental.rental_id" name="paramsRental.rental_id" value="%{#attr.rental.rental_id}" /> 
<table width="800" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;margin-bottom:10px;">
  <tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%"> 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle"><s:if test="#attr.rental!=null && #attr.rental.rental_id!=0">编辑</s:if><s:else>添加</s:else>汽车租赁</TD>
              <TD class="edittitleright">&nbsp;</TD>
            </TR>
        </TABLE>
     </td>
   </tr>
   <tr>
     <td height="1" bgcolor="#8f8f8f"></td>
   </tr>
   <tr>
     <td >
     <table width="100%" align="center" cellpadding="1" cellspacing="1" class="editbody">
       <tr>
          <td width="30%" align="right" style="padding-right:5px"><font color="red">*</font> 客户姓名：</td>
          <td width="70%">
          	<s:select id="paramsRental.custom_id" name="paramsRental.custom_id" list="#attr.customs" 
          			listKey="custom_id" listValue="custom_name" value="%{#attr.rental.custom_id}"
          			headerKey="0" headerValue="请选择" cssStyle="width:155px">
          	</s:select>
          </td>
        </tr>
        <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font> 租赁汽车：</td>
          <td>
          	<s:select id="paramsRental.car_id" name="paramsRental.car_id" list="#attr.cars" 
          			listKey="car_id" listValue="%{car_name+' '+car_model}" value="%{#attr.rental.car_id}"
          			headerKey="0" headerValue="请选择" cssStyle="width:155px">
          	</s:select>
          </td>
        </tr>
       <tr>
          <td  align="right" style="padding-right:5px"><font color="red">*</font> 租赁日期起：</td>
          <td>
          	<s:textfield name="paramsRental.rental_date1" id="paramsRental.rental_date1" 
          			value="%{#attr.rental!=null?#attr.rental.rental_date1.substring(0,10):''}" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"/>
          </td>
        </tr> 
        <tr>
          <td  align="right" style="padding-right:5px"><font color="red">*</font> 租赁日期止：</td>
          <td>
          	<s:textfield name="paramsRental.rental_date2" id="paramsRental.rental_date2" 
          			value="%{#attr.rental!=null?#attr.rental.rental_date2.substring(0,10):''}" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"/>
          </td>
        </tr> 
        <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font> 租赁数量：</td>
          <td>
          	<s:textfield name="paramsRental.rental_count" id="paramsRental.rental_count" value="%{#attr.rental.rental_count}" />
          </td>
        </tr> 
        <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font> 租赁费用：</td>
          <td>
          	<s:textfield name="paramsRental.rental_money" id="paramsRental.rental_money" value="%{#attr.rental.rental_money}" />
          </td>
        </tr>  
     </table>
     </td>
   </tr>  
   <tr>
     <td>
       <table width="100%" align="center" cellpadding="0" cellspacing="0" class="editbody">
        <tr class="editbody">
          <td align="center" height="30">
          	<s:if test="#attr.rental!=null && #attr.rental.rental_id!=0">
          	<input type="button" id="editBtn" Class="btnstyle" value="编 辑"/> 
          	</s:if>
          	<s:else>
          	<input type="button" id="addBtn" Class="btnstyle" value="添 加" />
          	</s:else>
            &nbsp;<label style="color:red">${tip}</label>
          </td>
        </tr>
      </table>
     </td>
   </tr>
</table>
</form>
</body>
</html>