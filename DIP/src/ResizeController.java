import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

public class ResizeController {

	@FXML private ResourceBundle resources;
	@FXML private URL location;
	@FXML private ChoiceBox<?> CmbMaesure;
	@FXML private Button applyBotton;
	@FXML private Button okButton;
	@FXML private TextField TextBoxHeight;
	@FXML private CheckBox CheckBoxResizeBoth;
	@FXML private TextField TextBoxWidth;
	private  Image image;
	private MainController parent;
    private ImageProcessing imageProcessing;

	public ResizeController(Image image, MainController formMain) {
    	this.image=image;
    	this.parent=formMain; 
    	imageProcessing = new ImageProcessing(image);
    }
	@FXML
	void okButton_OnClick(ActionEvent event) {

	}

	@FXML
	void applyBotton_OnClick(ActionEvent event) {

	}

	@FXML
	void initialize() {
		assert CmbMaesure != null : "fx:id=\"CmbMaesure\" was not injected: check your FXML file 'ResizeScene.fxml'.";
		assert applyBotton != null : "fx:id=\"applyBotton\" was not injected: check your FXML file 'ResizeScene.fxml'.";
		assert okButton != null : "fx:id=\"okButton\" was not injected: check your FXML file 'ResizeScene.fxml'.";
		assert TextBoxHeight != null : "fx:id=\"TextBoxHeight\" was not injected: check your FXML file 'ResizeScene.fxml'.";
		assert CheckBoxResizeBoth != null : "fx:id=\"CheckBoxResizeBoth\" was not injected: check your FXML file 'ResizeScene.fxml'.";
		assert TextBoxWidth != null : "fx:id=\"TextBoxWidth\" was not injected: check your FXML file 'ResizeScene.fxml'.";

	}
}
