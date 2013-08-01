package Adventure;

import Adventure.API.*;

import java.io.*;

import java.net.*;

import java.util.*;
import java.util.jar.*;

/**
 * This class is responsible for loading in GameMap objects from .jar files stored in the "Maps" directory. Though this is
 * working, it should be noted that this feature is not complete, nor is it completely tested. In future updates to the
 * Adventures in Text game, this class will be completely revamped, but for now it is here on an experimental only basis.
 */
public class Loader
{
    private static File gameDirectory;

    private static File mapDirectory;
    
    /**
     * Here we initialize all of the static File objects that we need for keeping track of map locations.
     */
    static
    {
        gameDirectory = new File( System.getProperty( "user.dir" ) );
        
        /**
         * TODO: There needs to be a check here for the existance of any folders that will be used by the game engine for
         *       user content. This includes a folder called "Maps" to store any map files that have been added to the game,
         *       another folder called "Saves" to store saved game files, and any other folders that might become required
         *       for loading in custom content. If these folders are found to exist, the game engine needs to check to see
         *       whether it has appropriate read/write permissions for the folders. If they do not exist, the engine should
         *       attempt to create them. If they do not exist and cannot be created, or if the appropriate permissions are
         *       not available for the game engine, there needs to be some appropriate action taken.
         */
        
        mapDirectory = new File( getPathForFile( "Maps", true ) );
    }

    /**
     * This class is completely static, so we make the constructor private.
     */
    private Loader()
    {
    }

    /**
     * This method will return a list of all the GameMap objects it was able to locate in the .jar files it finds in the
     * "Maps" directory.
     * 
     * @return A list of GameMap objects that can be loaded.
     */
    public static ArrayList<GameMap> loadMaps()
    {
        ArrayList<GameMap> availableMaps = new ArrayList<GameMap>();
        File files[] = mapDirectory.listFiles();

        //Iterate over files in the maps directory
        if ( files != null )
        {
            for ( File file : files )
            {
                if ( file.isFile() && file.getName().endsWith( ".jar" ) )
                {
                    // Now we can load all the levels within the jar file
                    availableMaps.addAll( loadMapsFromJar( file.getAbsolutePath() ) );
                }
                else
                {
                    //skip folders & non-jar files
                    continue;
                }
            }
        }
        return availableMaps;
    }

    /**
     * This method will scan through a .jar file looking for classes to load. If they are GameMap classes, they are loaded
     * and added to a list to be returned.
     * 
     * @param pathToJarFile This is the absolute path to the .jar file to bve scanned
     * @return A list of GameMap objects that were found durring the scan.
     */
    public static ArrayList<GameMap> loadMapsFromJar( String pathToJarFile )
    {
        ArrayList<GameMap> myMaps = new ArrayList<GameMap>();

        JarInputStream jarStream = null;
        JarEntry jarEntry = null;

        try
        {
            jarStream = new JarInputStream( new FileInputStream( pathToJarFile ) );
            jarEntry = jarStream.getNextJarEntry();

            while ( jarEntry != null )
            {
                if ( jarEntry.getName().endsWith( ".class" ) )
                {
                    String classname = jarEntry.getName().replaceAll( "/", "\\." );

                    classname = classname.substring( 0, classname.length() - 6 );

                    if ( classname.contains( "$" ) == false )
                    {
                        URL url = new URL( "jar:file:" + pathToJarFile + "!/" );

                        URLClassLoader ucl = new URLClassLoader( new URL[] { url } );

                        /* This snippet shows how to obtain a complete JarFile object without a stream.
                            URL url = new URL( "jar:file:" + pathToJarFile + "!/" );
                            URLClassLoader ucl = new URLClassLoader( new URL[] { url } );
                            JarURLConnection jucl = ( JarURLConnection ) url.openConnection();
                            JarFile jar = jucl.getJarFile();
                         */

                        // Up to this point, it will be almost the same no matter what Class I am loading
                        try
                        {
                            GameMap myMap = loadMapEntry( ucl, classname );
                            if ( myMap != null )
                            {
                                myMap.setMapPath( pathToJarFile );
                                myMaps.add( myMap );
                            }
                        }
                        catch ( Exception e )
                        {
                            e.printStackTrace();
                        }
                    }
                }
                jarEntry = jarStream.getNextJarEntry();
            }
            jarStream.close();
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
        return myMaps;
    }
    
    /**
     * This method is a helper for loading GameMap objects from a .jar file.
     * 
     * @param ucl This is the URLClassLoader object ti use to load the class.
     * @param classname This is the String name of the class to be loaded.
     * @return This will return a GameMap object if one can be loaded, or null if not.
     */
    private static GameMap loadMapEntry( URLClassLoader ucl, String classname )
    {
        try
        {
            Class c = ucl.loadClass( classname );

            // This if statement demonstrates how to figure out if a loaded file implements an interface
            if ( GameMap.class.isAssignableFrom( c ) )
            {
                GameMap myMap = ( GameMap ) c.newInstance();
                return myMap;
            }
        }
        catch ( Exception e )
        {
            IO.addLine( "There was an error while loading a map from a .jar file. " );
        }
        return null;
    }

    /**
     * This method will take a String filename and get the absolute path for it. It will not work for directories.
     * 
     * @param fileName The String name of the file to be located.
     * @return The String absolute path to the file.
     */
    public static String getPathForFile( String fileName )
    {
        return getPathForFile( fileName, false );
    }

    /**
     * This method will take a String filename and get the absolute path for it.
     * 
     * @param fileName The String name of the file to be located.
     * @param includeDirectories Whether or not to also scan directories.
     * @return The String absolute path to the file.
     */
    public static String getPathForFile( String fileName, boolean includeDirectories )
    {
        File foundFile = getFileByName( fileName, null, includeDirectories );

        if ( foundFile != null )
        {
            return foundFile.getAbsolutePath();
        }
        else
        {
            return "";
        }
    }

    /**
     * This is a helper method that will get the File object for a file with a given name. It looks recursively in all of
     * the subfolders of the working directory.
     * 
     * @param fileName The String name of the file to be found.
     * @param workingDirectory The File object for the directory to be scanned.
     * @param includeDirectories True if you also want to locate directores with the given name, false if you only want to search files.
     * @return The File object for the file with the given name if it is found, or null otherwise.
     */
    private static File getFileByName( String fileName, File workingDirectory, boolean includeDirectories )
    {
        if ( workingDirectory == null )
        {
            workingDirectory = gameDirectory;
        }

        File foundFile = null;
        File[] fileListing = workingDirectory.listFiles();

        if ( fileListing != null )
        {
            for ( File file : fileListing )
            {
                if ( file.isFile() )
                {
                    if ( file.getName().equalsIgnoreCase( fileName ) )
                    {
                        foundFile = file;
                        break;
                    }
                }
                else if ( file.isDirectory() )
                {
                    File temp = null;
                    if ( includeDirectories && file.getName().equalsIgnoreCase( fileName ) )
                    {
                        temp = file;
                    }
                    else
                    {
                        temp = getFileByName( fileName, file, includeDirectories );
                    }

                    if ( temp != null )
                    {
                        foundFile = temp;
                        break;
                    }
                }
            }
        }
        return foundFile;
    }

    /**
     * This method will retrieve the string absolute path for the "Maps" directory.
     *
     * @return The String absolute path for the "Maps" directory.
     */
    public static String getMapPath()
    {
        return mapDirectory.getAbsolutePath();
    }

}
