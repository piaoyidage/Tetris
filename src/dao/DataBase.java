package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Player;

public class DataBase implements Data
{
	// 驱动名
	private static String DB_DRIVER = "com.mysql.jdbc.Driver";
	// url
	private static String DB_URL = "jdbc:mysql://localhost:3306/tetris_record";
	// 用户名
	private static String DB_USER = "root";
	// 密码
	private static String DB_PASSWD = "root";
	
	private static String LOAD_SQL = "select username, point from user_point where type_id=1 order by point desc limit 5";
	private static String INSERT_SQL = "insert into user_point (`username`, `point`, `type_id`) values (?, ?, ?)";
	
	static 
	{
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
			ps = conn.prepareStatement(LOAD_SQL);
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
