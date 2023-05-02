package Final;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RapidRoll extends JFrame implements Runnable, KeyListener{//

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel wall1, wall2, wall3, wall4, ball, life, limit, ready, gameOver,wall;
	JPanel rank = new JPanel(), end = new JPanel(), panelHelp = new JPanel();
	JPanel p1, p2;
	
	Font font = new Font("Arial", Font.BOLD, 20);
	JTextField t1, t2,rank_0, rank_1,rank_2,rank_3 ,rank_end_1,rank_end_2,rank_end_3;
	JTextField textFieldName, textHelp;
	
	ArrayList<Model> list = rankDAO.getInstance().selectSort();
	
	int x = 600, wallX1,wallX2,wallX3,wallX4,ballX, lifeX, wallX;
	int y = 500, wallY1 = 0, wallY2 = 132, wallY3 = 260, wallY4 = 392, ballY = wallY3, lifeY, wallY=500;
	int score, lifeCounter, scroll = 15, move = 4;

	Random r = new Random();
	boolean goup=false, dead=false, givelife = false,boolPause = false, boolRight = true, 
			giveLifeF = true, lifeCount=true, countF=false, wait= true; 
	
	Thread myThread;
	
	JMenuBar myBar;
	JMenu game, help, level;//, rank ;
	JCheckBoxMenuItem easy, med, hard;
	ButtonGroup bg;
	JButton pause,ok;
	ImageIcon iconWall, iconBall,iconRedLine, iconLife, iconOver, iconReady, iconEx; 
	
	public void value() { // tạo giá trị các biến 
		wallX1 = 20 + r.nextInt(1);  // tạo vị trí ngang tường 1
		wallX2 = 100 + r.nextInt(1);
		wallX3 = 200 + r.nextInt(1);
		wallX4 = 280 + r.nextInt(1);
		wallX = 150; //+ r.nextInt(1);
		ballX = wallX3 + 70; // tạo vị trí ngang bóng
		lifeX = -20; // tạo trái tim
		
		wallY1 = 0; // tạo vị trí dọc tường 1
		wallY2 = 132;
		wallY3 = 260;
		wallY4 = 394; // ----------
		ballY = wallY3; // tạo vị trí dọc bóng
		lifeY = -20; // vị trí dọc trái tim
		score = 0; // điểm 
		lifeCounter = 0; // mạng
		
	}
	public RapidRoll() {
		setTitle("Rapid Roll"); // title
		setSize(x,y+130); // kích thước khung 
		setResizable(false); // ko phóng to thu nhỏ
		setLocation(400,250); // tạo frame ở
		this.setLayout(null);; // 
		this.setBackground(Color.BLUE);
		
		CreatBar();
		value();
		
		// khởi tạo tường, bóng, giới hạn, trái tim, 
		wall1 = new JLabel();
		wall2 = new JLabel();
		wall3 = new JLabel();
		wall4 = new JLabel();
		///---------------------------
		wall = new JLabel();
		
		ball = new JLabel();
		life = new JLabel();
		limit = new JLabel();
//		ready = new JLabel("img\\ready");
		gameOver = new JLabel("img\\GAMEOVER");
		
		iconBall = new ImageIcon("img\\ball.gif");
		iconLife = new ImageIcon("img\\life.gif");
		iconWall = new ImageIcon("img\\wall.gif");
		iconOver = new ImageIcon("img\\Over.gif");
		iconRedLine = new ImageIcon("img\\redLine.gif");
		
		iconEx = new ImageIcon("img\\th_1.gif");
//		iconReady = new ImageIcon("img\\ready.gif");
		
		wall1.setIcon(iconWall);
		wall2.setIcon(iconWall);
		wall3.setIcon(iconWall);
		wall4.setIcon(iconWall);
		
		wall.setIcon(iconEx);
		
		ball.setIcon(iconBall);
		life.setIcon(iconLife);
		limit.setIcon(iconRedLine);
		
		gameOver.setIcon(iconOver);
//		ready.setIcon(iconReady);
		
//		ready.setBounds(0, 100, 600, 100);
		limit.setBounds(0, 0, 600, 30);
		
		ok = new JButton("OK");
		
		createEnd();
		
		rank.setLayout(new GridLayout(4,1));
		
		rank_0 = new JTextField("Rank, Name and Score");
		rank_0.setFont(font);
		rank_0.setEnabled(false);
		rank.add(rank_0);

		rank_1 = new JTextField("1st     " + list.get(0).toString());
		rank_1.setFont(font);
		rank_1.setEnabled(false);
		rank.add(rank_1);

		rank_2 = new JTextField("2nd     " + list.get(1).toString());
		rank_2.setFont(font);
		rank_2.setEnabled(false);
		rank.add(rank_2);

		rank_3 = new JTextField("3rd     " + list.get(2).toString());
		rank_3.setFont(font);
		rank_3.setEnabled(false);
		rank.add(rank_3);
		
		rank.setBounds(0,0, 600, 500);
		
		t1 = new JTextField("Life => " + lifeCounter, 20);
		t2 = new JTextField("Score => " + score, 20);
		pause = new JButton("Pause");
		
		t1.setBackground(Color.BLACK);
		t2.setBackground(Color.BLACK);
		pause.setBackground(Color.CYAN);
		pause.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(boolPause == false) {
					boolPause = true;
					remove();
					p2.remove(panelHelp);
					p2.add(rank);
					pause.setText("Continue");
//					wait=true;
					
				} else {
					boolPause = false;
					p2.remove(rank);
					p2.remove(panelHelp);
					add();
					pause.setText("Pause");
//					wait=false;
					
				}
			}
		});
		
		t1.setEnabled(false);
		t2.setEnabled(false);
		
		p1 = new JPanel();
		p2 = new JPanel();
		
		p1.setBackground(Color.LIGHT_GRAY);
		p1.setBounds(0, 0, x, 40);
		p1.setLayout(new GridLayout(0,3));
		p1.add(t1);
		p1.add(t2);
		p1.add(pause);
		
		p2.setBackground(Color.CYAN);
		p2.setBounds(0,40,x,y);
		p2.setLayout(null);
	
		play();
		
		p2.add(rank);
//		p2.add(ready);
		p2.add(limit);
		p2.add(wall1);
		p2.add(wall2);
		p2.add(wall3);
		p2.add(wall4);
		p2.add(ball);
		//-----------------
		p2.add(wall);
		
		this.add(p1);
		this.add(p2);
		
		//show();
		this.setVisible(true);
		addKeyListener(this);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		myThread = new Thread(this);
		myThread.start();
	}

	private void play() {
		wall1.setBounds(wallX1, wallY1+20, 140, 20);
		wall2.setBounds(wallX2, wallY2+20, 140, 20);
		wall3.setBounds(wallX3, wallY3+20, 140, 20);
		wall4.setBounds(wallX4, wallY4+20, 140, 20);
		
		wall.setBounds(wallX, wallY+20, 140, 20);
		ball.setBounds(ballX, ballY, 20, 20);
		life.setBounds(lifeX, lifeY, 14, 14);
		if(boolPause==false) {

			// nếu bóng đi quá trên dưới trái phải == dead
			if(ballX <=30 || ballX>=x-14 || ballY >=y-10 || ballY <=0) {
				dead = true;
				if(lifeCount == true && lifeCounter >=0) {
					lifeCounter--;
					lifeCount = false;
				}
				t1.setText("Life => " + lifeCounter);
			}
			
			// nếu mạng âm == thua
			if(lifeCounter == -1) {
				t1.setText(" GAME OVER ");
				remove();
//				createEnd();
				p2.remove(gameOver);
				p2.add(end);
//				try {
////					myThread.join();
//					Thread.sleep(3000);
//				}catch(InterruptedException e) {
//					
//				}
				boolPause= true;
				
			}
			// nếu bóng trên tường thì đi lên
			if (((ballY == wallY1) && (wallX1 - 20 < ballX && ballX < (wallX1 + 140))) || ((ballY == wallY2) && (wallX2 - 20 < ballX && ballX < (wallX2 + 140))) || ((ballY == wallY) && (wallX - 20 <= ballX && ballX <= (wallX + 140)))
	                || (ballY == wallY3) && (wallX3 - 20 < ballX && ballX < (wallX3 + 140)) || ((ballY == wallY4) && (wallX4 - 20 < ballX && ballX < (wallY4 + 140)))) {
	            goup = true;
	        } else {
	            goup = false;
	            countF = true;
	        }
			// tạo tường 1 nếu tường đi qua limit
			if(wallY1 <=0) {
				wallY1 = 500;
				wallX1 = 10 + r.nextInt(x-140);
				if(dead == true) { //nếu bóng chết tạo bóng trên tường
					ballX = wallX1 + 72;
					ballY = wallY1 - 24;
					dead = false;
					lifeCount = true;
				}
				if (givelife == true) { // tạo trái tim nếu dc cho phép
	                lifeX = wallX1 + (20 + r.nextInt(100));
	                lifeY = wallY1;
	                life.setBounds(lifeX, lifeY, 14, 14);
	                givelife = false;
	            }
			}
			if(wallY2 <=0) {
				wallY2 = 500;
				wallX2 = 10 + r.nextInt(x-140);
				if(dead == true) {
					ballX = wallX2 + 72;
					ballY = wallY2 - 24;
					dead = false;
					lifeCount = true;
				}
				if (givelife == true) {
	                lifeX = wallX2 + (20 + r.nextInt(100));
	                lifeY = wallY2;
	                life.setBounds(lifeX, lifeY, 14, 14);
	                givelife = false;
	            }
			}
			if(wallY3 <=0) {
				wallY3 = 500;
				wallX3 = 10 + r.nextInt(x-140);
				if(dead == true) {
					ballX = wallX3 + 72;
					ballY = wallY3 - 24;
					dead = false;
					lifeCount = true;
				}
				if (givelife == true) {
	                lifeX = wallX3 + (20 + r.nextInt(100));
	                lifeY = wallY3;
	                life.setBounds(lifeX, lifeY, 14, 14);
	                givelife = false;
	            }
			}
			if(wallY4 <=0) {
				wallY4 = 500;
				wallX4 = 10 + r.nextInt(x-140);
				if(dead == true) {
					ballX = wallX4 + 72;
					ballY = wallY4 - 24;
					dead = false;
					lifeCount = true;
				}
				if (givelife == true) {
	                lifeX = wallX4 + (20 + r.nextInt(100));
	                lifeY = wallY4;
	                life.setBounds(lifeX, lifeY, 14, 14);
	                givelife = false;
	            }
			}
			if(wallY <=0) {
				wallY = 500;
				//wallX = 10 + r.nextInt(x-140);
				if(dead == true) {
					ballX = wallX + 72;
					ballY = wallY - 24;
					dead = false;
					lifeCount = true;
				}
				if (givelife == true) {
	                lifeX = wallX + (20 + r.nextInt(100));
	                lifeY = wallY;
	                life.setBounds(lifeX, lifeY, 14, 14);
	                givelife = false;
	            }
			}
			if(lifeY <=20) { // nếu trái tim đi qua limit xóa trái tim
				this.remove(life);
				givelife = false;
				giveLifeF = true;
			}
			
//			if(wait == false) {
//				wallY1 -=2; // tường đi lên
//				wallY2 -=2;
//				wallY3 -=2;
//				wallY4 -=2;
//				lifeY -=2;
//			}
			
			wallY1 -=2; // tường đi lên
			wallY2 -=2;
			wallY3 -=2;
			wallY4 -=2;
			//----------------
			wallY-=2;
			
			if(wallX ==(x-160)) boolRight = false;
			if(wallX == 0) boolRight = true;
			if(boolRight == true) {
				wallX+=2;
			}else wallX -=2;
			
			lifeY -=2;
			
			if(goup == false) { // nếu bóng trên tường bóng đi lên
				ballY +=2;
				
				// nếu bóng rơi xuống tường điểm tăng
				if(ballY == wallY1 || ballY == wallY2 || ballY == wallY3 || ballY == wallY4 ||ballY == wallY 
						|| ballY == wallY1 -2 || ballY == wallY2 -2 || ballY == wallY3 -2 || ballY == wallY4 -2||ballY == wallY -2) {
					score += (move *5);
					t2.setText("Score => " + (((score) - (move * 5))));
				}
			}
			
			// bóng đi xuống
			if(goup == true) {
				ballY -= 2;
			}
			
			//tạo trái tim
			if(giveLifeF == true) {
				if(score % 250 == 0) {
					p2.add(life);
					givelife = true;
					giveLifeF = false; 
				}
			}
		}
		
		repaint();
		this.setVisible(true);
	}
	private void CreatBar() {// tạo thanh nav Bar
		myBar = new JMenuBar(); 
		game = new JMenu("Game"); 
		
		JMenuItem newGame = new JMenuItem("New Game");
		JMenuItem exit = new JMenuItem("Exit");
		
		JMenuItem rankItem = new JMenuItem("Rank");
		
		rankItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				remove();
				p2.remove(end);
				p2.add(rank);
				boolPause=true;
			}
		});
		
		newGame.addActionListener(
				new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						reset();
						
					}
				});
		exit.addActionListener(
				new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
						
					}
				});
		
		game.add(newGame);
		game.add(rankItem);
		game.addSeparator();
		game.add(exit);
		
		myBar.add(game); // thanh bar có nút game, trong game có new game và exit
		
		level = new JMenu("Level");
		
		easy = new JCheckBoxMenuItem("Easy");
		med = new JCheckBoxMenuItem("Medium");
		hard = new JCheckBoxMenuItem("Hard");
		
		bg = new ButtonGroup();
		bg.add(easy);
		bg.add(med);
		bg.add(hard);
		
		easy.addActionListener(
					new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							scroll = 30;
							move = 4;
							reset();
							
						}
				});
		med.setSelected(true);
		med.addActionListener(
				new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						scroll = 16;
						move = 4;
						reset();
						
					}
			});
		hard.addActionListener(
				new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						scroll = 12;
						move = 8;
						reset();
						
					}
			});
		
		level.add(easy);
		level.add(med);
		level.add(hard);
		
		myBar.add(level); 
		
		help= new JMenu("Help");
		
		String text = "Nháy 2 lần down để xuyên tường \n"
				+"Left để sang trái và Right để sang phải";
		textHelp = new JTextField(text);
		textHelp.setBackground(Color.BLACK);
		textHelp.setEnabled(false);
		
		panelHelp.add(textHelp);
		panelHelp.setBackground(Color.BLACK);
		
		JMenuItem itemHelp = new JMenuItem("Help");
		
		itemHelp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				remove();
				p2.remove(rank);
				p2.remove(end);
			}
		});
		
		help.add(panelHelp);
		
		myBar.add(help);
		
		setJMenuBar(myBar);
	}
	@SuppressWarnings("deprecation")
	public void reset() {
		myThread.stop();
		wait = true;
		myThread = new Thread(this);
		value();
		myThread.start();
		p2.remove(life);
		lifeX = -20;
		lifeY =-20;
		
		t1.setText("Life => " + lifeCounter);
		t2.setText("Score => " + score);
		
		p2.remove(gameOver);
		p2.remove(end);
		p2.remove(panelHelp);
		p2.add(rank);
		add();
//		p2.add(ready);
		
	}
	public void run() {
		// TODO Auto-generated method stub
		for (;;) {
            play();
            try {
                if (wait == true) {
// ---------------------------------------------------------
                    Thread.sleep(3500);
                    wait = false;
                    boolPause=false;
                    //p2.remove(ready);
                    p2.remove(rank);
                    add();
                } else {
                    Thread.sleep(scroll);
                }
            } catch (Exception e) {
            }
       }
	}
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		// TODO Auto-generated method stub
		if(boolPause == false) {
			boolPause = true;
			remove();
			p2.remove(panelHelp);
			p2.add(rank);
			pause.setText("Continue");
//			wait=true;
			
		} else {
			boolPause = false;
			p2.remove(rank);
			p2.remove(panelHelp);
			add();
			pause.setText("Pause");
//			wait=false;
			
		}
		
			if(e.getKeyCode() == 37) {
				ballX -= move;
			}
			if(e.getKeyCode() == 39) {
				ballX += move;
			}
			if(e.getKeyCode() == 40) {
				ballY -=2;
			}
		ball.setBounds(ballX, ballY, 20, 20);
		
		if (ballX + 20 >= lifeX && ballX <= lifeX + 8 && ballY + 20 >= lifeY && ballY <= lifeY + 8) {
            if (countF == true && lifeCounter < 6) {
                lifeCounter++;
                score += (move * 10);
                countF = false;
            }
            p2.remove(life);
            givelife = false;

            t1.setText("Life==>	" + lifeCounter);
        }
		
		// ----------------------------------------------------
		
		
		repaint();
		this.setVisible(true);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void add() {
		p2.add(wall1);
		p2.add(wall2);
		p2.add(wall3);
		p2.add(wall4);
		p2.add(ball);
		p2.add(life);
		p2.add(wall);
	}
	public void remove() {
		p2.remove(wall1);
		p2.remove(wall2);
		p2.remove(wall3);
		p2.remove(wall4);
		p2.remove(ball);
		p2.remove(life);
		p2.remove(wall);
	}
	public void createEnd() {
		end.setLayout(new GridLayout(6,1));
		
		rank_end_1 = new JTextField("1st     " + list.get(0).toString());
		rank_end_1.setFont(font);
		rank_end_1.setEnabled(false);

		rank_end_2 = new JTextField("2nd     " + list.get(1).toString());
		rank_end_2.setFont(font);
		rank_end_2.setEnabled(false);

		rank_end_3 = new JTextField("3rd     " + list.get(2).toString());
		rank_end_3.setFont(font);
		rank_end_3.setEnabled(false);
		
		end.add(gameOver);
		end.add(textFieldName=new JTextField(50));
		textFieldName.setFont(font);
		end.add(ok);
		end.add(rank_end_1);
		ok.setFont(font);
		rank_end_1.setFont(font);
		end.add(rank_end_2);
		rank_end_2.setFont(font);
		end.add(rank_end_3);
		rank_end_3.setFont(font);
		
		end.setBounds(0,0, 600, 500);
		ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String Name = String.valueOf(textFieldName.getText());
				Model model1 = new Model(Name,score);
		    	rankDAO.getInstance().insert(model1);
			}
		});
	}
}
