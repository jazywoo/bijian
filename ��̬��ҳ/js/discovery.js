// JavaScript Document

$(document).ready(function(){
    toggleOwnSubMenu();//二级菜单
    togglePhotoInfo();//显示鼠标移到图标上显示用户信息面板
	toggleCommentCanvas();//显示评论等面板
	appendSentenceTitle();//显示一些元素title信息
	showLabelHotGraph();////显示发现也标签的活跃度的柱状图
});

//显示发现也标签的活跃度的柱状图，以及title（tip）
function showLabelHotGraph(){
	var labelHotContainers=$(".labelHotContainer");
	$.each(labelHotContainers,function(index,value){
		 var container=$(value);
		 var labelHotValues=container.find(".labelHotValue");
		 $.each(labelHotValues,function(index2,value2){
		     var hotValue=$(value2);//遍历所有的柱子，然后加上tip和高度显示
		     var ems=hotValue.find("em");
			 var height=$(ems[0]).text();
			 var count=$(ems[1]).text();
			 var date=$(ems[2]).text();
			 var tip=date+"\n该标签被使用了"+count+"次";
			 hotValue.css("height",height);
		     hotValue.attr("title",tip);
         })
    });
	
	
}
