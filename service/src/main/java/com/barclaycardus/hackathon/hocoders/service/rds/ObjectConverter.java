package com.barclaycardus.hackathon.hocoders.service.rds;

import org.dozer.Mapper;

import javax.inject.Inject;

/**
 * Created by abhishek on 11/06/16.
 */
public class ObjectConverter {

    @Inject
    private Mapper dozer;

    public <T> T map(Object source, Class<T> target) {
        return dozer.map(source, target);
    }

}
