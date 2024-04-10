package project3_final;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MapCalculator {

    private String cranqrelPath;
    private Map<Integer, Map<Integer, Integer>> relevanceMap;

    public MapCalculator(String cranqrelPath) {
        this.cranqrelPath = cranqrelPath;
        this.relevanceMap = loadRelevanceMap();
    }

    private Map<Integer, Map<Integer, Integer>> loadRelevanceMap() {
        Map<Integer, Map<Integer, Integer>> relevanceMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(cranqrelPath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.trim().split("\\s+");
                int queryId = Integer.parseInt(fields[0]);
                int docId = Integer.parseInt(fields[1]);
                int relevance = Integer.parseInt(fields[2]);

                if (!relevanceMap.containsKey(queryId)) {
                    relevanceMap.put(queryId, new HashMap<>());
                }
                relevanceMap.get(queryId).put(docId, relevance);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return relevanceMap;
    }

    public double calculateMAP(Map<String, Set<Integer>> map) {
        double sumAP = 0.0;
        int numQueries = map.size();

        for (String queryId : map.keySet()) {
            Set<Integer> results = map.get(queryId);
            int numRelevantDocs = 0;
            double sumPrecision = 0.0;

            for (int i = 0; i < results.size(); i++) {
                int docId = i;
                if (relevanceMap.containsKey(queryId) && relevanceMap.get(queryId).containsKey(docId)) {
                    int relevance = relevanceMap.get(queryId).get(docId);
                    if (relevance > 0) {
                        numRelevantDocs++;
                        double precision = (double) numRelevantDocs / (i + 1);
                        sumPrecision += precision;
                    }
                }
            }

            double averagePrecision = sumPrecision / Math.min(numRelevantDocs, results.size());
            sumAP += averagePrecision;
        }

        return sumAP / numQueries;
    }
}
