import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AboutController implements Initializable{
	
	@FXML private Pane AboutForm;
	private Stage stage;
	public AboutController(MainController mainForm) {
		
		stage = (Stage) AboutForm.getScene().getWindow();
		
	}
	@FXML
	private void AboutForm_OnMouseClick(Event e){
		 System.out.println("AboutForm_OnMouseClick");
		stage.close();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		 System.out.println("AboutForm_OnMouseClick");
			AboutForm.setOnMouseClicked(e-> {
				AboutForm_OnMouseClick(e);
			});
		
	}
}
