package com.dynamodb.pocdynamodb.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBTable(tableName = "client")
public class Customer {

    @DynamoDBHashKey
    private String email;

    @DynamoDBRangeKey
    private String name;

    @DynamoDBAttribute
    private int age;

    @DynamoDBAttribute
    private Address address;

}
