package awt823.action_chain;

import java.util.ArrayList;
import java.util.List;

import awt823.actions.CalculateMoneyAction;
import awt823.actions.CalculateRentalDaysAction;
import awt823.actions.ChainAction;
import awt823.actions.GetInputAction;
import awt823.actions.PrintOutRentalAgreementAction;

public class PointOfSaleActionChain
{
	private List<ChainAction> actions = new ArrayList<ChainAction>();
	private ActionState action_state = new ActionState();

	public PointOfSaleActionChain()

	{
		actions.add(new GetInputAction());
		actions.add(new CalculateRentalDaysAction());
		actions.add(new CalculateMoneyAction());
		actions.add(new PrintOutRentalAgreementAction());
	}

	public void execute()
	{
		for ( ChainAction action : actions )
		{
			try
			{
				action.execute(action_state);
			}
			catch ( Exception e )
			{
				action_state.addError(e.getMessage());
			}
			if ( action_state.hasFailure() )
			{
				System.out.println("Error Occured: "
						+ action_state.getErrors().get(0)
						+ ". Exiting");
				System.exit(1);
			}
		}
	}
}
