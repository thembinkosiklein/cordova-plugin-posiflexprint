package com.pos.printer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class qrcodeActivity extends Activity {
	Spinner myspinner_CorrectionLevel;
	Spinner myspinner_Model;
	EditText myeditText_CellSize;
	EditText myeditText_Version;
	EditText myeditText_QRCodeData;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qrcode);
        //--------------------------
        myeditText_CellSize=(EditText)findViewById(R.id.editText_qrcode_CellSize);
        myeditText_Version=(EditText)findViewById(R.id.editText_qrcode_Version);
    	myeditText_QRCodeData=(EditText)findViewById(R.id.editText_qrcode_QRCodeData);
        //--------------------------
        myspinner_CorrectionLevel = (Spinner) findViewById(R.id.spinner_qrcode_CorrectionLevel);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.qrcode_CorrectionLevel, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        myspinner_CorrectionLevel.setAdapter(adapter);
        
        myspinner_Model = (Spinner) findViewById(R.id.spinner_qrcode_Model);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(
                this, R.array.qrcode_Model, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        myspinner_Model.setAdapter(adapter2);
    }

    public void PrintText_qrcode(View view)
    {
    	int value_CorrectionLevel=48;
    	int value_Model=49;
    	
    	switch(myspinner_CorrectionLevel.getSelectedItemPosition()) {
        case 0:
        	value_CorrectionLevel=48;
        	break;
        case 1:
        	value_CorrectionLevel=49;
        	break;
        case 2:
        	value_CorrectionLevel=50;
        	break;
        case 3:
        	value_CorrectionLevel=51;
        	break;
        }
    	
        switch(myspinner_Model.getSelectedItemPosition()) {
        case 0:
        	value_Model=49;
        	break;
        case 1:
        	value_Model=50;
        	break;
        }
    	
        if(Main.isLAN) {
			PrinterFunctionsLAN.PrintQrCode(
					Main.portName,
					Main.portSettings,
					value_CorrectionLevel,
					value_Model,
					Integer.parseInt(myeditText_CellSize.getText().toString()),
					Integer.parseInt(myeditText_Version.getText().toString()),
					myeditText_QRCodeData.getText().toString());
			
			PrinterFunctionsLAN.PreformCut(Main.portName,Main.portSettings,1);
        } else {
			PrinterFunctions.PrintQrCode(
					Main.portName,
					Main.portSettings,
					value_CorrectionLevel,
					value_Model,
					Integer.parseInt(myeditText_CellSize.getText().toString()),
					Integer.parseInt(myeditText_Version.getText().toString()),
					myeditText_QRCodeData.getText().toString());
			
			PrinterFunctions.PreformCut(Main.portName,Main.portSettings,1);
        }
    }
    
    public void Help_qrcode(View view)
    {
    	String helpString = 
    			"<body>" +
    	    	"<MainTitle>int PrintQrCode(String portName,<br/>" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"int portSettings,<br/>" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"int n,<br/>" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"int mod,<br/>" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"int size,<br/>" +
				"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
				"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
				"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
				"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
				"int version,<br/>" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"String dArray)</MainTitle><br/>"+
    	    	"<br/>"+
    			"<MainTitle><ColorBlue>String portName</ColorBlue></MainTitle><br/>"+
    			"Port name parameters<br/>"+
    			"<br/>"+
    			"<MainTitle><ColorBlue>int portSettings</ColorBlue></MainTitle><br/>"+
    			"Port settings parameters<br/>"+
    			"<br/>"+
    			"<MainTitle><ColorBlue>int n</ColorBlue></MainTitle><br/>"+
    			"Sets the error correction level for QRCode symbol.<br/>"+
    			"<table border=1 bordercolor=#000000 cellspacing=1>"+
			    	"<tr>"+
			    		"<th>n</th>"+
			    		"<th>Function</th>"+
			    		"<th>Reference:Approximate figure for recovery(%)</th>"+
			    	"</tr>"+
			    	"<tr>"+
			    		"<td>48</td>"+
			    		"<td>Select error correction level L</td>"+
			    		"<td>7</td>"+
			    	"</tr>"+
			    	"<tr>"+
				    	"<td>49</td>"+
			    		"<td>Select error correction level M</td>"+
			    		"<td>15</td>"+
		    		"</tr>"+
		    		"<tr>"+
				    	"<td>50</td>"+
			    		"<td>Select error correction level Q</td>"+
			    		"<td>25</td>"+
		    		"</tr>"+
		    		"<tr>"+
				    	"<td>51</td>"+
			    		"<td>Select error correction level H</td>"+
			    		"<td>30</td>"+
	    			"</tr>"+
    			"</table>"+
    			"<br/>"+
    			"<MainTitle><ColorBlue>int mod</ColorBlue></MainTitle><br/>"+
    			"Specifies the mode for QRCode symbol.<br/>"+
    			"<table border=1 bordercolor=#000000 cellspacing=1>"+
			    	"<tr>"+
			    		"<th>mod</th>"+
			    		"<th>Function</th>"+
			    	"</tr>"+
			    	"<tr>"+
			    		"<td>49</td>"+
			    		"<td>Specifies the mode 1 conversion processing.</td>"+
			    	"</tr>"+
			    	"<tr>"+
				    	"<td>50</td>"+
			    		"<td>Specifies the mode 2 conversion processing.</td>"+
		    		"</tr>"+
    			"</table>"+
    			"<br/>"+
    			"<MainTitle><ColorBlue>int size</ColorBlue></MainTitle><br/>"+
    			"Sets the size of the QRCode symbol module to [n dots * n dots].<br/>"+
    			"Range: 1 <= n <= 16<br/>"+
    			"<br/>"+
				"<MainTitle><ColorBlue>int version</ColorBlue></MainTitle><br/>"+
				"Sets the version of the QRCode symbol module.<br/>"+
				"Range: 0 <= version <= 22<br/>"+
				"<br/>"+
    			"<MainTitle><ColorBlue>String dArray</ColorBlue></MainTitle><br/>"+
    			"<table border=1 bordercolor=#000000 cellspacing=1>"+
			    	"<tr>"+
			    		"<th>Data Length Range</th>"+
			    		"<th>Data Range</th>"+
			    	"</tr>"+
			    	"<tr>"+
			    		"<td>4 <= n <= 7092</td>"+
			    		"<td>0 <= d <= 255</td>"+
			    	"</tr>"+
	    		"</table>"+
	    		"<br/>"+
				"<MainTitle><ColorGreen>Return message</ColorGreen></MainTitle><br/>"+
				"Successful return value of 0<br/>"+
				"Fail return value of -1<br/>"+
	    		"</body></html>";
		helpMessageActivity.SetMessage(helpString);
		
		Intent myIntent = new Intent(this, helpMessageActivity.class);
		startActivityFromChild(this, myIntent, 0);
    }
}