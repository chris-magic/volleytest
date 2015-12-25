package yfz.com.volleytest.network;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.concurrent.ConcurrentHashMap;

public class RequestMap {
    private static String ENCODING = "UTF-8";
    protected ConcurrentHashMap< String, String > urlParams;
    protected ConcurrentHashMap< String, FileWrapper > fileParams;

    public RequestMap () {
        init();
    }

    private void init () {
        urlParams = new ConcurrentHashMap<>();
        fileParams = new ConcurrentHashMap<>();
    }

    public void put ( String key, String value ) {
        if ( key != null && value != null ) {
            urlParams.put( key, value );
        }
    }

    public void put ( String key, File file ) {
        try {
            put( key, new FileInputStream( file ), file.getName() );
        } catch ( FileNotFoundException e ) {
            e.printStackTrace();
        }
    }

    public void put ( String key, InputStream stream, String fileName ) {
        put( key, stream, fileName, null );
    }

    public void put ( String key, InputStream stream, String fileName,
                      String contentType ) {
        if ( key != null && stream != null ) {
            fileParams.put( key, new FileWrapper( stream, fileName, contentType ) );
        }
    }

    public HttpEntity getEntity () {
        HttpEntity entity = null;
        if ( ! fileParams.isEmpty() ) {
            MultipartHttpEntity multipartEntity = new MultipartHttpEntity();
            for ( ConcurrentHashMap.Entry< String, String > entry : urlParams
                    .entrySet() ) {
                multipartEntity.addPart( entry.getKey(), entry.getValue() );
            }
            int currentIndex = 0;
            int lastIndex = fileParams.entrySet().size() - 1;
            for ( ConcurrentHashMap.Entry< String, FileWrapper > entry : fileParams
                    .entrySet() ) {
                FileWrapper file = entry.getValue();
                if ( file.inputStream != null ) {
                    boolean isLast = currentIndex == lastIndex;
                    if ( file.contentType != null ) {
                        multipartEntity.addPart( entry.getKey(),
                                file.getFileName(), file.inputStream,
                                file.contentType, isLast );
                    } else {
                        multipartEntity.addPart( entry.getKey(),
                                file.getFileName(), file.inputStream, isLast );
                    }
                }
                currentIndex++;
            }
            entity = multipartEntity;
        } else {
            entity = new StringHttpEntity( urlParams, ENCODING );
        }
        return entity;
    }

    public ConcurrentHashMap<String, String> getUrlParams() {
        return urlParams;
    }

    public void setUrlParams(ConcurrentHashMap<String, String> urlParams) {
        this.urlParams = urlParams;
    }

    private static class FileWrapper {
        public InputStream inputStream;
        public String fileName;
        public String contentType;

        public FileWrapper ( InputStream inputStream, String fileName,
                             String contentType ) {
            this.inputStream = inputStream;
            this.fileName = fileName;
            this.contentType = contentType;
        }

        public String getFileName () {
            if ( fileName != null ) {
                return fileName;
            } else {
                return "nofilename";
            }
        }
    }

}