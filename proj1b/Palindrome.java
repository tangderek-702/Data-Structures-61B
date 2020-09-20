public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> holder = new LinkedListDeque<>();
        char[] stringArray = word.toCharArray();
        for (char a: stringArray) {
            holder.addLast(a);
        }
        return holder;
    }

    public boolean isPalindrome(String word) {
        if (word == null) {
            return false;
        }
        Deque<Character> wordHolder = wordToDeque(word);
        return isPalindromeHelper(wordHolder);
    }

    private boolean isPalindromeHelper(Deque deck) {
        if (deck.size() == 0 || deck.size() == 1) {
            return true;
        }
        if (deck.removeFirst() == deck.removeLast()) {
            return isPalindromeHelper(deck);
        } else {
            return false;
        }
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word == null) {
            return false;
        }
        if (cc == null) {
            return isPalindrome(word);
        }
        Deque<Character> wordHolder = wordToDeque(word);
        return isPalindromeHelper(wordHolder, cc);
    }

    private boolean isPalindromeHelper(Deque deck, CharacterComparator cc) {
        if (deck.size() == 0 || deck.size() == 1) {
            return true;
        }
        if (cc.equalChars((char) deck.removeFirst(), (char) deck.removeLast())) {
            return isPalindromeHelper(deck, cc);
        } else {
            return false;
        }
    }
}
