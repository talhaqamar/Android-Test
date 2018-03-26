package com.talha.test;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import adapter.RecyclerViewAdapter;
import models.FactsVO;
import network.VolleySingleton;
import utilities.Constants;

/**
 * 	Project's URL: https://github.com/talhaqamar/Android-Test
 * 	Project's Author: Talha Qamar
 * 	Author's Email: talhaaus@gmail.com
 * 	Created On: 25 March 2018
 */


/**
 * This class is the main entry point of the project.
 * This class maps the layout files to their adapters/java classes.
 */
public class MainActivity extends AppCompatActivity {

   RecyclerView mRecyclerView;
   private RecyclerView.LayoutManager mLayoutManager;
   List<FactsVO> factsList = new ArrayList<FactsVO>();
   RecyclerViewAdapter adapter;
   SwipeRefreshLayout swipeRefreshLayout;


   @Override
   protected void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.activity_main);

	  //Picasso is library for lazy loading images
	  Picasso.with(this).setIndicatorsEnabled(BuildConfig.DEBUG);
	  try {
		 mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
		 mRecyclerView.setHasFixedSize(true);
		 mLayoutManager = new LinearLayoutManager(this);
		 mRecyclerView.setLayoutManager(mLayoutManager);
		 swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.simpleSwipeRefreshLayout);
		 loadData(); // fetching and loading data

		 //refresh on swipe listener
		 swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {

			   loadData();
			   // implement Handler to wait for 3 seconds and then update UI means update value of TextView
			   new Handler().postDelayed(new Runnable() {
				  @Override
				  public void run() {
					 // cancle the Visual indication of a refresh
					 swipeRefreshLayout.setRefreshing(false);
					 // Generate a random integer number
				  }
			   }, 3000);
			}
		 });
	  }catch (Throwable ex){
		 ex.printStackTrace();
	  }
   }


   /**
	* Load Data method fetch and parse the data
	* Later save in the array list to populate the view
	*/
   void loadData(){
	  try {
		 //  final ProgressDialog dialog = ProgressDialog.show(MainActivity.this, "","Loading. Please wait...", true);
		 JsonObjectRequest req = new JsonObjectRequest(Constants.URL, null,
				 new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
					   try {
						  getSupportActionBar().setTitle(response.getString("title"));
						  factsList.removeAll(Collections.EMPTY_LIST);
						  JSONArray jo = response.getJSONArray("rows");
						  GsonBuilder builder = new GsonBuilder();
						  Gson mGson = builder.create();
						  factsList= new ArrayList<FactsVO>();
						  for(int i =0;i<jo.length();i++){
							 JSONObject obj = (JSONObject) jo.get(i);
							 FactsVO facts = mGson.fromJson(obj.toString(),FactsVO.class);
							 factsList.add(facts);
						  }

					   } catch (Throwable e) {
						  e.printStackTrace();
					   }

					   // dialog.dismiss();
					   adapter = new RecyclerViewAdapter(MainActivity.this,factsList);
					   mRecyclerView.setAdapter(adapter);
					}
				 }, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
			   //dialog.dismiss();
			   VolleyLog.e("Error: ", error.getMessage());
			}
		 });

		 VolleySingleton.getInstance(MainActivity.this).addToRequestQueue(req);
	  }catch (Throwable ex){
		 ex.printStackTrace();
	  }
   }
}

