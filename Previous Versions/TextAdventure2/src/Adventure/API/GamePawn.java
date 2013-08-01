package Adventure.API;

/**
 * This is the core interface for any creatures within the game. 
 */
public abstract interface GamePawn
    extends GameObject
{
    /**
     * This method is used to get a reference to the GameLocation tha this pawn is currently located in.
     * 
     * @return The GameLocation object that currently holds this pawn.
     */
    public abstract GameLocation getLocation();

    /**
     * This method is used to get a reference to the GameLocation that this pawn was in prior to their current
     * location.
     * 
     * @return The GameLocation object that this pawn was in last.
     */
    public abstract GameLocation getPreviousLocation();

    /**
     * This method is used to set the current location of this pawn to the given GameLocation object.
     * 
     * @param location The new GameLocation object to set this pawn in.
     */
    public abstract void setLocation( GameLocation location );

    /**
     * This method is used to set the current location of this pawn to the location with the given name.
     * 
     * @param locationName The name of the new GameLocation object to set this pawn in.
     */
    public abstract void setLocation( String locationName );
}
