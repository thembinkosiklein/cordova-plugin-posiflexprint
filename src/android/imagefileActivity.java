package com.pos.printer;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

public class imagefileActivity extends Activity {
	EditText myeditText_ImageURL;
	ImageView myimageView;
	Spinner myspinner_Mode;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imagefile);
        //--------------------------
        myeditText_ImageURL = (EditText) findViewById(R.id.editText_imagefile_ImageURL);
        myimageView = (ImageView) findViewById(R.id.imageView_imagefile_Image);
        //--------------------------
        myspinner_Mode = (Spinner) findViewById(R.id.spinner_imagefile_Mode);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(
                this, R.array.imagefile_Mode, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        myspinner_Mode.setAdapter(adapter2);
    }
    
    public void PrintText_imagefile(View view)
    {
    	try {
	    	Bitmap bm=BitmapFactory.decodeFile(
	    			myeditText_ImageURL.getText().toString());
	    	myimageView.setImageBitmap(bm); //app show
	        	
	    	int value_Mode=myspinner_Mode.getSelectedItemPosition();
	    	
	    	if(Main.isLAN) {
	    		PrinterFunctionsLAN.PrintBitmapImage(
						Main.portName,
						Main.portSettings,
						value_Mode,
						myeditText_ImageURL.getText().toString());
				
				PrinterFunctionsLAN.PreformCut(Main.portName,Main.portSettings,1);
	    	} else {
				PrinterFunctions.PrintBitmapImage(
						Main.portName,
						Main.portSettings,
						value_Mode,
						myeditText_ImageURL.getText().toString());
				
				PrinterFunctions.PreformCut(Main.portName,Main.portSettings,1);
	    	}
    	} catch(Exception e) {}
    }
    
    public void Help_imagefile(View view)
    {
    	String helpString = 
    			"<body>" +
    			"<MainTitle>int PrintBitmapImage(String portName,int portSettings,int m,String ImgUrl)</MainTitle><br/>"+
    	    	"<br/>"+
    			"<MainTitle><ColorBlue>String portName</ColorBlue></MainTitle><br/>"+
    			"Port name parameters<br/>"+
    			"<br/>"+
    			"<MainTitle><ColorBlue>int portSettings</ColorBlue></MainTitle><br/>"+
    			"Port settings parameters<br/>"+
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
    			"dpi: dots per 25.4 mm (1\")<br/>"+
    			"<br/>"+
				"<MainTitle><ColorBlue>String ImgUrl</ColorBlue></MainTitle><br/>"+
				"Image URL address.<br/>"+
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
