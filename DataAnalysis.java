import java.util.ArrayList;

public class DataAnalysis 
{
	// Helper class for finding the mode
	private static class ModeHelper
	{
		private ArrayList<Double> modeList; // Current mode(s)
		private int maxCount; // Current mode(s) appear this number of times in the list
		private int count; // The element we are currently counting has appeared this number of times
		private double counting; // This is the element we are currently counting
		
		// Constructor; takes first element of list
		private ModeHelper(double first)
		{
			modeList = new ArrayList<Double>();
			maxCount = 0;
			count = 0;
			counting = first;
		}
			
		// Attempts to count current element; returns true if still counting, false otherwise
		private boolean countCheck(double current)
		{
			boolean completed;
			if (counting == current)
			{
				count++;
				completed = false;
			}
			else completed = true;
			return completed;
		}
			
		// Assumes counting is complete; checks current element against list of modes
		private void modeCheck(double current)
		{
			if (count >= maxCount) // Counting complete; check if we have a mode
			{
				if (count > maxCount) // Reset list if the mode appears more times than the current modes
				{
					modeList.clear();
					maxCount = count;
				}
				modeList.add(counting); // Always add new mode to the list
			}
			count = 1; // Reset count for next element
			counting = current;
		}
		
		// Returns list of modes
		private ArrayList<Double> getModeList()
		{
			return modeList;
		}
	}
	
	// Statistics
	public static int getNumEntries(ArrayList<Double> gradeList)
	{
		return gradeList.size();
	}
	
	public static double getHighestGrade(ArrayList<Double> gradeList)
	{
		return gradeList.get(gradeList.size() - 1);
	}
	
	public static double getLowestGrade(ArrayList<Double> gradeList)
	{
		return gradeList.get(0);
	}
	
	public static double getMean(ArrayList<Double> gradeList)
	{
		double mean = 0;
		for (int i = 0; i < gradeList.size(); i++) mean += gradeList.get(i);
		return mean/gradeList.size();
	}
	
	public static double getMedian(ArrayList<Double> gradeList)
	{
		int mid = gradeList.size() / 2;
		double median;
		if (gradeList.size() % 2 == 0) median = (gradeList.get(mid) + gradeList.get(mid - 1)) / 2;
		else median = gradeList.get(mid);
		return median;
	}
	
	public static ArrayList<Double> getMode(ArrayList<Double> gradeList)
	{
		ModeHelper modeHelper = new ModeHelper(gradeList.get(0)); // Helper class for finding mode
		for (int i = 0; i < gradeList.size(); i++) // See helper class for details
		{
			double current = gradeList.get(i);
			boolean completed = modeHelper.countCheck(current);
			if (completed) modeHelper.modeCheck(current);
		}
		modeHelper.modeCheck(gradeList.get(gradeList.size() - 1)); // Check final element
	
		ArrayList<Double> modeList = modeHelper.getModeList(); // Don't return the entire list
		if (modeList.size() == gradeList.size()) modeList.clear();
		return modeHelper.getModeList();
	}
	
	// Printing
	public static void printList(ArrayList<Double> gradeList)
	{
		System.out.print("Our list: ");
		if (gradeList.size() != 0)
		{
			for (int i = 0; i < gradeList.size() - 1; i++) System.out.print(gradeList.get(i) + ", "); // Maybe send to error log?
			System.out.print(gradeList.get(gradeList.size() - 1));
		}
		System.out.println();
	}
	
	public static void printStats(ArrayList<Double> gradeList)
	{
		System.out.println("Number of entries: " + getNumEntries(gradeList));
		System.out.println("Highest grade: " + getHighestGrade(gradeList));
		System.out.println("Lowest grade: " + getLowestGrade(gradeList));
		System.out.println("Mean: " + getMean(gradeList));
		System.out.println("Median: " + getMedian(gradeList));
	}
	
	public static void printModes(ArrayList<Double> gradeList)
	{
		System.out.print("Mode(s): ");
		ArrayList<Double> modeList = getMode(gradeList);
		if (modeList.size() != 0)
		{
			for (int i = 0; i < modeList.size() - 1; i++) System.out.print(modeList.get(i) + ", ");
			System.out.print(modeList.get(modeList.size() - 1));
		}
		System.out.println();
	}
	
	// Insertion and deletion
	public static void addGrade(ArrayList<Double> gradeList, double grade)
	{
		gradeList.add(grade);
	}
	
	public static boolean deleteGrade(ArrayList<Double> gradeList, double grade)
	{
		boolean success = false;
		for (int i = 0; i < gradeList.size(); i++)
		{
			if (grade == gradeList.get(i))
			{
				gradeList.remove(i);
				success = true;
			}
		}
		return success; // If success is false, report to error log
	}
	
	// Testing
	public static void main(String[] args)
	{
		ArrayList<Double> sampleList1 = new ArrayList<Double>();
		addGrade(sampleList1, 95.5);
		sampleList1.add(82.2);
		sampleList1.add(73.6);
		sampleList1.add(55.2);
		sampleList1.add(43.2);
		sampleList1.sort(null);
		
		ArrayList<Double> sampleList2 = new ArrayList<Double>();
		sampleList2.add(23.0);
		sampleList2.add(24.0);
		sampleList2.add(85.0);
		sampleList2.add(32.0);
		sampleList2.add(96.0);
		sampleList2.add(59.0);
		sampleList2.add(60.0);
		sampleList2.add(61.0);
		sampleList2.add(82.0);
		sampleList2.add(49.0);
		sampleList2.sort(null);
		
		/*
		printList(sampleList1);
		printStats(sampleList1);
		printModes(sampleList1);
		System.out.println();
		*/
		
		/*
		printList(sampleList2);
		printStats(sampleList2);
		printModes(sampleList2);
		System.out.println();
		*/
		
		printList(sampleList1);
		deleteGrade(sampleList1, 95.5);
		deleteGrade(sampleList1, 82.2);
		if (!(deleteGrade(sampleList1, 69420))) System.out.println("UH OH STINKY");
		sampleList1.sort(null);
		printList(sampleList1);
	}
}
