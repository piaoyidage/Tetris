package util;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * 工具类
 * @author Ben
 *
 */
public class FrameUtil
{
	public static void setFrameCenter(JFrame jf)
	{
		// 使用Toolkit来调整窗口位置
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension d = toolkit.getScreenSize();
		int x = d.width - jf.getWidth() >> 1;
		int y = (d.height - jf.getHeight() >> 1) - 32;
		jf.setLocation(x, y);
	}
}
