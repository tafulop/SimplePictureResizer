package com.tamas.fulop.simplepictureresizer;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import net.coobird.thumbnailator.Thumbnails;

public class FXMLController implements Initializable {
   
    private Stage browser = new Stage();
    
    @FXML
    private Label label;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        
        Integer resized =  resizeImages(selectFiles());
        
        label.setText("Átméretezve: " + resized + " darab kép.");
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        // TODO
    }    
    
    /**
     * Shows the file selection window and loads the selected files.
     * @return 
     */
    private List<File> selectFiles(){
        
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Valaszd ki a kepeket!");
        fileChooser.getExtensionFilters().addAll(
            new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif", "*.bmp"));
         
        
        List<File> selectedFiles = fileChooser.showOpenMultipleDialog(browser);
       
        return selectedFiles;  
   
    }
    
    /**
     * Resizes the given image files and saves them in a child folder, named "atmeretezett"
     * 
     * @param images 
     */
    private Integer resizeImages(List<File> images){
        
        Integer i = 0;
        
        for(File originalImage : images){
                   
            File resizedFolder = new File(originalImage.getParent()  + File.separator + "atmeretezett");
            File resizedImage = new File(resizedFolder.getAbsolutePath() +  File.separator + originalImage.getName());
            
            resizedFolder.mkdirs();
            
            try {
                Thumbnails.of(originalImage)
                        .size(1024, 768)
                        .toFile(resizedImage);
                i++;
            } catch (IOException ex) {
                Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, "File can not be saved.", ex);
            }
        
        
        }
        
        return i;
         
    }
    
}
