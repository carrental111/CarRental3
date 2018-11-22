<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>汽车租赁续租</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<script language="javascript" type="text/javascript">
$(document).ready(function(){
	
	 var num = /^\d+?$/;
	 var num2 = /^\d+(\.\d+)?$/;
	 $("#editBtn").bind('click',function(){
			if($("#paramsRental\\.rental_date2").val()==''){
				alert('续租日期止不能为空');
				return;
			}
			if(!num2.exec($("#paramsRental\\.rental_money").val())){
				alert('租赁费用必须为数字');
				return;
			}
			$("#info").attr('action','Admin_saveExtendRental.action').submit();
			 
	});
	
});
</script>
<style type="text/css">
</style>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">汽车租赁管理&gt;&gt;汽车租赁续租</span>
</div>
<form id="info" name="info" action="Admin_addRental.action" method="post">   
<s:hidden id="paramsRental.rental_id" name="paramsRental.rental_id" value="%{#attr.rental.rental_id}" /> 
<table width="800" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;margin-bottom:10px;">
  <tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%"> 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle">汽车租赁续租</TD>
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
          	<s:property value="#attr.rental.custom_name"/>
          </td>
        </tr>
        <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font> 租赁汽车：</td>
          <td>
          	 <s:property value="#attr.rental.car_name"/> <s:property value="#attr.rental.car_model"/>
          </td>
        </tr>
        <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font> 租赁数量：</td>
          <td>
          	<s:property value="#attr.rental.rental_count"/>
          </td>
        </tr> 
       <tr>
          <td  align="right" style="padding-right:5px"><font color="red">*</font> 租赁日期起：</td>
          <td>
          	<s:property value="#attr.rental.rental_date1"/> ~ <s:property value="#attr.rental.rental_date2"/>
          </td>
        </tr> 
        <tr>
          <td  align="right" style="padding-right:5px"><font color="red">*</font> 续租日期止：</td>
          <td>
          	<s:textfield name="paramsRental.rental_date2" id="paramsRental.rental_date2" 
          			value="" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"/>
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
          <input type="button" id="editBtn" Class="btnstyle" value="续 租"/> 
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