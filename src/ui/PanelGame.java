package ui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * ��ϷPanel
 * @author Ben
 *
 */
public class PanelGame extends JPanel
{
//	private Lay lay_1 = new Lay(40, 32, 340, 282);
//	private Lay lay_2 = new Lay(40, 346, 340, 282);
//	private Lay lay_3 = new Lay(417, 32, 340, 596);
	
	private Lay lays[] = null;
	
	public PanelGame()
	{
		lays = new Lay[]
				{
					new Lay(40, 32, 340, 282),
					new Lay(40, 346, 340, 282),
					new Lay(417, 32, 340, 596),
					new Lay(794, 32, 340, 125),
					new Lay(794, 189, 165, 125),
					new Lay(969, 189, 165, 125),
					new Lay(794, 346, 340, 125),
					new Lay(794, 503, 340, 125),
				};
		
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
//		// ��ͼ
//		Image img = new ImageIcon("graphics/window/window.png").getImage();
//		// ͼƬ����
//		int imgW = img.getWidth(null);
//		int imgH = img.getHeight(null);
//		// ͼƬ��ʼ����
//		int x = 100;
//		int y = 100;
//		// �ǿ�
//		int size = 10;
//		// ��ͼ�ĳ���
//		int width = 300;
//		int height = 400;
//		// 1.����
//		g.drawImage(img, x, y, x+size, y+size, 0, 0, size, size, null);
//		// 2.��
//		g.drawImage(img, x+size, y, x-size+width, y+size, size, 0, imgW-size, size, null);
//		// 3.����
//		g.drawImage(img, x-size+width, y, x+width, y+size, imgW-size, 0, imgW, size, null);
//		// 4.����
//		g.drawImage(img, x, y+size, x+size, y-size+height, 0, size, size, imgH-size, null);
//		// 5.��
//		g.drawImage(img, x+size, y+size, x-size+width, y-size+height, size, size, imgW-size, imgH-size, null);
//		// 6.����
//		g.drawImage(img, x-size+width, y+size, x+width, y-size+height, imgW-size, size, imgW, imgH-size, null);
//		// 7.����
//		g.drawImage(img, x, y-size+height, x+size, y+height, 0, imgH-size, size, imgH, null);
//		// 8.��
//		g.drawImage(img, x+size, y-size+height, x-size+width, y+height, size, imgH-size, imgW-size, imgH, null);
//		// 9.����
//		g.drawImage(img, x-size+width, y-size+height, x+width, y+height, imgW-size, imgH-size, imgW, imgH, null);
		
//		lay_1.createWindow(g);
//		lay_2.createWindow(g);
//		lay_3.createWindow(g);
		
		for(int i = 0; i < lays.length; i++)
		{
			lays[i].createWindow(g);
		}
		
	}
}
