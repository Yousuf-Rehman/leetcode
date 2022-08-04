/*
Longest Substring Without Repeating Characters - Brute Force Hash Map

For repetition lookup, lets use hashMap - so no third loop needed
*/
class Solution {
    
    public int lengthOfLongestSubstring(String s) {
        int stringLength = s.length();
        
        int max = 0;//initialize by zero. If s is empty this will be returned. 
        
        Map<Character, Boolean> map;
        
        for(int i = 0; i < stringLength; i++) {
			
            map = new HashMap<>();
            
            for(int j = i; j < stringLength; j++) {
                
                if(!map.containsKey(s.charAt(j))) {
                    max = Math.max(max, j - i + 1);
                } else {
                    break;//characters repeated 
                }
                
                map.put(s.charAt(j), true);
            }
        }
        
        return max;
    }
}