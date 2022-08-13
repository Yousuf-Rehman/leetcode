/*
1. Median of Two Sorted Arrays - Merging Arrays

Easiest Solution Can be to merge two arrays and find the median afterwards.
But as you can see we are traversing each index of both arrays once. So,
Time Complexity: O(m+n)

This solution is just for the sake of completion but its not the actual answer 
for the question.
*/
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int mergeSize = (nums1.length + nums2.length);//combine size
        
        int[] arr = new int[mergeSize];
		
        int Index1 = 0, Index2 = 0;
        int num1, num2;
        for(int i = 0; i < mergeSize; i++) {
            /*If nums1 out of index then storing +infinity so that small values are stored first*/
            num1 = Index1 == nums1.length ? Integer.MAX_VALUE : nums1[Index1];
            num2 = Index2 == nums2.length ? Integer.MAX_VALUE : nums2[Index2];

            //storing small values first
            if(num1 < num2) {
              arr[i] = nums1[Index1];
              Index1++;
            } else {
              arr[i] = nums2[Index2];
              Index2++;
            }
        }
		
        /*All the values are in the arr (in ascending order)*/
        if(mergeSize % 2 == 0) {
            int mid = mergeSize / 2;//right align(means one element is already taken from right)
            return (arr[mid - 1] + arr[mid]) / 2.0;			
        } else {
            int mid = mergeSize / 2;
            return arr[mid];
        }
    }
}