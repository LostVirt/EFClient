package result;

import lombok.Data;

import java.util.HashSet;

/**
 * @author LostVirt
 * @created 20/12/2021 - 15:00
 * @project EFClient
 */
@Data
public class PageResult<T>
{
	private boolean success;
	private HashSet<T> data;
}
