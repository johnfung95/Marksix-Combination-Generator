import java.util.Arrays;

public class GenerateRandomNumbers {
    // main program responsible for generating sets and numbers and store them together
    static int[] allMarkSixNums;
    static int numberOfSetsToGenerate;
    static String preferredNumbers;
    // to store all sets in integer format to compare with array.deepequals
    static int[][] allSets;
    static ValidationUtils utils = new ValidationUtils();

    public GenerateRandomNumbers(String newpreferredNumbers, int newNumberOfSetsToGenerate) {
        preferredNumbers = newpreferredNumbers;
        numberOfSetsToGenerate = newNumberOfSetsToGenerate;
        allMarkSixNums = new int [49];
        for (int i = 0; i < 49; i++) {
            allMarkSixNums[i] = i + 1;
        }
        allSets = new int[numberOfSetsToGenerate][6];
    }

    public int[][] GetAllSets() {
        return  allSets;
    }

    public void Generate() throws Exception {
        String[] preferredNums;
        int[] result = new int[6];
        int sets;

        if (!preferredNumbers.isEmpty() || !preferredNumbers.isBlank()){
            preferredNums = preferredNumbers.split(",");
        } else {
            preferredNums = new String[6];
        }
        try {
            sets = numberOfSetsToGenerate;
            int[] intResultSet = ConvertFromArrayToIntArr(preferredNums);
            for (int i = 0; i < intResultSet.length; i++) {
                result[i] = intResultSet[i];
            }
            // the maximum number of preferred numbers is 6
            if (preferredNums.length == 6 && !preferredNumbers.isBlank()){
                writeIntoArrays(allSets, result, 0);   
            } else {
                // assuming the opposite is less than 6 numbers, because the validations in asking inputs will guard invalid number of counts
                int[] removePreferredNums = RemovePreferredNums(intResultSet);

                for (int j = 0; j < sets; j++) {
                    result = newSetOfArray(result);
                    int[] markSixSet = RandomNumberForSet(result, removePreferredNums);
                    writeIntoArrays(allSets, markSixSet, j);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception();
        }
    }

    static int[] ConvertFromArrayToIntArr(String[] arr) {
        int[] intArr = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null) {
                intArr[i] = 0;
            } else {
                intArr[i] = Integer.parseInt(arr[i].trim());
            }
            
        }

        return intArr;
    }

    static int[] RemovePreferredNums(int[] arr) {
        int[] purged = allMarkSixNums;

        for (int i = 0; i < arr.length; i++) {
            // pop the number from array
            if (arr[i] > 0) {
                purged[arr[i] - 1] = 0;
            }        
        }
        return purged;
    }

    static int[] RandomNumberForSet(int[] arr, int[] remainingMarkSixNums) {
        // store the sets in the int[][]
        int total = 0;
        int remains = 0;
        int[] afterGen;
        int[] result = newSetOfArray(arr);

        for (int i = 0; i < remainingMarkSixNums.length;i++) {
            if (remainingMarkSixNums[i] > 0) {
                total += 1;
            }
        }
        afterGen = new int[total];
        int count = 0;
        for (int k = 0; k < remainingMarkSixNums.length; k++) {
            if (remainingMarkSixNums[k] > 0) {
                afterGen[count] = remainingMarkSixNums[k];
                count += 1;
            }
        }

        for (int j = 0; j < arr.length; j++) {
            if (arr[j] > 0) {
                remains += 1;
            }
        }

        // generate random number and append to finalResult
        
        int[] finalResult = GenSet(result, afterGen, remains, total);
        boolean isExists = utils.checkSetsDuplication(allSets, finalResult);
        while (isExists) {
            result = newSetOfArray(arr);
            finalResult = GenSet(result, afterGen, remains, total);
            isExists = utils.checkSetsDuplication(allSets, finalResult);
        }
        return finalResult;
    }

    static int[] GenSet(int[] currentSet, int[] numsLeft, int placeLeft, int count) {

        for (int k = placeLeft; k < currentSet.length; k++) {
            int randomPlace = (int) Math.floor(Math.random() * (count));
            while(numsLeft[randomPlace] <= 0) {
                randomPlace = (int) Math.floor(Math.random() * (count));
            }
            currentSet[k] = numsLeft[randomPlace];
            numsLeft[randomPlace] = 0;
        }
        Arrays.sort(currentSet);
        return currentSet;
    }

    static void writeIntoArrays(int[][] allSets, int[] newSet, int currentRow) {
            allSets[currentRow] = newSet;
    }

    static int[] newSetOfArray(int[] currentSet) {
        // reset the set with preferred numbers
        int[] newArr = new int[currentSet.length];

        for (int i = 0; i < currentSet.length; i++) {
            if (currentSet[i] > 0) {
                newArr[i] = currentSet[i];
            }
        }
        return newArr;
    }
}