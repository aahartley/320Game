package assets;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.util.List;

public abstract class Colliders {

	public abstract int getX();

	public abstract void draw(Graphics2D g);

	public abstract int getY();

	public abstract void move(int x, int y, long elaspedTime);

	public abstract void move(long elaspedTime);

	public abstract void checkCollision(List<Colliders> colliders, long elaspedTime);

	public abstract int getWidth();

	public abstract int getHeight();

	public abstract boolean isPlayer();

	public abstract boolean isBullet();
}
