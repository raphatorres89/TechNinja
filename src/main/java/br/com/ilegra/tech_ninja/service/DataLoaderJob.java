package br.com.ilegra.tech_ninja.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.ilegra.tech_ninja.converters.ReportConverter;
import br.com.ilegra.tech_ninja.models.Report;
import br.com.ilegra.tech_ninja.utils.FileUtils;
import br.com.ilegra.tech_ninja.utils.StringUtils;

@DisallowConcurrentExecution
public class DataLoaderJob implements Job {

	private static final Logger log = LoggerFactory.getLogger(DataLoaderJob.class);
	
	private static final String PATH_IN = "/data/in";
	private static final String PATH_OUT = "/data/out";
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		log.info("Running data loader job");
		
		File pathIn = FileUtils.getFile(System.getProperty("user.home") + PATH_IN);
		File pathOut = FileUtils.getFile(System.getProperty("user.home") + PATH_OUT);
		
		List<File> files = readDatFiles(pathIn);
		
		if (!files.isEmpty()) {
			
			List<Report> reports = convertFilesToReport(files);
			
			saveReportsAndDeleteOldDat(reports, pathOut);

		} else {
			log.info("No .dat files in path {}", pathIn.getAbsolutePath());
		}
		
	}

	private void saveReportsAndDeleteOldDat(List<Report> reports, File pathOut) {
		for (Report report : reports) {
			
			saveReport(pathOut, report);
			
			deleteOldDat(report);
		}
	}
	
	private void deleteOldDat(Report report) {
		log.info("Deleting old .dat");
		
		if (!report.getFile().delete()) {
			log.error("Error deleting file {}", report.getFile().getAbsolutePath());
		}
		
	}

	private void saveReport(File pathOut, Report report) {
		log.info("Saving reports");
		
		Path path = Paths.get(pathOut.getAbsolutePath() + File.separator + report.getFile().getName());
		
		try (BufferedWriter writer = Files.newBufferedWriter(path)) {
			
		    writer.write(report.toString());
		    writer.close();
		    
		} catch (IOException e) {
			log.error("Error creating report {}", report);
			e.printStackTrace();
		}
	}

	private List<Report> convertFilesToReport(List<File> files) {
		List<Report> reports = new ArrayList<Report>();
		
		for (File file : files) {
			try {
				String content = StringUtils.convertFileToString(file);
				
				reports.add(ReportConverter.convertDataToReport(content, file));
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return reports;
	}

	private List<File> readDatFiles(File pathIn) {
		log.info("Loading files from folder {}", pathIn);
		
		List<File> dotFiles = new ArrayList<File>();
		
		try {
			Stream<Path> paths = Files.walk(Paths.get(pathIn.toURI()));
			paths
				.filter(Files::isRegularFile)
				.forEach(f -> {
					if (f.toFile().getName().endsWith(".dat")) {
						dotFiles.add(f.toFile());
					}
				});
			paths.close();
		} catch (IOException e) {
			log.error("Error reading folder {}", pathIn.getAbsolutePath());
			e.printStackTrace();
		}
		
		return dotFiles;
	}

}
