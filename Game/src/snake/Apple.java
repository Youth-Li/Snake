package snake;

import java.awt.Color;
import java.awt.Graphics;

public class Apple {
private int x;
private int y;
private int height;
private int width;
	
	
	
	
	public Apple(int xcor,int ycor ,int size){
		x = xcor;
		y = ycor;
		height = size;
		width =size;
	}
	public void draw(Graphics g){
		g.setColor(Color.RED);
		g.fillRect(x*width, y*height, width, height);
		g.setColor(Color.RED);
		g.fillRect(x*width, y*height, width-2, height-2);
	}



	public int getX() {
		return x;
	}




	public void setX(int x) {
		this.x = x;
	}




	public int getY() {
		return y;
	}




	public void setY(int y) {
		this.y = y;
	}
}
