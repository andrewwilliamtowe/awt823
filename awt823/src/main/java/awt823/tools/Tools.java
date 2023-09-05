package awt823.tools;

import awt823.tooltype.ToolType;
import lombok.Getter;

@Getter
public enum Tools
{
	CHNS("CHNS", ToolType.CHAINSAW, "Stihl"), //
	LADW("LADW", ToolType.LADDER, "Werner"), //
	JAKD("JAKD", ToolType.JACKHAMMER, "DeWalt"), //
	JAKR("JAKR", ToolType.JACKHAMMER, "Ridgid");

	private String code;
	private ToolType type;
	private String brand;

	private Tools( String code, ToolType type, String brand )
	{
		this.code = code;
		this.type = type;
		this.brand = brand;
	}

	public static Tools fromCode( String code, Tools default_value )
	{
		if ( code == null )
			return default_value;

		for ( Tools t : Tools.values() )
		{
			if ( t.getCode().equalsIgnoreCase(code) )
				return t;
		}

		return default_value;
	}
}
