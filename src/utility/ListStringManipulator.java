package utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IllegalFormatException;
import java.util.List;

public class ListStringManipulator {
    
    public static String listToString(List<String> list) {
        if (list != null && list.size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (String str : list) {
                sb.append(str+", ");
            }
            sb.deleteCharAt(sb.length()-2);
            sb.deleteCharAt(sb.length()-1);
            return sb.toString();
        } else {
            return null;
        }
    }
    
    /**
     * Converts lists in String form to a List of Strings.
     * 
     * Format (must be consistent): 
     * Strings can be separated by spaces, commas, or a comma followed by one space.
     * 
     * @param str - comma- or space-separated list
     * @return
     * @throws Exception 
     */
    public static List<String> stringToList(String str) throws Exception {
        int numCommaSpaces = countSubstringOccurences(str, ", ");
        int numCommas = countSubstringOccurences(str, ",");
        int numSpaces = countSubstringOccurences(str, " ");
        
        if (numCommas > 0 && numCommaSpaces > 0) {
            throw new Exception("More than one format used. Please be consistent.");
        } else if (numCommaSpaces < 1 && numCommas < 1) {
            throw new Exception("The string can not be separated. Please format the string correctly.");
        }
        
        String[] list;
        if (numCommaSpaces > 0) {
            list = str.split(", ");
        } else if (numCommas > 0) {
            list = str.split(",");
        } else {
            list = str.split(" ");
        }
        
        return Arrays.asList(list);
    }
    
    public static int countSubstringOccurences(String str, String findStr) {
        int lastIndex = 0;
        int count = 0;
        while (lastIndex != -1) {
            lastIndex = str.indexOf(findStr,lastIndex);
            if(lastIndex != -1){
                count ++;
                lastIndex += findStr.length();
            }
        }
        return count;
    }
    
    private void checkStringError(String str) throws Exception {
        int numCommaSpaces = countSubstringOccurences(str, ", ");
        int numCommas = countSubstringOccurences(str, ",");
        int numSpaces = countSubstringOccurences(str, " ");
        
        if (numCommas > 0 && numCommaSpaces > 0) {
            throw new Exception("More than one format used. Please be consistent.");
        } else if (numCommaSpaces < 1 && numCommas < 1 && numSpaces <1) {
            throw new Exception("The string can not be separated. Please format the string correctly.");
        }
    }

}
