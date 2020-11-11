package com.tsystems.javaschool.tasks.pyramid;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Math.*;

public class PyramidBuilder 
{

    /**
     * Builds a pyramid with sorted values (with minumum value at the top line and maximum at the bottom,
     * from left to right). All vacant positions in the array are zeros.
     *
     * @param inputNumbers to be used in the pyramid
     * @return 2d array with pyramid inside
     * @throws {@link CannotBuildPyramidException} if the pyramid cannot be build with given input
     */
    public int[][] buildPyramid(List<Integer> inputNumbers) 
    {
        // TODO : Implement your solution here
    	int size = inputNumbers.size();
    	// input length has to be equal to sum from 1 to n
    	// sum of this arithmetical progression equals to (1+n)*n/2
    	// from here we can go to n^2+n-2*size=0 equation and find n
    	// if it doesn't have fractional part
    	// then we can make pyramid
    	// else throw exception, pyramid can't be built
    	double root = sqrt(1 + 8 * size);
    	if(Math.abs(root - (int)root) <  0.000000001)
    	{
    		try
    		{
    			Collections.sort(inputNumbers);
    		}
    		catch(NullPointerException e)
			{
				throw new CannotBuildPyramidException();
			}
    		int n = (-1 + (int)root) / 2;
    		int m = n + (n - 1);
    		int center = (m - 1) / 2; 
    		int counter = 0;
    		int[][] pyramid = new int[n][m];
    		for(int i = 0; i < n; i++)
    		{
    			for(int j = center - i; j <= center + i; j += 2)
    			{
    				pyramid[i][j] = inputNumbers.get(counter);
    				counter++;
    			}
    		}
    		return pyramid;
    	}
    	else
    	{
    		throw new CannotBuildPyramidException();
    	}
    }


}
