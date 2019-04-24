package com.pos.printer;

import java.io.UnsupportedEncodingException;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

public class cntextformattingActivity extends Activity {
	CheckBox mycheckBox_Underline;
	CheckBox mycheckBox_InvertColor;
	CheckBox mycheckBox_Emphasized;
	CheckBox mycheckBox_UpsideDown;
	Spinner myspinner_Height;
	Spinner myspinner_Width;
	Spinner myspinner_Alignment;
	EditText myeditText_LeftMargin;
	EditText myeditText_TextToPrint;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cntextformatting);
      //--------------------------
        mycheckBox_Underline=(CheckBox)findViewById(R.id.checkBox_cntextformatting_Underline);
        mycheckBox_InvertColor=(CheckBox)findViewById(R.id.checkBox_cntextformatting_InvertColor);
        mycheckBox_Emphasized=(CheckBox)findViewById(R.id.checkBox_cntextformatting_Emphasized);
        mycheckBox_UpsideDown=(CheckBox)findViewById(R.id.checkBox_cntextformatting_UpsideDown);
        myeditText_LeftMargin=(EditText)findViewById(R.id.editText_cntextformatting_LeftMargin);
        myeditText_TextToPrint=(EditText)findViewById(R.id.editText_cntextformatting_TextToPrint);
        //--------------------------
        myspinner_Height = (Spinner) findViewById(R.id.spinner_cntextformatting_Height);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.textformatting_Height, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        myspinner_Height.setAdapter(adapter);
        //--------------------------
        myspinner_Width = (Spinner) findViewById(R.id.spinner_cntextformatting_Width);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(
                this, R.array.textformatting_Width, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        myspinner_Width.setAdapter(adapter2);
        //--------------------------
        myspinner_Alignment = (Spinner) findViewById(R.id.spinner_cntextformatting_Alignment);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(
                this, R.array.textformatting_Alignment, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        myspinner_Alignment.setAdapter(adapter3);
    }
    
    public void PrintText_cntextformatting(View view)
    {
    	int value_Underline=0;
    	int value_InvertColor=0;
    	int value_Emphasized=0;
    	int value_UpsideDown=0;
    	int value_Height=0;
    	int value_Width=0;
    	int value_Alignment=0;
    	int value_LeftMargin=0;
    	
    	if(mycheckBox_Underline.isChecked()) value_Underline=1;
		else value_Underline=0;
		
		if(mycheckBox_InvertColor.isChecked()) value_InvertColor=1;
		else value_InvertColor=0;
		
		if(mycheckBox_Emphasized.isChecked()) value_Emphasized=1;
		else value_Emphasized=0;
		
		if(mycheckBox_UpsideDown.isChecked()) value_UpsideDown=1;
		else value_UpsideDown=0;
		
		value_Height=myspinner_Height.getSelectedItemPosition();
		
		value_Width=myspinner_Width.getSelectedItemPosition();
		
		value_Alignment=myspinner_Alignment.getSelectedItemPosition();
		
		try{
			value_LeftMargin=Integer.parseInt(myeditText_LeftMargin.getText().toString());
		} catch(Exception e) {
			value_LeftMargin=0;
		}
		
		String str_mybig5="";
		byte mybig5[]=new byte[1];
		try {
			mybig5=myeditText_TextToPrint.getText().toString().getBytes("big5");
			//mybig5=myeditText_TextToPrint.getText().toString().getBytes("GB2312");
			
			char chr_mybig5[]=new char[mybig5.length];
			for(int i=0;i<mybig5.length;i++) {
				chr_mybig5[i]=(char)mybig5[i];
			}
			
			str_mybig5=String.valueOf(chr_mybig5);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(Main.isLAN) {
			PrinterFunctionsLAN.PrintTextKanji(
					Main.portName,
					Main.portSettings,
					value_Underline,
					value_InvertColor,
					value_Emphasized,
					value_UpsideDown,
					value_Height,
					value_Width,
					value_LeftMargin,
					value_Alignment,
					mybig5); //LAN的資料要輸入byte[]
			
			PrinterFunctionsLAN.PreformCut(Main.portName,Main.portSettings,1);
		} else {
			PrinterFunctions.PrintTextKanji(
					Main.portName,
					Main.portSettings,
					value_Underline,
					value_InvertColor,
					value_Emphasized,
					value_UpsideDown,
					value_Height,
					value_Width,
					value_LeftMargin,
					value_Alignment,
					str_mybig5);//資料要輸入String
			
			PrinterFunctions.PreformCut(Main.portName,Main.portSettings,1);
		}
    }
    
    public void Help_cntextformatting(View view)
    {
    	String helpString = 
    			"<body>" +
    	    	"<MainTitle>int PrintTextKanji(String portName,<br/>" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"int portSettings,<br/>" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"int underline,<br/>" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"int invertColor,<br/>" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"int emphasized,<br/>" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"int upsideDown,<br/>" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"int heightExpansion,<br/>" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"int widthExpansion,<br/>" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"int leftMargin,<br/>" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"int alignment,<br/>" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
    	    	"String textData)</MainTitle><br/>"+
    	    	"<br/>"+
    			"<MainTitle><ColorBlue>String portName</ColorBlue></MainTitle><br/>"+
    			"Port name parameters<br/>"+
    			"<br/>"+
    			"<MainTitle><ColorBlue>int portSettings</ColorBlue></MainTitle><br/>"+
    			"Port settings parameters<br/>"+
    			"<br/>"+
    			"<MainTitle><ColorBlue>int underline</ColorBlue></MainTitle><br/>"+
    			"Turn Underline Mode On/Off<br/>"+
    			"On = 1<br/>"+
    			"Off = 0<br/>"+
    			"<br/>"+
    			"<MainTitle><ColorBlue>int invertColor</ColorBlue></MainTitle><br/>"+
    			"Turn White Black Reverse Printing Mode On/Off<br/>"+
    			"On = 1<br/>"+
    			"Off = 0<br/>"+
    			"<br/>"+
    			"<MainTitle><ColorBlue>int emphasized</ColorBlue></MainTitle><br/>"+
    			"Turn Emphasized Mode On/Off<br/>"+
    			"On = 1<br/>"+
    			"Off = 0<br/>"+
    			"<br/>"+
    			"<MainTitle><ColorBlue>int upsideDown</ColorBlue></MainTitle><br/>"+
    			"Turn Upside Down Printing Mode On/Off<br/>"+
    			"On = 1<br/>"+
    			"Off = 0<br/>"+
    			"<br/>"+
    			"<MainTitle><ColorBlue>int heightExpansion</ColorBlue></MainTitle><br/>"+
    			"<MainTitle><ColorBlue>int widthExpansion</ColorBlue></MainTitle><br/>"+
    			"<table border=1 bordercolor=#000000 cellspacing=1>"+
			    	"<tr>"+
			    		"<th>Height/Width</th>"+
			    		"<th>Enlargement</th>"+
			    	"</tr>"+
			    	"<tr>"+
			    		"<td>0</td>"+
			    		"<td>1 time (standard)</td>"+
			    	"</tr>"+
			    	"<tr>"+
			    		"<td>1</td>"+
			    		"<td>2 times</td>"+
		    		"</tr>"+
		    		"<tr>"+
			    		"<td>2</td>"+
			    		"<td>3 times</td>"+
		    		"</tr>"+
		    		"<tr>"+
			    		"<td>3</td>"+
			    		"<td>4 times</td>"+
		    		"</tr>"+
		    		"<tr>"+
			    		"<td>4</td>"+
			    		"<td>5 times</td>"+
		    		"</tr>"+
		    		"<tr>"+
			    		"<td>5</td>"+
			    		"<td>6 times</td>"+
		    		"</tr>"+
		    		"<tr>"+
			    		"<td>6</td>"+
			    		"<td>7 times</td>"+
		    		"</tr>"+
		    		"<tr>"+
			    		"<td>7</td>"+
			    		"<td>8 times</td>"+
		    		"</tr>"+
    			"</table>"+
    			"<br/>"+
    			"<MainTitle><ColorBlue>int leftMargin</ColorBlue></MainTitle><br/>"+
    			"Set Left Margin<br/>"+
    			"Range: 0 <= leftMargin <= 65535<br/>"+
    			"<br/>"+
    			"<MainTitle><ColorBlue>int alignment</ColorBlue></MainTitle><br/>"+
    			"Aligns all the data in one line to the position specified by n as follows:<br/>"+
    			"<table border=1 bordercolor=#000000 cellspacing=1>"+
			    	"<tr>"+
			    		"<th>n</th>"+
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
    			"<MainTitle><ColorBlue>String textData</ColorBlue></MainTitle><br/>"+
    			"This feature sends raw text with decoration as defined above to the printer.<br/>"+
    			"<br/>"+
				"<MainTitle><ColorGreen>Return message</ColorGreen></MainTitle><br/>"+
				"Successful return value of 0<br/>"+
				"Fail return value of -1<br/>"+
    			"</body>";
		helpMessageActivity.SetMessage(helpString);
		
		Intent myIntent = new Intent(this, helpMessageActivity.class);
		startActivityFromChild(this, myIntent, 0);
    }
}
