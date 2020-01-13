package com.braga.utilities;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class CheckPoint {
    private static final Logger log = LogManager.getLogger(CheckPoint.class.getName());
    public static HashMap<String, String> resultMap = new HashMap<>();
    private static String PASS = "PASS";
    private static String FAIL = "FAIL";

    public static void clearHashMap() {
        log.info("Clearing Results Hash Map");
        resultMap.clear();
    }

    private static void setStatus(String mapKey, String status) {
        resultMap.put(mapKey, status);
        log.info(mapKey + " :-> " + resultMap.get(mapKey));
    }

    public static void mark(String testName, boolean result, String resultMessage) {
        testName = testName.toLowerCase();
        String mapKey = testName + "." + resultMessage;
        try {
            if (result) {
                setStatus(mapKey, PASS);
            } else {
                setStatus(mapKey, FAIL);
            }
        } catch (Exception e) {
            log.error("Exception Ocurred...");
            setStatus(mapKey, FAIL);
            e.printStackTrace();
        }
    }

    public static void markFinal(String testName, boolean result, String resultMessage) {
        testName = testName.toLowerCase();
        String mapKey = testName + "." + resultMessage;
        try {
            if (result) {
                setStatus(mapKey, PASS);
            } else {
                setStatus(mapKey, FAIL);
            }
        } catch (Exception e) {
            log.error("Exception Ocurred...");
            setStatus(mapKey, FAIL);
            e.printStackTrace();
        }

        ArrayList<String> resultList = new ArrayList<>();
        for (String key : resultMap.keySet()) {
            resultList.add(resultMap.get(key));
        }

        for (int i = 0; i < resultList.size(); i++) {
            if (resultList.contains(FAIL)) {
                log.info("Test Method Failed");
                Assert.assertTrue(false);
            } else {
                log.info("Test Method Successful");
                Assert.assertTrue(true);
            }
        }

    }


}
