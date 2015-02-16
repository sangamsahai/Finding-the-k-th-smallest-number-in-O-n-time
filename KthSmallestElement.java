import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class KthSmallestElement {

	/**
	 * @param args
	 */
	public static int[] arr;//this is the global array on which partitioning will be performed

	//this function returns a value close to the median
	//In partitioning routine, this values is supposed to be used as pivot
	public static int getPivotElementCloseToMedian(int[]arr1)
	{
		//base condition starts- directly calculate the median and return the median
		if(arr1.length<=9)
		{
			Arrays.sort(arr1);
			return arr1[arr1.length/2];
		}
		//base condition ends
		
		// This array list will hold the medians of groups if 5 elemnts each
		ArrayList<Integer> medianList=new ArrayList<Integer>();
		
		int big_counter=0;
		int counter=0;
		while(counter<arr1.length)
		{
			int[] temp=new int[Math.min(5, arr1.length-big_counter)];
			int temp_counter=0;

			for(int i=counter;i<=counter+(temp.length-1);i++)
			{
				temp[temp_counter]=arr1[i];
				temp_counter++;
				big_counter++;
				
			}//end of for loop

			Arrays.sort(temp);
			int median_temp=temp[temp.length/2];
			medianList.add(median_temp);

			counter+=5;
		}//end of while

		int medianList_length=medianList.size();
		int[] newMedianList=new int[medianList_length];
		for(int i=0;i<medianList_length;i++)
		{
			newMedianList[i]=medianList.get(i);//converting the array list of medians to array
		}

		return getPivotElementCloseToMedian(newMedianList); 
	}//end of function

    
	//Followng method partitions the arr array starting and ending at 'start' and 'end' indices respectively
	public static int partition(int start,int end)
	{
		int length=end-start+1;
		int[] tempArray=new int[length];

		int tempStart=start;
		for(int i=0;i<length;i++)
		{
			tempArray[i]=arr[tempStart];
			tempStart++;
		}
		
		int pivotElement=getPivotElementCloseToMedian(tempArray);

		int reqdIndex=-1;
		for(int i=0;i<arr.length;i++)
		{
			if(arr[i]==pivotElement)
			{
				reqdIndex=i;break;
			}
		}

		//swapping pivot element to last
		int temp=arr[reqdIndex];
		arr[reqdIndex]=arr[end];
		arr[end]=temp;

		int counter=start-1;
		for(int i=start;i<end;i++)
		{
			if(arr[i]<pivotElement)
			{
				int tempr=arr[counter+1];
				arr[counter+1]=arr[i];
				arr[i]=tempr;
				counter++;
			}
		}

		int tempq=arr[counter+1];
		arr[counter+1]=arr[end];
		arr[end]=tempq;


		return counter+1;//pivot element is now placed at index (counter+1)

	}


	public static void printArray(int[] arr)
	{
		for(int i=0;i<arr.length;i++)
		{
			System.out.println(arr[i]);
		}
	}

	//function to compute the k(th) smallest number
	public static  int getKthSmallestNumber(int k,int start,int end)
	{
		k-=1;//as we are starting with index 0 and not with 1
		int index=partition(start, end);
		if(k==index)return arr[index];
		else if(k>index) return getKthSmallestNumber(k+1,index+1,end);
		else return getKthSmallestNumber(k+1,start,index-1);

	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner scanner=new Scanner(System.in);
		System.out.println("enter the number of elemnts in the array");
		int arrayLength=scanner.nextInt();
		arr=new int[arrayLength];
		for(int i=0;i<arrayLength;i++)
		{
			System.out.println("enter the "+(i+1)+"th element");
			arr[i]=scanner.nextInt();
		}

		System.out.println("enter the value of k for k(th) smallest element");
		int k=scanner.nextInt();
		

		System.out.println(getKthSmallestNumber(k,0,arr.length-1));

	}

}
