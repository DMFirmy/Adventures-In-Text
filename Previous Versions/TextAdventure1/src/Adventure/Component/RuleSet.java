package Adventure.Component;

import Adventure.API.*;

import Adventure.Core.*;

/*
 * TODO: Add a rule that uses a method from the Math class
 */
/**
 * This class is used as the rule set for the default demo level. In later implementations
 * of this game, this class will be replaced by a series of GameRuleSet subclasses
 * that will represent various situations that might arise in levels created by a
 * user.
 * @author Firmy
 * @version 1.0
 */
public class RuleSet
    extends BaseObject
    implements GameRuleSet
{
    /**
     *
     */
    public RuleSet()
    {
        super();
    }

    /**
     *
     */
    public void applyRules()
    {
        if ( !Level.dialog( "Narrator Introduction" ).hasRun() )
        {
            Player.speakTo( "Narrator" );
            Player.location().describe();
        }

        if ( Level.dialog( "Narrator Introduction Ending" ).hasRun() &&
             Level.actor( "Narrator" ).location().name().equalsIgnoreCase( "Entry Hall" ) )
        {
            Level.actor( "Narrator" ).location( Level.actor( "Narrator" ).previousLocation() );
        }

        if ( Level.location( "West Wing" ).hasExit( "north" ) && !Player.checkStatus( "Found Hidden Room" ) )
        {
            Player.updateStatus( "Found Hidden Room" );
            Level.location( "West Wing" ).description( "This room appears to be a bedchamber of some kind, though it doesn't" +
                                                       " have any furnishings. There is a slight echo as your footsteps fall on the stone floor." +
                                                       " There is an exit on the north wall, and another to the west." );
            Level.dialog( "Hidden Room Found" ).initiate();
        }

        if ( Level.dialog( "Gamer Play" ).hasRun() && !Player.checkStatus( "Won Game" ) )
        {
            Player.updateStatus( "Playing Game" );
            Level.dialog( "Gamer Play" ).hasRun( false );
        }

        if ( Player.checkStatus( "Playing Game" ) )
        {
            String[] gameChoices =
            { "Rock", "Paper", "Scissors" };
            String choice;
            int choiceInt = -1;
            int winCount = 0;
            int loseCount = 0;

            while ( winCount < 3 && loseCount < 3 )
            {
                Output.add( "You have won " + winCount + " games, and I have won " + loseCount + "." );
                Output.add( "Choose...\n" );
                for ( int i = 0; i < gameChoices.length; i++ )
                {

                    Output.add( ( i + 1 ) + ": " + gameChoices[ i ] );
                }
                Output.add( "0: End game" );
                Output.display( true );

                choice = Engine.getUnprocessedInput();
                try
                {
                    choiceInt = Integer.parseInt( choice );
                }
                catch ( Exception e )
                {
                    choiceInt = -1;
                    Output.add( "I don't understand what you mean." );
                    continue;
                }

                if ( choiceInt == 0 )
                {
                    Player.updateStatus( "Playing Game" );
                    Player.location().describe();
                    return;
                }

                if ( choiceInt > 3 )
                {
                    choiceInt = -1;
                    Output.add( "I don't understand what you mean." );
                    continue;
                }

                int gamerChoice = ( int ) ( Math.random() * 3 ) + 1;

                Output.add( "You chose " + gameChoices[ choiceInt - 1 ] + " and I chose " +
                            gameChoices[ gamerChoice - 1 ] + "." );
                if ( gamerChoice == choiceInt )
                {
                    Output.add( "Darn, a tie. Let's go again.\n" );
                }
                else if ( ( choiceInt == 1 && gamerChoice == 3 ) || ( choiceInt == 2 && gamerChoice == 1 ) ||
                          ( choiceInt == 3 && gamerChoice == 2 ) )
                {
                    Output.add( "You win this round.\n" );
                    winCount++;
                }
                else
                {
                    Output.add( "I win this round.\n" );
                    loseCount++;
                }

                if ( winCount == 3 )
                {
                    Output.add( "I am defeated..." );
                    Player.updateStatus( "Playing Game" );
                    Player.updateStatus( "Won Game" );
                    Level.actor( "Gamer" ).giveItem( "Key 1" );
                    Level.actor( "Gamer" ).initialDialog( Level.dialog( "Gamer Defeated" ) );
                    Level.dialog( "Gamer Defeat" ).initiate();
                    return;
                }
                else if ( loseCount == 3 )
                {
                    Output.add( "You are defeated..." );
                    Player.updateStatus( "Playing Game" );
                }
            }

        }

        if ( Player.location().name().equalsIgnoreCase( "South Hall" ) )
        {
            if ( Player.hasItem( "Key 2" ) && Level.actor( "Sneak" ).hidden() )
            {
                Player.giveItem( "Key 2", Level.actor( "Sneak" ) );
            }
            else if ( !Level.actor( "Sneak" ).hidden() && Level.actor( "Sneak" ).hasItem( "Key 2" ) )
            {
                Level.dialog( "Sneak Found" ).initiate();
                Level.actor( "Sneak" ).giveItem( "Key 2" );
            }
        }

        if ( Player.location().name().equalsIgnoreCase( "Exit Corridor" ) )
        {
            if ( Level.actor( "Narrator" ).location().name().equalsIgnoreCase( "Exit Corridor" ) )
            {
                if ( !Level.location( "Exit Corridor" ).hasExit( "south" ) )
                {
                    if ( Player.hasItem( "Key 1" ) && Player.hasItem( "Key 2" ) )
                    {
                        //TODO: Add code to initiate final conversation here
                        Level.location( "Exit Corridor" ).addExit( "south", Level.location( "Exit Hall" ) );
                        Level.location( "Exit Corridor" ).description( "This is a hallway that leads to the exit chamber in the south." );
                    }
                }
            }
        }

        if ( Player.location().name().equalsIgnoreCase( "Exit Hall" ) )
        {
            Engine.endGame( "You have found the two keys and made it to the exit. Thank you for playing." );
        }
    }
}
