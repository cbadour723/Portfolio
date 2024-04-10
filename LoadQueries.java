package project3_final;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoadQueries {
    private final String path;
    private final Map<String, List<String>> queries;

    public LoadQueries(String path) {
        this.path = path;
        this.queries = new HashMap<>();
    }

    public Map<String, List<String>> load() {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            String currentQuery = null;
            while ((line = br.readLine()) != null) {
                if (line.startsWith(".I")) {
                    currentQuery = line.split(" ")[1];
                }  else if (line.startsWith(".A")) {
                	continue;
                }
                else if(line.startsWith(".T")) {
                	continue;
                }
                else if(line.startsWith(".B")) {
                	continue;
                }
                else if (line.startsWith(".W")) {
                	continue;
                } {
                    if (currentQuery == null) {
                        throw new RuntimeException("Invalid queries file format.");
                    }
                    List<String> queryTokens = new ArrayList<>();
                    String[] tokens = line.substring(3).trim().split("\\s+");
                    for (String token : tokens) {
                        if (!token.isEmpty()) {
                            queryTokens.add(token.toLowerCase());
                        }
                    }
                    queries.put(currentQuery, queryTokens);
                    //currentQuery = null;
                }
            }
        } catch (IOException e) {
            System.out.println("Error while reading queries file: " + e.getMessage());
        }

        return queries;
       // queries.forEach((key, value) -> System.out.println(key + " " + value));
    }
}

