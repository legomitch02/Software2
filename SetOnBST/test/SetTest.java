import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.set.Set;

/**
 * JUnit test fixture for {@code Set<String>}'s constructor and kernel methods.
 *
 * @author Put your name here
 *
 */
public abstract class SetTest {

    /**
     * Invokes the appropriate {@code Set} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new set
     * @ensures constructorTest = {}
     */
    protected abstract Set<String> constructorTest();

    /**
     * Invokes the appropriate {@code Set} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new set
     * @ensures constructorRef = {}
     */
    protected abstract Set<String> constructorRef();

    /**
     * Creates and returns a {@code Set<String>} of the implementation under
     * test type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsTest = [entries in args]
     */
    private Set<String> createFromArgsTest(String... args) {
        Set<String> set = this.constructorTest();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    /**
     * Creates and returns a {@code Set<String>} of the reference implementation
     * type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsRef = [entries in args]
     */
    private Set<String> createFromArgsRef(String... args) {
        Set<String> set = this.constructorRef();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    /**
     * size for an empty set.
     */
    @Test
    public final void testSizeZero() {
        Set<String> check = this.createFromArgsTest();
        Set<String> compare = this.createFromArgsRef();
        int size = check.size();

        assertEquals(0, size);
        assertEquals(compare, check);
    }

    /**
     * size for a set with one entry.
     */
    @Test
    public final void testSizeOne() {
        Set<String> check = this.createFromArgsTest("house");
        Set<String> compare = this.createFromArgsRef("house");
        int size = check.size();

        assertEquals(1, size);
        assertEquals(compare, check);
    }

    /**
     * size for a set with many entries.
     */
    @Test
    public final void testSizeLarger() {
        Set<String> check = this.createFromArgsTest("house", "meme", "cat",
                "paulo", "rocks", "i", "love", "sw2");
        Set<String> compare = this.createFromArgsRef("house", "meme", "cat",
                "paulo", "rocks", "i", "love", "sw2");
        int size = check.size();

        assertEquals(8, size);
        assertEquals(compare, check);
    }

    /**
     * remove for a set with one entry.
     */
    @Test
    public final void testRemoveSmall() {
        Set<String> check = this.createFromArgsTest("house");
        Set<String> compare = this.createFromArgsRef("house");
        boolean yes = check.contains("house");

        assertTrue(yes);
        assertEquals(compare, check);
    }

    /**
     * remove for a set with one entry, wrong entry.
     */
    @Test
    public final void testRemoveSmallFalse() {
        Set<String> check = this.createFromArgsTest("house");
        Set<String> compare = this.createFromArgsRef("house");
        boolean no = check.contains("memes");

        assertTrue(!no);
        assertEquals(compare, check);
    }

    /**
     * remove for a set with many entries.
     */
    @Test
    public final void testRemoveLarger() {
        Set<String> check = this.createFromArgsTest("house", "meme", "cat",
                "paulo", "rocks", "i", "love", "sw2");
        Set<String> compare = this.createFromArgsRef("house", "meme", "cat",
                "paulo", "rocks", "i", "love", "sw2");
        boolean yes = check.contains("rocks");

        assertTrue(yes);
        assertEquals(compare, check);
    }

    /**
     * remove for a set with many entries, wrong entry.
     */
    @Test
    public final void testRemoveLargerFalse() {
        Set<String> check = this.createFromArgsTest("house", "meme", "cat",
                "paulo", "rocks", "i", "love", "sw2");
        Set<String> compare = this.createFromArgsRef("house", "meme", "cat",
                "paulo", "rocks", "i", "love", "sw2");
        boolean no = check.contains("dank");

        assertTrue(!no);
        assertEquals(compare, check);
    }

    /**
     * Tests the constructors.
     */
    @Test
    public final void testConstructors() {
        Set<String> check = this.createFromArgsTest();
        Set<String> compare = this.createFromArgsRef();

        assertEquals(compare, check);
    }

    /**
     * Tests add on empty sets.
     */
    @Test
    public final void testAddToEmpty() {
        Set<String> check = this.createFromArgsTest();
        Set<String> compare = this.createFromArgsRef();
        check.add("dog");
        compare.add("dog");

        assertEquals(compare, check);
    }

    /**
     * Tests add on sets with a few values.
     */
    @Test
    public final void testAddToNotEmpty() {
        Set<String> check = this.createFromArgsTest("house", "cat");
        Set<String> compare = this.createFromArgsRef("house", "cat");
        check.add("dog");
        compare.add("dog");

        assertEquals(compare, check);
    }

    /**
     * Tests remove on sets with a few elements.
     */
    @Test
    public final void testRemoveFromSmall() {
        Set<String> check = this.createFromArgsTest("house", "dog");
        Set<String> compare = this.createFromArgsRef("house", "dog");
        String one = check.remove("dog");
        String two = compare.remove("dog");

        assertEquals(two, one);
        assertEquals(compare, check);
    }

    /**
     * Tests remove on sets with more elements.
     */
    @Test
    public final void testRemoveFromBig() {
        Set<String> check = this.createFromArgsTest("house", "dog", "cat",
                "cow", "noun", "verb", "adjective", "oh boy");
        Set<String> compare = this.createFromArgsRef("house", "dog", "cat",
                "cow", "noun", "verb", "adjective", "oh boy");
        String one = check.remove("dog");
        String two = compare.remove("dog");

        assertEquals(two, one);
        assertEquals(compare, check);
    }

    /**
     * Tests removeAny on sets with a few elements.
     */
    @Test
    public final void testRemoveAnyFromSmall() {
        Set<String> check = this.createFromArgsTest("house", "dog");
        Set<String> compare = this.createFromArgsRef("house", "dog");
        String one = check.removeAny();
        assertTrue(compare.contains(one));
        String two = compare.remove(one);

        assertEquals(two, one);
        assertEquals(compare, check);
    }

    /**
     * Tests removeAny on sets with more elements.
     */
    @Test
    public final void testRemoveAnyFromBig() {
        Set<String> check = this.createFromArgsTest("house", "dog", "cat",
                "cow", "noun", "verb", "adjective", "oh boy");
        Set<String> compare = this.createFromArgsRef("house", "dog", "cat",
                "cow", "noun", "verb", "adjective", "oh boy");
        String one = check.removeAny();
        assertTrue(compare.contains(one));
        String two = compare.remove(one);

        assertEquals(two, one);
        assertEquals(compare, check);
    }
}
