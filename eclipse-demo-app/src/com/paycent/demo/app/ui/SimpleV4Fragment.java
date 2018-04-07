package com.paycent.demo.app.ui;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.paycent.demo.app.R;
import com.paycent.demo.app.model.OrderFactory;
import com.paycent.demo.app.v4.App4Activity;


public class SimpleV4Fragment extends Fragment {

	App4Activity orderActivity;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		orderActivity = (App4Activity) activity;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		getActivity().setTitle(R.string.title_fragment_old);
	}

	String selectedCountryCode = "MM"; //MM
	Long totalAmount = 0L;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.fragment_oldapp, parent, false);

		Spinner spin = (Spinner) v.findViewById(R.id.spinner);
		spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
				String selectedCountry = adapterView.getItemAtPosition(position).toString();
				selectedCountryCode  = OrderFactory.ccMapping.get(selectedCountry);
				Toast.makeText(adapterView.getContext(), "The country is " +
						selectedCountry, Toast.LENGTH_LONG).show();

			}

			public void onNothingSelected(AdapterView<?> adapterView) {
				return;
			}
		});

		Button button = (Button) v.findViewById(R.id.button_pay);
		button.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				orderActivity.pay(selectedCountryCode, String.valueOf(totalAmount));
			}
		});

		TextView appVersionView = (TextView) v.findViewById(R.id.appVersion);
		appVersionView.setText( "ver:git sample");

		return v;
	}


}
