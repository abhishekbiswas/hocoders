package com.barclaycardus.hackathon.hocoders.service.dto;

/**
 * Created by abhishek on 12/06/16.
 */
public class RawDataStream {

    private String word;
    private long freq;
    private String row;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public long getFreq() {
        return freq;
    }

    public void setFreq(long freq) {
        this.freq = freq;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }
}
