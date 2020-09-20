public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> holder = new LinkedListDeque<>();
        char[] stringArray = word.toCharArray();
        for (char a: stringArray){
            holder.addLast(a);
        }
        return holder;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> wordHolder = wordToDeque(word);
        return isPalindromeHelper(wordHolder);
    }

    private boolean isPalindromeHelper(Deque deck) {
        if (deck == null){
            return false;
        }
        if (deck.size() == 0 || deck.size() == 1) {
            return true;
        }
        if (deck.removeFirst() == deck.removeLast()) {
            return isPalindromeHelper(deck);
        } else {
            return false;
        }
    }

    public boolean isPalindrome(String word, CharacterComparator comp) {
        Deque<Character> wordHolder = wordToDeque(word);
        return isPalindromeHelper(wordHolder, comp);
    }

    private boolean isPalindromeHelper(Deque deck,CharacterComparator comp){
        if (deck == null){
            return false;
        }
        if (deck.size() == 0 || deck.size() == 1) {
            return true;
        }
        if (comp.equalChars((char) deck.removeFirst(), (char) deck.removeLast())) {
            return isPalindromeHelper(deck, comp);
        } else {
            return false;
        }
    }
}
