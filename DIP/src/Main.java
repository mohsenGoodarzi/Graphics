import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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

	public static void main(String[] args) {
		launch(args);

	}

	
	public static FXMLLoader getMainFxml() {
	return fxml;	
	} 
	@Override
	public void start(Stage primaryStage) throws Exception {

		
		//FormMain frm= new FormMain(primaryStage);
		
		try {
			// Load the main scene.
			//ScrollPane root = (ScrollPane)FXMLLoader.load(getClass().getResource("FXMLFiles\\FormMain.fxml"));
			//Scene scene = new Scene(root, MAIN_WINDOW_WIDTH, MAIN_WINDOW_HEIGHT);

			 fxml = new  FXMLLoader(getClass().getResource("FXMLFiles\\MainForm.fxml"));
			ScrollPane root = (ScrollPane)fxml.load();
			Scene scene = new Scene(root, MAIN_WINDOW_WIDTH, MAIN_WINDOW_HEIGHT);
			//primaryStage.initStyle(StageStyle.TRANSPARENT);
			
			// Place the main scene on stage and show it.
			primaryStage.setScene(scene);
			primaryStage.setTitle(WINDOW_TITLE);
			primaryStage.show();
			//FormMain controller =new FormMain();
			//controller=(FormMain)fxml.getController();
			//controller.menuFile.setText("Hello");
			
		} catch(Exception e) {
		e.printStackTrace();
		}
	
	}

}
