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
public class EFPrimeLinkRequest
{
	private int id;
	@SerializedName(value = "user_id")
	private int userId;
	@SerializedName(value = "created_at")
	private Date createdAt;
	@SerializedName(value = "updated_at")
	private Date updatedAt;
	@SerializedName(value = "account_id")
	private int accountId;
	@SerializedName(value = "proxy_category_id")
	private int proxyCategoryId;
	@SerializedName(value = "proxy_id")
	private int proxyId;
	@SerializedName(value = "prime_email")
	private String primeEmail;
	@SerializedName(value = "prime_password")
	private String primePassword;
	@SerializedName(value = "mail_password")
	private String mailPassword;
	private String status;
	@SerializedName(value = "failed_reason")
	private String failedReason;

	@Override
	public boolean equals(Object o)
	{
		if (!(o instanceof EFPrimeLinkRequest other)) return false;
		return id == other.id;
	}
}
