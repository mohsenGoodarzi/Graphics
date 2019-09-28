import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class Histogram {
    private final int RED = 0;
    private final int GREEN = 1;
    private final int BLUE = 2;
    private final int GRAYSCALE = 3;
    private int[] redBar;
    private int[] greenBar;
    private int[] blueBar;
    private int[] brightnessBar;
    private int[] cumulativeRed;
    private int[] cumulativeGreen;
    private int[] cumulativeBlue;
    private int[] cumulativeBrightness;
    private Image image; 

    public Histogram(Image image)
    {
        
    	this.image = image;
        redBar = new int[256];
        greenBar = new int[256];
        blueBar = new int[256];
        brightnessBar = new int[256];
        cumulativeRed = new int[256];
        cumulativeGreen = new int[256];
        cumulativeBlue = new int[256];
        cumulativeBrightness = new int[256];
        calcHistogram();
    }

    public void calcHistogram()
    {
        initBars();
        initCumulativeDistribution();
    }
    public int[] getRedBar()
    {
        return redBar;
    }
    public int[] getGreenBar()
    {
        return greenBar;
    }
    public int[] getBlueBar()
    {
        return blueBar;
    }
    public int[] getBrightnessBar()
    {

        return brightnessBar;
    }
    public int[] getCumulativeRed()
    {
        return cumulativeRed;
    }

    public int[] getCumulativeGreen()
    {
        return cumulativeGreen;
    }
    public int[] getCumulativeBlue()
    {
        return cumulativeBlue;
    }

    public int[] getCumulativeBrightness()
    {
        return cumulativeBrightness;
    }

    private void initCumulativeDistribution()
    {
        cumulativeRed[0] = redBar[0];
        cumulativeGreen[0] = greenBar[0];
        cumulativeBlue[0] = blueBar[0];
        cumulativeBrightness[0] = brightnessBar[0];
        for (int i = 1; i < 256; i++)
        {
                cumulativeRed[i] = cumulativeRed[i - 1] + redBar[i];
                cumulativeGreen[i] = cumulativeGreen[i - 1] + greenBar[i];
                cumulativeBlue[i] = cumulativeBlue[i - 1] + blueBar[i];
                cumulativeBrightness[i] = cumulativeBrightness[i - 1] + brightnessBar[i];
        }
    }

    private void initBars()
    {
        for (int i = 0; i < 256; i++) {
            brightnessBar[i] = 0;
            redBar[i]=0;
            greenBar[i] = 0 ;
            blueBar[i]=0;
        }
            
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
		
		WritableImage inverted_image = new WritableImage(width, height);
		PixelWriter inverted_image_writer = inverted_image.getPixelWriter();
		PixelReader image_reader=image.getPixelReader();
		
        for (int x = 0; x < image.getWidth(); x++)
        {
            for (int y = 0; y < image.getHeight(); y++)
            {
            	Color color = image_reader.getColor(x, y);
                 redBar[(int)(color.getRed()*255)]++;
                greenBar[(int)(color.getGreen()*255)]++;
                blueBar[(int)(color.getBlue()*255)]++;
                brightnessBar[(int)(color.getBrightness()* 255)]++;
            }
        }    
    }

}
