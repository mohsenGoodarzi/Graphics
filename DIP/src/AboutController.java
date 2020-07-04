
/*
 * File-name: AboutController.java 
 * Version number: 0.1.0
 * Creation date: 03/03/2019
 * Last modification date: 04/07/2020 
 * Author’s name: Mohsen Goodarzi
 * Copyright: Mohsen Goodarzi  
 * Purpose of the program: Educational 
 */
import java.awt.ScrollPane;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AboutController implements Initializable {

	@FXML
	private Pane aboutForm;

	


	@FXML
	private void AboutForm_OnMouseClicked(Event e) {
		aboutForm.getScene().getWindow().hide();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		aboutForm.setOnMouseClicked(e -> {
			AboutForm_OnMouseClicked(e);

		});
	}

}
