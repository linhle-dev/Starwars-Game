package worlds;

import edu.monash.fit2099.gridworld.Grid.CompassBearing;
import edu.monash.fit2099.simulator.space.Direction;
import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.SWEntity;
import starwars.SWLocation;
import starwars.SWSpace;
import starwars.SWWorld;
import starwars.Team;
import starwars.actions.Take;
import starwars.entities.Blaster;
import starwars.entities.Canteen;
import starwars.entities.Grenade;
import starwars.entities.LightSaber;
import starwars.entities.Reservoir;
import starwars.entities.actors.BenKenobi;
import starwars.entities.actors.Droid;
import starwars.entities.actors.Player;
import starwars.entities.actors.Sandcrawler;
import starwars.entities.actors.TuskenRaider;

public class RegularWorld extends SWSpace{
	
	private Sandcrawler sandcrawler;
	
	public RegularWorld(SWWorld world) {
		super(world, "Regular World");
	}
	

	/**
	 * making the world of Star Wars in class RegularWorld instead of in class SWWorld
	 * 
	 */
	@Override
	public void initializeSpace(MessageRenderer iface) {
		SWLocation loc;
		defaultSettings(iface);
		// BadLands
		for (int row = 5; row < 8; row++) {
			for (int col = 4; col < 7; col++) {
				loc = grid.getLocationByCoordinates(col, row);
				loc.setLongDescription("Badlands (" + col + ", " + row + ")");
				loc.setShortDescription("Badlands (" + col + ", " + row + ")");
				loc.setSymbol('b');
			}
		}
		
		//Ben's Hut
		loc = grid.getLocationByCoordinates(5, 6);
		loc.setLongDescription("Ben's Hut");
		loc.setShortDescription("Ben's Hut");
		loc.setSymbol('H');
		
		Direction [] patrolmoves = {CompassBearing.EAST, CompassBearing.EAST,
                CompassBearing.SOUTH,
                CompassBearing.WEST, CompassBearing.WEST,
                CompassBearing.SOUTH,
                CompassBearing.EAST, CompassBearing.EAST,
                CompassBearing.NORTHWEST, CompassBearing.NORTHWEST};
		
		BenKenobi ben = BenKenobi.getBenKenobi(iface, world, patrolmoves);
		ben.setSymbol("B");
		loc = grid.getLocationByCoordinates(4,  5);
		world.getEntityManager().setLocation(ben, loc);
		
		
		loc = grid.getLocationByCoordinates(5,9);
		
		// Luke
		Player luke = new Player(Team.GOOD, 100, iface, world);
		luke.setShortDescription("Luke");
		world.getEntityManager().setLocation(luke, loc);
		luke.resetMoveCommands(loc);
		
		
		// Beggar's Canyon 
		for (int col = 3; col < 8; col++) {
			loc = grid.getLocationByCoordinates(col, 8);
			loc.setShortDescription("Beggar's Canyon (" + col + ", " + 8 + ")");
			loc.setLongDescription("Beggar's Canyon  (" + col + ", " + 8 + ")");
			loc.setSymbol('C');
			loc.setEmptySymbol('='); // to represent sides of the canyon
		}
		
		// Moisture Farms
		for (int row = 0; row < 10; row++) {
			for (int col = 8; col < 10; col++) {
				loc = grid.getLocationByCoordinates(col, row);
				loc.setLongDescription("Moisture Farm (" + col + ", " + row + ")");
				loc.setShortDescription("Moisture Farm (" + col + ", " + row + ")");
				loc.setSymbol('F');
				
				// moisture farms have reservoirs
				world.getEntityManager().setLocation(new Reservoir(iface), loc);				
			}
		}
		
		// Ben Kenobi's hut
		/*
		 * Scatter some other entities and actors around
		 */
		// a canteen
		loc = grid.getLocationByCoordinates(3,1);
		SWEntity canteen = new Canteen(iface, 10,0);
		canteen.setSymbol("c");
		canteen.setHitpoints(500);
		world.getEntityManager().setLocation(canteen, loc);
		canteen.addAffordance(new Take(canteen, iface));

		// an oil can treasure
		loc = grid.getLocationByCoordinates(1,5);
		SWEntity oilcan = new SWEntity(iface);
		oilcan.setShortDescription("an oil can");
		oilcan.setLongDescription("an oil can, which would theoretically be useful for fixing robots");
		oilcan.setSymbol("o");
		oilcan.setHitpoints(100);
		// add a Take affordance to the oil can, so that an actor can take it
		world.getEntityManager().setLocation(oilcan, loc);
		oilcan.addAffordance(new Take(oilcan, iface));
		
		// a lightsaber
		LightSaber lightSaber = new LightSaber(iface);
		loc = grid.getLocationByCoordinates(5,5);
		world.getEntityManager().setLocation(lightSaber, loc);
		
		// A blaster 
		Blaster blaster = new Blaster(iface);
		loc = grid.getLocationByCoordinates(3, 4);
		world.getEntityManager().setLocation(blaster, loc);
		
		// A Tusken Raider
		TuskenRaider tim = new TuskenRaider(10, "Tim", iface, world);
		tim.setSymbol("t");
		loc = grid.getLocationByCoordinates(4,3);
		world.getEntityManager().setLocation(tim, loc);
		
		// Creating an object droid
		Droid d2 = new Droid(10, "D2", iface, world, luke);
		d2.setSymbol("D2");
		loc = grid.getLocationByCoordinates(7, 1);
		world.getEntityManager().setLocation(d2, loc);
		
		// Creating an object droid
		Droid d3 = new Droid(10, "D3", iface, world, luke);
		d2.setSymbol("D3");
		loc = grid.getLocationByCoordinates(7, 5);
		world.getEntityManager().setLocation(d3, loc);
		
		//Creating grenade in the world
		Grenade g1 = new Grenade(iface);
		loc = grid.getLocationByCoordinates(6, 7);
		world.getEntityManager().setLocation(g1, loc);
		
		Grenade g2 = new Grenade(iface);
		loc = grid.getLocationByCoordinates(7, 9);
		world.getEntityManager().setLocation(g2, loc);
		
		//creating sandcrawler
		this.sandcrawler = new Sandcrawler("crawler1", iface, world, patrolmoves);
		sandcrawler.setSymbol("S1");
		loc = grid.getLocationByCoordinates(6, 5);
		world.getEntityManager().setLocation(sandcrawler, loc);
		
		}
	
	/** 
	 * Returns the position of the <code>Sandcrawler</code>
	 * 
	 * @author Linh
	 * @author Justin
	 * @return the position of the Sandcrawler
	 */
    public SWLocation getEntryPoint() {
    		return this.world.getEntityManager().whereIs(this.sandcrawler);
    }
		
}

