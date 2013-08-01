package Adventure.Demo.Condition;

import Adventure.API.*;

import Adventure.Base.*;

/**
 * This condition will test a given GameComponent to see if it doesn't have a given status.
 */
public class ComponentLacksStatus
    extends BaseCondition
{
    @SuppressWarnings( "compatibility:2589478914088872868" )
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
    public ComponentLacksStatus(String name, GameComponent theComponent, String theStatusName)
    {
        super(name);
        this.component = theComponent;
        this.statusName = theStatusName;
    }

    /**
     * Checks if the GameComponent has the named status.
     * 
     * @return True if the GameComponent doesn't have the status, false if it does.
     */
    public boolean checkCondition()
    {
        if ( !this.component.hasStatus(this.statusName) )
        {
            return true;
        }
        return false;
    }
}
