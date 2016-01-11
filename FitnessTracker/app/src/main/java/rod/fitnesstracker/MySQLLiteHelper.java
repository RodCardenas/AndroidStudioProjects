package rod.fitnesstracker;

/**
 * Created by Rodrigo on 12/8/2015.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLLiteHelper extends SQLiteOpenHelper
{

    public static final String TABLE_FITNESS = "fitness";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_DATE = "_date";
    public static final String COLUMN_WEIGHT = "weight";
    public static final String COLUMN_PUSHUPS = "pushups";
    public static final String COLUMN_SITUPS = "situps";
    public static final String COLUMN_SQUATS = "squats";
    public static final String COLUMN_DISTANCE = "distance";

    private static final String DATABASE_NAME = "fitness.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_FITNESS + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_DATE + " integer, "
            + COLUMN_WEIGHT + " double not null, "
            + COLUMN_PUSHUPS + " integer, "
            + COLUMN_SITUPS + " integer, "
            + COLUMN_SQUATS + " integer, "
            + COLUMN_DISTANCE + " double not null);";

    public MySQLLiteHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database)
    {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        Log.w(MySQLLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FITNESS);
        onCreate(db);
    }
}
