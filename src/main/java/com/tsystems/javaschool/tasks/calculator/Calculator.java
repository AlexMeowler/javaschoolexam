package com.tsystems.javaschool.tasks.calculator;

import java.util.*;
import java.util.regex.*;

public class Calculator 
{

    /**
     * Evaluate statement represented as string.
     *
     * @param statement mathematical statement containing digits, '.' (dot) as decimal mark,
     *                  parentheses, operations signs '+', '-', '*', '/'<br>
     *                  Example: <code>(1 + 38) * 4.5 - 1 / 2.</code>
     * @return string value containing result of evaluation or null if statement is invalid
     */
	public String evaluate(String statement) 
    {
        // TODO: Implement the logic here
		// checking for the most obvious wrong input
	 	if((statement == null) || (statement.length() == 0))
	 	{
	 		return null;
	 	}
    	Stack<String> operator_stack = new Stack<>();
    	ArrayList<String> output = new ArrayList<>();
    	ArrayList<String> splitted_input = new ArrayList<>();
    	//using regex to split statement into set of operators and numbers
    	//this method uses RPN(reverse polish notation) for calculation result
    	Pattern p = Pattern.compile("(\\d+[.,]?\\d*)|[(*/+\\-)]|([.,]?\\d*)");
    	int parenthese_rank = 0;
    	Matcher matcher = p.matcher(statement);
    	while(matcher.find())
    	{
    		if(!matcher.group().equals(""))
    		{
    			splitted_input.add(matcher.group());
    		}
    	}
    	int size = splitted_input.size();
    	for(int i = 0; i < size; i++)
    	{
    		String current_string = splitted_input.get(i);
    		if(isValidNumber(current_string))
    		{
    			output.add(current_string);
    		}
    		else
    		{
    			if(isOperator(current_string.charAt(0)) || isParethese(current_string.charAt(0)))
    			{
    				switch(current_string.charAt(0))
    				{
    					case '(':
    						parenthese_rank++;
    						operator_stack.push(current_string);
    						/*if(!parethese_open)
    						{
    							parethese_open = true;
    							
    						}
    						else 
    						{
    							return null;
    						}*/
    						break;
    					case ')':
    						parenthese_rank--;
    						if(parenthese_rank < 0)
    						{
    							return null;
    						}
    						String s;
							while(!(s = operator_stack.pop()).equals("("))
							{
								output.add(s);
							}
    						/*if(parethese_open)
    						{
    							String s;
    							while(!(s = operator_stack.pop()).equals("("))
    							{
    								output.add(s);
    							}
    							parethese_open = false;
    						}
    						else
    						{
    							return null;
    						}*/
    						break;
    					case '*':
    					case '/':
    					case '+':
    					case '-':
    						int priority = getOperatorPriority(current_string.charAt(0));
    						int stack_priority;
    						if(operator_stack.size() != 0)
    						{
    							stack_priority = getOperatorPriority(operator_stack.peek().charAt(0));
    						}
    						else
    						{
    							stack_priority = Integer.MIN_VALUE;
    						}
    						if(priority <= stack_priority)
    						{
    							output.add(operator_stack.pop());
    						}
    						operator_stack.push(current_string);
    						break;
    					default:
    						return null;
    				}
    			}
    			else
    			{
    				return null;
    			}
    		}
    	}
    	if(parenthese_rank != 0)
    	{
    		return null;
    	}
    	int stack_size = operator_stack.size();
		for(int j = 0; j < stack_size; j++)
		{
			output.add(operator_stack.pop());
		}
		//after RPN transform. it's time to calculate the result
		Stack<Double> calc = new Stack<>();
		while(output.size() != 0)
		{
			String current = output.get(0);
			output.remove(0);
			if(isOperator(current.charAt(0)))
			{
				try
				{
					double b = calc.pop();
					double a = calc.pop();
					switch(current.charAt(0))
			    	{
			    		case '*':
			    			calc.push(a * b);
			    			break;
			    		case '/':
			    			//the only possible error here could be division by zero
			    			if(b != 0.0)
			    			{
			    				calc.push(a / b);
			    			}
			    			else
			    			{
			    				return null;
			    			}
			    			break;
			    		case '+':
			    			calc.push(a + b);
			    			break;
			    		case '-':
			    			calc.push(a - b);
			    			break;
			    	}
				}
				//however if the expression is incorrect from operands' point of view
				//we can end up with trying to pop element from empty stack
				catch(EmptyStackException e)
				{
					return null;
				}
			}
			else
			{
				calc.push(Double.parseDouble(current));
			}
		}
		//shaping up the result
		//by default all numbers are considered to be double
		//but if we get int-like result (10.0)
		//we have to make it look like int (10)
		//also result should be rounded to 4 digits using math's rule
		double result = calc.pop();
		String string_result = "" + result;
		String fraction = string_result.substring(string_result.indexOf('.') + 1);
		if(Integer.parseInt(fraction) == 0)
		{
			string_result = string_result.substring(0, string_result.indexOf('.'));
		}
		else
		{
			if(fraction.length() > 4)
			{
				if(Integer.parseInt("" + fraction.charAt(4)) >= 5)
				{
					//fraction = fraction.substring(0, 3) + (Integer.parseInt("" + fraction.charAt(3)) + 1);
					//string_result = (int) result + "." + fraction;
					string_result = (("" + Math.signum(result)).substring(0, 1) + (Math.ceil((Math.abs(result) * Math.pow(10, 5))) / Math.pow(10, 5)));
					fraction = string_result.substring(string_result.indexOf('.') + 1);
					if(Integer.parseInt(fraction) == 0)
					{
						string_result = string_result.substring(0, string_result.indexOf('.'));
					}
					else
					{
						if(fraction.length() > 4)
						{
							string_result = string_result.substring(0, string_result.indexOf('.') + 5);
						}
					}
						
				}
				else
				{
					string_result = string_result.substring(0, string_result.indexOf('.') + 5);
				}
			}
		}
        return string_result;
    }
    
    private int getOperatorPriority(char c)
    {
    	switch(c)
    	{
    		case '(':
			case ')':
				return Integer.MIN_VALUE;
    		case '*':
    		case '/':
    			return 2;
    		case '+':
    		case '-':
    			return 1;
    		default:
    			return Integer.MIN_VALUE;
    	}
    }
    
    private boolean isOperator(char c)
    {
    	return (c == '+') || (c == '-') || (c == '*') || (c == '/');
    }
    
    private boolean isParethese(char c)
    {
    	return (c == '(') || (c == ')');
    }
    
    private boolean isValidNumber(String s)
    {
    	return (s.indexOf(',') == -1) && (s.indexOf('.') != s.length() - 1)  && (s.indexOf('.') != 0) && !(isOperator(s.charAt(0)) || isParethese(s.charAt(0)));
    }
    
}
