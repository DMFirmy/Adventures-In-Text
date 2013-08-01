package Adventure.API;

/**
 * This interface represents a playable level within the game. The Engine will utilize the methods of any available GameMap
 * objects to build the required GameCoponent object for the level and to determine the initial state of the game when
 * play begins.
 */
public abstract interface GameMap
    extends GameComponent
{
    /**
     * Classes that implement the GameMap interface should use this method to instantiate all of the GameCommand objects
     * that will be available as commands for this level. These GameCommand objects need to be passed through the
     * Engine.addCommand() method. Note that GameDirection onjects are counted as GameCommand components, and so all of
     * the directions that will be available for travel within the level should be instantiated in this method as well.
     */
    public abstract void buildMapCommands();

    /**
     * Classes that implement the GameMap interface should instantiate all of the GameLocation objects that will exist
     * within the level, as well as all of the other various GameObject components that will be contained within them.
     * Because this method is called after the buildMapCommands() method, you can access any instantiated GameDirection
     * objects using the Engine.direction( String directionName ) method. Note that GameDialog objects count as GameObject
     * components, so this method should also be used to instantiate any GameDialog objects and to add the necessary branches.
     * This method should also call the setCurrentPlayer( GamePlayer newCurrentPlayer ) method to mark one of the instantiated
     * GamePlayer objects as active.
     */
    public abstract void buildMapLocations();

    /**
     * Classes implementing the GameMap interface should use this method to instantiate any GameMechanic objects that will
     * be utilized by this level. This includes the Gameevent objects which will be run each turn and the GameCondition
     * objects that will be used to control when the events are allowed to run. Any GameComponent objects that have been
     * instantiated using other method calls can be accessed by using the Engine.getComponent( String componentName )
     * method. Note that if you wish to use a GameComponent object returned by this method as some derived type instead
     * of the base GameComponent type, you will need to cast the returned gameComponent object to the correct type.
     */
    public abstract void buildMapMechanics();

    /**
     * This method will get a reference to the GamePlayer object that has been marked as the current player.
     * 
     * @return The GamePlayer object that will be marked as the current player when the GameMap is loaded.
     */
    public abstract GamePlayer getCurrentPlayer();

    /**
     * This method is used to get the stored string reference to the absolute file location that this GameMap was located
     * in. This is because the Loader class will look for GameMap objects from within JAR archives, and the location of
     * the JAR gets stored for future reference. In the future I will find a more elegant means of handling this.
     * 
     * @return The absolute path to the JAR archive containing this GameMap object.
     */
    public abstract String getMapPath();

    /**
     * This method is used to mark the given GamePlayer as the current player for when the map is loaded.
     * 
     * @param newCurrentPlayer The GamePlayer object to be marked as the current player.
     */
    public abstract void setCurrentPlayer( GamePlayer newCurrentPlayer );

    /**
     * This method is used to retrieve a GamePlayer object from the Engine's master list with the given name, and to
     * mark that player as the current player for when the map is loaded.
     * 
     * @param newCurrentPlayerName The string name of the GamePlayer object to be marked as the current player.
     */
    public abstract void setCurrentPlayer( String newCurrentPlayerName );

    /**
     * This method will store the provided absolute file path for the JAR archive that contains this GameMap object.
     * 
     * @param newMapPath The String absolute file path to the JAR archive that contains the current GameMap class file.
     */
    public abstract void setMapPath( String newMapPath );
}
