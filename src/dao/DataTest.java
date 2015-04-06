package dao;

import java.util.ArrayList;
import java.util.List;

import dto.Player;

/**
 * 测试数据
 * @author Ben
 *
 */
public class DataTest implements Data
{

	@Override
	public List<Player> loadData()
	{
		List<Player> players = new ArrayList<Player>();
		players.add(new Player("张三", 100));
		players.add(new Player("张三", 2000));
		players.add(new Player("张三", 3000));
		players.add(new Player("张三", 4000));
		players.add(new Player("张三", 5000));
		
		return players;
	}

	@Override
	public void saveData(List<Player> players)
	{

	}

}
