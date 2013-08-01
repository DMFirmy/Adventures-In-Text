package Adventure.API;

/**
 * This interface is used as the base for any GameComponent that will actully exist within the game world,
 * including all of the characters, items, and locations, and other various entities that can be manipulated
 * within the game. GameObject objects should be created for each level within the associated GameMap's
 * buildMapLocations() method.
 */
public abstract interface GameObject
    extends GameComponent
{
    /**
     * This method will add a description for this GameObject component to the output.
     */
    public abstract void describe();
}
