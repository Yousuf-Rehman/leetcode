/*
3. Binary search Reduced code

run the method again by swapping arrays
*/
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int mergeSize = (nums1.length + nums2.length);//combine size
        int leftHalfMergeSize = (mergeSize + 1) / 2;//+1 means when total is odd still keeping the mid on left side.
        
        //nums1 is smaller array
        if(nums1.length > nums2.length) {
            /*run method again by swapping arrays and return the result immediately, so code dont go down*/
            return findMedianSortedArrays(nums2, nums1);
        }
        
        int left1Index = 0;
        int right1Index = nums1.length;
        int mid1Index;
        int left1, right1;
        
        int mid2Index, left2, right2;
            
        while(true) {//true means there must exist a solution, otherwise put left1Index <= right1Index
            mid1Index = (left1Index + right1Index) / 2;//mid point is between left and right pointers
            mid2Index = leftHalfMergeSize - mid1Index;//including only remaining elements in left2Index
            
            left1 = mid1Index == 0 ? Integer.MIN_VALUE : nums1[mid1Index - 1];
            right1 = mid1Index == nums1.length ? Integer.MAX_VALUE : nums1[mid1Index];
            left2 = mid2Index == 0 ? Integer.MIN_VALUE : nums2[mid2Index - 1];
            right2 = mid2Index == nums2.length ? Integer.MAX_VALUE : nums2[mid2Index];
            
            //partition is correct
            if(left1 <= right2 && right1 >= left2) {
                
                if(mergeSize % 2 == 0) {
                    return (Math.max(left1, left2) +
                            Math.min(right1, right2)) / 2.0;
                } else {
                    return Math.max(left1, left2);
                }
                
            } else if(left1 > right2) {
                right1Index = mid1Index - 1;
            }
            else {
                left1Index = mid1Index + 1;
            }            
        }
    }
}