
public class HSV {
	 private double hue;
	 private double saturation;
	 private double value;
	 
	 public HSV() {
		 this.hue=0;
		 this.saturation=0;
		 this.value=0;
	 }
	 public HSV(double hue,double saturation,double value) {
		 this.hue=hue;
		 this.saturation=saturation;
		 this.value=value;
	 }
	public double getHue() {
		return hue;
	}
	public void setHue(double hue) {
		this.hue = hue;
	}
	public double getSaturation() {
		return saturation;
	}
	public void setSaturation(double saturation) {
		this.saturation = saturation;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	
	 
}
