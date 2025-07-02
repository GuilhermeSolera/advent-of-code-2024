import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
  public static void main(String[] args) {
    Main app = new Main();
    app.run();
  }

  public void run() {
    DayFive1 problem1 = new DayFive1();
    problem1.solve();

  }

  public class DayFive1 {
    public void solve() {

      List<String> input = getData();

      List<String> pages = input.stream().filter(p -> p.contains("|")).toList();
      List<String> sequences = input.stream().filter(p -> p.contains(",")).toList();

      Map<Integer, List<Integer>> pagesMap = new HashMap<>();
      
      int middleSum = 0;
      
      //get page orders in hashmap
      for(String page : pages){
        String[] item = page.split("\\|");
        int key = Integer.parseInt(item[0]);
        int value = Integer.parseInt(item[1]);
        
        if(pagesMap.get(key) == null){
          pagesMap.put(key, new ArrayList<>(List.of(value)));  
        } else {
          pagesMap.get(key).add(value);
        }
      }
      
      //get valid sequences1
      for(String sequence : sequences){
        String[] items = sequence.split(",");

        for (int i = 0; i < items.length - 1; i++) {
          Integer current = Integer.parseInt(items[i]);
          Integer next = Integer.parseInt(items[i + 1]);
          
          if(pagesMap.get(current) == null || !pagesMap.get(current).contains(next))
            break;

          if(i == items.length - 2) {
            System.out.println(sequence);
            middleSum += Integer.parseInt(items[items.length / 2]);
          }
        }
      }
      
      System.out.println("Total middle sum: " + middleSum);
    }

    public List<String> getData() {
      Path filePath = Paths.get("input.txt");

      try {
        return Files.readAllLines(filePath);

      } catch (IOException e) {
        e.printStackTrace();
      }

      throw new RuntimeException("No file!");
    }
  }
}