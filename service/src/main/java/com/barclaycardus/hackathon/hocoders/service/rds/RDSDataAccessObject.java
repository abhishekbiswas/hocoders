package com.barclaycardus.hackathon.hocoders.service.rds;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TransactionRequiredException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Created by abhishek on 11/06/16.
 */
public class RDSDataAccessObject<T> implements DataAccessObject<T> {

    // ----------------------------------- FIELDS --------------------------------------
    //private static final Logger logger = LoggerFactory.getLogger(RDSDataAccessObject.class);
    private final RDSTableMetaData<T>                 rdsTableMetaData;
    //private final RDSTableMetaData<RDSStaleMarkerDto> staleMarkerRDSTableMetaData;

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private ObjectConverter objectConverter;

    public RDSDataAccessObject(RDSTableMetaData<T> rdsTableMetaData /*RDSTableMetaData<RDSStaleMarkerDto> staleMarkerRDSTableMetaData*/) {
        this.rdsTableMetaData = rdsTableMetaData;
        //this.staleMarkerRDSTableMetaData = staleMarkerRDSTableMetaData;
    }

    // -------------------------------- Interface DataAccessObject --------------------
    @Override
    public String getResourceName() {
        return rdsTableMetaData.getTableName();
    }

    @Transactional
    @Override
    public T add(T dto) throws Exception {
        try {
            entityManager.persist(dto);
        } catch (PersistenceException | IllegalArgumentException e) {
            throw new Exception(e);
        }
        return dto;
    }

    @Transactional
    @Override
    public void update(T dto) throws Exception{
        try {
            entityManager.merge(dto);
        } catch (IllegalArgumentException | TransactionRequiredException e) {
            throw new Exception(e);
        }
    }

    @Transactional
    @Override
    public List<T> read() throws Exception {
        try {
            String getAllDataQuery = "select c from " + rdsTableMetaData.getTableName() + " c";

            List<T> retVal = new LinkedList<T>();
            List<T> resultList = entityManager.createQuery(getAllDataQuery).getResultList();
            for (T t : resultList) {
                T n = deepCopy(t);
                retVal.add(n);
            }

            return retVal;
        } catch (IllegalArgumentException e) {
            throw new Exception(e);
        }
    }

    @Transactional
    @Override
    public void delete(T dto) throws Exception {
        try {
            entityManager.remove(entityManager.merge(dto));
        } catch (IllegalArgumentException | TransactionRequiredException e) {
            throw new Exception(e);
        }
    }

    private T deepCopy(T t) {
        return objectConverter.map(t, rdsTableMetaData.getClazz());
    }


    // -------------------------------- Interface ICachingStrategy --------------------
//    @Override
//    public ICacheKey getCacheKey(T dto) {
//        try {
//            Map<String, Object> keys = new HashMap<String, Object>();
//            for (String columnName : rdsTableMetaData.getUniqueColumnsList()) {
//                keys.put(columnName, PropertyUtils.getProperty(dto, columnName));
//            }
//            return new RDSCacheKey(keys);
//        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
//            throw new DatabaseConfigurationException(e);
//        }
//    }

//    @Override
//    public ICacheKey getCacheKey(Object... keys) {
//        //TODO
//        throw new DatabaseConfigurationException("Implement this");
//    }

//    @Transactional
//    @Override
//    public long lastUpdated() {
//        long defaultMinValue = (Long.MIN_VALUE + 1);
//        try {
//            Entity table = RDSStaleMarkerDto.class.getAnnotation(Entity.class);
//            String getTableInfo = "select c from " + table.name() + " c where c.tableName = " + "'" + rdsTableMetaData.getTableName() + "'";
//            List<RDSStaleMarkerDto> staleMarkerDtos = entityManager.createQuery(getTableInfo, RDSStaleMarkerDto.class).getResultList();
//            return staleMarkerDtos.size() != 0 ? staleMarkerDtos.get(0).getLastUpdated() : defaultMinValue;
//        } catch (Exception e) {
//            logger.warn("Exception caught during retrieving lastUpdated value from RDSStaleMarker.", e);
//            return defaultMinValue;
//        }
//    }

//    @Transactional
//    @Override
//    public void markStale() {
//        try {
//            RDSStaleMarkerDto staleMarkerDto = new RDSStaleMarkerDto(rdsTableMetaData.getTableName(), System.currentTimeMillis());
//            entityManager.merge(staleMarkerDto);
//            if (!rdsTableMetaData.getDependencyList().isEmpty()) {
//                for (String dependentTable : rdsTableMetaData.getDependencyList()) {
//                    RDSStaleMarkerDto dependentStaleMarkerDto = new RDSStaleMarkerDto(dependentTable, System.currentTimeMillis());
//                    entityManager.merge(dependentStaleMarkerDto);
//                }
//            }
//        } catch (IllegalArgumentException | TransactionRequiredException e) {
//            logger.warn("Exception caught during updating lastUpdated value in RDSStaleMarker.", e);
//        }
//    }

//    @Override
//    public boolean match(KeyDescriptor keyDescriptor, ICacheKey cacheKey) {
//        RDSCacheKey rdsCacheKey = (RDSCacheKey) cacheKey;
//        Map<String, Object> cacheKeys = rdsCacheKey.getKeys();
//        return keyDescriptor.getKeys().entrySet().containsAll(cacheKeys.entrySet());
//    }

}
