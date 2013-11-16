package ph.gov.kabantayngbayan.porkchop.db;

import java.util.ArrayList;
import java.util.List;

import ph.gov.kabantayngbayan.porkchop.models.Total;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class TotalDataSource extends DataSourceBase<Total> {

	public TotalDataSource(Context context) {
		super(context);
	}

	private static final String[] allColumns = { 
		DBHelper.COL_ID, 
		DBHelper.COL_TBL_TOTAL_ID, 
		DBHelper.COL_TBL_TOTAL_YEAR, 
		DBHelper.COL_TBL_TOTAL_GAA_TOTAL, 
		DBHelper.COL_TBL_TOTAL_NEW_APPRO_TOTAL 
	};

	@Override
	public void insert(Total total) {
		ContentValues cv = new ContentValues();

		cv.put(DBHelper.COL_TBL_TOTAL_ID, total.getId());
		cv.put(DBHelper.COL_TBL_TOTAL_YEAR, total.getYear());
		cv.put(DBHelper.COL_TBL_TOTAL_GAA_TOTAL, total.getGaa_total());
		cv.put(DBHelper.COL_TBL_TOTAL_NEW_APPRO_TOTAL, total.getNew_appro_total());

		db.insert(DBHelper.TBL_TOTAL, null, cv);
	}

	@Override
	public boolean delete(Total total) {
		String whereClause = DBHelper.COL_TBL_TOTAL_ID + "='" + total.getId() + "'";
		int result = db.delete(DBHelper.TBL_TOTAL, whereClause, null);
		return (result == 1);
	}

	@Override
	public boolean deleteAll() {
		int result = db.delete(DBHelper.TBL_TOTAL, null, null);
		return (result > 0);
	}

	@Override
	public boolean update(Total total) {
		String whereClause = DBHelper.COL_TBL_TOTAL_ID + "='" + total.getId() + "'";
		ContentValues cv = new ContentValues();

		cv.put(DBHelper.COL_ID, total.getId());
		cv.put(DBHelper.COL_TBL_TOTAL_YEAR, total.getYear());
		cv.put(DBHelper.COL_TBL_TOTAL_GAA_TOTAL, total.getGaa_total());
		cv.put(DBHelper.COL_TBL_TOTAL_NEW_APPRO_TOTAL, total.getNew_appro_total());

		int result = db.update(DBHelper.TBL_TOTAL, cv, whereClause, null);
		return (result == 1);
	}

	@Override
	public List<Total> getAll() {
		Cursor cursor = db.query(DBHelper.TBL_TOTAL, allColumns, null, null, null, null, "year DESC");
		return get(cursor);
	}

	@Override
	public List<Total> getByParameter(String params) {
		Cursor cursor = db.query(DBHelper.TBL_TOTAL, allColumns, params, null, null, null, null);
		return get(cursor);
	}

	@Override
	public Cursor getByRawQuery(String sql) {
		return db.rawQuery(sql, null);
	}
	
	private List<Total> get(Cursor cursor) {
		List<Total> totals = new ArrayList<Total>();
		if (cursor.getCount() > 0) {
			while (cursor.moveToNext()) {
				Total total = new Total();
				total.setId(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_TOTAL_ID)));
				total.setYear(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_TOTAL_YEAR)));
				total.setGaa_total(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_TOTAL_GAA_TOTAL)));
				total.setNew_appro_total(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_TOTAL_NEW_APPRO_TOTAL)));
				totals.add(total);
			}
		}
		return totals;
	}

}
