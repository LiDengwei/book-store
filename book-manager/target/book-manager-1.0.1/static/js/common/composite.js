/**
 * @author Ivy
 * @date     2010-08-19
 */

$(document).ready(function(){
 	//表格隔行换色，鼠标滑过换色
        $("tbody>tr").mouseover(function(){ 
        $(this).addClass("highlight");}).mouseout(function(){              
        $(this).removeClass("highlight");
	});
     $("tbody>tr:even").addClass("dtr");
	 $("tbody>tr:odd").addClass("str");
	
	 //显示隐藏表格
	 $('.buttonbox p a#rechar').toggle(function(){
	 	$(this).text('Click to hide recharging records of the day').addClass('viewhover');
	 	$('.tableh').fadeIn('slow');
		$('.tableh').addClass('radius shadow');
	 },function(){
		$(this).text('Click to show recharging records of the day').removeClass('viewhover').addClass('view');
	 	$('.tableh').hide();
	 });
	 $('.buttonbox p a#rever').toggle(function(){
	 	$(this).text('Click to hide the latest five reversal records').addClass('viewhover');
	 	$('.tableh').fadeIn('slow');
		$('.tableh').addClass('radius shadow');
	 },function(){
		$(this).text('Click to show the latest five reversal records').removeClass('viewhover').addClass('view');
	 	$('.tableh').hide();
	 });
	 $('.buttonbox p a#transfer').toggle(function(){
	 	$(this).text('Click to hide transfer records').addClass('viewhover');
	 	$('.tableh').fadeIn('slow');
		$('.tableh').addClass('radius shadow');
	 },function(){
		$(this).text('Click to show transfer records').removeClass('viewhover').addClass('view');
	 	$('.tableh').hide();
	 });
	 $('.buttonbox p a#agenr').toggle(function(){
	 	var fromAgencyNum = '';
	 	try{
			if(document.getElementById("transferBack.fromAgencyId").value != ''){
				fromAgencyNum = document.getElementById("transferBack.fromAgencyNum").value;
			}
		}catch(e){}
		
	 	$(this).text('Click to hide transfer records ' + fromAgencyNum).addClass('viewhover');
	 	$('.tableh').fadeIn('slow');
		$('.tableh').addClass('radius shadow');
	 },function(){
	 	var fromAgencyNum = '';
	 	try{
			if(document.getElementById("transferBack.fromAgencyId").value != ''){
				fromAgencyNum = document.getElementById("transferBack.fromAgencyNum").value;
			}
		}catch(e){}
		
		$(this).text('Click to show transfer records ' + fromAgencyNum).removeClass('viewhover').addClass('view');
	 	$('.tableh').hide();
	 });
	 $('.buttonboxNo p a#advanced_settings').toggle(function(){
	 	$(this).text('Hide advanced setting options').addClass('viewhover');
	 	$('.tableh').fadeIn('slow');
	 },function(){
		$(this).text('Show advanced setting options').removeClass('viewhover').addClass('view');
	 	$('.tableh').hide();
	 });
	 
	  /*左边导航隐藏显示
	 if(window.location.href.indexOf("Operators")>=0){
	 	//后台
		var navT="Transaction,SMS,Agents_Management,Report,Reconciliation,System,Bank";
		var dochref=window.location.href;var fileName=dochref.slice(dochref.lastIndexOf("/"));
		//$(".navbox ul").has("li a[href *='"+fileName+"']").show("fast").prev().removeClass("adown");

	 	$(".navbox h2" ).click(function(){
		$(this).toggleClass("adown").parent().parent().find("ul").slideUp("fast").end().find("h2").addClass("adown");
		if ($(this).next().css("display")=="none")
		{$(this).next().slideDown("fast").end().removeClass("adown");}
		else
		{$(this).next().slideUp("fast").end().addClass("adown"); }
		});
	 }
	 else
	 {
	 	 $(".navbox h2" ).click(function(){
		 	$(this).toggleClass("adown").next().slideToggle();
		 });
	 }*/
	 
	 $(".navbox h2" ).click(function(){
		 	$(this).toggleClass("adown").next().slideToggle();
		 });

		
	$("#rightbox a.lefthide").toggle(function(){
		$("#leftbox").hide("fast");
	},function(){
		$("#leftbox").show("fast");
	});
	
	//后台管理menu
	$("#menu ul").hide();
	$("#menu h2").click(function(){
		$(this).toggleClass("adown").next("#menu ul").slideToggle(300).siblings("#menu ul").slideUp("slow");
	});
});

//标签页
window.onload=resetTab;
function getTabTitle(tab) {
var childNodesList=tab.childNodes;
var titleNodes=new Array();
var j=0;
var i;
for (i=0;i<childNodesList.length;i++) {
if(childNodesList[i].nodeName=="H1") {
titleNodes[j]=childNodesList[i];
j++;
}
}
return titleNodes;
}
function getTabContent(tab) {
var childNodesList=tab.childNodes;
var tabContent=new Array();
var j=0;
var i;
for (i=0;i<childNodesList.length;i++) {
if(childNodesList[i].nodeName=="DIV") {
tabContent[j]=childNodesList[i];
j++;
}
}
return tabContent;
}
function resetTab() {
var allDiv=document.getElementsByTagName("div");
var tab=new Array();
var j=0;
var i;
for (i=0;i<allDiv.length;i++) {
if(allDiv[i].className=="tabs") {
tab[j]=allDiv[i];
j++;
}
}
var tabTitle,tabContent;
for(i=0;i<tab.length;i++) {
tabTitle=getTabTitle(tab[i]);
if(tabTitle[0]){
	tabTitle[0].className="selectTab";
}
tabContent=getTabContent(tab[i]);
if(tabContent[0]){
	tabContent[0].className="selectContent";
}
for (j=1;j<tabTitle.length;j++) {
tabTitle[j].className="unselectTab";
tabContent[j].className="unselectContent";
}
}
}
function changTab(tab) {
var tabTitle,tabContent,i;
if(tab.className!="selectTab") {
tabTitle=getTabTitle(tab.parentNode);
tabContent=getTabContent(tab.parentNode);
for(i=0;i<tabTitle.length;i++) {
if(tabTitle[i].className=="selectTab") {
tabTitle[i].className="unselectTab";
}
if(tabContent[i].className=="selectContent") {
tabContent[i].className="unselectContent";
}
}
tab.className="selectTab";
for(i=0;i<tabTitle.length;i++) {
if(tabTitle[i].className=="selectTab") {
tabContent[i].className="selectContent";
break;
}
}
}
}


BakupBodyFocus=null;
BackupWaitBodyContext=null;
CanCancelWait=true;
var lockDoc = document;

function jscomLockScreenToWaitf(msg)
{
	if (BakupBodyFocus==null && BackupWaitBodyContext==null)
	{
		BakupBodyFocus = lockDoc.body.onfocus+'';
		BackupWaitBodyContext = lockDoc.body.oncontextmenu+'';
		lockDoc.body.onfocus = jscomLockScreenToWaitf;
		lockDoc.body.oncontextmenu = jscomCancelClick;
	}
	var div = lockDoc.all["divLockWaiting"];
	if (div+''=="undefined")
	{
		div = lockDoc.createElement("DIV");
		div.setAttribute("id","divLockWaiting");
		div.className = "WaitBox";
		div.style.padding = 20;
		div.style.paddingLeft = 40;
		div.style.paddingRight = 30;
		div.innerHTML = "<img src='images/loading.gif'/>"+msg;
		lockDoc.body.appendChild(div);
	}
	var x =(lockDoc.body.clientWidth-div.offsetWidth)/2;
	var y = (lockDoc.body.clientHeight-div.offsetHeight)/2;
	div.style.pixelLeft = x;
	div.style.pixelTop = y;
	div.style.visibility = "visible";
	div.setCapture();
}

function jscomUnlockScreenWait()
{
	var div = lockDoc.all["divLockWaiting"];
	if (div+''=="undefined")
		return;
	div.style.visibility = "hidden";
	lockDoc.body.onfocus = BakupBodyFocus;
	lockDoc.body.oncontextmenu = BackupWaitBodyContext;
	BakupBodyFocus = null;
	BackupWaitBodyContext = null;
	lockDoc.releaseCapture();
}

function jscomCancelClick()
{
	var elem = event.srcElement;
	if (CanCancelWait==true && elem.className=='WaitBox'){
		jscomUnlockScreenWait();
		event.cancelBubble = false;
		return false;
	}
	return false;
}