import junit.framework.Assert;
import junit.framework.TestCase;

import main.SQLStream;
import org.junit.*;

//TODO CONFIGURE PROPERLY JUNIT 5 IN THIS PROJECT - WITH MOCKITO
public class SelectTests extends TestCase {
    @Test
    public void selectOnly() {
        SQLStream sqlStream = new SQLStream();
        sqlStream.select();

        Assert.assertEquals("SELECT", sqlStream.asString());
    }

    @Test
    public void selectOneParam() {
        SQLStream sqlStream = new SQLStream();
        sqlStream.select("*");

        Assert.assertEquals("SELECT *", sqlStream.asString());
    }

    @Test
    public void selectOneParamNull() {
        SQLStream sqlStream = new SQLStream();
        sqlStream.select(null);

        Assert.assertEquals("SELECT", sqlStream.asString());
    }

    @Test
    public void selectOneParamEmpty() {
        SQLStream sqlStream = new SQLStream();
        sqlStream.select("");//TODO CHANGE TO STRING.EMPTY_STRING

        Assert.assertEquals("SELECT", sqlStream.asString());
    }
}
