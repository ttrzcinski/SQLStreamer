package main.simpleQuery;

import junit.framework.Assert;
import main.SQLStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.fail;

public class SQLStreamTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void countBy() {
        SQLStream query = new SQLStream();
        query.select("user_id");
        query.countBy("1");
        query.from("da_dual");
        query.orderBy("user_id");
        query.endWithSemicolor();
        String result = query.asString();
        String expected = "SELECT user_id, COUNT(1) from da_dual ORDER BY user_id;";

        assertEquals(expected, result);
    }

    @Test
    public void from() {
        SQLStream query = new SQLStream();
        query.select();
        query.from("table_1");
        query.endWithSemicolor();
        String result = query.asString();
        String expected = "SELECT * FROM table_1;";

        assertEquals(expected, result);
    }

    @Test
    public void whereEquals() {
        fail("Implement me for f**k sake!");
    }

    @Test
    public void orderBy() {
        SQLStream query = new SQLStream();
        query.select("order_id");
        query.from("orders");
        query.orderBy("order_created");
        String result = query.asString();
        String expected = "SELECT order_id from orders ORDER BY order_created";

        assertEquals(expected, result);
    }

    @Test
    public void orderBy1() {
        fail("Implement me for f**k sake!");
    }

    @Test
    public void endWithSemicolor() {
        SQLStream query = new SQLStream();
        query.select();
        query.from("table_some");
        query.endWithSemicolor();
        String result = query.asString();

        Assert.assertTrue("Query should end with semicolon.", result.endsWith(";"));
    }

    @Test
    public void length() {
        fail("Implement me for f**k sake!");
    }

    @Test
    public void isEmpty() {
        fail("Implement me for f**k sake!");
    }

    @Test
    public void getSb() {
        fail("Implement me for f**k sake!");
    }

    @Test
    public void asString() {
        fail("Implement me for f**k sake!");
    }
}
