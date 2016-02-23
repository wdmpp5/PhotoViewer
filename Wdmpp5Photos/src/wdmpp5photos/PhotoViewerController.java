/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdmpp5photos;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 *
 * @author william
 */
public class PhotoViewerController implements Initializable
{
    //class fields that are not part of the UI
    private PhotoManager photoManager;
    ArrayList<Photo> photos; //an array of photos
    ObservableList<String> photoListItems;
    private Stage stage;
    private WebEngine webEngine;
    
    //class fields pulled from the UI
    @FXML
    private ListView photosListView;
    
    @FXML
    private WebView webView;
    
    @Override
    public void initialize( URL url, ResourceBundle rb ) 
    {
        // TODO
    }
    
    //cretae a ready method that will initialize the stage with relevant photos
    public void ready( Stage stage ) throws Exception
    {
        //get the stage from the main class
        this.stage = stage;
        
        //get the web engine from the web view
        webEngine = webView.getEngine();
        
        //get the photos
        photoListItems = FXCollections.observableArrayList();
        photosListView.getSelectionModel().selectedIndexProperty().addListener( ( ObservableValue< ? extends Number > ov, Number old_val, Number new_val ) ->
        {
            if( ( int )new_val < 0 || ( int ) new_val > ( photos.size() - 1 ) )
            {
                return;
            }
            Photo photo = photos.get( ( int )new_val );
            webEngine.load( photo.getUrl() );
        });
        
        //load the photos from the url
        loadPhotos();
    } //end method ready
    
    //begin method loadPhotos
    private void loadPhotos() throws Exception
    {
        //attempt to load photos from the photo manager
        try
        {
            photoManager.load();
        }
        catch( Exception ex )
        {
            throw ex;
        }
        
        //get the photos parsed from the manager; clear the current photo list
        photos = photoManager.getPhotos();
        photoListItems.clear();
        
        //set the title for the list view to the title of the photo
        for( Photo photo : photos )
        {
            photoListItems.add( photo.getTitle() );
        } //end for loop
        
        photosListView.setItems( photoListItems );
        if( photos.size() > 0 )
        {
            photosListView.getSelectionModel().select( 0 );
            photosListView.getFocusModel().focus( 0 );
            photosListView.scrollTo( 0 );
        } //end if
    } //end method loadPhotos
} //end photoViewerController class