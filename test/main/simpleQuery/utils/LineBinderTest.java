package main.simpleQuery.utils;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class LineBinderTest {
    //Inner test instance
    private LineBinder lineBinder;

    @Before
    public void setUp() throws Exception {
        this.lineBinder = new LineBinder();
    }

    @After
    public void tearDown() throws Exception {
        this.lineBinder = null;
    }


    @Test
    public void bindList_null() {
        List<String> toBind  = null;
        String result = this.lineBinder.bindList(toBind);

        Assert.assertNull("Binder should return null from processing null.", result);
    }

    @Test
    public void bindList_emptyList() {
        List<String> toBind  = new ArrayList<String>();
        String result = this.lineBinder.bindList(toBind);
        //TODO CHECK, IF THERE ARE NO SPECIAL METHOD TO CHECK EMPTY LIST
        Assert.assertNull("Binder should return null from processing empty list.", result);
    }

    @Test
    public void bindList_emptyStringTrimmed() {
        List<String> toBind  = Arrays.asList("");
        String result = this.lineBinder.bindList(toBind);

        Assert.assertNull("Binder should return null from processing trimmed empty string.", result);
    }

    @Test
    public void bindList_oneElem() {
        List<String> toBind  = Arrays.asList("foo");
        String result = this.lineBinder.bindList(toBind);
        String expected = "foo";
        //TODO Upgrade junit to 5 and use equals on lists
        Assert.assertTrue("Binder should return 'foo' from processing one element list with 'foo'.",
                result.equals(expected));
    }

    @Test
    public void bindList_oneElemToTrim() {
        List<String> toBind  = Arrays.asList("  foo ");
        String result = this.lineBinder.bindList(toBind);
        String expected = "foo";
        //TODO Upgrade junit to 5 and use equals on lists
        Assert.assertTrue("Binder should return 'foo' from processing trimmed one element list with '  foo '.",
                result.equals(expected));
    }


    @Test
    public void bindList_twoElems() {
        List<String> toBind  = Arrays.asList("foo","bar");
        String result = this.lineBinder.bindList(toBind, " ");
        String expected = "foo bar";
        //TODO Upgrade junit to 5 and use equals on lists
        Assert.assertTrue("Binder should return ('foo','bar') from processing two elements line 'foo bar'.",
                result.equals(expected));
    }

    @Test
    public void bindList_twoElemsToTrim() {
        List<String> toBind  = Arrays.asList("  foo "," bar");
        String result = this.lineBinder.bindList(toBind, " ");
        String expected = "foo bar";
        //TODO Upgrade junit to 5 and use equals on lists
        Assert.assertEquals("Binder should return ('  foo ',' bar') from processing " +
                        "trimmed two elements line 'foo bar'.",
                expected, result);
    }

    @Test
    public void bindList_fiveElemsMixed() {
        List<String> toBind  = Arrays.asList("  foo ", " bar", "is ", "boring", " dude ");
        String result = this.lineBinder.bindList(toBind, ", ");
        String expected = "foo, bar, is, boring, dude";
        //TODO Upgrade junit to 5 and use equals on lists
        Assert.assertTrue("Binder should return 'foo, bar, is, boring..' from processing " +
                        "trimmed 5 elements list ('  foo ', ' bar', 'is ', 'boring', ' dude ')",
                result.equals(expected));
    }
}