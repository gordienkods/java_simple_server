package simple_test;

import org.junit.Assert;
import org.junit.Test;

public class SimpleUnitTest {

    @Test
    public void simple_test(){
        Integer a = 1, b = 2;
        Assert.assertTrue(a < b);
    }

}
