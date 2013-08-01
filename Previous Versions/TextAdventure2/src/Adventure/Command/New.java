package Adventure.Command;

import Adventure.*;

import Adventure.API.*;

import Adventure.Base.*;

import Adventure.Demo.*;

import java.util.*;

/**
 * This command is one of the 5 required system commands, and is used to allow a player to choose a level and to begin the
 * game after the level has been loaded into the Engine.
 */
public class New
    extends BaseCommand
{
    @SuppressWarnings( "compatibility:735773540170778568" )
    private static final long serialVersionUID = 1L;

    /**
     * This constructor enables the New command.
     */
    public New()
    {
        super( "New", "Begin a new game.", "1" );
    }

    /**
     * When run, this method will load a list of levels that are currently available for the player to choose, then it
     * will load the initial state for the level that is chosen.
     * 
     * @param operation The current Operation object that is being processed.
     */
    public void run( Operation operation )
    {
        ArrayList<GameMap> maps = new ArrayList<GameMap>();
        Engine.initializeEngine();

        GameMap demoMap = new DemoMap();
        demoMap.setMapPath( Loader.getMapPath() );
        maps.add( demoMap );
        try
        {
            ArrayList<GameMap> mapList = Loader.loadMaps();
            if ( mapList != null && !mapList.isEmpty() )
            {
                maps.addAll( mapList );
            }
        }
        catch ( Exception e )
        {
            IO.addLine( "There was a problem scanning for maps to load." );
        }

        GameMap chosenMap = null;
        int choiceInt = -1;

        if ( maps.size() > 0 )
        {
            while ( choiceInt < 0 || choiceInt > maps.size() )
            {
                IO.addLine( "Choose a Level:" );
                int i = 0;
                for ( GameMap map : maps )
                {
                    i++;
                    IO.addLine( i + ": " + map.getName() );
                }
                IO.addLine( "0: Quit Game" );
                IO.display();
                try
                {
                    choiceInt = IO.getChoice();

                    // Improve error handling here.
                    if ( choiceInt > maps.size() )
                    {
                        throw new Exception();
                    }
                }
                catch ( Exception e )
                {
                    choiceInt = -1;
                    IO.addLine( "I don't understand that selection." );
                    continue;
                }
            }
            if ( choiceInt == 0 )
            {
                Engine.endGame( "Thank you for playing Adventures in Text." );
                return;
            }
            else
            {
                chosenMap = maps.get( choiceInt - 1 );
                for ( GameMap map : maps )
                {
                    if ( !map.equals( chosenMap ) )
                    {
                        Engine.removeComponent( map );
                    }
                }

                Engine.setupLevel( chosenMap );
                return;
            }
        }
    }
}
