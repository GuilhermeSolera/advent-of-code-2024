import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    List<String> data = getData();
    
    int safe = 0;
    int unsafe = 0;
    
    for (String line : data){
      List<Integer> lineSplit = Arrays.stream(line.split(" ")).map(Integer::valueOf).toList();
      
      boolean toggle = lineSplit.get(0) < lineSplit.get(1); //Check how line starts (desc or asc)
      int auxLog = unsafe;

      for (int i = 0; i < lineSplit.size() - 1; i++) {
        if(lineSplit.get(i) < lineSplit.get(i + 1) != toggle || //if diff from start = invalid
            Math.abs(lineSplit.get(i) - lineSplit.get(i + 1)) > 3 || //if diff bigger than 3 = invalid
            lineSplit.get(i) == lineSplit.get(i + 1)){ //if equals = invalid
          unsafe++;
          break;
        }
      }

      if(auxLog == unsafe){
        System.out.println(line);
      }
    }
    safe = data.size() - unsafe; //just need to check the safe amount and subtract
    
    System.out.println("safe: "+safe + " | " + "unsafe: "+ unsafe);
  }

  private static List<String> getData() {
    Path filePath = Paths.get("C:\\workspace\\adventofcode-2024-2\\adventofcode-2024-2\\input.txt");

    try {
      return Files.readAllLines(filePath);
      
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    throw new RuntimeException("No file!");
  }
}