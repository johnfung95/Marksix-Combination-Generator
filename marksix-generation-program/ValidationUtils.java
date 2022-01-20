import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Arrays;

// include global utilities and functions for calling
public class ValidationUtils {
    
    public boolean checkDuplicates(String[] arr) {
        // if duplicate number found then return false.
        if (arr.length != 0) {
            for (int i = 0; i < arr.length; i++) {
                for (int j = i + 1; j < arr.length; j++) {
                    if (arr[i].equals(arr[j])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean checkSetsDuplication(int[][] allSets, int[] set) {
        boolean isDuplicated = false;

        for(int i = 0; i < allSets.length; i++) {
            isDuplicated = Arrays.equals(allSets[i], set);
            if (isDuplicated) {
                break;
            }
        }
        return isDuplicated;
    }

    public boolean IsValidNumberFormat(String input) {
        // validate a number with regex
        boolean matchFound = false;

        Pattern pattern = Pattern.compile("^[0-9]+$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);
        matchFound = matcher.find();
        return matchFound;
    }

    public boolean stringRegEx(String input) {
        boolean matchFound = false;

        Pattern pattern = Pattern.compile("[a-zA-z]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);
        matchFound = matcher.find();
        return matchFound;
    }

    public boolean floatRegEx(String input) {
        boolean matchFound = false;

        Pattern pattern = Pattern.compile("([0-9]*)\\.([0-9]*)");
        Matcher matcher = pattern.matcher(input);
        matchFound = matcher.find();
        return matchFound;

    }

    public boolean numbersOutOfRange(String[] arr) {
        String temp = "";
        boolean inValidNum = false;

        for (int i = 0; i < arr.length; i++) {
            temp = arr[i].trim();
            // number has to be between 1 - 49, and must be an integer
            if (IsValidNumberFormat(temp) && (Integer.parseInt(temp) <= 49 && Integer.parseInt(temp) > 0)) {
                inValidNum = false;
            } else {
                inValidNum = true;
                break;
            }
        }
        return inValidNum;
    }
}
