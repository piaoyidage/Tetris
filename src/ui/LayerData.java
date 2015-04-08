package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;

import dto.Player;

/**
 * LayerDatabase���LayerDisk��ĸ���
 * @author Ben
 *
 */
public abstract class LayerData extends Layer
{
	/**
	 * ���ݿ� ͼƬ
	 */
	protected static final Image IMG_DB = new ImageIcon("graphics/string/db.png").getImage();
	protected static final int DB_HEIGHT = IMG_DB.getHeight(null);
	
	/**
	 * No Data ͼƬ
	 */
	protected static final Image IMG_NODATA = new ImageIcon("graphics/string/NoData.png").getImage();

	/**
	 * 5�����ݿ��¼
	 */
	protected static final int MAX_ROWS = 5;
	
	/**
	 * ����ֵ�ۼ��
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
	 * @param players �����Ϣ
	 * @param height ���ݿ�ͼƬ���߱��ؼ�¼ͼƬ�ĸ߶�
	 * @param g
	 */
	public void showData(List<Player> players, Graphics g)
	{
		// TODO ����
		// ���Ƽ�¼ֵ��
		for (int i = 0; i < MAX_ROWS; i++)
		{
			int expYStart = this.y + PADDING + MARGGING + DB_HEIGHT + i
					* (MARGGING + EXP_HEIGHT + 4);
			int curPoint = this.dto.getCurPoint();
			int recordPoint = players.get(i).getPoint();
			this.drawProgressBar(expYStart, curPoint, recordPoint, g);

			// ���ơ�No Data�� ����¼����5��
			if (players.get(i).getUsername().equals("no data"))
			{
				g.drawImage(IMG_NODATA, this.x + (PADDING << 1), expYStart + 4, null);
			}
			else
			{
				g.setColor(Color.red);
				g.setFont(new Font("���Ŀ���", Font.BOLD, 22));
				// ��������
				g.drawString(players.get(i).getUsername(), this.x + (PADDING << 1), expYStart + 22);
				// ���Ʒ���
				g.drawString(recordPoint + "", this.x + EXP_WIDTH - (PADDING << 2),
						expYStart + 22);
			}
		}
	}

}
