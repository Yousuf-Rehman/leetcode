class Solution {
    public int[] twoSum(int[] nums, int target) {
        
        for(int i = 0; i < nums.length; i++)
            for(int j = 0; j < nums.length; j++) {
                //you cannot use the same element twice so i != j
                if(i != j && nums[i] + nums[j] == target) {
                    return new int[] {i, j};//return array [i,j]
                }
            }
        
        return null;//no solution
    }
}