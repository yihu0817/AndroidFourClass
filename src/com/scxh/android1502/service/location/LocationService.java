
package com.scxh.android1502.service.location;

import java.util.ArrayList;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;

import com.scxh.android1502.R;
import com.scxh.android1502.storage.file.browse.FileExplorerActivity;
import com.scxh.android1502.util.Logs;

public class LocationService extends Service{

	private Context context;
	public static ArrayList<Store> stores=new ArrayList<Store>();
	public static double distance = 3;//7430km
	private LocationManager locationManager;
	private NotificationManager notificationManager;
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		Store store = new Store(); //体院
		store.setLatitude(104.047644);
		store.setLongitude(30.652735);
		store.setStoreName("体院");
		store.setStoreAddress("一环路西一段");
		store.setDistance("3公里");
		stores.add(store);
		
		store = new Store(); //住处
		store.setLatitude(103.992093);
		store.setLongitude(30.613707);
		store.setStoreName("住处");
		store.setStoreAddress("三环路西一段");
		store.setDistance("3公里");
		stores.add(store);
		
		
		context = this;
		locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		
		if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
			locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1000, 1f, locationListener);
		}else{
			locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,1000, 1f,locationListener);
		}
		
//		Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);     
//        if(location != null){
//        	checkDistance(location);
//        }     
        
	}

	
	private final LocationListener locationListener = new LocationListener() {
		//当坐标改变时触发此函数，如果Provider传进相同的坐标，它就不会被触发
	    public void onLocationChanged(Location location) { 
	        // log it when the location changes
	        if (location != null) {
	        	Logs.v("onLocationChanged>>>>");
	        	checkDistance(location);
	        }
	    }

	    // Provider被disable时触发此函数，比如GPS被关闭
	    public void onProviderDisabled(String provider) {
	    	Logs.v("onProviderDisabled>>>>");
	    }

	    //  Provider被enable时触发此函数，比如GPS被打开
	    public void onProviderEnabled(String provider) {
	    	Logs.v("onProviderEnabled>>>>");
	    }

	    // Provider的在可用、暂时不可用和无服务三个状态直接切换时触发此函数
	    public void onStatusChanged(String provider, int status, Bundle extras) {
	    	Logs.v("onLocationChanged>>>>");
	    }
	};
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		if(locationManager!=null){
			locationManager.removeUpdates(locationListener); 
			locationManager=null;
		}
	}

	private void checkDistance(Location location) {
		if (location != null) {
			float[] results = new float[1];
			for (Store store : stores) {
				Location.distanceBetween(location.getLatitude(),
						location.getLongitude(), store.getLatitude(),
						store.getLongitude(), results);
				
				double distances = onDistance(location.getLatitude(),
						location.getLongitude(), store.getLatitude(),
						store.getLongitude());
				
				Logs.v("distances  "+distances);
				Logs.v("location.getLatitude()  "+location.getLatitude() + "  location.getLongitude() "+location.getLongitude());
				
				Logs.v("checkDistance >>>>results[0] "+results[0]);
				
				float result=(results[0] / 1000);//km
				
				Logs.v("checkDistance >>>>result "+result + "  distance :"+distance);
				if (result < distance) {
					
					showNotification(store);
					
					stopSelf();//不要频繁的提醒
					break;
				}
			}
		}
	}

	double onDistance(double lat1, double lon1, double lat2, double lon2) {  
	    double theta = lon1 - lon2;  
	    double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))  
	                + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2))  
	                * Math.cos(deg2rad(theta));  
	    dist = Math.acos(dist);  
	    dist = rad2deg(dist);  
	    double miles = dist * 60 * 1.1515;  
	    return miles;  
	}  
	//将角度转换为弧度  
	static double deg2rad(double degree) {  
	    return degree / 180 * Math.PI;  
	}  
	//将弧度转换为角度  
	static double rad2deg(double radian) {  
	    return radian * 180 / Math.PI;  
	}  
	private static final int NOTIFY_ID = 0;
	private void showNotification(Store store) {
		notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		
		Intent intent = new Intent(this, FileExplorerActivity.class);
//		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
				intent, PendingIntent.FLAG_UPDATE_CURRENT);// FLAG_ONE_SHOT
		Notification notification = new Notification.Builder(context)
				.setTicker(context.getString(R.string.app_name, ""))
				.setContentTitle(store.getStoreName()+"("+store.getDistance()+")")
				.setContentText(store.getStoreAddress())
				.setContentIntent(pendingIntent)
				.setSmallIcon(R.drawable.ic_launcher)
				.setAutoCancel(true)
				.setWhen(System.currentTimeMillis())
				.setDefaults(Notification.DEFAULT_ALL)
				.getNotification();
		
		notificationManager.notify(NOTIFY_ID, notification);
	}
	
}

