package awt823.actions;

import awt823.action_chain.ActionState;

public class PrintOutRentalAgreementAction implements ChainAction
{

	@Override
	public void execute( ActionState state )
	{
		System.out.println();
		System.out.println("Rental Agreement");
		// Tool code - Specified at checkout
		System.out.println(state.getTool().getCode());
		// Tool type - From tool info
		System.out.println(state.getTool().getType().getCode());
		// Tool brand - From tool info
		System.out.println(state.getTool().getBrand());
		// Rental days - Specified at checkout
		System.out.println(state.getDays());
		// Check out date - Specified at checkout
		System.out.println(state.getDate());
		// Due date - Calculated from checkout date and rental days.
		System.out.println(state.getDue_date());
		// Daily rental charge - Amount per day, specified by the tool type.
		System.out.println(state.getTool().getType().getDaily_charge());
		// Charge days - Count of chargeable days, from day after checkout through and
		// including due date, excluding “no charge” days as specified by the tool type.
		System.out.println(state.getCharge_days());
		// Pre-discount charge - Calculated as charge days X daily charge. Resulting
		// total rounded half up to cents.

		System.out.println("$"
				+ state.getPre_discount_charge());
		// Discount percent - Specified at checkout.
		System.out.println(state.getPercentage()
				+ "%");
		// Discount amount - calculated from discount % and pre-discount charge.
		// Resulting amount rounded half up to cents.
		System.out.println("$"
				+ state.getDiscount_amount());

		// Final charge - Calculated as pre-discount charge - discount amount.

		System.out.println("$"
				+ state.getFinalCharge());

	}

}
