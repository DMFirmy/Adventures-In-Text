package Adventure;

import Adventure.API.*;

import Adventure.Command.*;

import java.io.*;

import java.util.*;

/**
 * This class represents is the core of the entire application. It handles all of the components for the game, loading and
 * saving game states, gathering input and output, and more. Pretty much everything that happens within the entire application
 * first gets checked through the engine.
 */
public class Engine
    implements Serializable
{
    @SuppressWarnings( "compatibility:7688155474666955850" )
    private static final long serialVersionUID = 1L;

    private ArrayList<GameComponent> activeComponents;

    private GamePlayer activePlayer;

    private static boolean quit;

    private static ArrayList<GameCommand> commands;

    private static ArrayList<GameEvent> events;

    private static ArrayList<GameComponent> masterComponentList;

    private static GamePlayer currentPlayer;

    private static Thread mainThread;

    static
    {
        // Though unused in the current GUI, we get a handle on the main thread in case future GUI's require it.
        mainThread = Thread.currentThread();
        
        // Here we initialize our static ArrayList objects
        commands = new ArrayList<GameCommand>();
        events = new ArrayList<GameEvent>();
        masterComponentList = new ArrayList<GameComponent>();
        
        // This call to initializeEngine() is used to load in the core commands and to set the game state to its default.
        initializeEngine();
    }
    
    private Engine()
    {
        this.activeComponents = componentList();
        this.activePlayer = currentPlayer();
    }

    /**
     * This method is used to get an input string from the current IOUtility and passes it to the Processor to be processed.
     */
    public static void getInput()
    {
        Processor.processCommands( IO.getInput() );
    }

    /**
     * This method is used by all of the various components of the game to get a reference to another component with a given
     * string name.
     * 
     * @param componentName This is the name of the component you want a reference to.
     * @return The component that has the given name, or null if none is found.
     */
    public static GameComponent getComponent( String componentName )
    {
        for ( GameComponent component : componentList() )
        {
            if ( component.getName().equalsIgnoreCase( componentName ) )
            {
                return component;
            }
        }
        return null;
    }

    /**
     * This method calls the display of the current IOUtility, giving it a default prompt to display to the player.
     */
    public static void showOutput()
    {
        IO.display( "What would you like to do?\n" );
    }

    /**
     * This method shows the game title screen.
     */
    public static void showTitleScreen()
    {
        IO.addLine( "*** Welcome to Adventures in Text ***" );
        IO.addLine( "Type 'new' to start a new game or 'load' to load a previously saved game." );
        IO.addLine( "You can also type 'quit' to end the game or type 'help' for the help text." );
        IO.display();
        getInput();
    }

    /**
     * This method is used to set the state of the game back to its default state. From this state, only the most important
     * system commands will be reinitialized and no game level will be loaded yet. 
     */
    public static void initializeEngine()
    {
        currentPlayer = null;
        commands.clear();
        events.clear();
        if ( !masterComponentList.isEmpty() )
        {
            masterComponentList.clear();
        }
        initializeCommands();
    }

    /**
     * This method is used to get a reference to the main application thread.
     * 
     * @return A reference to the main apoplication thread.
     */
    public static Thread mainThread()
    {
        return mainThread;
    }

    /**
     * This method will clear out the currently active command list and reload it with only the most important system commands.
     */
    private static void initializeCommands()
    {
        if ( !commands.isEmpty() )
        {
            commands.clear();
        }
        commands.add( new Help() );
        commands.add( new Quit() );
        commands.add( new New() );
        commands.add( new Load() );
    }

    /**
     * This method is used to load in a basic set of commands that level designers can bedin to utilize in thier creations.
     */
    public static void initializeDefaultCommands()
    {
        initializeDefaultDirections();

        addCommand( new Save() );
        addCommand( new Move() );
        addCommand( new Get() );
        addCommand( new Drop() );
        addCommand( new Inventory() );
        addCommand( new Look() );
        addCommand( new Speak() );
        addCommand( new WhereAmI() );
    }

    /**
     * This method is used to load in a listing of default directions including north, south, west, and east. These directions
     * can be used by level designers to connect locations and create exits.
     */
    public static void initializeDefaultDirections()
    {
        addCommand( new Direction( "West", "The direction west.", "w" ) );
        addCommand( new Direction( "East", "The direction east.", "e" ) );
        addCommand( new Direction( "North", "The direction north.", "n" ) );
        addCommand( new Direction( "South", "The direction south.", "s" ) );
    }

    /**
     * This method is used to load in several additional directions for level designers to use in their creations.
     */
    public static void initializeExtraDirections()
    {
        addCommand( new Direction( "NorthWest", "The direction north-west.", "nw" ) );
        addCommand( new Direction( "NorthEast", "The direction north-east.", "ne" ) );
        addCommand( new Direction( "SouthWest", "The direction north-west.", "sw" ) );
        addCommand( new Direction( "SouthEast", "The direction north-east.", "se" ) );
        addCommand( new Direction( "Up", "The direction up.", "u" ) );
        addCommand( new Direction( "Down", "The direction down.", "dn" ) );
        addCommand( new Direction( "Enter", "This is used to move inside of something.", "in" ) );
        addCommand( new Direction( "Exit", "This is used to move outside of something.", "out" ) );
    }

    /**
     * This method will add a GameCommand to the list of available commands for the game.
     * 
     * @param newCommand The new command to be added to the command list.
     */
    public static void addCommand( GameCommand newCommand )
    {
        boolean add = true;
        for ( GameCommand command : commands )
        {
            if ( command.getName().equalsIgnoreCase( newCommand.getName() ) ||
                 command.getHotkey().equalsIgnoreCase( newCommand.getHotkey() ) )
            {
                add = false;
            }
            if ( command instanceof GameAction )
            {
                GameAction action = ( GameAction ) command;
                for ( String alias : action.aliasList() )
                {
                    if ( alias.equalsIgnoreCase( newCommand.getName() ) ||
                         alias.equalsIgnoreCase( newCommand.getHotkey() ) )
                    {
                        add = false;
                        break;
                    }
                }
            }
        }

        if ( add )
        {
            commands.add( newCommand );
        }
    }

    /**
     * This method will allow you to add an entire ArrayList of GameCommand objects to the command list at once.
     * 
     * @param newCommands A list of GameCommand objects to add to the command list.
     */
    public static void addCommands( ArrayList<GameCommand> newCommands )
    {
        for ( GameCommand newCommand : newCommands )
        {
            addCommand( newCommand );
        }
    }

    /**
     * This method will add an event to be run every turn to the listing of game events.
     * 
     * @param newEvent The event to be added to the events list.
     */
    public static void addEvent( GameEvent newEvent )
    {
        boolean add = true;
        for ( GameEvent event : events )
        {
            if ( event.getName().equalsIgnoreCase( newEvent.getName() ) )
            {
                add = false;
            }
        }

        if ( add )
        {
            events.add( newEvent );
        }
    }

    /**
     * This method will allow you to add an entire ArrayList of GameEvent objects to the events list at once.
     *
     * @param newEvents A list of GameEvent objects to add to the events list.
     */
    public static void addEvents( ArrayList<GameEvent> newEvents )
    {
        for ( GameEvent newEvent : newEvents )
        {
            addEvent( newEvent );
        }
    }

    /**
     * This method is used to end the game.
     * 
     * @param endingText This is the final output text to be displayed to the player before quitting.
     */
    public static void endGame( String endingText )
    {
        quit();
        IO.addLine( endingText );

        if ( currentPlayer == null )
        {
            IO.add( "You may now close this window..." );
            IO.display();
            while ( true )
            {
                IO.getInput();
            }
        }
        else
        {
            IO.addLine( "Press Enter to continue..." );
            IO.display();
            IO.getInput();

            initializeEngine();
        }

    }

    /**
     * This method is used to determine if the game has been marked as quit.
     *
     * @return True ff the game has been marked as quit, false otherwise.
     */
    public static boolean gameOver()
    {
        if ( quit )
        {
            return true;
        }
        return false;
    }

    /**
     * This method will get a copy of the list containing all of the GameEvents that are currently in place.
     * 
     * @return A list of GameEvent objects
     */
    protected static ArrayList<GameEvent> eventList()
    {
        ArrayList<GameEvent> eventList = new ArrayList<GameEvent>();
        eventList.addAll( events );
        return eventList;
    }

    /**
     * This method will get a copy of the list containing all of the GameCommand objects that are currently in place.
     *
     * @return A list of GameCommand objects
     */
    protected static ArrayList<GameCommand> commandList()
    {
        ArrayList<GameCommand> commandList = new ArrayList<GameCommand>();
        commandList.addAll( commands );
        return commands;
    }

    /**
     * This method is used to mark the game as quit.
     */
    public static final void quit()
    {
        quit = true;
    }

    /**
     * This method is used to remove a component from the master list.
     * 
     * @param component The GameComponennt object to be removed.
     */
    public static void removeComponent( GameComponent component )
    {
        masterComponentList.remove( component );
    }

    /**
     * This method is used to remove a component from the master list.
     * 
     * @param componentName The String name of the GameComponent object to remove.
     */
    public static void removeComponent( String componentName )
    {
        for ( GameComponent component : componentList() )
        {
            if ( component.getName().equalsIgnoreCase( componentName ) )
            {
                masterComponentList.remove( component );
            }
        }
    }

    /**
     * This method is used to add a GameComponent object to the master list.
     * 
     * @param newComponent The GameComponent object to be added.
     */
    public static void addComponent( GameComponent newComponent )
    {
        boolean add = true;
        for ( GameComponent component : masterComponentList )
        {
            if ( component.getName().equalsIgnoreCase( newComponent.getName() ) )
            {
                add = false;
            }
        }

        if ( add )
        {
            masterComponentList.add( newComponent );
        }
    }

    /**
     * This method is used to get a copy of the master component list.
     * 
     * @return A list of GameComponent objects that are currently available.
     */
    public static ArrayList<GameComponent> componentList()
    {
        ArrayList<GameComponent> componentList = new ArrayList<GameComponent>();
        componentList.addAll( masterComponentList );
        return componentList;
    }

    /**
     * This method will check if a GameComponent object exists in the master list with a given name.
     * 
     * @param componentName The String name of the GameComponent to check for
     * @return True if a component with the given name is found, false otherwise.
     */
    public static boolean hasComponent( String componentName )
    {
        for ( GameComponent component : componentList() )
        {
            if ( component.getName().equalsIgnoreCase( componentName ) )
            {
                return true;
            }
        }
        return false;
    }

    /**
     * This method will take a GameMap object and load it into the engine so that the map can be played.
     * 
     * @param newMap The GameMap object to be set up.
     */
    public static void setupLevel( GameMap newMap )
    {
        // First we need to load in any commands and directions for the map
        newMap.buildMapCommands();
        reloadCommands();
        
        // Next we need to set up the locations that exist within the map and all of their subcomponents.
        newMap.buildMapLocations();
        
        // Now we need to designate who the current player is.
        switchPlayer( newMap.getCurrentPlayer() );
        
        // Here we initialize all of the events and conditions for the map.
        newMap.buildMapMechanics();
        reloadEvents();
        
        // After the game loads we run the postProcessEvents method to initialize kick of any initial events.
        Processor.enginePostProcessing = true;
        Processor.postProcessEvents( null );
        
        // Finally, we describe the player's location to them and the game can begin.
        currentPlayer.getLocation().describe();
    }

    /**
     * This method is used to get a reference to the current player.
     * 
     * @return A reference to the current player.
     */
    public static GamePlayer currentPlayer()
    {
        return currentPlayer;
    }

    /**
     * This method is used to assign a new GamePlayer object as the current player.
     * 
     * @param newCurrentPlayer The GamePlayer object to mark as the active player.
     */
    public static void switchPlayer( GamePlayer newCurrentPlayer )
    {
        currentPlayer = newCurrentPlayer;
    }

    /**
     * This method is used to assign the GamePlayer object with the given name as the current player.
     * 
     * @param newCurrentPlayerName The String name of the GamePlayer object to mark as the active player.
     */
    public static void switchPlayer( String newCurrentPlayerName )
    {
        GameComponent component = getComponent( newCurrentPlayerName );
        if ( component instanceof GamePlayer )
        {
            currentPlayer = ( GamePlayer ) component;
        }
    }

    /**
     * This is a helper method for level builders to get a single GameDirection from the master list by providing the name for it.
     * 
     * @param directionName The String name of the GameDirection you want to retrieve.
     * @return A reference to the GameDirection object with the given name.
     */
    public static GameDirection direction( String directionName )
    {
        for ( GameComponent component : componentList() )
        {
            if ( component instanceof GameDirection && component.getName().equalsIgnoreCase( directionName ) )
            {
                return ( GameDirection ) component;
            }
        }
        return null;
    }

    /**
     * This method will save the current state of the game to a file called "saveGame.data".
     */
    public static void saveGameState()
    {
        Engine gameState = new Engine();
        FileOutputStream saveGameFile;
        ObjectOutputStream saveGameData;

        try
        {
            // Write to disk with FileOutputStream
            saveGameFile = new FileOutputStream( "saveGame.data" );
            // Write object with ObjectOutputStream
            saveGameData = new ObjectOutputStream( saveGameFile );
            // Write object out to disk
            saveGameData.writeObject( gameState );

        }
        catch ( Exception e )
        {
            IO.addLine("There was an error saving the game state. Your game has not been saved.\n");
            return;
        }
        IO.addLine( "The current game state has been saved.\n" );
    }

    /**
     * This method loads in the content of the file "saveGame.data" and restores it as the current game state.
     */
    public static void loadGameState()
    {
        FileInputStream saveGameFile = null;
        ObjectInputStream saveGameData = null;
        Object engineObject = null;
        Engine gameState = null;

        try
        {
            // Read from disk using FileInputStream
            saveGameFile = new FileInputStream( "saveGame.data" );

            // Read object using ObjectInputStream
            saveGameData = new ObjectInputStream( saveGameFile );

            // Read an object
            engineObject = saveGameData.readObject();

            if ( engineObject instanceof Engine )
            {
                // Cast it as an Engine
                gameState = ( Engine ) engineObject;

                // Reload the data
                gameState.loadActiveComponents();

            }
        }
        catch ( Exception e )
        {
            IO.addLine( "There was an error loading the game state. Your game has not been loaded.\n" );
            return;
        }
        IO.addLine( "The game state has been reloaded from the file on disk.\n" );
    }

    /**
     * This instance method is used durring loading to restore the static state of the Engine back to the state stored
     * within the Engine object instance.
     */
    private void loadActiveComponents()
    {
        currentPlayer = null;
        masterComponentList.clear();

        masterComponentList.addAll( this.activeComponents );

        reloadCommands();
        reloadEvents();

        switchPlayer( this.activePlayer );
        currentPlayer().getLocation().describe();
    }

    /**
     * This method will go through the master component list and search for events, adding any that it finds.
     */
    private static void reloadEvents()
    {
        events.clear();
        for ( GameComponent component : componentList() )
        {
            if ( component instanceof GameEvent && !events.contains( component ) )
            {
                events.add( ( GameEvent ) component );
            }
        }
    }

    /**
     * This method will go through the master component list and search for commands, adding any that it finds.
     */
    private static void reloadCommands()
    {
        commands.clear();
        for ( GameComponent component : componentList() )
        {
            if ( component instanceof GameCommand && !commands.contains( component ) )
            {
                commands.add( ( GameCommand ) component );
            }
        }
    }
}
