package dao;

import java.util.List;

import dto.Player;

/**
 * 数据
 * @author Ben
 *
 */
public interface Data
{

	/**
	 * 加载数据
	 * @return
	 */
	public List<Player> loadData();
	
	/**
	 * 存储数据
	 * @param players
	 */
	public void saveData(Player players);
	
	
}
