package awt823.action_chain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;

import awt823.tools.Tools;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActionState
{
	// Input Data
	private List<String> errors = new ArrayList<String>();
	private Tools tool;
	private Integer days;
	private Integer percentage = 0;
	private LocalDate date;

	// Calculated Data
	private LocalDate due_date;
	private Integer charge_days;
	private Double pre_discount_charge;
	private Double discount_amount;

	public void addError( String error )
	{
		errors.add(error);
	}

	public boolean hasFailure()
	{
		return !errors.isEmpty();
	}

	public Double getFinalCharge()
	{
		return new BigDecimal(pre_discount_charge
				- discount_amount).setScale(2, RoundingMode.HALF_UP).doubleValue();
	}
}
