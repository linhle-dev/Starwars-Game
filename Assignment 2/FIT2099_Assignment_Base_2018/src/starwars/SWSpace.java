package starwars;

import edu.monash.fit2099.gridworld.Grid;
import edu.monash.fit2099.simulator.matter.EntityManager;
import edu.monash.fit2099.simulator.userInterface.MessageRenderer;

public abstract class SWSpace {

	/**
	 * <code>SWGrid</code> of this <code>SWWorld</code>
	 */
	protected SWGrid grid;
	
	/**The entity manager of the world which keeps track of <code>SWEntities</code> and their <code>SWLocation</code>s*/
	private static final EntityManager<SWEntityInterface, SWLocation> entityManager = new EntityManager<SWEntityInterface, SWLocation>();
	
	/** name of <code>SWSpace</code>*/
	private String name;
	
	/** creating the width and height of the space of the worlds*/
	protected int width;
	
	protected int height;

	protected SWWorld world;
	
	/**
	 * Constructor of <code>SWWorld</code>. This will initialize the <code>SWLocationMaker</code>
	 * and the grid.
	 */
	public SWSpace(SWWorld world, String name) {
		SWLocation.SWLocationMaker factory = SWLocation.getMaker();
		this.grid = new SWGrid(factory);
		this.name = name;
		this.world = world;
		
	}
	
	/**
	 * @author Linh
	 * @return the grid of SWSpace
	 */
	public SWGrid getGrid() { return this.grid; }
	
	/** 
	 * Returns the height of the <code>Grid</code>. Useful to the Views when rendering the map.
	 * 
	 * @author ram
	 * @return the height of the grid
	 */
	public int height() {
		return grid.getHeight();
	}
	
	/** 
	 * Returns the width of the <code>Grid</code>. Useful to the Views when rendering the map.
	 * 
	 * @author ram
	 * @return the height of the grid
	 */
	public int width() {
		return grid.getWidth();
	}
	
	/**
	 * Set up the world, setting descriptions for locations and placing items and actors
	 * on the grid.
	 * 
	 * @author 	ram
	 * @param 	iface a MessageRenderer to be passed onto newly-created entities
	 */
	public abstract void initializeSpace(MessageRenderer iface);
	
	
	/**
	 * sets up the defalt settings for the worlds that inherited from this abstact class 
	 * @param iface
	 */
	public void defaultSettings(MessageRenderer iface) {
		SWLocation loc;
		// Set default location string
		for (int row=0; row < height(); row++) {
			for (int col=0; col < width(); col++) {
				loc = grid.getLocationByCoordinates(col, row);
				loc.setLongDescription("SWWorld (" + col + ", " + row + ")");
				loc.setShortDescription("SWWorld (" + col + ", " + row + ")");
				loc.setSymbol('.');				
			}
		}
		
	}
}
