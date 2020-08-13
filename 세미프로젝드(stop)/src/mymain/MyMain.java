package mymain;

import java.awt.CardLayout;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyMain extends JFrame {
   
   int w = 1165, h = 855;  //��üȭ���� ũ�⼳��
   
  
  JPanel mainPanel;         //����������
  CardLayout card;       
  
  Typing typing;
  StartScreen startScreen;
  GameOver gameOver;        //��ȭ���� �����ڸ� �ҷ���
   

  
  public void changeTypingScreen() {
     card.show(mainPanel, "typing");
     typing.start();
   
     
  } 
  
  public void changeTypingScreen2() { 
    typing.on_overgame_new(); 
     card.show(mainPanel, "typing");
     typing.start();
   
  }
  
  public void changeGameOverScreen() {  
     card.show(mainPanel, "over");
  }
  
  
  
  public MyMain() {   
   
      super("Ÿ���ΰ���");
      //this.add(new StartScreen());
      
      
     //����ȭ�� ������...
     init_images();
      
      card = new CardLayout();
      mainPanel = new JPanel(card);

      mainPanel.add("start",new StartScreen(this));
      mainPanel.add("typing",typing = new Typing(this));
      mainPanel.add("over", new GameOver(this));
      
      this.add(mainPanel,"Center");
      
      super.setLocation(100, 100);
      
      super.setSize(w,h);
      setResizable(false);
      
      
      
      super.setVisible(true);
      super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
   }
   
   private void init_images() {
	// TODO Auto-generated method stub
	   
	   try {
	    URL url = getClass().getClassLoader().getResource("image/start.jpg");
		MyImages.START = new ImageIcon(url).getImage();
		
		System.out.println(MyImages.START);
		
		
		url = getClass().getClassLoader().getResource("image/��ư�κ�.PNG");
		MyImages.BUTT = new ImageIcon(url).getImage();
		
		url = getClass().getClassLoader().getResource("image/Jb_Start.PNG");
		MyImages.Jb_1 = new ImageIcon(url);
		
		url = getClass().getClassLoader().getResource("image/Jb_Close.PNG");
		MyImages.Jb_2 = new ImageIcon(url);
		
		
		
		//Typing
		url = getClass().getClassLoader().getResource("image/1.PNG");
		MyImages.PANBACK = new ImageIcon(url).getImage();
		
		
		url = getClass().getClassLoader().getResource("image/�Ʒ��κ�.png");
		MyImages.TYPING = new ImageIcon(url).getImage();
		

		url = getClass().getClassLoader().getResource("image/������.PNG");
		MyImages.SCORE = new ImageIcon(url).getImage();
		
		
		url = getClass().getClassLoader().getResource("image/6.png");
		MyImages.HEART = new ImageIcon(url).getImage();
		
		
		
		//gameover
		url = getClass().getClassLoader().getResource("image/���ӿ���.jpg");
		MyImages.OVER = new ImageIcon(url).getImage();
		
		url = getClass().getClassLoader().getResource("image/ee.png");
		MyImages.img_retry = new ImageIcon(url).getImage();
		
		url = getClass().getClassLoader().getResource("image/ee3.png");
		MyImages.img_close= new ImageIcon(url).getImage();
		
	   }catch (Exception e) {
		// TODO: handle exception
		   e.printStackTrace();
	}
		
		/*
		static Image img_retry = new ImageIcon("src/image/ee.png").getImage();
		static Image img_close = new ImageIcon("src/image/ee3.png").getImage();
		
	*/	
		
		
}

public static void main(String[] args) {
      // TODO Auto-generated method stub

      new MyMain();

   }

}
   