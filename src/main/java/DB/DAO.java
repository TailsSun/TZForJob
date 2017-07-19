package DB;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.ArrayList;


public class DAO {
    private Session session;

    public DAO(Session session) {
        this.session = session;
    }

    public ArrayList<Test> getAll() throws HibernateException {
        return (ArrayList) session.createCriteria(Test.class).list();
    }
    public void addNForBD(int n){
        for (int q = 1; q <= n; q++){
            addValueBD(q);
        }
    }
    private void addValueBD(int value) throws HibernateException{
        session.save(new Test(value));
    }
}
