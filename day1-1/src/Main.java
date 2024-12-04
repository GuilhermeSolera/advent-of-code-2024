import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    Main app = new Main();
    app.run();
  }

  public void run() {
    DayOne problem = new DayOne();
    problem.solve();
  }

  public class DayOne {
    public void solve() {
      List<String> data = getData();

      int sum = 0;
      int sum2 = 0;
      List<Integer> listOne = new ArrayList<>();
      List<Integer> listTwo = new ArrayList<>();

      for (String line : data) {
        List<Integer> lineSplit = Arrays.stream(line.split("   ")).map(Integer::valueOf).toList();

        listOne.add(lineSplit.get(0));
        listTwo.add(lineSplit.get(1));
      }
      
      for(Integer item : listOne){
        long count = listTwo.stream().filter(p -> p.equals(item)).count();

        System.out.println("Item: " + item + " " + "Count: " + count);
        
        sum2 += item * count;
      }
      

      while (!listOne.isEmpty()) {
        int one = listOne.get(0);
        int indexOne = 0;

        int two = listTwo.get(0);
        int indexTwo = 0;

        for (int i = 0; i < listOne.size() - 1; i++) {
          if(listOne.get(i + 1) < one) {
            one = listOne.get(i + 1);
            indexOne = i + 1;
          }
        }

        for (int i = 0; i < listTwo.size() - 1; i++) {
          if(listTwo.get(i + 1) < two) {
            two = listTwo.get(i + 1);
            indexTwo = i + 1;
          }
        }

        sum += Math.abs(one - two);

        System.out.println(one + " " + two);
        System.out.println("Puzzle 1: " + sum);

        listOne.remove(indexOne);
        listTwo.remove(indexTwo);
      }

      System.out.println("Puzzle 2: " + sum2);

    }


    private List<String> getData() {
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