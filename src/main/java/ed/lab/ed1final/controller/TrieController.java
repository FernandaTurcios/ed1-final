package ed.lab.ed1final.controller;
import ed.lab.ed1final.trie.Trie;
import ed.lab.ed1final.trie.model.WordCountResponse;
import ed.lab.ed1final.trie.model.PrefixCountResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trie")
public class TrieController {

    private final Trie trie;

    public TrieController(Trie trie) {
        this.trie = trie;
    }

    @PostMapping("/{word}")
    public ResponseEntity<Void> insert(@PathVariable String word) {
        if (word == null || word.isEmpty() || !word.matches("[a-z]+")) {
            return ResponseEntity.badRequest().build();
        }
        try {
            trie.insert(word);
            return ResponseEntity.status(201).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{word}/count")
    public ResponseEntity<WordCountResponse> countEqual(@PathVariable String word) {
        if (word == null || word.isEmpty() || !word.matches("[a-z]+")) {
            return ResponseEntity.badRequest().build();
        }
        int count = trie.countWordsEqualTo(word);
        return ResponseEntity.ok(new WordCountResponse(word, count));
    }

    @GetMapping("/{prefix}/prefix")
    public ResponseEntity<PrefixCountResponse> countPrefix(@PathVariable String prefix) {
        if (prefix == null || prefix.isEmpty() || !prefix.matches("[a-z]+")) {
            return ResponseEntity.badRequest().build();
        }
        int count = trie.countWordsStartingWith(prefix);
        return ResponseEntity.ok(new PrefixCountResponse(prefix, count));
    }

    @DeleteMapping("/{word}")
    public ResponseEntity<Void> delete(@PathVariable String word) {
        if (word == null || word.isEmpty() || !word.matches("[a-z]+")) {
            return ResponseEntity.badRequest().build();
        }
        try {
            trie.erase(word);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
