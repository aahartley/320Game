package assets;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Explosion {
	
	float gravity=0.5f;
	int x, y;

	public Explosion() {}
	public Explosion(int x, int y) {
		this.x=x;
		this.y=y;
	}
	
	public void draw(Graphics2D g) {
		g.setColor(Color.orange);
		g.drawOval(x, y, 5, 5);
	}
	public void update() {
		
	}
}
