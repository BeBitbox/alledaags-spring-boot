package be.jcrafters.workshop.alledaags.persistance;

import be.jcrafters.workshop.alledaags.core.DailyItem;
import be.jcrafters.workshop.alledaags.core.DailyItemRepository;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.IDynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@Component
public class DynamoDailyItemRepository implements DailyItemRepository {

    private final IDynamoDBMapper dynamoDBMapper;

    public DynamoDailyItemRepository(IDynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    @Override
    public List<DailyItem> getDailyItems() {
        Map<String, AttributeValue> eav = Map.of(":dateString", new AttributeValue().withS(LocalDate.now().toString()));

        DynamoDBQueryExpression<DynamoDailyItem> queryExpression = new DynamoDBQueryExpression<DynamoDailyItem>()
                .withKeyConditionExpression("dateString = :dateString")
                .withExpressionAttributeValues(eav);
        var query = dynamoDBMapper.query(DynamoDailyItem.class, queryExpression);

        if (query == null || query.isEmpty()) {
            return List.of();
        } else {
            return query
                    .stream()
                    .map(DynamoDailyItem::asDailyItem)
                    .collect(toList());
        }
    }

    @Override
    public void save(DailyItem dailyItem, LocalDate localDate) {
        var dynamoDailyItem = new DynamoDailyItem(localDate, dailyItem);
        dynamoDBMapper.save(dynamoDailyItem);
    }
}
