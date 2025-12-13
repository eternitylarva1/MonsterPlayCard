package EveryMonsterPlayCard.tests;

/**
 * 测试套件类
 * 用于运行所有测试
 */
public class TestSuite {
    
    /**
     * 运行所有测试
     */
    public static void runAllTests() {
        System.out.println("========================================");
        System.out.println("       动作转换系统测试套件");
        System.out.println("========================================");
        
        long startTime = System.currentTimeMillis();
        
        try {
            // 运行功能测试
            System.out.println("\n【功能测试】");
            ActionConversionTest.runAllTests();
            
            // 运行性能测试
            System.out.println("\n【性能测试】");
            PerformanceTest.runAllPerformanceTests();
            
            // 运行集成测试
            System.out.println("\n【集成测试】");
            IntegrationTest.runAllIntegrationTests();
            
            long endTime = System.currentTimeMillis();
            long totalTime = endTime - startTime;
            
            System.out.println("\n========================================");
            System.out.println("           测试完成");
            System.out.println("========================================");
            System.out.println("总耗时: " + totalTime + " 毫秒");
            System.out.println("测试状态: 全部完成");
            
        } catch (Exception e) {
            System.err.println("测试套件执行异常: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * 运行功能测试
     */
    public static void runFunctionalTests() {
        System.out.println("========================================");
        System.out.println("         功能测试");
        System.out.println("========================================");
        
        ActionConversionTest.runAllTests();
    }
    
    /**
     * 运行性能测试
     */
    public static void runPerformanceTests() {
        System.out.println("========================================");
        System.out.println("         性能测试");
        System.out.println("========================================");
        
        PerformanceTest.runAllPerformanceTests();
    }
    
    /**
     * 运行集成测试
     */
    public static void runIntegrationTests() {
        System.out.println("========================================");
        System.out.println("         集成测试");
        System.out.println("========================================");
        
        IntegrationTest.runAllIntegrationTests();
    }
    
    /**
     * 主方法，用于运行测试套件
     */
    public static void main(String[] args) {
        if (args.length > 0) {
            switch (args[0].toLowerCase()) {
                case "functional":
                    runFunctionalTests();
                    break;
                case "performance":
                    runPerformanceTests();
                    break;
                case "integration":
                    runIntegrationTests();
                    break;
                default:
                    System.out.println("用法: java TestSuite [functional|performance|integration]");
                    System.out.println("  functional   - 运行功能测试");
                    System.out.println("  performance  - 运行性能测试");
                    System.out.println("  integration  - 运行集成测试");
                    System.out.println("  无参数       - 运行所有测试");
                    break;
            }
        } else {
            runAllTests();
        }
    }
}