import java.io.FileNotFoundException;



import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.Window;

public class GammaController {
	
	@FXML private TextField gammaTextField;
	@FXML private Button okButton;
	@FXML private Button cancelButton;
	@FXML private Button applyButton;
	private  Image image;
	private MainController parent;
	private static final int MAIN_WINDOW_WIDTH = 350;
	private static final int MAIN_WINDOW_HEIGHT = 120;
	private static final String WINDOW_TITLE = "Gamma Correction";
    private ImageProcessing imageProcessing;
	private static MainController formMain;
    
	public GammaController(Image image, MainController formMain) {
    	this.image=image;
    	this.parent=formMain; 
    	imageProcessing = new ImageProcessing(image);
    }
    public void initialize(Stage primaryStage) {
			
		applyButton.setOnAction(e->{applyButton_OnClick(e);});
		okButton.setOnAction(e->{okButton_OnClick(e);});
		cancelButton.setOnAction(e->{cancelButton_OnClick(e);});
		
	}
    @FXML
	private void applyButton_OnClick(Event e){
		
			
		
		imageProcessing.setGamma(Double.parseDouble(gammaTextField.getText()));
    	imageProcessing.calcGamma();
    	imageProcessing.applyGamma();
    	parent.setImage(imageProcessing.getProcessedImage());
    	
    	
	}
	@FXML
	private void cancelButton_OnClick(Event e) {
		parent.setImage(imageProcessing.getOrginalImage());
		((Stage)cancelButton.getScene().getWindow()).close();
	}
	@FXML
	private void okButton_OnClick(Event e){
		parent.setImage(imageProcessing.getProcessedImage());
		((Stage)cancelButton.getScene().getWindow()).close();
	}
	
}
