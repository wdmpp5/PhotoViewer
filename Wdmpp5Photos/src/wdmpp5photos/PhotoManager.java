/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdmpp5photos;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.json.simple.*;

/**
 *
 * @author william
 */
public class PhotoManager 
{
    //class fields
    private final String urlString = "http://dalemusser.net/data/nocaltrip/photos.json"; //url to Prof. Musser's photos
    private URL url;
    private ArrayList<Photo> photos;
    
    //create constructor for class
    public PhotoManager()
    {
        photos = new ArrayList<>();
    } //end constructor method
    
    //create method to load photos from URL
    public void load() throws Exception
    {
        //attempt to create a URL from the given URL string
        try
        {
            url = new URL( urlString );
        }
        catch( MalformedURLException muex )
        {
            throw muex;
        }
        
        //attempt to parse the json from the URL
        String jsonString = "";
        try
        {
            //create a reader to read in the json
            BufferedReader in = new BufferedReader( new InputStreamReader( url.openStream() ) );
            
            //loop through the json until you reach the end
            String inputLine;
            while( ( inputLine = in.readLine() ) != null )
            {
                jsonString += inputLine;
            }
            
            //close the reader
            in.close();
        }
        catch( Exception ex )
        {
            throw ex;
        }
        
        //attempt to parse the json input
        try
        {
            parseJsonPhotos( jsonString );
        }
        catch( Exception ex )
        {
            throw ex;
        }
    } //end method load
    
    //create method to parse the json input from load
    private void parseJsonPhotos( String jsonString ) throws Exception
    {
        //start with a clean set of photos
        photos.clear();
        
        //if the jsonString is empty, do not attempt to parse
        if( jsonString == null || "".equals(jsonString) )
        {
            return;
        } //end if
        
        //attempt to create a json object out of the parsed input
        JSONObject jsonObj;
        try
        {
            jsonObj = ( JSONObject )JSONValue.parse( jsonString );
        }
        catch( Exception ex )
        {
            throw ex;
        }
        
        //if the json object was not create successfully, don't go any further
        if( jsonObj == null )
        {
            return;
        }
        
        //get the status of the json that was parsed
        String status = "";
        try
        {
            status = ( String )jsonObj.get( "status" );
        }
        catch( Exception ex )
        {
            throw ex;
        }
        
        //if the status is not good, don't go any further
        if( status == null || !status.equals( "OK" ) )
        {
            return;
        }
        
        //retrieve the response from the json object
        JSONObject response;
        try
        {
            response = ( JSONObject )jsonObj.get( "response" );
        }
        catch( Exception ex )
        {
            throw ex;
        }
        
        //create an array of json documents from the parsed json objects
        JSONArray docs;
        try
        {
            docs = ( JSONArray )response.get( "docs" );
        }
        catch( Exception ex )
        {
            throw ex;
        }
        
        //parse through the documents
        for( Object doc : docs )
        {
            try
            {
                //grab a photo object
                JSONObject jsonPhoto = ( JSONObject )doc;
                
                //set relevant fields from the json document for photo construction
                String url = ( String )jsonPhoto.getOrDefault( "image", "" );
                String title = ( String )jsonPhoto.getOrDefault( "title", "" );
                String description = ( String )jsonPhoto.getOrDefault( "description", "" );
                String latitudeString = ( String )jsonPhoto.getOrDefault( "latitude", "" );
                String longitudeString = ( String )jsonPhoto.getOrDefault( "longitude", "" );
                String dateString = ( String )jsonPhoto.getOrDefault( "date", "" );
                
                //convert fields to their correct types
                Float latitude = Float.valueOf( latitudeString );
                Float longitude = Float.valueOf( longitudeString );
                SimpleDateFormat date = new SimpleDateFormat( dateString );
                
                
                //create a new photo and add it to the array list
                Photo photo = new Photo( url, title, description, latitude, longitude, date );
                photos.add( photo );            
            }
            catch( Exception ex )
            {
                throw ex;
            }
        }
    } //end method parsJsonPhotos
    
    //begin method getPhotos
    public ArrayList<Photo> getPhotos()
    {
        return photos;
    } //end method getPhotos
    
    //begin method getNumPhotos
    public int getNumPhotos()
    {
        return photos.size();
    } //end method getNumPhotos
} //end class PhotoManager