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
	private int current_page;
	private String first_page_url;
	private int from;
	private int last_page;
	private String last_page_url;
	private String next_page_url;
	private String path;
	private int per_page;
	private String prev_page_url;
	private int to;
	private int total;
	private HashSet<T> data;
}
