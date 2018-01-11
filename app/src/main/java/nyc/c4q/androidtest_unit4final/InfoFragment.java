package nyc.c4q.androidtest_unit4final;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by justiceo on 1/9/18.
 */

public class InfoFragment extends Fragment {

    Button button;
    TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View v = inflater.inflate(R.layout.info_fragment, container, false);
        button = v.findViewById(R.id.more_text);
        textView = v.findViewById(R.id.more_textView);


        button.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                if(textView.getVisibility() == View.VISIBLE){
                    textView.setVisibility(View.GONE);
                    button.setVisibility(View.VISIBLE);
                }else {
                   button.setVisibility(View.GONE);
                    textView.setVisibility(View.VISIBLE);
                }
            }
        });


        return v;
    }
}
