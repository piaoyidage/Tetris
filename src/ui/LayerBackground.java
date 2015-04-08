package ui;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class LayerBackground extends Layer
{
	// TODO 临时背景
	private static final Image IMG_BG_TEMP = new ImageIcon("graphics/background/bg.png").getImage();
	
	private static List<Image> IMG_BG;
	
	static
	{
		// 获取文件夹下的所有背景图片
		File dir = new File("graphics/background");
		File[] files = dir.listFiles();
		IMG_BG = new ArrayList<Image>();
		for (File file : files)
		{
			if (!file.isDirectory())
			{
				IMG_BG.add(new ImageIcon(file.getPath()).getImage());
			}
		}
	}

	public LayerBackground(int x, int y, int width, int height)
	{
		super(x, y, width, height);
	}

	@Override
	public void paint(Graphics g)
	{
		g.drawImage(IMG_BG.get(1), 0, 0, null);
	}

}
