package starwars.actions;

import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.SWActor;
import starwars.SWAffordance;
import starwars.SWEntityInterface;
import starwars.SWWorld;
import starwars.entities.actors.Player;

public class Travel extends SWAffordance{
	
	private SWWorld world;
	
	public Travel(SWEntityInterface theTarget, MessageRenderer m, SWWorld w) {
		super(theTarget, m);
		priority = 1;
		this.world = w;
		
	}

	/** 
	 * allow <code>SWActor a</code> to travel between worlds 
	 * 
	 * @param <code>SWActor a</code>
	 * @author Linh
	 * @author Justin
	 * @return True for all <code>SWActor a</code> to travel
	 */
	@Override
	public boolean canDo(SWActor a) {
		return true;
	}
	
	/**
	 * this function checks if the  <code>SWActor a</code> is Player
	 * 
	 * if yes then it will display a new world that player will go into
	 * else then other actors will travel into another world but display will
	 * still be at current world as player is still in current world
	 * 
	 * @param <code>SWActor a</code> that is traveling
	 * @author User-G
	 *   
	 */
	
	@Override
	public void act(SWActor a) {
		this.world.switchWorld(a);
		if (a instanceof Player) {
			this.world.swapWorld();
		}
	}

	@Override
	public String getDescription() {
		return "Go to other world?";
	}
	
	@Override
	public int getDuration() {
		return 2;
	}
	
	
}
