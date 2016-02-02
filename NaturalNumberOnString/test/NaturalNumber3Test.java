import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber1L;

/**
 * Customized JUnit test fixture for {@code NaturalNumber3}.
 */
public class NaturalNumber3Test extends NaturalNumberTest {

    @Override
    protected final NaturalNumber constructorTest() {
        NaturalNumber test = new NaturalNumber3();
        return test;
    }

    @Override
    protected final NaturalNumber constructorTest(int i) {
        NaturalNumber test = new NaturalNumber3(i);
        return test;
    }

    @Override
    protected final NaturalNumber constructorTest(String s) {
        NaturalNumber test = new NaturalNumber3(s);
        return test;
    }

    @Override
    protected final NaturalNumber constructorTest(NaturalNumber n) {
        NaturalNumber test = new NaturalNumber3(n);
        return test;
    }

    @Override
    protected final NaturalNumber constructorRef() {
        NaturalNumber test = new NaturalNumber1L();
        return test;
    }

    @Override
    protected final NaturalNumber constructorRef(int i) {
        NaturalNumber test = new NaturalNumber1L(i);
        return test;
    }

    @Override
    protected final NaturalNumber constructorRef(String s) {
        NaturalNumber test = new NaturalNumber1L(s);
        return test;
    }

    @Override
    protected final NaturalNumber constructorRef(NaturalNumber n) {
        NaturalNumber test = new NaturalNumber1L(n);
        return test;
    }

}
