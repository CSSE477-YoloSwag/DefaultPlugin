package servlet;

import protocol.HttpRequest;
import protocol.HttpResponse;
import protocol.HttpResponseBuilder;
import protocol.Keywords;
import protocol.Protocol;

public class DefaultServletManager extends AServletManager {
	
	private AHttpServlet defaultServlet;

	public DefaultServletManager(String filePath) {
		super(filePath);
	}

	@Override
	public void init() {
		// Create default servlet
		this.defaultServlet = new DefaultServlet(filePath);
	}

	@Override
	public void destroy() {
		// Tear down default servlet
		this.defaultServlet.destroy();
	}
	
	@Override
	public HttpResponse handleRequest(HttpRequest request) {
		HttpResponseBuilder responseBuilder = new HttpResponseBuilder();

		if (request.getMethod() == Protocol.getProtocol().getStringRep(Keywords.GET)) {
			this.defaultServlet.doGet(request, responseBuilder);
		} else if (request.getMethod() == Protocol.getProtocol().getStringRep(Keywords.HEAD)) {
			this.defaultServlet.doHead(request, responseBuilder);
		} else if (request.getMethod() == Protocol.getProtocol().getStringRep(Keywords.POST)) {
			this.defaultServlet.doPost(request, responseBuilder);
		} else if (request.getMethod() == Protocol.getProtocol().getStringRep(Keywords.PUT)) {
			this.defaultServlet.doPut(request, responseBuilder);
		} else if (request.getMethod() == Protocol.getProtocol().getStringRep(Keywords.DELETE)) {
			this.defaultServlet.doDelete(request, responseBuilder);
		}
		
		// TODO: verify that response is okay?
		
		return responseBuilder.generateResponse();
	}

}