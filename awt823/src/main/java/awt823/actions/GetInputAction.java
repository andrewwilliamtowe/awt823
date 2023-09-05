package awt823.actions;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;

import awt823.action_chain.ActionState;
import awt823.tools.Tools;

public class GetInputAction implements ChainAction
{
	private static final String PATTERN = "MM/DD/YYYY";
	DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendPattern(PATTERN).toFormatter();
	Scanner reader;

	@Override
	public void execute( ActionState state )
	{
		reader = new Scanner(System.in);
		retrieveTool(state, reader);
		retrieveDays(state, reader);
		retrievePercentage(state, reader);
		retrieveCheckoutDate(state, reader);
		reader.close();

	}

	private void retrieveTool( ActionState state, Scanner reader )
	{

		Tools tool;
		do
		{
			System.out.println("Enter a code: ");
			String code = reader.next();
			tool = Tools.fromCode(code, null);
			if ( tool == null )
			{
				System.out.println("Invalid code. Please enter one of the following: "
						+ Arrays.stream(Tools.values()).map(e -> e.getCode()).collect(Collectors.joining(", ")));
			}

		}
		while ( tool == null );
		state.setTool(tool);
	}

	private void retrieveDays( ActionState state, Scanner reader )
	{
		reader.nextLine();// Clears out new line
		Integer days = 0;
		do
		{
			System.out.println("How Many Days: ");
			try
			{
				String line = reader.nextLine();
				days = Integer.parseInt(line);
				if ( days < 1 )
				{
					System.out.println("Invalid Days of use. Please enter 1 or more.");
				}
			}
			catch ( Exception e )
			{
				System.out.println("Invalid Days of use. Please enter a number");
			}
		}
		while ( days < 1 );
		state.setDays(days);
	}

	private void retrievePercentage( ActionState state, Scanner reader )
	{
		Integer percentage = -1;
		do
		{
			System.out.println("Discount Percentage: ");
			try
			{
				percentage = Integer.parseInt(reader.nextLine());
				if ( (percentage < 0 || percentage > 100) )
				{
					System.out.println("Invalid Percentage input. Please enter from [0-100]");
				}
			}
			catch ( Exception e )
			{
				System.out.println("Invalid Discount Percentage. Please enter a number");
			}

		}
		while ( percentage < 0 || percentage > 100 );
		state.setPercentage(percentage);

	}

	private void retrieveCheckoutDate( ActionState state, Scanner reader )
	{
		LocalDate date = null;
		do
		{
			System.out.println("Checkout Date: ");
			String checkout_date = reader.next();
			try
			{
				date = LocalDate.parse(checkout_date, formatter);
			}
			catch ( Exception e )
			{
				System.out.println("Invalid Format. Please use: "
						+ PATTERN);
			}
		}
		while ( date == null );
		state.setDate(date);
	}

}
