package nyc.c4q.androidtest_unit4final;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import nyc.c4q.androidtest_unit4final.Model.Color;
import nyc.c4q.androidtest_unit4final.Network.ColorJSON;
import nyc.c4q.androidtest_unit4final.Network.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

  private static final String TAG = "JSON?";
  private ColorAdapter adapter;
 // protected HashMap<String, String> colorDict;
  protected List<String> colorsList;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    //colorDict.put("indigo", "#4b0082");
    //colorDict.put("green", "#00ff00");
    //colorDict.put("blue", "#0000ff");
    //colorDict.put("red", "#ff0000");
    // TODO: adding all the colors and their values would be tedious, instead fetch it from the url below
    // https://raw.githubusercontent.com/operable/cog/master/priv/css-color-names.json
    ColorJSON colorJSON = ServiceGenerator.createService(ColorJSON.class);
    Call<Color> getColor = colorJSON.getColors();
    getColor.enqueue(new Callback<Color>() {
      @Override public void onResponse(Call<Color> call, Response<Color> response) {
        Color color = response.body();
        HashMap<String ,String > colorDict = new HashMap<>();

        colorDict.put("blue", color.getBlue());
        colorDict.put("red", color.getRed());
        colorDict.put("purple", color.getPurple());
        colorDict.put("indigo", color.getIndigo());
        colorDict.put("orange", color.getOrange());
        colorDict.put("brown", color.getBrown());
        colorDict.put("black", color.getBlack());
        colorDict.put("green", color.getGreen());

        colorsList = new ArrayList<>();
        String[] names =
            new String[] { "blue", "red", "purple", "indigo", "orange", "brown", "black", "green" };
        for (String n : names) colorsList.add(n);

        RecyclerView recyclerView = findViewById(R.id.rv);
        adapter = new ColorAdapter(colorsList, colorDict);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
      }

      @Override public void onFailure(Call<Color> call, Throwable t) {

      }
    });
  }

  // TODO: Add options menu with the item "Info" which is always visible
  // TODO: When "Info" menu item is clicked, display the fragment InfoFragment
  // TODO: If InfoFragment is already visible and I click "Info", remove InfoFragment from the view.
  // Link to creating options menu: https://github.com/C4Q/AC-Android/tree/v2/Android/Lecture-9-Menus-and-Navigation#anatomy-of-menu-xml

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.info_menu, menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.about:
        about();
        return true;
    }
    return super.onOptionsItemSelected(item);
  }

  private void about() {
    InfoFragment infofrag =
        (InfoFragment) getFragmentManager().findFragmentById(R.id.info_fragment);
    if (infofrag != null && infofrag.isVisible()) {
      getFragmentManager().beginTransaction().remove(infofrag).commit();
    } else {
      InfoFragment infoFragment = new InfoFragment();
      FragmentManager fragmentManager = getFragmentManager();
      FragmentTransaction transaction = fragmentManager.beginTransaction();
      transaction.add(R.id.info_fragment, infofrag);
      transaction.commit();
    }
  }
}
