import com.ping.Dao.Emp;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *  Testing hibernate is work
 */
public class TestStarter {

    public static void main(String[] args) {
        Configuration config
                = new Configuration();
        Configuration cf
                = config.configure("hibernate.cfg.xml");
        SessionFactory factory
                = cf.addAnnotatedClass(Emp.class).buildSessionFactory();
        Session session
                = factory.openSession();
        Transaction transaction
                = session.beginTransaction();

        transaction.begin();
//-------------------------------------------------------------
        Emp e
                = new Emp();
        e.setId(0);
        e.setName("XX");
        e.setPhone("111");
//-------------------------------------------------------------

        session.save(e);
        transaction.commit();
        session.close();
        System.exit(0);
    }

}
