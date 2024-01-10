public class SalaryCalculator {
    private static final int SLACKER_SALARY_MULTIPLIER_THRESHOLD = 5;
    private static final double STANDARD_SALARY_MULTIPLIER = 1.0;
    private static final double SLACKER_SALARY_MULTIPLIER = 0.85;
    private static final int OVERACHIEVER_PRODUCT_MULTIPLIER_THRESHOLD = 20;
    private static final int STANDARD_PRODUCT_MULTIPLIER = 10;
    private static final int OVERACHIEVER_PRODUCT_MULTIPLIER = 13;
    private static final double BASE_SALARY = 1000.0;
    private static final double MAX_SALARY = 2000.0;
    
    public double multiplierPerDaysSkipped(int daysSkipped) {
        return daysSkipped > SLACKER_SALARY_MULTIPLIER_THRESHOLD 
               ? SLACKER_SALARY_MULTIPLIER
               : STANDARD_SALARY_MULTIPLIER;
    }

    public int multiplierPerProductsSold(int productsSold) {
        return productsSold > OVERACHIEVER_PRODUCT_MULTIPLIER_THRESHOLD 
               ? OVERACHIEVER_PRODUCT_MULTIPLIER
               : STANDARD_PRODUCT_MULTIPLIER;
    }

    public double bonusForProductSold(int productsSold) {
        return productsSold * multiplierPerProductsSold(productsSold);
    }

    public double finalSalary(int daysSkipped, int productsSold) {
        final double salary = BASE_SALARY * multiplierPerDaysSkipped(daysSkipped) 
                            + bonusForProductSold(productsSold);
        return Math.min(MAX_SALARY, salary);
    } 
}
