package com.itsz.portfolio.service.calculator;


public class BlackScholesUtil {

    private BlackScholesUtil() {
    }

    public static final double INTEREST_RATE = 0.02;// Risk-free interest rate
    public static final double SIGMA = 0.20;

    // Cumulative normal distribution function generate by ChatGPT
    private static double cumulativeNormalDistribution(double x) {
        double b1 = 0.31938153;
        double b2 = -0.356563782;
        double b3 = 1.781477937;
        double b4 = -1.821255978;
        double b5 = 1.330274429;
        double p = 0.2316419;
        double c2 = 0.3989423;

        if (x >= 0.0) {
            double t = 1.0 / (1.0 + p * x);
            return (1.0 - c2 * Math.exp(-x * x / 2.0) * t *
                    (t * (t * (t * (t * b5 + b4) + b3) + b2) + b1));
        } else {
            double t = 1.0 / (1.0 - p * x);
            return (c2 * Math.exp(-x * x / 2.0) * t *
                    (t * (t * (t * (t * b5 + b4) + b3) + b2) + b1));
        }
    }

    public static double calculateCallOptionPrice(
            double S, // Spot price
            double K, // Strike price
            double T // Time to maturity (in years)
    ) {
        double d1 = getD1(S, K, T);
        double d2 = d1 - SIGMA * Math.sqrt(T);
        return S * cumulativeNormalDistribution(d1) - K * Math.exp(-INTEREST_RATE * T) * cumulativeNormalDistribution(d2);
    }

    private static double getD1(double S, double K, double T) {
        return (Math.log(S / K) + (INTEREST_RATE + 0.5 * Math.pow(SIGMA, 2)) * T) / (SIGMA * Math.sqrt(T));
    }

    public static double calculatePutOptionPrice(
            double S, // Spot price
            double K, // Strike price
            double T // Time to maturity (in years)
    ) {
        double d1 = getD1(S, K, T);
        double d2 = d1 - SIGMA * Math.sqrt(T);
        return K * Math.exp(-INTEREST_RATE * T) * cumulativeNormalDistribution(-d2) - S * cumulativeNormalDistribution(-d1);
    }
}