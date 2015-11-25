package com.example.ormlitedemo.dao;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.ormlitedemo.bean.Person;
import com.example.ormlitedemo.bean.User;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	private static DatabaseHelper instance;
	private static final String DB_NAME = "mytest.db";
	private static final int DATABASEVERSION = 1;

	private Dao<User, Integer> userDao;
	private Dao<Person, Integer> personDao;

	public DatabaseHelper(Context context) {
		super(context, DB_NAME, null, DATABASEVERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database,
			ConnectionSource connectionSource) {
		try {
			TableUtils.createTable(connectionSource, User.class);
			TableUtils.createTable(connectionSource, Person.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase database,
			ConnectionSource connectionSource, int oldVersion, int newVersion) {
		try {
			TableUtils.dropTable(connectionSource, User.class, true);
			TableUtils.dropTable(connectionSource, Person.class, true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static synchronized DatabaseHelper getHelper(Context context) {
		if (instance == null) {
			synchronized (DatabaseHelper.class) {
				if (instance == null) {
					instance = new DatabaseHelper(context);
				}
			}
		}
		return instance;
	}

	public Dao<User, Integer> getUserDao() {
		if (userDao == null) {
			try {
				userDao = getDao(User.class);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return userDao;
	}

	public Dao<Person, Integer> getPersonDao() throws SQLException {
		if (personDao == null) {
			personDao = getDao(Person.class);
		}
		return personDao;
	}

	@Override
	public void close() {
		super.close();
		userDao = null;
		personDao = null;
	}
}
