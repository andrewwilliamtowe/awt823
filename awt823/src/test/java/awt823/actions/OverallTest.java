package awt823.actions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import awt823.action_chain.PointOfSaleActionChain;

public class OverallTest
{

	@AfterAll
	public static void restoreStreams()
	{
		System.setIn(System.in);
		System.setOut(System.out);
	}

	@Test
	public void test1()
	{
		String input = "JAKR\n"
				+ "5\n"
				+ "101\n"
				+ "20\n"
				+ "9/3/2015\n";
		ByteArrayInputStream stream = new ByteArrayInputStream(input.getBytes());
		System.setIn(stream);
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

		PointOfSaleActionChain chain = new PointOfSaleActionChain();
		chain.execute();
		assertEquals("Enter a code: \r\n"
				+ "How Many Days: \r\n"
				+ "Discount Percentage: \r\n"
				+ "Invalid Percentage input. Please enter from [0-100]\r\n"
				+ "Discount Percentage: \r\n"
				+ "Checkout Date: \r\n"
				+ "\r\n"
				+ "Rental Agreement\r\n"
				+ "JAKR\r\n"
				+ "JACKHAMMER\r\n"
				+ "Ridgid\r\n"
				+ "5\r\n"
				+ "2015-01-03\r\n"
				+ "2015-01-07\r\n"
				+ "2.99\r\n"
				+ "3\r\n"
				+ "$8.97\r\n"
				+ "20%\r\n"
				+ "$1.79\r\n"
				+ "$7.18\r\n", outContent.toString());
	}

	@Test
	public void test2()
	{
		String input = "LADW\n"
				+ "3\n"
				+ "10\n"
				+ "7/2/2020\n";
		ByteArrayInputStream stream = new ByteArrayInputStream(input.getBytes());
		System.setIn(stream);
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

		PointOfSaleActionChain chain = new PointOfSaleActionChain();
		chain.execute();
		assertEquals("Enter a code: \r\n"
				+ "How Many Days: \r\n"
				+ "Discount Percentage: \r\n"
				+ "Checkout Date: \r\n"
				+ "\r\n"
				+ "Rental Agreement\r\n"
				+ "LADW\r\n"
				+ "LADDER\r\n"
				+ "Werner\r\n"
				+ "3\r\n"
				+ "2020-01-02\r\n"
				+ "2020-01-04\r\n"
				+ "1.99\r\n"
				+ "3\r\n"
				+ "$5.97\r\n"
				+ "10%\r\n"
				+ "$0.6\r\n"
				+ "$5.37\r\n", outContent.toString());
	}

	@Test
	public void test3()
	{
		String input = "CHNS\n"
				+ "5\n"
				+ "25\n"
				+ "7/2/2015\n";
		ByteArrayInputStream stream = new ByteArrayInputStream(input.getBytes());
		System.setIn(stream);
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

		PointOfSaleActionChain chain = new PointOfSaleActionChain();
		chain.execute();
		assertEquals("Enter a code: \r\n"
				+ "How Many Days: \r\n"
				+ "Discount Percentage: \r\n"
				+ "Checkout Date: \r\n"
				+ "\r\n"
				+ "Rental Agreement\r\n"
				+ "CHNS\r\n"
				+ "CHAINSAW\r\n"
				+ "Stihl\r\n"
				+ "5\r\n"
				+ "2015-01-02\r\n"
				+ "2015-01-06\r\n"
				+ "1.49\r\n"
				+ "3\r\n"
				+ "$4.47\r\n"
				+ "25%\r\n"
				+ "$1.12\r\n"
				+ "$3.35\r\n", outContent.toString());
	}

	@Test
	public void test4()
	{
		String input = "JAKD\n"
				+ "6\n"
				+ "0\n"
				+ "9/3/2015\n";
		ByteArrayInputStream stream = new ByteArrayInputStream(input.getBytes());
		System.setIn(stream);
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

		PointOfSaleActionChain chain = new PointOfSaleActionChain();
		chain.execute();
		assertEquals("Enter a code: \r\n"
				+ "How Many Days: \r\n"
				+ "Discount Percentage: \r\n"
				+ "Checkout Date: \r\n"
				+ "\r\n"
				+ "Rental Agreement\r\n"
				+ "JAKD\r\n"
				+ "JACKHAMMER\r\n"
				+ "DeWalt\r\n"
				+ "6\r\n"
				+ "2015-01-03\r\n"
				+ "2015-01-08\r\n"
				+ "2.99\r\n"
				+ "4\r\n"
				+ "$11.96\r\n"
				+ "0%\r\n"
				+ "$0.0\r\n"
				+ "$11.96\r\n", outContent.toString());
	}

	@Test
	public void test5()
	{
		String input = "JAKR\n"
				+ "9\n"
				+ "0\n"
				+ "7/2/2015\n";
		ByteArrayInputStream stream = new ByteArrayInputStream(input.getBytes());
		System.setIn(stream);
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

		PointOfSaleActionChain chain = new PointOfSaleActionChain();
		chain.execute();
		assertEquals("Enter a code: \r\n"
				+ "How Many Days: \r\n"
				+ "Discount Percentage: \r\n"
				+ "Checkout Date: \r\n"
				+ "\r\n"
				+ "Rental Agreement\r\n"
				+ "JAKR\r\n"
				+ "JACKHAMMER\r\n"
				+ "Ridgid\r\n"
				+ "9\r\n"
				+ "2015-01-02\r\n"
				+ "2015-01-10\r\n"
				+ "2.99\r\n"
				+ "6\r\n"
				+ "$17.94\r\n"
				+ "0%\r\n"
				+ "$0.0\r\n"
				+ "$17.94\r\n", outContent.toString());
	}

	@Test
	public void test6()
	{
		String input = "JAKR\n"
				+ "4\n"
				+ "50\n"
				+ "7/2/2020\n";
		ByteArrayInputStream stream = new ByteArrayInputStream(input.getBytes());
		System.setIn(stream);
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

		PointOfSaleActionChain chain = new PointOfSaleActionChain();
		chain.execute();
		assertEquals("Enter a code: \r\n"
				+ "How Many Days: \r\n"
				+ "Discount Percentage: \r\n"
				+ "Checkout Date: \r\n"
				+ "\r\n"
				+ "Rental Agreement\r\n"
				+ "JAKR\r\n"
				+ "JACKHAMMER\r\n"
				+ "Ridgid\r\n"
				+ "4\r\n"
				+ "2020-01-02\r\n"
				+ "2020-01-05\r\n"
				+ "2.99\r\n"
				+ "2\r\n"
				+ "$5.98\r\n"
				+ "50%\r\n"
				+ "$2.99\r\n"
				+ "$2.99\r\n", outContent.toString());
	}
}
