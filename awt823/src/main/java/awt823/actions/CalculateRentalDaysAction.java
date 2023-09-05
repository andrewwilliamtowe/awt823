package awt823.actions;

import org.joda.time.LocalDate;

import awt823.action_chain.ActionState;

public class CalculateRentalDaysAction implements ChainAction
{

	@Override
	public void execute( ActionState state )
	{
		LocalDate end_date = state.getDate().plusDays(state.getDays()
				- 1);// Because this plusdays math is not inclusive, we have to subract 1
		state.setDue_date(end_date);
		Integer charge_days = 0;
		for ( LocalDate current_date = state.getDate(); current_date.isBefore(end_date) || current_date.isEqual(end_date); current_date = current_date.plusDays(1) )
		{
			if ( isHoliday(current_date) )
			{
				if ( state.getTool().getType().isHoliday_charge() )// Special cases
				{
					charge_days++;
				}
				continue;
			}
			if ( state.getTool().getType().isWeekday_charge() && current_date.dayOfWeek().get() <= 5 )// if it's a weekday
			{
				charge_days++;
			}
			else if ( state.getTool().getType().isWeekend_charge() && current_date.dayOfWeek().get() >= 6 )// if it's a weekend
			{
				charge_days++;
			}

		}
		state.setCharge_days(charge_days);
	}

	public boolean isHoliday( LocalDate current_date )
	{
		LocalDate freedom_day = new LocalDate(current_date.getYear(), 7, 4);// Fourth of July
		if ( freedom_day.dayOfWeek().get() == 6 && current_date.equals(new LocalDate(current_date.getYear(), 7, 3)) ) // Fourth of july is on a saturday, check the friday before
		{
			return true;
		}
		else if ( freedom_day.dayOfWeek().get() == 7 && current_date.equals(new LocalDate(current_date.getYear(), 7, 5)) )// Fourth of July is on a sunday. Check monday afterwards.
		{
			return true;
		}
		else if ( current_date.equals(freedom_day) && current_date.dayOfWeek().get() < 6 )// Fourth of July falls on a weekday
		{
			return true;
		}
		// Labor Day
		if ( current_date.getMonthOfYear() == 9 && current_date.getDayOfMonth() < 7 && current_date.getDayOfWeek() == 1 )
		{
			return true;
		}
		return false;
	}

}
