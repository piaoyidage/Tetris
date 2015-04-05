package dto;

/**
 * @author Ben
 * 游戏玩家信息，用户名和得分
 */
public class Player
{
	private String username;
	
	private int point;

	public Player(String username, int point)
	{
		super();
		this.username = username;
		this.point = point;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public int getPoint()
	{
		return point;
	}

	public void setPoint(int point)
	{
		this.point = point;
	}
}
