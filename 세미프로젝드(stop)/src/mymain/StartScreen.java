package mymain;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class StartScreen extends JPanel {

	//전체화면의 넓이
	int w = 1165;
	int h = 855;
	
	JPanel screenpan;//전체스크린판
	JPanel gamePan; //전체스크린판
	JPanel bottom;
	
	JButton bt_start,bt_close; //버튼2개
	public static MyMain me2;
	
	
	static Image START = new ImageIcon("src/image/start.jpg").getImage();
	static Image BUTT = new ImageIcon("src/image/버튼부분.PNG").getImage();
	static Icon Jb_1 = new ImageIcon("src/image/Jb_Start.PNG");
	static Icon Jb_2 = new ImageIcon("src/image/Jb_Close.PNG");
	
	
	MyMain myMain;
	
	public StartScreen() {
		// TODO Auto-generated constructor stub
		setLayout(new BorderLayout());
		
		//화면
		init_gamePan();
		
		
		setPreferredSize(new Dimension(w, h));
	}
	
	public StartScreen(MyMain myMain) {
		// TODO Auto-generated constructor stub
		this();
		this.myMain = myMain;
	}

	//배경
	public void init_gamePan() {
	// TODO Auto-generated method stub
	
	screenpan = new JPanel(new BorderLayout());
	gamePan = new JPanel() {
		
	public void paintComponent(Graphics g) {
		g.drawImage(MyImages.START, 0, 0, w,h,gamePan);
		}
	};
	gamePan.setPreferredSize(new Dimension(w,h));
	screenpan.add(gamePan);
	this.add(screenpan);
			
	
	//버튼부분
	bottom= new JPanel() {
		
		@Override
		public void paintComponent(Graphics g) {
			// TODO Auto-generated method stub
			g.drawImage(MyImages.BUTT,0,0,w,h,bottom);
		}
	};

	bt_start = new JButton(MyImages.Jb_1);
	bt_start.setBorderPainted(false);
	bt_start.setFocusPainted(false);
	bt_start.setContentAreaFilled(false);
	
	
	bt_close = new JButton(MyImages.Jb_2);
	bt_close.setBorderPainted(false);
	bt_close.setFocusPainted(false);
	bt_close.setContentAreaFilled(false);
	
	bottom.add(bt_start);
	bottom.add(bt_close);
	
	screenpan.add(bottom,"South");
	

		ActionListener al = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (e.getSource() == bt_start) {
					
					repaint();
					myMain.changeTypingScreen();

				} else if (e.getSource() == bt_close) {
					int result = JOptionPane.showConfirmDialog(gamePan, 
							"정말 종료하시겠습니까?", "종료",JOptionPane.YES_NO_OPTION);
					
					//클릭했다면
					if (result !=JOptionPane.YES_OPTION) {
						return;
					} else if (result == JOptionPane.YES_OPTION) {
						System.exit(0);
					}
				}
			}
		};// al끝
		
		bt_start.addActionListener(al);
		bt_close.addActionListener(al);

	}
}
