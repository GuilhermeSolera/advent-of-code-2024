import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    Main app = new Main();
    app.run();
  }

  public void run() {
//    DayThree problem1 = new DayThree();
//    problem1.solve();
    
    DayThreePartTwo problem2 = new DayThreePartTwo();
    problem2.solve();
  }

  public class DayThree {
    public void solve() {
      List<String> data = getData();

      String file = String.join("",data);
      int closing = 0;
      int ocurrence = 0;
      int fullOcurrence = 0;
      int sum = 0;
      
      while (fullOcurrence <= file.length()){
        ocurrence = file.indexOf("mul(", ocurrence + 1);
        
        if(ocurrence < 0)
          break;
        
        fullOcurrence = ocurrence + 4;
        closing = file.indexOf(")", fullOcurrence);
        String foundNumbers = file.substring(fullOcurrence, closing);

        System.out.println("Begin: " + fullOcurrence + " | " + "End: " + closing);

        String[] split = foundNumbers.split(",");
        
        String found = file.substring(fullOcurrence - 4, closing + 1);

        if (split.length == 2 && split[0].matches("\\d+") && split[1].matches("\\d+")) {
          System.out.println("OK :" + split[0] + "," + split[1] + " -> " + Integer.parseInt(split[0]) * Integer.parseInt(split[1])
              + " found: " + found + " " + file.contains(found));
          sum+= Integer.parseInt(split[0]) * Integer.parseInt(split[1]);
          System.out.println("Sum: " + sum);  
        } else {
          System.out.println("NON OK :" + Arrays.toString(split) + " found: " + found + " " + file.contains(found));
        }
      }

      System.out.println("Final result 1: " + sum);

    }



  }

  public class DayThreePartTwo {
    public void solve() {
      List<String> data = getData();

      String file = String.join("",data);
      int closing = 0;
      int ocurrence = 0;
      int fullOcurrence = 0;
      int sum = 0;
      int dontOcurrence = 0;
      int doOcurrence = 0;
      int auxDont = 0;
      int dontCount = 0;

      while (fullOcurrence <= file.length()){

        ocurrence = file.indexOf("mul(", ocurrence + 1);
        
        if(ocurrence > dontOcurrence && (ocurrence < doOcurrence || doOcurrence < 0)) {
          continue;
        }

        dontOcurrence = file.indexOf("don't()", ocurrence + 1);
        doOcurrence = file.indexOf("do()", ocurrence + 1);
        
        //log
        if(auxDont != dontOcurrence && doOcurrence > 0){
          dontCount++;
          auxDont = dontOcurrence;
          System.out.println("Dont position: " + dontOcurrence);
        }       
        
        
        if(ocurrence < 0)
          break;
        

        fullOcurrence = ocurrence + 4;
        closing = file.indexOf(")", fullOcurrence);
        String foundNumbers = file.substring(fullOcurrence, closing);

        System.out.println("Begin: " + fullOcurrence + " | " + "End: " + closing);

        String[] split = foundNumbers.split(",");

        String found = file.substring(fullOcurrence - 4, closing + 1);

        if (split.length == 2 && split[0].matches("\\d+") && split[1].matches("\\d+")) {
          System.out.println("OK :" + split[0] + "," + split[1] + " -> " + Integer.parseInt(split[0]) * Integer.parseInt(split[1])
              + " found: " + found + " " + file.contains(found));
          sum+= Integer.parseInt(split[0]) * Integer.parseInt(split[1]);
          System.out.println("Sum: " + sum);
        } else {
          System.out.println("NON OK :" + Arrays.toString(split) + " found: " + found + " " + file.contains(found));
        }
      }

      System.out.println("Final result 1: " + sum);
      System.out.println("Dont founds: " + dontCount);

    }
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