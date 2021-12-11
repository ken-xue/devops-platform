package io.kenxue.cicd;

import io.kenxue.cicd.coreclient.dto.sys.user.UserAddCmd;
import io.kenxue.cicd.coreclient.dto.sys.user.UserDTO;
import org.junit.Test;

/**
 * ContextInterceptorTest 单元测试
 *
 * @date 2019-03-01 9:38 AM
 */
public class ContextInterceptorTest {

    @Test
    public void testNoOperatorContext(){
        UserAddCmd userAddCmd = new UserAddCmd();
        userAddCmd.setUserDTO(new UserDTO());

//        ContextInterceptor contextInterceptor = new ContextInterceptor();
//        contextInterceptor.preIntercept(userProfileAddCmd);
    }

    @Test
    public void testOperatorContext(){
        UserAddCmd userAddCmd = new UserAddCmd();
        userAddCmd.setUserDTO(new UserDTO());
        userAddCmd.setOperator("Frank");

//        ContextInterceptor contextInterceptor = new ContextInterceptor();
//        contextInterceptor.preIntercept(userProfileAddCmd);
    }
}
