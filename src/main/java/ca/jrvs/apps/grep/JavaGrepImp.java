package ca.jrvs.apps.grep;

import sun.nio.cs.UTF_32;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JavaGrepImp implements JavaGrep {
    private String regex;
    private String rootPath;
    private String outFile;

    public static void main(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("Usage: regex rootPath outFile");
        }
        JavaGrepImp javaGrepImp = new JavaGrepImp();
        javaGrepImp.setRegex(args[0]);
        javaGrepImp.setRootPath(args[1]);
        javaGrepImp.setOutFile(args[2]);
        try {
            javaGrepImp.process();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void process() throws IOException {
        List<String> matchedLines = new ArrayList<String>();
        List<File> directoryListing = listFilesStream(getRootPath());
        for (File file : directoryListing) {
            for (String line : readLinesStream(file)) {
                if (containsPattern(line)) {
                    matchedLines.add(line);
                }
            }
        }
        writeToFile(matchedLines);
    }

    @Override
    public List<File> listFiles(String rootDir) {
        List<File> listofFiles = new ArrayList<File>();
        File directory = new File(rootDir);
        for (File dirFile : directory.listFiles()) {
            if (dirFile.isFile()) {
                listofFiles.add(dirFile);
            } else {
                listofFiles.addAll(listFiles(dirFile.getAbsolutePath()));
            }
        }
        return listofFiles;
    }

    /**
     *  List of files present in dir and subdir using Stream API
     * @param rootDir
     * @return List of files
     * @throws IOException
     */
    public List<File> listFilesStream(String rootDir) throws IOException {


        List<File> listofFiles = Files.walk(Paths.get(rootDir))
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .collect(Collectors.toList());
        return listofFiles;
    }

    @Override
    public List<String> readLines(File inputFile) throws IOException {
        List<String> Lines = new ArrayList<String>();
        String str;
        try {
            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            while ((str = br.readLine()) != null) {
                Lines.add(str);
            }
            br.close();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return Lines;
    }

    /**
     * Read lines in a file using Stream API
     * @param inputFile
     * @return list of lines in a file
     * @throws IOException
     */
    public List<String> readLinesStream(File inputFile) throws IOException {
        List<String> Lines = new ArrayList<String>();
        BufferedReader crunchifyBufferReader = null;
        try {

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(new FileInputStream(inputFile), "UTF-8"));
            Lines = in.lines().collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return Lines;
    }

    @Override
    public boolean containsPattern(String line) {

        String regex = getRegex();
        if (line.matches(regex)) {
            return true;
        }
        return false;
    }

    @Override
    public void writeToFile(List<String> lines) throws IOException {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(getOutFile()));
            for (String line : lines) {
                bw.write(line);
            }
            bw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getRegex() {
        return regex;
    }

    @Override
    public void setRegex(String regex) {
        this.regex = regex;
    }

    @Override
    public String getRootPath() {
        return rootPath;
    }

    @Override
    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    @Override
    public String getOutFile() {
        return outFile;
    }

    @Override
    public void setOutFile(String outFile) {
        this.outFile = outFile;
    }


}
