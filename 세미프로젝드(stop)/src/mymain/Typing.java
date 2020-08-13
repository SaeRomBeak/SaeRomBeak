package mymain;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

//Ÿ�ڿ�������â
public class Typing extends JPanel {

   int w = 1165, h = 855; // ��üũ��

   static Image PANBACK = new ImageIcon("src/image/1.PNG").getImage(); // �ܾ���
   static Image TYPING = new ImageIcon("src/image/�Ʒ��κ�.png").getImage(); // �Է���
   static Image SCORE = new ImageIcon("src/image/������.PNG").getImage(); // �Է���
   static Image HEART = new ImageIcon("src/image/6.png").getImage(); // �Է���

   BorderLayout border;// ���� layout

   JPanel centerPanel;// �ܾ��������+Ÿ������
   JPanel pan; // �ܾ������ ��
   JPanel Typing; // Ÿ������
   JPanel score; // ������

    int SCREEN_WIDTH  = w-300;   //�ܾ �������� ���ǳ���
    int SCREEN_HEIGHT = h-50;    //�ܾ �������� ���� ����
    int MAX_LEVEL;              //�ִ��� ����

   JButton stop, replay, close; // ��ø��ߴ� ��ư

   JTextField typing;       // �ܾ�ġ�°�

   FontMetrics fm;
   
   boolean bAdd=false;
   ThreadGroup virusGrp = new ThreadGroup("virus");  //���̷����� �����Ǵ� �����庯��
 
   
   // ----------------------------------------------------------------------//
   String[][] work = { { "�ٺ�", "����ϴ�", "�ݹ�", "������", "��������", "���ΰ�", "�հ���", "���÷�", "�����ϴ�", "��������", "�����ϴ�", "����ɴ�", "����"},            //1
                  {"�پ��", "�����ִ�", "����ϴ�", "��ȣ�ϴ�", "���ϴ�", "���ڴ�", "��ǳ��", "�ڹ�", "�ؾ", "�����ϴ�", "�����ϴ�", "�ݴ��ϴ�", "��ġ�ϴ�", "�����Ǵ�"},//2
                  {"��¦", "����", "��", "��", "��", "å", "����", "����", "�ҳ���", "����", "�ʷ�", "�Ͼ��", "���", "�ܵ����", "�����ϴ�", "���Ҹ�", "����ʹ�"},   //3
                  {"��Ȯ�ϴ�", "�ѹ��ѹ�", "����", "�������", "���", "����", "�̺�", "���","�ݿ���","��ǻ��","���ݸ�","�Ӹ���","���ܸ�","������" }, };                //4

   final Level[] LEVEL = { new Level(500, 2000, 1000, work[0]),
                      new Level(500, 1500, 3000, work[1]), 
                      new Level(300, 1000, 10000, work[2]),
                      new Level(200, 500, 20000,  work[3]) };
   
   
   List words = new ArrayList();


   int level = 1;           // ���ӷ���
   int life = 3;           // ���ӻ���              // work[]�迭��������� �ʱ�ȭ�κ�
   int speed = 100;       // ���ǵ�
   int interval = 1*1000;
   int scorenum = 0;       // �ܾ��� ����

   Label lbLevel = new Label("����:" + level, Label.CENTER);
      
   boolean isPlaying = false;
   Random random = new Random();

   Font font = new Font("����ü", Font.BOLD, 15);
   Font font1 = new Font("����ü", Font.BOLD, 30);

   MyMain myMain; // �����ǵ���
   
   
   Timer swing_timer;

   
   // ������ �ʱ�ȭ�κ�------------------------------------------------------------------
   public Typing() {

      // TODO Auto-generated constructor stub
      setLayout(new BorderLayout());

      init_centerPanel(); // �ܾ������ȭ���ʱ�ȭ

      init_score(); // �������ʱ�

      init_timer();

   }


  // Main JFram�� �����Ǵ� ������ �߰�
   public Typing(MyMain myMain) {

      this();
      this.myMain = myMain;
   }

   
   public void start() {
	 
	   typing.requestFocus();
		  swing_timer.start();  
	  }
   
  
   
   
   private void init_timer() {
	// TODO Auto-generated method stub
	   ActionListener listener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			rain_word();//���ڻ���
			rain_drop();//���ڳ�����
			
			repaint();
		}
	   };
	   
	   swing_timer = new Timer(interval, listener);
   }
   
   
   protected void rain_word() {
		// TODO Auto-generated method stub
		String[] data = LEVEL[getCurLevel()].work;
       int rand = (int) (Math.random() * data.length);// ���� ����
       
       // �� 10���� �ѹ� �÷� ���̷����� �����Ѵ�.

       boolean isVirus = (random.nextInt(10) + 1) / 10 != 0;
       Word word = new Word(data[rand], isVirus);
                    
       words.add(word); 
	}

   
   
   
	protected void rain_drop() {
		// TODO Auto-generated method stub
		for (int i = 0; i < words.size(); i++) {

            Word tmp = (Word) words.get(i);            
            tmp.y += tmp.step+10;
            //System.out.printf("%s : (%d,%d)\n",tmp.word,tmp.x,tmp.y);

            if (tmp.y >= SCREEN_HEIGHT-20)
            {
               //tmp.y = SCREEN_HEIGHT;
               words.remove(tmp);
               life--;//�����ɷ� �������� ����
               break;
            }

            //�����ɷ��� 0���ϸ� ���ӿ���
            if (life <= 0) {
               myMain.changeGameOverScreen();
               swing_timer.stop();
               repaint();
               
            }

         } // for
	}
	
	
	public Level getLevel(int level) {

	      if (level > MAX_LEVEL)
	         level = MAX_LEVEL;

	      if (level < 0)
	         level = 0;

	      return LEVEL[level];
	   }

	   public boolean levelUpCheck() {

	      Level lvl = getLevel(level);
	      return scorenum >= lvl.levelUpScore;

	   }

	   
	   public synchronized int getCurLevel() {
	      return level;

	   }

	   
	   // ������
	   public synchronized void levelUp() {

	      virusGrp.interrupt();
	      Level lvl = getLevel(++level);
	      lbLevel.setText("Level:" + level);

	      words.clear();
	      //pan.clear();

	     
	      try {						//������������ �ӵ�����
	    	  showLevel(level);
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	      speed = lvl.speed;
	      interval = lvl.interval;

	   }

	   // ������ �ӵ�

	   public void showLevel(int level) {

	      String tmp = "Level " + level;
	      showTitle(tmp, 1 * 2000);

	   }

	   
	   
	   public void showTitle(String title, int time) {

	      Graphics g = pan.getGraphics();
	      Font titleFont = new Font("����ü", Font.BOLD, 50);
	      g.setColor(Color.white);
	      g.setFont(titleFont);

	      FontMetrics fm = pan.getFontMetrics(titleFont);
	      int width = fm.stringWidth(title);
	      g.drawString(title, (SCREEN_WIDTH - width) / 2, SCREEN_HEIGHT / 2);
	    

	   }
	
	
	
	
   // �ܾ���+�Է����ʱ�ȭ�κ�---------------------------------------------------------------------
   public void init_centerPanel() {

      // TODO Auto-generated method stub
      // �ܾ������ �� + �Է�â ���� ��������
      centerPanel = new JPanel(new BorderLayout());

      // �ܾ������ �ǻ���
      pan = new JPanel() {
         
         
         @Override
         protected void paintComponent(Graphics g) {
            // TODO Auto-generated method stub
            g.drawImage(MyImages.PANBACK, 0, 0,w-300,h,pan);
         
            for (int i = 0; i < words.size(); i++)
            {
             
               Word tmp = (Word) words.get(i);
               
               g.setColor(tmp.color);
               g.setFont(font);
               
               g.drawString(tmp.word, tmp.x, tmp.y);
              
            }
         }
      };
      
      pan.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
      centerPanel.add(pan, "Center");

      this.add(centerPanel);

      // �ܾ����� ��(�Է���)
      Typing = new JPanel() {
         @Override
         public void paintComponent(Graphics g) {
            // TODO Auto-generated method stub
            g.drawImage(MyImages.TYPING, 0, 0, w, 50, Typing);

         }
      };
      Typing.setPreferredSize(new Dimension(w, 50));

      JLabel input = new JLabel("�ܾ�:");
      input.setForeground(Color.white);
      typing = new JTextField(20);

      Typing.add(input);
      Typing.add(typing);
      centerPanel.add(Typing, "South");

      MyEventHandler handler = new MyEventHandler();// �۾��� �̺�Ʈ
      //addWindowListener(handler);
      typing.addActionListener(handler);
      //SCREEN_WIDTH = pan.getWidth();
      //SCREEN_HEIGHT = pan.getHeight();
      MAX_LEVEL = LEVEL.length - 1;

      fm = pan.getFontMetrics(getFont());

   }

   // ������ �ʱ�ȭ�κ�------------------------------------------------------------------------
   public void init_score() {
      // TODO Auto-generated method stub

      border = new BorderLayout();
      score = new JPanel(border) {

         @Override
         public void paintComponent(Graphics g) {
            // TODO Auto-generated method stub
            g.drawImage(MyImages.SCORE, 0, 0, 300, h,score);
            
            g.setFont(font1);
            String str_score = String.format("%04d(��)", scorenum);
            g.drawString(str_score, 95, 180);
         
            heart_life(g);
            
         }

      
      };
      
      score.setPreferredSize(new Dimension(300, 0));
      this.add(score, "East");

      // ��ư�� �κ�
      JPanel but = new JPanel();
      replay = new JButton("���ν���");
      close = new JButton("������");
      stop = new JButton("�Ͻ�����");


      but.add(replay);
      but.add(stop);
      but.add(close);
      
      score.add(but, "South");
      
      
     
      
      ActionListener al = new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            if (e.getSource() == replay) { 
            	
            	//myMain.changeStartScreen();
            	on_game_new();
            	
               
            } else if (e.getSource() == stop) {
            	
            	on_game_pause();
            	
            	
			} else if (e.getSource() == close) {
				
				on_game_exit();
				
			}
		}
	};
	replay.addActionListener(al);
	stop.addActionListener(al);
	close.addActionListener(al);
   }


   protected void on_game_exit() {
	// TODO Auto-generated method stub
	   int result = JOptionPane.showConfirmDialog(pan, "���� �����Ͻðڽ��ϱ�?", "����", JOptionPane.YES_NO_OPTION);
		if (result != JOptionPane.YES_OPTION) {
			return;
		} else if (result == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
   }


	public void on_game_pause() {
		// TODO Auto-generated method stub
		
		bAdd = !bAdd;
		stop.setText(bAdd ? "�ٽý���" : "�Ͻ�����");
		if(bAdd) {
	       swing_timer.stop();
		}else {
		   swing_timer.start();
		}
	}


	protected void on_game_new() {
		// TODO Auto-generated method stub
	
		int result = JOptionPane.showConfirmDialog(pan, "������ �Ͻðڽ��ϱ�?", "������", JOptionPane.YES_NO_OPTION);
		if (result != JOptionPane.YES_OPTION) {
			return;
		} 
		
		scorenum = 0;
		life = 3;
	    words.clear();
	    
	    swing_timer.start();
	}

	
	//���ӿ���â���� �ҷ��ð�
		protected void on_overgame_new() {
			swing_timer.stop();
			
			int result = JOptionPane.showConfirmDialog(pan, "������ �Ͻðڽ��ϱ�?", "������", JOptionPane.YES_NO_OPTION);
			if (result != JOptionPane.YES_OPTION) {
				System.exit(0);;
			} 
			scorenum = 0;
			life = 3;
		    words.clear();
		    
		    swing_timer.start();	
		}
		
	

	private void heart_life(Graphics g) {
       // TODO Auto-generated method stub
       if(life == 3) {
          g.drawImage(MyImages.HEART, 70, 560, 35,35, score);
          g.drawImage(MyImages.HEART, 130,560, 35,35, score);
          g.drawImage(MyImages.HEART, 190,560, 35,35, score);
       }else if(life == 2) {
          g.drawImage(MyImages.HEART, 70,560, 35,35, score);
          g.drawImage(MyImages.HEART, 130,560, 35,35, score);
       }else if(life == 1) {
          g.drawImage(MyImages.HEART, 70,560, 35,35, score);
       }
    }
  
 
   class VirusThread extends Thread {

      public VirusThread(ThreadGroup group, String name) {
         super(group, name);

      }

      //���� �̺�Ʈ ó�� (���ǵ� & Ŭ���� & ���͹�)
      public void run() {

         int rand = (random.nextInt(5));//5���� ��������
         int oldValue = 0;
         int virusTime = 10 * 1000; // ���̷��� ���۽ð��� 10�ʷ� �����Ѵ�.

         switch (rand) {
         case 0:
            speed = speed / 2;
            break;

         case 1:
           interval = interval / 2;
            break;
            
         case 2:
            speed = speed * 2;
            break; 
            
         case 3:
        
            interval = interval * 2;
            break;
         case 4:

            words.clear();
            break;
         } // swtich
         
         
         int curLevel = getCurLevel();
         speed = LEVEL[curLevel].speed;
         interval = LEVEL[curLevel].interval;

      } // end of run()
   }

   
   class Level {
      int speed;
      int interval;
      int levelUpScore;

      String[] work;

      Level(int speed, int interval, int levelUpScore, String[] work) {

         this.speed = speed;
         this.interval = interval;
         this.levelUpScore = levelUpScore;
         this.work = work;

      }

   }
   
   class Word {
      String word = "";
      int x = 0;
      int y = 0;
      int step = 5;
      
      Color color = Color.BLACK;

      boolean isVirus = false;
      
     
   
   
      Word(String word) {
         this(word, 10, false);

      }

      Word(String word, boolean isVirus) {
         this(word, 10, isVirus);

      }

      Word(String word, int step, boolean isVirus) {
         this.word = word;
         this.step = step;
         this.isVirus = isVirus;

         // ���� �̺�Ʈ ����(����)

         if (isVirus)
            color = Color.RED;
         
            
         int strWidth = fm.stringWidth(word);      
         
         x = random.nextInt(SCREEN_WIDTH - strWidth*2);
         //(�����ܾ�-����ܾ�) - x
        //�����ܾ�
         if(words.size()>0) {
            Word last_word = (Word)words.get(words.size()-1);
            
            if(Math.abs(last_word.x - x) < strWidth )
               x = x + strWidth;
         }
         
         y = 0;
         

      }

      public String toString() {// toString ���ڸ� ���ڿ��� �ٲ۴�.
        return word;
      }

//class MyEventHandler

   }
   
   
 //����ڰ� Ÿ������ ���ڿ� ��Ī��Ű�� �۾�
    class MyEventHandler extends WindowAdapter implements ActionListener {

       public void actionPerformed(ActionEvent ae) {

          String input = typing.getText().trim();//trim �յڿ� ���� ���� ������ �����ش�.
          typing.setText("");
          System.out.println(input);

          if (bAdd)
             return;

          for (int i = 0; i < words.size(); i++) {

             Word tmp = (Word) words.get(i);
             if (input.equals(tmp.word)) {

                words.remove(i);
                scorenum += input.length() * 50;
                //score.setText("Score:" + scorenum);
                Toolkit.getDefaultToolkit().beep();

                if (level != MAX_LEVEL && levelUpCheck()) {

                   levelUp();

                } else {

                   if (tmp.isVirus) {

                      new VirusThread(virusGrp, "virus").start();
                   }

                }
                break;

             }

          } // for

  
          repaint(); // ��ȭ�� ������ ���� ȣ���ؾ� �ٷ� �ٷ� ����ȴ�.
       }

       
    }

}