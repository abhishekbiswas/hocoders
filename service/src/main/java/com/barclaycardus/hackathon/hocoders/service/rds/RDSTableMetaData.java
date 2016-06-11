package com.barclaycardus.hackathon.hocoders.service.rds;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Created by abhishek on 11/06/16.
 */

public class RDSTableMetaData<T> {

    private String tableName;
    private List<String> dependencyList = new ArrayList<String>();
    private List<String> uniqueColumnsList = new ArrayList<String>();
    private Class<T> clazz;

    public RDSTableMetaData(Class<T> clazz) throws Exception {
        this.clazz = clazz;

        Entity table = this.clazz.getAnnotation(Entity.class);
        if(table == null){
            throw new Exception("Class <" + this.clazz.getName() + "> must have <" + Entity.class.getName() + "> annotation");
        }
        this.tableName = table.name();

        RDSDependencyAttribute dependencyAttribute = this.clazz.getAnnotation(RDSDependencyAttribute.class);
        if(dependencyAttribute != null) {
            for (Class dtoClass : dependencyAttribute.dependsOn()) {
                Entity dependentTable = (Entity)dtoClass.getAnnotation(Entity.class);
                if(dependentTable == null){
                    throw new Exception("Class <" + dtoClass.getName() + "> does not have <" + Entity.class.getName() + "> annotation");
                }
                this.dependencyList.add(dependentTable.name());
            }
        }

        Table tableAttribute = this.clazz.getAnnotation(Table.class);
        if (tableAttribute != null) {
            for (UniqueConstraint uq : tableAttribute.uniqueConstraints()) {
                uniqueColumnsList.addAll(Arrays.asList(uq.columnNames()));
            }
        }

        if (uniqueColumnsList.size() == 0) {
            for (Field field : this.clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(Id.class)) {
                    this.uniqueColumnsList.add(field.getName());
                }
            }
        }

        if (uniqueColumnsList.size() == 0) {
            throw new Exception("Class <" + this.clazz.getName() + "> is not properly authored");
        }
    }

    public String getTableName(){
        return this.tableName;
    }

    public Class<T> getClazz(){
        return this.clazz;
    }

    public List<String> getDependencyList() {
        return dependencyList;
    }

    public List<String> getUniqueColumnsList() {
        return uniqueColumnsList;
    }
}