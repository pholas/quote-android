package group.study.mobile.ibm.com.quote;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by jack on 10/12/16.
 */
public class QuoteLab {
    /**
     * An array of sample (Quote) items.
     */
    public static final List<Quote> QUOTES = new ArrayList<>();
    ;
    /**
     * A map of sample (Quote) items, by ID.
     */
    public static final Map<String, Quote> QUOTE_MAP = new HashMap<>();

    static {
        // Add some sample items.

        addItem(new Quote(String.valueOf(0), "非淡泊无以明志，非宁静无以致远。", "诸葛亮"));

        addItem(new Quote(String.valueOf(1), "鞠躬尽瘁，死而后已。", "诸葛亮"));

        addItem(new Quote(String.valueOf(2), "盲人骑瞎马，夜半临深也。", "刘义庆"));

        addItem(new Quote(String.valueOf(3), "成人不自在，自在不成人。", "宋，罗大经"));

        addItem(new Quote(String.valueOf(4), "蚍蜉撼大树，可笑不自量。", "韩愈"));

        addItem(new Quote(String.valueOf(5), "出污泥而不染，濯清涟而不妖。", "周敦颐"));

        addItem(new Quote(String.valueOf(6), "日薄西山，气息奄奄，人命危浅，朝不虑夕。", "李密"));

        addItem(new Quote(String.valueOf(7), "循序渐进，熟读而精思。", "朱熹"));

        addItem(new Quote(String.valueOf(8), "群贤毕至，少长咸集。", "王羲之"));

        addItem(new Quote(String.valueOf(9), "先天下之忧而忧，后天下之乐而乐。", "范仲淹"));

        addItem(new Quote(String.valueOf(10), "醉翁意不在酒，在乎山水之间也。", "欧阳修"));

        addItem(new Quote(String.valueOf(11), "明日复明日，明日何其多？日日待明日，万事成蹉跎。", "明，文嘉"));

    }

    private static void addItem(Quote item) {
        QUOTES.add(item);
        QUOTE_MAP.put(item.id, item);
    }

    public static Quote getRandomQuote() {
        int count = QUOTES.size();
        int index = (int) (Math.random() * count);
        return QUOTES.get(index);
    }

    public static Quote getQuoteById(String id) {
        for (Quote quote : QUOTES) {
            if (quote.id.equals(id)) {
                return quote;
            }
        }
        return null;
    }

    public static int getIndexById(String id) {

        for (int i = 0; i < QUOTES.size(); i++) {
            if (QUOTES.get(i).id.equals(id)) {
                return i;
            }
        }
        return -1;
    }

    public static void updateQuote(Quote quote) {
        int index = getIndexById(quote.id);
        if (index != -1) {
            QUOTES.set(getIndexById(quote.id), quote);
        }
    }

    public static void createQuote(Quote quote) {
        quote.id = UUID.randomUUID().toString();
        QUOTES.add(quote);
    }

}
