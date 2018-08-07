package com.hw.hellowash;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.icu.util.Calendar;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.hw.hellowash.Custom.CustomTypefaceSpan;
import com.hw.hellowash.country.AppLocationService;
import com.hw.hellowash.country.LocationAddress;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class Utilities {
    public static final SimpleDateFormat SIMPLE_DATE_FORMAT_SERVER = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");//yyyy-MM-dd'T'HH:mm:ss
    public static final SimpleDateFormat SIMPLE_DATE_FORMAT1 = new SimpleDateFormat("MMM dd, yyyy hh:mm aa");//MMM dd, yyyy hh:mm:ss aa
    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("MMM dd, yyyy  HH:mm");//MMM dd, yyyy hh:mm:ss aa
    public static final SimpleDateFormat CUSTOM_DATE_FORMAT = new SimpleDateFormat("MM/dd");
    public static final SimpleDateFormat CUSTOM_TIME_FORMAT = new SimpleDateFormat("hh:mm aa");
    public static int screenWidth, screenHeight;

    public static boolean home_Adddress, work_Address, other_Address;
    private static AppLocationService appLocationService;


    public static void showAlert(final Context mContext, String message) {
        final AlertDialog.Builder alert_Dialog = new AlertDialog.Builder(mContext);
        alert_Dialog.setCancelable(false);
        alert_Dialog.setTitle("Alert");
        alert_Dialog.setMessage(message);
        alert_Dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                ((AppCompatActivity)mContext).finish();
            }
        });

        alert_Dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alert_Dialog.show();
    }

    public static void hideKeyboard(View v) {
        InputMethodManager inputManager = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm.getActiveNetworkInfo() != null
                && cm.getActiveNetworkInfo().isFailover())
            return false;
        else if (cm.getActiveNetworkInfo() != null
                && cm.getActiveNetworkInfo().isAvailable()
                && cm.getActiveNetworkInfo().isConnected())
            return true;
        else
            return false;
    }

    public static void saveLoginPref(Context context, String key, String value) {
        SharedPreferences sharedPref = context.getSharedPreferences("Login_Preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getLoginPref(Context context, String key, String defaultValue) {
        SharedPreferences sharedPref = context.getSharedPreferences("Login_Preferences", Context.MODE_PRIVATE);
        return sharedPref.getString(key, defaultValue);
    }

    public static void saveLoginbooleanPref(Context context, String key, boolean value) {
        SharedPreferences sharedPref = context.getSharedPreferences("Login_Preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static Boolean getLoginBooleanPref(Context context, String key, boolean value) {
        SharedPreferences sharedPref = context.getSharedPreferences("Login_Preferences", Context.MODE_PRIVATE);
        return sharedPref.getBoolean(key, value);
    }

    public static void clearLoginPreferences(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences("Login_Preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear();
        editor.commit();
    }

    public static void savebooleanPref(Context context, String key, boolean value) {
        SharedPreferences sharedPref = context.getSharedPreferences("Login_Preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static boolean getBooleanPref(Context context, String key, boolean defaultValue) {
        SharedPreferences sharedPref = context.getSharedPreferences("Login_Preferences", Context.MODE_PRIVATE);
        return sharedPref.getBoolean(key, defaultValue);
    }

    public static void savePref(Context context, String key, String value) {
        SharedPreferences sharedPref = context.getSharedPreferences("MobileApp_Preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getPref(Context context, String key, String defaultValue) {
        SharedPreferences sharedPref = context.getSharedPreferences("MobileApp_Preferences", Context.MODE_PRIVATE);
        return sharedPref.getString(key, defaultValue);
    }

    public static void saveIntegerPref(Context context, String key, int value) {
        SharedPreferences sharedPref = context.getSharedPreferences("MobileApp_Preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public static int getIntegerPref(Context context, String key, int defaultValue) {
        SharedPreferences sharedPref = context.getSharedPreferences("MobileApp_Preferences", Context.MODE_PRIVATE);
        return sharedPref.getInt(key, defaultValue);
    }

    public static void removePreferencesValue(Context context, String key) {
        SharedPreferences sharedPref = context.getSharedPreferences("MobileApp_Preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.remove(key);
        editor.apply();
        editor.commit();
    }


    public static void clearPreferences(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences("MobileApp_Preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear();
        editor.apply();
        editor.commit();
    }

    public static void saveFCM_TOKEN(Context context, String key, String value) {
        SharedPreferences sharedPref = context.getSharedPreferences("FCM_TOKEN_Preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.apply();
        editor.commit();
    }

    public static String getFCM_TOKEN(Context context, String key, String defaultValue) {
        SharedPreferences sharedPref = context.getSharedPreferences("FCM_TOKEN_Preferences", Context.MODE_PRIVATE);
        return sharedPref.getString(key, defaultValue);
    }

    public static void showToast(Context mContext, String mesg) {
        Toast.makeText(mContext, mesg, Toast.LENGTH_SHORT).show();
    }

    public static String getConvertedDate(String date) {
        try {
            //SIMPLE_DATE_FORMAT_SERVER.setTimeZone(TimeZone.getTimeZone("UTC"));
            return SIMPLE_DATE_FORMAT.format(SIMPLE_DATE_FORMAT_SERVER.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
            return date;
        }
    }

    public static String getConvertedDateForChat(String date) {
        try {
            //SIMPLE_DATE_FORMAT_SERVER.setTimeZone(TimeZone.getTimeZone("UTC"));
            return SIMPLE_DATE_FORMAT1.format(SIMPLE_DATE_FORMAT_SERVER.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
            return date;
        }
    }

    public static String milliSecondsToTimer(long milliseconds) {
        String finalTimerString = "";
        String secondsString = "";

        // Convert total duration into time
        int hours = (int) (milliseconds / (1000 * 60 * 60));
        int minutes = (int) (milliseconds % (1000 * 60 * 60)) / (1000 * 60);
        int seconds = (int) ((milliseconds % (1000 * 60 * 60)) % (1000 * 60) / 1000);
        // Add hours if there
        if (hours > 0) {
            finalTimerString = hours + ":";
        }

        // Prepending 0 to seconds if it is one digit
        if (seconds < 10) {
            secondsString = "0" + seconds;
        } else {
            secondsString = "" + seconds;
        }

        finalTimerString = finalTimerString + minutes + ":" + secondsString;

        // return timer string
        return finalTimerString;
    }

    /**
     * Function to get Progress percentage
     *
     * @param currentDuration
     * @param totalDuration
     */
    public static int getProgressPercentage(long currentDuration, long totalDuration) {
        Double percentage = (double) 0;

        long currentSeconds = (int) (currentDuration / 1000);
        long totalSeconds = (int) (totalDuration / 1000);

        // calculating percentage
        percentage = (((double) currentSeconds) / totalSeconds) * 100;

        // return percentage
        return percentage.intValue();
    }

    /**
     * Function to change progress to timer
     *
     * @param progress      -
     * @param totalDuration returns current duration in milliseconds
     */
    public static int progressToTimer(int progress, int totalDuration) {
        int currentDuration = 0;
        totalDuration = (int) (totalDuration / 1000);
        currentDuration = (int) ((((double) progress) / 100) * totalDuration);

        // return current duration in milliseconds
        return currentDuration * 1000;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static String getDate(long milliSeconds) {
        SimpleDateFormat formatter = new SimpleDateFormat(
                "dd/MM/yyyy hh:mm:ss a");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

    public static Bitmap StringToBitMap(String encodedString) {
        Bitmap bitmap = null;
        try {
            byte[] encodeByte = Base64.decode(encodedString.getBytes(), Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return bitmap;

        }
    }

    public static String getcustomDateFormat(String date) {
        try {
            //SIMPLE_DATE_FORMAT_SERVER.setTimeZone(TimeZone.getTimeZone("UTC"));
            return CUSTOM_DATE_FORMAT.format(SIMPLE_DATE_FORMAT_SERVER.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
            return date;
        }
    }

    /**
     * function md5 encryption for passwords
     *
     * @return passwordEncrypted
     */
    public static String convertPassMd5(String pass) {
        String password = null;
        MessageDigest mdEnc;
        try {
            mdEnc = MessageDigest.getInstance("MD5");
            mdEnc.update(pass.getBytes(), 0, pass.length());
            pass = new BigInteger(1, mdEnc.digest()).toString(16);
            while (pass.length() < 32) {
                pass = "0" + pass;
            }
            password = pass;
        } catch (NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        }
        return password;
    }

    public static String getCurrentVersion(Context mContext) {
        String version = "";
        try {
            version = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return version;
    }

    public static int getStatusBarHeight(Context mContext) {
        int result = 0;
        int resourceId = mContext.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = mContext.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static void applyFontToMenuItem(MenuItem mi, Context mContext) {
        Typeface font = Typeface.createFromAsset(mContext.getAssets(), "fonts/quicksand_bold.ttf");
        SpannableString mNewTitle = new SpannableString(mi.getTitle());
        mNewTitle.setSpan(new CustomTypefaceSpan("" , font), 0 , mNewTitle.length(),  Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        mi.setTitle(mNewTitle);
    }

    public static String getPath(Uri uri, String type,Context mContext) {
        String document_id = null;
        Cursor cursor = mContext.getContentResolver().query(uri, null, null, null, null);
        if (cursor.moveToFirst()) {
            document_id = cursor.getString(0);
            document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        }

        String path = "";
        if (type.equalsIgnoreCase("Photo")) {

            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            cursor = mContext.getContentResolver().query(uri,
                    filePathColumn, null, null, null);

            /*cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);*/
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
                cursor.close();
            }
        } else {
            cursor = mContext.getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                    null, MediaStore.Video.Media._ID + " = ? ", new String[]{document_id}, null);
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA));
                cursor.close();
            }
        }

        if (path.equalsIgnoreCase("")) {
            path = document_id;
        }

        return path;
    }


    @TargetApi(Build.VERSION_CODES.N)
    public static boolean isWithingSH(String time) {
        boolean timeWithinRestrictions = false;
        try {
            String startTime = "09:30:00 AM";
            Date time1 = new SimpleDateFormat("HH:mm:ss a").parse(startTime);
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(time1);

            String endTime = "18:30:00 PM";
            Date time2 = new SimpleDateFormat("HH:mm:ss a").parse(endTime);
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(time2);

            Date curr = new SimpleDateFormat("HH:mm:ss").parse(time);
            Calendar calendar3 = Calendar.getInstance();
            calendar3.setTime(curr);

            Date currentTime = calendar3.getTime();
            if (currentTime.after(calendar1.getTime()) && currentTime.before(calendar2.getTime())) {
                //checkes whether the current time is between 14:49:00 and 20:11:13.
                timeWithinRestrictions = true;
            }else{
                timeWithinRestrictions = false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timeWithinRestrictions;
    }

    public static String getAddress(Context mContext){
        String result = null;
        double latitude, longitude;
        LocationAddress locationAddress;
        appLocationService = new AppLocationService(mContext);

        Location gpslocation = appLocationService
                .getLocation(LocationManager.GPS_PROVIDER);
        Location networkLocation = appLocationService
                .getLocation(LocationManager.NETWORK_PROVIDER);

        if (networkLocation != null) {

            latitude = networkLocation.getLatitude();
            longitude = networkLocation.getLongitude();

            result = getAddressFromGeoPoints(mContext, latitude, longitude);

        } else if (gpslocation != null) {

            try {
                latitude = gpslocation.getLatitude();
                longitude = gpslocation.getLongitude();
                result = getAddressFromGeoPoints(mContext, latitude, longitude);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            showSettingsAlert(mContext);
        }
    return result;
    }


    public static String getAddressFromGeoPoints(Context mContext, double latitude, double longitude){
        String result = "";
        Geocoder gcd = new Geocoder(mContext, Locale.getDefault());
        try {

            List<Address> addressList = gcd.getFromLocation(
                    latitude, longitude, 1);
            if (addressList != null && addressList.size() > 0) {
                Address address = addressList.get(0);
                StringBuilder sb = new StringBuilder();

                sb.append(address.getAddressLine(0)).append("\n");
                if(address.getAddressLine(1)!=null) {
                    sb.append(address.getAddressLine(1)).append(",\n");
                    if(address.getAddressLine(2)!=null) {
                        sb.append(address.getAddressLine(2)).append(",\n");
                        if(address.getAddressLine(3)!=null) {
                            sb.append(address.getAddressLine(3)).append(".");
                        }
                    }
                }
                result = sb.toString();

            }
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }
        return result;
    }

    private static void showSettingsAlert(Context mContext) {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
        alertDialog.setTitle("SETTINGS");
        alertDialog.setMessage("Enable Location Provider! Go to settings menu?");
        alertDialog.setPositiveButton("Settings",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        mContext.startActivity(intent);
                    }
                });
        alertDialog.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        alertDialog.show();
    }

    public static String getFormatedMonthDate(Date dateString) {
        SimpleDateFormat format = new SimpleDateFormat("d");
        String date = format.format(dateString);

        if (date.endsWith("1") && !date.endsWith("11"))
            format = new SimpleDateFormat("MMM d'st'");
        else if (date.endsWith("2") && !date.endsWith("12"))
            format = new SimpleDateFormat("MMM d'nd'");
        else if (date.endsWith("3") && !date.endsWith("13"))
            format = new SimpleDateFormat("MMM d'rd'");
        else
            format = new SimpleDateFormat("MMM d'th'");

        return format.format(dateString);
    }
}
