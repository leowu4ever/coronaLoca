package com.uk.location.activity;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.WebView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class RecordWebView extends Activity {

    WebView webView;
    CookieManager cookieManager = CookieManager.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_record);
        String currentUser = getIntent().getStringExtra("USER_ID");
        String token = getIntent().getStringExtra("USER_TOKEN");
        String urlSuffix = getIntent().getStringExtra("URL_SUFFIX");
        String cookie = getIntent().getStringExtra("COOKIE");
        webView = (WebView)findViewById(R.id.activity_main_webview);
        CookieManager.getInstance().setAcceptCookie(true);
        String url="https://covid-19.dsi.ic.ac.uk/simple_webapp/survey/" + urlSuffix;
        String postData = null;

        try {
            postData = "boo=" + URLEncoder.encode("a", "UTF-8") + "&foo=" + URLEncoder.encode("a", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            postData = "";
        }
        webView.getSettings().setJavaScriptEnabled(true);
        cookieManager.setCookie("https://covid-19.dsi.ic.ac.uk", cookie);
        webView.postUrl(url,postData.getBytes());
    }
}
