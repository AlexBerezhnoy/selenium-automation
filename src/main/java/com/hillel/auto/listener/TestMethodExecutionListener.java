package com.hillel.auto.listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

import java.time.Duration;


public class TestMethodExecutionListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        ITestNGMethod method = result.getMethod();
        System.out.println("Start " + method.getMethodName() + " test");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        long durationTime = result.getEndMillis() - result.getStartMillis();
        System.out.println("Execution time: " + Duration.ofMillis(durationTime).getSeconds());
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
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {

    }
}
