<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:if test="#attr.custom!=null && #attr.custom.custom_id!=0">编辑</s:if><s:else>添加</s:else>客户信息</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<script language="javascript" type="text/javascript">
$(document).ready(function(){
	 var num = /^\d+$/;
	 $("#addBtn").bind('click',function(){
		if($("#paramsCustom\\.custom_name").val()==''){
			alert('名称不能为空');
			return;
		}
		if($("#paramsCustom\\.custom_phone").val()==''){
			alert('电话不能为空');
			return;
		}
		if($("#paramsCustom\\.custom_address").val()==''){
			alert('住址不能为空');
			return;
		}
		$("#paramsCustom\\.custom_id").val(0);
		$("#info").attr('action','Admin_addCustom.action').submit();
		 
	 });
	 
	 $("#editBtn").bind('click',function(){
		 if($("#paramsCustom\\.custom_name").val()==''){
				alert('名称不能为空');
				return;
			}
		 if($("#paramsCustom\\.custom_phone").val()==''){
				alert('电话不能为空');
				return;
			}
			if($("#paramsCustom\\.custom_address").val()==''){
				alert('住址不能为空');
				return;
			}
			$("#info").attr('action','Admin_saveCustom.action').submit();
			 
	});
	
});
</script>
<style type="text/css">
</style>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">客户管理&gt;&gt;<s:if test="#attr.custom!=null && #attr.custom.custom_id!=0">编辑</s:if><s:else>添加</s:else>客户</span>
</div>
<form id="info" name="info" action="Admin_addCustom.action" method="post">   
<s:hidden id="paramsCustom.custom_id" name="paramsCustom.custom_id" value="%{#attr.custom.custom_id}" /> 
<table width="800" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;margin-bottom:10px;">
  <tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%"> 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle"><s:if test="#attr.custom!=null && #attr.custom.custom_id!=0">编辑</s:if><s:else>添加</s:else>客户</TD>
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
          <td width="20%" align="right" style="padding-right:5px"><font color="red">*</font> 名称：</td>
          <td width="80%" >
          	<s:textfield cssStyle="width:350px" name="paramsCustom.custom_name" id="paramsCustom.custom_name" value="%{#attr.custom.custom_name}"/> 
          </td>
        </tr> 
        <tr>
          <td align="right" style="padding-right:5px">电话：</td>
          <td>
            <s:textfield name="paramsCustom.custom_phone" id="paramsCustom.custom_phone" value="%{#attr.custom.custom_phone}"/>
          </td> 
        </tr>
        <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font> 住址：</td>
          <td>
            <s:textfield cssStyle="width:350px" name="paramsCustom.custom_address" id="paramsCustom.custom_address" value="%{#attr.custom.custom_address}" style="width:300px"/>
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
          	<s:if test="#attr.custom!=null && #attr.custom.custom_id!=0">
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