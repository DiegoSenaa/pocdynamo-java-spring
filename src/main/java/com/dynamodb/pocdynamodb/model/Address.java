package com.dynamodb.pocdynamodb.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBDocument
public class Address {

    @DynamoDBAttribute
    private String street;

    @DynamoDBAttribute
    private  String city;

    @DynamoDBAttribute
    private  String country;
}
