import student.TestCase;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

/**
 * This is the test class for externalsort
 * 
 * @version 01/23/2022
 * @author Administrator
 *
 */
public class ExternalsortTest extends TestCase {
// private GenFile file1 = new GenFile();
// private Externalsort sort = new Externalsort();

    private String[] reverse20 = { "reversedTest20.bin", "20" };
// private String[] r20 = { "reversedTest20.bin" };
//
// private String[] sorted20 = { "sortedTest20.bin", "20" };
// private String[] s20 = { "sortedTest20.bin" };

// private String[] sorted16 = { "sortedTest16.bin", "16" };
// private String[] s16 = { "sortedTest16.bin" };

// private String[] random8 = { "random8.lwt", "8" };
    private String[] ra8 = { "random8.lwt" };

// private String[] random16 = { "random16.lwt", "16" };
    private String[] ra16 = { "random16.lwt" };

// private String[] random32 = { "random32.lwt", "32" };
    private String[] ra32 = { "random32.lwt" };

// private String[] random100 = { "random100.lwt", "100" };
// private String[] ra100 = { "random100.lwt" };

    private String[] noE = { "11.bin" };

    /**
     * setup
     */
    public void setUp() throws IOException {
// sort.cleanFile(ra8[0]);
// sort.cleanFile(ra16[0]);
// sort.cleanFile(ra32[0]);
// sort.cleanFile(ra100[0]);
// file1.random(random100);
// file1.random(random8);
// file1.random(random16);
// file1.random(random32);
    }


    /**
     * test case for random when 8 blocks
     * 
     * @throws IOException
     */
    public void testRandom8() throws IOException {
        Externalsort.main(ra8);
        String str =
            "-3955906814185978015 1.4520474484336354E-4 -4072673071300719072"
                + " 0.13257946158606526 3682023122829626338"
                + " 0.26072197427595567 -4211344439904536205 0.3907760196360551"
                + " 4643359594436573302 0.5173554589429346\r\n"
                + "4529947521102172607 0.6313820384155555 -3565273881323245694"
                + " 0.7505919630363447 -6055966597886600697 0.872316067575484";

        assertEquals(systemOut().getHistory(), str);
    }


    /**
     * test case for random when 16 blocks
     * 
     * @throws IOException
     */
    public void testRandom16() throws IOException {
        Externalsort.main(ra16);
        String str =
            "2083881808377378729 1.7855667630894967E-4 -5759720124426955119"
                + " 0.06155648852938178 1937113786208593689"
                + " 0.12241105259867724 5659517779593604742 0.18612529921333465"
                + " 7987701117347401955 0.2504619732935385\r\n"
                + "-7670344659936141087 0.31577035328925285"
                + " -8606930228616684950"
                + " 0.37712256087805374 -8068233477579021731"
                + " 0.4410319290152679 -1606371652046128004 0.506138051619059"
                + " 5782516389547795195 0.5658675197032481\r\n"
                + "-3412663454705440680 0.6234087050097249 82802644301086946"
                + " 0.6821362486437068 6857093806394929371 0.7490301925241456"
                + " 8574260196851700562 0.8137323398802138 9116515216085578998"
                + " 0.8783448063212429\r\n"
                + "6471418524494107781 0.9420588088233887";

        assertEquals(systemOut().getHistory(), str);
    }


    /**
     * test case for random when 32 blocks
     * 
     * @throws IOException
     */
    public void testRandom32() throws IOException {
        Externalsort.main(ra32);
        String str =
            "-1912274043636725510 3.814768078846953E-5 5809226443892319989"
                + " 0.030774420768842736 -1472741859641830037"
                + " 0.06180981458270651 7661509020831976003 0.09331581429265956"
                + " -3587437928649278285 0.12399716673149974\r\n"
                + "-5101222275680741384 0.1535188163407749 8804010144181380541"
                + " 0.18654052914877617 -2574891137314282970"
                + " 0.2200159745500767 8894851597751975058 0.2514932075004961"
                + " -1508910758377444393 0.2843491555040709\r\n"
                + "8163562736968277081 0.3147977905264151 2338977424989516138"
                + " 0.3441331391115643 -4147441067182536209"
                + " 0.3750934250474862 -3299570406694872610 0.40694452356927335"
                + " -7248902526874986462 0.44011335540567587\r\n"
                + "-1484421710649105765 0.4693136191492666 -5908056018780642209"
                + " 0.4997790367712004 5301070110345345253"
                + " 0.5283923953124499 -2774888729319615980 0.5587601955673889"
                + " -4536312224752643339 0.5899111016933491\r\n"
                + "2499239224557647846 0.6226732822782266 -4145692771122999667"
                + " 0.65692779906689 329383308162410329"
                + " 0.6889357724394516 -7612958274529723071 0.721324918550338"
                + " 2931872301678398632 0.7504710828742573\r\n"
                + "7686301505160404232 0.7807754842606264 1615501369410217956"
                + " 0.8125993239992509 757754139929052586"
                + " 0.8440306563377765 8313949224989698312 0.8761035099360309"
                + " -5228794046236433756 0.9084955444464906\r\n"
                + "3075842392626422388 0.9384764227654497 4553617427819954478"
                + " 0.9694548629677925";
//
        assertEquals(systemOut().getHistory(), str);
    }


    /**
     * This test if it throws exceptions correctly
     */
    public void testIOException() {
        try {
            Externalsort.main(noE);
        }
        catch (IOException e) {
            assertTrue(e instanceof FileNotFoundException);
        }
    }


    /**
     * This test if it throws illegalargument corretcly
     *
     * @throws Exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testIllegal() throws Exception {

        Externalsort.main(reverse20);
        assertTrue(systemOut().getHistory().contains("wrong"));
    }
}
