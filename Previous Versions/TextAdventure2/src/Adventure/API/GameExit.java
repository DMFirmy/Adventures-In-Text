package Adventure.API;

/**
 * This interface is used to represent an exit between one GameLocation and another. It allows for the player
 * to move in a specified direction while in the location that contains this exit and to move to the location
 * that the exit leads to.
 */
public abstract interface GameExit
    extends GameObject, GameHideable
{
    /**
     * This method is used to get a reference to the GameDirection object that the player must use to move
     * to enter the connected location.
     * 
     * @return The GameDirection object associated with this exit.
     */
    public abstract GameDirection getExitDirection();

    /**
     * This method is used to get a reference to the GameLocation that this exit leads to when you move in 
     * the speciffied direction.
     * 
     * @return The gameLocation object that this exit leads to.
     */
    public abstract GameLocation getExitLocation();

    /**
     * This method is used to get a reference to the GameLocation that contains this exit.
     * 
     * @return the GameLocation object that contains this exit.
     */
    public abstract GameLocation getLocation();

    /**
     * This method is used to change the direction the player needs to move to get to the connected location.
     * 
     * @param direction The new GameDirection object that the player will need to move in to reach the connected location.
     */
    public abstract void setExitDirection( GameDirection direction );

    /**
     * This method is used to change the direction the player needs to move to get to the connected location.
     * 
     * @param directionName the name of the new direction that the player move to reach the connected location.
     */
    public abstract void setExitDirection( String directionName );

    /**
     * This method is used to change the location that taking this exit leads to.
     * 
     * @param location The new location that taking this exit should lead to.
     */
    public abstract void setExitLocation( GameLocation location );

    /**
     * This method is used to change the location that taking this exit leads to.
     * 
     * @param locationName The name of the new location that taking this exit should lead to.
     */
    public abstract void setExitLocation( String locationName );

    /**
     * This method is used to mark the location that contains this exit.
     * 
     * @param location The new location that will contain this exit.
     */
    public abstract void setLocation( GameLocation location );

    /**
     * This method is used to mark the location that contains this exit.
     * 
     * @param locationName The name of the new location that will contain this exit.
     */
    public abstract void setLocation( String locationName );
}
