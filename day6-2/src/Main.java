import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

      AtomicInteger firstRow = new AtomicInteger(0);
      AtomicInteger firstCol = new AtomicInteger(0);
      
      int repeated = 0;


      AtomicInteger count = new AtomicInteger(0);
      
      List<String> possibleCharacters = List.of("^", ">", "<", "v");
      
      List<String> visitedCoordinates = new ArrayList<>();
      Set<String> pathTraveled = new HashSet<>();
      
      int coordinateRow;
      int coordinateCol;
      
      int startRow = 0;
      int startCol = 0;
            
      char[][] matrix = new char[rows.length][];
      for (int i = 0; i < rows.length; i++) {
        matrix[i] = rows[i].toCharArray();
      }

      for (int row = 0; row < matrix.length ; row++) {
        for (int col = 0; col < matrix[row].length; col++) {
          if(possibleCharacters.contains(String.valueOf(matrix[row][col]))) {
            startRow = row;
            startCol = col;
            try {
              pathCount(matrix, row, col, "UP", count, visitedCoordinates, pathTraveled);
            } catch (RuntimeException e){
              repeated++;
            }
          }
        }
      }
      
      List<String> finalCoordinates = new ArrayList<>(visitedCoordinates.stream().distinct().toList());

      finalCoordinates.remove(0);
      
      System.out.println("List size: " + finalCoordinates.size());
      System.out.println("Visited Coordinates: " + finalCoordinates);
      System.out.println("Path traveled: " + pathTraveled);
      System.out.println("Repeated: " + repeated);
      System.out.println("First row: " + firstRow + " | " + "First col: " + firstCol);

      pathTraveled = new HashSet<>();
      
      for (int i = 0; i < finalCoordinates.size(); i++) {
        
        String[] coodinate = finalCoordinates.get(i).split(",");
        
        int row = Integer.parseInt(coodinate[0]);
        int col = Integer.parseInt(coodinate[1]);
        
        matrix[row][col] = '#';
        
        try {
          pathCount(matrix, startRow, startCol, "UP", count, visitedCoordinates, pathTraveled);
        } catch (RuntimeException e) {
          repeated++;
        }
        pathTraveled = new HashSet<>();
        matrix[row][col] = '.';
      }
          

      System.out.println("Repeated: " + repeated);
      System.out.println("Visited Coordinates: " + finalCoordinates);
      
      //while its lower than the list size do a loop replacing the next character to a block one
    }
  }
  
  private void pathCount(char[][] matrix, int row, int col, String direction, AtomicInteger count,
      List<String> visitedCoordinates, Set<String> pathTraveled){

    int coordinateRow = 0;
    int coordinateCol = 0;
    
    if(pathTraveled.stream().distinct().count() < pathTraveled.size()){
      return;
    }
    
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
          if(!pathTraveled.add("UP: " + row + "," + col + "|" + coordinateRow + "," + coordinateCol)){
           throw new RuntimeException(); 
          }
          break;
        }
        count.set(count.intValue() + 1);
        visitedCoordinates.add(i + "," + col);
      }
      System.out.println("UP: " + count);
      pathCount(matrix, coordinateRow + 1, coordinateCol + 1, "RIGHT", count, visitedCoordinates, pathTraveled);
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
          if(!pathTraveled.add("RIGHT: " + row + "," + col + "|" + coordinateRow + "," + coordinateCol)){
            throw new RuntimeException();
          }
          break;
        }
        count.set(count.intValue() + 1);
        visitedCoordinates.add(row + "," + i);
      }
      System.out.println("RIGHT: " + count);
      pathCount(matrix, coordinateRow + 1, coordinateCol - 1, "DOWN", count, visitedCoordinates, pathTraveled);
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
          if(!pathTraveled.add("DOWN: " + row + "," + col + "|" + coordinateRow + "," + coordinateCol)){
            throw new RuntimeException();
          }
          break;
        }
        count.set(count.intValue() + 1);
        visitedCoordinates.add(i + "," + col);
      }
      System.out.println("DOWN: " + count);
      pathCount(matrix, coordinateRow - 1, coordinateCol - 1, "LEFT", count, visitedCoordinates, pathTraveled);
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
          if(!pathTraveled.add("LEFT: " + row + "," + col + "|" + coordinateRow + "," + coordinateCol)){
            throw new RuntimeException();
          }
          break;
        }
        count.set(count.intValue() + 1);
        visitedCoordinates.add(row + "," + i);
      }
      System.out.println("LEFT: " + count);
      pathCount(matrix, coordinateRow - 1, coordinateCol + 1, "UP", count, visitedCoordinates, pathTraveled);
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