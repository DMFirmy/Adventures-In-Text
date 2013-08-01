package Adventure.API;

/**
 * This interface is used to represent a situation to be checked for within the current game state before
 * running an associated GameEvent. This condition could be pretty much anything, so long as it will return either a
 * true or a false answer when checked.
 */
public interface GameCondition
    extends GameMechanic
{
    /**
     * This method will run the associated check to test this GameCondition.
     * 
     * @return True if the given condition is detemined to be true, false otherwise.
     */
    public boolean checkCondition();
}
