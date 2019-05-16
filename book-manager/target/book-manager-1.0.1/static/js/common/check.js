//by suke 2007.2.13

// 确认操作
function ifconfirm() {
	if (!confirm("\u662f\u5426\u786e\u5b9a\u8be5\u64cd\u4f5c\uff1f")) {
		return false;
	} else {
		return true;
	}
}


// IP验证 1-232.0-255.0-255.0-255
function ipFormat(id, msg) {
	var tag = document.getElementById(id);
	if (tag.value == "") {
		return true;
	} else {
		if (!/^([1-9]|[1-9]\d|1\d{2}|2[0-1]\d|23[0-2])(\.(\d|[1-9]\d|1\d{2}|2[0-4]\d|25[0-5])){3}$/.test(tag.value)) {
			alert(msg);
			try {
				tag.focus();
			}
			catch (e) {
			}
			return false;
		} else {
			return true;
		}
	}
}

// 是否为空
function ifNull(id, msg) {
	var tag = document.getElementById(id);
	if (tag.value == "") {
		alert(msg);
		try {
			tag.focus();
		}
		catch (e) {
		}
		return false;
	} else {
		return true;
	}
}

//是否为数字
function ifNums(id, msg) {
	var tag = document.getElementById(id);
	if (tag.value == "") {
		return true;
	} else {
		if (!/^\d+$/.test(tag.value)) {
			alert(msg);
			try {
				tag.focus();
			}
			catch (e) {
			}
			return false;
		} else {
			return true;
		}
	}
}

// checkbox判断
function checkbox(name, msg) {
	var cs = document.getElementsByName(name);
	var j = 0;
	for (var i = 0; i < cs.length; i++) {
		if (cs[i].checked == true) {
			j++;
		}
	}
	if (j > 0) {
		return true;
	} else {
		alert(msg);
		return false;
	}
}

//比较大小 method=0表示 id1<id2时return false 
function compare(id1, id2, method, msg) {
	var tag1 = document.getElementById(id1);
	var tag2 = document.getElementById(id2);
	switch (method) {
	  case 0:
		if (parseInt(tag1.value) < parseInt(tag2.value)) {
			alert(msg);
			tag2.focus();
			return false;
		} else {
			return true;
		}
		break;
	  case 1:
		if (parseInt(tag1.value) > parseInt(tag2.value)) {
			alert(msg);
			tag2.focus();
			return false;
		} else {
			return true;
		}
		break;
	  case 2:
		if (parseInt(tag1.value) = parseInt(tag2.value)) {
			alert(msg);
			tag2.focus();
			return false;
		} else {
			return true;
		}
		break;
	}
}

// 移动电话
function mobileFormat(id, msg) {
	var tag = document.getElementById(id);
	if (tag.value == "") {
		return true;
	} else {
		if (!/^((134[0-8])|(13[5-9]\d)|(159\d)|(158\d))\d{7}$/.test(tag.value)) {
			alert(msg);
			try {
				tag.focus();
			}
			catch (e) {
			}
			return false;
		} else {
			return true;
		}
	}
}

// 日期类型
function dateFormat(id, msg) {
	var tag = document.getElementById(id);
	if (tag.value == "") {
		return true;
	} else {
		if (!/^(1|2){1}(\d){3}-(\d){1,2}-(\d){1,2}$/.test(tag.value)) {
			alert(msg);
			try {
				tag.focus();
			}
			catch (e) {
			}
			return false;
		} else {
			return true;
		}
	}
}

//URL地址
function urlFormat(id, msg) {
	var tag = document.getElementById(id);
	if (tag.value == "") {
		return true;
	} else {
		if (!/^(H|h){1}(T|t){2}(p|P){1}:\/\/.*$/.test(tag.value)) {
			alert(msg);
			try {
				tag.focus();
			}
			catch (e) {
			}
			return false;
		} else {
			return true;
		}
	}
}

//email地址
function emailFormat(id, msg) {
	var tag = document.getElementById(id);
	if (tag.value == "") {
		return true;
	} else {
		if (!/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(tag.value)) {
			alert(msg);
			try {
				tag.focus();
			}
			catch (e) {
			}
			return false;
		} else {
			return true;
		}
	}
}

//电话号码
function telFormat(id, msg) {
	var tag = document.getElementById(id);
	if (tag.value == "") {
		return true;
	} else {
		if (!/^(\d{3,4}-\d{7,8})$/.test(tag.value)) {
			alert(msg);
			try {
				tag.focus();
			}
			catch (e) {
			}
			return false;
		} else {
			return true;
		}
	}
}

// 移动电话或电话号码
function mobileOrTelFormat(id, msg) {
	var tag = document.getElementById(id);
	if (tag.value == "") {
		return true;
	} else {
		if (!/^((134[0-8])|(13[5-9]\d)|(159\d)|(158\d))\d{7}$/.test(tag.value) && !/^(\d{3,4}-\d{7,8})$/.test(tag.value)) {
			alert(msg);
			try {
				tag.focus();
			}
			catch (e) {
			}
			return false;
		} else {
			return true;
		}
	}
}

//邮政编码
function postcodeFormat(id, msg) {
	var tag = document.getElementById(id);
	if (tag.value == "") {
		return true;
	} else {
		if (!/^(\d{6})$/.test(tag.value)) {
			alert(msg);
			try {
				tag.focus();
			}
			catch (e) {
			}
			return false;
		} else {
			return true;
		}
	}
}

//用户名
function badChar(id, msg) {
	var tag = document.getElementById(id);
	if (!/^[\da-z_]{5,20}$/.test(tag.value)) {
		alert(msg);
		try {
			tag.focus();
		}
		catch (e) {
		}
		return false;
	} else {
		return true;
	}
}

//长度
function lengthCheck(id, msg, compare, len) {
	var tag = document.getElementById(id);
	switch (compare) {
	  case 0:
		if (tag.value.length < len) {
			alert(msg);
			try {
				tag.focus();
			}
			catch (e) {
			}
			return false;
		} else {
			return true;
		}
		break;
	  case 1:
		if (tag.value.length > len) {
			alert(msg);
			try {
				tag.focus();
			}
			catch (e) {
			}
			return false;
		} else {
			return true;
		}
		break;
	  case 2:
		if (tag.value.length = len) {
			alert(msg);
			try {
				tag.focus();
			}
			catch (e) {
			}
			return false;
		} else {
			return true;
		}
		break;
	}
}

//剩余字数
function wordsLeft(tid, maxChars, msg, chid) {
	var tag = document.getElementById(tid);
	if (tag.value.length > maxChars) {
		tag.value = tag.value.substring(0, maxChars);
		alert(msg);
	}
	var curr = maxChars - tag.value.length;
	document.getElementById(chid).innerHTML = curr.toString();
}  


//ifEqual
function ifEqual(aid, bid, msg) {
	var a = document.getElementById(aid);
	var b = document.getElementById(bid);
	if (a.value != b.value) {
		alert(msg);
		return false;
	} else {
		return true;
	}
}

//启止日期
function beginTime(bid, eid, msg) {
	var b = document.getElementById(bid);
	var e = document.getElementById(eid);
	bs = b.value.split("-");
	es = e.value.split("-");
	bd = new Date(bs[0], bs[1] - 1, bs[2]);
	ed = new Date(es[0], es[1] - 1, es[2]);
	if (bd.getTime() > ed.getTime()) {
		alert(msg);
		return false;
	} else {
		return true;
	}
}

//判断两个日期的大小，包括时分秒
function timeCompare(bid, eid, msg) {
	var b = document.getElementById(bid);
	var e = document.getElementById(eid);
	if (b.value == "" || e.value == "") {
		return true;
	} else {
		bs = b.value.split(" ");
		es = e.value.split(" ");
		bdate = bs[0].split("-");
		edate = es[0].split("-");
		btime = bs[1].split(":");
		etime = es[1].split(":");
		bd = new Date(bdate[0], bdate[1] - 1, bdate[2], btime[0], btime[1], btime[2]);
		ed = new Date(edate[0], edate[1] - 1, edate[2], etime[0], etime[1], etime[2]);
		if (bd.getTime() > ed.getTime()) {
			alert(msg);
			return false;
		} else {
			return true;
		}
	}
}

//判断两个日期之间 是不是相隔了days天
function daysBetweenDate(bid, eid, days, msg) {
	var b = document.getElementById(bid);
	var e = document.getElementById(eid);
	bs = b.value.split("-");
	es = e.value.split("-");
	bd = new Date(bs[0], bs[1] - 1, bs[2]);
	ed = new Date(es[0], es[1] - 1, es[2]);
	var times= ed.getTime() - bd.getTime(); 
    var day = parseInt(times / (1000 * 60 * 60 * 24)) + 1;
	if(day > days)
	{
	    alert(msg);
		return false;
	}
	else
	{
	    return true;
	}
}


//javascript:check(this,500,0,'dvMsg')
//function check(objTxt, iLimit, iText, showid) {
function check(objTxt, iLimit, iText) {
	try {
		var iInput = getCheckLength(objTxt.value);
		//var objshow = document.all.item(showid);
		if ((iText + iInput) > iLimit) {
			window.event.returnValue = false;
			objTxt.value = objTxt.value.substr(0, getLimitContentLen(iLimit,objTxt.value));
		} else {
		}
		//objshow.innerText = "(最多输入500个字符，已经输入了" + iInput + "个字符，还能输入"+(500-iInput)+"个字符)";
	}
	catch (e) {
	}
}
function setTextMouseEvent(objTxt,iLimit)
{
	if (window.ActiveXObject)
	{
		objTxt.attachEvent("onchange",setCheckText(objTxt,iLimit));
		//objTxt.attachEvent("onkeydown",setCheckText(objTxt,iLimit));
		//objTxt.attachEvent("onkeyup",setCheckText(objTxt,iLimit));
		objTxt.attachEvent("onmouseout",setCheckText(objTxt,iLimit));
		objTxt.attachEvent("onblur",setCheckText(objTxt,iLimit));
	}
	else if(document.getBoxObjectFor)
	{
		objTxt.addEventListener('change',setCheckText(objTxt,iLimit),false);
		//objTxt.addEventListener('onkeydown',setCheckText(objTxt,iLimit), false);
		//objTxt.addEventListener('onkeyup',setCheckText(objTxt,iLimit), false);
		objTxt.addEventListener('mouseout',setCheckText(objTxt,iLimit),false);
		objTxt.addEventListener('blur',setCheckText(objTxt,iLimit),false);
	}
}

function setCheckText(objTxt, iLimit)
{
	return function()
    {
		try {
			var iInput = getCheckLength(objTxt.value);
			var objText = objTxt.value;
			if(iInput > iLimit) {
				objTxt.value = objText.substr(0,getLimitContentLen(iLimit,objText));
			} else {
			}
		}
		catch (e) 
		{
		}
	}
}
function getLimitContentLen(iLimit,strTemp)
{
  var i,sum = 0,len = 0;
  var enterLen = 0;
  //alert(strTemp.length)
  for(i=0;i<strTemp.length;i++)  
  {
   len++;
   if ((strTemp.charCodeAt(i)>=0) 
   		&& (strTemp.charCodeAt(i)<=255) 
   		&& strTemp.charCodeAt(i) != 10)  
   {
   	 sum += 1;
   	 if(sum == iLimit)
   	 {
   	 	//alert("1######"+len+"#########"+enterLen/2)
   	 	return len - enterLen/2;
   	 }
   }
   else
   {
     sum += 2;
     if(strTemp.charCodeAt(i) == 10)
     {
     	//alert(enterLen);
     	enterLen += 2;
     }
   	 if(sum == iLimit)
   	 {
   	 	//alert("2######"+len+"#########"+enterLen/2)
   	 	return len - enterLen/2;
   	 }
   	 else if(sum > iLimit)
   	 {
   	 	//alert("3######"+len+"#########"+enterLen/2)
   	 	return len - 1 - enterLen/2;
   	 }
   } 
  }
}
function getCheckLength(strTemp)  
{  
  var i,sum;  
  sum=0;  
  for(i=0;i<strTemp.length;i++)  
  {  
  	//alert(strTemp.charCodeAt(i))
   if ((strTemp.charCodeAt(i)>=0) 
   		&& (strTemp.charCodeAt(i)<=255) 
   		&& strTemp.charCodeAt(i) != 10)  
   	{
    	sum=sum+1;
    } 
   	else
   	{
    	sum=sum+2;
    }  
  }
  return sum;  
}
function getStrLength(str)
{
    var sTmpStr,sTmpChar;
    var sOriLenth=0;
    var sReLenth=0;
    
    sTmpStr = new String(str);
    sOriLenth = sTmpStr.length;
    
    for(var i=0; i < sOriLenth; i++)
    {
      sTmpChar = sTmpStr.charAt(i);
      if(escape(sTmpChar).length > 4)//汉字
      {
          sReLenth += 3;
      }
      else if(sTmpChar != '\r')//换行
      {
          sReLenth++;
      }
    }    
    return sReLenth;   
}
//限制特殊键输入长度
function checkKey(objTxt, iLimit, iText) {
	try {
		var iInput = getCheckLength(objTxt.value);       //输入的长度
				//如果输入和已经显示长度不小于限制长度时候，对输入键盘值作处理
		if ((iText + iInput) >= iLimit) {
			var nKeyCode = window.event.keyCode;
			switch (nKeyCode) {
			  case 8://back
				break;
			  case 46://del
				break;
			  case 35://home
				break;
			  case 36://end
				break;
			  case 37://left
				break;
			  case 38://up
				break;
			  case 39://right
				break;
			  case 40://down
				break;
			  default:
				window.event.returnValue = false;
			}
		}
	}
	catch (e) {
	}
}

