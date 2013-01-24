<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'header.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  
     <s:a href="LanguageAction_changeLanguage.action?request_locale=zh_CN"><s:property value="%{getText('header.language.chinese')}"/></s:a> 
     <s:a href="LanguageAction_changeLanguage.action?request_locale=en_US"><s:property value="%{getText('header.language.english')}"/></s:a> 
     <br>
     <s:form action="">
         <s:textfield  name="searchKeword" value="%{getText('header.search.searchInput')}"/>
         <s:submit type="button" value="%{getText('header.search.searchButtonText')}" name="method:search"/>
      </s:form> 
      
      <s:form namespace="/user" action="UserAction_login" method="post">
         <s:textfield label="%{getText('header.loginForm.username')}" name="user.username"/>
         <s:password label="%{getText('header.loginForm.password')}" name="user.password"/>
         <s:submit type="button" value="%{getText('header.loginForm.buttonText')}"/>
      </s:form>
      
      
      <s:property value="username" />
      <s:a href="navigationAction_ownPage"><s:property value="%{getText('header.link.homepage')}"/></s:a>
      <s:a href="navigationAction_message"><s:property value="%{getText('header.link.message')}"/></s:a>
      <s:a href="navigationAction_setting"><s:property value="%{getText('header.link.setting')}"/></s:a>
      <s:a href="navigationAction_logout"><s:property value="%{getText('header.link.logout')}"/></s:a>
  </body>
</html>
