package janmejai.com.mvpproject.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import janmejai.com.mvpproject.R;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

/**
 * Created by hitesh-trisys on 9/14/17.
 */

public class NewFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.customer_list_one, container, false);
        return view;
    }

    public static Fragment newInstance(String message) {
        NewFragment f = new NewFragment();
        Bundle bdl = new Bundle(1);
        bdl.putString(EXTRA_MESSAGE, message);
        f.setArguments(bdl);
        return f;
    }
}
