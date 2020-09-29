package lab_one;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PolynomialTest {

    @Test
    void testToString() {
        Term h1 = new Term(1, 3);
        Polynomial p1 = new Polynomial(h1);
        p1.addTerm(new Term(-2, -2));
        p1.addTerm(new Term(-2, 4));
        assertEquals("-2.0*x^-2 + 1.0*x^3 - 2.0*x^4", p1.toString());

        Term h2 = new Term(-3, 2);
        Polynomial p2 = new Polynomial(h2);
        p2.addTerm(new Term(2, 4));
        p2.addTerm(new Term(5, 5));
        assertEquals("-3.0*x^2 + 2.0*x^4 + 5.0*x^5", p2.toString());

        Polynomial sum = p1.add(p2);
        assertEquals("-2.0*x^-2 - 3.0*x^2 + 1.0*x^3 + 5.0*x^5", sum.toString());

    }

}