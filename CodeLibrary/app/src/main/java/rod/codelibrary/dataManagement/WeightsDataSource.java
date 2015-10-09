package rod.codelibrary.dataManagement;

/**
 * Created by Rodrigo on 7/19/2015.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class WeightsDataSource
{
    // Database fields
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns =
            {
                    MySQLiteHelper.COLUMN_ID,
                    MySQLiteHelper.COLUMN_WEIGHT
            };

    public WeightsDataSource(Context context)
    {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException
    {
        database = dbHelper.getWritableDatabase();
    }

    public void close()
    {
        dbHelper.close();
    }

    public Weight createWeight(Double weight)
    {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_WEIGHT, weight);
        long insertId = database.insert(MySQLiteHelper.TABLE_WEIGHTS, null, values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_WEIGHTS, allColumns,
                MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Weight newWeight = cursorToWeight(cursor);
        cursor.close();
        return newWeight;
    }

    public void deleteWeight(Weight weight)
    {
        long id = weight.getId();
        System.out.println("Weight deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_WEIGHTS, MySQLiteHelper.COLUMN_ID + " = " + id, null);
    }

    public List<Weight> getAllWeights()
    {
        List<Weight> weights = new ArrayList<Weight>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_WEIGHTS, allColumns,
                null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            Weight weight = cursorToWeight(cursor);
            weights.add(weight);
            cursor.moveToNext();
        }

        cursor.close();
        return weights;
    }

    private Weight cursorToWeight(Cursor cursor) {
        Weight weight = new Weight();
        weight.setId(cursor.getLong(0));
        weight.setWeight(cursor.getDouble(1));
        return weight;
    }
}
