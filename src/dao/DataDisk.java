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
	 * 本地记录文件
	 */
	private static final String FILE_PATH = "save\\local.txt";

	
	/**
	 * 读取本地记录
	 */
	@Override
	public List<Player> loadData()
	{
		// 对象输入流
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
	 * 保存记录到本地
	 */
	@Override
	public void saveData(Player player)
	{
		// 获取前五位存储
		List<Player> players = this.loadData();
		players.add(player);
		Collections.sort(players);
		if (players.size() > 5)
		{
			players.remove(5);
		}
		
		// 对象输出流
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
