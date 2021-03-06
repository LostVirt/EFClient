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
public class EFProxy
{
	private int id;
	@SerializedName(value = "user_id")
	private int userId;
	@SerializedName(value = "created_at")
	private Date createdAt;
	@SerializedName(value = "updated_at")
	private Date updatedAt;
	@SerializedName(value = "proxy_category_id")
	private int proxyCategoryId;
	@SerializedName(value = "country_code")
	private String countryCode;
	private String type;
	private String name;
	@SerializedName(value = "ip_address")
	private String ipAddress;
	private int port;
	private String username;
	private String password;
	@SerializedName(value = "forwarder_port")
	private int forwarderPort;
	@SerializedName(value = "assignment_uses")
	private int assignmentUses;

	@Override
	public boolean equals(Object o)
	{
		if (!(o instanceof EFProxy other)) return false;
		return id == other.id;
	}
}

