package Adventure;

import Adventure.API.*;
import Adventure.API.Utility.*;

import Adventure.Core.Command.*;

import Adventure.Demo.*;

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
    /**
     * This static initializer is used to set up the Engine class so that it is ready to run the game.
     */
    static
    {
        // Though unused in the current GUI, we get a handle on the main thread in case future GUI's require it.
        mainThread = Thread.currentThread();

        // We need to assign a master component utility.
        masterComponentUtility = new EngineComponentUtility();

        // Here we initialize our static ArrayList objects
        commands = new ArrayList<GameCommand>();
        events = new ArrayList<GameEvent>();

        // This call to initializeEngine() is used to load in the core commands and to set the game state to its default.
        initializeEngine();
    }

    /*******************************************************************************************************************
     * Public Section
     ******************************************************************************************************************/

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
     * When run, this method will load a list of levels that are currently available for the player to choose, then it
     * will load the initial state for the level that is chosen.
     */
    public static void beginNewGame( String mapName )
    {
        // Here we create a variable to store any current game state information.
        Engine currentGameState = null;
        if ( gameInProgress )
        {
            // We will store the current game state in case of a problem.
            currentGameState = new Engine();

            // We will also prompt the player to save the current game, in case they forgot.
            promprForSave();
        }

        // Now we will create an empty list to hold our available maps.
        ArrayList<GameMap> maps = new ArrayList<GameMap>();
        // We also need another list variable to store whatever is returned from the Loader.
        ArrayList<GameMap> mapList = null;

        // Since our current game state is backed up, we can reinitialize the engine without worry.
        initializeEngine();

        // Now we need to scan for any external level maps with out Loader class.
        try
        {
            mapList = Loader.loadMaps();
        }
        catch ( Exception e )
        {
            // If there is some kind of error, we just add a message to output.
            IO.addLine( "There was a problem scanning for maps to load." );
        }

        // The demo map will always be available since it is part of the core package, so we add it here.
        GameMap demoMap = new DemoMap();
        // We will mark the path to our demo map's file since it never gets marked elsewhere.
        demoMap.setMapPath( Loader.getCustomContentDirectoryPath() );
        // Now we add the bemo map to our available maps list.
        maps.add( demoMap );

        // If the Loader was able to find any maps, we add them to our list as well.
        if ( mapList != null && !mapList.isEmpty() )
        {
            maps.addAll( mapList );
        }

        // Here we will initialize a variable to store our selected map.
        GameMap chosenMap = null;
        // We also initialize a variable to represent our selection.
        int choiceInt = -1;

        // If we have not been given a map name to be loaded, we need to prompt the player with a list of choices.
        if ( mapName.equals( "" ) )
        {
            // Now we will start a loop to output all of the available map choices.
            while ( choiceInt < 0 || choiceInt > maps.size() )
            {
                // First we add a prompt to output.
                IO.addLine( "Choose a Level:" );
                // Now we initialize a counter variable to keep track of which choice is which.
                int i = 0;

                // TODO: Add the ability to breal long lists into multiple pages.
                // Now we can loop through each of the maps in our list and add a choice for it to the output.
                for ( GameMap map : maps )
                {
                    // First increment our counter.
                    i++;
                    // Now add our choice to output.
                    IO.addLine( i + ": " + map.getName() );
                }
                // We also add a choice to just end the game completely.
                IO.addLine( "0: Quit Game" );
                // Now that our output is built, we can display it to the player.
                IO.display();

                try
                {
                    // Now we get the input from the player.
                    choiceInt = IO.getChoice();

                    // If the choice is greater than the number of maps, it must be invalid.
                    if ( choiceInt > maps.size() )
                    {
                        // We throw a generic exception, which will cause the loop to start over.
                        throw new Exception();
                    }
                }
                catch ( Exception e )
                {
                    // If our choise isn't valid for some reason, we just reset it to -1 and loop again.
                    choiceInt = -1;
                    // We also add a message to output.
                    IO.addLine( "I don't understand that selection." );
                    continue;
                }
            }

            // If the player chooses to end the game, we do that here.
            if ( choiceInt == 0 )
            {
                endGame( "Thank you for playing Adventures in Text." );
                return;
            }
            else
            {
                // Otherwise we set the chosen map to the map that corresponds to the player's input.
                chosenMap = maps.get( choiceInt - 1 );
            }
        }
        // If we have been provided the name of a map already we don't need to get a new selection.
        else
        {
            // We loop through our available maps to find the correct one to load.
            for ( GameMap map : maps )
            {
                if ( map.getName().equalsIgnoreCase( mapName ) )
                {
                    chosenMap = map;
                    break;
                }
            }

            if ( chosenMap == null )
            {
                // If there is still no map found with the given name, we will add a message to output to tell the player.
                IO.addLine( "The map with the name " + mapName + " could not be found." );
                // We will loop through and remove all of the maps from our component lists as cleanup.
                for ( GameMap map : maps )
                {
                    masterComponentUtility.removeComponent( map );
                }
                // Now we can call the beginNewGame() method again without supplying a name so the list is presented instead.
                beginNewGame( "" );
                return;
            }
        }

        // We will loop through and remove all of the maps except the chosen one from our component lists as cleanup.
        for ( GameMap map : maps )
        {
            if ( !map.equals( chosenMap ) )
            {
                masterComponentUtility.removeComponent( map );
            }
        }

        // Now that we have our chosen map, we can run the initial map setup.
        setupLevel( chosenMap );

        // We wait until now to add the save command since the game is now in a state saving is possible.
        addCommand( new Save() );
        return;
    }

    /**
     * This method is used to get a copy of the master component list.
     *
     * @return A list of GameComponent objects that are currently available.
     */
    public static ArrayList<GameComponent> componentList()
    {
        return masterComponentUtility.componentList();
    }

    /**
     * This method is used to end the game.
     *
     * @param endingText This is the final output text to be displayed to the player before quitting.
     */
    public static void endGame( String endingText )
    {
        // First we will add our ending text to our output.
        IO.addLine( endingText );

        // If there is not a game in progress we will exit the application entirely.
        if ( !gameInProgress )
        {
            // Here will prompt the player to press enter before quitting.
            IO.add( "Press enter to close this window..." );
            IO.display();
            IO.getInput();
            // After the player presses enter, we will terminate the program.
            quit();
        }
        else
        {
            // If the game is in progress, we just end the game without prompting to save first.
            IO.addLine( "Press Enter to continue..." );
            IO.display();
            IO.getInput();

            // Now we reinitialize the engine and return to the title screen.
            initializeEngine();
            showTitleScreen();
        }
    }

    public static GameEngineComponentUtility getComponentUtility()
    {
        return masterComponentUtility;
    }

    /**
     * This method is used to get a reference to the current player.
     *
     * @return A reference to the current player.
     */
    public static GamePlayer getCurrentPlayer()
    {
        return currentPlayer;
    }

    /**
     * This method is used to get an input string from the current IOUtility and passes it to the Processor to be processed.
     */
    public static void getInput()
    {
        Processor.processCommands( IO.getInput() );
    }

    /**
     * This method is used to load in a basic set of commands that level designers can bedin to utilize in thier creations.
     */
    public static void initializeDefaultCommands()
    {
        initializeDefaultDirections();

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
     * This method is used to set the state of the game back to its default state. From this state, only the most important
     * system commands will be reinitialized and no game level will be loaded yet.
     */
    public static void initializeEngine()
    {
        gameInProgress = false;
        currentPlayer = null;
        commands.clear();
        events.clear();
        masterComponentUtility.clear();

        initializeCommands();
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
     * This method is used to determine if the game has been marked as quit.
     *
     * @return True ff the game has been marked as quit, false otherwise.
     */
    public static boolean isGameOver()
    {
        if ( quit )
        {
            return true;
        }
        return false;
    }

    /**
     * This method loads in the content of the a save game file and restores it as the current game state.
     *
     * @param saveName The human readable name for the saved game file, without extension.
     */
    public static void loadGameState( String saveName )
    {
        promprForSave();

        // First we will initialize a couple of InputStreams we will need to load our game state.
        FileInputStream saveGameFileInputStream = null;
        ObjectInputStream saveGameObjectInputStream = null;

        // We also need a file object that represents our saved game file.
        File saveGameFile;

        // We will also initialize an object to store the data read in from our saved game file.
        Object engineObject = null;
        // Next we will initialize an Engine object to store our fully loaded game state.
        Engine gameState = null;

        // TODO: Add a check to see if the player wants to save their game before loading.

        // Here we sanitize our name as a file name so we can open the correct file.
        saveName = sanitizeFileName( saveName );

        // We need to make sure our saved game file exists before it can be loaded.
        saveGameFile = new File( saveName + "." + SAVE_EXT );
        if ( saveGameFile.exists() )
        {
            try
            {
                // First we open a FileInputStream for our saved game.
                saveGameFileInputStream = new FileInputStream( saveGameFile );
                // Now we open an ObjectInputStream to read in our save data.
                saveGameObjectInputStream = new ObjectInputStream( saveGameFileInputStream );

                // Next we read our game state from the file into our object.
                engineObject = saveGameObjectInputStream.readObject();

                // We will make sure we have an instance of an Engine object, just to be sure we have a valid save file.
                if ( engineObject instanceof Engine )
                {
                    // Now we can cast our object as an Engine since we know it is valid.
                    gameState = ( Engine ) engineObject;
                    // Finally, we reload all of the components into the Engine.
                    gameState.loadActiveComponents();
                }
                // Since we were successful, we mark the game as being in progress.
                gameInProgress = true;

                // When our data has been loaded, we can close our InputStreams.
                saveGameFileInputStream.close();
                saveGameObjectInputStream.close();
            }
            catch ( Exception e )
            {
                // If there are any errors, we will just output an error message and return.
                IO.addLine( "There was an error loading the game state. Your game has not been loaded.\n" );
                return;
            }
            // If we have made it to this point, then we have loaded our game, so we output a success message.
            IO.addLine( "The game state has been reloaded from the file on disk.\n" );
        }
        else
        {
            // If our file can't be found, we just output a message.
            IO.addLine( "The game file could not be found. Your game was not loaded.\n" );
        }
    }

    public static void loadIOUtility( String utilityName, boolean verbose )
    {
        GameIOUtility ioUtility = null;

        if ( !utilityName.equals( "" ) )
        {
            ArrayList<GameIOUtility> ioUtilityList = null;
            try
            {
                ioUtilityList = Loader.loadIOUtilities();

                for ( Object utilityObject : ioUtilityList )
                {
                    if ( utilityObject instanceof GameIOUtility &&
                         ( ( GameIOUtility ) utilityObject ).getName().equalsIgnoreCase( utilityName ) )
                    {
                        ioUtility = ( GameIOUtility ) utilityObject;
                    }
                    else
                    {
                        // TODO: We need to fix this here so the IOObject is only instantiated if it has the correct name.
                        if(utilityObject instanceof javax.swing.JFrame)
                        {
                            ((javax.swing.JFrame) utilityObject).dispose();
                        }
                    }
                }
            }
            catch ( Exception e )
            {
                // If there is some kind of error, we just add a message to output.
                IO.addLine( "There was a problem scanning for GameIOUtilities to load." );
            }

        }

        if ( ioUtility == null )
        {
            ioUtility = new SystemIOUtility();
        }

        IO.setIOUtility( ioUtility );
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
     * This method is used parse the command line arguments passed in to the main() method. It is used to determine if the
     * game should use the command line interface or to load the Swing based GUI window.
     *
     * @param args The arguments passed into the main() method
     */
    public static void parseCommandLineArguments( String[] args )
    {
        int i = 0, j;
        String arg;
        char flag;
        boolean verboseFlag = false;
        String ioUtilityName = "";

        while ( i < args.length && args[ i ].startsWith( "-" ) )
        {
            arg = args[ i++ ];

            // use this type of check for "wordy" arguments
            if ( arg.equals( "-verbose" ) )
            {
                System.out.println( "Verbose mode is on..." );
                verboseFlag = true;
            }

            // use this type of check for arguments that require arguments
            else if ( arg.equalsIgnoreCase( "-i" ) || arg.equalsIgnoreCase( "-ioutility" ) )
            {
                if ( i < args.length )
                    ioUtilityName = args[ i++ ];
                else
                    System.err.println( "You must supply a name after using the -ioutility flag." );
                if ( verboseFlag )
                    System.out.println( "Loading IUOtility: " + ioUtilityName );
            }
        }
        if ( i == args.length )
            System.err.println( "Usage: java Adventure [-verbose] [-ioutility utilityName]" );
        else
            System.out.println( "Success!" );

        loadIOUtility( ioUtilityName, verboseFlag );

    }

    public static void promprForSave()
    {
        if ( gameInProgress )
        {
            int choiceInt = -1;

            // We will loop until the player selects either no (1) or yes (2)
            while ( choiceInt != 1 && choiceInt != 2 )
            {
                IO.addLine( "Would you like to save your progress?" );
                IO.addLine( "1: No" );
                IO.addLine( "2: Yes" );
                IO.display();
                try
                {
                    choiceInt = IO.getChoice();
                    // If choic is no
                    if ( choiceInt == 1 )
                    {
                        return;
                    }
                    // If the choice is yes
                    if ( choiceInt == 2 )
                    {
                        String prompt = "Please enter a name for this save file";
                        if ( !Save.getMostRecentSaveName().equals( "" ) )
                        {
                            prompt += " or press enter to use the last used save file";
                        }
                        prompt += ".";

                        IO.addLine( prompt );
                        IO.display();
                        String saveName = "";
                        while ( saveName.equals( "" ) )
                        {
                            saveName = IO.getInput();

                            if ( saveName.equals( "" ) && !Save.getMostRecentSaveName().equals( "" ) )
                            {
                                saveGameState( Save.getMostRecentSaveName() );
                                return;
                            }

                            if ( !saveName.equals( "" ) )
                            {
                                saveGameState( saveName );
                                return;
                            }
                        }
                    }
                }
                catch ( Exception e )
                {
                    // If there was an error parsing the player's choice we need to try again.
                    choiceInt = -1;
                    IO.addLine( "I don't understand that selection." );
                    continue;
                }
            }
        }
    }

    /**
     * This method is used to mark the game as quit.
     */
    public static final void quit()
    {
        quit = true;
        System.exit( 0 );
    }

    public static final String SAVE_EXT = "adv";

    /**
     * This method will save the current state of the game to a file.
     *
     * @param saveName The human readable name for the saved game file, without extension.
     */
    public static void saveGameState( String saveName )
    {
        if ( !gameInProgress )
        {
            IO.addLine( "There is no game in progress to save." );
            return;
        }

        // First we will instantiate a new Engine object to store the state of the game.
        Engine gameState = new Engine();

        // Here we will create a couple output stream objects needed to save our game.
        FileOutputStream saveGameFileOutputStream;
        ObjectOutputStream saveGameObjectOutputStream;

        // We also need a file object that represents our saved game file.
        File saveGameFile;

        // Now we will store the unsanitized name for the saved game in the Engine object.
        gameState.saveName = saveName;

        // Here we sanitize our name so that it is acceptable for use as a file name.
        saveName = sanitizeFileName( saveName );

        // If our saved game file already exists, we must prompt the player before overwriting it.
        saveGameFile = new File( saveName + "." + SAVE_EXT );
        if ( saveGameFile.exists() )
        {
            int choiceInt = -1;

            // We will loop until the player selects either no (1) or yes (2)
            while ( choiceInt != 1 && choiceInt != 2 )
            {
                IO.addLine( "There is already a saved game with this name. Overwrite it?" );
                IO.addLine( "1: No" );
                IO.addLine( "2: Yes" );
                IO.display();
                try
                {
                    choiceInt = IO.getChoice();

                    // If the choice is no, we output a message the game was not saved and return.
                    if ( choiceInt == 1 )
                    {
                        IO.addLine( "Your game has not been saved." );
                        return;
                    }
                    // If the choice is not yes, then the choice must be invalid.
                    if ( choiceInt != 2 )
                    {
                        // We reset choiceInt to -1 and output a message the choice is not valid, then loop again.
                        choiceInt = -1;
                        IO.addLine( "I don't understand that selection." );
                        continue;
                    }
                }
                catch ( Exception e )
                {
                    // If there was an error parsing the player's choice we need to try again.
                    choiceInt = -1;
                    IO.addLine( "I don't understand that selection." );
                    continue;
                }
            }
        }

        // If we have made it this far, then we can just go ahead and perform the save.
        try
        {
            // First we open a FileOutputStream for our saved game.
            saveGameFileOutputStream = new FileOutputStream( saveName + "." + SAVE_EXT );
            // Now we open an ObjectOutputStream to record our save data.
            saveGameObjectOutputStream = new ObjectOutputStream( saveGameFileOutputStream );
            // Next we record our GameState to the file.
            saveGameObjectOutputStream.writeObject( gameState );
            // When our data has been recorded, we can close our OutputStreams.
            saveGameObjectOutputStream.close();
            saveGameFileOutputStream.close();
        }
        catch ( Exception e )
        {
            // If there any type of error, we output a message and just return.
            IO.addLine( "There was an error saving the game state. Your game has not been saved.\n" );
            return;
        }
        // If we make it to this point, everything went well and we can output a success message.
        IO.addLine( "The current game state has been saved.\n" );
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

        // Here we mark the game as being in progress.
        gameInProgress = true;

        // Finally, we describe the player's location to them and the game can begin.
        currentPlayer.getLocation().describe();
    }

    /**
     * This method calls the display of the current IOUtility, giving it a default prompt to display to the player.
     */
    public static void showOutput()
    {
        if ( gameInProgress )
        {
            IO.display( "What would you like to do?\n" );
        }
        else
        {
            showTitleScreen();
        }
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
        GameComponent component = masterComponentUtility.getComponent( newCurrentPlayerName );
        if ( component instanceof GamePlayer )
        {
            currentPlayer = ( GamePlayer ) component;
        }
    }

    /*******************************************************************************************************************
     * Package Protected Section
     ******************************************************************************************************************/

    /**
     * This method will get a copy of the list containing all of the GameCommand objects that are currently in place.
     *
     * @return A list of GameCommand objects
     */
    static ArrayList<GameCommand> commandList()
    {
        ArrayList<GameCommand> commandList = new ArrayList<GameCommand>();
        commandList.addAll( commands );
        return commands;
    }

    /**
     * This method will get a copy of the list containing all of the GameEvents that are currently in place.
     *
     * @return A list of GameEvent objects
     */
    static ArrayList<GameEvent> eventList()
    {
        ArrayList<GameEvent> eventList = new ArrayList<GameEvent>();
        eventList.addAll( events );
        return eventList;
    }

    /*******************************************************************************************************************
     * Private Section
     ******************************************************************************************************************/
    private ArrayList<GameComponent> activeComponents;

    private GamePlayer activePlayer;

    private static ArrayList<GameCommand> commands;

    private static GamePlayer currentPlayer;

    private Engine()
    {
        this.activeComponents = componentList();
        this.activePlayer = getCurrentPlayer();

        this.saveName = "";
    }

    private static ArrayList<GameEvent> events;

    private static boolean gameInProgress;

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
     * This instance method is used durring loading to restore the static state of the Engine back to the state stored
     * within the Engine object instance.
     */
    private void loadActiveComponents()
    {
        currentPlayer = null;
        masterComponentUtility.clear();

        masterComponentUtility.addComponents( this.activeComponents );

        reloadCommands();
        reloadEvents();

        switchPlayer( this.activePlayer );
        getCurrentPlayer().getLocation().describe();
    }

    private static Thread mainThread;

    private static GameEngineComponentUtility masterComponentUtility;

    private static boolean quit;

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
     * Convert a string to a filename safe form.
     *
     * @param fileName The string to sanitize.
     * @return The sanitized string.
     */
    private static String sanitizeFileName( String fileName )
    {
        // First we will create aregular expression to select all the characters that are not allowed for a filename.
        String forbiddenCharacters =
            "[\\s\\-\\?\\[\\]\\/\\\\\\=\\<\\>\\:\\;\\,\\'\\\"\\&\\$\\#\\*\\(\\)\\|\\~\\`\\!\\{\\}\\@\\%\\^\\=\\+]+";

        // First we will make the string all lowercase.
        fileName = fileName.toLowerCase();
        // Next we will replace all of the forbidden characters in the string with underscores.
        fileName = fileName.replaceAll( forbiddenCharacters, "_" );
        // If the first character is an underscore, we will remove it from the string.
        if ( fileName.startsWith( "_" ) )
        {
            fileName = fileName.substring( 1 );
        }
        // Finally we return our sanitized string.
        return fileName;
    }

    private String saveName;

    /**
     * This method will get a copy of the list containing all of the GameCommand objects that are currently in place.
     *
     * @return A list of GameCommand objects
     */
    @SuppressWarnings( "compatibility:7688155474666955850" )
    private static final long serialVersionUID = 1L;
}
