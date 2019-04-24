package com.pos.printer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class twodbarcodeActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.twodbarcode);
    }
    
    public void QRCode(View view)
    {
    	Intent myIntent=new Intent(view.getContext(),qrcodeActivity.class);
    	startActivityForResult(myIntent, 0);
    }
    
    public void PDF417(View view)
    {
    	Intent myIntent=new Intent(view.getContext(),pdf417Activity.class);
    	startActivityForResult(myIntent, 0);
    }
}
