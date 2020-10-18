Leave: (for UML diagram look in Leave Affordance Folder at starwars.actions.leave jpg file)
create a leave class that extends from the SWAffordance{}
Leave class will be in the Actions package along with the Take class and other actions classes
    
    methods:
    cando() - return true if the actor is carrying an object
    act()   - put the object down
    
    dependencies:
    SWActor - leave class will have an attribute of a SWActor so leave class could get the item from the actor
    SWEntityInterface - Leave class will need a target, and the target is an instance of SWEntityInterface
    
    subclass of:
    SWAffordance - leave class will extends from SWAffordance and implementing SWActionInterface
    
    
Force Ability: (for UML diagram look in starwars.SWForce jpg file)
create a SWForce class that is an independent class and does not belong to any package because its purpose
is only to store the value of force each actor have. Each actor will have SWForce attribute

    methods:
    getForce()      - returns force
    setForce()      - set the force
    increaseForce() - increase force value
    decreaseForce() - decrease force value
    
    Associations:
    SWActor - Each class in extends from SWActor will have an attribute of SWforce in their class and can
              be able to use all its methods
    
    
    
LightSabres: (for UML interaction diagram look in starwars.entities.LightSaber jpg file) 
create a new method inside lightsabres class.

You may also see the relationship between LightSabres and the SWForce in starwars.entities.actors(LightSabres).JPG


    methods:
    canWield() - call the force from SWActor using getForce().
                 returns True if the force is enough False if force is not enough
    
    
    
    
    
BenKenobi: (for UML diagram look inside the BenKenobi folder, starwars.actions.Train(BenKenobi)version 1.jpg and starwars.acions BenKenobi version1.jpg). 
We create a “Train” class  in the starwars.actions.Train(BenKenobi) and it is subclass of SWAffordance. As it has similar behavior as Take and Leave.

    Method:
    Cando() – returns true if Ben and Luke are in the same location
    locationCheck() – If statement, if the returns is true from cando() it moves on to the next function. 
    increaseForce(): increase the amount of force to Luke
    
    Dependencies:
    SWAction - Train depends to the SWAction abstract class as it is going to use the abstract method in SWAction
    
    Subclass:
    SWAffordance - Train extends from it as it is an action similar as take and leave so that it can follow the order of what can be done from the SWAffordance.
    
    


Droids:  (for UML diagram look inside Starwars.SWActor.Droid.jpg )
We created the Droid class inside the Actor. As Droid can be an actor and it owns its behavior. 
So Droid should also implements from SWEntities interface to use the method inside of SWEntities.

    Method:
    
    follow():to perform the following action to its owner
    getlocation(): returns its location
    boolean isOwned(): returns true if the droid is owned by another actor
    boolean ownDroid(): returns true if the droi is already owned by an actor
     
    getHitPoint(): returns the health of the Droid
    takeDamage(): reduce the hit point of the Droid
    
    Assosication:
    Follow - Droids assosicated to Follow as Droid has a movement and we have to control it. 
    SWActor - Droids associated with it as Droid is one of the Actor in the system and it has similar behavior with them.


Assignment 3

Resevoir: (for UML diagram look inside Assignment1 folder then go into resevoir folder)
in resevoir we set hitpoints for it to be equal to 40

    Method:
    
    damageTaken(): this method will check the condition of resevoir then change the state of resevoir
                    when the hit point is decreased, if the hitpoint is 0 or below, it cannot be attacked anymore
    
    takenDamage(): this is an Overrride method that will decrease the health of resevoir if its being attacked then call the
                    damageTaken() method to check it's condition and change its state.

Thrown: (for UML diagram look inside Assignment1 folder then go into Grenade folder)

    We created a new class in starwars.action package called Thrown.
    Thrown class will extends SWAffordance. It will also implement SWActionInterface and it will use 
    canDo() and act() methods

    Method act()
    
            this method will execute when thrown command is used by user and will set the item carried by actor to null
            it will also damage the entities around the actor according to the specification
            
            this method is designed to be reuse by other entities

Grenade: (for UML diagram look inside Assignment1 folder then go into Grenade folder)

    we created a new class in starwars.entities package
    the grenade class will extends SWEntity
    Grenade class will follow a similar conventions of how other entities are created

Jawa Sandcrawler: (for UML diagram look inside Assignment1 folder then go into JawaSandcrawler folder)

    We create a new class Sandcrawler in the starwars.entities.actors package
    Sandcrawler will extends from SWActors
    
    Variables:
    
        a private class variable of type Patrol will be initialised in the contructor as the sandcrawler will have
        similar movement of Ben Kenobi
        
        a private class variable of type boolean to determine whether or not the sandcrawler can move in its current turn
    
        add affordance Travel to Sandcrawler in the constructor

    method act()
        
        Sandcrawler will have movements similar to that of Ben Kenobi
        
        If sandcrawler meets the player, player can choose whether to travel inside its world (CrawlerWorld) or 
        to stay in the current one (RegularWorld)
        
        If sandcrawler meets a droid it will take the droid into another world (CrawlerWorld) and the droid cannot come back
        
        if sandcralwer meets an actor that is not the player, Actor can travel between the worlds

        Sandcrawler will be instantiate in the RegularWorld class


Gate: (for UML diagram look inside Assignment1 folder then go into JawaSandcrawler folder)

    We create a new class Gate class in the starwars.entities.actors package
    Gate will extends from SWActors 
    (Note: although it would makes more sense that the Gate to the RegularWorld inside CrawlerWorld to be an entity and not
    an actor, it is more suitable for it to be an actor because we can use the act() method to check the force ability of actor
    to let them go in and out)

    method act()
        Gate will check for the entities in its location, it they are and actor and they have the force
        they can use the gate to travel outside
        
        There will be a Travel affordance added in the constructor of Gate, much like the Sandcrawler

Travel: (for UML diagram look inside Assignment1 folder then go into JawaSandcrawler folder)

    Travel is a class that will extends SWAffordance in the same category as Attack, Take and Leave
    as the player wants to choose the action they want to perform
    
    methods
        canDo() - returns true as all actors can travel
        
        act() - set the location of the one who is traveling to other world
                if it is player, display the world that the actor is going into
                
SWSpace: (for UML diagram look inside Assignment1 folder then go into JawaSandcrawler folder)

    this class is an abstract class in starwars package
    this allows other classes that is going to be other Worlds to extends from it
    
    the constructor of SWSpace initialised the name of the World, the World, the SWLocationMaker and the grid
    
    methods
        getGrid() - returns the grid
        
        height() - returns the height of the Grid
        
        width() - returns the width of the Grid
        
        abstract method intializeSpace(MessageRenderer iface) - allows other classes that extends from SWSpace to implement
                                                                this method, purpose is to initialize what is going to be in that
                                                                specific world
        defaultSettings(MessageRenderer iface) - to set the default grid for other worlds that extends from this world

RegularWorld: (for UML diagram look inside Assignment1 folder then go into JawaSandcrawler folder)
    
    this is a class that extends from SWSpace
    
    implements the initializeSpace() method and create the objects in the world of starwars
        - copy the part that creates the things we see in the display from SWWorld initializeWorld to this method
        - instantiate a Sandcrawler object in this RegularWorld

CrawlerWorld: (for UML diagram look inside Assignment1 folder then go into JawaSandcrawler folder)
    
    this is a class that extends from SWSpace
    
    implements the initializeSpace() method
        - instantiate a Gate object in this CrawlerWorld
        
SWWorld: (for UML diagram look inside Assignment1 folder then go into JawaSandcrawler folder)
    
    private class variables:
    
        currentSpace: keeps track of the current space that that player is in
        regularWorld: starwars world
        crawlerWorld: world inside Sandcrawler
    
    methods
     initializeWorld() - create objects of regularWorld and crawlerWorld, sets the currentWorld to regularWorld
     isCrawlerWorld() - return true if currentWorld is crawlerWorld 
     isRegularWorld() - return true if currentWorld is regularWorld
     switchWorld() - set the location of SWActor to other world
     swapWorld() - swap the state of the currentWorld to other world
    
        





    
    
