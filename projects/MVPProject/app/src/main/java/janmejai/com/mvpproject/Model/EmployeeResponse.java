package janmejai.com.mvpproject.Model;

import java.util.List;

/**
 * Created by hitesh-trisys on 10/1/17.
 */
public class EmployeeResponse {
    private String status;
    private String message;
    private List<Employee> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Employee> getData() {
        return data;
    }

    public void setData(List<Employee> data) {
        this.data = data;
    }
}
