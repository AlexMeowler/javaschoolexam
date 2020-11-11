package com.tsystems.javaschool.tasks.calculator;

import org.junit.Assert;
import org.junit.Test;

public class MyCalculatorTest 
{
	private Calculator calc = new Calculator();

    @Test
    public void evaluate() {
        //given
        String input = "2+3";
        String expectedResult = "5";

        //run
        String result = calc.evaluate(input);

        //assert
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void evaluate1() {
        //given
        String input = "2.000006-6";
        String expectedResult = "-4";

        //run
        String result = calc.evaluate(input);

        //assert
        Assert.assertEquals(expectedResult, result);
    }
    
    @Test
    public void evaluate2() {
        //given
        String input = "4.-6";
        String expectedResult = null;

        //run
        String result = calc.evaluate(input);

        //assert
        Assert.assertEquals(expectedResult, result);
    }
    
    @Test
    public void evaluate3() {
        //given
        String input = ".4-6";
        String expectedResult = null;

        //run
        String result = calc.evaluate(input);

        //assert
        Assert.assertEquals(expectedResult, result);
    }
    
    @Test
    public void evaluate4() {
        //given
        String input = "0.4-6";
        String expectedResult = "-5.6";

        //run
        String result = calc.evaluate(input);

        //assert
        Assert.assertEquals(expectedResult, result);
    }
    
    @Test
    public void evaluate5() {
        //given
        String input = "2 + 0.831321";
        String expectedResult = "2.8313";

        //run
        String result = calc.evaluate(input);

        //assert
        Assert.assertEquals(expectedResult, result);
    }
    
    @Test
    public void evaluate6() {
        //given
        String input = "2 + 0..831321";
        String expectedResult = null;

        //run
        String result = calc.evaluate(input);

        //assert
        Assert.assertEquals(expectedResult, result);
    }
    
    @Test
    public void evaluate7() {
        //given
    	String input = "((2.5 + 1.5) * 4 * (4 - 2)) + (0.1 + 1.0)";
        String expectedResult = "33.1";

        //run
        String result = calc.evaluate(input);

        //assert
        Assert.assertEquals(expectedResult, result);
    }
    
    @Test
    public void evaluate8() {
        //given
    	String input = "(2.5 + 1.5 * 4 * (4 - 2)) + (0.1 + 1.0)";
        String expectedResult = "15.6";

        //run
        String result = calc.evaluate(input);

        //assert
        Assert.assertEquals(expectedResult, result);
    }
    
    @Test
    public void evaluate9() {
        //given
    	String input = "(2.5 + 1.5 * 4 * 4 - 2)) + (0.1 + 1.0)";
        String expectedResult = null;

        //run
        String result = calc.evaluate(input);

        //assert
        Assert.assertEquals(expectedResult, result);
    }
}
