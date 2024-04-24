package com.btcbrunch;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.TimeZone;

public class BitCoinVsStocksActivity extends AppCompatActivity implements View.OnClickListener, OnChartValueSelectedListener {

    //Mic check test


    static LineChart mpLineChart;
    //Button bitCoinApiButton0;
    private double[] bitCoinData;
    //private JSONObject[] cornData;

    private String[] bitCoinDataObj2;
    private String[] cornDataObj2;
    private String[] appleDataObj2;
    private String[] soybDataObj2;
    private String[] sugarDataObj2;
    private String[] amazonDataObj2;
    private String[] boeingDataObj2;
    private String[] bloombergCottonDataObj2;
    private String[] invescoDbOilDataObj2;
    private String[] duPontDataObj2;
    private String[] consolidatedEdisonDataObj2;
    private String[] vanEckVectorsGoldMinersDataObj2;
    private String[] alphabetDataObj2;
    private String[] nyseDataObj2;
    private String[] sAndP500DataObj2;
    private String[] sAndP500GrowthDataObj2;
    private String[] uSEnergyDataObj2;
    private String[] teucriumGrainDataObj2;
    private String[] bloombergCoffeeDataObj2;
    private String[] jPMorganChaseDataObj2;
    private String[] cocaColaDataObj2;
    private String[] microsoftDataObj2;
    private String[] nasdaqDataObj2;
    private String[] globalXSilverMinersDataObj2;
    private String[] vanEckVectorsSteelDataObj2;
    private String[] unitedHealthGroupDataObj2;
    private String[] exxonMobilDataObj2;
    private String[] dailyCommoditiesDataArr2;

    ArrayList<Entry> dataVals;
    ArrayList dailyCommoditiesVals;
    String rangeOfDateVals;

    ArrayList<Entry> dataVals2;
    ArrayList<Entry> cornVals2;
    ArrayList<Entry> appleVals2;
    ArrayList<Entry> soybVals2;
    ArrayList<Entry> sugarVals2;
    ArrayList<Entry> amazonVals2;
    ArrayList<Entry> boeingVals2;
    ArrayList<Entry> bloombergCottonVals2;
    ArrayList<Entry> invescoDbOilVals2;
    ArrayList<Entry> duPontVals2;
    ArrayList<Entry> consolidatedEdisonVals2;
    ArrayList<Entry> vanEckVectorsGoldMinersVals2;
    ArrayList<Entry> alphabetVals2;
    ArrayList<Entry> nyseVals2;
    ArrayList<Entry> sAndP500Vals2;
    ArrayList<Entry> sAndP500GrowthVals2;
    ArrayList<Entry> uSEnergyVals2;
    ArrayList<Entry> teucriumGrainVals2;
    ArrayList<Entry> bloombergCoffeeVals2;
    ArrayList<Entry> jPMorganChaseVals2;
    ArrayList<Entry> cocaColaVals2;
    ArrayList<Entry> microsoftVals2;
    ArrayList<Entry> nasdaqVals2;
    ArrayList<Entry> globalXSilverMinersVals2;
    ArrayList<Entry> vanEckVectorsSteelVals2;
    ArrayList<Entry> unitedHealthGroupVals2;
    ArrayList<Entry> exxonMobilVals2;
    ArrayList dailyCommoditiesVals2;
    private AdView mAdView;
    ArrayList<Candlestick> cryptoVals = new ArrayList<Candlestick>();
    static ArrayList<Candlestick> cryptoValsInOnClick = new ArrayList<Candlestick>();
    static ArrayList<Float> cryptoPriceArray = new ArrayList<>();
    static ArrayList<Float> commodityPriceArray = new ArrayList<>();
    int numOfDaysCrypto;
    static ChartMaker chartMaker;
    CommoditySelector commoditySelector;
    CryptoSelector cryptoSelector;

    static DBHelper mDatabaseHelper;

    static ArrayList<Candlestick> candlesticks = new ArrayList<Candlestick>();
    AppCompatButton sevenDay;
    AppCompatButton fourteenDay;
    AppCompatButton thirtyDay;
    AppCompatButton ninetyDay;
    AppCompatButton oneHundredEightyDay;
    AppCompatButton threeHundredSixtyFiveDay;
    Spinner stockSpinner;
    Spinner bitCoinSpinner;
    RequestQueue requestQueue;
    String URL1;
    String URL28;
    static int currentBtn;

    TextView commodityValTextView;
    TextView cryptoValTextView;
    TextView commodityDateValTextView;
    TextView cryptoDateValTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bit_coin_vs_stocks);

        stockSpinner = findViewById(R.id.stock_spinner);

        bitCoinSpinner = findViewById(R.id.bitcoin_spinner);

        sevenDay =  findViewById(R.id.bitcoin_api_button0);
        fourteenDay =  findViewById(R.id.bitcoin_api_button1);
        thirtyDay =  findViewById(R.id.bitcoin_api_button2);
        ninetyDay =  findViewById(R.id.bitcoin_api_button3);
        oneHundredEightyDay =  findViewById(R.id.bitcoin_api_button4);
        threeHundredSixtyFiveDay =  findViewById(R.id.bitcoin_api_button5);

        sevenDay.setEnabled(false);
        fourteenDay.setEnabled(false);
        thirtyDay.setEnabled(false);
        ninetyDay.setEnabled(false);
        oneHundredEightyDay.setEnabled(false);
        threeHundredSixtyFiveDay.setEnabled(false);

        commodityValTextView = (TextView) findViewById(R.id.commodity_text_view_value);
        cryptoValTextView = (TextView) findViewById(R.id.crypto_text_view_value);
        commodityDateValTextView = (TextView) findViewById(R.id.commodity_text_view_date_value);
        cryptoDateValTextView = (TextView) findViewById(R.id.crypto_text_view_date_value);
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mDatabaseHelper = new DBHelper(this);

        URL1 = "https://btcbrunchapi.com/ROLLING_365_BTC";
        URL28 = "https://btcbapi.com/DAILY_COMMODITIES_LIST";

        //Here to test the response of the Volley call that had been failing for http
//        URL28 = "https://jsonplaceholder.typicode.com/todos/1";

//        URL1="https://btcbrunchapi.com/ROLLING_7_BTC";
//        URL2="https://btcbrunchapi.com/ROLLING_7_CORN";
//        URL3="https://btcbrunchapi.com/ROLLING_7_AAPL";
//        URL4="https://btcbrunchapi.com/ROLLING_7_SOYB";
//        URL5="https://btcbrunchapi.com/ROLLING_7_CANE";


//      localhost:3000 API calls for development. Uses the Desktop's IP address for the wifi NIC

//        URL1="http://192.168.0.13:3000/ROLLING_365_BTC";
//        URL2="http://192.168.0.13:3000/ROLLING_365_CORN";
//        URL3="http://192.168.0.13:3000/ROLLING_365_AAPL";
//        URL4="http://192.168.0.13:3000/ROLLING_365_SOYB";
//        URL5="http://192.168.0.13:3000/ROLLING_365_CANE";

        //Creating the instance of the CommoditySelector object
        commoditySelector = new CommoditySelector();

        requestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest objectRequest1 = new JsonArrayRequest(Request.Method.GET,
                URL1,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.e("Bitcoin Response", response.toString());
                        System.out.println("BTC RESPONSE:" + response);
                        dataVals = bitcoinDataFunction(response);

                        checkLoaded();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Log.e("Rest Response", error.toString());
                    }
                }
        );

        JsonArrayRequest arrayRequest1 = new JsonArrayRequest(Request.Method.GET,
                URL28,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("dailyCommoditiesVals", response.toString());
                        dailyCommoditiesVals =  dailyCommoditiesDataFunction(response);

                        commoditySelector.setDailyCommoditiesVals(dailyCommoditiesVals);

                        System.out.println("These are the dailyCommoditiesVals you are looking for: " + dailyCommoditiesVals);
                        checkLoaded();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Log.e("Rest Response", error.toString());
                    }
                }
        );

        System.out.println("This is the objectRequest1 instance: " + objectRequest1);
        System.out.println("This is the arrayRequest1 instance: " + arrayRequest1);


        requestQueue.add(objectRequest1);
        requestQueue.add(arrayRequest1);



        //Links the line chart XML to the java code
        mpLineChart = findViewById(R.id.line_chart);


    }//end of onCreate()

    private void checkLoaded() {

        if (dataVals != null || dailyCommoditiesVals != null) {
            sevenDay.setEnabled(true);
            fourteenDay.setEnabled(true);
            thirtyDay.setEnabled(true);
            ninetyDay.setEnabled(true);
            oneHundredEightyDay.setEnabled(true);
            threeHundredSixtyFiveDay.setEnabled(true);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        //Hides the ad in Landscape mode so the Spinners and Buttons can be accessed
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            View my_view = findViewById(R.id.adView);
            my_view.setVisibility(View.GONE);
        }

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            View my_view = findViewById(R.id.adView);
            my_view.setVisibility(View.VISIBLE);
        }

    }


    private ArrayList<Entry> bitcoinDataFunction(JSONArray response) {
        //System.out.println("BTC ARRAY: " + response.toString());
        ArrayList<Entry> dataVals = new ArrayList<>();
        try {
            for (int i = 0; i <= response.length() - 1; i++) {
                bitCoinData = new double[]{(double) Double.parseDouble((String) response.getJSONArray(i).get(1))};
                dataVals.add(new Entry(i, (float) bitCoinData[0]));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return dataVals;
    }


    //The group of functions that are called once the app is opened to parse and clean the data
    //Converts the syntax from JavaScript to Java

    private ArrayList<Entry> bitCoinDataFunction2(ArrayList commValArrList) {
        ArrayList<Entry> bitCoinVals2 = new ArrayList<>();
        try {
            String holder = ((Commodities) commValArrList.get(0)).getValue();
            holder = holder.substring(1, holder.length() - 1); //Removes the [] javascript array braces
            holder = holder.replaceAll(",", ""); //Removes the commas from the string
            bitCoinDataObj2 = holder.split(" ");

            int commArrLength = bitCoinDataObj2.length;
            for (int i = 0; i <= commArrLength - 1; i++) {
                double bitCoinData = Double.parseDouble((String) Array.get(bitCoinDataObj2, i));
                bitCoinVals2.add(new Entry(i, (float) bitCoinData));
            }

            //$$$$$$ This next line of code is special for now $$$$$$
            //It assigns rangeOfDateVals the array that will be used to show the dates of the plot points in the UI
            //I would like to find a better place to put this in the code, this function should not be responsible for gathering this information
            //But for now this works, probably later I will just write a function for this, or add this to the correct API call and get this from the response object
            rangeOfDateVals = ((Commodities) commValArrList.get(0)).getRangeVal();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return bitCoinVals2;
    }

    private ArrayList<Entry> cornDataFunction2(ArrayList commValArrList) {
        ArrayList<Entry> cornVals2 = new ArrayList<>();
        try {
            String holder = ((Commodities) commValArrList.get(1)).getValue();
            holder = holder.substring(1, holder.length() - 1); //Removes the [] javascript array braces
            holder = holder.replaceAll(",", ""); //Removes the commas from the string
            cornDataObj2 = holder.split(" ");

            int commArrLength = cornDataObj2.length;
            for (int i = 0; i <= commArrLength - 1; i++) {
                double cornData = Double.parseDouble((String) Array.get(cornDataObj2, i));
                cornVals2.add(new Entry(i, (float) cornData));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return cornVals2;
    }

    private ArrayList<Entry> appleDataFunction2(ArrayList commValArrList) {
        ArrayList<Entry> appleVals2 = new ArrayList<>();
        try {
            String holder = ((Commodities) commValArrList.get(2)).getValue();
            holder = holder.substring(1, holder.length() - 1); //Removes the [] javascript array braces
            holder = holder.replaceAll(",", ""); //Removes the commas from the string
            appleDataObj2 = holder.split(" ");
            StringBuilder reversed = new StringBuilder();

            //Starts the reversing process so the data comes into the chart the correct direction
            for (int i = appleDataObj2.length; i > 0; i--) {
                reversed.append(appleDataObj2[i - 1]).append(" ");
            }

            String[] reversedArray = reversed.toString().split(" ");

            int commArrLength = reversedArray.length;
            for (int i = 0; i <= commArrLength - 1; i++) {
                double appleData = Double.parseDouble((String) Array.get(reversedArray, i));
                appleVals2.add(new Entry(i, (float) appleData));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return appleVals2;
    }

    private ArrayList<Entry> soybDataFunction2(ArrayList commValArrList) {
        ArrayList<Entry> soybVals2 = new ArrayList<>();
        try {
            String holder = ((Commodities) commValArrList.get(3)).getValue();
            holder = holder.substring(1, holder.length() - 1); //Removes the [] javascript array braces
            holder = holder.replaceAll(",", ""); //Removes the commas from the string
            soybDataObj2 = holder.split(" ");

            int commArrLength = soybDataObj2.length;
            for (int i = 0; i <= commArrLength - 1; i++) {
                double soybData = Double.parseDouble((String) Array.get(soybDataObj2, i));
                soybVals2.add(new Entry(i, (float) soybData));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return soybVals2;
    }

    private ArrayList<Entry> sugarDataFunction2(ArrayList commValArrList) {
        ArrayList<Entry> sugarVals2 = new ArrayList<>();
        try {
            String holder = ((Commodities) commValArrList.get(4)).getValue();
            holder = holder.substring(1, holder.length() - 1); //Removes the [] javascript array braces
            holder = holder.replaceAll(",", ""); //Removes the commas from the string
            sugarDataObj2 = holder.split(" ");

            int commArrLength = sugarDataObj2.length;
            for (int i = 0; i <= commArrLength - 1; i++) {
                double sugarData = Double.parseDouble((String) Array.get(sugarDataObj2, i));
                sugarVals2.add(new Entry(i, (float) sugarData));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return sugarVals2;
    }

    private ArrayList<Entry> amazonDataFunction2(ArrayList commValArrList) {
        ArrayList<Entry> amazonVals2 = new ArrayList<>();
        try {
            String holder = ((Commodities) commValArrList.get(5)).getValue();
            holder = holder.substring(1, holder.length() - 1); //Removes the [] javascript array braces
            holder = holder.replaceAll(",", ""); //Removes the commas from the string
            amazonDataObj2 = holder.split(" ");

            int commArrLength = amazonDataObj2.length;
            for (int i = 0; i <= commArrLength - 1; i++) {
                double amazonData = Double.parseDouble((String) Array.get(amazonDataObj2, i));
                amazonVals2.add(new Entry(i, (float) amazonData));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return amazonVals2;
    }

    private ArrayList<Entry> boeingDataFunction2(ArrayList commValArrList) {
        ArrayList<Entry> boeingVals2 = new ArrayList<>();
        try {
            String holder = ((Commodities) commValArrList.get(6)).getValue();
            holder = holder.substring(1, holder.length() - 1); //Removes the [] javascript array braces
            holder = holder.replaceAll(",", ""); //Removes the commas from the string
            boeingDataObj2 = holder.split(" ");

            int commArrLength = boeingDataObj2.length;
            for (int i = 0; i <= commArrLength - 1; i++) {
                double boeingData = Double.parseDouble((String) Array.get(boeingDataObj2, i));
                boeingVals2.add(new Entry(i, (float) boeingData));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return boeingVals2;
    }

    private ArrayList<Entry> bloombergCottonDataFunction2(ArrayList commValArrList) {
        ArrayList<Entry> bloombergCottonVals2 = new ArrayList<>();
        try {
            String holder = ((Commodities) commValArrList.get(7)).getValue();
            holder = holder.substring(1, holder.length() - 1); //Removes the [] javascript array braces
            holder = holder.replaceAll(",", ""); //Removes the commas from the string
            bloombergCottonDataObj2 = holder.split(" ");

            int commArrLength = bloombergCottonDataObj2.length;
            for (int i = 0; i <= commArrLength - 1; i++) {
                double bloombergCottonData = Double.parseDouble((String) Array.get(bloombergCottonDataObj2, i));
                bloombergCottonVals2.add(new Entry(i, (float) bloombergCottonData));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return bloombergCottonVals2;
    }

    private ArrayList<Entry> invescoDbOilDataFunction2(ArrayList commValArrList) {
        ArrayList<Entry> invescoDbOilVals2 = new ArrayList<>();
        try {
            String holder = ((Commodities) commValArrList.get(8)).getValue();
            holder = holder.substring(1, holder.length() - 1); //Removes the [] javascript array braces
            holder = holder.replaceAll(",", ""); //Removes the commas from the string
            invescoDbOilDataObj2 = holder.split(" ");

            int commArrLength = invescoDbOilDataObj2.length;
            for (int i = 0; i <= commArrLength - 1; i++) {
                double invescoDbOilData = Double.parseDouble((String) Array.get(invescoDbOilDataObj2, i));
                invescoDbOilVals2.add(new Entry(i, (float) invescoDbOilData));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return invescoDbOilVals2;
    }

    private ArrayList<Entry> duPontDataFunction2(ArrayList commValArrList) {
        ArrayList<Entry> duPontVals2 = new ArrayList<>();
        try {
            String holder = ((Commodities) commValArrList.get(9)).getValue();
            holder = holder.substring(1, holder.length() - 1); //Removes the [] javascript array braces
            holder = holder.replaceAll(",", ""); //Removes the commas from the string
            duPontDataObj2 = holder.split(" ");

            int commArrLength = duPontDataObj2.length;
            for (int i = 0; i <= commArrLength - 1; i++) {
                double duPontData = Double.parseDouble((String) Array.get(duPontDataObj2, i));
                duPontVals2.add(new Entry(i, (float) duPontData));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return duPontVals2;
    }

    private ArrayList<Entry> consolidatedEdisonDataFunction2(ArrayList commValArrList) {
        ArrayList<Entry> consolidatedEdisonVals2 = new ArrayList<>();
        try {
            String holder = ((Commodities) commValArrList.get(10)).getValue();
            holder = holder.substring(1, holder.length() - 1); //Removes the [] javascript array braces
            holder = holder.replaceAll(",", ""); //Removes the commas from the string
            consolidatedEdisonDataObj2 = holder.split(" ");

            int commArrLength = consolidatedEdisonDataObj2.length;
            for (int i = 0; i <= commArrLength - 1; i++) {
                double consolidatedEdisonData = Double.parseDouble((String) Array.get(consolidatedEdisonDataObj2, i));
                consolidatedEdisonVals2.add(new Entry(i, (float) consolidatedEdisonData));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return consolidatedEdisonVals2;
    }

    private ArrayList<Entry> vanEckVectorsGoldMinersDataFunction2(ArrayList commValArrList) {
        ArrayList<Entry> vanEckVectorsGoldMinersVals2 = new ArrayList<>();
        try {
            String holder = ((Commodities) commValArrList.get(11)).getValue();
            holder = holder.substring(1, holder.length() - 1); //Removes the [] javascript array braces
            holder = holder.replaceAll(",", ""); //Removes the commas from the string
            vanEckVectorsGoldMinersDataObj2 = holder.split(" ");

            int commArrLength = vanEckVectorsGoldMinersDataObj2.length;
            for (int i = 0; i <= commArrLength - 1; i++) {
                double vanEckVectorsGoldMinersData = Double.parseDouble((String) Array.get(vanEckVectorsGoldMinersDataObj2, i));
                vanEckVectorsGoldMinersVals2.add(new Entry(i, (float) vanEckVectorsGoldMinersData));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return vanEckVectorsGoldMinersVals2;
    }

    private ArrayList<Entry> alphabetDataFunction2(ArrayList commValArrList) {
        ArrayList<Entry> alphabetVals2 = new ArrayList<>();
        try {
            String holder = ((Commodities) commValArrList.get(12)).getValue();
            holder = holder.substring(1, holder.length() - 1); //Removes the [] javascript array braces
            holder = holder.replaceAll(",", ""); //Removes the commas from the string
            alphabetDataObj2 = holder.split(" ");

            int commArrLength = alphabetDataObj2.length;
            for (int i = 0; i <= commArrLength - 1; i++) {
                double alphabetData = Double.parseDouble((String) Array.get(alphabetDataObj2, i));
                alphabetVals2.add(new Entry(i, (float) alphabetData));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return alphabetVals2;
    }

    private ArrayList<Entry> nyseDataFunction2(ArrayList commValArrList) {
        ArrayList<Entry> nyseVals2 = new ArrayList<>();
        try {
            String holder = ((Commodities) commValArrList.get(13)).getValue();
            holder = holder.substring(1, holder.length() - 1); //Removes the [] javascript array braces
            holder = holder.replaceAll(",", ""); //Removes the commas from the string
            nyseDataObj2 = holder.split(" ");

            int commArrLength = nyseDataObj2.length;
            for (int i = 0; i <= commArrLength - 1; i++) {
                double nyseData = Double.parseDouble((String) Array.get(nyseDataObj2, i));
                nyseVals2.add(new Entry(i, (float) nyseData));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return nyseVals2;
    }

    private ArrayList<Entry> sAndP500DataFunction2(ArrayList commValArrList) {
        ArrayList<Entry> sAndP500Vals2 = new ArrayList<>();
        try {
            String holder = ((Commodities) commValArrList.get(14)).getValue();
            holder = holder.substring(1, holder.length() - 1); //Removes the [] javascript array braces
            holder = holder.replaceAll(",", ""); //Removes the commas from the string
            sAndP500DataObj2 = holder.split(" ");

            int commArrLength = sAndP500DataObj2.length;
            for (int i = 0; i <= commArrLength - 1; i++) {
                double sAndP500Data = Double.parseDouble((String) Array.get(sAndP500DataObj2, i));
                sAndP500Vals2.add(new Entry(i, (float) sAndP500Data));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return sAndP500Vals2;
    }

    private ArrayList<Entry> sAndP500GrowthDataFunction2(ArrayList commValArrList) {
        ArrayList<Entry> sAndP500GrowthVals2 = new ArrayList<>();
        try {
            String holder = ((Commodities) commValArrList.get(15)).getValue();
            holder = holder.substring(1, holder.length() - 1); //Removes the [] javascript array braces
            holder = holder.replaceAll(",", ""); //Removes the commas from the string
            sAndP500GrowthDataObj2 = holder.split(" ");

            int commArrLength = sAndP500GrowthDataObj2.length;
            for (int i = 0; i <= commArrLength - 1; i++) {
                double sAndP500GrowthData = Double.parseDouble((String) Array.get(sAndP500GrowthDataObj2, i));
                sAndP500GrowthVals2.add(new Entry(i, (float) sAndP500GrowthData));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return sAndP500GrowthVals2;
    }

    private ArrayList<Entry> uSEnergyDataFunction2(ArrayList commValArrList) {
        ArrayList<Entry> uSEnergyVals2 = new ArrayList<>();
        try {
            String holder = ((Commodities) commValArrList.get(16)).getValue();
            holder = holder.substring(1, holder.length() - 1); //Removes the [] javascript array braces
            holder = holder.replaceAll(",", ""); //Removes the commas from the string
            uSEnergyDataObj2 = holder.split(" ");

            int commArrLength = uSEnergyDataObj2.length;
            for (int i = 0; i <= commArrLength - 1; i++) {
                double uSEnergyData = Double.parseDouble((String) Array.get(uSEnergyDataObj2, i));
                uSEnergyVals2.add(new Entry(i, (float) uSEnergyData));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return uSEnergyVals2;
    }

    private ArrayList<Entry> teucriumGrainDataFunction2(ArrayList commValArrList) {
        ArrayList<Entry> teucriumGrainVals2 = new ArrayList<>();
        try {
            String holder = ((Commodities) commValArrList.get(17)).getValue();
            holder = holder.substring(1, holder.length() - 1); //Removes the [] javascript array braces
            holder = holder.replaceAll(",", ""); //Removes the commas from the string
            teucriumGrainDataObj2 = holder.split(" ");

            int commArrLength = teucriumGrainDataObj2.length;
            for (int i = 0; i <= commArrLength - 1; i++) {
                double teucriumGrainData = Double.parseDouble((String) Array.get(teucriumGrainDataObj2, i));
                teucriumGrainVals2.add(new Entry(i, (float) teucriumGrainData));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return teucriumGrainVals2;
    }

    private ArrayList<Entry> bloombergCoffeeDataFunction2(ArrayList commValArrList) {
        ArrayList<Entry> bloombergCoffeeVals2 = new ArrayList<>();
        try {
            String holder = ((Commodities) commValArrList.get(18)).getValue();
            holder = holder.substring(1, holder.length() - 1); //Removes the [] javascript array braces
            holder = holder.replaceAll(",", ""); //Removes the commas from the string
            bloombergCoffeeDataObj2 = holder.split(" ");

            int commArrLength = bloombergCoffeeDataObj2.length;
            for (int i = 0; i <= commArrLength - 1; i++) {
                double bloombergCoffeeData = Double.parseDouble((String) Array.get(bloombergCoffeeDataObj2, i));
                bloombergCoffeeVals2.add(new Entry(i, (float) bloombergCoffeeData));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return bloombergCoffeeVals2;
    }

    private ArrayList<Entry> jPMorganChaseDataFunction2(ArrayList commValArrList) {
        ArrayList<Entry> jPMorganChaseVals2 = new ArrayList<>();
        try {
            String holder = ((Commodities) commValArrList.get(19)).getValue();
            holder = holder.substring(1, holder.length() - 1); //Removes the [] javascript array braces
            holder = holder.replaceAll(",", ""); //Removes the commas from the string
            jPMorganChaseDataObj2 = holder.split(" ");

            int commArrLength = jPMorganChaseDataObj2.length;
            for (int i = 0; i <= commArrLength - 1; i++) {
                double jPMorganChaseData = Double.parseDouble((String) Array.get(jPMorganChaseDataObj2, i));
                jPMorganChaseVals2.add(new Entry(i, (float) jPMorganChaseData));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return jPMorganChaseVals2;
    }

    private ArrayList<Entry> cocaColaDataFunction2(ArrayList commValArrList) {
        ArrayList<Entry> cocaColaVals2 = new ArrayList<>();
        try {
            String holder = ((Commodities) commValArrList.get(20)).getValue();
            holder = holder.substring(1, holder.length() - 1); //Removes the [] javascript array braces
            holder = holder.replaceAll(",", ""); //Removes the commas from the string
            cocaColaDataObj2 = holder.split(" ");

            int commArrLength = cocaColaDataObj2.length;
            for (int i = 0; i <= commArrLength - 1; i++) {
                double cocaColaData = Double.parseDouble((String) Array.get(cocaColaDataObj2, i));
                cocaColaVals2.add(new Entry(i, (float) cocaColaData));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return cocaColaVals2;
    }

    private ArrayList<Entry> microsoftDataFunction2(ArrayList commValArrList) {
        ArrayList<Entry> microsoftVals2 = new ArrayList<>();
        try {
            String holder = ((Commodities) commValArrList.get(21)).getValue();
            holder = holder.substring(1, holder.length() - 1); //Removes the [] javascript array braces
            holder = holder.replaceAll(",", ""); //Removes the commas from the string
            microsoftDataObj2 = holder.split(" ");

            int commArrLength = microsoftDataObj2.length;
            for (int i = 0; i <= commArrLength - 1; i++) {
                double microsoftData = Double.parseDouble((String) Array.get(microsoftDataObj2, i));
                microsoftVals2.add(new Entry(i, (float) microsoftData));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return microsoftVals2;
    }

    private ArrayList<Entry> nasdaqDataFunction2(ArrayList commValArrList) {
        ArrayList<Entry> nasdaqVals2 = new ArrayList<>();
        try {
            String holder = ((Commodities) commValArrList.get(22)).getValue();
            holder = holder.substring(1, holder.length() - 1); //Removes the [] javascript array braces
            holder = holder.replaceAll(",", ""); //Removes the commas from the string
            nasdaqDataObj2 = holder.split(" ");

            int commArrLength = nasdaqDataObj2.length;
            for (int i = 0; i <= commArrLength - 1; i++) {
                double nasdaqData = Double.parseDouble((String) Array.get(nasdaqDataObj2, i));
                nasdaqVals2.add(new Entry(i, (float) nasdaqData));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return nasdaqVals2;
    }

    private ArrayList<Entry> globalXSilverMinersDataFunction2(ArrayList commValArrList) {
        ArrayList<Entry> globalXSilverMinersVals2 = new ArrayList<>();
        try {
            String holder = ((Commodities) commValArrList.get(23)).getValue();
            holder = holder.substring(1, holder.length() - 1); //Removes the [] javascript array braces
            holder = holder.replaceAll(",", ""); //Removes the commas from the string
            globalXSilverMinersDataObj2 = holder.split(" ");

            int commArrLength = globalXSilverMinersDataObj2.length;
            for (int i = 0; i <= commArrLength - 1; i++) {
                double globalXSilverMinersData = Double.parseDouble((String) Array.get(globalXSilverMinersDataObj2, i));
                globalXSilverMinersVals2.add(new Entry(i, (float) globalXSilverMinersData));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return globalXSilverMinersVals2;
    }

    private ArrayList<Entry> vanEckVectorsSteelDataFunction2(ArrayList commValArrList) {
        ArrayList<Entry> vanEckVectorsSteelVals2 = new ArrayList<>();
        try {
            String holder = ((Commodities) commValArrList.get(24)).getValue();
            holder = holder.substring(1, holder.length() - 1); //Removes the [] javascript array braces
            holder = holder.replaceAll(",", ""); //Removes the commas from the string
            vanEckVectorsSteelDataObj2 = holder.split(" ");

            int commArrLength = vanEckVectorsSteelDataObj2.length;
            for (int i = 0; i <= commArrLength - 1; i++) {
                double vanEckVectorsSteelData = Double.parseDouble((String) Array.get(vanEckVectorsSteelDataObj2, i));
                vanEckVectorsSteelVals2.add(new Entry(i, (float) vanEckVectorsSteelData));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return vanEckVectorsSteelVals2;
    }

    private ArrayList<Entry> unitedHealthGroupDataFunction2(ArrayList commValArrList) {
        ArrayList<Entry> unitedHealthGroupVals2 = new ArrayList<>();
        try {
            String holder = ((Commodities) commValArrList.get(25)).getValue();
            holder = holder.substring(1, holder.length() - 1); //Removes the [] javascript array braces
            holder = holder.replaceAll(",", ""); //Removes the commas from the string
            unitedHealthGroupDataObj2 = holder.split(" ");

            int commArrLength = unitedHealthGroupDataObj2.length;
            for (int i = 0; i <= commArrLength - 1; i++) {
                double unitedHealthGroupData = Double.parseDouble((String) Array.get(unitedHealthGroupDataObj2, i));
                unitedHealthGroupVals2.add(new Entry(i, (float) unitedHealthGroupData));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return unitedHealthGroupVals2;
    }


    private ArrayList<Entry> exxonMobilDataFunction2(ArrayList commValArrList) {
        ArrayList<Entry> exxonMobilVals2 = new ArrayList<>();
        try {
            String holder = ((Commodities) commValArrList.get(26)).getValue();
            holder = holder.substring(1, holder.length() - 1); //Removes the [] javascript array braces
            holder = holder.replaceAll(",", ""); //Removes the commas from the string
            exxonMobilDataObj2 = holder.split(" ");

            //Loads the data into the exxonMobilVals2 Arraylist as Entry objects
            int commArrLength = exxonMobilDataObj2.length;
            for (int i = 0; i <= commArrLength - 1; i++) {
                double exxmData = Double.parseDouble((String) Array.get(exxonMobilDataObj2, i));
                exxonMobilVals2.add(new Entry(i, (float) exxmData));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return exxonMobilVals2;
    }

    //********************************************************* START OF DATABASE RELATED FUNCTIONS ******************************************************************

    private ArrayList dailyCommoditiesDataFunction(JSONArray response) {

        //This may turn out to be the place that we test FIRST if the database data has not yet been updated
        //if, then do the below code...,
        //The query is actually "if the database is empty OR the database data is dated from the previous day"
        //So there must be two conditions to the if statement
        //I will have to either add the date data on this side or change what I send over to the Android side
        //Either way this will require I change the commodity object's structure

//        long dateInMillis = System.currentTimeMillis();
//        String format = "yyyy-MM-dd HH:mm:ss";
//        final SimpleDateFormat sdf = new SimpleDateFormat(format);
//        String dateString = sdf.format(new Date(dateInMillis));
//        System.out.println("This is the UTC time: " + dateString);

        final String DATEFORMAT = "yyyy-MM-dd HH:mm:ss";


        final SimpleDateFormat sdf = new SimpleDateFormat(DATEFORMAT);
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        final String utcDate = sdf.format(new Date());
//        System.out.println("This is the UTC date in utcDate variable: " + utcDate);


        String dateValFromDB = CheckDate();
//        System.out.println("This is the value of the CheckDate() call: " + dateValFromDB);

        String[] comparisonArr1;
        String[] comparisonArr2;
        comparisonArr1 = utcDate.split(" ");
        comparisonArr2 = dateValFromDB.split("T");

        //Checks the date value strings from the database on the phone vs UTC time for equality
        //The first condition is for the first time the app is open after being downloaded to the phone
        //The second condition is for subsequent reloads of the app
        //If the UTC date for today don't match the date in the database, we drop the table, create a new one,
        //then query fresh data
        //Else the data is fresh
        if(dateValFromDB == "" || !(comparisonArr2[0].equals(comparisonArr1[0]))) {
            DropTable();
            CreateTable();
        } else {
//            System.out.println("The data is as fresh as baking bread!");
        }


        boolean flag = mDatabaseHelper.checkIfEmpty();
//        System.out.println("This is the value of the flag in dailyCommoditiesDataFunction(): " + flag);

        if(flag) {

            ArrayList dailyCommoditiesVals1 = new ArrayList<Commodities>();
            try {
                for (int i = 0; i < response.length(); i++) {


                    //if response.getJSONObject(i) is not NULL
                    String column2 = response.getJSONObject(i).getString("name");
                    String column3 = response.getJSONObject(i).getString("value");
                    String column4 = response.getJSONObject(i).getString("date");
                    String column5 = response.getJSONObject(i).getString("rangeVal");
                    AddData(column2, column3, column4, column5);

                    //Here we build the arraylist that will feed the Commodities instances to
                    //to the dailyCommoditiesVals arraylist
                    Commodities commodityMint = new Commodities();
                    commodityMint.setName(column2);
                    commodityMint.setValue(column3);
                    commodityMint.setDate(column4);
                    commodityMint.setRangeVal(column5);
                    dailyCommoditiesVals1.add(i, commodityMint);

                }//end of 1st for loop in the try block

                dataVals2 = bitCoinDataFunction2(dailyCommoditiesVals1);
                cornVals2 = cornDataFunction2(dailyCommoditiesVals1);
                appleVals2 = appleDataFunction2(dailyCommoditiesVals1);
                soybVals2 = soybDataFunction2(dailyCommoditiesVals1);
                sugarVals2 = sugarDataFunction2(dailyCommoditiesVals1);
                amazonVals2 = amazonDataFunction2(dailyCommoditiesVals1);
                boeingVals2 = boeingDataFunction2(dailyCommoditiesVals1);
                bloombergCottonVals2 = bloombergCottonDataFunction2(dailyCommoditiesVals1);
                invescoDbOilVals2 = invescoDbOilDataFunction2(dailyCommoditiesVals1);
                duPontVals2 = duPontDataFunction2(dailyCommoditiesVals1);
                consolidatedEdisonVals2 = consolidatedEdisonDataFunction2(dailyCommoditiesVals1);
                vanEckVectorsGoldMinersVals2 = vanEckVectorsGoldMinersDataFunction2(dailyCommoditiesVals1);
                alphabetVals2 = alphabetDataFunction2(dailyCommoditiesVals1);
                nyseVals2 = nyseDataFunction2(dailyCommoditiesVals1);
                sAndP500Vals2 = sAndP500DataFunction2(dailyCommoditiesVals1);
                sAndP500GrowthVals2 = sAndP500GrowthDataFunction2(dailyCommoditiesVals1);
                uSEnergyVals2 = uSEnergyDataFunction2(dailyCommoditiesVals1);
                teucriumGrainVals2 = teucriumGrainDataFunction2(dailyCommoditiesVals1);
                bloombergCoffeeVals2 = bloombergCoffeeDataFunction2(dailyCommoditiesVals1);
                jPMorganChaseVals2 = jPMorganChaseDataFunction2(dailyCommoditiesVals1);
                cocaColaVals2 = cocaColaDataFunction2(dailyCommoditiesVals1);
                microsoftVals2 = microsoftDataFunction2(dailyCommoditiesVals1);
                nasdaqVals2 = nasdaqDataFunction2(dailyCommoditiesVals1);
                globalXSilverMinersVals2 = globalXSilverMinersDataFunction2(dailyCommoditiesVals1);
                vanEckVectorsSteelVals2 = vanEckVectorsSteelDataFunction2(dailyCommoditiesVals1);
                unitedHealthGroupVals2 = unitedHealthGroupDataFunction2(dailyCommoditiesVals1);
                exxonMobilVals2 = exxonMobilDataFunction2(dailyCommoditiesVals1);

                //Loading the commoditySelector with ArrayList<Entry> instances
                commoditySelector.setCornVals(cornVals2);
                commoditySelector.setAppleVals(appleVals2);
                commoditySelector.setSoybVals(soybVals2);
                commoditySelector.setSugarVals(sugarVals2);
                commoditySelector.setAmazonVals(amazonVals2);
                commoditySelector.setBoeingVals(boeingVals2);
                commoditySelector.setBloombergCottonVals(bloombergCottonVals2);
                commoditySelector.setInvescoDbOilVals(invescoDbOilVals2);
                commoditySelector.setDuPontVals(duPontVals2);
                commoditySelector.setConsolidatedEdisonVals(consolidatedEdisonVals2);
                commoditySelector.setVanEckVectorsGoldMinersVals(vanEckVectorsGoldMinersVals2);
                commoditySelector.setAlphabetVals(alphabetVals2);
                commoditySelector.setNyseVals(nyseVals2);
                commoditySelector.setsAndP500Vals(sAndP500Vals2);
                commoditySelector.setsAndP500GrowthVals(sAndP500GrowthVals2);
                commoditySelector.setuSEnergyVals(uSEnergyVals2);
                commoditySelector.setTeucriumGrainVals(teucriumGrainVals2);
                commoditySelector.setBloombergCoffeeVals(bloombergCoffeeVals2);
                commoditySelector.setjPMorganChaseVals(jPMorganChaseVals2);
                commoditySelector.setCocaColaVals(cocaColaVals2);
                commoditySelector.setMicrosoftVals(microsoftVals2);
                commoditySelector.setNasdaqVals(nasdaqVals2);
                commoditySelector.setGlobalXSilverMinersVals(globalXSilverMinersVals2);
                commoditySelector.setVanEckVectorsSteelVals(vanEckVectorsSteelVals2);
                commoditySelector.setUnitedHealthGroupVals(unitedHealthGroupVals2);
                commoditySelector.setExxonMobilVals(exxonMobilVals2);
                defaultCryptoSelector();

            } catch (JSONException e) {
                e.printStackTrace();
            }

        } else {

            //...else just get the data from the database and use that for rendering the chart
            //I think that else is going to be the code that actually would query the SQLite database here,
            //and return that data
            GetData();

            //**********************Start of SQLite database query code******************************
            //**********************End of SQLite database query code********************************
//            System.out.println("$$$$$$$$$$$$$$$$$$ Else: This proves that the database has data in it and Android knows!!!! $$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        } //end of the else


        return dailyCommoditiesVals;
    }

    //Method that starts the process of adding data to the SQLite database
    public void AddData(String column2, String column3, String column4, String column5) {
        boolean insertData = mDatabaseHelper.addData(column2, column3, column4, column5);

        if (insertData) {
//            System.out.println("Data Successfully Inserted");
        } else {
//            System.out.println("Something went wrong");
        }
    }

    public String CheckDate() {
        Cursor returnData = mDatabaseHelper.checkDate();
        String dateVal = "";
        if(returnData != null)
        {
            if (returnData.moveToFirst()) {
                dateVal = returnData.getString(3);
//                System.out.println("This is the date value in the first row of data in the SQLite database: " + dateVal);
            }
//            returnData.close();
        }

        return dateVal;
    }

    public void DropTable() {
        mDatabaseHelper.dropTable();
    }

    public void CreateTable() {
        mDatabaseHelper.createTable();
    }

    //Method that returns all the data in the daily_commodities_values table
    public void GetData() {

        //Creates a Cursor instance with the table data
        Cursor returnData = mDatabaseHelper.getData();

        if (returnData.getCount() != 0) {
//            System.out.println("Inside GetData(): Data Successfully Retrieved");
//            System.out.println(returnData.toString());
//            System.out.println(returnData.getColumnNames());
            String holder1 = (String) Array.get(returnData.getColumnNames(), 0);
            String holder2 = (String) Array.get(returnData.getColumnNames(), 1);
            String holder3 = (String) Array.get(returnData.getColumnNames(), 2);
            String holder4 = (String) Array.get(returnData.getColumnNames(), 3);
            String holder5 = (String) Array.get(returnData.getColumnNames(), 4);
//            System.out.println("This is the value in holder1: " + holder1);
//            System.out.println("This is the value in holder2: " + holder2);
//            System.out.println("This is the value in holder3: " + holder3);
//            System.out.println("This is the value in holder4: " + holder4);
//            System.out.println("This is the value in holder5: " + holder5);
//            returnData.close();

            int row = 0;
            int i = 0;
            ArrayList dailyCommoditiesVals2 = new ArrayList<Commodities>();

            //Gets the column names and the data from the cursor
            while(returnData.moveToNext()) {
                int columnIndex;

                columnIndex = returnData.getColumnIndexOrThrow("id");
                int id = returnData.getInt(columnIndex);
//                System.out.println("This is the id from column " + columnIndex + " row " + row + " as a Java value: " + id);
                columnIndex = returnData.getColumnIndexOrThrow("name");
                String name = returnData.getString(columnIndex);
//                System.out.println("This is the name from column " + columnIndex + " row " + row + " as a Java value " + name);
                columnIndex = returnData.getColumnIndexOrThrow("value");
                String value = returnData.getString(columnIndex);
//                System.out.println("This is the value from column " + columnIndex + " row " + row + " as a Java value " + value);
                columnIndex = returnData.getColumnIndexOrThrow("date");
                String date = returnData.getString(columnIndex);
//                System.out.println("This is the value from column " + columnIndex + " row " + row + " as a Java value " + date);
                columnIndex = returnData.getColumnIndexOrThrow("rangeVal");
                String rangeVal = returnData.getString(columnIndex);
//                System.out.println("This is the value from column " + columnIndex + " row " + row + " as a Java value " + rangeVal);


                //Loads those values to the AddData() method
                String column2 = name;
                String column3 = value;
                String column4 = date;
                String column5 = rangeVal;

                //Here we build the arraylist that will feed the Commodities instances to
                //to the dailyCommoditiesVals arraylist
                Commodities commodityMint = new Commodities();
                commodityMint.setName(column2);
                commodityMint.setValue(column3);
                commodityMint.setDate(column4);
                commodityMint.setRangeVal(column5);
                dailyCommoditiesVals2.add(i, commodityMint);

                i++;
                row++;

            }

            dataVals2 = bitCoinDataFunction2(dailyCommoditiesVals2);
            cornVals2 = cornDataFunction2(dailyCommoditiesVals2);
            appleVals2 = appleDataFunction2(dailyCommoditiesVals2);
            soybVals2 = soybDataFunction2(dailyCommoditiesVals2);
            sugarVals2 = sugarDataFunction2(dailyCommoditiesVals2);
            amazonVals2 = amazonDataFunction2(dailyCommoditiesVals2);
            boeingVals2 = boeingDataFunction2(dailyCommoditiesVals2);
            bloombergCottonVals2 = bloombergCottonDataFunction2(dailyCommoditiesVals2);
            invescoDbOilVals2 = invescoDbOilDataFunction2(dailyCommoditiesVals2);
            duPontVals2 = duPontDataFunction2(dailyCommoditiesVals2);
            consolidatedEdisonVals2 = consolidatedEdisonDataFunction2(dailyCommoditiesVals2);
            vanEckVectorsGoldMinersVals2 = vanEckVectorsGoldMinersDataFunction2(dailyCommoditiesVals2);
            alphabetVals2 = alphabetDataFunction2(dailyCommoditiesVals2);
            nyseVals2 = nyseDataFunction2(dailyCommoditiesVals2);
            sAndP500Vals2 = sAndP500DataFunction2(dailyCommoditiesVals2);
            sAndP500GrowthVals2 = sAndP500GrowthDataFunction2(dailyCommoditiesVals2);
            uSEnergyVals2 = uSEnergyDataFunction2(dailyCommoditiesVals2);
            teucriumGrainVals2 = teucriumGrainDataFunction2(dailyCommoditiesVals2);
            bloombergCoffeeVals2 = bloombergCoffeeDataFunction2(dailyCommoditiesVals2);
            jPMorganChaseVals2 = jPMorganChaseDataFunction2(dailyCommoditiesVals2);
            cocaColaVals2 = cocaColaDataFunction2(dailyCommoditiesVals2);
            microsoftVals2 = microsoftDataFunction2(dailyCommoditiesVals2);
            nasdaqVals2 = nasdaqDataFunction2(dailyCommoditiesVals2);
            globalXSilverMinersVals2 = globalXSilverMinersDataFunction2(dailyCommoditiesVals2);
            vanEckVectorsSteelVals2 = vanEckVectorsSteelDataFunction2(dailyCommoditiesVals2);
            unitedHealthGroupVals2 = unitedHealthGroupDataFunction2(dailyCommoditiesVals2);
            exxonMobilVals2 = exxonMobilDataFunction2(dailyCommoditiesVals2);

            //Loading the commoditySelector with ArrayList<Entry> instances
            commoditySelector.setCornVals(cornVals2);
            commoditySelector.setAppleVals(appleVals2);
            commoditySelector.setSoybVals(soybVals2);
            commoditySelector.setSugarVals(sugarVals2);
            commoditySelector.setAmazonVals(amazonVals2);
            commoditySelector.setBoeingVals(boeingVals2);
            commoditySelector.setBloombergCottonVals(bloombergCottonVals2);
            commoditySelector.setInvescoDbOilVals(invescoDbOilVals2);
            commoditySelector.setDuPontVals(duPontVals2);
            commoditySelector.setConsolidatedEdisonVals(consolidatedEdisonVals2);
            commoditySelector.setVanEckVectorsGoldMinersVals(vanEckVectorsGoldMinersVals2);
            commoditySelector.setAlphabetVals(alphabetVals2);
            commoditySelector.setNyseVals(nyseVals2);
            commoditySelector.setsAndP500Vals(sAndP500Vals2);
            commoditySelector.setsAndP500GrowthVals(sAndP500GrowthVals2);
            commoditySelector.setuSEnergyVals(uSEnergyVals2);
            commoditySelector.setTeucriumGrainVals(teucriumGrainVals2);
            commoditySelector.setBloombergCoffeeVals(bloombergCoffeeVals2);
            commoditySelector.setjPMorganChaseVals(jPMorganChaseVals2);
            commoditySelector.setCocaColaVals(cocaColaVals2);
            commoditySelector.setMicrosoftVals(microsoftVals2);
            commoditySelector.setNasdaqVals(nasdaqVals2);
            commoditySelector.setGlobalXSilverMinersVals(globalXSilverMinersVals2);
            commoditySelector.setVanEckVectorsSteelVals(vanEckVectorsSteelVals2);
            commoditySelector.setUnitedHealthGroupVals(unitedHealthGroupVals2);
            commoditySelector.setExxonMobilVals(exxonMobilVals2);
            defaultCryptoSelector();
        } else {
//            System.out.println("Inside GetData(): Something went wrong");
        }
    }

    //*********************************************************END OF DATABASE RELATED FUNCTIONS ******************************************************************


    //Here the default starting chart will get loaded with values
    @Override
    protected void onStart() {
        super.onStart();
        //stockSpinner = findViewById(R.id.stock_spinner);
        //bitCoinSpinner = findViewById(R.id.bitcoin_spinner);

        mpLineChart = findViewById(R.id.line_chart);
        mpLineChart.setNoDataText("Loading Data...");
        mpLineChart.setNoDataTextColor(Color.WHITE);

        //Gets the data from the SQLite database
        if(!mDatabaseHelper.checkIfEmpty()){
            GetData();
//            String dateValFromDB = CheckDate();
//            System.out.println("This is the value of the CheckDate() call: " + dateValFromDB);
        }
    }



    private void defaultCryptoSelector(){
        String stockSpinnerStrVal = "CORN";
        String bitCoinSpinnerStrVal = "BTC";
        int numOfDaysDefault = 365;
        threeHundredSixtyFiveDay.setBackgroundResource(R.drawable.curve2);
        sevenDay.setBackgroundResource(R.drawable.curve);
        fourteenDay.setBackgroundResource(R.drawable.curve);
        thirtyDay.setBackgroundResource(R.drawable.curve);
        ninetyDay.setBackgroundResource(R.drawable.curve);
        oneHundredEightyDay.setBackgroundResource(R.drawable.curve);
        currentBtn = numOfDaysDefault;
        cryptoSelector = new CryptoSelector();
        cryptoSelector.selectCrypto(stockSpinnerStrVal, bitCoinSpinnerStrVal, numOfDaysDefault, requestQueue, mpLineChart, commoditySelector);
        //sets the spinner to NDAQ
//        stockSpinner.setSelection(21);

        sevenDay.setOnClickListener(this);
        fourteenDay.setOnClickListener(this);
        thirtyDay.setOnClickListener(this);
        ninetyDay.setOnClickListener(this);
        oneHundredEightyDay.setOnClickListener(this);
        threeHundredSixtyFiveDay.setOnClickListener(this);


        HashMap<String, String> nameMap = CryptoCommodityNameMap.createNameMap();

        if(nameMap.containsKey(stockSpinnerStrVal)){
            TextView nameTextViewVal = (TextView) findViewById(R.id.commodity_text_view_name_value);
            nameTextViewVal.setText((String) nameMap.get(stockSpinnerStrVal));
        }

        if(nameMap.containsKey(bitCoinSpinnerStrVal)){
            TextView nameTextViewVal = (TextView) findViewById(R.id.crypto_text_view_name_value);
            nameTextViewVal.setText((String) nameMap.get(bitCoinSpinnerStrVal));
        }

        ArrayAdapter<String> stockSpinnerAdapter = new ArrayAdapter<String>(BitCoinVsStocksActivity.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.stock_tickers));
        stockSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stockSpinner.setAdapter(stockSpinnerAdapter);
        stockSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String, String> nameMap = CryptoCommodityNameMap.createNameMap();
                String stockSpinnerStrVal = stockSpinner.getSelectedItem().toString();
                String bitCoinSpinnerStrVal = bitCoinSpinner.getSelectedItem().toString();
                if(nameMap.containsKey(stockSpinnerStrVal)){
                    TextView nameTextViewVal = (TextView) findViewById(R.id.commodity_text_view_name_value);
                    nameTextViewVal.setText((String) nameMap.get(stockSpinnerStrVal));
                }
                TextView cryptoTextViewVal = (TextView) findViewById(R.id.crypto_text_view_value);
                cryptoTextViewVal.setText("");
                TextView cryptoTextViewDateVal = (TextView) findViewById(R.id.crypto_text_view_date_value);
                cryptoTextViewDateVal.setText("");
                TextView stockTextViewVal = (TextView) findViewById(R.id.commodity_text_view_value);
                stockTextViewVal.setText("");
                TextView stockTextViewDateVal = (TextView) findViewById(R.id.commodity_text_view_date_value);
                stockTextViewDateVal.setText("");
                cryptoSelector = new CryptoSelector();
                cryptoSelector.selectCrypto(stockSpinnerStrVal, bitCoinSpinnerStrVal, currentBtn, requestQueue, mpLineChart, commoditySelector);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> bitCoinSpinnerAdapter = new ArrayAdapter<String>(BitCoinVsStocksActivity.this,
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.bitcoin_tickers));
        bitCoinSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bitCoinSpinner.setAdapter(bitCoinSpinnerAdapter);
        bitCoinSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String, String> nameMap = CryptoCommodityNameMap.createNameMap();
                String stockSpinnerStrVal = stockSpinner.getSelectedItem().toString();
                String bitCoinSpinnerStrVal = bitCoinSpinner.getSelectedItem().toString();
                if(nameMap.containsKey(bitCoinSpinnerStrVal)){
                    TextView nameTextViewVal = (TextView) findViewById(R.id.crypto_text_view_name_value);
                    nameTextViewVal.setText((String) nameMap.get(bitCoinSpinnerStrVal));
                }
                TextView cryptoTextViewVal = (TextView) findViewById(R.id.crypto_text_view_value);
                cryptoTextViewVal.setText("");
                TextView cryptoTextViewDateVal = (TextView) findViewById(R.id.crypto_text_view_date_value);
                cryptoTextViewDateVal.setText("");
                TextView stockTextViewVal = (TextView) findViewById(R.id.commodity_text_view_value);
                stockTextViewVal.setText("");
                TextView stockTextViewDateVal = (TextView) findViewById(R.id.commodity_text_view_date_value);
                stockTextViewDateVal.setText("");
                cryptoSelector = new CryptoSelector();
                cryptoSelector.selectCrypto(stockSpinnerStrVal, bitCoinSpinnerStrVal, currentBtn, requestQueue, mpLineChart, commoditySelector);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

//        Create an event listener in the onResume() method that listens for a click event of the mpAndroid chart instance

        BitCoinVsStocksActivity.mpLineChart.setOnChartValueSelectedListener(this);


//        commodityValTextView.setText(commodityValTextView.toString());
//        cryptoValTextView.setText(cryptoValTextView.toString());

//        Context context = null;
//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View v = inflater.inflate(R.layout.activity_bit_coin_vs_stocks, null);
//        commodityValTextView = (TextView) v.findViewById(R.id.commodity_text_view_value);
//        cryptoValTextView = (TextView) v.findViewById(R.id.crypto_text_view_value);
//        System.out.println("I'm over here now! :" + commodityValTextView.toString());
//        System.out.println("Oh! :" + cryptoValTextView.toString());

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {

        //stockSpinner = findViewById(R.id.stock_spinner);
        //bitCoinSpinner = findViewById(R.id.bitcoin_spinner);
        String stockSpinnerStrVal = stockSpinner.getSelectedItem().toString();
        String bitCoinSpinnerStrVal = bitCoinSpinner.getSelectedItem().toString();

        mpLineChart = findViewById(R.id.line_chart);


        //String selectedURL = null;

        int id = v.getId();
        if (id == R.id.bitcoin_api_button0) {
            threeHundredSixtyFiveDay.setBackgroundResource(R.drawable.curve);
            sevenDay.setBackgroundResource(R.drawable.curve2);
            fourteenDay.setBackgroundResource(R.drawable.curve);
            thirtyDay.setBackgroundResource(R.drawable.curve);
            ninetyDay.setBackgroundResource(R.drawable.curve);
            oneHundredEightyDay.setBackgroundResource(R.drawable.curve);
            TextView cryptoTextViewVal = (TextView) findViewById(R.id.crypto_text_view_value);
            cryptoTextViewVal.setText("");
            TextView cryptoTextViewDateVal = (TextView) findViewById(R.id.crypto_text_view_date_value);
            cryptoTextViewDateVal.setText("");
            TextView stockTextViewVal = (TextView) findViewById(R.id.commodity_text_view_value);
            stockTextViewVal.setText("");
            TextView stockTextViewDateVal = (TextView) findViewById(R.id.commodity_text_view_date_value);
            stockTextViewDateVal.setText("");
            int numOfDaysBtn0 = 7;
//                System.out.println(sevenDay);
//                System.out.println(stockSpinnerStrVal + " + " + bitCoinSpinnerStrVal);
            cryptoSelector = new CryptoSelector();
            cryptoSelector.selectCrypto(stockSpinnerStrVal, bitCoinSpinnerStrVal, numOfDaysBtn0, requestQueue, mpLineChart, commoditySelector);
            currentBtn = numOfDaysBtn0;
        } else if (id == R.id.bitcoin_api_button1) {
            threeHundredSixtyFiveDay.setBackgroundResource(R.drawable.curve);
            sevenDay.setBackgroundResource(R.drawable.curve);
            fourteenDay.setBackgroundResource(R.drawable.curve2);
            thirtyDay.setBackgroundResource(R.drawable.curve);
            ninetyDay.setBackgroundResource(R.drawable.curve);
            oneHundredEightyDay.setBackgroundResource(R.drawable.curve);
            TextView cryptoTextViewVal = (TextView) findViewById(R.id.crypto_text_view_value);
            cryptoTextViewVal.setText("");
            TextView cryptoTextViewDateVal = (TextView) findViewById(R.id.crypto_text_view_date_value);
            cryptoTextViewDateVal.setText("");
            TextView stockTextViewVal = (TextView) findViewById(R.id.commodity_text_view_value);
            stockTextViewVal.setText("");
            TextView stockTextViewDateVal = (TextView) findViewById(R.id.commodity_text_view_date_value);
            stockTextViewDateVal.setText("");
            int numOfDaysBtn1 = 14;
//                System.out.println(fourteenDay);
//                System.out.println(stockSpinnerStrVal + " + " + bitCoinSpinnerStrVal);
            cryptoSelector = new CryptoSelector();
            cryptoSelector.selectCrypto(stockSpinnerStrVal, bitCoinSpinnerStrVal, numOfDaysBtn1, requestQueue, mpLineChart, commoditySelector);
            currentBtn = numOfDaysBtn1;
        } else if (id == R.id.bitcoin_api_button2) {
            threeHundredSixtyFiveDay.setBackgroundResource(R.drawable.curve);
            sevenDay.setBackgroundResource(R.drawable.curve);
            fourteenDay.setBackgroundResource(R.drawable.curve);
            thirtyDay.setBackgroundResource(R.drawable.curve2);
            ninetyDay.setBackgroundResource(R.drawable.curve);
            oneHundredEightyDay.setBackgroundResource(R.drawable.curve);
            TextView cryptoTextViewVal = (TextView) findViewById(R.id.crypto_text_view_value);
            cryptoTextViewVal.setText("");
            TextView cryptoTextViewDateVal = (TextView) findViewById(R.id.crypto_text_view_date_value);
            cryptoTextViewDateVal.setText("");
            TextView stockTextViewVal = (TextView) findViewById(R.id.commodity_text_view_value);
            stockTextViewVal.setText("");
            TextView stockTextViewDateVal = (TextView) findViewById(R.id.commodity_text_view_date_value);
            stockTextViewDateVal.setText("");
            int numOfDaysBtn2 = 30;
//                System.out.println(thirtyDay);
//                System.out.println(stockSpinnerStrVal + " + " + bitCoinSpinnerStrVal);
            cryptoSelector = new CryptoSelector();
            cryptoSelector.selectCrypto(stockSpinnerStrVal, bitCoinSpinnerStrVal, numOfDaysBtn2, requestQueue, mpLineChart, commoditySelector);
            currentBtn = numOfDaysBtn2;
        } else if (id == R.id.bitcoin_api_button3) {
            threeHundredSixtyFiveDay.setBackgroundResource(R.drawable.curve);
            sevenDay.setBackgroundResource(R.drawable.curve);
            fourteenDay.setBackgroundResource(R.drawable.curve);
            thirtyDay.setBackgroundResource(R.drawable.curve);
            ninetyDay.setBackgroundResource(R.drawable.curve2);
            oneHundredEightyDay.setBackgroundResource(R.drawable.curve);
            TextView cryptoTextViewVal = (TextView) findViewById(R.id.crypto_text_view_value);
            cryptoTextViewVal.setText("");
            TextView cryptoTextViewDateVal = (TextView) findViewById(R.id.crypto_text_view_date_value);
            cryptoTextViewDateVal.setText("");
            TextView stockTextViewVal = (TextView) findViewById(R.id.commodity_text_view_value);
            stockTextViewVal.setText("");
            TextView stockTextViewDateVal = (TextView) findViewById(R.id.commodity_text_view_date_value);
            stockTextViewDateVal.setText("");
            int numOfDaysBtn3 = 90;
//                System.out.println(ninetyDay);
//                System.out.println(stockSpinnerStrVal + " + " + bitCoinSpinnerStrVal);
            cryptoSelector = new CryptoSelector();
            cryptoSelector.selectCrypto(stockSpinnerStrVal, bitCoinSpinnerStrVal, numOfDaysBtn3, requestQueue, mpLineChart, commoditySelector);
            currentBtn = numOfDaysBtn3;
        } else if (id == R.id.bitcoin_api_button4) {
            threeHundredSixtyFiveDay.setBackgroundResource(R.drawable.curve);
            sevenDay.setBackgroundResource(R.drawable.curve);
            fourteenDay.setBackgroundResource(R.drawable.curve);
            thirtyDay.setBackgroundResource(R.drawable.curve);
            ninetyDay.setBackgroundResource(R.drawable.curve);
            oneHundredEightyDay.setBackgroundResource(R.drawable.curve2);
            TextView cryptoTextViewVal = (TextView) findViewById(R.id.crypto_text_view_value);
            cryptoTextViewVal.setText("");
            TextView cryptoTextViewDateVal = (TextView) findViewById(R.id.crypto_text_view_date_value);
            cryptoTextViewDateVal.setText("");
            TextView stockTextViewVal = (TextView) findViewById(R.id.commodity_text_view_value);
            stockTextViewVal.setText("");
            TextView stockTextViewDateVal = (TextView) findViewById(R.id.commodity_text_view_date_value);
            stockTextViewDateVal.setText("");
            int numOfDaysBtn4 = 180;
//                System.out.println(oneHundredEightyDay);
//                System.out.println(stockSpinnerStrVal + " + " + bitCoinSpinnerStrVal);
            cryptoSelector = new CryptoSelector();
            cryptoSelector.selectCrypto(stockSpinnerStrVal, bitCoinSpinnerStrVal, numOfDaysBtn4, requestQueue, mpLineChart, commoditySelector);
            currentBtn = numOfDaysBtn4;
        } else if (id == R.id.bitcoin_api_button5) {
            threeHundredSixtyFiveDay.setBackgroundResource(R.drawable.curve2);
            sevenDay.setBackgroundResource(R.drawable.curve);
            fourteenDay.setBackgroundResource(R.drawable.curve);
            thirtyDay.setBackgroundResource(R.drawable.curve);
            ninetyDay.setBackgroundResource(R.drawable.curve);
            oneHundredEightyDay.setBackgroundResource(R.drawable.curve);
            TextView cryptoTextViewVal = (TextView) findViewById(R.id.crypto_text_view_value);
            cryptoTextViewVal.setText("");
            TextView cryptoTextViewDateVal = (TextView) findViewById(R.id.crypto_text_view_date_value);
            cryptoTextViewDateVal.setText("");
            TextView stockTextViewVal = (TextView) findViewById(R.id.commodity_text_view_value);
            stockTextViewVal.setText("");
            TextView stockTextViewDateVal = (TextView) findViewById(R.id.commodity_text_view_date_value);
            stockTextViewDateVal.setText("");
            int numOfDaysBtn5 = 365;
//                System.out.println(threeHundredSixtyFiveDay);
//                System.out.println(stockSpinnerStrVal + " + " + bitCoinSpinnerStrVal);
            cryptoSelector = new CryptoSelector();
            cryptoSelector.selectCrypto(stockSpinnerStrVal, bitCoinSpinnerStrVal, numOfDaysBtn5, requestQueue, mpLineChart, commoditySelector);
            currentBtn = numOfDaysBtn5;
        }
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

        //These variables will be renamed closePriceCommodityVal and endOfDayPriceCryptoVal


        double lastPriceCommodityVal = BitCoinVsStocksActivity.commodityPriceArray.get((int) e.getX());
        double lastPriceCryptoVal = BitCoinVsStocksActivity.cryptoPriceArray.get((int) e.getX());
        commodityValTextView.setText(String.format(Locale.US, "%.2f", lastPriceCommodityVal));
        cryptoValTextView.setText(String.format(Locale.US, "%.2f", lastPriceCryptoVal));


        String[] holderArr;
//        System.out.println("This is the value of e.getX(): " + (int) e.getX());
//        System.out.println("These are the values stored in rangeOfDateVals: " + rangeOfDateVals);
        String holder = rangeOfDateVals;
        holder = holder.substring(1, holder.length() - 1); //Removes the [] javascript array braces
        holder = holder.replaceAll(",", ""); //Removes the commas from the string
        holder = holder.replaceAll("T00:00:00", "");
        holder = holder.replaceAll("\\+0000", "");
        holder = holder.replaceAll("\"", "");
        holderArr = holder.split(" ");

//        System.out.println("This is the value stored in the holderArr at the plot point selected: " + holderArr[(int) e.getX()]);


        //This gives me the value of the last timeperiod selected. This will be useful for the switch statement that
        //I must create here that branches to subarrays to render the date data correctly
//        System.out.println("This is the value of currentBtn: " + currentBtn);


        commodityDateValTextView.setText(holderArr[(int) e.getX()]);
        cryptoDateValTextView.setText(holderArr[(int) e.getX()]);

        try {
            switch(currentBtn) {
                case 7: {
                    String[] subHolderArr = Arrays.copyOfRange(holderArr, 358, 365);
                    commodityDateValTextView.setText(subHolderArr[(int) e.getX()]);
                    cryptoDateValTextView.setText(subHolderArr[(int) e.getX()]);
                    break;
                }
                case 14: {
                    String[] subHolderArr = Arrays.copyOfRange(holderArr, 351, 365);
                    commodityDateValTextView.setText(subHolderArr[(int) e.getX()]);
                    cryptoDateValTextView.setText(subHolderArr[(int) e.getX()]);
                    break;
                }
                case 30: {
                    String[] subHolderArr = Arrays.copyOfRange(holderArr, 335, 365);
                    commodityDateValTextView.setText(subHolderArr[(int) e.getX()]);
                    cryptoDateValTextView.setText(subHolderArr[(int) e.getX()]);
                    break;
                }
                case 90: {
                    String[] subHolderArr = Arrays.copyOfRange(holderArr, 275, 365);
                    commodityDateValTextView.setText(subHolderArr[(int) e.getX()]);
                    cryptoDateValTextView.setText(subHolderArr[(int) e.getX()]);
                    break;
                }
                case 180: {
                    String[] subHolderArr = Arrays.copyOfRange(holderArr, 185, 365);
                    commodityDateValTextView.setText(subHolderArr[(int) e.getX()]);
                    cryptoDateValTextView.setText(subHolderArr[(int) e.getX()]);
                    break;
                }
                case 365: {
                    commodityDateValTextView.setText(holderArr[(int) e.getX()]);
                    cryptoDateValTextView.setText(holderArr[(int) e.getX()]);
                    break;
                }
                default:
                    break;
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            ex.printStackTrace();
        }



    }//end of onValueSelected()

    @Override
    public void onNothingSelected() {

    }

}