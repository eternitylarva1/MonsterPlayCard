这是一个游戏的mod文件。大部分功能的实现都在游戏本体中。游戏本体代码在.SlayTheSpireLibrary文件夹的com文件夹下面，大部分代码都可以在这里找到

教程在.SlayTheSpireLibrary/SlayTheSpireModTutorials-master/Tutorials/ 里面，如果如果要使用新的东西先看看这个教程里面有没有。你查看这个目录的结构。

在正式开始编辑之前，一定要阅读.SlayTheSpireLibrary文件夹中的教程中。

禁止使用rm -rf这种危险的指令。不需要的类请你注释掉

在重写任何方法前，必须先仔细研究原版实现，不要想当然

不要简化处理：原版的calculateCardDamage方法是闭环的，考虑了所有power类型，应该保持完整性

理解角色关系：怪物卡牌是怪物出的，atDamageGive应该看怪物的powers，不要搞混角色

写完代码之后使用maven进行package，如果构建失败要修复。如果成功后要git commit

.SlayTheSpireLibrary\BaseMod.wiki  这里面有basemod提供的工具和钩子说明

.SlayTheSpireLibrary\StSLib.wiki  这里面有stslib提供的工具和钩子说明

.SlayTheSpireLibrary\SlayTheSpireModTutorials-master\Tutorials\高级技巧\01 - Patch\README.md    这里是patch教程，当你在basemod和stslib中找不到对应的接口的时候，你可以通过这个来增加接口，一般建议使用Postfix和Prefix，使用pacth时候，需要你在 .SlayTheSpireLibrary\com  中找到对应的类和方法  如果需要使用InsertFix，还要找到对应的相对行数。


## 🚀 高效工作要点

### **关键成功因素**

1. **🎯 精准问题定位**
   - 仔细分析用户的具体反馈
   - 快速识别代码中的逻辑错误

2. **🔍 深入对比研究**
   - 不满足于表面修复
   - 深入研究本系统的实现原理
    运行逻辑分析:按照运行顺序查看，分析问题原因

3. **📊 系统性方法论**
   - 使用TodoWrite工具管理复杂任务
   - 逐步分解问题
   - 每个问题都有专门的修复方法

4. **🧬 持续验证迭代**
   - 构建测试
   - 发现问题立即回溯分析
   - 不怕推倒重来

### **避免的陷阱**

1. **❌ 表面修复**
   - 不深入理解原理
   - 只是调整参数

2. **❌ 闭门造车**
   - 自己编api
   - 重复造轮子

3. **❌ 一次性修复**
   - 试图一次解决所有问题
   - 不做中间验证

4. **❌ 调试日志性能问题**
   - 避免在update/render循环中输出大量日志
   - 使用标志控制调试输出频率
   - 保留关键调试信息的同时提升游戏性能
