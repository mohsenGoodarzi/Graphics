/*
 * File-name: Main.java 
 * Version number: 0.1.0
 * Creation date: 01/03/2019
 * Last modification date: 04/07/2020 
 * Author’s name: Mohsen Goodarzi
 * Copyright: Mohsen Goodarzi  
 * Purpose of the program: Educational 
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class Main extends Application{
	// Constants for the main window
		private static final int MAIN_WINDOW_WIDTH = 650;
		private static final int MAIN_WINDOW_HEIGHT = 420;
		private static final String WINDOW_TITLE = "Image Editor";

		// Constants for the edit window.
		// We make them public and allow the controllers to access these,
		// so that we keep all window constants in one place.
		public static final int EDIT_WINDOW_WIDTH = 400;
		public static final int EDIT_WINDOW_HEIGHT = 250;
		public static final String EDIT_WINDOW_TITLE = "Edit Europe";
        private static FXMLLoader fxml=null;

        // Entry point of the programme
	public static void main(String[] args) {
		launch(args);

	}
	
	public static FXMLLoader getMainFxml() {
	return fxml;	
	} 
	
	@Override
	public void start(Stage primaryStage) throws Exception {

		try {
			
			fxml = new  FXMLLoader(getClass().getResource("FXMLFiles\\MainForm.fxml"));
			ScrollPane root = (ScrollPane)fxml.load();
			Scene scene = new Scene(root, MAIN_WINDOW_WIDTH, MAIN_WINDOW_HEIGHT);
			
			primaryStage.setScene(scene);
			primaryStage.setTitle(WINDOW_TITLE);
			primaryStage.show();
			
		} catch(Exception e) {
		e.printStackTrace();
		}
	
	}

}
