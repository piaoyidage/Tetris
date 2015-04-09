package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dto.Player;

public class DataBase implements Data
{
	// 驱动名
	private final String DB_DRIVER;
	// url
	private final String DB_URL;
	// 用户名
	private final String DB_USER;
	// 密码
	private final String DB_PASSWD;
	
	private static String SELECT_SQL = "select username, point from user_point where type_id=1 order by point desc limit 5";
	private static String INSERT_SQL = "insert into user_point (`username`, `point`, `type_id`) values (?, ?, ?)";
	
	
	public DataBase(HashMap<String, String> map)
	{
		DB_DRIVER = map.get("dbDriver");
		DB_URL = map.get("dbUrl");
		DB_USER = map.get("dbUser");
		DB_PASSWD = map.get("dbPasswd");
		try
		{
			// 加载驱动
			Class.forName(DB_DRIVER);
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public List<Player> loadData()
	{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Player> players = new ArrayList<Player>();
		try
		{
			// 获取连接
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);
			// 执行SQL语句
			ps = conn.prepareStatement(SELECT_SQL);
			rs = ps.executeQuery();
			while (rs.next())
			{
				String username = rs.getString(1);
				int point = rs.getInt(2);
				players.add(new Player(username, point));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (conn != null)
				{
					conn.close();
				}
				if (ps != null)
				{
					ps.close();
				}
				if (rs != null)
				{
					rs.close();
				}
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return players;
	}

	@Override
	public void saveData(Player player)
	{
		Connection conn = null;
		PreparedStatement ps = null;
		
		try
		{
			// 获取连接
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);
			// 执行SQL语句
			ps = conn.prepareStatement(INSERT_SQL);
			ps.setString(1, player.getUsername());
			ps.setInt(2, player.getPoint());
			ps.setInt(3, 1);
			ps.execute();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (conn != null)
				{
					conn.close();
				}
				if (ps != null)
				{
					ps.close();
				}
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
	
}
