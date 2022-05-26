package entities;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.Date;

@Data
public class EFBot
{
	private int id;
	@SerializedName(value = "user_id")
	private int userId;
	@SerializedName(value = "created_at")
	private Date createdAt;
	@SerializedName(value = "updated_at")
	private Date updatedAt;
	private String type;
	private String name;
	private String username;
	private String password;
	@SerializedName(value = "memory_limit")
	private int memoryLimit;
	private String settings;

	@Override
	public boolean equals(Object o)
	{
		if (!(o instanceof EFBot other)) return false;
		return id == other.id;
	}
}
