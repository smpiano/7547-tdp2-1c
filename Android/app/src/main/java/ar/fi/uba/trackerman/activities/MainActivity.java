package ar.fi.uba.trackerman.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ar.fi.uba.trackerman.domains.Order;
import ar.fi.uba.trackerman.tasks.GetDraftOrdersTask;
import ar.fi.uba.trackerman.tasks.GetOrderTask;
import ar.fi.uba.trackerman.utils.AppSettings;
import fi.uba.ar.soldme.R;

public class MainActivity extends AppCompatActivity implements GetDraftOrdersTask.DraftOrdersValidation {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ListView mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // Set the adapter for the list view
        String[] options={getString(R.string.clients),getString(R.string.products)};

        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, options));
        // Set the list's click listener
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    openMyClientsActivity(null);
                } else if (position == 1) {
                    openProductsActivity(null);
                } else {
                    openOrdersActivity(null);
                }
            }
        });

        new GetDraftOrdersTask(this).execute(String.valueOf(AppSettings.getVendorId()));

    }


    @Override
    public void setDraftOrders(List<Order> orders) {
        TextView message = (TextView) findViewById(R.id.client_detail_address);
        if (orders.size() > 0) {
            message.setText("Tienes un pedido Activo!");
        } else {
            message.setText("No hay pedido en curso");
        }
    }

    public void openMyClientsActivity(View view) {
        Intent intent = new Intent(this, MyClientsActivity.class);
        startActivity(intent);
    }

    public void openProductsActivity(View view) {
        Intent intent = new Intent(this, ProductsListActivity.class);
        startActivity(intent);
    }

    public void openOrdersActivity(View view) {
        Intent intent = new Intent(this, OrderActivity.class);
        intent.putExtra(Intent.EXTRA_UID,14l);
        startActivity(intent);
    }
}
