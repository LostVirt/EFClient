package result;

import lombok.Data;

/**
 * @author LostVirt
 * @created 20/12/2021 - 15:00
 * @project EFClient
 */
@Data
public class GetResult<T>
{
	private boolean success;
	private T data;
}
