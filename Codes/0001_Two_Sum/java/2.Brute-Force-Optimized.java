/*
num = [0,1,2,3];

In last brute force we were using i != j in 'if' statement which is over head on iterations:
like still loop is iterated for [0,0], [1,1], [2,2], [3,3] although the 'if' statement handled it - is is an overhead, 


Other overheads 
like 
0 will be compared with all others(j) [0,1], [0,2], [0,3]
1 will be compared with all others(j) [1,0], [1,2], [1,3]
2 will be compared with all others(j) [2,0], [2,1], [2,3]
3 will be compared with all others(j) [3,0], [3,1], [3,2]
see. like [0,1] <-> [1,0] etc are repeatedly compared (num[i] + num[j] == target), order does not matter in sum.

So total it run for all possible iterations; n*n = 4*4  = 16 iters
0 - [0,0], [0,1], [0,2], [0,3]
1 - [1,0], [1,1], [1,2], [1,3]
2 - [2,0], [2,1], [2,2], [2,3]
3 - [3,0], [3,1], [3,2], [3,3]
 

If 0 is checked with all other elements in first iteration then it should not be checked in other iterations. like we should do following
0 will be compared with all remaining elements(j) [0,1], [0,2], [0,3]
1 will be compared with all remaining elements(j) [1,2], [1,3]
2 will be compared with all remaining elements(j) [2,3]
3 is checked with nothing.

So current element(i) is compared with remaining/coming elements(j)

j is basically pointing to other elements so start it from coming elements than current element. also other element(j) cannot be current element(i)
so condition 'you may not use the same element twice.' is satisfied too(so not need i != j check again) .

so simply j = i + 1.
current element is compared with all other coming elements, not previously checked

total iterartions: n*(n-1)/2 = (4*3)/2 = 6
huge optimization :)
*/
class Solution {
    public int[] twoSum(int[] nums, int target) {
        
        for(int i = 0; i < nums.length; i++)
            for(int j = i + 1; j < nums.length; j++) {
                if(nums[i] + nums[j] == target) {
                    return new int[] {i, j};//return array [i,j]
                }
            }
        
        return null;//no solution
    }
}