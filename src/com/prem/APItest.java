package com.prem;

import java.io.ByteArrayOutputStream;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import org.w3c.dom.Node;

public class APItest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String soapEndpointUrl = "http://fl.bizlog.in/webservice/WebService.asmx?op=GetUserDetails";
        String soapAction = "http://www.webserviceX.NET/GetInfoByCity";

//        callSoapWebService(soapEndpointUrl, soapAction);
        apiCall();

	}
	public static SOAPBody getBody(SOAPMessage soapMessage) throws SOAPException
	{
		SOAPPart soapPart = soapMessage.getSOAPPart();
		SOAPEnvelope soapEnvelope = soapPart.getEnvelope();
		SOAPBody soapBody = soapEnvelope.getBody();
		return soapBody;
	}

	private static String toString(SOAPMessage message) throws Exception
	{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		message.writeTo(out);
		return new String(out.toByteArray());
	}
	
	private static void addChildElement1(SOAPElement parentElement, String elementName, String elementText) throws SOAPException {
		SOAPElement childElement = parentElement.addChildElement(elementName);

		childElement.addTextNode(elementText);
	}
	 private static void apiCall() {
		 
		 try
			{
				MessageFactory messageFactory = MessageFactory.newInstance();
				SOAPMessage soapRequest = messageFactory.createMessage();
				SOAPBody requestBody = getBody(soapRequest);

				String serverURI = "http://tempuri.org/";

				SOAPElement requestElement = requestBody.addChildElement("buyBackService", "", serverURI);
				addChildElement(requestElement, "Consignmentnumber", "DMR-MOB-113-HGRSU");
				addChildElement(requestElement, "Consumername", "prem");
				addChildElement(requestElement, "Phonenumber", "prem");
				addChildElement(requestElement, "Product", "prem");
				addChildElement(requestElement, "Model", "prem");
				addChildElement(requestElement, "Brand", "prem");
				addChildElement(requestElement, "Barcode", "prem");
				addChildElement(requestElement, "IMEInumber", "prem");
				addChildElement(requestElement, "Refnumber", "prem");
				addChildElement(requestElement, "City", "prem");
				addChildElement(requestElement, "Pincode", "prem");

				
				soapRequest.getMimeHeaders().addHeader("SOAPAction", "http://tempuri.org/buyBackService");

				soapRequest.saveChanges();

				System.out.println("Server request:" + toString(soapRequest));

				SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
				SOAPConnection soapConnection = soapConnectionFactory.createConnection();




				String url = "https://demo.bizlog.in/TicketStatus/TicketStatus.asmx?op=buyBackService";
				SOAPMessage soapResponse = soapConnection.call(soapRequest, url);
				System.out.println("Server response:" + toString(soapResponse));

				SOAPBody responseBody = getBody(soapResponse);

				Node statusElement = responseBody.getFirstChild().getFirstChild().getFirstChild().getFirstChild().getFirstChild();

			}catch(Exception e){
				
			}
		 
	 }
	 private static void addChildElement(SOAPElement parentElement, String elementName, String elementText) throws SOAPException {
			SOAPElement childElement = parentElement.addChildElement(elementName);

			childElement.addTextNode(elementText);
		}
	
	   
	    
	    
	    



}
