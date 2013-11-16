package ph.gov.kabantayngbayan.porkchop.db;

import java.util.ArrayList;
import java.util.List;

import ph.gov.kabantayngbayan.porkchop.models.BudgetAutoAppro;
import ph.gov.kabantayngbayan.porkchop.models.ProgramBudget;
import ph.gov.kabantayngbayan.porkchop.models.Programs;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

public class BudgetAutoApproDataSource extends DataSourceBase<BudgetAutoAppro> { /* TODO - FOR FULL RE-STRUCTURE */

	public BudgetAutoApproDataSource(Context context) {
		super(context);
	}

	private static final String[] allColumns = {
		DBHelper.COL_ID, 
		DBHelper.COL_TBL_BUDGET_APPRO_ID, 
		DBHelper.COL_TBL_BUDGET_APPRO_YEAR,
		DBHelper.COL_TBL_BUDGET_APPRO_OWNER_CODE,
		DBHelper.COL_TBL_BUDGET_APPRO_DEPARTMENT_CODE,
		DBHelper.COL_TBL_BUDGET_APPRO_AGENCY_TYPE,
		DBHelper.COL_TBL_BUDGET_APPRO_OWNER_DESC,
		DBHelper.COL_TBL_BUDGET_APPRO_DEPARTMENT_DESC,
		DBHelper.COL_TBL_BUDGET_APPRO_PROGRAM_DESC,
		DBHelper.COL_TBL_BUDGET_APPRO_PROGRAM_AREA_CODE,
		DBHelper.COL_TBL_BUDGET_APPRO_PROGRAM_AREA_DESC,
		DBHelper.COL_TBL_BUDGET_APPRO_PROGRAM_BUDGET_PS,
		DBHelper.COL_TBL_BUDGET_APPRO_PROGRAM_BUDGET_MOOE,
		DBHelper.COL_TBL_BUDGET_APPRO_PROGRAM_BUDGET_CO
	};

	@Override
	public void insert(BudgetAutoAppro budget) {
		ContentValues cv = new ContentValues();
		
		List<Programs> programs = budget.getPrograms();

		for(Programs p : programs) {
			
			cv.put(DBHelper.COL_TBL_TOTAL_ID, budget.getId());
			cv.put(DBHelper.COL_TBL_BUDGET_APPRO_YEAR, budget.getYear());
			cv.put(DBHelper.COL_TBL_BUDGET_APPRO_OWNER_CODE, budget.getOwner_code());
			cv.put(DBHelper.COL_TBL_BUDGET_APPRO_DEPARTMENT_CODE, budget.getDepartment_code());
			cv.put(DBHelper.COL_TBL_BUDGET_APPRO_AGENCY_TYPE, budget.getAgency_type());
			cv.put(DBHelper.COL_TBL_BUDGET_APPRO_OWNER_DESC, budget.getOwner_desc());
			cv.put(DBHelper.COL_TBL_BUDGET_APPRO_DEPARTMENT_DESC, budget.getDepartment_desc());
			
			cv.put(DBHelper.COL_TBL_BUDGET_APPRO_PROGRAM_DESC, p.getProgram_desc());
			cv.put(DBHelper.COL_TBL_BUDGET_APPRO_PROGRAM_AREA_CODE, p.getArea_code());
			cv.put(DBHelper.COL_TBL_BUDGET_APPRO_PROGRAM_AREA_DESC, p.getArea_desc());
			cv.put(DBHelper.COL_TBL_BUDGET_APPRO_PROGRAM_BUDGET_PS, p.getBudget().getPs());
			cv.put(DBHelper.COL_TBL_BUDGET_APPRO_PROGRAM_BUDGET_MOOE, p.getBudget().getMooe());
			cv.put(DBHelper.COL_TBL_BUDGET_APPRO_PROGRAM_BUDGET_CO, p.getBudget().getCo());
			
			db.insert(DBHelper.TBL_BUDGET_AUTO_APPRO, null, cv);			
		}
	}

	@Override
	public boolean delete(BudgetAutoAppro budget) {
		String whereClause = DBHelper.COL_TBL_BUDGET_APPRO_ID + "='" + budget.getId() + "'";
		int result = db.delete(DBHelper.TBL_BUDGET_AUTO_APPRO, whereClause, null);
		return (result == 1);
	}

	@Override
	public boolean deleteAll() {
		int result = db.delete(DBHelper.TBL_BUDGET_AUTO_APPRO, null, null);
		return (result > 0);
	}

	@Override
	public boolean update(BudgetAutoAppro budget) {
		return false; // NOT IMPLEMENTED
	}

	@Override
	public List<BudgetAutoAppro> getAll() {
		Cursor cursor = db.query(DBHelper.TBL_BUDGET_AUTO_APPRO, allColumns, null, null, null, null, null);
		return get(cursor);
	}


	@Override
	public List<BudgetAutoAppro> getByParameter(String params) {
		Cursor cursor = db.query(DBHelper.TBL_BUDGET_AUTO_APPRO, allColumns, params, null, null, null, null);
		return get(cursor);
	}

	@Override
	public Cursor getByRawQuery(String sql) {
		return db.rawQuery(sql, null);
	}

	private List<BudgetAutoAppro> get(Cursor cursor) {
		
		List<BudgetAutoAppro> budgets = new ArrayList<BudgetAutoAppro>();
		
		if (cursor.getCount() > 0) {
			while (cursor.moveToNext()) {
				
				BudgetAutoAppro budget = new BudgetAutoAppro();				

				budget.setId(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_BUDGET_APPRO_ID)));
				budget.setYear(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_BUDGET_APPRO_YEAR)));
				budget.setOwner_code(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_BUDGET_APPRO_OWNER_CODE)));
				budget.setDepartment_code(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_BUDGET_APPRO_DEPARTMENT_CODE)));
				budget.setAgency_type(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_BUDGET_APPRO_AGENCY_TYPE)));
				budget.setOwner_desc(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_BUDGET_APPRO_OWNER_DESC)));
				budget.setDepartment_desc(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_BUDGET_APPRO_DEPARTMENT_DESC)));

				budgets.add(budget);
			}
			
			
			for(int i=0; i<budgets.size(); i++) {				

				List<Programs> programs =  new ArrayList<Programs>();
				cursor.moveToFirst();
				while(cursor.moveToNext()) {
					
					String bId = cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_BUDGET_APPRO_ID));
					if(bId.equals(budgets.get(i).getId())) {
						
						Programs program = new Programs();
						program.setArea_code(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_BUDGET_APPRO_PROGRAM_AREA_CODE)));
						program.setProgram_desc(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_BUDGET_APPRO_PROGRAM_DESC)));
						program.setArea_desc(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_BUDGET_APPRO_PROGRAM_AREA_DESC)));
						
						ProgramBudget pBudget = new ProgramBudget();
						pBudget.setPs(cursor.getDouble(cursor.getColumnIndex(DBHelper.COL_TBL_BUDGET_APPRO_PROGRAM_BUDGET_PS)));
						pBudget.setMooe(cursor.getDouble(cursor.getColumnIndex(DBHelper.COL_TBL_BUDGET_APPRO_PROGRAM_BUDGET_MOOE)));
						pBudget.setCo(cursor.getDouble(cursor.getColumnIndex(DBHelper.COL_TBL_BUDGET_APPRO_PROGRAM_BUDGET_CO)));
						
						program.setBudget(pBudget);
						programs.add(program);
						
					}
				}
				//Log.d("RODEL", "programs count: " + programs.size());
				budgets.get(i).setPrograms(programs);
			}
			
		}
		Log.d("RODEL", "BUDGETS_AUTO_APPRO COUNT: " + budgets.size());
		return budgets;
	}
}
