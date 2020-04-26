package be.jcrafters.workshop.alledaags;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.IDynamoDBMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServerConfiguration {

    @Bean
    public IDynamoDBMapper getDynamoDBMapper() {
        var amazonDynamoDB = AmazonDynamoDBClientBuilder
                .standard()
                .withRegion("eu-west-3")
                .build();
        return new DynamoDBMapper(amazonDynamoDB);
    }
}
