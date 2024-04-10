package project1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Stream;

public class IndexBuilder {
    private Map<String, Set<Integer>> index;
	Tokenizer tokenss = new Tokenizer();
    public IndexBuilder() {
        index = new HashMap<>();
    }

    public void buildIndex(String corpusDir) throws IOException {
        try (Stream<Path> files = Files.walk(Paths.get(corpusDir))) {
            files.filter(Files::isRegularFile)
                 .filter(path -> path.getFileName().toString().endsWith(".txt"))
                 .sorted()
                 .forEach(path -> {
                     int docId = Integer.parseInt(path.getFileName().toString().replaceAll("\\D", ""));
                     try (BufferedReader reader = Files.newBufferedReader(path)) {
                         String line;
                         while ((line = reader.readLine()) != null) {
                        	 List<String> tokens = tokenss.tokenize(line);
                             for (String token : tokens) {
                                 index.computeIfAbsent(token, k -> new HashSet<>()).add(docId);
                             }
                         }
                     } catch (IOException e) {
                         throw new UncheckedIOException(e);
                     }
                 });
        }
    }

    public Map<String, Set<Integer>> getIndex() {
        return index;
    }

    public void printIndex() {
        index.forEach((key, value) -> System.out.println(key + " " + value));
    }

    public void printdocs(String key1) {
        System.out.println(index.get(key1));
    }

    public Set<Integer> intersect(List<String> terms) {
        Set<Integer> result = new HashSet<>(index.getOrDefault(terms.get(0), Collections.emptySet()));
        for (int i = 1; i < terms.size(); i++) {
            result.retainAll(index.getOrDefault(terms.get(i), Collections.emptySet()));
        }
        return result;
    }
}
