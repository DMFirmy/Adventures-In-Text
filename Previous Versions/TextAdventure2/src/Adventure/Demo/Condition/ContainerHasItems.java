package Adventure.Demo.Condition;

import Adventure.API.*;

import Adventure.Base.*;

/**
 * This condition will check for the presence of a given quantity of a given GameItem within a given GameContainer.
 */
public class ContainerHasItems
    extends BaseCondition
{
    @SuppressWarnings( "compatibility:-6808599345598360095" )
    private static final long serialVersionUID = 1L;

    private GameContainer container;
    private GameItem item;
    private int quantity;

    /**
     * This constructor will build and set up the condition.
     * 
     * @param name The GameComponent name to be assigned to this condition. 
     * @param theContainer The GameContainer object to be checked.
     * @param theItem The gameItem object to check for.
     * @param theQuantity The quantity of the GameItem to check for.
     */
    public ContainerHasItems( String name, GameContainer theContainer, GameItem theItem, int theQuantity )
    {
        super( name );
        this.container = theContainer;
        this.item = theItem;
        this.quantity = theQuantity;
    }

    /**
     * Checks if the GameContainer has the given quantity of the GameItem.
     * 
     * @return True if there is the quantity or more of the GameItem in the GameContainer, false otherwise.
     */
    public boolean checkCondition()
    {
        if ( this.container.hasItem( this.item, this.quantity ) )
        {
            return true;
        }
        return false;
    }
}
