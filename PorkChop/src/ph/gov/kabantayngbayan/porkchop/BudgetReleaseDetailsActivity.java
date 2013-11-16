package ph.gov.kabantayngbayan.porkchop;

import ph.gov.kabantayngbayan.porkchop.models.Saro;
import ph.gov.kabantayngbayan.porkchop.utils.Contents;
import ph.gov.kabantayngbayan.porkchop.utils.QRCodeEncoder;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;

public class BudgetReleaseDetailsActivity extends Activity {

	ImageView mQrCode;
	Bitmap bitmap;
	RatingBar mRatings;
	Button mBtnFb;
	Saro saro;

	public float ratings = 0.0f;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_saro_details);
		initialize();
	}

	private void initialize() {
		saro = (Saro) getIntent().getSerializableExtra("saro");
		TextView txtSaro = (TextView) findViewById(R.id.txt_budget_amount);
		mQrCode = (ImageView) findViewById(R.id.img_qrcode);
		mRatings = (RatingBar) findViewById(R.id.ratings);
		mBtnFb = (Button) findViewById(R.id.sharefb);

		mBtnFb.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				StringBuilder sBuilder = new StringBuilder();
				sBuilder.append("#SARO \n");
				sBuilder.append(ratings);
				sBuilder.append(saro.getDescription());
			}
		});

		mRatings.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {

			@Override
			public void onRatingChanged(RatingBar ratingBar, float rating,
					boolean fromUser) {
				ratings = rating;
			}
		});

		try {
			txtSaro.setText(saroDetailsBuilder(saro));

			WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
			Display display = manager.getDefaultDisplay();
			Point point = new Point();
			point.set(display.getWidth(), display.getHeight());
			// display.getSize(point);
			int width = point.x;
			int height = point.y;
			int smallerDimension = width < height ? width : height;
			smallerDimension = smallerDimension * 3 / 4;
			// Encode with a QR Code image
			QRCodeEncoder qrCodeEncoder = new QRCodeEncoder(
					saro.getBarcode_no(), null, Contents.Type.TEXT,
					BarcodeFormat.QR_CODE.toString(), smallerDimension);
			try {
				bitmap = qrCodeEncoder.encodeAsBitmap();
				mQrCode.setImageBitmap(bitmap);

			} catch (WriterException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private String saroDetailsBuilder(Saro saro) {
		String n = System.getProperty("line.separator");
		String details = n + "Year: " + saro.getYear() + n + "Issue Date: "
				+ saro.getIssue_date() + n + "Region: " + saro.getRegion() + n
				+ "Fund Code:" + saro.getFund_code() + n + "Department Code: "
				+ saro.getDepartment_code() + n + "Agency Code: "
				+ saro.getAgency_code() + n + "SPF Code: " + saro.getSpf_code()
				+ n + "Description: " + saro.getDescription() + n + "Purpose: "
				+ saro.getPurpose() + n + "Program Description: "
				+ saro.getProgram_description() + n + "Object Code: "
				+ saro.getObject_code() + n + "Object Description: "
				+ saro.getObject_description() + n + "Amount: PHP"
				+ saro.getAmount() + n + "SARO No.: " + saro.getSaro_no() + n
				+ "Barcode No.: " + saro.getBarcode_no()

		;

		return details;
	}
}
