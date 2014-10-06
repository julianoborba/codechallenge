package br.juliano.code.challenge.resource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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

    private static final String SERVER_UPLOAD_LOCATION_FOLDER = "C://DBfood/";

    @POST
    @Path("/thisDBFood")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(
            @FormDataParam("file") InputStream fileInputStream,
            @FormDataParam("file") FormDataContentDisposition contentDispositionHeader) {

        String filePath = SERVER_UPLOAD_LOCATION_FOLDER + contentDispositionHeader.getFileName();
        saveFile(fileInputStream, filePath);
        String output = "";

        FeedDBService feed = new FeedDBService();
        boolean nhomnhom = feed.withThisFood(SERVER_UPLOAD_LOCATION_FOLDER);

        if (nhomnhom) {
            output = "File saved into : " + filePath + ".";
            return Response.status(200).entity(output).build();
        } else {
            output = "File wasn't consumed. Try again.";
            deleteFile(filePath);
            return Response.status(200).entity(output).build();
        }

    }

    private void saveFile(InputStream uploadedInputStream, String serverLocation) {

        try {

            OutputStream outpuStream = new FileOutputStream(new File(serverLocation));
            int read = 0;
            byte[] bytes = new byte[1024];
            outpuStream = new FileOutputStream(new File(serverLocation));
            while ((read = uploadedInputStream.read(bytes)) != -1)
                outpuStream.write(bytes, 0, read);
            outpuStream.flush();
            outpuStream.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    private boolean deleteFile(String filePath) {

        return new File(filePath).delete();

    }

}