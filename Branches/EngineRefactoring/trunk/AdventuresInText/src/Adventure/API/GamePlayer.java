package Adventure.API;

/**
 * This interface represents a character that the player can control. Though there can only be a single GamePlayer
 * that has been marked as the current player, it is possible for there to be multiple gamePlayer objects available
 * for control that can be switched between.
 */
public abstract interface GamePlayer
    extends GamePawn, GameContainer
{
    /**
     * This method is used to set the current location of this pawn to the given GameLocation object. It includes'
     * an option to also add the description for the given location to the output.
     * 
     * @param newLocation The new GameLocation object to set this player in.
     * @param describeNewLocation Whether or not to add a description of the new location to the output.
     */
    public abstract void setLocation( GameLocation newLocation, boolean describeNewLocation );

    /**
     * This method is used to set the current location of this pawn to the location with the given name. It includes'
     * an option to also add the description for the given location to the output.
     * 
     * @param locationName The name of the new GameLocation object to set this pawn in.
     * @param describeNewLocation Whether or not to add a description of the new location to the output.
     */
    public abstract void setLocation( String locationName, boolean describeNewLocation );
}
