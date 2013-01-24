package bijian.test;

public class UserActionTest extends ActionTestBase {

	  /*这个是单元测试的方法，一个方法测试一个功能，或者一个action*/
	 public void test_login() throws Exception {		 
	        /*request是类StrutsSpringTestCase的成员变量， 是MockHttpServletRequest对象，在这里mock出来的一个web中的request*/
	     this.request.setParameter("user.username", "jazywoo");
	     this.request.setParameter("user.password", "123456");
	        /*我的环境中，返回值是json格式，result将是json格式的一个字符串输入action的地址*/
	     String result =  executeAction("/user/UserAction_login.action");
	     
	 }
}
