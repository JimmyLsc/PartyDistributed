package com.example.partydistributed.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.partydistributed.utils.People;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CRUD {
    private SQLiteOpenHelper dbHandler;
    private SQLiteDatabase db;

    private List<People> peopleList;

    private static final String[] columns = {
            PeopleDatabase.PEOPLE_ID,
            PeopleDatabase.PEOPLE_NAME
    };
    public CRUD(Context context) {
        dbHandler = new PeopleDatabase(context);
    }

    public void open() {
        db = dbHandler.getWritableDatabase();
    }
    public void close() {
        dbHandler.close();
    }

    public People addPeople(People people) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(PeopleDatabase.PEOPLE_NAME, people.getName());
        long people_id = db.insert(PeopleDatabase.TABLE_NAME, null, contentValues);
        people.setId(people_id);
        return people;
    }

    public People getPeople(long id) {
        Cursor cursor = db.query(PeopleDatabase.TABLE_NAME, columns, PeopleDatabase.PEOPLE_ID + "=?" ,  new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        People people = new People(cursor.getString(1));
        return people;
    }

    public List<People> getAllPeople() {
        Cursor cursor = db.query(PeopleDatabase.TABLE_NAME, columns, null, null, null, null, null );
        if (cursor != null) {
            cursor.moveToFirst();
        }
        peopleList = new ArrayList<>();
        while(cursor.moveToNext()){
            People people = new People();
            people.setId(cursor.getLong(cursor.getColumnIndex(PeopleDatabase.PEOPLE_ID)));
            people.setName(cursor.getString(cursor.getColumnIndex(PeopleDatabase.PEOPLE_NAME)));
            peopleList.add(people);
        }
        return peopleList;
    }

    public int updatePeople(People people) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(PeopleDatabase.PEOPLE_NAME, people.getName());
        return db.update(
                PeopleDatabase.TABLE_NAME, contentValues, PeopleDatabase.PEOPLE_ID+"=?",
                new String[]{String.valueOf(people.getId())});
    }

    public void remove(People people) {
        db.delete(PeopleDatabase.TABLE_NAME, PeopleDatabase.PEOPLE_ID + "=" + people.getId(), null );
    }

    public void removeAll() {
        db.delete(PeopleDatabase.TABLE_NAME,  null, null);
    }

}
