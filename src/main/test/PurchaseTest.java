import com.ttt.mim.service.PurchaseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-mybatis.xml")
public class PurchaseTest {
    @Resource
    PurchaseService purchaseService;
    @Test
    public void Test1(){
        String availablePurchaseNum = purchaseService.getAvailablePurchaseNum();
        System.out.println(availablePurchaseNum);
        System.out.println(purchaseService.isRevisable(availablePurchaseNum));
    }
}
