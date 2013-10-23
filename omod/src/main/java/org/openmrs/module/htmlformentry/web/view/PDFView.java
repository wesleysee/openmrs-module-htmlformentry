package org.openmrs.module.htmlformentry.web.view;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.APIAuthenticationException;
import org.openmrs.api.AdministrationService;
import org.openmrs.api.context.Context;
import org.openmrs.module.htmlformentry.HtmlFormEntryConstants;
import org.springframework.web.servlet.view.AbstractView;

public class PDFView extends AbstractView {
	
	protected final Log log = LogFactory.getLog(getClass());
	
	public PDFView() {
		setContentType("application/pdf");
	}
	
	@Override
	protected boolean generatesDownloadContent() {
		return true;
	}
	
	@Override
	protected void renderMergedOutputModel(@SuppressWarnings("rawtypes") Map model, HttpServletRequest request,
	                                       HttpServletResponse response)
	                                    		   throws Exception {
		// IE workaround: write into byte array first.
		ByteArrayOutputStream baos = createTemporaryOutputStream();
		
		Cookie sessionCookie = null;
		for (Cookie cookie : request.getCookies()) {
			if ("JSESSIONID".equals(cookie.getName())) {
				sessionCookie = cookie;
				break;
			}
		}
		
		if (sessionCookie == null) {
			throw new APIAuthenticationException();
		}
		
		generatePDF(model.get("url").toString(), sessionCookie, baos);
		
		// Flush to HTTP response.
		writeToResponse(response, baos);
	}
	
	public void generatePDF(String url, Cookie cookie, OutputStream os) {
		Process process = null;
		InputStream is = null;
		
		try {
			AdministrationService adminSvc = Context.getAdministrationService();
			String path = StringUtils.defaultString(
				adminSvc.getGlobalProperty(HtmlFormEntryConstants.PROP_WKHTMLTOPDF_PATH), "wkhtmltopdf");
			String extraArgs = StringUtils.defaultString(adminSvc
			        .getGlobalProperty(HtmlFormEntryConstants.PROP_WKHTMLTOPDF_ARGS));

			ProcessBuilder pb = new ProcessBuilder(path, extraArgs, "--page-size",
				adminSvc.getGlobalProperty(HtmlFormEntryConstants.PROP_PDF_PAGE_SIZE),
				"--cookie", cookie.getName(), cookie.getValue(), url, "-");
			process = pb.start();
			
			is = process.getInputStream();
			
			byte[] buf = new byte[512];
			int n;
			while ((n = is.read(buf)) != -1) {
				os.write(buf, 0, n);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if (is != null) {
				try {
					is.close();
				}
				catch (IOException e) {}
			}
			if (process != null) {
				process.destroy();
			}
		}
	}
	
}
