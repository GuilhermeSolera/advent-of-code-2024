import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
  public static void main(String[] args) {
    Main app = new Main();
    app.run();
  }

  public void run() {
    //    DayThree problem1 = new DayThree();
    //    problem1.solve();

    DayFour2 problem = new DayFour2();
    problem.solve();
  }

  public class DayFour1 {
    public void solve(){
      
      List<String> input = getData();

      String[] rows = input.toArray(new String[0]);

      String regex = "XMAS";
      
      int count = 0;

      char[][] matrix = new char[rows.length][];
      for (int i = 0; i < rows.length; i++) {
        matrix[i] = rows[i].toCharArray();
      }

      for (int d = 0; d < matrix.length ; d++) {
        for (int i = 0; i < matrix[d].length; i++) {
          if(matrix[d][i] == 'X') {
            
            //horizontal - left to right
            StringBuilder line = new StringBuilder();
            for (int j = i; j < matrix[d].length; j++) {
              line.append(matrix[d][j]);
            }
            if(line.toString().startsWith(regex)) {
              count++;
              System.out.println("Rule 1: " + line + " Line:" + d + " Column:"+i);
            }

            //horizontal - right to left
            StringBuilder line2 = new StringBuilder();
            for (int j = i; j >= 0; j--) {
              line2.append(matrix[d][j]);
            }
            if(line2.toString().startsWith(regex)) {
              count++;
              System.out.println("Rule 2: " + line2 + " Line:" + d + " Column:"+i);
            }

            //vertical - up to bottom
            StringBuilder line3 = new StringBuilder();
            for (int j = d; j < matrix.length; j++) {
              line3.append(matrix[j][i]);
            }
            if(line3.toString().startsWith(regex)) {
              count++;
              System.out.println("Rule 3: " + line3 + " Line:" + d + " Column:"+i);
            }

            //vertical - up to bottom
            StringBuilder line4 = new StringBuilder();
            for (int j = d; j >= 0; j--) {
              line4.append(matrix[j][i]);
            }
            if(line4.toString().startsWith(regex)) {
              count++;
              System.out.println("Rule 4: " + line4 + " Line:" + d + " Column:"+i);
            }

            //diagonal - right to bottom
            StringBuilder line5 = new StringBuilder();
            for (int j = 0; d+j < matrix.length && j + i < matrix[d].length; j++) {
              line5.append(matrix[d+j][i+j]);
            }
            if(line5.toString().startsWith(regex)) {
              count++;
              System.out.println("Rule 5: " + line5 + " Line:" + d + " Column:"+i);
            }

            //diagonal - left to bottom
            StringBuilder line6 = new StringBuilder();
            for (int j = 0; d+j < matrix.length && i - j >= 0; j++) {
              line6.append(matrix[d+j][i-j]);
            }
            if(line6.toString().startsWith(regex)) {
              count++;
              System.out.println("Rule 6: " + line6 + " Line:" + d + " Column:"+i);
            }

            //diagonal - right to up
            StringBuilder line7 = new StringBuilder();
            for (int j = 0; d-j >= 0 && j + i < matrix[d].length; j++) {
              line7.append(matrix[d-j][i+j]);
            }
            if(line7.toString().startsWith(regex)) {
              count++;
              System.out.println("Rule 7: " + line7 + " Line:" + d + " Column:"+i);
            }
            
            //diagonal - right to up
            StringBuilder line8 = new StringBuilder();
            for (int j = 0; d-j >= 0 && i - j >= 0; j++) {
              line8.append(matrix[d-j][i-j]);
            }
            if(line8.toString().startsWith(regex)) {
              count++;
              System.out.println("Rule 8: " + line8 + " Line:" + d + " Column:"+i);
            }
            
          }
        }
      }
      System.out.println("Total: "+count);
    }
  }

  public class DayFour2 {
    public void solve(){

      List<String> input = getData();

      String[] rows = input.toArray(new String[0]);

      String regex = "MAS";
      
      List<String> middle = new ArrayList<>(); 

      int count = 0;

      char[][] matrix = new char[rows.length][];
      for (int i = 0; i < rows.length; i++) {
        matrix[i] = rows[i].toCharArray();
      }

      for (int d = 0; d < matrix.length ; d++) {
        for (int i = 0; i < matrix[d].length; i++) {
          if(matrix[d][i] == 'M') {
            
            //diagonal - right to bottom
            StringBuilder line5 = new StringBuilder();
            for (int j = 0; d+j < matrix.length && j + i < matrix[d].length; j++) {
              line5.append(matrix[d+j][i+j]);
            }
            if(line5.toString().startsWith(regex)) {
              int d1 = d + 1;
              int i2 = i + 1;
              
              middle.add(d1 + "," + i2);
              System.out.println("Rule 5: " + line5 + " Line:" + d + " Column:"+i);
            }

            //diagonal - left to bottom
            StringBuilder line6 = new StringBuilder();
            for (int j = 0; d+j < matrix.length && i - j >= 0; j++) {
              line6.append(matrix[d+j][i-j]);
            }
            if(line6.toString().startsWith(regex)) {
              int d1 = d + 1;
              int i2 = i - 1;
              middle.add(d1 + "," + i2);
              System.out.println("Rule 6: " + line6 + " Line:" + d + " Column:"+i);
            }

            //diagonal - right to up
            StringBuilder line7 = new StringBuilder();
            for (int j = 0; d-j >= 0 && j + i < matrix[d].length; j++) {
              line7.append(matrix[d-j][i+j]);
            }
            if(line7.toString().startsWith(regex)) {
              int d1 = d - 1;
              int i2 = i + 1;
              middle.add(d1 + "," + i2);
              System.out.println("Rule 7: " + line7 + " Line:" + d + " Column:"+i);
            }

            //diagonal - right to up
            StringBuilder line8 = new StringBuilder();
            for (int j = 0; d-j >= 0 && i - j >= 0; j++) {
              line8.append(matrix[d-j][i-j]);
            }
            if(line8.toString().startsWith(regex)) {
              int d1 = d - 1;
              int i2 = i - 1;
              middle.add(d1 + "," + i2);
              System.out.println("Rule 8: " + line8 + " Line:" + d + " Column:"+i);
            }
          }
        }
      }

      long elementCount = middle.stream()
          .collect(Collectors.groupingBy(e -> e, Collectors.counting())).entrySet().stream().filter(entry -> entry.getValue() > 1).count();
      
      System.out.println("Middles: "+middle);
      System.out.println("Interceptions: "+elementCount);
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

  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  //----------------------------------------------------------------------
  public void improvedByGPT() {
    char[][] matrix = {
        {'M', 'M', 'M', 'S', 'X', 'X', 'M', 'A', 'S', 'M'},
        {'M', 'S', 'A', 'M', 'X', 'M', 'S', 'M', 'S', 'A'},
        {'A', 'M', 'X', 'S', 'X', 'M', 'A', 'A', 'M', 'M'},
        {'M', 'S', 'A', 'M', 'A', 'S', 'M', 'S', 'M', 'X'},
        {'X', 'M', 'A', 'S', 'A', 'M', 'X', 'A', 'M', 'M'},
        {'X', 'X', 'A', 'M', 'M', 'X', 'X', 'A', 'M', 'A'},
        {'S', 'M', 'S', 'M', 'S', 'A', 'S', 'X', 'S', 'S'},
        {'S', 'A', 'X', 'A', 'M', 'A', 'S', 'A', 'A', 'A'},
        {'M', 'A', 'M', 'M', 'M', 'X', 'M', 'M', 'M', 'M'},
        {'M', 'X', 'M', 'X', 'A', 'X', 'M', 'A', 'S', 'X'}
    };

    String word = "XMAS";
    int count = countWordInMatrix(matrix, word);
    System.out.println("Total XMAS occurrences: " + count);
  }

  public static int countWordInMatrix(char[][] matrix, String word) {
    int count = 0;
    int rows = matrix.length;
    int cols = matrix[0].length;
    int wordLen = word.length();

    // Directions: [row offset, col offset]
    int[][] directions = {
        {0, 1},  // Right
        {0, -1}, // Left
        {1, 0},  // Down
        {-1, 0}, // Up
        {1, 1},  // Diagonal down-right
        {1, -1}, // Diagonal down-left
        {-1, 1}, // Diagonal up-right
        {-1, -1} // Diagonal up-left
    };

    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < cols; col++) {
        // Check all directions from this starting point
        for (int[] direction : directions) {
          if (isWordPresent(matrix, word, row, col, direction)) {
            count++;
            System.out.println("Linha: " + row + "| Coluna: "+col);
          }
        }
      }
    }

    return count;
  }

  public static boolean isWordPresent(char[][] matrix, String word, int row, int col, int[] direction) {
    int wordLen = word.length();
    int rows = matrix.length;
    int cols = matrix[0].length;

    for (int i = 0; i < wordLen; i++) {
      int newRow = row + i * direction[0];
      int newCol = col + i * direction[1];

      // Check if we're out of bounds
      if (newRow < 0 || newRow >= rows || newCol < 0 || newCol >= cols) {
        return false;
      }

      // Check if the current character matches
      if (matrix[newRow][newCol] != word.charAt(i)) {
        return false;
      }
    }

    return true;
  }
}