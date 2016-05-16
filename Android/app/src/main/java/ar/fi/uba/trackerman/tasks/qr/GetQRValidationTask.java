package ar.fi.uba.trackerman.tasks.qr;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import ar.fi.uba.trackerman.domains.QRValidationWrapper;
import ar.fi.uba.trackerman.exceptions.BusinessException;
import ar.fi.uba.trackerman.exceptions.QRValidationException;
import ar.fi.uba.trackerman.exceptions.ServerErrorException;
import ar.fi.uba.trackerman.tasks.AbstractTask;
import ar.fi.uba.trackerman.utils.AppSettings;
import ar.fi.uba.trackerman.utils.ShowMessage;

/**
 * Created by guido on 15/05/16.
 */

public class GetQRValidationTask extends AbstractTask<String,Void,QRValidationWrapper,AppCompatActivity> {

    public GetQRValidationTask(AppCompatActivity validation) {
        super(validation);
    }

    public QRValidationWrapper getQRValidation(String idSeller, String idClient, String lat, String lon) {
        QRValidationWrapper qrValidationWrapper = null;
        String body = "{\"client_id\": "+ idClient +",\"seller_id\":"+ idSeller +",\"lat\": "+ lat +",\"lon\": "+ lon +",\"distance\":300000}";
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        String url = "/v1/scanqr/validate";

        try {
            qrValidationWrapper = (QRValidationWrapper) restClient.post(url, body, headers);
        } catch (QRValidationException e) {
            //ShowMessage.showSnackbarSimpleMessage(weakReference.get().getCurrentView(), "Error en la validación QR");
            Log.e("QRValidationException", e.getMessage(), e);
        } catch (BusinessException e) {
            Log.e("business_error", e.getMessage(), e);
        } catch (ServerErrorException e) {
            Log.e("server_error", e.getMessage(), e);
        }
        return qrValidationWrapper;
    }

    @Override
    public Object readResponse(String json) throws JSONException {
        JSONObject jsonObject = new JSONObject(json);
        return QRValidationWrapper.fromJson(jsonObject);
    }

    @Override
    protected QRValidationWrapper doInBackground(String... params) {
        // AppSettings.getSellerId()
        if (params.length == 4) {
            String idClient = params[0];
            String idSeller = params[1];
            String lat = params[2];
            String lon = params[3];
            return this.getQRValidation(idSeller, idClient, lat, lon); // paso el idClient
        }
        return null;
    }

    @Override
    protected void onPostExecute(QRValidationWrapper qrValidationWrapper) {
//        super.onPostExecute(client);
        if(qrValidationWrapper != null){
            ((QRValidationResponse) weakReference.get()).afterQRValidationResponse(qrValidationWrapper);
        } else {
            // weakReference.get().showSnackbarSimpleMessage("No se puede obtener info del cliente");
        }

    }

    public interface QRValidationResponse {
        public void afterQRValidationResponse(QRValidationWrapper qrValidationWrapper);
    }
}