package dao;

import java.util.List;

import dto.Player;

/**
 * ����
 * @author Ben
 *
 */
public interface Data
{

	/**
	 * ��������
	 * @return
	 */
	public List<Player> loadData();
	
	/**
	 * �洢����
	 * @param players
	 */
	public void saveData(Player players);
	
	
}
