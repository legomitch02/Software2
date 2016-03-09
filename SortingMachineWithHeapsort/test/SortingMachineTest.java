import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Comparator;

import org.junit.Test;

import components.sortingmachine.SortingMachine;

/**
 * JUnit test fixture for {@code SortingMachine<String>}'s constructor and
 * kernel methods.
 *
 * @author Mitchel Kromer % Tyler Kunkle
 *
 */
public abstract class SortingMachineTest {

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * implementation under test and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorTest = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorTest(
            Comparator<String> order);

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * reference implementation and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorRef = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorRef(
            Comparator<String> order);

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the
     * implementation under test type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures
     *
     *          <pre>
     * createFromArgsTest = (insertionMode, order, [multiset of entries in args])
     *          </pre>
     */
    private SortingMachine<String> createFromArgsTest(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorTest(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the reference
     * implementation type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures
     *
     *          <pre>
     * createFromArgsRef = (insertionMode, order, [multiset of entries in args])
     *          </pre>
     */
    private SortingMachine<String> createFromArgsRef(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorRef(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     * Comparator<String> implementation to be used in all test cases. Compare
     * {@code String}s in lexicographic order.
     */
    private static class StringLT implements Comparator<String> {

        @Override
        public int compare(String s1, String s2) {
            return s1.compareToIgnoreCase(s2);
        }

    }

    /**
     * Comparator instance to be used in all test cases.
     */
    private static final StringLT ORDER = new StringLT();

    /*
     * Sample test cases.
     */

    /**
     * Our implementation gives the same thing as the sample.
     */
    @Test
    public final void testConstructor() {
        SortingMachine<String> m = this.constructorTest(ORDER);
        SortingMachine<String> mExpected = this.constructorRef(ORDER);
        assertEquals(mExpected, m);
    }

    /**
     * Adds a single item to an empty collection.
     */
    @Test
    public final void testAddEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green");
        m.add("green");
        assertEquals(mExpected, m);
    }

    /**
     * Adds many items to an empty collection.
     */
    @Test
    public final void testAddManyEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "blue", "red", "green");
        m.add("blue");
        m.add("red");
        m.add("green");
        assertEquals(mExpected, m);
    }

    /**
     * Adds a single item to a collection of many.
     */
    @Test
    public final void testAddMany() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "one",
                "two", "three", "four", "five", "six", "seven", "eight", "nine",
                "ten", "eleven");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "one", "two", "three", "four", "five", "six", "seven", "eight",
                "nine", "ten", "eleven", "twelve");
        m.add("twelve");
        assertEquals(mExpected, m);
    }

    /**
     * Length of empty collections.
     */
    @Test
    public final void testLengthEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true);
        assertEquals(mExpected, m);
        assertEquals(mExpected.size(), m.size());
    }

    /**
     * Length on a single item collection.
     */
    @Test
    public final void testLengthOne() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "one");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "one");
        assertEquals(mExpected, m);
        assertEquals(mExpected.size(), m.size());
    }

    /**
     * Length of a many item collection.
     */
    @Test
    public final void testLengthMany() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "one",
                "two", "three", "four", "five", "six", "seven", "eight", "nine",
                "ten", "eleven", "twelve");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "one", "two", "three", "four", "five", "six", "seven", "eight",
                "nine", "ten", "eleven", "twelve");
        assertEquals(mExpected, m);
        assertEquals(mExpected.size(), m.size());
    }

    /**
     * Length after adding a single item.
     */
    @Test
    public final void testLengthAfterAdd() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "one",
                "two");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "one", "two", "three");
        assertEquals(mExpected.size(), m.size() + 1);
        m.add("three");
        assertEquals(mExpected, m);
        assertEquals(mExpected.size(), m.size());
    }

    /**
     * Checks that extraction mode changes just the mode in a collection size
     * zero.
     */
    @Test
    public final void testToExtractionModeZero() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true);
        m.changeToExtractionMode();
        mExpected.changeToExtractionMode();
        assertEquals(mExpected, m);
    }

    /**
     * Checks that extraction mode changes just the mode in a collection of size
     * one.
     */
    @Test
    public final void testToExtractionModeOne() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "one");
        m.add("one");
        m.changeToExtractionMode();
        mExpected.changeToExtractionMode();
        assertEquals(mExpected, m);
    }

    /**
     * Checks that extraction mode changes just the mode in a collection of size
     * three.
     */
    @Test
    public final void testToExtractionModeBig() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "one",
                "two", "three", "four", "five", "six", "seven", "eight", "nine",
                "ten", "eleven");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "one", "two", "three", "four", "five", "six", "seven", "eight",
                "nine", "ten", "eleven", "twelve");
        m.add("twelve");
        m.changeToExtractionMode();
        mExpected.changeToExtractionMode();
        assertEquals(mExpected, m);
    }

    /**
     * RemovesFirst on a single item collection.
     */
    @Test
    public final void testRemoveOne() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "one");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "one");
        m.removeFirst();
        mExpected.removeFirst();
        assertEquals(mExpected, m);
    }

    /**
     * RemovesFirst on a multi item collection.
     */
    @Test
    public final void testRemoveLarge() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "one",
                "two", "three", "four", "five", "six", "seven", "eight", "nine",
                "ten", "eleven", "twelve");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "one", "two", "three", "four", "five", "six", "seven", "eight",
                "nine", "ten", "eleven", "twelve");
        m.removeFirst();
        mExpected.removeFirst();
        assertEquals(mExpected, m);
    }

    /**
     * Insertion mode on a zero size collection.
     */
    @Test
    public final void testInsertionModeZero() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true);
        assertTrue(m.isInInsertionMode());
        assertEquals(mExpected, m);
        m.changeToExtractionMode();
        mExpected.changeToExtractionMode();
        assertTrue(!m.isInInsertionMode());
        assertEquals(mExpected, m);
    }

    /**
     * Insertion mode on a single item collection.
     */
    @Test
    public final void testInsertionModeOne() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "one");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "one");
        assertTrue(m.isInInsertionMode());
        assertEquals(mExpected, m);
        m.changeToExtractionMode();
        mExpected.changeToExtractionMode();
        assertTrue(!m.isInInsertionMode());
        assertEquals(mExpected, m);
    }

    /**
     * Insertion mode on a multi item collection.
     */
    @Test
    public final void testInsertionModeMany() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "one",
                "two", "three", "four", "five", "six", "seven", "eight", "nine",
                "ten", "eleven", "twelve");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "one", "two", "three", "four", "five", "six", "seven", "eight",
                "nine", "ten", "eleven", "twelve");
        assertTrue(m.isInInsertionMode());
        assertEquals(mExpected, m);
        m.changeToExtractionMode();
        mExpected.changeToExtractionMode();
        assertTrue(!m.isInInsertionMode());
        assertEquals(mExpected, m);
    }

    /**
     * Insertion mode while initialized with it false - zero item collection.
     */
    @Test
    public final void testInsertionModeZeroFalse() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false);
        assertTrue(!m.isInInsertionMode());
        assertEquals(mExpected, m);
    }

    /**
     * Insertion mode while initialized with it false - single item collection.
     */
    @Test
    public final void testInsertionModeOneFalse() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "one");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "one");
        assertTrue(!m.isInInsertionMode());
        assertEquals(mExpected, m);
    }

    /**
     * Insertion mode while initialized with it false - multi item collection.
     */
    @Test
    public final void testInsertionModeManyFalse() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "one",
                "two", "three", "four", "five", "six", "seven", "eight", "nine",
                "ten", "eleven", "twelve");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "one", "two", "three", "four", "five", "six", "seven", "eight",
                "nine", "ten", "eleven", "twelve");
        assertTrue(!m.isInInsertionMode());
        assertEquals(mExpected, m);
    }

    /**
     * Insertion mode while initialized with it false - zero item collection.
     */
    @Test
    public final void testCompareEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false);
        assertEquals(mExpected.order(), m.order());
        assertEquals(mExpected, m);
    }

    /**
     * Insertion mode while initialized with it false - single item collection.
     */
    @Test
    public final void testCompareOne() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "one");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "one");
        assertEquals(mExpected.order(), m.order());
        assertEquals(mExpected, m);
    }

    /**
     * Insertion mode while initialized with it false - multi item collection.
     */
    @Test
    public final void testCompareMany() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "one",
                "two", "three", "four", "five", "six", "seven", "eight", "nine",
                "ten", "eleven", "twelve");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "one", "two", "three", "four", "five", "six", "seven", "eight",
                "nine", "ten", "eleven", "twelve");
        assertEquals(mExpected.order(), m.order());
        assertEquals(mExpected, m);
    }
    // TODO - add test cases for add, changeToExtractionMode, removeFirst,
    // isInInsertionMode, order, and size

}
