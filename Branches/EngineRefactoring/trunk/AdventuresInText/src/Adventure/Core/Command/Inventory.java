package Adventure.Core.Command;

import Adventure.*;

import Adventure.Base.*;

/**
 * This command is used to display the contents of your inventory and the combined weight of your items.
 */
public class Inventory
    extends BaseAction
{
    @SuppressWarnings( "compatibility:-4506517784953650271" )
    private static final long serialVersionUID = 1L;

    /**
     * This constructor enables the Inventory command.
     */
    public Inventory()
    {
        super( "Inventory", "Display a listing of the items you are carryung.", "v" );
    }

    /**
     * when run, this method displays a listing of the items you are carrying, the quantity iof each, and the total combined
     * weight of your items.
     * 
     * @param operation The current Operation object that is being processed.
     */
    public void run( Operation operation )
    {
        IO.addLine( Engine.getCurrentPlayer().listItems() + "\n" );
    }
}
