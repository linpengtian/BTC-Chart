package com.btcbrunch;

import android.database.Cursor;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class CryptoSelector {

    public void selectCrypto(String stockSpinnerStrVal, String bitCoinSpinnerStrVal, int numOfDaysBtnSelected, RequestQueue requestQueue, LineChart mpLineChart, CommoditySelector commoditySelector) {
        String selectedURL = null;

        if ("BTC".equals(bitCoinSpinnerStrVal)) {//                System.out.println("This is the number of days selected. I can use this to modify the size of the array and grab what I need: "  + numOfDaysBtnSelected);
//
//                Cursor returnData = BitCoinVsStocksActivity.mDatabaseHelper.getData();
//                returnData.moveToPosition(0);
//                String holder = returnData.getString(2);
//                holder = holder.replaceAll(",", ""); //Removes the commas from the string
//                holder = holder.replaceAll("[\\[\\](){}]","");
//                String[] holderArr = holder.split(" ");
//
//                //Create a subarray from holder array equal to the size you get from numOfDaysBtnSelected
//                String[] holderSubArr = Arrays.copyOfRange(holderArr, 365 - numOfDaysBtnSelected, 365);
//
//                int numOfDaysCrypto;
//                BitCoinVsStocksActivity.cryptoValsInOnClick = tickerDataFunction2(holderSubArr);
//                numOfDaysCrypto = BitCoinVsStocksActivity.cryptoValsInOnClick.size();
//                ArrayList<Entry> commodityBitCoin = new ArrayList<>();
//
//                commoditySelector.selectCommodity(numOfDaysCrypto, stockSpinnerStrVal, bitCoinSpinnerStrVal, BitCoinVsStocksActivity.cryptoValsInOnClick, commodityBitCoin, mpLineChart);
//
//                System.out.println("This is the data from the BTC row in the SQLite database: " + holder);
//                System.out.println("This is the data from the holderArr: " + holderArr.toString());

            //Start of the Volley call and response object scope
            //Leave these calls for testing or other purposes

//            selectedURL = "http://192.168.0.13:3000/BTC";
//            selectedURL = "http://192.168.0.13:3000/ROLLING_180_BTC";
            selectedURL = "https://btcbapi.com/ROLLING_" + numOfDaysBtnSelected + "_BTC";
            System.out.println(selectedURL);
            //selectedURL = "https://api.binance.com/api/v3/klines?symbol=BTCUSDT&interval=1d&limit=180";

            String finalSelectedURL = selectedURL;
            JsonArrayRequest objectRequest8 = new JsonArrayRequest(Request.Method.GET,
                    selectedURL,
                    null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            //Log.e("Crypto Ticker Response", response.toString());
//                                System.out.println(response.toString());
                            int numOfDaysCrypto;
                            BitCoinVsStocksActivity.cryptoValsInOnClick = tickerDataFunction(response);
                            numOfDaysCrypto = BitCoinVsStocksActivity.cryptoValsInOnClick.size();
                            ArrayList<Entry> commodityBitCoin = new ArrayList<>();

                            commoditySelector.selectCommodity(numOfDaysCrypto, stockSpinnerStrVal, bitCoinSpinnerStrVal, BitCoinVsStocksActivity.cryptoValsInOnClick, commodityBitCoin, mpLineChart);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //Log.e("Crypto Response Error", error.toString());
                        }
                    }
            );
            requestQueue.add(objectRequest8);
        } else if ("ETH".equals(bitCoinSpinnerStrVal)) {
            System.out.println("This is the number of days selected. I can use this to modify the size of the array and grab what I need: " + numOfDaysBtnSelected);

            Cursor returnData = BitCoinVsStocksActivity.mDatabaseHelper.getData();
            returnData.moveToPosition(27);
            String holder = returnData.getString(2);
            holder = holder.replaceAll(",", ""); //Removes the commas from the string
            holder = holder.replaceAll("[\\[\\](){}]", "");
            String[] holderArr = holder.split(" ");

            //Create a subarray from holder array equal to the size you get from numOfDaysBtnSelected
            String[] holderSubArr = Arrays.copyOfRange(holderArr, 365 - numOfDaysBtnSelected, 365);

            int numOfDaysCrypto;
            BitCoinVsStocksActivity.cryptoValsInOnClick = tickerDataFunction2(holderSubArr);
            numOfDaysCrypto = BitCoinVsStocksActivity.cryptoValsInOnClick.size();
            ArrayList<Entry> commodityBitCoin = new ArrayList<>();

            commoditySelector.selectCommodity(numOfDaysCrypto, stockSpinnerStrVal, bitCoinSpinnerStrVal, BitCoinVsStocksActivity.cryptoValsInOnClick, commodityBitCoin, mpLineChart);

            System.out.println("This is the data from the ETH row in the SQLite database: " + holder);
            System.out.println("This is the data from the holderArr: " + holderArr.toString());

            //Start of the Volley call and response object scope
            //Leave these calls for testing or other purposes

//                        selectedURL="http://192.168.0.13:3000/ROLLING_180_ETH";
//                selectedURL="https://btcbrunchapi.com/ROLLING_" + numOfDaysBtnSelected + "_ETH";
//                System.out.println(selectedURL);
//                //selectedURL = "https://api.binance.com/api/v3/klines?symbol=ETHUSDT&interval=1d&limit=180";
//
//                String finalSelectedURL = selectedURL;
//                JsonArrayRequest objectRequest8 = new JsonArrayRequest(Request.Method.GET,
//                        selectedURL,
//                        null,
//                        new Response.Listener<JSONArray>() {
//                            @Override
//                            public void onResponse(JSONArray response) {
//                                int numOfDaysCrypto;
//                                //Log.e("Crypto Ticker Response", response.toString());
//                                BitCoinVsStocksActivity.cryptoValsInOnClick = tickerDataFunction(response);
//                                numOfDaysCrypto = BitCoinVsStocksActivity.cryptoValsInOnClick.size();
//                                ArrayList<Entry> commodityBitCoin = new ArrayList<>();
//                                commoditySelector.selectCommodity(numOfDaysCrypto, stockSpinnerStrVal, bitCoinSpinnerStrVal, BitCoinVsStocksActivity.cryptoValsInOnClick, commodityBitCoin, mpLineChart);
//                            }
//                        },
//                        new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//                                //Log.e("Crypto Response Error", error.toString());
//                            }
//                        }
//                );
//                requestQueue.add(objectRequest8);
        } else if ("BCH".equals(bitCoinSpinnerStrVal)) {
            System.out.println("This is the number of days selected. I can use this to modify the size of the array and grab what I need: " + numOfDaysBtnSelected);

            Cursor returnData = BitCoinVsStocksActivity.mDatabaseHelper.getData();
            returnData.moveToPosition(28);
            String holder = returnData.getString(2);
            holder = holder.replaceAll(",", ""); //Removes the commas from the string
            holder = holder.replaceAll("[\\[\\](){}]", "");
            String[] holderArr = holder.split(" ");

            //Create a subarray from holder array equal to the size you get from numOfDaysBtnSelected
            String[] holderSubArr = Arrays.copyOfRange(holderArr, 365 - numOfDaysBtnSelected, 365);

            int numOfDaysCrypto;
            BitCoinVsStocksActivity.cryptoValsInOnClick = tickerDataFunction2(holderSubArr);
            numOfDaysCrypto = BitCoinVsStocksActivity.cryptoValsInOnClick.size();
            ArrayList<Entry> commodityBitCoin = new ArrayList<>();

            commoditySelector.selectCommodity(numOfDaysCrypto, stockSpinnerStrVal, bitCoinSpinnerStrVal, BitCoinVsStocksActivity.cryptoValsInOnClick, commodityBitCoin, mpLineChart);

            System.out.println("This is the data from the BCH row in the SQLite database: " + holder);
            System.out.println("This is the data from the holderArr: " + holderArr.toString());

            //Start of the Volley call and response object scope
            //Leave these calls for testing or other purposes

//                        selectedURL="http://192.168.0.13:3000/ROLLING_180_BCH";
//                selectedURL="https://btcbrunchapi.com/ROLLING_" + numOfDaysBtnSelected + "_BCH";
//                System.out.println(selectedURL);
//                //selectedURL = "https://api.binance.com/api/v3/klines?symbol=BCHUSDT&interval=1d&limit=180";
//
//                String finalSelectedURL = selectedURL;
//                JsonArrayRequest objectRequest8 = new JsonArrayRequest(Request.Method.GET,
//                        selectedURL,
//                        null,
//                        new Response.Listener<JSONArray>() {
//                            @Override
//                            public void onResponse(JSONArray response) {
//                                int numOfDaysCrypto;
//
//                                //Log.e("Crypto Ticker Response", response.toString());
//                                BitCoinVsStocksActivity.cryptoValsInOnClick = tickerDataFunction(response);
//                                numOfDaysCrypto = BitCoinVsStocksActivity.cryptoValsInOnClick.size();
//                                ArrayList<Entry> commodityBitCoin = new ArrayList<>();
//                                commoditySelector.selectCommodity(numOfDaysCrypto, stockSpinnerStrVal, bitCoinSpinnerStrVal, BitCoinVsStocksActivity.cryptoValsInOnClick, commodityBitCoin, mpLineChart);
//                            }
//                        },
//                        new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//                                //Log.e("Crypto Response Error", error.toString());
//                            }
//                        }
//                );
//                requestQueue.add(objectRequest8);
        } else if ("LTC".equals(bitCoinSpinnerStrVal)) {
            System.out.println("This is the number of days selected. I can use this to modify the size of the array and grab what I need: " + numOfDaysBtnSelected);

            Cursor returnData = BitCoinVsStocksActivity.mDatabaseHelper.getData();
            returnData.moveToPosition(29);
            String holder = returnData.getString(2);
            holder = holder.replaceAll(",", ""); //Removes the commas from the string
            holder = holder.replaceAll("[\\[\\](){}]", "");
            String[] holderArr = holder.split(" ");

            //Create a subarray from holder array equal to the size you get from numOfDaysBtnSelected
            String[] holderSubArr = Arrays.copyOfRange(holderArr, 365 - numOfDaysBtnSelected, 365);

            int numOfDaysCrypto;
            BitCoinVsStocksActivity.cryptoValsInOnClick = tickerDataFunction2(holderSubArr);
            numOfDaysCrypto = BitCoinVsStocksActivity.cryptoValsInOnClick.size();
            ArrayList<Entry> commodityBitCoin = new ArrayList<>();

            commoditySelector.selectCommodity(numOfDaysCrypto, stockSpinnerStrVal, bitCoinSpinnerStrVal, BitCoinVsStocksActivity.cryptoValsInOnClick, commodityBitCoin, mpLineChart);

            System.out.println("This is the data from the LTC row in the SQLite database: " + holder);
            System.out.println("This is the data from the holderArr: " + holderArr.toString());

            //Start of the Volley call and response object scope
            //Leave these calls for testing or other purposes

//                        selectedURL="http://192.168.0.13:3000/ROLLING_180_LTC";
//                selectedURL="https://btcbrunchapi.com/ROLLING_" + numOfDaysBtnSelected + "_LTC";
//                System.out.println(selectedURL);
//                //selectedURL = "https://api.binance.com/api/v3/klines?symbol=LTCUSDT&interval=1d&limit=180";
//
//                String finalSelectedURL = selectedURL;
//                JsonArrayRequest objectRequest8 = new JsonArrayRequest(Request.Method.GET,
//                        selectedURL,
//                        null,
//                        new Response.Listener<JSONArray>() {
//                            @Override
//                            public void onResponse(JSONArray response) {
//                                int numOfDaysCrypto;
//
//                                //Log.e("Crypto Ticker Response", response.toString());
//                                BitCoinVsStocksActivity.cryptoValsInOnClick = tickerDataFunction(response);
//                                numOfDaysCrypto = BitCoinVsStocksActivity.cryptoValsInOnClick.size();
//                                ArrayList<Entry> commodityBitCoin = new ArrayList<>();
//                                commoditySelector.selectCommodity(numOfDaysCrypto, stockSpinnerStrVal, bitCoinSpinnerStrVal, BitCoinVsStocksActivity.cryptoValsInOnClick, commodityBitCoin, mpLineChart);
//                            }
//                        },
//                        new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//                                //Log.e("Crypto Response Error", error.toString());
//                            }
//                        }
//                );
//                requestQueue.add(objectRequest8);
        } else if ("BNB".equals(bitCoinSpinnerStrVal)) {
            System.out.println("This is the number of days selected. I can use this to modify the size of the array and grab what I need: " + numOfDaysBtnSelected);

            Cursor returnData = BitCoinVsStocksActivity.mDatabaseHelper.getData();
            returnData.moveToPosition(30);
            String holder = returnData.getString(2);
            holder = holder.replaceAll(",", ""); //Removes the commas from the string
            holder = holder.replaceAll("[\\[\\](){}]", "");
            String[] holderArr = holder.split(" ");

            //Create a subarray from holder array equal to the size you get from numOfDaysBtnSelected
            String[] holderSubArr = Arrays.copyOfRange(holderArr, 365 - numOfDaysBtnSelected, 365);

            int numOfDaysCrypto;
            BitCoinVsStocksActivity.cryptoValsInOnClick = tickerDataFunction2(holderSubArr);
            numOfDaysCrypto = BitCoinVsStocksActivity.cryptoValsInOnClick.size();
            ArrayList<Entry> commodityBitCoin = new ArrayList<>();

            commoditySelector.selectCommodity(numOfDaysCrypto, stockSpinnerStrVal, bitCoinSpinnerStrVal, BitCoinVsStocksActivity.cryptoValsInOnClick, commodityBitCoin, mpLineChart);

            System.out.println("This is the data from the BNB row in the SQLite database: " + holder);
            System.out.println("This is the data from the holderArr: " + holderArr.toString());

            //Start of the Volley call and response object scope
            //Leave these calls for testing or other purposes

//                selectedURL="https://btcbrunchapi.com/ROLLING_" + numOfDaysBtnSelected + "_BNB";
//                System.out.println(selectedURL);
//                //selectedURL = "https://api.binance.com/api/v3/klines?symbol=LTCUSDT&interval=1d&limit=180";
//
//                String finalSelectedURL = selectedURL;
//                JsonArrayRequest objectRequest8 = new JsonArrayRequest(Request.Method.GET,
//                        selectedURL,
//                        null,
//                        new Response.Listener<JSONArray>() {
//                            @Override
//                            public void onResponse(JSONArray response) {
//                                int numOfDaysCrypto;
//
//                                //Log.e("Crypto Ticker Response", response.toString());
//                                BitCoinVsStocksActivity.cryptoValsInOnClick = tickerDataFunction(response);
//                                numOfDaysCrypto = BitCoinVsStocksActivity.cryptoValsInOnClick.size();
//                                ArrayList<Entry> commodityBitCoin = new ArrayList<>();
//                                commoditySelector.selectCommodity(numOfDaysCrypto, stockSpinnerStrVal, bitCoinSpinnerStrVal, BitCoinVsStocksActivity.cryptoValsInOnClick, commodityBitCoin, mpLineChart);
//                            }
//                        },
//                        new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//                                //Log.e("Crypto Response Error", error.toString());
//                            }
//                        }
//                );
//                requestQueue.add(objectRequest8);
        } else if ("ADA".equals(bitCoinSpinnerStrVal)) {
            System.out.println("This is the number of days selected. I can use this to modify the size of the array and grab what I need: " + numOfDaysBtnSelected);

            Cursor returnData = BitCoinVsStocksActivity.mDatabaseHelper.getData();
            returnData.moveToPosition(31);
            String holder = returnData.getString(2);
            holder = holder.replaceAll(",", ""); //Removes the commas from the string
            holder = holder.replaceAll("[\\[\\](){}]", "");
            String[] holderArr = holder.split(" ");

            //Create a subarray from holder array equal to the size you get from numOfDaysBtnSelected
            String[] holderSubArr = Arrays.copyOfRange(holderArr, 365 - numOfDaysBtnSelected, 365);

            int numOfDaysCrypto;
            BitCoinVsStocksActivity.cryptoValsInOnClick = tickerDataFunction2(holderSubArr);
            numOfDaysCrypto = BitCoinVsStocksActivity.cryptoValsInOnClick.size();
            ArrayList<Entry> commodityBitCoin = new ArrayList<>();

            commoditySelector.selectCommodity(numOfDaysCrypto, stockSpinnerStrVal, bitCoinSpinnerStrVal, BitCoinVsStocksActivity.cryptoValsInOnClick, commodityBitCoin, mpLineChart);

            System.out.println("This is the data from the ADA row in the SQLite database: " + holder);
            System.out.println("This is the data from the holderArr: " + holderArr.toString());

            //Start of the Volley call and response object scope
            //Leave these calls for testing or other purposes

//                selectedURL="https://btcbrunchapi.com/ROLLING_" + numOfDaysBtnSelected + "_ADA";
//                System.out.println(selectedURL);
//                //selectedURL = "https://api.binance.com/api/v3/klines?symbol=LTCUSDT&interval=1d&limit=180";
//
//                String finalSelectedURL = selectedURL;
//                JsonArrayRequest objectRequest8 = new JsonArrayRequest(Request.Method.GET,
//                        selectedURL,
//                        null,
//                        new Response.Listener<JSONArray>() {
//                            @Override
//                            public void onResponse(JSONArray response) {
//                                int numOfDaysCrypto;
//
//                                //Log.e("Crypto Ticker Response", response.toString());
//                                BitCoinVsStocksActivity.cryptoValsInOnClick = tickerDataFunction(response);
//                                numOfDaysCrypto = BitCoinVsStocksActivity.cryptoValsInOnClick.size();
//                                ArrayList<Entry> commodityBitCoin = new ArrayList<>();
//                                commoditySelector.selectCommodity(numOfDaysCrypto, stockSpinnerStrVal, bitCoinSpinnerStrVal, BitCoinVsStocksActivity.cryptoValsInOnClick, commodityBitCoin, mpLineChart);
//                            }
//                        },
//                        new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//                                //Log.e("Crypto Response Error", error.toString());
//                            }
//                        }
//                );
//                requestQueue.add(objectRequest8);
        } else if ("DOGE".equals(bitCoinSpinnerStrVal)) {
            System.out.println("This is the number of days selected. I can use this to modify the size of the array and grab what I need: " + numOfDaysBtnSelected);

            Cursor returnData = BitCoinVsStocksActivity.mDatabaseHelper.getData();
            returnData.moveToPosition(32);
            String holder = returnData.getString(2);
            holder = holder.replaceAll(",", ""); //Removes the commas from the string
            holder = holder.replaceAll("[\\[\\](){}]", "");
            String[] holderArr = holder.split(" ");

            //Create a subarray from holder array equal to the size you get from numOfDaysBtnSelected
            String[] holderSubArr = Arrays.copyOfRange(holderArr, 365 - numOfDaysBtnSelected, 365);

            int numOfDaysCrypto;
            BitCoinVsStocksActivity.cryptoValsInOnClick = tickerDataFunction2(holderSubArr);
            numOfDaysCrypto = BitCoinVsStocksActivity.cryptoValsInOnClick.size();
            ArrayList<Entry> commodityBitCoin = new ArrayList<>();

            commoditySelector.selectCommodity(numOfDaysCrypto, stockSpinnerStrVal, bitCoinSpinnerStrVal, BitCoinVsStocksActivity.cryptoValsInOnClick, commodityBitCoin, mpLineChart);

            System.out.println("This is the data from the DOGE row in the SQLite database: " + holder);
            System.out.println("This is the data from the holderArr: " + holderArr.toString());

            //Start of the Volley call and response object scope
            //Leave these calls for testing or other purposes

//                selectedURL="https://btcbrunchapi.com/ROLLING_" + numOfDaysBtnSelected + "_DOGE";
//                System.out.println(selectedURL);
//                //selectedURL = "https://api.binance.com/api/v3/klines?symbol=LTCUSDT&interval=1d&limit=180";
//
//                String finalSelectedURL = selectedURL;
//                JsonArrayRequest objectRequest8 = new JsonArrayRequest(Request.Method.GET,
//                        selectedURL,
//                        null,
//                        new Response.Listener<JSONArray>() {
//                            @Override
//                            public void onResponse(JSONArray response) {
//                                int numOfDaysCrypto;
//
//                                //Log.e("Crypto Ticker Response", response.toString());
//                                BitCoinVsStocksActivity.cryptoValsInOnClick = tickerDataFunction(response);
//                                numOfDaysCrypto = BitCoinVsStocksActivity.cryptoValsInOnClick.size();
//                                ArrayList<Entry> commodityBitCoin = new ArrayList<>();
//                                commoditySelector.selectCommodity(numOfDaysCrypto, stockSpinnerStrVal, bitCoinSpinnerStrVal, BitCoinVsStocksActivity.cryptoValsInOnClick, commodityBitCoin, mpLineChart);
//                            }
//                        },
//                        new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//                                //Log.e("Crypto Response Error", error.toString());
//                            }
//                        }
//                );
//                requestQueue.add(objectRequest8);
        } else if ("XRP".equals(bitCoinSpinnerStrVal)) {
            System.out.println("This is the number of days selected. I can use this to modify the size of the array and grab what I need: " + numOfDaysBtnSelected);

            Cursor returnData = BitCoinVsStocksActivity.mDatabaseHelper.getData();
            returnData.moveToPosition(33);
            String holder = returnData.getString(2);
            holder = holder.replaceAll(",", ""); //Removes the commas from the string
            holder = holder.replaceAll("[\\[\\](){}]", "");
            String[] holderArr = holder.split(" ");

            //Create a subarray from holder array equal to the size you get from numOfDaysBtnSelected
            String[] holderSubArr = Arrays.copyOfRange(holderArr, 365 - numOfDaysBtnSelected, 365);

            int numOfDaysCrypto;
            BitCoinVsStocksActivity.cryptoValsInOnClick = tickerDataFunction2(holderSubArr);
            numOfDaysCrypto = BitCoinVsStocksActivity.cryptoValsInOnClick.size();
            ArrayList<Entry> commodityBitCoin = new ArrayList<>();

            commoditySelector.selectCommodity(numOfDaysCrypto, stockSpinnerStrVal, bitCoinSpinnerStrVal, BitCoinVsStocksActivity.cryptoValsInOnClick, commodityBitCoin, mpLineChart);

            System.out.println("This is the data from the XRP row in the SQLite database: " + holder);
            System.out.println("This is the data from the holderArr: " + holderArr.toString());

            //Start of the Volley call and response object scope
            //Leave these calls for testing or other purposes

//                selectedURL="https://btcbrunchapi.com/ROLLING_" + numOfDaysBtnSelected + "_XRP";
//                System.out.println(selectedURL);
//                //selectedURL = "https://api.binance.com/api/v3/klines?symbol=LTCUSDT&interval=1d&limit=180";
//
//                String finalSelectedURL = selectedURL;
//                JsonArrayRequest objectRequest8 = new JsonArrayRequest(Request.Method.GET,
//                        selectedURL,
//                        null,
//                        new Response.Listener<JSONArray>() {
//                            @Override
//                            public void onResponse(JSONArray response) {
//                                int numOfDaysCrypto;
//
//                                //Log.e("Crypto Ticker Response", response.toString());
//                                BitCoinVsStocksActivity.cryptoValsInOnClick = tickerDataFunction(response);
//                                numOfDaysCrypto = BitCoinVsStocksActivity.cryptoValsInOnClick.size();
//                                ArrayList<Entry> commodityBitCoin = new ArrayList<>();
//                                commoditySelector.selectCommodity(numOfDaysCrypto, stockSpinnerStrVal, bitCoinSpinnerStrVal, BitCoinVsStocksActivity.cryptoValsInOnClick, commodityBitCoin, mpLineChart);
//                            }
//                        },
//                        new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//                                //Log.e("Crypto Response Error", error.toString());
//                            }
//                        }
//                );
//                requestQueue.add(objectRequest8);
        } else if ("DOT".equals(bitCoinSpinnerStrVal)) {
            System.out.println("This is the number of days selected. I can use this to modify the size of the array and grab what I need: " + numOfDaysBtnSelected);

            Cursor returnData = BitCoinVsStocksActivity.mDatabaseHelper.getData();
            returnData.moveToPosition(34);
            String holder = returnData.getString(2);
            holder = holder.replaceAll(",", ""); //Removes the commas from the string
            holder = holder.replaceAll("[\\[\\](){}]", "");
            String[] holderArr = holder.split(" ");

            //Create a subarray from holder array equal to the size you get from numOfDaysBtnSelected
            String[] holderSubArr = Arrays.copyOfRange(holderArr, 365 - numOfDaysBtnSelected, 365);

            int numOfDaysCrypto;
            BitCoinVsStocksActivity.cryptoValsInOnClick = tickerDataFunction2(holderSubArr);
            numOfDaysCrypto = BitCoinVsStocksActivity.cryptoValsInOnClick.size();
            ArrayList<Entry> commodityBitCoin = new ArrayList<>();

            commoditySelector.selectCommodity(numOfDaysCrypto, stockSpinnerStrVal, bitCoinSpinnerStrVal, BitCoinVsStocksActivity.cryptoValsInOnClick, commodityBitCoin, mpLineChart);

            System.out.println("This is the data from the DOT row in the SQLite database: " + holder);
            System.out.println("This is the data from the holderArr: " + holderArr.toString());

            //Start of the Volley call and response object scope
            //Leave these calls for testing or other purposes

//                selectedURL="https://btcbrunchapi.com/ROLLING_" + numOfDaysBtnSelected + "_DOT";
//                System.out.println(selectedURL);
//                //selectedURL = "https://api.binance.com/api/v3/klines?symbol=LTCUSDT&interval=1d&limit=180";
//
//                String finalSelectedURL = selectedURL;
//                JsonArrayRequest objectRequest8 = new JsonArrayRequest(Request.Method.GET,
//                        selectedURL,
//                        null,
//                        new Response.Listener<JSONArray>() {
//                            @Override
//                            public void onResponse(JSONArray response) {
//                                int numOfDaysCrypto;
//
//                                //Log.e("Crypto Ticker Response", response.toString());
//                                BitCoinVsStocksActivity.cryptoValsInOnClick = tickerDataFunction(response);
//                                numOfDaysCrypto = BitCoinVsStocksActivity.cryptoValsInOnClick.size();
//                                ArrayList<Entry> commodityBitCoin = new ArrayList<>();
//                                commoditySelector.selectCommodity(numOfDaysCrypto, stockSpinnerStrVal, bitCoinSpinnerStrVal, BitCoinVsStocksActivity.cryptoValsInOnClick, commodityBitCoin, mpLineChart);
//                            }
//                        },
//                        new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//                                //Log.e("Crypto Response Error", error.toString());
//                            }
//                        }
//                );
//                requestQueue.add(objectRequest8);
        } else if ("UNI".equals(bitCoinSpinnerStrVal)) {
            System.out.println("This is the number of days selected. I can use this to modify the size of the array and grab what I need: " + numOfDaysBtnSelected);

            Cursor returnData = BitCoinVsStocksActivity.mDatabaseHelper.getData();
            returnData.moveToPosition(35);
            String holder = returnData.getString(2);
            holder = holder.replaceAll(",", ""); //Removes the commas from the string
            holder = holder.replaceAll("[\\[\\](){}]", "");
            String[] holderArr = holder.split(" ");

            //Create a subarray from holder array equal to the size you get from numOfDaysBtnSelected
            String[] holderSubArr = Arrays.copyOfRange(holderArr, 365 - numOfDaysBtnSelected, 365);

            int numOfDaysCrypto;
            BitCoinVsStocksActivity.cryptoValsInOnClick = tickerDataFunction2(holderSubArr);
            numOfDaysCrypto = BitCoinVsStocksActivity.cryptoValsInOnClick.size();
            ArrayList<Entry> commodityBitCoin = new ArrayList<>();

            commoditySelector.selectCommodity(numOfDaysCrypto, stockSpinnerStrVal, bitCoinSpinnerStrVal, BitCoinVsStocksActivity.cryptoValsInOnClick, commodityBitCoin, mpLineChart);

            System.out.println("This is the data from the UNI row in the SQLite database: " + holder);
            System.out.println("This is the data from the holderArr: " + holderArr.toString());

            //Start of the Volley call and response object scope
            //Leave these calls for testing or other purposes

//                selectedURL="https://btcbrunchapi.com/ROLLING_" + numOfDaysBtnSelected + "_UNI";
//                System.out.println(selectedURL);
//                //selectedURL = "https://api.binance.com/api/v3/klines?symbol=LTCUSDT&interval=1d&limit=180";
//
//                String finalSelectedURL = selectedURL;
//                JsonArrayRequest objectRequest8 = new JsonArrayRequest(Request.Method.GET,
//                        selectedURL,
//                        null,
//                        new Response.Listener<JSONArray>() {
//                            @Override
//                            public void onResponse(JSONArray response) {
//                                int numOfDaysCrypto;
//
//                                //Log.e("Crypto Ticker Response", response.toString());
//                                BitCoinVsStocksActivity.cryptoValsInOnClick = tickerDataFunction(response);
//                                numOfDaysCrypto = BitCoinVsStocksActivity.cryptoValsInOnClick.size();
//                                ArrayList<Entry> commodityBitCoin = new ArrayList<>();
//                                commoditySelector.selectCommodity(numOfDaysCrypto, stockSpinnerStrVal, bitCoinSpinnerStrVal, BitCoinVsStocksActivity.cryptoValsInOnClick, commodityBitCoin, mpLineChart);
//                            }
//                        },
//                        new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//                                //Log.e("Crypto Response Error", error.toString());
//                            }
//                        }
//                );
//                requestQueue.add(objectRequest8);
        } else if ("SOL".equals(bitCoinSpinnerStrVal)) {
            System.out.println("This is the number of days selected. I can use this to modify the size of the array and grab what I need: " + numOfDaysBtnSelected);

            Cursor returnData = BitCoinVsStocksActivity.mDatabaseHelper.getData();
            returnData.moveToPosition(36);
            String holder = returnData.getString(2);
            holder = holder.replaceAll(",", ""); //Removes the commas from the string
            holder = holder.replaceAll("[\\[\\](){}]", "");
            String[] holderArr = holder.split(" ");

            //Create a subarray from holder array equal to the size you get from numOfDaysBtnSelected
            String[] holderSubArr = Arrays.copyOfRange(holderArr, 365 - numOfDaysBtnSelected, 365);

            int numOfDaysCrypto;
            BitCoinVsStocksActivity.cryptoValsInOnClick = tickerDataFunction2(holderSubArr);
            numOfDaysCrypto = BitCoinVsStocksActivity.cryptoValsInOnClick.size();
            ArrayList<Entry> commodityBitCoin = new ArrayList<>();

            commoditySelector.selectCommodity(numOfDaysCrypto, stockSpinnerStrVal, bitCoinSpinnerStrVal, BitCoinVsStocksActivity.cryptoValsInOnClick, commodityBitCoin, mpLineChart);

            System.out.println("This is the data from the SOL row in the SQLite database: " + holder);
            System.out.println("This is the data from the holderArr: " + holderArr.toString());

            //Start of the Volley call and response object scope
            //Leave these calls for testing or other purposes

//                selectedURL="https://btcbrunchapi.com/ROLLING_" + numOfDaysBtnSelected + "_SOL";
//                System.out.println(selectedURL);
//                //selectedURL = "https://api.binance.com/api/v3/klines?symbol=LTCUSDT&interval=1d&limit=180";
//
//                String finalSelectedURL = selectedURL;
//                JsonArrayRequest objectRequest8 = new JsonArrayRequest(Request.Method.GET,
//                        selectedURL,
//                        null,
//                        new Response.Listener<JSONArray>() {
//                            @Override
//                            public void onResponse(JSONArray response) {
//                                int numOfDaysCrypto;
//
//                                //Log.e("Crypto Ticker Response", response.toString());
//                                BitCoinVsStocksActivity.cryptoValsInOnClick = tickerDataFunction(response);
//                                numOfDaysCrypto = BitCoinVsStocksActivity.cryptoValsInOnClick.size();
//                                ArrayList<Entry> commodityBitCoin = new ArrayList<>();
//                                commoditySelector.selectCommodity(numOfDaysCrypto, stockSpinnerStrVal, bitCoinSpinnerStrVal, BitCoinVsStocksActivity.cryptoValsInOnClick, commodityBitCoin, mpLineChart);
//                            }
//                        },
//                        new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//                                //Log.e("Crypto Response Error", error.toString());
//                            }
//                        }
//                );
//                requestQueue.add(objectRequest8);
        } else if ("LINK".equals(bitCoinSpinnerStrVal)) {
            System.out.println("This is the number of days selected. I can use this to modify the size of the array and grab what I need: " + numOfDaysBtnSelected);

            Cursor returnData = BitCoinVsStocksActivity.mDatabaseHelper.getData();
            returnData.moveToPosition(37);
            String holder = returnData.getString(2);
            holder = holder.replaceAll(",", ""); //Removes the commas from the string
            holder = holder.replaceAll("[\\[\\](){}]", "");
            String[] holderArr = holder.split(" ");

            //Create a subarray from holder array equal to the size you get from numOfDaysBtnSelected
            String[] holderSubArr = Arrays.copyOfRange(holderArr, 365 - numOfDaysBtnSelected, 365);

            int numOfDaysCrypto;
            BitCoinVsStocksActivity.cryptoValsInOnClick = tickerDataFunction2(holderSubArr);
            numOfDaysCrypto = BitCoinVsStocksActivity.cryptoValsInOnClick.size();
            ArrayList<Entry> commodityBitCoin = new ArrayList<>();

            commoditySelector.selectCommodity(numOfDaysCrypto, stockSpinnerStrVal, bitCoinSpinnerStrVal, BitCoinVsStocksActivity.cryptoValsInOnClick, commodityBitCoin, mpLineChart);

            System.out.println("This is the data from the LINK row in the SQLite database: " + holder);
            System.out.println("This is the data from the holderArr: " + holderArr.toString());

            //Start of the Volley call and response object scope
            //Leave these calls for testing or other purposes

//                selectedURL="https://btcbrunchapi.com/ROLLING_" + numOfDaysBtnSelected + "_LINK";
//                System.out.println(selectedURL);
//                //selectedURL = "https://api.binance.com/api/v3/klines?symbol=LTCUSDT&interval=1d&limit=180";
//
//                String finalSelectedURL = selectedURL;
//                JsonArrayRequest objectRequest8 = new JsonArrayRequest(Request.Method.GET,
//                        selectedURL,
//                        null,
//                        new Response.Listener<JSONArray>() {
//                            @Override
//                            public void onResponse(JSONArray response) {
//                                int numOfDaysCrypto;
//
//                                //Log.e("Crypto Ticker Response", response.toString());
//                                BitCoinVsStocksActivity.cryptoValsInOnClick = tickerDataFunction(response);
//                                numOfDaysCrypto = BitCoinVsStocksActivity.cryptoValsInOnClick.size();
//                                ArrayList<Entry> commodityBitCoin = new ArrayList<>();
//                                commoditySelector.selectCommodity(numOfDaysCrypto, stockSpinnerStrVal, bitCoinSpinnerStrVal, BitCoinVsStocksActivity.cryptoValsInOnClick, commodityBitCoin, mpLineChart);
//                            }
//                        },
//                        new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//                                //Log.e("Crypto Response Error", error.toString());
//                            }
//                        }
//                );
//                requestQueue.add(objectRequest8);
        } else if ("MATIC".equals(bitCoinSpinnerStrVal)) {
            System.out.println("This is the number of days selected. I can use this to modify the size of the array and grab what I need: " + numOfDaysBtnSelected);

            Cursor returnData = BitCoinVsStocksActivity.mDatabaseHelper.getData();
            returnData.moveToPosition(38);
            String holder = returnData.getString(2);
            holder = holder.replaceAll(",", ""); //Removes the commas from the string
            holder = holder.replaceAll("[\\[\\](){}]", "");
            String[] holderArr = holder.split(" ");

            //Create a subarray from holder array equal to the size you get from numOfDaysBtnSelected
            String[] holderSubArr = Arrays.copyOfRange(holderArr, 365 - numOfDaysBtnSelected, 365);

            int numOfDaysCrypto;
            BitCoinVsStocksActivity.cryptoValsInOnClick = tickerDataFunction2(holderSubArr);
            numOfDaysCrypto = BitCoinVsStocksActivity.cryptoValsInOnClick.size();
            ArrayList<Entry> commodityBitCoin = new ArrayList<>();

            commoditySelector.selectCommodity(numOfDaysCrypto, stockSpinnerStrVal, bitCoinSpinnerStrVal, BitCoinVsStocksActivity.cryptoValsInOnClick, commodityBitCoin, mpLineChart);

            System.out.println("This is the data from the MATIC row in the SQLite database: " + holder);
            System.out.println("This is the data from the holderArr: " + holderArr.toString());

            //Start of the Volley call and response object scope
            //Leave these calls for testing or other purposes

//                selectedURL="https://btcbrunchapi.com/ROLLING_" + numOfDaysBtnSelected + "_MATIC";
//                System.out.println(selectedURL);
//                //selectedURL = "https://api.binance.com/api/v3/klines?symbol=LTCUSDT&interval=1d&limit=180";
//
//                String finalSelectedURL = selectedURL;
//                JsonArrayRequest objectRequest8 = new JsonArrayRequest(Request.Method.GET,
//                        selectedURL,
//                        null,
//                        new Response.Listener<JSONArray>() {
//                            @Override
//                            public void onResponse(JSONArray response) {
//                                int numOfDaysCrypto;
//
//                                //Log.e("Crypto Ticker Response", response.toString());
//                                BitCoinVsStocksActivity.cryptoValsInOnClick = tickerDataFunction(response);
//                                numOfDaysCrypto = BitCoinVsStocksActivity.cryptoValsInOnClick.size();
//                                ArrayList<Entry> commodityBitCoin = new ArrayList<>();
//                                commoditySelector.selectCommodity(numOfDaysCrypto, stockSpinnerStrVal, bitCoinSpinnerStrVal, BitCoinVsStocksActivity.cryptoValsInOnClick, commodityBitCoin, mpLineChart);
//                            }
//                        },
//                        new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//                                //Log.e("Crypto Response Error", error.toString());
//                            }
//                        }
//                );
//                requestQueue.add(objectRequest8);
        } else if ("ETC".equals(bitCoinSpinnerStrVal)) {
            System.out.println("This is the number of days selected. I can use this to modify the size of the array and grab what I need: " + numOfDaysBtnSelected);

            Cursor returnData = BitCoinVsStocksActivity.mDatabaseHelper.getData();
            returnData.moveToPosition(39);
            String holder = returnData.getString(2);
            holder = holder.replaceAll(",", ""); //Removes the commas from the string
            holder = holder.replaceAll("[\\[\\](){}]", "");
            String[] holderArr = holder.split(" ");

            //Create a subarray from holder array equal to the size you get from numOfDaysBtnSelected
            String[] holderSubArr = Arrays.copyOfRange(holderArr, 365 - numOfDaysBtnSelected, 365);

            int numOfDaysCrypto;
            BitCoinVsStocksActivity.cryptoValsInOnClick = tickerDataFunction2(holderSubArr);
            numOfDaysCrypto = BitCoinVsStocksActivity.cryptoValsInOnClick.size();
            ArrayList<Entry> commodityBitCoin = new ArrayList<>();

            commoditySelector.selectCommodity(numOfDaysCrypto, stockSpinnerStrVal, bitCoinSpinnerStrVal, BitCoinVsStocksActivity.cryptoValsInOnClick, commodityBitCoin, mpLineChart);

            System.out.println("This is the data from the ETC row in the SQLite database: " + holder);
            System.out.println("This is the data from the holderArr: " + holderArr.toString());

            //Start of the Volley call and response object scope
            //Leave these calls for testing or other purposes

//                selectedURL="https://btcbrunchapi.com/ROLLING_" + numOfDaysBtnSelected + "_ETC";
//                System.out.println(selectedURL);
//                //selectedURL = "https://api.binance.com/api/v3/klines?symbol=LTCUSDT&interval=1d&limit=180";
//
//                String finalSelectedURL = selectedURL;
//                JsonArrayRequest objectRequest8 = new JsonArrayRequest(Request.Method.GET,
//                        selectedURL,
//                        null,
//                        new Response.Listener<JSONArray>() {
//                            @Override
//                            public void onResponse(JSONArray response) {
//                                int numOfDaysCrypto;
//
//                                //Log.e("Crypto Ticker Response", response.toString());
//                                BitCoinVsStocksActivity.cryptoValsInOnClick = tickerDataFunction(response);
//                                numOfDaysCrypto = BitCoinVsStocksActivity.cryptoValsInOnClick.size();
//                                ArrayList<Entry> commodityBitCoin = new ArrayList<>();
//                                commoditySelector.selectCommodity(numOfDaysCrypto, stockSpinnerStrVal, bitCoinSpinnerStrVal, BitCoinVsStocksActivity.cryptoValsInOnClick, commodityBitCoin, mpLineChart);
//                            }
//                        },
//                        new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//                                //Log.e("Crypto Response Error", error.toString());
//                            }
//                        }
//                );
//                requestQueue.add(objectRequest8);
        } else if ("HNT".equals(bitCoinSpinnerStrVal)) {
            System.out.println("This is the number of days selected. I can use this to modify the size of the array and grab what I need: " + numOfDaysBtnSelected);

            Cursor returnData = BitCoinVsStocksActivity.mDatabaseHelper.getData();
            returnData.moveToPosition(40);
            String holder = returnData.getString(2);
            holder = holder.replaceAll(",", ""); //Removes the commas from the string
            holder = holder.replaceAll("[\\[\\](){}]", "");
            String[] holderArr = holder.split(" ");

            //Create a subarray from holder array equal to the size you get from numOfDaysBtnSelected
            String[] holderSubArr = Arrays.copyOfRange(holderArr, 365 - numOfDaysBtnSelected, 365);

            int numOfDaysCrypto;
            BitCoinVsStocksActivity.cryptoValsInOnClick = tickerDataFunction2(holderSubArr);
            numOfDaysCrypto = BitCoinVsStocksActivity.cryptoValsInOnClick.size();
            ArrayList<Entry> commodityBitCoin = new ArrayList<>();

            commoditySelector.selectCommodity(numOfDaysCrypto, stockSpinnerStrVal, bitCoinSpinnerStrVal, BitCoinVsStocksActivity.cryptoValsInOnClick, commodityBitCoin, mpLineChart);

            System.out.println("This is the data from the HNT row in the SQLite database: " + holder);
            System.out.println("This is the data from the holderArr: " + holderArr.toString());

            //Start of the Volley call and response object scope
            //Leave these calls for testing or other purposes

//                selectedURL="https://btcbrunchapi.com/ROLLING_" + numOfDaysBtnSelected + "_HNT";
//                System.out.println(selectedURL);
//                //selectedURL = "https://api.binance.com/api/v3/klines?symbol=LTCUSDT&interval=1d&limit=180";
//
//                String finalSelectedURL = selectedURL;
//                JsonArrayRequest objectRequest8 = new JsonArrayRequest(Request.Method.GET,
//                        selectedURL,
//                        null,
//                        new Response.Listener<JSONArray>() {
//                            @Override
//                            public void onResponse(JSONArray response) {
//                                int numOfDaysCrypto;
//
//                                //Log.e("Crypto Ticker Response", response.toString());
//                                BitCoinVsStocksActivity.cryptoValsInOnClick = tickerDataFunction(response);
//                                numOfDaysCrypto = BitCoinVsStocksActivity.cryptoValsInOnClick.size();
//                                ArrayList<Entry> commodityBitCoin = new ArrayList<>();
//                                commoditySelector.selectCommodity(numOfDaysCrypto, stockSpinnerStrVal, bitCoinSpinnerStrVal, BitCoinVsStocksActivity.cryptoValsInOnClick, commodityBitCoin, mpLineChart);
//                            }
//                        },
//                        new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//                                //Log.e("Crypto Response Error", error.toString());
//                            }
//                        }
//                );
//                requestQueue.add(objectRequest8);
        } else if ("THETA".equals(bitCoinSpinnerStrVal)) {
            System.out.println("This is the number of days selected. I can use this to modify the size of the array and grab what I need: " + numOfDaysBtnSelected);

            Cursor returnData = BitCoinVsStocksActivity.mDatabaseHelper.getData();
            returnData.moveToPosition(41);
            String holder = returnData.getString(2);
            holder = holder.replaceAll(",", ""); //Removes the commas from the string
            holder = holder.replaceAll("[\\[\\](){}]", "");
            String[] holderArr = holder.split(" ");

            //Create a subarray from holder array equal to the size you get from numOfDaysBtnSelected
            String[] holderSubArr = Arrays.copyOfRange(holderArr, 365 - numOfDaysBtnSelected, 365);

            int numOfDaysCrypto;
            BitCoinVsStocksActivity.cryptoValsInOnClick = tickerDataFunction2(holderSubArr);
            numOfDaysCrypto = BitCoinVsStocksActivity.cryptoValsInOnClick.size();
            ArrayList<Entry> commodityBitCoin = new ArrayList<>();

            commoditySelector.selectCommodity(numOfDaysCrypto, stockSpinnerStrVal, bitCoinSpinnerStrVal, BitCoinVsStocksActivity.cryptoValsInOnClick, commodityBitCoin, mpLineChart);

            System.out.println("This is the data from the THETA row in the SQLite database: " + holder);
            System.out.println("This is the data from the holderArr: " + holderArr.toString());

            //Start of the Volley call and response object scope
            //Leave these calls for testing or other purposes

//                selectedURL="https://btcbrunchapi.com/ROLLING_" + numOfDaysBtnSelected + "_THETA";
//                System.out.println(selectedURL);
//                //selectedURL = "https://api.binance.com/api/v3/klines?symbol=LTCUSDT&interval=1d&limit=180";
//
//                String finalSelectedURL = selectedURL;
//                JsonArrayRequest objectRequest8 = new JsonArrayRequest(Request.Method.GET,
//                        selectedURL,
//                        null,
//                        new Response.Listener<JSONArray>() {
//                            @Override
//                            public void onResponse(JSONArray response) {
//                                int numOfDaysCrypto;
//
//                                //Log.e("Crypto Ticker Response", response.toString());
//                                BitCoinVsStocksActivity.cryptoValsInOnClick = tickerDataFunction(response);
//                                numOfDaysCrypto = BitCoinVsStocksActivity.cryptoValsInOnClick.size();
//                                ArrayList<Entry> commodityBitCoin = new ArrayList<>();
//                                commoditySelector.selectCommodity(numOfDaysCrypto, stockSpinnerStrVal, bitCoinSpinnerStrVal, BitCoinVsStocksActivity.cryptoValsInOnClick, commodityBitCoin, mpLineChart);
//                            }
//                        },
//                        new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//                                //Log.e("Crypto Response Error", error.toString());
//                            }
//                        }
//                );
//                requestQueue.add(objectRequest8);
        } else if ("VET".equals(bitCoinSpinnerStrVal)) {
            System.out.println("This is the number of days selected. I can use this to modify the size of the array and grab what I need: " + numOfDaysBtnSelected);

            Cursor returnData = BitCoinVsStocksActivity.mDatabaseHelper.getData();
            returnData.moveToPosition(42);
            String holder = returnData.getString(2);
            holder = holder.replaceAll(",", ""); //Removes the commas from the string
            holder = holder.replaceAll("[\\[\\](){}]", "");
            String[] holderArr = holder.split(" ");

            //Create a subarray from holder array equal to the size you get from numOfDaysBtnSelected
            String[] holderSubArr = Arrays.copyOfRange(holderArr, 365 - numOfDaysBtnSelected, 365);

            int numOfDaysCrypto;
            BitCoinVsStocksActivity.cryptoValsInOnClick = tickerDataFunction2(holderSubArr);
            numOfDaysCrypto = BitCoinVsStocksActivity.cryptoValsInOnClick.size();
            ArrayList<Entry> commodityBitCoin = new ArrayList<>();

            commoditySelector.selectCommodity(numOfDaysCrypto, stockSpinnerStrVal, bitCoinSpinnerStrVal, BitCoinVsStocksActivity.cryptoValsInOnClick, commodityBitCoin, mpLineChart);

            System.out.println("This is the data from the VET row in the SQLite database: " + holder);
            System.out.println("This is the data from the holderArr: " + holderArr.toString());

            //Start of the Volley call and response object scope
            //Leave these calls for testing or other purposes

//                selectedURL="https://btcbrunchapi.com/ROLLING_" + numOfDaysBtnSelected + "_VET";
//                System.out.println(selectedURL);
//                //selectedURL = "https://api.binance.com/api/v3/klines?symbol=LTCUSDT&interval=1d&limit=180";
//
//                String finalSelectedURL = selectedURL;
//                JsonArrayRequest objectRequest8 = new JsonArrayRequest(Request.Method.GET,
//                        selectedURL,
//                        null,
//                        new Response.Listener<JSONArray>() {
//                            @Override
//                            public void onResponse(JSONArray response) {
//                                int numOfDaysCrypto;
//
//                                //Log.e("Crypto Ticker Response", response.toString());
//                                BitCoinVsStocksActivity.cryptoValsInOnClick = tickerDataFunction(response);
//                                numOfDaysCrypto = BitCoinVsStocksActivity.cryptoValsInOnClick.size();
//                                ArrayList<Entry> commodityBitCoin = new ArrayList<>();
//                                commoditySelector.selectCommodity(numOfDaysCrypto, stockSpinnerStrVal, bitCoinSpinnerStrVal, BitCoinVsStocksActivity.cryptoValsInOnClick, commodityBitCoin, mpLineChart);
//                            }
//                        },
//                        new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//                                //Log.e("Crypto Response Error", error.toString());
//                            }
//                        }
//                );
//                requestQueue.add(objectRequest8);
        } else if ("XLM".equals(bitCoinSpinnerStrVal)) {
            System.out.println("This is the number of days selected. I can use this to modify the size of the array and grab what I need: " + numOfDaysBtnSelected);

            Cursor returnData = BitCoinVsStocksActivity.mDatabaseHelper.getData();
            returnData.moveToPosition(43);
            String holder = returnData.getString(2);
            holder = holder.replaceAll(",", ""); //Removes the commas from the string
            holder = holder.replaceAll("[\\[\\](){}]", "");
            String[] holderArr = holder.split(" ");

            //Create a subarray from holder array equal to the size you get from numOfDaysBtnSelected
            String[] holderSubArr = Arrays.copyOfRange(holderArr, 365 - numOfDaysBtnSelected, 365);

            int numOfDaysCrypto;
            BitCoinVsStocksActivity.cryptoValsInOnClick = tickerDataFunction2(holderSubArr);
            numOfDaysCrypto = BitCoinVsStocksActivity.cryptoValsInOnClick.size();
            ArrayList<Entry> commodityBitCoin = new ArrayList<>();

            commoditySelector.selectCommodity(numOfDaysCrypto, stockSpinnerStrVal, bitCoinSpinnerStrVal, BitCoinVsStocksActivity.cryptoValsInOnClick, commodityBitCoin, mpLineChart);

            System.out.println("This is the data from the XLM row in the SQLite database: " + holder);
            System.out.println("This is the data from the holderArr: " + holderArr.toString());

            //Start of the Volley call and response object scope
            //Leave these calls for testing or other purposes

//                selectedURL="https://btcbrunchapi.com/ROLLING_" + numOfDaysBtnSelected + "_XLM";
//                System.out.println(selectedURL);
//                //selectedURL = "https://api.binance.com/api/v3/klines?symbol=LTCUSDT&interval=1d&limit=180";
//
//                String finalSelectedURL = selectedURL;
//                JsonArrayRequest objectRequest8 = new JsonArrayRequest(Request.Method.GET,
//                        selectedURL,
//                        null,
//                        new Response.Listener<JSONArray>() {
//                            @Override
//                            public void onResponse(JSONArray response) {
//                                int numOfDaysCrypto;
//
//                                //Log.e("Crypto Ticker Response", response.toString());
//                                BitCoinVsStocksActivity.cryptoValsInOnClick = tickerDataFunction(response);
//                                numOfDaysCrypto = BitCoinVsStocksActivity.cryptoValsInOnClick.size();
//                                ArrayList<Entry> commodityBitCoin = new ArrayList<>();
//                                commoditySelector.selectCommodity(numOfDaysCrypto, stockSpinnerStrVal, bitCoinSpinnerStrVal, BitCoinVsStocksActivity.cryptoValsInOnClick, commodityBitCoin, mpLineChart);
//                            }
//                        },
//                        new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//                                //Log.e("Crypto Response Error", error.toString());
//                            }
//                        }
//                );
//                requestQueue.add(objectRequest8);
        } else if ("ZEN".equals(bitCoinSpinnerStrVal)) {
            System.out.println("This is the number of days selected. I can use this to modify the size of the array and grab what I need: " + numOfDaysBtnSelected);

            Cursor returnData = BitCoinVsStocksActivity.mDatabaseHelper.getData();
            returnData.moveToPosition(44);
            String holder = returnData.getString(2);
            holder = holder.replaceAll(",", ""); //Removes the commas from the string
            holder = holder.replaceAll("[\\[\\](){}]", "");
            String[] holderArr = holder.split(" ");

            //Create a subarray from holder array equal to the size you get from numOfDaysBtnSelected
            String[] holderSubArr = Arrays.copyOfRange(holderArr, 365 - numOfDaysBtnSelected, 365);

            int numOfDaysCrypto;
            BitCoinVsStocksActivity.cryptoValsInOnClick = tickerDataFunction2(holderSubArr);
            numOfDaysCrypto = BitCoinVsStocksActivity.cryptoValsInOnClick.size();
            ArrayList<Entry> commodityBitCoin = new ArrayList<>();

            commoditySelector.selectCommodity(numOfDaysCrypto, stockSpinnerStrVal, bitCoinSpinnerStrVal, BitCoinVsStocksActivity.cryptoValsInOnClick, commodityBitCoin, mpLineChart);

            System.out.println("This is the data from the ZEN row in the SQLite database: " + holder);
            System.out.println("This is the data from the holderArr: " + holderArr.toString());

            //Start of the Volley call and response object scope
            //Leave these calls for testing or other purposes

//                selectedURL="https://btcbrunchapi.com/ROLLING_" + numOfDaysBtnSelected + "_ZEN";
//                System.out.println(selectedURL);
//                //selectedURL = "https://api.binance.com/api/v3/klines?symbol=LTCUSDT&interval=1d&limit=180";
//
//                String finalSelectedURL = selectedURL;
//                JsonArrayRequest objectRequest8 = new JsonArrayRequest(Request.Method.GET,
//                        selectedURL,
//                        null,
//                        new Response.Listener<JSONArray>() {
//                            @Override
//                            public void onResponse(JSONArray response) {
//                                int numOfDaysCrypto;
//
//                                //Log.e("Crypto Ticker Response", response.toString());
//                                BitCoinVsStocksActivity.cryptoValsInOnClick = tickerDataFunction(response);
//                                numOfDaysCrypto = BitCoinVsStocksActivity.cryptoValsInOnClick.size();
//                                ArrayList<Entry> commodityBitCoin = new ArrayList<>();
//                                commoditySelector.selectCommodity(numOfDaysCrypto, stockSpinnerStrVal, bitCoinSpinnerStrVal, BitCoinVsStocksActivity.cryptoValsInOnClick, commodityBitCoin, mpLineChart);
//                            }
//                        },
//                        new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//                                //Log.e("Crypto Response Error", error.toString());
//                            }
//                        }
//                );
//                requestQueue.add(objectRequest8);
        } else if ("FIL".equals(bitCoinSpinnerStrVal)) {
            System.out.println("This is the number of days selected. I can use this to modify the size of the array and grab what I need: " + numOfDaysBtnSelected);

            Cursor returnData = BitCoinVsStocksActivity.mDatabaseHelper.getData();
            returnData.moveToPosition(45);
            String holder = returnData.getString(2);
            holder = holder.replaceAll(",", ""); //Removes the commas from the string
            holder = holder.replaceAll("[\\[\\](){}]", "");
            String[] holderArr = holder.split(" ");

            //Create a subarray from holder array equal to the size you get from numOfDaysBtnSelected
            String[] holderSubArr = Arrays.copyOfRange(holderArr, 365 - numOfDaysBtnSelected, 365);

            int numOfDaysCrypto;
            BitCoinVsStocksActivity.cryptoValsInOnClick = tickerDataFunction2(holderSubArr);
            numOfDaysCrypto = BitCoinVsStocksActivity.cryptoValsInOnClick.size();
            ArrayList<Entry> commodityBitCoin = new ArrayList<>();

            commoditySelector.selectCommodity(numOfDaysCrypto, stockSpinnerStrVal, bitCoinSpinnerStrVal, BitCoinVsStocksActivity.cryptoValsInOnClick, commodityBitCoin, mpLineChart);

            System.out.println("This is the data from the FIL row in the SQLite database: " + holder);
            System.out.println("This is the data from the holderArr: " + holderArr.toString());

            //Start of the Volley call and response object scope
            //Leave these calls for testing or other purposes

//                selectedURL="https://btcbrunchapi.com/ROLLING_" + numOfDaysBtnSelected + "_FIL";
//                System.out.println(selectedURL);
//                //selectedURL = "https://api.binance.com/api/v3/klines?symbol=LTCUSDT&interval=1d&limit=180";
//
//                String finalSelectedURL = selectedURL;
//                JsonArrayRequest objectRequest8 = new JsonArrayRequest(Request.Method.GET,
//                        selectedURL,
//                        null,
//                        new Response.Listener<JSONArray>() {
//                            @Override
//                            public void onResponse(JSONArray response) {
//                                int numOfDaysCrypto;
//
//                                //Log.e("Crypto Ticker Response", response.toString());
//                                BitCoinVsStocksActivity.cryptoValsInOnClick = tickerDataFunction(response);
//                                numOfDaysCrypto = BitCoinVsStocksActivity.cryptoValsInOnClick.size();
//                                ArrayList<Entry> commodityBitCoin = new ArrayList<>();
//                                commoditySelector.selectCommodity(numOfDaysCrypto, stockSpinnerStrVal, bitCoinSpinnerStrVal, BitCoinVsStocksActivity.cryptoValsInOnClick, commodityBitCoin, mpLineChart);
//                            }
//                        },
//                        new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//                                //Log.e("Crypto Response Error", error.toString());
//                            }
//                        }
//                );
//                requestQueue.add(objectRequest8);
        } else if ("ZIL".equals(bitCoinSpinnerStrVal)) {
            System.out.println("This is the number of days selected. I can use this to modify the size of the array and grab what I need: " + numOfDaysBtnSelected);

            Cursor returnData = BitCoinVsStocksActivity.mDatabaseHelper.getData();
            returnData.moveToPosition(46);
            String holder = returnData.getString(2);
            holder = holder.replaceAll(",", ""); //Removes the commas from the string
            holder = holder.replaceAll("[\\[\\](){}]", "");
            String[] holderArr = holder.split(" ");

            //Create a subarray from holder array equal to the size you get from numOfDaysBtnSelected
            String[] holderSubArr = Arrays.copyOfRange(holderArr, 365 - numOfDaysBtnSelected, 365);

            int numOfDaysCrypto;
            BitCoinVsStocksActivity.cryptoValsInOnClick = tickerDataFunction2(holderSubArr);
            numOfDaysCrypto = BitCoinVsStocksActivity.cryptoValsInOnClick.size();
            ArrayList<Entry> commodityBitCoin = new ArrayList<>();

            commoditySelector.selectCommodity(numOfDaysCrypto, stockSpinnerStrVal, bitCoinSpinnerStrVal, BitCoinVsStocksActivity.cryptoValsInOnClick, commodityBitCoin, mpLineChart);

            System.out.println("This is the data from the ZIL row in the SQLite database: " + holder);
            System.out.println("This is the data from the holderArr: " + holderArr.toString());

            //Start of the Volley call and response object scope
            //Leave these calls for testing or other purposes

//                selectedURL="https://btcbrunchapi.com/ROLLING_" + numOfDaysBtnSelected + "_ZIL";
//                System.out.println(selectedURL);
//                //selectedURL = "https://api.binance.com/api/v3/klines?symbol=LTCUSDT&interval=1d&limit=180";
//
//                String finalSelectedURL = selectedURL;
//                JsonArrayRequest objectRequest8 = new JsonArrayRequest(Request.Method.GET,
//                        selectedURL,
//                        null,
//                        new Response.Listener<JSONArray>() {
//                            @Override
//                            public void onResponse(JSONArray response) {
//                                int numOfDaysCrypto;
//
//                                //Log.e("Crypto Ticker Response", response.toString());
//                                BitCoinVsStocksActivity.cryptoValsInOnClick = tickerDataFunction(response);
//                                numOfDaysCrypto = BitCoinVsStocksActivity.cryptoValsInOnClick.size();
//                                ArrayList<Entry> commodityBitCoin = new ArrayList<>();
//                                commoditySelector.selectCommodity(numOfDaysCrypto, stockSpinnerStrVal, bitCoinSpinnerStrVal, BitCoinVsStocksActivity.cryptoValsInOnClick, commodityBitCoin, mpLineChart);
//                            }
//                        },
//                        new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//                                //Log.e("Crypto Response Error", error.toString());
//                            }
//                        }
//                );
//                requestQueue.add(objectRequest8);
        } else if ("WAVES".equals(bitCoinSpinnerStrVal)) {
            System.out.println("This is the number of days selected. I can use this to modify the size of the array and grab what I need: " + numOfDaysBtnSelected);

            Cursor returnData = BitCoinVsStocksActivity.mDatabaseHelper.getData();
            returnData.moveToPosition(47);
            String holder = returnData.getString(2);
            holder = holder.replaceAll(",", ""); //Removes the commas from the string
            holder = holder.replaceAll("[\\[\\](){}]", "");
            String[] holderArr = holder.split(" ");

            //Create a subarray from holder array equal to the size you get from numOfDaysBtnSelected
            String[] holderSubArr = Arrays.copyOfRange(holderArr, 365 - numOfDaysBtnSelected, 365);

            int numOfDaysCrypto;
            BitCoinVsStocksActivity.cryptoValsInOnClick = tickerDataFunction2(holderSubArr);
            numOfDaysCrypto = BitCoinVsStocksActivity.cryptoValsInOnClick.size();
            ArrayList<Entry> commodityBitCoin = new ArrayList<>();

            commoditySelector.selectCommodity(numOfDaysCrypto, stockSpinnerStrVal, bitCoinSpinnerStrVal, BitCoinVsStocksActivity.cryptoValsInOnClick, commodityBitCoin, mpLineChart);

            System.out.println("This is the data from the WAVES row in the SQLite database: " + holder);
            System.out.println("This is the data from the holderArr: " + holderArr.toString());

            //Start of the Volley call and response object scope
            //Leave these calls for testing or other purposes

//                selectedURL="https://btcbrunchapi.com/ROLLING_" + numOfDaysBtnSelected + "_WAVES";
//                System.out.println(selectedURL);
//                //selectedURL = "https://api.binance.com/api/v3/klines?symbol=LTCUSDT&interval=1d&limit=180";
//
//                String finalSelectedURL = selectedURL;
//                JsonArrayRequest objectRequest8 = new JsonArrayRequest(Request.Method.GET,
//                        selectedURL,
//                        null,
//                        new Response.Listener<JSONArray>() {
//                            @Override
//                            public void onResponse(JSONArray response) {
//                                int numOfDaysCrypto;
//
//                                //Log.e("Crypto Ticker Response", response.toString());
//                                BitCoinVsStocksActivity.cryptoValsInOnClick = tickerDataFunction(response);
//                                numOfDaysCrypto = BitCoinVsStocksActivity.cryptoValsInOnClick.size();
//                                ArrayList<Entry> commodityBitCoin = new ArrayList<>();
//                                commoditySelector.selectCommodity(numOfDaysCrypto, stockSpinnerStrVal, bitCoinSpinnerStrVal, BitCoinVsStocksActivity.cryptoValsInOnClick, commodityBitCoin, mpLineChart);
//                            }
//                        },
//                        new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//                                //Log.e("Crypto Response Error", error.toString());
//                            }
//                        }
//                );
//                requestQueue.add(objectRequest8);
        } else if ("EOS".equals(bitCoinSpinnerStrVal)) {
            System.out.println("This is the number of days selected. I can use this to modify the size of the array and grab what I need: " + numOfDaysBtnSelected);

            Cursor returnData = BitCoinVsStocksActivity.mDatabaseHelper.getData();
            returnData.moveToPosition(48);
            String holder = returnData.getString(2);
            holder = holder.replaceAll(",", ""); //Removes the commas from the string
            holder = holder.replaceAll("[\\[\\](){}]", "");
            String[] holderArr = holder.split(" ");

            //Create a subarray from holder array equal to the size you get from numOfDaysBtnSelected
            String[] holderSubArr = Arrays.copyOfRange(holderArr, 365 - numOfDaysBtnSelected, 365);

            int numOfDaysCrypto;
            BitCoinVsStocksActivity.cryptoValsInOnClick = tickerDataFunction2(holderSubArr);
            numOfDaysCrypto = BitCoinVsStocksActivity.cryptoValsInOnClick.size();
            ArrayList<Entry> commodityBitCoin = new ArrayList<>();

            commoditySelector.selectCommodity(numOfDaysCrypto, stockSpinnerStrVal, bitCoinSpinnerStrVal, BitCoinVsStocksActivity.cryptoValsInOnClick, commodityBitCoin, mpLineChart);

            System.out.println("This is the data from the EOS row in the SQLite database: " + holder);
            System.out.println("This is the data from the holderArr: " + holderArr.toString());

            //Start of the Volley call and response object scope
            //Leave these calls for testing or other purposes

//                selectedURL="https://btcbrunchapi.com/ROLLING_" + numOfDaysBtnSelected + "_EOS";
//                System.out.println(selectedURL);
//                //selectedURL = "https://api.binance.com/api/v3/klines?symbol=LTCUSDT&interval=1d&limit=180";
//
//                String finalSelectedURL = selectedURL;
//                JsonArrayRequest objectRequest8 = new JsonArrayRequest(Request.Method.GET,
//                        selectedURL,
//                        null,
//                        new Response.Listener<JSONArray>() {
//                            @Override
//                            public void onResponse(JSONArray response) {
//                                int numOfDaysCrypto;
//
//                                //Log.e("Crypto Ticker Response", response.toString());
//                                BitCoinVsStocksActivity.cryptoValsInOnClick = tickerDataFunction(response);
//                                numOfDaysCrypto = BitCoinVsStocksActivity.cryptoValsInOnClick.size();
//                                ArrayList<Entry> commodityBitCoin = new ArrayList<>();
//                                commoditySelector.selectCommodity(numOfDaysCrypto, stockSpinnerStrVal, bitCoinSpinnerStrVal, BitCoinVsStocksActivity.cryptoValsInOnClick, commodityBitCoin, mpLineChart);
//                            }
//                        },
//                        new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//                                //Log.e("Crypto Response Error", error.toString());
//                            }
//                        }
//                );
//                requestQueue.add(objectRequest8);
        } else if ("AAVE".equals(bitCoinSpinnerStrVal)) {
            System.out.println("This is the number of days selected. I can use this to modify the size of the array and grab what I need: " + numOfDaysBtnSelected);

            Cursor returnData = BitCoinVsStocksActivity.mDatabaseHelper.getData();
            returnData.moveToPosition(49);
            String holder = returnData.getString(2);
            holder = holder.replaceAll(",", ""); //Removes the commas from the string
            holder = holder.replaceAll("[\\[\\](){}]", "");
            String[] holderArr = holder.split(" ");

            //Create a subarray from holder array equal to the size you get from numOfDaysBtnSelected
            String[] holderSubArr = Arrays.copyOfRange(holderArr, 365 - numOfDaysBtnSelected, 365);

            int numOfDaysCrypto;
            BitCoinVsStocksActivity.cryptoValsInOnClick = tickerDataFunction2(holderSubArr);
            numOfDaysCrypto = BitCoinVsStocksActivity.cryptoValsInOnClick.size();
            ArrayList<Entry> commodityBitCoin = new ArrayList<>();

            commoditySelector.selectCommodity(numOfDaysCrypto, stockSpinnerStrVal, bitCoinSpinnerStrVal, BitCoinVsStocksActivity.cryptoValsInOnClick, commodityBitCoin, mpLineChart);

            System.out.println("This is the data from the ETH row in the SQLite database: " + holder);
            System.out.println("This is the data from the holderArr: " + holderArr.toString());

            //Start of the Volley call and response object scope
            //Leave these calls for testing or other purposes

//                selectedURL="https://btcbrunchapi.com/ROLLING_" + numOfDaysBtnSelected + "_AAVE";
//                System.out.println(selectedURL);
//                //selectedURL = "https://api.binance.com/api/v3/klines?symbol=LTCUSDT&interval=1d&limit=180";
//
//                String finalSelectedURL = selectedURL;
//                JsonArrayRequest objectRequest8 = new JsonArrayRequest(Request.Method.GET,
//                        selectedURL,
//                        null,
//                        new Response.Listener<JSONArray>() {
//                            @Override
//                            public void onResponse(JSONArray response) {
//                                int numOfDaysCrypto;
//
//                                //Log.e("Crypto Ticker Response", response.toString());
//                                BitCoinVsStocksActivity.cryptoValsInOnClick = tickerDataFunction(response);
//                                numOfDaysCrypto = BitCoinVsStocksActivity.cryptoValsInOnClick.size();
//                                ArrayList<Entry> commodityBitCoin = new ArrayList<>();
//                                commoditySelector.selectCommodity(numOfDaysCrypto, stockSpinnerStrVal, bitCoinSpinnerStrVal, BitCoinVsStocksActivity.cryptoValsInOnClick, commodityBitCoin, mpLineChart);
//                            }
//                        },
//                        new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//                                //Log.e("Crypto Response Error", error.toString());
//                            }
//                        }
//                );
//                requestQueue.add(objectRequest8);
        } else if ("ENJ".equals(bitCoinSpinnerStrVal)) {
            System.out.println("This is the number of days selected. I can use this to modify the size of the array and grab what I need: " + numOfDaysBtnSelected);

            Cursor returnData = BitCoinVsStocksActivity.mDatabaseHelper.getData();
            returnData.moveToPosition(50);
            String holder = returnData.getString(2);
            holder = holder.replaceAll(",", ""); //Removes the commas from the string
            holder = holder.replaceAll("[\\[\\](){}]", "");
            String[] holderArr = holder.split(" ");

            //Create a subarray from holder array equal to the size you get from numOfDaysBtnSelected
            String[] holderSubArr = Arrays.copyOfRange(holderArr, 365 - numOfDaysBtnSelected, 365);

            int numOfDaysCrypto;
            BitCoinVsStocksActivity.cryptoValsInOnClick = tickerDataFunction2(holderSubArr);
            numOfDaysCrypto = BitCoinVsStocksActivity.cryptoValsInOnClick.size();
            ArrayList<Entry> commodityBitCoin = new ArrayList<>();

            commoditySelector.selectCommodity(numOfDaysCrypto, stockSpinnerStrVal, bitCoinSpinnerStrVal, BitCoinVsStocksActivity.cryptoValsInOnClick, commodityBitCoin, mpLineChart);

            System.out.println("This is the data from the ENJ row in the SQLite database: " + holder);
            System.out.println("This is the data from the holderArr: " + holderArr.toString());

            //Start of the Volley call and response object scope
            //Leave these calls for testing or other purposes

//                selectedURL="https://btcbrunchapi.com/ROLLING_" + numOfDaysBtnSelected + "_ENJ";
//                System.out.println(selectedURL);
//                //selectedURL = "https://api.binance.com/api/v3/klines?symbol=LTCUSDT&interval=1d&limit=180";
//
//                String finalSelectedURL = selectedURL;
//                JsonArrayRequest objectRequest8 = new JsonArrayRequest(Request.Method.GET,
//                        selectedURL,
//                        null,
//                        new Response.Listener<JSONArray>() {
//                            @Override
//                            public void onResponse(JSONArray response) {
//                                int numOfDaysCrypto;
//
//                                //Log.e("Crypto Ticker Response", response.toString());
//                                BitCoinVsStocksActivity.cryptoValsInOnClick = tickerDataFunction(response);
//                                numOfDaysCrypto = BitCoinVsStocksActivity.cryptoValsInOnClick.size();
//                                ArrayList<Entry> commodityBitCoin = new ArrayList<>();
//                                commoditySelector.selectCommodity(numOfDaysCrypto, stockSpinnerStrVal, bitCoinSpinnerStrVal, BitCoinVsStocksActivity.cryptoValsInOnClick, commodityBitCoin, mpLineChart);
//                            }
//                        },
//                        new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//                                //Log.e("Crypto Response Error", error.toString());
//                            }
//                        }
//                );
//                requestQueue.add(objectRequest8);
        } else {
            throw new IllegalStateException("Unexpected value: " + bitCoinSpinnerStrVal);
        }
    }

    private ArrayList<Candlestick> tickerDataFunction(JSONArray response) {

        //Clears the array every time so the old data doesn't get used in plotting the chart
        BitCoinVsStocksActivity.candlesticks.clear();

        //ArrayList<CandleEntry> candleVals = new ArrayList<>();
        try {

            for (int idx = 0; idx <= response.length() - 1; idx++) {
                JSONArray candlestickData = response.getJSONArray(idx);
                long data0 = (long) candlestickData.get(0);
                String data1 = (String) candlestickData.get(1);
                String data2 = (String) candlestickData.get(2);
                String data3 = (String) candlestickData.get(3);
                String data4 = (String) candlestickData.get(4);
//                System.out.println("This is the data string from the response in the tickerDataFunction() :" + data1 );
//                System.out.println("This is the data string from the response in the tickerDataFunction() :" + data2 );
//                System.out.println("This is the data string from the response in the tickerDataFunction() :" + data3 );
//                System.out.println("This is the data string from the response in the tickerDataFunction() :" + data4 );

                long convertedData0 = data0;
                float convertedData1 = Float.parseFloat(data1);
                float convertedData2 = Float.parseFloat(data2);
                float convertedData3 = Float.parseFloat(data3);
                float convertedData4 = Float.parseFloat(data4);


                Candlestick candlestick = new Candlestick(convertedData0, convertedData1, convertedData2, convertedData3, convertedData4);
                BitCoinVsStocksActivity.candlesticks.add(candlestick);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return BitCoinVsStocksActivity.candlesticks;
    }

    private ArrayList<Candlestick> tickerDataFunction2(String[] cryptArr) {
        BitCoinVsStocksActivity.candlesticks.clear();
        try {

            for (int idx = 0; idx <= cryptArr.length - 1; idx++) {
//                JSONArray candlestickData = response.getJSONArray(idx);
                long data0 = (long) idx;
                String data1 = (String) Array.get(cryptArr, idx);
                String data2 = (String) Array.get(cryptArr, idx);
                String data3 = (String) Array.get(cryptArr, idx);
                String data4 = (String) Array.get(cryptArr, idx);
//                System.out.println("This is the data string from the response in the tickerDataFunction() :" + data1 );
//                System.out.println("This is the data string from the response in the tickerDataFunction() :" + data2 );
//                System.out.println("This is the data string from the response in the tickerDataFunction() :" + data3 );
//                System.out.println("This is the data string from the response in the tickerDataFunction() :" + data4 );

                long convertedData0 = data0;
                float convertedData1 = Float.parseFloat(data1);
                float convertedData2 = Float.parseFloat(data2);
                float convertedData3 = Float.parseFloat(data3);
                float convertedData4 = Float.parseFloat(data4);


                Candlestick candlestick = new Candlestick(convertedData0, convertedData1, convertedData2, convertedData3, convertedData4);
                BitCoinVsStocksActivity.candlesticks.add(candlestick);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return BitCoinVsStocksActivity.candlesticks;
    }
}
