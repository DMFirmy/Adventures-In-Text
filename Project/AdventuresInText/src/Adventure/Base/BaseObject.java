package Adventure.Base;

import Adventure.*;

import Adventure.API.*;

/**
 * This is the base implementation of the GameObject interface.
 */
public abstract class BaseObject
    extends BaseComponent
    implements GameObject
{

    @SuppressWarnings( "compatibility:4098143126993747575" )
    private static final long serialVersionUID = 1L;

    /**
     * This constructor is used to initialize and set up this object.
     *
     * @param name The string name of this object.
     */
    public BaseObject( String name )
    {
        super( name );
        this.setText( "description", "" );
    }

    /**
     * This constructor is used to initialize and set up this object.
     *
     * @param name The string name of this object.
     * @param description The text that will be shown as the description for this object.
     */
    public BaseObject( String name, String description )
    {
        super( name );
        this.setText( "description", description );
    }

    /**
     * This method will add a description for this GameObject component to the output.
     */
    public void describe()
    {
        this.incrementStatus( "described" );
        
        if ( !this.getText( "description" ).equals( "" ) )
        {
            IO.add( this.getText( "description" ) + "\n" );
        }
        else
        {
            IO.add( "It seems like an ordanary " + this.getName() + ".\n" );
        }
    }
}
