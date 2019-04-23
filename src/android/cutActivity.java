package com.pos.printer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class cutActivity extends Activity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cut);
    }
    
    public void StandardCut(View view)
    {
    	if(Main.isLAN) {
    		PrinterFunctionsLAN.PreformCut(Main.portName,Main.portSettings,0);
    	} else {
    		PrinterFunctions.PreformCut(Main.portName,Main.portSettings,0);
    	}
    }
    
    public void CutWithFeed(View view)
    {
    	if(Main.isLAN) {
    		PrinterFunctionsLAN.PreformCut(Main.portName,Main.portSettings,1);
    	} else {
    		PrinterFunctions.PreformCut(Main.portName,Main.portSettings,1);
    	}
    }
    
    public void Help_cut(View view)
    {
    	String helpString = 
    			"<body>" +
    	    	"<MainTitle>int PreformCut(String portName,int portSettings,int cuttype)</MainTitle><br/>"+
    	    	"<br/>"+
    			"<MainTitle><ColorBlue>String portName</ColorBlue></MainTitle><br/>"+
    			"Port name parameters<br/>"+
    			"<br/>"+
    			"<MainTitle><ColorBlue>int portSettings</ColorBlue></MainTitle><br/>"+
    			"Port settings parameters<br/>"+
    			"<br/>"+
    			"<MainTitle><ColorBlue>int cuttype</ColorBlue></MainTitle><br/>"+
    			"<table border=1 bordercolor=#000000 cellspacing=1>"+
			    	"<tr>"+
			    		"<th>cuttype</th>"+
			    		"<th>Function</th>"+
			    	"</tr>"+
			    	"<tr>"+
		    			"<td>0</td>"+
		    			"<td>Cut at current position</td>"+
		    		"</tr>"+
	    			"<tr>"+
		    			"<td>1</td>"+
		    			"<td>Paper is fed to cutting position</td>"+
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