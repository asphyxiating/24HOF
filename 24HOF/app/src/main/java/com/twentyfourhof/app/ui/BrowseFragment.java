package com.twentyfourhof.app.ui;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import com.twentyfourhof.app.R;
import com.twentyfourhof.app.data.MediaItem;
import com.twentyfourhof.app.ui.events.ItemEventResult;
import roboguice.event.Observes;
import roboguice.fragment.RoboListFragment;

import java.util.ArrayList;

public class BrowseFragment extends RoboListFragment {

    private TextAdapter textAdapter;
    private SearchView searchView;

    public void handleMediaEvent(@Observes ItemEventResult itemEventResult){
        textAdapter.addAll(itemEventResult.getItems());
        textAdapter.notifyDataSetChanged();
    }

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_browse, container, false);

        // Font path
        String fontPath = "fonts/max_handwriting.ttf";
        // Loading Font Face
        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), fontPath);

        SearchView searchView = (SearchView) rootView.findViewById(R.id.searchView);

        int id = searchView.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
        EditText editText = (EditText) searchView.findViewById(id);
        editText.setHintTextColor(getResources().getColor(R.color.white));
        editText.setTypeface(tf);
        editText.setTextSize(30);

        textAdapter = new TextAdapter(getActivity(), 0, new ArrayList<MediaItem>());
        setListAdapter(textAdapter);
        return rootView;
	}

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        getListView();
    }
}