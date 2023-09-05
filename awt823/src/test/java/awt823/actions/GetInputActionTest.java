package awt823.actions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import awt823.action_chain.ActionState;

public class GetInputActionTest
{

	@AfterAll
	public static void restoreStreams()
	{
		System.setIn(System.in);
		System.setOut(System.out);
	}

	@Test
	public void testWrongToolEntry()
	{
		String input = "bad\n" // "Wrong input, try again."
				+ "CHNS\n"
				+ "9\n"
				+ "20\n"
				+ "12/14/1990\n";
		ByteArrayInputStream stream = new ByteArrayInputStream(input.getBytes());
		System.setIn(stream);
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

		GetInputAction action = new GetInputAction();
		action.execute(new ActionState());
		assertEquals("Enter a code: \r\n"
				+ "Invalid code. Please enter one of the following: CHNS, LADW, JAKD, JAKR\r\n"
				+ "Enter a code: \r\n"
				+ "How Many Days: \r\n"
				+ "Discount Percentage: \r\n"
				+ "Checkout Date: \r\n", outContent.toString());
	}

	@Test
	public void testWrongDayEntry()
	{
		String input = "CHNS\r\n"
				+ "bad\n"
				+ "-9\n"
				+ "9\n"
				+ "20\n"
				+ "12/14/1990\n";
		ByteArrayInputStream stream = new ByteArrayInputStream(input.getBytes());
		System.setIn(stream);
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

		GetInputAction action = new GetInputAction();
		action.execute(new ActionState());
		assertEquals("Enter a code: \r\n"
				+ "How Many Days: \r\n"
				+ "Invalid Days of use. Please enter a number\r\n"
				+ "How Many Days: \r\n"
				+ "Invalid Days of use. Please enter 1 or more.\r\n"
				+ "How Many Days: \r\n"
				+ "Discount Percentage: \r\n"
				+ "Checkout Date: \r\n", outContent.toString());
	}

	@Test
	public void testWrongPercentEntry()
	{
		String input = "CHNS\n"
				+ "9\n"
				+ "bad\n"
				+ "-20\n"
				+ "20\n"
				+ "12/14/1990\n";
		ByteArrayInputStream stream = new ByteArrayInputStream(input.getBytes());
		System.setIn(stream);
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

		GetInputAction action = new GetInputAction();
		action.execute(new ActionState());
		assertEquals("Enter a code: \r\n"
				+ "How Many Days: \r\n"
				+ "Discount Percentage: \r\n"
				+ "Invalid Discount Percentage. Please enter a number\r\n"
				+ "Discount Percentage: \r\n"
				+ "Invalid Percentage input. Please enter from [0-100]\r\n"
				+ "Discount Percentage: \r\n"
				+ "Checkout Date: \r\n", outContent.toString());
	}

	@Test
	public void testWrongDateEntry()
	{
		String input = "CHNS\n"
				+ "9\n"
				+ "20\n"
				+ "bad\n"
				+ "12/14/1990\n";
		ByteArrayInputStream stream = new ByteArrayInputStream(input.getBytes());
		System.setIn(stream);
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

		GetInputAction action = new GetInputAction();
		action.execute(new ActionState());
		assertEquals("Enter a code: \r\n"
				+ "How Many Days: \r\n"
				+ "Discount Percentage: \r\n"
				+ "Checkout Date: \r\n"
				+ "Invalid Format. Please use: MM/DD/YYYY\r\n"
				+ "Checkout Date: \r\n", outContent.toString());
	}
}
