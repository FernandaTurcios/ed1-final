package ed.lab.ed1final.trie.model;

public class PrefixCountResponse {
    private String prefix;
    private int wordsStartingWith;

    public PrefixCountResponse() {}
    public PrefixCountResponse(String prefix, int wordsStartingWith) {
        this.prefix = prefix;
        this.wordsStartingWith = wordsStartingWith;
    }

    public String getPrefix() { return prefix; }
    public int getWordsStartingWith() { return wordsStartingWith; }
}
 //push