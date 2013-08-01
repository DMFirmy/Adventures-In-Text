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
     * This method will run the associated check to test this GameCondition by calling the runConditionCheck() method.
     * This method will then check whether this GameCondition is marked as negated or not. If the condition is marked as
     * negated, than the opposite result of what is actually returned from the call to runConditionCheck() should be 
     * returned from this method.
     * 
     * @return True if the given condition is detemined to be true, false otherwise, or the opposite if negated.
     */
    public abstract boolean checkCondition();
    
    /**
     * This method will perform the actual check to determine if this GameCondition is true or not. Implementations of
     * this method should never be affected by the status of whether this condition is marked as negated or not. Instead,
     * the negated status of this GameCondition should be handled within the checkCondition() method.
     * 
     * @return True if this GameCondition is determined to be being met, false if it is not.
     */
    abstract boolean runConditionCheck();
    
    /**
     * This method is used to set whether this GameCondition should be negated or not. A negated GameCondition will
     * return the opposite result for a call to checkCondition() than the actual result that is recieved from a call to
     * runConitionCheck(). For example, if a GameCondition implementation is checking whether a given GameComponent has
     * a specified name, a call to the runConditionCheck() method will return true if the name is the one specified. In
     * this case, a call to checkCondition would also return true. If, however, the condition were marked as negated,
     * then the call to checkCondition() would instead return a value of false.
     * 
     * @param newNegated The boolean value to set the negated status for this GameCondition to.
     */
    public abstract void setNegated(boolean newNegated);
    
    /**
     * Checks whether this game condition is marked as negated or not.
     * 
     * @return True if this GameCondition is marked as negated, false if not.
     */
    public abstract boolean isNegated();
    
}
