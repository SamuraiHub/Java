package spelling;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

/** 
 * Represents a node in a Trie
 * @author UC San Diego Intermediate Programming MOOC Team
 *
 */
class TrieNode {
	private HashMap<Character, TrieNode> children; 
	private String text;  // Maybe omit for space
	private boolean isWord;
	
	/** Create a new TrieNode */
	public TrieNode()
	{
		children = new HashMap<Character, TrieNode>();
		text = "";
		isWord = false;
	}
	
	/** Create a new TrieNode given a text String to store in it */
	public TrieNode(String text)
	{
		this();
		this.text = text;
	}
	
	/** Return the TrieNode that is the child when you follow the 
	 * link from the given Character 
	 * @param c The next character in the key
	 * @return The TrieNode that character links to, or null if that link
	 *   is not in the trie.
	 */
	public TrieNode getChild(Character c)
	{
		return children.get(c);
	}
	
	/** Return wheather the TrieNode contains the given character(key) (which links to a child TrieNode)
	 * @param c The next character in the key
	 * @return True if the given character(key) is contained as a child  in the TrieNode. False otherwise
	 */
	public boolean containsChild(Character c)
	{
		return children.containsKey(c);
	}
	
	/** Inserts this character at this node.
	 * Returns the newly created node, if c wasn't already
	 * in the trie.  If it was, it does not modify the trie
	 * and returns null.
	 * @param c The character that will link to the new node
	 * @return The newly created TrieNode, or null if the node is already 
	 *     in the trie.
	 */
	public boolean insert(Character c)
	{
		if(!children.containsKey(c))
		{	
		   TrieNode next = new TrieNode(text + c.toString());
		   children.put(c, next);
		   return true;
		}
		return false;
	}
	
	/** Return the text string at this node */
    public String getText()
	{
		return text;
	}
	
    /** Set whether or not this node ends a word in the trie. */
	public void setEndsWord(boolean b)
	{
		isWord = b;
	}
	
	/** Return whether or not this node ends a word in the trie. */
	public boolean endsWord()
	{
		return isWord;
	}
	
	/** Return the set of characters that have links from this node */
	public Set<Character> getValidNextCharacters()
	{
		return children.keySet();
	}
	
	/** Return the set of values of characters that have links from this node */
	public Collection<TrieNode> getValidNextValues()
	{
		return children.values();
	}

}

