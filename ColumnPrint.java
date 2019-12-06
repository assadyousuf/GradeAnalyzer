import java.util.ArrayList;
import java.util.Collections;

/**
 * Class provides a static method to print data in columns.
 * @author brand
 *
 */
public class ColumnPrint 
{
	// Constants
	private static final int COLUMN_AMOUNT = 4;
	private static final int TEST_AMOUNT = 20;
	
	/**
	 * Testing driver for this file.
	 * @param args Command line arguments are stored here, but this is not applicable for this method.
	 */
	public static void main(String[] args)
	{
		// Run a number of tests equal to the set test amount
		for (int i = 0; i < TEST_AMOUNT; i++)
		{
			ArrayList<Double> dataSet = getTestData(i);
			dataSet.sort(Collections.reverseOrder());
			System.out.println(getDataInColumns(dataSet));
		}
	}
	
	/**
	 * Returns the data in the given data set as a string; uses column format.
	 * @param dataSet The data to be formatted.
	 * @return The formatted data.
	 */
	public static String getDataInColumns(ArrayList<Double> dataSet)
	{
		ColumnPrinter columnPrinter = new ColumnPrinter(dataSet);
		columnPrinter.initialize();
		return columnPrinter.getText();
	}
	
	/**
	 * Method that returns a data set from zero to the given value minus one. If the value isn't positive, an empty set is returned.
	 * @return The generated data set.
	 */
	private static ArrayList<Double> getTestData(int size)
	{
		ArrayList<Double> dataSet = new ArrayList<Double>();
		for (int i = 0; i < size; i++)
		{
			dataSet.add((double)i);
		}
		return dataSet;
	}
	
	/**
	 * Helper class that prints data in columns.
	 * @author brand
	 */
	private static class ColumnPrinter
	{
		private ArrayList<Double> dataSet;
		private String text;
		private int originalRemainder;
		private int currentRemainder;
		private int originalRemaining;
		private int currentRemaining;
		private int index;
		private int lines;
		private int size;
		
		/**
		 * Constructs a printer object with the given data set; other values are not initialized automatically.
		 */
		private ColumnPrinter(ArrayList<Double> dataSet)
		{
			this.dataSet = dataSet;	
		}
		
		/**
		 * Initializes all other member variables based on the currently loaded data set.
		 */
		private void initialize()
		{			
			text = ""; // Text begins as empty string
			
			// Remainder and remaining
			originalRemainder = dataSet.size() % COLUMN_AMOUNT;
			currentRemainder = originalRemainder;
			originalRemaining = dataSet.size();
			currentRemaining = originalRemaining;
			
			// Index, lines, and size
			index = 0; // Begins at the zero index
			lines = (int)Math.ceil(((double)dataSet.size() / COLUMN_AMOUNT));
			size = dataSet.size();
		}
		
		/**
		 * Advances the index; this can change the current remainder.
		 */
		private void next()
		{
			index = index + lines;
			if (currentRemainder > 0) currentRemainder--;
			else if (size > 4) index--;
		}
				
		/**
		 * Returns the formatted data.
		 * @return The formatted data.
		 */
		private String getText()
		{
			currentRemaining = originalRemaining; // Printing is done when we run out of values; reset this total before we start
			text = ""; // Begin with a blank string
			getTextOuterLoop(); // Begin outer loop
			return text;
		}
		
		/**
		 * Runs the outer loop of the get text function.
		 */
		private void getTextOuterLoop()
		{
			for (int i = 0; i < lines; i++)
			{
				currentRemainder = originalRemainder; // Reset the remainder and update the index for each row
				index = i;
				text += dataSet.get(i) + "\t"; // Print the first value in the row, and decrement the counter
				currentRemaining--;
				getTextInnerLoop(); // Perform the inner loop
				text += "\n"; // New row
			}
		}
		
		/**
		 * Runs the inner loop of the get text function.
		 */
		private void getTextInnerLoop()
		{
			for (int j = 0; j < COLUMN_AMOUNT - 1 && currentRemaining > 0; j++) // Loop once per remaining column, or until we run out of values
			{
				next(); // Move to next column and print the value
				text += dataSet.get(index) + "\t"; 	
				currentRemaining--; // A value has been printed, so decrement the remaining number of values
			}
		}
		
		/**
		 * Prints debug info for the printer by showing the values of its member variables.
		 */
		@SuppressWarnings("unused")
		private void printDebugInfo()
		{
			System.out.println("Lines = " + lines);
			System.out.println("Original Remainder = " + originalRemainder);
			System.out.println("Current Remainder = " + currentRemainder);
			System.out.println("Index = " + index);
			System.out.println();
		}
	}
}
