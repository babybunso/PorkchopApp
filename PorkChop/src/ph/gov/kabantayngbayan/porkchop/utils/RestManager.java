package ph.gov.kabantayngbayan.porkchop.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import ph.gov.kabantayngbayan.porkchop.R;
import ph.gov.kabantayngbayan.porkchop.db.BudgetAutoApproDataSource;
import ph.gov.kabantayngbayan.porkchop.db.BudgetNewApproDataSource;
import ph.gov.kabantayngbayan.porkchop.db.SaobDataSource;
import ph.gov.kabantayngbayan.porkchop.db.SaroDataSource;
import ph.gov.kabantayngbayan.porkchop.db.TotalDataSource;
import ph.gov.kabantayngbayan.porkchop.models.BudgetAutoAppro;
import ph.gov.kabantayngbayan.porkchop.models.BudgetNewAppro;
import ph.gov.kabantayngbayan.porkchop.models.Saob;
import ph.gov.kabantayngbayan.porkchop.models.Saro;
import ph.gov.kabantayngbayan.porkchop.models.Total;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class RestManager {

	private static final String TAG = RestManager.class.getSimpleName();

	private static final boolean IS_LOCAL_JSON = true;
	private static RestManager instance = null; 						// Singleton Instance
	private static final String APP_ID = "528598ea5e13db3b2c096fd2";	// "5271b3f75e13db245fab2b45";
	private Context context;

	private InputStream mSaob, mSaro, mBudget, mTotal;

	private List<Total> listTotal;
	private List<BudgetAutoAppro> listBudgetAutoAppro;
	private List<BudgetNewAppro> listBudgetNewAppro;
	private List<Saro> listSaro;
	private List<Saob> listSaob;

	private boolean saobDone;
	private boolean saroDone;
	private boolean budgetDone;
	private boolean totalDone;
	private boolean dbDone;

	public static RestManager getInstance(Context context) {
		
		if (instance == null) {
			instance = new RestManager(context);
		}
		return instance;
	}

	protected RestManager(Context context) {
		// Exists only to defeat instantiation.
		this.context = context;
	}

	public void initData() {
		
		if (IS_LOCAL_JSON) {
			
			Log.i(TAG, "Parsing Local JSON File");
			mSaob = context.getResources().openRawResource(R.raw.saob);
			mSaro = context.getResources().openRawResource(R.raw.saro);
			mBudget = context.getResources().openRawResource(R.raw.budget);
			mTotal = context.getResources().openRawResource(R.raw.total);
			
		} else {
			
			Log.i(TAG, "Parsing JSON File Using HTTP-CLIENT");
			mSaob = getJSONFromUrl("http://api.kabantayngbayan.ph/saob?app_id=" + APP_ID); 		// saob request
			mSaro = getJSONFromUrl("http://api.kabantayngbayan.ph/saro?app_id=" + APP_ID); 		// saro request
			mBudget = getJSONFromUrl("http://api.kabantayngbayan.ph/budget?app_id=" + APP_ID); 	// budget request
			mTotal = getJSONFromUrl("http://api.kabantayngbayan.ph/total?app_id=" + APP_ID); 	// total request

		}

		if(!saobDone && null != mSaob) parseSaob(mSaob);
		if(!saroDone && null != mSaro) parseSaro(mSaro);
		if(!budgetDone && null != mBudget) parseBudget(mBudget);
		if(!totalDone && null != mTotal) parseTotal(mTotal);
		
		if(!dbDone) populateDB();
	}
	
	public InputStream getJSONFromUrl(String url) {
		 
        InputStream is = null;

		// Making HTTP GET request
        try {

        	DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet httpPost = new HttpGet(url);
 
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            is = httpEntity.getContent();          
 
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
 
        return is;
    }
	
	private void populateDB() {
		populateTotal();
		populateSaro();
		populateSaob();
		populateBudget();
		
		dbDone = true;
	}

	private void populateTotal() {
		TotalDataSource totalDataSource = new TotalDataSource(context);
		totalDataSource.open();
		totalDataSource.deleteAll(); // TODO - DELETE ALL EXISTING DATA(JUST FOR NOW)

		for (Total total : listTotal) {
			totalDataSource.insert(total);
		}

		totalDataSource.close();
	}

	private void populateSaro() {
		SaroDataSource saroDataSource = new SaroDataSource(context);
		saroDataSource.open();
		saroDataSource.deleteAll(); // TODO - DELETE ALL EXISTING DATA(JUST FOR NOW)

		for (Saro saro : listSaro) {
			saroDataSource.insert(saro);
		}

		saroDataSource.close();
	}

	private void populateSaob() {
		SaobDataSource saobDataSource = new SaobDataSource(context);
		saobDataSource.open();
		saobDataSource.deleteAll(); // TODO - DELETE ALL EXISTING DATA(JUST FOR NOW)

		for (Saob saob : listSaob) {
			saobDataSource.insert(saob);
		}

		saobDataSource.close();
	}

	private void populateBudget() {
		populateBudgetAutoAppro();
		populateBudgetNewAppro();
	}

	private void populateBudgetNewAppro() {
		BudgetNewApproDataSource budgetDataSource = new BudgetNewApproDataSource(context);
		budgetDataSource.open();
		budgetDataSource.deleteAll(); // TODO - DELETE ALL EXISTING DATA(JUST FOR NOW)

		for (BudgetNewAppro budget : listBudgetNewAppro) {
			budgetDataSource.insert(budget);
		}

		budgetDataSource.close();
	}

	private void populateBudgetAutoAppro() {
		BudgetAutoApproDataSource budgetDataSource = new BudgetAutoApproDataSource(context);
		budgetDataSource.open();
		budgetDataSource.deleteAll(); // TODO - DELETE ALL EXISTING DATA(JUST FOR NOW)

		for (BudgetAutoAppro budget : listBudgetAutoAppro) {
			budgetDataSource.insert(budget);
		}

		budgetDataSource.close();
	}

	private void parseTotal(InputStream mTotal) {
		String strJson = getStringFromInputStream(mTotal);
		parseTotal(strJson);
	}

	private void parseTotal(String strJson) {

		Gson gson = new Gson();

		Type type = new TypeToken<HashMap<String, Object>>() {}.getType();
		Map<String, Object> map = gson.fromJson(strJson, type);

		type = new TypeToken<List<Total>>() {}.getType();
		listTotal = gson.fromJson(gson.toJson(map.get("data")), type);
		
		totalDone = true;

		Log.w(TAG, "MODEL_TOTAL COUNT: " + listTotal.size());
		// for (Total t : listTotal) {
		// Log.d(TAG, "ID: " + t.getId());
		// Log.d(TAG, "YEAR: " + t.getYear());
		// Log.d(TAG, "GAA_TOTAL: " + t.getGaa_total());
		// Log.d(TAG, "NEW_APPRO_TOTAL: " + t.getNew_appro_total());
		// }
	}

	private void parseBudget(InputStream mBudget) {
		String strJson = getStringFromInputStream(mBudget);
		parseBudget(strJson);
	}

	private void parseBudget(String strJson) {

		Gson gson = new Gson();

		Type type = new TypeToken<HashMap<String, Object>>() {}.getType();
		Map<String, Object> map = gson.fromJson(strJson, type);

		type = new TypeToken<List<Object>>() {}.getType();
		List<Object> listObj = gson.fromJson(gson.toJson(map.get("data")), type);

		type = new TypeToken<HashMap<String, Object>>() {}.getType();
		map = gson.fromJson(gson.toJson(listObj.get(0)), type); // BUDGET AUTO_APPRO
		type = new TypeToken<List<BudgetAutoAppro>>() {	}.getType();
		listBudgetAutoAppro = gson.fromJson(gson.toJson(map.get("auto_appro")), type);

		type = new TypeToken<HashMap<String, Object>>() {}.getType();
		map = gson.fromJson(gson.toJson(listObj.get(1)), type); // BUDGET NEW_APPRO
		type = new TypeToken<List<BudgetNewAppro>>() {}.getType();
		listBudgetNewAppro = gson.fromJson(gson.toJson(map.get("new_appro")), type);
		

		Log.w(TAG, "MODEL_BUDGET_AUTO_APPRO COUNT: " + listBudgetAutoAppro.size());
		Log.w(TAG, "MODEL_BUDGET_NEW_APPRO COUNT: " + listBudgetNewAppro.size());
		budgetDone = true;
		// for (Budget b : listBudget) {
		// Log.d(TAG, "BUDGET ID: " + b.getId());
		// }
	}

	private void parseSaro(InputStream mSaro) {
		String strJson = getStringFromInputStream(mSaro);
		parseSaro(strJson);
	}

	private void parseSaro(String strJson) {
		Gson gson = new Gson();

		Type type = new TypeToken<HashMap<String, Object>>() {}.getType();
		Map<String, Object> map = gson.fromJson(strJson, type);

		type = new TypeToken<List<Saro>>() {}.getType();
		listSaro = gson.fromJson(gson.toJson(map.get("data")), type);
		

		Log.w(TAG, "MODEL_SARO COUNT: " + listSaro.size());
		saroDone = true;
		// for (Saro s : listSaro) {
		// Log.d(TAG, "ID: " + s.getId());
		// }
	}

	private void parseSaob(InputStream mSaob) {
		String strJson = getStringFromInputStream(mSaob);
		parseSaob(strJson);
	}

	private void parseSaob(String strJson) {
		Gson gson = new Gson();

		Type type = new TypeToken<HashMap<String, Object>>() {}.getType();
		Map<String, Object> map = gson.fromJson(strJson, type);

		type = new TypeToken<List<Saob>>() {}.getType();
		listSaob = gson.fromJson(gson.toJson(map.get("data")), type);
		

		Log.w(TAG, "MODEL_SAOB COUNT: " + listSaob.size());
		saobDone = true;
		// for (Saob s : listSaob) {
		// Log.d(TAG, "ID: " + s.getId());
		// }
	}

	private String getStringFromInputStream(InputStream is) {

		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		String line;
		
		try {

			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return sb.toString();
	}

}
