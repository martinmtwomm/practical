package io.practical.p0001;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

public class P_0001 {

	public static void main(String[] args) throws IOException {
		String basePath = new File("").getAbsolutePath() + "/../";
		addFilesToZip(basePath + "testzip123.zip", "text1.txt", "text2.txt", "text3.txt");
		unzip("testzip123.zip", "extractFolder", basePath);

	}

	public static FileSystem createZipFile(String zipFilename, boolean create) throws IOException {
		final Path path = Paths.get(zipFilename);
		final URI uri = URI.create("jar:file:" + path.toUri().getPath());

		final Map<String, String> env = new HashMap<>();
		if (create) {
			env.put("create", "true");
			env.put("encoding", "UTF-8");
		}
		return FileSystems.newFileSystem(uri, env);
	}

	public static void addFilesToZip(String zipFilename, String... filenames) throws IOException {

		try (FileSystem zipFileSystem = createZipFile(zipFilename, true)) {
			for (String filename : filenames) {
				if (Files.deleteIfExists(zipFileSystem.getPath(filename))) {
					System.err.printf("file %s already exists in zip -> deleting it...\n", filename);

				}
				Files.write(zipFileSystem.getPath(filename), ("content file " + filename).getBytes());

			}
		}
	}

	public static void unzip(String zipFilename, String destinationDirectory, String basePath) throws IOException {

		final Path pathDestinationDirectory = Paths.get(basePath + destinationDirectory);
		if (Files.notExists(pathDestinationDirectory)) {
			System.out.printf("Directory %s does not exist -> creating it now...\n\n\n", destinationDirectory);
			Files.createDirectories(pathDestinationDirectory);
		}

		try (FileSystem zipFileSystem = createZipFile(basePath + zipFilename, false)) {
			final Path root = zipFileSystem.getPath("/");

			Files.walkFileTree(root, new SimpleFileVisitor<Path>() {

				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
					final Path destFile = Paths.get(pathDestinationDirectory.toString(), file.toString());
					System.out.printf("Extracting file %s to %s\n", file, destFile);
//					Files.copy(file, destFile, StandardCopyOption.REPLACE_EXISTING);
					
					//changed file content before extracting
					String content = new String(Files.readAllBytes(file));
					content = content.replace("content", "replaced content");
					
					Files.write(destFile, content.getBytes());
					
					System.out.printf("Content of file %s is: %s \n\n", file, content);
					return FileVisitResult.CONTINUE;
				}

			});
		}
	}

}
