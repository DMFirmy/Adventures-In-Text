package Adventure.Base;

import Adventure.*;

import Adventure.API.*;

/**
 * This is the base implementation of the GameMap interface.
 */
public abstract class BaseMap
    extends BaseObject
    implements GameMap
{
    @SuppressWarnings( "compatibility:2182583336152191575" )
    private static final long serialVersionUID = 1L;

    /**
     * This field stores a reference to the GamePlayer object that will be the active player when the map is loaded.
     */
    protected GamePlayer currentPlayer;

    /**
     * This field stores a string to be displayed as the initial output when the map is loaded.
     */
    protected String initialOutput;

    /**
     * This field holds the absolute file path to the JAR archive that containes this GameMap
     */
    protected String mapPath;

    /**
     * This constructor is used to initialize and set up this map.
     *
     * @param name The string name of this map.
     * @param description The text that will be shown as the description for this map.
     */
    public BaseMap( String name, String description )
    {
        super( name, description );
    }

    /**
     * This method is used to mark the given GamePlayer as the current player for when the map is loaded.
     *
     * @param newCurrentPlayer The GamePlayer object to be marked as the current player.
     */
    public void setCurrentPlayer( GamePlayer newCurrentPlayer )
    {
        this.currentPlayer = newCurrentPlayer;
    }

    /**
     * This method is used to retrieve a GamePlayer object from the Engine's master list with the given name, and to
     * mark that player as the current player for when the map is loaded.
     *
     * @param newCurrentPlayerName The string name of the GamePlayer object to be marked as the current player.
     */
    public void setCurrentPlayer( String newCurrentPlayerName )
    {
        for ( GameComponent component : Engine.componentList() )
        {
            if ( component instanceof GamePlayer && component.getName().equalsIgnoreCase( newCurrentPlayerName ) )
            {
                setCurrentPlayer( ( GamePlayer ) component );
                return;
            }
        }
    }

    /**
     * This method will get a reference to the GamePlayer object that has been marked as the current player.
     *
     * @return The GamePlayer object that will be marked as the current player when the GameMap is loaded.
     */
    public GamePlayer getCurrentPlayer()
    {
        return this.currentPlayer;
    }

    /**
     * This method will store the provided absolute file path for the JAR archive that contains this GameMap object.
     *
     * @param newMapPath The String absolute file path to the JAR archive that contains the current GameMap class file.
     */
    public void setMapPath( String newMapPath )
    {
        this.mapPath = newMapPath;
    }

    /**
     * This method is used to get the stored string reference to the absolute file location that this GameMap was located
     * in. This is because the Loader class will look for GameMap objects from within JAR archives, and the location of
     * the JAR gets stored for future reference. In the future I will find a more elegant means of handling this.
     *
     * @return The absolute path to the JAR archive containing this GameMap object.
     */
    public String getMapPath()
    {
        return this.mapPath;
    }
}
