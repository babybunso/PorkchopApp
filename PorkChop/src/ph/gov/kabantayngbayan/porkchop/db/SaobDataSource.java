package ph.gov.kabantayngbayan.porkchop.db;

import java.util.ArrayList;
import java.util.List;

import ph.gov.kabantayngbayan.porkchop.models.Saob;
import ph.gov.kabantayngbayan.porkchop.models.SaobBudget;
import ph.gov.kabantayngbayan.porkchop.models.SaobBudgetContinuingAppro;
import ph.gov.kabantayngbayan.porkchop.models.SaobBudgetCurrentYearBudget;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class SaobDataSource extends DataSourceBase<Saob> {

	public SaobDataSource(Context context) {
		super(context);
	}

	private static final String[] allColumns = {
		
		DBHelper.COL_ID, 
		
		DBHelper.COL_TBL_SAOB_ID,
		DBHelper.COL_TBL_SAOB_YEAR,
		DBHelper.COL_TBL_SAOB_PERIOD,
		DBHelper.COL_TBL_SAOB_BUDGET_RECORD,
		DBHelper.COL_TBL_SAOB_BUDGET_RECORD_LABEL,
		DBHelper.COL_TBL_SAOB_BUDGET_APPROP,
		DBHelper.COL_TBL_SAOB_BUDGET_ALLOT_PS,
		DBHelper.COL_TBL_SAOB_BUDGET_ALLOT_MOOE,
		DBHelper.COL_TBL_SAOB_BUDGET_ALLOT_CO,
		DBHelper.COL_TBL_SAOB_BUDGET_ALLOT_TOTAL,
		DBHelper.COL_TBL_SAOB_BUDGET_OBLIG_PS,
		DBHelper.COL_TBL_SAOB_BUDGET_OBLIG_MOOE,
		DBHelper.COL_TBL_SAOB_BUDGET_OBLIG_CO,
		DBHelper.COL_TBL_SAOB_BUDGET_OBLIG_TOTAL,
		DBHelper.COL_TBL_SAOB_BUDGET_UOBLIG_PS,
		DBHelper.COL_TBL_SAOB_BUDGET_UOBLIG_MOOE,
		DBHelper.COL_TBL_SAOB_BUDGET_UOBLIG_CO,
		DBHelper.COL_TBL_SAOB_BUDGET_UOBLIG_TOTAL,
		DBHelper.COL_TBL_SAOB_OBLIGERATE,
		
		DBHelper.COL_TBL_SAOB_CURRENT_YEAR_BUDGET_RECORD,
		DBHelper.COL_TBL_SAOB_CURRENT_YEAR_BUDGET_RECORD_LABEL,
		DBHelper.COL_TBL_SAOB_CURRENT_YEAR_BUDGET_APPROP,
		DBHelper.COL_TBL_SAOB_CURRENT_YEAR_BUDGET_ALLOT_PS,
		DBHelper.COL_TBL_SAOB_CURRENT_YEAR_BUDGET_ALLOT_MOOE,
		DBHelper.COL_TBL_SAOB_CURRENT_YEAR_BUDGET_ALLOT_CO,
		DBHelper.COL_TBL_SAOB_CURRENT_YEAR_BUDGET_ALLOT_TOTAL,
		DBHelper.COL_TBL_SAOB_CURRENT_YEAR_BUDGET_OBLIG_PS,
		DBHelper.COL_TBL_SAOB_CURRENT_YEAR_BUDGET_OBLIG_MOOE,
		DBHelper.COL_TBL_SAOB_CURRENT_YEAR_BUDGET_OBLIG_CO,
		DBHelper.COL_TBL_SAOB_CURRENT_YEAR_BUDGET_OBLIG_TOTAL,
		DBHelper.COL_TBL_SAOB_CURRENT_YEAR_BUDGET_UOBLIG_PS,
		DBHelper.COL_TBL_SAOB_CURRENT_YEAR_BUDGET_UOBLIG_MOOE,
		DBHelper.COL_TBL_SAOB_CURRENT_YEAR_BUDGET_UOBLIG_CO,
		DBHelper.COL_TBL_SAOB_CURRENT_YEAR_BUDGET_UOBLIG_TOTAL,
		DBHelper.COL_TBL_SAOB_CURRENT_YEAR_OBLIGERATE,
		
		DBHelper.COL_TBL_SAOB_CONTINUING_APPRO_RECORD,
		DBHelper.COL_TBL_SAOB_CONTINUING_APPRO_RECORD_LABEL,
		DBHelper.COL_TBL_SAOB_CONTINUING_APPRO_APPROP,
		DBHelper.COL_TBL_SAOB_CONTINUING_APPRO_ALLOT_PS,
		DBHelper.COL_TBL_SAOB_CONTINUING_APPRO_ALLOT_MOOE,
		DBHelper.COL_TBL_SAOB_CONTINUING_APPRO_ALLOT_CO,
		DBHelper.COL_TBL_SAOB_CONTINUING_APPRO_ALLOT_TOTAL,
		DBHelper.COL_TBL_SAOB_CONTINUING_APPRO_OBLIG_PS,
		DBHelper.COL_TBL_SAOB_CONTINUING_APPRO_OBLIG_MOOE,
		DBHelper.COL_TBL_SAOB_CONTINUING_APPRO_OBLIG_CO,
		DBHelper.COL_TBL_SAOB_CONTINUING_APPRO_OBLIG_TOTAL,
		DBHelper.COL_TBL_SAOB_CONTINUING_APPRO_UOBLIG_PS,
		DBHelper.COL_TBL_SAOB_CONTINUING_APPRO_UOBLIG_MOOE,
		DBHelper.COL_TBL_SAOB_CONTINUING_APPRO_UOBLIG_CO,
		DBHelper.COL_TBL_SAOB_CONTINUING_APPRO_UOBLIG_TOTAL,
		DBHelper.COL_TBL_SAOB_CONTINUING_APPRO_OBLIGERATE
	};

	@Override
	public void insert(Saob saob) {
		
		if(null != saob.getBudget()) {
			
			ContentValues cv = new ContentValues();
			
			cv.put(DBHelper.COL_TBL_SAOB_ID, saob.getId());
			cv.put(DBHelper.COL_TBL_SAOB_YEAR, saob.getYear());
			cv.put(DBHelper.COL_TBL_SAOB_PERIOD, saob.getPeriod());
			cv.put(DBHelper.COL_TBL_SAOB_BUDGET_RECORD, saob.getBudget().getRecord());
			cv.put(DBHelper.COL_TBL_SAOB_BUDGET_RECORD_LABEL, saob.getBudget().getRecord_label());
			cv.put(DBHelper.COL_TBL_SAOB_BUDGET_APPROP, saob.getBudget().getApprop());
			cv.put(DBHelper.COL_TBL_SAOB_BUDGET_ALLOT_PS, saob.getBudget().getAllot_ps());
			cv.put(DBHelper.COL_TBL_SAOB_BUDGET_ALLOT_MOOE, saob.getBudget().getAllot_mooe());
			cv.put(DBHelper.COL_TBL_SAOB_BUDGET_ALLOT_CO, saob.getBudget().getAllot_co());
			cv.put(DBHelper.COL_TBL_SAOB_BUDGET_ALLOT_TOTAL, saob.getBudget().getAllot_tot());
			cv.put(DBHelper.COL_TBL_SAOB_BUDGET_OBLIG_PS, saob.getBudget().getOblig_ps());
			cv.put(DBHelper.COL_TBL_SAOB_BUDGET_OBLIG_MOOE, saob.getBudget().getOblig_mooe());
			cv.put(DBHelper.COL_TBL_SAOB_BUDGET_OBLIG_CO, saob.getBudget().getOblig_co());
			cv.put(DBHelper.COL_TBL_SAOB_BUDGET_OBLIG_TOTAL, saob.getBudget().getOblig_tot());
			cv.put(DBHelper.COL_TBL_SAOB_BUDGET_UOBLIG_PS, saob.getBudget().getUoblig_ps());
			cv.put(DBHelper.COL_TBL_SAOB_BUDGET_UOBLIG_MOOE, saob.getBudget().getUoblig_mooe());
			cv.put(DBHelper.COL_TBL_SAOB_BUDGET_UOBLIG_CO, saob.getBudget().getUoblig_co());
			cv.put(DBHelper.COL_TBL_SAOB_BUDGET_UOBLIG_TOTAL, saob.getBudget().getUoblig_tot());
			cv.put(DBHelper.COL_TBL_SAOB_OBLIGERATE, saob.getBudget().getObligrate());
			
			cv.put(DBHelper.COL_TBL_SAOB_CURRENT_YEAR_BUDGET_RECORD, saob.getBudget().getCurrent_year_budget().getRecord());
			cv.put(DBHelper.COL_TBL_SAOB_CURRENT_YEAR_BUDGET_RECORD_LABEL, saob.getBudget().getCurrent_year_budget().getRecord_label());
			cv.put(DBHelper.COL_TBL_SAOB_CURRENT_YEAR_BUDGET_APPROP, saob.getBudget().getCurrent_year_budget().getApprop());
			cv.put(DBHelper.COL_TBL_SAOB_CURRENT_YEAR_BUDGET_ALLOT_PS, saob.getBudget().getCurrent_year_budget().getAllot_ps());
			cv.put(DBHelper.COL_TBL_SAOB_CURRENT_YEAR_BUDGET_ALLOT_MOOE, saob.getBudget().getCurrent_year_budget().getAllot_mooe());
			cv.put(DBHelper.COL_TBL_SAOB_CURRENT_YEAR_BUDGET_ALLOT_CO, saob.getBudget().getCurrent_year_budget().getAllot_co());
			cv.put(DBHelper.COL_TBL_SAOB_CURRENT_YEAR_BUDGET_ALLOT_TOTAL, saob.getBudget().getCurrent_year_budget().getAllot_tot());
			cv.put(DBHelper.COL_TBL_SAOB_CURRENT_YEAR_BUDGET_OBLIG_PS, saob.getBudget().getCurrent_year_budget().getOblig_ps());
			cv.put(DBHelper.COL_TBL_SAOB_CURRENT_YEAR_BUDGET_OBLIG_MOOE, saob.getBudget().getCurrent_year_budget().getOblig_mooe());
			cv.put(DBHelper.COL_TBL_SAOB_CURRENT_YEAR_BUDGET_OBLIG_CO, saob.getBudget().getCurrent_year_budget().getOblig_co());
			cv.put(DBHelper.COL_TBL_SAOB_CURRENT_YEAR_BUDGET_OBLIG_TOTAL, saob.getBudget().getCurrent_year_budget().getOblig_tot());
			cv.put(DBHelper.COL_TBL_SAOB_CURRENT_YEAR_BUDGET_UOBLIG_PS, saob.getBudget().getCurrent_year_budget().getUoblig_ps());
			cv.put(DBHelper.COL_TBL_SAOB_CURRENT_YEAR_BUDGET_UOBLIG_MOOE, saob.getBudget().getCurrent_year_budget().getUoblig_mooe());
			cv.put(DBHelper.COL_TBL_SAOB_CURRENT_YEAR_BUDGET_UOBLIG_CO, saob.getBudget().getCurrent_year_budget().getUoblig_co());
			cv.put(DBHelper.COL_TBL_SAOB_CURRENT_YEAR_BUDGET_UOBLIG_TOTAL, saob.getBudget().getCurrent_year_budget().getUoblig_tot());
			cv.put(DBHelper.COL_TBL_SAOB_CURRENT_YEAR_OBLIGERATE, saob.getBudget().getCurrent_year_budget().getObligrate());
			
			cv.put(DBHelper.COL_TBL_SAOB_CONTINUING_APPRO_RECORD, saob.getBudget().getContinuing_appro().getRecord());
			cv.put(DBHelper.COL_TBL_SAOB_CONTINUING_APPRO_RECORD_LABEL, saob.getBudget().getContinuing_appro().getRecord_label());
			cv.put(DBHelper.COL_TBL_SAOB_CONTINUING_APPRO_APPROP, saob.getBudget().getContinuing_appro().getApprop());
			cv.put(DBHelper.COL_TBL_SAOB_CONTINUING_APPRO_ALLOT_PS, saob.getBudget().getContinuing_appro().getAllot_ps());
			cv.put(DBHelper.COL_TBL_SAOB_CONTINUING_APPRO_ALLOT_MOOE, saob.getBudget().getContinuing_appro().getAllot_mooe());
			cv.put(DBHelper.COL_TBL_SAOB_CONTINUING_APPRO_ALLOT_CO, saob.getBudget().getContinuing_appro().getAllot_co());
			cv.put(DBHelper.COL_TBL_SAOB_CONTINUING_APPRO_ALLOT_TOTAL, saob.getBudget().getContinuing_appro().getAllot_tot());
			cv.put(DBHelper.COL_TBL_SAOB_CONTINUING_APPRO_OBLIG_PS, saob.getBudget().getContinuing_appro().getOblig_ps());
			cv.put(DBHelper.COL_TBL_SAOB_CONTINUING_APPRO_OBLIG_MOOE, saob.getBudget().getContinuing_appro().getOblig_mooe());
			cv.put(DBHelper.COL_TBL_SAOB_CONTINUING_APPRO_OBLIG_CO, saob.getBudget().getContinuing_appro().getOblig_co());
			cv.put(DBHelper.COL_TBL_SAOB_CONTINUING_APPRO_OBLIG_TOTAL, saob.getBudget().getContinuing_appro().getOblig_tot());
			cv.put(DBHelper.COL_TBL_SAOB_CONTINUING_APPRO_UOBLIG_PS, saob.getBudget().getContinuing_appro().getUoblig_ps());
			cv.put(DBHelper.COL_TBL_SAOB_CONTINUING_APPRO_UOBLIG_MOOE, saob.getBudget().getContinuing_appro().getUoblig_mooe());
			cv.put(DBHelper.COL_TBL_SAOB_CONTINUING_APPRO_UOBLIG_CO, saob.getBudget().getContinuing_appro().getUoblig_co());
			cv.put(DBHelper.COL_TBL_SAOB_CONTINUING_APPRO_UOBLIG_TOTAL, saob.getBudget().getContinuing_appro().getUoblig_tot());
			cv.put(DBHelper.COL_TBL_SAOB_CURRENT_YEAR_OBLIGERATE, saob.getBudget().getContinuing_appro().getObligrate());
			
			db.insert(DBHelper.TBL_SAOB, null, cv);
		}

	}

	@Override
	public boolean delete(Saob saob) {
		String whereClause = DBHelper.COL_TBL_SAOB_ID + "='" + saob.getId() + "'";
		int result = db.delete(DBHelper.TBL_SAOB, whereClause, null);
		return (result == 1);
	}

	@Override
	public boolean deleteAll() {
		int result = db.delete(DBHelper.TBL_SAOB, null, null);
		return (result > 0);
	}

	@Override
	public boolean update(Saob saob) {
		String whereClause = DBHelper.COL_TBL_SAOB_ID + "='" + saob.getId() + "'";
		ContentValues cv = new ContentValues();

		// cv.put(DBHelper.COL_TBL_SAOB_ID, saob.getId());
		cv.put(DBHelper.COL_TBL_SAOB_YEAR, saob.getYear());
		cv.put(DBHelper.COL_TBL_SAOB_PERIOD, saob.getPeriod());
		cv.put(DBHelper.COL_TBL_SAOB_BUDGET_RECORD, saob.getBudget().getRecord());
		cv.put(DBHelper.COL_TBL_SAOB_BUDGET_RECORD_LABEL, saob.getBudget().getRecord_label());
		cv.put(DBHelper.COL_TBL_SAOB_BUDGET_APPROP, saob.getBudget().getApprop());
		cv.put(DBHelper.COL_TBL_SAOB_BUDGET_ALLOT_PS, saob.getBudget().getAllot_ps());
		cv.put(DBHelper.COL_TBL_SAOB_BUDGET_ALLOT_MOOE, saob.getBudget().getAllot_mooe());
		cv.put(DBHelper.COL_TBL_SAOB_BUDGET_ALLOT_CO, saob.getBudget().getAllot_co());
		cv.put(DBHelper.COL_TBL_SAOB_BUDGET_ALLOT_TOTAL, saob.getBudget().getAllot_tot());
		cv.put(DBHelper.COL_TBL_SAOB_BUDGET_OBLIG_PS, saob.getBudget().getOblig_ps());
		cv.put(DBHelper.COL_TBL_SAOB_BUDGET_OBLIG_MOOE, saob.getBudget().getOblig_mooe());
		cv.put(DBHelper.COL_TBL_SAOB_BUDGET_OBLIG_CO, saob.getBudget().getOblig_co());
		cv.put(DBHelper.COL_TBL_SAOB_BUDGET_OBLIG_TOTAL, saob.getBudget().getOblig_tot());
		cv.put(DBHelper.COL_TBL_SAOB_BUDGET_UOBLIG_PS, saob.getBudget().getUoblig_ps());
		cv.put(DBHelper.COL_TBL_SAOB_BUDGET_UOBLIG_MOOE, saob.getBudget().getUoblig_mooe());
		cv.put(DBHelper.COL_TBL_SAOB_BUDGET_UOBLIG_CO, saob.getBudget().getUoblig_co());
		cv.put(DBHelper.COL_TBL_SAOB_BUDGET_UOBLIG_TOTAL, saob.getBudget().getUoblig_tot());
		cv.put(DBHelper.COL_TBL_SAOB_OBLIGERATE, saob.getBudget().getObligrate());
		
		cv.put(DBHelper.COL_TBL_SAOB_CURRENT_YEAR_BUDGET_RECORD, saob.getBudget().getCurrent_year_budget().getRecord());
		cv.put(DBHelper.COL_TBL_SAOB_CURRENT_YEAR_BUDGET_RECORD_LABEL, saob.getBudget().getCurrent_year_budget().getRecord_label());
		cv.put(DBHelper.COL_TBL_SAOB_CURRENT_YEAR_BUDGET_APPROP, saob.getBudget().getCurrent_year_budget().getApprop());
		cv.put(DBHelper.COL_TBL_SAOB_CURRENT_YEAR_BUDGET_ALLOT_PS, saob.getBudget().getCurrent_year_budget().getAllot_ps());
		cv.put(DBHelper.COL_TBL_SAOB_CURRENT_YEAR_BUDGET_ALLOT_MOOE, saob.getBudget().getCurrent_year_budget().getAllot_mooe());
		cv.put(DBHelper.COL_TBL_SAOB_CURRENT_YEAR_BUDGET_ALLOT_CO, saob.getBudget().getCurrent_year_budget().getAllot_co());
		cv.put(DBHelper.COL_TBL_SAOB_CURRENT_YEAR_BUDGET_ALLOT_TOTAL, saob.getBudget().getCurrent_year_budget().getAllot_tot());
		cv.put(DBHelper.COL_TBL_SAOB_CURRENT_YEAR_BUDGET_OBLIG_PS, saob.getBudget().getCurrent_year_budget().getOblig_ps());
		cv.put(DBHelper.COL_TBL_SAOB_CURRENT_YEAR_BUDGET_OBLIG_MOOE, saob.getBudget().getCurrent_year_budget().getOblig_mooe());
		cv.put(DBHelper.COL_TBL_SAOB_CURRENT_YEAR_BUDGET_OBLIG_CO, saob.getBudget().getCurrent_year_budget().getOblig_co());
		cv.put(DBHelper.COL_TBL_SAOB_CURRENT_YEAR_BUDGET_OBLIG_TOTAL, saob.getBudget().getCurrent_year_budget().getOblig_tot());
		cv.put(DBHelper.COL_TBL_SAOB_CURRENT_YEAR_BUDGET_UOBLIG_PS, saob.getBudget().getCurrent_year_budget().getUoblig_ps());
		cv.put(DBHelper.COL_TBL_SAOB_CURRENT_YEAR_BUDGET_UOBLIG_MOOE, saob.getBudget().getCurrent_year_budget().getUoblig_mooe());
		cv.put(DBHelper.COL_TBL_SAOB_CURRENT_YEAR_BUDGET_UOBLIG_CO, saob.getBudget().getCurrent_year_budget().getUoblig_co());
		cv.put(DBHelper.COL_TBL_SAOB_CURRENT_YEAR_BUDGET_UOBLIG_TOTAL, saob.getBudget().getCurrent_year_budget().getUoblig_tot());
		cv.put(DBHelper.COL_TBL_SAOB_CURRENT_YEAR_OBLIGERATE, saob.getBudget().getCurrent_year_budget().getObligrate());
		
		cv.put(DBHelper.COL_TBL_SAOB_CONTINUING_APPRO_RECORD, saob.getBudget().getContinuing_appro().getRecord());
		cv.put(DBHelper.COL_TBL_SAOB_CONTINUING_APPRO_RECORD_LABEL, saob.getBudget().getContinuing_appro().getRecord_label());
		cv.put(DBHelper.COL_TBL_SAOB_CONTINUING_APPRO_APPROP, saob.getBudget().getContinuing_appro().getApprop());
		cv.put(DBHelper.COL_TBL_SAOB_CONTINUING_APPRO_ALLOT_PS, saob.getBudget().getContinuing_appro().getAllot_ps());
		cv.put(DBHelper.COL_TBL_SAOB_CONTINUING_APPRO_ALLOT_MOOE, saob.getBudget().getContinuing_appro().getAllot_mooe());
		cv.put(DBHelper.COL_TBL_SAOB_CONTINUING_APPRO_ALLOT_CO, saob.getBudget().getContinuing_appro().getAllot_co());
		cv.put(DBHelper.COL_TBL_SAOB_CONTINUING_APPRO_ALLOT_TOTAL, saob.getBudget().getContinuing_appro().getAllot_tot());
		cv.put(DBHelper.COL_TBL_SAOB_CONTINUING_APPRO_OBLIG_PS, saob.getBudget().getContinuing_appro().getOblig_ps());
		cv.put(DBHelper.COL_TBL_SAOB_CONTINUING_APPRO_OBLIG_MOOE, saob.getBudget().getContinuing_appro().getOblig_mooe());
		cv.put(DBHelper.COL_TBL_SAOB_CONTINUING_APPRO_OBLIG_CO, saob.getBudget().getContinuing_appro().getOblig_co());
		cv.put(DBHelper.COL_TBL_SAOB_CONTINUING_APPRO_OBLIG_TOTAL, saob.getBudget().getContinuing_appro().getOblig_tot());
		cv.put(DBHelper.COL_TBL_SAOB_CONTINUING_APPRO_UOBLIG_PS, saob.getBudget().getContinuing_appro().getUoblig_ps());
		cv.put(DBHelper.COL_TBL_SAOB_CONTINUING_APPRO_UOBLIG_MOOE, saob.getBudget().getContinuing_appro().getUoblig_mooe());
		cv.put(DBHelper.COL_TBL_SAOB_CONTINUING_APPRO_UOBLIG_CO, saob.getBudget().getContinuing_appro().getUoblig_co());
		cv.put(DBHelper.COL_TBL_SAOB_CONTINUING_APPRO_UOBLIG_TOTAL, saob.getBudget().getContinuing_appro().getUoblig_tot());
		cv.put(DBHelper.COL_TBL_SAOB_CURRENT_YEAR_OBLIGERATE, saob.getBudget().getContinuing_appro().getObligrate());

		int result = db.update(DBHelper.TBL_SAOB, cv, whereClause, null);
		return (result == 1);
	}

	@Override
	public List<Saob> getAll() {
		Cursor cursor = db.query(DBHelper.TBL_SAOB, allColumns, null, null, null, null, null);
		return get(cursor);
	}

	@Override
	public List<Saob> getByParameter(String params) {
		Cursor cursor = db.query(DBHelper.TBL_SAOB, allColumns, params, null, null, null, null);
		return get(cursor);
	}
	
	@Override
	public Cursor getByRawQuery(String sql) {
		return db.rawQuery(sql, null);
	}
	
	private List<Saob> get(Cursor cursor) {
		List<Saob> saobs = new ArrayList<Saob>();
		
		if (cursor.getCount() > 0) {
			while (cursor.moveToNext()) {
				
				Saob saob = new Saob();
				SaobBudget budget = new SaobBudget();
				SaobBudgetCurrentYearBudget currentYearBudget = new SaobBudgetCurrentYearBudget();
				SaobBudgetContinuingAppro continuingAppro = new SaobBudgetContinuingAppro();

				saob.setId(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_SAOB_ID)));
				saob.setYear(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_SAOB_YEAR)));
				saob.setPeriod(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_SAOB_PERIOD)));
				budget.setRecord(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_SAOB_BUDGET_RECORD)));
				budget.setRecord_label(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_SAOB_BUDGET_RECORD_LABEL)));
				budget.setApprop(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_SAOB_BUDGET_APPROP)));
				budget.setAllot_ps(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_SAOB_BUDGET_ALLOT_PS)));
				budget.setAllot_mooe(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_SAOB_BUDGET_ALLOT_MOOE)));
				budget.setAllot_co(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_SAOB_BUDGET_ALLOT_CO)));
				budget.setAllot_tot(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_SAOB_BUDGET_ALLOT_TOTAL)));
				budget.setOblig_ps(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_SAOB_BUDGET_OBLIG_PS)));
				budget.setOblig_mooe(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_SAOB_BUDGET_OBLIG_MOOE)));
				budget.setOblig_co(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_SAOB_BUDGET_OBLIG_CO)));
				budget.setOblig_tot(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_SAOB_BUDGET_OBLIG_TOTAL)));
				budget.setUoblig_ps(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_SAOB_BUDGET_UOBLIG_PS)));
				budget.setUoblig_mooe(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_SAOB_BUDGET_UOBLIG_MOOE)));
				budget.setUoblig_co(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_SAOB_BUDGET_UOBLIG_CO)));
				budget.setUoblig_tot(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_SAOB_BUDGET_UOBLIG_TOTAL)));
				budget.setObligrate(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_SAOB_OBLIGERATE)));
				
				currentYearBudget.setRecord(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_SAOB_CURRENT_YEAR_BUDGET_RECORD)));
				currentYearBudget.setRecord_label(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_SAOB_CURRENT_YEAR_BUDGET_RECORD_LABEL)));
				currentYearBudget.setApprop(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_SAOB_CURRENT_YEAR_BUDGET_APPROP)));
				currentYearBudget.setAllot_ps(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_SAOB_CURRENT_YEAR_BUDGET_ALLOT_PS)));
				currentYearBudget.setAllot_mooe(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_SAOB_CURRENT_YEAR_BUDGET_ALLOT_MOOE)));
				currentYearBudget.setAllot_co(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_SAOB_CURRENT_YEAR_BUDGET_ALLOT_CO)));
				currentYearBudget.setAllot_tot(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_SAOB_CURRENT_YEAR_BUDGET_ALLOT_TOTAL)));
				currentYearBudget.setOblig_ps(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_SAOB_CURRENT_YEAR_BUDGET_OBLIG_PS)));
				currentYearBudget.setOblig_mooe(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_SAOB_CURRENT_YEAR_BUDGET_OBLIG_MOOE)));
				currentYearBudget.setOblig_co(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_SAOB_CURRENT_YEAR_BUDGET_OBLIG_CO)));
				currentYearBudget.setOblig_tot(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_SAOB_CURRENT_YEAR_BUDGET_OBLIG_TOTAL)));
				currentYearBudget.setUoblig_ps(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_SAOB_CURRENT_YEAR_BUDGET_UOBLIG_PS)));
				currentYearBudget.setUoblig_mooe(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_SAOB_CURRENT_YEAR_BUDGET_UOBLIG_MOOE)));
				currentYearBudget.setUoblig_co(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_SAOB_CURRENT_YEAR_BUDGET_UOBLIG_CO)));
				currentYearBudget.setUoblig_tot(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_SAOB_CURRENT_YEAR_BUDGET_UOBLIG_TOTAL)));
				currentYearBudget.setObligrate(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_SAOB_CURRENT_YEAR_OBLIGERATE)));
				
				continuingAppro.setRecord(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_SAOB_CONTINUING_APPRO_RECORD)));
				continuingAppro.setRecord_label(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_SAOB_CONTINUING_APPRO_RECORD_LABEL)));
				continuingAppro.setApprop(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_SAOB_CONTINUING_APPRO_APPROP)));
				continuingAppro.setAllot_ps(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_SAOB_CONTINUING_APPRO_ALLOT_PS)));
				continuingAppro.setAllot_mooe(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_SAOB_CONTINUING_APPRO_ALLOT_MOOE)));
				continuingAppro.setAllot_co(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_SAOB_CONTINUING_APPRO_ALLOT_CO)));
				continuingAppro.setAllot_tot(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_SAOB_CONTINUING_APPRO_ALLOT_TOTAL)));
				continuingAppro.setOblig_ps(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_SAOB_CONTINUING_APPRO_OBLIG_PS)));
				continuingAppro.setOblig_mooe(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_SAOB_CONTINUING_APPRO_OBLIG_MOOE)));
				continuingAppro.setOblig_co(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_SAOB_CONTINUING_APPRO_OBLIG_CO)));
				continuingAppro.setOblig_tot(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_SAOB_CONTINUING_APPRO_OBLIG_TOTAL)));
				continuingAppro.setUoblig_ps(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_SAOB_CONTINUING_APPRO_UOBLIG_PS)));
				continuingAppro.setUoblig_mooe(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_SAOB_CONTINUING_APPRO_UOBLIG_MOOE)));
				continuingAppro.setUoblig_co(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_SAOB_CONTINUING_APPRO_UOBLIG_CO)));
				continuingAppro.setUoblig_tot(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_SAOB_CONTINUING_APPRO_UOBLIG_TOTAL)));
				continuingAppro.setObligrate(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_SAOB_CURRENT_YEAR_OBLIGERATE)));

				budget.setCurrent_year_budget(currentYearBudget);
				budget.setContinuing_appro(continuingAppro);
				
				saob.setBudget(budget);
				saobs.add(saob);
			}
		}
		
		return saobs;
	}

}
