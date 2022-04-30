package com.test.assignment.utils;

import com.main.assignment.api.CurrencyMapObject;
import com.main.assignment.utils.ConfigProvider;
import com.main.assignment.utils.HttpMehodCall;
import com.main.assignment.utils.RestAPIAuthToken;
import com.main.assignment.utils.RestAPIUtilities;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class APIOperations {
    public static String token;
    public static String BaseURL;
    public static String endPointUrl;
    public static HashMap<String, String> headerinfo;
    public static HashMap<String, Object> qParam;
    RestAPIAuthToken AuthToken = new RestAPIAuthToken();
    RestAPIUtilities restUtil = new RestAPIUtilities();
    public static CurrencyMapObject currencyMap;
    public static ArrayList<HashMap> currancyMapList;
    public static String coinId;

    public void getToken() throws IOException {
        token = ConfigProvider.getString("API_Token");
        AuthToken.setToken(token);
    }

    public void setValidUrl(String URI) throws IOException {
        BaseURL = ConfigProvider.getString("API_Base_URL");
        endPointUrl = URI;
        System.out.println("Final URI :: " + BaseURL + endPointUrl);
        restUtil.setBaseUrl(BaseURL);
    }

    public void setValidHeaderForCurrencyMap(String queryParam) {
        headerinfo = new HashMap<>();
        headerinfo.put("X-CMC_PRO_API_KEY", token);
        headerinfo.put("Accept", "application/json");
        qParam = new HashMap<>();
        qParam.put("symbol", queryParam);
    }

    public void setValidHeaderForPriceConversion(String queryParam) {
        headerinfo = new HashMap<>();
        headerinfo.put("X-CMC_PRO_API_KEY", token);
        headerinfo.put("Accept", "application/json");
        qParam = new HashMap<>();
        for (int i = 0; i < currancyMapList.size(); i++) {
            if (currancyMapList.get(i).get("symbol").equals(queryParam)) {
                qParam.put("id", currancyMapList.get(i).get("id"));
            }
        }
    }

    public void setValidHeaderForInfo(String queryParam) {
        coinId = queryParam;
        headerinfo = new HashMap<>();
        headerinfo.put("X-CMC_PRO_API_KEY", token);
        headerinfo.put("Accept", "application/json");
        qParam = new HashMap<>();
        qParam.put("id", queryParam);
    }

    public void setValidHeaderForPriceConversionTo(String queryParam) {
        qParam.put("convert", queryParam);
    }

    public void setValidHeaderForPriceConversionTotheNumberOfCurrency(String queryParam) {
        qParam.put("amount", queryParam);
    }

    public void triggetApiCall(String methodCall) {
        restUtil.execute(endPointUrl, HttpMehodCall.valueOf(methodCall), headerinfo, qParam);
    }

    public void verifyStatusCode(String code) {

        Assert.assertTrue(restUtil.isStatusValid(Integer.parseInt(code)));
    }

    public void getIdsForCurrencies() throws IOException {

        currancyMapList = RestAPIUtilities.response.getBody().jsonPath().get("data");
        for (int i = 0; i < currancyMapList.size(); i++) {
            System.out.println(currancyMapList.get(i).get("symbol") + "::" + currancyMapList.get(i).get("id"));
        }

    }

    public void printConvertedValueonScreen() throws IOException {
        JsonPath path = RestAPIUtilities.response.getBody().jsonPath();
        System.out.println("Price of " + path.get("data.symbol") + " in Bolivian Boliviano " + "::" + path.get("data.quote.BOB.price"));
    }

    public void verify_a_coin_details(String key, String value) {
        JsonPath pathEvaluator = RestAPIUtilities.response.getBody().jsonPath();
        String jsonPath;
        switch (key) {
            case "logo":
                jsonPath = "data." + coinId + ".logo";
                Assert.assertEquals(pathEvaluator.get(jsonPath), value);
                System.out.println("Actual :: " + pathEvaluator.get(jsonPath));
                System.out.println("Expected :: " + value);
                break;
            case "technical_doc":
                jsonPath = "data." + coinId + ".urls.technical_doc";
                Assert.assertEquals(pathEvaluator.get(jsonPath).toString(), value);
                System.out.println("Actual :: " + pathEvaluator.get(jsonPath).toString());
                System.out.println("Expected :: " + value);
                break;
            case "symbol":
                jsonPath = "data." + coinId + ".symbol";
                Assert.assertEquals(pathEvaluator.get(jsonPath), value);
                System.out.println("Actual :: " + pathEvaluator.get(jsonPath));
                System.out.println("Expected :: " + value);
                break;
            case "date_added":
                jsonPath = "data." + coinId + ".date_added";
                Assert.assertEquals(pathEvaluator.get(jsonPath), value);
                System.out.println("Actual :: " + pathEvaluator.get(jsonPath));
                System.out.println("Expected :: " + value);
                break;
            case "tags":
                jsonPath = "data." + coinId + ".tags";
                Assert.assertTrue(pathEvaluator.get(jsonPath).toString().contains(value));
                System.out.println("Actual :: " + pathEvaluator.get(jsonPath));
                System.out.println("Expected :: " + value);
                break;
            default:
                break;
        }
    }

    public void verifyAndPrintTheCoin() {
        currancyMapList = null;
        JsonPath pathEvaluator = RestAPIUtilities.response.getBody().jsonPath();
        String [] ids = coinId.split(",");
        for (int i = 0; i <ids.length ; i++) {
            String jsonPath = "data." + ids[i] + ".tags";
            if(pathEvaluator.get(jsonPath).toString().contains("mineable"))
            System.out.println("Currency associated with mineable tag is :: " + pathEvaluator.get("data." + ids[i] + ".symbol")+" ID ::"+ pathEvaluator.get("data." + ids[i] + ".id"));
        }
    }
}
