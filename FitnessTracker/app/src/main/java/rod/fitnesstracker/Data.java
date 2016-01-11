package rod.fitnesstracker;

/**
 * Created by Rodrigo on 12/8/2015.
 */
public class Data
{
    private long id;
    private long date;
    private double weight;
    private int pushups;
    private int situps;
    private int squats;
    private double distance;


    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public long getDate()
    {
        return date;
    }

    public void setDate(long date)
    {
        this.date = date;
    }

    public Double getWeight()
    {
        return weight;
    }

    public void setWeight(Double weight)
    {
        this.weight = weight;
    }

    public int getPushups()
    {
        return pushups;
    }

    public void setPushups(int pushups)
    {
        this.pushups = pushups;
    }

    public int getSitups() {
        return situps;
    }

    public void setSitups(int situps) {
        this.situps = situps;
    }

    public int getSquats() {
        return squats;
    }

    public void setSquats(int squats) {
        this.squats = squats;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString()
    {
        return date + " Weight = " + Double.toString(weight) + " PushUps = " + pushups  + " SitUps = " + situps + " Squats = " + squats + " Distance = " + Double.toString(distance);
    }
}

