package fontedebencao.com.radio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Site extends AppCompatActivity {
    private WebView mWebview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site);
        mWebview = findViewById(R.id.wv_site);

        WebSettings ws = mWebview.getSettings();
        ws.setJavaScriptEnabled(true);
        ws.setSupportZoom(false);
        mWebview.loadUrl("http://fontedebencao.com/");
        mWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }
        });
    }

    @Override
    public void onBackPressed(){
        if (mWebview.canGoBack()){
            mWebview.goBack();
        } else {
            finish();
        }
    }
}
