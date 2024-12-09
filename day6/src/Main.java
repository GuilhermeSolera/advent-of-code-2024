import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
  public static void main(String[] args) {
    Main app = new Main();
    app.run();
  }

  public void run() {
    //    DayThree problem1 = new DayThree();
    //    problem1.solve();

    DaySix1 problem = new DaySix1();
    problem.solve();
  }

  public class DaySix1 {
    public void solve(){
      
      List<String> input = getData();

      String[] rows = input.toArray(new String[0]);

      String regex = "XMAS";

      AtomicInteger count = new AtomicInteger(0);
      
      List<String> possibleCharacters = List.of("^", ">", "<", "v");
      
      List<String> visitedCoordinates = new ArrayList<>();
      
      int coordinateRow = 0;
      int coordinateCol = 0;
      
      String character = null;
            
      char[][] matrix = new char[rows.length][];
      for (int i = 0; i < rows.length; i++) {
        matrix[i] = rows[i].toCharArray();
      }

      for (int d = 0; d < matrix.length ; d++) {
        for (int i = 0; i < matrix[d].length; i++) {
          if(possibleCharacters.contains(String.valueOf(matrix[d][i]))) {
            coordinateRow = d;
            coordinateCol = i;
            character = String.valueOf(matrix[d][i]);
            pathCount(matrix, coordinateRow, coordinateCol, "UP", count, visitedCoordinates);
          }
        }
      }
      
      
      
      
      System.out.println("Row: " + coordinateRow + " | " + "Col: " + coordinateCol + " | " + "Character: " + character + 
          " | " + "Count: " + count);
      System.out.println("List size:" + visitedCoordinates.stream().distinct().toList().size());
    }
  }
  
  private void pathCount(char[][] matrix, int row, int col, String direction, AtomicInteger count, List<String> visitedCoordinates){

    int coordinateRow = 0;
    int coordinateCol = 0;
    
    if(direction.equals("UP")) {
      for (int i = row; i >= 0; i--) {
        
        if(i == 0 && !String.valueOf(matrix[i][col]).equals("#")){
          count.set(count.intValue() + 1);
          visitedCoordinates.add(i + "," + col);
          return;
        }
        
        if (String.valueOf(matrix[i][col]).equals("#")) {
          coordinateRow = i;
          coordinateCol = col;
          break;
        }
        count.set(count.intValue() + 1);
        visitedCoordinates.add(i + "," + col);
      }
      System.out.println("UP: " + count);
      pathCount(matrix, coordinateRow + 1, coordinateCol + 1, "RIGHT", count, visitedCoordinates);
    }
    
    if(direction.equals("RIGHT")){
      for (int i = col; i <= matrix[col].length; i++) {
        
        if(i == matrix[row].length - 1 && !String.valueOf(matrix[row][i]).equals("#")){
          count.set(count.intValue() + 1);
          visitedCoordinates.add(row + "," + i);
          return;
        }
        
        if (String.valueOf(matrix[row][i]).equals("#")) {
          coordinateRow = row;
          coordinateCol = i;
          break;
        }
        count.set(count.intValue() + 1);
        visitedCoordinates.add(row + "," + i);
      }
      System.out.println("RIGHT: " + count);
      pathCount(matrix, coordinateRow + 1, coordinateCol - 1, "DOWN", count, visitedCoordinates);
    }

    if(direction.equals("DOWN")) {
      for (int i = row; i <= matrix.length; i++) {

        if(i == matrix.length - 1 && !String.valueOf(matrix[i][col]).equals("#")){
          count.set(count.intValue() + 1);
          visitedCoordinates.add(i + "," + col);
          return;
        }

        if (String.valueOf(matrix[i][col]).equals("#")) {
          coordinateRow = i;
          coordinateCol = col;
          break;
        }
        count.set(count.intValue() + 1);
        visitedCoordinates.add(i + "," + col);
      }
      System.out.println("DOWN: " + count);
      pathCount(matrix, coordinateRow - 1, coordinateCol - 1, "LEFT", count, visitedCoordinates);
    }

    if(direction.equals("LEFT")){
      for (int i = col; i >= 0; i--) {

        if(i == 0 && !String.valueOf(matrix[row][i]).equals("#")){
          count.set(count.intValue() + 1);
          visitedCoordinates.add(row + "," + i);
          return;
        }

        if (String.valueOf(matrix[row][i]).equals("#")) {
          coordinateRow = row;
          coordinateCol = i;
          break;
        }
        count.set(count.intValue() + 1);
        visitedCoordinates.add(row + "," + i);
      }
      System.out.println("LEFT: " + count);
      pathCount(matrix, coordinateRow - 1, coordinateCol + 1, "UP", count, visitedCoordinates);
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