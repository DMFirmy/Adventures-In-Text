package Adventure;

import Adventure.Base.Utility.*;

import java.util.*;

/**
 */
public class SystemIOUtility
    extends BaseIOUtility
{
    @SuppressWarnings( "compatibility:-2143926047598089381" )
    private static final long serialVersionUID = 1L;
    
    // We make this field static so there are no serialization conflicts.
    private static Scanner in;

    /**
     */
    public SystemIOUtility()
    {
        super( "DefaultIOUtility" );
        
        in = new Scanner( System.in );
    }

    // We use nextLine() instead of using nextInt() so we don't have to clear invalid input.

    /**
     * @return
     */
    public int getChoice()
    {
        String input = "";
        int choice = -1;

        input = in.nextLine();
        try
        {
            choice = Integer.parseInt( input );
        }
        catch ( Exception e )
        {
            choice = -1;
            IO.addLine( "I don't understand that selection." );
        }
        return choice;
    }

    /**
     * @return
     */
    public String getInput()
    {
        return in.nextLine();
    }

    /**
     * @param outputBuffer
     */
    public void display( StringBuilder outputBuffer )
    {
        System.out.println( wordWrap( outputBuffer.toString() ) );
    }

    /**
     * @param outputBuffer
     * @param prompt
     */
    public void display( StringBuilder outputBuffer, String prompt )
    {
        this.display( outputBuffer );
        System.out.println( prompt );
    }

    /**
     */
    public void clearScreen()
    {
        int i = 0;
        while ( i < 80 )
        {
            System.out.print( "-" );
            i++;
        }
        for ( i = 0; i < 24; i++ )
        {
            System.out.println();
        }
    }

    /**
     * @param text
     * @return
     */
    public String wordWrap( String text )
    {
        int width = 80;

        String result = "";
        String[] lines = text.split( "\n" );

        for ( String line : lines )
        {
            int lineLength = 0;
            String[] words = line.split( " " );

            for ( String word : words )
            {
                if ( word.length() + lineLength >= width - 1 )
                {
                    result += "\n";
                    lineLength = 0;
                }
                result += word + " ";
                lineLength += word.length() + 1;
            }
            result += "\n";
        }

        return result;
    }

    /**
     * @param text
     * @return
     */
    public String underline( String text )
    {
        String underline = "\n";
        for ( int i = 0; i < text.length(); i++ )
        {
            underline += "-";
        }
        return underline;
    }
}
