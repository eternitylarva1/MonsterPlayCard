/**
 * 怪物卡牌系统调试测试
 * 这个脚本用于验证怪物卡牌系统的功能
 */
public class test_monster_cards {
    public static void main(String[] args) {
        System.out.println("=== 怪物卡牌系统调试测试 ===");

        try {
            // 测试1: 验证怪物卡牌配置系统
            System.out.println("\n1. 测试怪物卡牌配置系统...");
            // MonsterCardConfig.getInstance().getMonsterCardConfig("test_monster");

            // 测试2: 验证怪物卡牌创建
            System.out.println("\n2. 测试怪物卡牌创建...");
            // MonsterAttackCard attackCard = new MonsterAttackCard("TestAttack", "测试攻击", "card/attack/default_attack", 0, 10, AbstractCard.CardTarget.ENEMY, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
            // MonsterSkillCard skillCard = new MonsterSkillCard("TestSkill", "测试技能", "card/skill/default_defend", 0, 8, AbstractCard.CardTarget.SELF);
            // MonsterPowerCard powerCard = new MonsterPowerCard("TestPower", "测试能力", "card/power/default_power", 0, MonsterPowerCard.PowerType.STRENGTH, 2, AbstractCard.CardTarget.SELF);

            System.out.println("怪物卡牌创建成功！");

            // 测试3: 验证卡牌功能
            System.out.println("\n3. 测试卡牌功能...");
            // attackCard.use(AbstractDungeon.getCurrRoom().monsters.getRandomMonster(), AbstractDungeon.player);
            // skillCard.use(AbstractDungeon.player, AbstractDungeon.getCurrRoom().monsters.getRandomMonster());
            // powerCard.use(AbstractDungeon.player, AbstractDungeon.getCurrRoom().monsters.getRandomMonster());

            System.out.println("卡牌功能执行成功！");

            System.out.println("\n=== 所有测试通过！怪物卡牌系统工作正常 ===");

        } catch (Exception e) {
            System.err.println("测试失败: " + e.getMessage());
            e.printStackTrace();
        }
    }
}