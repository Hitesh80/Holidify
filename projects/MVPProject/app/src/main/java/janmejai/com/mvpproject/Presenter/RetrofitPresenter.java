package janmejai.com.mvpproject.Presenter;

import android.content.Context;
import android.widget.Toast;
import janmejai.com.mvpproject.Model.EmployeeResponse;
import janmejai.com.mvpproject.Model.ServiceApi;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hitesh-trisys on 10/1/17.
 */

public class RetrofitPresenter {
    private final Context context;
    private EmployeeResponse employeeResponse;
    public RetrofitPresenter(Context context) {
        this.context=context;
    }
    public EmployeeResponse syncData() {

        Retrofit.Builder builder=new Retrofit.Builder();
        HttpLoggingInterceptor httpLoggingInterceptor=new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder okHttpClient=new OkHttpClient.Builder();
        okHttpClient.addInterceptor(httpLoggingInterceptor);


        builder.baseUrl("http://www.mocky.io").addConverterFactory(GsonConverterFactory.create()).client(okHttpClient.build());

        Retrofit retrofit=builder.build();

        ServiceApi serviceApi=retrofit.create(ServiceApi.class);

        Call<EmployeeResponse> employeeResponseCall=serviceApi.getEmployee();

        employeeResponseCall.enqueue(new Callback<EmployeeResponse>() {

            @Override
            public void onResponse(Call<EmployeeResponse> call, Response<EmployeeResponse> response) {
              employeeResponse=response.body();
            }
            @Override
            public void onFailure(Call<EmployeeResponse> call, Throwable t) {
                Toast.makeText(context,"data sync failed", Toast.LENGTH_SHORT).show();
            }
        });

        return employeeResponse;
    }
}
