package com.paycent.demo.app.ui;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.*;
import com.paycent.demo.app.R;
import com.paycent.demo.app.model.OrderFactory;
import com.paycent.demo.app.v5.App5Activity;

public class OrderReqRecyclerFragment extends Fragment {

	private App5Activity orderActivity;

	private RecyclerView mRecyclerView;
	private OrderAdapter mAdapter;
	private RecyclerView.LayoutManager mLayoutManager;


	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		orderActivity =  (App5Activity)activity;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		getActivity().setTitle(R.string.title_fragment_order_req_fragment);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_req_recycler, parent, false);

		Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
		orderActivity.setSupportActionBar(toolbar);
//		orderActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(false);

		// pay button
		FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				orderActivity.pay(OrderFactory.selectedCountryCode, String.valueOf(mAdapter.getAmount()));
			}
		});

		//country code selection
		Spinner spin = (Spinner) rootView.findViewById(R.id.spinner);

		spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

					String selectedCountry = adapterView.getItemAtPosition(position).toString();

					if (selectedCountry != null && !"".equals(selectedCountry)) {
						OrderFactory.selectedCountryCode = OrderFactory.ccMapping.get(selectedCountry);
						Toast.makeText(adapterView.getContext(), "Demo country is " +
								selectedCountry, Toast.LENGTH_LONG).show();
					}

			}

			public void onNothingSelected(AdapterView<?> adapterView) {
				return;
			}
		});

		mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerContent);

		mLayoutManager = new LinearLayoutManager(getActivity() );

		mRecyclerView.setLayoutManager(mLayoutManager);

		mAdapter = new OrderAdapter(getActivity(), orderActivity.getOrders());
		mRecyclerView.setAdapter(mAdapter);

		TextView appVersionView = (TextView) rootView.findViewById(R.id.appVersion);
		appVersionView.setText( "ver: git sample");

		return rootView;
	}

}
