var localPath = getRootPath();
var context = localPath;

/**
 * 获取项目路径
 * @returns
 */
function getRootPath(){
	//获取当前网址，如： http://localhost:8091/sales-assistant-management/activity/index
	var curWwwPath=window.document.location.href;
	//获取主机地址之后的目录，如： sales-assistant-management/activity/index
	var pathName=window.document.location.pathname;
	var pos=curWwwPath.indexOf(pathName);
	//获取主机地址，如： http://localhost:8091
	var localhostPaht=curWwwPath.substring(0,pos);
	//获取带"/"的项目名，如：/sales-assistant-management
	var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
	localPath = localhostPaht + projectName;
	//alert(localPath);
	return localPath;
}

/**
 * 图片上传
 * @param imgId 预览图片的控件ID
 * @param type 模块标示(activity)
 * @param hide_imageUrl (用于存放上传成功后的图片名)
 * @param fileBtnId 文件按钮
 */
function fileUpload(type,imgId,hide_imageUrl,fileBtnId){
	$.ligerDialog.waitting('正在保存中,请稍候...');
	$.ajaxFileUpload({
		//处理文件上传操作的服务器端地址(可以传参数,已亲测可用)
		url: localPath + "/upload/filePath?pre=" + type,
		secureuri: false,                       //是否启用安全提交,默认为false 
		fileElementId: fileBtnId,           //文件选择框的id属性
		dataType: 'json',                       //服务器返回的格式,可以是json或xml等
		success: function(data, status){        //服务器响应成功时的处理函数
			$.ligerDialog.closeWaitting();
			if(data.tip){
				$.ligerDialog.success('图片上传成功!');
				data.message = data.message.replace("&amp;", "&");
				$("#"+imgId+"").attr("src", data.message);
				$("#"+hide_imageUrl+"").val(data.key);
			}else{
				$.ligerDialog.warn(data.message);
			}
		},
		error:function(data, status, e){ //服务器响应失败时的处理函数
			$.ligerDialog.error("图片上传失败，请重试！！");
		}
	});
}

jQuery.mh = {
	getURL : function(path) {
		return context + path;
	},
	myAjax: function(jParam){
		var that = this;
		jDefaultParam = {
			dataType: "html",
			success: function(data){
				$("#div_body").html(data);
			},
			error: function(data){
				$("#div_body").html(data.responseText);
			}
		};
		$.extend(jDefaultParam, jParam);
		$.ajax(jDefaultParam);
	},
	/**
	 * 上传图片
	 * @param {} jParam
	 * fileBtnId: 上传控件file的id
	 * showUrlId: 显示图片的img的id
	 * fileNameId: 保存图片在七牛上的文件名称
	 * mode: 简单图片缩放，比如：?imageView/2/w/200/h/200
	 */
	uploadImage: function(jParam){
		jParam = jParam == null ? {} : jParam;
		var fileBtnId = jParam.fileBtnId == null ? "btn_choose" : jParam.fileBtnId;
		var showUrlId = jParam.showUrlId == null ? "img_view" : jParam.showUrlId;
		var fileNameId = jParam.fileNameId == null ? "hide_imageUrl" : jParam.fileNameId;
		var pre = jParam.pre == null ? "" : jParam.pre;
		var mode = jParam.mode == null ? "" : jParam.mode;
		jParam.successed = jParam.successed == null ? function(){} : jParam.successed;
		$.ligerDialog.waitting("图片正在上传中，请稍后......");
		$.ajaxFileUpload({
			//处理文件上传操作的服务器端地址(可以传参数,已亲测可用)
			url: $.mh.getURL("/upload/uploadImage"),
			data: {
				pre: pre
			},
			secureuri: false,                       	//是否启用安全提交,默认为false 
			fileElementId: fileBtnId,           		//文件选择框的id属性
			dataType: 'json',                      		//服务器返回的格式,可以是json或xml等
			success: function(data, status){        	//服务器响应成功时的处理函数
				$.ligerDialog.closeWaitting();
				if(data.tip){
					$.ligerDialog.closeWaitting();
					data.message = data.message.replace("&amp;", "&");
					$("#" + showUrlId).attr("src", data.message + mode);
					$("#" + fileNameId).val(data.key);
				}else{
					$.ligerDialog.warn(data.message);
				}
				jParam.successed(data);
			},
			error:function(data, status, e){ //服务器响应失败时的处理函数
				$.ligerDialog.closeWaitting();
				console.log(data)
				$.ligerDialog.error("图片上传失败，请重试！！");
			}
		});
	},
	/**
	 * 上传文件
	 * @param {} jParam
	 * fileBtnId: 上传控件file的id
	 * showUrlId: 显示图片的img的id
	 * fileNameId: 保存图片在七牛上的文件名称
	 * mode: 简单图片缩放，比如：?imageView/2/w/200/h/200
	 * data:　删除参数{pre: "文件名前缀", maxSize: "最大上传文件大小"}
	 */
	uploadFile: function(jParam){
		jParam = jParam == null ? {} : jParam;
		var fileBtnId = jParam.fileBtnId == null ? "btn_upload_file" : jParam.fileBtnId;
		var fileNameId = jParam.fileNameId == null ? "hide_upload_file" : jParam.fileNameId;
		jParam.successed = jParam.successed == null ? function(){} : jParam.successed;
		$.ligerDialog.waitting("文件正在上传中，请稍后......");
		$.ajaxFileUpload({
			//处理文件上传操作的服务器端地址(可以传参数,已亲测可用)
			url: $.mh.getURL("/upload/uploadFile"),
			data: jParam.data,
			secureuri: false,                       	//是否启用安全提交,默认为false 
			fileElementId: fileBtnId,           		//文件选择框的id属性
			dataType: 'json',                      		//服务器返回的格式,可以是json或xml等
			success: function(data, status){        	//服务器响应成功时的处理函数
				$.ligerDialog.closeWaitting();
				if(data.tip){
					$.ligerDialog.closeWaitting();
					data.message = data.message.replace("&amp;", "&");
//					$("#" + showUrlId).attr("src", data.message + mode);
					$("#" + fileNameId).val(data.key);
				}else{
					$.ligerDialog.warn(data.message);
				}
				jParam.successed(data);
			},
			error:function(data, status, e){ //服务器响应失败时的处理函数
				$.ligerDialog.closeWaitting();
				console.log(data)
				$.ligerDialog.error("文件上传失败，请重试！！");
			}
		});
	}
}

$(function (){
	//查看详情返回首页
	$("#btn_back_index").die().live("click", function(){
		parent.tab.reload(parent.tab.getSelectedTabItemID());
	});
});
