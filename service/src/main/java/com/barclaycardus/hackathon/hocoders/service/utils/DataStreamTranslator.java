package com.barclaycardus.hackathon.hocoders.service.utils;

import com.barclaycardus.hackathon.hocoders.service.dto.RawDataStream;
import com.barclaycardus.hackathon.hocoders.service.dto.DataStream;

/**
 * Created by abhishek on 12/06/16.
 */
public class DataStreamTranslator implements Translator {

    @Override
    public Object translateFrom(Object source) throws Exception {
        if(source instanceof RawDataStream) {
            RawDataStream rawDataStream = (RawDataStream) source;
            String userId = rawDataStream.getRow().substring(0, rawDataStream.getRow().indexOf("."));
            DataStream dataStream = new DataStream(userId, rawDataStream.getWord(), rawDataStream.getFreq());
            return dataStream;
        } else {
            throw new Exception("Incompatible Type!");
        }
    }
}
