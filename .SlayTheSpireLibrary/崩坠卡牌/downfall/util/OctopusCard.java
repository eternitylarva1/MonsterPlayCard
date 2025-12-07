package downfall.util;

import com.megacrit.cardcrawl.monsters.AbstractMonster;
import downfall.cards.OctoChoiceCard;
import java.util.ArrayList;

public interface OctopusCard {
  ArrayList<OctoChoiceCard> choiceList();
  
  void doChoiceStuff(AbstractMonster paramAbstractMonster, OctoChoiceCard paramOctoChoiceCard);
}


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfal\\util\OctopusCard.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */