package kr.happy.myarmy.UserDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by JY on 2017-05-02.
 */

public class UserDBManager extends SQLiteOpenHelper {

    /*db명, 테이블명, 디비 버전*/
    static final String DB_USER = "Users.db";
    static final String TABLE_USER = "User";
    static final int DB_VERSION = 1;

    Context mContext = null;
    private static UserDBManager mDBManager = null;


    //DB매니저는 싱글톤
    public static synchronized UserDBManager getInstance(Context context) {
        if (mDBManager == null) {
            mDBManager = new UserDBManager(context, DB_USER, null, DB_VERSION);
        }
        return mDBManager;
    }

    private UserDBManager(Context context, String dbName, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, dbName, factory, version);
        mContext = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_USER + //CREATE TABLE
                    "(" + "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "token                TEXT, "+
                    "proimg             BLOB, " +
                    "email                TEXT, " +
                    "pwd                  TEXT, "+
                    "name               TEXT , " +
                    "birth                TEXT , " +
                    "wantjob          TEXT, " +
                    "specialnote     TEXT, " +
                    "edu                  TEXT, " +
                    "certificate        TEXT, " +
                    "address            TEXT, " +
                    "etccareer          TEXT, " +
                    "phone              TEXT ); ");

            db.execSQL("INSERT INTO " + TABLE_USER + "(email, pwd, name, birth, wantjob, specialnote, edu, certificate, address, etccareer, phone) " +
                    "VALUES ('', '', '' , '', '', '', '', '', '', '','' ); ");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (oldVersion < newVersion) {
            //create new table
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
            onCreate(db);
        }
    }


    /*read*/
    public Cursor query(String[] columns,
                        String selection,
                        String[] selectionArgs,
                        String groupBy,
                        String having,
                        String orderBy) {


        return getReadableDatabase().query(TABLE_USER, columns, selection, selectionArgs, groupBy, having, orderBy);
    }

    /*update */
    public int update(ContentValues updateRowValue, String whereClause, String[] whereArgs) {
        return getWritableDatabase().update(TABLE_USER, updateRowValue, whereClause, whereArgs);
    }

    /* insert*/
    public long insert(ContentValues addRowValue) {
        return getWritableDatabase().insert(TABLE_USER, null, addRowValue);
    }

    public int insertAll(ContentValues[] values) {
        SQLiteDatabase db = getWritableDatabase();

        db.beginTransaction();

        try {
            for (ContentValues contentValues : values)
                db.insert(TABLE_USER, null, contentValues);

            db.setTransactionSuccessful();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            db.endTransaction();
        }
        return values.length;
    }

    /*delete*/
    public int delete(String whereClause, String[] whereArgs) {
        return getWritableDatabase().delete(TABLE_USER, whereClause, whereArgs);
    }
}
