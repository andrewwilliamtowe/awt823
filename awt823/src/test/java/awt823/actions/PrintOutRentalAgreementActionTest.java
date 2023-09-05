package awt823.actions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.joda.time.LocalDate;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import awt823.action_chain.ActionState;
import awt823.tools.Tools;

public class PrintOutRentalAgreementActionTest
{
	private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private static final PrintStream originalOut = System.out;

	@BeforeAll
	public static void setUpStreams()
	{
		System.setOut(new PrintStream(outContent));
	}

	@AfterAll
	public static void restoreStreams()
	{
		System.setOut(originalOut);
	}

	@Test
	public void out()
	{
		PrintOutRentalAgreementAction action = new PrintOutRentalAgreementAction();
		ActionState state = new ActionState();
		state.setTool(Tools.CHNS);
		state.setDays(4);
		state.setDate(new LocalDate(2023, 9, 1));
		state.setPercentage(20);
		state.setPre_discount_charge(20.00);
		state.setDiscount_amount(4.00);
		state.setDue_date(new LocalDate(2023, 9, 4));

		action.execute(state);
		assertEquals("\r\n"//
				+ "Rental Agreement\r\n"//
				+ "CHNS\r\n"//
				+ "CHAINSAW\r\n"//
				+ "Stihl\r\n"//
				+ "4\r\n"//
				+ "2023-09-01\r\n"//
				+ "2023-09-04\r\n"//
				+ "1.49\r\n"//
				+ "null\r\n"//
				+ "$20.0\r\n"//
				+ "20%\r\n"//
				+ "$4.0\r\n"//
				+ "$16.0\r\n"//
				+ "", outContent.toString());
	}
}
