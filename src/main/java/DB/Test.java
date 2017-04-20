package DB;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by DNS on 19.04.2017.
 */
@Entity
@Table(name = "test")
public class Test implements Serializable {
    @Id
    @Column(name = "test")
    private int test;

    public Test(int test) {
        this.test = test;
    }
    public Test() {
        this.test = test;
    }

    public int getTest() {
        return test;
    }

    public void setTest(int test) {
        this.test = test;
    }

    @Override
    public String toString() {
        return test+"";
    }
}
