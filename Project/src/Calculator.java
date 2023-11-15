import java.io.*;
import java.net.*;
import java.util.*;

public class Calculator {
	static String[] value = new String[100];
	static float firstValue = 0;
	static float secondValue = 0;
	static float result = 0;
	//return E(error) of S(success)
	String[] returnValue = new String[2];

	public Calculator(String Line)
	{
		//Divide words according to " "
		value = Line.split(" "); 
	}
		
	public static float calc()
	{
		//Case-insensitive
		String operator = value[0].toUpperCase();
		
		switch(operator) {
		case "ADD":
			result = firstValue + secondValue;
			break;
		case "SUB":
			result = firstValue - secondValue;
			break;
		case "MUL":
			result = firstValue * secondValue;
			break;
		case "DIV":
			result = firstValue / secondValue;
			break;
		}
		
		return result;
	}
	
	public String[] CalcReturnValue()
	{
		//Format Error (Protocol is not correct)
		if(value.length != 3)
		{
			returnValue[0] = "E";
			returnValue[1] = "F";
			return returnValue;
		}
		
		try {
			firstValue = Float.parseFloat(value[1]);
			secondValue = Float.parseFloat(value[2]);
		}
		catch(NumberFormatException e)
		{
			//Number Error (It is not number)
			returnValue[0] = "E";
			returnValue[1] = "N";
			return returnValue;
		}
		
		String operator = value[0].toUpperCase();
		
		if(operator.equals("ADD") || operator.equals("SUB") || operator.equals("MUL"))
		{
			returnValue[0] = "S";
			return returnValue;
		}
		
		else if(operator.equals("DIV"))
		{
			if(secondValue == 0)
			{
				//Divided by 0 Error (second number is 0)
				returnValue[0] = "E";
				returnValue[1]= "D";
				return returnValue;
			}
			
			else
			{
				returnValue[0] = "S";
				return returnValue;
			}
		}
		
		else
		{
			//Operator error (Operator is not correct)
			returnValue[0] = "E";
			returnValue[1] = "O";
			return returnValue;
		}
	}
}
