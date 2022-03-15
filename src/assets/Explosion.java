package assets;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Explosion {
	
	float gravity=0.5f;
	int x, y;
	int time=0;
	int state=1;
	int right, left,down,up=0;

	public Explosion() {}
	public Explosion(int x, int y) {
		this.x=x;
		this.y=y;
	}
	public void draw(Graphics2D g) {
		if(state==1) {
		g.setColor(Color.orange);
		g.drawOval(x+right,y,1,1);  //right
		g.drawOval(x-left,y,1,1);  //left
		g.drawOval(x,y+down,1,1);  //down
		g.drawOval(x,y-up,1,1);  //up

	//	g.drawOval(x, y, 5, 5);
		}
	}
	public void update(float elaspedTime) {
		time+=elaspedTime;
		//x++;
		//y++;
		right++;left++;up++;down++;
		System.out.println(time);
		if(time>=1000) {
			this.state=0;
			time=0;
		}
	}
}
