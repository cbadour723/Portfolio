package project3_final;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TfIdf {

    private Map<String, Integer> dfMap;
    private Map<Integer, Map<String, Integer>> tfMap;
    private Map<Integer, Map<String, Double>> tfIdfMap;
    private int numDocs;
   
    
    public TfIdf(String corpusPath) throws IOException {
        dfMap = new HashMap<>();
        tfMap = new HashMap<>();
        tfIdfMap = new HashMap<>();
        numDocs = 0;
        index(corpusPath);
        calculateTfIdf();
    }
    
    private void index(String corpusPath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\caleb\\eclipse-workspace\\CSCI 4130\\cran\\cran.all.1400"));
        String line;
        int currentDoc = -1;
        List<String> currentDocTerms = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            if (line.startsWith(".I")) {
                if (currentDoc >= 0) {
                    indexDocument(currentDoc, currentDocTerms);
                    currentDocTerms.clear();
                }
                currentDoc = Integer.parseInt(line.split(" ")[1]);
                numDocs++;
            }
            else if (line.startsWith(".A")) {
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
            }
                String[] words = line.substring(3).toLowerCase().split("[^a-z]");
                for (String word : words) {
                    if (!word.isEmpty()) {
                        currentDocTerms.add(word);
                    }
                }
            
        }
        indexDocument(currentDoc, currentDocTerms);
        br.close();
    }
    
    private void indexDocument(int docId, List<String> terms) {
        Map<String, Integer> termFreqMap = new HashMap<>();
        for (String term : terms) {
            int freq = termFreqMap.getOrDefault(term, 0) + 1;
            termFreqMap.put(term, freq);
            int df = dfMap.getOrDefault(term, 0) + 1;
            dfMap.put(term, df);
        }
        tfMap.put(docId, termFreqMap);
    }
    
    private void calculateTfIdf() {
        for (int docId : tfMap.keySet()) {
            Map<String, Integer> termFreqMap = tfMap.get(docId);
            Map<String, Double> tfIdfScoreMap = new HashMap<>();
            for (String term : termFreqMap.keySet()) {
                int tf = termFreqMap.get(term);
                double idf = Math.log((double)numDocs / dfMap.get(term));
                double tfIdf = tf * idf;
                tfIdfScoreMap.put(term, tfIdf);
            }
            tfIdfMap.put(docId, tfIdfScoreMap);
        }
    }
    
    public Map<Integer, Map<String, Double>> getTfIdfMap() {
        return tfIdfMap;
    }
    
    public void printTfIdfMap() {
        tfIdfMap.forEach((key, value) -> System.out.println(key + " " + value));
    }
    
}

