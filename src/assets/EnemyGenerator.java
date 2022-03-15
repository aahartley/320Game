package assets;

import java.util.List;

import main.Game;
import main.Main;

public class EnemyGenerator {
	
	private int numOfE;
	private List<Colliders> colliders;
	private float speed=0.05f;
	public EnemyGenerator() {}
	
	public EnemyGenerator(List<Colliders> colliders,int num) {
		this.numOfE = num;
		this.colliders= colliders;
		//not on player
		for(int i=0; i<numOfE/2;++i) {
			int rand = (int) (Math.random()*400);

			Enemy e = new Enemy(Main.WIDTH/2-rand,Main.HEIGHT/2-rand);
			colliders.add(e);
		}
		for(int i=numOfE/2; i<numOfE;++i) {
			int rand = (int) (Math.random()*400);

			Enemy e = new Enemy(Main.WIDTH/2+rand,Main.HEIGHT/2+rand);
			colliders.add(e);
		}
	}
	public void update() {
		numOfE+=2;
		speed+=0.01f;
		for(int i=0; i<numOfE/2;++i) {
			int rand = (int) (Math.random()*400);
			Enemy e = new Enemy(Main.WIDTH/2-rand,Main.HEIGHT/2-rand);
			e.setSpeed(speed);
			colliders.add(e);
		}
		for(int i=numOfE/2; i<numOfE;++i) {
			int rand = (int) (Math.random()*400);
			Enemy e = new Enemy(Main.WIDTH/2+rand,Main.HEIGHT/2+rand);
			e.setSpeed(speed);
			colliders.add(e);
		}
	}
	public void setNumOfEnemies(int num) {
		this.numOfE = num;
	}
	public int getNumOfEnemies() {
		int count=0;
		for(Colliders c: colliders) {
			if(!c.isBullet()&&!c.isPlayer())
				++count;
		}
		return count;
		
	}
	public void reset() {
		for(Colliders c: colliders) {
			if(!c.isPlayer())
				colliders.remove(c);
		}
	}
}
