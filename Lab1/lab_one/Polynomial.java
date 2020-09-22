package lab_one;

import javax.sound.midi.Soundbank;

public class Polynomial {
    private Term head;

    public Polynomial(Term head) {
        this.head = head;
    }

    public Term getHead() {
        return this.head;
    }

    /**
     * @param head change head
     */
    public void setHead(Term head) {
        this.head = head;
    }

    /**
     * new term may have same exp with the term in polynomial
     * @param term new term
     */
    public void addTerm(Term term) {
    Term oldHead = head;
        if(head.getExp() < term.getExp()){
            setHead(term);
            term.setNext(oldHead);
            return;
        }

        while(head.next() != null){
            if(head.next().getExp()<term.getExp()){
                term.setNext(head.next());
                head.setNext(term);
                head = oldHead;
                return;
            }
            setHead(head.next());

        }

    
        head = oldHead;


    }

    /**
     * @param another another polynomial
     * @return the sum
     */
    public Polynomial add(Polynomial another) {
        Term oldHead = another.getHead();
        while(another.getHead().next() != null){
            this.addTerm(another.getHead());
            System.out.println(1);
            another.setHead(another.getHead().next());
        }
        addTerm(another.getHead());
        another.setHead(oldHead);

        return this;
    }

    /**
     * example: 4.0x^3+3.2x^2-2.1x^1+1.0x^0
     * xample: -12.0x^9-1.0x^7+3.0x^5+10.0x^2+5.0x^0
     * @return a string representing the polynomial
     */
    @Override
    public String toString() {
        String result = "";
        Term theHeadTerm = head;


        result += head.getCoef()+"x^"+head.getExp();
        setHead(head.next());


        while(head.next() != null){
            if(head.getCoef()>=0){
                result += "+"+head.getCoef()+"x^"+head.getExp();
            }else{
                result += head.getCoef()+"x^"+head.getExp();
            }
            setHead(head.next());
        }

        if(head.getCoef()>=0){
            result += "+"+head.getCoef()+"x^"+head.getExp();
        }else{
            result += head.getCoef()+"x^"+head.getExp();
        }
        head = theHeadTerm;

        return result;
    }

    public static void main(String[] args) {
        /* You can write your test code here,
         * and you can also use junit.
         * */
        Term term = new Term(1, 1);
        Term term1 = new Term(1, 2);
        Term term2 = new Term(1, 3);
        Term term3 = new Term(-1, 4);
        Term term4 = new Term(1, 5);
        Term term5 = new Term(1, 6);

        Polynomial polynomial = new Polynomial(term);
        polynomial.addTerm(term4);
        polynomial.addTerm(term1);
        polynomial.addTerm(term3);
        polynomial.addTerm(term2);
        polynomial.addTerm(term5);
    

        Term term6 = new Term(19,1);
        Term term7 = new Term(222, 2);
        Term term8 = new Term(-222, 55);
        Term term9 = new Term(-1, 4);
    

        Polynomial polynomial2 = new Polynomial(term);
        polynomial2.addTerm(term6);
        polynomial2.addTerm(term9);
        polynomial2.addTerm(term8);
        polynomial2.addTerm(term7);
        polynomial2.addTerm(term5);
    


        System.out.println(polynomial.toString());
        //System.out.println(polynomial.add(polynomial2).toString());
    

    
    }
}
