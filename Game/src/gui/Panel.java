package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import snake.Apple;
import snake.Part;

public class Panel extends JPanel implements Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int Width = 300;
	private final int Height = 300;
	private Thread thread ; 
	private boolean running;
	private Part p ;
	private ArrayList<Part> snake;
	private int ticks = 0;
	private boolean up, down, left , right ;
	private int xcor=10, ycor=10  ;
	private boolean square = true;
	private ArrayList<Apple> grid ;
	private int size =1;
	private Apple pill;
	private Random r;
	private int score;
	private int speed =999990;
	private String file = System.getProperty("user.home")+ "/Documents/la";
	private Scanner scan;
	private JPanel june;
	private int highScore;
	
	public Panel(){
		snake = new ArrayList<Part>();
		grid = new ArrayList<Apple>();
		scan = new Scanner(System.in);
		june = new JPanel();
		highScore = 0;
		score = 0;
		r = new Random();
		if(snake.size() == 0 ){
			p = new Part(xcor ,ycor,10);
			snake.add(p);
			
		}
		
	
		 //arraypop();
		styles();
	}
	
	public void styles(){
		this.setFocusable(true);
		this.setPreferredSize(new Dimension(Width , Height));
		this.setOpaque(true);
		
		this.setLayout(new GridLayout(1,1,0,0));
		this.addKeyListener(new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent evt) {
			moveIt(evt);
			}
		});
		start();
	}
	public void start(){
		running = true;
		thread = new Thread(this , " Gameloop");
		thread.start();
	}
	public void stop(){
		running  =  false;
		JLabel hall = new JLabel();
		june.setLayout(new BoxLayout(june ,BoxLayout.Y_AXIS));
		hall.setSize(20, 20);
		hall.setText("Hall of Fame");
		june.add(hall);
		JFrame jop = new JFrame();
		readfiles();
		june.add(new JLabel("You died at score "+ score));
		writefiles();
		jop.add(june);
		jop.pack();
		jop.setDefaultCloseOperation(jop.EXIT_ON_CLOSE);
		jop.setLocationRelativeTo(null);
		jop.setVisible(true);
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(running){
			
			tick();
			repaint();
			check();
			
			
		}
	}

	public void tick() {
		
		if(grid.size() == 0){
			int xcor = r.nextInt(25);
			int ycor = r.nextInt(25);
			pill= new Apple(xcor,ycor,10);
			grid.add(pill);
		}
		ticks++;
		if(ticks>speed){
			
			if(up)
				ycor--;
			if(down )
				ycor++;
			if(right )
				xcor++;
			if(left )
				xcor--;
			ticks = 0;
			p =  new Part(xcor, ycor ,10);
			snake.add(p);
			if(snake.size()>10){
				speed =speed -100;
			}
			
			if(snake.size()>size){
				snake.remove(0);
			}
			
		}
		
		
	}
	
	
	
	
	public void paint(Graphics g){
		g.clearRect(0, 0, Width, Height);
		String strI =  Integer.toString(score);
		g.drawString(strI, Width/10-30, Height/10-20);
		for( int x = 0 ; x<Width/10 ; x++){
			g.setColor(Color.blue);
			g.drawLine(x*10, 0,x*10 ,Height);
			g.drawLine(0, x*10, Width, x*10);

		}
		for(int i = 0 ; i < snake.size() ; i++){
			snake.get(i).draw(g);
		}
		for(int i = 0 ; i <grid.size(); i++){
			grid.get(0).draw(g);
		}
		
	}
	
	
	
	
	public void moveIt(KeyEvent evt){
		if(evt.getKeyCode() == evt.VK_DOWN&& up == false){
			down = true;
			up = false;
			right =false;
			left = false;
	}
		if(evt.getKeyCode() == evt.VK_UP&& down == false){
			up = true;
			down = false;
			right =false;
			left = false;
			}
		if(evt.getKeyCode() == evt.VK_RIGHT&& left == false){
			right =true;
			up = false;
			down =false;
			left = false;
		}
		if(evt.getKeyCode() == evt.VK_LEFT&& right == false){
			left = true;
			up = false;
			right =false;
			down = false;
			}
	}
	
	
	
	
	public void check(){
		if(p.getX() == (Height/10)   || p.getX()+1 == 0){
			stop();
			
		}
		if(p.getY() == (Height/10)   || p.getY()+1 == 0){
			stop();
		}
		if(xcor == grid.get(0).getX()&& ycor == grid.get(0).getY()){
			grid.remove(0);
			size=size+1;
			score++;
			
		}
		for(int x = 0 ; x<snake.size();x++){
			if(xcor == snake.get(x).getX() && ycor == snake.get(x).getY())
				if(x != snake.size()-1){
					stop();
					
				}
		}
	}
	public void readfiles(){
		String bling ="";
		try {
			for(String s :Files.readAllLines(Paths.get(file))){
			bling = s; //this can cause a problem if there are too many high scores so set the bar high.
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] jork = bling.split(",");
		int zink = 0;
		boolean xuxu = true;
		
			for(String s : jork){
				
				june.add(new JLabel(s));
				
				if(zink == 1){
					int x = new Integer(s);
					if(x>highScore){
						highScore = x;
					}
				}
				zink++;
				if(zink==2){
				zink =0;
				}
				
				
			}
	}
	public void writefiles(){
		if(score>highScore){
		System.out.println("What is you name");
		String textToWrite = scan.nextLine() +" "+","+ score +",";
		 try {
			Files.write(Paths.get(file), textToWrite.getBytes(),StandardOpenOption.APPEND);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	}
}
