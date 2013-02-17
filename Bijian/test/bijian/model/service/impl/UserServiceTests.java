package bijian.model.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import bijian.model.bean.User;
import bijian.model.service.IUserService;


/**
 * @author jazywoo
 * 测试通过1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring.xml"})
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=true)
public class UserServiceTests  extends AbstractTransactionalJUnit4SpringContextTests{
	@Resource(name="userService",type=UserServiceImpl.class)
    private IUserService userService;
	
	@Test
	public void getHotUsers(){
		List<User> users=userService.getHotUsers();
		Assert.assertTrue(users.size()>0);
		System.out.println(users.get(0).getUsername());
	}
}
