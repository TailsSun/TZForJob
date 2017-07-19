package DB;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Table(name = "test")
public class Test implements Serializable {
    @Id
    @Column(name = "column1")
    private int testValue;

    public Test() {
    }
    public Test(int testValue) {
        this.testValue = testValue;
    }

    public int getTestValue() {
        return testValue;
    }
    public void setTestValue(int testValue) {
        this.testValue = testValue;
    }

    @Override
    public String toString() {
        return testValue+"";
    }
}
