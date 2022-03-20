import student.TestCase;

public class testAbstractScroll extends TestCase {
    Scroll<String> ab_cd6; // represent [A,B,C,D]
    Scroll<String> e_fg6; // [E, F, G]
    Scroll<String> abcde_fghij10;

    public void setUp() throws Exception {

        ab_cd6 = new ListScroll<>(7);

        ab_cd6.insert("D");

        ab_cd6.insert("C");

        ab_cd6.insert("B");

        ab_cd6.insert("A");

        ab_cd6.reset();

        ab_cd6.advance();

        ab_cd6.advance();

        e_fg6 = new StackScroll<>(6);

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

        abcde_fghij10.insert("C");

        abcde_fghij10.insert("B");

        abcde_fghij10.insert("A");

        abcde_fghij10.advance();

        abcde_fghij10.advance();

        abcde_fghij10.advance();

        abcde_fghij10.advance();

        abcde_fghij10.advance();
    }


    public void testSwapRights() {

        ab_cd6.swapRights(e_fg6);

        assertEquals("[E][C D]", e_fg6.toString());

        assertEquals("[A B][F G]", ab_cd6.toString());
    }


    public void testSwapRights2() {

        e_fg6.swapRights(abcde_fghij10);

        assertEquals(e_fg6.rightLength(), 5);

        assertEquals(abcde_fghij10.rightLength(), 2);

        System.out.println(abcde_fghij10.toString());

        System.out.println(e_fg6.toString());
    }


    public void testReverse() {
        abcde_fghij10.reset();
        abcde_fghij10.reverse();
        System.out.println(abcde_fghij10.toString());
    }


    public void testEmptyReverse() {
        Scroll<String> empty = new LinkedScroll<String>(10);
        empty.reverse();
        assertEquals(empty.leftLength(), 0);
        assertEquals(empty.rightLength(), 0);
    }


    public void testSplice() {
        e_fg6.reset();
        System.out.println(e_fg6.toString());
        ab_cd6.splice(e_fg6);
        System.out.println(e_fg6.toString());
        System.out.println(ab_cd6.toString());
    }


    public void testHashCode() {
        Scroll<String> Sab_cd7 = new StackScroll<>(7);
        Sab_cd7.insert("E");
        Sab_cd7.insert("C");
        Sab_cd7.insert("B");
        Sab_cd7.insert("A");
        Sab_cd7.reset();
        Sab_cd7.advance();
        Sab_cd7.advance();

        System.out.println(Sab_cd7.hashCode());
        System.out.println(ab_cd6.hashCode());
    }


    public void testEquals() {

        assertFalse(ab_cd6.equals(e_fg6));
    }


    public void testToStringEmpty() {
        Scroll<String> Sab_cd7 = new StackScroll<>(7);
        System.out.println(Sab_cd7.toString());
    }
}
