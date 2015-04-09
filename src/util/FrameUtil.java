package util;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * ������
 * @author Ben
 *
 */
public class FrameUtil
{
	public static void setFrameCenter(JFrame jf)
	{
		// ʹ��Toolkit����������λ��
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension d = toolkit.getScreenSize();
		int x = d.width - jf.getWidth() >> 1;
		int y = (d.height - jf.getHeight() >> 1) - 32;
		jf.setLocation(x, y);
	}
}
