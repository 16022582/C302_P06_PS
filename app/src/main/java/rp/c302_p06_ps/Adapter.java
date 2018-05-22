package rp.c302_p06_ps;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends ArrayAdapter<Category> {

    private ArrayList<Category> alCategory;
    private Context context;
    private TextView textName, textDesc;

    public Adapter(Context context, int resource, ArrayList<Category> objects){
        super(context, resource, objects);
        alCategory = objects;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View listView = inflater.inflate(R.layout.listview, parent, false);
        textName = listView.findViewById(R.id.textName);
        textDesc = listView.findViewById(R.id.textDesc);

        Category currentCategory = alCategory.get(position);

        textName.setText(currentCategory.getName());
        textDesc.setText(currentCategory.getDesc());

        return listView;
    }

}
