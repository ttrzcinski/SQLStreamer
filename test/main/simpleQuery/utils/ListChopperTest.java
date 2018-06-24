package main.simpleQuery.utils;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class ListChopperTest {
    //Inner test instance
    private ListChopper listChopper;

    @Before
    public void setUp() throws Exception {
        this.listChopper = new ListChopper();
    }

    @After
    public void tearDown() throws Exception {
        this.listChopper = null;
    }

    @Test
    public void chopBy_null() {
        String toChop = null;
        List<String> result = this.listChopper.chopBy(toChop);

        Assert.assertNull("Chopper should return null from processing null.", result);
    }

    @Test
    public void chopBy_emptyString() {
        String toChop = "";
        List<String> result = this.listChopper.chopBy(toChop);

        Assert.assertNull("Chopper should return null from processing empty string.", result);
    }

    @Test
    public void chopBy_emptyStringTrimmed() {
        String toChop = "  ";
        List<String> result = this.listChopper.chopBy(toChop);

        Assert.assertNull("Chopper should return null from processing trimmed empty string.", result);
    }

    @Test
    public void chopBy_oneElem() {
        String toChop = "foo";
        List<String> result = this.listChopper.chopBy(toChop);
        List<String> expected = new ArrayList<String>();
        expected.add("foo");
        //TODO Upgrade junit to 5 and use equals on lists
        Assert.assertTrue("Chopper should return 'foo' from processing one element string 'foo'.",
                result.toString().equals(expected.toString()));
    }

    @Test
    public void chopBy_oneElemToTrim() {
        String toChop = " foo ";
        List<String> result = this.listChopper.chopBy(toChop);
        List<String> expected = new ArrayList<String>();
        expected.add("foo");
        //TODO Upgrade junit to 5 and use equals on lists
        Assert.assertTrue("Chopper should return 'foo' from processing trimmed one element string ' foo '.",
                result.toString().equals(expected.toString()));
    }


    @Test
    public void chopBy_twoElems() {
        String toChop = "foo bar";
        List<String> result = this.listChopper.chopBy(toChop, " ");
        List<String> expected = new ArrayList<String>();
        expected.add("foo");
        expected.add("bar");
        //TODO Upgrade junit to 5 and use equals on lists
        Assert.assertTrue("Chopper should return ('foo','bar') from processing two elements string 'foo bar'.",
                result.size() == expected.size());
    }

    @Test
    public void chopBy_twoElemsToTrim() {
        String toChop = " foo bar ";
        List<String> result = this.listChopper.chopBy(toChop, " ");
        List<String> expected = new ArrayList<String>();
        expected.add("foo");
        expected.add("bar");
        //TODO Upgrade junit to 5 and use equals on lists
        Assert.assertTrue("Chopper should return ('foo','bar') from processing trimmed two elements string ' foo bar '.",
                result.size() == expected.size());
    }

    @Test
    public void chopBy_fiveElemsMixed() {
        String toChop = " foo, bar,is boring,    dude";
        List<String> result = this.listChopper.chopBy(toChop, ", ");
        List<String> expected = new ArrayList<String>();
        expected.add("foo");
        expected.add("bar");
        expected.add("is");
        expected.add("boring");
        expected.add("dude");
        //TODO Upgrade junit to 5 and use equals on lists
        Assert.assertTrue("Chopper should return ('foo','bar',..) from processing trimmed 5 elements string ' foo, bar,is boring,    dude'.",
                result.size() == expected.size());
        for (int i = 0; i < expected.size(); i++) {
            String expectedElement = expected.get(i);
            String resultElement = result.get(i);
            Assert.assertEquals(String.format("Element #s must be the same.", i, expectedElement, resultElement),
                    expectedElement, resultElement);
        }
    }
}