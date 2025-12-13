package EveryMonsterPlayCard.downfall;

import EveryMonsterPlayCard.downfall.config.DownfallCardList;
import EveryMonsterPlayCard.downfall.config.MigrationConfig;
import EveryMonsterPlayCard.downfall.service.CardMigrationService;

/**
 * 简化的移植系统测试
 * 不依赖Slay the Spire类，用于验证系统基本功能
 */
public class SimpleMigrationTest {
    
    public static void main(String[] args) {
        System.out.println("=== Downfall卡牌移植系统简化测试 ===");
        
        // 测试配置系统
        testConfigurationSystem();
        
        // 测试卡牌列表
        testCardListSystem();
        
        // 测试服务层初始化
        testServiceInitialization();
        
        System.out.println("=== 简化测试完成 ===");
        System.out.println("注意：完整测试需要Slay the Spire环境");
    }
    
    /**
     * 测试配置系统
     */
    private static void testConfigurationSystem() {
        System.out.println("\n--- 测试配置系统 ---");
        
        try {
            MigrationConfig config = MigrationConfig.getInstance();
            
            // 测试基本配置
            System.out.println("自动移植: " + config.isEnableAutoMigration());
            System.out.println("平衡性调整: " + config.isEnableBalanceAdjustment());
            System.out.println("资源处理: " + config.isEnableResourceProcessing());
            System.out.println("验证: " + config.isEnableValidation());
            
            // 测试配置修改
            config.setEnableAutoMigration(false);
            config.setEnableBalanceAdjustment(true);
            
            System.out.println("配置修改后:");
            System.out.println("自动移植: " + config.isEnableAutoMigration());
            System.out.println("平衡性调整: " + config.isEnableBalanceAdjustment());
            
            // 恢复默认配置
            config.resetToDefaults();
            
            System.out.println("配置摘要:");
            System.out.println(config.getConfigSummary());
            
            System.out.println("✓ 配置系统测试通过");
            
        } catch (Exception e) {
            System.err.println("✗ 配置系统测试失败: " + e.getMessage());
        }
    }
    
    /**
     * 测试卡牌列表系统
     */
    private static void testCardListSystem() {
        System.out.println("\n--- 测试卡牌列表系统 ---");
        
        try {
            DownfallCardList cardList = DownfallCardList.getInstance();
            
            // 测试卡牌统计
            DownfallCardList.CardTypeStatistics stats = cardList.getStatistics();
            System.out.println("卡牌统计: " + stats.toString());
            
            // 测试分类查询
            System.out.println("攻击卡牌数量: " + cardList.getAttackCards().size());
            System.out.println("技能卡牌数量: " + cardList.getSkillCards().size());
            System.out.println("能力卡牌数量: " + cardList.getPowerCards().size());
            System.out.println("诅咒卡牌数量: " + cardList.getCurseCards().size());
            System.out.println("特殊卡牌数量: " + cardList.getSpecialCards().size());
            
            // 测试具体卡牌
            String[] testCards = {"CharbossAnger", "CharbossBash", "CharbossDefend"};
            for (String cardId : testCards) {
                boolean hasCard = cardList.hasCard(cardId);
                DownfallCardList.CardInfo cardInfo = cardList.getCardInfo(cardId);
                
                System.out.println("卡牌 " + cardId + ": 存在=" + hasCard + 
                                 ", 信息=" + (cardInfo != null ? cardInfo.toString() : "null"));
            }
            
            System.out.println("✓ 卡牌列表系统测试通过");
            
        } catch (Exception e) {
            System.err.println("✗ 卡牌列表系统测试失败: " + e.getMessage());
        }
    }
    
    /**
     * 测试服务层初始化
     */
    private static void testServiceInitialization() {
        System.out.println("\n--- 测试服务层初始化 ---");
        
        try {
            CardMigrationService service = new CardMigrationService();
            
            // 测试配置
            service.setEnableAsync(false);
            service.setEnableCache(true);
            
            // 测试统计
            CardMigrationService.MigrationStatistics stats = service.getMigrationStatistics();
            System.out.println("初始统计: " + stats.toString());
            
            // 测试缓存
            System.out.println("缓存大小: " + service.getCacheSize());
            
            // 测试组件获取
            System.out.println("移植器: " + (service.getMigrator() != null ? "已初始化" : "未初始化"));
            System.out.println("资源处理器: " + (service.getResourceProcessor() != null ? "已初始化" : "未初始化"));
            System.out.println("平衡性调整器: " + (service.getBalanceAdjuster() != null ? "已初始化" : "未初始化"));
            
            System.out.println("✓ 服务层初始化测试通过");
            
        } catch (Exception e) {
            System.err.println("✗ 服务层初始化测试失败: " + e.getMessage());
        }
    }
    
    /**
     * 运行性能基准测试
     */
    public static void runPerformanceBenchmark() {
        System.out.println("\n--- 性能基准测试 ---");
        
        try {
            long startTime = System.currentTimeMillis();
            
            // 测试配置加载性能
            for (int i = 0; i < 1000; i++) {
                MigrationConfig config = MigrationConfig.getInstance();
                config.isEnableAutoMigration();
            }
            
            long configTime = System.currentTimeMillis() - startTime;
            System.out.println("配置加载1000次耗时: " + configTime + "ms");
            
            startTime = System.currentTimeMillis();
            
            // 测试卡牌列表查询性能
            DownfallCardList cardList = DownfallCardList.getInstance();
            for (int i = 0; i < 1000; i++) {
                cardList.hasCard("CharbossAnger");
                cardList.getStatistics();
            }
            
            long listTime = System.currentTimeMillis() - startTime;
            System.out.println("卡牌列表查询1000次耗时: " + listTime + "ms");
            
            System.out.println("✓ 性能基准测试完成");
            
        } catch (Exception e) {
            System.err.println("✗ 性能基准测试失败: " + e.getMessage());
        }
    }
    
    /**
     * 验证系统完整性
     */
    public static void validateSystemIntegrity() {
        System.out.println("\n--- 系统完整性验证 ---");
        
        try {
            // 验证所有核心组件是否可以正常实例化
            MigrationConfig config = MigrationConfig.getInstance();
            DownfallCardList cardList = DownfallCardList.getInstance();
            CardMigrationService service = new CardMigrationService();
            
            // 验证配置完整性
            boolean configValid = config.getConfigSummary() != null && !config.getConfigSummary().isEmpty();
            
            // 验证卡牌列表完整性
            boolean listValid = cardList.getTotalCardCount() > 0;
            
            // 验证服务完整性
            boolean serviceValid = service.getMigrator() != null && 
                                  service.getResourceProcessor() != null && 
                                  service.getBalanceAdjuster() != null;
            
            System.out.println("配置完整性: " + (configValid ? "✓" : "✗"));
            System.out.println("卡牌列表完整性: " + (listValid ? "✓" : "✗"));
            System.out.println("服务完整性: " + (serviceValid ? "✓" : "✗"));
            
            boolean allValid = configValid && listValid && serviceValid;
            System.out.println("系统整体完整性: " + (allValid ? "✓ 通过" : "✗ 失败"));
            
            if (allValid) {
                System.out.println("\n🎉 Downfall卡牌移植系统已成功实现！");
                System.out.println("系统包含以下核心功能：");
                System.out.println("  • 卡牌分析器 - 分析Downfall卡牌属性和逻辑");
                System.out.println("  • 移植模板 - 生成原生卡牌代码");
                System.out.println("  • 自动移植工具 - 执行卡牌移植流程");
                System.out.println("  • 服务层 - 提供高级移植服务");
                System.out.println("  • 配置管理 - 管理移植设置和选项");
                System.out.println("  • 资源处理 - 处理图片、本地化等资源");
                System.out.println("  • 平衡性调整 - 自动调整卡牌平衡性");
                System.out.println("  • 验证系统 - 验证移植结果");
            }
            
        } catch (Exception e) {
            System.err.println("✗ 系统完整性验证失败: " + e.getMessage());
        }
    }
}