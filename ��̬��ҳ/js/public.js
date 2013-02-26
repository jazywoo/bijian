// JavaScript Document

//�л���ʾheader�˵���ʾ����ʧ
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
//�ƶ���photo����ʾ�û���Ϣ
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
//�л���ʾ���� ���ۡ�ת����ϲ�����
function toggleCommentCanvas(){//��ʾ���ۣ�ת��, ϲ��
	$(".sentenceContainer .extractBtn").bind("click",function(){
		 var btn=$(this);	
		 var isSelect=btn.attr("isSelect");
		 var isExpend=btn.parent().attr("expend");
		 var sentenceID=btn.attr("sentenceID");
		 var extractContainer=$(".extractContainer[sentenceID="+sentenceID+"]");
		 
		 if(isSelect=="true"){ //���Ѿ�ѡ��,���������
		     toggleImgBtn(btn,"unSelect");//ȡ��ѡ�а�ť
		     hideExtractContainer(extractContainer);//����
		     return;	 
		 }else{   //�����������ť���л��������İ�ť
			 var siblinSelectedBtns=btn.siblings(".extractBtn[isSelect='true']");
			 toggleImgBtn(siblinSelectedBtns,"unSelect");//ȡ��ѡ�а�ť
			 toggleImgBtn(btn,"select");//ѡ�иð�ť
			 toggleCommentContainer(btn,extractContainer,sentenceID,isExpend);	 //չ��
		 }
		 	 
    });
	$(".sentenceContainer .extractBtn").bind("mouseover",function(){
		 var btn=$(this);	
		 var isSelect=btn.attr("isSelect");
		 if(isSelect=="true"){ //���Ѿ�չ����
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
		 if(isSelect=="true"){ //���Ѿ�չ����
		     return;	 
		 }
		 var label= btn.children(".label");
		 var icon=btn.children(".icon");
		 label.removeClass("extractBtnSelect");	
		 icon.removeClass("extractBtnSelect");									 
    });
	$(".packUp").bind("click",function(){//����ť
		var extractContainer=$(this).parents(".extractContainer");
		var extractBtns=extractContainer.parent().find(".extractBtn");
		toggleImgBtn(extractBtns,"unSelect");//ȡ��ѡ�а�ť
		hideExtractContainer(extractContainer);
	});
	
	function toggleCommentContainer(btn,extractContainer,sentenceID,isExpend){
		 var subContainer;
		 var extracImg=btn.parent().find(".extracImg");//չ��ָʾͼ��
		 if(btn.attr("class").indexOf("btn1")!=-1){   //����
			 var subContainer=extractContainer.children(".commentContainer");
			 extracImg.css("left","15px");
		 }else if(btn.attr("class").indexOf("btn2")!=-1){  //ת��
			 var subContainer=extractContainer.children(".forwardingContainer");
			 extracImg.css("left","77px");
		 }else if(btn.attr("class").indexOf("btn3")!=-1){  //ϲ��
			 var subContainer=extractContainer.children(".loveContainer");
			 extracImg.css("left","150px");
		 }	
		 if(isExpend=="true"){ //���Ѿ�չ����
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
			 btn.attr("isSelect","true");//�л�����ǰ���Ϊѡ��
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
//���Ԫ�ص�title��ʾ��Ϣ
function appendSentenceTitle(){
	var btns=$(".extractBtn");  //���۰�ť��
	$.each(btns,function(index,value){
		 var btn=$(value);				 
		 var tipText=btn.find(".label").text();
		 tipText=tipText.replace("(","").replace(")","");
		 if(btn.attr("class").indexOf("btn1")!=-1){
			 tipText=tipText+"��������";
		 }else if(btn.attr("class").indexOf("btn2")!=-1){
			 tipText=tipText+"����ת��";
		 }else if(btn.attr("class").indexOf("btn3")!=-1){
			 tipText=tipText+"����ϲ��"
		 }
	     btn.attr("title",tipText);	
	});
	var hotValueLabels=$(".hotValueLabel");//�ȶȱ�ǩ
	$.each(hotValueLabels,function(index,value){
	     var content="";
		 var hotValue=$(value);	
		 var btns=hotValue.parents(".sentenceContainer").find(".extractBtn");
		 
		 var tipText=$(btns[0]).find(".label").text();
		 tipText=tipText.replace("(","").replace(")","");
		 tipText=tipText+"��������"+"\n";
		 content=content+tipText;
		 var tipText=$(btns[1]).find(".label").text();
		 tipText=tipText.replace("(","").replace(")","");
		 tipText=tipText+"����ת��"+"\n";
		 content=content+tipText;
		 var tipText=$(btns[2]).find(".label").text();
		 tipText=tipText.replace("(","").replace(")","");
		 tipText=tipText+"����ϲ��";
		 content=content+tipText;
	     hotValue.attr("title",content);	
	});
}