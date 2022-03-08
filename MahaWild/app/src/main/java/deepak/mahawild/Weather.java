package deepak.mahawild;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.TextView;

public class Weather extends AppCompatActivity {
    private static int z;
    TextView detailsField, currentTemperatureField, humidity_field, pressure_field, weatherIcon, updatedField;
    Typeface weatherFont;
    String[] longitude;
    String[] latitude;
    public static void setpos(int k){
        z=k;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather);


        weatherFont = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/weathericons-regular-webfont.ttf");

        updatedField = (TextView) findViewById(R.id.updated_field);
        detailsField = (TextView) findViewById(R.id.details_field);
        currentTemperatureField = (TextView) findViewById(R.id.current_temperature_field);
        humidity_field = (TextView) findViewById(R.id.humidity_field);
        pressure_field = (TextView) findViewById(R.id.pressure_field);
        weatherIcon = (TextView) findViewById(R.id.weather_icon);
        weatherIcon.setTypeface(weatherFont);


        Function.placeIdTask asyncTask = new Function.placeIdTask(new Function.AsyncResponse() {
            public void processFinish(String weather_city, String weather_description, String weather_temperature, String weather_humidity, String weather_pressure, String weather_updatedOn, String weather_iconText, String sun_rise) {

                updatedField.setText(weather_updatedOn);
                detailsField.setText(weather_description);
                currentTemperatureField.setText(weather_temperature);
                humidity_field.setText("Humidity: " + weather_humidity);
                pressure_field.setText("Pressure: " + weather_pressure);
                weatherIcon.setText(Html.fromHtml(weather_iconText));

            }
        });
        if(MainActivity.posView == 1) {

            longitude = getResources().getStringArray(R.array.longitude);
            latitude = getResources().getStringArray(R.array.latitude);
            asyncTask.execute(longitude[Amba_Barwa.pos1], latitude[Amba_Barwa.pos1]);
        }
        if(MainActivity.posView == 2){

            longitude = getResources().getStringArray(R.array.np_longitude);
            latitude = getResources().getStringArray(R.array.np_latitude);
            asyncTask.execute(longitude[Tadoba.pos1], latitude[Tadoba.pos1]);

        }
        if (MainActivity.posView == 3){

            longitude = getResources().getStringArray(R.array.longitude_b);
            latitude = getResources().getStringArray(R.array.latitude_b);
            asyncTask.execute(longitude[Jaikwadi.pos1], latitude[Jaikwadi.pos1]);

        }
    }
}
