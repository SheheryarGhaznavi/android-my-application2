package com.example.sherry.myapplication;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    static TextView t;
    private int LoopCurrentIP = 0;
    static String ad ,a;
    RequestQueue r;
    ListView nlist;
    String[] ab;
    final ArrayList<String> arrayList = new ArrayList<String>();
    String url ="http://pastebin.com/raw/Em972E5s/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] n = {"a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c", "a", "b", "c"};

        nlist = (ListView) findViewById(R.id.lisst);
          t = (TextView) findViewById(R.id.tt);
        r = Volley.newRequestQueue(this);
      //  main();
        Log.i("hhhh","bvbvbvbvb");
        jj();
        ListAdapter nd = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        nlist.setAdapter(nd);



    }
    public void jjj()
    {
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        t.setText("Response is: "+ response.substring(0,500));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                t.setText("That didn't work!");
            }
        });
// Add the request to the RequestQueue.
        r.add(stringRequest);
    }
    public void jj()
    {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for(int i=0;i<response.length();i++){
                                // Get current json object
                                JSONObject student = (JSONObject) response.get(i);

                                // Get the current student (json object) data
                               String ccc = student.getString("firstname");
                                a = student.getString("lastname");
                                String age = student.getString("age");

                                // Display the formatted json data in text view
                                arrayList.add(ccc);
//                               t.append(ccc +" " + a +"\nAge : " + age);
  //                              t.append("\n\n");
                            }
                                // Get current json object


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        t.setText(error.getMessage());
                    }
                }
        );
        r.add(jsonArrayRequest);
    }


    public static void main(){
        t.setText("new");

        InetAddress ip;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            ip = InetAddress.getLocalHost();
            t.setText(ip.getHostAddress());
            a=ip.getHostAddress();
        }
        catch(Exception e){}

//        try {
//
//            ip = InetAddress.getLocalHost();
//            System.out.println("Current IP address : " + ip.getHostAddress());
//
//            NetworkInterface network = NetworkInterface.getByInetAddress(ip);
//
//            byte[] mac = network.getHardwareAddress();
//
//            System.out.print("Current MAC address : ");
//
//            StringBuilder sb = new StringBuilder();
//            for (int i = 0; i < mac.length; i++) {
//                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
//            }
//            System.out.println(sb.toString());
//
//        } catch (UnknownHostException e) {
//
//            e.printStackTrace();
//
//        } catch (SocketException e){
//
//            e.printStackTrace();
//
//        }

}

}
