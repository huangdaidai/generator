package com.dd.generator.model;

import lombok.Data;

@Data
public class Field {
    String fieldNameInDB;
    String fieldNameInClass;
    String typeInDB;
    String typeInClass;
    String key;
    String comment;
    
}
