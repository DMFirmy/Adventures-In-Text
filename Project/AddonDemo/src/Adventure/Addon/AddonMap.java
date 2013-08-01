package Adventure.Addon;

import Adventure.*;

import Adventure.API.*;

import Adventure.Base.*;

import Adventure.Condition.*;
import Adventure.Demo.Condition.*;
import Adventure.Demo.Event.*;

import Adventure.Objects.*;

/**
 * This is a very simple demo level with only 2 rooms that is used to demonstrate gow custom content can be added
 * to the game.
 */
public class AddonMap
    extends BaseMap
{
    @SuppressWarnings( "compatibility:4088856610171379135" )
    private static final long serialVersionUID = 1L;

    /**
     * This constructor creates our AddonMap object.
     */
    public AddonMap()
    {
        super( "Addon Demo", "This is a very simple demo level that shows how custom content can be added." );
    }

    /**
     * This method will build all of the allowed commands for this level.
     */
    public void buildMapCommands()
    {
        Engine.initializeDefaultCommands();
        // For this demo, we will utilize some of the extra directions available.
        Engine.initializeExtraDirections();

        // This level also uses a couple very simple custom commands.
        Engine.addCommand( new Fly() );
        Engine.addCommand( new Land() );
    }

    /**
     * This method will set up all of the objects and locations for this level.
     */
    public void buildMapLocations()
    {
        Location room1 = new Location( "Room1", "A room with doors in several directions." );
        Location room2 =
            new Location( "Room2", "A room no doors, only a single enterence in the center of the floor that leads to a room below." );

        // This room is a maze that always leads back to the same room unless you travel "up"
        room1.addExit( Engine.getDirection( "east" ), room1 );
        room1.addExit( Engine.getDirection( "west" ), room1 );
        room1.addExit( Engine.getDirection( "northeast" ), room1 );
        room1.addExit( Engine.getDirection( "southwest" ), room1 );
        room1.addExit( Engine.getDirection( "down" ), room1 );
        room1.addExit( Engine.getDirection( "up" ), room2 );

        // This room will require traveling "up" to reach which will require the custom "Fly" command.
        room2.addExit( Engine.getDirection( "down" ), room1 );

        // This orb will serve as the key to exiting the level. It has a very high weight that a regular player
        // could not lift. Instead we are using a stronger custom StrongPlayer instead
        Item orb = new Item( "Orb", "This is an extremely heavy steel orb. No ordinary player could ever lift it!" );
        orb.setItemWeight( 50.0 );
        room2.addItem( orb, 1, null );

        StrongPlayer player =
            new StrongPlayer( "StrongPlayer1", "A very strong player character who can lift a lot of weight." );
        player.setLocation( room1, false );

        this.setCurrentPlayer( player );
    }

    /**
     * This method will initialize the simple rules and events for this level.
     */
    public void buildMapMechanics()
    {
        // Moving the direction "up" is not allowed unless you are flying.
        AllowMovingUp upAllowed = new AllowMovingUp( "upAllowed" );
        
        // Once the orb is brought to room 1, the game ending is triggered.
        TriggerEndOfGame exitAllowed =
            new TriggerEndOfGame( "exitAllowed", "Having brought the heavy orb into thee first room, you are magically warped back outside. Thank you for playing.\n" );
        exitAllowed.addEventCondition( new PawnInLocation( "playerInRoom1", Engine.currentPlayer(),
                                                           ( GameLocation ) Engine.getComponent( "Room1" ) ) );
        exitAllowed.addEventCondition( new ContainerHasItems( "playerHasOrb", Engine.currentPlayer(),
                                                              ( GameItem ) Engine.getComponent( "Orb" ), 1 ) );
    }
}
