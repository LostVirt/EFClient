package entities;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.Date;

@Data
public class EFTask
{
	private int id;
	@SerializedName(value = "user_id")
	private int userId;
	@SerializedName(value = "created_at")
	private Date createdAt;
	@SerializedName(value = "updated_at")
	private Date updatedAt;
	@SerializedName(value = "bot_id")
	private int botId;
	private String name;
	@SerializedName(value = "script_name")
	private String scriptName;
	@SerializedName(value = "script_args")
	private String scriptArgs;
	@SerializedName(value = "script_args_delimiter")
	private String scriptArgsDelimiter;
	@SerializedName(value = "append_account_id_arg")
	private boolean appendAccountIdArg;
	@SerializedName(value = "append_api_key_arg")
	private boolean appendApiKeyArg;
	@SerializedName(value = "close_on_script_stop")
	private boolean closeOnScriptStop;
	@SerializedName(value = "relaunch_on_script_stop")
	private boolean relaunchOnScriptStop;
	@SerializedName(value = "max_duration")
	private int maxDuration;
	@SerializedName(value = "max_launch_duration")
	private int maxLaunchDuration;
	@SerializedName(value = "world_selector_type")
	private boolean worldSelectorType;
	@SerializedName(value = "world_id")
	private int worldId;
	private boolean enabled;
	private boolean continuous;
	@SerializedName(value = "parallel_limit")
	private int parallelLimit;
	@SerializedName(value = "account_required")
	private boolean accountRequired;
	@SerializedName(value = "create_account")
	private boolean createAccount;
	@SerializedName(value = "unlock_account")
	private boolean unlockAccount;
	@SerializedName(value = "account_country_code")
	private String accountCountryCode;
	@SerializedName(value = "account_category_id")
	private int accountCategoryId;
	@SerializedName(value = "continuous_account_category_id")
	private int continuousAccountCategoryId;

	@Override
	public boolean equals(Object o)
	{
		if (!(o instanceof EFTask other)) return false;
		return id == other.id;
	}
}
