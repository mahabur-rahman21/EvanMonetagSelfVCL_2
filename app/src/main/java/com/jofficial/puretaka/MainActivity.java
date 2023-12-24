package com.jofficial.puretaka;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.appbar.MaterialToolbar;

import org.json.JSONException;
import org.json.JSONObject;

import co.notix.banner.BannerRequest;
import co.notix.banner.BannerSize;
import co.notix.banner.NotixBannerView;
import co.notix.interstitial.InterstitialLoader;
import co.notix.interstitial.NotixInterstitial;
import kotlin.Unit;

public class MainActivity extends AppCompatActivity {

    Button showBtn, lodBtn;
    MaterialToolbar topAppBar;

    TextView tvCountry, tvRegion, tvIP;

    static InterstitialLoader interstitialLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showBtn = findViewById(R.id.showBtn);
        lodBtn = findViewById(R.id.lodBtn);
        topAppBar = findViewById(R.id.topAppBar);

        tvCountry = findViewById(R.id.tvCountry);
        tvRegion = findViewById(R.id.tvRegion);
        tvIP = findViewById(R.id.tvIP);


        startLoading();

        loadIP();


        showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                interstitialLoader.doOnNextAvailable(result -> {
                    if (result != null) {
                        NotixInterstitial.Companion.show(result);
                    }
                    return Unit.INSTANCE;
                });


            }
        });

        lodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startLoading();
            }
        });


        topAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if (item.getItemId() == R.id.developer) {
                    String url = "https://t.me/VIRTUAL_CYBER_LAB";
                    Intent intent_developer = new Intent(Intent.ACTION_VIEW);
                    intent_developer.setData(Uri.parse(url));
                    startActivity(intent_developer);
                }

                return true;
            }
        });


    }

    private void loadIP() {


        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://ipinfo.io/?token=24523170e222c2";

        JsonObjectRequest  jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    tvCountry.setText("Country: "+response.getString("country"));
                    tvRegion.setText("Region: "+response.getString("region"));
                    tvIP.setText("IP Address: "+response.getString("ip"));



                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(jsonObjectRequest);

    }

    private void startLoading() {

        int appID=Integer.parseInt(getResources().getString(R.string.app_id));
        interstitialLoader = NotixInterstitial.Companion.createLoader(appID);
        interstitialLoader.startLoading();
    }
}