/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdmpp5photos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author william
 */
public class Wdmpp5Photos extends Application 
{
    @Override
    public void start( Stage stage ) throws Exception 
    {
        //load the PhotoViewer controller into main
        FXMLLoader loader = new FXMLLoader( getClass().getResource( "PhotoViewer.fxml" ) );
        Parent root = ( Parent )loader.load();
        PhotoViewerController controller = ( PhotoViewerController )loader.getController();
        
        //set the scene with the root
        Scene scene = new Scene( root );
        
        //set the stage and show it
        stage.setScene( scene );
        stage.setTitle( "Photo Viewer" );
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main( String[] args ) 
    {
        launch(args);
    }
}