// JavaScript Document

//切换显示header菜单显示与消失
function toggleOwnSubMenu(){   
	$("#ownNavi").bind("mouseenter",function (){
            $("#ownTip").css("display","block");
            $("#ownTip").onfocus();
    });
	$(document).bind("click",function(event){  
		  var e = event || window.event;   
		  var elem = e.srcElement||e.target;   
		  while(elem){   
			  if(elem.id == "ownTip"){   
				  return;   
			  }   
			  elem = elem.parentNode;        
		  }   
		 $("#ownTip").hide();  
   }); 
}
//移动到photo上显示用户信息
function togglePhotoInfo(){
	$(".photoInfo").bind("mouseenter",function(event){
			  var container=$(event.currentTarget);
			  //alert(container.offset().left);
			  var x=container.offset().left;
			  var y=container.offset().top+container.height();
			  $("#userInfoTip").css("left",x-30);
			  $("#userInfoTip").css("top",y+10);
			  $("#userInfoTip").css("display","block");								   
											   
	});
	$(".photoInfo").bind("mouseleave",function(e){
			  $("#userInfoTip").css("display","none");								   
											   
	});
	
}
//切换显示隐藏 评论、转发、喜欢面板
function toggleCommentCanvas(){//显示评论，转发, 喜欢
	$(".sentenceContainer .extractBtn").bind("click",function(){
		 var btn=$(this);	
		 var isSelect=btn.attr("isSelect");
		 var isExpend=btn.parent().attr("expend");
		 var sentenceID=btn.attr("sentenceID");
		 var extractContainer=$(".extractContainer[sentenceID="+sentenceID+"]");
		 
		 if(isSelect=="true"){ //是已经选中,则收缩面板
		     toggleImgBtn(btn,"unSelect");//取消选中按钮
		     hideExtractContainer(extractContainer);//收缩
		     return;	 
		 }else{   //若点击其他按钮，切换到其他的按钮
			 var siblinSelectedBtns=btn.siblings(".extractBtn[isSelect='true']");
			 toggleImgBtn(siblinSelectedBtns,"unSelect");//取消选中按钮
			 toggleImgBtn(btn,"select");//选中该按钮
			 toggleCommentContainer(btn,extractContainer,sentenceID,isExpend);	 //展开
		 }
		 	 
    });
	$(".sentenceContainer .extractBtn").bind("mouseover",function(){
		 var btn=$(this);	
		 var isSelect=btn.attr("isSelect");
		 if(isSelect=="true"){ //是已经展开的
		     return;	 
		 }
		 var label= btn.children(".label");
		 var icon=btn.children(".icon");
		 label.addClass("extractBtnSelect");	
		 icon.addClass("extractBtnSelect");								 
    });
	$(".sentenceContainer .extractBtn").bind("mouseleave",function(){
		 var btn=$(this);	
		 var isSelect=btn.attr("isSelect");
		 if(isSelect=="true"){ //是已经展开的
		     return;	 
		 }
		 var label= btn.children(".label");
		 var icon=btn.children(".icon");
		 label.removeClass("extractBtnSelect");	
		 icon.removeClass("extractBtnSelect");									 
    });
	$(".packUp").bind("click",function(){//收起按钮
		var extractContainer=$(this).parents(".extractContainer");
		var extractBtns=extractContainer.parent().find(".extractBtn");
		toggleImgBtn(extractBtns,"unSelect");//取消选中按钮
		hideExtractContainer(extractContainer);
	});
	
	function toggleCommentContainer(btn,extractContainer,sentenceID,isExpend){
		 var subContainer;
		 var extracImg=btn.parent().find(".extracImg");//展开指示图标
		 if(btn.attr("class").indexOf("btn1")!=-1){   //评论
			 var subContainer=extractContainer.children(".commentContainer");
			 extracImg.css("left","15px");
		 }else if(btn.attr("class").indexOf("btn2")!=-1){  //转发
			 var subContainer=extractContainer.children(".forwardingContainer");
			 extracImg.css("left","77px");
		 }else if(btn.attr("class").indexOf("btn3")!=-1){  //喜欢
			 var subContainer=extractContainer.children(".loveContainer");
			 extracImg.css("left","150px");
		 }	
		 if(isExpend=="true"){ //是已经展开的
		     extractContainer.children().not(".moreBtnContainer").css("display","none");
		     subContainer.css("display","block");	 
		 }else{
			 extractContainer.children().not(".moreBtnContainer").css("display","none");
			 subContainer.css("display","block");
			 showExtractContainer(extractContainer);
			 
		 }
	}
	function toggleImgBtn(btn,type){
		if(type=="select"){
			 btn.attr("isSelect","true");//切换到当前点击为选中
			 var label2= btn.children(".label");
			 var icon2=btn.children(".icon");
			 label2.addClass("extractBtnSelect");	
			 icon2.addClass("extractBtnSelect");
			
		}else{
			 btn.removeAttr("isSelect");
			 var label= btn.children(".label");
			 var icon=btn.children(".icon");
			 label.removeClass("extractBtnSelect");	
			 icon.removeClass("extractBtnSelect");
		}
	}
	function showExtractContainer(extractContainer){
		extractContainer.slideDown();
		extractContainer.parent().find(".extractBtnContainer").attr("expend","true");
		var extracImg=extractContainer.parent().find(".extracImg");
		extracImg.show();
	}
	function hideExtractContainer(extractContainer){
		extractContainer.slideUp();
		extractContainer.parent().find(".extractBtnContainer").removeAttr("expend");
		var extracImg=extractContainer.parent().find(".extracImg");
		extracImg.hide();
	}
	
}
//添加元素的title提示信息
function appendSentenceTitle(){
	var btns=$(".extractBtn");  //评论按钮的
	$.each(btns,function(index,value){
		 var btn=$(value);				 
		 var tipText=btn.find(".label").text();
		 tipText=tipText.replace("(","").replace(")","");
		 if(btn.attr("class").indexOf("btn1")!=-1){
			 tipText=tipText+"个人评论";
		 }else if(btn.attr("class").indexOf("btn2")!=-1){
			 tipText=tipText+"个人转发";
		 }else if(btn.attr("class").indexOf("btn3")!=-1){
			 tipText=tipText+"个人喜欢"
		 }
	     btn.attr("title",tipText);	
	});
	var hotValueLabels=$(".hotValueLabel");//热度标签
	$.each(hotValueLabels,function(index,value){
	     var content="";
		 var hotValue=$(value);	
		 var btns=hotValue.parents(".sentenceContainer").find(".extractBtn");
		 
		 var tipText=$(btns[0]).find(".label").text();
		 tipText=tipText.replace("(","").replace(")","");
		 tipText=tipText+"个人评论"+"\n";
		 content=content+tipText;
		 var tipText=$(btns[1]).find(".label").text();
		 tipText=tipText.replace("(","").replace(")","");
		 tipText=tipText+"个人转发"+"\n";
		 content=content+tipText;
		 var tipText=$(btns[2]).find(".label").text();
		 tipText=tipText.replace("(","").replace(")","");
		 tipText=tipText+"个人喜欢";
		 content=content+tipText;
	     hotValue.attr("title",content);	
	});
}