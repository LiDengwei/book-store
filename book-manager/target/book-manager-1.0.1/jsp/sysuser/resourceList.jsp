<%--
  Created by IntelliJ IDEA.
  User: teela
  Date: 2017/3/30
  Time: 15:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <title></title>
  <link href="../js/jQueryLigerUI1.3.3/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
  <link href="../js/jQueryLigerUI1.3.3/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
  <script src="../js/jQueryLigerUI1.3.3/lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script>
  <script src="../js/jQueryLigerUI1.3.3/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
  <script src="../js/jQueryLigerUI1.3.3/lib/ligerUI/js/plugins/ligerGrid.js" type="text/javascript"></script>
  <script src="../js/jQueryLigerUI1.3.3/lib/ligerUI/js/plugins/ligerToolBar.js" type="text/javascript"></script>
  <script src="../js/jQueryLigerUI1.3.3/lib/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
  <script type="text/javascript">
    var grid = null;
    var CustomersData =
    {Rows:${result.resourceList},Total:${result.resource.totalRows}};
    $(function () {

      grid = $("#maingrid4").ligerGrid({
        columns: [
          { display: '资源ID', name: 'id',type:'hidden' },
          { display: '资源名称', name: 'resourceName' },
          { display: '资源地址', name: 'resourceUrl' },
          { display: '资源描述', name: 'resourceDesc' },
          { display: '资源类型', name: 'resourceType' },
          { display: '资源优先权', name: 'priority' },
          { display: '是否禁用', name: 'enabled',render:function(rowData){
            if(rowData.enabled=='0'){
              return '禁用';
            }else if(rowData.enabled=='1'){
              return '正常';
            }
          } },
          { display: '是否是超级权限', name: 'isSys',render:function(rowData){
            if(rowData.isSys=='0'){
              return '否';
            }else if(rowData.isSys=='1'){
              return '是';
            }
          } }
        ],  pageSize:${result.resource.pageSize},where : f_getWhere(),
        checkbox:true,
        data: CustomersData,
        width: '100%',height:'100%',
        toolbar: {
          items: [{
            text: '新增', click: function () {
              $.ligerDialog.open({ width: 500, height: 500, title: "新增", url: '/resource/preCreateResource?resourceId=0' });
            }, icon: 'add'
          },{
            text: '修改', click: function () {
              var rows = grid.getSelectedRows();
              if(rows==null || rows=="" || rows.length!=1){
                $.ligerDialog.warn("请选择一条记录进行修改!");
                return false;
              }
              var id=rows[0].id;
              $.ligerDialog.open({ width: 500, height: 500, title: "修改", url: '/resource/preCreateResource?resourceId='+id });
            }, icon: 'modify'
          },
            {
              text: '删除', click: function () {
              $.ligerDialog.confirm('确定删除?', function (yes) {
                if (yes) {
                  var _Rows = grid.getSelectedRows();
                  var _keys = [];
                  if(_Rows==null || _Rows=="" || _Rows.length<=0){
                    $.ligerDialog.warn("请至少选择一条记录!");
                    return false;
                  }
                  for (var i = 0; i < _Rows.length; i++) {
                    _keys.push(_Rows[i].id);
                  }
                  $.post("/resource/deleteResources", {"ids":_keys}, function (data) {
                    if (data.status=="success") {
                      $.ligerDialog.success(data.msg);
                    }else if(data.status=="failed"){
                      $.ligerDialog.error(data.msg);
                    }
                  });
                }
              });
            }, icon: 'delete'
            }
          ]
        }
      });


      $("#pageloading").hide();
    });
    function itemclick(item)
    {
      alert(item.text);
    }
    function f_search()
    {
      grid.options.data = $.extend(true, {}, CustomersData);
      grid.loadData(f_getWhere());
    }
    function f_getWhere()
    {
      if (!grid) return null;
      var clause = function (rowdata, rowindex)
      {
        var resourceName = $("#resourceName").val();
        var resourceUrl = $("#resourceUrl").val();
        return (rowdata.resourceName.indexOf(resourceName) > -1 && rowdata.resourceUrl.indexOf(resourceUrl) > -1);
      };
      return clause;
    }
  </script>
</head>
<body style="padding:6px; overflow:hidden;">
<div id="searchbar" style="margin-bottom: 10px;">
  资源名称：<input id="resourceName" type="text" />
  资源地址：<input id="resourceUrl" type="text" />
  <input id="btnOK" type="button" value="查询" onclick="f_search()" />
</div>
<%--<div class="l-loading" style="display:block" id="pageloading"></div>--%>
<%--<a class="l-button" style="width:120px;float:left; margin-left:10px; display:none;" onclick="deleteRow()">删除选择的行</a>--%>


<div class="l-clear"></div>

<div id="maingrid4" style="margin:0; padding:0"></div>


<div style="display:none;">
  <!-- g data total ttt -->
</div>

</body>
</html>

