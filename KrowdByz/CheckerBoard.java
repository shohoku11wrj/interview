import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.crimson.tree.TextNode;
import org.apache.crimson.tree.XmlDocument;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class CheckerBoard {

private Document document;
	
	public CheckerBoard(String fileName) {
		File htmlFile = new File(fileName);
		Element html = null; // doc root element
		// Obtain a SAX-based parser:
		DocumentBuilderFactory factory = 
				DocumentBuilderFactory.newInstance();
		try { // to get a new DocumentBuilder:
			DocumentBuilder builder = 
					factory.newDocumentBuilder();
			
			document = builder.newDocument();
			
			// add a comment:
			Comment comment = 
					document.createComment("KrowdByz Developer skill track assignment -- Checker Board -- by Renjie Weng<rweng@stevens.edu> March 30 2013");
			document.appendChild(comment);
			
			// Create the root element: html
			
			html = document.createElement("html");
			document.appendChild(html);
			
			// Create the head
			Element head = createHead("UTF-8");
			html.appendChild(head);
			
			// Create the body
			Element body = createBody();
			html.appendChild(body);
			
		}
		catch(ParserConfigurationException pce) {
			pce.printStackTrace();
			System.exit(1);
		}
		
		
		try { // to write the HTML document to file fileName
			((XmlDocument) document).write(new FileOutputStream(fileName));
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	private Element createHead(String charset) {
		Element head = document.createElement("head");
		/*StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("<");
		sBuilder.append("meta http-equiv=\"Content-Type\" content=\"text/html; charset=");
		sBuilder.append(charset);
		sBuilder.append("\">\"");
		Text metaData = document.createTextNode(sBuilder.toString());
		head.appendChild(metaData);*/
		Node title = document.createElement("title");
		TextNode titleValue = (TextNode) document.createTextNode("KrowdByz Interview -- Renjie Weng");
		title.appendChild(titleValue);
		head.appendChild(title);
		return head;
	}
	
	private Element createBody() {
		Element body = document.createElement("body");
		// Layout center
		Element center = document.createElement("center");
		
		// Table
		Element table = createTableElement("table","500","100","","");
		Element tr = createTableElement("tr","","","","");
		Element td1 = createTableElement("td","100","","","");
		Element td2 = createTableElement("td","300","","","");
		Element td3 = createTableElement("td","100","","","");
		
		// Nodes in table
		Map<String, String> attr = new HashMap<String,String>();
		// player1
		attr.put("id", "player1");
		attr.put("style", "color:rgb(255,0,0); font-size:120%;");
		Node label = createBasicNode("label",attr,"");
		td1.appendChild(label);
		// player2
		attr = new HashMap<String,String>();
		attr.put("id", "player2");
		attr.put("style", "color:rgb(0,255,0); font-size:120%; text-align: right; display: block;");
		label = createBasicNode("label",attr,"");
		td3.appendChild(label);
		// turn
		attr = new HashMap<String,String>();
		attr.put("id", "turn");
		attr.put("style", "font-size:150%; font-style:bold; text-align: center; display: block;");
		label = createBasicNode("label",attr,"");
		td2.appendChild(label);
		// compose table
		tr.appendChild(td1);
		tr.appendChild(td2);
		tr.appendChild(td3);
		table.appendChild(tr);
		
		// canvas
		Element div = document.createElement("div");
		div.setAttribute("style", "position: relative;");
		attr = new HashMap<String,String>();
		attr.put("id", "canvas");
		attr.put("width", "600");
		attr.put("height", "600");
		attr.put("onClick", "newClick()");
		attr.put("style", "position: relative;");
		Node canvas = createBasicNode("canvas", attr, "");
		div.appendChild(canvas);
		
		// div2
		Element div2 = document.createElement("div");
		Element table2 = createTableElement("table","","","","");
		Element tr1 = createTableElement("tr","","","","");
		Element tr2 = createTableElement("tr","","","","");
		// name1 input
		td1 = createTableElement("td","","","","");
		// player1 text
		TextNode playerLabel = (TextNode) document.createTextNode("player1: ");
		// input area
		attr = new HashMap<String,String>();
		attr.put("type", "text");
		attr.put("id", "name1");
		attr.put("value", "");
		Node name1 = createBasicNode("input", attr, "");
		td1.appendChild(playerLabel);
		td1.appendChild(name1);
		// start button
		td2 = createTableElement("td","","","2","");
		attr = new HashMap<String,String>();
		attr.put("type", "button");
		attr.put("id", "start");
		attr.put("value", "Start!");
		attr.put("onclick", "StartGame();");
		attr.put("style", "width: 150px; height:50px");
		Node start = createBasicNode("input", attr, "");
		td2.appendChild(start);
		// tr1
		tr1.appendChild(td1);
		tr1.appendChild(td2);
		// name2 input
		td1  = createTableElement("td","","","","");
		// player1 text
		playerLabel = (TextNode) document.createTextNode("player2: ");
		// input area
		attr = new HashMap<String,String>();
		attr.put("type", "text");
		attr.put("id", "name2");
		attr.put("value", "");
		Node name2 = createBasicNode("input", attr, "");
		td1.appendChild(playerLabel);
		td1.appendChild(name2);
		// tr2
		tr2.appendChild(td1);
		// compose table2
		table2.appendChild(tr1);
		table2.appendChild(tr2);
		
		// compose div2
		div2.appendChild(table2);
		
		
		
		// compose center
		center.appendChild(table);
		center.appendChild(div);
		center.appendChild(div2);
		
		// Javascript
		attr = new HashMap<String,String>();
		attr.put("src", "./CheckerBoard.js");
		Node js = createBasicNode("script", attr, " ");
		
		// compose body
		body.appendChild(center);
		body.appendChild(js);
		
		return body;
	}
	
	/*
	 * Table element:
	 * table, tr, td
	 */
	private Element createTableElement(String trc, String width, String height, String rowSpan, String columnSpan){
		Element table = document.createElement(trc);
		if(!width.equals("")) {
			table.setAttribute("width", width);
		}
		if(!height.equals("")) {
			table.setAttribute("height", height);
		}
		if(!rowSpan.equals("")) {
			table.setAttribute("rowspan", rowSpan);
		}
		if(!columnSpan.equals("")) {
			table.setAttribute("columnSpan", columnSpan);
		}
		return table;
	}
	
	/*
	 * Basic element: 
	 * label, input, canvas
	 */
	private Node createBasicNode(String tag, Map<String, String> attr, String innerHtml) {
		// create Node
		Element basicNode = document.createElement(tag);
		List<Map.Entry<String,String>> attrList = 
				new ArrayList<Map.Entry<String,String>>(attr.entrySet());
		// set attributes
		for(Entry<String, String> map : attrList) {
			basicNode.setAttribute(map.getKey(), map.getValue());
		}
		// set innerHtml
		if(!innerHtml.equals("")) {
			TextNode text = (TextNode) document.createTextNode(innerHtml);
			basicNode.appendChild(text);
		}
		return basicNode;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CheckerBoard c = new CheckerBoard("./CheckerBoard.html");
	}

}
