package awt823.actions;

import java.math.BigDecimal;
import java.math.RoundingMode;

import awt823.action_chain.ActionState;

public class CalculateMoneyAction implements ChainAction
{

	@Override
	public void execute( ActionState state )
	{
		state.setPre_discount_charge(state.getTool().getType().getDaily_charge() * state.getCharge_days());

		state.setDiscount_amount(new BigDecimal(state.getPre_discount_charge() * state.getPercentage() / 100).setScale(2, RoundingMode.HALF_UP).doubleValue());

	}

}
