
/**
 * 判断是不是一个正确的时间 yyyy-MM-dd
 * @param {String} str
 * @return {Date}
 */
Date.isSimpleDate = function(str){
	var   reg   =   /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/;
    var r = str.match(reg);
  	if(r==null)return   null;
  	var   d=   new   Date(r[1], r[3]-1,r[4]);
 	var   newStr=d.getFullYear() + "-";
	newStr+=(((d.getMonth()+1)<10&&r[3].length>1)?('0'+(d.getMonth()+1)):(d.getMonth()+1)) + "-" ;
	newStr+=(((d.getDate())<10 && r[4].length>1)?('0'+d.getDate()):d.getDate());
  	if(newStr==str){
		return d;
	}else{
		return null;
	}
}
/**
 * 判断是不是一个正确的时间类型 yyyy-MM-dd hh
 * @param {Object} str
 */
Date.isDateHH = function(str){
	var   reg   =   /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2}) (\d{1,2})$/;
    var r = str.match(reg);
  	if(r==null)return   null;
  	var   d=   new   Date(r[1], r[3]-1,r[4],r[5]);
 	var   newStr=d.getFullYear() + "-";
	newStr+=(((d.getMonth()+1)<10&&r[3].length>1)?('0'+(d.getMonth()+1)):(d.getMonth()+1)) + "-" ;
	newStr+=(((d.getDate())<10 && r[4].length>1)?('0'+d.getDate()):d.getDate()) + " ";
	newStr+=((d.getHours()<10&&r[5].length>1)?('0'+d.getHours()):d.getHours());
  	if(newStr==str){
		return d;
	}else{
		return null;
	}
}
/**
 * 判断是不是一个正确的时间类型 yyyy-MM-dd hh:mm
 * @param {String} str
 */
Date.isDateHHMM = function(str){
	var   reg   =   /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2}) (\d{1,2}):(\d{1,2})$/;
	var   r   =   str.match(reg);
	if(r==null)return   null;
	var   d=   new   Date(r[1], r[3]-1,r[4],r[5],r[6]);
	var   newStr=d.getFullYear() + "-";
	newStr+=(((d.getMonth()+1)<10&&r[3].length>1)?('0'+(d.getMonth()+1)):(d.getMonth()+1)) + "-" ;
	newStr+=(((d.getDate())<10 && r[4].length>1)?('0'+d.getDate()):d.getDate()) + " ";
	newStr += ((d.getHours()<10&&r[5].length>1)?('0'+d.getHours()):d.getHours())+":";
	newStr += ((d.getMinutes()<10&&r[6].length>1)?('0'+d.getMinutes()):d.getMinutes());
	if(newStr==str){
		return d;
	}else{
		return null;
	}
}
/**
 * 判断是不是一个正确的时间类型 yyyy-MM-dd hh:mm:ss
 * @param {String} str
 */
Date.isDateHHMMSS = function(str){
	var   reg   =   /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/;
	var   r   =   str.match(reg);
	if(r==null)return   null;
	var   d=   new   Date(r[1], r[3]-1,r[4],r[5],r[6],r[7]);
	var   newStr=d.getFullYear() + "-";
	newStr+=(((d.getMonth()+1)<10&&r[3].length>1)?('0'+(d.getMonth()+1)):(d.getMonth()+1)) + "-" ;
	newStr+=(((d.getDate())<10 && r[4].length>1)?('0'+d.getDate()):d.getDate()) + " ";
	newStr += (((d.getHours()<10&&r[5].length>1)?('0'+d.getHours()):d.getHours()))+":";
	newStr += (((d.getMinutes()<10&&r[6].length>1)?('0'+d.getMinutes()):d.getMinutes())) + ":";
	newStr += ((d.getSeconds()<10&&r[7].length>1)?('0'+d.getSeconds()):d.getSeconds());
	if(newStr==str){
		return d;
	}else{
		return null;
	}
}
/***
 * 判断是日期1是不是在日期2后面
 * @param {Date/String} d1
 * @param {Date/String} d2
 * @return {Boolean} true 小于日期2
 */
Date.before = function(d1,d2){
	if((typeof d1 == 'object' && d1.constructor == Date) && (typeof d2 == 'object' && d2.constructor == Date)){
		if(d1<=d2){
			return true;
		}else{
			return false;
		}
	}else if((typeof d1 == 'string' && d1.constructor == String) && (typeof d2 == 'string' && d2.constructor == String)){

		var date1 = Date.isSimpleDate(d1)!=null?Date.isSimpleDate(d1):Date.isDateHH(d1);
		date1 = date1!=null?date1:Date.isDateHHMM(d1);
		date1 = date1!=null?date1:Date.isDateHHMMSS(d1);
		var date2 = Date.isSimpleDate(d2)!=null?Date.isSimpleDate(d2):Date.isDateHH(d2);
		date2 = date2!=null?date2:Date.isDateHHMM(d2)
		date2 = date2!=null?date2:Date.isDateHHMMSS(d2);
		if(date1==null||date2==null){
			alert("日期格式不正确!");
			return false;
		}
		if(date1<=date2){
			return true;
		}else{
			return false;
		}
	}else if((typeof d1 == 'string' && d1.constructor == String) && (typeof d2 == 'object' && d2.constructor == Date)){

		var date1 = Date.isSimpleDate(d1)!=null?Date.isSimpleDate(d1):Date.isDateHH(d1);
		date1 = date1!=null?date1:Date.isDateHHMM(d1);
		date1 = date1!=null?date1:Date.isDateHHMMSS(d1);
		if(date1==null||d2==null){
			alert("日期格式不正确!");
			return false;
		}
		if(date1<=d2){
			return true;
		}else{
			return false;
		}
	}else if((typeof d1 == 'object' && d1.constructor == Date) && (typeof d2 == 'string' && d2.constructor == String)){

		var date2 = Date.isSimpleDate(d2)!=null?Date.isSimpleDate(d2):Date.isDateHH(d2);
		date2 = date2!=null?date2:Date.isDateHHMM(d2)
		date2 = date2!=null?date2:Date.isDateHHMMSS(d2);
		if(d1==null||date2==null){
			alert("日期格式不正确!");
			return false;
		}
		if(d1<=date2){
			return true;
		}else{
			return false;
		}
	}else{
		alert("无法判断输入数据类型！");
		return false;
	}
}
/***
 * 比较d1和d2 的大小 返回 d2-d1
 * @param {Date/String} d1
 * @param {Date/String} d2
 * @return number 0=相等 1=d2大于d1 -1=d2小于d1
 */
Date.compare = function(d1,d2){
	if((typeof d1 == 'object' && d1.constructor == Date) && (typeof d2 == 'object' && d2.constructor == Date)){
		if(d2 > d1){
			return 1;
		}else if(d2 < d1){
			return -1;
		}else{
			return 0;
		}
	}else if((typeof d1 == 'string' && d1.constructor == String) && (typeof d2 == 'string' && d2.constructor == String)){

		var date1 = Date.isSimpleDate(d1)!=null?Date.isSimpleDate(d1):Date.isDateHH(d1);
		date1 = date1!=null?date1:Date.isDateHHMM(d1);
		date1 = date1!=null?date1:Date.isDateHHMMSS(d1);
		var date2 = Date.isSimpleDate(d2)!=null?Date.isSimpleDate(d2):Date.isDateHH(d2);
		date2 = date2!=null?date2:Date.isDateHHMM(d2)
		date2 = date2!=null?date2:Date.isDateHHMMSS(d2);
		if(date1==null||date2==null){
			alert("日期格式不正确!");
			return false;
		}
		if(date2 > date1){
			return 1;
		}else if(date2 < date1){
			return -1;
		}else{
			return 0;
		}
	}else if((typeof d1 == 'string' && d1.constructor == String) && (typeof d2 == 'object' && d2.constructor == Date)){

		var date1 = Date.isSimpleDate(d1)!=null?Date.isSimpleDate(d1):Date.isDateHH(d1);
		date1 = date1!=null?date1:Date.isDateHHMM(d1);
		date1 = date1!=null?date1:Date.isDateHHMMSS(d1);
		if(date1==null||d2==null){
			alert("日期格式不正确!");
			return false;
		}
		if(d2 > date1){
			return 1;
		}else if(d2 < date1){
			return -1;
		}else{
			return 0;
		}
	}else if((typeof d1 == 'object' && d1.constructor == Date) && (typeof d2 == 'string' && d2.constructor == String)){

		var date2 = Date.isSimpleDate(d2)!=null?Date.isSimpleDate(d2):Date.isDateHH(d2);
		date2 = date2!=null?date2:Date.isDateHHMM(d2)
		date2 = date2!=null?date2:Date.isDateHHMMSS(d2);
		if(d1==null||date2==null){
			alert("日期格式不正确!");
			return false;
		}
		if(date2 > d1){
			return 1;
		}else if(date2 < d1){
			return -1;
		}else{
			return 0;
		}
	}else{
		alert("无法判断输入数据类型！");
		return false;
	}
}
