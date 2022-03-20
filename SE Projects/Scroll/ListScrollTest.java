import student.TestCase;

public class ListScrollTest extends TestCase {

    Scroll<String> ab_cd6; // represent [A,B,C,D]
    Scroll<String> e_fg6; // [E, F, G]
    Scroll<String> hijkl_mnopq10; // [H I J K L] [M N O P Q]

    public void setUp() throws Exception {
        ab_cd6 = new ListScroll<>(6);
        ab_cd6.insert("D");

// System.out.println(ab_cd6.toString());
        ab_cd6.insert("C");

        ab_cd6.insert("B");

        ab_cd6.insert("A");
        // System.out.println(ab_cd6.toString());
        ab_cd6.reset();
        ab_cd6.advance();
        ab_cd6.advance();
        // hoping it will give me [A B] [C,D]:6
        e_fg6 = new ListScroll<>(6);
        e_fg6.insert("E");
        e_fg6.advance();
        e_fg6.insert("F");
        e_fg6.advance();
        e_fg6.insert("G");

        e_fg6.reset();
        e_fg6.advance();
        // hoping it will give me [E][f,G]:6

    }


    public void testSetup1() {

        assertEquals(2, ab_cd6.leftLength());
        assertEquals(2, ab_cd6.rightLength());
        assertEquals(6, ab_cd6.capacity());
// //lecture, will setup1 pass?at end of lecture pass
        assertEquals(1, e_fg6.leftLength());
        assertEquals(2, e_fg6.rightLength());
        assertEquals(6, e_fg6.capacity());

        // System.out.println(e_fg6.toString());
//
// assertEquals(5, hijkl_mnopq10.leftLength());
// assertEquals(5, hijkl_mnopq10.rightLength());
// assertEquals(10, hijkl_mnopq10.capacity());

// System.out.println(hijkl_mnopq10.toString());
    }


    public void testDelete() {
        ab_cd6.delete();
        assertEquals(1, ab_cd6.rightLength());
        ab_cd6.delete();
        assertEquals(0, ab_cd6.rightLength());
// System.out.println(ab_cd6.toString());
// System.out.println("L:" + ab_cd6.leftLength() + " ; R: " + ab_cd6
// .rightLength());

    }


    public void testSwap() {

        ab_cd6.swapRights(e_fg6);

        assertEquals("[E][C D]", e_fg6.toString());
        assertEquals("[A B][F G]", ab_cd6.toString());
// toString();
    }

    // TODO
    // need to start hash
    // goal for this weekend
    // implemented and tested iterator
    // impl and test stackscroll and listscroll
    // equal scrolls, same elements and cursor in same position- stack
    // recordings for equality

}
