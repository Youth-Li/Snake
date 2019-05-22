package snake;

import java.awt.Color;
import java.awt.Graphics;

public class Part {
private int x ,y, width , height;
public Part(int X ,int Y, int sTile){
	x = X;
	y = Y;
	width = sTile;
	height = sTile;
	
}
public void tick(){
	
}
public void draw(Graphics g){
	g.setColor(Color.BLACK);
	g.fillRect(x*width, y*height, width, height);
	g.setColor(Color.GREEN);
	g.fillRect(x*width, y*height, width-2, height-2);
}
public int getY(){
	return y;
}
public int getX(){
	return x;
}
}
