package project3_final;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RunQueries {
    private Map<String, List<String>> queries;
    private Map<Integer, Map<String, Double>> tfidfMap;

    public RunQueries(Map<String, List<String>> map, Map<Integer, Map<String, Double >> tfidfMap) {
        this.queries = map;
        this.tfidfMap = tfidfMap;
    }

    public void run() {
        Map<String, List<Integer>> results = new HashMap<>();
        for (String queryId : queries.keySet()) {
            List<String> query = queries.get(queryId);
            Map<Integer, Double> scores = new HashMap<>();
            for (int docId : tfidfMap.keySet()) {
                double score = calculateScore(query, tfidfMap.get(docId));
                scores.put(docId, score);
            }
            List<Map.Entry<Integer, Double>> sortedScores = new ArrayList<>(scores.entrySet());
            Collections.sort(sortedScores, new Comparator<Map.Entry<Integer, Double>>() {
                @Override
                public int compare(Map.Entry<Integer, Double> o1, Map.Entry<Integer, Double> o2) {
                    return Double.compare(o2.getValue(), o1.getValue());
                }
            });
            List<Integer> topDocs = new ArrayList<>();
            for (int i = 0; i < 10 && i < sortedScores.size(); i++) {
                topDocs.add(sortedScores.get(i).getKey());
            }
            results.put(queryId, topDocs);
        }
        //return results;
        results.forEach((key, value) -> System.out.println(key + " " + value));
    }

    private double calculateScore(List<String> query, Map<String, Double> docVector) {
        double score = 0.0;
        List<String> queryTerms = query;
        for (String term : queryTerms) {
            if (docVector.containsKey(term.hashCode())) {
                score += docVector.get(term.hashCode());
            }
        }
        return score;
    }
}

