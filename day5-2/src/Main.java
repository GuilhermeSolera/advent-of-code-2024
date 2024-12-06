import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
  public static void main(String[] args) {
    Main app = new Main();
    app.run();
  }

  public void run() {
    DayFive2 problem1 = new DayFive2();
    problem1.solve();

  }

  public class DayFive2 {
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
      
      //get valid sequences
      for(String sequence : sequences){
        Integer[] items = Arrays.stream(sequence.split(",")).map(Integer::parseInt).toArray(Integer[]::new);

        for (int i = 0; i < items.length - 1; i++) {
          Integer current = items[i];
          Integer next = items[i + 1];
          
          if(pagesMap.get(current) == null || !pagesMap.get(current).contains(next)){
            quick(items,pagesMap);
            System.out.println("Original: " + sequence);
            System.out.println("Sorted: " + Arrays.toString(items));
            middleSum += items[items.length / 2];
            break;
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


  public static void quickSort(Integer[] array, int low, int high, Map<Integer, List<Integer>> pagesMap) {
    if (low < high) {
      int pivotIndex = partition(array, low, high, pagesMap);

      quickSort(array, low, pivotIndex - 1, pagesMap);
      quickSort(array, pivotIndex + 1, high, pagesMap);
    }
  }

  private static int partition(Integer[] array, int low, int high, Map<Integer, List<Integer>> pagesMap) {
    int pivot = array[high];
    int i = low - 1;
    for (int j = low; j < high; j++) {
      if (pagesMap.get(array[j]) != null && pagesMap.get(array[j]).contains(pivot)) {
        i++;
        swap(array, i, j);
      }
    }

    swap(array, i + 1, high);

    return i + 1;
  }

  private static void swap(Integer[] array, int i, int j) {
    int temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }

  private static void quick(Integer[] array, Map<Integer, List<Integer>> pagesMap) {
    quickSort(array, 0, array.length - 1, pagesMap);
  }
}