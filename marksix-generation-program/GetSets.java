import java.util.*;

public class GetSets implements GetUserInputs {
    // request user for inputs
    static int sets;
    static int maxNumberOfSets;
    static boolean bypass = false;
    static String preferredNumbers;

    public GetSets(boolean newBypass, String newPreferredNumbers) {
        bypass = newBypass;
        preferredNumbers = newPreferredNumbers;

    }

    public void setPreferredNumbers(String newPreferredNumbers) {
        preferredNumbers = newPreferredNumbers;
    }

    public String getPreferredNumbers() {
        return preferredNumbers;
    }

    public int getNumberOfSets() {
        return sets;
    }
    // function from interface GetUserInputs
    public void askForInputs(String inputValue) throws Exception {
        String ans = "";
        String printStr = "";
        String[] arr;
        int numberOfPreferredNumbers;
        int inputSets = 0;
        ValidationUtils util = new ValidationUtils();

        if (!bypass) {
            if (inputValue == null || inputValue.isEmpty()) {
                // TODO: change to JOptionpane to provide better UX
                System.out.println("===========================================================");
                System.out.println("Please enter the number of sets you would like to generate.\n");
                Scanner setScanner = new Scanner(System.in);
                ans = setScanner.nextLine();
            }
        }
        
        if (inputValue != null && bypass) {
            ans = inputValue;
        } else {
            ans = ans.trim();
        }
        ans = ans.replaceAll("\\s","");
        arr = ans.split(",");
        numberOfPreferredNumbers = arr.length;
        printStr = "The number of sets you would like to generate is " + ans + "\n";
        try {
            boolean isValidNumberFormat = util.IsValidNumberFormat(ans);
            
            if (ans.isBlank() || ans.isEmpty()) {
                throw new IndexOutOfBoundsException();
            }
            
            if(isValidNumberFormat) {
                inputSets = Integer.parseInt(ans);
            } else {
                throw new NumberFormatException();
            }

            if (InvalidNumberOfSetsInput(preferredNumbers, inputSets)) {
                throw new ArithmeticException();
            } else {
                sets = inputSets;
                printStr += "The number of sets entered is " + sets + "\n" + "===========================================================";
                if (!bypass) {
                    System.out.println(printStr);
                }
            }

        } catch (IndexOutOfBoundsException io) {
            throw new IndexOutOfBoundsException("No number of sets detected. Stopping procedure" + "\n" + "===========================================================");
        } catch (NumberFormatException nu) {
            throw new NumberFormatException("Invalid number format detected. Please enter an Integer." + "\n" + "===========================================================");  
        } catch (ArithmeticException ar) {
            throw new ArithmeticException("Invalid number of sets detected. The number of sets required is " + inputSets + "\n" + "The maximum number of sets can be generated is " + maxNumberOfSets + " as the number fo preferred numbers you entered is " + numberOfPreferredNumbers + "\n" + "===========================================================");
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception();
        }
    }

    static boolean InvalidNumberOfSetsInput(String preferredNumbers, int sets) {
        boolean isInvalid = false;
        String arr[] = preferredNumbers.split(",");
        int arrNums = arr.length;
        int maxSets = 0;

        // maxmium is 6 numbers, max possible outcome is (49-n)! / (49-6)!(6-n)!
        switch(arrNums) {
            case 0:
                maxSets = 13983816;
                break;
            case 1:
                maxSets = 1712304;
                break;
            case 2:
                maxSets = 178365;
                break;
            case 3:
                maxSets = 15180;
                break;
            case 4:
                maxSets = 990;
                break;
            case 5:
                maxSets = 44;
                break;
            case 6:
                maxSets = 1;
                break;
            default:
                maxNumberOfSets = maxSets;
        }  

        if (sets > maxSets) {
            isInvalid = true;
        }
        return isInvalid;
    }
}
