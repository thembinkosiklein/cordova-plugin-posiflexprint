package com.pos.printer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class opencashdrawerActivity extends Activity {
	EditText myeditText_PulseTime;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opencashdrawer);
        //--------------------------
        myeditText_PulseTime=(EditText)findViewById(R.id.editText_OpenCashDrawer_PulseTime);
    }

    public void OpenCashDrawer1(View view)
    {
    	if(Main.isLAN) {
    		PrinterFunctionsLAN.OpenCashDrawer(
	    			Main.portName,
					Main.portSettings,
	    			0,
					Integer.parseInt(myeditText_PulseTime.getText().toString()));
    	} else {
	    	PrinterFunctions.OpenCashDrawer(
	    			Main.portName,
					Main.portSettings,
	    			0,
					Integer.parseInt(myeditText_PulseTime.getText().toString()));
    	}
    }
    
    public void OpenCashDrawer2(View view)
    {
    	if(Main.isLAN) {
    		PrinterFunctionsLAN.OpenCashDrawer(
	    			Main.portName,
					Main.portSettings,
					1,
					Integer.parseInt(myeditText_PulseTime.getText().toString()));
    	} else {
	    	PrinterFunctions.OpenCashDrawer(
	    			Main.portName,
					Main.portSettings,
					1,
					Integer.parseInt(myeditText_PulseTime.getText().toString()));
    	}
    }
    
    public void Help_OpenCashDrawer(View view)
    {
    	String helpString = 
    			"<body>" +
    	    	"<MainTitle>int OpenCashDrawer(String portName,int portSettings,int m,int t)</MainTitle><br/>"+
    	    	"<br/>"+
    			"<MainTitle><ColorBlue>String portName</ColorBlue></MainTitle><br/>"+
    			"Port name parameters<br/>"+
    			"<br/>"+
    			"<MainTitle><ColorBlue>int portSettings</ColorBlue></MainTitle><br/>"+
    			"Port settings parameters<br/>"+
    			"<br/>"+
    			"<MainTitle><ColorBlue>int m</ColorBlue></MainTitle><br/>"+
    			"Outputs the pulse specified by t in real-time to the connector pin specified by m as follows:<br/>"+
    			"<table border=1 bordercolor=#000000 cellspacing=1>"+
			    	"<tr>"+
			    		"<th>m</th>"+
			    		"<th>Connector pin</th>"+
			    	"</tr>"+
			    	"<tr>"+
			    		"<td>0</td>"+
			    		"<td>Drawer kick-out connector pin 2.</td>"+
			    	"</tr>"+
			    	"<tr>"+
		    			"<td>1</td>"+
		    			"<td>Drawer kick-out connector pin 5.</td>"+
		    		"</tr>"+
    			"</table>"+
    			"<br/>"+
    			"<MainTitle><ColorBlue>int t</ColorBlue></MainTitle><br/>"+
    			"The pulse ON time or OFF time is set to [t * 100 ms].<br/>"+
    			"Range: 1 <= t <= 8<br/>"+
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
