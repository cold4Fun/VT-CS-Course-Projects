import student.TestCase;

public class testStackScroll extends TestCase {
    Scroll<String> ab_cd6; // represent [A,B,C,D]
    Scroll<String> e_fg6; // [E, F, G]

    public void setUp() throws Exception {
        ab_cd6 = new StackScroll<>(6);

        ab_cd6.insert("D");

        ab_cd6.insert("C");

        ab_cd6.insert("B");

        ab_cd6.insert("A");

        ab_cd6.advance();
        ab_cd6.advance();

        System.out.println(ab_cd6.toString());
        // hoping it will give me [A B] [C,D]:6
        e_fg6 = new StackScroll<>(6);

        e_fg6.insert("G");

        e_fg6.insert("F");

        e_fg6.insert("E");

// e_fg6.reset();
        e_fg6.advance();
        // System.out.println(e_fg6.toString());
    }

// public void testSetup1() {
//
// assertEquals(2, ab_cd6.leftLength());
// assertEquals(2, ab_cd6.rightLength());
// assertEquals(6, ab_cd6.capacity());
//
// assertEquals(1, e_fg6.leftLength());
// assertEquals(2, e_fg6.rightLength());
// assertEquals(6, e_fg6.capacity());
// }


    public void testDelete() {
        System.out.println(ab_cd6.leftLength());
        System.out.println(ab_cd6.rightLength());
        System.out.println(ab_cd6.toString());
        assertEquals("C", ab_cd6.delete());
        // System.out.println(ab_cd6.toString());
        assertEquals("D", ab_cd6.delete());
        // System.out.println(ab_cd6.toString());

    }


    public void testSwap() {

        ab_cd6.swapRights(e_fg6);

        assertEquals("[E][C D]", e_fg6.toString());

        assertEquals("[A B][F G]", ab_cd6.toString());

    }
}
