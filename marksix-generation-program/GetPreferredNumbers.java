import java.util.*;

public class GetPreferredNumbers implements GetUserInputs {
    // request user for inputs
    private String preferredNums;    
    private boolean bypass = false;

    public GetPreferredNumbers(boolean newBypass) {
        this.bypass = newBypass;
    }

    public void setPreferredNums(String newPreferredNums) {
        this.preferredNums = newPreferredNums;
    }

    public String getPreferredNums() {
        return this.preferredNums;
    }
    // function from interface GetUserInputs
    public void askForInputs(String inputValue) throws Exception {
        String ans = "";
        String msg = "";
        String[] strArr;
        ValidationUtils util = new ValidationUtils();

        if (!bypass) {
            if (inputValue == null || inputValue.isEmpty()) {
                // TODO: change to JOptionpane to provide better UX
                System.out.println("===========================================================");
                System.out.println("Please enter the preferred numbers you would like to include in each sets.\n Please separate numbers with a comma. i.e. 49,1,12 etc.\n If you have no preferred number, please leave it blank and hit [enter].\n Please enter numbers between 1 to 49.\n The maximum inputs are 6 different numbers.\n");
                Scanner preferScanner = new Scanner(System.in);
                ans = preferScanner.nextLine();
            }
        }
        
        if (inputValue != null && bypass) {
            ans = inputValue;
        } else {
            ans = ans.trim();
        }
        
        if (ans.isEmpty()){
            if (!bypass) {
                System.out.println("No Preferred Numbers are detected.");    
            }
            this.preferredNums = ans;
            return;
        }

        if (!bypass) {
            System.out.println("The preferred number(s) you entered are " + ans);
            System.out.println("===========================================================");
        }

        strArr = ans.split(",");
        // check if the length > 6, whether each number is within range 1-49, whether it is a number format, whether there are duplicates in the set
        try {
            if (strArr.length > 6) {
                throw new IndexOutOfBoundsException();
            } else if (util.checkDuplicates(strArr)) {
                msg = "There are duplicates in the numbers entered, please revise your inputs.";
                throw new DuplicatedArrayElementException(msg);
            } else if (util.numbersOutOfRange(strArr)) {
                throw new NumberFormatException();
            }
            this.preferredNums = ans;

        } catch (IndexOutOfBoundsException io) {
            throw new IndexOutOfBoundsException("Please enter at most 6 numbers (There are only 6 numbers in Mark Six).");
        } catch (DuplicatedArrayElementException du) {
            throw new DuplicatedArrayElementException(msg);
        } catch (NumberFormatException nu) {
            throw new NumberFormatException("There are invalid numbers detected, please make sure the numbers you enter are between 1 - 49 and they are integers.");
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception();
        }
    }
}
