package Adventure.Core.Command;

import Adventure.*;

import Adventure.API.*;

import Adventure.Base.*;

/**
 * This command is used to take items from a container and to place them into your inventory.
 */
public class Get
    extends BaseAction
{
    @SuppressWarnings( "compatibility:-3179530366639613834" )
    private static final long serialVersionUID = 1L;

    /**
     * This constructor enables the Get command.
     */
    public Get()
    {
        super( "Get", "Pick up an item from the game world.", "g" );
        this.addAlias( "Take" );
        this.addAlias( "Pickup" );
    }

    /**
     * When run, this method will remove items from the players current location and place them in their inventory .
     * 
     * @param operation The current Operation object that is being processed.
     */
    public void run( Operation operation )
    {
        GamePlayer player = Engine.getCurrentPlayer();
        GameItem item = null;
        GameContainer container = null;
        int quantity = 0;

        if ( operation.getDirectObject() != null && operation.getDirectObject() instanceof GameItem )
        {
            item = ( GameItem ) operation.getDirectObject();
        }

        if ( operation.getQuantityString() == null || operation.getQuantityString().equals( "" ) )
        {
            quantity = 1;
        }
        else
        {
            quantity = operation.getQuantity();
        }

        if ( operation.getIndirectObject() != null && operation.getIndirectObject() instanceof GameContainer )
        {
            container = ( GameContainer ) operation.getIndirectObject();
        }
        else
        {
            container = player.getLocation();
        }

        if ( item != null && quantity > 0 )
        {
            if ( !container.hasItem( item, quantity ) )
            {
                IO.addLine( "There is no " + item.getName() + " here to take." );
                return;
            }
            if ( !item.hasStatus( "movable" ) )
            {
                IO.addLine( "The " + item.getName() + " cannot be moved." );
                return;
            }
            
            if ( player.containedItemWeight() + ( item.getItemWeight() * quantity ) <= player.getMaxWeight() &&
                 !item.isHidden() )
            {
                player.addItem( item, quantity, container );
                // This should remove the item from the container specified as an indirect object
                //player.getLocation().itemList().remove( item );
                item.describe();
                IO.addLine( "You are now carrying " + player.containedItemWeight() + " of " + player.getMaxWeight() );
            }
            else
            {
                IO.addLine( "It is too heavy to carry. You need to drop something first." );
            }
        }
    }
}
