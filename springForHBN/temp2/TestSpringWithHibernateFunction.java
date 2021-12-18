import com.ping.Controller.DispatcherController;
import com.ping.Dao.Emp;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Objects;

/**
 * Testing Spring With Hibernate function is work;
 * 如果成功的話，控制台會打印
 */
public class TestSpringWithHibernateFunction {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext app
                = new ClassPathXmlApplicationContext("spring-servlet.xml");
        DispatcherController controller
                = app.getBean(DispatcherController.class);
        SessionFactory fac
                = app.getBean(SessionFactory.class);
        Session session
                = fac.openSession();
        Transaction transaction
                = session.getTransaction();
        transaction.begin();

//----------------------------------------------------------
        Emp emp
                = new Emp();
        emp.setPhone("XXX");
        emp.setName("TTT");
        session.save(emp);
        transaction.commit();
//----------------------------------------------------------


        if (Objects.nonNull(controller)) {
//            System.out.printf("有偵測到 [%s]" , DispatcherConteller.class.getSimpleName());
            System.out.printf("有偵測到 [%s]", controller.getClass().getSimpleName());
            System.out.println();
        }

        session.close();
        app.close();
        System.out.println("去查看資料庫有沒有資料,select * from empp;");
        System.exit(0);
    }

}
