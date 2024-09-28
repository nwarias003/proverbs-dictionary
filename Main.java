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
    private String hawaiianProverb;
    private String englishProverb;
    private String hawaiianExplanation;
    private String englishExplanation;

    // Creating constructor for initializing object.
    public Proverb(String hawaiianProverb, String englishProverb, String hawaiianExplanation, String englishExplanation) {
        this.hawaiianProverb = hawaiianProverb;
        this.englishProverb = englishProverb;
        this.hawaiianExplanation = hawaiianExplanation;
        this.englishExplanation = englishExplanation;
    }

    // Getter method for Hawaiian proverb.
    public String getHawaiianProverb() {
        return hawaiianProverb;
    }

    // Getter method for English proverb.
    public String getEnglishProverb() {
        return englishProverb;
    }

    // Getter method for Hawaiian explanation.
    public String getHawaiianExplanation() {
        return hawaiianExplanation;
    }

    // Getter method for English explanation.
    public String getEnglishExplanation() {
        return englishExplanation;
    }

    // toString to represent the Proverb class object.
    @Override
    public String toString() {
        return "Hawaiian Proverb: " + hawaiianProverb + "\n" +
               "English Translation: " + englishProverb + "\n" +
               "Hawaiian Explanation: " + hawaiianExplanation + "\n" +
               "English Explanation: " + englishExplanation + "\n";
    }
}

class BalancedTree {
    private class TreeNode {
        Proverb proverb;
        TreeNode left, right;
        int height;  

        TreeNode(Proverb proverb) {
            this.proverb = proverb;
            this.height = 1; 
        }
    }

    private TreeNode root;

    public void insert(Proverb proverb) {
        root = insert(root, proverb);
    }

    private TreeNode insert(TreeNode node, Proverb proverb) {
        
        //
        if (node == null) {
            return new TreeNode(proverb);
        }
        if (proverb.getHawaiianProverb().compareTo(node.proverb.getHawaiianProverb()) < 0) {
            node.left = insert(node.left, proverb);
        } else {
            node.right = insert(node.right, proverb);
        }

        // 
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        //
        int balance = getBalance(node);

        // 
        if (balance > 1 && proverb.getHawaiianProverb().compareTo(node.left.proverb.getHawaiianProverb()) < 0) {
            return rightRotate(node);
        }
       
        // 
        if (balance < -1 && proverb.getHawaiianProverb().compareTo(node.right.proverb.getHawaiianProverb()) > 0) {
            return leftRotate(node);
        }
        
        // 
        if (balance > 1 && proverb.getHawaiianProverb().compareTo(node.left.proverb.getHawaiianProverb()) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        
        //
        if (balance < -1 && proverb.getHawaiianProverb().compareTo(node.right.proverb.getHawaiianProverb()) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // 
    private TreeNode rightRotate(TreeNode y) {
        TreeNode x = y.left;
        TreeNode T2 = x.right;

        // 
        x.right = y;
        y.left = T2;

        // 
        y.height = 1 + Math.max(getHeight(y.left), getHeight(y.right));
        x.height = 1 + Math.max(getHeight(x.left), getHeight(x.right));

        // 
        return x;
    }

    // 
    private TreeNode leftRotate(TreeNode x) {
        TreeNode y = x.right;
        TreeNode T2 = y.left;

        // 
        y.left = x;
        x.right = T2;

        // 
        x.height = 1 + Math.max(getHeight(x.left), getHeight(x.right));
        y.height = 1 + Math.max(getHeight(y.left), getHeight(y.right));

        // 
        return y;
    }

    // 
    private int getHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    // 
    private int getBalance(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    public boolean member(String hawaiianProverb) {
        return member(root, hawaiianProverb);
    }

    private boolean member(TreeNode node, String hawaiianProverb) {
        if (node == null) {
            return false;
        }
        int cmp = hawaiianProverb.compareTo(node.proverb.getHawaiianProverb());
        if (cmp == 0) {
            return true;
        } else if (cmp < 0) {
            return member(node.left, hawaiianProverb);
        } else {
            return member(node.right, hawaiianProverb);
        }
    }

    // Returns all proverbs in the binary search tree in alphatbetical order.
    public List<Proverb> getAllProverbs() {
        List<Proverb> proverbs = new ArrayList<>();
        inOrderTraversal(root, proverbs);
        return proverbs;
    }

    // Method that performs in-order traversal of the binary search tree.
    private void inOrderTraversal(TreeNode node, List<Proverb> proverbs) {
        if (node == null) {
            return;
        }
        inOrderTraversal(node.left, proverbs);
        proverbs.add(node.proverb);
        inOrderTraversal(node.right, proverbs);
    }

    // Search through the binary search tree to see if it has a specific word in either Hawaiian or English.
    public List<Proverb> searchByWord(String word) {
        List<Proverb> proverbs = new ArrayList<>();
        searchByWord(root, word, proverbs);
        return proverbs;
    }

    // Method that recursively searches for proverbs by word.
    private void searchByWord(TreeNode node, String word, List<Proverb> proverbs) {
        if (node == null) {
            return;
        }
        searchByWord(node.left, word, proverbs);
        if (node.proverb.getHawaiianProverb().contains(word) || node.proverb.getEnglishProverb().contains(word)) {
            proverbs.add(node.proverb);
        }
        searchByWord(node.right, word, proverbs);
    }
}


class ProverbCollection {
    
    // Intializes the Balanced Search Tree for storing proverbs.
    private BalancedTree proverbTree = new BalancedTree();

    // Intializes the HashMap to index each Hawaiian word.
    private Map<String, List<Proverb>> hawaiiWordIndex = new HashMap<>();

    // Intializes the HashMap to index each English word.
    private Map<String, List<Proverb>> engWordIndex = new HashMap<>();

    // Inserts proverb to collection and index the words in both languages.
    public void addProverb(Proverb proverb) {
        proverbTree.insert(proverb);
        indexProverb(proverb);
    }

    // Checks if proverb exists in the collection by Hawaiian phrase.
    public boolean containsProverb(String hawaiianPhrase) {
        return proverbTree.member(hawaiianPhrase);
    }

    // Indexes proverb both Hawaiian and English for quick search by word.
    private void indexProverb(Proverb proverb) {
 
        // Splits and adds each word to into Hawaiian index.
        String[] hawaiianWords = proverb.getHawaiianProverb().split("\\s+");
        for (String word : hawaiianWords) {
            hawaiiWordIndex.computeIfAbsent(word, k -> new ArrayList<>()).add(proverb);
        }

        // Splits and adds each word into English index.
        String[] englishWords = proverb.getEnglishProverb().split("\\s+");
        for (String word : englishWords) {
            engWordIndex.computeIfAbsent(word, k -> new ArrayList<>()).add(proverb);
        }
    }

    // Retrieves all proverbs stored within balanced search tree.
    public List<Proverb> getAllProverbs() {
        return proverbTree.getAllProverbs();
    }
    
    // Returns a list of proverbs that contain specified Hawaiian word.
    public List<Proverb> meHua(String word) {
        return hawaiiWordIndex.getOrDefault(word, List.of());
    }


    // Returns a list of proverbs that contain specified Englishword.
    public List<Proverb> withWord(String word) {
        return engWordIndex.getOrDefault(word, List.of());
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

