# Finding-the-k-th-smallest-number-in-O-n-time
Finding the k(th) smallest number in O(n) time


This code finds the k(th) smallest number in an array in O(n) time.

The Algorithm used is -

1) Partition the given array and get the index of the pivot after partitioning.
2) If k=index  , then return array[k]
3) If k>index , then make recursive call to get k th smallest number in the sub array to the right of index
4) If k>index , then make recursive call to get k th smallest number in the sub array to the left of index

Also , the choice of pivot is very important here.
Usually , in order to make sure that this Algorithm splits the sub Array of half the length of original length,
then the pivot should be close to Median of the set.
For this , median of medians algorithm should be used to get a value close to median.
This will ensure that this Algorithm takes O(n) time.