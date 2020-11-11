package com.tsystems.javaschool.tasks.subsequence;

import static java.util.stream.Collectors.toList;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class Subsequence {

    /**
     * Checks if it is possible to get a sequence which is equal to the first
     * one by removing some elements from the second one.
     *
     * @param x first sequence
     * @param y_copy second sequence
     * @return <code>true</code> if possible, otherwise <code>false</code>
     */
    @SuppressWarnings("rawtypes")
    public boolean find(List x, List y) 
    {
        // TODO: Implement the logic here
    	//checking for bad arguments
    	if((x == null) || (y == null))
    	{
    		throw new IllegalArgumentException();
    	}
    	List y_copy = (List) y.stream().collect(toList());
    	//making a copy of y so original data won't get damaged, because in order to
    	//correctly process duplicating items i'll need to remove them from y (due to indexOf specifics)
    	int y_size = y_copy.size();
    	int x_size = x.size();
    	int previous_index = -1;
    	//if y has less elements than x, then result is false (because there is no way make x from y)
        if(y_size >= x_size)
        {
        	for(int i = 0; i < x_size; i++)
        	{
        		//if y contains each of x's elements and order is the same
        		//them result is true
        		//otherwise false
        		int index = y_copy.indexOf(x.get(i));
        		if(index == -1)
        		{
        			return false;
        		}
        		if(index > previous_index)
        		{
        			previous_index = index - 1;
        			y_copy.remove(index);
        		}
        		else
        		{
        			return false;
        		}
        	}
        	return true;
        }
        else
        {
        	return false;
        }
    }
}
