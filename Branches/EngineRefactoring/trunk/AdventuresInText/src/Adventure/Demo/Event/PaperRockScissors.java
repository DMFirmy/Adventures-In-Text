package Adventure.Demo.Event;

import Adventure.*;

import Adventure.API.*;

import Adventure.Base.*;

/**
 * This event, when triggered, will begin a simple game of Paper/Rock/Scissors. The rules are that the first to
 * 3 wins is declared the winner. Paper beats rock, rock beats scissors, and scissors beats paper. Draw matches
 * where both the player and the computer choose the same thing are not counted.
 */
public class PaperRockScissors
    extends BaseEvent
{
    @SuppressWarnings( "compatibility:6257898234781706337" )
    private static final long serialVersionUID = 1L;

    /**
     * This constructor will build and set up the event.
     *
     * @param name The GameComponent name to be assigned to this event.
     */
    public PaperRockScissors( String name )
    {
        super( name );
    }

    /**
     * This event contains all of the processing for the Paper/Rock/Scissors game.
     * 
     * @param operation Not used for this event.
     */
    public void processEvent( Operation operation )
    {
        GamePlayer player = Engine.getCurrentPlayer();
        GameActor gamer = ( GameActor ) Engine.getComponentUtility().getComponent( "Gamer" );
        
        ( Engine.getComponentUtility().getComponent( "Gamer Play" ) ).setStatus( "has run", 0 );
        
        if ( player.hasStatus( "PaperRockScissors" ) )
        {
            // First we set up an array for valid selections.
            String[] gameChoices =
            { "Rock", "Paper", "Scissors" };
            // Here we initialize a couple of counters and placeholders.
            int choice = -1;
            int winCount = 0;
            int loseCount = 0;
            
            // This will loop until either the player wins or loses 3 times
            while ( winCount < 3 && loseCount < 3 )
            {
                // First we output the current score and prompt for a choice.
                IO.addLine( "You have won " + winCount + " games, and I have won " + loseCount + "." );
                IO.addLine( "Choose...\n" );
                // We can loop through our array and use the choices as our choice selections.
                for ( int i = 0; i < gameChoices.length; i++ )
                {

                    IO.addLine( ( i + 1 ) + ": " + gameChoices[ i ] );
                }
                // We also add the option to simply quit playing as choice 0
                IO.addLine( "0: End game" );
                // Now that we have created our output, we can display it and get the selection from the input
                IO.display();
                
                try
                {
                    // we use a try block to watch for invalid input.
                    choice = IO.getChoice();
                }
                catch ( Exception e )
                {
                    // If an invalid input is entered, we will set the choice as -1 and display an error message.
                    choice = -1;
                    IO.addLine( "I don't understand what you mean." );
                    // Since our input isn't valid, we continue our loop without further processing.
                    continue;
                }
                
                // If our choice was valid, we can check to see if it was to quit the game.
                if ( choice == 0 )
                {
                    // If so, we remove the status to stop the game.
                    player.removeStatus( "PaperRockScissors" );
                    // Now we describe the current location.
                    player.getLocation().describe();
                    // We are done...
                    return;
                }
                
                // If the game wasn't quit above, we need to check that it is not an invalid number above 3.
                if ( choice > 3 )
                {
                    
                    // If an invalid input is entered, we will set the choice as -1 and display an error message.
                    choice = -1;
                    IO.addLine( "I don't understand what you mean." );
                    // Since our input isn't valid, we continue our loop without further processing.
                    continue;
                }

                // Here we get a random choice from the computer player. 
                int gamerChoice = ( int ) ( Math.random() * 3 ) + 1;
                
                // Now that both players have made valid choices we can add the results to the output.
                IO.addLine( "You chose " + gameChoices[ choice - 1 ] + " and I chose " +
                            gameChoices[ gamerChoice - 1 ] + "." );
                
                // Here we figure out if we have a tie.
                if ( gamerChoice == choice )
                {
                    IO.addLine( "Darn, a tie. Let's go again.\n" );
                }
                // And here we figure out if the player has beat the computer.
                else if ( ( choice == 1 && gamerChoice == 3 ) || ( choice == 2 && gamerChoice == 1 ) ||
                          ( choice == 3 && gamerChoice == 2 ) )
                {
                    IO.addLine( "You win this round.\n" );
                    winCount++;
                }
                // If we get here, the computer must have won the round.
                else
                {
                    IO.addLine( "I win this round.\n" );
                    loseCount++;
                }

                // Now we can check the current win count for the player                
                if ( winCount == 3 )
                {
                    // If the player has 3 wins, the computer is defeated
                    IO.addLine( "I am defeated..." );
                    // We remove the status to stop the game.
                    player.removeStatus( "PaperRockScissors" );
                    // Here we increment the player's as win count.
                    player.incrementStatus( "WonPaperRockScissors" );
                    
                    // TODO: This code needs to me moved outside of this event into thier own events.
                    player.addItem( gamer.getItem( "Key 1" ), gamer );
                    gamer.setInitialDialog( ( GameDialog ) Engine.getComponentUtility().getComponent( "Gamer Defeated" ) );
                    ( ( GameDialog ) Engine.getComponentUtility().getComponent( "Gamer Defeat" ) ).initiate();
                    return;
                }
                else if ( loseCount == 3 )
                {
                    // If the player has lost 3 times, then the computer is the winner.
                    IO.addLine( "You are defeated..." );
                    // Finally, we remove the status to stop the game.
                    player.removeStatus( "PaperRockScissors" );
                }
            }
        }
    }
}
