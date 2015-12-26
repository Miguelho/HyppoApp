package miguel.hyppoapp;

import android.content.Context;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
import com.parse.ParseUser;

import java.util.List;

import miguel.hyppoapp.R;

/**
 * Created by Miguel on 12/24/2015.
 */
public class HyppoAdapter extends ArrayAdapter<ParseObject> {
    private ParseHandler parseHandler;
    ParseObject mood=null;
    public HyppoAdapter(Context context, int resource, List<ParseObject> registros) {
        super(context, 0, registros);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ParseObject registro = getItem(position);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("State");
                query.fromLocalDatastore();
        ParseRelation relation = registro.getRelation("condition");
        List <ParseObject> conditionsList=null;

        try {
            conditionsList = relation.getQuery().find();
            mood= registro.getParseObject("Relation_Mood");
        } catch (ParseException e) {
            e.printStackTrace();
        }



        if (convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_registro, parent,false);
        }
        TextView dateLabel = (TextView) convertView.findViewById(R.id.dateLabel);
        TextView moodLabel = (TextView) convertView.findViewById(R.id.moodLabel);
        TextView painLabel = (TextView) convertView.findViewById(R.id.painLabel);
        TextView sleepLabel = (TextView) convertView.findViewById(R.id.sleepLabel);
        TextView mentalLabel = (TextView) convertView.findViewById(R.id.mentalLabel);

        registro.fetchInBackground();

        String returnKey= parseHandler.returnKey(mood.getObjectId());
        moodLabel.setText(returnKey);
        dateLabel.setText(Time.getCurrentTimezone());





        /*String recurso = alumno.getPhotoId();
        ivFoto.setImageResource(getContext().getResources().getIdentifier(recurso, "drawable", getContext().getPackageName()));*/
        //Log.v("Numero asignaturas", String.valueOf(alumno.getAsignaturas().size()));
        //tvAsignaturas.setText("Nº Asignaturas: "+String.valueOf(alumno.getAsignaturas().size()));

        return convertView;



    }
}
