import com.ttt.mim.controller.LogSysController;
import com.ttt.mim.controller.LogUserController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-mybatis.xml")
public class LogTest {
    @Resource
    private LogUserController logUserController;
    @Resource
    private LogSysController logSysController;

    @Test
    public void Test(){
    }
}


