/*
Longest Substring Without Repeating Characters - Brute Force Leet Code
*/
class Solution {
    
    private boolean checkRepetition(String s, int currentIndex, int endIndex) {
        Set<Character> chars = new HashSet<>();
        
        for(int i = currentIndex; i <= endIndex; i++) {
            char c = s.charAt(i);
            
            if(chars.contains(c)) {
                return false;
            }
            
            chars.add(c);
        }
        
        return true;
    }
    
    
    public int lengthOfLongestSubstring(String s) {
        int stringLength = s.length();
        
        int max = 0;//initialize by zero. If s is empty this will be returned. 
        
        for(int i = 0; i < stringLength; i++) {
			
            /*if array has 1 element still it will work as j = i but j = i + 1 wont 
              (as j < length is false). 1 element should be allowed in loop because 
              'max' should be updated.*/
            for(int j = i; j < stringLength; j++) {
                if(checkRepetition(s, i, j)) {
                    max = Math.max(max, j - i + 1);//+ 1 means calculate current element as well, with difference.
                }
            }
        }
        
        return max;
    }
}