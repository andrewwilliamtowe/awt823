package awt823.tooltype;

import lombok.Getter;

@Getter
public enum ToolType
{
	LADDER("LADDER", 1.99, true, true, false), //
	CHAINSAW("CHAINSAW", 1.49, true, false, true), //
	JACKHAMMER("JACKHAMMER", 2.99, true, false, false);

	private String code;
	private Double daily_charge;
	private boolean weekday_charge;
	private boolean weekend_charge;
	private boolean holiday_charge;

	private ToolType( String code, Double daily_charge, boolean weekday_charge, boolean weekend_charge, boolean holiday_charge )
	{
		this.code = code;
		this.daily_charge = daily_charge;
		this.weekday_charge = weekday_charge;
		this.weekend_charge = weekend_charge;
		this.holiday_charge = holiday_charge;
	}

	public static ToolType fromCode( String code, ToolType default_value )
	{
		if ( code == null )
			return default_value;

		for ( ToolType t : ToolType.values() )
		{
			if ( t.getCode().equalsIgnoreCase(code) )
				return t;
		}

		return default_value;
	}
}
