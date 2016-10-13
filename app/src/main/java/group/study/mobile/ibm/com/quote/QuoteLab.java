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
    public static final List<Quote> QUOTES = new ArrayList<>();;
    /**
     * A map of sample (Quote) items, by ID.
     */
    public static final Map<String, Quote> QUOTE_MAP = new HashMap<>();

    private static final int COUNT = 25;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(CreateQuote(i));
        }
    }

    private static void addItem(Quote item) {
        QUOTES.add(item);
        QUOTE_MAP.put(item.id, item);
    }

    private static Quote CreateQuote(int position) {
        return new Quote(String.valueOf(position), makeQuoteText(position), "Author " + position);
    }

    private static String makeQuoteText(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Quote: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore quote information here.");
        }
        return builder.toString();
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
