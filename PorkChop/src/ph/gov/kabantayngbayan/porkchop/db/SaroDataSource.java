package ph.gov.kabantayngbayan.porkchop.db;

import java.util.ArrayList;
import java.util.List;

import ph.gov.kabantayngbayan.porkchop.models.Saro;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class SaroDataSource extends DataSourceBase<Saro> {

	public SaroDataSource(Context context) {
		super(context);
	}

	private static final String[] allColumns = {
		DBHelper.COL_ID,
		DBHelper.COL_TBL_SARO_ID, 
		DBHelper.COL_TBL_SARO_YEAR, 
		DBHelper.COL_TBL_SARO_DEPARTMENT_CODE,
		DBHelper.COL_TBL_SARO_AGENCY_CODE, 
		DBHelper.COL_TBL_SARO_OPERATING_UNIT, 
		DBHelper.COL_TBL_SARO_SPF_CODE,
		DBHelper.COL_TBL_SARO_FUND_CODE, 
		DBHelper.COL_TBL_SARO_DESCRIPTION,
		DBHelper.COL_TBL_SARO_REGION,
		DBHelper.COL_TBL_SARO_APPRO_SRC,
		DBHelper.COL_TBL_SARO_PROGRAM_DESCRIPTION,
		DBHelper.COL_TBL_SARO_PROGRAM_CODE,
		DBHelper.COL_TBL_SARO_PURPOSE,
		DBHelper.COL_TBL_SARO_OBJECT_CODE,
		DBHelper.COL_TBL_SARO_NO,
		DBHelper.COL_TBL_SARO_BARCODE_NO,
		DBHelper.COL_TBL_SARO_ISSUE_DATE,
		DBHelper.COL_TBL_SARO_AMOUNT,
		DBHelper.COL_TBL_SARO_OBJECT_DESCRIPTION
	};

	@Override
	public void insert(Saro saro) {

		ContentValues cv = new ContentValues();
				
		cv.put(DBHelper.COL_TBL_SARO_ID, saro.getId());
		cv.put(DBHelper.COL_TBL_SARO_YEAR, saro.getYear());
		cv.put(DBHelper.COL_TBL_SARO_DEPARTMENT_CODE, saro.getDepartment_code());
		cv.put(DBHelper.COL_TBL_SARO_AGENCY_CODE, saro.getAgency_code());
		cv.put(DBHelper.COL_TBL_SARO_OPERATING_UNIT, saro.getOperating_unit());
		cv.put(DBHelper.COL_TBL_SARO_SPF_CODE, saro.getSpf_code());
		cv.put(DBHelper.COL_TBL_SARO_FUND_CODE, saro.getFund_code());
		cv.put(DBHelper.COL_TBL_SARO_DESCRIPTION, saro.getDescription());
		cv.put(DBHelper.COL_TBL_SARO_REGION, saro.getRegion());
		cv.put(DBHelper.COL_TBL_SARO_APPRO_SRC, saro.getAppro_src());
		cv.put(DBHelper.COL_TBL_SARO_PROGRAM_DESCRIPTION, saro.getProgram_description());
		cv.put(DBHelper.COL_TBL_SARO_PROGRAM_CODE, saro.getProgram_code());
		cv.put(DBHelper.COL_TBL_SARO_PURPOSE, saro.getPurpose());
		cv.put(DBHelper.COL_TBL_SARO_OBJECT_CODE, saro.getObject_code());
		cv.put(DBHelper.COL_TBL_SARO_NO, saro.getSaro_no());
		cv.put(DBHelper.COL_TBL_SARO_BARCODE_NO, saro.getBarcode_no());
		cv.put(DBHelper.COL_TBL_SARO_ISSUE_DATE, saro.getIssue_date());
		cv.put(DBHelper.COL_TBL_SARO_AMOUNT, saro.getAmount());
		cv.put(DBHelper.COL_TBL_SARO_OBJECT_DESCRIPTION, saro.getObject_description());

		db.insert(DBHelper.TBL_SARO, null, cv);
	}

	@Override
	public boolean delete(Saro saro) {
		String whereClause = DBHelper.COL_TBL_SARO_ID + "='" + saro.getId() + "'";
		int result = db.delete(DBHelper.TBL_SARO, whereClause, null);
		return (result == 1);
	}

	@Override
	public boolean deleteAll() {
		int result = db.delete(DBHelper.TBL_SARO, null, null);
		return (result > 0);
	}

	@Override
	public boolean update(Saro saro) {
		String whereClause = DBHelper.COL_TBL_TOTAL_ID + "='" + saro.getId() + "'";
		ContentValues cv = new ContentValues();

		cv.put(DBHelper.COL_TBL_SARO_ID, saro.getId());
		cv.put(DBHelper.COL_TBL_SARO_YEAR, saro.getYear());
		cv.put(DBHelper.COL_TBL_SARO_DEPARTMENT_CODE, saro.getDepartment_code());
		cv.put(DBHelper.COL_TBL_SARO_AGENCY_CODE, saro.getAgency_code());
		cv.put(DBHelper.COL_TBL_SARO_OPERATING_UNIT, saro.getOperating_unit());
		cv.put(DBHelper.COL_TBL_SARO_SPF_CODE, saro.getSpf_code());
		cv.put(DBHelper.COL_TBL_SARO_FUND_CODE, saro.getFund_code());
		cv.put(DBHelper.COL_TBL_SARO_DESCRIPTION, saro.getDescription());
		cv.put(DBHelper.COL_TBL_SARO_REGION, saro.getRegion());
		cv.put(DBHelper.COL_TBL_SARO_APPRO_SRC, saro.getAppro_src());
		cv.put(DBHelper.COL_TBL_SARO_PROGRAM_DESCRIPTION, saro.getProgram_description());
		cv.put(DBHelper.COL_TBL_SARO_PROGRAM_CODE, saro.getProgram_code());
		cv.put(DBHelper.COL_TBL_SARO_PURPOSE, saro.getPurpose());
		cv.put(DBHelper.COL_TBL_SARO_OBJECT_CODE, saro.getObject_code());
		cv.put(DBHelper.COL_TBL_SARO_NO, saro.getSaro_no());
		cv.put(DBHelper.COL_TBL_SARO_BARCODE_NO, saro.getBarcode_no());
		cv.put(DBHelper.COL_TBL_SARO_ISSUE_DATE, saro.getIssue_date());
		cv.put(DBHelper.COL_TBL_SARO_AMOUNT, saro.getAmount());
		cv.put(DBHelper.COL_TBL_SARO_OBJECT_DESCRIPTION, saro.getObject_description());

		int result = db.update(DBHelper.TBL_SARO, cv, whereClause, null);
		return (result == 1);
	}

	@Override
	public List<Saro> getAll() {
		Cursor cursor = db.query(DBHelper.TBL_SARO, allColumns, null, null, null, null, null);
		return get(cursor);
	}

	@Override
	public List<Saro> getByParameter(String params) {
		Cursor cursor = db.query(DBHelper.TBL_SARO, allColumns, params, null, null, null, null);
		return get(cursor);
	}
	
	@Override
	public Cursor getByRawQuery(String sql) {
		return db.rawQuery(sql, null);
	}
	
	private List<Saro> get(Cursor cursor) {
		List<Saro> saros = new ArrayList<Saro>();
		
		if (cursor.getCount() > 0) {
			while (cursor.moveToNext()) {
				Saro saro = new Saro();
				saro.setId(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_SARO_ID)));
				saro.setYear(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_SARO_YEAR)));
				saro.setDepartment_code(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_SARO_DEPARTMENT_CODE)));
				saro.setAgency_code(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_SARO_AGENCY_CODE)));
				saro.setOperating_unit(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_SARO_OPERATING_UNIT)));
				saro.setSpf_code(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_SARO_SPF_CODE)));
				saro.setFund_code(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_SARO_FUND_CODE)));
				saro.setDescription(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_SARO_DESCRIPTION)));
				saro.setRegion(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_SARO_REGION)));
				saro.setAppro_src(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_SARO_APPRO_SRC)));
				saro.setProgram_description(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_SARO_PROGRAM_DESCRIPTION)));
				saro.setProgram_code(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_SARO_PROGRAM_CODE)));
				saro.setPurpose(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_SARO_PURPOSE)));
				saro.setObject_code(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_SARO_OBJECT_CODE)));
				saro.setSaro_no(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_SARO_NO)));
				saro.setBarcode_no(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_SARO_BARCODE_NO)));
				saro.setIssue_date(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_SARO_ISSUE_DATE)));
				saro.setAmount(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_SARO_AMOUNT)));
				saro.setObject_description(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_SARO_OBJECT_DESCRIPTION)));
				
				saros.add(saro);
			}
		}

		return saros;
	}

}
