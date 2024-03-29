package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABSE_NAME = "userinfor.db";
    public DBHelper(Context context) {
        super(context, DATABSE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sqlCreateTable = "CREATE TABLE "+UserMaster.User.TABLE_NAME+" ("+
                UserMaster.User._ID+" INTEGER PRIMARY KEY,"+
                UserMaster.User.COLUMN_NAME_USERNAME+ " TEXT,"+
                UserMaster.User.COLUMN_NAME_EMAIL+ " TEXT,"+
                UserMaster.User.COLUMN_NAME_PASSWORD+ " TEXT);";

                db.execSQL(sqlCreateTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public boolean addUser(String username,String password,String email){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(UserMaster.User.COLUMN_NAME_USERNAME,username);
        values.put(UserMaster.User.COLUMN_NAME_EMAIL,email);
        values.put(UserMaster.User.COLUMN_NAME_PASSWORD,password);

        long reault = db.insert(UserMaster.User.TABLE_NAME,null,values);

        if (reault > 0){
            return true;
        }else{
            return false;
        }


    }

    public boolean checkUser(String username,String password){
        String[] columns = {UserMaster.User._ID};
        SQLiteDatabase db = getReadableDatabase();
        String selection = UserMaster.User.COLUMN_NAME_EMAIL + "=?" +
                " and " + UserMaster.User.COLUMN_NAME_PASSWORD + "=?";
        String[] selectionArgs = {username , password};
        Cursor cursor = db.query(UserMaster.User.TABLE_NAME,columns,selection,selectionArgs,null,null,null);
        int count = cursor.getCount();
        db.close();

        if (count>0){
            return true;
        }else{
            return false;
        }
    }

    public int deleteUser(String email){
        SQLiteDatabase db = getReadableDatabase();

        String select = UserMaster.User.COLUMN_NAME_EMAIL+" Like ? ";
        String[] selectionArgs = {email};

        int result = db.delete(UserMaster.User.TABLE_NAME,select,selectionArgs);

        if (result > 0){
            return 1;
        }else{
            return -1;
        }
    }

  public Cursor readData(String email){
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {UserMaster.User._ID,UserMaster.User.COLUMN_NAME_USERNAME,UserMaster.User.COLUMN_NAME_EMAIL,UserMaster.User.COLUMN_NAME_PASSWORD};
        String select = UserMaster.User.COLUMN_NAME_EMAIL+" Like ? ";
        String[] selectionArgs = {email};
        Cursor cursor = db.query(UserMaster.User.TABLE_NAME,columns,select,selectionArgs,null,null,null);

        return cursor;



    }

    public int updateUser(String UserIdU,String nameU, String emailU,String passwordU){
        SQLiteDatabase db = getReadableDatabase();



        ContentValues values = new ContentValues();
        values.put(UserMaster.User.COLUMN_NAME_EMAIL,emailU);
        values.put(UserMaster.User.COLUMN_NAME_USERNAME,nameU);
        values.put(UserMaster.User.COLUMN_NAME_PASSWORD,passwordU);

        String select = UserMaster.User._ID + " LIKE ? ";
        String[] selectionArgs = {UserIdU};

        int count = db.update(
                UserMaster.User.TABLE_NAME,
                values,
                select,
                selectionArgs
        );

        return count;
    }

}
