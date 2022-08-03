/*
num = [0,1,2,3];

Last time we come up to following solution:
0 will be compared with all remaining elements(j) [0,1], [0,2], [0,3]
1 will be compared with all remaining elements(j) [1,2], [1,3]
2 will be compared with all remaining elements(j) [2,3]
3 + 1 < size - loop ended

We have 'target' to compare with num[i] + num[j]. For each current element we are trying to find 
an element which after sum gives 'target'. Let's rephrase it a bit:

For current element(i) we are trying to find another element(j) from same array which matches target.
current element - target => finding another element. 'target' is the sum of both so bigger value.
target - current element => finding another element.

The lookup time in finding another element(j) for current element(i) is O(n) => j loop. Can we directly 
check if another element that matches the condition is present or not? 

Yes! Using HashMap, in which when we put an element it tells us 'Is the element present or not?' in O(1).

We can reduce the lookup time from O(n) to O(1) by trading space for speed. 
A hash map is well suited for this purpose because it supports fast lookup in near constant time. 
I say "near" because if a collision occurred, a lookup could degenerate to O(n) time. However, 
lookup in a hash map should be amortized O(1) time as long as the hash function was chosen carefully.

Total Iteration: n (looping on current elements) * 1 (for lookup another element) = n * 1 = n.

Lets first copy array elements in map then we loop on current array (current elements(i)) and find another 
element(j) from map. In Map, array elements are stored as keys, and for value just put index of the 
element. we will compare for current element If we have a key then its index should not be same. 
i != j. i and j are indices of elements.
*/
class Solution {
    public int[] twoSum(int[] nums, int target) {
        //storing elements in map, so that lookup is fast
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        
        for(int i = 0; i < nums.length; i++) {
            int anotherElement = target - nums[i];
            
            if(map.containsKey(anotherElement) && i != map.get(anotherElement)){
                return new int[] {i, map.get(anotherElement)};
            }
        }
        
        return null;//no solution
    }
}