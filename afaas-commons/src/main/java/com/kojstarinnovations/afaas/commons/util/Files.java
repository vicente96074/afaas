package com.kojstarinnovations.afaas.commons.util;

import lombok.SneakyThrows;

import java.io.*;

/**
 * Files class
 * <p>
 * Class to manage the files
 *
 * @Author: Augusto Vicente
 */
public class Files {

    /**
     * Get the input stream of the report
     *
     * @param filename name of the report
     * @return input stream of the report
     * @throws FileNotFoundException if the report is not found
     */
    private InputStream inputStreamReport(String filename) throws FileNotFoundException {
        InputStream jrxmlInputStream = getClass().getClassLoader().getResourceAsStream("reports/" + filename);

        if (jrxmlInputStream == null) {
            throw new FileNotFoundException("El archivo jrxml no se encontró: " + filename);
        }

        return jrxmlInputStream;
    }

    /**
     * Get the input stream of the report
     *
     * @param filename name of the report
     * @return input stream of the report
     * @throws FileNotFoundException if the report is not found
     */
    public static InputStream getInputStreamReport(String filename) throws FileNotFoundException {
        return getInstance().inputStreamReport(filename);
    }

    /**
     * Get the path of the logo
     *
     * @return path of the logo
     * @throws IOException if the logo is not found
     */
    public static String getPathLogo() throws IOException {
        return getInstance().pathLogo();
    }

    /**
     * Get the path of the logo
     *
     * @return path of the logo
     * @throws IOException if the logo is not found
     */
    private String pathLogo() throws IOException {
        File tempImgFile = File.createTempFile("logo", ".jpg");
        tempImgFile.deleteOnExit();

        try (OutputStream out = new FileOutputStream(tempImgFile)) {
            InputStream imgInputStream = getClass().getClassLoader().getResourceAsStream("img/logo.jpg");
            if (imgInputStream == null) {
                throw new FileNotFoundException("La imagen del logo no se encontró");
            }

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = imgInputStream.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }
        return tempImgFile.getAbsolutePath();
    }

    /**
     * Get the path of the logo
     *
     * @return path of the logo
     * @throws IOException if the logo is not found
     */
    private String otherFilePath(String fileName) throws IOException {

        String[] parts = fileName.split("\\.");

        String name = parts[0];
        String extension = parts[1];

        File tempFile = File.createTempFile(name, "." + extension);
        tempFile.deleteOnExit();

        try (OutputStream out = new FileOutputStream(tempFile)) {
            InputStream imgInputStream = getClass().getClassLoader().getResourceAsStream("reports/" + fileName);
            if (imgInputStream == null) {
                throw new FileNotFoundException("Archivo " + name + " no se encontró");
            }

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = imgInputStream.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }

        return tempFile.getAbsolutePath();
    }

    /**
     * Get the path of the logo
     *
     * @return path of the logo
     */
    @SneakyThrows
    public static String getOtherFilePath(String fileName) {

        try {
            return getInstance().otherFilePath(fileName);
        } catch (IOException ex) {
            throw new FileNotFoundException("El archivo no se encontró: " + fileName);
        }
    }

    /**
     * Singleton
     */
    private Files(){

    }
    public static Files getInstance(){
        if(instance == null){
            instance = new Files();
        }
        return instance;
    }
    private static Files instance;
}
