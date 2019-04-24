package cordova.plugin.posiflexprint;

import com.pos.printer.*;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class echoes a string called from JavaScript.
 */
public class PosiflexPRINT extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("print")) {
            PrinterFunctions.PrintSampleReceipt("USB:", 0);
            return true;
        }
        return false;
    }
}
