package ar.fi.uba.trackerman.tasks;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import ar.fi.uba.trackerman.activities.OrderActivity;
import ar.fi.uba.trackerman.domains.Order;
import ar.fi.uba.trackerman.domains.OrderItem;
import ar.fi.uba.trackerman.utils.DateUtils;

/**
 * Created by plucadei on 31/3/16.
 */
public class ConfirmOrderTask extends AbstractTask<String,Void,Order,OrderActivity> {

    public ConfirmOrderTask(OrderActivity activity) {
        super(activity);
    }

    @Override
    protected Order doInBackground(String... params) {
        String orderId= params[0];
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        String url = "/v1/orders/"+orderId;
        String body = "{\"status\": \"confirmed\"}";
        return (Order) restClient.put(url,body,headers);
    }

    @Override
    public Object readResponse(String json) throws JSONException {
        JSONObject orderJson = new JSONObject(json);
        return Order.fromJson(orderJson);
    }

    @Override
    protected void onPostExecute(Order order) {
        OrderConfirmer reciver= weakReference.get();
        if(reciver!=null){
            reciver.afterOrderConfirmed(order);
        }else{
            Log.w(this.getClass().getCanonicalName(),"Adapter no longer available!");
        }
    }

    public interface OrderConfirmer {
        public void afterOrderConfirmed(Order order);
    }
}
