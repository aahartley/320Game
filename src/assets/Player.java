package assets;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.List;

import main.Main;

public class Player extends Colliders {
	private boolean up,down,left,right;
	private int xPos = Main.WIDTH/2-10;
    private int yPos = Main.HEIGHT/2-10;
	//private int xPos = 0;
   // private int yPos = 0;
    private int width = 20;
    private int height = 20;
    public float speed = 0.4f;
    
    public Player() {
    	//circle.setFrame(xPos,yPos,width,height);

    }

    public void checkCollision(List<Colliders> colliders,long elaspedTime) {
    	if(xPos<1)xPos=0;
    	if(xPos>Main.WIDTH-this.width)xPos=Main.WIDTH-this.width-1;
    	if(yPos<1)yPos=0;
    	if(yPos>Main.HEIGHT-this.height)yPos=Main.HEIGHT-this.height-1;
    	for(Colliders c:colliders) {
    		if(!c.isPlayer()) {
    	  	//if(circle.intersects(c.circle.getBounds()))speed=0;
    		}
    	}
    }
    public void move(long elaspedTime){
    	if(up) yPos-=Math.round(speed*elaspedTime);
    	if(down) yPos+=Math.round(speed*elaspedTime);
    	if(left) xPos-=Math.round(speed*elaspedTime);
    	if(right) xPos+=Math.round(speed*elaspedTime);
    //aaaa	circle.setFrame(xPos,yPos,width,height);


    }
    public void draw(Graphics2D g){
        g.setColor(Color.yellow);
        g.fillOval(xPos,yPos,width,height);
        g.setColor(Color.BLACK);
        g.drawOval(this.xPos,this.yPos,width,height);  
        //check center
      //  g.setColor(Color.red);
       // g.drawLine(Main.WIDTH/2, Main.HEIGHT/2-100, Main.WIDTH/2+1, Main.HEIGHT/2+1-100);
    }
    

    public boolean isUp() {
		return up;
	}
	public void setUp(boolean up) {
		this.up = up;
	}
	public boolean isDown() {
		return down;
	}
	public void setDown(boolean down) {
		this.down = down;
	}
	public boolean isLeft() {
		return left;
	}
	public void setLeft(boolean left) {
		this.left = left;
	}
	public boolean isRight() {
		return right;
	}
	public void setRight(boolean right) {
		this.right = right;
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
    public void setWidth(int width) {
    	this.width = width;
    }
    public int getWidth(){
        return width;
    } 
    public void setHeight(int height) {
    	this.height = height;
    }
    public int getHeight(){
        return height;
    }
    public void setSpeed(float speed){
        this.speed=speed;
    } 

    public float getSpeed(){
        return speed;
    }
	@Override
	public void move(int x, int y, long elaspedTime) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean isPlayer() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isBullet() {
		// TODO Auto-generated method stub
		return false;
	}
	
}