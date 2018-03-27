package network;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * 	Project's URL: https://github.com/talhaqamar/Android-Test
 * 	Created By: Talha Qamar on 25 March 2018
 * 	Author's Email: talhaaus@gmail.com
 */

/**
 * VolleySingleton use to fetch data from the api
 */
public class VolleySingleton {
   private static VolleySingleton mInstance;
   private RequestQueue mRequestQueue;
   private static Context mContext;

   private VolleySingleton(Context context){
	  mContext = context;
	  mRequestQueue = getRequestQueue();
   }

   public static synchronized VolleySingleton getInstance(Context context){
	  if(mInstance == null){
		 mInstance = new VolleySingleton(context);
	  }
	  return mInstance;
   }

   public RequestQueue getRequestQueue(){
	  if(mRequestQueue == null){
		 mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
	  }

	  return mRequestQueue;
   }

   public<T> void addToRequestQueue(Request<T> request){
	  getRequestQueue().add(request);
   }
}