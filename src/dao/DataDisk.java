package dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dto.Player;

public class DataDisk implements Data
{
	/**
	 * ���ؼ�¼�ļ�
	 */
	private static final String FILE_PATH = "save\\local.txt";

	
	/**
	 * ��ȡ���ؼ�¼
	 */
	@Override
	public List<Player> loadData()
	{
		// ����������
		ObjectInputStream ois = null;
		List<Player> players = null;
	
		try
		{
			ois = new ObjectInputStream(new FileInputStream(FILE_PATH));
			players = (List<Player>)ois.readObject();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return players;
	}

	/**
	 * �����¼������
	 */
	@Override
	public void saveData(Player player)
	{
		// ��ȡǰ��λ�洢
		List<Player> players = this.loadData();
		players.add(player);
		Collections.sort(players);
		if (players.size() > 5)
		{
			players.remove(5);
		}
		
		// ���������
		ObjectOutputStream oos = null;
		try
		{
			oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH));
			oos.writeObject(players);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				oos.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	
}
