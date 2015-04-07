package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;

import dto.Player;

/**
 * 游戏数据库
 * @author Ben
 *
 */
public class LayerDatabase extends LayerData
{

	
	public LayerDatabase(int x, int y, int width, int height)
	{
		super(x, y, width, height);
		
		MARGGING = (this.height - DB_HEIGHT - (EXP_HEIGHT+4) * MAX_ROWS - (PADDING << 1)) / MAX_ROWS;
	}

	@Override
	public void paint(Graphics g)
	{
		this.createWindow(g);
		g.drawImage(IMG_DB, this.x + PADDING, this.y + PADDING, null);
		// 绘制数据库记录值槽
		List<Player> players = this.dto.getDatabaseRecord();
		this.showData(players, g);
	}
	
}
