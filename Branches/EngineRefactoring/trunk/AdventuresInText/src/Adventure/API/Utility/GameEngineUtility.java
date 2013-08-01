package Adventure.API.Utility;

public interface GameEngineUtility
    extends GameUtility
{
    /**
     * Even though the GameIOUtility class is not a GameComponent in itself, it still stores an internal unique name used
     * to identify it so that various GUI's can be loaded in as command line args.
     *
     * @return The string name of this GameIOUtility object.
     */
    public abstract String getName();
}
