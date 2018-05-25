package com.paycent.demo.app.model;

import android.content.Context;
import android.util.SparseArray;

import com.google.gson.annotations.SerializedName;
import com.paycent.android.sdk.SdkPayRequest;
import com.paycent.demo.app.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class OrderFactory {

	public static String selectedCountryCode ="ID";
	
	public SdkPayRequest createSdkRequest(){
		SdkPayRequest req = new SdkPayRequest();
		req.setMerchantNo("888xxxxxxxxxx");	//merchant no
		req.setMerchantKey("merchantkey"); //merchant key
		req.setPayServerUrl("https://gateway-sandbox.bellotec.com/"); //this should be replaced with prod
		req.setNotifyUrl("https://merchanthost/notify_url"); //this should be replaced with merchant server

		return  req;
	}



	private List<Order> orders;

	public List<Order> getOrders() {

		if(orders == null){
			initOrders();
		}

		return orders;
	}

	private static SparseArray<String> iconMap = new SparseArray<>();

	private static SparseArray<String> titleMap = new SparseArray<>();

	private static SparseArray<String> amountMap = new SparseArray<>();

	static{
		iconMap.put(0, "prod0");
		iconMap.put(1, "prod1");
		iconMap.put(2, "prod1");
		iconMap.put(3, "prod2");
		iconMap.put(4, "prod2");
		iconMap.put(5, "prod2");
		iconMap.put(6, "prod3");
		iconMap.put(7, "prod3");
		iconMap.put(8, "prod3");
		iconMap.put(9, "prod4");
		iconMap.put(10, "prod4");
		iconMap.put(11, "prod4");

		titleMap.put(0, "Credits MELUHA");
		titleMap.put(1, "Credits NAGAS");
		titleMap.put(2, "Credits NAGAS");
		titleMap.put(3, "Credits VAYUPUTRAS");
		titleMap.put(4, "Credits VAYUPUTRAS");
		titleMap.put(5, "Credits VAYUPUTRAS");
		titleMap.put(6, "Credits War Craft");
		titleMap.put(7, "Credits War Craft");
		titleMap.put(8, "Credits War Craft");
		titleMap.put(9, "Credits Expendables");
		titleMap.put(10, "Credits Expendables");
		titleMap.put(11, "Credits Expendables");

		amountMap.put(0, "1");
		amountMap.put(1, "10");
		amountMap.put(2, "20");
		amountMap.put(3, "100");
		amountMap.put(4, "300");
		amountMap.put(5, "500");
		amountMap.put(6, "1000");
		amountMap.put(7, "2000");
		amountMap.put(8, "2200");
		amountMap.put(9, "5000");
		amountMap.put(10, "10000");
		amountMap.put(11, "50000");
	}

	private void initOrders(){

		orders = new ArrayList<>();

		for(int i=0; i<amountMap.size(); i++){

			Order o = new Order();
			o.setTitle(titleMap.get(i) );
			o.setIconName(iconMap.get(i) );
			o.setAmount("Price: " + amountMap.get(i) );
			o.setNumber("Number: " + 1);

			orders.add(o);
		}

	}

	private static Map<String, String> idMaps = new HashMap<>();
	static{
		
		idMaps.put("ID-DCB-TELKOMSEL", "TELKOMSEL");
		idMaps.put("ID-DCB-INDOSAT", "INDOSAT");
		idMaps.put("ID-DCB-XL", "XL");
		idMaps.put("ID-DCB-H3I", "H3I");
		idMaps.put("ID-EW-UNIPIN", "UNIPIN");

		idMaps.put("ID-BANK-BCA", "BCA");
		idMaps.put("ID-OTC-ALFAMART", "ALFAMART");
	}

	private static Map<String, String> thMaps = new HashMap<>();
	static{
		
		thMaps.put("TH-DCB-DTAC", "DTAC");

	}

	private static Map<String, String> phMaps = new HashMap<>();
	static {
	
		phMaps.put("PH-DCB-SMART", "SMART");

	}

	private static Map<String, String> vnMaps = new HashMap<>();
	static {
	
		vnMaps.put("VN-DCB-VIETTEL", "VIETTEL");
		vnMaps.put("VN-DCB-MOBIFONE", "MOBIFONE");
		vnMaps.put("VN-DCB-VINAPHONE", "VINAPHONE");

		vnMaps.put("VN-VR-VIETTEL", "VIETTEL");
		vnMaps.put("VN-VR-VINAPHONE", "VINAPHONE");
		vnMaps.put("VN-VR-MOBIFONE", "MOBIFONE");
		vnMaps.put("VN-VR-VNMOBILE", "VNMOBILE");
		vnMaps.put("VN-VR-VCOIN", "VCOIN");
		vnMaps.put("VN-VR-GATE", "GATE");
		vnMaps.put("VN-VR-ZING", "ZING");
	}

	private static Map<String, String> mmMaps = new HashMap<>();
	static {
	

		mmMaps.put("MM-DCB-OOREDOO", "OOREDOO");
		mmMaps.put("MM-DCB-TELENOR", "TELENOR");

		mmMaps.put("MM-VR-REDDOT", "REDDOT");

		mmMaps.put("MM-VR-MYPIN", "MYPIN");
		mmMaps.put("MM-VR-MEC", "MEC");

		mmMaps.put("MM-EW-OKDOLLAR", "OKDOLLAR");

	}

	private static Map<String, String> sgMaps = new HashMap<>();
	static {
		sgMaps.put("SG-DCB-M1", "M1");
	}

	private static Map<String, String> myMaps = new HashMap<>();
	static {

		myMaps.put("MY-DCB-MAXIS", "MAXIS");
	}


	private static Map<String, String> inMaps = new HashMap<>();
	static {
		inMaps.put("IN-DCB-VODAFONE", "VODAFONE");
		inMaps.put("IN-DCB-IDEA", "IDEA");
		inMaps.put("IN-DCB-AIRTEL", "AIRTEL");

	}

	private static Map<String, Map<String, String>>channelMaps = new HashMap<>();

	static{
		channelMaps.put("ID", idMaps);
		channelMaps.put("TH", thMaps);
		channelMaps.put("PH", phMaps);
		channelMaps.put("VN", vnMaps);
		channelMaps.put("MM", mmMaps);
		channelMaps.put("SG", sgMaps);
		channelMaps.put("MY", myMaps);
		channelMaps.put("IN", inMaps);


	}


	public static Map<String,  String> ccMapping = new HashMap<>();

	static{
		ccMapping.put("Indonesia","ID");
		ccMapping.put("Thailand","TH");
		ccMapping.put("Philippines","PH" );
		ccMapping.put("Vietnam","VN");
		ccMapping.put("Myanmar","MM");
		ccMapping.put("Singapore","SG");
		ccMapping.put("Malaysia","MY");
		ccMapping.put("India","IN");
		//ccMapping.put("China","CN");

	}

	public String[] getChannelCodes(String countryCode){
		//Option1: you can input multiple channels code instead of using dynamic channels
		/*
		Object[] ks = channelMaps.get(countryCode).keySet().toArray();
		return Arrays.copyOf(ks, ks.length, String[].class);
		*/

		//Option2 : you can input empty, it will retrieve channels from server dynamically
		return null;
	}



}
