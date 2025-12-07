# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Repository Overview

This is a **Slay the Spire** mod written in Java using Maven. The mod allows monsters to play cards during combat, implementing a comprehensive monster card system.

### Project Details
- **Artifact ID**: EchoForm
- **Name**: EveryMonsterPlayCard
- **Type**: Java Mod for Slay the Spire
- **Java Version**: 1.8
- **Build System**: Maven
- **Main Package**: `EveryMonsterPlayCard`

## Build and Test Commands

### Building the Mod
```bash
# Standard build (compiles and copies to game directory)
mvn clean package

# Build with UTF-8 encoding (required for Chinese comments)
mvn clean package -Dfile.encoding=UTF-8

# Compile only (without copying to game directory)
mvn clean compile
```

### Development Workflow
1. Make changes to the code
2. Build with `mvn clean package -Dfile.encoding=UTF-8`
3. Test in Slay the Spire
4. Commit changes to git after successful testing

## Code Architecture

### Core Components

#### 1. Main Mod Entry (`EveryMonsterPlayCard.modcore.everyMonsterPlayCard`)
- Implements multiple BaseMod interfaces: `StartActSubscriber`, `PostDungeonInitializeSubscriber`, `PostInitializeSubscriber`, etc.
- Handles mod initialization and event subscriptions
- Manages global state (turn counter, fire map, etc.)
- Located at: `src/main/java/EveryMonsterPlayCard/modcore/everyMonsterPlayCard.java`

#### 2. Monster Card System
**MonsterCardManager** (`monstercards.MonsterCardManager`)
- Singleton manager for all monster card players
- Maps monster IDs to their respective MonsterCardPlayer instances
- Controls enabling/disabling of monster card systems
- Located at: `src/main/java/EveryMonsterPlayCard/monstercards/MonsterCardManager.java`

**MonsterCardPlayer** (`monstercards.MonsterCardPlayer`)
- Handles card playing logic for individual monsters
- Manages monster's card pile, drawing, and playing cards
- Each monster gets its own CardPlayer instance

**MonsterCardConfig** (`monstercards.MonsterCardConfig`)
- Configuration system for defining which cards each monster can play
- Loads and manages monster-specific card configurations
- Provides examples in `MonsterCardConfigExamples.java`

#### 3. Patch System (`monstercards` and `patchs`)
- `AbstractMonsterPatch` - Base patch for monster behavior
- `AbstractMonsterDiePatch` - Handles monster death events
- `AbstractMonsterEscapePatch` - Handles monster escape events
- `AbstractMonsterRenderPatch` - Handles monster rendering
- `BattleStartPatch` - Initializes card systems at battle start
- `CamfirePatch` - Campfire-related functionality

#### 4. Utility Classes (`utils`)
- `Hpr` - Logging and information utilities
- `Invoker` - Reflection utility for accessing game internals
- `InstanceMaker` - Helper for creating game object instances
- `ModHelper` - General mod helper functions

#### 5. Abstract Monster Card (`cards.AbstractMonsterCard`)
- Base class for monster-specific cards
- Extended by individual monster card implementations

## Important Resources

### 1. SlLibrary
Located at `.SlayTheSpireLibrary/`, this directoryayTheSpireDecompiled game code** in `com/` - Always reference this contains:
- ** first to understand game mechanics
- **Documentation** in `BaseMod.wiki/` and `StSLib.wiki/`
- **Tutorials** in `SlayTheSpireModTutorials-master/`
- **Localization files** in `本地化文本/` - Contains cards.json, relics.json, etc.

### 2. Development Rules
**rules.md** - Contains critical lessons learned:
- ⚠️ **MUST READ** before making changes to existing systems
- Documents common mistakes and correct implementations
- Emphasizes reusing existing game systems over custom implementations

**newrules.md** (in `.SlayTheSpireLibrary/`) - Contains coding standards:
- Usage of native game systems
- Security and exception handling guidelines
- Patch and rendering standards
- Performance optimization tips

## Development Guidelines

### Critical Rules (from rules.md)

1. **Use Native Game Methods First**
   - ✅ Always check `.SlayTheSpireLibrary` for existing implementations
   - ✅ Use game-provided APIs before custom solutions
   - ❌ Don't reimplement existing functionality

2. **Reflection Usage**
   - Always use MCP tools to verify fields/methods exist before reflection
   - Check `.SlayTheSpireLibrary` source code first
   - Only use reflection when game APIs are insufficient
   - Example: Check field modifiers before using `getDeclaredField()`

3. **Field Access Pattern**
   ```java
   // ✅ Correct: Check if public first
   // Use MCP or read source to verify
   Object value = gameObject.publicField;

   // ❌ Incorrect: Blindly use reflection
   java.lang.reflect.Field field = clazz.getDeclaredField("field");
   field.setAccessible(true);
   ```

4. **Configuration Mapping**
   - `MonsterReward` uses `HashMap<String,List<String>>`: key=遗物ID, value=List<怪物ID>
   - Verify entity types in `relics.json` before configuring rewards
   - One relic can drop from multiple monsters, one monster can drop multiple relics

### Coding Standards (from newrules.md)

1. **Action System**
   ```java
   // ✅ Correct
   AbstractDungeon.actionManager.addToTop(new DamageAction(target, damageInfo));
   AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target, source, power));

   // ✅ Card usage
   effectCard.use(AbstractDungeon.player, targetMonster);
   ```

2. **Safety Checks**
   ```java
   // ✅ Standard safety pattern
   if (AbstractDungeon.getCurrRoom() != null) {
       if (AbstractDungeon.getCurrRoom() instanceof RestRoom) {
           // Room-specific logic
       }
   }

   if (CardCrawlGame.isInARun()) {
       // Game-specific logic
   }
   ```

3. **Exception Handling**
   ```java
   if (player != null && targetMonster != null) {
       try {
           // Critical operations
       } catch (Exception e) {
           logger.error("Operation failed: " + e.getMessage());
           // Safe exit
       }
   }
   ```

### Maven Configuration

The `pom.xml` includes:
- **Automatic deployment** to Steam mods directory during package phase
- **Steam path** is configurable in properties (default: `C:\Program Files (x86)\steam\steamapps`)
- **System dependencies** for game jars (not available on Maven Central)

**Important**: Update Steam.path in pom.xml to match your installation.

### Original Content Identification

**Using .SlayTheSpireLibrary to verify original content:**
```bash
# Check for original cards
grep "CardName" ".SlayTheSpireLibrary/本地化文本/cards.json"

# Check for original relics
grep "RelicName" ".SlayTheSpireLibrary/本地化文本/relics.json"
```

**Confirmed Original Curse Cards** (emotion-themed):
- `Doubt` (疑虑) - "Cannot be played. At end of turn, gain 1 Frail."
- `Regret` (悔恨) - "Cannot be played. At end of turn, lose HP equal to hand size."
- `Writhe` (苦恼) - "Cannot be played. Innate."
- `Pride` (傲慢) - "Innate. Exhaust. At end of turn, add a copy to top of draw pile."
- `Shame` (羞耻) - "Cannot be played. At end of turn, gain 1 Weak."

## Common Development Tasks

### Adding New Monster Cards
1. Define cards in `MonsterCardConfig.java` or `MonsterCardConfigExamples.java`
2. Each monster ID maps to a list of playable cards
3. Cards are automatically loaded when battle starts

### Creating New Patches
1. Check `.SlayTheSpireLibrary` for target class structure
2. Use appropriate `@SpirePatch` annotation
3. Follow patch patterns in existing files
4. Add null checks and safety guards

### Debugging with MCP Tools
```bash
# Analyze game class structure
mcp__bfHaz-Y7LcmAWkjy1mpdr__analyze_class("com.megacrit.cardcrawl.ui.campfire.CampfireUI", projectPath)

# Decompile specific class
mcp__bfHaz-Y7LcmAWkjy1mpdr__decompile_class("com.megacrit.cardcrawl.relics.AbstractRelic", projectPath)

# Scan dependencies
mcp__bfHaz-Y7LcmAWkjy1mpdr__scan_dependencies(projectPath)
```

## Key Files to Review

1. **pom.xml** - Build configuration and dependencies
2. **rules.md** - Critical lessons and forbidden patterns
3. **.SlayTheSpireLibrary/newrules.md** - Coding standards
4. **Main mod file** - Event subscriptions and initialization
5. **MonsterCardManager** - Core system architecture
6. **MonsterCardConfig** - Monster-card mapping configuration

## Important Notes

- **MUST read rules.md before modifying existing systems**
- Always build with `-Dfile.encoding=UTF-8`- **NE for Chinese comments
VER** use `rm -rf` or other destructive commands
- Comment out unused code instead of deleting
- **ALWAYS** commit to git after successful build and testing
- Verify field/method existence with MCP before using reflection
- Prefer simple, direct solutions over complex implementations
- Test all changes in actual game environment before committing
