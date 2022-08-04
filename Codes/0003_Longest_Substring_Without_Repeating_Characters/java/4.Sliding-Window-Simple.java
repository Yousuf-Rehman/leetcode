/*
Sliding Window Simple

Given a substring with a fixed end index in the string, maintain a HashMap 
to record the frequency of each character in the current substring. 
If any character occurs more than once, drop the leftmost characters 
until there are no duplicate characters.

When RightPointer move to one next element (immediate neighbour) on each 
iteration, so once the character get duplicated we can pin it out
on that iterartion because for repetition lookup, we are using HashMap.
So we will move LeftPointer until the duplication get fixed. While moving
Left Pointer, we will remove the elements from map as these are no longer
in the substring.

e.g. "pawkew"

After some Iter
pawkew --- map(p,a,w,k,e)
L    R

pawkew --- map(w,k,e)
   L R
   
just L+1 is not work. we need LeftPointer to move until it cross the element 
which is duplicating.

Time complexity : O(n) as max 2*n time traverse.

Right Pointer traverse array once - loop goes one time
does not repeat previously visited elements - n

Also Left pointer can traverse array max once and it
does not repeat previously visited elements. - n

For one time move in Rigfht pointer we are not traversing 
Left Pointer on the array fully. so its addition not 
multiplication.
*/
class Solution {
    
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        
        int stringLength = s.length();
        int max = 0;//initialize by zero. If s is empty this will be returned. 
        int leftPointer = 0, rightPointer = 0;
        
        while(rightPointer < stringLength) {
            
            /*moving Left pointer until we can able to put rightPointer char*/ 
            while(map.containsKey(s.charAt(rightPointer))) {
                map.remove(s.charAt(leftPointer));
                leftPointer++;
            }
            
            max = Math.max(max, rightPointer - leftPointer + 1);
            map.put(s.charAt(rightPointer), rightPointer);//adding keys
            
            rightPointer++;    
        }
        
        return max;
    }
}