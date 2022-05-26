package entities;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.Date;

@Data
public class EFAccountCategory
{
	private int id;
	@SerializedName(value = "user_id")
	private int userId;
	@SerializedName(value = "created_at")
	private Date createdAt;
	@SerializedName(value = "updated_at")
	private Date updatedAt;
	private String name;

	@Override
	public boolean equals(Object o)
	{
		if (!(o instanceof EFAccountCategory other)) return false;
		return id == other.id;
	}
}
