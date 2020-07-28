// validating/tests/StringInverterTests.java
// (c)2017 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
package validating.tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import validating.Inverter4;
import validating.StringInverter;

import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StringInverterTests {

    StringInverter inverter = new Inverter4();

    @BeforeAll
    static void startMsg() {
        System.out.println(">>> StringInverterTests <<<");
    }

    @Test
    void basicInversion1() {
        String in = "Exit, Pursued by a Bear.";
        String out = "eXIT, pURSUED BY A bEAR.";
        assertEquals(inverter.invert(in), out);
    }

    @Test
    void basicInversion2() {
//        expectThrows(Error.class, () -> {
//            assertEquals(inverter.invert("X"), "X");
//        });
    }

    @Test
    void disallowedCharacters() {
        String disallowed = ";-_()*&^%$#@!~`0123456789";
        String result = disallowed.chars()
                .mapToObj(c -> {
                    String cc = Character.toString((char) c);
                    try {
                        inverter.invert(cc);
                        return "";
                    } catch (RuntimeException e) {
                        return cc;
                    }
                }).collect(Collectors.joining(""));
        assertEquals(result, disallowed);
    }

    @Test
    void allowedCharacters() {
        String lowcase = "abcdefghijklmnopqrstuvwxyz ,.";
        String upcase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ ,.";
        assertEquals(inverter.invert(lowcase), upcase);
        assertEquals(inverter.invert(upcase), lowcase);
    }

    @Test
    void lengthNoGreaterThan30() {
        String str = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
        assertTrue(str.length() > 30);
//        expectThrows(RuntimeException.class, () -> {
//            inverter.invert(str);
//        });
    }

    @Test
    void lengthLessThan31() {
        String str = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
        assertTrue(str.length() < 31);
        inverter.invert(str);
    }
}
