/*
2. Binary search

We need Time Complexity: O(log(m+n)) - for that we cannot check each element in both
arrays. and we should only check almost half elements. As we know Binary Search is 
used to reduce the searching to log(n) - n is mergeSize(totalSize).
We are basically searching for specific element. Binary Search can be the solution.

lets take an arrays:
nums1 = [1, 3 , 5, 7, 9]
nums2 = [2, 4]
mergeSize = 7

If we somehow find all the elements which are on left of a point lets say mid. We have 
to find mid point such that all elements that belong to leftside should be on its left and
eall lements that belong to right side are on its right. Here is one interesting thing if we 
only find all elements that should be left of mid point then remaining elements must be on 
its right :)

Now question is how many pointers are needed to do so!
1) One pointer points to specific mid from array1 - main mid
2) One pointer points to specific mid from array2. For specific mid1 which elements belong to 
left2 and right2 in array2.
3) Two Pointers for changing the mid1 - as in binary search we have two other helping pointers

**Need four pointers: lets call them Four Indecies!**

As arrays are sorted!
So selected mid1 from array1, we know all the elements on its left side in array1 are alreay on 
its left (means smaller or equal) :). We just need to find array2 elements for the selected
mid1. Lets add mid point as right align (mergeLength = total + 1). This means mid point itself 
is right element and is included in right side, then we can say mid - 1 is left. In Odd length1, 
mid1 will be either left or right. In code its considered on right. 

From above we can say left1 = mid1 - 1 (or left1 + 1 == mid1 is right of the array1)
same for array2.

Actual mid is left1 = mid-1 in that case.
1 2 4
    |
  mid1
first two on Left. mid - 1 is actual mid.

How we pick up mid2. As for mergeSize we can know how many elements will be on left by dividing
by 2. totalLeftSize  = mergeSize / 2. Only these numbers of elements on the left. so if we 
incorporate some left elements with mid1 - 1, remaining elements will be incorporated by mid2
(array2's mid). if we select some mid1 then... 

mid2 = totalLeftSize - mid1;

**With 2 pointers only and we create four values that are enough to check the conditions because 
arrays are sorted! - we dont need loop to check other far elements**

Also Why I emphasize on sorted array? lets describe by example
nums1 = [1, 3 , 5, 7, 9]
nums2 = [2, 4]
mergeSize = 7

1 2 3 4 5 7 9
      -

totalHalf = (7 + 1)/2 = 4    ... why +1 means when total is odd still keeping the actual mid on left side.
mid1 = (L + R) / 2 = 6/2 = 3
L, R are two helping pointers in binary search. initially L = 0, R = length

[1, 3 , 5, 7, 9]
           |
          mid1
As array1 is sorted, all elements on its left are already smaller (or equal)
and on its right are bigger (or equal)

[2, 4]
    |
   mid2

check1: if mid2 - 1 (left2) is smaller than mid1, then mid2 - 2 or mid2 - 3 etc are also smaller we dont need to check.
because arrays are sorted we donot need explicit check for each. just put one check on mid2 - 1 < mid1

Full arrays View:
1 2 3 4 5 7 9
          |
         mid1 -> rigth1 (basically we are taking that as original mid from both arrays)
still its not a mid!. we are missing more checks.

it looks like this below. 4 is considered on right of mid1 (as mid2 is right2).
Full arrays View:
         mid2 (right2)
          |
1 2 3 5 7 4 9	
    |
   mid1

this means this 4 right2 is not correctly placed.

check2: The selected right2 means that it must be bigger than left1 as left1 means it is on left side of both
selected right1 and right2. so we need this second condition too.

we have to check that right elements of array2 should also be on right of array1.
so condition is
left1 <= right2 && right1 >= left2    => if this true then partition is correct.

second iteration - mid1 should be smaller. 7 onwards elements are considered right as they are bigger, 
we keep two other pointers to discard them.
totalHalf = 4
mid1 = (0 + 3) / 2 = 1  ... selecting mid1(righ1) again
mid2 = 4 - 1 = 3

1 3 5 7 9
L | R 
 mid1

[2, 4] 
        |
       mid2
	   
mid2 is far away from length2. so better to run binary search on smaller array so that 0 to length
checks are enough. we donot let any mid go beyond 0 to length. So length - 1 give left value.
Other wise both end become positive or negative infinity. Also We do binary search on smaller array 
so that we can have some elements in start should be selected from each array as a left. but we 
can do other too.

lets complete this example anyhow.

mid2 is outside of array means all elements on left are smaller, and we take mid2 infinite as 
all from its right are bigger - this way few condition checks needed

mid2 - 1 is 4 which is not smaller then mid1

repeating.... this time its small so go towards right so moving L
mid1 = (1 + 3) / 2 = 2
mid2 = 4 - 2 = 2

1 3  5   7  9
  L  |R 
    mid1

[2, 4] 
        |
       mid2 (right2)
this satisfy conditions mid1 - 1 <= mid2 and mid1 >= mid2 - 1

mid2-1 is left2 
max(Left1,Left2) is answer.

Note: In example binary search on bigger array, lets do it on smaller array in code. 
*/
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int mergeSize = (nums1.length + nums2.length);//combine size
        int leftHalfMergeSize = (mergeSize + 1) / 2;//+1 means when total is odd still keeping the mid on left side.
        
        //we run binary search on smaller array
        //array1 smaller
        int[] array1 = nums1;
        int[] array2 = nums2;
        
        if(nums1.length > nums2.length) {
            array1 = nums2;
            array2 = nums1;
        }
        
        int length1 = array1.length;
        int length2 = array2.length;
        
        int left1Index = 0;
        int right1Index = length1;
        int mid1Index;
        int left1, right1;
        
        int mid2Index, left2, right2;
            
        while(true) {//true means there must exist a solution, otherwise put left1Index <= right1Index
            mid1Index = (left1Index + right1Index) / 2;//mid point is between left and right pointers
            mid2Index = leftHalfMergeSize - mid1Index;//including only remaining elements in left2Index
            
            left1 = mid1Index == 0 ? Integer.MIN_VALUE : array1[mid1Index - 1];
            right1 = mid1Index == length1 ? Integer.MAX_VALUE : array1[mid1Index];
            left2 = mid2Index == 0 ? Integer.MIN_VALUE : array2[mid2Index - 1];
            right2 = mid2Index == length2 ? Integer.MAX_VALUE : array2[mid2Index];
            
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