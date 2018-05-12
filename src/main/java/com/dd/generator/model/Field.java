package com.dd.generator.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
