package ee.eovtchar.sharedpreference;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends Activity
{
    private int currentRate;
    private int highestRate;
    private TextView currentRateView;
    private TextView highestRateView;

    public static final String PREFS_NAME = "SharedPrefsFile";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //проверяем сохранённые значения
        //currentRate (зелёным) вроде сами создаём название
        SharedPreferences storeData = getSharedPreferences(PREFS_NAME,0);
        currentRate = storeData.getInt("currentRate",0);
        highestRate = storeData.getInt("highestRate",0);

        currentRateView = (TextView) findViewById(R.id.currentRateView);
        highestRateView = (TextView) findViewById(R.id.highestRateView);

        currentRateView.setText("Current rate: " + currentRate);
        highestRateView.setText("Highest rate: " + highestRate);
    }

    public void addCurrentRate(View view)
    {
        currentRate++;
        currentRateView.setText("Current rate: " + currentRate);
        if (highestRate < currentRate)
        {
            highestRate = currentRate;
        }
        highestRateView.setText("Highest rate: " + highestRate);
    }

    public void clearCurrentRate(View view)
    {
        currentRate = 0;
        saveRates();
        currentRateView.setText("Current rate: " + currentRate);
    }

    public void clearAllRate(View view)
    {
        currentRate = 0;
        highestRate = 0;
        saveRates();
        currentRateView.setText("Current rate: " + currentRate);
        highestRateView.setText("Highest rate: " + highestRate);
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        this.saveRates();
    }

    protected void saveRates() {
        SharedPreferences storedData = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor storedDataEditor = storedData.edit();
        storedDataEditor.putInt("currentRate", currentRate);
        storedDataEditor.putInt("highestRate", highestRate);
        storedDataEditor.commit();
    }

}
