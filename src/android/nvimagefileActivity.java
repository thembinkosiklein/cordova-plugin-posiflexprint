package com.pos.printer;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

public class nvimagefileActivity extends Activity {
	EditText myeditText_ImageURL1;
	EditText myeditText_ImageURL2;
	Spinner myspinner_Mode;
	ImageView myimageView_Image1;
	ImageView myimageView_Image2;
	Spinner myspinner_printNum;
	CheckBox checkBox_DefinesTwo;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nvimagefile);
        //--------------------------
        myeditText_ImageURL1=(EditText)findViewById(R.id.editText_nvimagefile_ImageURL1);
        myeditText_ImageURL2=(EditText)findViewById(R.id.editText_nvimagefile_ImageURL2);
        myimageView_Image1=(ImageView)findViewById(R.id.imageView_nvimagefile_Image1);
        myimageView_Image2=(ImageView)findViewById(R.id.imageView_nvimagefile_Image2);
        myeditText_ImageURL2.setEnabled(false);
        //--------------------------
        myspinner_printNum = (Spinner) findViewById(R.id.spinner_nvimagefile_printNum);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.imagefile_printNum, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        myspinner_printNum.setAdapter(adapter);
        
        myspinner_Mode = (Spinner) findViewById(R.id.spinner_nvimagefile_Mode);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(
                this, R.array.imagefile_Mode, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        myspinner_Mode.setAdapter(adapter2);
        //--------------------------
        checkBox_DefinesTwo=(CheckBox)findViewById(R.id.checkBox_nvimagefile_DefinesTwo);
        checkBox_DefinesTwo.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(checkBox_DefinesTwo.isChecked()) {
					myeditText_ImageURL2.setEnabled(true);
				} else {
					myeditText_ImageURL2.setEnabled(false);
				}
			}
		});
    }
    
    public void Define_imagefile(View view)
    {
    	try {
    		if(checkBox_DefinesTwo.isChecked()==false) {
    			Bitmap bm1=BitmapFactory.decodeFile(
	    			myeditText_ImageURL1.getText().toString());
    			myimageView_Image1.setImageBitmap(bm1); //app show
    			myimageView_Image2.setImageBitmap(null); //app show
    			if(Main.isLAN) {
    				PrinterFunctionsLAN.DefineNVBitImage(
    						Main.portName,
    						Main.portSettings,
    						myeditText_ImageURL1.getText().toString());
    			} else {
    				PrinterFunctions.DefineNVBitImage(
    						Main.portName,
    						Main.portSettings,
    						myeditText_ImageURL1.getText().toString());
    			}
    		} else {
    			Bitmap bm1=BitmapFactory.decodeFile(
    	    			myeditText_ImageURL1.getText().toString());
        		myimageView_Image1.setImageBitmap(bm1); //app show
    			Bitmap bm2=BitmapFactory.decodeFile(
    	    			myeditText_ImageURL2.getText().toString());
        		myimageView_Image2.setImageBitmap(bm2); //app show
        		if(Main.isLAN) {
        			PrinterFunctionsLAN.DefineNVBitImageTwo(
        					Main.portName,
        					Main.portSettings,
        					myeditText_ImageURL1.getText().toString(),
        					myeditText_ImageURL2.getText().toString());
        		} else {
        			PrinterFunctions.DefineNVBitImageTwo(
        					Main.portName,
        					Main.portSettings,
        					myeditText_ImageURL1.getText().toString(),
        					myeditText_ImageURL2.getText().toString());
        		}
    		}
    	} catch(Exception e) {}
    }
    
    public void PrintText_imagefile(View view)
    {
        int value_Mode=myspinner_Mode.getSelectedItemPosition();

        if(Main.isLAN) {
        	PrinterFunctionsLAN.PrintNVBitImage(
    				Main.portName,
    				Main.portSettings,
    				myspinner_printNum.getSelectedItemPosition()+1,
    				value_Mode);

    		PrinterFunctionsLAN.PreformCut(Main.portName,Main.portSettings,1);
        } else {
	        PrinterFunctions.PrintNVBitImage(
					Main.portName,
					Main.portSettings,
					myspinner_printNum.getSelectedItemPosition()+1,
					value_Mode);
	
			PrinterFunctions.PreformCut(Main.portName,Main.portSettings,1);
        }
    }
    
    public void Help_imagefile(View view)
    {
    	String helpString = 
    			"<body>" +
				"<MainTitle>int DefineNVBitImage(String portName,int portSettings,String ImgUrl)</MainTitle><br/>"+
				"<br/>"+
				"<MainTitle><ColorBlue>String portName</ColorBlue></MainTitle><br/>"+
				"Port name parameters<br/>"+
				"<br/>"+
				"<MainTitle><ColorBlue>int portSettings</ColorBlue></MainTitle><br/>"+
				"Port settings parameters<br/>"+
				"<br/>"+
				"<MainTitle><ColorBlue>String ImgUrl</ColorBlue></MainTitle><br/>"+
				"Image URL address.<br/>"+
				"<br/>"+
				"<MainTitle><ColorGreen>Return message</ColorGreen></MainTitle><br/>"+
				"Successful return value of 0<br/>"+
				"Fail return value of -1<br/>"+
				"<br/>"+
				"<MainTitle>int DefineNVBitImageTwo(String portName,int portSettings,String ImgUrl1,String ImgUrl2)</MainTitle><br/>"+
				"<br/>"+
				"<MainTitle><ColorBlue>String portName</ColorBlue></MainTitle><br/>"+
				"Port name parameters<br/>"+
				"<br/>"+
				"<MainTitle><ColorBlue>int portSettings</ColorBlue></MainTitle><br/>"+
				"Port settings parameters<br/>"+
				"<br/>"+
				"<MainTitle><ColorBlue>String ImgUrl1</ColorBlue></MainTitle><br/>"+
				"First image URL address.<br/>"+
				"<br/>"+
				"<MainTitle><ColorBlue>String ImgUrl2</ColorBlue></MainTitle><br/>"+
				"Second image URL address.<br/>"+
				"<br/>"+
				"<MainTitle><ColorGreen>Return message</ColorGreen></MainTitle><br/>"+
				"Successful return value of 0<br/>"+
				"Fail return value of -1<br/>"+
				"<br/>"+
				"<MainTitle>int PrintNVBitImage(String portName,int portSettings,int n,int m)</MainTitle><br/>"+
				"<br/>"+
				"<MainTitle><ColorBlue>String portName</ColorBlue></MainTitle><br/>"+
				"Port name parameters<br/>"+
				"<br/>"+
				"<MainTitle><ColorBlue>int portSettings</ColorBlue></MainTitle><br/>"+
				"Port settings parameters<br/>"+
				"<br/>"+
				"<MainTitle><ColorBlue>int n</ColorBlue></MainTitle><br/>"+
				"n is the number of the NV bit image.<br/>"+
				"<br/>"+
				"<MainTitle><ColorBlue>int m</ColorBlue></MainTitle><br/>"+
				"<table border=1 bordercolor=#000000 cellspacing=1>"+
					"<tr>"+
						"<th>m</th>"+
						"<th>Mode</th>"+
						"<th>Vertical dot density</th>"+
						"<th>Horizontal dot density</th>"+
					"</tr>"+
					"<tr>"+
						"<td>0, 48</td>"+
						"<td>Normal</td>"+
						"<td>203 dpi</td>"+
						"<td>203 dpi</td>"+
					"</tr>"+
					"<tr>"+
						"<td>1, 49</td>"+
						"<td>Double-width</td>"+
						"<td>203 dpi</td>"+
						"<td>203/2 dpi</td>"+
					"</tr>"+
					"<tr>"+
						"<td>2, 50</td>"+
						"<td>Double-height</td>"+
						"<td>203/2 dpi</td>"+
						"<td>203 dpi</td>"+
					"</tr>"+
					"<tr>"+
						"<td>3, 51</td>"+
						"<td>Quadruple</td>"+
						"<td>203/2 dpi</td>"+
						"<td>203/2 dpi</td>"+
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
