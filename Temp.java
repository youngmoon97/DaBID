package project1;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


class AucButton extends JButton {
	
	public AucButton(int itemNum) {
		super("��Ű���");
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				
				System.out.println("aaa");
				
				// new ���â(itemNum);
				// ���â �ȿ��� DB ����ؼ� ��� ������ �����ͼ�
				// ȭ�� ���
				
			}
		});
		
		
	}
	
}

public class Temp {

	public static void main(String[] args) {
		JFrame jf =  new JFrame();
		jf.setSize(400, 400);
		JPanel jp = new JPanel();
		
		int[] items = {1, 2, 3};
		
		for (int i = 0; i < items.length; i++) {
			JButton jb= new AucButton(items[i]);
			jp.add(jb);
		}
		
		jf.getContentPane().add(jp);
		jf.setVisible(true);
	}
	
}
