package ui;

import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;

import dto.Player;

/**
 * ��Ϸ���ؼ�¼
 * @author Ben
 *
 */
public class LayerDisk extends LayerData
{

	private static final Image IMG_DISK = new ImageIcon("graphics/string/disk.png").getImage();
	
	public LayerDisk(int x, int y, int width, int height)
	{
		super(x, y, width, height);
		
		MARGGING = (this.height - DB_HEIGHT - (EXP_HEIGHT+4) * MAX_ROWS - (PADDING << 1)) / MAX_ROWS;
	}

	@Override
	public void paint(Graphics g)
	{
		this.createWindow(g);
		g.drawImage(IMG_DISK, this.x + PADDING, this.y + PADDING, null);
		// ��ʾ���ؼ�¼
		List<Player> players = this.dto.getDatabaseRecord();
		this.showData(players, g);
	}

}
