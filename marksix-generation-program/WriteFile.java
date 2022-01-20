import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;  
import java.util.Arrays;
import java.io.*;

// write the sets into txt files
public class WriteFile {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");  
    DateTimeFormatter today = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm:ss");  
    LocalDateTime now = LocalDateTime.now();
    static int[][] allSets;
    static String timeStamp;

    public void GenerateFile()throws IOException {
        String fileName = "./results/MarkSix_" + timeStamp + ".txt";
        String msg = "";
        File file = new File(fileName);
        file.createNewFile();
        FileWriter writer = new FileWriter(file); 

        writer.write("Mark Six Generator Results\n") ; 
        for (int i = 0; i < allSets.length; i++) {
            msg = "";
            msg += "Set " + (i+1) + " : ";
            msg +=  Arrays.toString(allSets[i]);
            msg += "\n";
            writer.append(msg);
        }
        writer.flush();
        writer.close();
     }


    public WriteFile(int[][] newAllSets) {
        allSets = newAllSets;
        timeStamp = dtf.format(now);
    }
}