package edu.uwb.css143b2020fall.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IndexerImpl implements Indexer {
    public Map<String, List<List<Integer>>> index(List<String> docs) {
        Map<String, List<List<Integer>>> indexes = new HashMap<>();

        int size = 0;

        for (String docIndex: docs)
        {
            int wordIndex = 0;
            //split the document index with spaces
            String[] words = docIndex.split(" ");

            for (String wordResult: words)
            {
                // if wordresult is empty, continue
                if (wordResult.equals(""))
                {
                    continue;
                }

                // if the indexes has the wordresult, put the result into indexes with a value
                if (!indexes.containsKey(wordResult))
                {
                    List<List<Integer>> indexValue = new ArrayList<>();
                    for (int i = 0; i < docs.size(); i++)
                    {
                        List<Integer> atValue = new ArrayList<>();
                        indexValue.add(atValue);
                    }
                    indexes.put(wordResult, indexValue);
                }

                List<List<Integer>> docList = indexes.get(wordResult);
                List<Integer> indexList = docList.get(size);
                indexList.add(wordIndex);
                docList.add(size, indexList);
                docList.remove(size);
                indexes.replace(wordResult, docList);
                wordIndex++;
            }
            size++;
        }
        return indexes;
    }
}