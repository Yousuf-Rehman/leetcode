/*
num = [0,1,2,3];

Instead of copying elements in naother loop, we can do it in same loop.
while moving in current loop we can compare current element with all 
other elements added in map. If current element not found in map then
we add it in map and move forward.

The current elment cannot be compare with current element from map as it is
not yet added, so we can remove i != map.get(anotherElement) from 'if' 
statement
*/
class Solution {
    public int[] twoSum(int[] nums, int target) {
        //storing elements in map, so that lookup is fast
        Map<Integer, Integer> map = new HashMap<>();
        
        for(int i = 0; i < nums.length; i++) {
            int anotherElement = target - nums[i];
            
            if(map.containsKey(anotherElement)){
                return new int[] {i, map.get(anotherElement)};
            }
			
            map.put(nums[i], i);
        }
        
        return null;//no solution
    }
}