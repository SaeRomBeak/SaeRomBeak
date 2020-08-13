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

//타자연습게임창
public class Typing extends JPanel {

   int w = 1165, h = 855; // 전체크기

   static Image PANBACK = new ImageIcon("src/image/1.PNG").getImage(); // 단어판
   static Image TYPING = new ImageIcon("src/image/아래부분.png").getImage(); // 입력판
   static Image SCORE = new ImageIcon("src/image/점수판.PNG").getImage(); // 입력판
   static Image HEART = new ImageIcon("src/image/6.png").getImage(); // 입력판

   BorderLayout border;// 현재 layout

   JPanel centerPanel;// 단어떨어지는판+타이핑판
   JPanel pan; // 단어떨어지는 판
   JPanel Typing; // 타이핑판
   JPanel score; // 점수판

    int SCREEN_WIDTH  = w-300;   //단어가 떨어지는 판의넓이
    int SCREEN_HEIGHT = h-50;    //단어가 떨어지는 판의 높이
    int MAX_LEVEL;              //최대의 레벨

   JButton stop, replay, close; // 잠시멈추는 버튼

   JTextField typing;       // 단어치는곳

   FontMetrics fm;
   
   boolean bAdd=false;
   ThreadGroup virusGrp = new ThreadGroup("virus");  //바이러스가 생성되는 쓰레드변수
 
   
   // ----------------------------------------------------------------------//
   String[][] work = { { "바보", "우수하다", "반발", "마라톤", "전해지다", "주인공", "손가락", "수시로", "선명하다", "수도꼭지", "건전하다", "가라앉다", "믿음"},            //1
                  {"뛰어가다", "숨어있는", "고용하다", "보호하다", "착하다", "나쁘다", "선풍기", "자바", "붕어빵", "선택하다", "찬성하다", "반대하다", "위치하다", "생각되다"},//2
                  {"깜짝", "감다", "꿈", "별", "감", "책", "오다", "가다", "소나기", "가방", "초록", "일어나다", "쏘다", "겨드랑이", "존경하다", "종소리", "갖고싶다"},   //3
                  {"명확하다", "뚜벅뚜벅", "할인", "아장아장", "사과", "감사", "이별", "대기","금요일","컴퓨터","초콜릿","머리끈","아줌마","아저씨" }, };                //4

   final Level[] LEVEL = { new Level(500, 2000, 1000, work[0]),
                      new Level(500, 1500, 3000, work[1]), 
                      new Level(300, 1000, 10000, work[2]),
                      new Level(200, 500, 20000,  work[3]) };
   
   
   List words = new ArrayList();


   int level = 1;           // 게임레벨
   int life = 3;           // 게임생명              // work[]배열만들어지는 초기화부분
   int speed = 100;       // 스피드
   int interval = 1*1000;
   int scorenum = 0;       // 단어의 점수

   Label lbLevel = new Label("레벨:" + level, Label.CENTER);
      
   boolean isPlaying = false;
   Random random = new Random();

   Font font = new Font("돋움체", Font.BOLD, 15);
   Font font1 = new Font("돋움체", Font.BOLD, 30);

   MyMain myMain; // 연동되도록
   
   
   Timer swing_timer;

   
   // 생성자 초기화부분------------------------------------------------------------------
   public Typing() {

      // TODO Auto-generated constructor stub
      setLayout(new BorderLayout());

      init_centerPanel(); // 단어내려오는화면초기화

      init_score(); // 점수판초기

      init_timer();

   }


  // Main JFram과 연동되는 생성자 추가
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
			rain_word();//글자생성
			rain_drop();//글자내리기
			
			repaint();
		}
	   };
	   
	   swing_timer = new Timer(interval, listener);
   }
   
   
   protected void rain_word() {
		// TODO Auto-generated method stub
		String[] data = LEVEL[getCurLevel()].work;
       int rand = (int) (Math.random() * data.length);// 램덤 워드
       
       // 약 10번에 한번 꼴로 바이러스를 생성한다.

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
               life--;//생존능력 떨어지는 과정
               break;
            }

            //생존능력이 0이하면 게임오버
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

	   
	   // 레벨업
	   public synchronized void levelUp() {

	      virusGrp.interrupt();
	      Level lvl = getLevel(++level);
	      lbLevel.setText("Level:" + level);

	      words.clear();
	      //pan.clear();

	     
	      try {						//레벨보여지는 속도조절
	    	  showLevel(level);
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	      speed = lvl.speed;
	      interval = lvl.interval;

	   }

	   // 레벨의 속도

	   public void showLevel(int level) {

	      String tmp = "Level " + level;
	      showTitle(tmp, 1 * 2000);

	   }

	   
	   
	   public void showTitle(String title, int time) {

	      Graphics g = pan.getGraphics();
	      Font titleFont = new Font("굴림체", Font.BOLD, 50);
	      g.setColor(Color.white);
	      g.setFont(titleFont);

	      FontMetrics fm = pan.getFontMetrics(titleFont);
	      int width = fm.stringWidth(title);
	      g.drawString(title, (SCREEN_WIDTH - width) / 2, SCREEN_HEIGHT / 2);
	    

	   }
	
	
	
	
   // 단어판+입력판초기화부분---------------------------------------------------------------------
   public void init_centerPanel() {

      // TODO Auto-generated method stub
      // 단어떨어지는 판 + 입력창 넣을 공간생성
      centerPanel = new JPanel(new BorderLayout());

      // 단어떨어지는 판생성
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

      // 단어적는 판(입력판)
      Typing = new JPanel() {
         @Override
         public void paintComponent(Graphics g) {
            // TODO Auto-generated method stub
            g.drawImage(MyImages.TYPING, 0, 0, w, 50, Typing);

         }
      };
      Typing.setPreferredSize(new Dimension(w, 50));

      JLabel input = new JLabel("단어:");
      input.setForeground(Color.white);
      typing = new JTextField(20);

      Typing.add(input);
      Typing.add(typing);
      centerPanel.add(Typing, "South");

      MyEventHandler handler = new MyEventHandler();// 글쓰는 이벤트
      //addWindowListener(handler);
      typing.addActionListener(handler);
      //SCREEN_WIDTH = pan.getWidth();
      //SCREEN_HEIGHT = pan.getHeight();
      MAX_LEVEL = LEVEL.length - 1;

      fm = pan.getFontMetrics(getFont());

   }

   // 점수판 초기화부분------------------------------------------------------------------------
   public void init_score() {
      // TODO Auto-generated method stub

      border = new BorderLayout();
      score = new JPanel(border) {

         @Override
         public void paintComponent(Graphics g) {
            // TODO Auto-generated method stub
            g.drawImage(MyImages.SCORE, 0, 0, 300, h,score);
            
            g.setFont(font1);
            String str_score = String.format("%04d(점)", scorenum);
            g.drawString(str_score, 95, 180);
         
            heart_life(g);
            
         }

      
      };
      
      score.setPreferredSize(new Dimension(300, 0));
      this.add(score, "East");

      // 버튼들어갈 부분
      JPanel but = new JPanel();
      replay = new JButton("새로시작");
      close = new JButton("끝내기");
      stop = new JButton("일시정지");


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
	   int result = JOptionPane.showConfirmDialog(pan, "정말 종료하시겠습니까?", "종료", JOptionPane.YES_NO_OPTION);
		if (result != JOptionPane.YES_OPTION) {
			return;
		} else if (result == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
   }


	public void on_game_pause() {
		// TODO Auto-generated method stub
		
		bAdd = !bAdd;
		stop.setText(bAdd ? "다시시작" : "일시정지");
		if(bAdd) {
	       swing_timer.stop();
		}else {
		   swing_timer.start();
		}
	}


	protected void on_game_new() {
		// TODO Auto-generated method stub
	
		int result = JOptionPane.showConfirmDialog(pan, "새게임 하시겠습니까?", "새게임", JOptionPane.YES_NO_OPTION);
		if (result != JOptionPane.YES_OPTION) {
			return;
		} 
		
		scorenum = 0;
		life = 3;
	    words.clear();
	    
	    swing_timer.start();
	}

	
	//게임오버창에서 불러올것
		protected void on_overgame_new() {
			swing_timer.stop();
			
			int result = JOptionPane.showConfirmDialog(pan, "새게임 하시겠습니까?", "새게임", JOptionPane.YES_NO_OPTION);
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

      //워드 이벤트 처리 (스피드 & 클리어 & 인터벌)
      public void run() {

         int rand = (random.nextInt(5));//5개의 랜덤숫자
         int oldValue = 0;
         int virusTime = 10 * 1000; // 바이러스 동작시간을 10초로 설정한다.

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

         // 적색 이벤트 워드(난수)

         if (isVirus)
            color = Color.RED;
         
            
         int strWidth = fm.stringWidth(word);      
         
         x = random.nextInt(SCREEN_WIDTH - strWidth*2);
         //(이전단어-현재단어) - x
        //이전단어
         if(words.size()>0) {
            Word last_word = (Word)words.get(words.size()-1);
            
            if(Math.abs(last_word.x - x) < strWidth )
               x = x + strWidth;
         }
         
         y = 0;
         

      }

      public String toString() {// toString 숫자를 문자열로 바꾼다.
        return word;
      }

//class MyEventHandler

   }
   
   
 //사용자가 타이핑한 글자와 매칭시키는 작업
    class MyEventHandler extends WindowAdapter implements ActionListener {

       public void actionPerformed(ActionEvent ae) {

          String input = typing.getText().trim();//trim 앞뒤에 쓸때 없는 공백을 없애준다.
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

  
          repaint(); // 변화가 있을때 마다 호출해야 바로 바로 적용된다.
       }

       
    }

}