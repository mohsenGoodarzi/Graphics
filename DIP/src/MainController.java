
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import FilterCollection.FilterCollection3By3;
import FilterCollection.FilterCollection3By3.GusianBlur;
import FilterCollection.FilterCollection5By5;
import FilterCollection.IFilter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

public class MainController implements Initializable {
	@FXML
	private ScrollPane mainPane;
	@FXML
	private MenuBar menu;
	@FXML
	private Menu menuFile;
	@FXML
	private MenuItem menuFileOpen;
	@FXML
	private MenuItem menuFileSave;
	@FXML
	private MenuItem menuFileSaveAs;
	@FXML
	private MenuItem menuFileClose;
	@FXML
	private Menu menuImage;
	@FXML
	private MenuItem menuImageGamma;
	@FXML
	private MenuItem menuImageContrast;
	@FXML
	private MenuItem menuImageGrayscale;
	@FXML
	private MenuItem menuImageHistogram;
	@FXML
	private MenuItem menuImageHistogramEqualizaion;
	@FXML
	private MenuItem menuImageCrossCorrelation;
	@FXML
	private MenuItem menuImageInvert;
	@FXML
	private MenuItem menuImageBlurGussianBlur;
	@FXML
	private MenuItem menuImageEdgeDetectionPrewitt;
	@FXML
	private MenuItem menuImageEdgeDetectionSobel;
	
	
	
	
	//<MenuItem fx:id="menuImageEdgeDetectionSecondDerivation" mnemonicParsing="false" onAction="#menuImageEdgeDetectionSecondDerivation_OnClick" text="Edge Detection Second Derivation" />
	//private MenuItem menuImageEdgeDetectionSecondDerivation;
	
	@FXML
	private MenuItem menuImageDitheringThresholding;
	@FXML
	private MenuItem menuImageDitheringErrorDiffusion;
	@FXML
	private MenuItem menuImageDitheringErrorDiffusionFloyd;
	
	//private MenuItem menuImageDitheringHalftoning;
	
	//private MenuItem menuImageDitheringHalftoningPatterns;
	
	//private MenuItem menuImageDitheringPatternDither;
	
	//private MenuItem menuImageDitheringOrderedDither;
	@FXML
	private Menu menuHelp;
	@FXML
	private MenuItem menuHelp_About;
	@FXML
	private ImageView mainImageView;
	@FXML
	private Image image;
	private ImageProcessing imageprocessing;
	private static final int MAIN_WINDOW_WIDTH = 150;
	private static final String WINDOW_TITLE = "Image Editor";

	public Image getImage() {

		return image;
	}

	public void setImage(Image image) {

		this.image = image;
		mainImageView.setImage(this.image);
	}

	@FXML
	private void menuImageGamma_OnClick(Event event) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLFiles\\GammaForm.fxml"));
		ScrollPane root = null;
		try {
			fxmlLoader.setControllerFactory(c -> {
				return new GammaController(image, this);
			});
			root = (ScrollPane) fxmlLoader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scene nscene = new Scene(root);

		Stage tStage = new Stage();

		tStage.setScene(nscene);
		// GammaController formGamma = fxmlLoader.<GammaController>getController();
		tStage.setAlwaysOnTop(true);
		tStage.setResizable(false);

		tStage.show();

		// Using a flow pane

	}

	@FXML
	private void menuFileOpen_OnClick(Event e) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		File selectedFile = fileChooser.showOpenDialog(menu.getScene().getWindow());
		image = null;
		if (selectedFile != null) {
			try {
				image = new Image(new FileInputStream(selectedFile.getAbsolutePath()));
				mainImageView.setImage(image);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	@FXML
	private void menuFileSave_OnClick(Event e) {
	}

	@FXML
	private void menuFileSaveAs_OnClick(Event e) {
	}

	@FXML
	private void menuFileClose_OnClick(Event e) {
		System.exit(-1);
	}

	@FXML
	private void menuHelp_About_OnClick(Event e) {
	}

	@FXML
	private void menuImageContrast_OnClick(Event e) {
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLFiles\\ContrastForm.fxml"));
		ScrollPane root = null;
		try {
			fxmlLoader.setControllerFactory(c -> {
				return new ContrastController(image, this);
			});
			root = (ScrollPane) fxmlLoader.load();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Scene nscene = new Scene(root);
		Stage tStage = new Stage();
		tStage.setScene(nscene);
		tStage.setAlwaysOnTop(true);
		tStage.setResizable(false);

		tStage.show();

	}

	@FXML
	private void menuImageGrayscale_OnClick(Event e) {

		imageprocessing = new ImageProcessing(image);
		imageprocessing.applyGrayScale();
		mainImageView.setImage(imageprocessing.getProcessedImage());
		this.image = imageprocessing.getProcessedImage();

	}

	@FXML
	private void menuImageInvert_OnKlick(Event e) {
		System.out.println("menuImageInvert_OnKlick");
	}

	@FXML
	private void menuImageHistogram_OnClick(Event e) {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLFiles\\HistogramForm.fxml"));
		ScrollPane root = null;
		try {

			fxmlLoader.setControllerFactory(a -> {
				return new HistogramController(image, this);
			});
			root = (ScrollPane) fxmlLoader.load();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		Scene nscene = new Scene(root);
		Stage tStage = new Stage();
		tStage.setScene(nscene);
		tStage.setAlwaysOnTop(true);
		tStage.setResizable(false);
		tStage.show();
	}

	@FXML
	private void menuImageHistogramEqualizaion_OnClick(Event e) {
		imageprocessing = new ImageProcessing(image);
		// imageprocessing.calcEqualization();
		imageprocessing.applyEqualization();
		mainImageView.setImage(imageprocessing.getProcessedImage());
		image = imageprocessing.getProcessedImage();
	}

	@FXML
	private void menuImageCrossCorrelation_OnClick(Event e) {
		imageprocessing = new ImageProcessing(image);
		IFilter crossCorolationFilter = new FilterCollection5By5.CrossCorrelation();
		imageprocessing.applyFilter(crossCorolationFilter);
		mainImageView.setImage(imageprocessing.getProcessedImage());
		image = imageprocessing.getProcessedImage();
	}

	@FXML
	private void menuImageEdgeDetectionPrewitt_OnClick(Event e) {
		
		imageprocessing = new ImageProcessing(image);
		FilterCollection.FilterCollection3By3.edgeDetectionV VerticalFilter = new FilterCollection3By3.edgeDetectionV();
		FilterCollection.FilterCollection3By3.edgeDetectionH horizontalFilter = new FilterCollection3By3.edgeDetectionH();
		int thresholdPoint = 20;
		imageprocessing.applyEdgeDetection(horizontalFilter, VerticalFilter, thresholdPoint);
		mainImageView.setImage(imageprocessing.getProcessedImage());
		image = imageprocessing.getProcessedImage();
	}

	@FXML
	private void menuImageEdgeDetectionSobel_OnClick(Event e) {
		
		imageprocessing = new ImageProcessing(image);
		FilterCollection.FilterCollection3By3.edgeDetectionSobelV VerticalFilter = new FilterCollection3By3.edgeDetectionSobelV();
		FilterCollection.FilterCollection3By3.edgeDetectionSobelH horizontalFilter = new FilterCollection3By3.edgeDetectionSobelH();
		int thresholdPoint = 20;
		imageprocessing.applyEdgeDetection(horizontalFilter, VerticalFilter, thresholdPoint);
		mainImageView.setImage(imageprocessing.getProcessedImage());
		image = imageprocessing.getProcessedImage();
	}


	@FXML
	private void menuImageDitheringThresholding_OnClick(Event e) {

		imageprocessing = new ImageProcessing(image);
		imageprocessing.applyThreshold();
		mainImageView.setImage(imageprocessing.getProcessedImage());
		image = imageprocessing.getProcessedImage();
	}

	@FXML
	private void menuImageDitheringErrorDiffusion_OnClick(Event e) {

		imageprocessing = new ImageProcessing(image);
		imageprocessing.applyErrorDiffusion();
		mainImageView.setImage(imageprocessing.getProcessedImage());
		image = imageprocessing.getProcessedImage();
	}

	@FXML
	private void menuImageDitheringErrorDiffusionFloyd_OnClick(Event e) {
		imageprocessing = new ImageProcessing(image);
		imageprocessing.applyDiffusionDitheringFloyd();
		mainImageView.setImage(imageprocessing.getProcessedImage());
		image = imageprocessing.getProcessedImage();

	}

	

	/**
	 * Handle the edit button. This will display a window allowing the user to edit
	 * the selected country. After the edit is complete, the displayed list will be
	 * updated.
	 */
	@FXML
	private void handleEditButtonAction() {
	}

	
	@FXML
	private void menuImageBlurGussianBlur_OnClick(Event e){
		
		
		imageprocessing = new ImageProcessing(image);

		//IFilter embossingFilter = new  FilterCollection3By3.GusianBlur();
		IFilter embossingFilter = new  FilterCollection5By5.EdgeDetectionSecondDerivation();
		imageprocessing.applyFilter(embossingFilter);
		mainImageView.setImage(imageprocessing.getProcessedImage());
		image = imageprocessing.getProcessedImage();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		menuFileOpen.setOnAction(e -> {
			menuFileOpen_OnClick(e);
		});
		menuImageGamma.setOnAction(e -> {
			menuImageGamma_OnClick(e);
		});
		menuImageContrast.setOnAction(e -> {
			menuImageContrast_OnClick(e);
		});
		menuImageGrayscale.setOnAction(e -> {
			menuImageGrayscale_OnClick(e);
		});
		menuImageHistogram.setOnAction(e -> {
			menuImageHistogram_OnClick(e);
		});
		menuImageInvert.setOnAction(e -> {
			menuImageInvert_OnKlick(e);
		});
		menuImageCrossCorrelation.setOnAction(e -> {
			menuImageCrossCorrelation_OnClick(e);
		});
		menuImageHistogramEqualizaion.setOnAction(e -> {
			menuImageHistogramEqualizaion_OnClick(e);
		});
		menuImageEdgeDetectionPrewitt.setOnAction(e -> {
			menuImageEdgeDetectionPrewitt_OnClick(e);
		});
		menuImageEdgeDetectionSobel.setOnAction(e -> {
			menuImageEdgeDetectionSobel_OnClick(e);
		});
		
		menuImageDitheringThresholding.setOnAction(e -> {
			menuImageDitheringThresholding_OnClick(e);
		});
		menuImageDitheringErrorDiffusion.setOnAction(e -> {
			menuImageDitheringErrorDiffusion_OnClick(e);
		});
		menuImageDitheringErrorDiffusionFloyd.setOnAction(e -> {
			menuImageDitheringErrorDiffusionFloyd_OnClick(e);
		});
		
		menuImageBlurGussianBlur.setOnAction(e -> {
			menuImageBlurGussianBlur_OnClick(e);
		});
		
		
		
		mainPane.heightProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				mainImageView.setFitHeight((double) newValue - 25);
			}
		});
		mainPane.widthProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				mainImageView.setFitWidth((double) newValue - 25);
			}
		});
	}

}
