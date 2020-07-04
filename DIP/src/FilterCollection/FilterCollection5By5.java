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
public class FilterCollection5By5{

public static class CrossCorrelation implements IFilter {
	private int[][] crossCorrelation = { { -4, -1, 0, -1, -4 }, { -1, 2, 3, 2, -1 }, { 0, 3, 4, 3, 0 },
			{ -1, 2, 3, 2, -1 }, { -4, -1, 0, -1, -4 } };

	@Override
	public int getLength() {
		// TODO Auto-generated method stub
		return crossCorrelation.length;
	}

	@Override
	public int[][] getMatrix() {
		// TODO Auto-generated method stub
		return crossCorrelation;
	}

}

public static class HighPass implements IFilter {
	private int[][] highPass = { { 0, 0, 1, 0, 0 }, { 0, 1, 2, 1, 0 }, { 1, 2, -16, 2, 1 }, { 0, 1, 2, 1, 0 },
			{ 0, 0, 1, 0, 0 } };

	@Override
	public int getLength() {
		// TODO Auto-generated method stub
		return highPass.length;
	}

	@Override
	public int[][] getMatrix() {
		// TODO Auto-generated method stub
		return highPass;
	}
}


public static class EdgeDetectionSecondDerivation implements IFilter {
	private int[][] edgeDetectionSecondDerivation = { { 0, 0, 1, 0, 0 }, { 0, 1, 2, 1, 0 }, { 1, 2, -16, 2, 1 }, { 0, 1, 2, 1, 0 },
			{ 0, 0, 1, 0, 0 } };

	@Override
	public int getLength() {
		// TODO Auto-generated method stub
		return edgeDetectionSecondDerivation.length;
	}

	@Override
	public int[][] getMatrix() {
		// TODO Auto-generated method stub
		return edgeDetectionSecondDerivation;
	}
}


}