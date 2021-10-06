package be.bitbox.alledaags;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.IDynamoDBMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Arrays;

@Configuration
public class ServerConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServerConfiguration.class);
    private final Environment env;

    public ServerConfiguration(Environment env) {
        this.env = env;
    }

    @Bean
    public IDynamoDBMapper getDynamoDBMapper() {
        if (Arrays.asList(env.getActiveProfiles()).contains("local")) {
            LOGGER.info("[SERVER-CONFIG] Using Local config");
            return new LocalDynamoDBMapper();
        } else {
            LOGGER.info("[SERVER-CONFIG] Using Production config");
            var amazonDynamoDB = AmazonDynamoDBClientBuilder
                    .standard()
                    .withRegion("eu-west-3")
                    .build();
            return new DynamoDBMapper(amazonDynamoDB);
        }
    }
}
