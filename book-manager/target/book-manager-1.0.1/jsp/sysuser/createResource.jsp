<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: teela
  Date: 2017/3/31
  Time: 18:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <title>编辑资源信息</title>
  <link href="../js/jQueryLigerUI1.3.3/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
  <link href="../js/jQueryLigerUI1.3.3/lib/ligerUI/skins/Gray2014/css/all.css" rel="stylesheet" />
  <script src="../js/jQueryLigerUI1.3.3/lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script>
  <script src="../js/jQueryLigerUI1.3.3/lib/ligerUI/js/ligerui.all.js"></script>
  <script src="../js/jQueryLigerUI1.3.3/lib/jquery-validation/jquery.validate.min.js"></script>
  <script src="../js/jQueryLigerUI1.3.3/lib/jquery-validation/jquery.metadata.js"></script>
  <script src="../js/jQueryLigerUI1.3.3/lib/jquery-validation/messages_cn.js"></script>
  <script src="../js/jQueryLigerUI1.3.3/lib/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
  <script type="text/javascript">
    $(function ()
    {

      $("#form1").ligerForm({
        validate: true
      });
    });


  </script>
  <style type="text/css">
    body
    {
      padding-left:10px;
      font-size:13px;
    }
    h1
    {
      font-size:20px;
      font-family:Verdana;
    }
    h4
    {
      font-size:16px;
      margin-top:25px;
      margin-bottom:10px;
    }

    .description
    {
      padding-bottom:30px;
      font-family:Verdana;
    }
    .description h3
    {
      color:#CC0000;
      font-size:16px;
      margin:0 30px 10px 0px;
      padding:45px 0 8px;
      border-bottom:solid 1px #888;
    }
    td {
      padding: 5px;
    }

  </style>

</head>

<body style="padding:10px">

<h1>编辑资源信息</h1>


<form id="form1" >
  <table>
    <input id="id"  name="id" type="hidden" value="${resource.id}" />
    <tr>
      <td>
        <label for="resourceName">资源名称：</label>
      </td>
      <td>
        <input id="resourceName"  name="resourceName" value="${resource.resourceName}" type="text" class="ui-textbox"  validate="{required:true}"  />
      </td>
    </tr>
    <tr>
      <td>
        <label for="resourceUrl">资源地址：</label>
      </td>
      <td>
        <input id="resourceUrl" name="resourceUrl" value="${resource.resourceUrl}" type="text" class="ui-textbox"  validate="{required:true}" />
      </td>
    </tr>
    <tr>
      <td>
        <label for="resourceType">资源类型：</label>
      </td>
      <td>
        <input id="resourceType" name="resourceType" value="${resource.resourceType}" type="text" class="ui-textbox"  validate="{required:true}" />
      </td>
    </tr>
    <tr>
      <td>
        <label for="resourceDesc">资源描述：</label>
      </td>
      <td>
        <textarea id="resourceDesc" name="resourceDesc" class="ui-textarea"  validate="{required:true}">${resource.resourceDesc}</textarea>
      </td>
    </tr>
    <tr>
      <td>
        <label for="priority">资源优先权：</label>
      </td>
      <td>
        <input id="priority" name="priority" value="${resource.priority}" type="text" class="ui-textbox" validate="{number:true}" />
      </td>
    </tr>
    <%--<tr>
      <td>
        <label for="date1">DatePicker：</label>
      </td>
      <td>
        <input id="date1" name="birthDay" type="text"  class="ui-datepicker" value="2010-10-12"  validate="{required:true}"  />
      </td>
    </tr>
    <tr>
      <td>
        Spinner：
      </td>
      <td>
        <input name="age" type="text" class="ui-spinner" value="22"  validate="{required:true}"   />
      </td>
    </tr>
    <tr>
      <td>
        CheckBox：
      </td>
      <td>
        <input name="married" class="ui-checkbox" type="text"  text="婚否" value="Y"  validate="{required:true}"  />
      </td>
    </tr>--%>
    <tr>
      <td>
        是否被禁用：
      </td>
      <td>
        <select id="enabled" name="enabled" validate="{required:true}">
          <option value="1">正常</option>
          <option value="0">禁用</option>
        </select>
      </td>
    </tr>
    <tr>
      <td>
        是否是超级权限：
      </td>
      <td>
        <select id="isSys" name="isSys" validate="{required:true}">
          <option value="0">不是</option>
          <option value="1">是</option>
        </select>
      </td>
    </tr>

    <%--<tr>
      <td>
        ComboBox：
      </td>
      <td>
        <input name="country1" type="hidden" class="ui-combobox" data-value="de" data-ajaxType="get" data-url="data/countrys.txt"  data-textField="text" data-valueField="id"  validate="{required:true}" />
      </td>
    </tr>
    <tr>
      <td>
        ComboBox(多选)：
      </td>
      <td>
        <input name="country1_mul" type="hidden" class="ui-combobox" data-isMultiSelect="true" data-value="de" data-ajaxType="get" data-url="data/countrys.txt"  data-textField="text" data-valueField="id"  validate="{required:true}" />
      </td>
    </tr>
    <tr>
      <td>
        CheckBoxList：
      </td>
      <td>
        <input name="country2" type="hidden" class="ui-checkboxlist" data-value="cn" data-ajaxType="get" data-url="data/countrys.txt"  data-textField="text" data-valueField="id" />
      </td>
    </tr>
    <tr>
      <td>
        RadioButtonList：
      </td>
      <td>
        <input name="country3" type="hidden" class="ui-radiolist" data-value="fr" data-ajaxType="get" data-url="data/countrys.txt"  data-textField="text" data-valueField="id" />
      </td>
    </tr>
    <tr>
      <td>
        ListBox：
      </td>
      <td>
        <input name="country4" type="hidden" class="ui-listbox" data-value="cn" data-ajaxType="get" data-url="data/countrys.txt"  data-textField="text" data-valueField="id" />
      </td>
    </tr>
    <tr>
      <td>
        ListBox(多选)：
      </td>
      <td>
        <input name="country4_mul" type="hidden" class="ui-listbox" data-isMultiSelect="true"  data-value="cn" data-ajaxType="get" data-url="data/countrys.txt"  data-textField="text" data-valueField="id" />
      </td>
    </tr>
    <tr>--%>
      <td>

      </td>
      <td>
        <%--<input value="valid" type="button" onclick="valid()" />--%>
        <%--<input value="setData" type="button" onclick="setData()" />--%>
        <%--<input value="getData" type="button" onclick="getData()" />--%>
        <input value="提交" type="button" onclick="submitForm()" />

      </td>
    </tr>
  </table>
</form>


</form>
<script type="text/javascript">
  $(function(){
    setData();
  });

  function valid()
  {
    var form = $("#form1");
    return form.valid();
  }
  function getData()
  {
    var form = new liger.get("form1");
    var data = form.getData();
    alert(data);
    alert(JSON.stringify(data));
  }
  function setData()
  {
    <%--var obj = {--%>
      <%--id: "${resource.id}",--%>
      <%--resourceName: "${resource.resourceName}",--%>
      <%--resourceUrl: "${resource.resourceUrl}",--%>
      <%--resourceDesc: "${resource.resourceDesc}",--%>
      <%--resourceType: "${resource.resourceType}",--%>
      <%--priority: "${resource.priority}",--%>
      <%--enabled: "${resource.enabled}",--%>
      <%--isSys: "${resource.isSys}"--%>
    <%--};--%>

    $("#enabled option[value="+"${resource.enabled}"+"]").attr("selected",true);
    $("#isSys option[value="+"${resource.isSys}"+"]").attr("selected",true);
  }


  function submitForm()
  {
    //表单验证
    if(!valid()){
      return false;
    }
    var data = {};
    $("input,select,textarea").each(function ()
    {
      var name = $(this).attr("name");
      if (name && name.indexOf('ligerui') == -1)
      {
        data[name] = this.value;
      }
    });
    var json=JSON.stringify(data);
    $.post("/resource/createResource",{"jsonData":json},function(data){
      if(data.status=="success"){
        $.ligerDialog.success(data.msg);
//        window.location.reload();
      }else if(data.status=="failed"){
        $.ligerDialog.error(data.msg);
      }
    });

  }

</script>



</body>
</html>

