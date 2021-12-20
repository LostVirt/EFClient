package entities;

import lombok.Data;

/**
 * @author LostVirt
 * @created 20/12/2021 - 15:00
 * @project EFClient
 */
@Data
public class EFProxy
{
	private int id;
	private int user_id;
	private int proxy_category_id;
	private String type;
	private String country_code;
	private String name;
	private String ip_address;
	private int port;
	private String username;
	private String password;
	private boolean enabled;
}
