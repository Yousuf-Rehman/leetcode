/*
Longest Substring Without Repeating Characters - Brute Force Simple

The Easiest Solution is Brute Force
current element is compared with next elements in array => need two loops 
(one handle the current pointer movement other handle the next pointer movement)

Also at next elements we have to check all previous character to check repetition
till current element => need one more loop

Total: 3 loops => worst it can run is all ellemnt are unique then all loops run till length
['a', 'b', 'c'];

'a' => 	[a,b],   =>  a b check
        [a,c],   =>  a b c check
		
'b' =>  [b,b],   =>  b c check

'c' =>  X

9 times loop run.

Time Complexity = O(n^3)
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
        
        if (stringLength < 2) {
            return stringLength;//if zero or one element then it is the only solution.
        }
		
        int max = 0;
        
        for(int i = 0; i < stringLength; i++) {
            for(int j = i + 1; j < stringLength; j++) {//j start from next element
                if(checkRepetition(s, i, j)) {
                    max = Math.max(max, j - i + 1);//+ 1 means add current element as well, with difference.
                }
            }
        }
        
        return max;
    }
}