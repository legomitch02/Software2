import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * JUnit test fixture for {@code NaturalNumber}'s constructors and kernel
 * methods.
 *
 * @authors Tyler Kunkel & Mitchel Kromer
 *
 */
public abstract class NaturalNumberTest {

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @return the new number
     * @ensures constructorTest = 0
     */
    protected abstract NaturalNumber constructorTest();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorTest = i
     */
    protected abstract NaturalNumber constructorTest(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorTest)
     */
    protected abstract NaturalNumber constructorTest(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorTest = n
     */
    protected abstract NaturalNumber constructorTest(NaturalNumber n);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @return the new number
     * @ensures constructorRef = 0
     */
    protected abstract NaturalNumber constructorRef();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorRef = i
     */
    protected abstract NaturalNumber constructorRef(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorRef)
     */
    protected abstract NaturalNumber constructorRef(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorRef = n
     */
    protected abstract NaturalNumber constructorRef(NaturalNumber n);

    /**
     * Tests empty construct.
     *
     */
    @Test
    public final void constructorEmpty() {
        NaturalNumber compare = this.constructorRef();
        NaturalNumber check = this.constructorTest();
        assertEquals(compare, check);
    }

    /**
     * Tests construct with an int.
     */
    @Test
    public final void constructorInt() {
        final int test = 12;
        NaturalNumber compare = this.constructorRef(test);
        NaturalNumber check = this.constructorTest(test);
        assertEquals(compare, check);
    }

    /**
     * Tests construct with a string.
     */
    @Test
    public final void constructorString() {
        NaturalNumber compare = this.constructorRef("12");
        NaturalNumber check = this.constructorTest("12");
        assertEquals(compare, check);
    }

    /**
     * Tests constructor with Natural Number.
     */
    @Test
    public final void constructorNatNum() {
        final int constint = 12;
        NaturalNumber test = new NaturalNumber2(constint);
        NaturalNumber compare = this.constructorRef(test);
        NaturalNumber check = this.constructorTest(test);
        assertEquals(compare, check);
    }

    /**
     * Tests multiply by ten at zero.
     */
    @Test
    public final void multiplyBy10Empty() {
        NaturalNumber compare = this.constructorRef();
        NaturalNumber check = this.constructorTest();
        compare.multiplyBy10(1);
        check.multiplyBy10(1);
        assertEquals(compare, check);
    }

    /**
     * Tests multiply by ten at a regular number.
     */
    @Test
    public final void multiplyBy10NotEmpty() {
        NaturalNumber compare = this.constructorRef("12");
        NaturalNumber check = this.constructorTest("12");
        compare.multiplyBy10(1);
        check.multiplyBy10(1);
        assertEquals(compare, check);
    }

    /**
     * Tests divide by ten for a non-singular and non-near-max int number.
     */
    @Test
    public final void divideBy10Standard() {
        NaturalNumber compare = this.constructorRef("12345");
        NaturalNumber check = this.constructorTest("12345");
        int compareRemainder = compare.divideBy10();
        int checkRemainder = check.divideBy10();
        assertEquals(compare, check);
        assertEquals(compareRemainder, checkRemainder);
    }

    /**
     * Tests divide by ten for a single digit number.
     */
    @Test
    public final void divideBy10OneEntry() {
        NaturalNumber compare = this.constructorRef("1");
        NaturalNumber check = this.constructorTest("1");
        compare.divideBy10();
        check.divideBy10();
        assertEquals(compare, check);
    }

    /**
     * Tests is zero for a zero number.
     */
    @Test
    public final void isZeroTrue() {
        NaturalNumber compare = this.constructorRef();
        NaturalNumber check = this.constructorTest();
        assertTrue(compare.isZero());
        assertTrue(check.isZero());
        assertEquals(compare, check);
    }

    /**
     * Tests is zero for a non zero number.
     */
    @Test
    public final void isZeroFalse() {
        NaturalNumber compare = this.constructorRef("1");
        NaturalNumber check = this.constructorTest("1");
        assertTrue(!compare.isZero());
        assertTrue(!check.isZero());
        assertEquals(compare, check);
    }

    /**
     * Tests max int.
     */
    @Test
    public final void isZeroFalsePastMaxInt() {
        NaturalNumber compare = this.constructorRef("12345678900");
        NaturalNumber check = this.constructorTest("12345678900");
        assertTrue(!compare.isZero());
        assertTrue(!check.isZero());
        assertEquals(compare, check);
    }
}
