package entities;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.Date;

/**
 * @author LostVirt
 * @created 20/12/2021 - 15:00
 * @project EFClient
 */
@Data
public class EFAccount
{
	private int id;
	@SerializedName(value = "user_id")
	private int userId;
	@SerializedName(value = "created_at")
	private Date createdAt;
	@SerializedName(value = "updated_at")
	private Date updatedAt;
	@SerializedName(value = "account_category_id")
	private int accountCategoryId;
	@SerializedName(value = "proxy_id")
	private int proxyId;
	private String username;
	private String password;
	private String notes;
	private String status;
	@SerializedName(value = "status_notes")
	private String statusNotes;
	@SerializedName(value = "date_of_birth")
	private Date dateOfBirth;
	@SerializedName(value = "ip_address")
	private String ipAddress;
	@SerializedName(value = "country_code")
	private String countryCode;
	@SerializedName(value = "recovery_email")
	private String recoveryEmail;
	@SerializedName(value = "display_name")
	private String displayName;
	private String type;
	@SerializedName(value = "bank_pin")
	private String bankPin;
	@SerializedName(value = "otp_key")
	private String otpKey;
	@SerializedName(value = "tutorial_status")
	private int tutorialStatus;
	@SerializedName(value = "total_level")
	private int totalLevel;
	@SerializedName(value = "combat_level")
	private int combatLevel;
	@SerializedName(value = "quest_points")
	private int questPoints;
	@SerializedName(value = "play_time")
	private int playTime;
	@SerializedName(value = "membership_days")
	private int membershipDays;
	private boolean registered;
	@SerializedName(value = "registered_at")
	private Date registeredAt;
	@SerializedName(value = "logged_in_at")
	private Date loggedInAt;
	@SerializedName(value = "last_checked_at")
	private Date lastCheckedAt;
	private EFProxy proxy;

	@Override
	public boolean equals(Object o)
	{
		if (!(o instanceof EFAccount other)) return false;
		return id == other.id;
	}
}
