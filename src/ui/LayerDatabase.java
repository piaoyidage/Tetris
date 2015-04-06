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
public class LayerDatabase extends Layer
{

	/**
	 * 数据库 图片
	 */
	private static final Image IMG_DB = new ImageIcon("graphics/string/db.png").getImage();
	private static final int DB_HEIGHT = IMG_DB.getHeight(null);
	
	/**
	 * No Data 图片
	 */
	private static final Image IMG_NODATA = new ImageIcon("graphics/string/NoData.png").getImage();

	/**
	 * 5行数据库记录
	 */
	private static final int MAX_ROWS = 5;
	/**
	 * 两个值槽间距
	 */
	private final int MARGGING;
	
	
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
		
		// TODO 测试数据
		List<Player> players = this.dto.getDatabaseRecord();
		// 绘制数据库记录值槽
		for (int i = 0; i < MAX_ROWS; i++)
		{
			int expYStart = this.y + PADDING + MARGGING + DB_HEIGHT + i * (MARGGING+EXP_HEIGHT+4);
			int curPoint = this.dto.getCurPoint();
			int recordPoint = players.get(i).getPoint();
			this.drawProgressBar(expYStart, curPoint, recordPoint, g);
			
			// 绘制“No Data”
			g.drawImage(IMG_NODATA, this.x + (PADDING << 1), expYStart + 4, null);
			
			g.setColor(Color.white);
			//g.setFont(new Font("黑体", Font.BOLD, 32));
			g.drawString(recordPoint+"", this.x + EXP_WIDTH - (PADDING << 2), expYStart + 22);
		}
		
	}
	
}
