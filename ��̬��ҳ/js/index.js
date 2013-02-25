// JavaScript Document

$(document).ready(function(){
    toggleOwnMenu();
    togglePhotoInfo();
	toggleCommentCanvas();
});
function toggleOwnMenu(){   //切换显示header菜单显示与消失
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
function togglePhotoInfo(){//移动到photo上显示用户信息
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
function toggleCommentCanvas(){//显示评论，转发, 喜欢
	$(".sentenceContainer .extractBtn").bind("click",function(){
		 var btn=$(this);	
		 var sentenceID=btn.attr("sentenceID");
		 var extractContainer=$(".extractContainer[sentenceID="+sentenceID+"]");
		 toggleCommentContainer(btn,extractContainer,sentenceID);
    });
	$(".sentenceContainer .extractBtn").bind("mouseover",function(){
		 var btn=$(this);	
		 btn.css("color","#0099FF");
		 var icon=btn.children(".icon");
		 icon.addClass("extractBtnSelect");								 
    });
	$(".sentenceContainer .extractBtn").bind("mouseleave",function(){
		 var btn=$(this);	
		 btn.css("color","#65ABBB");
		 var icon=btn.children(".icon");
		 icon.removeClass("extractBtnSelect");								 
    });
	
	function toggleCommentContainer(btn,extractContainer,sentenceID){
		 var subContainer;
		 if(btn.attr("class").indexOf("btn1")!=-1){   //评论
			 var subContainer=extractContainer.children(".commentContainer");
		 }else if(btn.attr("class").indexOf("btn2")!=-1){  //转发
			 var subContainer=extractContainer.children(".commentContainer");
		 }else if(btn.attr("class").indexOf("btn3")!=-1){  //喜欢
			 var subContainer=extractContainer.children(".commentContainer");
		 }	
		 if(extractContainer.css("display")=="none"){//未显示，点击后则展开
			 extractContainer.slideDown();
			 subContainer.css("display","block");
		 }else{
			 if(subContainer.css("display")=="block"){//已显示，点击后则关闭
				 extractContainer.slideUp();
				// subContainer.css("display","none");
			 }
		 }
	}
}




















