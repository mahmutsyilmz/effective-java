import java.util.*;

public class Union {

	// Generic method
	public static <T> Set<T> union(Set<T> set1, Set<T> set2) {
		Set<T> result = new HashSet<>(set1);
		result.addAll(set2);
		return result;
	}
	

	// access modifier <conventionlar> dönüşTipi method_name(parameters..)
	public static <T extends Number> int summary(T value1, T value2)
	{
		return value1.intValue() + value2.intValue();
	}
	

	
	public static void main(String[] args) {
		
		Set<String> fenerbahce = Set.of("Uğur Boral", "Caner", "Pelkas");
		Set<String> turkey = Set.of("Uğur Boral", "Hasan Şaş", "Burak", "Rüştü");
		
		
		
		Set<String> union = union(fenerbahce, turkey);
		
		System.out.println(union);
		
		
		summary(44.4, 12);
		

	}
	
}