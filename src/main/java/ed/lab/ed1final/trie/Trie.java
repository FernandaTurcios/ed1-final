package ed.lab.ed1final.trie;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Trie {

    private static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        int prefixCount = 0;
        int wordCount   = 0;
    }

    private final TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    public void insert(String word) {
        if (word == null) return;
        word = word.trim();
        TrieNode node = root;
        node.prefixCount++;
        for (char c : word.toCharArray()) {
            node = node.children.computeIfAbsent(c, k -> new TrieNode());
            node.prefixCount++;
        }
        node.wordCount++;
    }

    public int countWordsEqualTo(String word) {
        if (word == null) return 0;
        word = word.trim();
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            node = node.children.get(c);
            if (node == null) return 0;
        }
        return node.wordCount;
    }

    public int countWordsStartingWith(String prefix) {
        if (prefix == null) return 0;
        prefix = prefix.trim();
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            node = node.children.get(c);
            if (node == null) return 0;
        }
        return node.prefixCount;
    }

    public void erase(String word) {
        if (word == null) return;
        word = word.trim();
        if (countWordsEqualTo(word) == 0) return;

        // Guardamos el camino desde root hasta el nodo final
        TrieNode[] path = new TrieNode[word.length() + 1];
        TrieNode node = root;
        path[0] = node;
        for (int i = 0; i < word.length(); i++) {
            node = node.children.get(word.charAt(i));
            path[i + 1] = node;
        }

        TrieNode end = path[word.length()];
        end.wordCount--;

        for (TrieNode n : path) {
            n.prefixCount--;
        }

        node = root;
        for (char c : word.toCharArray()) {
            TrieNode child = node.children.get(c);
            if (child.prefixCount == 0) {
                node.children.remove(c);
                break;
            }
            node = child;
        }
    }
}
