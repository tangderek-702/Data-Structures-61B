public class OffByN implements CharacterComparator{
    int num;
    public OffByN(int n) {
        this.num = n;
    }

    @Override
    public boolean equalChars(char x, char y) {
        if (Math.abs(x - y) <= num) {
            return true;
        }
        return false;
    }
}
