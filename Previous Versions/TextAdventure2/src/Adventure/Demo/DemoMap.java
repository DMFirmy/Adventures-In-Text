package Adventure.Demo;

import Adventure.*;

import Adventure.API.*;

import Adventure.Base.*;

import Adventure.Demo.Condition.*;
import Adventure.Demo.Event.*;

import Adventure.Objects.*;

/**
 * This is a rather simple demo level with a rather straight forward plot. It only serves as a very brief and
 * incomplete demonstration of what this game engine can do and how to build a game level in general. Note that
 * in the 3 build methods we instantiate all of the necessary GameComponent objects and we configure them as needed,
 * but we don't do anything in the way of storing them. This is because the constructor for the BaseComponent class will
 * automatically register the component with the Engine class whenever it is instantiated. If a level designer were
 * to create a customized implementation of the GameComponent interface that didn't extend the BaseComponent class, they
 * would need to address this problem. Also note that the GameEvents and GameConditions used in this demo are very
 * specific and overly simplified. In future versions I intend to expand this functionality greatly.
 *
 * In order to reference to the GameComponents that you have created from within a differnt build method,
 * you need to get a reference to them from the Engine class using the getComponent() method. These
 * references could have been instead stored as fields within this class, but I chose not to do so. Since
 * getComponent() returns an object that is cast as a GameComponent object, it needs to be re-cast as whatever
 * type is necessary after it is returned. In future versions of this game I intend to create some helper
 * methods to handle this type casting, but for now I have done it by hand.
 */
public class DemoMap
    extends BaseMap
{
    @SuppressWarnings( "compatibility:2361553155046296186" )
    private static final long serialVersionUID = 1L;

    /**
     * This constructor will pass in the name and description assigned to this level to the base constructor.
     */
    public DemoMap()
    {
        super( "Demo Map", "A Demo Level created to show off the game engine" );
    }

    /**
     * This method is used to instantiate any GameCommand objects that will be available within this level.
     * Because this is only a very simple demo, we are only initializing the default command from the engine,
     * but a level designer that has created custom commands can also instantiate them here so that they are available
     * during the game. Note that GameDirections are considered to be commands, and since it is necessary to have all of
     * the directions in place for creating exits, this is the first setup method that will be called by the engine when
     * the level is loaded.
     */
    public void buildMapCommands()
    {
        // For this simple level we can just use the default commands, which gives us 4 directions and 12 commands.
        Engine.initializeDefaultCommands();
    }

    /**
     * This method is used to set up all of the various GameLocations and the GameObjects that are contained within them.
     * Anything that exists within the game world that can be interacted with should be initialized within this method.
     * It is worth noting that GameDialogs are also considered to be GameObjects, and so conversations are also set up in
     * this method. Also note that the current player is set at the end of this method as well. the engine will call this
     * method after it has called buildMapCommands() so that the directions that have been assigned are available, but
     * before the call to buildMapMechanics()
     */
    public void buildMapLocations()
    {
        // First we can initialize all of our locations and set their names & descriptions.
        Location entryHall =
            new Location( "Entry Hall", "Though you could swear that the way that you entered this building was" +
                          " just north of this room, the wall on the north side of the room is completely bare" +
                          " and featureless. This stone chamber has a plush red carpet, but no furnishings at all." +
                          " An archway on the south wall leads out into what appears to be a wide hallway." );
        Location mainRoom =
            new Location( "Main Room", "This wide and tall hallway runs from west to east, branching off to the" +
                          " north in the middle. Though there is no apparent sources of light, you can see well" +
                          " even though there are no windows in this area." );
        Location westWing =
            new Location( "West Wing", "This room appears to be a bedchamber of some kind, though it doesn't" +
                          " have any furnishings. There is a slight echo as your footsteps fall on the stone floor," +
                          " and you feel a chill draft coming from somewhere." );
        Location hiddenRoom =
            new Location( "Hidden Room", "This small chamber is more like an over-sized closet. It can't be" +
                          " much more than 5 feet on all sides. In the back of this room, a young man no" +
                          " more than 12-years-old is reading a book by the light of a candle. When he notices" +
                          " you approaching, he looks up and smiles." );
        Location eastWing =
            new Location( "East Wing", "This room is very wide, and has the appearance that it might" + " have served as a dining chamber at some point in the past. There is really very little" +
                          " of note in here now, just a few chairs that look quite uncomfortable and a small" +
                          " table along one wall. There is an ornate archway on the wall to the south," +
                          " though in the north wall is also a much smaller passageway that is of rough stone." );
        Location caveMouth =
            new Location( "Cave Mouth", "Given how narrow the corridor leading to this chamber was, it is" +
                          " surprisingly large inside. There appears to be another opening obscured behind" +
                          " a very large boulder in the back of this chamber, but there is no chance to move" +
                          " such a heavy object." );
        Location southHall =
            new Location( "South Hall", "This room is a mask of shadows. You can barley see the exits to the" +
                          "north and off the west wall." );
        Location exitCorridor =
            new Location( "Exit Corridor", "This room appears to be a hallway that bends off to the south, but as you approach" +
                          "the south end of the room, all you find is a completely baren wall." );
        Location exitHall = new Location( "Exit Hall", "" );

        // Here we add the correct exits to each location to complete our map.
        entryHall.addExit( Engine.direction( "south" ), mainRoom );

        mainRoom.addExit( Engine.direction( "north" ), entryHall );
        mainRoom.addExit( Engine.direction( "west" ), westWing );
        mainRoom.addExit( Engine.direction( "east" ), eastWing );

        westWing.addExit( Engine.direction( "east" ), mainRoom );
        // This exit is hidden
        Exit exit = new Exit( Engine.direction( "north" ), hiddenRoom );
        exit.hide();
        westWing.addExit( exit );

        hiddenRoom.addExit( Engine.direction( "south" ), westWing );

        eastWing.addExit( Engine.direction( "north" ), caveMouth );
        eastWing.addExit( Engine.direction( "south" ), southHall );
        eastWing.addExit( Engine.direction( "west" ), mainRoom );

        caveMouth.addExit( Engine.direction( "south" ), eastWing );

        southHall.addExit( Engine.direction( "west" ), exitCorridor );
        southHall.addExit( Engine.direction( "north" ), eastWing );

        exitCorridor.addExit( Engine.direction( "east" ), southHall );

        exitHall.addExit( Engine.direction( "north" ), exitCorridor );

        // Here we initialize the items that will exist in our level map and set their names.
        Item key1 = new Item( "Key 1", "This is a small key. It is very shiny like it has been polished recently." );

        Item key2 = new Item( "Key 2", "This is a small, but dingy key. It looks old." );

        Item boulder =
            new Item( "Boulder", "This large grey boulder is far too heavy to move. It appears that it is covering an opening," +
                      "but there is no way that you can reach it." );

        // Here we initialize the actors that will appear in our level map and set their names.
        Actor narrator = new Actor( "Narrator", "A scrawny old man with long silver hair." );
        Actor gamer = new Actor( "Gamer", "A young man who looks about 12." );
        Actor sneak = new Actor( "Sneak", "A small and dirty man with very dark skin." );

        // Here we initialize all of our dialogs for conversations by setting their names and texts.
        Dialog narratorIntro =
            new Dialog( "N-Intro", "Welcome to you player. I am the Narrator, and it is my job to welcome you to \"Adventures In Text\", " +
                        "a game designed by that old wizard DMFirmy...\n\nBecause I am only here to provide you with a very brief introduction to what " +
                        "is possible in this game, the task I have for you is simple. Are you ready to begin?" );
        Dialog narratorReady =
            new Dialog( "N-Ready", "Hidden in this place are two keys. Find them and bring them to me, I will be waiting " +
                        "in the chamber that lies south of here. Once you have found them, I will unlock the exit and allow you to leave this place." );
        Dialog narratorNotReady =
            new Dialog( "N-Not-Ready", "Oh, I am pretty sure you are going to be just fine. All you need to do is find " +
                        "the two keys that are lost in this building and bring them to me in the exit cooridor to the south.\n\n" +
                "If you are having trouble finding the keys, remember to \"look\" at your surroundings, to explore everywhere, " +
                "and to \"speak\" with anyone that you meet. I have a couple of assistants around here, and they might be able to help you." );
        Dialog narratorIntroEnding =
            new Dialog( "N-Intro-Ending", "Don't worry, you'll do fine. Just come and see me near the exit when you have " +
                        "found those keys. I know it will be an easy task for one of your talents.\n\nWith that, the Narrator waves " +
                        "his hand and disapears in a puff of smoke. You gag for a moment before you realize that you are now alone. " +
                        "Looking around, you see an archway to the south that leads to a large hallway.\n" );
        Dialog narratorExit =
            new Dialog( "Narrator Exit", "Congratulations, you have completed your assigned task. Now that you have brought " +
                        "me the keys to the exit, I can use them to open the passage to the south.\n\nThe Narrator waves his hand " +
                        "gently, and the wall to the south fades as though it was never there at all. You can now tell that you are " +
                        "in a hallway that stretches about 10 feet to the south and the west." );

        Dialog narratorExitNotOpen =
            new Dialog( "N Exit Not Open", "You must bring me the two keys before I will reveal the exit.\n" );

        Dialog narratorExitOpen =
            new Dialog( "Narrator Exit Open", "Head to the south, you are free to leave whenever you choose.\n" );

        Dialog hiddenRoomFound =
            new Dialog( "Hidden Room Found", "Searching the room, you notice the source of the draft is coming from the " +
                        "north wall. You move closer to incestigate, and realize that there is a very faint outline of a door. After " +
                        "searching around for a bit, you find a cleverly hidden catch, and the door swings open." );

        Dialog gamerIntro =
            new Dialog( "Gamer Introduction", "I got into trouble, so I decided to hide out here for awhile. Want to play " +
                        "a game with me to pass some time?" );
        Dialog gamerPlay =
            new Dialog( "Gamer Play", "Let's play a game of rock/paper/scissors... best of three wins. Ready?" );
        Dialog gamerDefeat =
            new Dialog( "Gamer Defeat", "I can't believe you beat me. As a reward, I will give you this shiny key I found.\n\n" +
                "He hands you the key and you place it into your pocket." );
        Dialog gamerDefeated = new Dialog( "Gamer Defeated", "I can't believe you beat me..." );

        Dialog sneakIntro = new Dialog( "Sneak Intro", "Darn, you caught me." );
        Dialog sneakFound =
            new Dialog( "Sneak Found", "Darn, you caught me... You can have your key back. I just got bored and wanted to mess with ya... no hard fellings right?" );

        // Conversations require connections between dialogs, so we set up the branches here.
        narratorIntro.addBranch( "Yes, I am ready. What should I do now?", narratorReady );
        narratorIntro.addBranch( "I am not really sure...", narratorNotReady );
        narratorReady.addBranch( "Continue...", narratorIntroEnding );
        narratorNotReady.addBranch( "Continue...", narratorIntroEnding );

        gamerIntro.addBranch( "Sure, sounds fun.", gamerPlay );

        // Now that all of the conversations are set up, we assign them to the correct actors.
        narrator.setInitialDialog( narratorIntro );
        gamer.setInitialDialog( gamerIntro );
        sneak.setInitialDialog( sneakIntro );

        // Some things are hidden, so we need to set that here.
        sneak.hide();
        key2.hide();

        // The boulder is too big to move, so we must mark that too.
        boulder.setStatus( "movable", 0 );

        // We move the narrator here to make it easier to return them to the exitCooridor later.
        entryHall.addPawn( "Narrator" );
        hiddenRoom.addPawn( "Gamer" );
        southHall.addPawn( "Sneak" );

        // Here we set all the items where they belong.
        gamer.addItem( "Key 1", null );
        caveMouth.addItem( "Boulder", null );
        caveMouth.addItem( "Key 2", null );

        // Now that everything is set up, we place the player in the level map.
        Player player = new Player( "Player", "" );
        player.setLocation( entryHall, false );

        // We set the player as the current player for the game
        this.setCurrentPlayer( "Player" );
    }

    /**
     * This method is used to build all of the mechanics that will effect the flow of the game. Mechanics consist of
     * GameEvent objects, which are things that the engine will attempt to do every turn, and GameCondition objects that
     * are attached to each event and determine if the event is allowed to run or not. Every time the engine attempts
     * to run an event, each of the attached conditions is checked. Only if all of the attached conditions return true
     * will the event be allowed to run. If even one of the conditions is checked and returns false, the event is bypassed
     * until the next turn.
     */
    public void buildMapMechanics()
    {
        // At the beginning of the game, the Narrator introduces himself.
        SpeakerInitiatesDialog narratorIntroducesSelf =
            new SpeakerInitiatesDialog( "OpeningDialog", ( GameSpeaker ) Engine.getComponent( "Narrator" ) );

        // We need to add a check to make sure it is only run once
        narratorIntroducesSelf.addEventCondition( new DialogHasNotRun( "introComplete",
                                                                       ( GameDialog ) Engine.getComponent( "N-Intro" ) ) );

        // We also need to add a condition to make sure it is the first turn
        narratorIntroducesSelf.addEventCondition( new CurrentPlayerMovesEquals( "firstTurn", 0 ) );

        // This event will automatically move the Narrator to the exit once his initial conversation is finished.
        MovePawnToLocation narrator2exit =
            new MovePawnToLocation( "Narrator2Exit", ( GamePawn ) Engine.getComponent( "Narrator" ),
                                    ( GameLocation ) Engine.getComponent( "Exit Corridor" ) );

        // This condition checks if the Narrator has made it to the end of his initial conversation yet.
        DialogHasRun narratorSpeachDone =
            new DialogHasRun( "narratorSpeachDone", ( GameDialog ) Engine.getComponent( "N-Intro-Ending" ) );
        narrator2exit.addEventCondition( narratorSpeachDone );

        // This condition confirms that the Narrator is still in the Entry Hall.
        narrator2exit.addEventCondition( new PawnInLocation( "narratorInEntry",
                                                             ( GamePawn ) Engine.getComponent( "Narrator" ),
                                                             ( GameLocation ) Engine.getComponent( "Entry Hall" ) ) );

        // Once the Narrator moves we need to update his initial dialog so he no longer gives his speach.
        SetSpeakerInitialDialog findKeysDialog =
            new SetSpeakerInitialDialog( "findKeysDialog", ( GameSpeaker ) Engine.getComponent( "Narrator" ),
                                         ( GameDialog ) Engine.getComponent( "N Exit Not Open" ) );

        // We will only run this event if the narrator has already finished his speach.
        findKeysDialog.addEventCondition( narratorSpeachDone );

        // We also only want this event to run once.
        findKeysDialog.addEventCondition( new ComponentLacksStatus( "findKeysDialogActive",
                                                                    Engine.getComponent( "Narrator" ),
                                                                    "dialog changed" ) );

        // Here we set up the new description for the West Wing once the hidden room is found
        ChangeComponentDescription newWestWingDesc =
            new ChangeComponentDescription( "WWDescriptUpdate", Engine.getComponent( "West Wing" ),
                                            "This room appears to be a bedchamber of some kind, though it doesn't" +
                                            " have any furnishings. There is a slight echo as your footsteps fall on the stone floor." +
                                            " There is an exit on the north wall, and another to the west." );

        // We need a check to see if the hidden exit is found first.
        newWestWingDesc.addEventCondition( new GameHideableIsNotHidden( "hiddenRoomFound",
                                                                        ( GameHideable ) Engine.getComponent( "n_5" ) ) );

        // We also check that this event only happens 1 time
        newWestWingDesc.addEventCondition( new ComponentLacksStatus( "WWDescUpdated",
                                                                     Engine.getComponent( "West Wing" ),
                                                                     "description changed" ) );

        // Once the hidden room is found, a dialog is initiated with this event.
        InitiateDialog hiddenRoomDialog =
            new InitiateDialog( "hiddenRoomDialog", ( GameDialog ) Engine.getComponent( "Hidden Room Found" ) );

        // This will check to make sure that the dialog has been changed
        hiddenRoomDialog.addEventCondition( new ComponentHasStatus( "hasWWDescUpdated",
                                                                    Engine.getComponent( "West Wing" ),
                                                                    "description changed" ) );

        // We also check that this event only happens 1 time
        ComponentLacksStatus wwDialogPlayed =
            new ComponentLacksStatus( "wwDialogPlayed", Engine.getComponent( "Hidden Room Found" ), "has run" );
        hiddenRoomDialog.addEventCondition( wwDialogPlayed );

        // This condition confirms that the Player is still in the West Wing.
        hiddenRoomDialog.addEventCondition( new PawnInLocation( "playerInWestWing", Engine.currentPlayer(),
                                                                ( GameLocation ) Engine.getComponent( "West Wing" ) ) );

        // Inside the hidden room there you can play a game of Paper/Rock/Scissors
        AssignStatusToComponent beginPlayingGame =
            new AssignStatusToComponent( "playingGame", Engine.getComponent( "Player" ), "PaperRockScissors", 1 );

        // Depending on the conversation with the Gamer in the hidden room you might get a status assigned
        DialogHasRun gamerPlayDialog =
            new DialogHasRun( "gamerPlayDialog", ( GameDialog ) Engine.getComponent( "Gamer Play" ) );
        beginPlayingGame.addEventCondition( gamerPlayDialog );

        // The game can only be won once, so we check here
        ComponentLacksStatus wonGame =
            new ComponentLacksStatus( "wonGame", Engine.currentPlayer(), "WonPaperRockScissors" );
        beginPlayingGame.addEventCondition( wonGame );

        // Once the game is begun, this event will kick off the paper/rock/scissors game loop
        PaperRockScissors paperRockScissors = new PaperRockScissors( "PaperRockScissors" );

        // The game will only run if the gamer assigns the above status to the player
        ComponentHasStatus isPlayingGame =
            new ComponentHasStatus( "isPlayingGame", Engine.currentPlayer(), "PaperRockScissors" );
        paperRockScissors.addEventCondition( isPlayingGame );

        // This event causes the sneak to steal Key 2 from the player
        ItemMovedToNewContainer sneakTheft =
            new ItemMovedToNewContainer( "sneakTheft", ( GameContainer ) Engine.getComponent( "Sneak" ),
                                         ( GameItem ) Engine.getComponent( "Key 2" ), 1, Engine.currentPlayer() );

        // We need to make sure that the player is in the South Hall first
        PawnInLocation playerInSouthHall =
            new PawnInLocation( "playerInSouthHall", Engine.currentPlayer(), ( GameLocation ) Engine.getComponent( "South Hall" ) );
        sneakTheft.addEventCondition( playerInSouthHall );

        // We also need to make sure the sneak is still hidden
        sneakTheft.addEventCondition( new ComponentHasStatus( "sneakIsHidden", Engine.getComponent( "Sneak" ),
                                                              "hidden" ) );

        // When the sneak is found, a dialog is started with this event
        InitiateDialog sneakFoundDialog =
            new InitiateDialog( "sneakFoundDialog", ( GameDialog ) Engine.getComponent( "Sneak Found" ) );

        // We need to make sure the sneak is not hidden is not hidden anymore
        ComponentLacksStatus sneakNotHidden =
            new ComponentLacksStatus( "sneakNotHidden", Engine.getComponent( "Sneak" ), "hidden" );
        sneakFoundDialog.addEventCondition( sneakNotHidden );

        // The dialog will only be triggered if the sneak has the key
        ContainerHasItems sneakHasKey =
            new ContainerHasItems( "sneakHasKey", ( GameContainer ) Engine.getComponent( "Sneak" ),
                                   ( GameItem ) Engine.getComponent( "Key 2" ), 1 );
        sneakFoundDialog.addEventCondition( sneakHasKey );

        // We also need to make sure this dialog only runs once.
        ComponentLacksStatus shDialogPlayed =
            new ComponentLacksStatus( "shDialogPlayed", Engine.getComponent( "Sneak Found" ), "has run" );
        sneakFoundDialog.addEventCondition( shDialogPlayed );

        // This event causes the sneak to give back Key 2 to the player once he is found
        ItemMovedToNewContainer sneakReturnsKey =
            new ItemMovedToNewContainer( "sneakReturnsKey", Engine.currentPlayer(),
                                         ( GameItem ) Engine.getComponent( "Key 2" ), 1,
                                         ( GameContainer ) Engine.getComponent( "Sneak" ) );

        // We must test to be sure that the sneak has the key to return it.
        sneakReturnsKey.addEventCondition( sneakHasKey );

        // We also need to make sure the dialog has been run.
        ComponentHasStatus shDialogHasPlayed =
            new ComponentHasStatus( "shDialogHasPlayed", Engine.getComponent( "Sneak Found" ), "has run" );
        sneakReturnsKey.addEventCondition( shDialogHasPlayed );

        // We also need to make sure the sneak is not still hidden
        sneakReturnsKey.addEventCondition( sneakNotHidden );

        // This event will add the final exit once the player has completed the tasks assigned to him
        AddExitToLocation addLevelExit =
            new AddExitToLocation( "addLevelExit", ( GameLocation ) Engine.getComponent( "Exit Corridor" ),
                                   ( GameLocation ) Engine.getComponent( "Exit Hall" ), Engine.direction( "South" ) );

        // We must make sure that the narrator is at the exit first
        addLevelExit.addEventCondition( new PawnInLocation( "narratorAtExit",
                                                            ( GamePawn ) Engine.getComponent( "Narrator" ),
                                                            ( GameLocation ) Engine.getComponent( "Exit Corridor" ) ) );

        // We must make sure that the player is at the exit also
        addLevelExit.addEventCondition( new PawnInLocation( "playerAtExit", Engine.currentPlayer(),
                                                            ( GameLocation ) Engine.getComponent( "Exit Corridor" ) ) );

        // We must also make sure that the player has found both keys first.
        addLevelExit.addEventCondition( new ContainerHasItems( "playerHasKey1", Engine.currentPlayer(),
                                                               ( GameItem ) Engine.getComponent( "Key 1" ), 1 ) );
        addLevelExit.addEventCondition( new ContainerHasItems( "playerHasKey2", Engine.currentPlayer(),
                                                               ( GameItem ) Engine.getComponent( "Key 2" ), 1 ) );

        // We need to make sure this event only happens one time also
        LocationLacksExitInDirection exitPathNotAdded =
            new LocationLacksExitInDirection( "exitPathNotAdded", ( GameLocation ) Engine.getComponent( "Exit Corridor" ),
                                              Engine.direction( "South" ) );
        addLevelExit.addEventCondition( exitPathNotAdded );

        // Once the exit is open we need to update his Narrator's initial dialog again.
        SetSpeakerInitialDialog keysFoundDialog =
            new SetSpeakerInitialDialog( "keysFoundDialog", ( GameSpeaker ) Engine.getComponent( "Narrator" ),
                                         ( GameDialog ) Engine.getComponent( "Narrator Exit Open" ) );

        // We will only run this event if the narrator has already finished his speach.
        keysFoundDialog.addEventCondition( narratorSpeachDone );

        // We also only want this event to run once.
        keysFoundDialog.addEventCondition( new ComponentStatusInRange( "keysFoundActive",
                                                                       Engine.getComponent( "Narrator" ),
                                                                       "dialog changed", 1, 1 ) );

        // This event will trigger the Narrator's final dialog.
        InitiateDialog exitFoundDialog =
            new InitiateDialog( "exitFoundDialog", ( GameDialog ) Engine.getComponent( "Narrator Exit" ) );

        // We need to make sure this dialog only runs once.
        ComponentLacksStatus exitDialogPlayed =
            new ComponentLacksStatus( "exitDialogPlayed", Engine.getComponent( "Narrator Exit" ), "has run" );
        exitFoundDialog.addEventCondition( exitDialogPlayed );

        // We only want to initiate this dialog once the exit is added
        LocationHasExitInDirection exitPathAdded =
            new LocationHasExitInDirection( "exitPathAdded", ( GameLocation ) Engine.getComponent( "Exit Corridor" ),
                                            Engine.direction( "South" ) );
        exitFoundDialog.addEventCondition( exitPathAdded );

        // Here we set up the new description for the Exit Corridor once the final exit path is added
        ChangeComponentDescription newExitCorridorDesc =
            new ChangeComponentDescription( "newExitCorridorDesc", Engine.getComponent( "Exit Corridor" ),
                                            "This is a hallway that leads to the exit chamber in the south." );

        // We only want to update the description once the exit is added
        newExitCorridorDesc.addEventCondition( exitPathAdded );

        // We also check that this event only happens 1 time
        newExitCorridorDesc.addEventCondition( new ComponentLacksStatus( "exitDescUpdated",
                                                                         Engine.getComponent( "Exit Corridor" ),
                                                                         "description changed" ) );

        // This event will trigger the end of the game once the player reaches the Exit Hall
        TriggerEndOfGame ending =
            new TriggerEndOfGame( "ending", "You have found the two keys and made it to the exit. Thank you for playing." );

        ending.addEventCondition( new PawnInLocation( "playerAtEnd", Engine.currentPlayer(),
                                                      ( GameLocation ) Engine.getComponent( "Exit Hall" ) ) );

    }
}
