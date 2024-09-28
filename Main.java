/**
 * This program implements a dictionary of Hawaiian proverbs from the book 
 * Ōlelo noʻeau by Mary Kawena Pukui along with their translation 
 * in English, as well as explanations of the sayings in Hawaiian and English. 
 * The program makes use of a balanced search tree to store the proverbs in an orderly
 * manner and the hashmaps to index the Hawaiian and English words found 
 * within the proverbs for efficient retrieval. The program also makes use of five 
 * custom functions that insert, check, retrieve, and return the proverbs and specific 
 * words. The program first initializes a balanced search tree to store the proverbs,
 *  and two hashmaps to index the words found within the Hawaiian  and English 
 * versions of the proverbs. The program then calls the function addProverb
 * to insert a proverb in the balanced search tree and indexes each word in the proverb using the
 * hashmaps to allow for quick lookups of proverbs containing specific words. Next,
 * it calls the containsProverbs function to check if a proverb exists in the collection by 
 * searching through the balanced search tree. Then the program calls the function 
 * getAllProverbs  to retrieve the list of all of the proverbs that are stored in the balanced search
 * tree and outputs it. Lastly, the program calls the meHua and withWord functions to retrieve
 * and output all of the proverbs containing a specific Hawaiian word by looking up the word in
 * the Hawaiian word index. It will repeat the same process for all proverbs containing a specific
 * English word  by looking up the word in the English word index.
 *
 * @author Nigel Arias and Andre Miller 
 */

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;


class Proverb {
    private String hawaiianPhrase;
    private String englishPhrase;
    private String hawaiianExplanation;
    private String englishExplanation;

    public Proverb(String hawaiianPhrase, String englishPhrase, String hawaiianExplanation, String englishExplanation) {
        this.hawaiianPhrase = hawaiianPhrase;
        this.englishPhrase = englishPhrase;
        this.hawaiianExplanation = hawaiianExplanation;
        this.englishExplanation = englishExplanation;
    }

    public String getHawaiianPhrase() {
        return hawaiianPhrase;
    }

    public String getEnglishPhrase() {
        return englishPhrase;
    }

    public String getHawaiianExplanation() {
        return hawaiianExplanation;
    }

    public String getEnglishExplanation() {
        return englishExplanation;
    }

    @Override
    public String toString() {
        return "Hawaiian Phrase: " + hawaiianPhrase + "\n" +
               "English Translation: " + englishPhrase + "\n" +
               "Hawaiian Explanation: " + hawaiianExplanation + "\n" +
               "English Explanation: " + englishExplanation + "\n";
    }
}








public class Main {
    public static void main(String[] args) {
        
        // Create a collection to store proverbs.
        ProverbCollection collection = new ProverbCollection();
        // Sample proverbs.
        Proverb proverb1 = new Proverb("ʻAʻohe hana nui ke alu ʻia", "No task is too big when done together by all", "Hoʻoikaika kēia ʻōlelo i ka mana o ka hui pū ʻana a me ka hui pū ʻana. Hōʻike ia e hiki ke hoʻokō ʻia nā hana paʻakikī loa ke hana like nā kānaka me ka lokahi.", "This proverb emphasizes the power of teamwork and collaboration. It suggests that even the most challenging tasks can be accomplished when people work together in harmony.");
        
        Proverb proverb2 = new Proverb("E mālama pono i ka ʻāina", " Take good care of the land", "Hōʻike kēia ʻōlelo ʻōlelo i ka waiwai Hawaiʻi o ka mālama ʻāina, ʻo ia hoʻi ka mālama ʻana a me ka mālama ʻana i ka ʻāina. Hōʻike ia i ke koʻikoʻi o ka mālama kaiapuni a me ka pilina pili ma waena o ke kanaka a me ke ʻano.", " This proverb reflects the Hawaiian value of mālama ʻāina, which means to care for and nurture the land. It underscores the importance of environmental stewardship and the reciprocal relationship between humans and nature.");
        
        Proverb proverb3 = new Proverb("He aliʻi ka ʻāina, he kauwā ke kanaka", "The land is a chief; man is its servant", "Hōʻike kēia ʻōlelo ʻōlelo i ka manaʻo o ka poʻe Hawaiʻi i ka ʻāina, me ka nānā ʻana iā ia he aliʻi a he aliʻi paha. Hōʻike ia i ka manaʻo he poʻe mālama ʻāina ke kanaka, lawelawe a mahalo ʻia e like me ke alakaʻi kūlana kiʻekiʻe.", "This proverb highlights the reverence Hawaiians have for the land, viewing it as a chief or ruler. It conveys the idea that humans are caretakers of the land, serving and respecting it as they would a high-ranking leader.");

        // Insert proverbs into collection.
        collection.addProverb(proverb1);
        collection.addProverb(proverb2);
        collection.addProverb(proverb3);



        // Print out all proverbs
        System.out.println("All Proverbs:");
        for (Proverb proverb : collection.getAllProverbs()) {
            System.out.println(proverb);
        }

        // Print proverbs that contain the word “pono” in Hawaiian
        System.out.println("\nProverbs containing the word 'pono' (in Hawaiian):");
        for (Proverb proverb : collection.meHua("pono")) {
            System.out.println(proverb);
        }
        // Print proverbs that contain the word “care” in English
        System.out.println("\nProverbs containing  the word 'care' (in English):");
        for (Proverb proverb : collection.withWord("care")) {
            System.out.println(proverb);
        }
    }
}

