package Adventure.Base.Utility;

import Adventure.API.Utility.*;

public abstract class BaseEngineUtility
    implements GameEngineUtility
{
    private String name;
    
    public BaseEngineUtility( String name )
    {
        super();
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }
}
