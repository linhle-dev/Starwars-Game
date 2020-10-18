package starwars;



public class SWForce  {
		
	/**
	 * force power of an actor
	 */
	private int forceStat;
	
	
	/**
	 * minimum force to use LightSaber
	 * @author Linh
	 * @author Justin
	 */
	public final int baseForce = 50;
	
	
	/**
	 * Constructor for SWForce instance
	 * @param forceStat: initial force ability
	 * @param hasForceAbility: whether an actor has the force
	 */
	public SWForce (int forceStat) {
		this.forceStat = forceStat;
	}
		
	/**
	 * baseForce getter 
	 * @return return the baseForce
	 */
	public int getBaseForceStat() {
		return baseForce;
	}
	
	/**
	 * force getter
	 * @return return the force of an actor
	 */
	public int getForceStat() {
		return forceStat;
		
	}

	/**
	 * method that increment the force
	 */
	public void incrementForce() {
	   this.forceStat += 10;
	   
	}
}
		

