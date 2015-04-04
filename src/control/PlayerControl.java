package control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


/**
 * ��ҿ�����
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
	 * ���̰����¼�
	 */
	@Override
	public void keyPressed(KeyEvent e)
	{
		switch (e.getKeyCode())
		{
		// �����Ϸ����
		case KeyEvent.VK_UP:
			this.gameControl.moveUp();
			break;
		// �����·����
		case KeyEvent.VK_DOWN:
			this.gameControl.moveDown();
			break;
		// ���������
		case KeyEvent.VK_LEFT:
			this.gameControl.moveLeft();
			break;
		// �����ҷ����
		case KeyEvent.VK_RIGHT:
			this.gameControl.moveRight();
			break;
		default:
			break;
		}
	}
}
