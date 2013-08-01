package Adventure.Base;

import Adventure.*;

import Adventure.API.*;
import Adventure.API.Utility.*;

import Adventure.Base.Utility.*;

/**
 * This is the base implementation of the GameExit interface.
 */
public abstract class BaseExit
    extends BaseObject
    implements GameExit
{
    @SuppressWarnings( "compatibility:5881344048409122855" )
    private static final long serialVersionUID = 1L;

    /**
     * This field contains the direction to be traveled to use this exit.
     */
    protected GameDirection direction;

    /**
     * This field contains the location that this exit leads to.
     */
    protected GameLocation exitTo;

    /**
     * This field contains the location that this exit is in.
     */
    protected GameLocation location;

    /**
     * This field contains the GameObjectHideUtility that this utilizes.
     */
    protected GameObjectHideUtility hideUtility;

    /**
     * This constructor is used to initialize and set up this event.
     *
     * @param direction The direction to be traveled to use this exit.
     * @param exitToLocation The location that this exit leads to.
     */
    public BaseExit( GameDirection direction, GameLocation exitToLocation )
    {
        // TODO: There needs to be a better method for generating unique exit names...
        super( direction.getHotkey() + "_" + Engine.componentList().size());

        this.hideUtility = new ObjectHideUtility( this );

        this.direction = direction;
        this.exitTo = exitToLocation;
    }

    ///////////////////////Hide Utility////////////////////////////

    /**
     * This method is used to mark this GameObject component as hidden.
     */
    public void hide()
    {
        this.hideUtility.hideObject();
    }

    /**
     * This method is used to determine if this GameObject component is marked as hidden.
     *
     * @return True if this component is marked as hidden, false otherwise.
     */
    public boolean isHidden()
    {
        return this.hideUtility.isHidden();
    }

    /**
     * This method is used to unmark this component as being hidden.
     */
    public void reveal()
    {
        this.hideUtility.revealObject();
    }

    /**
     * This method is used to assign an new GameObjectHideUtility to handle the hidden status of this component.
     *
     * @param newHideUtility The new GameObjectHideUtility to set as the active utility for this component.
     */
    public void setHideUtility( GameObjectHideUtility newHideUtility )
    {
        newHideUtility.setObjectComponent( this );
        this.hideUtility = newHideUtility;
    }
    ///////////////////////Hide Utility////////////////////////////

    /**
     * This method is used to get a reference to the GameDirection object that the player must use to move
     * to enter the connected location.
     *
     * @return The GameDirection object associated with this exit.
     */
    public GameDirection getExitDirection()
    {
        return direction;
    }

    /**
     * This method is used to get a reference to the GameLocation that this exit leads to when you move in
     * the speciffied direction.
     *
     * @return The gameLocation object that this exit leads to.
     */
    public GameLocation getExitLocation()
    {
        return this.exitTo;
    }

    /**
     * This method is used to get a reference to the GameLocation that contains this exit.
     *
     * @return the GameLocation object that contains this exit.
     */
    public GameLocation getLocation()
    {
        return this.location;
    }

    /**
     * This method is used to change the direction the player needs to move to get to the connected location.
     *
     * @param direction The new GameDirection object that the player will need to move in to reach the connected location.
     */
    public void setExitDirection( GameDirection newDirection )
    {
        this.direction = newDirection;
    }

    /**
     * This method is used to change the direction the player needs to move to get to the connected location.
     *
     * @param directionName the name of the new direction that the player move to reach the connected location.
     */
    public void setExitDirection( String directionName )
    {
        for ( GameComponent component : Engine.componentList() )
        {
            if ( component instanceof GameDirection && component.getName().equalsIgnoreCase( directionName ) )
            {
                setExitDirection( ( GameDirection ) component );
                return;
            }
        }
    }

    /**
     * This method is used to change the location that taking this exit leads to.
     *
     * @param newLocation The new location that taking this exit should lead to.
     */
    public void setExitLocation( GameLocation newLocation )
    {
        this.exitTo = newLocation;
    }

    /**
     * This method is used to change the location that taking this exit leads to.
     *
     * @param locationName The name of the new location that taking this exit should lead to.
     */
    public void setExitLocation( String locationName )
    {
        for ( GameComponent component : Engine.componentList() )
        {
            if ( component instanceof GameLocation && location.getName().equalsIgnoreCase( locationName ) )
            {
                setExitLocation( ( GameLocation ) component );
                return;
            }
        }
    }

    /**
     * This method is used to mark the location that contains this exit.
     *
     * @param location The new location that will contain this exit.
     */
    public void setLocation( GameLocation newLocation )
    {
        this.location = newLocation;
    }

    /**
     * This method is used to mark the location that contains this exit.
     *
     * @param locationName The name of the new location that will contain this exit.
     */
    public void setLocation( String locationName )
    {
        for ( GameComponent component : Engine.componentList() )
        {
            if ( component instanceof GameLocation && component.getName().equalsIgnoreCase( locationName ) )
            {
                this.setLocation( ( GameLocation ) component );
                return;
            }
        }
    }
}
