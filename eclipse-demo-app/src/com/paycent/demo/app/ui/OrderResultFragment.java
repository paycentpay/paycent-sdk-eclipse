package com.paycent.demo.app.ui;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.paycent.demo.app.R;
import com.paycent.android.sdk.SdkPayConstants;
import com.paycent.android.sdk.SdkPayResult;
import com.paycent.demo.app.v5.App5Activity;

public class OrderResultFragment extends Fragment {

	public static final String EXTRA_PAY_RESULT_ID = "OrderResultFragment.pay_result";

	public static OrderResultFragment newInstance(SdkPayResult result) {
		Bundle args = new Bundle();
		args.putParcelable(EXTRA_PAY_RESULT_ID, result);

		OrderResultFragment fragment = new OrderResultFragment();
		fragment.setArguments(args);
		return fragment;
	}

	private App5Activity orderActivity;

	private SdkPayResult payResult;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		orderActivity = (App5Activity) activity;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		payResult = getArguments().getParcelable(EXTRA_PAY_RESULT_ID);

		setHasOptionsMenu(true);

		getActivity().setTitle(R.string.title_fragment_order_result);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_result, parent, false);

		Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
		orderActivity.setSupportActionBar(toolbar);
		orderActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		addStatusDetail(rootView);

		addPaymentDetail(rootView.findViewById(R.id.pay_detail_layout) );

		return rootView;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
//		inflater.inflate(R.menu.menu_order_result, menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.action_order_result:
				orderActivity.showOrderReqRecyclerFragment();
				return true;

			case android.R.id.home:
				orderActivity.showOrderReqRecyclerFragment();
				return true;

			default:
				return super.onOptionsItemSelected(item);
		}
	}

	public void addStatusDetail(View parentView){

		String iconName = null;
		String titleName = null;
		int resourceId;

		String status = payResult.getStatus();

		if(status.equalsIgnoreCase(SdkPayConstants.StatusServerError) ||
				status.equalsIgnoreCase(SdkPayConstants.StatusNetworkError)){
			iconName = "ic_result_failure";
			titleName = "order_result_failure";
		}else  if( status.equalsIgnoreCase(SdkPayConstants.StatusSuccess)){
			iconName = "ic_result_success";
			titleName = "order_result_success";
		}

		if(iconName == null || titleName == null){
			return;
		}

		ImageView iconView = (ImageView) parentView.findViewById(R.id.order_result_icon);
		resourceId = getActivity().getResources().getIdentifier(iconName, "drawable", getActivity().getPackageName());
		if(resourceId > 0){
			iconView.setImageResource(resourceId);
		}

		TextView textView = (TextView)parentView.findViewById(R.id.order_result_title);
		resourceId = getActivity().getResources().getIdentifier(titleName, "string", getActivity().getPackageName());
		if(resourceId > 0){
			textView.setText(resourceId);
		}
	}

	private void addPaymentDetail(View parentView){

		TextView textView = (TextView) parentView.findViewById(R.id.pay_detail_orderNo);
		if(textView != null){
			textView.setText(payResult.getOrderNo() );
		}

		textView = (TextView) parentView.findViewById(R.id.pay_detail_amount);
		if(textView != null){
			textView.setText(payResult.getAmount() );
		}
	}

}
