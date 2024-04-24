package com.btcbrunch;

import static com.btcbrunch.BitCoinVsStocksActivity.mpLineChart;

import android.graphics.Color;
import android.widget.TextView;

import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

public class ChartMaker {

    int numOfDaysCrypto;
    String stockSpinnerStrVal;
    String bitCoinSpinnerStrVal;
    ArrayList<Candlestick> cryptoValsInOnClick;
    ArrayList<Entry> commodityBitCoin;
    //This will take an argument in the constructor like cornVals
    ArrayList<Entry> selectedCommodityVals;

    //I think this will import the mpLineChart from the main Activity...
//    LineChart mpLineChart;
    ArrayList<Float> cryptoPriceArray = new ArrayList<>();
    ArrayList<Float> commodityPriceArray = new ArrayList<>();
    TextView commodityValTextView;
    TextView cryptoValTextView;

    ChartMaker(int numOfDaysCrypto,
               String stockSpinnerStrVal,
               String bitCoinSpinnerStrVal,
               ArrayList<Candlestick> cryptoValsInOnClick,
               ArrayList<Entry> commodityBitCoin,
               ArrayList<Entry> selectedCommodityVals
              ) {

        this.numOfDaysCrypto = numOfDaysCrypto;
        this.stockSpinnerStrVal = stockSpinnerStrVal;
        this.bitCoinSpinnerStrVal = bitCoinSpinnerStrVal;
        this.cryptoValsInOnClick = cryptoValsInOnClick;
        this.commodityBitCoin = commodityBitCoin;
        this.selectedCommodityVals = selectedCommodityVals;
    }

    //contains the code that was in the case. Populates the chart
    public void populateChart() {

        for(int i = 0; i <= numOfDaysCrypto - 1; i++) {
            float result;
            float cryptoPrice = cryptoValsInOnClick.get(i).getOpen();
            float cornPrice = this.selectedCommodityVals.get(i).getY();
            result = cornPrice / cryptoPrice;
            commodityBitCoin.add(new Entry(i, result));

            //Loads the cryptoPriceArray
            BitCoinVsStocksActivity.cryptoPriceArray.add(i, cryptoValsInOnClick.get(i).getOpen());
            //Loads the commodityPriceArray
            BitCoinVsStocksActivity.commodityPriceArray.add(i, this.selectedCommodityVals.get(i).getY());

            LineDataSet lineDataSet1 = new LineDataSet(commodityBitCoin, stockSpinnerStrVal + " / " + bitCoinSpinnerStrVal + " " + numOfDaysCrypto + " " + "Day Comparison");
            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(lineDataSet1);

            LineData data = new LineData(dataSets);
            mpLineChart.setBackgroundColor(Color.TRANSPARENT);
            lineDataSet1.setLineWidth(1);
            lineDataSet1.setColor(Color.parseColor("#FE752F"));
            lineDataSet1.setDrawCircles(true);
            lineDataSet1.setDrawCircleHole(true);
            lineDataSet1.setCircleColor(Color.WHITE);
            lineDataSet1.setCircleHoleColor(Color.parseColor("#FE752F"));
            lineDataSet1.setCircleRadius(2);
            lineDataSet1.setCircleHoleRadius(1);
            XAxis xAxis = mpLineChart.getXAxis();
            YAxis yAxisLeft = mpLineChart.getAxisLeft();
            YAxis yAxisRight = mpLineChart.getAxisRight();
            mpLineChart.getDescription().setTextColor(Color.WHITE);
//            The text for Description shows up in the bottom right corner inside the chart
//            Could be useful for giving other APIs credit for data if necessary
//            Description description = mpLineChart.getDescription();
//            description.setText(this.stockSpinnerStrVal);
//            description.setTextSize(12);
//            description.setTextColor(Color.LTGRAY);
//            mpLineChart.setDescription(description);

            xAxis.setTextColor(Color.WHITE);
            yAxisLeft.setTextColor(Color.WHITE);
            yAxisRight.setTextColor(Color.WHITE);
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

            Legend legend = mpLineChart.getLegend();
            legend.setTextColor(Color.LTGRAY);

            lineDataSet1.setValueTextSize(0);
            lineDataSet1.setValueTextColor(Color.LTGRAY);
//          mpLineChart.setNoDataText("Querying Database for " + stockSpinnerStrVal + " / " + bitCoinSpinnerStrVal + "data.");
            mpLineChart.setData(data);
            mpLineChart.invalidate();

        }
    }
//
//    @Override
//    public void onValueSelected(Entry e, Highlight h) {
//
//        System.out.println(BitCoinVsStocksActivity.commodityPriceArray.get((int) e.getX()));
//        System.out.println(BitCoinVsStocksActivity.cryptoPriceArray.get((int) e.getX()));
//
////        Context context = null;
////        Context context = null;
////        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
////        View v = inflater.inflate(R.layout.activity_bit_coin_vs_stocks, null);
////        commodityValTextView = (TextView) v.findViewById(R.id.commodity_text_view_value);
////        cryptoValTextView = (TextView) v.findViewById(R.id.crypto_text_view_value);
////        System.out.println("I'm over here now! :" + commodityValTextView.toString());
////        System.out.println("Oh! :" + cryptoValTextView.toString());
//
//    }
//
//    @Override
//    public void onNothingSelected() {
//
//    }
}//end of the ChartMaker class
