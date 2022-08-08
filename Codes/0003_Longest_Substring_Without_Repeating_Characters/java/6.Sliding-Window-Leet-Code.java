/*
Sliding Window LeetCode

name changed: rightPointer => j
              leftPointer  => i

Whenever there is duplication then leftPointer is updated 
from map till duplication and we have to also move leftPointer one 
next element index (leftPointer++). So we can store directly
in map one next element index.

Instead of checking leftPointer <= map.get(s.charAt(rightPointer))
use Math.max(). If leftPointer index is smaller than duplicated index,
update leftPointer otherwise leftPointer remain same.
*/
class Solution {
    
    public int lengthOfLongestSubstring(String s) {
        int stringLength = s.length();

        int max = 0;//initialize by zero. If s is empty this will be returned. 
        
        Map<Character, Integer> map = new HashMap<>();
        
        for(int j = 0, i = 0; j < stringLength; j++) {
            
            if(map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            
            max = Math.max(max, j - i + 1);
            map.put(s.charAt(j), j + 1);//update if duplicate or add new
        }
        
        return max;
    }
}