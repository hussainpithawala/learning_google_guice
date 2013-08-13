package org.packt.client.producer;

import static org.packt.client.utils.FlightUtils.parseDate;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.Set;
import java.util.TreeSet;

import au.com.bytecode.opencsv.CSVReader;

public class CSVSupplier {
	Set<SearchResponse> searchResponses = new TreeSet<SearchResponse>();
	
	private String csvPath = "./flightCSV/";
	
	public CSVSupplier(String csvPath) {
		this.csvPath = csvPath;
	}

	public Set<SearchResponse> getResults() {
		if(searchResponses.isEmpty())
			loadCSVFiles();
		return searchResponses;
	}

	private void loadCSVFiles() {
		String fileName;
		File folder = new File(csvPath);
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {

			if (listOfFiles[i].isFile()) {
				File file = listOfFiles[i];
				if (file.getName().endsWith(".csv") || file.getName().endsWith(".CSV")) {
					CSVReader reader;
					try {
						reader = new CSVReader(new FileReader(file));

						String[] nextLine;
						int counter = 0;
						
						while ((nextLine = reader.readNext()) != null) {

							SearchResponse flightSearchRS = new SearchResponse();

							flightSearchRS.setFlightNumber(nextLine[0]);
							flightSearchRS.setDepartureLocation(nextLine[1]);
							flightSearchRS.setArrivalLocation(nextLine[2]);
							flightSearchRS.setValidDate(parseDate(nextLine[3]));
							flightSearchRS.setDepartTime(nextLine[4]);
							flightSearchRS.setFlightDuration(Double
									.parseDouble(nextLine[5]));
							flightSearchRS.setFare(Float
									.parseFloat(nextLine[6]));

							searchResponses.add(flightSearchRS);

							counter++;
						}
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					} catch (ParseException e) {
						e.printStackTrace();
					}

				}
			}
		}
	}


}
