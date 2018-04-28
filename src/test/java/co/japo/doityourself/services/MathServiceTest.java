package co.japo.doityourself.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;

public class MathServiceTest {

    private MathService mathService;

    @Before
    public void setUp(){
        this.mathService = new MathService();
    }

    @Test
    public void decimalToFraction(){
        String fraction1 = mathService.decimalToFraction(0.5);// fraction 1/2
        String fraction2 = mathService.decimalToFraction(1);// fraction 1/1
        String fraction3 = mathService.decimalToFraction(0.35);// fraction 7/20
        String fraction4 = mathService.decimalToFraction(1.625);// fraction 13/8

        assertEquals("1/2",fraction1);
        assertEquals("1/1",fraction2);
        assertEquals("7/20",fraction3);
        assertEquals("13/8",fraction4);
    }

    @Test
    public void getGCD(){
        int gcd1 = mathService.getGCD(2,1);// gcd = 1
        int gcd2 = mathService.getGCD(27,9);// gcd = 9
        int gcd3 = mathService.getGCD(5,100);// gcd = 5
        int gcd4 = mathService.getGCD(100,5);// gcd = 5

        assertEquals(1,gcd1);
        assertEquals(9,gcd2);
        assertEquals(5,gcd3);
        assertEquals(5,gcd4);
    }
}
