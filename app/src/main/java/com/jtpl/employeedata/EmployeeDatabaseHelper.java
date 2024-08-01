package com.jtpl.employeedata;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "employees.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_EMPLOYEES = "employees";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_ADDRESS = "address";
    private static final String COLUMN_DEPARTMENT = "department";
    private static final String COLUMN_GENDER = "gender";
    private static final String COLUMN_FRESHER = "fresher";

    public EmployeeDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_EMPLOYEES + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_ADDRESS + " TEXT, " +
                COLUMN_DEPARTMENT + " TEXT, " +
                COLUMN_GENDER + " TEXT, " +
                COLUMN_FRESHER + " INTEGER)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EMPLOYEES);
        onCreate(db);
    }

    public boolean insertEmployee(String name, String address, String department, String gender, boolean isFresher) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_ADDRESS, address);
        contentValues.put(COLUMN_DEPARTMENT, department);
        contentValues.put(COLUMN_GENDER, gender);
        contentValues.put(COLUMN_FRESHER, isFresher ? 1 : 0);
        long result = db.insert(TABLE_EMPLOYEES, null, contentValues);
        return result != -1;
    }

    @SuppressLint("Range")
    public List<Employee> getAllEmployees() {
        List<Employee> employeeList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_EMPLOYEES, null);
        if (cursor.moveToFirst()) {
            do {
                Employee employee = new Employee();
                employee.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                employee.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
                employee.setAddress(cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS)));
                employee.setDepartment(cursor.getString(cursor.getColumnIndex(COLUMN_DEPARTMENT)));
                employee.setGender(cursor.getString(cursor.getColumnIndex(COLUMN_GENDER)));
                employee.setFresher(cursor.getInt(cursor.getColumnIndex(COLUMN_FRESHER)) == 1);
                employeeList.add(employee);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return employeeList;
    }
}
