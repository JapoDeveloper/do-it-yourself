package co.japo.doityourself.services;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class MathService {

    /**
     * Convert a decimal number into an equivalent fraction representation
     * @param x the number
     * @return a String that contain the fraction representation of input decimal number
     */
    public String decimalToFraction(double x) {
        String splits[] = String.valueOf(x).split("\\."); // split using decimal
        int b = splits[1].length(); // find the decimal length
        int denominator = (int) Math.pow(10, b); // calculate the denominator
        int numerator = (int) (x * denominator); // calculate the numeratpr Ex. 1.2*10 = 12
        int gcd = getGCD(numerator, denominator); // find the greatest common divisor
        return "" + numerator / gcd + "/" + denominator / gcd;
    }

    /**
     * Get the greatest common divisor between two numbers
     * @param n1 number 1
     * @param n2 number 2
     * @return and int that represent the greatest common divisor between two number
     */
    public int getGCD(int n1, int n2) {
        if (n2 == 0) {
            return n1;
        }
        return getGCD(n2, n1 % n2);
    }

}
