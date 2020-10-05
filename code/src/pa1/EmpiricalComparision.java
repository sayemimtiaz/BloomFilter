package pa1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class EmpiricalComparision {
	//selects a particular key from GRAM_FILE with 1/10000 probability to use as an key
	Pair queryKeySelectionProbability =  new Pair(1,50000);

	BaseDifferential [] differentials;
	int numDifferentials = 5;

	EmpiricalComparision() throws Exception {
		differentials=new BaseDifferential[numDifferentials];

		differentials[0]=new BloomDifferential().createFilter(BloomFilterType.FNV);
		differentials[1]=new BloomDifferential().createFilter(BloomFilterType.RANDOM);
		differentials[2]=new BloomDifferential().createFilter(BloomFilterType.MULTI);
		differentials[3]=new BloomDifferential().createFilter(BloomFilterType.NAIVE);
		differentials[4]=new NaiveDifferential();
	}

	void comparePerformance() throws Exception {
		System.out.println("Comparision of Naive and Bloom Differentials");

		List<String> queryKeys = getRandomQueryKeys();

		for(String key: queryKeys)
		{
			for(BaseDifferential differential: differentials)
			{
				Instant start = Instant.now();
				String value = differential.retrieveRecord(key);
				Instant end = Instant.now();
				differential.totalElapsedTime+= Duration.between(start, end).toMillis();
			}
		}
		for(BaseDifferential differential: differentials)
		{
			double avrageElapsedTime=differential.totalElapsedTime/(double) queryKeys.size();
			System.out.println("Average time taken by "+ differential.getType()+": "+avrageElapsedTime);
		}
	}
	public static void main(String args[]) throws Exception {
		EmpiricalComparision comparision=new EmpiricalComparision();
		comparision.comparePerformance();
	}

	private  List<String> getRandomQueryKeys() {

		List<String> list = new ArrayList<>();
		Random random=new Random();

		try (Stream<String> stream = Files.lines(Paths.get(Constants.GRAMS_FILE))) {
			Iterator<String> iterator = stream.iterator();

			while(iterator.hasNext())
			{
				int randomNum=random.nextInt(queryKeySelectionProbability.second) +1;
				if(randomNum<=this.queryKeySelectionProbability.first)
				{
					list.add(iterator.next());
				}
				else
					iterator.next();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Number of queries: "+ list.size());
		return list;
	}
}
