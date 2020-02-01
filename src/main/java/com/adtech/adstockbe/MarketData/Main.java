package com.adtech.adstockbe.MarketData;

import com.adtech.adstockbe.model.Stock;
import com.adtech.adstockbe.model.UpstoxRes;
import com.adtech.adstockbe.service.FetchData;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String abc="4741497225";
        Integer ced = Integer.valueOf(abc);
        System.out.println(ced);

        //long time= Long.parseLong("1580542500000");
        long time=0;

        List<Stock> stockList=new ArrayList<>();
        String[] comList={"IDBI","PGHH","TCS","CASTROLIND","DIVISLAB","BAJAJHLDNG","AJANTPHARM","HINDUNILVR","EMAMILTD","NESTLEIND","LTI","TECHM","UBL","FRETAIL","CUB","MCDOWELL-N","RAJESHEXPO","JUBLFOOD","WIPRO","INFY","DRREDDY","SYNGENE","HCLTECH","VBL","ALKEM","OFSS","ENGINERSIN","CIPLA","NATCOPHARM","MINDTREE","INFRATEL","BATAINDIA","BHARTIARTL","IGL","HINDZINC","ICICIGI","MGL","APOLLOHOSP","COLPAL","NATIONALUM","BBTC","LTTS","APOLLOTYRE","VOLTAS","IDEA","VGUARD","HAVELLS","BAJAJ-AUTO","MRF","TITAN","TORNTPHARM","FORTIS","COROMANDEL","JSWENERGY","GODREJIND","ENDURANCE","MUTHOOTFIN","PETRONET","TVSMOTOR","ABFRL","EICHERMOT","DMART","TORNTPOWER","LUPIN","RAMCOCEM","GODREJCP","WHIRLPOOL","MPHASIS","BIOCON","MANAPPURAM","PIDILITIND","BERGEPAINT","BOSCHLTD","ADANIPORTS","CROMPTON","ASIANPAINT","QUESS","MARUTI","TATAGLOBAL","ADANIPOWER","BAJFINANCE","PFIZER","KOTAKBANK","JSWSTEEL","CUMMINSIND","GRASIM","AUBANK","BANDHANBNK","PNB","CHOLAFIN","NHPC","CADILAHC","DABUR","VEDL","HINDALCO","POWERGRID","INDIGO","SUNPHARMA","SRF","HDFCBANK","RELIANCE","GLENMARK","GODREJAGRO","RECLTD","GAIL","EXIDEIND","NTPC","CONCOR","AXISBANK","BALKRISIND","NBCC","MARICO","AMARAJABAT","SHREECEM","INDIANB","INDHOTEL","HEROMOTOCO","CESC","BHARATFORG","GRAPHITE","GSPL","AUROPHARMA","FEDERALBNK","FCONSUMER","BANKINDIA","UPL","PAGEIND","YESBANK","SRTRANSFIN","DALBHARAT","BRITANNIA","ESCORTS","UNIONBANK","INDUSINDBK","HDFCAMC","TATASTEEL","BPCL","IOC","IDFCFIRSTB","ULTRACEMCO","ICICIBANK","VARROC","HEXAWARE","M&M","STRTECH","IPCALAB","GICRE","OIL","M&MFIN","MOTHERSUMI","COALINDIA","ABCAPITAL","AMBUJACEM","HINDPETRO","ONGC","HEG","BANKBARODA","HDFCLIFE","SAIL","MRPL","SBIN","IBVENTURES","OBEROIRLTY","JINDALSTEL","EDELWEISS","ACC","TATAPOWER","BAJAJFINSV","SIEMENS","NAUKRI","PIIND","BEL","ASHOKLEY","ZEEL","LT","PFC","PEL","HUDCO","CANBK","HDFC","TATAMOTORS","RBLBANK","L&TFH","PNBHOUSING","ITC","NAM-INDIA","BHEL","JUBILANT","LICHSGFIN","SUNTV","SBILIFE","TATAMTRDVR","GMRINFRA","NIACL","GODREJPROP","PRESTIGE","NMDC","IBULHSGFIN","ICICIPRULI","DLF","MFSL"};
        String[] durationList={"1","30","day","week","month"};


        for (String com:comList) {
            for (String duration:durationList) {
                String sName=com;
                String timeInterval=duration;
                String timestamp = time==0 ? "" : String.valueOf(time);

                FetchData fetchData=new FetchData();
                List<String> stringList=fetchData.fetchData(sName,timeInterval, timestamp);

                List<UpstoxRes> upstoxResList=new ArrayList<>();
                stringList.forEach(x->{
                    UpstoxRes upstoxRes=new UpstoxRes();
                    upstoxRes.setTimestamp(Long.valueOf(x.split(",")[0].trim()));
                    upstoxRes.setTime(new Timestamp(Long.valueOf(x.split(",")[0].trim())).toString());
                    upstoxRes.setOpenPrice(BigDecimal.valueOf(Double.parseDouble(x.split(",")[1].trim())));
                    upstoxRes.setHighPrice(BigDecimal.valueOf(Double.parseDouble(x.split(",")[2].trim())));
                    upstoxRes.setLowPrice(BigDecimal.valueOf(Double.parseDouble(x.split(",")[3].trim())));
                    upstoxRes.setClosePrice(BigDecimal.valueOf(Double.parseDouble(x.split(",")[4].trim())));
                    upstoxRes.setVolume(Integer.valueOf(x.split(",")[5].replace(".0","").trim()));

                    upstoxResList.add(upstoxRes);
                });

                Stock stock=new Stock();
                stock.setStockName(sName);
                stock.setDetails("Is a Company");
                stock.setUpstoxResList(upstoxResList);

            /*
            stock.getUpstoxResList().forEach(x->{
                System.out.println(x.getTime()+"=>>>  "+x.getOpenPrice()+"       Vol: "+x.getVolume());
            });
            */

                stockList.add(stock);
            }
        }


        System.out.println(stockList);
    }
}
