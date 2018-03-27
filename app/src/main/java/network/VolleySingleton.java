package network;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by talhaqamar on 27/3/18.
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