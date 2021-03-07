package lucene;

import java.io.IOException;

import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.StopwordAnalyzerBase;

public class MyAnalyzer extends StopwordAnalyzerBase{
	public final static String DEFAULT_STOPWORD_FILE = "stopwords.txt";
	protected final CharArraySet stemExclusionSet;

	public static CharArraySet getDefaultStopSet(){
		return DefaultSetHolder.DEFAULT_STOP_SET;
	}
	  
	private static class DefaultSetHolder {
		static final CharArraySet DEFAULT_STOP_SET;

		static {
			try {
				DEFAULT_STOP_SET = loadStopwordSet(false, 
						MyAnalyzer.class, 
	        		DEFAULT_STOPWORD_FILE, 
	        		"#");
			} catch (IOException ex) {
				// default set should always be present as it is part of the
				// distribution (JAR)
				throw new RuntimeException("Unable to load default stopword set");
			}
	    }
	}
	  
	public MyAnalyzer() {
		this(DefaultSetHolder.DEFAULT_STOP_SET);
	}
		  
	public MyAnalyzer(CharArraySet stopwords){
		this(stopwords, CharArraySet.EMPTY_SET);
	}
	
	public MyAnalyzer(CharArraySet stopwords, CharArraySet stemExclusionSet){
	    super(stopwords);
	    this.stemExclusionSet = CharArraySet.unmodifiableSet(CharArraySet.copy(stemExclusionSet));
	}
	
	@Override
	protected TokenStreamComponents createComponents(String paramString) {
		// TODO Auto-generated method stub
		return null;
	}

}
