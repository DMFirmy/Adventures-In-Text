package Adventure.Condition;

import Adventure.API.*;

import Adventure.Base.*;

/**
 * This condition will test a given GameComponent to see if it has a text string with the given name.
 */
public class ComponentHasText
    extends BaseCondition
{
    @SuppressWarnings( "compatibility:-2805729815429923379" )
    private static final long serialVersionUID = 1L;

    private GameComponent component;
    private String textName;
    
    /**
     * This constructor will build and set up the condition.
     * 
     * @param name The GameComponent name to be assigned to this condition. 
     * @param theComponent The GameComponent to be checked.
     * @param theTextName The name of the text string to check for.
     */
    public ComponentHasText(String name, GameComponent theComponent, String theTextName)
    {
        super(name);
        this.component = theComponent;
        this.textName = theTextName;
    }
    /**
     * This constructor will build and set up the condition.
     * 
     * @param name The GameComponent name to be assigned to this condition. 
     * @param theComponent The GameComponent to be checked.
     * @param theTextName The name of the text string to check for.
     * @param negated The boolean value to mark as the negated status for this condition.
     */
    public ComponentHasText(String name, GameComponent theComponent, String theTextName, boolean negated)
    {
        super(name, negated);
        this.component = theComponent;
        this.textName = theTextName;
    }
    
    /**
     * Checks if the GameComponent has the named text string.
     * 
     * @return True if the GameComponent has the status, false if it doesn't.
     */
    public boolean runConditionCheck()
    {
        if ( this.component.hasStatus(this.textName) )
        {
            return true;
        }
        return false;
    }
}
