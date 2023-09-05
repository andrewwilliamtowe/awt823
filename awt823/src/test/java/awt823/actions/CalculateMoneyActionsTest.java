package awt823.actions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import awt823.action_chain.ActionState;
import awt823.tools.Tools;

public class CalculateMoneyActionsTest
{

	@Test
	public void testExecution()
	{
		CalculateMoneyAction action = new CalculateMoneyAction();
		ActionState state = new ActionState();

		state.setCharge_days(5);
		state.setTool(Tools.CHNS);

		action.execute(state);
		assertEquals(state.getPre_discount_charge(), 7.45);
		assertEquals(state.getDiscount_amount(), 0);
		assertEquals(state.getFinalCharge(), 7.45);

		state.setCharge_days(1);
		state.setTool(Tools.CHNS);
		state.setPercentage(20);

		action.execute(state);
		assertEquals(state.getPre_discount_charge(), 1.49);
		assertEquals(state.getDiscount_amount(), .30); // actual math is .298. The rounding works
		assertEquals(state.getFinalCharge(), 1.19);

		state.setCharge_days(3);
		state.setTool(Tools.CHNS);
		state.setPercentage(20);

		action.execute(state);
		assertEquals(state.getPre_discount_charge(), 4.47);
		assertEquals(state.getDiscount_amount(), .89);
		assertEquals(state.getFinalCharge(), 3.58);
	}
}
