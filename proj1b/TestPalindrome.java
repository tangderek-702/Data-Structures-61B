import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        assertTrue(palindrome.isPalindrome("%"));
        assertTrue(palindrome.isPalindrome("racecar"));
        assertFalse(palindrome.isPalindrome(null));
        assertTrue(palindrome.isPalindrome("harrah"));
        assertTrue(palindrome.isPalindrome(""));
    }

    @Test
    public void testIsPalindromeObo() {
        OffByOne obo = new OffByOne();
        assertTrue(palindrome.isPalindrome("f", obo));
        assertTrue(palindrome.isPalindrome("",obo));
        assertTrue(palindrome.isPalindrome("racecar", null));
        assertTrue(palindrome.isPalindrome("flake", obo));
        assertTrue(palindrome.isPalindrome("dabc", obo));
    }
}
