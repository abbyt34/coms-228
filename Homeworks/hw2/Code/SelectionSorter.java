	
	/**
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also
	 * set the instance variables algorithm in the superclass.
	 * 
	 * @param pts 
	 */
	public SelectionSorter(Point[] pts) 
	{
		super(pts);
		algorithm = "selection sort";
	}	
	
	/**
	 * Apply selection sort on the array points[] of the parent class AbstractSorter. 
	 *
	 */
	@Override
	public void sort()
	{
		//find the length of the super points array
		int n = points.length;
		
		//for the length of the points array
		for (int i = 0; i < n - 1; i++) {
			//start with the minIndex at the beginning
			int minIndex = i;
			
			//check each next point
			for (int j = i + 1; j < n; j++) {
				//if points[j] < points[minIndex]
				if (points[minIndex].compareTo(points[j]) > 0){
					//swap minIndex with index j
					minIndex = j;
				}
			}
			//use the AbstractSorter swap function to swap the values
			super.swap(i, minIndex);
		}
	}
