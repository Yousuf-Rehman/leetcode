/*
Sliding Window Optimized

As we also store index of elements in Map when we move
Right Pointer. So when find the duplicate issue, move the 
Left Pointer to the position where the duplicate character 
(first element duplicated) is located instead of moving 
Left Pointer one time again and again in loop. 
And we donot need to remove the elements in between
we can skip them by checking if Left Pointer index is greater
than this duplication.

e.g. "pawkew"

After some Iter
pawkew --- map(p,a,w,k,e)
L    R

pawkew --- map(w,k,e)
   L R
   
Directly skip the intermediate elements.
*/
class Solution {
    
    public int lengthOfLongestSubstring(String s) {
        int stringLength = s.length();
        
        int max = 0;//initialize by zero. If s is empty this will be returned. 
        
        Map<Character, Integer> map = new HashMap<>();
        
        int leftPointer = 0, rightPointer = 0;
        while(rightPointer < stringLength) {
            
            if(map.containsKey(s.charAt(rightPointer)) 
               && leftPointer <= map.get(s.charAt(rightPointer))) {//main check
                leftPointer = map.get(s.charAt(rightPointer));
                leftPointer++;
            }
            
            max = Math.max(max, rightPointer - leftPointer + 1);
            
            map.put(s.charAt(rightPointer), rightPointer);//update
            rightPointer++;    
        }
        
        return max;
    }
}