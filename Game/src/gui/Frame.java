package gui;

import java.awt.GridLayout;

import javax.swing.JFrame;

public class Frame extends JFrame{
 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public Frame(){
	 this.setTitle("GooGoo's Big Adventure");
	 this.setResizable(false);
	 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 style();
 }
  public void style(){
	
	 
	
	 //this.setLayout(new GridLayout(1,1,0,0));
	 Panel p = new Panel();
	 this.add(p);
	 this.pack();
	 this.setLocationRelativeTo(null);
	 
	 this.setVisible(true);
  }
  
  public static void main(String args[]){
  
  Frame f = new Frame();


  
  
  
  }
  
  
  
}
