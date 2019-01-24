package domain.vo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DateTimeSpan {
    private static final String TIME_FORMAT = "HH:mm";

    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;

    public DateTimeSpan(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public LocalDate getStartDate() {
        return startDateTime.toLocalDate();
    }

    public LocalDate getEndDate() {
        return endDateTime.toLocalDate();
    }

    public String getStartTime() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern(TIME_FORMAT);
        return startDateTime.format(format);
    }

    public String getEndTime() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern(TIME_FORMAT);
        return endDateTime.format(format);
    }

    public boolean isSameDay() {
        return startDateTime.toLocalDate().equals(endDateTime.toLocalDate());
    }

    public List<LocalDate> getDateList() {
        List<LocalDate> dateList = new ArrayList<>();
        long days = ChronoUnit.DAYS.between(startDateTime.toLocalDate(), endDateTime.toLocalDate());
        for (long i = 0; i <= days; i++) {
            dateList.add(startDateTime.toLocalDate().plusDays(i));
        }
        return dateList;
    }

    /**
     * 判断当前的日期是否包含对应的时间段
     * 比如：当前是21号15:00--23号13:00，而传入的参数是22号09:00--22号19:00，那么就是包含
     * @param otherDateTimeSpan 对比的时间段
     * @return 是否包含
     */
    public boolean contains(DateTimeSpan otherDateTimeSpan) {
        long startDateTimeStamp = startDateTime.toEpochSecond(ZoneOffset.UTC);
        long endDateTimeStamp = endDateTime.toEpochSecond(ZoneOffset.UTC);

        long otherStartDateTimeStamp = otherDateTimeSpan.getStartDateTime().toEpochSecond(ZoneOffset.UTC);
        long otherEndDateTimeStamp = otherDateTimeSpan.getEndDateTime().toEpochSecond(ZoneOffset.UTC);

        return startDateTimeStamp <= otherStartDateTimeStamp && endDateTimeStamp >= otherEndDateTimeStamp;
    }

    /**
     * 时间段差集
     * 比如：当前是21号15:00--23号13:00，而传入的参数是22号09:00--22号19:00，那么差集就是：
     * 21号15:00--22号09:00
     * 22号19:00--23号13:00
     * @param newDateTimeSpan 新的时间段
     * @return 当前时间段比新时间多出来的部分
     */
    public List<DateTimeSpan> diff(DateTimeSpan newDateTimeSpan) {
        long startDateTimeStamp = startDateTime.toEpochSecond(ZoneOffset.UTC);
        long endDateTimeStamp = endDateTime.toEpochSecond(ZoneOffset.UTC);

        long newStartDateTimeStamp = newDateTimeSpan.getStartDateTime().toEpochSecond(ZoneOffset.UTC);
        long newEndDateTimeStamp = newDateTimeSpan.getEndDateTime().toEpochSecond(ZoneOffset.UTC);

        List<DateTimeSpan> diffList = new ArrayList<>();

        if (newStartDateTimeStamp > startDateTimeStamp) {
            diffList.add(new DateTimeSpan(startDateTime, newDateTimeSpan.getStartDateTime()));
        }

        if (newEndDateTimeStamp < endDateTimeStamp) {
            diffList.add(new DateTimeSpan(newDateTimeSpan.getEndDateTime(), endDateTime));
        }

        return diffList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DateTimeSpan that = (DateTimeSpan) o;
        return Objects.equals(startDateTime, that.startDateTime) &&
                Objects.equals(endDateTime, that.endDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDateTime, endDateTime);
    }

    @Override
    public String toString() {
        return "DateTimeSpan{" +
                "startDateTime=" + startDateTime +
                ", endDateTime=" + endDateTime +
                '}';
    }
}
