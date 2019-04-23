package com.pos.printer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class getstatusActivity extends Activity {
	TextView mytextView_messages;
	Spinner myspinner_StatusSpecified;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getstatus);
        //--------------------------
        mytextView_messages=(TextView)findViewById(R.id.textView_getstatus_messages);
        //--------------------------
        myspinner_StatusSpecified = (Spinner) findViewById(R.id.spinner_getstatus_StatusSpecified);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.getstatus_StatusSpecified, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        myspinner_StatusSpecified.setAdapter(adapter);
    }

    public void GetStatus(View view)
    {
    	int value_StatusSpecified=1;
    	
    	switch(myspinner_StatusSpecified.getSelectedItemPosition()) {
    	case 0:
    		value_StatusSpecified=1;
    		break;
    	case 1:
    		value_StatusSpecified=2;
    		break;
    	case 2:
    		value_StatusSpecified=3;
    		break;
    	case 3:
    		value_StatusSpecified=4;
    		break;
    	}
    	
		int res=0;
		if(Main.isLAN) {
			res=PrinterFunctionsLAN.CheckStatus(
					Main.portName,
					Main.portSettings,
					value_StatusSpecified);
		} else {
			res=PrinterFunctions.CheckStatus(
					Main.portName,
					Main.portSettings,
					value_StatusSpecified);
		}
		String mStr="";
		switch(myspinner_StatusSpecified.getSelectedItemPosition()) {
		case 0:
			mStr="Printer status:\n";
			if((res & 0x04)==0) mStr+="1.Drawer kick-out connector pin 3 is LOW.\n";
			else mStr+="1.Drawer kick-out connector pin 3 is HIGH.\n";
			
			if((res & 0x08)==0) mStr+="2.Online.\n";
			else mStr+="2.Offline.\n";
			
			if((res & 0x20)==0) mStr+="3.Not in online waiting status.\n";
			else mStr+="3.During online waiting status.\n";
			
			if((res & 0x40)==0) mStr+="4.Paper FEED button is turned Off.\n";
			else mStr+="4.Paper FEED button is turned On.\n";
			break;
		case 1:
			mStr="Offline status:\n";
			if((res & 0x4)==0) mStr+="1.Cover is closed.\n";
			else mStr+="1.Cover is open.\n";
			
			if((res & 0x8)==0) mStr+="2.Paper is not being fed by using the paper FEED button.\n";
			else mStr+="2.Paper is being fed by the paper FEED button.\n";
			
			if((res & 0x20)==0) mStr+="3.No paper-end stop.\n";
			else mStr+="3.Printing is being stopped due to a paper end.\n";
			
			if((res & 0x40)==0) mStr+="4.No error.\n";
			else mStr+="4.Error has occurred.\n";
			break;
		case 2:
			mStr="Error status:\n";
			if((res & 0x04)==0) mStr+="1.No mechanical error.\n";
			else mStr+="1.Mechanical error has occurred.\n";
			
			if((res & 0x08)==0) mStr+="2.No autocutter error.\n";
			else mStr+="2.Autocutter error occurred.\n";
			
			if((res & 0x20)==0) mStr+="3.No unrecoverable error.\n";
			else mStr+="3.Unrecoverable error has occurred.\n";
			
			if((res & 0x40)==0) mStr+="4.No automatically recoverable error.\n";
			else mStr+="4.Automatically recoverable error has occurred.\n";
			break;
		case 3:
			mStr="Continuous paper sensor status:\n";
			if((res & 0x04)==0) mStr+="1.Roll paper near-end sensor: paper adequate.\n";
			else mStr+="1.Roll paper near-end sensor: paper near end.\n";
			
			if((res & 0x08)==0) mStr+="2.Roll paper near-end sensor: paper adequate.\n";
			else mStr+="2.Roll paper near-end sensor: paper near end.\n";
			
			if((res & 0x20)==0) mStr+="3.Roll paper end sensor: paper present.\n";
			else mStr+="3.Roll paper end sensor: paper not present.\n";
			
			if((res & 0x40)==0) mStr+="4.Roll paper end sensor: paper present.\n";
			else mStr+="4.Roll paper end sensor: paper not present.\n";
			break;
		}
		if(res==255) mStr="Error!!\nNo Messages...";
		if(res==-1) mStr="Error!!\nNo Messages...";
		mytextView_messages.setText(mStr);
    }
    
    public void Help_getstatus(View view)
    {
    	String helpString = 
    			"<body>" +
    			"<MainTitle>int PrinterFunctions.CheckStatus(String portName,int portSettings,int n)</MainTitle><br/>"+
    			"<br/>"+
    			"<MainTitle><ColorBlue>String portName</ColorBlue></MainTitle><br/>"+
    			"Port name parameters<br/>"+
    			"<br/>"+
    			"<MainTitle><ColorBlue>int portSettings</ColorBlue></MainTitle><br/>"+
    			"Port settings parameters<br/>"+
    			"<br/>"+
    			"<MainTitle><ColorBlue>int n</ColorBlue></MainTitle><br/>"+
    			"Transmits the status specified by n in real time as follows:<br/>"+
    			"<table border=1 bordercolor=#000000 cellspacing=1>"+
    		    	"<tr>"+
    		    		"<th>n</th>"+
    		    		"<th>Function</th>"+
    		    	"</tr>"+
    		    	"<tr>"+
    		    		"<td>1</td>"+
    		    		"<td>Transmits printer status.</td>"+
    		    	"</tr>"+
    		    	"<tr>"+
		    			"<td>2</td>"+
		    			"<td>Transmits offline status.</td>"+
		    		"</tr>"+
		    		"<tr>"+
		    			"<td>3</td>"+
		    			"<td>Transmits error status.</td>"+
		    		"</tr>"+
		    		"<tr>"+
	    				"<td>4</td>"+
	    				"<td>Transmits paper roll sensor status.</td>"+
	    			"</tr>"+
    		    "</table>"+
    		    "<br/>"+
    		    "<MainTitle><ColorGreen>Return message</ColorGreen></MainTitle><br/>"+
    			"This printer transmits the following status in real time.<br/>"+
    			"<SubTitle>n = 1: Printer status</SubTitle><br/>"+
				"<table border=1 bordercolor=#000000 cellspacing=1>"+
					"<tr>"+
						"<th>Bit</th>"+
					    "<th>Off/On</th>"+
					    "<th>Hex</th>"+
					    "<th>Decimal</th>"+
					    "<th>Function</th>"+
					"</tr>"+
					"<tr>"+
					    "<td>0</td>"+
					    "<td>Off</td>"+
					    "<td>00</td>"+
					    "<td>0</td>"+
					    "<td>Fixed.</td>"+
				    "</tr>"+
					"<tr>"+
					    "<td>1</td>"+
					    "<td>On</td>"+
					    "<td>02</td>"+
					    "<td>2</td>"+
					    "<td>Fixed.</td>"+
				    "</tr>"+
					"<tr>"+
					    "<td rowspan=2>2</td>"+
					    "<td>Off</td>"+
					    "<td>00</td>"+
					    "<td>0</td>"+
					    "<td>Drawer kick-out connector pin 3 is LOW.</td>"+
					"</tr>"+
					"<tr>"+
					    "<td>On</td>"+
					    "<td>04</td>"+
					    "<td>4</td>"+
					    "<td>Drawer kick-out connector pin 3 is HIGH.</td>"+
					"</tr>"+
					"<tr>"+
					    "<td rowspan=2>3</td>"+
					    "<td>Off</td>"+
					    "<td>00</td>"+
					    "<td>0</td>"+
					    "<td>Online.</td>"+
					"</tr>"+
					"<tr>"+
					    "<td>On</td>"+
					    "<td>08</td>"+
					    "<td>8</td>"+
					    "<td>Offline.</td>"+
					"</tr>"+
					"<tr>"+
						"<td>4</td>"+
						"<td>On</td>"+
						"<td>10</td>"+
						"<td>16</td>"+
						"<td>Fixed.</td>"+
					"</tr>"+
					"<tr>"+
					    "<td rowspan=2>5</td>"+
					    "<td>Off</td>"+
					    "<td>00</td>"+
					    "<td>0</td>"+
					    "<td>Not in online waiting status.</td>"+
				    "</tr>"+
				    "<tr>"+
					    "<td>On</td>"+
					    "<td>20</td>"+
					    "<td>32</td>"+
					    "<td>During online waiting status.</td>"+
					"</tr>"+
					"<tr>"+
						"<td rowspan=2>6</td>"+
						"<td>Off</td>"+
						"<td>00</td>"+
						"<td>0</td>"+
						"<td>Paper FEED button is turned Off.</td>"+
					"</tr>"+
					"<tr>"+
						"<td>On</td>"+
						"<td>40</td>"+
						"<td>64</td>"+
						"<td>Paper FEED button is turned On.</td>"+
					"</tr>"+
					"<tr>"+
						"<td>7</td>"+
						"<td>Off</td>"+
						"<td>00</td>"+
						"<td>0</td>"+
						"<td>Fixed.</td>"+
					"</tr>"+
				"</table>"+
    		    "<br/>"+
    			"<SubTitle>n = 2: Offline status</SubTitle><br/>"+
				"<table border=1 bordercolor=#000000 cellspacing=1>"+
					"<tr>"+
						"<th>Bit</th>"+
					    "<th>Off/On</th>"+
					    "<th>Hex</th>"+
					    "<th>Decimal</th>"+
					    "<th>Function</th>"+
					"</tr>"+
					"<tr>"+
					    "<td>0</td>"+
					    "<td>Off</td>"+
					    "<td>00</td>"+
					    "<td>0</td>"+
					    "<td>Fixed.</td>"+
				    "</tr>"+
					"<tr>"+
					    "<td>1</td>"+
					    "<td>On</td>"+
					    "<td>02</td>"+
					    "<td>2</td>"+
					    "<td>Fixed.</td>"+
				    "</tr>"+
					"<tr>"+
					    "<td rowspan=2>2</td>"+
					    "<td>Off</td>"+
					    "<td>00</td>"+
					    "<td>0</td>"+
					    "<td>Cover is closed.</td>"+
					"</tr>"+
					"<tr>"+
					    "<td>On</td>"+
					    "<td>04</td>"+
					    "<td>4</td>"+
					    "<td>Cover is open.</td>"+
					"</tr>"+
					"<tr>"+
					    "<td rowspan=2>3</td>"+
					    "<td>Off</td>"+
					    "<td>00</td>"+
					    "<td>0</td>"+
					    "<td>Paper is not being fed by using the paper FEED button.</td>"+
					"</tr>"+
					"<tr>"+
					    "<td>On</td>"+
					    "<td>08</td>"+
					    "<td>8</td>"+
					    "<td>Paper is being fed by the paper FEED button.</td>"+
					"</tr>"+
					"<tr>"+
						"<td>4</td>"+
						"<td>On</td>"+
						"<td>10</td>"+
						"<td>16</td>"+
						"<td>Fixed.</td>"+
					"</tr>"+
					"<tr>"+
					    "<td rowspan=2>5</td>"+
					    "<td>Off</td>"+
					    "<td>00</td>"+
					    "<td>0</td>"+
					    "<td>No paper-end stop.</td>"+
				    "</tr>"+
				    "<tr>"+
					    "<td>On</td>"+
					    "<td>20</td>"+
					    "<td>32</td>"+
					    "<td>Printing is being stopped due to a paper end.</td>"+
					"</tr>"+
					"<tr>"+
						"<td rowspan=2>6</td>"+
						"<td>Off</td>"+
						"<td>00</td>"+
						"<td>0</td>"+
						"<td>No error.</td>"+
					"</tr>"+
					"<tr>"+
						"<td>On</td>"+
						"<td>40</td>"+
						"<td>64</td>"+
						"<td>Error has occurred.</td>"+
					"</tr>"+
					"<tr>"+
						"<td>7</td>"+
						"<td>Off</td>"+
						"<td>00</td>"+
						"<td>0</td>"+
						"<td>Fixed.</td>"+
					"</tr>"+
				"</table>"+
    		    "<br/>"+
    			"<SubTitle>n = 3: Error status</SubTitle><br/>"+
    			"<table border=1 bordercolor=#000000 cellspacing=1>"+
					"<tr>"+
						"<th>Bit</th>"+
					    "<th>Off/On</th>"+
					    "<th>Hex</th>"+
					    "<th>Decimal</th>"+
					    "<th>Function</th>"+
					"</tr>"+
					"<tr>"+
					    "<td>0</td>"+
					    "<td>Off</td>"+
					    "<td>00</td>"+
					    "<td>0</td>"+
					    "<td>Fixed.</td>"+
				    "</tr>"+
					"<tr>"+
					    "<td>1</td>"+
					    "<td>On</td>"+
					    "<td>02</td>"+
					    "<td>2</td>"+
					    "<td>Fixed.</td>"+
				    "</tr>"+
					"<tr>"+
					    "<td rowspan=2>2</td>"+
					    "<td>Off</td>"+
					    "<td>00</td>"+
					    "<td>0</td>"+
					    "<td>No mechanical error.</td>"+
					"</tr>"+
					"<tr>"+
					    "<td>On</td>"+
					    "<td>04</td>"+
					    "<td>4</td>"+
					    "<td>Mechanical error has occurred.</td>"+
					"</tr>"+
					"<tr>"+
					    "<td rowspan=2>3</td>"+
					    "<td>Off</td>"+
					    "<td>00</td>"+
					    "<td>0</td>"+
					    "<td>No autocutter error.</td>"+
					"</tr>"+
					"<tr>"+
					    "<td>On</td>"+
					    "<td>08</td>"+
					    "<td>8</td>"+
					    "<td>Autocutter error occurred.</td>"+
					"</tr>"+
					"<tr>"+
						"<td>4</td>"+
						"<td>On</td>"+
						"<td>10</td>"+
						"<td>16</td>"+
						"<td>Fixed.</td>"+
					"</tr>"+
					"<tr>"+
					    "<td rowspan=2>5</td>"+
					    "<td>Off</td>"+
					    "<td>00</td>"+
					    "<td>0</td>"+
					    "<td>No unrecoverable error.</td>"+
				    "</tr>"+
				    "<tr>"+
					    "<td>On</td>"+
					    "<td>20</td>"+
					    "<td>32</td>"+
					    "<td>Unrecoverable error has occurred.</td>"+
					"</tr>"+
					"<tr>"+
						"<td rowspan=2>6</td>"+
						"<td>Off</td>"+
						"<td>00</td>"+
						"<td>0</td>"+
						"<td>No automatically recoverable error.</td>"+
					"</tr>"+
					"<tr>"+
						"<td>On</td>"+
						"<td>40</td>"+
						"<td>64</td>"+
						"<td>Automatically recoverable error has occurred.</td>"+
					"</tr>"+
					"<tr>"+
						"<td>7</td>"+
						"<td>Off</td>"+
						"<td>00</td>"+
						"<td>0</td>"+
						"<td>Fixed.</td>"+
					"</tr>"+
				"</table>"+
    		    "<br/>"+
    			"<SubTitle>n = 4: Continuous paper sensor status</SubTitle><br/>"+
    			"<table border=1 bordercolor=#000000 cellspacing=1>"+
					"<tr>"+
						"<th>Bit</th>"+
					    "<th>Off/On</th>"+
					    "<th>Hex</th>"+
					    "<th>Decimal</th>"+
					    "<th>Function</th>"+
					"</tr>"+
					"<tr>"+
					    "<td>0</td>"+
					    "<td>Off</td>"+
					    "<td>00</td>"+
					    "<td>0</td>"+
					    "<td>Fixed.</td>"+
				    "</tr>"+
					"<tr>"+
					    "<td>1</td>"+
					    "<td>On</td>"+
					    "<td>02</td>"+
					    "<td>2</td>"+
					    "<td>Fixed.</td>"+
				    "</tr>"+
					"<tr>"+
					    "<td rowspan=2>2</td>"+
					    "<td>Off</td>"+
					    "<td>00</td>"+
					    "<td>0</td>"+
					    "<td>Roll paper near-end sensor: paper adequate.</td>"+
					"</tr>"+
					"<tr>"+
					    "<td>On</td>"+
					    "<td>04</td>"+
					    "<td>4</td>"+
					    "<td>Roll paper near-end sensor: paper near end.</td>"+
					"</tr>"+
					"<tr>"+
					    "<td rowspan=2>3</td>"+
					    "<td>Off</td>"+
					    "<td>00</td>"+
					    "<td>0</td>"+
					    "<td>Roll paper near-end sensor: paper adequate.</td>"+
					"</tr>"+
					"<tr>"+
					    "<td>On</td>"+
					    "<td>08</td>"+
					    "<td>8</td>"+
					    "<td>Roll paper near-end sensor: paper near end.</td>"+
					"</tr>"+
					"<tr>"+
						"<td>4</td>"+
						"<td>On</td>"+
						"<td>10</td>"+
						"<td>16</td>"+
						"<td>Fixed.</td>"+
					"</tr>"+
					"<tr>"+
					    "<td rowspan=2>5</td>"+
					    "<td>Off</td>"+
					    "<td>00</td>"+
					    "<td>0</td>"+
					    "<td>Roll paper end sensor: paper present.</td>"+
				    "</tr>"+
				    "<tr>"+
					    "<td>On</td>"+
					    "<td>20</td>"+
					    "<td>32</td>"+
					    "<td>Roll paper end sensor: paper not present.</td>"+
					"</tr>"+
					"<tr>"+
						"<td rowspan=2>6</td>"+
						"<td>Off</td>"+
						"<td>00</td>"+
						"<td>0</td>"+
						"<td>Roll paper end sensor: paper present.</td>"+
					"</tr>"+
					"<tr>"+
						"<td>On</td>"+
						"<td>40</td>"+
						"<td>64</td>"+
						"<td>Roll paper end sensor: paper not present.</td>"+
					"</tr>"+
					"<tr>"+
						"<td>7</td>"+
						"<td>Off</td>"+
						"<td>00</td>"+
						"<td>0</td>"+
						"<td>Fixed.</td>"+
					"</tr>"+
				"</table>"+
    		    "<br/>"+
    		    "</body></html>";
		helpMessageActivity.SetMessage(helpString);
		
		Intent myIntent = new Intent(this, helpMessageActivity.class);
		startActivityFromChild(this, myIntent, 0);
    }
}
