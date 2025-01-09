		
	/**
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also
	 * set the instance variables algorithm in the superclass.
	 *  
	 * @param pts   input array of integers
	 */
	public QuickSorter(Point[] pts)
	{
		super(pts);
		algorithm = "quicksort";
	}
		
	/**
	 * Carry out quicksort on the array points[] of the AbstractSorter class. 
	 *
	 */
	@Override
	public void sort()
	{
		quickSortRec(0, points.length - 1);
	}
	
	
	/**
	 * Operates on the subarray of points[] with indices between first and last.
	 *
	 * @param first  starting index of the subarray
	 * @param last   ending index of the subarray
	 */
	private void quickSortRec(int first, int last)
	{
		//if the first element is at the end, end the sort
		if (first >= last) {
			return;
		}
		
		//find the partition point in the array
		int lowEnd = partition(first, last);
		
		//recursivley call quickSort on the low and high partitions
		quickSortRec(first, lowEnd - 1);
		quickSortRec(lowEnd, last);
	}
	
	
	/**
	 * Operates on the subarray of points[] with indices between first and last.
	 *
	 * @param first
	 * @param last
	 * @return int first
	 */
	private int partition(int first, int last)
	{
		//find the mid point
		int mid = first + (last - first) / 2;
		//find the pivot point at the mid point
		Point pivot = points[mid];
		
		//while done is still false
		while (first <= last) {
			//while points[first] < pivot
			while (pointComparator.compare(points[first], pivot) < 0) {
				//increment first
				first++;
			}
			//while pivot < points[last]
			while(pointComparator.compare(points[last], pivot) > 0) {
				//decrement last
				last--;
			}
			//if we have gone through the whole array
			if (first <= last) {
				//use the swap method to swap the values
				super.swap(first, last);
				//increment first
				first++;
				//decrement last
				last--;
			}
		}
		//retuen the first int
		return first;
	}
}
