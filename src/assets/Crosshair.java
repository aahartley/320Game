package assets;

import java.awt.Color;
import java.awt.Graphics;
import main.*;


public class Crosshair {

    private int xPos = Main.WIDTH/2;
    private int yPos = Main.HEIGHT/2-5;
    private int xxPos = Main.WIDTH/2;
    private int yyPos = Main.HEIGHT/2+5;
    
    private int xPos2 = Main.WIDTH/2-5;
    private int yPos2 = Main.HEIGHT/2;
    private int xxPos2 = Main.WIDTH/2+5;
    private int yyPos2 = Main.HEIGHT/2;
    private Color color = Color.WHITE;
    
	public void moveCrosshair(int x, int y){

		xPos=x;
		yPos=y-5;
		xxPos=x;
		yyPos=y+5;
		 
		xPos2=x-5;
		yPos2=y;
		xxPos2=x+5;
		yyPos2=y;	
	}
	public void draw(Graphics g){
        g.setColor(color);
        g.drawLine(xPos, yPos, xxPos, yyPos);
        g.drawLine(xPos2,yPos2, xxPos2, yyPos2);
    }
	public void setColor(Color c){ 
        this.color = c;
	}
	public Color getColor(){
		return color;
	}
	
	public void setX(int xPos){ 
		this.xPos = xPos;
	}
	public int getX(){
        return xPos;
    }

    public void setY(int yPos){
        this.yPos = yPos;
    }

    public int getY(){
        return yPos;
    }


    public int getXx() {
		return xxPos;
	}

	public void setXx(int xxPos) {
		this.xxPos = xxPos;
	}

	public int getYy() {
		return yyPos;
	}

	public void setYy(int yyPos) {
		this.yyPos = yyPos;
	}
	
	public int getX2() {
		return xPos2;
	}

	public void setX2(int xPos2) {
		this.xPos2 = xPos2;
	}

	public int getY2() {
		return yPos2;
	}

	public void setY2(int yPos2) {
		this.yPos2 = yPos2;
	}

	public int getXx2() {
		return xxPos2;
	}

	public void setXx2(int xxPos2) {
		this.xxPos2 = xxPos2;
	}

	public int getYy2() {
		return yyPos2;
	}

	public void setYy2(int yyPos2) {
		this.yyPos2 = yyPos2;
	}

	public int getCenterX() {
		return xPos;
	}
	public int getCenterY() {
		return yPos2;
	}
	    
}
