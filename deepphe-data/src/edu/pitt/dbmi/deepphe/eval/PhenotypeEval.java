package edu.pitt.dbmi.deepphe.eval;
import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

public class PhenotypeEval {
	public static boolean STRICT_VALUE_CALCULATION = false;
	public static boolean PRINT_RECORD_LEVEL_STATS = false;
	public static void main(String[] args) throws IOException {
		// compare two files
		if(args.length >= 2){
			File gold = null, candidate = null;
			for(String s: args){
				if("-strict".equals(s)){
					STRICT_VALUE_CALCULATION = true;
				}else if("-print".equals(s)){
					PRINT_RECORD_LEVEL_STATS = true;
				}else if(gold  == null){
					gold = new File(s);
				}else if(candidate == null){
					candidate = new File(s);
				}
			}
			PhenotypeEval pe = new PhenotypeEval();
			pe.evaluate(gold,candidate);
		}else{
			System.err.println("Usage: java PhenotypeEval [-print|-strict] <gold .bsv file> <candidate .bsv file>");
		}
	}
	public enum Confusion {
		TP,FP,FN,TN
	}
	
	/**
	 * record data 
	 * @author tseytlin
	 *
	 */
	
	private static class Record {
		private static final String I = "\\|";
		private static final String FS = ";";
		private static List<String> headers,valueHeaders;
		private Map<String,String> content;
		private String id;
		private Confusion confusion;
		private Record pairedRecord;
		
		public static Record loadRecord(String l) {
			Record r = new Record();
			int i = 0;
			for(String s: l.split(I)){
				String key = headers.get(i++);
				String val = s.trim();
				r.addField(key,val);
			}
			return r;
		}

		private void addField(String key, String val) {
			if(content == null)
				content = new LinkedHashMap<String, String>();
			content.put(key,val);
			
		}

		public String getId() {
			if(id == null){
				id = createId();
			}
			return id;
		}
		public static boolean hasHeaders(){
			return headers != null;
		}

		public static void loadHeaders(String l) {
			headers = new ArrayList<String>();
			for(String s: l.split(I)){
				headers.add(s.trim());
			}
		}

		public String createId() {
			StringBuffer b = new StringBuffer();
			for(String hd: headers){
				if(isIdField(hd)){
					for(String v: getValues(hd)){
						b.append(v+" ");
					}
				}
			}
			return b.toString().trim();
		}

		public static boolean isIdField(String hd) {
			return hd.startsWith("*");
		}
		public static boolean isIgnoreField(String hd) {
			return hd.startsWith("-");
		}

		public Confusion getConfusion() {
			return confusion;
		}

		public void setConfusion(Confusion confusion) {
			this.confusion = confusion;
		}

		public Record getPairedRecord() {
			return pairedRecord;
		}

		public void setPairedRecord(Record pairedRecord) {
			this.pairedRecord = pairedRecord;
		}

		public void print(PrintStream out) {
			out.println(getConfusion()+": "+getId());
			for(String hd: getValueHeaders()){
				List<String> gold = getValues(hd);
				List<String> pred = Collections.EMPTY_LIST;
				switch (getConfusion()) {
				case TP:
					pred = getPairedRecord().getValues(hd);
					break;
				case FP:
					gold = Collections.EMPTY_LIST;
					pred = getValues(hd);
					break;
				default:
					break;
				}
				out.println(hd+"\t gold: "+gold+"\t pred: "+pred+"\t score: "+compare(gold,pred));
			}
			out.println("Weighted Score: "+getWeightedScore()+"\n");
		}

		public double getWeightedScore() {
			if(pairedRecord != null){
				double score = 0;
				int total =  getValueHeaders().size();
				for(String hd: getValueHeaders()){
					score += compare(getValues(hd),pairedRecord.getValues(hd));
				}
				return score/ total;
			}
			return 1.0;
		}
		
		private double compare(List<String> val1, List<String> val2) {
			// if strict calculation, do 1 or 0 match
			if(STRICT_VALUE_CALCULATION)
				return val1.size() == val2.size() && val1.containsAll(val2)?1:0;
			
			double score = 0;
			for(String v: val1){
				if(val2.contains(v)){
					score ++;
				}
			}
			double t = val1.size() + (val2.size() - score);
			// if all values are empty, then we have a match :)
			if(t == 0)
				return 1.0;
			return score / t;
		}

		public List<String> getValues(String hd) {
			List<String> vals = new ArrayList<String>();
			String str = content.get(hd);
			if(str != null){
				if(str.contains(FS)){
					for(String s: str.split(FS)){
						vals.add(s.trim());
					}
					Collections.sort(vals);
				}else{
					vals.add(str);
				}
			}
			return vals;
		}

		public List<String> getValueHeaders(){
			if(valueHeaders == null){
				valueHeaders = new ArrayList<String>();
				for(String h : headers){
					if(!isIgnoreField(h) && !isIdField(h)){
						valueHeaders.add(h);
					}
				}
			}
			return valueHeaders;
		}
		
	}


	private Map<String, Record> loadAnnotations(File file) throws IOException {
		Map<String,Record> map = new LinkedHashMap<String, PhenotypeEval.Record>();
		BufferedReader reader = new BufferedReader(new FileReader(file));
		boolean headerLine = true;
		for(String l=reader.readLine();l != null; l = reader.readLine()){
			if(headerLine){
				Record.loadHeaders(l);
				headerLine = false;
			}else{
				Record record = Record.loadRecord(l);
				map.put(record.getId(),record);
			}
		}
		reader.close();
		return map;
	}

	


	/**
	 * evaluate phenotype of two BSV files
	 * @param file1
	 * @param file2
	 * @throws IOException 
	 */
	
	private void evaluate(File file1, File file2) throws IOException {
		Map<String,Record> goldAnnotations = loadAnnotations(file1);
		Map<String,Record> candidateAnnotations = loadAnnotations(file2);
		System.out.println("Gold File:\t"+file1.getAbsolutePath());
		System.out.println("Candidate File:\t"+file2.getAbsolutePath());
		System.out.println("");
		double TP = 0,FP = 0 ,FN = 0;
		
		// iterate over gold standard
		List<Record> records = new ArrayList<PhenotypeEval.Record>();
		for(String id: goldAnnotations.keySet()){
			Record gold = goldAnnotations.get(id);
			Record candidate = candidateAnnotations.get(id);
			// if we have a candidate with the same (like the same span), we have a TP
			if(candidate != null){
				gold.setConfusion(Confusion.TP);
				gold.setPairedRecord(candidate);
				TP += gold.getWeightedScore();
			// if we don't have a candidate, then we have a FN	
			}else{
				gold.setConfusion(Confusion.FN);
				FN ++;
			}
			records.add(gold);	
		}
		// now lets look at FPs
		for(String id: candidateAnnotations.keySet()){
			if(!goldAnnotations.containsKey(id)){
				Record r = candidateAnnotations.get(id);
				r.setConfusion(Confusion.FP);
				records.add(r);
				
				FP ++;
			}
		}
		
		// display individual counts
		if(PRINT_RECORD_LEVEL_STATS){
			for(Record r: records){
				r.print(System.out);
			}
		}
		
		// caluclate overall stats
		/*
		 *  precision = tp / (tp + fp)
		 *  recall = tp / (tp + fn)
		 *  f-score = (precision * recall) / precision + recall)
		 */
		
		double precision = TP / (TP+ FP);
		double recall = TP / (TP+ FN);
		double Fscore = (precision*recall)/(precision + recall);
		
		
		System.out.println("\n\n=================================================");
		System.out.println("Total number of gold annotations: "+goldAnnotations.size());
		System.out.println("Total number of candidate annotations: "+candidateAnnotations.size());
		
		System.out.println("TP:\t"+TP);
		System.out.println("FP:\t"+FP);
		System.out.println("FN:\t"+FN);
		System.out.println("");
		System.out.println("Precision: "+precision);
		System.out.println("Recall: "+recall);
		System.out.println("F-Score: "+Fscore);
	}

}
