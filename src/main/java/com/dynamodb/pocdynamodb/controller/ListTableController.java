package com.dynamodb.pocdynamodb.controller;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.ListTablesRequest;
import com.amazonaws.services.dynamodbv2.model.ListTablesResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ListTableController {

    @Value("${aws.region}")
    private String awsRegion;

    @Value("${aws.dynamodb.accessKey}")
    private String dynamodbAccessKey;

    @Value("${aws.dynamodb.secretKey}")
    private String dynamodbSecretKey;

    @GetMapping("/listTables")
    public List<String> getTables() {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(dynamodbAccessKey, dynamodbSecretKey);
        AmazonDynamoDB dynamoDB = AmazonDynamoDBClientBuilder
                .standard()
                .withRegion(awsRegion)
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .build();

        ListTablesResult result = null;

        try {

            /* Creating ListTableRequest with limit 50 */
            ListTablesRequest request = new ListTablesRequest();
            request.withLimit(50);

            String lastTable = null;

            while (true) {

                if (lastTable == null) {
                    /* Send First List Table Request */
                    result = dynamoDB.listTables(request);
                } else {
                    /* Send Subsequent List Table Request */
                    result = dynamoDB.listTables(request.withExclusiveStartTableName(lastTable));
                }
                List<String> tabelas;

                /* Getting name of last evaluated table */
                lastTable = result.getLastEvaluatedTableName();
                if (lastTable == null) {
                    break;
                }

            }

        } catch (AmazonServiceException e) {

            System.out.println(e.getErrorMessage());
        }
        return result.getTableNames();

    }
}
