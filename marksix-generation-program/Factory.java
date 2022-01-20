// a factory to create instances when needed, it can integrate with stubs if any
public class Factory {
    public GetUserInputs createGetSets(boolean bypass, String preferredNumbers) {
        return new GetSets(bypass, preferredNumbers);
    }
    
    public GetUserInputs createGetPreferredNumbers(boolean bypass) {
        return new GetPreferredNumbers(bypass);
    }

    public GenerateRandomNumbers createGenerateRandomNumbers(String preferredNumberString, int sets) {
        return new GenerateRandomNumbers(preferredNumberString, sets);
    }

    public WriteFile createWriteFile(int[][] allSets) {
        return new WriteFile(allSets);
    }
}
