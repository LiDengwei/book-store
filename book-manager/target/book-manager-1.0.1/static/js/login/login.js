$(function() {
	var parentUrl = window.location.href ;
	var param = parentUrl.split('?')[1] ;
	var hrefUrl = parentUrl.split('?')[0] ;
	var parentUrl = parent.location.pathname.split(';')[0] ;
	if(parentUrl == '/user/index'){
		parent.location.reload() ;
	}
	if(param == 'msg=0'){
		// session 失效配置 刷新父页面
        parent.location.href = hrefUrl ;
	}
	if(param == 'error=true'){
        $.ajax({
            url : '/user/msg' ,
            type : 'post' ,
            contentType: 'application/json;charset=utf-8',
            dataType: 'json',
            success : function(data){
                $('#message').html(data.msg) ;
                $('#message').show();
            },
			error : function(err){
                $('#message').html(err.msg) ;
                $('#message').show();
			}
        })
	}
	$("input[type=submit]").button();
	$(":checkbox").button({
		icons : {
			primary : "ui-icon-locked"
		},
		text : false
	});
	$(document).tooltip({
		position : {
			my : "left",
			at : "right+10"
		}
	});
});