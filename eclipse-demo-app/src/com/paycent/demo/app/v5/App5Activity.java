package com.paycent.demo.app.v5;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.paycent.android.sdk.SdkPayRequest;
import com.paycent.android.sdk.SdkPayResult;
import com.paycent.android.sdk.SdkPayTask;
import com.paycent.android.sdk.SdkPayTaskFactory;
import com.paycent.demo.app.R;
import com.paycent.demo.app.model.Order;
import com.paycent.demo.app.model.OrderFactory;
import com.paycent.demo.app.ui.OrderReqRecyclerFragment;
import com.paycent.demo.app.ui.OrderResultFragment;
import com.paycent.demo.app.ui.SingleFragmentActivity;

import java.util.List;

public class App5Activity extends SingleFragmentActivity {

	@Override
	protected Fragment createInitFragment() {

		return new OrderReqRecyclerFragment();
	}

	String TAG = this.getClass().getSimpleName();

	OrderFactory orderFactory;



	@Override
	public void onCreate(Bundle savedInstanceState) {

		orderFactory = new OrderFactory();


		super.onCreate(savedInstanceState);

	}

	public List<Order> getOrders() {
		return orderFactory.getOrders();
	}

	private void showNewFragment(Fragment fragment) {

		try{

			FragmentManager fm = getSupportFragmentManager();

			FragmentTransaction ft = fm.beginTransaction();

			ft.replace(getFragmentResId(), fragment);

			ft.commitAllowingStateLoss();

		}catch(Throwable tr){
			Log.i(TAG, tr.toString());
		}
	}

	public void showOrderResultFragment(SdkPayResult result){
		showNewFragment(OrderResultFragment.newInstance(result) );
	}

	public void showOrderReqRecyclerFragment(){
		showNewFragment(new OrderReqRecyclerFragment() );
	}


	private Handler mHandler = new Handler() {


		public void handleMessage(Message msg) {

			SdkPayResult result = (SdkPayResult) msg.obj;

			Log.d(TAG, result.toString() );
			if( !result.getStatus() .equals("102")) {
				showOrderResultFragment(result);
			}else{
				Log.i(TAG, "TODO: Got to query...");
				Toast.makeText(App5Activity.this, "Order was created but not completed", Toast.LENGTH_LONG).show();
			}
		}
	};

	public void pay(String countryCode, String amount) {
		final SdkPayRequest req = orderFactory.createSdkRequest();
		req.setCountry(countryCode);
		req.setOrderNo("APP"+String.valueOf(System.currentTimeMillis()));
		req.setAmount(amount);
		req.setProductName("SDK Game Credits"+amount);
		req.setFrpCodes(orderFactory.getChannelCodes(countryCode));
		Log.i(TAG, req.getFrpCodes().toString() );


		SdkPayTask sdkTask = SdkPayTaskFactory.getInstance(this);

		sdkTask.pay(req, mHandler);
	}

}
