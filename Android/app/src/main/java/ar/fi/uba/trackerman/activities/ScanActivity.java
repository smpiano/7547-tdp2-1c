package ar.fi.uba.trackerman.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import ar.fi.uba.trackerman.domains.Client;
import fi.uba.ar.soldme.R;

public class ScanActivity extends AppCompatActivity {

    public static boolean isEmulator() {
        return Build.FINGERPRINT.startsWith("generic")
                || Build.FINGERPRINT.startsWith("unknown")
                || Build.MODEL.contains("google_sdk")
                || Build.MODEL.contains("Emulator")
                || Build.MODEL.contains("Android SDK built for x86")
                || Build.MANUFACTURER.contains("Genymotion")
                || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic"))
                || "google_sdk".equals(Build.PRODUCT);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        try {

            Intent intent = new Intent("com.google.zxing.client.android.SCAN");
            intent.putExtra("SCAN_MODE", "QR_CODE_MODE"); // "PRODUCT_MODE for bar codes

            startActivityForResult(intent, 0);

        } catch (Exception e) {

            if (isEmulator()) {
                Toast.makeText(getApplicationContext(), "Desde emulador no se accede al scanner", Toast.LENGTH_LONG).show();
                finish();
            } else {
                Uri marketUri = Uri.parse("market://details?id=com.google.zxing.client.android");
                Intent marketIntent = new Intent(Intent.ACTION_VIEW,marketUri);
                startActivity(marketIntent);
            }

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {

            if (resultCode == RESULT_OK) {
                String contents = data.getStringExtra("SCAN_RESULT");
                //Toast.makeText(getApplicationContext(), contents, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, ClientActivity.class);
                intent.putExtra(Intent.EXTRA_UID, Long.parseLong(contents));
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
            if(resultCode == RESULT_CANCELED){
                Toast.makeText(getApplicationContext(), "Scan Cancelado", Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }
}
