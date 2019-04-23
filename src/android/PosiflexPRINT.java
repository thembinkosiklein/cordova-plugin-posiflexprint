package cordova.plugin.posiflexprint;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * This class echoes a string called from JavaScript.
 */
public class PosiflexPRINT extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("print")) {
            String message = args.getString(0);
            this.print(message, callbackContext);
            return true;
        }
        return false;
    }

    private void print(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            // PrinterFunctions.PrintSampleReceipt("USB:", 0);
            PrintSampleReceipt("USB:", 0);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }

    public static native int PrintSampleReceipt(String portName,int portSettings);
}
