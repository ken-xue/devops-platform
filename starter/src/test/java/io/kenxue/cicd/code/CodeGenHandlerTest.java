package io.kenxue.cicd.code;

import io.kenxue.cicd.Application;
import io.kenxue.cicd.infrastructure.util.CodeGenHandler;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * MybatisTest
 *
 * @date 2019-02-27 4:38 PM
 */
@SpringBootTest(classes={Application.class})
@RunWith(SpringRunner.class)
public class CodeGenHandlerTest {

    @Resource
    private CodeGenHandler codeGenHandler;

    @Test
    public void genTest()throws Exception {
        PropertiesConfiguration configuration = new PropertiesConfiguration("generator.properties");
        //codeGenHandler.execute(configuration);
    }

    @Test
    public void delTest() {
//        codeGenHandler.deleteGenFile();
    }



}
