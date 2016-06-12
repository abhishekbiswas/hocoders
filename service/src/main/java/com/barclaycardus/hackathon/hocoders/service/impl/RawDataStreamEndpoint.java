package com.barclaycardus.hackathon.hocoders.service.impl;

import com.barclaycardus.hackathon.hocoders.service.dto.DataStream;
import com.barclaycardus.hackathon.hocoders.service.dto.RawDataStream;
import com.barclaycardus.hackathon.hocoders.service.rds.RDSDataAccessObject;
import com.barclaycardus.hackathon.hocoders.service.utils.DataStreamTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Random;

/**
 * Created by abhishek on 12/06/16.
 */

@Component
@Path("datastream")
public class RawDataStreamEndpoint {


    @Inject
    DataStreamTranslator dataStreamTranslator;

    @Autowired
    RDSDataAccessObject<DataStream> dataStreamRDSDataAccessObject;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseEntity<String> addDataStream(List<RawDataStream> rawDataStreams) throws Exception {
        for(RawDataStream rawDataStream : rawDataStreams) {
            DataStream dataStream = (DataStream) dataStreamTranslator.translateFrom(rawDataStream);
            dataStream.setId(new Random().nextInt(999999));
            dataStreamRDSDataAccessObject.add(dataStream);
        }
        return new ResponseEntity<>("Success!", HttpStatus.OK);
    }



}
