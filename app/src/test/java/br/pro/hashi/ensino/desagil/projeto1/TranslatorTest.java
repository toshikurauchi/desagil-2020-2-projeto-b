// Não é permitido mudar nada neste arquivo.

package br.pro.hashi.ensino.desagil.projeto1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

public class TranslatorTest {
    private Translator translator;

    @Before
    public void setUp() {
        translator = new Translator();
    }

    @Test
    public void toA() {
        Assert.assertEquals('a', translator.morseToChar(".-"));
    }

    @Test
    public void toB() {
        Assert.assertEquals('b', translator.morseToChar("-..."));
    }

    @Test
    public void toC() {
        Assert.assertEquals('c', translator.morseToChar("-.-."));
    }

    @Test
    public void toD() {
        Assert.assertEquals('d', translator.morseToChar("-.."));
    }

    @Test
    public void toE() {
        Assert.assertEquals('e', translator.morseToChar("."));
    }

    @Test
    public void toF() {
        Assert.assertEquals('f', translator.morseToChar("..-."));
    }

    @Test
    public void toG() {
        Assert.assertEquals('g', translator.morseToChar("--."));
    }

    @Test
    public void toH() {
        Assert.assertEquals('h', translator.morseToChar("...."));
    }

    @Test
    public void toI() {
        Assert.assertEquals('i', translator.morseToChar(".."));
    }

    @Test
    public void toJ() {
        Assert.assertEquals('j', translator.morseToChar(".---"));
    }

    @Test
    public void toK() {
        Assert.assertEquals('k', translator.morseToChar("-.-"));
    }

    @Test
    public void toL() {
        Assert.assertEquals('l', translator.morseToChar(".-.."));
    }

    @Test
    public void toM() {
        Assert.assertEquals('m', translator.morseToChar("--"));
    }

    @Test
    public void toN() {
        Assert.assertEquals('n', translator.morseToChar("-."));
    }

    @Test
    public void toO() {
        Assert.assertEquals('o', translator.morseToChar("---"));
    }

    @Test
    public void toP() {
        Assert.assertEquals('p', translator.morseToChar(".--."));
    }

    @Test
    public void toQ() {
        Assert.assertEquals('q', translator.morseToChar("--.-"));
    }

    @Test
    public void toR() {
        Assert.assertEquals('r', translator.morseToChar(".-."));
    }

    @Test
    public void toS() {
        Assert.assertEquals('s', translator.morseToChar("..."));
    }

    @Test
    public void toT() {
        Assert.assertEquals('t', translator.morseToChar("-"));
    }

    @Test
    public void toU() {
        Assert.assertEquals('u', translator.morseToChar("..-"));
    }

    @Test
    public void toV() {
        Assert.assertEquals('v', translator.morseToChar("...-"));
    }

    @Test
    public void toW() {
        Assert.assertEquals('w', translator.morseToChar(".--"));
    }

    @Test
    public void toX() {
        Assert.assertEquals('x', translator.morseToChar("-..-"));
    }

    @Test
    public void toY() {
        Assert.assertEquals('y', translator.morseToChar("-.--"));
    }

    @Test
    public void toZ() {
        Assert.assertEquals('z', translator.morseToChar("--.."));
    }

    @Test
    public void to0() {
        Assert.assertEquals('0', translator.morseToChar("-----"));
    }

    @Test
    public void to1() {
        Assert.assertEquals('1', translator.morseToChar(".----"));
    }

    @Test
    public void to2() {
        Assert.assertEquals('2', translator.morseToChar("..---"));
    }

    @Test
    public void to3() {
        Assert.assertEquals('3', translator.morseToChar("...--"));
    }

    @Test
    public void to4() {
        Assert.assertEquals('4', translator.morseToChar("....-"));
    }

    @Test
    public void to5() {
        Assert.assertEquals('5', translator.morseToChar("....."));
    }

    @Test
    public void to6() {
        Assert.assertEquals('6', translator.morseToChar("-...."));
    }

    @Test
    public void to7() {
        Assert.assertEquals('7', translator.morseToChar("--..."));
    }

    @Test
    public void to8() {
        Assert.assertEquals('8', translator.morseToChar("---.."));
    }

    @Test
    public void to9() {
        Assert.assertEquals('9', translator.morseToChar("----."));
    }

    @Test
    public void fromA() {
        Assert.assertEquals(".-", translator.charToMorse('a'));
    }

    @Test
    public void fromB() {
        Assert.assertEquals("-...", translator.charToMorse('b'));
    }

    @Test
    public void fromC() {
        Assert.assertEquals("-.-.", translator.charToMorse('c'));
    }

    @Test
    public void fromD() {
        Assert.assertEquals("-..", translator.charToMorse('d'));
    }

    @Test
    public void fromE() {
        Assert.assertEquals(".", translator.charToMorse('e'));
    }

    @Test
    public void fromF() {
        Assert.assertEquals("..-.", translator.charToMorse('f'));
    }

    @Test
    public void fromG() {
        Assert.assertEquals("--.", translator.charToMorse('g'));
    }

    @Test
    public void fromH() {
        Assert.assertEquals("....", translator.charToMorse('h'));
    }

    @Test
    public void fromI() {
        Assert.assertEquals("..", translator.charToMorse('i'));
    }

    @Test
    public void fromJ() {
        Assert.assertEquals(".---", translator.charToMorse('j'));
    }

    @Test
    public void fromK() {
        Assert.assertEquals("-.-", translator.charToMorse('k'));
    }

    @Test
    public void fromL() {
        Assert.assertEquals(".-..", translator.charToMorse('l'));
    }

    @Test
    public void fromM() {
        Assert.assertEquals("--", translator.charToMorse('m'));
    }

    @Test
    public void fromN() {
        Assert.assertEquals("-.", translator.charToMorse('n'));
    }

    @Test
    public void fromO() {
        Assert.assertEquals("---", translator.charToMorse('o'));
    }

    @Test
    public void fromP() {
        Assert.assertEquals(".--.", translator.charToMorse('p'));
    }

    @Test
    public void fromQ() {
        Assert.assertEquals("--.-", translator.charToMorse('q'));
    }

    @Test
    public void fromR() {
        Assert.assertEquals(".-.", translator.charToMorse('r'));
    }

    @Test
    public void fromS() {
        Assert.assertEquals("...", translator.charToMorse('s'));
    }

    @Test
    public void fromT() {
        Assert.assertEquals("-", translator.charToMorse('t'));
    }

    @Test
    public void fromU() {
        Assert.assertEquals("..-", translator.charToMorse('u'));
    }

    @Test
    public void fromV() {
        Assert.assertEquals("...-", translator.charToMorse('v'));
    }

    @Test
    public void fromW() {
        Assert.assertEquals(".--", translator.charToMorse('w'));
    }

    @Test
    public void fromX() {
        Assert.assertEquals("-..-", translator.charToMorse('x'));
    }

    @Test
    public void fromY() {
        Assert.assertEquals("-.--", translator.charToMorse('y'));
    }

    @Test
    public void fromZ() {
        Assert.assertEquals("--..", translator.charToMorse('z'));
    }

    @Test
    public void from0() {
        Assert.assertEquals("-----", translator.charToMorse('0'));
    }

    @Test
    public void from1() {
        Assert.assertEquals(".----", translator.charToMorse('1'));
    }

    @Test
    public void from2() {
        Assert.assertEquals("..---", translator.charToMorse('2'));
    }

    @Test
    public void from3() {
        Assert.assertEquals("...--", translator.charToMorse('3'));
    }

    @Test
    public void from4() {
        Assert.assertEquals("....-", translator.charToMorse('4'));
    }

    @Test
    public void from5() {
        Assert.assertEquals(".....", translator.charToMorse('5'));
    }

    @Test
    public void from6() {
        Assert.assertEquals("-....", translator.charToMorse('6'));
    }

    @Test
    public void from7() {
        Assert.assertEquals("--...", translator.charToMorse('7'));
    }

    @Test
    public void from8() {
        Assert.assertEquals("---..", translator.charToMorse('8'));
    }

    @Test
    public void from9() {
        Assert.assertEquals("----.", translator.charToMorse('9'));
    }

    @Test
    public void bfs() {
        String[] gold = new String[]{
                ".",
                "-",
                "..",
                ".-",
                "-.",
                "--",
                "...",
                "..-",
                ".-.",
                ".--",
                "-..",
                "-.-",
                "--.",
                "---",
                "....",
                "...-",
                "..-.",
                ".-..",
                ".--.",
                ".---",
                "-...",
                "-..-",
                "-.-.",
                "-.--",
                "--..",
                "--.-",
                ".....",
                "....-",
                "...--",
                "..---",
                ".----",
                "-....",
                "--...",
                "---..",
                "----.",
                "-----",
        };

        LinkedList<String> codes = translator.getCodes();

        Assert.assertEquals(gold.length, codes.size());

        int i = 0;

        for(String code: codes) {
            Assert.assertEquals(gold[i], code);

            i++;
        }
    }
}
