import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;  

// a testing module that will include automated tests and display to user the result of the unit testing
public class TestAll {
    public static ValidationUtils utils = new ValidationUtils();
    public static void main(String[] args) throws Exception {
        TestGetSetsChar();
        TestGetSetsDouble();
        TestGetSetsExceedMaxSets();
        TestGetPreferredNumbersNormal();
        TestGetPreferredNumbersOutofBound();
        TestGetPreferredNumbersDuplicatedNumbers();
        TestGetPreferredNumbersInvalidNumbers();
        TestGenerateRandomNumbers();
        TestUtilCheckSetDuplication();
        TestWriteFile();
    }
    
    static void TestGetSetsChar() throws Exception {
        String preferredNumbers = "1";
        Factory factory = new Factory();
        GetSets sets = (GetSets) factory.createGetSets(true, preferredNumbers);

        try {
            sets.askForInputs("A");
        } catch (NumberFormatException nu) {
            assertEqual("NumberFormatException raised!", "NumberFormatException raised!", new Exception().getStackTrace()[0].getMethodName());
        } catch (Exception e) {
            e.printStackTrace();
            assertEqual("NumberFormatException raised!","Other Exceptions raised!", new Exception().getStackTrace()[0].getMethodName());
        }
    }

    static void TestGetSetsDouble() throws Exception {
        String preferredNumbers = "1";
        Factory factory = new Factory();
        GetSets sets = (GetSets) factory.createGetSets(true, preferredNumbers);

        try {
            sets.askForInputs("3.144444");
        } catch (NumberFormatException nu) {
            assertEqual("NumberFormatException raised!", "NumberFormatException raised!",new Exception().getStackTrace()[0].getMethodName());
        } catch (Exception e) {
            e.printStackTrace();
            assertEqual("NumberFormatException raised!","Other Exceptions raised!",new Exception().getStackTrace()[0].getMethodName());
        }
    }

    static void TestGetSetsExceedMaxSets() throws Exception {
        String preferredNumbers = "1, 2, 3, 4, 5, 6";
        Factory factory = new Factory();
        GetSets sets = (GetSets) factory.createGetSets(true, preferredNumbers);

        try {
            sets.askForInputs("10");
        } catch (ArithmeticException ar) {
            assertEqual("ArithmeticException raised!", "ArithmeticException raised!",new Exception().getStackTrace()[0].getMethodName());
        } catch (Exception e) {
            e.printStackTrace();
            assertEqual("ArithmeticException raised!","Other Exceptions raised!",new Exception().getStackTrace()[0].getMethodName());
        }

        preferredNumbers = "1, 2, 3, 4, 5";
        try {
            sets.askForInputs("50");
        } catch (ArithmeticException ar) {
            assertEqual("ArithmeticException raised!", "ArithmeticException raised!",new Exception().getStackTrace()[0].getMethodName());
        } catch (Exception e) {
            e.printStackTrace();
            assertEqual("ArithmeticException raised!","Other Exceptions raised!",new Exception().getStackTrace()[0].getMethodName());
        }
    }

    static void TestGetPreferredNumbersNormal() throws Exception {
        Factory factory = new Factory();
        GetPreferredNumbers pn = (GetPreferredNumbers) factory.createGetPreferredNumbers(true);

        try {
            pn.askForInputs("39,12");
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEqual("39,12", pn.getPreferredNums(),new Exception().getStackTrace()[0].getMethodName());
        
        try {
            pn.askForInputs("");
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEqual("", pn.getPreferredNums(),new Exception().getStackTrace()[0].getMethodName());

    }

    static void TestGetPreferredNumbersOutofBound() throws Exception {
        Factory factory = new Factory();
        GetPreferredNumbers pn = (GetPreferredNumbers) factory.createGetPreferredNumbers(true);

        try {
            pn.askForInputs("39,12,1,2,3,4,5,6,7,8,9");
        } catch (IndexOutOfBoundsException io) {
            assertEqual("IndexOutOfBoundsException raised!","IndexOutOfBoundsException raised!",new Exception().getStackTrace()[0].getMethodName());
        } catch (Exception e) {
            e.printStackTrace();
            assertEqual("IndexOutOfBoundsException raised!","Other Exceptions raised!",new Exception().getStackTrace()[0].getMethodName());
        }
    }

    static void TestGetPreferredNumbersDuplicatedNumbers() throws Exception {
        Factory factory = new Factory();
        GetPreferredNumbers pn = (GetPreferredNumbers) factory.createGetPreferredNumbers(true);

        try {
            pn.askForInputs("1,1,1,1");
        } catch (DuplicatedArrayElementException du) {
            assertEqual("DuplicatedArrayElementException raised!","DuplicatedArrayElementException raised!",new Exception().getStackTrace()[0].getMethodName());
        } catch (Exception e) {
            e.printStackTrace();
            assertEqual("DuplicatedArrayElementException raised!","Other Exceptions raised!",new Exception().getStackTrace()[0].getMethodName());
        }

        try {
            pn.askForInputs("12,11,9,9");
        } catch (DuplicatedArrayElementException du) {
            assertEqual("DuplicatedArrayElementException raised!","DuplicatedArrayElementException raised!",new Exception().getStackTrace()[0].getMethodName());
        } catch (Exception e) {
            e.printStackTrace();
            assertEqual("DuplicatedArrayElementException raised!","Other Exceptions raised!",new Exception().getStackTrace()[0].getMethodName());
        }
    }

    static void TestGetPreferredNumbersInvalidNumbers() throws Exception {
        Factory factory = new Factory();
        GetPreferredNumbers pn = (GetPreferredNumbers) factory.createGetPreferredNumbers(true);

        try {
            pn.askForInputs("1,2,A,4");
        } catch (NumberFormatException nu) {
            assertEqual("NumberFormatException raised!", "NumberFormatException raised!",new Exception().getStackTrace()[0].getMethodName());
        } catch (Exception e) {
            e.printStackTrace();
            assertEqual("NumberFormatException raised!", "Other Exceptions raised!",new Exception().getStackTrace()[0].getMethodName());
        }

        try {
            pn.askForInputs("12,55,9,60");
        } catch (NumberFormatException nu) {
            assertEqual("NumberFormatException raised!", "NumberFormatException raised!",new Exception().getStackTrace()[0].getMethodName());
        } catch (Exception e) {
            e.printStackTrace();
            assertEqual("NumberFormatException raised!", "Other Exceptions raised!",new Exception().getStackTrace()[0].getMethodName());
        }

        try {
            pn.askForInputs("12,55,9.60");
        } catch (NumberFormatException nu) {
            assertEqual("NumberFormatException raised!", "NumberFormatException raised!",new Exception().getStackTrace()[0].getMethodName());
        } catch (Exception e) {
            e.printStackTrace();
            assertEqual("NumberFormatException raised!", "Other Exceptions raised!",new Exception().getStackTrace()[0].getMethodName());
        }
    
        try {
            pn.askForInputs("12,5E6F");
        } catch (NumberFormatException nu) {
            assertEqual("NumberFormatException raised!", "NumberFormatException raised!",new Exception().getStackTrace()[0].getMethodName());
        } catch (Exception e) {
            e.printStackTrace();
            assertEqual("NumberFormatException raised!", "Other Exceptions raised!",new Exception().getStackTrace()[0].getMethodName());
        }
    }

    static void TestGenerateRandomNumbers() {
        Factory factory = new Factory();
        GenerateRandomNumbers gr = factory.createGenerateRandomNumbers("1,2,3", 3);
    
        try {
            gr.Generate();
            int[][] resultSets = gr.GetAllSets();
            // make sure it is producing the correct number of sets and the numbers are correct
            String str = "";
            int count = resultSets.length;
            if (count == 3) {
                str = "Generated 3 sets";
            } else {
                str = "Incorrect number of sets";
            }
            assertEqual(str, "Generated 3 sets", new Exception().getStackTrace()[0].getMethodName());
            assertEqual(Integer.toString(resultSets[0][0]), "1", new Exception().getStackTrace()[0].getMethodName());
            assertEqual(Integer.toString(resultSets[0][1]), "2", new Exception().getStackTrace()[0].getMethodName());
            assertEqual(Integer.toString(resultSets[0][2]), "3", new Exception().getStackTrace()[0].getMethodName());
            assertEqual(Integer.toString(resultSets[1][0]), "1", new Exception().getStackTrace()[0].getMethodName());
            assertEqual(Integer.toString(resultSets[1][1]), "2", new Exception().getStackTrace()[0].getMethodName());
            assertEqual(Integer.toString(resultSets[1][2]), "3", new Exception().getStackTrace()[0].getMethodName());
            assertEqual(Integer.toString(resultSets[2][0]), "1", new Exception().getStackTrace()[0].getMethodName());
            assertEqual(Integer.toString(resultSets[2][1]), "2", new Exception().getStackTrace()[0].getMethodName());
            assertEqual(Integer.toString(resultSets[2][2]), "3", new Exception().getStackTrace()[0].getMethodName());
        } catch (Exception e) {
            assertEqual("No Exception", "Other Exceptions raised!",new Exception().getStackTrace()[0].getMethodName());
        }

        try {
            // assume no exception is a pass case
            GenerateRandomNumbers gr2 = factory.createGenerateRandomNumbers("", 5);
            gr2.Generate();
            int[][] resultSets2 = gr2.GetAllSets();
            String msg = "";
            int count = resultSets2.length;
            if (count == 5) {
                msg = "Generated 5 sets";
            } else {
                msg = "Incorrect number of sets";
            }

            boolean validNumbers = false;
            String str = "";
            validNumbers = checkEachSetNumbers(resultSets2);
            if (validNumbers) {
                str = "True";
            } else {
                str = "False";
            }
            assertEqual(msg, "Generated 5 sets",new Exception().getStackTrace()[0].getMethodName());
            assertEqual(str, "True",new Exception().getStackTrace()[0].getMethodName());
        } catch (Exception e) {
            assertEqual("No Exception", "Other Exceptions raised!",new Exception().getStackTrace()[0].getMethodName());
        }
    }

    static void TestUtilCheckSetDuplication() {
        int[][] allSets = {{1, 2, 3}, {4,5,6}};
        int[] testSet = {1, 2, 3};

        boolean isDuplicated = false;
        String str = "";

        isDuplicated = utils.checkSetsDuplication(allSets, testSet);
        if (isDuplicated) {
            str = "True";
        } else {
            str = "False";
        }
        assertEqual(str, "True", new Exception().getStackTrace()[0].getMethodName());

        int[] testNoDupSet = {7,8,9};
        isDuplicated = utils.checkSetsDuplication(allSets, testNoDupSet);
        if (isDuplicated) {
            str = "True";
        } else {
            str = "False";
        }
        assertEqual(str, "False", new Exception().getStackTrace()[0].getMethodName());
    }

    static void TestWriteFile() {
        Factory factory = new Factory();
        int[][] allSets = {{1,2,3,4,5,6}, {11,12,13,14,15,16}};

        WriteFile wf = factory.createWriteFile(allSets);
        try {
            //TODO: refactor the below function so that it can allow no user inputs to facilitate automated testing
            wf.GenerateFile();
        } catch (Exception e) {
            assertEqual("No Exception", "Exceptions raised!",new Exception().getStackTrace()[0].getMethodName());
        }
    }

    static void assertEqual(String expectedVal, String actualVal, String functionName) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd_HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();  

        if (actualVal.equals(expectedVal)) {
            System.out.println(functionName + "()_" + dtf.format(now) + " - Pass; Expected output = " + expectedVal + ", Actual output = " + actualVal + ";");
        } else {
            System.out.println(functionName + "()_" + dtf.format(now) + " - Fail; Expected output = " + expectedVal + ", Actual output = " + actualVal + ";");
        }
    }

    static void assertNotEqual(String expectedVal, String actualVal, String functionName) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd_HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();  

        if (actualVal.equals(expectedVal)) {
            System.out.println(functionName + "()_" + dtf.format(now) + " - Fail; Expected output = " + expectedVal + ", Actual output = " + actualVal + ";");
        } else {
            System.out.println(functionName + "()_" + dtf.format(now) + " - Pass; Expected output = " + expectedVal + ", Actual output = " + actualVal + ";");
        }
    }

    static void FailThisTest(String functionName) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd_HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();  

        System.out.println(functionName + "()_" + dtf.format(now) + " - Fail;");
    }

    static boolean checkEachSetNumbers(int[][] allSets) {
        boolean isValid = false;
        for (int i = 0; i < allSets.length; i++) {
            for (int j = 0; j < allSets[i].length; j++) {
                if (allSets[i][j] > 0 && allSets[i][j] <= 49) {
                    isValid = true;
                } else {
                    isValid = false;
                    break;
                }
            }
            if (!isValid) {
                break;
            }
        }
        return isValid;
    }
}
