package Adventure.Base;

import Adventure.*;

import Adventure.API.*;
import Adventure.API.Utility.*;

import Adventure.Base.Utility.*;

/**
 * This is the base implementation of the GamePawn interface.
 */
public abstract class BasePawn
    extends BaseObject
    implements GamePawn
{
    @SuppressWarnings( "compatibility:6113902952688265116" )
    private static final long serialVersionUID = 1L;

    /**
     * This field holds the location that holds this pawn.
     */
    protected GameLocation location;

    /**
     * This field holds this pawn's prior location.
     */
    protected GameLocation previousLocation;

    /**
     * This field is used to hold the GameObjectHideUtility utilized by this component.
     */
    protected GameObjectHideUtility hideUtility;

    /**
     * This constructor is used to initialize and set up this pawn.
     *
     * @param name The string name of this pawn.
     */
    public BasePawn( String name )
    {
        super( name );
        this.hideUtility = new ObjectHideUtility( this );
    }

    /**
     * This constructor is used to initialize and set up this pawn.
     *
     * @param name The string name of this action.
     * @param description The text that will be shown as the description for this pawn.
     */
    public BasePawn( String name, String description )
    {
        super( name, description );
        this.hideUtility = new ObjectHideUtility( this );
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
     * This method is used to get a reference to the GameLocation tha this pawn is currently located in.
     *
     * @return The GameLocation object that currently holds this pawn.
     */
    public GameLocation getLocation()
    {
        return this.location;
    }

    /**
     * This method is used to get a reference to the GameLocation that this pawn was in prior to their current
     * location.
     *
     * @return The GameLocation object that this pawn was in last.
     */
    public GameLocation getPreviousLocation()
    {
        return this.previousLocation;
    }

    /**
     * This method is used to set the current location of this pawn to the given GameLocation object.
     *
     * @param newLocation The new GameLocation object to set this pawn in.
     */
    public void setLocation( GameLocation newLocation )
    {
        if ( this.hasStatus( "moves" ) )
        {
            this.incrementStatus( "moves" );
        }
        this.previousLocation = this.location;
        if ( this.previousLocation != null )
        {
            this.previousLocation.removePawn( this );
        }
        this.location = newLocation;
        this.location.addPawn( this );
    }

    /**
     * This method is used to set the current location of this pawn to the location with the given name.
     *
     * @param locationName The name of the new GameLocation object to set this pawn in.
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
