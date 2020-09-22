package com.example.connection;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

import org.springframework.stereotype.Component;

@Component
public class ConnectionImpl implements ConnectionIntf{
	
	public static Map<String, String> mergedMap;
	
	private static Map<String, String> getSortedMap(Entry<String, String> entry, Entry<String, String> innerEntry) {

		Map<String, String> tempMap = new HashMap();

		if (entry.getKey().equalsIgnoreCase(innerEntry.getKey())) {
			tempMap.put(entry.getValue(), innerEntry.getValue());

		} else if (entry.getKey().equalsIgnoreCase(innerEntry.getValue())) {
			tempMap.put(entry.getValue(), innerEntry.getKey());
		} else if (entry.getValue().equalsIgnoreCase(innerEntry.getKey())) {
			tempMap.put(entry.getKey(), innerEntry.getValue());

		} else if (entry.getValue().equalsIgnoreCase(innerEntry.getValue())) {
			tempMap.put(entry.getKey(), innerEntry.getKey());

		}
		System.out.println("getSortedMap :::tempMap ::" + tempMap);

		return tempMap;
	}

	@Override
	public String checkCityConnected(String origin, String destination) throws Exception {
		
		System.out.println("\n=======Check City Connection ==> Origin ::["+origin+"] Destination ::["+destination+"]");
		
		Map<String, String> updatedMap = new HashMap<String, String>();
		Map<String, String> internalMap = new HashMap<String, String>();

		
		if(origin == null || "".equals(origin) || destination == null || "".equals(destination)) {
			System.out.println("=======NOT CONNECTED");
			return "no";
		}
		else {
			
			try {
			    String delimiter = ",";
			    Map<String, String> cityMap = new HashMap<String, String>();
			   
			    Scanner scanner = new Scanner(new File("src/main/resources/city.txt"));
			    String line;

			    while (scanner.hasNextLine()) {
			    	line = scanner.nextLine();
			    	if (line.contains(delimiter)) {
					    String key = line.split(delimiter)[0].trim();
					    String value = line.split(delimiter)[1].trim();
					    cityMap.put(key, value);
					    
					} else {
					    break;
					}
			    	
			    }
			    System.out.println("cityMap:"+cityMap);
			    
				for (Map.Entry<String, String> mapElement : cityMap.entrySet()) {

					String key = mapElement.getKey();
					String value = mapElement.getValue();

					if (origin.equalsIgnoreCase(key) || origin.equalsIgnoreCase(value)) {
						updatedMap.put(key, value);
					}

					if (destination.equalsIgnoreCase(key) || destination.equalsIgnoreCase(value)) {
						updatedMap.put(key, value);
					}
				}

				System.out.println("updatedMap:" + updatedMap);

				Iterator<Map.Entry<String, String>> entries = updatedMap.entrySet().iterator();
				Iterator<Map.Entry<String, String>> innerEntries = updatedMap.entrySet().iterator();
				while (entries.hasNext()) {

					Map.Entry<String, String> entry = entries.next();
	
					// innerEntries.next();
					innerEntries = entries;

					while (innerEntries.hasNext()) {
						Map.Entry<String, String> innerEntry = innerEntries.next();
	
						Map<String, String> tempMap = getSortedMap(entry, innerEntry);
						internalMap.putAll(tempMap);

					}
				}
				System.out.println("Internal MAP :: " + internalMap);

				//System.out.println("Updated MAP :: " + updatedMap);

				mergedMap = new HashMap<String, String>(internalMap);

				updatedMap.forEach((key, value) -> mergedMap.merge(key, value,
						(v1, v2) -> v1.equalsIgnoreCase(v2) ? v1 : v1 + "," + v2));

				System.out.println("Final Merged MAP :: " + mergedMap);

				if ((mergedMap.get(origin) != null
						&& (mergedMap.get(origin).contains(origin) || mergedMap.get(origin).contains(destination)))
						|| (mergedMap.get(destination) != null && (mergedMap.get(destination).contains(origin)
								|| mergedMap.get(destination).contains(destination)))) {

					System.out.println("=======CONNECTED");
					return "yes";
				}
			    
			} catch (IOException e) {
			    e.printStackTrace();
			    System.out.println("=======NOT CONNECTED");
			    return "no";
			}
			
			System.out.println("=======NOT CONNECTED");
			return "no";
			
		}
		
		
	}

}
