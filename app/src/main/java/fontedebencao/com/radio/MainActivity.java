package fontedebencao.com.radio;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;

import android.view.View;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;


public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Button btEnviar;
    private Drawer result = null;
    private Intent it;
    private Uri uri;
    private WebView webView;
    private static final String URL = "http://stream.locaweb.com.br/embed/?s=26329920857&u=//server-1.locaaovivo.com.br/&m=0";
    public static String FACEBOOK_URL = "https://www.facebook.com/igrejaevangelicafontedebencao";
    public static String FACEBOOK_PAGE_ID = "igrejaevangelicafontedebencao";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        webView = findViewById(R.id.ma_web_player);
        btEnviar = findViewById(R.id.ma_bt_pedido);

        btEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                it = new Intent(MainActivity.this, PedidoOracao.class);
                startActivity(it);
            }
        });

        WebSettings ws = webView.getSettings();
        ws.setJavaScriptEnabled(true);
        ws.setSupportZoom(false);
        setDesktopMode(webView, true);

        webView.loadUrl(URL);

        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withSelectionListEnabled(false)
                .withHeaderBackground(R.drawable.fundo)
                .withTextColor(getResources().getColor(R.color.grayA200))
                .addProfiles(
                        new ProfileDrawerItem()
                                .withName(R.string.nomeigreja)
                                .withEmail(R.string.foneigreja)
                                .withIcon(getResources().getDrawable(R.mipmap.ic_launcher_round))
                )
                .build();

        result = new DrawerBuilder()
                .withActivity(this)
                .withTranslucentStatusBar(false)
                .withToolbar(toolbar)
                .withAccountHeader(headerResult)
                .withSavedInstance(savedInstanceState)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.inicio)
                                .withIdentifier(0)
                                .withIcon(FontAwesome.Icon.faw_home),

                        new PrimaryDrawerItem().withName(R.string.pedidomenu)
                                .withIdentifier(1)
                                .withIcon(FontAwesome.Icon.faw_envelope),

                        new DividerDrawerItem(),

                        new PrimaryDrawerItem().withName(R.string.acompanhe)
                                .withIdentifier(2),

                        new SecondaryDrawerItem()
                                .withName("FACEBOOK")
                                .withIdentifier(3).withIcon(
                                FontAwesome.Icon.faw_facebook),

                        new SecondaryDrawerItem()
                                .withName("INSTAGRAM")
                                .withIdentifier(4)
                                .withIcon(FontAwesome.Icon.faw_instagram),

                        new SecondaryDrawerItem()
                                .withName(R.string.sitemenu)
                                .withIdentifier(5)
                                .withIcon(getResources().getDrawable(R.drawable.ic_action_site)),

                        new DividerDrawerItem(),

                        new PrimaryDrawerItem().withName(R.string.sobremenu)
                                .withIdentifier(6)
                                .withIcon(FontAwesome.Icon.faw_info_circle)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

                        switch ((int) drawerItem.getIdentifier()) {
                            case 1:
                                it = new Intent(MainActivity.this, PedidoOracao.class);
                                startActivity(it);
                                break;
                            case 3:
                                Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                                String facebookUrl = getFacebookPageURL(MainActivity.this);
                                facebookIntent.setData(Uri.parse(facebookUrl));
                                startActivity(facebookIntent);
                                break;
                            case 4:
                                uri = Uri.parse("http://instagram.com/fontedebencao");
                                it = new Intent(Intent.ACTION_VIEW, uri);
                                startActivity(it);
                                break;

                            case 5:
                                it = new Intent(MainActivity.this, Site.class);
                                startActivity(it);
                                break;
                            case 6:
                                it = new Intent(MainActivity.this, SobreDev.class);
                                startActivity(it);
                                break;
                        }
                        return false;
                    }
                }).build();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_sobre:
                it = new Intent(MainActivity.this, SobreDev.class);
                startActivity(it);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setDesktopMode(WebView webView, boolean enabled) {
        String newUserAgent = webView.getSettings().getUserAgentString();
        if (enabled) {
            try {
                String ua = webView.getSettings().getUserAgentString();
                String androidOSString = webView.getSettings().getUserAgentString().substring(ua.indexOf("("), ua.indexOf(")") + 1);
                newUserAgent = webView.getSettings().getUserAgentString().replace(androidOSString, "(X11; Linux x86_64)");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            newUserAgent = null;
        }

        webView.getSettings().setUserAgentString(newUserAgent);
        webView.getSettings().setUseWideViewPort(enabled);
        webView.getSettings().setLoadWithOverviewMode(enabled);
        webView.reload();
    }

    public String getFacebookPageURL(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) {
                return "fb://facewebmodal/f?href=" + FACEBOOK_URL;
            } else {
                return "fb://page/" + FACEBOOK_PAGE_ID;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return FACEBOOK_URL;
        }
    }
}