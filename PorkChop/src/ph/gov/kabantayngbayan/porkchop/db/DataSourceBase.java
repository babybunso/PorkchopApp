package ph.gov.kabantayngbayan.porkchop.db;

import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public abstract class DataSourceBase<T> {

	protected DBHelper dbHelper;
	protected SQLiteDatabase db;

	public DataSourceBase(Context context) {
		dbHelper = new DBHelper(context);
	}

	public void open() {
		db = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public abstract void insert(T t);

	public abstract boolean delete(T t);

	public abstract boolean deleteAll();

	public abstract boolean update(T t);

	public abstract List<T> getAll();

	public abstract List<T> getByParameter(String params);
	
	public abstract Cursor getByRawQuery(String sql);

}
