import java.awt.color.ColorSpace;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Map;

import org.omg.CORBA.OMGVMCID;

import FilterCollection.FilterCollection3By3;
import FilterCollection.IFilter;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import FilterCollection.FilterCollection5By5;

public class ImageProcessing {

	private static Image orginalImage;
	private Image processedImage;
	private double gamma;
	private double contrast;
	private double[] lookupTable;

	//private double[] redLookupTable;
	//private double[] greenLookupTable;
	//private double[] blueLookupTable;
	private double[] grayscaleLookupTable;
	private double imageWidth;
	private double imageHeight;
	//private final int RED = 0;
	//private final int GREEN = 1;
	//private final int BLUE = 2;
	//private final int CUMULATIVE_RED = 3;
	//private final int CUMULATIVE_GREEN = 4;
	//private final int CUMULATIVE_BLUE = 5;

	private Histogram histogram;

	public ImageProcessing(Image image) {

		setOrginalImage(image);

		setProcessedImage(null);

		imageWidth = image.getWidth();
		imageHeight = image.getHeight();
		histogram = new Histogram(orginalImage);

		processedImage = orginalImage;
		//redLookupTable = new double[256];
		//greenLookupTable = new double[256];
		//blueLookupTable = new double[256];
		//grayscaleLookupTable = new double[256];
		for (int i = 0; i <= 255; i++) {
			double j = (double) i;
			//redLookupTable[j] = i / 255.0d;
			//greenLookupTable[j] = i / 255.0d;
			//blueLookupTable[j] = i / 255.0d;
			//grayscaleLookupTable[j] = i / 55.0d;
			lookupTable[i]= j/255.0d;
			

		}

	}

	private double [] getLookupTabe() {
		return lookupTable;
	}

	private void setLookupTabe(double [] lookupTable) {
		this.lookupTable = lookupTable;
	}

	public Image getOrginalImage() {
		return orginalImage;
	}

	private void setOrginalImage(Image orginalImage) {
		this.orginalImage = orginalImage;
	}

	public Histogram getHistogram() {
		return histogram;
	}

	public void applyGrayScale() {

		int width = (int) orginalImage.getWidth();
		int height = (int) orginalImage.getHeight();

		WritableImage writableImage = new WritableImage(width, height);
		PixelWriter imageWeiter = writableImage.getPixelWriter();
		PixelReader imageReader = orginalImage.getPixelReader();

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				Color color = imageReader.getColor(x, y);
				double gray = (color.getRed() + color.getGreen() + color.getBlue()) / 3;
				color = Color.color(gray, gray, gray);
				imageWeiter.setColor(x, y, color);
			}
		}
		setProcessedImage(writableImage);
	}

	public HSV toHSV(double red, double green, double blue){
		HSV hsv = new HSV();
		
			 double computedHue = 0;
			 double computedSaturation = 0;
			 double computedValue = 0;

			 //remove spaces from input RGB values, convert to int
			 
			 
			 double min = Math.min(red,Math.min(green,blue));
			 double max = Math.max(red,Math.max(green,blue));
			 double delta,hue =0;
			 // Black-gray-white image
			 if (min==max) {
				 computedValue = min;
			  hsv.setValue(min/255.0d);
			  return hsv;
			 }
			 else {
				 
				 if (red == min) delta = green -blue;
				 else if (blue == min) delta = red-green;
				 else delta = blue - red;
					 
				 if(red == min) hue = 3;
				 else if (blue==min) hue= 1;
				 else
					 hue = blue-red;
				 
				 computedHue = 60*(hue - delta/(max - min));
				 computedSaturation = (max - min)/max* 100;
				 computedValue = max/255d*100;// computed value must be mapped into the range [0-100] 
				 		 
				 hsv.setHue(computedHue);
				 hsv.setSaturation(computedSaturation);
				 hsv.setValue(computedValue);
				 return hsv;	 
				 
			 }

}
		
	public Color toRGB(HSV hsv) {
		double value,hue,saturation,c=0,x=0,m=0;
		hue=hsv.getHue();
		value=hsv.getValue();
		saturation=hsv.getSaturation();
		c=value*saturation;
		x = c *(1- Math.abs(((hue/60)% 2)-1));
		m = value-c;
		double notRed=0,notGreen=0,notBlue =0;
		if (0<=hue && hue<60) {
		notRed=c;
		notGreen=x;
		notBlue=0;
		}
		
		if (60<=hue && hue<120) {
			notRed=x;
			notGreen=c;
			notBlue=0;
			}
		if (120<=hue && hue<180) {
			notRed=0;
			notGreen=c;
			notBlue=x;
			}
			
		if (180<=hue && hue<240) {
			notRed=0;
			notGreen=x;
			notBlue=c;
			}
		if (240<=hue && hue<300) {
			notRed=x;
			notGreen=0;
			notBlue=c;
			}
		if (300<=hue && hue<360) {
			notRed=c;
			notGreen=0;
			notBlue=x;
			}
		
	int red=0,green=0,blue=0;
	red  =(int) (notRed+m)  *255;
	green=(int) (notGreen+m)*255;
	blue =(int) (notBlue+m) *255;
	
	return Color.rgb(red, green, blue);
	}	
		
	
	public void calcEqualization() {
		// mapping[i] = 255.0 * (t[i] / Size)
		for (int i = 0; i < 256; i++) {
			
			
			//redLookupTable[i] = (double) (255.0d * ((histogram.getCumulativeRed()[i]
			//		/ (double) (orginalImage.getWidth() * orginalImage.getHeight()))));
			//greenLookupTable[i] = (double) (255.0d * ((histogram.getCumulativeGreen()[i]
		//			/ (double) (orginalImage.getWidth() * orginalImage.getHeight()))));
		//	blueLookupTable[i] = (double) (255.0d * ((histogram.getCumulativeBlue()[i]
			//		/ (double) (orginalImage.getWidth() * orginalImage.getHeight()))));
			lookupTable[i] = (double) (255.0d * ((histogram.getCumulativeBrightness()[i]
					/ (double) (orginalImage.getWidth() * orginalImage.getHeight()))));

		}
	}

	public void applyEqualization() {
		int width = (int) orginalImage.getWidth();
		int height = (int) orginalImage.getHeight();

		WritableImage inverted_image = new WritableImage(width, height);
		PixelWriter inverted_image_writer = inverted_image.getPixelWriter();
		PixelReader image_reader = orginalImage.getPixelReader();
double brightness =0,hue=0,saturation=0;
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				Color color = image_reader.getColor(x, y);
		//		color = Color.color(redLookupTable[(int) (color.getRed() * 255)],
		//				greenLookupTable[(int) (color.getGreen() * 255)],
		//				blueLookupTable[(int) (color.getBlue() * 255)]);
				brightness	=lookupTable[(int)(color.getBrightness()*255)];
				hue= color.getHue();
				saturation= color.getSaturation();
				Color newColor = toRGB(new HSV(hue,saturation,brightness));
				inverted_image_writer.setColor(x, y, newColor);
			}
		}
		setProcessedImage(inverted_image);
	}

	

	private int[] calcFilterOnPixel(int x, int y,IFilter filter) {
		int[] result = new int[3];

		
		int margin = (filter.getLength()-1)/2;
		
		int width = (int) orginalImage.getWidth();
		int height = (int) orginalImage.getHeight();
		PixelReader image_reader = orginalImage.getPixelReader();

		// if (((x -2) >= 0 && (y-2)>=0) || ((x+2)<=tempImage.Width
		// &&((x+2)<=tempImage.Width && (y + 2) <= tempImage.Height)))
		for (int column = 0; column < filter.getLength(); column++) 
		 {
			
				for (int row = 0; row < filter.getLength(); row++){

				{
					Color color = image_reader.getColor(((x - margin) + row), ((y - margin) + column));
					result[0] += filter.getMatrix()[column][row] * color.getRed();
					result[1] += filter.getMatrix()[column][row]  * color.getGreen();
					result[2] += filter.getMatrix()[column][row]  * color.getBlue();
				}
			}

		}

		return result;
	}

	private double[][][] calcFilter(IFilter filter) {

		int totalMargin= filter.getLength()-1;
		int size = ((int) orginalImage.getWidth() - totalMargin) * ((int) orginalImage.getHeight() - totalMargin);
		double[][][] result = new double[(int) orginalImage.getWidth() - totalMargin][(int) orginalImage.getHeight() - totalMargin][3];
		int[] newValuesRed = new int[size];
		int[] newValuesGreen = new int[size];
		int[] newValuesBlue = new int[size];
		int counter = 0;
		int margin = (filter.getLength()-1)/2;
		
		for (int x = 2; x < orginalImage.getWidth() - margin; x++) {
			for (int y = 2; y < orginalImage.getHeight() - margin; y++) {
				int[] pxl = calcFilterOnPixel(x, y,filter);
				// I’= ((I - min) * 255) / (max - min)

				newValuesRed[counter] = pxl[0];
				newValuesGreen[counter] = pxl[1];
				newValuesBlue[counter] = pxl[2];

				counter++;
			}
		}

		// result

		int[] red = Arrays.copyOfRange(newValuesRed, 0, newValuesRed.length - 1);
		Arrays.sort(red);
		int[] green = Arrays.copyOfRange(newValuesGreen, 0, newValuesGreen.length - 1);
		Arrays.sort(green);
		int[] blue = Arrays.copyOfRange(newValuesBlue, 0, newValuesBlue.length - 1);
		Arrays.sort(blue);

		int redMax = red[red.length - 1];
		int redMin = red[0];
		int greenMax = green[green.length - 1];
		int greenMin = green[0];
		int blueMax = blue[blue.length - 1];
		int blueMin = blue[0];

		int finalMin = Math.min(redMin, Math.min(greenMin, blueMin));
		int finalMax = Math.max(redMax, Math.max(greenMax, blueMax));
		counter = 0;
		for (int x = margin; x < orginalImage.getWidth() - margin; x++) {
			for (int y = margin; y < orginalImage.getHeight() - margin; y++) {
				int[] pxl = calcFilterOnPixel(x, y,filter);
				// I’= ((I - min) * 255) / (max - min)

				result[x - margin][y - margin][0] += ((newValuesRed[counter] - finalMin) * 255) / (finalMax - finalMin);
				result[x - margin][y - margin][1] += ((newValuesGreen[counter] - finalMin) * 255) / (finalMax - finalMin);
				result[x - margin][y - margin][2] += ((newValuesBlue[counter] - finalMin) * 255) / (finalMax - finalMin);
				counter++;

			}
		}

		return result;
	}

	public void applyFilter(IFilter filter) {

		int width = (int) orginalImage.getWidth();
		int height = (int) orginalImage.getHeight();
		int margin = (filter.getLength()-1)/2;
		WritableImage inverted_image = new WritableImage(width, height);
		PixelWriter inverted_image_writer = inverted_image.getPixelWriter();
		PixelReader image_reader = orginalImage.getPixelReader();

		double[][][] newValues = calcFilter(filter);

		for (int x = margin; x < orginalImage.getWidth() - margin; x++) {
			for (int y = margin; y < orginalImage.getHeight() - margin; y++) {
				Color color = image_reader.getColor(x, y);

				color = Color.color((double) (newValues[x - margin][y - margin][0] / 255d),
						(double) (newValues[x - margin][y - margin][1] / 255d), (double) (newValues[x - margin][y - margin][2] / 255d));
				inverted_image_writer.setColor(x, y, color);
			}
		}

		setProcessedImage(inverted_image);
	}

	public Image getProcessedImage() {
		return processedImage;
	}

	private void setProcessedImage(Image processedImage) {
		this.processedImage = processedImage;
	}

	public double getGamma() {
		return gamma;
	}

	public void setGamma(double gamma) {
		this.gamma = gamma;
	}

	public void calcGamma() {

		
		for (int i = 0; i <= 255; i++) {
			getLookupTabe()[i]=Math.pow((double) i / 255, ((double) 1 / gamma));
		}
	}

	public void applyGamma() {

		int width = (int) orginalImage.getWidth();
		int height = (int) orginalImage.getHeight();

		WritableImage inverted_image = new WritableImage(width, height);
		PixelWriter inverted_image_writer = inverted_image.getPixelWriter();
		PixelReader image_reader = orginalImage.getPixelReader();

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				Color color = image_reader.getColor(x, y);
				color = Color.color(lookupTable[((int) (color.getRed() * 255))],
						lookupTable[((int) (color.getGreen() * 255))],
						lookupTable[((int) (color.getBlue() * 255))]);

				inverted_image_writer.setColor(x, y, color);

			}
		}
		setProcessedImage(inverted_image);
	}

	public double getContrast() {
		return contrast;
	}

	public void setContrast(double contrast) {
		this.contrast = contrast;
	}

	public void calcContrast() {
		// add formula
	}

	public void applyContrast() {

	}

	public float calcEdgeDetection(int x, int y, IFilter filter) {

		int a = (filter.getLength() - 1) / 2;
		int b = (filter.getLength() - 1) / 2;
		float sum = 0;
		int width = (int) orginalImage.getWidth();
		int height = (int) orginalImage.getHeight();

		WritableImage inverted_image = new WritableImage(width, height);
		PixelWriter inverted_image_writer = inverted_image.getPixelWriter();
		PixelReader image_reader = orginalImage.getPixelReader();

		for (int s = -a; s <= a; s++) {

			for (int t = -b; t <= b; t++) {
				Color color = image_reader.getColor(x + s, y + t);

				sum = (int) (sum + filter.getMatrix()[s + a][t + b] * ((color.getRed()+color.getGreen()+color.getBlue())/3 * 255));
			}
		}
		return sum;
	}

	public void applyEdgeDetection(IFilter horizontalFilter,IFilter VerticalFilter, int thresholdPoint) {
		
		 
		 int filterSize = VerticalFilter.getLength();
		int startCount = (filterSize - 1) / 2;

		int width = (int) orginalImage.getWidth();
		int height = (int) orginalImage.getHeight();

		WritableImage inverted_image = new WritableImage(width, height);
		PixelWriter inverted_image_writer = inverted_image.getPixelWriter();
		PixelReader image_reader = processedImage.getPixelReader();

		for (int y = startCount; y < height - 1; y++) {
			for (int x = startCount; x < width - 1; x++) {
				Color color = image_reader.getColor(x, y);

				double resultX = calcEdgeDetection(x, y, horizontalFilter);
				double resultY = calcEdgeDetection(x, y, VerticalFilter);
						
			double magnitude = Math.sqrt((resultX*resultX)+(resultY*resultY));
			
			if (magnitude < thresholdPoint)
					
				inverted_image_writer.setColor(x, y,
										Color.color(1.0,1.0,1.0));
			else
				inverted_image_writer.setColor(x, y,
						Color.color(0,0,0));
			}
		}
		setProcessedImage(inverted_image);

	}

	
	public void applyEdgeDetectionSecondDerivation(IFilter filter) {
		
		
		 int filterSize = filter.getLength();
		int startCount = (filterSize - 1) / 2;
		System.out.println("Start point"+startCount);
		int width = (int) orginalImage.getWidth();
		int height = (int) orginalImage.getHeight();

		WritableImage image = new WritableImage(width, height);
		PixelWriter image_writer = image.getPixelWriter();
		

		for (int y = startCount; y < height - 2; y++) {
			for (int x = startCount; x < width - 3; x++) {
				

				double current = calcEdgeDetection(x, y, filter);
				double nextX = calcEdgeDetection(x+1, y, filter);
				//double afterNextX = calcEdgeDetection(x+2, y, filter);
				
			if (current==0 && (nextX<0 || nextX>0)){
				Color color=Color.color(0,0,0);
				image_writer.setColor(x,y,color);
			}
			else 
			{
				Color color=Color.color(1,1,1);
				image_writer.setColor(x,y,color);
			}
			
		}
	}
		setProcessedImage(image);

	}

	
	
	
	public void applyThreshold() {
		int width = (int) orginalImage.getWidth();
		int height = (int) orginalImage.getHeight();
		int threshold = 128;

		// calcs threshold image
		// applyGrayScale();
		WritableImage inverted_image = new WritableImage(width, height);
		PixelWriter inverted_image_writer = inverted_image.getPixelWriter();

		PixelReader image_reader = orginalImage.getPixelReader();

		long totalIntensityOrginalImage = 0;
		long totalIntensityFresholdImage = 0;
		for (int y = 0; y < height; y++)
			for (int x = 0; x < width; x++)

			{
				Color color = image_reader.getColor(x, y);
				int resultOrginalImage = (int) ((color.getBlue() + color.getRed() + color.getGreen()) * 255 / 3);

				totalIntensityOrginalImage += resultOrginalImage;

				// since the image is Grayscale then the color can be either red, green or blue
				// in if statement

				if (resultOrginalImage < threshold) {
					color = Color.color(0, 0, 0);
					inverted_image_writer.setColor(x, y, color);
				} else {

					color = Color.color(1.0, 1.0, 1.0);
					inverted_image_writer.setColor(x, y, color);
					int resultFreshold = (int) ((color.getBlue() + color.getRed() + color.getGreen()) * 255 / 3);
					totalIntensityFresholdImage += resultFreshold;
				}

			}
		System.out.println("orginal image total intensity: " + totalIntensityOrginalImage + " Avereage: "
				+ totalIntensityOrginalImage / (width * height));
		System.out.println("freshold image total intensity: " + totalIntensityFresholdImage + " Avereage: "
				+ totalIntensityFresholdImage / (width * height));
		System.out.println("Error (Total): " + (totalIntensityFresholdImage - totalIntensityOrginalImage));
		setProcessedImage(inverted_image);

	}

	public void applyErrorDiffusion() {

		int width = (int) orginalImage.getWidth();
		int height = (int) orginalImage.getHeight();

		int threshold = 128;
		int max_intensity = 255;
		int error = 0;

		double[][] bw = new double[height][width];

		WritableImage inverted_image = new WritableImage(width, height);
		PixelWriter inverted_image_writer = inverted_image.getPixelWriter();
		PixelReader image_reader = orginalImage.getPixelReader();

		boolean direction = true;
		for (int y = 0; y < height; y++) {

			if (direction)
				for (int x = 0; x < width; x++) {

					Color color = image_reader.getColor(x, y);
					int currentGrey = (int) ((color.getBlue() + color.getGreen() + color.getRed()) * 255 / 3);

					// grey[j][i]=grey[j][i]+error

					currentGrey = currentGrey + error;

					if (currentGrey < threshold) {
						bw[y][x] = 0;
						// error= grey[x][y];
						error = currentGrey;

					} else {
						bw[y][x] = max_intensity;
						// error=grey[x][y]-max_intensity;
						error = currentGrey - max_intensity;
					}

					color = Color.color((bw[y][x] / 255), (bw[y][x] / 255), (bw[y][x] / 255));
					// System.out.println(color.getBlue());
					inverted_image_writer.setColor(x, y, color);

					direction = false;
				}
			else if (!direction)
				for (int x = width - 1; x >= 0; x--) {

					Color color = image_reader.getColor(x, y);
					int currentGrey = (int) ((color.getBlue() + color.getGreen() + color.getRed()) * 255 / 3);

					// grey[j][i]=grey[j][i]+error
					currentGrey = currentGrey + error;

					if (currentGrey < threshold) {
						bw[y][x] = 0;
						// error= grey[x][y];
						error = currentGrey;

					} else {
						bw[y][x] = max_intensity;
						// error=grey[x][y]-max_intensity;
						error = currentGrey - max_intensity;
					}

					color = Color.color((bw[y][x] / 255.0d), (bw[y][x] / 255.0d), (bw[y][x] / 255.0d));
					// System.out.println(color.getBlue());
					inverted_image_writer.setColor(x, y, color);
					direction = true;
				}

		}

		setProcessedImage(inverted_image);

	}

	public void applyDiffusionDitheringFloyd() {

		int width = (int) orginalImage.getWidth();
		int height = (int) orginalImage.getHeight();

		int threshold = 128;
		int max_intensity = 255;
		double error = 0;

		int[][] grey = new int[height][width];

		double[][] bw = new double[height][width];

		WritableImage inverted_image = new WritableImage(width, height);
		PixelWriter inverted_image_writer = inverted_image.getPixelWriter();
		PixelReader image_reader = orginalImage.getPixelReader();

// initials grey array
		for (int y = 0; y < height; y++)
			for (int x = 0; x < width; x++) {
				Color color = image_reader.getColor(x, y);
				grey[y][x] = (int) ((color.getBlue() + color.getGreen() + color.getRed()) * 255 / 3);
			}

		
		for(int y=0;y<3;y++)
		{	for (int x = 0; x<5;x++)
			{
		System.out.print(grey[y][x]+"	");
			}
		System.out.print("\n");
		}
		double alpha = (7d / 16d);
		double beta = (3d / 16d);
		double gamma = (5d / 16d);
		double ro = (1d / 16d);

		boolean direction = true;
		for (int y = 0; y < (height - 1); y++) {

			if (direction)
				for (int x = 1; x < (width - 1); x++) {

					if (grey[y][x] < threshold) {
						bw[y][x] = 0;
						
						error = grey[y][x];

					} else {
						bw[y][x] = max_intensity;
						
						error = grey[y][x] - max_intensity;
					}
					
					Color color = image_reader.getColor(x + 1, y);
					grey[y][x + 1] = grey[y][x + 1] + (int) (error * alpha);

					color = image_reader.getColor(x - 1, y + 1);
					grey[y + 1][x - 1] = grey[y + 1][x - 1] + (int) (error * beta);

					color = image_reader.getColor(x, y + 1);
					grey[y + 1][x] = grey[y + 1][x] + (int) (error * gamma);

					color = image_reader.getColor(x + 1, y + 1);
					grey[y + 1][x + 1] = grey[y + 1][x + 1] + (int) (error * ro);

				

					color = Color.color((bw[y][x] / 255), (bw[y][x] / 255), (bw[y][x] / 255));
					inverted_image_writer.setColor(x, y, color);
					direction = false;
				}
			else if (!direction)
				for (int x = (width - 2); x >= 1; x--) {

					if (grey[y][x] < threshold) {
						bw[y][x] = 0;
						// error= grey[x][y];
						error = grey[y][x];

					} else {
						bw[y][x] = max_intensity;
						// error=grey[x][y]-max_intensity;
						error = grey[y][x] - max_intensity;
					}

					
					
					Color color = image_reader.getColor(x + 1, y);
					grey[y][x + 1] = grey[y][x + 1] + (int) (error * alpha);

					color = image_reader.getColor(x - 1, y + 1);
					grey[y + 1][x - 1] = grey[y + 1][x - 1] + (int) (error * beta);

					color = image_reader.getColor(x, y + 1);
					grey[y + 1][x] = grey[y + 1][x] + (int) (error * gamma);

					color = image_reader.getColor(x + 1, y + 1);
					grey[y + 1][x + 1] = grey[y + 1][x + 1] + (int) (error * ro);

					
					
					color = Color.color((bw[y][x] / 255), (bw[y][x] / 255), (bw[y][x] / 255));
					inverted_image_writer.setColor(x, y, color);

					direction = true;
				}

		}
		System.out.print("\n grey before \n");
		
		for(int y=0;y<3;y++)
		{	for (int x = 0; x<5;x++)
			{
		System.out.print(grey[y][x]+"	");
			}
		System.out.print("\n");
		}
		
		
				//for()int y=0;y<System.out.println(bw[y][x]);
		for(int y=0;y<3;y++)
		{	for (int x = 0; x<5;x++)
			{
		System.out.print(bw[y][x]+"	");
			}
		System.out.print("\n");
		}
		setProcessedImage(inverted_image);

	}

	
	
	public void resizeOldMethod() {
		//for j=0 to Yb-1
		//for i=0 to Xb-1
		//for c=0 to 2
		//y=j*Ya/Yb <- make sure this is done using floats
		//x=i*Xa/Xb <- same
		//Ib[j][i][c]=Ia[y][x][c]

		
		int width = (int) orginalImage.getWidth();
		int height = (int) orginalImage.getHeight();
		
		
		
		float newX = 1.5f;
		float newY = 1.5f;
		int newWidth =  (int)(newX * (float)width);
		int newHeight = (int)(newY * (float)height);
		int y=0;
		int x=0;
		WritableImage resizedImage = new WritableImage(newWidth,newHeight);
		PixelReader resizedImageReader = resizedImage.getPixelReader();
		PixelWriter resizedImageWriter = resizedImage.getPixelWriter();
		
		
		for (int ty=0;ty<resizedImage.getHeight();ty++)
			for (int tx=0;y<resizedImage.getWidth();tx++)
			{
				Color color=Color.color(0, 0, 0);
				resizedImageWriter.setColor(x, y, color);
			}
		
		
		for (int nY =0;nY<newHeight;nY++)
			for (int nX =0;nX<newWidth;nX++)
			{
				
				y= nY * height / newHeight ; //make sure this is float
				x = nX * width / newWidth;  ////make sure this is float
				
				Color orginalColor=resizedImageReader.getColor(x, y);
				resizedImageWriter.setColor(nX, nY, orginalColor);
			}
		
		setProcessedImage(resizedImage);
	}


	
	private int[][] getIntermediateResult(IFilter filter) {
		
		int width = (int) orginalImage.getWidth();
		int height = (int) orginalImage.getHeight();
		
		
		
      int margin = (filter.getLength()-1)/2;
		
      int[][] newResultArray= new int[height-margin][width-margin];
      
		for (int y =margin;y<height-margin-1;y++)
			for (int x =margin;x<width-margin-1;x++)
			{
				
			int[] result=	calcFilterOnPixel(x, y, filter);
				//asume picture is grayscale
			newResultArray[y][x]=result[0];
				
			}
		
		
		
	return newResultArray;
	}
		
		
		private int [] getMinMaxFromIntermediate(int[][] intermediate) {
			int width = (int) orginalImage.getWidth();
			int height = (int) orginalImage.getHeight();
				
			
			int[] allMins= new int[height];
			int[] allMaxs= new int[height];
			
			for(int y=0;y<height;y++ )
		{
			int [] temp = Arrays.copyOf(intermediate[y], width);
			Arrays.sort(temp);
			allMins[y] = temp[0];
			allMaxs[y]=temp[temp.length-1];
		
		}

			Arrays.sort(allMins);
			Arrays.sort(allMaxs);
			int []result = new int[2];
			result[0] =Math.min(allMins[0],allMaxs[0]);
			
			result[1] =Math.max(allMins[allMins.length-1],allMaxs[allMaxs.length-1]);
			
				
			
		return result;
		}
		
	
	
	
	public void resizeImageLinear() {
		
	}


	public void resizeImageBilinear() {
		
	}
	 
	

}
