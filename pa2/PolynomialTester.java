import java.util.ArrayList;

/**
   A class to test Polynomial class
*/

public class PolynomialTester {
	public static void main(String[] args){

		//tests constructor
		System.out.println("test constructor...");
		Polynomial poly1 = new Polynomial();
		System.out.println(poly1.toFormattedString() + "[exp: 0.0]");

		poly1 = new Polynomial(new Term(0,0));
		System.out.println(poly1.toFormattedString() + "[exp: 0.0]");

		poly1 = new Polynomial(new Term(0,2));
		System.out.println(poly1.toFormattedString() + "[exp: 0.0]");

		poly1 = new Polynomial(new Term(1,2));
		System.out.println(poly1.toFormattedString() + "[exp: x^2]");

		poly1 = new Polynomial(new Term(1,1));
		System.out.println(poly1.toFormattedString() + "[exp: x]");
		System.out.println();

		//tests add
		System.out.println("test add method...");
		Polynomial poly0 = new Polynomial();
		Polynomial poly2 = new Polynomial(new Term(0,3));
		Polynomial poly3 = new Polynomial();
		Polynomial poly4 = new Polynomial(new Term(2.3,2));
		Polynomial poly5 = new Polynomial(new Term(2.7,3));
		Polynomial poly6 = new Polynomial(new Term(2.2,0));
		Polynomial poly7 = new Polynomial(new Term(-1.0,2));
		Polynomial poly8 = new Polynomial(new Term(-1.0,0));
		Polynomial poly9 = new Polynomial(new Term(0,2));
		poly1 = new Polynomial(new Term(1.0,2));
		doOneAdd(poly0, poly1, "term(1,2)");
		doOneAdd(poly1, poly1, "term(2,2)");
		doOneAdd(poly1, poly2, "term(1,2)");
		doOneAdd(poly1, poly3, "term(1,2)");
		doOneAdd(poly1, poly4, "term(3.3,2)");
		doOneAdd(poly1, poly5, "term(2.7,3),term(1,2)");
		doOneAdd(poly1, poly6, "term(1,2),term(2.2,0)");
		doOneAdd(poly1, poly7, "0.0");
		doOneAdd(poly1, poly8, "term(1,2),term(-1,0)");
		doOneAdd(poly1, poly9, "term(1,2)");
		System.out.println();

		//tests eval
		System.out.println("test eval method...");
		poly1 = new Polynomial(new Term(3,3)).add(new Polynomial(new Term(2,1)));
		poly1 = poly1.add(new Polynomial(new Term(7,0)));
		doOneEval(poly1, 2, "35");
		poly2 = new Polynomial(new Term(3.5,0));
		doOneEval(poly2, 2, "3.5");
		System.out.println();

		//tests toFormattedString
		System.out.println("test toFormattedString method...");

		poly1 = new Polynomial();
		doOnePrinting(poly1, "0.0");
		poly1 = new Polynomial(new Term(0.0, 0));
		doOnePrinting(poly1, "0.0");
		poly1 = new Polynomial(new Term(3, 0));
		doOnePrinting(poly1, "3.0");
		poly1 = new Polynomial(new Term(3.7, 1));
		doOnePrinting(poly1, "3.7x");
		poly1 = poly1.add(new Polynomial(new Term(2.5, 2)));
		doOnePrinting(poly1, "2.5x^2 + 3.7x");
		poly1 = poly1.add(new Polynomial(new Term(3, 0)));
		doOnePrinting(poly1, "2.5x^2 + 3.7x + 3.0");
		poly1 = poly1.add(new Polynomial(new Term(-1, 4)));
		doOnePrinting(poly1, "-x^4 + 2.5x^2 + 3.7x + 3.0");
		poly1 = poly1.add(new Polynomial(new Term(1, 5)));
		doOnePrinting(poly1, "x^5 + -x^4 + 2.5x^2 + 3.7x + 3.0");
		poly1 = poly1.add(new Polynomial(new Term(-2, 4)));
		doOnePrinting(poly1, "x^5 + -3.0x^4 + 2.5x^2 + 3.7x + 3.0");
		poly1 = poly1.add(new Polynomial(new Term(-2.5, 2)));
		doOnePrinting(poly1, "x^5 + -3.0x^4 + 3.7x + 3.0");
		System.out.println();


	}

	private static void doOneAdd(Polynomial poly1, Polynomial poly2, String expectedResult){
		System.out.println(poly1.toFormattedString() + "+" + poly2.toFormattedString() + "= ");
		System.out.println(poly1.add(poly2).toFormattedString() + " [exp:" + expectedResult + "]");
		//System.out.println(poly1.add(poly2).toFormattedString());

	}

	private static void doOneEval(Polynomial poly, double x, String expectedResult){
		System.out.println("poly:" + poly.toFormattedString() + " when x = " + x + ", value =" + poly.eval(x) + " [exp:" + expectedResult + "]");
		//System.out.println(poly.eval(x));

	}

	private static void doOnePrinting(Polynomial poly, String expectedPrint){

		System.out.println("FormattedString [exp:"+ expectedPrint + "]: ");
		System.out.println(poly.toFormattedString());

	}
}
