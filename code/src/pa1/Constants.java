package pa1;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Constants {

	public static String DATABASE_FILE = "data/database.txt";
	public static String DIFF_FILE = "data/DiffFile.txt";
    public static String GRAMS_FILE = "data/grams.txt";
  
    
    public static int[] BITS_PER_ELEMENT_ARRAY = { 4, 8, 10 };

    public static int[] SET_SIZE_ARRAY = {
        20000, 30000, 40000, 50000
    };
}

enum BloomFilterType
{
    FNV,
    NAIVE,
    RANDOM,
    MULTI
}