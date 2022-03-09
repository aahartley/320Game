package assets;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

import main.Main;
import main.Shooter;

public class Bullet extends Colliders {
	private int xPos = 0;
    private int yPos = 0;
    private int xm;
    private int ym;
    private boolean up,down,left,right;

    private int width = 10;
    private int height = 10;
    private float speedx = 0.2f;
    private float speedy = 0.2f;


    public Bullet() {
    //	circle.setFrame(xPos,yPos,width,height);

    }
    public Bullet(int x, int y, int xm, int ym) {
    	
    	x+=20/2;
    	y+=20/2;
    	this.xm = xm;
    	this.ym =ym;
    	this.xPos=x-5;
    	this.yPos=y-5;
//bullet no on rect
    	
    	if(xm-10>x) {
    		right=true;
        	this.xPos = x+12;
    	}
    	if(ym-10>y) {
    		down=true;
        	this.yPos = y+15;
    	}
    	if(xm<x-10) {
    		left = true;
        	this.xPos = x-22;
    	}
    	if(ym<y-10) {
    		up=true;
        	this.yPos = y-22;
    	}

    	double bulletVelocity = 0.5;
    	double theta = Math.atan2(ym - y, xm - x);
    	speedx = (float) ((float) Math.cos(-theta)*bulletVelocity);
    	speedy = (float) ((float) -Math.sin(-theta)*bulletVelocity);
    //	circle.setFrame(xPos,yPos,width,height);

    }
    public void checkCollision(List<Colliders> colliders,long elaspedTime) {

    	for(Colliders c:colliders) {
    		if(!c.isPlayer()&&!c.isBullet()) {
        	if(xPos<1)colliders.remove(this);
        	if(xPos>Main.WIDTH-this.width)colliders.remove(this);
        	if(yPos<1)colliders.remove(this);
        	if(yPos>Main.HEIGHT-this.height)colliders.remove(this);
        	double xDistance = c.getX()+5-xPos+5;
        	double yDistance = c.getY()+5-yPos+5;
        	double radiusSum = 15;
        	double distance = Math.sqrt(Math.pow(xDistance, 2) + Math.pow(yDistance, 2));
    	  	if(distance <= radiusSum) {
    	  		colliders.remove(this);
    	  		colliders.remove(c);
    	  		Explosion e = new Explosion(xPos,yPos);
    	  		Shooter.explosions.add(e);
    	  		System.out.println("bullet gone");
    	  	}
    		}

    	}
    }
    public void move(long elaspedTime){
    	if(up) yPos-=Math.round(-speedy*elaspedTime);
    	if(down) yPos+=Math.round(speedy*elaspedTime);
    	if(left) xPos-=Math.round(-speedx*elaspedTime);
    	if(right) xPos+=Math.round(speedx*elaspedTime);
    	//circle.setFrame(xPos,yPos,width,height);


    }
    public void draw(Graphics2D g){
        g.setColor(Color.BLACK);
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
    public void setSpeedX(float speedx){
        this.speedx=speedx;
    } 

    public float getSpeedX(){
        return speedx;
    }
    public void setSpeedY(float speedy){
        this.speedy=speedy;
    } 

    public float getSpeedY(){
        return speedy;
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
		return true;
	}
	
}