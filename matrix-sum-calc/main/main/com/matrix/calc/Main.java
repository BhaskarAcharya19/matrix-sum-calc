package com.matrix.calc;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main {

	private int[][] matrix = new int[3][3];

	public boolean isValid(int row, int col) {

		return row >= 0 && row <= 2 && col >= 0 && col <= 2;
	}

	private int findSum() {

		int maxValue = 0;
		for (int i = 0; i < matrix.length; i++) {

			for (int j = 0; j < matrix[i].length; j++) {

				int tempI = i;
				int tempJ = j + 1;
				int tempValue = 0;

				// LEFT-RIGHT TRAVERSE
				if (isValid(tempI, tempJ + 1)) {
					tempValue = matrix[i][j] + matrix[tempI][tempJ] + matrix[tempI][tempJ + 1];
					maxValue = tempValue > maxValue ? tempValue : maxValue;
				}
				if (isValid(tempI + 1, tempJ)) {
					tempValue = matrix[i][j] + matrix[tempI][tempJ] + matrix[tempI + 1][tempJ];
					maxValue = tempValue > maxValue ? tempValue : maxValue;
				}
				if (isValid(tempI - 1, tempJ)) {
					tempValue = matrix[i][j] + matrix[tempI][tempJ] + matrix[tempI - 1][tempJ];
					maxValue = tempValue > maxValue ? tempValue : maxValue;
				}

				// LEFT-RIGHT TRAVERSE
				tempI = i;
				tempJ = j - 1;

				if (isValid(tempI, tempJ - 1)) {

					tempValue = matrix[i][j] + matrix[tempI][tempJ] + matrix[tempI][tempJ - 1];
					maxValue = tempValue > maxValue ? tempValue : maxValue;
				}
				if (isValid(tempI - 1, tempJ)) {
					tempValue = matrix[i][j] + matrix[tempI][tempJ] + matrix[tempI - 1][tempJ];
					maxValue = tempValue > maxValue ? tempValue : maxValue;
				}
				if (isValid(tempI + 1, tempJ)) {
					tempValue = matrix[i][j] + matrix[tempI][tempJ] + matrix[tempI + 1][tempJ];
					maxValue = tempValue > maxValue ? tempValue : maxValue;
				}

				// TOP-DOWN TRAVERSE

				tempI = i + 1;
				tempJ = j;

				if (isValid(tempI + 1, tempJ)) {

					tempValue = matrix[i][j] + matrix[tempI][tempJ] + matrix[tempI + 1][tempJ];
					maxValue = tempValue > maxValue ? tempValue : maxValue;
				}
				if (isValid(tempI, tempJ + 1)) {
					tempValue = matrix[i][j] + matrix[tempI][tempJ] + matrix[tempI][tempJ + 1];
					maxValue = tempValue > maxValue ? tempValue : maxValue;
				}
				if (isValid(tempI, tempJ - 1)) {
					tempValue = matrix[i][j] + matrix[tempI][tempJ] + matrix[tempI][tempJ - 1];
					maxValue = tempValue > maxValue ? tempValue : maxValue;
				}

				// BOTTOM-UP TRAVERSE

				tempI = i - 1;
				tempJ = j;

				if (isValid(tempI - 1, tempJ)) {

					tempValue = matrix[i][j] + matrix[tempI][tempJ] + matrix[tempI - 1][tempJ];
					maxValue = tempValue > maxValue ? tempValue : maxValue;
				}

				if (isValid(tempI, tempJ + 1)) {
					tempValue = matrix[i][j] + matrix[tempI][tempJ] + matrix[tempI][tempJ + 1];
					maxValue = tempValue > maxValue ? tempValue : maxValue;
				}

				if (isValid(tempI, tempJ - 1)) {
					tempValue = matrix[i][j] + matrix[tempI][tempJ] + matrix[tempI][tempJ - 1];
					maxValue = tempValue > maxValue ? tempValue : maxValue;
				}

			}
		}

		return maxValue;
	}

	private void validateAndUpdateMatrix(String[] arr, int rowNum) throws Exception {

		if (arr == null) {
			throw new Exception("Empty values for row " + (rowNum+1));
		}

		if (arr.length != 3) {
			throw new Exception("Invalid number of values for row " + (rowNum + 1));
		}

		for (int i = 0; i < arr.length; i++) {

			int temp = Integer.parseInt(arr[i]);
			if (temp < 0 || temp > 9) {
				throw new Exception("The value should be between 0-9 for row " + (rowNum + 1));
			}
			matrix[rowNum][i] = temp;

		}

	}

	public void initGrid() throws Exception {
		
		try (InputStream input = Main.class.getClassLoader().getResourceAsStream("app.properties")) {

			Properties prop = new Properties();

			if (input == null) {
				System.out.println("Sorry, unable to find app.properties");
				return;
			}

			prop.load(input);
			validateAndUpdateMatrix(prop.getProperty("grid.1").split(","), 0);
			validateAndUpdateMatrix(prop.getProperty("grid.2").split(","), 1);
			validateAndUpdateMatrix(prop.getProperty("grid.3").split(","), 2);

		} catch (IOException ex) {
				throw new Exception("There was an error initializing the file.");
		}
	}

	public static void main(String[] args) {

		Main m = new Main();
		try {
			m.initGrid();
			int maxValue = m.findSum();
			System.out.println("The max sum is " + maxValue);
		} catch (Exception e) {
			System.out.println("ERROR:"  + e.getMessage());
		}

	}
}