import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.swing.filechooser.FileSystemView;

import com.google.gson.Gson;


import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;



/**
 * tmp1
 */
public class tmp1 {
    public static void main(String[] args) {
        System.out.println("Hello world");
        File[] paths;
        FileSystemView fsv = FileSystemView.getFileSystemView();

        // returns pathnames for files and directory
        paths = File.listRoots();

        // for each pathname in pathname array
        for(File path:paths)
        {
            // prints file and directory paths
            System.out.println("Name: "+path);
            System.out.println("volume label: "+fsv.getSystemTypeDescription(path));
            System.out.println("\tTotal space: " + path.getTotalSpace());
            System.out.println("\tFree space: " + path.getFreeSpace());
            System.out.println();
 
        }


        try {
            File myFile = new File("file.txt");
            if (myFile.createNewFile()) {
              System.out.println("File created: " + myFile.getName());
            } else {
              System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        Scanner myScanner = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter a string to write to a file");

        String fileString = myScanner.nextLine();


        try {
            FileWriter myWriter = new FileWriter("file.txt");
            myWriter.write(fileString);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
        System.out.println("Reading from a file");
        try {
            File myObj = new File("file.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
              String data = myReader.nextLine();
              System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


        File fileToDelete = new File("filename.txt"); 
        if (fileToDelete.delete()) { 
            System.out.println("Deleted the file: " + fileToDelete.getName());
        } else {
            System.out.println("Failed to delete the file.");
        }


        try {
            Files.copy( Paths.get("saved.json"), Paths.get("object.json"), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        user john = new user("John", "Worker", 0, 192);

        Gson gson = new Gson();
        String jsonString = gson.toJson(john);

        try {
            Files.writeString( Paths.get("object.json"), jsonString);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


        try {
            String jString = Files.readString( Paths.get("object.json"));
            System.out.println(jString);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


        try {
            Files.deleteIfExists( Paths.get("object.json"));
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        try {
            Files.copy( Paths.get("saved.xml"), Paths.get("object.xml"), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        System.out.println("Input id:");
        int id = Integer.parseInt(myScanner.nextLine());
        System.out.println("Input message:");
        String Message = myScanner.nextLine();

        Serializer serializer = new Persister();
        Example example = new Example(Message, id);
        File result = new File("object.xml");

        try {
            serializer.write(example, result);
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


        try {
            File source = new File("object.xml");
            Example readExample = serializer.read(Example.class, source);
            readExample.Print();
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


        try {
            Files.deleteIfExists( Paths.get("object.xml"));
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


        try {
            System.out.println("Input path to file to zip:");
            String Path = myScanner.nextLine();
           

            String sourceFile = Path;

            FileOutputStream fos = new FileOutputStream("compressed.zip");
            ZipOutputStream zipOut = new ZipOutputStream(fos);
            File fileToZip = new File(sourceFile);
            FileInputStream fis = new FileInputStream(fileToZip);
            ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
            zipOut.putNextEntry(zipEntry);
            byte[] bytes = new byte[1024];
            int length;
            while((length = fis.read(bytes)) >= 0) {
                zipOut.write(bytes, 0, length);
            }
            zipOut.close();
            fis.close();
            fos.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
        try {
            String fileZip = "compressed.zip";
           
            byte[] buffer = new byte[1024];
            ZipInputStream zis = new ZipInputStream(new FileInputStream(fileZip));
            ZipEntry zipEntry = zis.getNextEntry();
            while (zipEntry != null) {
                File newFile = new File("_"+zipEntry);
                if (zipEntry.isDirectory()) {
                    if (!newFile.isDirectory() && !newFile.mkdirs()) {
                        throw new IOException("Failed to create directory " + newFile);
                    }
                } else {
                    
                    // write file content
                    FileOutputStream fos = new FileOutputStream(newFile);
                    int len;
                    while ((len = zis.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                    }
                    fos.close();
                }

                BasicFileAttributes attr = Files.readAttributes(Paths.get("_"+zipEntry), BasicFileAttributes.class);
                System.out.println("_"+zipEntry);
                System.out.println("creationTime: " + attr.creationTime());
                System.out.println("lastAccessTime: " + attr.lastAccessTime());
                System.out.println("lastModifiedTime: " + attr.lastModifiedTime());

                System.out.println("isDirectory: " + attr.isDirectory());
                System.out.println("isOther: " + attr.isOther());
                System.out.println("isRegularFile: " + attr.isRegularFile());
                System.out.println("isSymbolicLink: " + attr.isSymbolicLink());
                System.out.println("size: " + attr.size());

                Files.deleteIfExists( Paths.get("_"+zipEntry));

            zipEntry = zis.getNextEntry();
            }
            zis.closeEntry();
            zis.close();

            Files.deleteIfExists( Paths.get("compressed.zip"));

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        myScanner.close();
    }
}