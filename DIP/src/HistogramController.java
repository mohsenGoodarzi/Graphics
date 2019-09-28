import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.input.InputEvent;
import javafx.scene.input.InputMethodEvent;
import javafx.stage.Stage;

public class HistogramController  {
	
    private final int RED = 0;
    private final int GREEN = 1;
    private final int BLUE = 2;
    private final int GRAYSCALE = 3;
    private final int CUMULATIVE_RED= 4;
    private final int CUMULATIVE_GREEN = 5;
    private final int CUMULATIVE_BLUE = 6;
    private final int CUMULATIVE_GRAYSCALE = 7;
    private ImageProcessing imageProcessing;
	private MainController mainController;
	private Image image;
	
	
	@FXML private AreaChart<Number, Number> mainChart;
	@FXML private ComboBox<String> channelColorCMB;
	
	XYChart.Series<Number, Number> redSeries;
	XYChart.Series<Number, Number> greenSeries;
	XYChart.Series<Number, Number> blueSeries;
	XYChart.Series<Number, Number> brigthnessSeries;
	XYChart.Series<Number, Number> cumulativeRedSeries;
	XYChart.Series<Number, Number> cumulativeGreenSeries;
	XYChart.Series<Number, Number> cumulativeBlueSeries;
	XYChart.Series<Number, Number> cumulativeBrightnessSeries;
	public HistogramController(Image image,MainController mainController) {
		this.image=image;
		this.mainController=mainController;
		imageProcessing=new ImageProcessing(this.image);
	
	}
	
@FXML	
private void initHistogram() {
	
	redSeries= new XYChart.Series<Number, Number>();
	redSeries.setName("Red Colour");
	
	greenSeries= new XYChart.Series<Number, Number>();
	greenSeries.setName("Green Colour");
	blueSeries= new XYChart.Series<Number, Number>();
	blueSeries.setName("Blue Colour");
	brigthnessSeries= new XYChart.Series<Number, Number>();
	brigthnessSeries.setName("Brigthness Colour");
	cumulativeRedSeries= new XYChart.Series<Number, Number>();
	cumulativeRedSeries.setName("Cumulative Red Colour");
	cumulativeGreenSeries= new XYChart.Series<Number, Number>();
	cumulativeGreenSeries.setName("Cumulative Green Colour");
	cumulativeBlueSeries= new XYChart.Series<Number, Number>();
	cumulativeBlueSeries.setName("Cumulative Blue Colour");
	cumulativeBrightnessSeries= new XYChart.Series<Number, Number>();
	cumulativeBrightnessSeries.setName("Cumulative Brigthness Colour");
	
	for (int i=0;i<256;i++) {
		
		redSeries.getData().add(new XYChart.Data<Number, Number>(i, imageProcessing.getHistogram().getRedBar()[i]));	
		greenSeries.getData().add(new XYChart.Data<Number, Number>(i, imageProcessing.getHistogram().getGreenBar()[i]));
		blueSeries.getData().add(new XYChart.Data<Number, Number>(i, imageProcessing.getHistogram().getBlueBar()[i]));
		brigthnessSeries.getData().add(new XYChart.Data<Number, Number>(i, imageProcessing.getHistogram().getBrightnessBar()[i]));
		cumulativeRedSeries.getData().add(new XYChart.Data<Number, Number>(i, imageProcessing.getHistogram().getCumulativeRed()[i]));	
		cumulativeGreenSeries.getData().add(new XYChart.Data<Number, Number>(i, imageProcessing.getHistogram().getCumulativeGreen()[i]));
		cumulativeBlueSeries.getData().add(new XYChart.Data<Number, Number>(i, imageProcessing.getHistogram().getCumulativeBlue()[i]));
		cumulativeBrightnessSeries.getData().add(new XYChart.Data<Number, Number>(i, imageProcessing.getHistogram().getCumulativeBrightness()[i]));			
	}
	
	//mainChart.getData().addAll(
//			redSeries,greenSeries,blueSeries,brigthnessSeries,
//			cumulativeRedSeries,cumulativeGreenSeries,
//			cumulativeBlueSeries,cumulativeBrightnessSeries
//			);
//	mainChart.setAnimated(true);
//	mainChart.setLegendVisible(false);
}
@FXML
public void channelColorCMB_onTextChanged(ActionEvent e){
	mainChart.getData().clear();
	switch( channelColorCMB.getValue()) {
case "Red":{
	mainChart.getData().add(redSeries);
	
	}
break;
case "Green":{
	
	mainChart.getData().add(greenSeries);
	
	}
break;
case "Blue":{
	mainChart.getData().add(blueSeries);
	
	}
break;
case "Brightness":{
	mainChart.getData().add(brigthnessSeries);
	}
break;
case "Cumulative Red":{
	
	mainChart.getData().add(cumulativeRedSeries);
	}
break;
case "Cumulative Green":{
	
	mainChart.getData().add(cumulativeGreenSeries);
	}
break;
case "Cumulative Blue":{
	
	mainChart.getData().add(cumulativeBlueSeries);
	}
break;
case "Cumulative Brightness":{
	
	mainChart.getData().add(cumulativeBrightnessSeries);
	}
break;


}
}

@FXML
public void initialize() {

	channelColorCMB.getItems().addAll("Red","Green","Blue","Brightness",
			"Cumulative Red","Cumulative Green","Cumulative Blue","Cumulative Brightness");
	channelColorCMB.setOnAction(e->{channelColorCMB_onTextChanged(e);});
	initHistogram();
}



}
