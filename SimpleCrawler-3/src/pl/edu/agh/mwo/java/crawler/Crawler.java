package pl.edu.agh.mwo.java.crawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;



public class Crawler {

	protected String urlToProcess;	
	protected TextExtractor textExtractor;
	protected ConsoleResultsPrinter consoleResultsPrinter;
	protected List<SentenceFinder> sentenceFinders; 
	
	public Crawler(String urlToProcess, TextExtractor textExtractor, ConsoleResultsPrinter consoleResultsPrinter) 
	{
		this.urlToProcess = urlToProcess;
		this.textExtractor = textExtractor;
		this.consoleResultsPrinter = consoleResultsPrinter;
		sentenceFinders = new ArrayList<>();
	}
	
	public void addSentenceFinder(SentenceFinder sentenceFinder)
	{
		sentenceFinders.add(sentenceFinder);
	}
	
	public void run()
	{	
		Document doc = null;
		try {
			doc = Jsoup.connect(urlToProcess).get();
		} catch (IOException e) {
		}
		
		List<String> sentences = textExtractor.extractText(doc);
		
		for(SentenceFinder sentenceFinder : sentenceFinders)
			consoleResultsPrinter.print(sentenceFinder.findSentences(sentences), urlToProcess);
	}


}
