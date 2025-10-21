package com.framework.utils;

import java.util.UUID;

import org.testng.asserts.SoftAssert;

import io.qameta.allure.Allure;
import io.qameta.allure.model.StepResult;
import io.qameta.allure.model.Status;


public final class SoftAssertManager {

    // ThreadLocal to isolate SoftAssert per thread/scenario
    private static final ThreadLocal<SoftAssert> softAssertThreadLocal = new ThreadLocal<>();

    // Private constructor to prevent instantiation
    private SoftAssertManager() {
        throw new UnsupportedOperationException("Utility class - cannot be instantiated");
    }

    // Initialize before each scenario
    public static void initSoftAssert() {
        softAssertThreadLocal.set(new SoftAssert());
    }

    // Get current thread's SoftAssert
    public static SoftAssert getSoftAssert() {
        return softAssertThreadLocal.get();
    }
    
    private static void logStep(String name, boolean passed, String details) {
        String uuid = UUID.randomUUID().toString();
        StepResult step = new StepResult().setName(name);
        Allure.getLifecycle().startStep(uuid, step);
        if (details != null && !details.isEmpty()) {
            Allure.addAttachment("Details", details);
        }
        Allure.getLifecycle().updateStep(uuid, s -> s.setStatus(passed ? Status.PASSED : Status.FAILED));
        Allure.getLifecycle().stopStep();
    }

    public static void assertTrue(boolean condition, String stepDescription) {
        // Log status explicitly
        logStep(stepDescription, condition, null);
        // Collect in SoftAssert
        getSoftAssert().assertTrue(condition, stepDescription);
    }

    public static void assertFalse(boolean condition, String stepDescription) {
        boolean passed = !condition;
        logStep(stepDescription, passed, null);
        getSoftAssert().assertFalse(condition, stepDescription);
    }

    public static <T> void assertEquals(T actual, T expected, String stepDescription) {
        boolean passed = (expected == null && actual == null) || (expected != null && expected.equals(actual));
        String details = "expected=" + expected + ", actual=" + actual;
        logStep(stepDescription, passed, details);
        getSoftAssert().assertEquals(actual, expected, stepDescription);
    }

    public static <T> void assertNotEquals(T actual, T expected, String stepDescription) {
        boolean passed = !((expected == null && actual == null) || (expected != null && expected.equals(actual)));
        String details = "actual=" + actual + ", expected=" + expected;
        logStep(stepDescription, passed, details);
        getSoftAssert().assertNotEquals(actual, expected, stepDescription);
    }
    

	/*
	 * // ✅ assertTrue public static void assertTrue(boolean condition, String
	 * stepDescription) { if (condition) { // Allure.step("✅ " + stepDescription); }
	 * else { Allure.step("❌ " + stepDescription); }
	 * getSoftAssert().assertTrue(condition, stepDescription); }
	 * 
	 * // ❌ assertFalse public static void assertFalse(boolean condition, String
	 * stepDescription) { if (!condition) { // Allure.step("✅ " + stepDescription);
	 * } else { Allure.step("❌ " + stepDescription); }
	 * getSoftAssert().assertFalse(condition, stepDescription); }
	 * 
	 * 
	 * // 🔄 assertEquals public static <T> void assertEquals(T actual, T expected,
	 * String stepDescription) { if ((expected == null && actual == null) ||
	 * (expected != null && expected.equals(actual))) { // Allure.step("✅ " +
	 * stepDescription + " | expected=" + expected + ", actual=" + actual); } else {
	 * Allure.step("❌ " + stepDescription + " | expected=" + expected + ", actual="
	 * + actual); } getSoftAssert().assertEquals(actual, expected, stepDescription);
	 * }
	 * 
	 * // 🚫 assertNotEquals public static <T> void assertNotEquals(T actual, T
	 * expected, String stepDescription) { if ((expected == null && actual == null)
	 * || (expected != null && expected.equals(actual))) { Allure.step("❌ " +
	 * stepDescription + " | actual=" + actual + " equals expected=" + expected); }
	 * else { // Allure.step("✅ " + stepDescription + " | actual=" + actual +
	 * " not equal to expected=" + expected); }
	 * getSoftAssert().assertNotEquals(actual, expected, stepDescription); }
	 */
    // Final assertAll at scenario end
    public static void assertAll() {
        try {
            if (getSoftAssert() != null) {
                getSoftAssert().assertAll();
            }
        } catch (AssertionError e) {
            Allure.addAttachment("Soft Assertion Failures", e.getMessage());
            throw e;
        }
    }

    // Remove to avoid memory leaks
    public static void remove() {
        softAssertThreadLocal.remove();
    }
}