package Adventure.API.Utility;

import Adventure.API.*;

import java.util.*;

public interface GameEngineComponentUtility
    extends GameEngineUtility
{
    /**
     * This method is used to get a copy of the master component list.
     *
     * @return A list of GameComponent objects that are currently available.
     */
    public abstract ArrayList<GameComponent> componentList();
    
    /**
     * This method is used to add a GameComponent object to the master list.
     *
     * @param newComponent The GameComponent object to be added.
     */
    public abstract void addComponent( GameComponent newComponent );
    
    /**
     * This method will allow you to add an entire ArrayList of GameComponent objects to the component list at once.
     *
     * @param newComponents A list of GameComponent objects to add to the command list.
     */
    public abstract void addComponents( ArrayList<GameComponent> newComponents );
    
    /**
     * This method is used to remove a component from the master list.
     *
     * @param component The GameComponennt object to be removed.
     */
    public abstract void removeComponent( GameComponent component );
    
    /**
     * This method is used to remove a component from the master list.
     *
     * @param componentName The String name of the GameComponent object to remove.
     */
    public abstract void removeComponent( String componentName );
    
    /**
     * This method will completely clear out the master component list.
     */
    public abstract void clear();
    
    /**
     * This method is used by all of the various components of the game to get a reference to another component with a given
     * string name.
     *
     * @param componentName This is the name of the component you want a reference to.
     * @return The component that has the given name, or null if none is found.
     */
    public abstract GameComponent getComponent( String componentName );
    
    /**
     * This is a helper method for level builders to get a single GameAction from the master list.
     *
     * @param actionName The String name of the GameAction you want to retrieve.
     * @return A reference to the GameAction object with the given name or null if none is found.
     */
    public abstract GameAction getAction( String actionName );
    
    /**
     * This is a helper method for level builders to get a single GameActor from the master list.
     *
     * @param actorName The String name of the GameActor you want to retrieve.
     * @return A reference to the GameActor object with the given name or null if none is found.
     */
    public abstract GameActor getActor( String actorName );
    
    /**
     * This is a helper method for level builders to get a single GameCommand from the master list.
     *
     * @param commandName The String name of the GameCommand you want to retrieve.
     * @return A reference to the GameCommand object with the given name or null if none is found.
     */
    public abstract GameCommand getCommand( String commandName );
    
    /**
     * This is a helper method for level builders to get a single GameCondition from the master list.
     *
     * @param conditionName The String name of the GameCondition you want to retrieve.
     * @return A reference to the GameCondition object with the given name or null if none is found.
     */
    public abstract GameCondition getCondition( String conditionName );
    
    /**
     * This is a helper method for level builders to get a single GameContainer from the master list.
     *
     * @param containerName The String name of the GameContainer you want to retrieve.
     * @return A reference to the GameContainer object with the given name or null if none is found.
     */
    public abstract GameContainer getContainer( String containerName ); 
    
    /**
     * This is a helper method for level builders to get a single GameDialog from the master list.
     *
     * @param dialogName The String name of the GameDialog you want to retrieve.
     * @return A reference to the GameDialog object with the given name or null if none is found.
     */
    public abstract GameDialog getDialog( String dialogName );
    
    /**
     * This is a helper method for level builders to get a single GameDirection from the master list.
     *
     * @param directionName The String name of the GameDirection you want to retrieve.
     * @return A reference to the GameDirection object with the given name or null if none is found.
     */
    public abstract GameDirection getDirection( String directionName );
    
    /**
     * This is a helper method for level builders to get a single GameEvent from the master list.
     *
     * @param eventName The String name of the GameEvent you want to retrieve.
     * @return A reference to the GameEvent object with the given name or null if none is found.
     */
    public abstract GameEvent getEvent( String eventName );
    
    /**
     * This is a helper method for level builders to get a single GameExit from the master list.
     *
     * @param exitName The String name of the GameExit you want to retrieve.
     * @return A reference to the GameExit object with the given name or null if none is found.
     */
    public abstract GameExit getExit( String exitName );
    
    /**
     * This is a helper method for level builders to get a single GameHideable from the master list.
     *
     * @param hideableName The String name of the GameHideable you want to retrieve.
     * @return A reference to the GameHideable object with the given name or null if none is found.
     */
    public abstract GameHideable getHideable( String hideableName );
    
    /**
     * This is a helper method for level builders to get a single GameItem from the master list.
     *
     * @param itemName The String name of the GameItem you want to retrieve.
     * @return A reference to the GameItem object with the given name or null if none is found.
     */
    public abstract GameItem getItem( String itemName );
    
    /**
     * This is a helper method for level builders to get a single GameLocation from the master list.
     *
     * @param locationName The String name of the GameLocation you want to retrieve.
     * @return A reference to the GameLocation object with the given name or null if none is found.
     */
    public abstract GameLocation getLocation( String locationName );
    
    /**
     * This is a helper method for level builders to get a single GameMap from the master list.
     *
     * @param mapName The String name of the GameMap you want to retrieve.
     * @return A reference to the GameMap object with the given name or null if none is found.
     */
    public abstract GameMap getMap( String mapName );
    
    /**
     * This is a helper method for level builders to get a single GameMechanic from the master list.
     *
     * @param mechanicName The String name of the GameMechanic you want to retrieve.
     * @return A reference to the GameMechanic object with the given name or null if none is found.
     */
    public abstract GameMechanic getMechanic( String mechanicName );
    
    /**
     * This is a helper method for level builders to get a single GameObject from the master list.
     *
     * @param objectName The String name of the GameObject you want to retrieve.
     * @return A reference to the GameObject object with the given name or null if none is found.
     */
    public abstract GameObject getObject( String objectName );
    
    /**
     * This is a helper method for level builders to get a single GamePawn from the master list.
     *
     * @param pawnName The String name of the GamePawn you want to retrieve.
     * @return A reference to the GamePawn object with the given name or null if none is found.
     */
    public abstract GamePawn getPawn( String pawnName );
    
    /**
     * This is a helper method for level builders to get a single GamePlayer from the master list.
     *
     * @param playerName The String name of the GamePlayer you want to retrieve.
     * @return A reference to the GamePlayer object with the given name or null if none is found.
     */
    public abstract GamePlayer getPlayer( String playerName );
    
    /**
     * This is a helper method for level builders to get a single GameSpeaker from the master list.
     *
     * @param speakerName The String name of the GameSpeaker you want to retrieve.
     * @return A reference to the GameSpeaker object with the given name or null if none is found.
     */
    public abstract GameSpeaker getSpeaker( String speakerName );

    /**
     * This method will check if a GameComponent object exists in the master list with a given name.
     *
     * @param componentName The String name of the GameComponent to check for
     * @return True if a component with the given name is found, false otherwise.
     */
    public abstract boolean hasComponent( String componentName );
    
    /**
     * This method will check if a GameComponent object exists in the master list with a given name and if it is
     * an instance of a GameAction.
     *
     * @param actionName The String name of the GameAction to check for
     * @return True if a component with the given name is found, false otherwise.
     */
    public abstract boolean hasAction( String actionName );
    
    /**
     * This method will check if a GameComponent object exists in the master list with a given name and if it is
     * an instance of a GameActor.
     *
     * @param actorName The String name of the GameActor to check for
     * @return True if a component with the given name is found, false otherwise.
     */
    public abstract boolean hasActor( String actorName );
    
    /**
     * This method will check if a GameComponent object exists in the master list with a given name and if it is
     * an instance of a GameCommand.
     *
     * @param commandName The String name of the GameCommand to check for
     * @return True if a component with the given name is found, false otherwise.
     */
    public abstract boolean hasCommand( String commandName );
    
    /**
     * This method will check if a GameComponent object exists in the master list with a given name and if it is
     * an instance of a GameCondition.
     *
     * @param conditionName The String name of the GameCondition to check for
     * @return True if a component with the given name is found, false otherwise.
     */
    public abstract boolean hasCondition( String conditionName );
    
    /**
     * This method will check if a GameComponent object exists in the master list with a given name and if it is
     * an instance of a GameContainer.
     *
     * @param containerName The String name of the GameContainer to check for
     * @return True if a component with the given name is found, false otherwise.
     */
    public abstract boolean hasContainer( String containerName );
    
    /**
     * This method will check if a GameComponent object exists in the master list with a given name and if it is
     * an instance of a GameDialog.
     *
     * @param dialogName The String name of the GameDialog to check for
     * @return True if a component with the given name is found, false otherwise.
     */
    public abstract boolean hasDialog( String dialogName );
    
    /**
     * This method will check if a GameComponent object exists in the master list with a given name and if it is
     * an instance of a GameDirection.
     *
     * @param directionName The String name of the GameDirection to check for
     * @return True if a component with the given name is found, false otherwise.
     */
    public abstract boolean hasDirection( String directionName );
    
    /**
     * This method will check if a GameComponent object exists in the master list with a given name and if it is
     * an instance of a GameEvent.
     *
     * @param eventName The String name of the GameEvent to check for
     * @return True if a component with the given name is found, false otherwise.
     */
    public abstract boolean hasEvent( String eventName );
    
    /**
     * This method will check if a GameComponent object exists in the master list with a given name and if it is
     * an instance of a GameHideable.
     *
     * @param hideableName The String name of the GameHideable to check for
     * @return True if a component with the given name is found, false otherwise.
     */
    public abstract boolean hasHideable( String hideableName );
    
    /**
     * This method will check if a GameComponent object exists in the master list with a given name and if it is
     * an instance of a GameItem.
     *
     * @param itemName The String name of the GameItem to check for
     * @return True if a component with the given name is found, false otherwise.
     */
    public abstract boolean hasItem( String itemName );
    
    /**
     * This method will check if a GameComponent object exists in the master list with a given name and if it is
     * an instance of a GameLocation.
     *
     * @param locationName The String name of the GameLocation to check for
     * @return True if a component with the given name is found, false otherwise.
     */
    public abstract boolean hasLocation( String locationName );
    
    /**
     * This method will check if a GameComponent object exists in the master list with a given name and if it is
     * an instance of a GameMap.
     *
     * @param mapName The String name of the GameMap to check for
     * @return True if a component with the given name is found, false otherwise.
     */
    public abstract boolean hasMap( String mapName );
    
    /**
     * This method will check if a GameComponent object exists in the master list with a given name and if it is
     * an instance of a GameMechanic.
     *
     * @param mechanicName The String name of the GameMechanic to check for
     * @return True if a component with the given name is found, false otherwise.
     */
    public abstract boolean hasMechanic( String mechanicName );
    
    /**
     * This method will check if a GameComponent object exists in the master list with a given name and if it is
     * an instance of a GameObject.
     *
     * @param objectName The String name of the GameObject to check for
     * @return True if a component with the given name is found, false otherwise.
     */
    public abstract boolean hasObject( String objectName );
    
    /**
     * This method will check if a GameComponent object exists in the master list with a given name and if it is
     * an instance of a GamePawn.
     *
     * @param pawnName The String name of the GamePawn to check for
     * @return True if a component with the given name is found, false otherwise.
     */
    public abstract boolean hasPawn( String pawnName );
    
    /**
     * This method will check if a GameComponent object exists in the master list with a given name and if it is
     * an instance of a GamePlayer.
     *
     * @param playerName The String name of the GamePlayer to check for
     * @return True if a component with the given name is found, false otherwise.
     */
    public abstract boolean hasPlayer( String playerName );
    
    /**
     * This method will check if a GameComponent object exists in the master list with a given name and if it is
     * an instance of a GameSpeaker.
     *
     * @param speakerName The String name of the GameSpeaker to check for
     * @return True if a component with the given name is found, false otherwise.
     */
    public abstract boolean hasSpeaker( String speakerName );
}
