package assets;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

import main.Main;
import main.Shooter;

public class Enemy extends Colliders{

	private int xPos = Main.WIDTH/2-10;
    private int yPos = Main.HEIGHT/2-100-10;
    private int width = 20;
    private int height = 20;
    private float speed = 0.05f;
    
    public Enemy(){
    	

    }
    public Enemy(int xPos,int yPos){
    	this.xPos= xPos;
    	this.yPos = yPos;
    	//add trig
   

    }

    public void checkCollision(List<Colliders> colliders,long elaspedTime) {
    	if(xPos<0)speed=0.1f;
    	if(xPos>Main.WIDTH-this.width)speed=-speed;
    	if(yPos<0)speed=0.1f;
    	if(yPos>Main.HEIGHT-this.height)speed=-speed;
    	
    	for(Colliders c:colliders) {
    		if(this!=c&&!c.isBullet()) {
    			double xDistance = c.getX()-xPos;
            	double yDistance = c.getY()-yPos;
            	double radiusSum = 10;
            	double distance = Math.sqrt(Math.pow(xDistance, 2) + Math.pow(yDistance, 2));
    			if(c.isPlayer()&&distance<=radiusSum) {
    				System.out.println("called");
    				colliders.remove(this);
        	  		Explosion e = new Explosion(xPos,yPos);
        	  		Shooter.explosions.add(e);
    			}
    			//else if(rect.intersects(c.getRect()))speed=-speed;
   
    		}
    	}
    	
    }
    
    public void move(int x, int y,long elaspedTime){
    	if(x>xPos)xPos+=Math.round(speed*elaspedTime);
        if(y>yPos)yPos+=Math.round(speed*elaspedTime);
      	if(x<xPos)xPos-=Math.round(speed*elaspedTime);
        if(y<yPos)yPos-=Math.round(speed*elaspedTime);
    //	circle.setFrame(xPos,yPos,width,height);

    }
    public void draw(Graphics2D g){
        g.setColor(Color.RED);
        g.fillOval(xPos,yPos,width,height);
        g.setColor(Color.BLACK);
        g.drawOval(this.xPos,this.yPos,width,height);  
        
        //check center
      //  g.setColor(Color.red);
       // g.drawLine(Main.WIDTH/2, Main.HEIGHT/2-100, Main.WIDTH/2+1, Main.HEIGHT/2+1-100);
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
	public void move(long elaspedTime) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean isPlayer() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isBullet() {
		// TODO Auto-generated method stub
		return false;
	}
	
}