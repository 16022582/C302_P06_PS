package rp.c302_p06_ps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lvCategory;
    private ArrayList<Category> alCategory;
    ArrayAdapter<Category> aaCategory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @Override
    protected void onResume() {
        super.onResume();

        lvCategory = findViewById(R.id.listViewCategories);

        lvCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                Category data = alCategory.get(position);
                Integer dataID = data.getId();
                intent.putExtra("id",dataID);
                startActivity(intent);
            }
        });

        lvCategory = findViewById(R.id.listViewCategories);
        alCategory = new ArrayList<Category>();

        aaCategory = new Adapter(this, R.layout.listview, alCategory);
        lvCategory.setAdapter(aaCategory);

        // Code for step 1 start
        HttpRequest request = new HttpRequest
                ("http://10.0.2.2/C302_P06_PhotoStoreWS/getCategories.php");
        request.setOnHttpResponseListener(mHttpResponseListener);
        request.setMethod("GET");
        request.execute();
        // Code for step 1 end
    }

    // Code for step 2 start
    private HttpRequest.OnHttpResponseListener mHttpResponseListener =
            new HttpRequest.OnHttpResponseListener() {
                @Override
                public void onResponse(String response){

                    // process response here
                    try {
                        JSONArray jsonArray = new JSONArray(response);

                        for (int i=0; i<jsonArray.length(); i++){
                            JSONObject jsonObj = jsonArray.getJSONObject(i);

                            int categoryId = jsonObj.getInt("category_id");
                            String categoryName = jsonObj.getString("name");
                            String description = jsonObj.getString("description");

                            //String displayResults = "Category Id: " + categoryId + "\n\nCategory Name: "
//                                    + categoryName + "\n\nDescription: " + description + "\n";
                            Category newCate = new Category(categoryId, categoryName, description);
                            alCategory.add(newCate);
                        }
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }

                    aaCategory.notifyDataSetChanged();
                }
            };


}

