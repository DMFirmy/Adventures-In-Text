package Adventure.API;

import Adventure.*;

import java.util.*;

/**
 * This interface is used to represent an event within the game that will be checked for associated conditions
 * each turn, and if they come back as true, the code within the event will be run. GameEvents are run after the player
 * takes their turn but before the next turn's output is displayed, and they can be used to perform any types of action
 * that will effect the game and the output returned. It is worth noting that a Gameevent can have as many GameCondition
 * objects attached to it as you would like, and the code within the event will only be run if all of the conditions are
 * true. This will allow for the creation of custom rule sets that are as simple or as complex as necessary for the
 * storyline of a given level.
 */
public interface GameEvent
    extends GameMechanic
{
    /**
     * This method is used to add a GameCondition object to the list of conditions to be checked before running
     * the code in the processEvent() method each turn. It will loop through the master component list from the
     * Engine class and add the GameCondition object with the given name to the conditios list for this event.
     * 
     * @param conditionName The string name of the condition toadd to the list for this event.
     */
    public abstract void addEventCondition( String conditionName );

    /**
     * This method is used to add a given GameCondition object to the list of conditions to be checked before running
     * the code in the processEvent() method each turn.
     * 
     * @param newCondition The new GameCondition object to add to the list of conditions for this event.
     */
    public abstract void addEventCondition( GameCondition newCondition );

    /**
     * This method is used to loop through all of the GameCondition objects in the list for this event, checking if
     * each of them return true in turn.
     * 
     * @return True if all GameCondition checks pass or if there are no GameConditions for this event, false otherwise.
     */
    public abstract boolean checkEventConditions();

    /**
     * This method is used to run the actual event code each turn that this event is determined to be valid to run.
     * It takes a reference to the current Operation object that is being processed so that the components that are being
     * currently worked with are available for manipulation within the event code.
     * 
     * @param operation The Operation object currently being worked with by the Processor.
     */
    public abstract void processEvent( Operation operation );

    /**
     * This method is used to determine if there is a GameCondition with the given name that is in the
     * list of GameCondition objects associated with this event.
     * 
     * @param conditionName The string name of the GameCondition to be checked for.
     * @return True if a Gamecondition is found with the given name, false otherwise.
     */
    public abstract boolean hasEventCondition( String conditionName );

    /**
     * This method is used to determine if there are any GameCondition objects within the list of conditions for
     * this event.
     * 
     * @return True if there are any GameCondition objects in the list for this event, false otherwise.
     */
    public abstract boolean hasEventConditions();

    /**
     * This method is used to retrieve a copy of the list containing all of the GameCondition objects associate
     * with this event.
     * 
     * @return A list of GameCondition objects attached to this event.
     */
    public abstract ArrayList<GameCondition> conditionList();
}
