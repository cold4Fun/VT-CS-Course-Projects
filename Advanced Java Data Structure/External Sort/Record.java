import java.nio.ByteBuffer;

/**
 * This is the record class
 * 
 * @version 01/23/2022
 * @author Administrator
 *
 */
public class Record implements Comparable<Record> {
    private long iD;
    private double key;
    private byte[] wholeThing;
    private int flag;

    /**
     * constructor
     * 
     * @param record
     *            byteArr
     */
    public Record(byte[] record) {
        this.wholeThing = record;
        ByteBuffer buff = ByteBuffer.wrap(wholeThing);
        iD = buff.getLong();
        key = buff.getDouble();
        flag = -1;
    }


    /**
     * constructor
     * 
     * @param record
     *            record
     * @param flag
     *            runnumber
     */
    public Record(Record record, int flag) {
        this.iD = record.getID();
        this.key = record.getKey();
        this.flag = flag;
    }


    /**
     * constructor
     * 
     * @param id
     *            id
     * @param key
     *            key
     */
    public Record(long id, double key) {
        this.iD = id;
        this.key = key;
        flag = -1;
    }


    /**
     * getter for record
     * 
     * @return byte[]
     */
    public byte[] getRecord() {
        return wholeThing;
    }


    /**
     * get run num
     * 
     * @return flag
     */
    public int getFlag() {
        return flag;
    }


    /**
     * set Id
     * 
     * @param input
     *            input
     */
    public void setID(long input) {
        iD = input;
    }


    /**
     * seter for data
     * 
     * @param input
     *            input
     */
    public void setData(double input) {
        key = input;
    }


    /**
     * get id
     * 
     * @return id
     */
    public long getID() {
        return iD;
    }


    /**
     * get key
     * 
     * @return key
     */
    public double getKey() {
        return key;
    }


    /**
     * toString
     * 
     * @return string
     */
    public String toString() {
        return iD + " " + key;
    }


    /**
     * compare to
     * 
     *            
     * @return integer that represents big or small
     */
    @Override
    public int compareTo(Record o) {

        if (key < o.key) {
            return -1;
        }
        else if (key == o.key) {
            return 0;
        }
        else {
            return 1;
        }
    }
}
