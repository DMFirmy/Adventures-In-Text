package Adventure.Base;

import Adventure.*;

import Adventure.API.*;

import java.util.*;

/**
 * This is the base implementation of the GameEvent interface.
 */
public abstract class BaseEvent
    extends BaseMechanic
    implements GameEvent
{
    @SuppressWarnings( "compatibility:5331470612769222128" )
    private static final long serialVersionUID = 1L;

    /**
     * This field holds a list of conditions that are connected with this event.
     */
    protected ArrayList<GameCondition> conditions;

    /**
     * This constructor is used to initialize and set up this event.
     *
     * @param name The string name of this event.
     */
    public BaseEvent( String name )
    {
        super( name );
        this.conditions = new ArrayList<GameCondition>();
    }

    /**
     * This method is used to add a given GameCondition object to the list of conditions to be checked before running
     * the code in the processEvent() method each turn.
     *
     * @param newCondition The new GameCondition object to add to the list of conditions for this event.
     */
    public void addEventCondition( GameCondition newCondition )
    {
        boolean add = true;
        for ( GameCondition condition : this.conditionList() )
        {
            if ( condition.getName().equalsIgnoreCase( newCondition.getName() ) )
            {
                add = false;
                break;
            }
        }
        if ( add )
        {
            this.conditions.add( newCondition );
        }

    }

    /**
     * This method is used to add a GameCondition object to the list of conditions to be checked before running
     * the code in the processEvent() method each turn. It will loop through the master component list from the
     * Engine class and add the GameCondition object with the given name to the conditios list for this event.
     *
     * @param conditionName The string name of the condition toadd to the list for this event.
     */
    public void addEventCondition( String conditionName )
    {
        GameComponent component = Engine.getComponent( conditionName );

        if ( component != null && component instanceof GameCondition )
        {
            this.addEventCondition( ( GameCondition ) component );
        }
    }

    /**
     * This method is used to determine if there is a GameCondition with the given name that is in the
     * list of GameCondition objects associated with this event.
     *
     * @param conditionName The string name of the GameCondition to be checked for.
     * @return True if a Gamecondition is found with the given name, false otherwise.
     */
    public boolean hasEventCondition( String conditionName )
    {
        return false;
    }

    /**
     * This method is used to determine if there are any GameCondition objects within the list of conditions for
     * this event.
     *
     * @return True if there are any GameCondition objects in the list for this event, false otherwise.
     */
    public boolean hasEventConditions()
    {
        if ( !this.conditions.isEmpty() )
        {
            return true;
        }
        return false;
    }

    /**
     * This method is used to retrieve a copy of the list containing all of the GameCondition objects associate
     * with this event.
     *
     * @return A list of GameCondition objects attached to this event.
     */
    public ArrayList<GameCondition> conditionList()
    {
        return ( ArrayList<GameCondition> ) this.conditions.clone();
    }

    /**
     * This method is used to loop through all of the GameCondition objects in the list for this event, checking if
     * each of them return true in turn.
     *
     * @return True if all GameCondition checks pass or if there are no GameConditions for this event, false otherwise.
     */
    public boolean checkEventConditions()
    {
        for ( GameCondition condition : this.conditionList() )
        {
            if ( !condition.checkCondition() )
            {
                return false;
            }
        }
        return true;
    }
}
