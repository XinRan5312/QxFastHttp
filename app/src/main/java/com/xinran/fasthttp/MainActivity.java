package com.xinran.fasthttp;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.xinran.fastokhttp.QxHttpManager;
import com.xinran.fastokhttp.requestcall.QxGetReqCall;
import com.xinran.fastokhttp.requests.QxCommonCallBack;

import okhttp3.Request;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=$(R.id.tv);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    new QxGetReqCall().url("http://sec.mobile.tiancity.com/server/mobilesecurity/version.xml")
                        .tag(this)
                        .call(new QxCommonCallBack<String>(String.class) {
                            @Override
                            public void onError(Request request, Exception e) {
                            }
                            @Override
                            public void onResponse(String response) {

                                tv.setText(response);
                            }
                        });
            }
        });

    }

    private <T extends View> T $(@IdRes int id) {
        return (T) findViewById(id);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        QxHttpManager.cancelTag(this);
    }
}
