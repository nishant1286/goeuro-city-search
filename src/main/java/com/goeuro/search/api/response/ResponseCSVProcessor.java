package com.goeuro.search.api.response;

import au.com.bytecode.opencsv.CSVWriter;
import com.goeuro.search.api.response.exception.ResultProcessingException;
import lombok.extern.slf4j.Slf4j;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;

/**
 * Created by nishantgupta on 22/2/16.
 */
@Slf4j
public abstract class ResponseCSVProcessor<T> implements ResponseProcessor<T> {

    private final String fileNameDelimiter = "_";
    private final String resultFolderLocation;

    public ResponseCSVProcessor(String resultFolderLocation){
        this.resultFolderLocation = resultFolderLocation;
    }

    @Override
    public void process(String query, Collection<T> results) throws ResultProcessingException{
        CSVWriter writer = null ;
        try {
            log.info("Handling response...");
            String fileLocation = this.resultFolderLocation + getResultFileName(query);
            log.info("Search results file output location: {}", fileLocation);
            writer = new CSVWriter(new FileWriter(fileLocation));
            writer.writeNext(getHeadings());
            for(T result: results) {
                String[] record = convertResult(result);
                writer.writeNext(record);
            }
            log.info("Request completed. Please find search requests at mentioned file location.");
        }catch (IOException e){
            log.error("Error processing result:", e);
            throw new ResultProcessingException("Error processing result:", e);
        }finally {
            if(writer != null){
                try {
                    writer.close();
                } catch (IOException e) {
                    throw new ResultProcessingException("Error closing writer:", e);
                }
            }
        }
    }

    protected abstract String[] convertResult(T result);

    protected abstract String[] getHeadings();

    private String getResultFileName(String query){
        return query + fileNameDelimiter + System.currentTimeMillis();
    }


}
