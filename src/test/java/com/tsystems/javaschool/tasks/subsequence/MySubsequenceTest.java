package com.tsystems.javaschool.tasks.subsequence;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;

public class MySubsequenceTest 
{
	private Subsequence subsequence = new Subsequence();

    @Test
    public void find() {
        //given
        List x = Stream.of(1, null, 5, 7, 9).collect(toList());
        List y = Stream.of(10, 1, 2, null, 4, 5, 7, 9, 20).collect(toList());

        //run
        boolean result = subsequence.find(x, y);

        //assert
        Assert.assertTrue(result);
    }

    @Test
    public void find1() {
        //given
    	 List x = Stream.of(1, null, null, 7, 9).collect(toList());
         List y = Stream.of(10, 1, 2, null, 4, 5, 7, 9, 20).collect(toList());

        //run
        boolean result = subsequence.find(x, y);

        //assert
        Assert.assertFalse(result);
    }

    @Test
    public void find2() {
        //given
    	List x = Stream.of(1, null, null, 7, 9).collect(toList());
        List y = Stream.of(10, 1, 2, null, null, 5, 7, 9, 20).collect(toList());

        //run
        boolean result = subsequence.find(x, y);

        //assert
        Assert.assertTrue(result);
    }
}
