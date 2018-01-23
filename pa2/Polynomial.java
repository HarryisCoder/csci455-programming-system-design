
// CS 455 PA2
// Spring 2017


import java.util.ArrayList;

/**
   A polynomial. Polynomials can be added together, evaluated, and
   converted to a string form for printing.
*/
public class Polynomial {

/* Representation invariant
    --Polynomial is in a simplified form (only one term for any exponent).
    --Terms are shown in decreasing order by exponent.
    --Zero-coefficient terms won't be showed.
*/

    private ArrayList<Term> polynomial;

    /**
       Creates the 0 polynomial
    */
    public Polynomial() {

      polynomial = new ArrayList<Term>();
      assert isValidPolynomial();
    }


    /**
       Creates polynomial with single term given
     */
    public Polynomial(Term term) {

      polynomial = new ArrayList<Term>();
      //do not store zero-coefficient term
      if (term.getCoeff() != 0){
        polynomial.add(term);
      }
      assert isValidPolynomial();
    }


    /**
       Returns the Polynomial that is the sum of this polynomial and b
       (neither poly is modified)
     */
    public Polynomial add(Polynomial b) {

      Polynomial result = new Polynomial();
      // if either addend or augend is an empty polynomial, then the result is the other one
      if (this.polynomial.size() == 0 && b.polynomial.size() != 0){result = b;}
      if (this.polynomial.size() != 0 && b.polynomial.size() == 0){result = this;}

      // if both addend and augend are not empty polynomials, do addition using merge method
      if (this.polynomial.size() != 0 && b.polynomial.size() != 0){
        // i, j are references which point to the current term of polynomials
        int i = 0;
        int j = 0;
        while(i < this.polynomial.size() || j < b.polynomial.size()){
          // when both i and j do not reach the last term of polynomial
          if (i < this.polynomial.size() && j < b.polynomial.size()){
            // if the exponents of current terms from two polynomials are not equal, add the one with larger exponent to result polynomial
            if(j < b.polynomial.size() && b.polynomial.get(j).getExpon() > this.polynomial.get(i).getExpon()){
              result.polynomial.add(b.polynomial.get(j));
              j++;
            }
            else if(i < this.polynomial.size() && b.polynomial.get(j).getExpon() < this.polynomial.get(i).getExpon()){
              result.polynomial.add(this.polynomial.get(i));
              i++;
            }
            // if the exponents of current terms from two polynomials are equal, add them up together and add the sum to result polynomial, if the sum is empty polynomial, discard it
            else if(j < b.polynomial.size() && i < this.polynomial.size() && b.polynomial.get(j).getExpon() == this.polynomial.get(i).getExpon()){
              if (b.polynomial.get(j).getCoeff()+this.polynomial.get(i).getCoeff()!= 0){
                result.polynomial.add(new Term(b.polynomial.get(j).getCoeff()+this.polynomial.get(i).getCoeff(), this.polynomial.get(i).getExpon()));
              }
              i++;
              j++;
            }
          }
          // when j reaches the last term of polynomial, copy all the rest of the terms from the other polynomial into the result polynomial.
          else if (i < this.polynomial.size()){
            result.polynomial.add(this.polynomial.get(i));
            i++;
          }
          // when i reaches the last term of polynomial, copy all the rest of the terms from the other polynomial into the result polynomial.
          else if (j < b.polynomial.size()){
            result.polynomial.add(b.polynomial.get(j));
            j++;
          }
        }
      }
      // if both addend and augend are empty polynomials, do nothing

      assert isValidPolynomial();   // call it on "this"-- the left operand of the add
      assert b.isValidPolynomial();  // call it on the right operand of the add
      assert result.isValidPolynomial();    // call it on the poly created in add
	    return result;   
    }


    /**
       Returns the value of the poly at a given value of x.
     */
    public double eval(double x) {
      double value = 0.0;
      for (int i = 0; i < this.polynomial.size(); i++){
        value += this.polynomial.get(i).getCoeff() * Math.pow(x, this.polynomial.get(i).getExpon());
      }
      assert isValidPolynomial();
	    return value;         
    }


    /**
       Return a String version of the polynomial with the 
       following format, shown by example:
       zero poly:   "0.0"
       1-term poly: "3.2x^2"
       4-term poly: "3.0x^5 + -x^2 + x + -7.9"

       Polynomial is in a simplified form (only one term for any exponent),
       with no zero-coefficient terms, and terms are shown in
       decreasing order by exponent.
    */
    public String toFormattedString() {
      String string = "";

      // empty polynomial
      if(this.polynomial.size() == 0){
        string = "0.0";
      }
      else{
        for (int i = 0; i < this.polynomial.size(); i++){
            
          // for term with -1 coefficient, print "-x^" instead of "-1.0x^"
          // special case to print "1.0"
          if (this.polynomial.get(i).getCoeff() == 1 && this.polynomial.get(i).getExpon() == 0){
              string += this.polynomial.get(i).getCoeff();
          }
          // special case to print "-1.0"
          else if (this.polynomial.get(i).getCoeff() == -1 && this.polynomial.get(i).getExpon() == 0){
              string += this.polynomial.get(i).getCoeff();
          }
 
          else{

            // for term with coefficient 1, print "x^" instead of "1.0x^"
            if (this.polynomial.get(i).getCoeff() == 1){
              string += "x^" + this.polynomial.get(i).getExpon();
            }
            // for term with coefficient -1, print "-x^" instead of "-1.0x^"
            else if (this.polynomial.get(i).getCoeff() == -1){
              string += "-x^" + this.polynomial.get(i).getExpon();
            }
            // for term with zero exponent, only print the coefficient
            else if(this.polynomial.get(i).getExpon() == 0){
              string += this.polynomial.get(i).getCoeff();
            }
            // for term with exponent 1, print "x" instead of "x^1"
            else if(this.polynomial.get(i).getExpon() == 1){
              string += this.polynomial.get(i).getCoeff() + "x";
            }
            else{
              string += this.polynomial.get(i).getCoeff() + "x^" + this.polynomial.get(i).getExpon();
            }
          }

        if(i != this.polynomial.size() - 1){
          string += " + ";
        }   
        }           
      }
      assert isValidPolynomial();
      return string;
  }


    // **************************************************************
    //  PRIVATE METHOD(S)

    /**
       Returns true iff the poly data is in a valid state.
    */
    private boolean isValidPolynomial() {
      
      // check if:
      //1.terms are stored in decreasing order by exponent; 
      //2.only one term for any exponent
      for (int i = 0; i < this.polynomial.size()-1; i++)
      {
        if (this.polynomial.get(i).getExpon() <= this.polynomial.get(i+1).getExpon()){ 
          return false;
        }
      }
      // check Zero-coefficient terms
      for (int i = 0; i < this.polynomial.size(); i++)
      {
        if (this.polynomial.get(i).getCoeff() == 0){  
          return false;
        }
      }
	    return true;     // passed all the tests!
    }

}
