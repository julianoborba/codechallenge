package br.juliano.code.challenge.resource;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.juliano.code.challenge.service.FeedDBService;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

@Path("/upload")
public class FileUploadResource {

	private static final String SERVER_UPLOAD_LOCATION_FOLDER = "/tmp/";

	@POST
	@Path("/thisDBFood")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(
			@FormDataParam("file") InputStream fileInputStream,
			@FormDataParam("file") FormDataContentDisposition contentDispositionHeader) {

		String filePath = SERVER_UPLOAD_LOCATION_FOLDER + contentDispositionHeader.getFileName();
		saveFile(fileInputStream, filePath);

		boolean nhomnhom = new FeedDBService().withThisFood(filePath);

		String output = "";
		if (nhomnhom) {
			output = "File saved into : " + filePath + ".";
		} else {
			output = "File wasn't consumed. Try again.";
			deleteFile(filePath);
		}

		return Response.status(200).entity(output).build();
	}

	private long saveFile(InputStream uploadedInputStream, String serverLocation) {

		try {
			
			java.nio.file.Path path = Paths.get(serverLocation);
			return Files.copy(uploadedInputStream, path);
			
		} catch (IOException ex) {
			ex.printStackTrace();
			return 0;
		}

	}

	private boolean deleteFile(String filePath) {

		try {
			
			java.nio.file.Path file = Paths.get(filePath);
			return Files.deleteIfExists(file);
			
		} catch (IOException ex) {
			ex.printStackTrace();
			return false;
		}

	}

}