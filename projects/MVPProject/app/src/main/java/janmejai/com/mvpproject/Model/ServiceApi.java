package janmejai.com.mvpproject.Model;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;

/**
 * Created by hitesh-trisys on 10/1/17.
 */

public interface ServiceApi {

    @GET("/v2/59d077b90f0000b80293dcdc")
    Call<EmployeeResponse> getEmployee();
}
