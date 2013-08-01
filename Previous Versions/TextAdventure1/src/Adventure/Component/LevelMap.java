package Adventure.Component;

import Adventure.API.*;

import Adventure.Core.*;

/**
 *
 * @author Firmy
 */
public class LevelMap
	extends BaseObject
	implements GameLevelMap
{
	/**
	 *
	 */
	public LevelMap()
	{
		super();
	}

  /**
   *
   */
	public void buildLevel()
	{
		// First, we will initialize our default rules
		Level.rules().add(new RuleSet());

		// Now we can initialize all of our locations and set their names.
		Location entryHall = new Location();
		entryHall.name("Entry Hall");
		Location mainRoom = new Location();
		mainRoom.name("Main Room");
		Location westWing = new Location();
		westWing.name("West Wing");
		Location hiddenRoom = new Location();
		hiddenRoom.name("Hidden Room");
		Location eastWing = new Location();
		eastWing.name("East Wing");
		Location caveMouth = new Location();
		caveMouth.name("Cave Mouth");
		Location southHall = new Location();
		southHall.name("South Hall");
		Location exitCorridor = new Location();
		exitCorridor.name("Exit Corridor");
		Location exitHall = new Location();
		exitHall.name("Exit Hall");

		// Now that all of our rooms are created, we can add the descriptive text.
		entryHall.description("Though you could swear that the way that you entered this building was" +
        " just north of this room, the wall on the north side of the room is completely bare" +
        " and featureless. This stone chamber has a plush red carpet, but no furnishings at all." +
        " An archway on the south wall leads out into what appears to be a wide hallway.");

	  mainRoom.description("This wide and tall hallway runs from west to east, branching off to the" +
        " north in the middle. Though there is no apparent sources of light, you can see well" +
        " even though there are no windows in this area.");
        
    westWing.description("This room appears to be a bedchamber of some kind, though it doesn't" +
        " have any furnishings. There is a slight echo as your footsteps fall on the stone floor," +
        " and you feel a chill draft coming from somewhere.");
    
    hiddenRoom.description("This small chamber is more like an over-sized closet. It can't be" +
        " much more than 5 feet on all sides. In the back ot this room, a young man no" +
        " more than 16-years-old is reading a book by the light of a candle. When he notices" +
        " you approaching, he looks up and smiles.");
    
	  eastWing.description("This room is very wide, and has the appearance that it might" +
        " have served as a dining chamber at some point in the past. There is really very little" +
        " of note in here now, just a few chairs that look quite uncomfortable and a small" +
        " table along one wall. There is an ornate archway on the wall to the south," +
        " though in the north wall is also a much smaller passageway that is of rough stone.");
    
    caveMouth.description("Given how narrow the corridor leading to this chamber was, it is" +
        " surprisingly large inside. There appears to be another opening obscured behind" +
        " a very large boulder in the back of this chamber, but there is no chance to move" +
        " such a heavy object.");
    
    southHall.description("This room is a mask of shadows. You can barley see the exits to the" +
        "north and off the west wall.");
    
    exitCorridor.description("This room appears to be a hallway that bends off to the south, but as you approach" +
				"the south end of the room, all you find is a completely baren wall.");
    
	  exitHall.description("");
    
		// Here we add the correct exits to each location to complete our map.
		entryHall.addExit("south", mainRoom);
    
		mainRoom.addExit("north", entryHall);
		mainRoom.addExit("west", westWing);
		mainRoom.addExit("east", eastWing);
    
		westWing.addExit("east", mainRoom);
		westWing.addExit("north", hiddenRoom, true);
    
		hiddenRoom.addExit("south", westWing);
    
		eastWing.addExit("north", caveMouth);
		eastWing.addExit("south", southHall);
		eastWing.addExit("west", mainRoom);
    
	  caveMouth.addExit("south", eastWing);
    
		southHall.addExit("west", exitCorridor);
		southHall.addExit("north", eastWing);
    
		exitCorridor.addExit("east", southHall);
    
		exitHall.addExit("north", exitCorridor);

		// Here we initialize the items that will exist in our level map and set their names.
		Item key1 = new Item();
		key1.name("Key 1");
    
		Item key2 = new Item();
		key2.name("Key 2");
    
		Item boulder = new Item();
		boulder.name("Boulder");

		// Now that all of our items are created, we can add the descriptive text.
		key1.description("This is a small, but dingy key. It looks old.");
    
		key2.description("This is a small key. It is very shiny like it has been polished recently.");
    
		boulder.description("This large grey boulder is far too heavy to move. It appears that it is" +
                        "covering an opening, but there is no way that you can reach it.");
    
		// Here we initialize the actors that will appear in our level map and set their names.
		Actor narrator = new Actor();
	  narrator.name("Narrator");
    
		Actor gamer = new Actor();
		gamer.name("Gamer");
    
		Actor sneak = new Actor();
		sneak.name("Sneak");

		// Now that all of our actors are created, we can add the descriptive text.
		narrator.description("");
		gamer.description("");
		sneak.description("");

    // Here we initialize all of our dialogs for conversations and set their names.
    Dialog narratorIntro = new Dialog();
    narratorIntro.name("Narrator Introduction");    
		Dialog narratorReady = new Dialog();
		narratorReady.name("Narrator Ready Response");    
		Dialog narratorNotReady = new Dialog();
		narratorNotReady.name("Narrator Not Ready Response");    
		Dialog narratorIntroEnding = new Dialog();
		narratorIntroEnding.name("Narrator Introduction Ending");
		Dialog narratorExit = new Dialog();
		narratorExit.name("Narrator Exit");
		Dialog narratorExitOpen = new Dialog();
		narratorExitOpen.name("Narrator Exit Open");

		Dialog hiddenRoomFound = new Dialog();
		hiddenRoomFound.name("Hidden Room Found");
    
		Dialog gamerIntro = new Dialog();
		gamerIntro.name("Gamer Introduction");
		Dialog gamerPlay = new Dialog();
		gamerPlay.name("Gamer Play");
		Dialog gamerDefeat = new Dialog();
		gamerDefeat.name("Gamer Defeat");
		Dialog gamerDefeated = new Dialog();
		gamerDefeated.name("Gamer Defeated");

		Dialog sneakIntro = new Dialog();
		sneakIntro.name("Sneak Intro");
		Dialog sneakFound = new Dialog();
		sneakFound.name("Sneak Found");
    
    // Now that all of our dialogs are initialized, we can add the text to them.
    narratorIntro.dialogText("Welcome to you player. I am the Narrator, and it is my job to welcome" +
        " you to \"Adventures In Text\", a game designed by that old wizard DMFirmy...\n\n" +
        "Because I am only here to provide you with a very brief introduction to what is" +
        " possible in this game, the task I have for you is simple. Are you ready to begin?");
	  narratorReady.dialogText("Hidden in this place are two keys. Find them and bring them to me," +
        " I will be waiting in the chamber that lies south of here. Once you have found them, I" +
        " will unlock the exit and allow you to leave this place.");
		narratorNotReady.dialogText("Oh, I am pretty sure you are going to be just fine. All you" +
        " need to do is find the two keys that are lost in this building and bring them to me in" +
        " the exit cooridor to the south.\n\nIf you are having trouble finding the keys, remember" +
        " to \"examine\" your surroundings, to explore everywhere, and to \"speak\" with anyone" +
        " that you meet. I have a couple of assistants around here, and they might be able to" +
        " help you.");
		narratorIntroEnding.dialogText("Don't worry, you'll do fine. Just come and see me near the exit" +
        " when you have found those keys. I know it will be an easy task for one of your talents.\n\n" +
        "With that, the Narrator waves his hand and disapears in a puff of smoke. You gag for a moment" +
        " before you realize that you are now alone. Looking around, you see an archway to the south" +
        " that leads to a large hallway.");
    
    narratorExit.dialogText("Congratulations, you have completed your assigned task. Now that you have" +
        " brought me the keys to the exit, I can use them to open the passage to the south.\n\n" +
        "The Narrator waves his hand gently, and the wall to the south fades as though it was never" +
        "there at all. You can now tell that you are in a hallway that stretches about 10 feet to the" +
        " south and the west.");
    
		narratorExitOpen.dialogText("Head to the south, you are free to leave whenever you choose.");

	  hiddenRoomFound.dialogText("Searching the room, you notice the source of the draft is coming from" +
        " the north wall. You move closer to incestigate, and realize that there is a very faint outline" +
        " of a door. After searching around for a bit, you find a cleverly hidden catch, and the door swings open.");
    
		gamerIntro.dialogText("I a, so board, but I got into trouble, so I decided to hide " +
        " out here for awhile. Want to play a game with me to pass some time?");
		gamerPlay.dialogText("Let's play a game of rock/paper/scissors... best of three wins. Ready?");
		gamerDefeat.dialogText("I can't believe you beat me. As a reward, I will give you this shiny key I found.\n\n" +
        "He hands you the key and you place it into your pocket.");
		gamerDefeated.dialogText("I can't believe you beat me...");
    
    sneakIntro.dialogText("Darn, you caught me.");
    sneakFound.dialogText("Darn, you caught me... You can have your key" +
              " back. I just got bored and wanted to mess with ya... no hard fellings right?");

    // Conversations require connections between dialogs, so we set up the branches here.
    narratorIntro.addBranch(narratorReady, "Yes, I am ready. What should I do now?");
		narratorIntro.addBranch(narratorNotReady, "I am not really sure...");
		narratorReady.addBranch(narratorIntroEnding, "Continue...");
		narratorNotReady.addBranch(narratorIntroEnding, "Continue...");

		gamerIntro.addBranch(gamerPlay, "Sure, sounds fun.");

    // Now that all of the conversations are set up, we assign them to the correct actors.
    narrator.initialDialog(narratorIntro);
    gamer.initialDialog(gamerIntro);
		sneak.initialDialog(sneakIntro);


		// Some things are hidden, so we need to set that here.
		sneak.hidden(true);
    key2.hidden(true);

    // The boulder is too big to move, so we must mark that too.
    boulder.movable(false);

		// Here we place all of the actors in the correct locations.
		narrator.location(exitCorridor);

    // We move the narrator here to make it easier to return them to the exitCooridor later.
		narrator.location(entryHall);
		gamer.location(hiddenRoom);
		sneak.location(southHall);

    // Here we set all the items where they belong.
    hiddenRoom.items().add(key1);
		eastWing.items().add(key2);
    caveMouth.items().add(boulder);

    // With the items and actors in place, the actors can pick up the things they are to hold.
    gamer.pickUpItem("Key 1");

		// Now that everything is set up, we place the player in the level map.
		Player.location(entryHall, false);

    // Lastly, we apply the game rules once so that the game initial conversation begins.
    Engine.applyRules();
	}
}
