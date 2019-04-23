package com.pos.printer;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Main extends Activity {
	TextView myeditText_PortName;
	//--------------------------
	public static boolean isLAN=false; //�O�_�ϥκ���
	//public static String portName="COM:3"; //******
	//public static int portSettings=115200; //******
	public static String portName="USB:"; //******
	public static int portSettings=0; //******
	//public static String portName="LAN:192.168.6.2"; //******
	//public static int portSettings=9100; //******
	//public static String portName="WIFI:192.168.23.123"; //******
	//public static int portSettings=9100; //******
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //--------------------------
        myeditText_PortName=(TextView)findViewById(R.id.textView_PortName);
        //--------------------------
        //PrinterFunctions.PortDiscovery(Main.portName,Main.portSettings); //******
    }
    
    public void PortDiscovery(View view)
    {
    	final EditText editPortName = new EditText(view.getContext());
    	editPortName.setText(portName); //******
    	
    	final EditText editPortName2 = new EditText(view.getContext());
    	editPortName2.setText(String.valueOf(portSettings)); //******
    	
    	final RadioButton RadioButton_JAVA = new RadioButton(view.getContext());
    	final RadioButton RadioButton_CPP = new RadioButton(view.getContext());
		RadioButton_JAVA.setText("JAVA Code (LanPP.jar)");
		RadioButton_CPP.setText("C++ Code (libPosPPdrv.so)");
		final RadioGroup RadioGroup_NetworkProgram = new RadioGroup(view.getContext());
		RadioGroup_NetworkProgram.addView(RadioButton_JAVA);
		RadioGroup_NetworkProgram.addView(RadioButton_CPP);

        new AlertDialog.Builder(view.getContext())
		.setTitle("Please Input IP Address or Port Name")
		.setView(editPortName)
		.setPositiveButton("OK", new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface dialog, int button)
			{
				TextView portNameField = (TextView)findViewById(R.id.textView_PortName);
				portNameField.setText(editPortName.getText().toString());
    			
				portName=editPortName.getText().toString();
    			
    	        SharedPreferences pref = getSharedPreferences("pref", MODE_WORLD_READABLE | MODE_WORLD_WRITEABLE);
    	        Editor editor = pref.edit();
    			editor.putString("m_portName", portNameField.getText().toString());
    			editor.commit();
    			//--------------------------
		    	if(portName.length()>0) {
		    		AlertDialog.Builder dialog2 = new AlertDialog.Builder(Main.this);
		    		
		    		dialog2.setTitle("Please Input Port Settings Parameters");
		    		dialog2.setView(editPortName2);
		    		
		    		dialog2.setPositiveButton("OK",new DialogInterface.OnClickListener() {
		    			@Override
		    			public void onClick(DialogInterface dialog, int which) {
		    				try {
		    					portSettings=Integer.parseInt(editPortName2.getText().toString());
		    				} catch(Exception e) {}
		    				//--------------------------
		    				//��ܨϥΪ�����
		    				if(portName.substring(0, 3).equals("LAN") || portName.substring(0, 4).equals("WIFI")) {
		    					AlertDialog.Builder dialog3 = new AlertDialog.Builder(Main.this);
		    					dialog3.setTitle("Use of the network program");
		    					RadioButton_JAVA.setChecked(true);
		    					RadioButton_CPP.setChecked(false);
					        	dialog3.setView(RadioGroup_NetworkProgram);
					        	dialog3.setPositiveButton("OK",new DialogInterface.OnClickListener() {
					    			@Override
					    			public void onClick(DialogInterface dialog, int which) {
					    				if(RadioButton_JAVA.isChecked()) isLAN=true;
					    				else isLAN=false;
					    				//--------------------------
					    				int resOpen=(-1);
					    				AlertDialog.Builder dialog4 = new AlertDialog.Builder(Main.this);
					    				if(isLAN) {
					    					resOpen=PrinterFunctionsLAN.PortDiscovery(Main.portName,Main.portSettings);
					    				} else {
					    					resOpen=PrinterFunctions.PortDiscovery(Main.portName,Main.portSettings);
					    				}
								        if(resOpen==0) {
								        	dialog4.setTitle("PortDiscovery");
								        	dialog4.setMessage("ok.");
								        	dialog4.setPositiveButton("OK",new DialogInterface.OnClickListener() {
								    			@Override
								    			public void onClick(DialogInterface dialog, int which) {}
								    		});
								        	dialog4.show();
								        } else {
								        	dialog4.setTitle("PortDiscovery");
								        	dialog4.setMessage("fail.");			
								        	dialog4.setPositiveButton("OK",new DialogInterface.OnClickListener() {
								    			@Override
								    			public void onClick(DialogInterface dialog, int which) {}
								    		});
								        	dialog4.show();
								        }
					    			}
					    		});
					        	dialog3.show();
		    				} else {
		    					isLAN=false;
			    				//--------------------------
		    					int resOpen2=(-1);
			    				AlertDialog.Builder dialog5 = new AlertDialog.Builder(Main.this);
			    				if(isLAN) {
			    					resOpen2=PrinterFunctionsLAN.PortDiscovery(Main.portName,Main.portSettings);
			    				} else {
			    					resOpen2=PrinterFunctions.PortDiscovery(Main.portName,Main.portSettings);
			    				}
						        if(resOpen2==0) {
						        	dialog5.setTitle("PortDiscovery");
						        	dialog5.setMessage("ok.");
						        	dialog5.setPositiveButton("OK",new DialogInterface.OnClickListener() {
						    			@Override
						    			public void onClick(DialogInterface dialog, int which) {}
						    		});
						        	dialog5.show();
						        } else {
						        	dialog5.setTitle("PortDiscovery");
						        	dialog5.setMessage("fail.");			
						        	dialog5.setPositiveButton("OK",new DialogInterface.OnClickListener() {
						    			@Override
						    			public void onClick(DialogInterface dialog, int which) {}
						    		});
						        	dialog5.show();
						        }
		    				}
		    			}
		    		});
		    		dialog2.show();
		        }
			}
		})
		.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface dialog, int button)
			{}
		})
		.show();
    }
    
    public void ClosePort(View view)
    {
    	int res=(-1);
    	if(isLAN) {
    		res=PrinterFunctionsLAN.ClosePort(Main.portName);
    	} else {
    		res=PrinterFunctions.ClosePort(Main.portName);
    	}
    	
    	((TextView)findViewById(R.id.textView_PortName)).setText("");
    	portName="USB:"; //******
    	portSettings=0; //******
    	
    	AlertDialog.Builder dialog1 = new AlertDialog.Builder(view.getContext());
    	dialog1.setTitle("ClosePort");
    	if(res==0) {
    		dialog1.setMessage("ok.");
    	} else {
    		dialog1.setMessage("fail.");			
    	}
    	dialog1.setPositiveButton("OK",new DialogInterface.OnClickListener() {
    			@Override
    			public void onClick(DialogInterface dialog, int which) {}
    		});
    	dialog1.show();
    }
    
    public void GetStatus(View view)
    {
    	Intent myIntent=new Intent(view.getContext(),getstatusActivity.class);
    	startActivityForResult(myIntent, 0);
    }
    
    public void SampleReceipt(View view)
    {
    	Intent myIntent=new Intent(view.getContext(),samplereceiptActivity.class);
    	startActivityForResult(myIntent, 0);
    }
    
    public void OpenCashDrawer(View view)
    {
    	Intent myIntent=new Intent(view.getContext(),opencashdrawerActivity.class);
    	startActivityForResult(myIntent, 0);
    }
    
    public void oneDBarcodes(View view)
    {
    	Intent myIntent=new Intent(view.getContext(),onedbarcodeActivity.class);
    	startActivityForResult(myIntent, 0);
    }
    
    public void twoDBarcodes(View view)
    {
    	Intent myIntent=new Intent(view.getContext(),twodbarcodeActivity.class);
    	startActivityForResult(myIntent, 0);
    }
    
    public void Cut(View view)
    {
    	Intent myIntent=new Intent(view.getContext(),cutActivity.class);
    	startActivityForResult(myIntent, 0);
    }
    
    public void TextFormatting(View view)
    {
    	Intent myIntent=new Intent(view.getContext(),textformattingActivity.class);
    	startActivityForResult(myIntent, 0);
    }
    
    public void CnTextFormatting(View view)
    {
    	Intent myIntent=new Intent(view.getContext(),cntextformattingActivity.class);
    	startActivityForResult(myIntent, 0);
    }
    
    public void ImageFile(View view)
    {
    	Intent myIntent=new Intent(view.getContext(),imagefileActivity.class);
    	startActivityForResult(myIntent, 0);
    }
    
    public void NVImageFile(View view)
    {
    	Intent myIntent=new Intent(view.getContext(),nvimagefileActivity.class);
    	startActivityForResult(myIntent, 0);
    }
    
    public void PageMode(View view)
    {
    	Intent myIntent=new Intent(view.getContext(),pagemodeActivity.class);
    	startActivityForResult(myIntent, 0);
    }
    
    public void Help_main(View view)
    {
    	String helpString = 
    			"<body>" +
    			"<MainTitle>int PortDiscovery(String portName,int portSettings)</MainTitle><br/>"+
    	    	"<br/>"+
    			"<SubTitle><ColorBlue>IF USING COM PORT</ColorBlue></SubTitle><br/>"+
    			"<SubTitle>Port Name Parameters</SubTitle><br/>"+
    			"<StandardItalic>COM:3</StandardItalic><br/>"+
    			"Enter your COM port number<br/>"+
    			"<SubTitle>Port Settings Parameters</SubTitle><br/>"+
    			"<StandardItalic>115200</StandardItalic><br/>"+
    			"Enter your baudrate<br/>"+
    			"<br/>"+
    			"<SubTitle><ColorBlue>IF USING USB</ColorBlue></SubTitle><br/>"+
    			"<SubTitle>Port Name Parameters</SubTitle><br/>"+
    			"<StandardItalic>USB: </StandardItalic>(No value)<br/>"+
    			"<SubTitle>Port Settings Parameters</SubTitle><br/>"+
    			"Default value is 0<br/>"+
    			"<br/>"+
    			"<SubTitle><ColorBlue>IF USING LAN</ColorBlue></SubTitle><br/>"+
    			"<SubTitle>Port Name Parameters</SubTitle><br/>"+
    			"<StandardItalic>LAN:192.168.2.11</StandardItalic><br/>"+
    			"Enter your IP address<br/>"+
    			"<SubTitle>Port Settings Parameters</SubTitle><br/>"+
    			"<StandardItalic>9100</StandardItalic><br/>"+
    			"Enter your port number<br/>"+
    			"<br/>"+
    			"<SubTitle><ColorBlue>IF USING WIFI</ColorBlue></SubTitle><br/>"+
    			"<SubTitle>Port Name Parameters</SubTitle><br/>"+
    			"<StandardItalic>WIFI:192.168.2.22</StandardItalic><br/>"+
    			"Enter your IP address<br/>"+
    			"<SubTitle>Port Settings Parameters</SubTitle><br/>"+
    			"<StandardItalic>9100</StandardItalic><br/>"+
    			"Enter your port number<br/>"+
    			"<br/>"+
    			"<br/>"+
    			"<br/>"+
    			"<StandardItalic>Version Number 1.7</StandardItalic><br/>"+
    			"</body></html>";
		helpMessageActivity.SetMessage(helpString);
		
		Intent myIntent = new Intent(this, helpMessageActivity.class);
		startActivityFromChild(this, myIntent, 0);
    }
}
