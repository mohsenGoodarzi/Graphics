/*
CS-255 Getting started code for the assignment
I do not give you permission to post this code online
Do not post your solution online
Do not copy code
Do not use JavaFX functions or other libraries to do the main parts of the assignment:
	Gamma Correction
	Contrast Stretching
	Histogram calculation and equalisation
	Cross correlation
All of those functions must be written by yourself
You may use libraries to achieve a better GUI
*/
import java.io.FileInputStream; 
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;  
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class Photoshop extends Application {
	
	private  Map<Integer,Double>lookupTable = new Hashtable<Integer, Double>();
	private Image orginalImage=null;
	@Override
    public void start(Stage stage) throws FileNotFoundException {
		stage.setTitle("Photoshop");
	
		//Read the image
		Image image = new Image(new FileInputStream("raytrace.jpg"));  

		//Create the graphical view of the image
		ImageView imageView = new ImageView(image); 
		
		//Create the simple GUI
		Button invert_button = new Button("Invert");
		Button gamma_button = new Button("Gamma Correct");
		Button contrast_button = new Button("Contrast Stretching");
		Button histogram_button = new Button("Histograms");
		Button cc_button = new Button("Cross Correlation");
		TextField gammaInput = new TextField();
		Button restButton = new Button("Reset");
		Label firstCordination =new Label();
		Label secondCordination =new Label();
		
        
		orginalImage = new Image(new FileInputStream("raytrace.jpg"));
		orginalImage=imageView.getImage();
		
		imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
		
			@Override
			public void handle(MouseEvent mouseEvent) {
				// TODO Auto-generated method stub
				System.out.print("First Point X: "+mouseEvent.getX()+ " Y: "+mouseEvent.getY());
				firstCordination.setText("First Point X: "+mouseEvent.getX()+ " Y: "+mouseEvent.getY());
			}
		});
		
		restButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle (ActionEvent event) {
				imageView.setImage(orginalImage);
			}
		});
		
		//Add all the event handlers (this is a minimal GUI - you may try to do better)
		invert_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Invert");
				//At this point, "image" will be the original image
				//imageView is the graphical representation of an image
				//imageView.getImage() is the currently displayed image
				
				//Let's invert the currently displayed image by calling the invert function later in the code
				Image inverted_image=ImageInverter(imageView.getImage());
				//Update the GUI so the new image is displayed
				imageView.setImage(inverted_image);
            }
        });

		gamma_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
             	for(int i=0;i<=255;i++) {
					lookupTable.put(i,Math.pow((double)i/255,1/(Double.parseDouble(gammaInput.getText()))));
             	}
               Image inverted_image=imageGammaCorrection(imageView.getImage());
				imageView.setImage(inverted_image);
            }
        });

		contrast_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Contrast Stretching");
                // implement contrast furmula
                lookupTable.clear();
                for(int i=0;i<=255;i++) {
					lookupTable.put(i,Math.pow((double)i/255,1/(Double.parseDouble(gammaInput.getText()))));
             	}
                Image inverted_image=imageContrastCorrection(imageView.getImage());
				imageView.setImage(inverted_image);
            
            
            }
        });
		
		histogram_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Histogram");
            }
        });
		
		cc_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Cross Correlation");
            }
        });
		
		//Using a flow pane
		FlowPane root = new FlowPane();
		//Gaps between buttons
		root.setVgap(10);
        root.setHgap(5);

		//Add all the buttons and the image for the GUI
		root.getChildren().addAll(invert_button, gamma_button,
								contrast_button, histogram_button, cc_button,
								imageView,gammaInput, restButton,firstCordination,secondCordination);

		//Display to user
        Scene scene = new Scene(root, 1024, 768);
        stage.setScene(scene);
        stage.show();
    }

	
    public Image imageContrastCorrection(Image image) {
    	
		int width = (int)image.getWidth();
        int height = (int)image.getHeight();
		
		WritableImage inverted_image = new WritableImage(width, height);
		
		PixelWriter inverted_image_writer = inverted_image.getPixelWriter();
		
		PixelReader image_reader=image.getPixelReader();
		
		
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				Color color = image_reader.getColor(x, y);
				color=Color.color(Math.pow(((color.getRed() + 0.055)/1.055), 2.4),Math.pow(((color.getGreen() + 0.055)/1.055), 2.4),Math.pow(((color.getBlue() + 0.055)/1.055), 2.4));
				//color=Color.color(Math.pow(((color.getRed() + 0.055)/1.055), 2.4),Math.pow(((color.getGreen() + 0.055)/1.055), 2.4),Math.pow(((color.getBlue() + 0.055)/1.055), 2.4));
				inverted_image_writer.setColor(x, y, color);
			}
		}
		return inverted_image;
		 
   
    	
    	
    }

    
    
    public Image imageGammaCorrection(Image image) {
    	
		int width = (int)image.getWidth();
        int height = (int)image.getHeight();
		
		WritableImage inverted_image = new WritableImage(width, height);
		
		PixelWriter inverted_image_writer = inverted_image.getPixelWriter();
		
		PixelReader image_reader=image.getPixelReader();
		
		
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				Color color = image_reader.getColor(x, y);
				color=Color.color(lookupTable.get((int)(color.getRed()*255)),
						lookupTable.get((int)(color.getGreen()*255)),
						lookupTable.get((int)(color.getBlue()*255)));
				//color=Color.color(Math.pow(color.getRed(),2.2),Math.pow( color.getGreen(),1.2), Math.pow(color.getBlue(),1.2));
				inverted_image_writer.setColor(x, y, color);
			}
		}
	

		
		
		return inverted_image;
		 
   
    	
    	
    }
    
    
    
	//Example function of invert
	public Image ImageInverter(Image image) {
		//Find the width and height of the image to be process
		int width = (int)image.getWidth();
        int height = (int)image.getHeight();
		//Create a new image of that width and height
		WritableImage inverted_image = new WritableImage(width, height);
		//Get an interface to write to that image memory
		PixelWriter inverted_image_writer = inverted_image.getPixelWriter();
		//Get an interface to read from the original image passed as the parameter to the function
		PixelReader image_reader=image.getPixelReader();
		
		//Iterate over all pixels
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				//For each pixel, get the colour
				Color color = image_reader.getColor(x, y);
				//Do something (in this case invert) - the getColor function returns colours as 0..1 doubles (we could multiply by 255 if we want 0-255 colours)
				color=Color.color(1.0-color.getRed(), 1.0-color.getGreen(), 1.0-color.getBlue());
				//Note: for gamma correction you may not need the divide by 255 since getColor already returns 0-1, nor may you need multiply by 255 since the Color.color function consumes 0-1 doubles.
				
				//Apply the new colour
				inverted_image_writer.setColor(x, y, color);
			}
		}
		return inverted_image;
	}
	public static void main(String[] args) {
        launch();
    }


};