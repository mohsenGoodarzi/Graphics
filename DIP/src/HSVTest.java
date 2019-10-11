import javafx.scene.paint.Color;

public class HSVTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		HSV hsv = new HSV();

		hsv = toHSV(150, 120, 60);

		System.out.println("H:" + hsv.getHue() + " S:" + hsv.getSaturation() + " V:" + hsv.getValue());
		// H:26.08695652173913 S:0.6804733727810651 V:169.0 toHSV(169,104,54);
		hsv = toHSV(150, 150, 150);
		System.out.println("H:" + hsv.getHue() + " S:" + hsv.getSaturation() + " V:" + hsv.getValue());
		hsv = toHSV(60, 120, 150);
		System.out.println("H:" + hsv.getHue() + " S:" + hsv.getSaturation() + " V:" + hsv.getValue());
		Color color = toRGB(hsv);
		System.out.println("Red:" + color.getRed()*255 + " Green:" + color.getGreen()*255 + " Blue:" + color.getBlue()*255);
	}

	public static HSV toHSV(double red, double green, double blue) {
		HSV hsv = new HSV();

		double computedH = 0;
		double computedS = 0;
		double computedV = 0;

		// remove spaces from input RGB values, convert to int

		double minRGB = Math.min(red, Math.min(green, blue));
		double maxRGB = Math.max(red, Math.max(green, blue));
		double delta, hue = 0;
		// Black-gray-white image
		if (minRGB == maxRGB) {
			computedV = minRGB;
			hsv.setValue(minRGB / 255.0d);
			return hsv;
		} else {

			if (red == minRGB)
				delta = green - blue;
			else if (blue == minRGB)
				delta = red - green;
			else
				delta = blue - red;

			if (red == minRGB)
				hue = 3;
			else if (blue == minRGB)
				hue = 1;
			else
				hue = blue - red;

			computedH = 60 * (hue - delta / (maxRGB - minRGB));
			computedS = (maxRGB - minRGB) / maxRGB * 100;
			computedV = maxRGB / 255d * 100;// computed value must be mapped into the range [0-100]

			hsv.setHue(computedH);
			hsv.setSaturation(computedS);
			hsv.setValue(computedV);
			return hsv;

		}

	}

	public static Color toRGB(HSV hsv) {

		double value, hue, saturation, c = 0, x = 0, m = 0;
		hue = hsv.getHue();
		value = hsv.getValue() / 100;
		saturation = hsv.getSaturation() / 100;
		c = value * saturation;
		x = c * (1 - Math.abs(((hue / 60) % 2) - 1));
		m = value - c;
		double notRed = 0, notGreen = 0, notBlue = 0;

		if (hue >= 0 && hue < 60) {
			notRed = c;
			notGreen = x;
			notBlue = 0;
		}

		if (hue >= 60 && hue < 120) {
			notRed = x;
			notGreen = c;
			notBlue = 0;
		}
		if (hue >= 120 && hue < 180) {
			notRed = 0;
			notGreen = c;
			notBlue = x;
		}

		if (hue >= 180 && hue < 240) {
			notRed = 0;
			notGreen = x;
			notBlue = c;
		}
		if (hue >= 240 && hue < 300) {
			notRed = x;
			notGreen = 0;
			notBlue = c;
		}
		if (hue >= 300 && hue < 360) {
			notRed = c;
			notGreen = 0;
			notBlue = x;
		}

		double red = 0, green = 0, blue = 0;
		red = ((notRed + m) * 255);
		green =((notGreen + m) * 255);
		blue = ((notBlue + m) * 255);
		//System.out.println("RED:" + red + " Green:" + green + " Blue:" + blue);
		Color color = new Color(red/255, green/255, blue/255, 1.0);
		System.out.println("HUE:" + color.getHue() + " SATURATION:" + color.getSaturation() + " VALUE:" + color.getBrightness());
		return color;
	}

}
