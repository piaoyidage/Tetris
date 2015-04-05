package main;

import service.GameService;
import ui.JFrameGame;
import ui.JPanelGame;
import config.GameConfig;
import control.GameControl;
import control.PlayerControl;
import dto.GameDto;

/**
 * �������
 * @author Ben
 *
 */
public class Main
{
	public static void main(String[] args)
	{
		// ������Ϸ����Դ
		GameDto dto = new GameDto();
		// ������Ϸ��壨��������Դ��
		JPanelGame panel = new JPanelGame(dto);
		// ������Ϸ�߼��㣨��������Դ��
		GameService gameService = new GameService(dto);
		// ������Ϸ��������������Ϸ������Ϸ�߼���
		GameControl gameControl = new GameControl(panel, gameService);
		// ������ҿ�������������Ϸ��������
		PlayerControl playControl = new PlayerControl(gameControl);
		// ��װ��ҿ�����
		panel.setGameControl(playControl);
		// ������Ϸ���棬��װ��Ϸ���
		new JFrameGame(panel).setTitle("����˹����");;
	}
}
