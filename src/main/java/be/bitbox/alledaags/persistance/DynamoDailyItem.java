package be.bitbox.alledaags.persistance;

import be.bitbox.alledaags.core.DailyItem;
import com.amazonaws.services.dynamodbv2.datamodeling.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;

@DynamoDBTable(tableName = "DailyItem")
public class DynamoDailyItem {

    @DynamoDBHashKey(attributeName = "dateString")
    private String date;

    @DynamoDBRangeKey(attributeName = "title")
    private String title;

    @DynamoDBAttribute(attributeName = "specific")
    private String specific;

    @DynamoDBAttribute(attributeName = "image")
    private String image;

    @DynamoDBAttribute(attributeName = "credit")
    private String credit;

    @DynamoDBAttribute(attributeName = "creditName")
    private String creditName;

    @DynamoDBTypeConverted(converter = InstantConverter.class)
    @DynamoDBAttribute(attributeName = "creationTime")
    private Instant creationTime;

    public DynamoDailyItem() {
    }

    DynamoDailyItem(LocalDate date, DailyItem dailyItem) {
        this.date = date.toString();
        this.title = dailyItem.getTitle();
        this.specific = dailyItem.getSpecific();
        this.image = dailyItem.getImage();
        this.credit = dailyItem.getCredit();
        this.creditName = dailyItem.getCreditName();
        this.creationTime = Instant.now();
    }

    DailyItem asDailyItem() {
        return DailyItem.DailyItemBuilder.aDailyItem()
                .withTitle(title)
                .withCredit(credit)
                .withCreditName(creditName)
                .withImage(image)
                .withSpecific(specific)
                .build();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSpecific() {
        return specific;
    }

    public void setSpecific(String specific) {
        this.specific = specific;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getCreditName() {
        return creditName;
    }

    public void setCreditName(String creditName) {
        this.creditName = creditName;
    }

    public Instant getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Instant creationTime) {
        this.creationTime = creationTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DynamoDailyItem that = (DynamoDailyItem) o;
        return Objects.equals(date, that.date) && Objects.equals(title, that.title) && Objects.equals(specific, that.specific) && Objects.equals(image, that.image) && Objects.equals(credit, that.credit) && Objects.equals(creditName, that.creditName) && Objects.equals(creationTime, that.creationTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, title, specific, image, credit, creditName, creationTime);
    }

    @Override
    public String toString() {
        return "DynamoDailyItem{" +
                "date='" + date + '\'' +
                ", title='" + title + '\'' +
                ", specific='" + specific + '\'' +
                ", image='" + image + '\'' +
                ", credit='" + credit + '\'' +
                ", creditName='" + creditName + '\'' +
                ", creationTime=" + creationTime +
                '}';
    }
}
