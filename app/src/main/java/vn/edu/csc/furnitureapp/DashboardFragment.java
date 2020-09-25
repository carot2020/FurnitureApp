package vn.edu.csc.furnitureapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class DashboardFragment extends Fragment {

    GridView gridView;
    ArrayList<Categories> arrayList;
    FurnitureAdapterGrid furnitureAdapterGrid;
    Utils utils;
    DBHelper dbHelper;

    String url = "http://192.168.0.101:5000/Categories";
    public DashboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        utils = new Utils(getContext());
        dbHelper = new DBHelper(getContext());
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        gridView = view.findViewById(R.id.gridView);

        // lab 23
        //arrayList = utils.getMockDataCategories();

        // lab 45
        //arrayList = dbHelper.getALLCategories();

        // lab 78
        arrayList = new ArrayList<>();

        furnitureAdapterGrid = new FurnitureAdapterGrid(getContext(),arrayList);

        gridView.setAdapter(furnitureAdapterGrid);

        getAPICategories();

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment,CategoriesFragment.newInstance(arrayList.get(i).getId()));
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }

    public void getAPICategories(){

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null
                , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("categories");
                    for (int i = 0; i< jsonArray.length() ; i++ ){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Categories categories = new Categories();
                        categories.setId(jsonObject.getInt("categoriesId"));
                        categories.setName(jsonObject.getString("name"));
                        categories.setImage(jsonObject.getString("image"));
                        arrayList.add(categories);
                    }
                    furnitureAdapterGrid.notifyDataSetChanged();
                    Log.i("APIHelper", arrayList.size()+"") ;
                } catch (JSONException e) {

                    Log.i("APIHelper", e.getMessage()) ;
                }

            }
        }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("APIHelper", error.getMessage()) ;
            }
        });
        requestQueue.add(jsonObjectRequest);
        Log.i("APIHelper", arrayList.size()+"") ;

    }


}