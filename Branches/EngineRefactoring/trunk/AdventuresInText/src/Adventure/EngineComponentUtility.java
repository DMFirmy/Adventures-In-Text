package Adventure;

import Adventure.API.*;

import Adventure.API.Utility.*;

import Adventure.Base.Utility.*;

import java.util.*;

public class EngineComponentUtility
    extends BaseEngineUtility
    implements GameEngineComponentUtility
{

    private static ArrayList<GameComponent> masterComponentList;

    @SuppressWarnings( "compatibility:8857067558553225039" )
    private static final long serialVersionUID = 1L;

    public EngineComponentUtility()
    {
        super( "DefaultEngineUtility" );
        this.masterComponentList = new ArrayList<GameComponent>();
    }

    /**
     * This method is used to get a copy of the master component list.
     *
     * @return A list of GameComponent objects that are currently available.
     */
    public ArrayList<GameComponent> componentList()
    {
        ArrayList<GameComponent> componentList = new ArrayList<GameComponent>();
        componentList.addAll( masterComponentList );
        return componentList;
    }

    /**
     * This method is used to add a GameComponent object to the master list.
     *
     * @param newComponent The GameComponent object to be added.
     */
    public void addComponent( GameComponent newComponent )
    {
        boolean add = true;
        for ( GameComponent component : masterComponentList )
        {
            if ( component.getName().equalsIgnoreCase( newComponent.getName() ) )
            {
                add = false;
            }
        }

        if ( add )
        {
            masterComponentList.add( newComponent );
        }
    }

    /**
     * This method will allow you to add an entire ArrayList of GameComponent objects to the component list at once.
     *
     * @param newComponents A list of GameComponent objects to add to the command list.
     */
    public void addComponents( ArrayList<GameComponent> newComponents )
    {
        for ( GameComponent newComponent : newComponents )
        {
            addComponent( newComponent );
        }
    }

    /**
     * This method is used to remove a component from the master list.
     *
     * @param component The GameComponennt object to be removed.
     */
    public void removeComponent( GameComponent component )
    {
        masterComponentList.remove( component );
    }

    /**
     * This method is used to remove a component from the master list.
     *
     * @param componentName The String name of the GameComponent object to remove.
     */
    public void removeComponent( String componentName )
    {
        GameComponent component = this.getComponent( componentName );
        masterComponentList.remove( component );
    }

    /**
     * This method will completely clear out the master component list.
     */
    public void clear()
    {
        if ( !masterComponentList.isEmpty() )
        {
            masterComponentList.clear();
        }
    }

    /**
     * This method is used by all of the various components of the game to get a reference to another component with a given
     * string name.
     *
     * @param componentName This is the name of the component you want a reference to.
     * @return The component that has the given name, or null if none is found.
     */
    public GameComponent getComponent( String componentName )
    {
        for ( GameComponent component : componentList() )
        {
            if ( component.getName().equalsIgnoreCase( componentName ) )
            {
                return component;
            }
        }
        return null;
    }

    /**
     * This is a helper method for level builders to get a single GameAction from the master list.
     *
     * @param actionName The String name of the GameAction you want to retrieve.
     * @return A reference to the GameAction object with the given name or null if none is found.
     */
    public GameAction getAction( String actionName )
    {
        GameComponent component = getComponent( actionName );
        if ( component instanceof GameAction )
        {
            return ( GameAction ) component;
        }
        return null;
    }

    /**
     * This is a helper method for level builders to get a single GameActor from the master list.
     *
     * @param actorName The String name of the GameActor you want to retrieve.
     * @return A reference to the GameActor object with the given name or null if none is found.
     */
    public GameActor getActor( String actorName )
    {
        GameComponent component = getComponent( actorName );
        if ( component instanceof GameActor )
        {
            return ( GameActor ) component;
        }
        return null;

    }

    /**
     * This is a helper method for level builders to get a single GameCommand from the master list.
     *
     * @param commandName The String name of the GameCommand you want to retrieve.
     * @return A reference to the GameCommand object with the given name or null if none is found.
     */
    public GameCommand getCommand( String commandName )
    {
        GameComponent component = getComponent( commandName );
        if ( component instanceof GameCommand )
        {
            return ( GameCommand ) component;
        }
        return null;
    }

    /**
     * This is a helper method for level builders to get a single GameCondition from the master list.
     *
     * @param conditionName The String name of the GameCondition you want to retrieve.
     * @return A reference to the GameCondition object with the given name or null if none is found.
     */
    public GameCondition getCondition( String conditionName )
    {
        GameComponent component = getComponent( conditionName );
        if ( component instanceof GameCondition )
        {
            return ( GameCondition ) component;
        }
        return null;
    }

    /**
     * This is a helper method for level builders to get a single GameContainer from the master list.
     *
     * @param containerName The String name of the GameContainer you want to retrieve.
     * @return A reference to the GameContainer object with the given name or null if none is found.
     */
    public GameContainer getContainer( String containerName )
    {
        GameComponent component = getComponent( containerName );
        if ( component instanceof GameContainer )
        {
            return ( GameContainer ) component;
        }
        return null;
    }

    /**
     * This is a helper method for level builders to get a single GameDialog from the master list.
     *
     * @param dialogName The String name of the GameDialog you want to retrieve.
     * @return A reference to the GameDialog object with the given name or null if none is found.
     */
    public GameDialog getDialog( String dialogName )
    {
        GameComponent component = getComponent( dialogName );
        if ( component instanceof GameDialog )
        {
            return ( GameDialog ) component;
        }
        return null;
    }

    /**
     * This is a helper method for level builders to get a single GameDirection from the master list.
     *
     * @param directionName The String name of the GameDirection you want to retrieve.
     * @return A reference to the GameDirection object with the given name or null if none is found.
     */
    public GameDirection getDirection( String directionName )
    {
        GameComponent component = getComponent( directionName );
        if ( component instanceof GameDirection )
        {
            return ( GameDirection ) component;
        }
        return null;
    }

    /**
     * This is a helper method for level builders to get a single GameEvent from the master list.
     *
     * @param eventName The String name of the GameEvent you want to retrieve.
     * @return A reference to the GameEvent object with the given name or null if none is found.
     */
    public GameEvent getEvent( String eventName )
    {
        GameComponent component = getComponent( eventName );
        if ( component instanceof GameEvent )
        {
            return ( GameEvent ) component;
        }
        return null;
    }

    /**
     * This is a helper method for level builders to get a single GameExit from the master list.
     *
     * @param exitName The String name of the GameExit you want to retrieve.
     * @return A reference to the GameExit object with the given name or null if none is found.
     */
    public GameExit getExit( String exitName )
    {
        GameComponent component = getComponent( exitName );
        if ( component instanceof GameExit )
        {
            return ( GameExit ) component;
        }
        return null;
    }

    /**
     * This is a helper method for level builders to get a single GameHideable from the master list.
     *
     * @param hideableName The String name of the GameHideable you want to retrieve.
     * @return A reference to the GameHideable object with the given name or null if none is found.
     */
    public GameHideable getHideable( String hideableName )
    {
        GameComponent component = getComponent( hideableName );
        if ( component instanceof GameHideable )
        {
            return ( GameHideable ) component;
        }
        return null;
    }

    /**
     * This is a helper method for level builders to get a single GameItem from the master list.
     *
     * @param itemName The String name of the GameItem you want to retrieve.
     * @return A reference to the GameItem object with the given name or null if none is found.
     */
    public GameItem getItem( String itemName )
    {
        GameComponent component = getComponent( itemName );
        if ( component instanceof GameItem )
        {
            return ( GameItem ) component;
        }
        return null;
    }

    /**
     * This is a helper method for level builders to get a single GameLocation from the master list.
     *
     * @param locationName The String name of the GameLocation you want to retrieve.
     * @return A reference to the GameLocation object with the given name or null if none is found.
     */
    public GameLocation getLocation( String locationName )
    {
        GameComponent component = getComponent( locationName );
        if ( component instanceof GameLocation )
        {
            return ( GameLocation ) component;
        }
        return null;
    }

    /**
     * This is a helper method for level builders to get a single GameMap from the master list.
     *
     * @param mapName The String name of the GameMap you want to retrieve.
     * @return A reference to the GameMap object with the given name or null if none is found.
     */
    public GameMap getMap( String mapName )
    {
        GameComponent component = getComponent( mapName );
        if ( component instanceof GameMap )
        {
            return ( GameMap ) component;
        }
        return null;
    }

    /**
     * This is a helper method for level builders to get a single GameMechanic from the master list.
     *
     * @param mechanicName The String name of the GameMechanic you want to retrieve.
     * @return A reference to the GameMechanic object with the given name or null if none is found.
     */
    public GameMechanic getMechanic( String mechanicName )
    {
        GameComponent component = getComponent( mechanicName );
        if ( component instanceof GameMechanic )
        {
            return ( GameMechanic ) component;
        }
        return null;
    }

    /**
     * This is a helper method for level builders to get a single GameObject from the master list.
     *
     * @param objectName The String name of the GameObject you want to retrieve.
     * @return A reference to the GameObject object with the given name or null if none is found.
     */
    public GameObject getObject( String objectName )
    {
        GameComponent component = getComponent( objectName );
        if ( component instanceof GameObject )
        {
            return ( GameObject ) component;
        }
        return null;
    }

    /**
     * This is a helper method for level builders to get a single GamePawn from the master list.
     *
     * @param pawnName The String name of the GamePawn you want to retrieve.
     * @return A reference to the GamePawn object with the given name or null if none is found.
     */
    public GamePawn getPawn( String pawnName )
    {
        GameComponent component = getComponent( pawnName );
        if ( component instanceof GamePawn )
        {
            return ( GamePawn ) component;
        }
        return null;
    }

    /**
     * This is a helper method for level builders to get a single GamePlayer from the master list.
     *
     * @param playerName The String name of the GamePlayer you want to retrieve.
     * @return A reference to the GamePlayer object with the given name or null if none is found.
     */
    public GamePlayer getPlayer( String playerName )
    {
        GameComponent component = getComponent( playerName );
        if ( component instanceof GamePlayer )
        {
            return ( GamePlayer ) component;
        }
        return null;
    }

    /**
     * This is a helper method for level builders to get a single GameSpeaker from the master list.
     *
     * @param speakerName The String name of the GameSpeaker you want to retrieve.
     * @return A reference to the GameSpeaker object with the given name or null if none is found.
     */
    public GameSpeaker getSpeaker( String speakerName )
    {
        GameComponent component = getComponent( speakerName );
        if ( component instanceof GameSpeaker )
        {
            return ( GameSpeaker ) component;
        }
        return null;
    }

    /**
     * This method will check if a GameComponent object exists in the master list with a given name.
     *
     * @param componentName The String name of the GameComponent to check for
     * @return True if a component with the given name is found, false otherwise.
     */
    public boolean hasComponent( String componentName )
    {
        GameComponent component = getComponent( componentName );
        if ( component.getName().equalsIgnoreCase( componentName ) )
        {
            return true;
        }
        return false;
    }

    /**
     * This method will check if a GameComponent object exists in the master list with a given name and if it is
     * an instance of a GameAction.
     *
     * @param actionName The String name of the GameAction to check for
     * @return True if a component with the given name is found, false otherwise.
     */
    public boolean hasAction( String actionName )
    {
        GameComponent component = getComponent( actionName );
        if ( component.getName().equalsIgnoreCase( actionName ) && component instanceof GameAction )
        {
            return true;
        }
        return false;
    }

    /**
     * This method will check if a GameComponent object exists in the master list with a given name and if it is
     * an instance of a GameActor.
     *
     * @param actorName The String name of the GameActor to check for
     * @return True if a component with the given name is found, false otherwise.
     */
    public boolean hasActor( String actorName )
    {
        GameComponent component = getComponent( actorName );
        if ( component.getName().equalsIgnoreCase( actorName ) && component instanceof GameActor )
        {
            return true;
        }
        return false;
    }

    /**
     * This method will check if a GameComponent object exists in the master list with a given name and if it is
     * an instance of a GameCommand.
     *
     * @param commandName The String name of the GameCommand to check for
     * @return True if a component with the given name is found, false otherwise.
     */
    public boolean hasCommand( String commandName )
    {
        GameComponent component = getComponent( commandName );
        if ( component.getName().equalsIgnoreCase( commandName ) && component instanceof GameCommand )
        {
            return true;
        }
        return false;
    }

    /**
     * This method will check if a GameComponent object exists in the master list with a given name and if it is
     * an instance of a GameCondition.
     *
     * @param conditionName The String name of the GameCondition to check for
     * @return True if a component with the given name is found, false otherwise.
     */
    public boolean hasCondition( String conditionName )
    {
        GameComponent component = getComponent( conditionName );
        if ( component.getName().equalsIgnoreCase( conditionName ) && component instanceof GameCondition )
        {
            return true;
        }
        return false;
    }

    /**
     * This method will check if a GameComponent object exists in the master list with a given name and if it is
     * an instance of a GameContainer.
     *
     * @param containerName The String name of the GameContainer to check for
     * @return True if a component with the given name is found, false otherwise.
     */
    public boolean hasContainer( String containerName )
    {
        GameComponent component = getComponent( containerName );
        if ( component.getName().equalsIgnoreCase( containerName ) && component instanceof GameContainer )
        {
            return true;
        }
        return false;
    }

    /**
     * This method will check if a GameComponent object exists in the master list with a given name and if it is
     * an instance of a GameDialog.
     *
     * @param dialogName The String name of the GameDialog to check for
     * @return True if a component with the given name is found, false otherwise.
     */
    public boolean hasDialog( String dialogName )
    {
        GameComponent component = getComponent( dialogName );
        if ( component.getName().equalsIgnoreCase( dialogName ) && component instanceof GameDialog )
        {
            return true;
        }
        return false;
    }

    /**
     * This method will check if a GameComponent object exists in the master list with a given name and if it is
     * an instance of a GameDirection.
     *
     * @param directionName The String name of the GameDirection to check for
     * @return True if a component with the given name is found, false otherwise.
     */
    public boolean hasDirection( String directionName )
    {
        GameComponent component = getComponent( directionName );
        if ( component.getName().equalsIgnoreCase( directionName ) && component instanceof GameDirection )
        {
            return true;
        }
        return false;
    }

    /**
     * This method will check if a GameComponent object exists in the master list with a given name and if it is
     * an instance of a GameEvent.
     *
     * @param eventName The String name of the GameEvent to check for
     * @return True if a component with the given name is found, false otherwise.
     */
    public boolean hasEvent( String eventName )
    {
        GameComponent component = getComponent( eventName );
        if ( component.getName().equalsIgnoreCase( eventName ) && component instanceof GameEvent )
        {
            return true;
        }
        return false;
    }

    /**
     * This method will check if a GameComponent object exists in the master list with a given name and if it is
     * an instance of a GameHideable.
     *
     * @param hideableName The String name of the GameHideable to check for
     * @return True if a component with the given name is found, false otherwise.
     */
    public boolean hasHideable( String hideableName )
    {
        GameComponent component = getComponent( hideableName );
        if ( component.getName().equalsIgnoreCase( hideableName ) && component instanceof GameHideable )
        {
            return true;
        }
        return false;
    }

    /**
     * This method will check if a GameComponent object exists in the master list with a given name and if it is
     * an instance of a GameItem.
     *
     * @param itemName The String name of the GameItem to check for
     * @return True if a component with the given name is found, false otherwise.
     */
    public boolean hasItem( String itemName )
    {
        GameComponent component = getComponent( itemName );
        if ( component.getName().equalsIgnoreCase( itemName ) && component instanceof GameItem )
        {
            return true;
        }
        return false;
    }

    /**
     * This method will check if a GameComponent object exists in the master list with a given name and if it is
     * an instance of a GameLocation.
     *
     * @param locationName The String name of the GameLocation to check for
     * @return True if a component with the given name is found, false otherwise.
     */
    public boolean hasLocation( String locationName )
    {
        GameComponent component = getComponent( locationName );
        if ( component.getName().equalsIgnoreCase( locationName ) && component instanceof GameLocation )
        {
            return true;
        }
        return false;
    }

    /**
     * This method will check if a GameComponent object exists in the master list with a given name and if it is
     * an instance of a GameMap.
     *
     * @param mapName The String name of the GameMap to check for
     * @return True if a component with the given name is found, false otherwise.
     */
    public boolean hasMap( String mapName )
    {
        GameComponent component = getComponent( mapName );
        if ( component.getName().equalsIgnoreCase( mapName ) && component instanceof GameMap )
        {
            return true;
        }
        return false;
    }

    /**
     * This method will check if a GameComponent object exists in the master list with a given name and if it is
     * an instance of a GameMechanic.
     *
     * @param mechanicName The String name of the GameMechanic to check for
     * @return True if a component with the given name is found, false otherwise.
     */
    public boolean hasMechanic( String mechanicName )
    {
        GameComponent component = getComponent( mechanicName );
        if ( component.getName().equalsIgnoreCase( mechanicName ) && component instanceof GameMechanic )
        {
            return true;
        }
        return false;
    }

    /**
     * This method will check if a GameComponent object exists in the master list with a given name and if it is
     * an instance of a GameObject.
     *
     * @param objectName The String name of the GameObject to check for
     * @return True if a component with the given name is found, false otherwise.
     */
    public boolean hasObject( String objectName )
    {
        GameComponent component = getComponent( objectName );
        if ( component.getName().equalsIgnoreCase( objectName ) && component instanceof GameObject )
        {
            return true;
        }
        return false;
    }

    /**
     * This method will check if a GameComponent object exists in the master list with a given name and if it is
     * an instance of a GamePawn.
     *
     * @param pawnName The String name of the GamePawn to check for
     * @return True if a component with the given name is found, false otherwise.
     */
    public boolean hasPawn( String pawnName )
    {
        GameComponent component = getComponent( pawnName );
        if ( component.getName().equalsIgnoreCase( pawnName ) && component instanceof GamePawn )
        {
            return true;
        }
        return false;
    }

    /**
     * This method will check if a GameComponent object exists in the master list with a given name and if it is
     * an instance of a GamePlayer.
     *
     * @param playerName The String name of the GamePlayer to check for
     * @return True if a component with the given name is found, false otherwise.
     */
    public boolean hasPlayer( String playerName )
    {
        GameComponent component = getComponent( playerName );
        if ( component.getName().equalsIgnoreCase( playerName ) && component instanceof GamePlayer )
        {
            return true;
        }
        return false;
    }

    /**
     * This method will check if a GameComponent object exists in the master list with a given name and if it is
     * an instance of a GameSpeaker.
     *
     * @param speakerName The String name of the GameSpeaker to check for
     * @return True if a component with the given name is found, false otherwise.
     */
    public boolean hasSpeaker( String speakerName )
    {
        GameComponent component = getComponent( speakerName );
        if ( component.getName().equalsIgnoreCase( speakerName ) && component instanceof GameSpeaker )
        {
            return true;
        }
        return false;
    }
}
