package awt823.actions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.joda.time.LocalDate;
import org.junit.jupiter.api.Test;

import awt823.action_chain.ActionState;
import awt823.tools.Tools;

public class CalculateRentalDaysActionTest
{
	@Test
	public void testExecution()
	{
		CalculateRentalDaysAction action = new CalculateRentalDaysAction();

		// Test Base Case
		ActionState state = new ActionState();
		state.setTool(Tools.JAKR);
		state.setDate(new LocalDate(2023, 8, 28));
		state.setDays(4);
		action.execute(state);
		assertEquals(4, state.getCharge_days());
		assertEquals(new LocalDate(2023, 8, 31), state.getDue_date());

	}

	@Test
	public void testHolidayFourthOfJuly()
	{
		CalculateRentalDaysAction action = new CalculateRentalDaysAction();

		ActionState state = new ActionState();
		// Case 1
		state.setTool(Tools.CHNS);
		state.setDate(new LocalDate(2023, 7, 3));
		state.setDays(4);
		action.execute(state);
		assertEquals(4, state.getCharge_days());
		assertEquals(new LocalDate(2023, 7, 6), state.getDue_date());

		state = new ActionState();
		state.setTool(Tools.JAKR);
		state.setDate(new LocalDate(2023, 7, 3));
		state.setDays(4);
		action.execute(state);
		assertEquals(3, state.getCharge_days());
		assertEquals(new LocalDate(2023, 7, 6), state.getDue_date());

		// Case 2
		state.setTool(Tools.CHNS);
		state.setDate(new LocalDate(2021, 7, 2));
		state.setDays(4);
		action.execute(state);
		assertEquals(2, state.getCharge_days());
		assertEquals(new LocalDate(2021, 7, 5), state.getDue_date());

		state = new ActionState();
		state.setTool(Tools.JAKR);
		state.setDate(new LocalDate(2021, 7, 3));
		state.setDays(4);
		action.execute(state);
		assertEquals(1, state.getCharge_days());
		assertEquals(new LocalDate(2021, 7, 6), state.getDue_date());

		// Case 3
		state.setTool(Tools.CHNS);
		state.setDate(new LocalDate(2020, 7, 2));
		state.setDays(4);
		action.execute(state);
		assertEquals(2, state.getCharge_days());
		assertEquals(new LocalDate(2020, 7, 5), state.getDue_date());

		state = new ActionState();
		state.setTool(Tools.JAKR);
		state.setDate(new LocalDate(2021, 7, 2));
		state.setDays(4);
		action.execute(state);
		assertEquals(1, state.getCharge_days());
		assertEquals(new LocalDate(2021, 7, 5), state.getDue_date());
	}

	@Test
	public void testHolidayLaborDay()
	{
		CalculateRentalDaysAction action = new CalculateRentalDaysAction();

		ActionState state = new ActionState();
		state = new ActionState();
		state.setTool(Tools.CHNS);
		state.setDate(new LocalDate(2023, 9, 1));
		state.setDays(4);
		action.execute(state);
		assertEquals(2, state.getCharge_days());
		assertEquals(new LocalDate(2023, 9, 4), state.getDue_date());

		state = new ActionState();
		state.setTool(Tools.JAKR);
		state.setDate(new LocalDate(2023, 9, 1));
		state.setDays(4);
		action.execute(state);
		assertEquals(1, state.getCharge_days());
		assertEquals(new LocalDate(2023, 9, 4), state.getDue_date());
	}

	@Test
	public void testWeekendMath()
	{
		CalculateRentalDaysAction action = new CalculateRentalDaysAction();

		ActionState state = new ActionState();
		state = new ActionState();
		state.setTool(Tools.JAKR);
		state.setDate(new LocalDate(2023, 8, 4));
		state.setDays(4);
		action.execute(state);
		assertEquals(2, state.getCharge_days());
		assertEquals(new LocalDate(2023, 8, 7), state.getDue_date());

		state = new ActionState();
		state.setTool(Tools.LADW);
		state.setDate(new LocalDate(2023, 8, 4));
		state.setDays(4);
		action.execute(state);
		assertEquals(4, state.getCharge_days());
		assertEquals(new LocalDate(2023, 8, 7), state.getDue_date());
	}

	@Test
	public void testHolidayMath()
	{
		CalculateRentalDaysAction action = new CalculateRentalDaysAction();
		// baseline
		assertFalse(action.isHoliday(new LocalDate(2023, 8, 31)));

		// fourth of july math
		assertTrue(action.isHoliday(new LocalDate(2023, 7, 4)));
		assertTrue(action.isHoliday(new LocalDate(2021, 7, 5)));
		assertTrue(action.isHoliday(new LocalDate(2020, 7, 3)));

		// Labor Day Math
		assertTrue(action.isHoliday(new LocalDate(2023, 9, 4)));

	}

}
