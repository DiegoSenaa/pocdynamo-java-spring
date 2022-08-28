package com.dynamodb.pocdynamodb.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.dynamodb.pocdynamodb.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CustomerRepository {
    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public Customer saveCustomer(Customer customer) {
        dynamoDBMapper.save(customer);
        return customer;
    }

    public Customer getCustomerByEmailAndName(String email, String name) {
        return dynamoDBMapper.load(Customer.class, email, name);
    }

    public void deleteCustomer(Customer customer) {
        dynamoDBMapper.delete(customer);
    }

    public List<Customer> scanCustomerByEmail(String email) {

        Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":email", new AttributeValue().withS(email));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("email = :email").withExpressionAttributeValues(eav);

        return dynamoDBMapper.scan(Customer.class, scanExpression);
    }

    public List<Customer> queryCustomerByEmail(String email, String partName) {

        Customer customerOrder = new Customer();
        customerOrder.setEmail(email);

        Condition condition = new Condition();
        condition.withComparisonOperator(ComparisonOperator.BEGINS_WITH)
                .withAttributeValueList(new AttributeValue().withS(partName));

        DynamoDBQueryExpression<Customer> queryExpression =
                new DynamoDBQueryExpression<Customer>()
                        .withHashKeyValues(customerOrder)
                        .withRangeKeyCondition("name", condition)
                        .withLimit(10);
        return dynamoDBMapper.query(Customer.class, queryExpression);
    }
}
