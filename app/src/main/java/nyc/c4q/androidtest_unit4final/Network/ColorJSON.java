package nyc.c4q.androidtest_unit4final.Network;

import nyc.c4q.androidtest_unit4final.Model.Color;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by c4q on 1/10/18.
 */

public interface ColorJSON {

  @GET("css-color-names.json") Call<Color> getColors();
}
