import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.map.Map;
import components.map.Map.Pair;

/**
 * JUnit test fixture for {@code Map<String, String>}'s constructor and kernel
 * methods.
 *
 * @author Put your name here
 *
 */
public abstract class MapTest {

    /**
     * Invokes the appropriate {@code Map} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new map
     * @ensures constructorTest = {}
     */
    protected abstract Map<String, String> constructorTest();

    /**
     * Invokes the appropriate {@code Map} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new map
     * @ensures constructorRef = {}
     */
    protected abstract Map<String, String> constructorRef();

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the implementation
     * under test type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires
     *
     *           <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     *           </pre>
     *
     * @ensures createFromArgsTest = [pairs in args]
     */
    private Map<String, String> createFromArgsTest(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorTest();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires
     *
     *           <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     *           </pre>
     *
     * @ensures createFromArgsRef = [pairs in args]
     */
    private Map<String, String> createFromArgsRef(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorRef();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    /**
     * Tests creating empty maps.
     */
    @Test
    public final void testConstructors() {
        Map<String, String> check = this.createFromArgsTest();
        Map<String, String> compare = this.createFromArgsRef();
        assertEquals(compare, check);
    }

    /**
     * Tests adding a single pair to an empty map.
     */
    @Test
    public final void testAddToEmptyMap() {
        Map<String, String> check = this.createFromArgsTest();
        Map<String, String> compare = this.createFromArgsRef("Hi", "Cow");
        check.add("Hi", "Cow");
        assertEquals(compare, check);
    }

    /**
     * Tests addition of a pair to a non-empty map.
     */
    @Test
    public final void testAddToNonEmptyMap() {
        Map<String, String> check = this.createFromArgsTest("Hi", "Cow");
        Map<String, String> compare = this.createFromArgsRef("Hi", "Cow",
                "corn", "egg");
        check.add("corn", "egg");
        assertEquals(compare, check);
    }

    /**
     * Tests removing a single pair from a map with a single pair.
     */
    @Test
    public final void testRemoveToEmpty() {
        Map<String, String> check = this.createFromArgsTest("Hi", "Cow");
        Map<String, String> compare = this.createFromArgsRef("Hi", "Cow");
        Pair<String, String> hold = check.remove("Hi");
        Pair<String, String> match = compare.remove("Hi");
        assertEquals(compare, check);
        assertEquals(match, hold);
    }

    /**
     * Tests removal of a single pair from a map with more than one.
     */
    @Test
    public final void testRemoveStillHasItems() {
        Map<String, String> check = this.createFromArgsTest("Hi", "Cow", "corn",
                "egg");
        Map<String, String> compare = this.createFromArgsRef("Hi", "Cow",
                "corn", "egg");
        Pair<String, String> hold = check.remove("Hi");
        Pair<String, String> match = compare.remove("Hi");
        assertEquals(compare, check);
        assertEquals(match, hold);
    }

    /**
     * Remove any from a map with more than one entry.
     */
    @Test
    public final void testRemoveAny() {
        Map<String, String> check = this.createFromArgsTest("Hi", "Cow", "corn",
                "egg");
        Map<String, String> compare = this.createFromArgsRef("Hi", "Cow",
                "corn", "egg");
        Pair<String, String> hold = check.removeAny();
        assertTrue(compare.hasKey(hold.key()));
        Pair<String, String> match = compare.remove(hold.key());
        assertEquals(compare, check);
        assertEquals(match, hold);
    }

    /**
     * Comparison of stored values with a given key.
     */
    @Test
    public final void testValue() {
        Map<String, String> check = this.createFromArgsTest("Hi", "Cow");
        Map<String, String> compare = this.createFromArgsRef("Hi", "Cow");
        assertEquals(compare.value("Hi"), check.value("Hi"));
        assertEquals("Cow", check.value("Hi"));
        assertEquals(compare, check);
    }

    /**
     * Checks hasKey is true AND that is does not alter the map.
     */
    @Test
    public final void testHasKey() {
        Map<String, String> check = this.createFromArgsTest("Hi", "Cow", "corn",
                "egg");
        Map<String, String> compare = this.createFromArgsRef("Hi", "Cow",
                "corn", "egg");
        assertTrue(check.hasKey("Hi"));
        assertTrue(check.hasKey("corn"));
        assertEquals(compare, check);

    }

    /**
     * Checks hasKey is false AND that is does not alter the map.
     */
    @Test
    public final void testHasKeyEmpty() {
        Map<String, String> check = this.createFromArgsTest();
        Map<String, String> compare = this.createFromArgsRef();
        assertTrue(!check.hasKey("Hi"));
        assertTrue(!check.hasKey("corn"));
        assertEquals(compare, check);
    }

    /**
     * Checks hasKey is false AND that is does not alter the map of a nonzero
     * size.
     */
    @Test
    public final void testHasKeyFalse() {
        Map<String, String> check = this.createFromArgsTest("Hi", "Cow", "corn",
                "egg");
        Map<String, String> compare = this.createFromArgsTest("Hi", "Cow",
                "corn", "egg");
        assertTrue(!check.hasKey("Dog"));
        assertTrue(!check.hasKey("cat"));
        assertEquals(compare, check);
    }

    /**
     * Checks size is correct for a zero size map and that the map is not
     * altered.
     */
    @Test
    public final void testSizeEmpty() {
        Map<String, String> check = this.createFromArgsTest();
        Map<String, String> compare = this.createFromArgsRef();
        assertEquals(0, check.size());
        assertEquals(compare, check);
    }

    /**
     * Checks size is correct for a nonzero sized map and that the map is not
     * altered.
     */
    @Test
    public final void testSizeNotZero() {
        Map<String, String> check = this.createFromArgsTest("Hi", "Cow", "corn",
                "egg");
        Map<String, String> compare = this.createFromArgsRef("Hi", "Cow",
                "corn", "egg");
        assertEquals(2, check.size());
        assertEquals(compare, check);
    }

}
