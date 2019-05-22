package com.dce.util;

//
//import java.awt.Insets;
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.IOException;
//import java.io.StringReader;
//import java.net.MalformedURLException;
//import java.security.InvalidParameterException;
//import java.util.HashMap;
//import java.util.Map;
//
//import org.zefer.pd4ml.PD4Constants;
//import org.zefer.pd4ml.PD4ML;
//
public class GettingStarted2 {
	// protected int topValue = 10;
	// protected int leftValue = 20;
	// protected int rightValue = 10;
	// protected int bottomValue = 10;
	// protected int userSpaceWidth = 1300;
	//
	// public static void main(String[] args) {
	// try {
	// GettingStarted2 jt = new GettingStarted2();
	// Map<String, Object> map = new HashMap<String, Object>();
	// map.put("test", "hello world");
	// VelocityUtils velocityUtils = new VelocityUtils();
	// String html = velocityUtils.parse("abcd$test", map, null);
	//
	// jt.doConversion2(html, "e:/pd4ml11.pdf");
	// // FileUtils.readFileToString(new File(""));
	// // IOUtils.copy(new BufferedInputStream(null), new
	// BufferedOutputStream(null));
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	//
	// public void doConversion2(String htmlDocument, String outputPath)
	// throws InvalidParameterException, MalformedURLException, IOException {
	//
	// PD4ML pd4ml = new PD4ML();
	//
	// pd4ml.setHtmlWidth(this.userSpaceWidth); // set frame width of "virtual web
	// browser"
	//
	// // choose target paper format
	// pd4ml.setPageSize(pd4ml.changePageOrientation(PD4Constants.A4));
	//
	// // define PDF page margins
	// pd4ml.setPageInsetsMM(new Insets(this.topValue, this.leftValue,
	// this.bottomValue, this.rightValue));
	//
	// // source HTML document also may have margins, could be suppressed this way
	// // (PD4ML *Pro* feature):
	// pd4ml.addStyle("BODY {margin: 0}", true);
	//
	// // If built-in basic PDF fonts are not sufficient or
	// // if you need to output non-Latin texts, TTF embedding feature should help
	// // (PD4ML *Pro*)
	// pd4ml.useTTF("c:/windows/fonts", true);
	//
	// ByteArrayOutputStream baos = new ByteArrayOutputStream();
	// // actual document conversion from HTML string to byte array
	// pd4ml.render(new StringReader(htmlDocument), baos);
	// // if the HTML has relative references to images etc,
	// // use render() method with baseDirectory parameter instead
	// baos.close();
	//
	// System.out.println("resulting PDF size: " + baos.size() + " bytes");
	// // in Web scenarios it is a good idea to send the size with
	// // "Content-length" HTTP header
	//
	// File output = new File(outputPath);
	// java.io.FileOutputStream fos = new java.io.FileOutputStream(output);
	// fos.write(baos.toByteArray());
	// fos.close();
	//
	// System.out.println(outputPath + "\ndone.");
	//
	// }
	//
}