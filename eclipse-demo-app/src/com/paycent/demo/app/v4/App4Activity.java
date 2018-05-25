package com.paycent.demo.app.v4;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.paycent.demo.app.R;
import com.paycent.demo.app.model.OrderFactory;
import com.paycent.demo.app.ui.SimpleV4Fragment;
import com.paycent.android.sdk.SdkPayRequest;
import com.paycent.android.sdk.SdkPayResult;
import com.paycent.android.sdk.SdkPayTask;
import com.paycent.android.sdk.SdkPayTaskFactory;

public class App4Activity extends FragmentActivity {

	protected Fragment createInitFragment() {

		return new SimpleV4Fragment();
	}

	protected int getLayoutResId() {
		return R.layout.activity_fragment;
	}

	protected int getFragmentResId() {
		return R.id.fragmentContainer;
	}

	String TAG = this.getClass().getSimpleName();

	OrderFactory orderFactory;


	@Override
	public void onCreate(Bundle savedInstanceState) {

		orderFactory = new OrderFactory();


		super.onCreate(savedInstanceState);

		setContentView(getLayoutResId());

		FragmentManager fm = getSupportFragmentManager();
		Fragment fragment = fm.findFragmentById(getFragmentResId());

		if (fragment == null) {
			fragment = createInitFragment();
			fm.beginTransaction()
					.add(getFragmentResId(), fragment)
					.commit();
		}
	}

	private Handler mHandler = new Handler() {


		public void handleMessage(Message msg) {

		SdkPayResult result = (SdkPayResult) msg.obj;

		Log.d(TAG, result.toString() );
		if( !result.getStatus() .equals("102")) {
			Log.d(TAG, result.toString() );
		}else{
			Log.i(TAG, "TODO: Got to query...");
		}

		}
	};

	public void pay(String countryCode, String amount) {

		final SdkPayRequest req = orderFactory.createSdkRequest();
		req.setCountry(countryCode);
		req.setOrderNo(String.valueOf(System.currentTimeMillis()));
		req.setProductName("SDK Game credits");
		req.setAmount(amount);
		req.setFrpCodes(orderFactory.getChannelCodes(countryCode));
		if( req.getFrpCodes() != null){
			Log.i(TAG, req.getFrpCodes().toString() );
		}
		SdkPayTask sdkTask = SdkPayTaskFactory.getInstance(this);

		sdkTask.pay(req, mHandler);
	}

}
