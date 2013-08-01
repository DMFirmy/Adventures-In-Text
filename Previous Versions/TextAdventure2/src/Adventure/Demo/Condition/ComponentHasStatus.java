package Adventure.Demo.Condition;

import Adventure.API.*;

import Adventure.Base.*;

/**
 * This condition will test a given GameComponent to see if it has a given status.
 */
public class ComponentHasStatus
    extends BaseCondition
{
    @SuppressWarnings( "compatibility:1968506424260490245" )
    private static final long serialVersionUID = 1L;

    private GameComponent component;
    private String statusName;

    /**
     * This constructor will build and set up the condition.
     * 
     * @param name The GameComponent name to be assigned to this condition. 
     * @param theComponent The GameComponent to be checked.
     * @param theStatusName The name of the status to check for.
     */
    public ComponentHasStatus(String name, GameComponent theComponent, String theStatusName)
    {
        super(name);
        this.component = theComponent;
        this.statusName = theStatusName;
    }
    
    /**
     * Checks if the GameComponent has the named status.
     * 
     * @return True if the GameComponent has the status, false if it doesn't.
     */
    public boolean checkCondition()
    {
        if ( this.component.hasStatus(this.statusName) )
        {
            return true;
        }
        return false;
    }
}
