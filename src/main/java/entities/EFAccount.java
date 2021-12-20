package entities;

import lombok.Data;

/**
 * @author LostVirt
 * @created 20/12/2021 - 15:00
 * @project EFClient
 */
@Data
public class EFAccount
{
	private int id;
	private int proxy_id;
	private int active_agent_id;
	private int active_task_id;
	private int account_category_id;
	private int email_verified;
	private String country_code;
	private String email;
	private String password;
	private String recovery_email;
	private String script;
	private int enabled;
	private String notes;
	private String status;
	private String status_notes;
	private EFProxy proxy;


	@Override
	public boolean equals(Object o)
	{
		if (!(o instanceof EFAccount other)) return false;
		return email.equals(other.email);
	}
}
