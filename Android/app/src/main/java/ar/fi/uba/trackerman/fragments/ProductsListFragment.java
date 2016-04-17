package ar.fi.uba.trackerman.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;


import ar.fi.uba.trackerman.adapters.ProductsListAdapter;
import ar.fi.uba.trackerman.domains.Product;
import fi.uba.ar.soldme.R;

/**
 * Created by plucadei on 3/4/16.
 */
public class ProductsListFragment extends Fragment implements AdapterView.OnItemClickListener{

    private String brands;
    private ProductsListAdapter productsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        brands=null;
        View fragmentView= inflater.inflate(R.layout.fragment_products_list, container, false);
        ListView productsList= (ListView)fragmentView.findViewById(R.id.productsListView);
        Activity activity= getActivity();

        productsAdapter = new ProductsListAdapter( getContext(), R.layout.products_list_item, new ArrayList<Product>());
        productsAdapter.setBrands(brands);
        productsList.setAdapter(productsAdapter);
        productsList.setOnItemClickListener(this);

        ProgressBar bar= new ProgressBar(getContext());
        bar.setIndeterminate(true);
        productsList.setEmptyView(bar);
        productsAdapter.refresh();
        return fragmentView;
    }

    public void setBrands(String brands){
        productsAdapter.setBrands(brands);
        productsAdapter.refresh();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Product product= (Product)parent.getItemAtPosition(position);
    }
}
