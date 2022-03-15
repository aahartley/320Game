package main;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import assets.Bullet;
import assets.Colliders;
import assets.Crosshair;
import assets.Enemy;
import assets.EnemyGenerator;
import assets.Player;

public class Game {
	private Thread gameLoop;
	private Shooter shooter;
	private Crosshair crosshair;
	private Colliders enemy;
	private Colliders enemy2;
	private Colliders player;
    public List<Colliders> colliders;
    public List<Bullet>  bullets;
    private EnemyGenerator gen=null;
	private boolean isRunning;
	public static boolean paused =false;
	
	public Game() {
		this.colliders = null;
		this.bullets = new CopyOnWriteArrayList<>();
		createAndShowGUI();
	}
	private void input() {
	
		  this.shooter.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_Q, 0, false), "Q pressed");
	      this.shooter.getActionMap().put("Q pressed", new AbstractAction() {
	    	  @Override
	          public void actionPerformed(ActionEvent e) {
	    		  crosshair.setColor(Color.blue);
	          }
	      });
	      this.shooter.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, false), "W pressed");
	      this.shooter.getActionMap().put("W pressed", new AbstractAction() {
	    	  @Override
	          public void actionPerformed(ActionEvent e) {
	    		  ((Player) player).setUp(true);
	          }
	      });
	      this.shooter.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, true), "W released");
	      this.shooter.getActionMap().put("W released", new AbstractAction() {
	    	  @Override
	          public void actionPerformed(ActionEvent e) {
	    		  ((Player) player).setUp(false);
	          }
	      });
	      this.shooter.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, false), "A pressed");
	      this.shooter.getActionMap().put("A pressed", new AbstractAction() {
	    	  @Override
	          public void actionPerformed(ActionEvent e) {
	    		  ((Player) player).setLeft(true);
	          }
	      });
	      this.shooter.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, true), "A released");
	      this.shooter.getActionMap().put("A released", new AbstractAction() {
	    	  @Override
	          public void actionPerformed(ActionEvent e) {
	    		  ((Player) player).setLeft(false);
	          }
	      });
	      this.shooter.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, false), "S pressed");
	      this.shooter.getActionMap().put("S pressed", new AbstractAction() {
	    	  @Override
	          public void actionPerformed(ActionEvent e) {
	    		  ((Player) player).setDown(true);
	          }
	      });
	      this.shooter.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, true), "S released");
	      this.shooter.getActionMap().put("S released", new AbstractAction() {
	    	  @Override
	          public void actionPerformed(ActionEvent e) {
	    		  ((Player) player).setDown(false);
	          }
	      });
	      this.shooter.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, false), "D pressed");
	      this.shooter.getActionMap().put("D pressed", new AbstractAction() {
	    	  @Override
	          public void actionPerformed(ActionEvent e) {
	    		  ((Player) player).setRight(true);
	          }
	      });
	      this.shooter.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, true), "D released");
	      this.shooter.getActionMap().put("D released", new AbstractAction() {
	    	  @Override
	          public void actionPerformed(ActionEvent e) {
	    		  ((Player) player).setRight(false);
	          }
	      });

	      this.shooter.addMouseListener( new MouseListener(){
	          public void mousePressed(MouseEvent e){
	        	 // System.out.println("click");
	        	  if(paused) {
	        		  paused=false;
	        		  gen.reset();
	        		  gen.setNumOfEnemies(0);
	        	  }
	              crosshair.moveCrosshair(e.getX(),e.getY());
	              Bullet b = new Bullet(player.getX(),player.getY(),e.getX(),e.getY());
	              colliders.add(b);
	          	
	          }

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
	      });
	  
	      this.shooter.addMouseMotionListener(new MouseAdapter(){
	          public void mouseDragged(MouseEvent e){

	        	  crosshair.moveCrosshair(e.getX(),e.getY());
	           }
	      });
	      this.shooter.addMouseMotionListener(new MouseAdapter(){
	          public void mouseMoved(MouseEvent e){
	        	  crosshair.moveCrosshair(e.getX(),e.getY());
	          }
	      });
	        
		 
	}
	 private void setupGameLoop() {
	        // initialise the thread 
	        gameLoop = new Thread(() -> {
	        	// how many frames should be drawn in a second
	            final int FRAMES_PER_SECOND = 60;
	            // calculate how many nano seconds each frame should take for our target frames per second.
	            final long TIME_BETWEEN_UPDATES = 1000000000 / FRAMES_PER_SECOND;
	            // track number of frames
	            int frameCount;
	            // if you're worried about visual hitches more than perfect timing, set this to 1. else 5 should be okay
	            final int MAX_UPDATES_BETWEEN_RENDER = 1;

	            // we will need the last update time.
	            long lastUpdateTime = System.nanoTime();
	            // store the time we started this will be used for updating map and charcter animations
	            long currTime = System.currentTimeMillis();

	            while (isRunning) {
	                long now = System.nanoTime();
	                long elapsedTime = System.currentTimeMillis() - currTime;
	                currTime += elapsedTime;
	                int updateCount = 0;
	                // do as many game updates as we need to, potentially playing catchup.
	                if(!paused) {
	                while (now - lastUpdateTime >= TIME_BETWEEN_UPDATES && updateCount < MAX_UPDATES_BETWEEN_RENDER) {
	                    this.shooter.update(elapsedTime);//Update the entity movements and collision checks etc (all has to do with updating the games status i.e  call move() on Enitites)
	                  //  System.out.println(elapsedTime);
	                    lastUpdateTime += TIME_BETWEEN_UPDATES;
	                    updateCount++;
	                }
	                }

	                // if for some reason an update takes forever, we don't want to do an insane number of catchups.
	                // if you were doing some sort of game that needed to keep EXACT time, you would get rid of this.
	                if (now - lastUpdateTime >= TIME_BETWEEN_UPDATES) {
	                    lastUpdateTime = now - TIME_BETWEEN_UPDATES;
	                }

	                this.shooter.repaint(); // draw call for rendering sprites etc

	                long lastRenderTime = now;

	                //Yield until it has been at least the target time between renders. This saves the CPU from hogging.
	                while (now - lastRenderTime < TIME_BETWEEN_UPDATES && now - lastUpdateTime < TIME_BETWEEN_UPDATES) {
	                    Thread.yield();
	                    now = System.nanoTime();
	                }
	                
	            }
	        });
	    }

    private  void createAndShowGUI() {
        System.out.println("Created GUI on EDT? "+
        SwingUtilities.isEventDispatchThread());
        JFrame f = new JFrame("Swing Paint Demo");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        f.setSize(Main.WIDTH, Main.HEIGHT);
        f.setLocationRelativeTo(null);
        
        shooter = new Shooter();
        f.add(shooter);
        BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");
        f.setCursor(blankCursor);
        
        player = new Player();
        this.shooter.addPlayer(player);
     //   enemy = new Enemy();
       // this.shooter.add(enemy);
      //  enemy2 = new Enemy(Main.WIDTH/2-200,Main.HEIGHT/2-200);
       // this.shooter.add(enemy2);
        crosshair = new Crosshair();
        this.shooter.add(crosshair);
        this.colliders = this.shooter.colliders;
        gen = new EnemyGenerator(colliders,2);
        shooter.addGen(gen);

        f.pack();
        input();
        setupGameLoop();
        f.setVisible(true);
        this.isRunning = true;
        this.gameLoop.start();
    } 
}
