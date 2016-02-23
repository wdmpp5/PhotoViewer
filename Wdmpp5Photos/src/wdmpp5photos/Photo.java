/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdmpp5photos;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author william
 */
class Photo 
{
    //class fields
    private String url;
    private String title;
    private String description;
    private float latitude;
    private float longitude;
    private SimpleDateFormat date;
    
    //create constructor for photo class
    public Photo( String url, String title, String description, float latitude, float longitude, SimpleDateFormat date )
    {
        this.url = url;
        this.title = title;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.date = date;
    } //end constructor method
    
    //begin method getUrl
    public String getUrl()
    {
        return this.url;
    } //end method getUrl
    
    //begin method getTitle
    public String getTitle()
    {
        return this.title;
    } //end method getTitle
    
    //begin method getDescription
    public String getDescription()
    {
        return this.description;
    } //end method getDescription
    
    //begin method getLatitude
    public float getLatitude()
    {
        return this.latitude;
    } //end method getLatitude
    
    //begin method getLongitude
    public float getLongitude()
    {
        return this.longitude;
    } //end method getLongitude
    
    //begin method getDate
    public SimpleDateFormat getDate()
    {
        return this.date;
    } //end method getDate
} //end photo class