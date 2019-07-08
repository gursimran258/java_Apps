package ca.jrvs.apps.grep;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Stream;

public interface JavaGrep {


    /**
     * Top level search flow
     * @throws IOException
     */
    void process() throws IOException;

    /**
     * Traverse a given directory and return all
     * files
     * @param rootDir input directory
     * @return files under the rootDir
     */
    List<File> listFiles (String rootDir);


    /**
     * Read a file and return all the limes
     * Explain fileReader, BufferedReader
     * and character encoding
     * @param inputFile file to be read
     * return lines
     * @throws IllegalArgument Exception if a given input
     * file is not a file
     */
    List<String> readLines (File inputFile) throws IOException;

    /**
     * Check if a line contains the regex pattern
     * (passed by user)
     * @param line input string
     * @return true if there is a match
     */
    boolean containsPattern(String line);

    /**
     * Write lines to a file
     *
     * Explore: FileOutputSteam, OutputStreamWriter and BufferedWriter
     *
     * @params lines matched line
     * throws IOException if write failes
     */
    void writeToFile(List<String> lines) throws IOException;

    String getRootPath();

    void setRootPath(String rootPath);

    String getRegex();

    void setRegex(String regex);

    String getOutFile();

    void setOutFile(String outFile);

}
