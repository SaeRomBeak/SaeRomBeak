package mymain;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GameOver extends JPanel {

	int w = 1165;
	int h = 855;
	
	static Image OVER = new ImageIcon("src/image/���ӿ���.jpg").getImage();
	//static Icon B1 = new ImageIcon("src/image/ee.png");
	//static Icon B2 = new ImageIcon("src/image/ee3.png");
	
	static Image img_retry = new ImageIcon("src/image/ee.png").getImage();
	static Image img_close = new ImageIcon("src/image/ee3.png").getImage();
	
	
	
	JPanel gameover; //��ü��ũ��
	//JButton close,replay;
	MyMain myMain;
	 
	//�ʱ�ȭ�κ�
	public GameOver() {
		setLayout(new BorderLayout());
		
		init_Overpan();
		
		setPreferredSize(new Dimension(w, h));
	}


	public GameOver(MyMain myMain) {
		this();
		this.myMain = myMain;
	}
	

	
	//���ӳ������ǻ���
	public void init_Overpan() {
		// TODO Auto-generated method stub
		
		gameover = new JPanel() {
			
			@Override
			protected void paintComponent(Graphics g) {
				// TODO Auto-generated method stub
				g.drawImage(MyImages.OVER, 0,0,w,h,gameover);
				
				g.drawImage(MyImages.img_retry, 328, 375, this);
				g.drawImage(MyImages.img_close, 612, 375, this);
			}
		};
		this.add(gameover);
		
		Rectangle  r_retry = new Rectangle(328, 375, 215, 100);
		Rectangle  r_close = new Rectangle(612, 375, 215, 100);
		
		gameover.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				Point pt = e.getPoint();
				if(r_retry.contains(pt)) {//�����
					myMain.changeTypingScreen2();
				}else if(r_close.contains(pt)) {//����
					
					int result = JOptionPane.showConfirmDialog(GameOver.this, 
							"���� �����Ͻðڽ��ϱ�?", "����",JOptionPane.YES_NO_OPTION);
					
					//Ŭ���ߴٸ�
					if (result !=JOptionPane.YES_OPTION) {
						return;
					} else if (result == JOptionPane.YES_OPTION) {
						System.exit(0);
					}
				}
				
			}
			
		});
		
	}
}