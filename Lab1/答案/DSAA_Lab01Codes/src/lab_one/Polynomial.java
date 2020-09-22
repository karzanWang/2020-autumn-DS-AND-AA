package lab_one;

/**
 * The polynomial is represented as a linked list
 * The terms in polynomial should be kept in order from the smallest to the largest exponent.
 * For example, 3.0 + 3.1 * a ^ 1 + 2.9 * a ^ 3.
 */
public class Polynomial {
    private Term head;

    public Polynomial(Term head) {
        this.head = head;
    }

    public Term getHead() {
        return this.head;
    }

    /**
     * redefine the polynomial
     *
     * @param head new head
     */
    public void setHead(Term head) {
        this.head = head;
    }

    /**
     * new term may have same exp with the term in polynomial
     *
     * @param term new term
     */
    public void addTerm(Term term) {
        if (term == null) {
            System.err.println("Term should not be null");
            System.exit(-1);
        }
        Term prev = null;
        if (term.getExp() < this.head.getExp()) {
            Term head_ptr = this.head;
            this.head = term;
            this.head.setNext(head_ptr);
            return;
        }
        for (Term t = this.head; t != null; t = t.getNext()) {
            if (term.getExp() == t.getExp()) {
                t.setCoef(t.getCoef() + term.getCoef());
                prev = null;
                break;
            } else if (t.getExp() < term.getExp()) prev = t;
        }
        if (prev != null) {
            Term next = prev.getNext();
            prev.setNext(term);
            term.setNext(next);
        }
    }

    /**
     * @param another another polynomial
     * @return the sum
     */
    public Polynomial add(Polynomial another) {
        Term thisHead = this.head;
        Term anotherHead = another.head;
        Term sum = null;
        Term cursor4sum = null;
        while (thisHead != null && anotherHead != null) {
            Term cur;
            if (thisHead.getExp() < anotherHead.getExp()) {
                cur = new Term(thisHead.getCoef(), thisHead.getExp());
                thisHead = thisHead.getNext();
            } else if (thisHead.getExp() > anotherHead.getExp()) {
                cur = new Term(anotherHead.getCoef(), anotherHead.getExp());
                anotherHead = anotherHead.getNext();
            } else {
                cur = new Term(thisHead.getCoef() + anotherHead.getCoef(), thisHead.getExp());
                thisHead = thisHead.getNext();
                anotherHead = anotherHead.getNext();
            }
            if (sum == null) {
                sum = cur;
                cursor4sum = cur;
            } else {
                cursor4sum.setNext(cur);
                cursor4sum = cursor4sum.getNext();
            }
        }

        Term rest;
        if (thisHead != null) {
            rest = thisHead;
        } else rest = anotherHead;
        if (rest != null && cursor4sum != null) {
            for (Term t = rest; t != null; t = t.getNext()) {
                Term cur = new Term(t.getCoef(), t.getExp());
                cursor4sum.setNext(cur);
                cursor4sum = cursor4sum.getNext();
            }
        }
        if (sum == null) {
            System.err.println("can not add two polynomials");
            System.exit(-1);
        }
        return new Polynomial(sum);
    }

    /**
     * example: 1.0*x^-1 + 9.0 - 2.1*x^3 + 3.9*x^4
     *
     * @return a string representing the polynomial
     */
    @Override
    public String toString() {
        Term head = this.getHead();
        StringBuilder sb = new StringBuilder();
        boolean start = true;
        for (Term t = head; t != null; t = t.getNext()) {
            String symbol = t.getCoef() > 0 ? " + " : " - ";
            if (Math.abs(t.getCoef()) < Double.MIN_VALUE) {
                continue;
            }
            if (start) {
                sb.append(t.getCoef());
            } else {
                sb.append(symbol).append(Math.abs(t.getCoef()));
            }
            if (t.getExp() != 0) {
                sb.append("*x^").append(t.getExp());
            }
            start = false;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        /* You can write your test code here,
         * and you can also use junit.
         * */
    }
}
