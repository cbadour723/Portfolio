package project3_final;

import java.util.*;
import java.util.regex.Pattern;

public class Tokenizer {
    private Set<String> vocabulary;
    private Pattern pattern;

    public Tokenizer() {
        vocabulary = new HashSet<String>();
        pattern = Pattern.compile("\\W+");
    }

    public List<String> tokenize(String text) {
        String[] words = pattern.split(text.toLowerCase());
        List<String> tokens = new ArrayList<String>();
        for (String word : words) {
            if (!word.isEmpty()) {
                tokens.add(word);
                vocabulary.add(word);
            }
        }
        return tokens;
    }

    public Set<String> getVocabulary() {
        return vocabulary;
    }
}


//In this example, the Tokenizer class has two methods: tokenize and getVocabulary. 
//The tokenize method takes a string of text as input, tokenizes it by splitting on non-word characters, converts the tokens to lowercase, and adds them to a list of tokens. 
//This method also adds each unique token to a set of vocabulary words. 
//The getVocabulary method returns the set of vocabulary words.

