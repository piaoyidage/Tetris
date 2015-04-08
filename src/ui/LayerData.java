package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;

import dto.Player;

/**
 * LayerDatabase类和LayerDisk类的父类
 * @author Ben
 *
 */
public abstract class LayerData extends Layer
{
	/**
	 * 数据库 图片
	 */
	protected static final Image IMG_DB = new ImageIcon("graphics/string/db.png").getImage();
	protected static final int DB_HEIGHT = IMG_DB.getHeight(null);
	
	/**
	 * No Data 图片
	 */
	protected static final Image IMG_NODATA = new ImageIcon("graphics/string/NoData.png").getImage();

	/**
	 * 5行数据库记录
	 */
	protected static final int MAX_ROWS = 5;
	
	/**
	 * 两个值槽间距
	 */
	protected int MARGGING = 0;

	protected LayerData(int x, int y, int width, int height)
	{
		super(x, y, width, height);
	}

	@Override
	public abstract void paint(Graphics g);
	
	/**
	 * 
	 * @param players 玩家信息
	 * @param height 数据库图片或者本地记录图片的高度
	 * @param g
	 */
	public void showData(List<Player> players, Graphics g)
	{
		// TODO 测试
		// 绘制记录值槽
		for (int i = 0; i < MAX_ROWS; i++)
		{
			int expYStart = this.y + PADDING + MARGGING + DB_HEIGHT + i
					* (MARGGING + EXP_HEIGHT + 4);
			int curPoint = this.dto.getCurPoint();
			int recordPoint = players.get(i).getPoint();
			this.drawProgressBar(expYStart, curPoint, recordPoint, g);

			// 绘制“No Data” 当记录不够5个
			if (players.get(i).getUsername().equals("no data"))
			{
				g.drawImage(IMG_NODATA, this.x + (PADDING << 1), expYStart + 4, null);
			}
			else
			{
				g.setColor(Color.red);
				g.setFont(new Font("华文楷体", Font.BOLD, 22));
				// 绘制姓名
				g.drawString(players.get(i).getUsername(), this.x + (PADDING << 1), expYStart + 22);
				// 绘制分数
				g.drawString(recordPoint + "", this.x + EXP_WIDTH - (PADDING << 2),
						expYStart + 22);
			}
		}
	}

}
