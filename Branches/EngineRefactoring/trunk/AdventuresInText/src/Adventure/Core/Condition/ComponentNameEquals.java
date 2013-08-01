package Adventure.Core.Condition;

import Adventure.API.*;

import Adventure.Base.*;

/**
 * This GameCondition implementation will check whether or not the given GameComponent has the name that is provided.
 */
public class ComponentNameEquals
    extends BaseCondition
{
    @SuppressWarnings( "compatibility:4595167946005003391" )
    private static final long serialVersionUID = 1L;

    private GameComponent component;
    private String nameToCompare;
    
    /**
     * This constructor will build and set up the condition.
     * 
     * @param name The GameComponent name to be assigned to this condition. 
     * @param theComponent The GameComponent to be checked.
     * @param nameToCompare The name to be compared to.
     */
    public ComponentNameEquals(String name, GameComponent theComponent, String nameToCompare)
    {
        super(name);
        this.component = theComponent;
        this.nameToCompare = nameToCompare;
    }
    /**
     * This constructor will build and set up the condition.
     * 
     * @param name The GameComponent name to be assigned to this condition. 
     * @param theComponent The GameComponent to be checked.
     * @param nameToCompare The name to be compared to.
     * @param negated The boolean value to mark as the negated status for this condition.
     */
    public ComponentNameEquals(String name, GameComponent theComponent, String nameToCompare, boolean negated)
    {
        super(name, negated);
        this.component = theComponent;
        this.nameToCompare = nameToCompare;
    }
    
    /**
     * Checks if the GameComponent name is equal to the given name.
     * 
     * @return True if the GameComponent has the status, false if it doesn't.
     */
    public boolean runConditionCheck()
    {
        if ( this.component.getName().equalsIgnoreCase( this.nameToCompare) )
        {
            return true;
        }
        return false;
    }
}
