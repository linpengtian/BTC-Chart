package com.btcbrunch;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.btcbrunch.R;
import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import android.os.Bundle;

public class BitCoinCandleStickActivity extends AppCompatActivity implements View.OnClickListener {

    CandleStickChart candleStickChart;
//    Button candleStickApiButton0;
    private double[] bitCoinData;

    ArrayList<CandleEntry> listValsCandleStick = new ArrayList<CandleEntry>();
    ArrayList<Candlestick> candlesticks = new ArrayList<Candlestick>();
    ArrayList<Candlestick> stockValsInOnClick = new ArrayList<Candlestick>();


    Button sevenDay;
    Button fourteenDay;
    Button thirtyDay;
    Button ninetyDay;
    Button oneHundredEightyDay;
    Button threeHundredSixtyFiveDay;
    Spinner bitCoinSpinner;
    RequestQueue requestQueue;
    String URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bit_coin_candle_stick);

        sevenDay = (Button) findViewById(R.id.candle_stick_api_button0);
        fourteenDay = (Button) findViewById(R.id.candle_stick_api_button1);
        thirtyDay = (Button) findViewById(R.id.candle_stick_api_button2);
        ninetyDay = (Button) findViewById(R.id.candle_stick_api_button3);
        oneHundredEightyDay = (Button) findViewById(R.id.candle_stick_api_button4);
        threeHundredSixtyFiveDay = (Button) findViewById(R.id.candle_stick_api_button5);

        sevenDay.setOnClickListener(this);
        fourteenDay.setOnClickListener(this);
        thirtyDay.setOnClickListener(this);
        ninetyDay.setOnClickListener(this);
        oneHundredEightyDay.setOnClickListener(this);
        threeHundredSixtyFiveDay.setOnClickListener(this);


        bitCoinSpinner = findViewById(R.id.bitcoin_spinner);
        ArrayAdapter<String> bitCoinSpinnerAdapter = new ArrayAdapter<String>(BitCoinCandleStickActivity.this, android.R.layout.simple_list_item_single_choice, getResources().getStringArray(R.array.bitcoin_tickers));
        bitCoinSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bitCoinSpinner.setAdapter(bitCoinSpinnerAdapter);


        //String URL="https://api.binance.com/api/v3/klines?symbol=BTCUSDT&interval=1d&limit=100";
        URL="https://btcbrunchapi.com/ROLLING_365_BTC";

        requestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest objectRequest1 = new JsonArrayRequest(Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //Log.e("Ticker1 Response", response.toString());
                        tickerDataFunction(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Log.e("Rest Response1 Error", error.toString());
                    }
                }
        );

        requestQueue.add(objectRequest1);

        //Links the line chart XML to the java code
        candleStickChart = findViewById(R.id.candle_stick_chart);

        //The button that loads the data from the API call into the chart
//        candleStickApiButton0 = findViewById(R.id.candle_stick_api_button0);

        //The code that formats the chart
        candleStickChart.setHighlightPerDragEnabled(true);
        candleStickChart.setDrawBorders(true);
        candleStickChart.setBorderColor(getResources().getColor(R.color.design_default_color_primary));

        YAxis yAxis = candleStickChart.getAxisLeft();
        YAxis rightAxis = candleStickChart.getAxisRight();
        yAxis.setDrawGridLines(false);
        rightAxis.setDrawGridLines(false);
        candleStickChart.requestDisallowInterceptTouchEvent(true);

        XAxis xAxis = candleStickChart.getXAxis();
        xAxis.setDrawGridLines(false);
        xAxis.setDrawLabels(false);
        rightAxis.setTextColor(Color.WHITE);
        yAxis.setDrawLabels(false);
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        xAxis.setAvoidFirstLastClipping(true);

        Legend legend = candleStickChart.getLegend();
        legend.setEnabled(false);




        sevenDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //We create another query object inside the onClick that will
                //eventually be given a new URL address to query for it's data

                //Place a switch here that will check the value of the string in cryptoTicker
                String cryptoTicker = bitCoinSpinner.getSelectedItem().toString();

                //The value of the crypto string that will trigger the switch to perform the desired query
                //System.out.println(cryptoTicker);
                String selectedURL;

                switch (cryptoTicker) {
                    case "BTC": {
                        selectedURL="https://btcbrunchapi.com/ROLLING_7_BTC";
                        //selectedURL="https://api.binance.com/api/v3/klines?symbol=BTCUSDT&interval=1d&limit=100";
                    }
                    break;
                    case "ETH": {
                        selectedURL="https://btcbrunchapi.com/ROLLING_7_ETH";
                        //selectedURL="https://api.binance.com/api/v3/klines?symbol=ETHUSDT&interval=1d&limit=100";
                    }
                    break;
                    case "BCH": {
                        selectedURL="https://btcbrunchapi.com/ROLLING_7_BCH";
                        //selectedURL="https://api.binance.com/api/v3/klines?symbol=BCHUSDT&interval=1d&limit=100";

                    }
                    break;
                    case "LTC": {
                        selectedURL="https://btcbrunchapi.com/ROLLING_7_LTC";
                        //selectedURL="https://api.binance.com/api/v3/klines?symbol=LTCUSDT&interval=1d&limit=100";

                    }
                    break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + cryptoTicker);
                }

                //System.out.println(selectedURL);


                JsonArrayRequest objectRequest2 = new JsonArrayRequest(Request.Method.GET,
                        selectedURL,
                        null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                //Log.e("Ticker2 Response", response.toString());
                                tickerDataFunction(response);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                //Log.e("Rest Response2 Error", error.toString());
                            }
                        }
                );

                //If if breaks anywhere, I think it might break here...

                requestQueue.add(objectRequest2);



             //Create a loop here that will iterate as many times as size
                int candleStickSize = candlesticks.size();
                int listValsCandleStickSize = listValsCandleStick.size();
                //System.out.println("This is the size of candlesticks that we are looking for : " + candleStickSize);
                //System.out.println("This is the size of listValsCandleStick that we are looking for : " + listValsCandleStickSize);

                //clear the chart values and prepare for a new chart in the requestqueue
                if(listValsCandleStickSize > 0) {
                    for(int i = listValsCandleStickSize - 1; i >= 0; i--) {

                        listValsCandleStick.remove(i);
                        listValsCandleStickSize--;
                        //System.out.println(listValsCandleStick.size());
                    }

                }

                  for(int idx = 0; idx <= candleStickSize - 1; idx++) {
                    listValsCandleStick.add(new CandleEntry(idx, candlesticks.get(idx).getHigh(), candlesticks.get(idx).getLow(), candlesticks.get(idx).getOpen(), candlesticks.get(idx).getClose()));
                 }

//                  This call SHOULD destroy any array already in the chart
                  if(candleStickSize > 0) {
                      for(int i = candleStickSize - 1; i >=0; i--) {
                          candlesticks.remove(i);
                          candleStickSize--;
                      }
                  }

                CandleDataSet dataList = new CandleDataSet(listValsCandleStick, "DataList 1");
                dataList.setColor(Color.rgb(80, 80, 80));

                dataList.setShadowColor(getResources().getColor(R.color.design_default_color_secondary));
                dataList.setShadowWidth(0.8f);
                dataList.setDecreasingColor(getResources().getColor(R.color.design_default_color_surface));
                dataList.setDecreasingPaintStyle(Paint.Style.FILL);
                dataList.setIncreasingColor(getResources().getColor(R.color.design_default_color_primary_variant));
                dataList.setIncreasingPaintStyle(Paint.Style.FILL);
                dataList.setNeutralColor(Color.LTGRAY);
                dataList.setDrawValues(false);

                CandleData data = new CandleData(dataList);

                candleStickChart.setData(data);
                candleStickChart.setBackgroundColor(Color.rgb(0, 0, 0));
                candleStickChart.invalidate();

            }//end of onClick()
        });
    }//end of onCreate()

//The code in this function is almost identical to the code in the onClick because I copied it from the onClick... BE CAREFUL TO KNOW WHERE YOU ARE!!!!
    //This code runs upon loading the Activity. It initializes the chart to 365 day data
    private ArrayList<Candlestick> tickerDataFunction(JSONArray response) {

        ArrayList<CandleEntry> candleVals = new ArrayList<>();
        try {

           for(int idx = 0; idx <= response.length() - 1; idx++) {
               JSONArray candlestickData = response.getJSONArray(idx);
               long data0 = (long) candlestickData.get(0);
               String data1 = (String) candlestickData.get(1);
               String data2 = (String) candlestickData.get(2);
               String data3 = (String) candlestickData.get(3);
               String data4 = (String) candlestickData.get(4);

               long convertedData0 = data0;
               float convertedData1 = Float.parseFloat(data1);
               float convertedData2 = Float.parseFloat(data2);
               float convertedData3 = Float.parseFloat(data3);
               float convertedData4 = Float.parseFloat(data4);


               Candlestick candlestick = new Candlestick(convertedData0, convertedData1, convertedData2, convertedData3, convertedData4);

               candlesticks.add(candlestick);
           }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Create a loop here that will iterate as many times as size
        int candleStickSize = candlesticks.size();
        int listValsCandleStickSize = listValsCandleStick.size();
        System.out.println("This is the size of candlesticks array list in tickerData() : " + candleStickSize);
        System.out.println("This is the size of listValsCandleStick in tickerData() first appearance: " + listValsCandleStickSize);

//        clear the chart values and prepare for a new chart in the requestqueue
        if(listValsCandleStickSize > 0) {
            for(int i = listValsCandleStickSize - 1; i >= 0; i--) {

                listValsCandleStick.remove(i);
                listValsCandleStickSize--;
                //System.out.println(listValsCandleStick.size());
            }
        }

        for(int idx = 0; idx <= candleStickSize - 1; idx++) {
            listValsCandleStick.add(new CandleEntry(idx, candlesticks.get(idx).getHigh(), candlesticks.get(idx).getLow(), candlesticks.get(idx).getOpen(), candlesticks.get(idx).getClose()));
        }

        listValsCandleStickSize = listValsCandleStick.size();
        System.out.println("This is the size of listValsCandleStick in tickerData() second appearance: " + listValsCandleStickSize);


        CandleDataSet dataList = new CandleDataSet(listValsCandleStick, "DataList 1");
        dataList.setColor(Color.rgb(80, 80, 80));

        dataList.setShadowColor(getResources().getColor(R.color.design_default_color_secondary));
        dataList.setShadowWidth(0.8f);
        dataList.setDecreasingColor(getResources().getColor(R.color.design_default_color_surface));
        dataList.setDecreasingPaintStyle(Paint.Style.FILL);
        dataList.setIncreasingColor(getResources().getColor(R.color.design_default_color_primary_variant));
        dataList.setIncreasingPaintStyle(Paint.Style.FILL);
        dataList.setNeutralColor(Color.LTGRAY);
        dataList.setDrawValues(false);

        CandleData data = new CandleData(dataList);

        candleStickChart.setData(data);
        candleStickChart.setBackgroundColor(Color.rgb(0, 0, 0));
        candleStickChart.invalidate();

        return candlesticks;
    }


    @Override
    public void onClick(View v) {
        bitCoinSpinner = findViewById(R.id.bitcoin_spinner);
        String bitCoinSpinnerStrVal = bitCoinSpinner.getSelectedItem().toString();

        candleStickChart = findViewById(R.id.line_chart);
        String selectedURL = null;

        int candleStickSize = candlesticks.size();
        int listValsCandleStickSize = listValsCandleStick.size();
//        System.out.println("This is the size of candlesticks that we are looking for : " + candleStickSize);
//        System.out.println("This is the size of listValsCandleStick that we are looking for : " + listValsCandleStickSize);

        switch (v.getId()) {
            case R.id.candle_stick_api_button0:
                switch (bitCoinSpinnerStrVal) {
                    case "BTC": {
                        System.out.println("Hi ho 7 day BTC!");
                        selectedURL = "https://btcbrunchapi.com/ROLLING_7_BTC";
                        String finalSelectedURL = selectedURL;

                        JsonArrayRequest objectRequest3 = new JsonArrayRequest(Request.Method.GET,
                                selectedURL,
                                null,
                                new Response.Listener<JSONArray>() {
                                    @Override
                                    public void onResponse(JSONArray response) {
                                        //Log.e("Ticker2 Response", response.toString());
                                        tickerDataFunction(response);

                                        //Create a loop here that will iterate as many times as size
                                        int candleStickSize = candlesticks.size();
                                        int listValsCandleStickSize = listValsCandleStick.size();
                                        //System.out.println("This is the size of candlesticks: " + candleStickSize);
                                        //System.out.println("This is the size of listValsCandleStick: " + listValsCandleStickSize);

                                        //clear the chart values and prepare for a new chart in the requestqueue
                                        if(listValsCandleStickSize > 0) {
                                            for(int i = listValsCandleStickSize - 1; i >= 0; i--) {

                                                listValsCandleStick.remove(i);
                                                listValsCandleStickSize--;
                                                //System.out.println(listValsCandleStick.size());
                                            }

                                        }

                                        for(int idx = 0; idx <= candleStickSize - 1; idx++) {
                                            listValsCandleStick.add(new CandleEntry(idx, candlesticks.get(idx).getHigh(), candlesticks.get(idx).getLow(), candlesticks.get(idx).getOpen(), candlesticks.get(idx).getClose()));
                                        }

                                        //This call SHOULD destroy any array already in the chart
                                        if(candleStickSize > 0) {
                                            for(int i = candleStickSize - 1; i >=0; i--) {
                                                candlesticks.remove(i);
                                                candleStickSize--;
                                            }
                                        }

                                        CandleDataSet dataList = new CandleDataSet(listValsCandleStick, "DataList 1");
                                        dataList.setColor(Color.rgb(80, 80, 80));

                                        dataList.setShadowColor(getResources().getColor(R.color.design_default_color_secondary));
                                        dataList.setShadowWidth(0.8f);
                                        dataList.setDecreasingColor(getResources().getColor(R.color.design_default_color_surface));
                                        dataList.setDecreasingPaintStyle(Paint.Style.FILL);
                                        dataList.setIncreasingColor(getResources().getColor(R.color.design_default_color_primary_variant));
                                        dataList.setIncreasingPaintStyle(Paint.Style.FILL);
                                        dataList.setNeutralColor(Color.LTGRAY);
                                        dataList.setDrawValues(false);

                                        CandleData data = new CandleData(dataList);

                                        candleStickChart.setData(data);
                                        candleStickChart.setBackgroundColor(Color.rgb(0, 0, 0));
                                        candleStickChart.invalidate();
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        //Log.e("Rest Response2 Error", error.toString());
                                    }
                                }
                        );

                        //If if breaks anywhere, I think it might break here...

                        requestQueue.add(objectRequest3);
                    }
                    break;
                    case "ETH": {
                        System.out.println("Hi ho 7 day ETH!");
                        selectedURL = "https://btcbrunchapi.com/ROLLING_7_ETH";
                        String finalSelectedURL = selectedURL;

                        JsonArrayRequest objectRequest3 = new JsonArrayRequest(Request.Method.GET,
                                selectedURL,
                                null,
                                new Response.Listener<JSONArray>() {
                                    @Override
                                    public void onResponse(JSONArray response) {
                                        //Log.e("Ticker2 Response", response.toString());
                                        tickerDataFunction(response);

                                        //Create a loop here that will iterate as many times as size
                                        int candleStickSize = candlesticks.size();
                                        int listValsCandleStickSize = listValsCandleStick.size();
                                        //System.out.println("This is the size of candlesticks: " + candleStickSize);
                                        //System.out.println("This is the size of listValsCandleStick: " + listValsCandleStickSize);

                                        //clear the chart values and prepare for a new chart in the requestqueue
                                        if(listValsCandleStickSize > 0) {
                                            for(int i = listValsCandleStickSize - 1; i >= 0; i--) {

                                                listValsCandleStick.remove(i);
                                                listValsCandleStickSize--;
                                                //System.out.println(listValsCandleStick.size());
                                            }

                                        }

                                        for(int idx = 0; idx <= candleStickSize - 1; idx++) {
                                            listValsCandleStick.add(new CandleEntry(idx, candlesticks.get(idx).getHigh(), candlesticks.get(idx).getLow(), candlesticks.get(idx).getOpen(), candlesticks.get(idx).getClose()));
                                        }

                                        //This call SHOULD destroy any array already in the chart
                                        if(candleStickSize > 0) {
                                            for(int i = candleStickSize - 1; i >=0; i--) {
                                                candlesticks.remove(i);
                                                candleStickSize--;
                                            }
                                        }

                                        CandleDataSet dataList = new CandleDataSet(listValsCandleStick, "DataList 1");
                                        dataList.setColor(Color.rgb(80, 80, 80));

                                        dataList.setShadowColor(getResources().getColor(R.color.design_default_color_secondary));
                                        dataList.setShadowWidth(0.8f);
                                        dataList.setDecreasingColor(getResources().getColor(R.color.design_default_color_surface));
                                        dataList.setDecreasingPaintStyle(Paint.Style.FILL);
                                        dataList.setIncreasingColor(getResources().getColor(R.color.design_default_color_primary_variant));
                                        dataList.setIncreasingPaintStyle(Paint.Style.FILL);
                                        dataList.setNeutralColor(Color.LTGRAY);
                                        dataList.setDrawValues(false);

                                        CandleData data = new CandleData(dataList);

                                        candleStickChart.setData(data);
                                        candleStickChart.setBackgroundColor(Color.rgb(0, 0, 0));
                                        candleStickChart.invalidate();
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        //Log.e("Rest Response2 Error", error.toString());
                                    }
                                }
                        );

                        //If if breaks anywhere, I think it might break here...

                        requestQueue.add(objectRequest3);
                    }
                    break;
                    case "BCH": {
                        System.out.println("Hi ho 7 day BCH!");
                        selectedURL = "https://btcbrunchapi.com/ROLLING_7_BCH";
                        String finalSelectedURL = selectedURL;

                        JsonArrayRequest objectRequest3 = new JsonArrayRequest(Request.Method.GET,
                                selectedURL,
                                null,
                                new Response.Listener<JSONArray>() {
                                    @Override
                                    public void onResponse(JSONArray response) {
                                        //Log.e("Ticker2 Response", response.toString());
                                        tickerDataFunction(response);

                                        //Create a loop here that will iterate as many times as size
                                        int candleStickSize = candlesticks.size();
                                        int listValsCandleStickSize = listValsCandleStick.size();
                                        //System.out.println("This is the size of candlesticks: " + candleStickSize);
                                        //System.out.println("This is the size of listValsCandleStick: " + listValsCandleStickSize);

                                        //clear the chart values and prepare for a new chart in the requestqueue
                                        if(listValsCandleStickSize > 0) {
                                            for(int i = listValsCandleStickSize - 1; i >= 0; i--) {

                                                listValsCandleStick.remove(i);
                                                listValsCandleStickSize--;
                                                //System.out.println(listValsCandleStick.size());
                                            }

                                        }

                                        for(int idx = 0; idx <= candleStickSize - 1; idx++) {
                                            listValsCandleStick.add(new CandleEntry(idx, candlesticks.get(idx).getHigh(), candlesticks.get(idx).getLow(), candlesticks.get(idx).getOpen(), candlesticks.get(idx).getClose()));
                                        }

                                        //This call SHOULD destroy any array already in the chart
                                        if(candleStickSize > 0) {
                                            for(int i = candleStickSize - 1; i >=0; i--) {
                                                candlesticks.remove(i);
                                                candleStickSize--;
                                            }
                                        }

                                        CandleDataSet dataList = new CandleDataSet(listValsCandleStick, "DataList 1");
                                        dataList.setColor(Color.rgb(80, 80, 80));

                                        dataList.setShadowColor(getResources().getColor(R.color.design_default_color_secondary));
                                        dataList.setShadowWidth(0.8f);
                                        dataList.setDecreasingColor(getResources().getColor(R.color.design_default_color_surface));
                                        dataList.setDecreasingPaintStyle(Paint.Style.FILL);
                                        dataList.setIncreasingColor(getResources().getColor(R.color.design_default_color_primary_variant));
                                        dataList.setIncreasingPaintStyle(Paint.Style.FILL);
                                        dataList.setNeutralColor(Color.LTGRAY);
                                        dataList.setDrawValues(false);

                                        CandleData data = new CandleData(dataList);

                                        candleStickChart.setData(data);
                                        candleStickChart.setBackgroundColor(Color.rgb(0, 0, 0));
                                        candleStickChart.invalidate();
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        //Log.e("Rest Response2 Error", error.toString());
                                    }
                                }
                        );

                        //If if breaks anywhere, I think it might break here...

                        requestQueue.add(objectRequest3);
                    }
                    break;
                    case "LTC": {
                        System.out.println("Hi ho 7 day LTC!");
                        selectedURL = "https://btcbrunchapi.com/ROLLING_7_LTC";
                        String finalSelectedURL = selectedURL;

                        JsonArrayRequest objectRequest3 = new JsonArrayRequest(Request.Method.GET,
                                selectedURL,
                                null,
                                new Response.Listener<JSONArray>() {
                                    @Override
                                    public void onResponse(JSONArray response) {
                                        //Log.e("Ticker2 Response", response.toString());
                                        tickerDataFunction(response);

                                        //Create a loop here that will iterate as many times as size
                                        int candleStickSize = candlesticks.size();
                                        int listValsCandleStickSize = listValsCandleStick.size();
                                        //System.out.println("This is the size of candlesticks: " + candleStickSize);
                                        //System.out.println("This is the size of listValsCandleStick: " + listValsCandleStickSize);

                                        //clear the chart values and prepare for a new chart in the requestqueue
                                        if(listValsCandleStickSize > 0) {
                                            for(int i = listValsCandleStickSize - 1; i >= 0; i--) {

                                                listValsCandleStick.remove(i);
                                                listValsCandleStickSize--;
                                                //System.out.println(listValsCandleStick.size());
                                            }

                                        }

                                        for(int idx = 0; idx <= candleStickSize - 1; idx++) {
                                            listValsCandleStick.add(new CandleEntry(idx, candlesticks.get(idx).getHigh(), candlesticks.get(idx).getLow(), candlesticks.get(idx).getOpen(), candlesticks.get(idx).getClose()));
                                        }

                                        //This call SHOULD destroy any array already in the chart
                                        if(candleStickSize > 0) {
                                            for(int i = candleStickSize - 1; i >=0; i--) {
                                                candlesticks.remove(i);
                                                candleStickSize--;
                                            }
                                        }

                                        CandleDataSet dataList = new CandleDataSet(listValsCandleStick, "DataList 1");
                                        dataList.setColor(Color.rgb(80, 80, 80));

                                        dataList.setShadowColor(getResources().getColor(R.color.design_default_color_secondary));
                                        dataList.setShadowWidth(0.8f);
                                        dataList.setDecreasingColor(getResources().getColor(R.color.design_default_color_surface));
                                        dataList.setDecreasingPaintStyle(Paint.Style.FILL);
                                        dataList.setIncreasingColor(getResources().getColor(R.color.design_default_color_primary_variant));
                                        dataList.setIncreasingPaintStyle(Paint.Style.FILL);
                                        dataList.setNeutralColor(Color.LTGRAY);
                                        dataList.setDrawValues(false);

                                        CandleData data = new CandleData(dataList);

                                        candleStickChart.setData(data);
                                        candleStickChart.setBackgroundColor(Color.rgb(0, 0, 0));
                                        candleStickChart.invalidate();
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        //Log.e("Rest Response2 Error", error.toString());
                                    }
                                }
                        );

                        //If if breaks anywhere, I think it might break here...

                        requestQueue.add(objectRequest3);
                    }
                    break;
                }
                break;

            case R.id.candle_stick_api_button1:
                switch (bitCoinSpinnerStrVal) {
                    case "BTC": {
                        System.out.println("Hi ho 14 day BTC!");
                        System.out.println("This is the size of candlesticks that we are looking for : " + candleStickSize);
                        System.out.println("This is the size of listValsCandleStick that we are looking for : " + listValsCandleStickSize);
                        selectedURL = "https://btcbrunchapi.com/ROLLING_14_BTC";
                        String finalSelectedURL = selectedURL;

                        JsonArrayRequest objectRequest3 = new JsonArrayRequest(Request.Method.GET,
                                selectedURL,
                                null,
                                new Response.Listener<JSONArray>() {
                                    @Override
                                    public void onResponse(JSONArray response) {
                                        Log.e("Ticker2 Response", response.toString());
                                        //The response has 14 elements, its the right size. But it is being added to 365 when we call the tickerDataFunction.
                                        //I need that data gone, or to revamp the tickerDataFunction, or to not call it at all!

                                        tickerDataFunction(response);



                                        //Create a loop here that will iterate as many times as size
                                        int candleStickSize = candlesticks.size();
                                        int listValsCandleStickSize = listValsCandleStick.size();
                                        System.out.println("This is the size of candlesticks: " + candleStickSize);
                                        System.out.println("This is the size of listValsCandleStick: " + listValsCandleStickSize);

                                        //clear the chart values and prepare for a new chart in the requestqueue
                                        if(listValsCandleStickSize > 0) {
                                            for(int i = listValsCandleStickSize - 1; i >= 0; i--) {

                                                listValsCandleStick.remove(i);
                                                listValsCandleStickSize--;
                                                //System.out.println(listValsCandleStick.size());
                                            }

                                        }

                                        for(int idx = 0; idx <= candleStickSize - 1; idx++) {
                                            listValsCandleStick.add(new CandleEntry(idx, candlesticks.get(idx).getHigh(), candlesticks.get(idx).getLow(), candlesticks.get(idx).getOpen(), candlesticks.get(idx).getClose()));
                                        }

                                        //This call SHOULD destroy any array already in the chart
                                        if(candleStickSize > 0) {
                                            for(int i = candleStickSize - 1; i >=0; i--) {
                                                candlesticks.remove(i);
                                                candleStickSize--;
                                            }
                                        }

                                        CandleDataSet dataList = new CandleDataSet(listValsCandleStick, "DataList 1");
                                        dataList.setColor(Color.rgb(80, 80, 80));

                                        dataList.setShadowColor(getResources().getColor(R.color.design_default_color_secondary));
                                        dataList.setShadowWidth(0.8f);
                                        dataList.setDecreasingColor(getResources().getColor(R.color.design_default_color_surface));
                                        dataList.setDecreasingPaintStyle(Paint.Style.FILL);
                                        dataList.setIncreasingColor(getResources().getColor(R.color.design_default_color_primary_variant));
                                        dataList.setIncreasingPaintStyle(Paint.Style.FILL);
                                        dataList.setNeutralColor(Color.LTGRAY);
                                        dataList.setDrawValues(false);

                                        CandleData data = new CandleData(dataList);

                                        candleStickChart.setData(data);
                                        candleStickChart.setBackgroundColor(Color.rgb(0, 0, 0));
                                        candleStickChart.invalidate();
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        //Log.e("Rest Response2 Error", error.toString());
                                    }
                                }
                        );

                        //If if breaks anywhere, I think it might break here...

                        requestQueue.add(objectRequest3);
                    }
                    break;
                    case "ETH": {
                        System.out.println("Hi ho 14 day ETH!");
                        selectedURL = "https://btcbrunchapi.com/ROLLING_14_ETH";
                        String finalSelectedURL = selectedURL;

                        JsonArrayRequest objectRequest3 = new JsonArrayRequest(Request.Method.GET,
                                selectedURL,
                                null,
                                new Response.Listener<JSONArray>() {
                                    @Override
                                    public void onResponse(JSONArray response) {
                                        //Log.e("Ticker2 Response", response.toString());
                                        tickerDataFunction(response);

                                        //Create a loop here that will iterate as many times as size
                                        int candleStickSize = candlesticks.size();
                                        int listValsCandleStickSize = listValsCandleStick.size();
                                        //System.out.println("This is the size of candlesticks: " + candleStickSize);
                                        //System.out.println("This is the size of listValsCandleStick: " + listValsCandleStickSize);

                                        //clear the chart values and prepare for a new chart in the requestqueue
                                        if(listValsCandleStickSize > 0) {
                                            for(int i = listValsCandleStickSize - 1; i >= 0; i--) {

                                                listValsCandleStick.remove(i);
                                                listValsCandleStickSize--;
                                                //System.out.println(listValsCandleStick.size());
                                            }

                                        }

                                        for(int idx = 0; idx <= candleStickSize - 1; idx++) {
                                            listValsCandleStick.add(new CandleEntry(idx, candlesticks.get(idx).getHigh(), candlesticks.get(idx).getLow(), candlesticks.get(idx).getOpen(), candlesticks.get(idx).getClose()));
                                        }

                                        //This call SHOULD destroy any array already in the chart
                                        if(candleStickSize > 0) {
                                            for(int i = candleStickSize - 1; i >=0; i--) {
                                                candlesticks.remove(i);
                                                candleStickSize--;
                                            }
                                        }

                                        CandleDataSet dataList = new CandleDataSet(listValsCandleStick, "DataList 1");
                                        dataList.setColor(Color.rgb(80, 80, 80));

                                        dataList.setShadowColor(getResources().getColor(R.color.design_default_color_secondary));
                                        dataList.setShadowWidth(0.8f);
                                        dataList.setDecreasingColor(getResources().getColor(R.color.design_default_color_surface));
                                        dataList.setDecreasingPaintStyle(Paint.Style.FILL);
                                        dataList.setIncreasingColor(getResources().getColor(R.color.design_default_color_primary_variant));
                                        dataList.setIncreasingPaintStyle(Paint.Style.FILL);
                                        dataList.setNeutralColor(Color.LTGRAY);
                                        dataList.setDrawValues(false);

                                        CandleData data = new CandleData(dataList);

                                        candleStickChart.setData(data);
                                        candleStickChart.setBackgroundColor(Color.rgb(0, 0, 0));
                                        candleStickChart.invalidate();
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        //Log.e("Rest Response2 Error", error.toString());
                                    }
                                }
                        );

                        //If if breaks anywhere, I think it might break here...

                        requestQueue.add(objectRequest3);
                    }
                    break;
                    case "BCH": {
                        System.out.println("Hi ho 14 day BCH!");
                        selectedURL = "https://btcbrunchapi.com/ROLLING_14_BCH";
                        String finalSelectedURL = selectedURL;

                        JsonArrayRequest objectRequest3 = new JsonArrayRequest(Request.Method.GET,
                                selectedURL,
                                null,
                                new Response.Listener<JSONArray>() {
                                    @Override
                                    public void onResponse(JSONArray response) {
                                        //Log.e("Ticker2 Response", response.toString());
                                        tickerDataFunction(response);

                                        //Create a loop here that will iterate as many times as size
                                        int candleStickSize = candlesticks.size();
                                        int listValsCandleStickSize = listValsCandleStick.size();
                                        //System.out.println("This is the size of candlesticks: " + candleStickSize);
                                        //System.out.println("This is the size of listValsCandleStick: " + listValsCandleStickSize);

                                        //clear the chart values and prepare for a new chart in the requestqueue
                                        if(listValsCandleStickSize > 0) {
                                            for(int i = listValsCandleStickSize - 1; i >= 0; i--) {

                                                listValsCandleStick.remove(i);
                                                listValsCandleStickSize--;
                                                //System.out.println(listValsCandleStick.size());
                                            }

                                        }

                                        for(int idx = 0; idx <= candleStickSize - 1; idx++) {
                                            listValsCandleStick.add(new CandleEntry(idx, candlesticks.get(idx).getHigh(), candlesticks.get(idx).getLow(), candlesticks.get(idx).getOpen(), candlesticks.get(idx).getClose()));
                                        }

                                        //This call SHOULD destroy any array already in the chart
                                        if(candleStickSize > 0) {
                                            for(int i = candleStickSize - 1; i >=0; i--) {
                                                candlesticks.remove(i);
                                                candleStickSize--;
                                            }
                                        }

                                        CandleDataSet dataList = new CandleDataSet(listValsCandleStick, "DataList 1");
                                        dataList.setColor(Color.rgb(80, 80, 80));

                                        dataList.setShadowColor(getResources().getColor(R.color.design_default_color_secondary));
                                        dataList.setShadowWidth(0.8f);
                                        dataList.setDecreasingColor(getResources().getColor(R.color.design_default_color_surface));
                                        dataList.setDecreasingPaintStyle(Paint.Style.FILL);
                                        dataList.setIncreasingColor(getResources().getColor(R.color.design_default_color_primary_variant));
                                        dataList.setIncreasingPaintStyle(Paint.Style.FILL);
                                        dataList.setNeutralColor(Color.LTGRAY);
                                        dataList.setDrawValues(false);

                                        CandleData data = new CandleData(dataList);

                                        candleStickChart.setData(data);
                                        candleStickChart.setBackgroundColor(Color.rgb(0, 0, 0));
                                        candleStickChart.invalidate();
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        //Log.e("Rest Response2 Error", error.toString());
                                    }
                                }
                        );

                        //If if breaks anywhere, I think it might break here...

                        requestQueue.add(objectRequest3);
                    }
                    break;
                    case "LTC": {
                        System.out.println("Hi ho 14 day LTC!");
                        selectedURL = "https://btcbrunchapi.com/ROLLING_14_LTC";
                        String finalSelectedURL = selectedURL;

                        JsonArrayRequest objectRequest3 = new JsonArrayRequest(Request.Method.GET,
                                selectedURL,
                                null,
                                new Response.Listener<JSONArray>() {
                                    @Override
                                    public void onResponse(JSONArray response) {
                                        //Log.e("Ticker2 Response", response.toString());
                                        tickerDataFunction(response);

                                        //Create a loop here that will iterate as many times as size
                                        int candleStickSize = candlesticks.size();
                                        int listValsCandleStickSize = listValsCandleStick.size();
                                        //System.out.println("This is the size of candlesticks: " + candleStickSize);
                                        //System.out.println("This is the size of listValsCandleStick: " + listValsCandleStickSize);

                                        //clear the chart values and prepare for a new chart in the requestqueue
                                        if(listValsCandleStickSize > 0) {
                                            for(int i = listValsCandleStickSize - 1; i >= 0; i--) {

                                                listValsCandleStick.remove(i);
                                                listValsCandleStickSize--;
                                                //System.out.println(listValsCandleStick.size());
                                            }

                                        }

                                        for(int idx = 0; idx <= candleStickSize - 1; idx++) {
                                            listValsCandleStick.add(new CandleEntry(idx, candlesticks.get(idx).getHigh(), candlesticks.get(idx).getLow(), candlesticks.get(idx).getOpen(), candlesticks.get(idx).getClose()));
                                        }

                                        //This call SHOULD destroy any array already in the chart
                                        if(candleStickSize > 0) {
                                            for(int i = candleStickSize - 1; i >=0; i--) {
                                                candlesticks.remove(i);
                                                candleStickSize--;
                                            }
                                        }

                                        CandleDataSet dataList = new CandleDataSet(listValsCandleStick, "DataList 1");
                                        dataList.setColor(Color.rgb(80, 80, 80));

                                        dataList.setShadowColor(getResources().getColor(R.color.design_default_color_secondary));
                                        dataList.setShadowWidth(0.8f);
                                        dataList.setDecreasingColor(getResources().getColor(R.color.design_default_color_surface));
                                        dataList.setDecreasingPaintStyle(Paint.Style.FILL);
                                        dataList.setIncreasingColor(getResources().getColor(R.color.design_default_color_primary_variant));
                                        dataList.setIncreasingPaintStyle(Paint.Style.FILL);
                                        dataList.setNeutralColor(Color.LTGRAY);
                                        dataList.setDrawValues(false);

                                        CandleData data = new CandleData(dataList);

                                        candleStickChart.setData(data);
                                        candleStickChart.setBackgroundColor(Color.rgb(0, 0, 0));
                                        candleStickChart.invalidate();
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        //Log.e("Rest Response2 Error", error.toString());
                                    }
                                }
                        );

                        //If if breaks anywhere, I think it might break here...

                        requestQueue.add(objectRequest3);
                    }
                    break;
                }
                break;

            case R.id.candle_stick_api_button2:
                switch (bitCoinSpinnerStrVal) {
                    case "BTC": {
                        System.out.println("Hi ho 30 day BTC!");
                        selectedURL = "https://btcbrunchapi.com/ROLLING_30_BTC";
                        String finalSelectedURL = selectedURL;

                        JsonArrayRequest objectRequest3 = new JsonArrayRequest(Request.Method.GET,
                                selectedURL,
                                null,
                                new Response.Listener<JSONArray>() {
                                    @Override
                                    public void onResponse(JSONArray response) {
                                        //Log.e("Ticker2 Response", response.toString());
                                        tickerDataFunction(response);

                                        //Create a loop here that will iterate as many times as size
                                        int candleStickSize = candlesticks.size();
                                        int listValsCandleStickSize = listValsCandleStick.size();
                                        //System.out.println("This is the size of candlesticks: " + candleStickSize);
                                        //System.out.println("This is the size of listValsCandleStick: " + listValsCandleStickSize);

                                        //clear the chart values and prepare for a new chart in the requestqueue
                                        if(listValsCandleStickSize > 0) {
                                            for(int i = listValsCandleStickSize - 1; i >= 0; i--) {

                                                listValsCandleStick.remove(i);
                                                listValsCandleStickSize--;
                                                //System.out.println(listValsCandleStick.size());
                                            }

                                        }

                                        for(int idx = 0; idx <= candleStickSize - 1; idx++) {
                                            listValsCandleStick.add(new CandleEntry(idx, candlesticks.get(idx).getHigh(), candlesticks.get(idx).getLow(), candlesticks.get(idx).getOpen(), candlesticks.get(idx).getClose()));
                                        }

                                        //This call SHOULD destroy any array already in the chart
                                        if(candleStickSize > 0) {
                                            for(int i = candleStickSize - 1; i >=0; i--) {
                                                candlesticks.remove(i);
                                                candleStickSize--;
                                            }
                                        }

                                        CandleDataSet dataList = new CandleDataSet(listValsCandleStick, "DataList 1");
                                        dataList.setColor(Color.rgb(80, 80, 80));

                                        dataList.setShadowColor(getResources().getColor(R.color.design_default_color_secondary));
                                        dataList.setShadowWidth(0.8f);
                                        dataList.setDecreasingColor(getResources().getColor(R.color.design_default_color_surface));
                                        dataList.setDecreasingPaintStyle(Paint.Style.FILL);
                                        dataList.setIncreasingColor(getResources().getColor(R.color.design_default_color_primary_variant));
                                        dataList.setIncreasingPaintStyle(Paint.Style.FILL);
                                        dataList.setNeutralColor(Color.LTGRAY);
                                        dataList.setDrawValues(false);

                                        CandleData data = new CandleData(dataList);

                                        candleStickChart.setData(data);
                                        candleStickChart.setBackgroundColor(Color.rgb(0, 0, 0));
                                        candleStickChart.invalidate();
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        //Log.e("Rest Response2 Error", error.toString());
                                    }
                                }
                        );

                        //If if breaks anywhere, I think it might break here...

                        requestQueue.add(objectRequest3);
                    }
                    break;
                    case "ETH": {
                        System.out.println("Hi ho 30 day ETH!");
                        selectedURL = "https://btcbrunchapi.com/ROLLING_30_ETH";
                        String finalSelectedURL = selectedURL;

                        JsonArrayRequest objectRequest3 = new JsonArrayRequest(Request.Method.GET,
                                selectedURL,
                                null,
                                new Response.Listener<JSONArray>() {
                                    @Override
                                    public void onResponse(JSONArray response) {
                                        //Log.e("Ticker2 Response", response.toString());
                                        tickerDataFunction(response);

                                        //Create a loop here that will iterate as many times as size
                                        int candleStickSize = candlesticks.size();
                                        int listValsCandleStickSize = listValsCandleStick.size();
                                        //System.out.println("This is the size of candlesticks: " + candleStickSize);
                                        //System.out.println("This is the size of listValsCandleStick: " + listValsCandleStickSize);

                                        //clear the chart values and prepare for a new chart in the requestqueue
                                        if(listValsCandleStickSize > 0) {
                                            for(int i = listValsCandleStickSize - 1; i >= 0; i--) {

                                                listValsCandleStick.remove(i);
                                                listValsCandleStickSize--;
                                                //System.out.println(listValsCandleStick.size());
                                            }

                                        }

                                        for(int idx = 0; idx <= candleStickSize - 1; idx++) {
                                            listValsCandleStick.add(new CandleEntry(idx, candlesticks.get(idx).getHigh(), candlesticks.get(idx).getLow(), candlesticks.get(idx).getOpen(), candlesticks.get(idx).getClose()));
                                        }

                                        //This call SHOULD destroy any array already in the chart
                                        if(candleStickSize > 0) {
                                            for(int i = candleStickSize - 1; i >=0; i--) {
                                                candlesticks.remove(i);
                                                candleStickSize--;
                                            }
                                        }

                                        CandleDataSet dataList = new CandleDataSet(listValsCandleStick, "DataList 1");
                                        dataList.setColor(Color.rgb(80, 80, 80));

                                        dataList.setShadowColor(getResources().getColor(R.color.design_default_color_secondary));
                                        dataList.setShadowWidth(0.8f);
                                        dataList.setDecreasingColor(getResources().getColor(R.color.design_default_color_surface));
                                        dataList.setDecreasingPaintStyle(Paint.Style.FILL);
                                        dataList.setIncreasingColor(getResources().getColor(R.color.design_default_color_primary_variant));
                                        dataList.setIncreasingPaintStyle(Paint.Style.FILL);
                                        dataList.setNeutralColor(Color.LTGRAY);
                                        dataList.setDrawValues(false);

                                        CandleData data = new CandleData(dataList);

                                        candleStickChart.setData(data);
                                        candleStickChart.setBackgroundColor(Color.rgb(0, 0, 0));
                                        candleStickChart.invalidate();
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        //Log.e("Rest Response2 Error", error.toString());
                                    }
                                }
                        );

                        //If if breaks anywhere, I think it might break here...

                        requestQueue.add(objectRequest3);
                    }
                    break;
                    case "BCH": {
                        System.out.println("Hi ho 30 day BCH!");
                        selectedURL = "https://btcbrunchapi.com/ROLLING_30_BCH";
                        String finalSelectedURL = selectedURL;

                        JsonArrayRequest objectRequest3 = new JsonArrayRequest(Request.Method.GET,
                                selectedURL,
                                null,
                                new Response.Listener<JSONArray>() {
                                    @Override
                                    public void onResponse(JSONArray response) {
                                        //Log.e("Ticker2 Response", response.toString());
                                        tickerDataFunction(response);

                                        //Create a loop here that will iterate as many times as size
                                        int candleStickSize = candlesticks.size();
                                        int listValsCandleStickSize = listValsCandleStick.size();
                                        //System.out.println("This is the size of candlesticks: " + candleStickSize);
                                        //System.out.println("This is the size of listValsCandleStick: " + listValsCandleStickSize);

                                        //clear the chart values and prepare for a new chart in the requestqueue
                                        if(listValsCandleStickSize > 0) {
                                            for(int i = listValsCandleStickSize - 1; i >= 0; i--) {

                                                listValsCandleStick.remove(i);
                                                listValsCandleStickSize--;
                                                //System.out.println(listValsCandleStick.size());
                                            }

                                        }

                                        for(int idx = 0; idx <= candleStickSize - 1; idx++) {
                                            listValsCandleStick.add(new CandleEntry(idx, candlesticks.get(idx).getHigh(), candlesticks.get(idx).getLow(), candlesticks.get(idx).getOpen(), candlesticks.get(idx).getClose()));
                                        }

                                        //This call SHOULD destroy any array already in the chart
                                        if(candleStickSize > 0) {
                                            for(int i = candleStickSize - 1; i >=0; i--) {
                                                candlesticks.remove(i);
                                                candleStickSize--;
                                            }
                                        }

                                        CandleDataSet dataList = new CandleDataSet(listValsCandleStick, "DataList 1");
                                        dataList.setColor(Color.rgb(80, 80, 80));

                                        dataList.setShadowColor(getResources().getColor(R.color.design_default_color_secondary));
                                        dataList.setShadowWidth(0.8f);
                                        dataList.setDecreasingColor(getResources().getColor(R.color.design_default_color_surface));
                                        dataList.setDecreasingPaintStyle(Paint.Style.FILL);
                                        dataList.setIncreasingColor(getResources().getColor(R.color.design_default_color_primary_variant));
                                        dataList.setIncreasingPaintStyle(Paint.Style.FILL);
                                        dataList.setNeutralColor(Color.LTGRAY);
                                        dataList.setDrawValues(false);

                                        CandleData data = new CandleData(dataList);

                                        candleStickChart.setData(data);
                                        candleStickChart.setBackgroundColor(Color.rgb(0, 0, 0));
                                        candleStickChart.invalidate();
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        //Log.e("Rest Response2 Error", error.toString());
                                    }
                                }
                        );

                        //If if breaks anywhere, I think it might break here...

                        requestQueue.add(objectRequest3);
                    }
                    break;
                    case "LTC": {
                        System.out.println("Hi ho 30 day LTC!");
                        selectedURL = "https://btcbrunchapi.com/ROLLING_30_LTC";
                        String finalSelectedURL = selectedURL;

                        JsonArrayRequest objectRequest3 = new JsonArrayRequest(Request.Method.GET,
                                selectedURL,
                                null,
                                new Response.Listener<JSONArray>() {
                                    @Override
                                    public void onResponse(JSONArray response) {
                                        //Log.e("Ticker2 Response", response.toString());
                                        tickerDataFunction(response);

                                        //Create a loop here that will iterate as many times as size
                                        int candleStickSize = candlesticks.size();
                                        int listValsCandleStickSize = listValsCandleStick.size();
                                        //System.out.println("This is the size of candlesticks: " + candleStickSize);
                                        //System.out.println("This is the size of listValsCandleStick: " + listValsCandleStickSize);

                                        //clear the chart values and prepare for a new chart in the requestqueue
                                        if(listValsCandleStickSize > 0) {
                                            for(int i = listValsCandleStickSize - 1; i >= 0; i--) {

                                                listValsCandleStick.remove(i);
                                                listValsCandleStickSize--;
                                                //System.out.println(listValsCandleStick.size());
                                            }

                                        }

                                        for(int idx = 0; idx <= candleStickSize - 1; idx++) {
                                            listValsCandleStick.add(new CandleEntry(idx, candlesticks.get(idx).getHigh(), candlesticks.get(idx).getLow(), candlesticks.get(idx).getOpen(), candlesticks.get(idx).getClose()));
                                        }

                                        //This call SHOULD destroy any array already in the chart
                                        if(candleStickSize > 0) {
                                            for(int i = candleStickSize - 1; i >=0; i--) {
                                                candlesticks.remove(i);
                                                candleStickSize--;
                                            }
                                        }

                                        CandleDataSet dataList = new CandleDataSet(listValsCandleStick, "DataList 1");
                                        dataList.setColor(Color.rgb(80, 80, 80));

                                        dataList.setShadowColor(getResources().getColor(R.color.design_default_color_secondary));
                                        dataList.setShadowWidth(0.8f);
                                        dataList.setDecreasingColor(getResources().getColor(R.color.design_default_color_surface));
                                        dataList.setDecreasingPaintStyle(Paint.Style.FILL);
                                        dataList.setIncreasingColor(getResources().getColor(R.color.design_default_color_primary_variant));
                                        dataList.setIncreasingPaintStyle(Paint.Style.FILL);
                                        dataList.setNeutralColor(Color.LTGRAY);
                                        dataList.setDrawValues(false);

                                        CandleData data = new CandleData(dataList);

                                        candleStickChart.setData(data);
                                        candleStickChart.setBackgroundColor(Color.rgb(0, 0, 0));
                                        candleStickChart.invalidate();
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        //Log.e("Rest Response2 Error", error.toString());
                                    }
                                }
                        );

                        //If if breaks anywhere, I think it might break here...

                        requestQueue.add(objectRequest3);
                    }
                    break;
                }
                break;

            case R.id.candle_stick_api_button3:
                switch (bitCoinSpinnerStrVal) {
                    case "BTC": {
                        System.out.println("Hi ho 90 day BTC!");
                        selectedURL = "https://btcbrunchapi.com/ROLLING_90_BTC";
                        String finalSelectedURL = selectedURL;

                        JsonArrayRequest objectRequest3 = new JsonArrayRequest(Request.Method.GET,
                                selectedURL,
                                null,
                                new Response.Listener<JSONArray>() {
                                    @Override
                                    public void onResponse(JSONArray response) {
                                        //Log.e("Ticker2 Response", response.toString());
                                        tickerDataFunction(response);

                                        //Create a loop here that will iterate as many times as size
                                        int candleStickSize = candlesticks.size();
                                        int listValsCandleStickSize = listValsCandleStick.size();
                                        //System.out.println("This is the size of candlesticks: " + candleStickSize);
                                        //System.out.println("This is the size of listValsCandleStick: " + listValsCandleStickSize);

                                        //clear the chart values and prepare for a new chart in the requestqueue
                                        if(listValsCandleStickSize > 0) {
                                            for(int i = listValsCandleStickSize - 1; i >= 0; i--) {

                                                listValsCandleStick.remove(i);
                                                listValsCandleStickSize--;
                                                //System.out.println(listValsCandleStick.size());
                                            }

                                        }

                                        for(int idx = 0; idx <= candleStickSize - 1; idx++) {
                                            listValsCandleStick.add(new CandleEntry(idx, candlesticks.get(idx).getHigh(), candlesticks.get(idx).getLow(), candlesticks.get(idx).getOpen(), candlesticks.get(idx).getClose()));
                                        }

                                        //This call SHOULD destroy any array already in the chart
                                        if(candleStickSize > 0) {
                                            for(int i = candleStickSize - 1; i >=0; i--) {
                                                candlesticks.remove(i);
                                                candleStickSize--;
                                            }
                                        }

                                        CandleDataSet dataList = new CandleDataSet(listValsCandleStick, "DataList 1");
                                        dataList.setColor(Color.rgb(80, 80, 80));

                                        dataList.setShadowColor(getResources().getColor(R.color.design_default_color_secondary));
                                        dataList.setShadowWidth(0.8f);
                                        dataList.setDecreasingColor(getResources().getColor(R.color.design_default_color_surface));
                                        dataList.setDecreasingPaintStyle(Paint.Style.FILL);
                                        dataList.setIncreasingColor(getResources().getColor(R.color.design_default_color_primary_variant));
                                        dataList.setIncreasingPaintStyle(Paint.Style.FILL);
                                        dataList.setNeutralColor(Color.LTGRAY);
                                        dataList.setDrawValues(false);

                                        CandleData data = new CandleData(dataList);

                                        candleStickChart.setData(data);
                                        candleStickChart.setBackgroundColor(Color.rgb(0, 0, 0));
                                        candleStickChart.invalidate();
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        //Log.e("Rest Response2 Error", error.toString());
                                    }
                                }
                        );

                        //If if breaks anywhere, I think it might break here...

                        requestQueue.add(objectRequest3);
                    }
                    break;
                    case "ETH": {
                        System.out.println("Hi ho 90 day ETH!");
                        selectedURL = "https://btcbrunchapi.com/ROLLING_90_ETH";
                        String finalSelectedURL = selectedURL;

                        JsonArrayRequest objectRequest3 = new JsonArrayRequest(Request.Method.GET,
                                selectedURL,
                                null,
                                new Response.Listener<JSONArray>() {
                                    @Override
                                    public void onResponse(JSONArray response) {
                                        //Log.e("Ticker2 Response", response.toString());
                                        tickerDataFunction(response);

                                        //Create a loop here that will iterate as many times as size
                                        int candleStickSize = candlesticks.size();
                                        int listValsCandleStickSize = listValsCandleStick.size();
                                        //System.out.println("This is the size of candlesticks: " + candleStickSize);
                                        //System.out.println("This is the size of listValsCandleStick: " + listValsCandleStickSize);

                                        //clear the chart values and prepare for a new chart in the requestqueue
                                        if(listValsCandleStickSize > 0) {
                                            for(int i = listValsCandleStickSize - 1; i >= 0; i--) {

                                                listValsCandleStick.remove(i);
                                                listValsCandleStickSize--;
                                                //System.out.println(listValsCandleStick.size());
                                            }

                                        }

                                        for(int idx = 0; idx <= candleStickSize - 1; idx++) {
                                            listValsCandleStick.add(new CandleEntry(idx, candlesticks.get(idx).getHigh(), candlesticks.get(idx).getLow(), candlesticks.get(idx).getOpen(), candlesticks.get(idx).getClose()));
                                        }

                                        //This call SHOULD destroy any array already in the chart
                                        if(candleStickSize > 0) {
                                            for(int i = candleStickSize - 1; i >=0; i--) {
                                                candlesticks.remove(i);
                                                candleStickSize--;
                                            }
                                        }

                                        CandleDataSet dataList = new CandleDataSet(listValsCandleStick, "DataList 1");
                                        dataList.setColor(Color.rgb(80, 80, 80));

                                        dataList.setShadowColor(getResources().getColor(R.color.design_default_color_secondary));
                                        dataList.setShadowWidth(0.8f);
                                        dataList.setDecreasingColor(getResources().getColor(R.color.design_default_color_surface));
                                        dataList.setDecreasingPaintStyle(Paint.Style.FILL);
                                        dataList.setIncreasingColor(getResources().getColor(R.color.design_default_color_primary_variant));
                                        dataList.setIncreasingPaintStyle(Paint.Style.FILL);
                                        dataList.setNeutralColor(Color.LTGRAY);
                                        dataList.setDrawValues(false);

                                        CandleData data = new CandleData(dataList);

                                        candleStickChart.setData(data);
                                        candleStickChart.setBackgroundColor(Color.rgb(0, 0, 0));
                                        candleStickChart.invalidate();
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        //Log.e("Rest Response2 Error", error.toString());
                                    }
                                }
                        );

                        //If if breaks anywhere, I think it might break here...

                        requestQueue.add(objectRequest3);
                    }
                    break;
                    case "BCH": {
                        System.out.println("Hi ho 90 day BCH!");
                        selectedURL = "https://btcbrunchapi.com/ROLLING_90_BCH";
                        String finalSelectedURL = selectedURL;

                        JsonArrayRequest objectRequest3 = new JsonArrayRequest(Request.Method.GET,
                                selectedURL,
                                null,
                                new Response.Listener<JSONArray>() {
                                    @Override
                                    public void onResponse(JSONArray response) {
                                        //Log.e("Ticker2 Response", response.toString());
                                        tickerDataFunction(response);

                                        //Create a loop here that will iterate as many times as size
                                        int candleStickSize = candlesticks.size();
                                        int listValsCandleStickSize = listValsCandleStick.size();
                                        //System.out.println("This is the size of candlesticks: " + candleStickSize);
                                        //System.out.println("This is the size of listValsCandleStick: " + listValsCandleStickSize);

                                        //clear the chart values and prepare for a new chart in the requestqueue
                                        if(listValsCandleStickSize > 0) {
                                            for(int i = listValsCandleStickSize - 1; i >= 0; i--) {

                                                listValsCandleStick.remove(i);
                                                listValsCandleStickSize--;
                                                //System.out.println(listValsCandleStick.size());
                                            }

                                        }

                                        for(int idx = 0; idx <= candleStickSize - 1; idx++) {
                                            listValsCandleStick.add(new CandleEntry(idx, candlesticks.get(idx).getHigh(), candlesticks.get(idx).getLow(), candlesticks.get(idx).getOpen(), candlesticks.get(idx).getClose()));
                                        }

                                        //This call SHOULD destroy any array already in the chart
                                        if(candleStickSize > 0) {
                                            for(int i = candleStickSize - 1; i >=0; i--) {
                                                candlesticks.remove(i);
                                                candleStickSize--;
                                            }
                                        }

                                        CandleDataSet dataList = new CandleDataSet(listValsCandleStick, "DataList 1");
                                        dataList.setColor(Color.rgb(80, 80, 80));

                                        dataList.setShadowColor(getResources().getColor(R.color.design_default_color_secondary));
                                        dataList.setShadowWidth(0.8f);
                                        dataList.setDecreasingColor(getResources().getColor(R.color.design_default_color_surface));
                                        dataList.setDecreasingPaintStyle(Paint.Style.FILL);
                                        dataList.setIncreasingColor(getResources().getColor(R.color.design_default_color_primary_variant));
                                        dataList.setIncreasingPaintStyle(Paint.Style.FILL);
                                        dataList.setNeutralColor(Color.LTGRAY);
                                        dataList.setDrawValues(false);

                                        CandleData data = new CandleData(dataList);

                                        candleStickChart.setData(data);
                                        candleStickChart.setBackgroundColor(Color.rgb(0, 0, 0));
                                        candleStickChart.invalidate();
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        //Log.e("Rest Response2 Error", error.toString());
                                    }
                                }
                        );

                        //If if breaks anywhere, I think it might break here...

                        requestQueue.add(objectRequest3);
                    }
                    break;
                    case "LTC": {
                        System.out.println("Hi ho 90 day LTC!");
                        selectedURL = "https://btcbrunchapi.com/ROLLING_90_LTC";
                        String finalSelectedURL = selectedURL;

                        JsonArrayRequest objectRequest3 = new JsonArrayRequest(Request.Method.GET,
                                selectedURL,
                                null,
                                new Response.Listener<JSONArray>() {
                                    @Override
                                    public void onResponse(JSONArray response) {
                                        //Log.e("Ticker2 Response", response.toString());
                                        tickerDataFunction(response);

                                        //Create a loop here that will iterate as many times as size
                                        int candleStickSize = candlesticks.size();
                                        int listValsCandleStickSize = listValsCandleStick.size();
                                        //System.out.println("This is the size of candlesticks: " + candleStickSize);
                                        //System.out.println("This is the size of listValsCandleStick: " + listValsCandleStickSize);

                                        //clear the chart values and prepare for a new chart in the requestqueue
                                        if(listValsCandleStickSize > 0) {
                                            for(int i = listValsCandleStickSize - 1; i >= 0; i--) {

                                                listValsCandleStick.remove(i);
                                                listValsCandleStickSize--;
                                                //System.out.println(listValsCandleStick.size());
                                            }

                                        }

                                        for(int idx = 0; idx <= candleStickSize - 1; idx++) {
                                            listValsCandleStick.add(new CandleEntry(idx, candlesticks.get(idx).getHigh(), candlesticks.get(idx).getLow(), candlesticks.get(idx).getOpen(), candlesticks.get(idx).getClose()));
                                        }

                                        //This call SHOULD destroy any array already in the chart
                                        if(candleStickSize > 0) {
                                            for(int i = candleStickSize - 1; i >=0; i--) {
                                                candlesticks.remove(i);
                                                candleStickSize--;
                                            }
                                        }

                                        CandleDataSet dataList = new CandleDataSet(listValsCandleStick, "DataList 1");
                                        dataList.setColor(Color.rgb(80, 80, 80));

                                        dataList.setShadowColor(getResources().getColor(R.color.design_default_color_secondary));
                                        dataList.setShadowWidth(0.8f);
                                        dataList.setDecreasingColor(getResources().getColor(R.color.design_default_color_surface));
                                        dataList.setDecreasingPaintStyle(Paint.Style.FILL);
                                        dataList.setIncreasingColor(getResources().getColor(R.color.design_default_color_primary_variant));
                                        dataList.setIncreasingPaintStyle(Paint.Style.FILL);
                                        dataList.setNeutralColor(Color.LTGRAY);
                                        dataList.setDrawValues(false);

                                        CandleData data = new CandleData(dataList);

                                        candleStickChart.setData(data);
                                        candleStickChart.setBackgroundColor(Color.rgb(0, 0, 0));
                                        candleStickChart.invalidate();
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        //Log.e("Rest Response2 Error", error.toString());
                                    }
                                }
                        );

                        //If if breaks anywhere, I think it might break here...

                        requestQueue.add(objectRequest3);
                    }
                    break;
                }
                break;

            case R.id.candle_stick_api_button4:
                switch (bitCoinSpinnerStrVal) {
                    case "BTC": {
                        System.out.println("Hi ho 180 day BTC!");
                        selectedURL = "https://btcbrunchapi.com/ROLLING_180_BTC";
                        String finalSelectedURL = selectedURL;

                        JsonArrayRequest objectRequest3 = new JsonArrayRequest(Request.Method.GET,
                                selectedURL,
                                null,
                                new Response.Listener<JSONArray>() {
                                    @Override
                                    public void onResponse(JSONArray response) {
                                        //Log.e("Ticker2 Response", response.toString());
                                        tickerDataFunction(response);

                                        //Create a loop here that will iterate as many times as size
                                        int candleStickSize = candlesticks.size();
                                        int listValsCandleStickSize = listValsCandleStick.size();
                                        //System.out.println("This is the size of candlesticks: " + candleStickSize);
                                        //System.out.println("This is the size of listValsCandleStick: " + listValsCandleStickSize);

                                        //clear the chart values and prepare for a new chart in the requestqueue
                                        if(listValsCandleStickSize > 0) {
                                            for(int i = listValsCandleStickSize - 1; i >= 0; i--) {

                                                listValsCandleStick.remove(i);
                                                listValsCandleStickSize--;
                                                //System.out.println(listValsCandleStick.size());
                                            }

                                        }

                                        for(int idx = 0; idx <= candleStickSize - 1; idx++) {
                                            listValsCandleStick.add(new CandleEntry(idx, candlesticks.get(idx).getHigh(), candlesticks.get(idx).getLow(), candlesticks.get(idx).getOpen(), candlesticks.get(idx).getClose()));
                                        }

                                        //This call SHOULD destroy any array already in the chart
                                        if(candleStickSize > 0) {
                                            for(int i = candleStickSize - 1; i >=0; i--) {
                                                candlesticks.remove(i);
                                                candleStickSize--;
                                            }
                                        }

                                        CandleDataSet dataList = new CandleDataSet(listValsCandleStick, "DataList 1");
                                        dataList.setColor(Color.rgb(80, 80, 80));

                                        dataList.setShadowColor(getResources().getColor(R.color.design_default_color_secondary));
                                        dataList.setShadowWidth(0.8f);
                                        dataList.setDecreasingColor(getResources().getColor(R.color.design_default_color_surface));
                                        dataList.setDecreasingPaintStyle(Paint.Style.FILL);
                                        dataList.setIncreasingColor(getResources().getColor(R.color.design_default_color_primary_variant));
                                        dataList.setIncreasingPaintStyle(Paint.Style.FILL);
                                        dataList.setNeutralColor(Color.LTGRAY);
                                        dataList.setDrawValues(false);

                                        CandleData data = new CandleData(dataList);

                                        candleStickChart.setData(data);
                                        candleStickChart.setBackgroundColor(Color.rgb(0, 0, 0));
                                        candleStickChart.invalidate();
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        //Log.e("Rest Response2 Error", error.toString());
                                    }
                                }
                        );

                        //If if breaks anywhere, I think it might break here...

                        requestQueue.add(objectRequest3);
                    }
                    break;
                    case "ETH": {
                        System.out.println("Hi ho 180 day ETH!");
                        selectedURL = "https://btcbrunchapi.com/ROLLING_180_ETH";
                        String finalSelectedURL = selectedURL;

                        JsonArrayRequest objectRequest3 = new JsonArrayRequest(Request.Method.GET,
                                selectedURL,
                                null,
                                new Response.Listener<JSONArray>() {
                                    @Override
                                    public void onResponse(JSONArray response) {
                                        //Log.e("Ticker2 Response", response.toString());
                                        tickerDataFunction(response);

                                        //Create a loop here that will iterate as many times as size
                                        int candleStickSize = candlesticks.size();
                                        int listValsCandleStickSize = listValsCandleStick.size();
                                        //System.out.println("This is the size of candlesticks: " + candleStickSize);
                                        //System.out.println("This is the size of listValsCandleStick: " + listValsCandleStickSize);

                                        //clear the chart values and prepare for a new chart in the requestqueue
                                        if(listValsCandleStickSize > 0) {
                                            for(int i = listValsCandleStickSize - 1; i >= 0; i--) {

                                                listValsCandleStick.remove(i);
                                                listValsCandleStickSize--;
                                                //System.out.println(listValsCandleStick.size());
                                            }

                                        }

                                        for(int idx = 0; idx <= candleStickSize - 1; idx++) {
                                            listValsCandleStick.add(new CandleEntry(idx, candlesticks.get(idx).getHigh(), candlesticks.get(idx).getLow(), candlesticks.get(idx).getOpen(), candlesticks.get(idx).getClose()));
                                        }

                                        //This call SHOULD destroy any array already in the chart
                                        if(candleStickSize > 0) {
                                            for(int i = candleStickSize - 1; i >=0; i--) {
                                                candlesticks.remove(i);
                                                candleStickSize--;
                                            }
                                        }

                                        CandleDataSet dataList = new CandleDataSet(listValsCandleStick, "DataList 1");
                                        dataList.setColor(Color.rgb(80, 80, 80));

                                        dataList.setShadowColor(getResources().getColor(R.color.design_default_color_secondary));
                                        dataList.setShadowWidth(0.8f);
                                        dataList.setDecreasingColor(getResources().getColor(R.color.design_default_color_surface));
                                        dataList.setDecreasingPaintStyle(Paint.Style.FILL);
                                        dataList.setIncreasingColor(getResources().getColor(R.color.design_default_color_primary_variant));
                                        dataList.setIncreasingPaintStyle(Paint.Style.FILL);
                                        dataList.setNeutralColor(Color.LTGRAY);
                                        dataList.setDrawValues(false);

                                        CandleData data = new CandleData(dataList);

                                        candleStickChart.setData(data);
                                        candleStickChart.setBackgroundColor(Color.rgb(0, 0, 0));
                                        candleStickChart.invalidate();
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        //Log.e("Rest Response2 Error", error.toString());
                                    }
                                }
                        );

                        //If if breaks anywhere, I think it might break here...

                        requestQueue.add(objectRequest3);
                    }
                    break;
                    case "BCH": {
                        System.out.println("Hi ho 180 day BCH!");
                        selectedURL = "https://btcbrunchapi.com/ROLLING_180_BCH";
                        String finalSelectedURL = selectedURL;

                        JsonArrayRequest objectRequest3 = new JsonArrayRequest(Request.Method.GET,
                                selectedURL,
                                null,
                                new Response.Listener<JSONArray>() {
                                    @Override
                                    public void onResponse(JSONArray response) {
                                        //Log.e("Ticker2 Response", response.toString());
                                        tickerDataFunction(response);

                                        //Create a loop here that will iterate as many times as size
                                        int candleStickSize = candlesticks.size();
                                        int listValsCandleStickSize = listValsCandleStick.size();
                                        //System.out.println("This is the size of candlesticks: " + candleStickSize);
                                        //System.out.println("This is the size of listValsCandleStick: " + listValsCandleStickSize);

                                        //clear the chart values and prepare for a new chart in the requestqueue
                                        if(listValsCandleStickSize > 0) {
                                            for(int i = listValsCandleStickSize - 1; i >= 0; i--) {

                                                listValsCandleStick.remove(i);
                                                listValsCandleStickSize--;
                                                //System.out.println(listValsCandleStick.size());
                                            }

                                        }

                                        for(int idx = 0; idx <= candleStickSize - 1; idx++) {
                                            listValsCandleStick.add(new CandleEntry(idx, candlesticks.get(idx).getHigh(), candlesticks.get(idx).getLow(), candlesticks.get(idx).getOpen(), candlesticks.get(idx).getClose()));
                                        }

                                        //This call SHOULD destroy any array already in the chart
                                        if(candleStickSize > 0) {
                                            for(int i = candleStickSize - 1; i >=0; i--) {
                                                candlesticks.remove(i);
                                                candleStickSize--;
                                            }
                                        }

                                        CandleDataSet dataList = new CandleDataSet(listValsCandleStick, "DataList 1");
                                        dataList.setColor(Color.rgb(80, 80, 80));

                                        dataList.setShadowColor(getResources().getColor(R.color.design_default_color_secondary));
                                        dataList.setShadowWidth(0.8f);
                                        dataList.setDecreasingColor(getResources().getColor(R.color.design_default_color_surface));
                                        dataList.setDecreasingPaintStyle(Paint.Style.FILL);
                                        dataList.setIncreasingColor(getResources().getColor(R.color.design_default_color_primary_variant));
                                        dataList.setIncreasingPaintStyle(Paint.Style.FILL);
                                        dataList.setNeutralColor(Color.LTGRAY);
                                        dataList.setDrawValues(false);

                                        CandleData data = new CandleData(dataList);

                                        candleStickChart.setData(data);
                                        candleStickChart.setBackgroundColor(Color.rgb(0, 0, 0));
                                        candleStickChart.invalidate();
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        //Log.e("Rest Response2 Error", error.toString());
                                    }
                                }
                        );

                        //If if breaks anywhere, I think it might break here...

                        requestQueue.add(objectRequest3);
                    }
                    break;
                    case "LTC": {
                        System.out.println("Hi ho 180 day LTC!");
                        selectedURL = "https://btcbrunchapi.com/ROLLING_180_LTC";
                        String finalSelectedURL = selectedURL;

                        JsonArrayRequest objectRequest3 = new JsonArrayRequest(Request.Method.GET,
                                selectedURL,
                                null,
                                new Response.Listener<JSONArray>() {
                                    @Override
                                    public void onResponse(JSONArray response) {
                                        //Log.e("Ticker2 Response", response.toString());
                                        tickerDataFunction(response);

                                        //Create a loop here that will iterate as many times as size
                                        int candleStickSize = candlesticks.size();
                                        int listValsCandleStickSize = listValsCandleStick.size();
                                        //System.out.println("This is the size of candlesticks: " + candleStickSize);
                                        //System.out.println("This is the size of listValsCandleStick: " + listValsCandleStickSize);

                                        //clear the chart values and prepare for a new chart in the requestqueue
                                        if(listValsCandleStickSize > 0) {
                                            for(int i = listValsCandleStickSize - 1; i >= 0; i--) {

                                                listValsCandleStick.remove(i);
                                                listValsCandleStickSize--;
                                                //System.out.println(listValsCandleStick.size());
                                            }

                                        }

                                        for(int idx = 0; idx <= candleStickSize - 1; idx++) {
                                            listValsCandleStick.add(new CandleEntry(idx, candlesticks.get(idx).getHigh(), candlesticks.get(idx).getLow(), candlesticks.get(idx).getOpen(), candlesticks.get(idx).getClose()));
                                        }

                                        //This call SHOULD destroy any array already in the chart
                                        if(candleStickSize > 0) {
                                            for(int i = candleStickSize - 1; i >=0; i--) {
                                                candlesticks.remove(i);
                                                candleStickSize--;
                                            }
                                        }

                                        CandleDataSet dataList = new CandleDataSet(listValsCandleStick, "DataList 1");
                                        dataList.setColor(Color.rgb(80, 80, 80));

                                        dataList.setShadowColor(getResources().getColor(R.color.design_default_color_secondary));
                                        dataList.setShadowWidth(0.8f);
                                        dataList.setDecreasingColor(getResources().getColor(R.color.design_default_color_surface));
                                        dataList.setDecreasingPaintStyle(Paint.Style.FILL);
                                        dataList.setIncreasingColor(getResources().getColor(R.color.design_default_color_primary_variant));
                                        dataList.setIncreasingPaintStyle(Paint.Style.FILL);
                                        dataList.setNeutralColor(Color.LTGRAY);
                                        dataList.setDrawValues(false);

                                        CandleData data = new CandleData(dataList);

                                        candleStickChart.setData(data);
                                        candleStickChart.setBackgroundColor(Color.rgb(0, 0, 0));
                                        candleStickChart.invalidate();
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        //Log.e("Rest Response2 Error", error.toString());
                                    }
                                }
                        );

                        //If if breaks anywhere, I think it might break here...

                        requestQueue.add(objectRequest3);
                    }
                    break;
                }
                break;

            case R.id.candle_stick_api_button5:
                switch (bitCoinSpinnerStrVal) {
                    case "BTC": {
                        System.out.println("Hi ho 365 day BTC!");
                        selectedURL = "https://btcbrunchapi.com/ROLLING_365_BTC";
                        String finalSelectedURL = selectedURL;

                        JsonArrayRequest objectRequest3 = new JsonArrayRequest(Request.Method.GET,
                                selectedURL,
                                null,
                                new Response.Listener<JSONArray>() {
                                    @Override
                                    public void onResponse(JSONArray response) {
                                        //Log.e("Ticker2 Response", response.toString());
                                        tickerDataFunction(response);

                                        //Create a loop here that will iterate as many times as size
                                        int candleStickSize = candlesticks.size();
                                        int listValsCandleStickSize = listValsCandleStick.size();
                                        //System.out.println("This is the size of candlesticks: " + candleStickSize);
                                        //System.out.println("This is the size of listValsCandleStick: " + listValsCandleStickSize);

                                        //clear the chart values and prepare for a new chart in the requestqueue
                                        if(listValsCandleStickSize > 0) {
                                            for(int i = listValsCandleStickSize - 1; i >= 0; i--) {

                                                listValsCandleStick.remove(i);
                                                listValsCandleStickSize--;
                                                //System.out.println(listValsCandleStick.size());
                                            }

                                        }

                                        for(int idx = 0; idx <= candleStickSize - 1; idx++) {
                                            listValsCandleStick.add(new CandleEntry(idx, candlesticks.get(idx).getHigh(), candlesticks.get(idx).getLow(), candlesticks.get(idx).getOpen(), candlesticks.get(idx).getClose()));
                                        }

                                        //This call SHOULD destroy any array already in the chart
                                        if(candleStickSize > 0) {
                                            for(int i = candleStickSize - 1; i >=0; i--) {
                                                candlesticks.remove(i);
                                                candleStickSize--;
                                            }
                                        }

                                        CandleDataSet dataList = new CandleDataSet(listValsCandleStick, "DataList 1");
                                        dataList.setColor(Color.rgb(80, 80, 80));

                                        dataList.setShadowColor(getResources().getColor(R.color.design_default_color_secondary));
                                        dataList.setShadowWidth(0.8f);
                                        dataList.setDecreasingColor(getResources().getColor(R.color.design_default_color_surface));
                                        dataList.setDecreasingPaintStyle(Paint.Style.FILL);
                                        dataList.setIncreasingColor(getResources().getColor(R.color.design_default_color_primary_variant));
                                        dataList.setIncreasingPaintStyle(Paint.Style.FILL);
                                        dataList.setNeutralColor(Color.LTGRAY);
                                        dataList.setDrawValues(false);

                                        CandleData data = new CandleData(dataList);

                                        candleStickChart.setData(data);
                                        candleStickChart.setBackgroundColor(Color.rgb(0, 0, 0));
                                        candleStickChart.invalidate();
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        //Log.e("Rest Response2 Error", error.toString());
                                    }
                                }
                        );

                        //If if breaks anywhere, I think it might break here...

                        requestQueue.add(objectRequest3);
                    }
                    break;
                    case "ETH": {
                        System.out.println("Hi ho 365 day ETH!");
                        selectedURL = "https://btcbrunchapi.com/ROLLING_365_ETH";
                        String finalSelectedURL = selectedURL;

                        JsonArrayRequest objectRequest3 = new JsonArrayRequest(Request.Method.GET,
                                selectedURL,
                                null,
                                new Response.Listener<JSONArray>() {
                                    @Override
                                    public void onResponse(JSONArray response) {
                                        //Log.e("Ticker2 Response", response.toString());
                                        tickerDataFunction(response);

                                        //Create a loop here that will iterate as many times as size
                                        int candleStickSize = candlesticks.size();
                                        int listValsCandleStickSize = listValsCandleStick.size();
                                        //System.out.println("This is the size of candlesticks: " + candleStickSize);
                                        //System.out.println("This is the size of listValsCandleStick: " + listValsCandleStickSize);

                                        //clear the chart values and prepare for a new chart in the requestqueue
                                        if(listValsCandleStickSize > 0) {
                                            for(int i = listValsCandleStickSize - 1; i >= 0; i--) {

                                                listValsCandleStick.remove(i);
                                                listValsCandleStickSize--;
                                                //System.out.println(listValsCandleStick.size());
                                            }

                                        }

                                        for(int idx = 0; idx <= candleStickSize - 1; idx++) {
                                            listValsCandleStick.add(new CandleEntry(idx, candlesticks.get(idx).getHigh(), candlesticks.get(idx).getLow(), candlesticks.get(idx).getOpen(), candlesticks.get(idx).getClose()));
                                        }

                                        //This call SHOULD destroy any array already in the chart
                                        if(candleStickSize > 0) {
                                            for(int i = candleStickSize - 1; i >=0; i--) {
                                                candlesticks.remove(i);
                                                candleStickSize--;
                                            }
                                        }

                                        CandleDataSet dataList = new CandleDataSet(listValsCandleStick, "DataList 1");
                                        dataList.setColor(Color.rgb(80, 80, 80));

                                        dataList.setShadowColor(getResources().getColor(R.color.design_default_color_secondary));
                                        dataList.setShadowWidth(0.8f);
                                        dataList.setDecreasingColor(getResources().getColor(R.color.design_default_color_surface));
                                        dataList.setDecreasingPaintStyle(Paint.Style.FILL);
                                        dataList.setIncreasingColor(getResources().getColor(R.color.design_default_color_primary_variant));
                                        dataList.setIncreasingPaintStyle(Paint.Style.FILL);
                                        dataList.setNeutralColor(Color.LTGRAY);
                                        dataList.setDrawValues(false);

                                        CandleData data = new CandleData(dataList);

                                        candleStickChart.setData(data);
                                        candleStickChart.setBackgroundColor(Color.rgb(0, 0, 0));
                                        candleStickChart.invalidate();
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        //Log.e("Rest Response2 Error", error.toString());
                                    }
                                }
                        );

                        //If if breaks anywhere, I think it might break here...

                        requestQueue.add(objectRequest3);
                    }
                    break;
                    case "BCH": {
                        System.out.println("Hi ho 365 day BCH!");
                        selectedURL = "https://btcbrunchapi.com/ROLLING_365_BCH";
                        String finalSelectedURL = selectedURL;

                        JsonArrayRequest objectRequest3 = new JsonArrayRequest(Request.Method.GET,
                                selectedURL,
                                null,
                                new Response.Listener<JSONArray>() {
                                    @Override
                                    public void onResponse(JSONArray response) {
                                        //Log.e("Ticker2 Response", response.toString());
                                        tickerDataFunction(response);

                                        //Create a loop here that will iterate as many times as size
                                        int candleStickSize = candlesticks.size();
                                        int listValsCandleStickSize = listValsCandleStick.size();
                                        //System.out.println("This is the size of candlesticks: " + candleStickSize);
                                        //System.out.println("This is the size of listValsCandleStick: " + listValsCandleStickSize);

                                        //clear the chart values and prepare for a new chart in the requestqueue
                                        if(listValsCandleStickSize > 0) {
                                            for(int i = listValsCandleStickSize - 1; i >= 0; i--) {

                                                listValsCandleStick.remove(i);
                                                listValsCandleStickSize--;
                                                //System.out.println(listValsCandleStick.size());
                                            }

                                        }

                                        for(int idx = 0; idx <= candleStickSize - 1; idx++) {
                                            listValsCandleStick.add(new CandleEntry(idx, candlesticks.get(idx).getHigh(), candlesticks.get(idx).getLow(), candlesticks.get(idx).getOpen(), candlesticks.get(idx).getClose()));
                                        }

                                        //This call SHOULD destroy any array already in the chart
                                        if(candleStickSize > 0) {
                                            for(int i = candleStickSize - 1; i >=0; i--) {
                                                candlesticks.remove(i);
                                                candleStickSize--;
                                            }
                                        }

                                        CandleDataSet dataList = new CandleDataSet(listValsCandleStick, "DataList 1");
                                        dataList.setColor(Color.rgb(80, 80, 80));

                                        dataList.setShadowColor(getResources().getColor(R.color.design_default_color_secondary));
                                        dataList.setShadowWidth(0.8f);
                                        dataList.setDecreasingColor(getResources().getColor(R.color.design_default_color_surface));
                                        dataList.setDecreasingPaintStyle(Paint.Style.FILL);
                                        dataList.setIncreasingColor(getResources().getColor(R.color.design_default_color_primary_variant));
                                        dataList.setIncreasingPaintStyle(Paint.Style.FILL);
                                        dataList.setNeutralColor(Color.LTGRAY);
                                        dataList.setDrawValues(false);

                                        CandleData data = new CandleData(dataList);

                                        candleStickChart.setData(data);
                                        candleStickChart.setBackgroundColor(Color.rgb(0, 0, 0));
                                        candleStickChart.invalidate();
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        //Log.e("Rest Response2 Error", error.toString());
                                    }
                                }
                        );

                        //If if breaks anywhere, I think it might break here...

                        requestQueue.add(objectRequest3);
                    }
                    break;
                    case "LTC": {
                        System.out.println("Hi ho 365 day LTC!");
                        selectedURL = "https://btcbrunchapi.com/ROLLING_365_LTC";
                        String finalSelectedURL = selectedURL;

                        JsonArrayRequest objectRequest3 = new JsonArrayRequest(Request.Method.GET,
                                selectedURL,
                                null,
                                new Response.Listener<JSONArray>() {
                                    @Override
                                    public void onResponse(JSONArray response) {
                                        //Log.e("Ticker2 Response", response.toString());
                                        tickerDataFunction(response);

                                        //Create a loop here that will iterate as many times as size
                                        int candleStickSize = candlesticks.size();
                                        int listValsCandleStickSize = listValsCandleStick.size();
                                        //System.out.println("This is the size of candlesticks: " + candleStickSize);
                                        //System.out.println("This is the size of listValsCandleStick: " + listValsCandleStickSize);

                                        //clear the chart values and prepare for a new chart in the requestqueue
                                        if(listValsCandleStickSize > 0) {
                                            for(int i = listValsCandleStickSize - 1; i >= 0; i--) {

                                                listValsCandleStick.remove(i);
                                                listValsCandleStickSize--;
                                                //System.out.println(listValsCandleStick.size());
                                            }

                                        }

                                        for(int idx = 0; idx <= candleStickSize - 1; idx++) {
                                            listValsCandleStick.add(new CandleEntry(idx, candlesticks.get(idx).getHigh(), candlesticks.get(idx).getLow(), candlesticks.get(idx).getOpen(), candlesticks.get(idx).getClose()));
                                        }

                                        //This call SHOULD destroy any array already in the chart
                                        if(candleStickSize > 0) {
                                            for(int i = candleStickSize - 1; i >=0; i--) {
                                                candlesticks.remove(i);
                                                candleStickSize--;
                                            }
                                        }

                                        CandleDataSet dataList = new CandleDataSet(listValsCandleStick, "DataList 1");
                                        dataList.setColor(Color.rgb(80, 80, 80));

                                        dataList.setShadowColor(getResources().getColor(R.color.design_default_color_secondary));
                                        dataList.setShadowWidth(0.8f);
                                        dataList.setDecreasingColor(getResources().getColor(R.color.design_default_color_surface));
                                        dataList.setDecreasingPaintStyle(Paint.Style.FILL);
                                        dataList.setIncreasingColor(getResources().getColor(R.color.design_default_color_primary_variant));
                                        dataList.setIncreasingPaintStyle(Paint.Style.FILL);
                                        dataList.setNeutralColor(Color.LTGRAY);
                                        dataList.setDrawValues(false);

                                        CandleData data = new CandleData(dataList);

                                        candleStickChart.setData(data);
                                        candleStickChart.setBackgroundColor(Color.rgb(0, 0, 0));
                                        candleStickChart.invalidate();
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        //Log.e("Rest Response2 Error", error.toString());
                                    }
                                }
                        );

                        //If if breaks anywhere, I think it might break here...

                        requestQueue.add(objectRequest3);
                    }
                    break;
                }
                break;
            default:
        }//end of buttons switch

    }
}




































