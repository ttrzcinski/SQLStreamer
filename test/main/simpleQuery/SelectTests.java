package main.simpleQuery;

import junit.framework.Assert;

import main.SQLStream;
import main.simpleQuery.utils.SimpleQueryBuilder;
import org.junit.*;

import java.util.Arrays;

//TODO CONFIGURE PROPERLY JUNIT 5 IN THIS PROJECT - WITH MOCKITO
public class SelectTests {
    @Test
    public void selectOnly() {
        SQLStream sqlStream = new SQLStream();
        sqlStream.select();
        String result = sqlStream.asString();
        String expected = "SELECT *";

        Assert.assertEquals(expected, result);
    }

    @Test
    public void selectOneParam() {
        SQLStream sqlStream = new SQLStream();
        sqlStream.select("*");
        String result = sqlStream.asString();
        String expected = "SELECT *";

        Assert.assertEquals(expected, result);
    }

    @Test
    public void selectOneParamNull() {
        SQLStream sqlStream = new SQLStream();
        sqlStream.select(null);
        String result = sqlStream.asString();
        String expected = "SELECT *";

        Assert.assertEquals(expected, result);
    }

    @Test
    public void selectOneParamEmpty() {
        SQLStream sqlStream = new SQLStream();
        sqlStream.select("");//TODO CHANGE TO STRING.EMPTY_STRING
        String result = sqlStream.asString();
        String expected = "SELECT *";

        Assert.assertEquals(expected, result);
    }

    @Test
    public void selectOneParamKnown() {
        SQLStream sqlStream = new SQLStream();
        sqlStream.select(" foo ");//TODO CHANGE TO STRING.EMPTY_STRING
        String result = sqlStream.asString();
        String expected = "SELECT foo";

        Assert.assertEquals(expected, result);
    }

    @Test
    public void selectTwoParamKnown() {
        SQLStream sqlStream = new SQLStream();
        sqlStream.select(" foo ");
        sqlStream.select("bar ");
        String result = sqlStream.asString();
        String expected = "SELECT foo, bar";

        Assert.assertEquals(expected, result);
    }

    @Test
    public void selectFiveParamKnown() {
        SQLStream sqlStream = new SQLStream();
        sqlStream.select(" foo ");
        sqlStream.select("bar ");
        sqlStream.select(" is");
        sqlStream.select("so");
        sqlStream.select(" stupid  ");
        String result = sqlStream.asString();
        String expected = "SELECT foo, bar, is, so, stupid";

        Assert.assertEquals(expected, result);
    }

    @Test
    public void selectCombining() {
        SimpleQuery query = new SimpleQueryBuilder()
                .select("some_column")
                .select(Arrays.asList("other_column"))
                .select("COUNT(the_column)")
                .from("table_1")
                .from("table_2")
                .from("table_N")
                .orderBy("id")
                .orderBy("uuid")
                .orderBy("the_last_column")
                .build();
        String result = query.asString();
        String expected = "SELECT some_column, other_column, COUNT(the_column) FROM table_1, table_2, table_N ORDER BY id, uuid, the_last_column";

        Assert.assertEquals(expected, result);
    }
}
