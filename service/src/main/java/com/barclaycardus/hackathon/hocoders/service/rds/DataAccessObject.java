package com.barclaycardus.hackathon.hocoders.service.rds;

import java.util.List;

/**
 * Created by abhishek on 11/06/16.
 */
public interface DataAccessObject<T> {

    String getResourceName();

    T add(T dto) throws Exception; //DatabaseErrorException;

    List<T> read() throws Exception; //DatabaseErrorException;

    void update(T dto) throws Exception; //DatabaseErrorException;

    void delete(T dto) throws Exception; //DatabaseErrorException;

    List<T> query(String namedQuery, String paramName, String paramValue) throws Exception;

    //List<T> get(QueryDescriptor descriptor) throws DatabaseErrorException;

    //List<T> getItemsByPrimaryKeys(QueryDescriptor queryDescriptor);

    //T getItemByPrimaryKey(Object... keys);
}
