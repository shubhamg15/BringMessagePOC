package com.bring.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bring.service.PackageService;
import com.bring.service.TokenService;

/**
 * Handles requests for the application file upload requests
 */
@Controller
public class FileUploadController {

	@Autowired
	private PackageService packageService;

	@Autowired
	private TokenService tokenService;
	/**
	 * Upload single file using Spring Controller
	 */
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public @ResponseBody
	String uploadFileHandler(@RequestParam("token") String createTokenId,
			@RequestParam("file") MultipartFile file) {
		System.out.println(createTokenId);
		if (file.isEmpty() || createTokenId == null) {
			return "Failed to upload! Empty values not allowed.";
		}
		try {
			if (tokenService.isValidCreateToken(createTokenId)) {
				byte[] bytes = file.getBytes();
				// Creating the directory to store file
				String rootPath = System.getProperty("catalina.home");
				File dir = new File(rootPath + File.separator + "tmpFiles");
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath()
						+ File.separator + createTokenId);
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

			}
		} catch (Exception e) {
			return "You failed to upload " + createTokenId + " => "
					+ e.getMessage();
		}
		return "You successfully uploaded file=" + createTokenId;
	}
	
	@RequestMapping(value = "/uploadFile/{tokenId}", method = RequestMethod.GET)
	public String uploadFileHandler(@PathVariable("tokenId") String tokenId, Model model){
		if(!tokenService.isValidCreateToken(tokenId)){
			return "error";	
		}
		model.addAttribute("createToken", tokenId);
		return "upload";
	}
}