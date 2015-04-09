package ui.config;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

public class TextCtrl extends JTextField
{
	public TextCtrl(int x, int y, int width, int height)
	{
		this.setBounds(x, y, width, height);
		this.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyReleased(KeyEvent e)
			{
				String str = KeyEvent.getKeyText(e.getKeyCode());
				setText(str);
			}
		});
	}
}
