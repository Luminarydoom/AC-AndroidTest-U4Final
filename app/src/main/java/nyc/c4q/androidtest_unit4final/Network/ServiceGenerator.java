package nyc.c4q.androidtest_unit4final.Network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by c4q on 1/10/18.
 */

public class ServiceGenerator {
  private static final String BASE_URL = "https://raw.githubusercontent.com/operable/cog/master/priv/";

  private static Retrofit.Builder builder =
      new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create());

  private static Retrofit retrofit = builder.build();

  private static HttpLoggingInterceptor loggingInterceptor =
      new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

  private static OkHttpClient.Builder okhttpclient = new OkHttpClient.Builder();

  public static <T> T createService(Class<T> serviceClass) {
    if (!okhttpclient.interceptors().contains(loggingInterceptor)) {
      okhttpclient.addInterceptor(loggingInterceptor);
      builder.client(okhttpclient.build());
      retrofit = builder.build();
    }
    return retrofit.create(serviceClass);
  }
}
