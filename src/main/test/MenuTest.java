import com.ttt.mim.dao.MenuDao;
import com.ttt.mim.domain.Menu;
import com.ttt.mim.service.MenuService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.Map;


public class MenuTest {
    @Resource
private MenuDao menuDao;
    @Resource
private MenuService menuService;
    public void Test(){
     Menu menu=new Menu();
     menu.setName("人员管理");
     menu.setParentId(125);
     menu.setIcon("sjkjhd");
     menu.setPerm("@fjsa");
     menu.setUrl("dafaf");
    menuService.add(menu);
    }
}
