import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.*;

public class Blur {

  public static void main(String[] args) throws IOException {
    int pics = 17;
    File[] file = new File[pics];
    //String prefix = "../resource/asnlib/publicdata/";
    String prefix = "";
    //String prefix = "";
    for (int p = 1; p <= pics; ++p) {
      String suff = String.valueOf(p);
      String filename = prefix + "birds" + suff + ".ppm";
      System.out.println("Opening "+filename);
      file[p-1] = new File(filename);
    }

    // create a scanner for each of the pictures
    Scanner[] scan = new Scanner[pics];

    int rows = 0, cols = 0, mx = 0;
    for (int i = 0; i < pics; ++i) {
      try {
        scan[i] = new Scanner(file[i]);
        String line = scan[i].nextLine();
        cols = Integer.parseInt(scan[i].next());
        rows = Integer.parseInt(scan[i].next());
        mx = Integer.parseInt(scan[i].next());
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
    }

    // open output file and print out header

    BufferedWriter output = new BufferedWriter(new FileWriter("blurred.ppm"));
    output.write(String.format("%s\n", "P3"));
    output.write(String.format("%d  %d\n", cols, rows));
    output.write(String.format("%d\n", mx));
    System.out.println(rows + ", " + cols + ", " + mx);

    

    for (int i = 0; i < cols; ++i) {
      for (int j = 0; j < rows; ++j) {
        // read in red, green, blue
        for (int c = 0; c < 3; ++c) {
          int avg = 0;
          for (int k = 0; k < pics; ++k){
            avg += Integer.parseInt(scan[k].next());
          }
          avg = avg/pics;
          output.write(String.format("%d ", avg));
        }
      }
      output.write(String.format("\n"));
    }

    for (int i = 0; i < pics; ++i) {
      scan[i].close();
    }
    output.close();
  }
}
