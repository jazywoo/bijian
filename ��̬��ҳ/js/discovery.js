// JavaScript Document

$(document).ready(function(){
    toggleOwnSubMenu();//�����˵�
    togglePhotoInfo();//��ʾ����Ƶ�ͼ������ʾ�û���Ϣ���
	toggleCommentCanvas();//��ʾ���۵����
	appendSentenceTitle();//��ʾһЩԪ��title��Ϣ
	showLabelHotGraph();////��ʾ����Ҳ��ǩ�Ļ�Ծ�ȵ���״ͼ
});

//��ʾ����Ҳ��ǩ�Ļ�Ծ�ȵ���״ͼ���Լ�title��tip��
function showLabelHotGraph(){
	var labelHotContainers=$(".labelHotContainer");
	$.each(labelHotContainers,function(index,value){
		 var container=$(value);
		 var labelHotValues=container.find(".labelHotValue");
		 $.each(labelHotValues,function(index2,value2){
		     var hotValue=$(value2);//�������е����ӣ�Ȼ�����tip�͸߶���ʾ
		     var ems=hotValue.find("em");
			 var height=$(ems[0]).text();
			 var count=$(ems[1]).text();
			 var date=$(ems[2]).text();
			 var tip=date+"\n�ñ�ǩ��ʹ����"+count+"��";
			 hotValue.css("height",height);
		     hotValue.attr("title",tip);
         })
    });
	
	
}
