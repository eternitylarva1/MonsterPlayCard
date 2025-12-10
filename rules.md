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

