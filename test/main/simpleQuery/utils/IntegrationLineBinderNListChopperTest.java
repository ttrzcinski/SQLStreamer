package main.simpleQuery.utils;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntegrationLineBinderNListChopperTest {
    //Inner test instance
    private LineBinder lineBinder;
    private ListChopper listChopper;

    @Before
    public void setUp() throws Exception {
        this.lineBinder = new LineBinder();
        this.listChopper = new ListChopper();
    }

    @After
    public void tearDown() throws Exception {
        this.lineBinder = null;
        this.listChopper = null;
    }


    @Test
    public void listBindNChop_null() {
        List<String> toBind  = null;
        String bindedLine = this.lineBinder.bindList(toBind);
        List<String> result = this.listChopper.chopBy(bindedLine);

        Assert.assertEquals("Processing null should return the same result, as given on the beginning.", toBind, result);
    }

    @Test
    public void listBindNChop_empty() {
        List<String> toBind  = new ArrayList<String>();
        String bindedLine = this.lineBinder.bindList(toBind);
        List<String> result = this.listChopper.chopBy(bindedLine);
        List<String> expected = null;

        Assert.assertEquals("Processing empty list should return null.", expected, result);
    }

    @Test
    public void listBindNChop_oneElemEmpty() {
        List<String> toBind  = Arrays.asList("  ");
        String bindedLine = this.lineBinder.bindList(toBind);
        List<String> result = this.listChopper.chopBy(bindedLine);
        List<String> expected = null;

        Assert.assertEquals("Processing list with one empty param should return null.", expected, result);
    }

    @Test
    public void listBindNChop_oneElem() {
        List<String> toBind  = Arrays.asList(" foo ");
        String bindedLine = this.lineBinder.bindList(toBind);
        List<String> result = this.listChopper.chopBy(bindedLine);
        List<String> expected = Arrays.asList("foo");

        Assert.assertEquals("Processing list with one param should return list with this param as trimmed string.", expected, result);
    }

    @Test
    public void listBindNChop_twoElem() {
        List<String> toBind  = Arrays.asList(" foo ", " bar ");
        String bindedLine = this.lineBinder.bindList(toBind);
        List<String> result = this.listChopper.chopBy(bindedLine);
        List<String> expected = Arrays.asList("foo","bar");

        Assert.assertEquals("Processing list with two params should return list with those params as trimmed strings.", expected, result);
    }
}
