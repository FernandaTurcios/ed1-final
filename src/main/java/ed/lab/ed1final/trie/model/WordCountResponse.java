package ed.lab.ed1final.trie.model;

public class WordCountResponse {
    private String word;
    private int wordsEqualTo;

    public WordCountResponse() {}
    public WordCountResponse(String word, int wordsEqualTo) {
        this.word = word;
        this.wordsEqualTo = wordsEqualTo;
    }

    public String getWord() { return word; }
    public int getWordsEqualTo() { return wordsEqualTo; }
}
