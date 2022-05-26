package entities;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.Date;

@Data
public class EFAgent
{
	private int id;
	@SerializedName(value = "user_id")
	private int userId;
	@SerializedName(value = "created_at")
	private Date createdAt;
	@SerializedName(value = "updated_at")
	private Date updatedAt;
	private String name;
	@SerializedName(value = "total_capacity")
	private int totalCapacity;
	@SerializedName(value = "active_tasks")
	private int activeTasks;
	private String status;
	private String version;

	@Override
	public boolean equals(Object o)
	{
		if (!(o instanceof EFAgent other)) return false;
		return id == other.id;
	}
}
