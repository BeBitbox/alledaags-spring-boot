package be.jcrafters.workshop.alledaags;

import be.jcrafters.workshop.alledaags.persistance.DynamoDailyItem;
import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.DeleteTableRequest;
import com.amazonaws.services.s3.model.Region;
import org.mockito.Mockito;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.mockito.Mockito.when;

public class LocalDynamoDBMapper implements IDynamoDBMapper {
    private final Set<DynamoDailyItem> set = new HashSet<>();

    @Override
    public <T> DynamoDBMapperTableModel<T> getTableModel(Class<T> aClass) {
        return null;
    }

    @Override
    public <T> DynamoDBMapperTableModel<T> getTableModel(Class<T> aClass, DynamoDBMapperConfig dynamoDBMapperConfig) {
        return null;
    }

    @Override
    public <T> T load(Class<T> aClass, Object o, DynamoDBMapperConfig dynamoDBMapperConfig) {
        return null;
    }

    @Override
    public <T> T load(Class<T> aClass, Object o) {
        return null;
    }

    @Override
    public <T> T load(Class<T> aClass, Object o, Object o1) {
        return null;
    }

    @Override
    public <T> T load(T t) {
        return null;
    }

    @Override
    public <T> T load(T t, DynamoDBMapperConfig dynamoDBMapperConfig) {
        return null;
    }

    @Override
    public <T> T load(Class<T> aClass, Object o, Object o1, DynamoDBMapperConfig dynamoDBMapperConfig) {
        return null;
    }

    @Override
    public <T> T marshallIntoObject(Class<T> aClass, Map<String, AttributeValue> map) {
        return null;
    }

    @Override
    public <T> List<T> marshallIntoObjects(Class<T> aClass, List<Map<String, AttributeValue>> list) {
        return null;
    }

    @Override
    public <T> void save(T t) {
        set.add((DynamoDailyItem) t);
    }

    @Override
    public <T> void save(T t, DynamoDBSaveExpression dynamoDBSaveExpression) {
        set.add((DynamoDailyItem) t);
    }

    @Override
    public <T> void save(T t, DynamoDBMapperConfig dynamoDBMapperConfig) {
        set.add((DynamoDailyItem) t);
    }

    @Override
    public <T> void save(T t, DynamoDBSaveExpression dynamoDBSaveExpression, DynamoDBMapperConfig dynamoDBMapperConfig) {
        set.add((DynamoDailyItem) t);
    }

    @Override
    public void delete(Object o) {

    }

    @Override
    public void delete(Object o, DynamoDBDeleteExpression dynamoDBDeleteExpression) {

    }

    @Override
    public void delete(Object o, DynamoDBMapperConfig dynamoDBMapperConfig) {

    }

    @Override
    public <T> void delete(T t, DynamoDBDeleteExpression dynamoDBDeleteExpression, DynamoDBMapperConfig dynamoDBMapperConfig) {

    }

    @Override
    public void transactionWrite(TransactionWriteRequest transactionWriteRequest) {

    }

    @Override
    public void transactionWrite(TransactionWriteRequest transactionWriteRequest, DynamoDBMapperConfig dynamoDBMapperConfig) {

    }

    @Override
    public List<Object> transactionLoad(TransactionLoadRequest transactionLoadRequest) {
        return null;
    }

    @Override
    public List<Object> transactionLoad(TransactionLoadRequest transactionLoadRequest, DynamoDBMapperConfig dynamoDBMapperConfig) {
        return null;
    }

    @Override
    public List<DynamoDBMapper.FailedBatch> batchDelete(Iterable<?> iterable) {
        return null;
    }

    @Override
    public List<DynamoDBMapper.FailedBatch> batchDelete(Object... objects) {
        return null;
    }

    @Override
    public List<DynamoDBMapper.FailedBatch> batchSave(Iterable<?> iterable) {
        return null;
    }

    @Override
    public List<DynamoDBMapper.FailedBatch> batchSave(Object... objects) {
        return null;
    }

    @Override
    public List<DynamoDBMapper.FailedBatch> batchWrite(Iterable<?> iterable, Iterable<?> iterable1) {
        return null;
    }

    @Override
    public List<DynamoDBMapper.FailedBatch> batchWrite(Iterable<?> iterable, Iterable<?> iterable1, DynamoDBMapperConfig dynamoDBMapperConfig) {
        return null;
    }

    @Override
    public Map<String, List<Object>> batchLoad(Iterable<?> iterable) {
        return null;
    }

    @Override
    public Map<String, List<Object>> batchLoad(Iterable<?> iterable, DynamoDBMapperConfig dynamoDBMapperConfig) {
        return null;
    }

    @Override
    public Map<String, List<Object>> batchLoad(Map<Class<?>, List<KeyPair>> map) {
        return null;
    }

    @Override
    public Map<String, List<Object>> batchLoad(Map<Class<?>, List<KeyPair>> map, DynamoDBMapperConfig dynamoDBMapperConfig) {
        return null;
    }

    @Override
    public <T> PaginatedScanList<T> scan(Class<T> aClass, DynamoDBScanExpression dynamoDBScanExpression) {
        return null;
    }

    @Override
    public <T> PaginatedScanList<T> scan(Class<T> aClass, DynamoDBScanExpression dynamoDBScanExpression, DynamoDBMapperConfig dynamoDBMapperConfig) {
        return null;
    }

    @Override
    public <T> PaginatedParallelScanList<T> parallelScan(Class<T> aClass, DynamoDBScanExpression dynamoDBScanExpression, int i) {
        return null;
    }

    @Override
    public <T> PaginatedParallelScanList<T> parallelScan(Class<T> aClass, DynamoDBScanExpression dynamoDBScanExpression, int i, DynamoDBMapperConfig dynamoDBMapperConfig) {
        return null;
    }

    @Override
    public <T> ScanResultPage<T> scanPage(Class<T> aClass, DynamoDBScanExpression dynamoDBScanExpression, DynamoDBMapperConfig dynamoDBMapperConfig) {
        return null;
    }

    @Override
    public <T> ScanResultPage<T> scanPage(Class<T> aClass, DynamoDBScanExpression dynamoDBScanExpression) {
        return null;
    }

    @Override
    public <T> PaginatedQueryList<T> query(Class<T> aClass, DynamoDBQueryExpression<T> dynamoDBQueryExpression) {
        var mock = Mockito.mock(PaginatedQueryList.class);
        when(mock.stream()).thenReturn(set.stream());
        return mock;
    }

    @Override
    public <T> PaginatedQueryList<T> query(Class<T> aClass, DynamoDBQueryExpression<T> dynamoDBQueryExpression, DynamoDBMapperConfig dynamoDBMapperConfig) {
        return null;
    }

    @Override
    public <T> QueryResultPage<T> queryPage(Class<T> aClass, DynamoDBQueryExpression<T> dynamoDBQueryExpression) {
        return null;
    }

    @Override
    public <T> QueryResultPage<T> queryPage(Class<T> aClass, DynamoDBQueryExpression<T> dynamoDBQueryExpression, DynamoDBMapperConfig dynamoDBMapperConfig) {
        return null;
    }

    @Override
    public int count(Class<?> aClass, DynamoDBScanExpression dynamoDBScanExpression) {
        return 0;
    }

    @Override
    public int count(Class<?> aClass, DynamoDBScanExpression dynamoDBScanExpression, DynamoDBMapperConfig dynamoDBMapperConfig) {
        return 0;
    }

    @Override
    public <T> int count(Class<T> aClass, DynamoDBQueryExpression<T> dynamoDBQueryExpression) {
        return 0;
    }

    @Override
    public <T> int count(Class<T> aClass, DynamoDBQueryExpression<T> dynamoDBQueryExpression, DynamoDBMapperConfig dynamoDBMapperConfig) {
        return 0;
    }

    @Override
    public S3ClientCache getS3ClientCache() {
        return null;
    }

    @Override
    public S3Link createS3Link(String s, String s1) {
        return null;
    }

    @Override
    public S3Link createS3Link(Region region, String s, String s1) {
        return null;
    }

    @Override
    public S3Link createS3Link(String s, String s1, String s2) {
        return null;
    }

    @Override
    public CreateTableRequest generateCreateTableRequest(Class<?> aClass) {
        return null;
    }

    @Override
    public DeleteTableRequest generateDeleteTableRequest(Class<?> aClass) {
        return null;
    }
}
