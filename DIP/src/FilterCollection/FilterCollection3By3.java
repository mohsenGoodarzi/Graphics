/*
 * File-name: FilterCollection3By3.java 
 * Version number: 0.1.0
 * Creation date: 01/07/2019
 * Last modification date: 04/07/2020 
 * Author’s name: Mohsen Goodarzi
 * Copyright: Mohsen Goodarzi  
 * Purpose of the program: Educational 
 */

package FilterCollection;
public class FilterCollection3By3{
	
 public static class LowPass implements IFilter {
	private int[][] lowPass = { { 1, 1, 1 }, { 1, 1, 1 }, { 1, 1, 1 } };

	@Override
	public int getLength() {
		return lowPass.length;
	}

	@Override
	public int[][] getMatrix() {
		// TODO Auto-generated method stub
		return lowPass;
	}
}

public static class edgeDetectionV implements IFilter {
	private int[][] edgeDetection = { { 1, 1, 1 }, {0, 0, 0 }, { -1, -1, -1 } };

	@Override
	public int getLength() {
		return edgeDetection.length;
	}

	@Override
	public int[][] getMatrix() {
		// TODO Auto-generated method stub
		return edgeDetection;
	}
}


public static class edgeDetectionH implements IFilter {
	private int[][] edgeDetection = { { 1, 0, -1 }, { 1, 0, -1 }, { 1, 0, -1 } };

	@Override
	public int getLength() {
		return edgeDetection.length;
	}

	@Override
	public int[][] getMatrix() {
		// TODO Auto-generated method stub
		return edgeDetection;
	}
}

public static class edgeDetectionSobelH implements IFilter {
	private int[][] edgeDetectionSobelH = { { 1, 0, -1 }, { 2, 0, -2 }, { 1, 0, -1 } };

	@Override
	public int getLength() {
		// TODO Auto-generated method stub
		return edgeDetectionSobelH.length;
	}

	@Override
	public int[][] getMatrix() {
		// TODO Auto-generated method stub
		return edgeDetectionSobelH;
	}

}

public static class edgeDetectionSobelV implements IFilter {
	private  int[][] edgeDetectionSobelV = { { 1, 2, 1 }, { 0, 0, 0 }, { -1, -2, -1 } };

	@Override
	public int getLength() {
		// TODO Auto-generated method stub
		return edgeDetectionSobelV.length;
	}

	@Override
	public int[][] getMatrix() {
		// TODO Auto-generated method stub
		return edgeDetectionSobelV;
	}
}

public static class Embossing implements IFilter {
	private int[][] embossing = { { -1, -1, -1 }, { -1, 1, 1 }, { -1, 1, 1 } };

	@Override
	public int getLength() {
		// TODO Auto-generated method stub
		return embossing.length;
	}

	@Override
	public int[][] getMatrix() {
		// TODO Auto-generated method stub
		return embossing;
	}

}
public static class GusianBlur implements IFilter {
	private int[][] gusianBlur = { { 1, 3, 1 }, { 3, 9, 3 }, { 1, 3, 1 } };

	@Override
	public int getLength() {
		// TODO Auto-generated method stub
		return gusianBlur.length;
	}

	@Override
	public int[][] getMatrix() {
		// TODO Auto-generated method stub
		return gusianBlur;
	}

}





}

