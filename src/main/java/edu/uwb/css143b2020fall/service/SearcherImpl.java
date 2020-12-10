package edu.uwb.css143b2020fall.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SearcherImpl implements Searcher {
    public List<Integer> search(String keyPhrase, Map<String, List<List<Integer>>> index) {
        List<Integer> result = new ArrayList<>();

        List<List<Integer>> list = index.get(keyPhrase);
        String[] wordIndex = keyPhrase.trim().split(" ");

        if (!index.containsKey(wordIndex[0]))
        {
            return result;
        }

        if (wordIndex.length == 1)
        {
            for (int i = 0; i < list.size(); i++)
            {
                if (list.get(i).size() != 0)
                {
                    result.add(i);
                }
            }
        }

        if (wordIndex.length > 1)
        {
            for (int j = 0; j < index.get(wordIndex[0]).size(); j++)
            {
                List<List<Integer>> wordPosition = new ArrayList<>(wordIndex.length);
                for (String words : wordIndex)
                {
                    wordPosition.add(index.get(words).get(j));
                }
                // if it is in order and in the correct position, add j to result
                if (inOrder(wordPosition)) {
                    result.add(j);
                }
            }
        }
        return result;
    }

    // sorts the position of each word in the index
    private boolean sort(List<List<Integer>> wordPosition, int wordIndex, int prevPosition) {

        if (wordPosition.size() <= wordIndex) {
            return true;
        }

        else {
            for (Integer position : wordPosition.get(wordIndex)) {
                if (position != prevPosition + 1) {
                    continue;
                }
                return sort(wordPosition, wordIndex + 1, prevPosition + 1);
            }
        }
        return false;
    }

    // makes sure position of each word is in order
    private boolean inOrder(List<List<Integer>> wordPosition) {
        for (int i = 0; i < wordPosition.get(0).size(); i++)
        {
            return sort(wordPosition, 1, wordPosition.get(0).get(i));
        }
        return false;
    }

}