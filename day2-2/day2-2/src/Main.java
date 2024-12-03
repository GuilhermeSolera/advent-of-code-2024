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
      
      boolean toggle = lineSplit.get(0) == lineSplit.get(1) ? lineSplit.get(0) < lineSplit.get(2) : lineSplit.get(0) < lineSplit.get(1); //Check how line starts (desc or asc)
      int strike = 0;
      int listSize = lineSplit.size() - 1;
      int auxLog = unsafe;

      for (int i = 0; i < listSize; i++) {
        int current = lineSplit.get(i);
        int next = lineSplit.get(i + 1);
        int next2 = i+2 > listSize ? 9999 : lineSplit.get(i + 2);
        
        if(invalidLine(current, next, toggle)){
          strike++;
          if(strike > 1) {
            unsafe++;
            break;
          }
          
          if(next2 == 9999 || invalidLine(current, next2, toggle)){
            strike++;
          }
        }
      }

      if(auxLog == unsafe){
        System.out.println(line);
      }
    }
    safe = data.size() - unsafe; //just need to check the safe amount and subtract
    
    System.out.println("safe: "+safe + " | " + "unsafe: "+ unsafe);
  }

  private static boolean invalidLine(int current, int next, boolean toggle) {
    return current < next != toggle || //if diff from start = invalid
        Math.abs(current - next) > 3 || //if diff bigger than 3 = invalid
        current == next; //if equals = invalid
  }

  private static List<String> getData() {
    Path filePath = Paths.get("C:\\workspace\\adventofcode-2024-2-2\\adventofcode-2024-2\\input.txt");

    try {
      return Files.readAllLines(filePath);
      
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    throw new RuntimeException("No file!");
  }
}