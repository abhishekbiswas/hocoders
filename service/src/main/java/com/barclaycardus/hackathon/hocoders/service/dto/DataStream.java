package com.barclaycardus.hackathon.hocoders.service.dto;

import javax.persistence.*;

/**
 * Created by abhishek on 12/06/16.
 */

@Entity(name = "DataStream")
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"userId"}))
public class DataStream {

    @Id
    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String word;

    @Column(nullable = false)
    private long frequency;

    public DataStream(String userId, String word, long frequency) {
        this.userId = userId;
        this.word = word;
        this.frequency = frequency;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public long getFrequency() {
        return frequency;
    }

    public void setFrequency(long frequency) {
        this.frequency = frequency;
    }
}
