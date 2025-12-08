# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Repository Overview

This is a **Slay the Spire** mod written in Java using Maven. The mod allows monsters to play cards during combat, implementing a comprehensive monster card system.

### Project Details
- **Artifact ID**: MonsterPlayCard
- **Name**: EveryMonsterPlayCard
- **Type**: Java Mod for Slay the Spire
- **Java Version**: 1.8
- **Build System**: Maven
- **Main Package**: `EveryMonsterPlayCard`
- **Mod ID**: everyMonsterPlayCard

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
- Implements multiple BaseMod interfaces: `EditStringsSubscriber`, `PostInitializeSubscriber`, `PostDungeonInitializeSubscriber`, `OnStartBattleSubscriber`, `PostBattleSubscriber`
- Handles mod initialization and event subscriptions
- Manages global state and mod configuration
- Located at: `src/main/java/EveryMonsterPlayCard/modcore/everyMonsterPlayCard.java`

#### 2. Monster Card System
**MonsterCardManager** (`monstercards.MonsterCardManager`)
- Singleton manager for all monster card players
- Uses SpireField to avoid ID conflicts between monsters
- Controls enabling/disabling of monster card systems
- Located at: `src/main/java/EveryMonsterPlayCard/monstercards/MonsterCardManager.java`

**MonsterCardPlayer** (`monstercards.MonsterCardPlayer`)
- Handles card playing logic for individual monsters
- Manages monster's card pile, drawing, and playing cards
- Each monster gets its own CardPlayer instance via SpireField

**MonsterCardConfig** (`monstercards.MonsterCardConfig`)
- Configuration system for monster card pools
- Provides universal card pool for all monsters
- Manages card loading and initialization
- Located at: `src/main/java/EveryMonsterPlayCard/monstercards/MonsterCardConfig.java`

**MonsterCardConfigExamples** (`monstercards.MonsterCardConfigExamples`)
- Contains example configurations and templates
- Shows how to configure monster-specific card sets
- Located at: `src/main/java/EveryMonsterPlayCard/monstercards/MonsterCardConfigExamples.java`

#### 3. Patch System
**Field Injection Patches** (`monstercards`)
- `AbstractMonsterAddFieldPatch` - SpireField injection for MonsterCardPlayer
- `AbstractMonsterFieldPatch` - Field access patches
- `AbstractMonsterPatch` - Base patch for monster behavior
- `AbstractMonsterDiePatch` - Handles monster death events
- `AbstractMonsterEscapePatch` - Handles monster escape events
- `AbstractMonsterRenderPatch` - Handles monster rendering
- `MonsterStartTurnPatch` - Handles monster turn start events

**Battle Patches** (`patchs`)
- `CamfirePatch` - Campfire-related functionality

#### 4. Card System
**Abstract Monster Card** (`monstercards.cards`)
- `AbstractMonsterCard` - Base class for monster-specific cards
- `MonsterAttackCard` - Attack card template
- `MonsterSkillCard` - Skill card template
- `MonsterPowerCard` - Power card template

**Individual Monster Cards** (`cards.monster`)
- Currently contains 19 implemented cards from red path
- Examples: `EnBash`, `EnCarnage`, `EnCleave`, `EnDemonForm`, etc.
- Following naming convention: `En{OriginalCardName}`

#### 5. Utility Classes (`utils`)
- `Hpr` - Logging and information utilities
- `Invoker` - Reflection utility for accessing game internals
- `InstanceMaker` - Helper for creating game object instances

#### 6. Action System
- `MonsterApplyPowerAction` - Custom action for applying powers to monsters
- Integrated with game's action management system

## Important Resources

### 1. SlLibrary
Located at `.SlayTheSpireLibrary/`, this directory contains:
- **Decompiled game code** in `com/` - Always reference this first to understand game mechanics
- **Documentation** in `BaseMod.wiki/` and `StSLib.wiki/
- **Tutorials** in `SlayTheSpireModTutorials-master/`
- **Localization files** in `本地化文本/` - Contains cards.json, relics.json, etc.
- **Monster Cards** in `崩坠卡牌/` - Source cards for implementation reference

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

### 3. Progress Tracking
**red_card_progress.md** - Current implementation status:
- **Total Cards**: 48 cards to implement from red path
- **Completed**: 19 cards implemented and working
- **Remaining**: 30 cards pending implementation
- Detailed progress table with card mapping and status

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

4. **SpireField Usage for Monster Management**
   - Use `AbstractMonsterAddFieldPatch` for adding fields to monsters
   - Avoid HashMap-based solutions to prevent ID conflicts
   - Each monster gets its own CardPlayer instance via SpireField

### Coding Standards (from newrules.md)

1. **Action System**
   ```java
   // ✅ Correct
   AbstractDungeon.actionManager.addToTop(new DamageAction(target, damageInfo));
   AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target, source, power));

   // ✅ Monster-specific action
   AbstractDungeon.actionManager.addToBottom(new MonsterApplyPowerAction(target, source, power));

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
- **UTF-8 encoding** requirement for Chinese comments

**Important**: Update Steam.path in pom.xml to match your installation.

## Current Implementation Status

### Card Implementation Progress
- **19/48 cards implemented** from red path
- **Monster card system** fully functional
- **Universal card pool** implemented
- **SpireField-based monster management** working correctly

### Key Features Implemented
1. ✅ Monster card playing system
2. ✅ Universal card configuration
3. ✅ SpireField-based monster management
4. ✅ Custom action system for monster powers
5. ✅ Comprehensive patch system
6. ✅ Progress tracking and documentation

### Upcoming Tasks
1. Implement remaining 29 red path cards
2. Add green path cards (if needed)
3. Optimize card selection algorithms
4. Add more monster-specific configurations
5. Improve UI rendering for monster cards

## Common Development Tasks

### Adding New Monster Cards
1. Create new card class in `src/main/java/EveryMonsterPlayCard/cards/monster/`
2. Follow naming convention: `En{OriginalCardName}`
3. Extend appropriate base class (`MonsterAttackCard`, `MonsterSkillCard`, `MonsterPowerCard`)
4. Add card to `MonsterCardConfig.initializeUniversalCardPool()`
5. Update progress in `red_card_progress.md`

### Creating New Patches
1. Check `.SlayTheSpireLibrary` for target class structure
2. Use appropriate `@SpirePatch` annotation
3. Follow patch patterns in existing files
4. Add null checks and safety guards
5. Use SpireField for monster-specific data

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
4. **red_card_progress.md** - Current implementation status
5. **Main mod file** - Event subscriptions and initialization
6. **MonsterCardManager** - Core system architecture
7. **MonsterCardConfig** - Monster-card mapping configuration
8. **AbstractMonsterAddFieldPatch** - SpireField implementation

## PVP System Integration

### Overview
The project is integrating advanced UI rendering and intelligent card playing systems from the **PVP in the Spire** mod located in `.SlayTheSpireLibrary/pvp玩家系统/`. This will transform our basic monster card system into a comprehensive, visualized combat experience.

### Integration Plan
**Reference Document**: `PVP系统分析与搬运指导.md` - Contains detailed analysis and implementation guide.

### Key Components to Integrate

#### 1. UI Rendering System
**Location**: `.SlayTheSpireLibrary/pvp玩家系统/pvp_in_the_spire/ui/BattleUI/`

**Components**:
- `BattleCardPanel.java` - Complete battle information display
- `CardBox.java` - Core card display with intelligent intent detection
- `MonsterEnergyPanel.java` - Energy visualization
- `MonsterRelicPanel.java` - Relic display
- `MonsterPotionPanel.java` - Potion display

**Integration Steps**:
1. Copy components to `src/main/java/EveryMonsterPlayCard/ui/BattleUI/`
2. Update package names and imports
3. Remove network dependencies
4. Integrate with MonsterCardManager

#### 2. Card Management System
**Location**: `.SlayTheSpireLibrary/pvp玩家系统/pvp_in_the_spire/player_management/`

**Components**:
- `PlayerCardManager.java` - Card ID mapping and management
- `CardRecorder.java` - Card list recorder for UI updates

**Integration Benefits**:
- Avoids duplicate card transmission
- Real-time hand synchronization
- Intelligent card intent detection

#### 3. Event-Driven Architecture
**Location**: `.SlayTheSpireLibrary/pvp玩家系统/pvp_in_the_spire/pvp_api/`

**Components**:
- `Communication.java` - Event registration and sending (to be simplified)
- `FightProtocol.java` - Event handling (to be localized)

**Integration Strategy**:
- Remove network layer
- Create `LocalEventBus` for local event management
- Maintain event-driven architecture without networking

#### 4. Automation System
**Reference**: `.SlayTheSpireLibrary/pvp玩家系统/单机模式核心修改方案.md`

**Components**:
- `AutoPlayManager.java` - Intelligent card playing management
- `FixedSequencePlayer.java` - Fixed sequence card playing
- `LocalEventHandler.java` - Local event processing

**Integration Benefits**:
- Intelligent card selection strategies
- Automated combat flow
- Configurable play patterns

### Implementation Phases

#### Phase 1: UI System Integration (2 days)
- [ ] Copy BattleCardPanel and related components
- [ ] Remove network dependencies
- [ ] Integrate with MonsterCardManager
- [ ] Test card display functionality

#### Phase 2: Event System Transformation (2 days)
- [ ] Create LocalEventBus
- [ ] Define core event classes
- [ ] Refactor MonsterCardPlayer to use events
- [ ] Remove network patch dependencies
- [ ] Test event propagation

#### Phase 3: Automation Upgrade (2 days)
- [ ] Integrate AutoPlayManager
- [ ] Implement fixed sequence card playing
- [ ] Add intelligent card strategies
- [ ] Implement energy management
- [ ] Test automated combat

#### Phase 4: System Optimization (1 day)
- [ ] Performance testing and optimization
- [ ] Memory leak checking
- [ ] Compatibility testing
- [ ] Documentation updates
- [ ] Final integration testing

### Key Advantages of Integration

#### Visual Enhancement
- **Real-time card display** above monsters
- **Energy, relic, and potion visualization**
- **Intelligent intent detection and display**
- **Complete combat process visualization**

#### System Architecture Upgrade
- **Event-driven architecture** instead of simple logic
- **Intelligent card playing strategies**
- **Fixed sequence play support**
- **Automated combat mode**

#### Development Experience
- **Enhanced debugging information**
- **Complete logging system**
- **Configurable play strategies**
- **Significantly improved extensibility**

### Single-Player Adaptation

The PVP system is designed for network multiplayer. For our single-player project:

**Remove**:
- Network communication layers
- Socket server dependencies
- Remote player synchronization

**Adapt**:
- `Communication.java` → `LocalEventBus`
- `FightProtocol.java` → `LocalEventHandler`
- Network events → Local events

**Enhance**:
- Add automation managers
- Implement AI decision making
- Create fixed sequence players

### Technical Considerations

#### Performance Impact
- **CPU Usage**: +5-10% due to additional rendering
- **Memory Usage**: +2-5% for UI components
- **Optimization**: Delayed rendering, card caching

#### Compatibility
- **Game Versions**: Test across multiple Slay the Spire versions
- **Mod Compatibility**: Ensure no conflicts with other mods
- **Performance**: Monitor frame rates on lower-end systems

#### Quality Assurance
- **Unit Testing**: Core logic unit tests
- **Integration Testing**: UI component integration tests
- **Performance Testing**: Memory and rendering performance tests
- **Compatibility Testing**: Multi-version game testing

### Code Examples

#### Enhanced MonsterCardPlayer with UI
```java
public class MonsterCardPlayer {
    // Original fields
    private AbstractMonster monster;

    // New UI components
    public BattleCardPanel battleCardPanel;
    public PlayerCardManager cardManager;
    public CardRecorder cardRecorder;

    // Automation
    public AutoPlayManager autoPlayManager;
    public boolean autoPlayEnabled = true;

    public void onHandUpdate(HandUpdateEvent event) {
        cardRecorder.updateHand(event.cardIds);
        if (autoPlayManager != null) {
            autoPlayManager.onHandUpdated(event.cardIds);
        }
    }

    public void playCards() {
        if (autoPlayManager != null) {
            autoPlayManager.executeTurn();
        } else {
            playCardsSimple(); // Fallback
        }
    }
}
```

#### Local Event Bus
```java
public class LocalEventBus {
    private HashMap<Class<?>, ArrayList<Consumer<?>>> handlers;

    public <T> void registerEvent(Class<T> eventType, Consumer<T> handler) {
        handlers.computeIfAbsent(eventType, k -> new ArrayList<>())
                .add((Consumer<?>) handler);
    }

    public <T> void sendEvent(T event) {
        // Process event locally
        processEvent(event);
    }
}
```

### Risk Mitigation

#### Technical Risks
| Risk | Impact | Probability | Mitigation |
|------|--------|-------------|------------|
| UI Integration Conflicts | Medium | Medium | Phased integration, thorough testing |
| Performance Degradation | Low | Low | Optimize rendering, lazy loading |
| Memory Leaks | Medium | Low | Reference cleanup, memory monitoring |
| Compatibility Issues | High | Medium | Detailed testing, multi-version validation |

#### Implementation Risks
| Risk | Impact | Probability | Mitigation |
|------|--------|-------------|------------|
| Workload Overrun | High | Medium | Phased delivery, priority sorting |
| Skill Mismatch | Medium | Low | Detailed documentation, code comments |
| Time Constraints | High | Medium | Core features first, optional features later |

### Success Metrics

#### Functional Enhancements
- ✅ Monster hand real-time display
- ✅ Energy, relics, potions visualization
- ✅ Intelligent intent judgment display
- ✅ Complete combat process visualization

#### System Architecture
- ✅ Event-driven card playing logic
- ✅ Intelligent card playing strategies
- ✅ Fixed sequence play support
- ✅ Automated combat mode

#### Development Experience
- ✅ Better debugging information
- ✅ Complete logging system
- ✅ Configurable play strategies
- ✅ Significantly improved extensibility

## Important Notes

- **MUST read rules.md before modifying existing systems**
- **MUST read PVP系统分析与搬运指导.md before starting integration**
- Always build with `-Dfile.encoding=UTF-8` for Chinese comments
- **NEVER** use `rm -rf` or other destructive commands
- Comment out unused code instead of deleting
- **ALWAYS** commit to git after successful build and testing
- Verify field/method existence with MCP before using reflection
- Prefer simple, direct solutions over complex implementations
- Test all changes in actual game environment before committing
- Use SpireField for monster-specific data to avoid ID conflicts
- Follow the established card naming convention
- Update progress documentation when adding new cards
- **Priority**: UI system integration has highest priority
- **Testing**: Each component must be tested independently before integration