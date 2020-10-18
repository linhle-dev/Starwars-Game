package worlds;

import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.SWLocation;
import starwars.SWSpace;
import starwars.SWWorld;
import starwars.entities.actors.Gate;
public class CrawlerWorld extends SWSpace {
	
	private Gate gate;
	
	public CrawlerWorld(SWWorld world) {
		super(world, "Sandcrawler World");
	}

	@Override
	public void initializeSpace(MessageRenderer iface) {
		SWLocation loc;
		defaultSettings(iface);
		
		this.gate = new Gate("Gate", iface, world);
		gate.setSymbol("G");
		loc = grid.getLocationByCoordinates(1, 1);
		world.getEntityManager().setLocation(gate, loc);
		
	}
	
	/** 
	 * Returns the position of the <code>Gate</code>
	 * 
	 * @author Linh
	 * @author Justin
	 * @return the position of the Gate
	 */
    public SWLocation getEntryPoint() {
    		return this.world.getEntityManager().whereIs(this.gate);
    }
		

}
