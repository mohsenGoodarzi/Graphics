/*
 * File-name: ContrastController.java 
 * Version number: 0.1.0
 * Creation date: 06/03/2019
 * Last modification date: 04/07/2020 
 * Author’s name: Mohsen Goodarzi
 * Copyright: Mohsen Goodarzi  
 * Purpose of the program: Educational 
 */

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class ContrastController {
	
	private final double TOP_MARGIN = 30;
    private final double BOTTON_MARGIN = 30;
    private final double LEFT_MARGIN = 30;
    private final double RIGHT_MARGIN = 30;

    private final double START_POSITION_X;
    private final double START_POSIOTON_Y;
    private final double END_POSITION_X;
    private final double END_POSITION_Y;
    private Line firstAreaLine;
    private Line secondAreaLine;
    private Line thirdAreaLine;
    private Circle firstElipse;
	private Circle SecondElipse; 
    private Image image;
	private MainController mainController;
    @FXML private Canvas mainCanvas; 
	
	public ContrastController(Image image,MainController mainForm) {
	
		 START_POSITION_X = LEFT_MARGIN;
         START_POSIOTON_Y = TOP_MARGIN;
         END_POSITION_X = mainCanvas.getWidth() - RIGHT_MARGIN;
         END_POSITION_Y = mainCanvas.getHeight() - BOTTON_MARGIN;
		this.image = image;
		this.mainController=mainForm;
		initCanvas();
	}

private void initCanvas() {
	
	Line horizentalAx = new Line();
    horizentalAx.setStartX(START_POSITION_X);
    horizentalAx.setEndX(END_POSITION_X);
    horizentalAx.setStartY(END_POSITION_Y);
    horizentalAx.setEndY(END_POSITION_Y);


    // Vertical Axies
    Line verticalAx = new Line();
    verticalAx.setStartX( START_POSITION_X);
    verticalAx.setEndX(START_POSITION_X);
    verticalAx.setStartY(START_POSIOTON_Y);
    verticalAx.setEndY(END_POSITION_Y);

    
    double startPointX = START_POSITION_X;
    double startPointY = END_POSITION_Y ;


   
    double firstElipsePointX = (85) + RIGHT_MARGIN;
    double firstElipsePointY = (170) + BOTTON_MARGIN;

  
    double SecondElipsePointX = (170) + RIGHT_MARGIN;
    double SecondElipsePointY = (85) + BOTTON_MARGIN;

    
    double endPointX = END_POSITION_X;
    double endPointY = START_POSIOTON_Y;
    
    // lower bound area
	firstAreaLine  = new Line(); 
	firstAreaLine.setStartX(startPointX); 
	firstAreaLine.setStartY(startPointY); 
	firstAreaLine.setEndX(firstElipsePointX); 
	firstAreaLine.setEndY(firstElipsePointY);
	
	// middle Area
	secondAreaLine  = new Line(); 
	secondAreaLine.setStartX(firstElipsePointX); 
	secondAreaLine.setStartY(firstElipsePointY); 
	secondAreaLine.setEndX(SecondElipsePointX); 
	secondAreaLine.setEndY(SecondElipsePointY);
	
	//Upper Area
	thirdAreaLine  = new Line(); 
	thirdAreaLine.setStartX(SecondElipsePointX); 
	thirdAreaLine.setStartY(SecondElipsePointY); 
	thirdAreaLine.setEndX(endPointX); 
	thirdAreaLine.setEndY(endPointY);
	
 firstElipse = new Circle(firstElipsePointX,firstElipsePointY,23);
 SecondElipse = new Circle(SecondElipsePointX,SecondElipsePointY,23);

 
}

}
