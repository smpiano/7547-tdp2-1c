package ar.fi.uba.trackerman.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import ar.fi.uba.trackerman.adapters.ClientsListAdapter;
import ar.fi.uba.trackerman.domains.Client;
import fi.uba.ar.soldme.R;

/**
 * Created by plucadei on 1/5/16.
 */
public class DailyRouteFragment extends Fragment{

    public static String DAY_ARG="DailyRouteFragment.DAY_ARG";
    private String day;

    public DailyRouteFragment(){
        super();
    }

    public void setDay(String day){
        this.day=day;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle args = getArguments();
        day = args.getString(DAY_ARG);
        View fragmentView= inflater.inflate(R.layout.fragment_daily_route, container, false);

        TextView text= (TextView)fragmentView.findViewById(R.id.daily_route_day);
        text.setText(day);



/*
        ListView clientsList= (ListView)fragmentView.findViewById(R.id.dayAgendaListView);

        ClientsListAdapter clientsAdapter = new ClientsListAdapter( getContext(), R.layout.agenda_list_item, new ArrayList<Client>());
        clientsList.setAdapter(clientsAdapter);
        clientsList.setOnItemClickListener(this);

        ProgressBar bar= new ProgressBar(getContext());
        bar.setIndeterminate(true);
        clientsList.setEmptyView(bar);
        clientsAdapter.refresh();
*/
        return fragmentView;
    }
}