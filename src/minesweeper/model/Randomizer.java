package minesweeper.model;

import java.util.Arrays;
import java.util.Random;

import minesweeper.exceptions.AmountOutOfRange;

public class Randomizer {

	private Random rnd = new Random();
	
	public int[] getRandomMineNumbers(int minesToPlace, int numberOfFields) throws AmountOutOfRange {
		return getRandomMineNumbersWithoutOne(minesToPlace, numberOfFields, -1);
	}
	
	public int[] getRandomMineNumbersWithoutOne(int minesToPlace, int numberOfFields, int fieldNumberToSkip) throws AmountOutOfRange {
		if (numberOfFields <= 1)
			throw new AmountOutOfRange("Wrong number of fields");
		if (minesToPlace <= 0 || minesToPlace >= numberOfFields)
			throw new AmountOutOfRange("Wrong number of mines to place");
		
		
		int[] arrayOfRandomNumbers = new int[minesToPlace];
		int randomNumber;
		
		for (int i = 0; i < minesToPlace; i++) {
			do {
				randomNumber = rnd.nextInt(numberOfFields);
			} while (isUsed(randomNumber, arrayOfRandomNumbers, i, fieldNumberToSkip));
			
			arrayOfRandomNumbers[i] = randomNumber;
		}
		
		return arrayOfRandomNumbers;
	}
	
	private boolean isUsed(int randomNumber, int[] arrayOfRandomNumbers, int currentIndex, int additionalNumberToSkip) {
		if (randomNumber == additionalNumberToSkip)
			return true;
		for (int i = 0; i < currentIndex; i++) {
			if ( randomNumber == arrayOfRandomNumbers[i])
				return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		Randomizer rnd = new Randomizer();
		int[] t;
			
		try {
			long time1 = System.nanoTime();
			t = rnd.getRandomMineNumbers(9, 10);
			long time2 = System.nanoTime();
			Arrays.sort(t);
			System.out.println(Arrays.toString(t));
			System.out.println(time2-time1);
		} catch (AmountOutOfRange e) {
			e.printStackTrace();
		}
		
		
	}


}
