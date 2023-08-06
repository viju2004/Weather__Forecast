package com.example.weatherforecast;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
//import com.google.android.gms.location.FusedLocationProviderClient;
//import com.google.android.gms.location.LocationCallback;
//import com.google.android.gms.location.LocationResult;
//import com.google.android.gms.location.LocationServices;
//import com.google.android.gms.tasks.OnCompleteListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Date;

import pl.droidsonroids.gif.GifImageView;

public class Whether extends AppCompatActivity {
    ScrollView back;
    EditText citynm;
    GifImageView whether;
    TextView how, tempre, hum, winds, place, pres;
    LinearLayout today, nextday, nextafterday;
    TextView time1;

    TextView datetoday,datenext,dateafter;
    ImageView todayicon,nextdayicon,nextaftericon;
    String imgurld1,imgurld2,imgurld3;
    String imgd1,imgd2,imgd3;

    TextView todaytempmax,todaytempmin,nextdaytempmax,nextdaytempmin,afterdaytempmax,afterdaytempmin;
    int t;

    String ct;

//    setdaynight
    int hour;


    //For current Weather
    double lati, logi;
//    FusedLocationProviderClient flpc;

    private final String url = "https://api.openweathermap.org/data/2.5/weather";
    private final String appid = "e53301e27efa0b66d05045d91b2742d3";
    DecimalFormat df = new DecimalFormat("#.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whether);

        whether = findViewById(R.id.whimg);

        startanim();
        back = findViewById(R.id.back);
        citynm = findViewById(R.id.citynm);
        how = findViewById(R.id.how);
        tempre = findViewById(R.id.temp);
        hum = findViewById(R.id.hum);
        winds = findViewById(R.id.wind);
        place = findViewById(R.id.place);
        pres = findViewById(R.id.pres);
        today = findViewById(R.id.today);
        nextday = findViewById(R.id.nextday);
        nextafterday = findViewById(R.id.nextafterday);
        time1 = findViewById(R.id.time1);


        datetoday=findViewById(R.id.datetoday);
        datenext=findViewById(R.id.datenext);
        dateafter=findViewById(R.id.datenextafter);

        todayicon=findViewById(R.id.todayicon);
        nextdayicon=findViewById(R.id.nextdayicon);
        nextaftericon=findViewById(R.id.nextaftericon);

        todaytempmax=findViewById(R.id.todaytempmax);
        todaytempmin=findViewById(R.id.todaytempmin);
        nextdaytempmax=findViewById(R.id.nextdaytempmax);
        nextdaytempmin=findViewById(R.id.nextdaytempmin);
        afterdaytempmax=findViewById(R.id.afterdaytempmax);
        afterdaytempmin=findViewById(R.id.afterdaytempmin);






//        getlocation();
//     set greetings
        Date d = new Date();
        hour = d.getHours();
        setTime(hour);

//        setDayNight(n);


//        getdate for next days
            getdateofnextdays();
    }

    private void setDayNight(int c) {

//
        if (c >= 5&& c <19)
        {

//            Toast.makeText(this, "Good Morning", Toast.LENGTH_SHORT).show();
            if(t>=35)
            {
                whether.setImageResource(R.drawable.sunny);
                back.setBackgroundResource(R.drawable.sunnyx);

                citynm.setTextColor(Color.BLACK);
                citynm.setHintTextColor(Color.BLACK);
                tempre.setTextColor(Color.RED);
                hum.setTextColor(Color.BLACK);
                winds.setTextColor(Color.BLACK);
                place.setTextColor(Color.BLACK);
                how.setTextColor(Color.BLACK);
                pres.setTextColor(Color.BLACK);

            }


            else if(t<35 && t>10)
            {
                whether.setImageResource(R.drawable.cloudy);
                back.setBackgroundResource(R.drawable.cloudyx);
                time1.setTextColor(Color.rgb(27, 245, 27));
                citynm.setTextColor(Color.BLACK);
                citynm.setHintTextColor(Color.BLACK);
                tempre.setTextColor(Color.BLACK);
                hum.setTextColor(Color.rgb(96, 70, 227));
                winds.setTextColor(Color.rgb(96, 70, 227));
                place.setTextColor(Color.BLACK);
                how.setTextColor(Color.BLACK);
                pres.setTextColor(Color.rgb(96, 70, 227));

            }
            else if(t<10)
            {
                whether.setImageResource(R.drawable.rainynew);
                back.setBackgroundResource(R.drawable.nightx);

                citynm.setTextColor(Color.WHITE);
                citynm.setHintTextColor(Color.WHITE);
                tempre.setTextColor(Color.rgb(157, 115, 235));
                hum.setTextColor(Color.rgb(61, 245, 214));
                winds.setTextColor(Color.rgb(61, 245, 214));
                place.setTextColor(Color.rgb(245, 138, 61));
                how.setTextColor(Color.rgb(208, 232, 86));
                pres.setTextColor(Color.rgb(61, 245, 214));

                datetoday.setTextColor(Color.YELLOW);
                datenext.setTextColor(Color.YELLOW);
                dateafter.setTextColor(Color.YELLOW);

            }

        }
        else
        {
                whether.setImageResource(R.drawable.nigh);
                back.setBackgroundResource(R.drawable.nighttime);

                citynm.setTextColor(Color.WHITE);
                citynm.setHintTextColor(Color.WHITE);
                tempre.setTextColor(Color.rgb(157, 115, 235));
                hum.setTextColor(Color.rgb(61, 245, 214));
                winds.setTextColor(Color.rgb(61, 245, 214));
                place.setTextColor(Color.rgb(245, 138, 61));
                how.setTextColor(Color.rgb(208, 232, 86));
                pres.setTextColor(Color.rgb(61, 245, 214));

                datetoday.setTextColor(Color.YELLOW);
                datenext.setTextColor(Color.YELLOW);
                dateafter.setTextColor(Color.YELLOW);




        }

    }

    protected void onResume(){
        super.onResume();
//        startanim();

    }

    private void setTime(int c)
    {


        if (c >= 1 && c <= 11)
        {
            time1.setText("Good Morning");


        }
        else if (c >= 12 && c <= 15)
        {
            time1.setText("Good Afternoon");


        }
        else if (c >= 16 && c <= 20)
        {
            time1.setText("Good Evening");

        }
        else if (c >= 21 && c <= 24)
        {
            time1.setText("Good Night");

        }
    }
    private void startanim() {

    whether.setImageResource(R.drawable.startnew);

    }

    public void getwhether(View view) {

        getweathernextdays();
        String tempUrl = "";
        String city = citynm.getText().toString().trim();
//        String country = etCountry.getText().toString().trim();
        if(city.equals("")){
//            tvResult.setText("City field can not be empty!");

        }

        else{
//            if(!country.equals("")){
//                tempUrl = url + "?q=" + city + "," + country + "&appid=" + appid;
//            }else{
                tempUrl = url + "?q=" + city + "&appid=" + appid;
//            }
            StringRequest stringRequest = new StringRequest(Request.Method.POST, tempUrl, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    String output = "";
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        JSONArray jsonArray = jsonResponse.getJSONArray("weather");
                        JSONObject jsonObjectWeather = jsonArray.getJSONObject(0);
                        String description = jsonObjectWeather.getString("description");
                        JSONObject jsonObjectMain = jsonResponse.getJSONObject("main");
                        double temp = jsonObjectMain.getDouble("temp") - 273.15;
                        double feelsLike = jsonObjectMain.getDouble("feels_like") - 273.15;
                        float pressure = jsonObjectMain.getInt("pressure");
                        int humidity = jsonObjectMain.getInt("humidity");
                        JSONObject jsonObjectWind = jsonResponse.getJSONObject("wind");
                        String wind = jsonObjectWind.getString("speed");
                        JSONObject jsonObjectClouds = jsonResponse.getJSONObject("clouds");
                        String clouds = jsonObjectClouds.getString("all");
                        JSONObject jsonObjectSys = jsonResponse.getJSONObject("sys");
                        String countryName = jsonObjectSys.getString("country");
                        String cityName = jsonResponse.getString("name");
                        int windsp=jsonObjectWind.getInt("speed");

                        output += "Current weather of " + cityName + " (" + countryName + ")"
                                + "\n Temp: " + df.format(temp) + " °C"
                                + "\n Feels Like: " + df.format(feelsLike) + " °C"
                                + "\n Humidity: " + humidity + "%"
                                + "\n Description: " + description
                                + "\n Wind Speed: " + wind + "m/s (meters per second)"
                                + "\n Cloudiness: " + clouds + "%"
                                + "\n Pressure: " + pressure + " hPa";

                        float newtemp=(float)temp;
                        t=(int)newtemp;
                        int pressurenew=(int)pressure;
                        setDayNight(hour);
//                        int windmps=Integer.parseInt(wind);
                        float windkph= (float) (windsp*3.6);
                        int windkphn=(int)windkph;

//                        String des=description;
                        String des = description.substring(0, 1).toUpperCase() + description.substring(1);
                        tempre.setText(" "+t+"°C ");
                        hum.setText(" Hum: "+humidity+"% ");
                        winds.setText(" Wind: "+windkphn+" km/h ");
                        how.setText(" "+des+" ");
                        place.setText(" "+cityName+" ("+countryName+") ");
                        pres.setText(" Pressure: "+pressurenew+" hPa ");


//                        today.setText(""+t+"°");
                        if(t>=35)
                        {
//                            citynm.setTextColor(Color.BLACK);
//                            citynm.setHintTextColor(Color.BLACK);
//
//                            tempre.setTextColor(Color.RED);
//                            hum.setTextColor(Color.BLACK);
//                            winds.setTextColor(Color.BLACK);
//                            place.setTextColor(Color.BLACK);
//                            how.setTextColor(Color.BLACK);
//                            pres.setTextColor(Color.BLACK);
//                            whether.setImageResource(R.drawable.sunny);
//                            back.setBackgroundResource(R.drawable.sunnyx);

                        }
                        else if(t<35 && t>10){
//                            citynm.setTextColor(Color.BLACK);
//                            citynm.setHintTextColor(Color.BLACK);
////                            whether.setImageResource(R.drawable.cloudy);
//                            tempre.setTextColor(Color.BLACK);
//                            hum.setTextColor(Color.BLACK);
//                            winds.setTextColor(Color.BLACK);
//                            place.setTextColor(Color.BLACK);
//                            how.setTextColor(Color.BLACK);
//                            pres.setTextColor(Color.BLACK);
//                            back.setBackgroundResource(R.drawable.cloudyx);

                        }
                        else if(t<10)
                        {
//                            citynm.setTextColor(Color.WHITE);
//                            citynm.setHintTextColor(Color.WHITE);
//                            tempre.setTextColor(Color.WHITE);
//                            hum.setTextColor(Color.WHITE);
//                            winds.setTextColor(Color.WHITE);
//                            place.setTextColor(Color.WHITE);
//                            how.setTextColor(Color.WHITE);
//                            pres.setTextColor(Color.WHITE);
//                            whether.setImageResource(R.drawable.rainynew);
//                            back.setBackgroundResource(R.drawable.nightx);

                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener(){

                @Override
                public void onErrorResponse(VolleyError error) {
//                    if(error.toString().equals(""))
//                    Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
//                    Log.d("inet",""+error.toString().trim());
                    ConnectivityManager conMgr =  (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
                    if (netInfo == null){
                        Toast.makeText(Whether.this, "No Internet Connection ", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(Whether.this, "Enter Valid City Name !", Toast.LENGTH_SHORT).show();

                    }
                }
            });
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }

    }
    public void getdateofnextdays() {
        Date d= new Date();
        int date=d.getDate();
        int day=d.getDay();
        int month=d.getMonth();
        String days[]={"Sun","Mon","Tue","Wed","Thu","Fri","Sat"};
        String months[]={"Jan","Feb","Mar","Apr","May","June","July","Sep","Oct","Nov","Dec"};

        Log.d("date12", String.valueOf(date+":"+day+":"+month));
        datetoday.setText("Today, "+date+" "+months[month]);
        if(day==6){
            int dnext=0;
            datenext.setText(days[dnext]+", "+(date+1)+" "+months[month]);

        }
        else
        {
            datenext.setText(days[day+1]+", "+(date+1)+" "+months[month]);

        }

        if(day>=5)
        {
            int dafter=0;
            if(day==5)
            {
                dafter=0;
            }
            else if(day==6)
            {
            dafter=1;
            }

            dateafter.setText(days[dafter]+", "+(date+2)+" "+months[month]);
        }

        else
        {
            dateafter.setText(days[day+2]+", "+(date+2)+" "+months[month]);

        }

    }
    public void getweathernextdays()
    {
        ct=citynm.getText().toString();
        if(ct.equals("karad") || ct.equals("Karad")){
            ct="karad,IN";
        }
        if(ct.equals("miraj") || ct.equals("Miraj")){
            ct="miraj,IN";
        }
//        Toast.makeText(this, "getweather", Toast.LENGTH_SHORT).show();
        RequestQueue requestQueue;
        requestQueue =Volley.newRequestQueue(this);
//        String tempUrl="https://api.openweathermap.org/data/2.5/forecast?q="+ct+"&appid=7b92b5046bd534f7f6c370d8de48329e";
          String tempUrl="https://api.weatherbit.io/v2.0/forecast/daily?city="+ct+"&key=c4b3041887a548eebcaf412edd115478&days=3";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, tempUrl, new Response.Listener<String>() {




            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onResponse(String response) {
                Log.d("resgg", String.valueOf(response));
                try {
//                    tv.setText(""+response);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                JSONArray jsonArray = null;
                try {
//   old
//                    JSONObject jsonResponse = new JSONObject(String.valueOf(response));
//
//                    JSONArray list = jsonResponse.getJSONArray("list");
//
//                    //today
//                    JSONObject in=list.getJSONObject(0);
//
//                    JSONObject main=in.getJSONObject("main");
//                    int temp = (int) (main.getInt("temp") - 272.50);
//                    int tempmax = (int)(main.getInt("temp_max")-272);
//                    int tempmin = (int)(main.getInt("temp_min")-272);


//    new
                    JSONObject jsonResponse = new JSONObject(response);
                    JSONArray d = jsonResponse.getJSONArray("data");

                    JSONObject data = d.getJSONObject(0);
                     int tempmax = data.getInt("high_temp");
                     int tempmin = data.getInt("low_temp");



//                    Log.d("hight",hightemp+" : "+lowtemp);


                    JSONObject foric = data.getJSONObject("weather");
                    String icon = foric.getString("icon");

                    if(tempmax<tempmin)
                    {
                        todaytempmax.setText("Max:"+tempmin+"°C");
                        todaytempmin.setText("Min:"+tempmax+"°C");
                    }
                    else{
                        todaytempmax.setText("Max:"+tempmax+"°C");
                        todaytempmin.setText("Min:"+tempmin+"°C");
                    }
                    if(tempmin>t)
                    {
                        todaytempmin.setText("Min:"+t+"°C");
                    }
                    if(tempmax<t)
                    {
                        todaytempmax.setText("Max:"+t+"°C");

                    }



//old
//                    JSONArray ic=in.getJSONArray("weather");
//                    JSONObject in2=ic.getJSONObject(0);
                    JSONObject ic1 = data.getJSONObject("weather");
                    imgurld1=ic1.getString("icon");

//                    imgd1="https://openweathermap.org/img/wn/"+imgurl+"@2x.png";

                    //nextday

//                    JSONObject next=list.getJSONObject(1);
//                    JSONObject mainnext=next.getJSONObject("main");
//                    int tempnext = (int) (mainnext.getInt("temp") - 272.50);
//                    int tempmaxnxt = (int)(mainnext.getInt("temp_max")-272);
//                    int tempminnxt = (int)(main.getInt("temp_min")-272);

                    JSONObject next = new JSONObject(response);
                    JSONArray nextd = next.getJSONArray("data");

                    JSONObject ndata = nextd.getJSONObject(1);
                    int tempmaxnxt = ndata.getInt("high_temp");
                    int tempminnxt = ndata.getInt("low_temp");

                    if(tempminnxt>tempmaxnxt)
                    {
                        nextdaytempmax.setText("Max:"+tempminnxt+"°C");
                        nextdaytempmin.setText("Min:"+tempmaxnxt+"°C");
                    }
                    else{
                        nextdaytempmax.setText("Max:"+tempmaxnxt+"°C");
                        nextdaytempmin.setText("Min:"+tempminnxt+"°C");
                    }


//
//                    JSONArray icnext=next.getJSONArray("weather");
//                    JSONObject in21=icnext.getJSONObject(0);
                    JSONObject ic2 = ndata.getJSONObject("weather");
                    imgurld2=ic2.getString("icon");

                    //nextafterday
//
//                    JSONObject nextaft=list.getJSONObject(2);
//                    JSONObject mainnextaft=nextaft.getJSONObject("main");
//                    int tempnextaft = (int) (mainnextaft.getInt("temp") - 272.50);
//                    int tempmaxaft = (int)(mainnext.getInt("temp_max")-272);
//                    int tempminaft = (int)(main.getInt("temp_min")-272);

                    JSONObject after = new JSONObject(response);
                    JSONArray aftd = after.getJSONArray("data");

                    JSONObject aftdata = aftd.getJSONObject(0);
                    int tempmaxaft = aftdata.getInt("high_temp");
                    int tempminaft = ndata.getInt("low_temp");

                    if (tempmaxaft<tempminaft)
                    {
                        afterdaytempmax.setText("Max:"+tempminaft+"°C");
                        afterdaytempmin.setText("Min:"+tempmaxaft+"°C");
                    }
                    else
                    {
                        afterdaytempmax.setText("Max:"+tempmaxaft+"°C");
                        afterdaytempmin.setText("Min:"+tempminaft+"°C");
                    }




////                    JSONArray icnextaft=nextaft.getJSONArray("weather");
////                    JSONObject in21aft=icnextaft.getJSONObject(0);
                    JSONObject ic3 = aftdata.getJSONObject("weather");
                    imgurld3=ic3.getString("icon");
//                    imgurld3=in21aft.getString("icon");
//
//                    imgd2="https://openweathermap.org/img/wn/"+imgurl+"@2x.png";





//                    Log.d("temp223", String.valueOf(temp)+":::"+String.valueOf(tempmax));
//                    Log.d("temp223", String.valueOf(tempnext)+":::"+String.valueOf(tempmax1));

                    Log.d("im223", String.valueOf(imgurld1+";;;"+imgurld2));


//                    tv.setText(""+temp);


                    new DownloadImageFromInternet((ImageView) findViewById(R.id.todayicon)).execute("https://www.weatherbit.io/static/img/icons/"+imgurld1+".png");

                    new DownloadImageFromInternetday2((ImageView) findViewById(R.id.nextdayicon )).execute("https://www.weatherbit.io/static/img/icons/"+imgurld2+".png");
                    new DownloadImageFromInternetday3((ImageView) findViewById(R.id.nextaftericon )).execute("https://www.weatherbit.io/static/img/icons/"+imgurld3+".png");


                } catch (JSONException e ) {
                    e.printStackTrace();
                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(Whether.this, "Error occured", Toast.LENGTH_SHORT).show();
                ConnectivityManager conMgr =  (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
                if (netInfo == null){
                    Toast.makeText(Whether.this, "No Internet Connection ", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Whether.this, "Enter Valid City Name !", Toast.LENGTH_SHORT).show();

                }


            }
        });

        requestQueue.add(stringRequest);

    }



    private class DownloadImageFromInternet extends AsyncTask<String, Void, Bitmap> {
        ImageView td;
        public DownloadImageFromInternet(ImageView tod) {
            this.td=tod;
//            Toast.makeText(getApplicationContext(), "Please wait, it may take a few minute...",Toast.LENGTH_SHORT).show();
        }
        protected Bitmap doInBackground(String... urls) {
            String imageURL=urls[0];
            Bitmap bimage=null;

            try {
                InputStream in=new java.net.URL(imageURL).openStream();
                bimage= BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("em123", e.getMessage());
                e.printStackTrace();
            }
            return bimage;
        }
        protected void onPostExecute(Bitmap result) {
            td.setImageBitmap(result);
        }

    }
    private class DownloadImageFromInternetday2 extends AsyncTask<String, Void, Bitmap> {
        ImageView td;
        public DownloadImageFromInternetday2(ImageView tod) {
            this.td=tod;
//            Toast.makeText(getApplicationContext(), "Please wait, it may take a few minute...",Toast.LENGTH_SHORT).show();
        }
        protected Bitmap doInBackground(String... urls) {
            String imageURL=urls[0];
            Bitmap bimage=null;

            try {
                InputStream in=new java.net.URL(imageURL).openStream();
                bimage= BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("em123", e.getMessage());
                e.printStackTrace();
            }
            return bimage;
        }
        protected void onPostExecute(Bitmap result) {
            td.setImageBitmap(result);
        }
    }
    private class DownloadImageFromInternetday3 extends AsyncTask<String, Void, Bitmap> {
        ImageView td;
        public DownloadImageFromInternetday3(ImageView tod) {
            this.td=tod;
//            Toast.makeText(getApplicationContext(), "Please wait, it may take a few minute...",Toast.LENGTH_SHORT).show();
        }
        protected Bitmap doInBackground(String... urls) {
            String imageURL=urls[0];
            Bitmap bimage=null;

            try {
                InputStream in=new java.net.URL(imageURL).openStream();
                bimage= BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("em123", e.getMessage());
                e.printStackTrace();
            }
            return bimage;
        }
        protected void onPostExecute(Bitmap result) {
            td.setImageBitmap(result);
        }
    }
    public void showtodaylink(View view) {
        if(ct=="null")
        {
            Toast.makeText(this, "First enter city name !", Toast.LENGTH_SHORT).show();


        }
        else {
            Intent i = new Intent(Whether.this, web.class);
            i.putExtra("city", ct);
            startActivity(i);

        }
    }
}