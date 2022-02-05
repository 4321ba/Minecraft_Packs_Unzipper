import java.io.File;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

// heavily inspired by: http://tutorials.jenkov.com/java-zip/zipfile.html

public class Unzip {
    public static void main(String[] args) throws Exception {
    
        if (args.length != 1)
            throw new java.lang.IllegalArgumentException("There should be exactly one argument, usage: \"java -jar Unzip.jar my_zip_file.zip\"");
            
        String zip_file_str = args[0];
        // https://stackoverflow.com/questions/4545937/java-splitting-the-filename-into-a-base-and-extension
        String[] zip_file_str_split = zip_file_str.split("\\.(?=[^\\.]+$)");
        String unzip_folder_str = Paths.get(zip_file_str_split[0]).normalize().toString();
        
        if (zip_file_str_split.length < 2 || !"zip".equals(zip_file_str_split[1]))
            throw new java.lang.IllegalArgumentException("The file needs to have the \".zip\" extension");
        
        System.out.println("Zip file path: " + zip_file_str);
        ZipFile zip_file = new ZipFile(zip_file_str);

        java.util.Enumeration<? extends ZipEntry> entries = zip_file.entries();

        while (entries.hasMoreElements()) {
            ZipEntry entry = entries.nextElement();
            
            // we will make the directory only when there is a file needed to be placed inside
            // because PackSquash (probably) leaves out folders altogether
            if (entry.isDirectory())
                continue;
            
            String dest_file_str = unzip_folder_str + File.separator + entry.getName();
            java.nio.file.Path dest_file_path = Paths.get(dest_file_str);
            
            // not really tested if this is actually safe, this should be against zip slip attacks
            if (!dest_file_path.normalize().toString().startsWith(unzip_folder_str))
                throw new java.io.IOException("File output path is outside of the root folder (zip slip problem): " + dest_file_str);
            
            // making all the folders needed
            File file = new File(dest_file_path.getParent().toString());
            file.mkdirs();
            
            System.out.println("Unpacking file: " + dest_file_str);
            java.nio.file.Files.copy(zip_file.getInputStream(entry), dest_file_path, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
        }
        
    }
}    
