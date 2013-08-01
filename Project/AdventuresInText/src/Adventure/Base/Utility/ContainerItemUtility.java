package Adventure.Base.Utility;

import Adventure.*;

import Adventure.API.*;
import Adventure.API.Utility.*;

import java.util.*;

/**
 */
public class ContainerItemUtility
    implements GameContainerItemUtility
{
    @SuppressWarnings( "compatibility:4222409569748532993" )
    private static final long serialVersionUID = 1L;

    /**
     */
    protected HashMap<GameItem, Integer> itemList;

    /**
     */
    protected GameContainer container;

    /**
     */
    protected double maxWeight;

    /**
     * @param container
     */
    public ContainerItemUtility( GameContainer container )
    {
        super();
        this.container = container;
        this.itemList = new HashMap<GameItem, Integer>();
        this.maxWeight = Double.MAX_VALUE;
    }

    /**
     * @param container
     * @param maxWeight
     */
    public ContainerItemUtility( GameContainer container, double maxWeight )
    {
        super();
        this.container = container;
        this.itemList = new HashMap<GameItem, Integer>();
        this.maxWeight = maxWeight;
    }

    /**
     * @param newItem
     * @param container
     * @return
     */
    public boolean addItem( GameItem newItem, GameContainer container )
    {
        return this.addItem( newItem, 1, container );
    }

    /**
     * @param itemName
     * @param contaierName
     * @return
     */
    public boolean addItem( String itemName, String contaierName )
    {
        GameComponent container = Engine.getComponent( contaierName );
        for ( GameComponent component : Engine.componentList() )
        {
            if ( component instanceof GameItem && component.getName().equalsIgnoreCase( itemName ) )
            {
                return this.addItem( ( GameItem ) component, 1, ( GameContainer ) container );
            }
        }
        return false;
    }

    /**
     * @param newItem
     * @param quantity
     * @param container
     * @return
     */
    public boolean addItem( GameItem newItem, int quantity, GameContainer container )
    {
        String currentContainerName = "";
        if ( container != null )
        {
            currentContainerName = ( ( GameObject ) container ).getName();
        }

        double currentItemWeight = 0.0;
        double currentTotalWeight = 0.0;
        int currentQuantity = 0;

        double weightOfNewItems = newItem.getItemWeight() * quantity;

        if ( this.itemList.containsKey( newItem ) )
        {
            currentQuantity = this.itemList.get( newItem );
            currentItemWeight = newItem.getItemWeight() * currentQuantity;
        }
        else
        {
            currentQuantity = 0;
            currentItemWeight = 0.0;
        }

        // Gets the current total weight in this container
        for ( GameItem item : this.itemList() )
        {
            currentTotalWeight += item.getItemWeight() * this.itemList.get( item );
        }

        if ( currentTotalWeight + weightOfNewItems <= this.getMaxWeight() )
        {
            if ( newItem.hasContainer( currentContainerName ) && container.hasItem( newItem, quantity) )
            {
                if ( newItem.getContainer( currentContainerName ).decreaseItemQuantity( newItem, quantity ) &&
                     this.increaseItemQuantity( newItem, quantity ) )
                {
                    newItem.addContainer( this.container );
                    return true;
                }
            }
            else
            {
                if ( this.increaseItemQuantity( newItem, quantity ) )
                {
                    newItem.addContainer( this.container );
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @param itemName
     * @param quantity
     * @param contaierName
     * @return
     */
    public boolean addItem( String itemName, int quantity, String contaierName )
    {
        GameComponent container = Engine.getComponent( contaierName );
        if ( container instanceof GameContainer )
        {
            for ( GameComponent component : Engine.componentList() )
            {
                if ( component instanceof GameItem && component.getName().equalsIgnoreCase( itemName ) )
                {
                    return this.addItem( ( GameItem ) component, quantity, ( GameContainer ) component );
                }
            }
        }
        return false;
    }

    /**
     * @return
     */
    public double containedItemWeight()
    {
        double containedWeight = 0.0;
        for ( GameItem item : this.itemList.keySet() )
        {
            containedWeight += item.getItemWeight() * this.itemList.get( item );
            if ( item instanceof GameContainer )
            {
                GameContainer container = ( GameContainer ) item;
                containedWeight += container.containedItemWeight();
            }
        }
        return containedWeight;
    }

    /**
     * @param item
     * @param decreaseByAmount
     * @return
     */
    public boolean decreaseItemQuantity( GameItem item, int decreaseByAmount )
    {
        if ( this.itemList.containsKey( item ) )
        {
            int quantityAfterDecrease = itemList.get( item ) - decreaseByAmount;
            if ( quantityAfterDecrease < 1 )
            {
                itemList.remove( item );
                item.removeContainer( this.container );
                return true;
            }
            else
            {
                itemList.put( item, quantityAfterDecrease );
                return true;
            }
        }
        return false;
    }

    /**
     * @param itemName
     * @param decreaseByAmount
     * @return
     */
    public boolean decreaseItemQuantity( String itemName, int decreaseByAmount )
    {
        for ( GameItem item : this.itemList() )
        {
            if ( item.getName().equalsIgnoreCase( itemName ) )
            {
                return this.decreaseItemQuantity( item, decreaseByAmount );
            }
        }
        return false;
    }

    /**
     * @param itemName
     * @return
     */
    public GameItem getItem( String itemName )
    {
        for ( GameItem item : this.itemList() )
        {
            if ( item.getName().equalsIgnoreCase( itemName ) )
            {
                return item;
            }
        }
        return null;
    }

    /**
     * @param itemName
     * @return
     */
    public int getItemQuantity( String itemName )
    {
        for ( GameItem item : this.itemList() )
        {
            if ( item.getName().equalsIgnoreCase( itemName ) )
            {
                return this.getItemQuantity( item );
            }
        }
        return 0;
    }

    /**
     * @param item
     * @return
     */
    public int getItemQuantity( GameItem item )
    {
        if ( this.itemList.containsKey( item ) )
        {
            return this.itemList.get( item );
        }
        return 0;
    }

    /**
     * @return
     */
    public double getMaxWeight()
    {
        return this.maxWeight;
    }

    /**
     * @param item
     * @return
     */
    public boolean hasItem( GameItem item )
    {
        return this.hasItem( item.getName(), 1, false );
    }

    /**
     * @param item
     * @param quantity
     * @return
     */
    public boolean hasItem( GameItem item, int quantity )
    {
        return this.hasItem( item.getName(), quantity, false );
    }

    /**
     * @param item
     * @param includeHidden
     * @return
     */
    public boolean hasItem( GameItem item, boolean includeHidden )
    {
        return this.hasItem( item.getName(), 1, includeHidden );
    }

    /**
     * @param item
     * @param quantity
     * @param includeHidden
     * @return
     */
    public boolean hasItem( GameItem item, int quantity, boolean includeHidden )
    {
        return this.hasItem( item.getName(), quantity, includeHidden );
    }

    /**
     * @param itemName
     * @return
     */
    public boolean hasItem( String itemName )
    {
        return this.hasItem( itemName, false );
    }

    /**
     * @param itemName
     * @param quantity
     * @return
     */
    public boolean hasItem( String itemName, int quantity )
    {
        return this.hasItem( itemName, quantity, false );
    }

    /**
     * @param itemName
     * @param includeHidden
     * @return
     */
    public boolean hasItem( String itemName, boolean includeHidden )
    {
        return this.hasItem( itemName, 1, includeHidden );
    }

    /**
     * @param itemName
     * @param quantity
     * @param includeHidden
     * @return
     */
    public boolean hasItem( String itemName, int quantity, boolean includeHidden )
    {
        for ( GameItem item : this.itemList() )
        {
            if ( item.getName().equalsIgnoreCase( itemName ) )
            {
                if ( this.itemList.get( item ) >= quantity && ( includeHidden || !item.hasStatus( "hidden" ) ) )
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @return
     */
    public ArrayList<GameItem> itemList()
    {
        ArrayList<GameItem> items = new ArrayList<GameItem>();
        items.addAll( this.itemList.keySet() );
        return items;
    }

    /**
     * @param item
     * @param increaseByAmount
     * @return
     */
    public boolean increaseItemQuantity( GameItem item, int increaseByAmount )
    {
        double weightOfNewItems = item.getItemWeight() * increaseByAmount;

        if ( this.containedItemWeight() + weightOfNewItems <= this.getMaxWeight() )
        {
            int quantity = 0;
            if ( this.itemList.containsKey( item ) )
            {
                quantity = this.itemList.get( item ) + increaseByAmount;
                this.itemList.put( item, quantity );
            }
            else
            {
                this.itemList.put( item, increaseByAmount );
            }
            item.addContainer( this.container );
            return true;
        }
        return false;
    }

    /**
     * @param itemName
     * @param increaseByAmount
     * @return
     */
    public boolean increaseItemQuantity( String itemName, int increaseByAmount )
    {
        for ( GameComponent component : Engine.componentList() )
        {
            if ( component instanceof GameItem && component.getName().equalsIgnoreCase( itemName ) )
            {
                return this.increaseItemQuantity( ( GameItem ) component, increaseByAmount );
            }
        }
        return false;
    }

    /**
     * @return
     */
    public String listItems()
    {
        String output = "";
        if ( this.itemList.size() > 0 )
        {
            output = String.format( "%-20s %-6s %-3s\n", "Item Name:", "Qty:", "Wt:" );
            for ( GameItem item : this.itemList() )
            {
                if ( !item.isHidden() )
                {
                    output +=
                            String.format( "%-20s %-6d %.1f\n", item.getName(), this.itemList.get( item ), item.getItemWeight() );
                }
            }
        }

        return output;
    }

    /**
     * @param item
     * @return
     */
    public boolean removeSingleItem( GameItem item )
    {
        return this.removeItemQuantity( item, 1 );
    }

    /**
     * @param itemName
     * @return
     */
    public boolean removeSingleItem( String itemName )
    {
        for ( GameItem item : this.itemList() )
        {
            if ( item.getName().equalsIgnoreCase( itemName ) )
            {
                return this.removeItemQuantity( item, 1 );
            }
        }
        return false;
    }

    /**
     * @param item
     * @param quantityToRemove
     * @return
     */
    public boolean removeItemQuantity( GameItem item, int quantityToRemove )
    {
        if ( this.itemList.containsKey( item ) )
        {
            int currentQuantity = this.itemList.get( item );

            if ( quantityToRemove < currentQuantity )
            {
                this.itemList.put( item, currentQuantity - quantityToRemove );
                return true;
            }
            else if ( quantityToRemove == currentQuantity )
            {
                item.removeContainer( this.container );
                this.itemList.remove( item );
                return true;
            }
        }
        return false;
    }

    /**
     * @param itemName
     * @param quantityToRemove
     * @return
     */
    public boolean removeItemQuantity( String itemName, int quantityToRemove )
    {
        for ( GameItem item : this.itemList() )
        {
            if ( item.getName().equalsIgnoreCase( itemName ) )
            {
                return this.removeItemQuantity( item, quantityToRemove );
            }
        }
        return false;
    }

    /**
     * @param container
     */
    public void setContainerComponent( GameContainer container )
    {
        this.container = container;
    }

    /**
     * @param containerName
     */
    public void setContainerComponent( String containerName )
    {
        for ( GameComponent component : Engine.componentList() )
        {
            if ( component instanceof GameContainer && component.getName().equalsIgnoreCase( containerName ) )
            {
                setContainerComponent( ( GameContainer ) component );
            }
        }
    }

    /**
     * @param newMaxWeight
     */
    public void setMaxWeight( double newMaxWeight )
    {
        this.maxWeight = newMaxWeight;
    }
}
