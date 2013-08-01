package Adventure.Demo.Event;

import Adventure.*;

import Adventure.API.*;

import Adventure.Base.*;

/**
 * This event, when triggered, will cause the given GameContainer to remove the given quantity of GameItem objects from
 * the other given GameContainer object and add them to its own inventory.
 */
public class ItemMovedToNewContainer
    extends BaseEvent
{
    @SuppressWarnings( "compatibility:4069620037241879158" )
    private static final long serialVersionUID = 1L;

    private GameContainer newContainer;

    private GameItem item;
    
    private int quantity;

    private GameContainer oldContainer;

    /**
     * This constructor will build and set up the event.
     * 
     * @param name The GameComponent name to be assigned to this event.
     * @param theNewContainer The new GameContainer that will take the item from the old GameContainer.
     * @param theItem The GameItem object to be taken from the GameContainer.
     * @param theQuantity The quantity of the GameItem to be taken from the GameContainer.
     * @param theOldContainer The GameContainer that contains the GameItem object.
     */
    public ItemMovedToNewContainer( String name, GameContainer theNewContainer, GameItem theItem, int theQuantity, GameContainer theOldContainer )
    {
        super( name );
        this.newContainer = theNewContainer;
        this.oldContainer = theOldContainer;
        this.item = theItem;
        this.quantity = theQuantity;
    }

    /**
     * This will check the GameContainer to make sure it contains enough of the GameItem objects, and then
     * it adds them to the GamePawn while removing them from the current container.
     * 
     * @param operation Not used in this event.
     */
    public void processEvent( Operation operation )
    {
        if ( this.oldContainer.hasItem( this.item, this.quantity ) )
        {
            this.newContainer.addItem( this.item, this.quantity, this.oldContainer );
        }
    }
}
