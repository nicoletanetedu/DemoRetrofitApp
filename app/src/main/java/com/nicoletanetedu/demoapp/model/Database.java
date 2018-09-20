package com.nicoletanetedu.demoapp.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.nicoletanetedu.demoapp.model.pojos.Forks;
import com.nicoletanetedu.demoapp.model.pojos.Owner;
import com.nicoletanetedu.demoapp.model.pojos.Repository;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "gitComponents";
    public static final String TABLE_REPOS = "Repositories";
    public static final String TABLE_FORKS = "Forks";


    //Repositories table fields
    public static final String REPO_ID = "id";
    public static final String REPO_NAME = "repo_name";
    public static final String REPO_FULL_NAME = "full_name";
    public static final String REPO_DESCRIPTION = "repo_description";
    public static final String REPO_FORKS_URL = "forks_url";
    public static final String REPO_OWNER_IMAGE = "image";
    public static final String REPO_STARGAZERS_URL = "stargazers_url";

    //Forks table fields
    public static final String FORKS_ID = "fork_id";
    public static final String FORKS_REPO_ID  = "repo_id";
    public static final String FORKS_COUNT = "forks_count";
    public static final String STARGAZERS_COUNT = "stargazers_count";
    public static final String WATCHERS_COUNT = "watchers_count";
    public static final String OPENED_ISSUES  = "opened_issues";

    private static Database instance = null;

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public static Database getInstance(Context c) {
        if (instance == null) {
            instance = new Database(c.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_REPOS +
                "("
                + REPO_ID + " INTEGER PRIMARY KEY,"
                + REPO_NAME + " TEXT, "
                + REPO_FULL_NAME + " TEXT, "
                + REPO_DESCRIPTION + " TEXT, "
                + REPO_FORKS_URL + " TEXT, "
                + REPO_STARGAZERS_URL + " TEXT, "
                + REPO_OWNER_IMAGE + " TEXT "
                + ")";

        String CREATE_TABLE_FORKS = "CREATE TABLE " + TABLE_FORKS +
                "("
                + FORKS_ID + " TEXT PRIMARY KEY,"
                + FORKS_REPO_ID + " INTEGER, "
                + FORKS_COUNT + " TEXT, "
                + STARGAZERS_COUNT + " TEXT, "
                + WATCHERS_COUNT + " TEXT, "
                + OPENED_ISSUES + " TEXT "
                + ")";

        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_TABLE_FORKS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REPOS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FORKS);

        onCreate(db);
    }

    public void insertRepo(Repository repository) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(REPO_ID, repository.getId());
        values.put(REPO_NAME, repository.getRepoName());
        values.put(REPO_FULL_NAME,repository.getFullName());
        values.put(REPO_DESCRIPTION, repository.getRepoDescription());
        values.put(REPO_FORKS_URL, repository.getForksUrl());
        values.put(REPO_STARGAZERS_URL, repository.getStargazersUrl());
        values.put(REPO_OWNER_IMAGE, repository.getOwner().image);

        db.insert(TABLE_REPOS, null, values);
        db.close();
    }

    public void insertForks(Forks fork, int repoId) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(FORKS_ID, fork.getId());
        values.put(FORKS_REPO_ID, repoId);
        values.put(FORKS_COUNT,fork.getForksCount());
        values.put(STARGAZERS_COUNT, fork.getStargazersCount());
        values.put(WATCHERS_COUNT, fork.getWatchersCount());
        values.put(OPENED_ISSUES, fork.getOpenIssues());

        db.insert(TABLE_FORKS, null, values);
        db.close();
    }

    public ArrayList<Repository> getAllRepos() {
        ArrayList<Repository> repoItems = new ArrayList<>();

        String name, image, fullName, description, forks, stargazers;
        int id;
        String selectQuery = "SELECT  * FROM " + TABLE_REPOS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            id = cursor.getInt(0);
            name = cursor.getString(1);
            fullName = cursor.getString(2);
            description = cursor.getString(3);
            forks = cursor.getString(4);
            stargazers = cursor.getString(5);
            image = cursor.getString(6);


            repoItems.add(new Repository(id, name, fullName, description, forks, stargazers, new Owner(image)));

            cursor.moveToNext();
        }
        cursor.close();
        db.close();

        return repoItems;
    }

    public ArrayList<Forks> getAllForks() {
        ArrayList<Forks> repoItems = new ArrayList<>();

        String id;
        int forksNo, stargazersNo, watchersNo, openedIssues;

        String selectQuery = "SELECT  * FROM " + TABLE_FORKS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            id = cursor.getString(0);
            forksNo = cursor.getInt(1);
            stargazersNo = cursor.getInt(2);
            watchersNo = cursor.getInt(3);
            openedIssues = cursor.getInt(4);

            repoItems.add(new Forks(id, forksNo, stargazersNo, watchersNo, openedIssues));

            cursor.moveToNext();
        }
        cursor.close();
        db.close();

        return repoItems;
    }

    public void delete() {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("delete from "
                + TABLE_REPOS);
        db.execSQL("delete from "
                + TABLE_FORKS);
        db.close();
    }
}