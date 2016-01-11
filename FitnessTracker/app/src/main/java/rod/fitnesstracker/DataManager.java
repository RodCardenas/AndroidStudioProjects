package rod.fitnesstracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rodrigo on 12/8/2015.
 */

public class DataManager
{
    // Database fields
    private SQLiteDatabase database;
    private MySQLLiteHelper dbHelper;
    private String[] allColumns =
            {
                    MySQLLiteHelper.COLUMN_ID,
                    MySQLLiteHelper.COLUMN_DATE,
                    MySQLLiteHelper.COLUMN_WEIGHT,
                    MySQLLiteHelper.COLUMN_PUSHUPS,
                    MySQLLiteHelper.COLUMN_SITUPS,
                    MySQLLiteHelper.COLUMN_SQUATS,
                    MySQLLiteHelper.COLUMN_DISTANCE
            };

    public DataManager(Context context)
    {
        dbHelper = new MySQLLiteHelper(context);
    }

    public void open() throws SQLException
    {
        database = dbHelper.getWritableDatabase();
    }

    public void close()
    {
        dbHelper.close();
    }

    public Data createData(long date, double weight, int pushups, int situps, int squats, double distance)
    {
        ContentValues values = new ContentValues();

        values.put(MySQLLiteHelper.COLUMN_DATE, date);
        values.put(MySQLLiteHelper.COLUMN_WEIGHT, weight);
        values.put(MySQLLiteHelper.COLUMN_PUSHUPS, pushups);
        values.put(MySQLLiteHelper.COLUMN_SITUPS, situps);
        values.put(MySQLLiteHelper.COLUMN_SQUATS, squats);
        values.put(MySQLLiteHelper.COLUMN_DISTANCE, distance);

        long insertId = database.insert(MySQLLiteHelper.TABLE_FITNESS, null, values);
        Cursor cursor = database.query(MySQLLiteHelper.TABLE_FITNESS, allColumns,
                MySQLLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Data newData = cursorToData(cursor);
        cursor.close();
        return newData;
    }

    public void deleteData(Data dta)
    {
        long id = dta.getId();
        System.out.println("Data deleted with id: " + id);
        database.delete(MySQLLiteHelper.TABLE_FITNESS, MySQLLiteHelper.COLUMN_ID + " = " + id, null);
    }

    public List<Data> getAllData()
    {
        List<Data> fitnessData = new ArrayList<Data>();

        Cursor cursor = database.query(MySQLLiteHelper.TABLE_FITNESS, allColumns,
                null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            Data dta = cursorToData(cursor);
            fitnessData.add(dta);
            cursor.moveToNext();
        }

        cursor.close();
        return fitnessData;
    }

    private Data cursorToData(Cursor cursor) {
        Data dta = new Data();

        //long id, int date, double weight, int pushups, int situps, int squats, double distance
        dta.setId(cursor.getLong(0));
        dta.setDate(cursor.getLong(1));
        dta.setWeight(cursor.getDouble(2));
        dta.setPushups(cursor.getInt(3));
        dta.setSitups(cursor.getInt(4));
        dta.setSquats(cursor.getInt(5));
        dta.setDistance(cursor.getDouble(6));

        return dta;
    }
}