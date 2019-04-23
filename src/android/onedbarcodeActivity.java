package com.pos.printer;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class onedbarcodeActivity extends Activity {
	ImageView myimageView;
	EditText myeditText_Height;
	EditText myeditText_Width;
	EditText myeditText_Data;
	Spinner myspinner_HRIcharacters;
	Spinner myspinner_Alignment;
	Spinner mybarcodeType;
	//--------------------------
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onedbarcode);
        //--------------------------
        myimageView = (ImageView) findViewById(R.id.imageView_oneBarCode);
    	myeditText_Height=(EditText)findViewById(R.id.editText_oneBarCode_Height);
    	myeditText_Width=(EditText)findViewById(R.id.editText_oneBarCode_Width);
    	myeditText_Data=(EditText)findViewById(R.id.editText_oneBarCode_Data);
        //--------------------------
    	myspinner_HRIcharacters = (Spinner) findViewById(R.id.spinner_oneBarCode_HRIcharacters);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(
                this, R.array.oneDBarcode_HRIcharacters, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        myspinner_HRIcharacters.setAdapter(adapter2);
        
        myspinner_Alignment = (Spinner) findViewById(R.id.spinner_oneBarCode_Alignment);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(
                this, R.array.textformatting_Alignment, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        myspinner_Alignment.setAdapter(adapter3);
        
        mybarcodeType = (Spinner) findViewById(R.id.spinner_oneBarCode_barcodeType);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.oneDBarcode, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mybarcodeType.setAdapter(adapter);
        
        mybarcodeType.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
	            int index = arg0.getSelectedItemPosition();
	            // storing string resources into Array
	            String[] str = getResources().getStringArray(R.array.oneDBarcode);
	            Toast.makeText(getBaseContext(),
	            		"You have selected : "+str[index],
	            		Toast.LENGTH_SHORT).show();
	            //--------------------------
	            switch(index) {
	            case 4:
	            	myimageView.setImageResource(R.drawable.code39);
	            	break;
	            case 5:
	            	myimageView.setImageResource(R.drawable.itf);
	            	break;
	            case 7:
	            	myimageView.setImageResource(R.drawable.code93);
	            	break;
	            case 8:
	            	myimageView.setImageResource(R.drawable.code128);
	            	break;
	            default :
	            	myimageView.setImageResource(R.drawable.itf);
	            	break;
	            }
	            
	    		switch(mybarcodeType.getSelectedItemPosition()) {
	        	case 0:
	        		myDialog("BarcodeType : UPC-A",
	        				"Data Length\n" +
	        				"11 <= n <= 12\n"+
	        				"\n" +
	        				"Data Range (ASCII)\n" +
	        				"48 <= d <= 57");
	        		break;
	        	case 1:
	        		myDialog("BarcodeType : UPC-E",
	        				"Data Length\n" +
	    	        		"11 <= n <= 12\n"+
	    	        		"\n" +
	        				"Data Range (ASCII)\n" +
	        				"48 <= d <= 57");
	        		break;
	        	case 2:
	        		myDialog("BarcodeType : JAN13 (EAN13)",
	        				"Data Length\n" +
	    	    	        "12 <= n <= 13\n"+
	    	    	        "\n" +
	        				"Data Range (ASCII)\n" +
	        				"48 <= d <= 57");
	        		break;
	        	case 3:
	        		myDialog("BarcodeType : JAN8 (EAN8)",
	        				"Data Length\n" +
	        				"7 <= n <= 8\n"+
	    	    	    	"\n" +
	        				"Data Range (ASCII)\n" +
	        				"48 <= d <= 57");
	        		break;
	        	case 4:
	        		myDialog("BarcodeType : CODE39",
	        				"Data Length\n" +
	    	        		"1 <= n <= 255\n"+
	    	    	    	"\n" +
	        				"Data Range (ASCII)\n" +
	        				"48 <= d <= 57");
	        		break;
	        	case 5:
	        		myDialog("BarcodeType : ITF",
	        				"Data Length\n" +
	    	        		"1 <= n <= 255\n"+
	    	    	    	"\n" +
	        				"Data Range (ASCII)\n" +
	        				"48 <= d <= 57,\n" +
	        				"65 <= d <= 90,\n" +
	        				"d = 32,36,37,43,45,46,47");
	        		break;
	        	case 6:
	        		myDialog("BarcodeType : CODABAR(NW7)",
	        				"Data Length\n" +
	    	        		"1 <= n <= 255\n"+
	    	    	    	"\n" +
	        				"Data Range (ASCII)\n" +
	        				"48 <= d <= 57,\n" +
	        				"65 <= d <= 68,\n" +
	        				"d = 36,43,45,46,47,58");
	        		break;
	        	case 7:
	        		myDialog("BarcodeType : CODE93",
	        				"Data Length\n" +
	    	        		"1 <= n <= 255\n"+
	    	    	    	"\n" +
	        				"Data Range (ASCII)\n" +
	        				"0 <= d <= 127");
	        		break;
	        	case 8:
	        		myDialog("BarcodeType : CODE128",
	        				"Data Length\n" +
	    	        		"2 <= n <= 255\n"+
	    	    	    	"\n" +
	        				"Data Range (ASCII)\n" +
	        				"0 <= d <= 127");
	        		break;
	        	}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
    }
    
    public void PrintText(View view)
    {
	    int res=0;
	    
	    if(Main.isLAN) {
	    	res=PrinterFunctionsLAN.PrintBarCode(
					Main.portName,
					Main.portSettings,
					myeditText_Data.getText().toString(),
					myspinner_HRIcharacters.getSelectedItemPosition(),
					Integer.parseInt(myeditText_Height.getText().toString()),
					Integer.parseInt(myeditText_Width.getText().toString()),
					myspinner_Alignment.getSelectedItemPosition(),
					mybarcodeType.getSelectedItemPosition()+65);
	    } else {
		    res=PrinterFunctions.PrintBarCode(
					Main.portName,
					Main.portSettings,
					myeditText_Data.getText().toString(),
					myspinner_HRIcharacters.getSelectedItemPosition(),
					Integer.parseInt(myeditText_Height.getText().toString()),
					Integer.parseInt(myeditText_Width.getText().toString()),
					myspinner_Alignment.getSelectedItemPosition(),
					mybarcodeType.getSelectedItemPosition()+65);
	    }
	    
	    switch(res) {
	    case 0:
	    	if(Main.isLAN) {
	    		PrinterFunctionsLAN.PreformCut(Main.portName,Main.portSettings,1);
	    	} else {
	    		PrinterFunctions.PreformCut(Main.portName,Main.portSettings,1);
	    	}
	    	break;
	    case (-2):
	    	myDialog("PrintBarCode","Data error.");
	    	break;
	    case (-3):
	    	myDialog("PrintBarCode","Data length error.");
	    	break;
	    }
    }
    
	public int myDialog(String Title,String Messages) {
		AlertDialog.Builder dialog = new AlertDialog.Builder(onedbarcodeActivity.this);
		
		dialog.setTitle(Title);
		dialog.setMessage(Messages);			
		
		dialog.setPositiveButton("OK",new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {}
		});
		
		dialog.show();
		
		return 0;
	}
    
    public void Help(View view)
    {
    	String helpString = 
    			"<body>" +
    	    	"<MainTitle>int PrintBarCode(String portName,<br/>" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"int portSettings,<br/>" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"String barcodeData,<br/>" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"int option,<br/>" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"int height,<br/>" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"int width,<br/>" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"int alignment,<br/>" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"int mod)</MainTitle><br/>"+
    	    	"<br/>"+
    			"<MainTitle><ColorBlue>String portName</ColorBlue></MainTitle><br/>"+
    			"Port name parameters<br/>"+
    			"<br/>"+
    			"<MainTitle><ColorBlue>int portSettings</ColorBlue></MainTitle><br/>"+
    			"Port settings parameters<br/>"+
    			"<br/>"+
    			"<MainTitle><ColorBlue>String barcodeData</ColorBlue></MainTitle><br/>"+
    			"<MainTitle><ColorBlue>int mod</ColorBlue></MainTitle><br/>"+
				"<table border=1 bordercolor=#000000 cellspacing=1>"+
				"<tr>"+
					"<th>mod</th>"+
					"<th>Bar Code System</th>"+
					"<th>barcodeData Length Range</th>"+
					"<th>barcodeData Data Range</th>"+
				"</tr>"+
				"<tr>"+
				"<td>65</td>"+
				"<td>UPC-A</td>"+
				"<td>11 <= n <= 12</td>"+
				"<td>48 <= d <= 57</td>"+
				"</tr>"+
				"<tr>"+
				"<td>66</td>"+
				"<td>UPC-E</td>"+
				"<td>11 <= n <= 12</td>"+
				"<td>48 <= d <= 57</td>"+
				"</tr>"+
				"<tr>"+
				"<td>67</td>"+
				"<td>JAN13 (EAN13)</td>"+
				"<td>12 <= n <= 13</td>"+
				"<td>48 <= d <= 57</td>"+
				"</tr>"+
				"<tr>"+
				"<td>68</td>"+
				"<td>JAN8 (EAN8)</td>"+
				"<td>7 <= n <= 8</td>"+
				"<td>48 <= d <= 57</td>"+
				"</tr>"+
				"<tr>"+
				"<td>69</td>"+
				"<td>CODE39</td>"+
				"<td>1 <= n <= 255</td>"+
				"<td>48 <= d <= 57,65 <= d <= 90,<br/>" +
				"d = 32,36,37,43,45,46,47</td>"+
				"</tr>"+
				"<tr>"+
				"<td>70</td>"+
				"<td>ITF</td>"+
				"<td>1 <= n <= 255 (even number)</td>"+
				"<td>48 <= d <= 57</td>"+
				"</tr>"+
				"<tr>"+
				"<td>71</td>"+
				"<td>CODABAR(NW7)</td>"+
				"<td>1 <= n <= 255</td>"+
				"<td>48 <= d <= 57,65 <= d <= 68,<br/>" +
				"d = 36,43,45,46,47,58</td>"+
				"</tr>"+
				"<tr>"+
				"<td>72</td>"+
				"<td>CODE93</td>"+
				"<td>1 <= n <= 255</td>"+
				"<td>0 <= d <= 127</td>"+
				"</tr>"+
				"<tr>"+
				"<td>73</td>"+
				"<td>CODE128</td>"+
				"<td>2 <= n <= 255</td>"+
				"<td>0 <= d <= 127</td>"+
				"</tr>"+
				"</table>"+
				"Note:The user must consider the quiet zone of the bar code<br/>" +
				"(left and right spaces beside the bar code).<br/>"+
				"<br/>"+
    			"<MainTitle><ColorBlue>int option</ColorBlue></MainTitle><br/>"+
    			"<table border=1 bordercolor=#000000 cellspacing=1>"+
		    	"<tr>"+
		    		"<th>option</th>"+
		    		"<th>Printing position</th>"+
		    	"</tr>"+
		    	"<tr>"+
		    		"<td>0, 48</td>"+
		    		"<td>Not printed</td>"+
		    	"</tr>"+
		    	"<tr>"+
		    		"<td>1, 49</td>"+
		    		"<td>Above the bar code</td>"+
	    		"</tr>"+
	    		"<tr>"+
		    		"<td>2, 50</td>"+
		    		"<td>Below the bar code</td>"+
	    		"</tr>"+
	    		"<tr>"+
	    		"<td>3, 51</td>"+
	    		"<td>Both above and below the bar code</td>"+
	    		"</tr>"+
	    		"</table>"+
	    		"<br/>"+
    			"<MainTitle><ColorBlue>int height</ColorBlue></MainTitle><br/>"+
    			"Selects the height of the bar code.<br/>"+
    			"Range: 1 <= height <= 255<br/>"+
    			"<br/>"+
    			"<MainTitle><ColorBlue>int width</ColorBlue></MainTitle><br/>"+
    			"Sets the horizontal size of the bar code.<br/>"+
    			"Range: 1 <= width <= 6<br/>"+
    			"<br/>"+
    			"<MainTitle><ColorBlue>int alignment</ColorBlue></MainTitle><br/>"+
    			"Aligns all the data in one line to the position specified by n as follows:<br/>"+
    			"<table border=1 bordercolor=#000000 cellspacing=1>"+
			    	"<tr>"+
			    		"<th>alignment</th>"+
			    		"<th>Justification</th>"+
			    	"</tr>"+
			    	"<tr>"+
			    		"<td>0, 48</td>"+
			    		"<td>Left justification</td>"+
			    	"</tr>"+
			    	"<tr>"+
			    		"<td>1, 49</td>"+
			    		"<td>Centering</td>"+
		    		"</tr>"+
		    		"<tr>"+
			    		"<td>2, 50</td>"+
			    		"<td>Right justification</td>"+
		    		"</tr>"+
	    		"</table>"+
    			"<br/>"+
				"<MainTitle><ColorGreen>Return message</ColorGreen></MainTitle><br/>"+
				"Successful return value of 0<br/>"+
				"Fail return value of -1<br/>"+
				"Data entry error return value of -2<br/>"+
				"Data length error return value of -3<br/>"+
    			"</body></html>";
		helpMessageActivity.SetMessage(helpString);
		
		Intent myIntent = new Intent(this, helpMessageActivity.class);
		startActivityFromChild(this, myIntent, 0);
    }
}
