import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.util.LinkedList;

/**
 * Firstly try to implement a replacement selection with only one block of bytes
 * 
 * @version 01/23/2022
 * @author Weiting Li
 *
 */
public class Process {

    private final int blockSize = 8192;

    private final int recordSize = 16;

    private final int recordsInBlock = 512;

    // record how much got read from input file
    private long bytesAlreadyRead = 0;

    private long bytesAlreadyWrite = 0;

    private static long bytesWriteRun = 0; // bytes Written in one Run
    // particularly

    private static long beginIndex = 0; // start index of a run

    private LinkedList<Run> runManager = new LinkedList<Run>();

    private int outputSize = 0;

    /**
     * This is the process program to help with the external sort
     * it consists of RS and merge
     * 
     * @param raf
     *            inputFIle
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    public Process(RandomAccessFile raf) throws IOException {

        cleanFile("output.bin");
        cleanFile("merge.bin");

        RandomAccessFile outputFile 
            = new RandomAccessFile("output.bin", "rw"); // the
        // file
        // after
        // RS

        RandomAccessFile mergeFile 
            = new RandomAccessFile("merge.bin", "rw"); // the
        beginIndex = 0;
        bytesWriteRun = 0;
        bytesAlreadyWrite = 0;
        bytesAlreadyRead = 0;
        outputSize = 0;
        runManager.clear();

        byte[] inputBuffer = new byte[blockSize];

        byte[] outputBuffer = new byte[blockSize];

        long originalSize = raf.length();
        // record backing array for the heap

        Record[] recordHeap = new Record[8 * recordsInBlock];

        /// * first step the replacement selection

        // fill in heap for all 8 blocks of records
        MinHeap heap = makeHeap(raf, inputBuffer, recordHeap);

        // initiate two record array for input and output
        Record[] inputBufferRecord = new Record[recordsInBlock];

        Record[] outputBufferRecord = new Record[recordsInBlock];
        /*
         * When file is equal to just 8 blocks
         */
        if (raf.length() == 8 * blockSize) {

            heap.buildheap();

            // output all 8 blocks
            for (int i = 0; i < 8; i++) {

                // for every block of output Records

                for (int indexOut = 0; indexOut < recordsInBlock; indexOut++) {

                    outputBufferRecord[indexOut] = heap.removemin();

                    // to check

                }

                // write to outputFile
                writeToOutputFile(outputBuffer, outputBufferRecord, outputFile,
                    i * blockSize);

            }
            updateRun(runManager, beginIndex, bytesWriteRun);
        } // when it's more than 8
        else if (raf.length() >= 8 * blockSize) {

            heap.buildheap();

            while (bytesAlreadyRead != raf.length()) {

                // counter to measure how much we go thru already

                long temp = bytesAlreadyRead;
                double lastInOutputVal = -1000000;
                // we read a block from raf into inputBuffer

                readABlock(raf, inputBuffer, temp);

                // we change it from bytes to records
                inputBufferRecord = bytesToRecord(inputBuffer);

                for (int i = 0; i < recordsInBlock; i++) { // recordsInBlock =
                    // 512
                    // we get the root
                    // value
                    Record lastInOutput = heap.getRoot();

                    lastInOutputVal = lastInOutput.getKey();

                    outputBufferRecord[outputSize] = lastInOutput;

                    outputSize++;

                    if (outputSize == recordsInBlock) { // if full,
                        // output outputbuffer

                        writeToOutputFile(outputBuffer, outputBufferRecord,
                            outputFile, bytesAlreadyWrite);
                        outputBufferRecord = new Record[recordsInBlock];
                        outputBuffer = new byte[blockSize];
                        // set output index to zero again

                        outputSize = 0;

                        // System.out.println(indexInput);
                    }
                    // this is the first in our input record array
                    Record firtInInput = inputBufferRecord[i];

                    if (firtInInput.getKey() < lastInOutputVal) {

                        heap.recordNextRun(firtInInput);

                    }
                    else {
                        // normal routine
                        heap.modify(0, firtInInput);
                    }

                    // if heap is empty
                    if (heap.currSize() == 0) {
                        // start next run
                        heap.heapnextRun();
                        // output
                        // heap.buildheap();
                        writeToOutputFile2(outputBuffer, outputBufferRecord,
                            outputFile, outputSize);
                        outputSize = 0;
                        outputBufferRecord = new Record[recordsInBlock];
                        outputBuffer = new byte[blockSize];
                        // System.out.println("1");
                        updateRun(runManager, beginIndex, bytesWriteRun);
                        // set things back

                        lastInOutputVal = -1000000;
                    }
                }

                if (bytesAlreadyRead == raf.length()) {

                    if (outputSize > 0) {

                        writeToOutputFile2(outputBuffer, outputBufferRecord,
                            outputFile, outputSize);
                        outputBufferRecord = new Record[recordsInBlock];
                        outputBuffer = new byte[blockSize];
                        outputSize = 0;

                        updateRun(runManager, beginIndex, bytesWriteRun);

                    }
                    else if (!heap.isHeapFull()) {

                        updateRun(runManager, beginIndex, bytesWriteRun);
                    }

                    heap.heapnextRun();

                    int a = 0;

                    while (heap.currSize() != 0) {

                        if (outputSize == recordsInBlock) {

                            writeToOutputFile2(outputBuffer, outputBufferRecord,
                                outputFile, outputSize);
                            outputBufferRecord = new Record[recordsInBlock];
                            outputBuffer = new byte[blockSize];
                            outputSize = 0;

                            a = 0;
                        }

                        outputBufferRecord[a] = heap.removemin();

                        a++;

                        outputSize++;

                    }

                    if (outputSize > 0) {

                        writeToOutputFile2(outputBuffer, outputBufferRecord,
                            outputFile, outputSize);
                        outputBufferRecord = new Record[recordsInBlock];
                        outputBuffer = new byte[blockSize];
                        outputSize = 0;
                    }

                    updateRun(runManager, beginIndex, bytesWriteRun);
                }
            }
        }

        if (originalSize > 8 * blockSize) {

            int numSize = runManager.size();

            // reset all conditions
            beginIndex = 0;
            bytesWriteRun = 0;
            bytesAlreadyWrite = 0;
            bytesAlreadyRead = 0;
            outputSize = 0;

            LinkedList<Run> rSRunStorage = (LinkedList<Run>)runManager.clone();

            LinkedList<Run> tempStorage = new LinkedList<Run>();
            int res = -10000;

            while (res != 1) {
                mergeFile.length();
                res = this.multiWayMerge(rSRunStorage, tempStorage, outputFile,
                    mergeFile, "output.bin");
                outputFile.setLength(0);
                bytesAlreadyWrite = 0;
                if (res != 1 && res > 0) {
                    res = this.multiWayMerge(tempStorage, rSRunStorage,
                        mergeFile, outputFile, "merge.bin");
                    bytesAlreadyWrite = 0;
                    mergeFile.setLength(0);
                }
            }
        }

        if (outputFile.length() > 0) {
            printOutput(outputFile, blockSize);
        }
        else if (mergeFile.length() > 0) {

            printOutput(mergeFile, blockSize);
        }

    }

    /*
     * merge step
     * 
     */


    /**
     * This is the main method of multi merge
     * 
     * @param firstRun
     *            the runmanager of the inputFile
     * @param secondRun
     *            the runmanager of the outputFile
     * @param inputFile
     *            inputFile
     * @param writeFile
     *            writeFile
     * @param str
     *            name of inputFile
     * @return integer that equals to number of runs in the output run manager
     * @throws IOException
     */
    public int multiWayMerge(
        LinkedList<Run> firstRun,
        LinkedList<Run> secondRun,
        RandomAccessFile inputFile,
        RandomAccessFile writeFile,
        String str)
        throws IOException {
        beginIndex = 0;

        while (firstRun.size() != 0) {

            runManager.clear();

            writeFile.seek(0);

            if (firstRun.size() > 0 && firstRun.size() <= 8) {
                runManager = firstRun;
                bytesWriteRun = runManager.get(firstRun.size() - 1).getEnd()
                    - runManager.get(0).getStart();
                updateRun2(secondRun, bytesWriteRun);
                merge(inputFile, writeFile, runManager);

                firstRun.clear();

            }
            else if (firstRun.size() > 8) {
                for (int i = 0; i < 8; i++) {
                    runManager.add(firstRun.getFirst());
                    firstRun.removeFirst();
                }

                bytesWriteRun = runManager.get(8 - 1).getEnd() - runManager.get(
                    0).getStart();
                updateRun2(secondRun, bytesWriteRun);
                merge(inputFile, writeFile, runManager);

            }
        }
        cleanFile(str);

        return secondRun.size();

    }

    /**
     * This is the main merge function
     * 
     * @param outputFile
     * @param mergeFile
     * @param heap
     * @throws IOException
     */


// ----------- main methods that help merge
    /**
     * subsequent merge method
     * 
     * @param runFile
     *            input File
     * @param fileMergeInto
     *            fileMergeInto
     * @param runManager1
     *            input runManager
     * @throws IOException
     */
    public void merge(
        RandomAccessFile runFile,
        RandomAccessFile fileMergeInto,
        LinkedList<Run> runManager1)
        throws IOException {

        LinkedList<Run> runMerge = new LinkedList<Run>();
        LinkedList<Record> recordStore1 = new LinkedList<Record>();
        int start = 0;
        outputSize = 0;
        int abcd = 0;

        Record[] outputBufferRecord = new Record[recordsInBlock];
        byte[] outputBuffer = new byte[blockSize];

        if (runManager1.size() <= 8) {

            for (int i = 0; i < runManager1.size(); i++) {
                int end = (int)runManager1.get(i).getEnd();
                int s = (int)runManager1.get(i).getStart();

                if (end - s + 1 < blockSize) {
                    runMerge.add(new Run(start, start + (end - s + 1) / 16
                        - 1));
                    start = start + (end - s + 1) / 16;
                }
                else {
                    runMerge.add(new Run(start, start + recordsInBlock - 1,
                        recordsInBlock));
                    start = start + recordsInBlock; // 512
                }
                loadIntoList(runFile, recordStore1, runManager1.get(i));
            }

            int runNum = 0;
            Record[] recordheap2 = new Record[runManager1.size()];
            MinHeap heap2 = new MinHeap(recordheap2, 0, runManager1.size());

            for (int j = 0; j < runManager1.size(); j++) {

                int curr = (int)runMerge.get(j).getCurrIndex();
// System.out.println("When adding the heap, run j current index: "
// + curr);
                Record new1 = new Record(recordStore1.get(curr), j);

                runMerge.get(j).moveCurrIndex(1);

                heap2.insert(new1);
            }
            // System.out.println("the heap size : " + heap2.currSize());

            while (!heap2.isEmpty()) {

                Record r = heap2.removemin();

                outputBufferRecord[outputSize] = r;
                outputSize++;

// System.out.println(outputSize);
                if (outputSize == recordsInBlock) {
                    abcd += outputSize;
                    writeToOutputFile3(outputBuffer, outputBufferRecord,
                        fileMergeInto);

                    // set output index to zero again
                    outputSize = 0;
                }

                int rNum = r.getFlag();

                if (!runMerge.get(rNum).isEmpty()) { // 假如他不空
                    // 我們就找現在的index
                    int indexToFind = (int)runMerge.get(rNum).getCurrIndex();
                    //
                    Record nextPutIn = recordStore1.get(indexToFind);

// System.out.println("curr index in linkedlist [" + rNum
// + "] : " + runMerge.get(rNum).getCurrIndex());

                    runMerge.get(rNum).moveCurrIndex(1);

                    heap2.insert(new Record(nextPutIn, rNum));

                }
                else {
                    // System.out.println(heap2.currSize());
                    // System.out.println("Happy");
                    runMerge.get(rNum).reset();
                    if (!runManager1.get(rNum).isEmpty()) {

                        needMoreRecords(rNum, runFile, recordStore1, runMerge);

                        int indexToFind = (int)runMerge.get(rNum)
                            .getCurrIndex();

                        Record nextPutIn = recordStore1.get(indexToFind);

                        runMerge.get(rNum).moveCurrIndex(1);

                        heap2.insert(new Record(nextPutIn, rNum));
                    }
                }
            }
        }
        if (outputSize > 0) {
            abcd += outputSize;
            writeToOutputFile4(outputBuffer, outputBufferRecord, fileMergeInto,
                outputSize);
        }
    }


    /**
     * When merge, we first input blocks into the linkedlist
     * 
     * @param runFile
     *            input File
     * @param recordStore1
     *            record Array
     * @param run
     *            the run manager
     * @throws IOException
     */
    public void loadIntoList(
        RandomAccessFile runFile,
        LinkedList<Record> recordStore1,
        Run run)
        throws IOException {
        runFile.seek(run.getStart());
        int end = (int)run.getEnd();
        int s = (int)run.getStart();

        if (end - s + 1 < blockSize) {
            byte[] input = new byte[(int)(end - s + 1)];
            runFile.read(input);

            ByteBuffer buffer = ByteBuffer.wrap(input);

            for (int j = 0; j < input.length; j += 16) {
                byte[] recordBytes = new byte[recordSize];
                buffer.get(recordBytes);
                Record b = new Record(recordBytes);
                recordStore1.add(b);

            }
// System.out.println("#" + i + " : " + recordStore1.size());

        }
        else {
            byte[] input = new byte[blockSize];
            runFile.read(input);

            ByteBuffer buffer = ByteBuffer.wrap(input);

            for (int j = 0; j < input.length; j += 16) {
                byte[] recordBytes = new byte[recordSize];
                buffer.get(recordBytes);
                Record a = new Record(recordBytes);
                recordStore1.add(a);

            }

        }
        run.moveCurrIndex(blockSize); // 8192
    }


    /**
     * When we need more records
     * 
     * @param rNum
     *            a
     * @param runFile
     *            b
     * @param recordStore1
     *            c
     * @param runMerge
     *            d
     * @throws IOException
     *             e
     */
    public void needMoreRecords(
        int rNum,
        RandomAccessFile runFile,
        LinkedList<Record> recordStore1,
        LinkedList<Run> runMerge)
        throws IOException {

        int a = (int)runManager.get(rNum).getCurrIndex();
        int b = (int)runManager.get(rNum).getEnd();
        runFile.seek(runManager.get(rNum).getCurrIndex());

        if ((b - a + 1) < blockSize) {
            byte[] input = new byte[(int)(b - a + 1)];
            runFile.read(input);

            ByteBuffer buffer = ByteBuffer.wrap(input);

            for (int j = 0; j < input.length; j += 16) {
                byte[] recordBytes = new byte[recordSize];
                buffer.get(recordBytes);
                Record abc = new Record(recordBytes);
                recordStore1.set((int)runMerge.get(rNum).getCurrIndex(), abc);
                runMerge.get(rNum).moveCurrIndex(1);

            }

            runMerge.get(rNum).setEnd(runMerge.get(rNum).getStart() + (b - a
                + 1) / 16 - 1);
        }
        else {
            byte[] input = new byte[blockSize];
            runFile.read(input);

            ByteBuffer buffer = ByteBuffer.wrap(input);

            for (int j = 0; j < input.length; j += 16) {
                byte[] recordBytes = new byte[recordSize];
                buffer.get(recordBytes);
                Record abc = new Record(recordBytes);
                recordStore1.set((int)runMerge.get(rNum).getCurrIndex(), abc);
                runMerge.get(rNum).moveCurrIndex(1);

            }

        }
        runManager.get(rNum).moveCurrIndex(blockSize); // 8192
        runMerge.get(rNum).reset();
    }


// -------------- methods to update the run
    /**
     * This is the method to update our run in RS
     * 
     * @param runList
     *            a
     * @param start
     *            b
     * @param length
     *            c
     */
    public static void updateRun(
        LinkedList<Run> runList,
        long start,
        long length

    ) {
        long end = start + length - 1;
// System.out.println(start + " " + end + " : " + length);
        Run newRun = new Run(start, end, length);
        runList.add(newRun);
        beginIndex += length;
        bytesWriteRun = 0;
        // System.out.println(runList.size());

    }


    /**
     * update 2
     * 
     * @param runManager
     *            a
     * @param bytesIndexRun
     *            b
     */
    private void updateRun2(LinkedList<Run> runL, long bytesIndexRun) {

        runL.add(new Run(beginIndex, beginIndex + bytesIndexRun));
// System.out.println(beginIndex + " " + (beginIndex + bytesWriteRun)
// + " :" + (bytesWriteRun + 1));
        beginIndex += bytesIndexRun + 1;
        bytesWriteRun = -1;

    }


// ------------- output methods
    /**
     * We use this method when the outputBuffer is full in RS
     * 
     * @param outputBuff
     *            a
     * @param outRecordArr
     *            b
     * @param runFile
     *            c
     * @param indexToWrite
     *            d
     * @throws IOException
     *             e
     */
    public void writeToOutputFile(
        byte[] outputBuff,
        Record[] outRecordArr,
        RandomAccessFile runFile,
        long indexToWrite)
        throws IOException {

        // transfer from record to bytes
        outputBuff = recordToByteArr(outRecordArr);
        // seek and write

        // runFile.seek(indexToWrite);
        runFile.write(outputBuff);
        // update bytes already Write parameter to keep track of how far we gone
        bytesAlreadyWrite += outputBuff.length;
        bytesWriteRun += outputBuff.length;
        // System.out.println(outputFile.length());

        outputSize = 0;
    }


    /**
     * (
     * This is a special case for when output buffer is not full and we want to
     * push
     * 
     * @param outputBuff
     *            outputbuffer
     * @param outRecordArr
     *            record array for output
     * @param runFile
     *            file to store
     * @param size
     *            number of bytes
     * @throws IOException
     *             when no input is found
     */
    public void writeToOutputFile2(
        byte[] outputBuff,
        Record[] outRecordArr,
        RandomAccessFile runFile,
        int size)
        throws IOException {
        if (size != 0) {

            // transfer from record to bytes
            outputBuff = recordToByteArr2(outRecordArr, size);

            runFile.write(outputBuff);
            bytesAlreadyWrite += size * 16;
            bytesWriteRun += size * 16;
            // System.out.println(outputFile.length());
            outputBuff = new byte[blockSize];
            outRecordArr = new Record[recordsInBlock];
        }
    }


    /**
     * 
     * @param outputBuff
     *            outputBuffer
     * @param outRecordArr
     *            outputbuffer record array
     * @param runFile
     *            output file
     * @throws IOException
     */
    public void writeToOutputFile3(
        byte[] outputBuff,
        Record[] outRecordArr,
        RandomAccessFile runFile)
        throws IOException {

        // transfer from record to bytes
        outputBuff = recordToByteArr(outRecordArr);
        // seek and write
        runFile.seek(bytesAlreadyWrite);
        // runFile.seek(indexToWrite);
        runFile.write(outputBuff);
        // update bytes already Write parameter to keep track of how far we gone
        bytesAlreadyWrite += outputBuff.length;
        outputBuff = new byte[blockSize];
        outRecordArr = new Record[recordsInBlock];

        // System.out.println(outputFile.length());

        outputSize = 0;
    }


    /**
     * This is a special output Method for multi merge only
     * When the size is less than 512 records
     * 
     * @param outputBuff
     *            outputBuffer
     * @param outRecordArr
     *            outputbuffer record array
     * @param runFile
     *            output file
     * @param size
     *            the input record size
     * @throws IOException
     */
    public void writeToOutputFile4(
        byte[] outputBuff,
        Record[] outRecordArr,
        RandomAccessFile runFile,
        int size)
        throws IOException {
        if (size == 0) {
            System.out.println("invalid application!");
        }
        else if (size != 0) {

            // transfer from record to bytes
            outputBuff = recordToByteArr2(outRecordArr, size);
            runFile.seek(bytesAlreadyWrite);
            runFile.write(outputBuff);
            bytesAlreadyWrite += size * 16;

            // System.out.println(outputFile.length());
            outputBuff = new byte[blockSize];
            outRecordArr = new Record[recordsInBlock];
            outputSize = 0;
        }
    }


    /**
     * bytes to Record Array
     * 
     * @param inputBuffer
     *            input
     * @return Record Array
     */
    public Record[] bytesToRecord(byte[] inputBuffer) {
        ByteBuffer buf = ByteBuffer.wrap(inputBuffer);
        Record[] records = new Record[recordsInBlock];
        int index = 0;
        for (int i = 0; i < blockSize; i += 16) {
            byte[] bytesRecord = new byte[recordSize];
            buf.get(bytesRecord);
            records[index] = new Record(bytesRecord);
            index++;
        }
        return records;
    }


    /**
     * Record to bytes
     * 
     * @param recordArr
     *            record Array
     * @return byte[]
     */

    public byte[] recordToByteArr(Record[] recordArr) {
        // temp buffer to store

        ByteBuffer temp = ByteBuffer.allocate(blockSize);

        for (int i = 0; i < recordsInBlock; i++) {
            temp.putLong(recordArr[i].getID());
            temp.putDouble(recordArr[i].getKey());

        }

        byte[] out = temp.array();

        return out;
    }


    /**
     * The method we use to transfer record[] to byte[] when it
     * 
     * @param recordArr
     *            record Array
     * @param size
     *            record size of input
     * @return byte[]
     */
    public byte[] recordToByteArr2(Record[] recordArr, int size) {
        // temp buffer to store

        ByteBuffer temp = ByteBuffer.allocate(size * 16);

        for (int i = 0; i < size; i++) {
            temp.putLong(recordArr[i].getID());
            temp.putDouble(recordArr[i].getKey());
        }

        byte[] out = temp.array();

        return out;

    }


    /**
     * This method read Exactly 1 block into the heap
     * 
     * @param raf
     *            input file
     * @param input
     *            input buffer
     * @param recordArr
     *            input buffer record array
     * @return Minheap
     * @throws IOException
     */
    public MinHeap makeHeap(
        RandomAccessFile raf,
        byte[] input,
        Record[] recordArr)
        throws IOException {

        int counter1 = 0;

        for (int i = 0; i < 8; i++) {
            // read a block into inputBuffer
            readABlock(raf, input, blockSize * i);

            ByteBuffer buffer = ByteBuffer.wrap(input);

            for (int j = 0; j < blockSize; j += 16) {
                byte[] recordBytes = new byte[recordSize];
                buffer.get(recordBytes);
                recordArr[counter1] = new Record(recordBytes);
                counter1++;

            }
        }
        MinHeap heap = new MinHeap(recordArr, recordArr.length, 8
            * recordsInBlock);
        return heap;

    }


    /**
     * This method read a block into inputBuffer
     * 
     * @param raf
     *            a randomAccessFile
     * @param inputBuffer
     *            byte[]
     * 
     * @param pos
     *            int
     * @throws IOException
     */
    public void readABlock(RandomAccessFile raf, byte[] inputBuffer, long pos)
        throws IOException {

        raf.seek(pos);
        raf.read(inputBuffer);
        bytesAlreadyRead += blockSize;

    }


    /**
     * This is the print method to output standard output required by the spec
     * 
     * @param file
     *            final file
     * @param blockSize
     *            block size
     * @throws IOException
     *             input output exception
     */
    public static void printOutput(RandomAccessFile file, long blockSize)
        throws IOException {

        long val1;
        double val2;

        for (int i = 0, temp = 1; i < file.length(); i += blockSize, temp++) {

            file.seek(i);
            val1 = file.readLong();
            val2 = file.readDouble();

            Record record = new Record(val1, val2);

            System.out.print(record.toString());

            if (temp % 5 == 0) {
                System.out.println();
            }
            else if (i < file.length() - blockSize) {
                System.out.print(" ");
            }

        }

    }


    /**
     * cleanser
     * 
     * @param str
     *            string
     * @throws IOException
     */
    public static void cleanFile(String str) throws IOException {
        FileWriter fw = new FileWriter(str, false);
        fw.close();
    }
}
