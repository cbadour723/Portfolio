package project3_final;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CranfieldParser {

  public static void main(String[] args) throws IOException {
    String path = "C:\\Users\\caleb\\eclipse-workspace\\CSCI 4130\\cran";
    File collection = new File(path + "/cran.all.1400");

    // create directory for documents
    File dir = new File(path + "/documents");
    if (!dir.exists()) {
      dir.mkdir();
    }

    BufferedReader reader = new BufferedReader(new FileReader(collection));
    String line;
    int docId = 0;
    String title = null;
    String authors = null;
    String journal = null;
    String abstractText = null;
    boolean inTitle = false;
    boolean inAuthors = false;
    boolean inJournal = false;
    boolean inAbstract = false;

    while ((line = reader.readLine()) != null) {
      if (line.startsWith(".I ")) {
        docId = Integer.parseInt(line.substring(3));
        title = null;
        authors = null;
        journal = null;
        abstractText = null;
        inTitle = false;
        inAuthors = false;
        inJournal = false;
        inAbstract = false;
      } else if (line.startsWith(".T")) {
        title = "";
        inTitle = true;
      } else if (line.startsWith(".A")) {
        authors = "";
        inAuthors = true;
      } else if (line.startsWith(".B")) {
        journal = "";
        inJournal = true;
      } else if (line.startsWith(".W")) {
        abstractText = "";
        inAbstract = true;
      } else if (line.startsWith(".")) {
        continue;
      } else {
        if (inTitle) {
          title += line.trim() + " ";
        } else if (inAuthors) {
          authors += line.trim() + " ";
        } else if (inJournal) {
          journal += line.trim() + " ";
        } else if (inAbstract) {
          abstractText += line.trim() + " ";
        }
      }

      // save document to file when finished parsing
      if (abstractText != null) {
        File file = new File(dir.getPath() + "/" + docId + ".txt");
        FileWriter writer = new FileWriter(file);
        writer.write(title.trim() + "\n");
        writer.write(authors.trim() + "\n");
        writer.write(journal.trim() + "\n");
        writer.write(abstractText.trim() + "\n");
        writer.close();
      }
    }

    reader.close();
    System.out.print("HI");
  }

}

