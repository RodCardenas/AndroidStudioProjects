package rod.codelibrary.dataManagement;

/**
 * Created by Rodrigo on 7/19/2015.
 */
public class Weight
{
    private long id;
    private double weight;

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public Double getWeight()
    {
        return weight;
    }

    public void setWeight(Double weight)
    {
        this.weight = weight;
    }

    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString()
    {
        return Double.toString(weight);
    }
}
