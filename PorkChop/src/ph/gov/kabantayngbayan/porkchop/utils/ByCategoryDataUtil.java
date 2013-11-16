package ph.gov.kabantayngbayan.porkchop.utils;

import java.util.ArrayList;
import java.util.List;
import ph.gov.kabantayngbayan.porkchop.db.DBHelper;
import ph.gov.kabantayngbayan.porkchop.db.SaobDataSource;
import ph.gov.kabantayngbayan.porkchop.db.SaroDataSource;
import ph.gov.kabantayngbayan.porkchop.db.SectorDatasource;
import ph.gov.kabantayngbayan.porkchop.models.Particular;
import ph.gov.kabantayngbayan.porkchop.models.PieChartModel;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

public class ByCategoryDataUtil {

	private static final String TAG = ByCategoryDataUtil.class.getSimpleName();
	private Context context;
	
	
	public ByCategoryDataUtil(Context context) {
		this.context = context;
		
	}
	public List<PieChartModel> getExpenseClass(String year) {
		
		SaobDataSource saobDataSource = new SaobDataSource(context);
		saobDataSource.open();
		Cursor cursor = saobDataSource.getByRawQuery("SELECT " 
				+ DBHelper.COL_TBL_SAOB_BUDGET_ALLOT_CO + ", "
				+ DBHelper.COL_TBL_SAOB_BUDGET_ALLOT_MOOE + ", "
				+ DBHelper.COL_TBL_SAOB_BUDGET_ALLOT_PS + " FROM "
				+ DBHelper.TBL_SAOB
				+ " WHERE year='" + year + "'" );
		
		List<PieChartModel> expenseClass = new ArrayList<PieChartModel>();
		
		double allotCO = 0;
		double allotMOOE = 0;
		double allotPS = 0; 
		
		if (cursor.getCount() > 0) {
			while (cursor.moveToNext()) {
				
				String tmpCO = cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_SAOB_BUDGET_ALLOT_CO)).trim();
				allotCO += Double.parseDouble((tmpCO.equals("-")) ?  "0" : tmpCO);
				
				String tmpMOOE = cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_SAOB_BUDGET_ALLOT_MOOE)).trim();
				allotMOOE += Double.parseDouble((tmpMOOE.equals("-")) ?  "0" : tmpMOOE);
				
				String tmpPS = cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_SAOB_BUDGET_ALLOT_PS)).trim();
				allotPS += Double.parseDouble((tmpPS.equals("-")) ?  "0" : tmpPS);
			}
		}
		
		saobDataSource.close();	
		
		expenseClass.add(new PieChartModel("Capital Outlays", (allotCO>0) ? allotCO:-1));
		expenseClass.add(new PieChartModel("Maintenance & Other Operating Expences", (allotMOOE>0) ? allotMOOE:-1));
		expenseClass.add(new PieChartModel("Personal Services", (allotPS>0) ? allotPS:-1));
		
		Log.d(TAG, "TOTAL_ALLOT_CO: " + allotCO);
		Log.d(TAG, "TOTAL_ALLOT_MOOE: " + allotMOOE);
		Log.d(TAG, "TOTAL_ALLOT_PS: " + allotPS);

		return expenseClass;
	}
	
	public List<PieChartModel> getRecipientUnits(String year) {
		
		SaroDataSource saroDataSource = new SaroDataSource(context);
		saroDataSource.open();
		Cursor cursor = saroDataSource.getByRawQuery("SELECT DISTINCT " + DBHelper.COL_TBL_SARO_DEPARTMENT_CODE + " FROM " 
				+ DBHelper.TBL_SARO + " WHERE " + DBHelper.COL_TBL_SARO_DEPARTMENT_CODE + "!='' ORDER BY " 
				+ DBHelper.COL_TBL_SARO_DEPARTMENT_CODE);
		
		List<String> recipients = new ArrayList<String>();
		
		if (cursor.getCount() > 0) {
			while (cursor.moveToNext()) {
				recipients.add(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_SARO_DEPARTMENT_CODE)));
			}
		}

		Log.d(TAG, "RECIPIENTS: " + recipients.size());

		List<PieChartModel> recipientUnits = new ArrayList<PieChartModel>();		
		
		for(String r : recipients) {
			Log.d(TAG, "RECIPIENTS: " + r);			
			Cursor cursorByRecipient = saroDataSource.getByRawQuery("SELECT DISTINCT " + DBHelper.COL_TBL_SARO_AMOUNT + " FROM " 
					+ DBHelper.TBL_SARO + " WHERE " + DBHelper.COL_TBL_SARO_DEPARTMENT_CODE + "='" + r + "' AND year='" + year +"'");
			
			double amount = 0;
			
			if (cursorByRecipient.getCount() > 0) {
				while (cursorByRecipient.moveToNext()) {
					
					String tmpAmount = cursorByRecipient.getString(cursorByRecipient.getColumnIndex(DBHelper.COL_TBL_SARO_AMOUNT)).trim();
					amount += Double.parseDouble((tmpAmount.equals("-")) ?  "0" : tmpAmount);
					
				}
			}
			
			Log.d(TAG, "RECIPIENT SARO AMOUNT: " + amount);
			recipientUnits.add(new PieChartModel(r, (amount>0) ? amount : -1));

		}
		
		saroDataSource.close();	

		
		
		return recipientUnits;
	}
	
	public List<PieChartModel> getRegions(String year) {
		
		SaroDataSource saroDataSource = new SaroDataSource(context);
		saroDataSource.open();
		Cursor cursor = saroDataSource.getByRawQuery("SELECT DISTINCT " + DBHelper.COL_TBL_SARO_REGION + " FROM " 
				+ DBHelper.TBL_SARO + " WHERE " + DBHelper.COL_TBL_SARO_REGION + "!='' ORDER BY " + DBHelper.COL_TBL_SARO_REGION );
		
		List<String> regions = new ArrayList<String>();
		
		if (cursor.getCount() > 0) {
			while (cursor.moveToNext()) {
				regions.add(cursor.getString(cursor.getColumnIndex(DBHelper.COL_TBL_SARO_REGION)));
			}
		}

		Log.d(TAG, "REGIONS: " + regions.size());
		
		List<PieChartModel> pmRegions = new ArrayList<PieChartModel>();		
		
		for(String r : regions) {
			Log.d(TAG, "REGIONS: " + r);
			Cursor cursorByRegion = saroDataSource.getByRawQuery("SELECT DISTINCT " + DBHelper.COL_TBL_SARO_AMOUNT + " FROM " 
					+ DBHelper.TBL_SARO + " WHERE " + DBHelper.COL_TBL_SARO_REGION + "='" + r + "' AND year='" + year +"'");
			
			double amount = 0;
			
			if (cursorByRegion.getCount() > 0) {
				while (cursorByRegion.moveToNext()) {
					
					String tmpAmount = cursorByRegion.getString(cursorByRegion.getColumnIndex(DBHelper.COL_TBL_SARO_AMOUNT)).trim();
					amount += Double.parseDouble((tmpAmount.equals("-")) ?  "0" : tmpAmount);
					
				}
			}
			Log.d(TAG, "REGIONS SARO AMOUNT: " + amount);
			pmRegions.add(new PieChartModel("Region " + r, (amount>0) ? amount:-1));

		}
		
		saroDataSource.close();	
		return pmRegions;
	}
	
	public List<PieChartModel> getSectors(String year) {
		
		SectorDatasource sectorDataSource = new SectorDatasource();
		
		List<Particular> tmpSectors = sectorDataSource.getParticulars();
		//List<Particular> sectors = new ArrayList<Particular>();

		List<PieChartModel> pmSectors = new ArrayList<PieChartModel>();		

		for(Particular p : tmpSectors) {
			if( p.getYear().equals(year)) {
				//sectors.add(p);
				Log.d(TAG, "SECTORS: " + p.getName());
				pmSectors.add(new PieChartModel(p.getName(), p.getPercentShare()));
			}
		}
		
		Log.d(TAG, "SECTORS COUNT: " + pmSectors.size());
		return pmSectors;
	}

}
