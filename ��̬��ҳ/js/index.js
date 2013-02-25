// JavaScript Document

$(document).ready(function(){
    toggleOwnMenu();
    togglePhotoInfo();
	toggleCommentCanvas();
});
function toggleOwnMenu(){   //�л���ʾheader�˵���ʾ����ʧ
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
function togglePhotoInfo(){//�ƶ���photo����ʾ�û���Ϣ
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
function toggleCommentCanvas(){//��ʾ���ۣ�ת��, ϲ��
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
		 if(btn.attr("class").indexOf("btn1")!=-1){   //����
			 var subContainer=extractContainer.children(".commentContainer");
		 }else if(btn.attr("class").indexOf("btn2")!=-1){  //ת��
			 var subContainer=extractContainer.children(".commentContainer");
		 }else if(btn.attr("class").indexOf("btn3")!=-1){  //ϲ��
			 var subContainer=extractContainer.children(".commentContainer");
		 }	
		 if(extractContainer.css("display")=="none"){//δ��ʾ���������չ��
			 extractContainer.slideDown();
			 subContainer.css("display","block");
		 }else{
			 if(subContainer.css("display")=="block"){//����ʾ���������ر�
				 extractContainer.slideUp();
				// subContainer.css("display","none");
			 }
		 }
	}
}




















