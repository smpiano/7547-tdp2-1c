package ar.fi.uba.trackerman.exceptions;

import android.util.Log;

/**
 * Created by smpiano on 4/19/16.
 */
public class ApiCallException extends RuntimeException {

    private ApiCallException(String msg, Boolean logError) {
        super(msg);
        if (logError) Log.e("api_call_error", msg, this);
    }

    public ApiCallException(String url) {
        this("Error en el servidor, invocando [" + url + "]", true);
    }

    public ApiCallException(String url, Integer status) {
        this("Error en el servidor, invocando [" + url + "] status [" + status + "]", true);
    }
}
