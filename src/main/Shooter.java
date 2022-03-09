package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.*;

import assets.*;

public class Shooter extends JPanel  {

	private static final long serialVersionUID = 1L;
	
    public final List<Colliders> colliders;
    public static List<Explosion> explosions = null;
    private Crosshair crosshair;
    private Colliders player;
    private EnemyGenerator gen;
    
 
	public Shooter() {
		colliders = new CopyOnWriteArrayList<>();
		explosions = new CopyOnWriteArrayList<>();
		crosshair=null;
		player = null;
		gen=null;
        setBorder(BorderFactory.createLineBorder(Color.black));
        setBackground(Color.gray);
        setIgnoreRepaint(true);
 
     
	}
	public void add(Colliders e) {
		this.colliders.add(e);
    }
	public void add(Crosshair c) {
		this.crosshair=(c);
    }
	public void addPlayer(Colliders p) {
		this.player=(p);
		this.colliders.add(p);
    }
	public void addGen(EnemyGenerator gen) {
		this.gen=gen;
    }
	@Override
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
       	for(Explosion e: explosions) {
       		e.draw(g2d);
    	}
        for(Colliders e: colliders) {
            e.draw(g2d);
        }
        crosshair.draw(g2d);
        g2d.dispose();
        g.dispose();
	}
	public void update(long elaspedTime) {
	
		if(gen.getNumOfEnemies()==0) {
			gen.update();
		}
		for(Colliders e: colliders.subList(1, colliders.size())) {
			e.move(player.getX(), player.getY(),elaspedTime);
			if(e.isBullet())
				((Bullet)e).move(elaspedTime);
		}
		player.move(elaspedTime);
		
		checkCollision(elaspedTime);
		
	}
	public void checkCollision(long elaspedTime) {
		for(Colliders e: colliders) {
			e.checkCollision(colliders,elaspedTime);
		}
		//player.checkCollision(colliders,elaspedTime);
	}
	public Dimension getPreferredSize() {
		return new Dimension(Main.WIDTH,Main.HEIGHT);
	}
	public Crosshair getCrosshair() {
		return crosshair;
	}
}

