// main program to trigger other classes
public class MarkSix {
    public static void main (String[] args) throws Exception {
        startLogo();
        String preferredNumbers = "";
        Factory factory = new Factory();
        try {
            GetPreferredNumbers gn = (GetPreferredNumbers) factory.createGetPreferredNumbers(false);
            gn.askForInputs(null);
            preferredNumbers = gn.getPreferredNums();
            GetSets sets = (GetSets) factory.createGetSets(false, preferredNumbers);    
            sets.askForInputs(null);
            int numberOfSet = sets.getNumberOfSets();
            GenerateRandomNumbers gr =  factory.createGenerateRandomNumbers(preferredNumbers, numberOfSet);
            gr.Generate();
            int[][] allSets = gr.GetAllSets();
            WriteFile file = factory.createWriteFile(allSets);
            file.GenerateFile();
            System.out.println("The Mark Six sets are generated successfully.\n Please find the text file in the 'result' folder.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Mark Six sets generation is terminated.\n Please try again.");
            return;
        }
        
    }

    static void startLogo() {
        System.out.println("===========================================================");
        System.out.println("Initialize Mark Six Set Generator\n");
        System.out.println("      ___________");
        System.out.println("     /     __    \\");
        System.out.println("    /     /  /    \\");
        System.out.println("   |     /  /_     |");
        System.out.println("   |    /  _  \\    |");
        System.out.println("   \\   |  (_)  |   /");
        System.out.println("    \\   \\_____/   /");
        System.out.println("     \\___________/");
        System.out.println("\n");
    }
}
