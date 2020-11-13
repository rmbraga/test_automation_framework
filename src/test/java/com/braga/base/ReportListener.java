package com.braga.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

public class ReportListener extends BaseTest implements ITestListener {
    private static final Logger logger = LogManager.getLogger(ReportListener.class);

    @Override
    public void onStart(ITestContext context) {
        logger.info("onStart -> Test Tag Name: " + context.getName());
        ITestNGMethod methods[] = context.getAllTestMethods();
        logger.info("These methods will be executed in this <test> tag");
        for (ITestNGMethod method : methods) {
            logger.info(method.getMethodName());
        }
    }

    @Override
    public void onFinish(ITestContext context) {

    }

    @Override
    public void onTestStart(ITestResult result) {

    }

    @Override
    public void onTestSuccess(ITestResult result) {

    }

    @Override
    public void onTestFailure(ITestResult result) {

    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {

    }

}
