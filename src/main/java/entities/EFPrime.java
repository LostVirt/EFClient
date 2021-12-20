package entities;

import lombok.Data;

/**
 * @author LostVirt
 * @created 20/12/2021 - 15:00
 * @project EFClient
 */
@Data
public class EFPrime
{
	private int id;
	private int user_id;
	private int account_id;
	private int proxy_category_id;
	private int proxy_id;
	private String prime_email;
	private String prime_password;
	private String status;
	private String failed_reason;
}
