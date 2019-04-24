package com.pos.printer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class pdf417Activity extends Activity {
	EditText myeditText_Columns;
	EditText myeditText_Rows;
	EditText myeditText_Width;
	EditText myeditText_Height;
	EditText myeditText_Data;
	Spinner myspinner_CorrectionLevel;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pdf417);
        //--------------------------
    	myeditText_Columns=(EditText)findViewById(R.id.editText_pdf417_Columns);
    	myeditText_Rows=(EditText)findViewById(R.id.editText_pdf417_Rows);
    	myeditText_Width=(EditText)findViewById(R.id.editText_pdf417_Width);
    	myeditText_Height=(EditText)findViewById(R.id.editText_pdf417_Height);
    	myeditText_Data=(EditText)findViewById(R.id.editText_pdf417_Data);
        //--------------------------
        myspinner_CorrectionLevel = (Spinner) findViewById(R.id.spinner_pdf417_CorrectionLevel);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.pdf417_CorrectionLevel, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        myspinner_CorrectionLevel.setAdapter(adapter);
    }

    public void PrintText_pdf417(View view)
    {
    	int value_CorrectionLevel=48;
    	
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
        case 4:
        	value_CorrectionLevel=52;
        	break;
        case 5:
        	value_CorrectionLevel=53;
        	break;
        case 6:
        	value_CorrectionLevel=54;
        	break;
        case 7:
        	value_CorrectionLevel=55;
        	break;
        case 8:
        	value_CorrectionLevel=56;
        	break;
        }
    	
    	if(Main.isLAN) {
	    	PrinterFunctionsLAN.PrintPDF417Code(
	    			Main.portName,
					Main.portSettings,
	    			Integer.parseInt(myeditText_Columns.getText().toString()),
					Integer.parseInt(myeditText_Rows.getText().toString()),
					Integer.parseInt(myeditText_Width.getText().toString()),
					Integer.parseInt(myeditText_Height.getText().toString()),
					value_CorrectionLevel,
					myeditText_Data.getText().toString());
	    	
	    	PrinterFunctionsLAN.PreformCut(Main.portName,Main.portSettings,1);
    	} else {
	    	PrinterFunctions.PrintPDF417Code(
	    			Main.portName,
					Main.portSettings,
	    			Integer.parseInt(myeditText_Columns.getText().toString()),
					Integer.parseInt(myeditText_Rows.getText().toString()),
					Integer.parseInt(myeditText_Width.getText().toString()),
					Integer.parseInt(myeditText_Height.getText().toString()),
					value_CorrectionLevel,
					myeditText_Data.getText().toString());
	    	
	    	PrinterFunctions.PreformCut(Main.portName,Main.portSettings,1);
    	}
    }
    
    public void Help_pdf417(View view)
    {
    	String helpString = 
    			"<body>" +
    	    	"<MainTitle>int PrintPDF417Code(String portName,<br/>" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"int portSettings,<br/>" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"int Columns,<br/>" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"int Rows,<br/>" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"int Width,<br/>" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"int Height,<br/>" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"int CorrectionLevel,<br/>" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"String dArray)</MainTitle><br/>" +
    	    	"<br/>"+
    	    	"<MainTitle><ColorBlue>String portName</ColorBlue></MainTitle><br/>"+
    			"Port name parameters<br/>"+
    			"<br/>"+
    			"<MainTitle><ColorBlue>int portSettings</ColorBlue></MainTitle><br/>"+
    			"Port settings parameters<br/>"+
    			"<br/>"+
    			"<MainTitle><ColorBlue>int Columns</ColorBlue></MainTitle><br/>"+
    			"Range: 0 <= Columns <= 30<br/>"+
    			"n = 0 specifies automatic processing.<br/>"+
    			"n != 0 sets the number of columns of the data area to n code words.<br/>"+
    			"<br/>"+
    			"<MainTitle><ColorBlue>int Rows</ColorBlue></MainTitle><br/>"+
    			"Range: Rows = 0, 3 <= Rows <= 90<br/>"+
    			"n = 0 specifies automatic processing.<br/>"+
    			"n != 0 sets the number of rows to n.<br/>"+
    			"<br/>"+
    			"<MainTitle><ColorBlue>int Width</ColorBlue></MainTitle><br/>"+
    			"Range: 2 <= n <= 8<br/>"+
    			"<br/>"+
    			"<MainTitle><ColorBlue>int Height</ColorBlue></MainTitle><br/>"+
    			"Range: 2 <= n <= 8<br/>"+
    			"<br/>"+
    			"<MainTitle><ColorBlue>int CorrectionLevel</ColorBlue></MainTitle><br/>"+
				"<table border=1 bordercolor=#000000 cellspacing=1>"+
					"<tr>"+
						"<th>CorrectionLevel</th>"+
						"<th>Function</th>"+
						"<th>Error correction code word</th>"+
					"</tr>"+
					"<tr>"+
						"<td>48</td>"+
						"<td>Select error correction level 0</td>"+
						"<td>2</td>"+
					"</tr>"+
					"<tr>"+
						"<td>49</td>"+
						"<td>Select error correction level 1</td>"+
						"<td>4</td>"+
					"</tr>"+
					"<tr>"+
						"<td>50</td>"+
						"<td>Select error correction level 2</td>"+
						"<td>8</td>"+
					"</tr>"+
					"<tr>"+
						"<td>51</td>"+
						"<td>Select error correction level 3</td>"+
						"<td>16</td>"+
					"</tr>"+
					"<tr>"+
						"<td>52</td>"+
						"<td>Select error correction level 4</td>"+
						"<td>32</td>"+
					"</tr>"+
					"<tr>"+
						"<td>53</td>"+
						"<td>Select error correction level 5</td>"+
						"<td>64</td>"+
					"</tr>"+
					"<tr>"+
						"<td>54</td>"+
						"<td>Select error correction level 6</td>"+
						"<td>128</td>"+
					"</tr>"+
					"<tr>"+
						"<td>55</td>"+
						"<td>Select error correction level 7</td>"+
						"<td>256</td>"+
					"</tr>"+
					"<tr>"+
						"<td>56</td>"+
						"<td>Select error correction level 8</td>"+
						"<td>512</td>"+
					"</tr>"+
				"</table>"+
    			"<br/>"+
    			"<MainTitle><ColorBlue>String dArray</ColorBlue></MainTitle><br/>"+
    			"<table border=1 bordercolor=#000000 cellspacing=1>"+
			    	"<tr>"+
			    		"<th>Data Length Range</th>"+
			    		"<th>Data Range</th>"+
			    	"</tr>"+
			    	"<tr>"+
			    		"<td>4 <= n <= 65535</td>"+
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