function showError(inputId,errorMsg,visiTag)
	{
		//document.getElementById('img.' + inputId).style.visibility=visiTag;
		var spanObj = document.getElementById('span.' + inputId);
		if(spanObj){
			spanObj.style.visibility=visiTag;
			spanObj.innerHTML=errorMsg;
		}else{
			var pObj = document.getElementById('p.' + inputId);
			if(pObj){
				pObj.style.visibility=visiTag;
				pObj.innerHTML=errorMsg;			
			}
		}
		if(visiTag=='visible')
		{
			var objInput = document.getElementById(inputId);
			if(objInput){
				if(objInput.type=='text' || objInput.type=='password')
				{
					try{
						objInput.setAttribute("class",'textlo redborder');
					}catch(e){}
					try{
						objInput.setAttribute("className",'textlo redborder');
					}catch(e){}			
				}
			}
		}else{
			var objInput = document.getElementById(inputId);
			if(objInput){
				if(objInput.type=='text' || objInput.type=='password')
				{
					try{
						objInput.setAttribute("class",'textlo');
					}catch(e){}
					try{
						objInput.setAttribute("className",'textlo');
					}catch(e){}			
				}
			}		
		}
	}
	
	String.prototype.trim=function()
	{
	 	return this.replace(/^\s+|\s+$/g, "");
	}

	function isNumber(value)
	{
		var isuse = false;
		var vArr = value.match(/^\d+(\.\d{1,})?$/);
		if(vArr==null){
			isuse = false;
		}else{
			isuse = true;
		}	
		return isuse;
	}
	
	function isInt(v)
	{
    	var vArr = v.match(/^[0-9]+$/);
    	if (vArr == null)
    	{
        	return false;
    	}
    	else
    	{
        	return true;
    	}
	}
	
	function isEmpty(id){
		var tag = document.getElementById(id);
		if(!tag)
		{
		    tag = document.getElementsByName(id)[0];
		}
		tag.value = tag.value.trim();
		if (tag.value == "")
		{
			try 
			{
	          tag.select();
	        } 
	        catch (e) {}
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/*
	* Date format
	*/
	Date.prototype.format = function(format)
	{
	    var o =
	    {
	        "M+" : this.getMonth()+1, //month
	        "d+" : this.getDate(),    //day
	        "h+" : this.getHours(),   //hour
	        "m+" : this.getMinutes(), //minute
	        "s+" : this.getSeconds(), //second
	        "q+" : Math.floor((this.getMonth()+3)/3),  //quarter
	        "S" : this.getMilliseconds() //millisecond
	    }
	    if(/(y+)/.test(format))
	    format=format.replace(RegExp.$1,(this.getFullYear()+"").substr(4 - RegExp.$1.length));
	    for(var k in o)
	    if(new RegExp("("+ k +")").test(format))
	    format = format.replace(RegExp.$1,RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));
	    return format;
	}
	
	/**
	* clear tbody
	*/
	function clearTBody(tBodyId){
			var tbodyObj = document.getElementById(tBodyId);    
			if(tbodyObj){
	      		for(var i=tbodyObj.childNodes.length-1; i>=0; i--){
	    			tbodyObj.removeChild(tbodyObj.childNodes[i]);
	  	 		}
	  	 	}
	 }
	 
	 /**
	 * agency password validate
	 */
	function agencyPwdPattern(strPwd)
	{
		/*
		if(strPwd.length<7||strPwd.length>15||!/[0-9]+/.test(strPwd)||!/[a-z]+/.test(strPwd)||!/[A-Z]+/.test(strPwd)||!/[^0-9a-zA-Z]/.test(strPwd)){
			return false;
		}
		return true;
		*/
		
		var patrn = /^[0-9]{1,5}$/;
		if(strPwd!='')
		{
			if(patrn.exec(strPwd)) 
			{
				return true;
			}
			return false;
		}
		return true;
	}
	
	function agencyPwdPattern2(strPwd,minLen,maxLen)
	{
		if(strPwd.length < minLen){
			return false;
		}
		if(strPwd.length > maxLen){
			return false;
		}
		var patrn = /^[0-9]+$/;
		if(strPwd!='')
		{
			if(patrn.exec(strPwd)) 
			{
				return true;
			}
			return false;
		}
		return true;
	}
	/**
	 * sys loginNum validate
	 * @param {Object} strLogin
	 */
	function sysLoginNum(strLogin)
	{
		var patrn = /^([a-zA-Z0-9]|[-_]){1,20}$/;
		if(strLogin!=''){
			if(!patrn.exec(strLogin))
			{
				return false;	
			}
			return true;					
		}
		return false;
	}
	
	/**
	* sys password validate
	*/
	function sysPwdPattern(strPwd)
	{
		/*
			1.Minumum length 7 characters
			2.(1)Lowercase(2)UpperCase(3)numbers(4)Special characters
		*/
		if(strPwd.length<7||strPwd.length>15||!/[0-9]+/.test(strPwd)||!/[a-z]+/.test(strPwd)||!/[A-Z]+/.test(strPwd)||!/[^0-9a-zA-Z]/.test(strPwd)){
			return false;
		}
		return true;
		/*
		var patrn = /^([a-zA-Z0-9]|[-_`~!@#$%^&*()+=|:;,.\\/?]){6,20}$/;
		if(strPwd!='')
		{
			if(patrn.exec(strPwd)) 
			{
				return true;
			}
			return false;
		}
		return true; 
		*/	
	}
	
	/**
	* get the value of the element
	*/
	function getValue(id)
	{
		var tag = document.getElementById(id);
		if(!tag)
		{
		    tag = document.getElementsByName(id)[0];
		}
		return tag.value;
	}
	
	/**
	* set confirm button disable
	*/
	function setButtonConfirmDisable(id){
		var btnConfirm = document.getElementById(id);
		try{
			btnConfirm.setAttribute("class",'but');
		}catch(e){}
		try{
			btnConfirm.setAttribute("className",'but');
		}catch(e){}	
	}
	
	/**
	* set confirm button enable
	*/
	function setButtonConfirmEnable(id){
		var btnConfirm = document.getElementById(id);
		try{
			btnConfirm.setAttribute("class",'but example1');
		}catch(e){}
		try{
			btnConfirm.setAttribute("className",'but example1');
		}catch(e){}	
		$(".example1").colorbox({inline:true, href:"#confirm",scrolling:false});
	}
	
	
	/**
	* set confirm button enable
	*/
	function setButtonConfirmEnable2(id){
		var btnConfirm = document.getElementById(id);
		try{
			btnConfirm.setAttribute("class",'but example1');
		}catch(e){}
		try{
			btnConfirm.setAttribute("className",'but example1');
		}catch(e){}	
		$(".example1").colorbox({inline:true, href:"#confirm2"});
	}
	
	/**
	* clear query conditions
	*/
	function cleanQryConditions()
	{
		var inputs = document.getElementsByTagName("input");
		for(var i = 0; i < inputs.length; i ++)
		{
			if(inputs[i].type == 'text')
			{
				inputs[i].value = "";
			}
			else if(inputs[i].type == 'hidden')
			{
				continue;
			}
		}
		
		var selects = document.getElementsByTagName("select");
		
		for(var j = 0; j < selects.length; j ++)
		{
			selects[j].options[0].selected = true;
		} 
	}
	
	/**
	* judge if it is a email
	*/
	function isEmail(str)
	{
		var email = /(\S)+[@]{1}(\S)+[.]{1}(\w)+/;
		if(!isEmpty(str))
		{
			if(email.test(getValue(str)))
			{
				return true;
			}
			return false;
		}
		return true;
	}
	
	//judge if the element is empty,but don't get the focus
	function isEmpty2(id){
		var tag = document.getElementById(id);
		if(!tag)
		{
		    tag = document.getElementsByName(id)[0];
		}
		tag.value = tag.value.trim();
		if (tag.value == "")
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	function toBreakWord(intLen, id){
		var obj=document.getElementById(id);
		var strContent=obj.innerHTML;
		var strTemp="";
		while(strContent.length>intLen){
			strTemp+=strContent.substr(0,intLen)+"<br>";
			strContent=strContent.substr(intLen,strContent.length);
		}
		strTemp+= strContent;
		obj.innerHTML=strTemp;
	}
	
	function ajaxFormSubmit(){
		var iss = false;
		var result;
		$(document).ready(function() 
		{
		    var options = {
		     type:'post', 
			 cache: false,
			 async: false,
		     //beforeSubmit:  showRequest,  // pre-submit callback 
        	 //success:       showResponse  // post-submit callback 
		     //success:showResponse
		     success: function(result){
		     	//var boardDiv = "<div style='display:none;' id='showMessage'>111</div>";
				//$(window).load(function(){
				//	$(document.body).append(boardDiv);
				//});
			    //$("#showMessage").show();
			    //alert(result);
			    $("#showMessage").html(result);
			 }
		    }; 
		    $('#form1').ajaxSubmit(options); 
		 }
		);
		//setBtnConfirmEnable();
		var isComplete = document.getElementById("complete");
		//alert(isComplete.innerHTML)
		if(isComplete)
		{    
			$("#btnConfirm").colorbox({inline:true, href:"#complete"});
		}else{
			$("#btnConfirm").colorbox({inline:true, href:"#failure"});
		}
	}
	
	/**
	 * chr2Unicode
	 */
	function chr2Unicode(str)
	{
		if ('' != str) 
		{
			var st, t, i;
			st = '';
			for (i = 1; i <= str.length; i++)
			{
				t = str.charCodeAt(i - 1).toString(16);
				if (t.length < 4)
				while(t.length <4)
					t = '0'.concat(t);
				t = t.slice(2, 4).concat(t.slice(0, 2))
				st = st.concat(t);
			}
			return(st.toUpperCase());
		}
		else
		{
			return('');
		}
	}

	/**
	 * unicode2Chr
	 */
	function unicode2Chr(str) 
	{
		if ('' != str)
		{
			var st, t, i
			st = '';
			for (i = 1; i <= str.length/4; i++)
			{
				t = str.slice(4*i-4, 4*i-2);
				t = str.slice(4*i-2, 4*i).concat(t);
				st = st.concat('%u').concat(t);
			}
			st = unescape(st);
			return(st);
		}
		else
			return('');
	}
	
	function uniencode(text)
	{
		text = escape(text.toString()).replace(/\+/g, "%2B");
		var matches = text.match(/(%([0-9A-F]{2}))/gi);
		if (matches)
		{
			for (var matchid = 0; matchid < matches.length; matchid++)
			{
				var code = matches[matchid].substring(1,3);
				if (parseInt(code, 16) >= 128)
				{
					text = text.replace(matches[matchid], '%u00' + code);
				}
			}
		}
		text = text.replace('%25', '%u0025');
		return text;
	}
	
	function removeSpaceChar(obj)
	{
		obj.value = obj.value.replace(/^\s+|\s+$/g,"");
	}
	
	function isRightAgencyNum(agencyNum){
		if(!isNumber(agencyNum)){
			return false;
		}
		 if(!/^[0-9]+$/.test(agencyNum)){
			return false;
	    }
		return true;
	}
	
	function formatNumber(num,exponent) {
		var str = '' + num;
		var num2 = parseFloat(str);
		return num2.toFixed(exponent);
	}
	function formatNumber2(num) {
		return formatNumber(num,2);
	}
	
	/**
	* clear options for select
	*/
	function clearOptions(selectId){
		var objSelect = document.getElementById(selectId);
		var length = objSelect.options.length - 1;
		for(var i = length; i >= 0; i--){
				objSelect.options[i] = null;
		}
	}
	
	/**
	* compare date,date format yyyy-MM
	*/
	function dateCompareOnlyMouth(bid, eid) {
		var b = document.getElementById(bid);
		var e = document.getElementById(eid);
		if (b.value == "" || e.value == "") {
			return true;
		} else {
			var bs = b.value.split("-");
			var es = e.value.split("-");
			var bd = new Date(bs[0], bs[1] - 1, 0, 0, 0, 0);
			var ed = new Date(es[0], es[1] - 1, 0, 0, 0, 0);
			if (bd.getTime() > ed.getTime()) {
				return false;
			} else {
				return true;
			}
		}
	}
	
	/**
	* compare date,date format yyyy-MM-dd 
	*/
	function dateCompare(bid, eid) {
		var b = document.getElementById(bid);
		var e = document.getElementById(eid);
		if (b.value == "" || e.value == "") {
			return true;
		} else {
			var bs = b.value.split("-");
			var es = e.value.split("-");
			var bd = new Date(bs[0], bs[1] - 1, bs[2], 0, 0, 0);
			var ed = new Date(es[0], es[1] - 1, es[2], 0, 0, 0);
			if (bd.getTime() > ed.getTime()) {
				return false;
			} else {
				return true;
			}
		}
	}
	/**
	 * 比较不能超过最大天数
	 * @param {Object} bid
	 * @param {Object} eid
	 * @param {Object} maxDays
	 * @return {TypeName} 
	 */
	function dateCompareMaxDays(bid, eid,maxDays) {
		var b = document.getElementById(bid);
		var e = document.getElementById(eid);
		if (b.value == "" || e.value == "") {
			return true;
		} else {
			var bs = b.value.split("-");
			var es = e.value.split("-");
			var bd = new Date(bs[0], bs[1] - 1, bs[2], 0, 0, 0);
			var ed = new Date(es[0], es[1] - 1, es[2], 0, 0, 0);
			if (ed.getTime() - bd.getTime() > (maxDays-1) * 24*60*60*1000) {
				return false;
			} else {
				return true;
			}
		}
	}
	/**
	* compare date,date format yyyy-MM-dd
	* isday 是否包括当前日期
	*/
	function nowDateCompare(bid,isday) {
		var b = document.getElementById(bid);
		var d = new Date();	
		var s =d.getYear()+"-"+(d.getMonth()+1)+"-"+d.getDate();
		if (b.value == "") {
			return true;
		} else {
			var bs = b.value.split("-");
			var es = s.split("-");
			var bd = new Date(bs[0], bs[1] - 1, bs[2], 0, 0, 0);
			var ed = new Date(es[0], es[1] - 1, es[2], 0, 0, 0);
			if(isday){
				if (bd.getTime() >= ed.getTime()) {
					return false;
				} else {
					return true;
				}
			}else{
				if (bd.getTime() > ed.getTime()) {
					return false;
				} else {
					return true;
				}
			}
		}
	}

    //查询在线用户数量
    function queryOnlineUserCount(){

        if($("#onlineUserCount").length>0){
            var url ="UserInfoAction!queryOnlineUserCount.action?r="+Math.random();
            $.post(url,function(data){
                $("#onlineUserCount").html(data[0]);
            },'json');
        }
    }


/**
 * 弹出DIV层
 * @param divId:容器ID
 * @return
 */
function show_div_layer(divId,title,width,height,successFun,closeFun){
    var w='auto';
    var h='auto';
    if(width){
        w=width;
    }
    if(height){
        h=height;
    }
    $.layer({
        shade : [0.5 , '#000' , true],
        type : 1,
        area : [w,h],
        title : !title?false:title,
        border : [0],
        page : {dom : '#'+divId},
        close : function(index){
            layer.close(index);
            if(closeFun){
                closeFun();
            }

        },
        success : function(){ //层加载成功后进行的回调
            $("#xubox_layer"+layer.index).css({top:'0px'});
            var h2 = $("#xubox_layer"+layer.index).height();
            var wh = document.documentElement.clientHeight;
            var top = Math.abs(wh-h2)/2;
            $("#xubox_layer"+layer.index).animate({top:'+'+top+'px'},800);

            if(successFun){
                successFun();
            }
        }
    });
}


/**
 * 关闭弹出层
 * @return
 */
function closeLayerAll(){
    if(layer.closeAll){
        layer.closeAll();
    }

}

//弹出输入框，提示消息
function openInputTips(id,message){
    if(id && message){
        var $this = $("#"+id);
        layer.tips(message, $this,{guide:2,time:3,style: ['background-color:red; color:#fff', 'red'],maxWidth:150});
    }
}

//弹出成功提示
function show_success_tips(message,width,height,fun){
    var w='250px';
    var h='150px';
    if(width){
        w=width;
    }
    if(height){
        h=height;
    }
    $.layer({
        dialog : {msg : message, type : 1,btn : ['Ok','Cancel']},
        title : 'Tips',
        area : [w , h],
        end : function(){
            if(fun){
                fun();
            }
        }
    });
}

//弹出失败提示
function show_failure_tips(message){
    $.layer({
        dialog : {msg : message, type : 2},
        title : 'Error',
        area : ['350px' , '150px'],
        btn : ['Ok','Cancel']
    });
}

//弹出确认窗口
function show_confirm(msg,title,okFun,cancelFun){
    var t='温馨提示';
    if(title){
        t=title;
    }
    layer.confirm(msg,function(){
        if(okFun){
            okFun();
        }

    },t,function(){
        if(cancelFun){
            cancelFun();
        }

        layer.closeAll();
    });
}


    $(function(){
    });