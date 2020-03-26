
package model;

public class PickupScheduleBean {

    private String day_name;
    private String is_open;
    private String start;
    private String end;

    public String getDayName() {
        return day_name;
    }

    public void setDayName(String day_name) {
        this.day_name = day_name;
    }

    public String getIsOpen() {
        return is_open;
    }

    public void setIsOpen(String is_open) {
        this.is_open = is_open;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
