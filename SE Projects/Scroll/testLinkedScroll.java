import student.TestCase;

public class testLinkedScroll extends TestCase {
    Scroll<String> ab_cd6; // represent [A,B,C,D]
    Scroll<String> e_fg6; // [E, F, G]
    Scroll<String> abcde_fghij10;

    public void setUp() throws Exception {
        ab_cd6 = new LinkedScroll<>(6);

        ab_cd6.insert("D");
        // ab_cd6.advance();
// System.out.println(ab_cd6.toString());
        ab_cd6.insert("C");
        // ab_cd6.advance();
        ab_cd6.insert("B");
        // ab_cd6.advance();
        ab_cd6.insert("A");
        ab_cd6.advance();
        ab_cd6.advance();
        // System.out.println(ab_cd6.toString());
        // hoping it will give me [A B] [C,D]:6
        e_fg6 = new LinkedScroll<>(6);

        e_fg6.insert("G");

        e_fg6.insert("F");

        e_fg6.insert("E");

        e_fg6.advance();

        abcde_fghij10 = new LinkedScroll<>(10);

        abcde_fghij10.insert("J");

        abcde_fghij10.insert("I");
        abcde_fghij10.insert("H");
        abcde_fghij10.insert("G");
        abcde_fghij10.insert("F");
        abcde_fghij10.insert("E");

        abcde_fghij10.insert("D");
        // ab_cd6.advance();
// System.out.println(ab_cd6.toString());
        abcde_fghij10.insert("C");
        // ab_cd6.advance();
        abcde_fghij10.insert("B");
        // ab_cd6.advance();
        abcde_fghij10.insert("A");

        abcde_fghij10.advance();
        abcde_fghij10.advance();
        abcde_fghij10.advance();
        abcde_fghij10.advance();
        abcde_fghij10.advance();
    }


    public void testSetup1() {

        assertEquals(2, ab_cd6.leftLength());
        assertEquals(2, ab_cd6.rightLength());
        assertEquals(6, ab_cd6.capacity());
// //lecture, will setup1 pass?at end of lecture pass
        assertEquals(1, e_fg6.leftLength());
        assertEquals(2, e_fg6.rightLength());
        assertEquals(6, e_fg6.capacity());
    }


    public void testDelete() {
        ab_cd6.delete();
        assertEquals(1, ab_cd6.rightLength());
        ab_cd6.delete();
        assertEquals(0, ab_cd6.rightLength());
    }


    public void testDelete2() {

        ab_cd6.reset();
        assertEquals("A", ab_cd6.delete());
        assertEquals("B", ab_cd6.delete());
        assertEquals("C", ab_cd6.delete());
        assertEquals("D", ab_cd6.delete());

        assertEquals(null, ab_cd6.delete());

        // System.out.println(ab_cd6.toString());

    }


    public void testSwap() {

        ab_cd6.swapRights(e_fg6);
        // System.out.println(ab_cd6.toString());
        // System.out.println(e_fg6.toString());
        assertEquals("[E][C D]", e_fg6.toString());
        assertEquals("[A B][F G]", ab_cd6.toString());
// toString();
    }


    public void testSwapException() {
        try {
            ab_cd6.swapRights(abcde_fghij10);
        }
        catch (Exception e) {
            // System.out.println("Something is wrong");
        }
    }


    public void testSwap2() {
        try {
            e_fg6.swapRights(abcde_fghij10);
            // System.out.println(e_fg6.toString());
            // System.out.println(abcde_fghij.toString());
        }
        catch (Exception e) {
            System.out.println("Something is wrong");
        }
    }


    public void testSwap3() {
        Scroll<String> e_fg10 = new LinkedScroll<String>(10);
        e_fg10.insert("G");

        e_fg10.insert("F");

        e_fg10.insert("E");

        e_fg10.advanceToEnd();

        try {
            e_fg10.swapRights(abcde_fghij10);
            System.out.println(e_fg10.toString());
            System.out.println(abcde_fghij10.toString());
        }
        catch (Exception e) {
            System.out.println("Something is wrong");
        }
    }
}
