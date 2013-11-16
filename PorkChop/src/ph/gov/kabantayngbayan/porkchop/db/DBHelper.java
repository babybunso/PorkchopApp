package ph.gov.kabantayngbayan.porkchop.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	// DB
	private static final String DB_NAME = "porkchop.sqlite";
	private static final int DB_VERSION = 1;

	// TABLE NAMES
	public static final String TBL_TOTAL = "tbl_total";
	public static final String TBL_SARO = "tbl_saro";
	public static final String TBL_SAOB = "tbl_saob";
	public static final String TBL_BUDGET_AUTO_APPRO = "tbl_budget_auto_appro";
	public static final String TBL_BUDGET_NEW_APPRO = "tbl_budget_new_appro";

	// TABLE COMMON COLUMN
	public static final String COL_ID = "_id";

	// TBL_TOTAL COLUMNS
	public static final String COL_TBL_TOTAL_ID = "id";
	public static final String COL_TBL_TOTAL_YEAR = "year";
	public static final String COL_TBL_TOTAL_GAA_TOTAL = "gaa_total";
	public static final String COL_TBL_TOTAL_NEW_APPRO_TOTAL = "new_appro_total";

	// TBL_SARO COLUMNS
	public static final String COL_TBL_SARO_ID = "id";
	public static final String COL_TBL_SARO_YEAR = "year";
	public static final String COL_TBL_SARO_DEPARTMENT_CODE = "department_code" ;
	public static final String COL_TBL_SARO_AGENCY_CODE = "agency_code";
	public static final String COL_TBL_SARO_OPERATING_UNIT = "operating_unit";
	public static final String COL_TBL_SARO_SPF_CODE = "spf_code";
	public static final String COL_TBL_SARO_FUND_CODE = "description";
	public static final String COL_TBL_SARO_DESCRIPTION = "fund_code";
	public static final String COL_TBL_SARO_REGION = "region";
	public static final String COL_TBL_SARO_APPRO_SRC = "appro_src";
	public static final String COL_TBL_SARO_PROGRAM_DESCRIPTION = "purpose";
	public static final String COL_TBL_SARO_PROGRAM_CODE = "program_code";
	public static final String COL_TBL_SARO_PURPOSE = "program_description";
	public static final String COL_TBL_SARO_OBJECT_CODE = "object_code";
	public static final String COL_TBL_SARO_NO = "saro_no";
	public static final String COL_TBL_SARO_BARCODE_NO = "barcode_no";
	public static final String COL_TBL_SARO_ISSUE_DATE = "issue_date";
	public static final String COL_TBL_SARO_AMOUNT = "amount";
	public static final String COL_TBL_SARO_OBJECT_DESCRIPTION = "object_description";

	// TBL_SAOB COLUMNS
	public static final String COL_TBL_SAOB_ID = "id";
	public static final String COL_TBL_SAOB_YEAR = "year";
	public static final String COL_TBL_SAOB_PERIOD = "period";
	public static final String COL_TBL_SAOB_BUDGET_RECORD = "budget_record";
	public static final String COL_TBL_SAOB_BUDGET_RECORD_LABEL = "budget_record_label";
	public static final String COL_TBL_SAOB_BUDGET_APPROP = "budget_approp";
	public static final String COL_TBL_SAOB_BUDGET_ALLOT_PS = "budget_allot_ps";
	public static final String COL_TBL_SAOB_BUDGET_ALLOT_MOOE = "budget_allot_mooe";
	public static final String COL_TBL_SAOB_BUDGET_ALLOT_CO = "budget_allot_co";
	public static final String COL_TBL_SAOB_BUDGET_ALLOT_TOTAL = "budget_allot_total";
	public static final String COL_TBL_SAOB_BUDGET_OBLIG_PS = "budget_oblig_ps";
	public static final String COL_TBL_SAOB_BUDGET_OBLIG_MOOE = "budget_oblig_mooe";
	public static final String COL_TBL_SAOB_BUDGET_OBLIG_CO = "budget_oblig_co";
	public static final String COL_TBL_SAOB_BUDGET_OBLIG_TOTAL = "budget_oblig_total";
	public static final String COL_TBL_SAOB_BUDGET_UOBLIG_PS = "budget_uoblig_ps";
	public static final String COL_TBL_SAOB_BUDGET_UOBLIG_MOOE = "budget_uoblig_mooe";
	public static final String COL_TBL_SAOB_BUDGET_UOBLIG_CO = "budget_uoblig_co";
	public static final String COL_TBL_SAOB_BUDGET_UOBLIG_TOTAL = "budget_uoblig_total";
	public static final String COL_TBL_SAOB_OBLIGERATE = "obligerate";
	
	public static final String COL_TBL_SAOB_CURRENT_YEAR_BUDGET_RECORD = "current_year_budget_record";
	public static final String COL_TBL_SAOB_CURRENT_YEAR_BUDGET_RECORD_LABEL = "current_year_budget_record_label";
	public static final String COL_TBL_SAOB_CURRENT_YEAR_BUDGET_APPROP = "current_year_budget_approp";
	public static final String COL_TBL_SAOB_CURRENT_YEAR_BUDGET_ALLOT_PS = "current_year_budget_allot_ps";
	public static final String COL_TBL_SAOB_CURRENT_YEAR_BUDGET_ALLOT_MOOE = "current_year_budget_allot_mooe";
	public static final String COL_TBL_SAOB_CURRENT_YEAR_BUDGET_ALLOT_CO = "current_year_budget_allot_co";
	public static final String COL_TBL_SAOB_CURRENT_YEAR_BUDGET_ALLOT_TOTAL = "current_year_budget_allot_total";
	public static final String COL_TBL_SAOB_CURRENT_YEAR_BUDGET_OBLIG_PS = "current_year_budget_oblig_ps";
	public static final String COL_TBL_SAOB_CURRENT_YEAR_BUDGET_OBLIG_MOOE = "current_year_budget_oblig_mooe";
	public static final String COL_TBL_SAOB_CURRENT_YEAR_BUDGET_OBLIG_CO = "current_year_budget_oblig_co";
	public static final String COL_TBL_SAOB_CURRENT_YEAR_BUDGET_OBLIG_TOTAL = "current_year_budget_oblig_total";
	public static final String COL_TBL_SAOB_CURRENT_YEAR_BUDGET_UOBLIG_PS = "current_year_budget_uoblig_ps";
	public static final String COL_TBL_SAOB_CURRENT_YEAR_BUDGET_UOBLIG_MOOE = "current_year_budget_uoblig_mooe";
	public static final String COL_TBL_SAOB_CURRENT_YEAR_BUDGET_UOBLIG_CO = "current_year_budget_uoblig_co";
	public static final String COL_TBL_SAOB_CURRENT_YEAR_BUDGET_UOBLIG_TOTAL = "current_year_budget_uoblig_total";
	public static final String COL_TBL_SAOB_CURRENT_YEAR_OBLIGERATE = "current_year_obligerate";
	
	public static final String COL_TBL_SAOB_CONTINUING_APPRO_RECORD = "continuing_appro_record";
	public static final String COL_TBL_SAOB_CONTINUING_APPRO_RECORD_LABEL = "continuing_appro_record_label";
	public static final String COL_TBL_SAOB_CONTINUING_APPRO_APPROP = "continuing_appro_approp";
	public static final String COL_TBL_SAOB_CONTINUING_APPRO_ALLOT_PS = "continuing_appro_allot_ps";
	public static final String COL_TBL_SAOB_CONTINUING_APPRO_ALLOT_MOOE = "continuing_appro_allot_mooe";
	public static final String COL_TBL_SAOB_CONTINUING_APPRO_ALLOT_CO = "continuing_appro_allot_co";
	public static final String COL_TBL_SAOB_CONTINUING_APPRO_ALLOT_TOTAL = "continuing_appro_allot_total";
	public static final String COL_TBL_SAOB_CONTINUING_APPRO_OBLIG_PS = "continuing_appro_oblig_ps";
	public static final String COL_TBL_SAOB_CONTINUING_APPRO_OBLIG_MOOE = "continuing_appro_oblig_mooe";
	public static final String COL_TBL_SAOB_CONTINUING_APPRO_OBLIG_CO = "continuing_appro_oblig_co";
	public static final String COL_TBL_SAOB_CONTINUING_APPRO_OBLIG_TOTAL = "continuing_appro_oblig_total";
	public static final String COL_TBL_SAOB_CONTINUING_APPRO_UOBLIG_PS = "continuing_appro_uoblig_ps";
	public static final String COL_TBL_SAOB_CONTINUING_APPRO_UOBLIG_MOOE = "continuing_appro_uoblig_mooe";
	public static final String COL_TBL_SAOB_CONTINUING_APPRO_UOBLIG_CO = "continuing_appro_uoblig_co";
	public static final String COL_TBL_SAOB_CONTINUING_APPRO_UOBLIG_TOTAL = "continuing_appro_uoblig_total";
	public static final String COL_TBL_SAOB_CONTINUING_APPRO_OBLIGERATE = "continuing_appro_obligerate";
	
	// TBL_BUDGET BASE COLUMNS - USE IN BOTH TBL_BUDGET_AUTO_APPRO, TBL_BUDGET_NEW_APPRO
	public static final String COL_TBL_BUDGET_APPRO_ID = "id";
	public static final String COL_TBL_BUDGET_APPRO_YEAR = "year";
	public static final String COL_TBL_BUDGET_APPRO_OWNER_CODE = "owner_code";
	public static final String COL_TBL_BUDGET_APPRO_DEPARTMENT_CODE = "department_code";
	public static final String COL_TBL_BUDGET_APPRO_AGENCY_TYPE = "agency_type";
	public static final String COL_TBL_BUDGET_APPRO_OWNER_DESC = "owner_desc";
	public static final String COL_TBL_BUDGET_APPRO_DEPARTMENT_DESC = "department_desc";
	public static final String COL_TBL_BUDGET_APPRO_PROGRAM_DESC = "program_desc";
	public static final String COL_TBL_BUDGET_APPRO_PROGRAM_AREA_CODE = "program_area_code";
	public static final String COL_TBL_BUDGET_APPRO_PROGRAM_AREA_DESC = "program_area_desc";
	public static final String COL_TBL_BUDGET_APPRO_PROGRAM_BUDGET_PS = "program_budget_ps";
	public static final String COL_TBL_BUDGET_APPRO_PROGRAM_BUDGET_MOOE = "program_budget_mooe";
	public static final String COL_TBL_BUDGET_APPRO_PROGRAM_BUDGET_CO = "program_budget_co";

	// CREATE DB TABLES
	private static final String TBL_CREATE_TOTAL = "CREATE  TABLE " + TBL_TOTAL + " (" + COL_ID + " INTEGER PRIMARY KEY  AUTOINCREMENT NOT NULL, " 
			+ COL_TBL_TOTAL_ID + " VARCHAR, " 
			+ COL_TBL_TOTAL_YEAR + " VARCHAR, " 
			+ COL_TBL_TOTAL_GAA_TOTAL + " VARCHAR, " 
			+ COL_TBL_TOTAL_NEW_APPRO_TOTAL + " VARCHAR" 
			+ ");";
	
	private static final String TBL_CREATE_SARO = "CREATE  TABLE " + TBL_SARO + " (" + COL_ID + " INTEGER PRIMARY KEY  AUTOINCREMENT NOT NULL,  " 
			+ COL_TBL_SARO_ID + " VARCHAR, " 
			+ COL_TBL_SARO_YEAR + " VARCHAR, " 
			+ COL_TBL_SARO_DEPARTMENT_CODE + " VARCHAR, " 
			+ COL_TBL_SARO_AGENCY_CODE + " VARCHAR, " 
			+ COL_TBL_SARO_OPERATING_UNIT + " VARCHAR, " 
			+ COL_TBL_SARO_SPF_CODE + " VARCHAR, " 
			+ COL_TBL_SARO_DESCRIPTION + " TEXT, " 
			+ COL_TBL_SARO_FUND_CODE + " VARCHAR, " 
			+ COL_TBL_SARO_REGION + " VARCHAR, " 
			+ COL_TBL_SARO_APPRO_SRC + " VARCHAR, " 
			+ COL_TBL_SARO_PURPOSE + " TEXT, " 
			+ COL_TBL_SARO_PROGRAM_CODE + " VARCHAR, " 
			+ COL_TBL_SARO_PROGRAM_DESCRIPTION + " TEXT, " 
			+ COL_TBL_SARO_OBJECT_CODE + " VARCHAR, " 
			+ COL_TBL_SARO_OBJECT_DESCRIPTION + " TEXT, " 
			+ COL_TBL_SARO_AMOUNT + " VARCHAR, "
			+ COL_TBL_SARO_ISSUE_DATE + " VARCHAR, " 
			+ COL_TBL_SARO_BARCODE_NO + " VARCHAR, " 
			+ COL_TBL_SARO_NO + " VARCHAR"
			+ ");";

	private static final String TBL_CREATE_SAOB = "CREATE  TABLE " + TBL_SAOB + " (" + COL_ID + " INTEGER PRIMARY KEY  AUTOINCREMENT NOT NULL,  "		
			
		+ COL_TBL_SAOB_ID + " VARCHAR, " 
		+ COL_TBL_SAOB_YEAR + " VARCHAR, " 
		+ COL_TBL_SAOB_PERIOD + " VARCHAR, "
		+ COL_TBL_SAOB_BUDGET_RECORD + " VARCHAR, "
		+ COL_TBL_SAOB_BUDGET_RECORD_LABEL + " VARCHAR, "
		+ COL_TBL_SAOB_BUDGET_APPROP + " VARCHAR, "
		+ COL_TBL_SAOB_BUDGET_ALLOT_PS + " VARCHAR, "
		+ COL_TBL_SAOB_BUDGET_ALLOT_MOOE + " VARCHAR, "
		+ COL_TBL_SAOB_BUDGET_ALLOT_CO + " VARCHAR, "
		+ COL_TBL_SAOB_BUDGET_ALLOT_TOTAL + " VARCHAR, "
		+ COL_TBL_SAOB_BUDGET_OBLIG_PS + " VARCHAR, "
		+ COL_TBL_SAOB_BUDGET_OBLIG_MOOE + " VARCHAR, "
		+ COL_TBL_SAOB_BUDGET_OBLIG_CO + " VARCHAR, "
		+ COL_TBL_SAOB_BUDGET_OBLIG_TOTAL + " VARCHAR, "
		+ COL_TBL_SAOB_BUDGET_UOBLIG_PS + " VARCHAR, "
		+ COL_TBL_SAOB_BUDGET_UOBLIG_MOOE + " VARCHAR, "
		+ COL_TBL_SAOB_BUDGET_UOBLIG_CO + " VARCHAR, "
		+ COL_TBL_SAOB_BUDGET_UOBLIG_TOTAL + " VARCHAR, "
		+ COL_TBL_SAOB_OBLIGERATE + " VARCHAR, "
		
		+ COL_TBL_SAOB_CURRENT_YEAR_BUDGET_RECORD + " VARCHAR, "
		+ COL_TBL_SAOB_CURRENT_YEAR_BUDGET_RECORD_LABEL + " VARCHAR, "
		+ COL_TBL_SAOB_CURRENT_YEAR_BUDGET_APPROP + " VARCHAR, "
		+ COL_TBL_SAOB_CURRENT_YEAR_BUDGET_ALLOT_PS + " VARCHAR, "
		+ COL_TBL_SAOB_CURRENT_YEAR_BUDGET_ALLOT_MOOE + " VARCHAR, "
		+ COL_TBL_SAOB_CURRENT_YEAR_BUDGET_ALLOT_CO + " VARCHAR, "
		+ COL_TBL_SAOB_CURRENT_YEAR_BUDGET_ALLOT_TOTAL + " VARCHAR, "
		+ COL_TBL_SAOB_CURRENT_YEAR_BUDGET_OBLIG_PS + " VARCHAR, "
		+ COL_TBL_SAOB_CURRENT_YEAR_BUDGET_OBLIG_MOOE + " VARCHAR, "
		+ COL_TBL_SAOB_CURRENT_YEAR_BUDGET_OBLIG_CO + " VARCHAR, "
		+ COL_TBL_SAOB_CURRENT_YEAR_BUDGET_OBLIG_TOTAL + " VARCHAR, "
		+ COL_TBL_SAOB_CURRENT_YEAR_BUDGET_UOBLIG_PS + " VARCHAR, "
		+ COL_TBL_SAOB_CURRENT_YEAR_BUDGET_UOBLIG_MOOE + " VARCHAR, "
		+ COL_TBL_SAOB_CURRENT_YEAR_BUDGET_UOBLIG_CO + " VARCHAR, "
		+ COL_TBL_SAOB_CURRENT_YEAR_BUDGET_UOBLIG_TOTAL + " VARCHAR, "
		+ COL_TBL_SAOB_CURRENT_YEAR_OBLIGERATE + " VARCHAR, "
		
		+ COL_TBL_SAOB_CONTINUING_APPRO_RECORD + " VARCHAR, "
		+ COL_TBL_SAOB_CONTINUING_APPRO_RECORD_LABEL + " VARCHAR, "
		+ COL_TBL_SAOB_CONTINUING_APPRO_APPROP + " VARCHAR, "
		+ COL_TBL_SAOB_CONTINUING_APPRO_ALLOT_PS + " VARCHAR, "
		+ COL_TBL_SAOB_CONTINUING_APPRO_ALLOT_MOOE + " VARCHAR, "
		+ COL_TBL_SAOB_CONTINUING_APPRO_ALLOT_CO + " VARCHAR, "
		+ COL_TBL_SAOB_CONTINUING_APPRO_ALLOT_TOTAL + " VARCHAR, "
		+ COL_TBL_SAOB_CONTINUING_APPRO_OBLIG_PS + " VARCHAR, "
		+ COL_TBL_SAOB_CONTINUING_APPRO_OBLIG_MOOE + " VARCHAR, "
		+ COL_TBL_SAOB_CONTINUING_APPRO_OBLIG_CO + " VARCHAR, "
		+ COL_TBL_SAOB_CONTINUING_APPRO_OBLIG_TOTAL + " VARCHAR, "
		+ COL_TBL_SAOB_CONTINUING_APPRO_UOBLIG_PS + " VARCHAR, "
		+ COL_TBL_SAOB_CONTINUING_APPRO_UOBLIG_MOOE + " VARCHAR, "
		+ COL_TBL_SAOB_CONTINUING_APPRO_UOBLIG_CO + " VARCHAR, "
		+ COL_TBL_SAOB_CONTINUING_APPRO_UOBLIG_TOTAL + " VARCHAR, "
		+ COL_TBL_SAOB_CONTINUING_APPRO_OBLIGERATE + " VARCHAR"
		+ ");";
	
	// TBL_BUDGET CREATE TABLE SQL BASE - USE IN BOTH TBL_BUDGET_AUTO_APPRO, TBL_BUDGET_NEW_APPRO
	private final static String createBudgetTable(String budgetTableType) {
	
		return String.format("CREATE  TABLE %s (" + COL_ID + " INTEGER PRIMARY KEY  AUTOINCREMENT NOT NULL,  " 
					+ COL_TBL_BUDGET_APPRO_ID + " VARCHAR, " 
					+ COL_TBL_BUDGET_APPRO_YEAR + " VARCHAR, "
					+ COL_TBL_BUDGET_APPRO_OWNER_CODE + " VARCHAR, "
					+ COL_TBL_BUDGET_APPRO_DEPARTMENT_CODE + " VARCHAR, "
					+ COL_TBL_BUDGET_APPRO_AGENCY_TYPE + " VARCHAR, "
					+ COL_TBL_BUDGET_APPRO_OWNER_DESC + " TEXT, "
					+ COL_TBL_BUDGET_APPRO_DEPARTMENT_DESC + " VARCHAR, "
					+ COL_TBL_BUDGET_APPRO_PROGRAM_DESC + " TEXT, "
					+ COL_TBL_BUDGET_APPRO_PROGRAM_AREA_CODE + " VARCHAR, "
					+ COL_TBL_BUDGET_APPRO_PROGRAM_AREA_DESC + " TEXT, "
					+ COL_TBL_BUDGET_APPRO_PROGRAM_BUDGET_PS + " DOUBLE, "
					+ COL_TBL_BUDGET_APPRO_PROGRAM_BUDGET_MOOE + " DOUBLE, "
					+ COL_TBL_BUDGET_APPRO_PROGRAM_BUDGET_CO + " DOUBLE"
					+ ");"
					
					, budgetTableType);
	}
	private static final String TBL_CREATE_BUDGET_AUTO_APPRO = createBudgetTable(TBL_BUDGET_AUTO_APPRO);
	private static final String TBL_CREATE_BUDGET_NEW_APPRO = createBudgetTable(TBL_BUDGET_NEW_APPRO);
	

	// DROP DB TABLES
	private static final String TBL_DROP_TOTAL = "DROP TABLE IF EXISTS " + TBL_TOTAL;
	private static final String TBL_DROP_SARO = "DROP TABLE IF EXISTS " + TBL_SARO;
	private static final String TBL_DROP_SAOB = "DROP TABLE IF EXISTS " + TBL_SAOB;
	private static final String TBL_DROP_BUDGET_AUTO_APPRO = "DROP TABLE IF EXISTS " + TBL_BUDGET_AUTO_APPRO;
	private static final String TBL_DROP_BUDGET_NEW_APPRO = "DROP TABLE IF EXISTS " + TBL_BUDGET_NEW_APPRO;

	public DBHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(TBL_CREATE_TOTAL);
		db.execSQL(TBL_CREATE_SARO);
		db.execSQL(TBL_CREATE_SAOB);
		db.execSQL(TBL_CREATE_BUDGET_AUTO_APPRO);
		db.execSQL(TBL_CREATE_BUDGET_NEW_APPRO);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(TBL_DROP_TOTAL);
		db.execSQL(TBL_DROP_SARO);
		db.execSQL(TBL_DROP_SAOB);
		db.execSQL(TBL_DROP_BUDGET_AUTO_APPRO);
		db.execSQL(TBL_DROP_BUDGET_NEW_APPRO);
		onCreate(db);
	}

}
