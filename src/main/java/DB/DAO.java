package DB;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.ArrayList;

/**
 * Created by DNS on 19.04.2017.
 */
public class DAO {
    private Session session;

    public DAO(Session session) {
        this.session = session;
    }

    public ArrayList<Integer> getAll() throws HibernateException {
        return (ArrayList) session.createCriteria(Test.class).list();
    }
    private void add(int q) throws HibernateException{
        session.save(new Test(q));
    }
    public void addN(int n){
        for (int q = 1; q <= n; q++){
            add(q);
        }
    }

}
