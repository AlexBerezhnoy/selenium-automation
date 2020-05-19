package com.hillel.auto.listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;


public class TestMethodExecutionListener implements ITestListener {

    private Map<String, String> testResult = new HashMap<>();

    @Override
    public void onTestStart(ITestResult result) {
        ITestNGMethod method = result.getMethod();
        System.out.println("Start " + method.getMethodName() + " test");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ITestNGMethod method = result.getMethod();
        long durationTime = result.getEndMillis() - result.getStartMillis();
        testResult.put(method.getMethodName(), Long.toString(Duration.ofMillis(durationTime).toMillis()));
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
        System.out.println();
        for (Map.Entry entry: testResult.entrySet()) {
            System.out.println("Test Method "+entry.getKey()+" executed throughout " +  entry.getValue() + " milliseconds");
        }
        System.out.println();
    }

}
