package control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


/**
 * 玩家控制器
 * @author Ben
 *
 */
public class PlayerControl extends KeyAdapter
{
	private GameControl gameControl;
	
	public PlayerControl(GameControl gameControl)
	{
		this.gameControl = gameControl;
	}

	
	/* 
	 * 键盘按下事件
	 */
	@Override
	public void keyPressed(KeyEvent e)
	{
		switch (e.getKeyCode())
		{
		// 按下上方向键
		case KeyEvent.VK_UP:
			this.gameControl.moveUp();
			break;
		// 按下下方向键
		case KeyEvent.VK_DOWN:
			this.gameControl.moveDown();
			break;
		// 按下左方向键
		case KeyEvent.VK_LEFT:
			this.gameControl.moveLeft();
			break;
		// 按下右方向键
		case KeyEvent.VK_RIGHT:
			this.gameControl.moveRight();
			break;
		default:
			break;
		}
	}
}
