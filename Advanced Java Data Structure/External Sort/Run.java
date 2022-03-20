/**
 * This is the run class
 * 
 * @version 01/23/2022
 * @author Administrator
 *
 */
public class Run {

    private long start = 0;
    private long end = 0;
    private long currIndex = 0;
    private long length = 0;

    /**
     * constructor
     * 
     * @param start
     *            s
     * @param end
     *            e
     * @param length
     *            l
     */
    public Run(long start, long end, long length) {
        this.start = start;
        this.end = end;
        this.length = length;
        currIndex = start;
    }


    /**
     * constrcuctor
     * 
     * @param beginIndex
     *            1
     * @param l
     *            2
     */
    public Run(long beginIndex, long l) {
        this.start = beginIndex;
        this.end = l;
        this.currIndex = beginIndex;
    }


    /**
     * get cuurent index
     * 
     * @return long
     */
    public long getCurrIndex() {
        return currIndex;
    }


    /**
     * move current index
     * 
     * @param num
     *            long
     */
    public void moveCurrIndex(long num) {
        currIndex += num;
    }


    /**
     * setter for end
     * 
     * @param index
     *            index to change
     */
    public void setEnd(long index) {
        end = index;
    }


    /**
     * boolean function check empty
     * 
     * @return boolean
     */
    public boolean isEmpty() {
        return currIndex > end;
    }


    /**
     * reset the run
     */
    public void reset() {
        currIndex = start;
    }


    /**
     * getter for satrt
     * 
     * @return long
     */
    public long getStart() {
        return start;
    }


    /**
     * getter for end
     * 
     * @return long
     */
    public long getEnd() {
        return end;
    }


    /**
     * getter for length
     * 
     * @return long
     */
    public long getLength() {
        return length;
    }


    /**
     * getter for tostring
     * 
     * @return String
     */
    public String toString() {
        return "Start " + start + "; end " + end;
    }
}
