package Adventure.Base;

import Adventure.API.*;

/**
 * This is the base implementation of the GameCondition interface.
 */
public abstract class BaseCondition
    extends BaseMechanic
    implements GameCondition
{
    @SuppressWarnings( "compatibility:8953708310952030740" )
    private static final long serialVersionUID = 1L;

    protected boolean negated;
    
    /**
     * This constructor is used to initialize and set up this condition.
     * 
     * @param name The string name of this command.
     */
    public BaseCondition( String name )
    {
        super( name );
        
        this.negated = false;
    }
    
    /**
     * This constructor is used to initialize and set up this condition.
     * 
     * @param name The string name of this command.
     */
    public BaseCondition( String name, boolean negated )
    {
        super( name );
        
        this.negated = negated;
    }

    /**
     * This method will run the associated check to test this GameCondition.
     *
     * @return True if the given condition is detemined to be true, false otherwise.
     */
    public boolean checkCondition()
    {
        boolean condition = this.runConditionCheck();
        if(this.negated)
        {
            return !condition;
        }
        return condition;
    }
    
    public void setNegated(boolean newNegated)
    {
        this.negated = newNegated;
    }
    
    public boolean isNegated()
    {
        return this.negated;
    }
}
