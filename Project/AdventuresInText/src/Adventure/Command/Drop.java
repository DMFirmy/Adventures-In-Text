package Adventure.Command;

import Adventure.*;

import Adventure.API.*;

import Adventure.Base.*;

/**
 * This command is used to drop items from your inventory into your location.
 */
public class Drop
    extends BaseAction
{
    @SuppressWarnings( "compatibility:-3793651441658477881" )
    private static final long serialVersionUID = 1L;

    /**
     * This constructor enables the Drop command.
     */
    public Drop()
    {
        super( "Drop", "Remove an item from your inventory and place it in your current location.", "d" );
    }

    /**
     * When run, this method will remove items from the players inventory and place them in their location.
     * 
     * @param operation The current Operation object that is being processed.
     */
    public void run( Operation operation )
    {
        GameItem item = null;
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

        GamePlayer player = Engine.currentPlayer();
        if ( item != null )
        {
            if ( quantity == 0 )
            {
                IO.addLine( "You can not drop 0 " + item.getName() + "." );
                return;
            }

            if ( !player.hasItem( item, quantity ) )
            {
                if ( player.hasItem( item ) )
                {
                    IO.addLine( "You only have " + player.getItemQuantity( item ) + " " + item.getName() +
                                ", so you cannot drop " + quantity + " of them here." );
                }
                else
                {
                    IO.addLine( "You do not have any " + item.getName() + " to drop." );
                }
            }

            if ( player.hasItem( item, quantity ) )
            {
                player.getLocation().addItem( item, quantity, player );
            }
        }
    }
}
