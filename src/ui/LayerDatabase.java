package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;

import dto.Player;

/**
 * ��Ϸ���ݿ�
 * @author Ben
 *
 */
public class LayerDatabase extends Layer
{

	/**
	 * ���ݿ� ͼƬ
	 */
	private static final Image IMG_DB = new ImageIcon("graphics/string/db.png").getImage();
	private static final int DB_HEIGHT = IMG_DB.getHeight(null);
	
	/**
	 * No Data ͼƬ
	 */
	private static final Image IMG_NODATA = new ImageIcon("graphics/string/NoData.png").getImage();

	/**
	 * 5�����ݿ��¼
	 */
	private static final int MAX_ROWS = 5;
	/**
	 * ����ֵ�ۼ��
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
		
		// TODO ��������
		List<Player> players = this.dto.getDatabaseRecord();
		// �������ݿ��¼ֵ��
		for (int i = 0; i < MAX_ROWS; i++)
		{
			int expYStart = this.y + PADDING + MARGGING + DB_HEIGHT + i * (MARGGING+EXP_HEIGHT+4);
			int curPoint = this.dto.getCurPoint();
			int recordPoint = players.get(i).getPoint();
			this.drawProgressBar(expYStart, curPoint, recordPoint, g);
			
			// ���ơ�No Data��
			g.drawImage(IMG_NODATA, this.x + (PADDING << 1), expYStart + 4, null);
			
			g.setColor(Color.white);
			//g.setFont(new Font("����", Font.BOLD, 32));
			g.drawString(recordPoint+"", this.x + EXP_WIDTH - (PADDING << 2), expYStart + 22);
		}
		
	}
	
}
